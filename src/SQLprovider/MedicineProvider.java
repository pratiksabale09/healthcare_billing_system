package SQLprovider;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import connection.DBConnection;
import models.Medicine;
import usageModels.MedicineUsage;
import java.util.Date;

import common.CommonProvider;

public class MedicineProvider extends DBConnection {

    protected int availableMedicineCount(int medicineID) {

        // tested

        int count = -1;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            String sql = "Select UNITS from MEDICINE_DETAILS where MEDICINE_ID=?";
            preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, medicineID);
            result = preparedStatement.executeQuery();
            while (result.next()) {
                count = result.getInt("UNITS");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }

                if (result != null) {
                    result.close();
                }
                closeConnection();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return count;
        // get available medicines count from medicine table
    }

    private int isValidMedicine(int medicineId, int requiredUnits) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int medicineCount = 0;
        try {
            String checkMedicine = "SELECT COUNT(MEDICINE_ID) FROM MEDICINE_DETAILS WHERE MEDICINE_ID = ? AND UNITS >= ?";
            preparedStatement = getConnection().prepareStatement(checkMedicine);
            preparedStatement.setInt(1, medicineId);
            preparedStatement.setInt(2, requiredUnits);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            medicineCount = resultSet.getInt(1);
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
        return medicineCount;
    }

    private void updateMedicineCount(int medicineId, int requiredUnits) {
        PreparedStatement preparedStatement = null;
        try {
            String updateMedicine = "UPDATE MEDICINE_DETAILS SET UNITS = (UNITS - ?) WHERE MEDICINE_ID = ?";
            preparedStatement = getConnection().prepareStatement(updateMedicine);
            preparedStatement.setInt(1, requiredUnits);
            preparedStatement.setInt(2, medicineId);
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

    protected int useMedicine(int medicineID, int patientID, int units) {
        int resultCode = 0;
        int patientCount = CommonProvider.isValidPatient(patientID);
        if (patientCount <= 0) {
            resultCode = 0;
            return resultCode;
        }
        int medicineCount = isValidMedicine(medicineID, units);
        if (medicineCount <= 0) {
            resultCode = 1;
            return resultCode;
        }

        PreparedStatement preparedStatement = null;
        try {
            String sql = "INSERT INTO MEDICINE_USAGE (MEDICINE_ID, PATIENT_ID, USAGE_COUNT, U_DATE) VALUES (?,?,?,?)";
            preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, medicineID);
            preparedStatement.setInt(2, patientID);
            preparedStatement.setInt(3, units);
            preparedStatement.setDate(4, new java.sql.Date(new Date().getTime()));
            int rows = preparedStatement.executeUpdate();
            if (rows > 0) {
                resultCode = 2;
            }
            updateMedicineCount(medicineID, units);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
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
        return resultCode;
    }

    protected ArrayList<Medicine> showAllMedicines() {

        // tested

        ArrayList<Medicine> allMedicineList = new ArrayList<Medicine>();
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            String sql = "Select * from MEDICINE_DETAILS";
            preparedStatement = getConnection().prepareStatement(sql);
            result = preparedStatement.executeQuery();
            Medicine medicine = null;
            while (result.next()) {
                medicine = new Medicine();
                medicine.setMedicineId(result.getInt("MEDICINE_ID"));
                medicine.setMedicineName(result.getString("MEDICINE_NAME"));
                medicine.setMedicineChargePerUnit(result.getFloat("MEDICINE_CHARGE_PER_UNIT"));
                medicine.setUnits(result.getInt("UNITS"));
                medicine.setBatchNo(result.getString("BATCH_NO"));
                allMedicineList.add(medicine);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }

                if (result != null) {
                    result.close();
                }
                closeConnection();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return allMedicineList;
    }

    protected ArrayList<MedicineUsage> showMedicinesByPatientID(int patientID) {

        // not tested

        ArrayList<MedicineUsage> medicineUsagesList = new ArrayList<MedicineUsage>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            String query = "SELECT MU.MEDICINE_USAGE_ID, MU.MEDICINE_ID, MU.PATIENT_ID, MU.USAGE_COUNT, MU.U_DATE, " +
                    "MD.MEDICINE_NAME, MD.MEDICINE_CHARGE_PER_UNIT, MD.UNITS, MD.BATCH_NO " +
                    "FROM MEDICINE_USAGE MU " +
                    "INNER JOIN MEDICINE_DETAILS MD ON MU.MEDICINE_ID = MD.MEDICINE_ID " +
                    "WHERE MU.PATIENT_ID = ?";
            preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1, patientID);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int medicineUsageId = resultSet.getInt("MEDICINE_USAGE_ID");
                int medicineId = resultSet.getInt("MEDICINE_ID");
                String medicineName = resultSet.getString("MEDICINE_NAME");
                float medicineChargePerUnit = resultSet.getFloat("MEDICINE_CHARGE_PER_UNIT");
                int units = resultSet.getInt("UNITS");
                String batchNo = resultSet.getString("BATCH_NO");
                int patientId = resultSet.getInt("PATIENT_ID");
                int usageCount = resultSet.getInt("USAGE_COUNT");
                Date date = resultSet.getDate("U_DATE");

                // (int medicineUsageId, int patientId, int usageCount, Date date, int id,
                // String name,
                // double charge, int units, String batch)

                MedicineUsage medicineUsage = new MedicineUsage(medicineUsageId, patientId, usageCount, date,
                        medicineId, medicineName, medicineChargePerUnit, units, batchNo);

                medicineUsagesList.add(medicineUsage);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
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
        return medicineUsagesList;
    }
}
