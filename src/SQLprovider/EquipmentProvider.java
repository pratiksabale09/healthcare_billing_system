package SQLprovider;

import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Common.CommonProvider;
import connection.DBConnection;
import models.Equipment;
import usageModels.EquipmentUsage;

public class EquipmentProvider extends DBConnection {
    protected ArrayList<Equipment> getAllEquipments() {
        ArrayList<Equipment> equipments = new ArrayList<>();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String sql = "SELECT * FROM EQUIPMENT_DETAILS WHERE UNITS_AVAILABLE>0 ORDER BY EQUIPMENT_ID ASC";
            preparedStatement = getConnection().prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            Equipment e = null;
            while (resultSet.next()) {
                int equipmentId = resultSet.getInt("EQUIPMENT_ID");
                String equipmentName = resultSet.getString("EQUIPMENT_NAME");
                float equipmentChargePerUnit = resultSet.getFloat("EQUIPMENT_CHARGE_PER_UNIT");
                String equipmentCategory = resultSet.getString("EQUIPMENT_CATEGORY");
                int units = resultSet.getInt("UNITS_AVAILABLE");

                e = new Equipment(equipmentId, equipmentName, equipmentChargePerUnit, equipmentCategory, units);
                equipments.add(e);
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

        return equipments;
    }

    private int isvalidEquipment(int equipmentId, int count) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int equipmentCount = 0;
        try {

            String checkEquipment = "SELECT COUNT(EQUIPMENT_ID) FROM EQUIPMENT_DETAILS WHERE EQUIPMENT_ID = ? AND UNITS_AVAILABLE >= ?";
            preparedStatement = getConnection().prepareStatement(checkEquipment);
            preparedStatement.setInt(1, equipmentId);
            preparedStatement.setInt(2, count);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            equipmentCount = resultSet.getInt(1);
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
                closeConnection();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return equipmentCount;
    }

    private int checkAlreadyExist(int equipmentId, int patientID) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int entryCount = 0;
        try {

            String checkEquipmentEntry = "SELECT COUNT(EQUIPMENT_USAGE_ID) FROM EQUIPMENT_USAGE WHERE EQUIPMENT_ID = ? AND PATIENT_ID = ?";
            preparedStatement = getConnection().prepareStatement(checkEquipmentEntry);
            preparedStatement.setInt(1, equipmentId);
            preparedStatement.setInt(2, patientID);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            entryCount = resultSet.getInt(1);
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
                closeConnection();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return entryCount;
    }

    protected int allocateEquipment(int patientID, int equipmentId, int count) {
        int resultCode = 0;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int insertCount = 0;
        try {
            int patientCount = CommonProvider.isValidPatient(patientID);
            if (patientCount <= 0) {
                resultCode = 0;
                return resultCode;
            }

            int countOfEquipment = isvalidEquipment(equipmentId, count);
            if (countOfEquipment < 0) {
                resultCode = 1;
                return resultCode;
            }

            int countOfEntries = checkAlreadyExist(equipmentId, patientID);
            if (countOfEntries >= 1) {
                resultCode = 2;
                return resultCode;
            }
            if (countOfEntries <= 0) {
                String sql = "INSERT INTO EQUIPMENT_USAGE  (EQUIPMENT_ID, PATIENT_ID, USAGE_COUNT, U_DATE) VALUES(?,?,?,?)";
                preparedStatement = getConnection().prepareStatement(sql);
                preparedStatement.setInt(1, equipmentId);
                preparedStatement.setInt(2, patientID);
                preparedStatement.setInt(3, count);
                preparedStatement.setDate(4, new java.sql.Date(new Date().getTime()));
                insertCount = preparedStatement.executeUpdate();
                updateEquipmentCount(equipmentId, count);
                if (insertCount > 0) {
                    resultCode = 3;
                }
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

    private void updateEquipmentCount(int equipmentID, int count) {
        PreparedStatement preparedStatement = null;
        try {

            String checkEquipment = "UPDATE EQUIPMENT_DETAILS SET UNITS_AVAILABLE = (UNITS_AVAILABLE-?) WHERE EQUIPMENT_ID = ?";
            preparedStatement = getConnection().prepareStatement(checkEquipment);
            preparedStatement.setInt(1, count);
            preparedStatement.setInt(2, equipmentID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                closeConnection();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    protected ArrayList<EquipmentUsage> getAllocatedEquipments(int patientId) {

        ArrayList<EquipmentUsage> equipmentUsageList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT ");
            sb.append("EU.EQUIPMENT_USAGE_ID, EU.EQUIPMENT_ID, EU.PATIENT_ID, EU.USAGE_COUNT, EU.U_DATE, ");
            sb.append("ED.EQUIPMENT_NAME, ED.EQUIPMENT_CHARGE_PER_UNIT, ED.EQUIPMENT_CATEGORY ");
            sb.append("FROM ");
            sb.append("EQUIPMENT_USAGE EU ");
            sb.append("INNER JOIN EQUIPMENT_DETAILS ED ");
            sb.append("ON EU.EQUIPMENT_ID = ED.EQUIPMENT_ID ");
            sb.append("WHERE ");
            sb.append("EU.PATIENT_ID = ?");
            String sql = sb.toString();
            preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, patientId);
            resultSet = preparedStatement.executeQuery();
            EquipmentUsage equipmentUsage = null;
            while (resultSet.next()) {
                int equipmentUsageId = resultSet.getInt("EQUIPMENT_USAGE_ID");
                int equipmentId = resultSet.getInt("EQUIPMENT_ID");
                int usageCount = resultSet.getInt("USAGE_COUNT");
                Date usageDate = resultSet.getDate("U_DATE");
                String equipmentName = resultSet.getString("EQUIPMENT_NAME");
                float equipmentChargePerUnit = resultSet.getFloat("EQUIPMENT_CHARGE_PER_UNIT");
                String equipmentCategory = resultSet.getString("EQUIPMENT_CATEGORY");

                equipmentUsage = new EquipmentUsage(equipmentUsageId, patientId, usageCount, usageDate, equipmentId,
                        equipmentName, equipmentChargePerUnit, equipmentCategory, 10);
                equipmentUsageList.add(equipmentUsage);
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
                closeConnection();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return equipmentUsageList;
    }

    protected int getUsageCount(int patientID, int equipmentId) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int usageCount = 0;
        try {
            String sql = "SELECT USAGE_COUNT FROM EQUIPMENT_USAGE WHERE PATIENT_ID = ? AND EQUIPMENT_ID = ?";
            preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, patientID);
            preparedStatement.setInt(2, equipmentId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                usageCount = resultSet.getInt("USAGE_COUNT");
            }
        } catch (SQLException e) {
            System.out.println("Error getting usage count: " + e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
                closeConnection();
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
        return usageCount;
    }

    protected void removeEquipment(int patientID, int equipmentId) {
        PreparedStatement preparedStatement = null;
        int c = getUsageCount(patientID, equipmentId);
        updateEquipmentCount(equipmentId, (-1 * c));
        try {
            String sql = "DELETE FROM EQUIPMENT_USAGE WHERE PATIENT_ID = ? AND EQUIPMENT_ID = ?";
            preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, patientID);
            preparedStatement.setInt(2, equipmentId);
            preparedStatement.executeUpdate();
            System.out
                    .println("\nEquipment with ID " + equipmentId + " has been removed from patient " + patientID
                            + ".");
        } catch (SQLException e) {
            System.out.println("Error removing equipment: " + e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                closeConnection();
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
    }

}
