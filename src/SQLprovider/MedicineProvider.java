package SQLprovider;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import connection.DBConnection;
import models.Medicine;
import usageModels.MedicineUsage;

public class MedicineProvider extends DBConnection{

    protected int availableMedicineCount(int medicineID)
    {
        int count = -1;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            String sql = "Select UNITS from MEDICINE_DETAILS where MEDICINE_ID=?";
            preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, medicineID);
            result = preparedStatement.executeQuery();
            while(result.next())
            {
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
        //get available medicines count from medicine table
    }

    protected  void useMedicine(int medicineID, int patientID) {

        PreparedStatement preparedStatement = null;
        try {
            String sql = "";
            preparedStatement = getConnection().prepareStatement(sql);
            int rows = preparedStatement.executeUpdate();

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
    }

    protected  ArrayList<Medicine> showAllMedicines() {
        ArrayList<Medicine> allMedicineList = new ArrayList<Medicine>();
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            String sql = "Select * from MEDICINE_DETAILS";
            preparedStatement = getConnection().prepareStatement(sql);
            result = preparedStatement.executeQuery();
            Medicine medicine = new Medicine();
            while(result.next())
            {
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

    protected  ArrayList<MedicineUsage> showMedicinesByPatientID(int patientID) {

        //not tested

        ArrayList<MedicineUsage> medTestList = new ArrayList<MedicineUsage>();
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            String sql = "Select * from MEDICINE_USAGE where PATIENT_ID=?";
            preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, patientID);
            result = preparedStatement.executeQuery();
            MedicineUsage usedMedicine = new MedicineUsage();
            while(result.next())
            {
                usedMedicine.setMedicineSegmentId(result.getInt("MEDICINE_USAGE_ID"));
                usedMedicine.setMedicineId(result.getInt("MEDICINE_ID"));
                usedMedicine.setPatientId(result.getInt("PATIENT_ID"));
                usedMedicine.setUsageCount(result.getInt("USAGE_COUNT"));
                usedMedicine.setDate(result.getDate("U_DATE"));
                medTestList.add(usedMedicine);
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
        return medTestList;
    }
}
