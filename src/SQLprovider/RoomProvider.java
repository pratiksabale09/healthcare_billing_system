package SQLprovider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import connection.DBConnection;
import models.Room;
import usageModels.RoomUsage;

public class RoomProvider extends DBConnection {
    protected int allocateRoom(int roomID, int patientId, int duration) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int resultCode = 0;
        try {
            String checkPatient = "SELECT COUNT(PATIENT_ID) FROM PATIENT WHERE PATIENT_ID = ?";
            String checkRoom = "SELECT COUNT(ROOM_ID) FROM ROOM_DETAILS WHERE ROOM_ID = ? AND IS_AVAILABLE = 1";
            preparedStatement = getConnection().prepareStatement(checkPatient);
            preparedStatement.setInt(1, patientId);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int patientCount = resultSet.getInt(1);

            preparedStatement.close();
            resultSet.close();

            preparedStatement = getConnection().prepareStatement(checkRoom);
            preparedStatement.setInt(1, roomID);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int roomCount = resultSet.getInt(1);

            resultSet.close();

            int insertCount = 0;
            if (patientCount > 0 && roomCount > 0) {
                String sql = "INSERT INTO ROOM_USAGE (ROOM_ID, PATIENT_ID, DURATION_IN_DAYS, U_DATE) VALUES(?,?,?,?)";
                preparedStatement = getConnection().prepareStatement(sql);
                preparedStatement.setInt(1, roomID);
                preparedStatement.setInt(2, patientId);
                preparedStatement.setInt(3, duration);
                preparedStatement.setDate(4, new java.sql.Date(new Date().getTime()));
                insertCount = preparedStatement.executeUpdate();

            }
            // for invalid patient
            if (patientCount == 0) {
                resultCode = 1;
                return resultCode;
            }
            // for invalid room
            if (roomCount == 0) {
                resultCode = 2;
                return resultCode;
            }
            // if row is inserted
            if (insertCount > 0) {
                resultCode = 3;
                String sql = "UPDATE ROOM_DETAILS SET IS_AVAILABLE = 0 WHERE ROOM_ID = ?";
                preparedStatement = getConnection().prepareStatement(sql);
                preparedStatement.setInt(1, roomID);
                preparedStatement.executeUpdate();
                return resultCode;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
                if (getConnection() != null) {
                    getConnection().close();
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return resultCode;
    }

    protected ArrayList<Room> viewRooms() {
        ArrayList<Room> rooms = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            String sql = "SELECT * FROM ROOM_DETAILS WHERE IS_AVAILABLE = 1 ORDER BY ROOM_ID ASC";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            Room r = null;
            while (resultSet.next()) {
                int roomId = resultSet.getInt("ROOM_ID");
                String roomType = resultSet.getString("ROOM_TYPE");
                double roomCharge = resultSet.getDouble("ROOM_CHARGE");
                r = new Room(roomId, roomType, roomCharge, true);
                rooms.add(r);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return rooms;
    }

    protected void editRoom(int patientID, int roomID) {
        // SQL Query goes here
    }

    protected ArrayList<RoomUsage> viewAllocatedRooms(int PateintId) {
        ArrayList<RoomUsage> roomDetailsOfPatient = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT ");
            sb.append("RU.ROOM_USAGE_ID, RU.ROOM_ID, RU.PATIENT_ID, RU.U_DATE, RU.DURATION_IN_DAYS, ");
            sb.append("RD.ROOM_TYPE, RD.ROOM_CHARGE, RD.IS_AVAILABLE ");
            sb.append("FROM ");
            sb.append("ROOM_USAGE RU ");
            sb.append("INNER JOIN ROOM_DETAILS RD ");
            sb.append("ON RD.ROOM_ID = RU.ROOM_ID ");
            sb.append("WHERE ");
            sb.append("RU.PATIENT_ID = ?");
            String sql = sb.toString();
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, PateintId);
            resultSet = preparedStatement.executeQuery();
            RoomUsage roomUsage = null;
            while (resultSet.next()) {
                int roomId = resultSet.getInt("ROOM_ID");
                String roomType = resultSet.getString("ROOM_TYPE");
                double roomCharge = resultSet.getDouble("ROOM_CHARGE");
                boolean isAvailable = resultSet.getBoolean("IS_AVAILABLE");
                int roomUsageId = resultSet.getInt("ROOM_USAGE_ID");
                int patientId = resultSet.getInt("PATIENT_ID");
                int durationInDays = resultSet.getInt("DURATION_IN_DAYS");
                Date date = resultSet.getDate("U_DATE");
                roomUsage = new RoomUsage(roomUsageId, patientId, durationInDays, date, roomId, roomType,
                        roomCharge, isAvailable);
                roomDetailsOfPatient.add(roomUsage);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return roomDetailsOfPatient;
    }

    protected boolean removeRoom(int patientID, int roomID) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        boolean result = false;
        try {
            String sqlDelete = "DELETE FROM ROOM_USAGE WHERE ROOM_ID = ? AND PATIENT_ID = ?";
            String sqlUpdate = "UPDATE ROOM_DETAILS SET IS_AVAILABLE = 1 WHERE ROOM_ID = ?";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sqlDelete);
            preparedStatement.setInt(1, roomID);
            preparedStatement.setInt(2, patientID);
            int c = preparedStatement.executeUpdate();
            preparedStatement.close();
            preparedStatement = connection.prepareStatement(sqlUpdate);
            preparedStatement.setInt(1, roomID);
            preparedStatement.executeUpdate();
            if (c > 0) {
                result = true;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return result;
    }

    protected static void viewAllocatedRooms(Room room) {
        // SQL Query goes here
    }
}
