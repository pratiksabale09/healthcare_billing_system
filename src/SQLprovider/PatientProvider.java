package SQLprovider;

import java.sql.Connection;
import models.Patient;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import connection.DBConnection;

public class PatientProvider extends DBConnection {

    public ArrayList<Patient> getPatients() {
        ArrayList<Patient> patients = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            String sql = "SELECT * FROM PATIENT";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            Patient p = null;
            while (resultSet.next()) {
                p = new Patient(
                        resultSet.getInt("PATIENT_ID"),
                        resultSet.getString("FIRST_NAME"),
                        resultSet.getString("LAST_NAME"),
                        resultSet.getDate("DATE_OF_BIRTH"),
                        resultSet.getString("ADDRESS"),
                        resultSet.getString("PHONE_NUMBER"),
                        resultSet.getString("GENDER"),
                        resultSet.getInt("AGE"),
                        resultSet.getString("EMAIL_ADDRESS"),
                        resultSet.getBoolean("IS_INSURED"),
                        resultSet.getInt("INSURANCE_COVER_ID"));
                patients.add(p);
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

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return patients;
    }

    public int insert(Patient p) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet result = null;
        int key = -1;
        try {
            String sql;
            if (p.isInsured()) {
                sql = "INSERT INTO PATIENT (FIRST_NAME, LAST_NAME, DATE_OF_BIRTH, ADDRESS, PHONE_NUMBER, GENDER, AGE, EMAIL_ADDRESS, IS_INSURED, INSURANCE_COVER_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            } else {
                sql = "INSERT INTO PATIENT (FIRST_NAME, LAST_NAME, DATE_OF_BIRTH, ADDRESS, PHONE_NUMBER, GENDER, AGE, EMAIL_ADDRESS, IS_INSURED) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            }
            connection = getConnection();

            preparedStatement = connection.prepareStatement(sql, new String[] { "PATIENT_ID" });
            preparedStatement.setString(1, p.getFirstName());
            preparedStatement.setString(2, p.getLastName());
            preparedStatement.setDate(3, new java.sql.Date(p.getDateOfBirth().getTime()));
            preparedStatement.setString(4, p.getAddress());
            preparedStatement.setString(5, p.getPhoneNumber());
            preparedStatement.setString(6, p.getGender());
            preparedStatement.setInt(7, p.getAge());
            preparedStatement.setString(8, p.getEmailAddress());
            preparedStatement.setInt(9, p.isInsured() ? 1 : 0);
            if (p.isInsured()) {
                preparedStatement.setInt(10, p.getInsuranceCoverType());
            }

            preparedStatement.executeUpdate();
            result = preparedStatement.getGeneratedKeys();
            result.next();
            key = result.getInt(1);

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

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return key;
    }

    public boolean delete(int id) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        boolean result = false;
        try {
            String sql = "DELETE FROM PATIENT WHERE PATIENT_ID = ?";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            int rowCount = preparedStatement.executeUpdate();
            if (rowCount > 0) {
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

    public Patient getPatientById(int patientId) {
        Patient p = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            String sql = "SELECT * FROM PATIENT WHERE PATIENT_ID = ?";
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, patientId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                p = new Patient(
                        resultSet.getInt("PATIENT_ID"),
                        resultSet.getString("FIRST_NAME"),
                        resultSet.getString("LAST_NAME"),
                        resultSet.getDate("DATE_OF_BIRTH"),
                        resultSet.getString("ADDRESS"),
                        resultSet.getString("PHONE_NUMBER"),
                        resultSet.getString("GENDER"),
                        resultSet.getInt("AGE"),
                        resultSet.getString("EMAIL_ADDRESS"),
                        resultSet.getBoolean("IS_INSURED"),
                        resultSet.getInt("INSURANCE_COVER_ID"));
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

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return p;
    }
}