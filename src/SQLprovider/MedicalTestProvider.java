package SQLprovider;

import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Common.CommonProvider;
import connection.DBConnection;
import models.MedicalTest;
import usageModels.MedicalTestConducted;

public class MedicalTestProvider extends DBConnection {
    private int isValidMedicalTest(int medicalTestId) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int testCount = 0;
        try {
            String checkTest = "SELECT COUNT(MEDICAL_TEST_ID) FROM MEDICAL_TESTS WHERE MEDICAL_TEST_ID = ?";
            preparedStatement = getConnection().prepareStatement(checkTest);
            preparedStatement.setInt(1, medicalTestId);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            testCount = resultSet.getInt(1);
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
        return testCount;
    }

    protected int conductMedicalTest(int medTestID, int patientID) {
        int resultCode = 0;
        int patientCount = CommonProvider.isValidPatient(patientID);
        int testCount = isValidMedicalTest(medTestID);
        if (patientCount <= 0) {
            return resultCode;
        }
        if (testCount <= 0) {
            resultCode = 1;
            return resultCode;
        }

        PreparedStatement preparedStatement = null;
        try {
            String sql = "INSERT INTO MEDICAL_TEST_CONDUCTED (MEDICAL_TEST_ID, PATIENT_ID, U_DATE) VALUES (?,?,?)";
            preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, medTestID);
            preparedStatement.setInt(2, patientID);
            preparedStatement.setDate(3, new java.sql.Date(new Date().getTime()));
            int rows = preparedStatement.executeUpdate();
            if (rows > 0) {
                resultCode = 2;
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
                closeConnection();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return resultCode;
    }

    protected ArrayList<MedicalTest> getAllMedicalTests() {

        // tested

        ArrayList<MedicalTest> medTestList = new ArrayList<MedicalTest>();
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        try {
            String sql = "SELECT * FROM MEDICAL_TESTS";
            preparedStatement = getConnection().prepareStatement(sql);
            result = preparedStatement.executeQuery();
            MedicalTest medTest = null;
            while (result.next()) {
                medTest = new MedicalTest();
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

    protected ArrayList<MedicalTestConducted> viewAllConductedTests(int patientID) {

        // not tested

        ArrayList<MedicalTestConducted> medTestList = new ArrayList<MedicalTestConducted>();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append("SELECT MT.MEDICAL_TEST_ID, MT.TEST_NAME, MT.TEST_CHARGE, ");
            queryBuilder.append("MTC.TEST_CONDUCTED_ID, MTC.PATIENT_ID, MTC.U_DATE ");
            queryBuilder.append("FROM MEDICAL_TESTS MT ");
            queryBuilder.append("INNER JOIN MEDICAL_TEST_CONDUCTED MTC ");
            queryBuilder.append("ON MT.MEDICAL_TEST_ID = MTC.MEDICAL_TEST_ID ");
            queryBuilder.append("WHERE MTC.PATIENT_ID = ?");
            String sqlQuery = queryBuilder.toString();
            preparedStatement = getConnection().prepareStatement(sqlQuery);

            preparedStatement.setInt(1, patientID);
            rs = preparedStatement.executeQuery();
            MedicalTestConducted conductedMedTest = null;
            while (rs.next()) {
                int testConductedId = rs.getInt("TEST_CONDUCTED_ID");
                int patientId = rs.getInt("PATIENT_ID");
                Date date = rs.getDate("U_DATE");
                int medicalTestId = rs.getInt("MEDICAL_TEST_ID");
                String testName = rs.getString("TEST_NAME");
                float testCharge = rs.getFloat("TEST_CHARGE");
                conductedMedTest = new MedicalTestConducted(testConductedId, patientId, date,
                        medicalTestId, testName, testCharge);
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

                if (rs != null) {
                    rs.close();
                }
                closeConnection();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return medTestList;
    }
}
