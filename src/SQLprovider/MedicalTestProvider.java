package SQLprovider;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import connection.DBConnection;
import models.MedicalTest;
import usageModels.MedicalTestConducted;

public class MedicalTestProvider extends DBConnection{
    protected void conductMedicalTest(int medTestID, int patientID) {

        //query remaining
        
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
 
    protected  ArrayList<MedicalTest> viewAllMedicalTests() {

        //tested

        ArrayList<MedicalTest> medTestList = new ArrayList<MedicalTest>();
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            String sql = "Select * from MEDICAL_TESTS";
            preparedStatement = getConnection().prepareStatement(sql);
            result = preparedStatement.executeQuery();
            MedicalTest medTest = new MedicalTest();
            while(result.next())
            {
                medTest.setMedicalTestId(result.getInt("MEDICAL_TEST_ID"));
                medTest.setTestName(result.getString("TEST_NAME"));
                medTest.setTestCharge(result.getFloat("TEST_CHARGE"));
                medTestList.add(medTest);
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



    protected  ArrayList<MedicalTestConducted> viewAllConductedTests(int patientID) {

        //not tested

        ArrayList<MedicalTestConducted> medTestList = new ArrayList<MedicalTestConducted>();
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            String sql = "Select * from MEDICAL_TEST_CONDUCTED where PATIENT_ID=?";
            preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, patientID);
            result = preparedStatement.executeQuery();
            MedicalTestConducted conductedMedTest = new MedicalTestConducted();
            while(result.next())
            {
                conductedMedTest.setTestConductedId(result.getInt("TEST_CONDUCTED_ID"));
                conductedMedTest.setMedicalTestId(result.getInt("MEDICAL_TEST_ID"));
                conductedMedTest.setPatientId(result.getInt("PATIENT_ID"));
                conductedMedTest.setDate(result.getDate("U_DATE").toString());
                medTestList.add(conductedMedTest);
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
