package Common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.DBConnection;

public final class CommonProvider {
    public static int isValidPatient(int patientId) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int patientCount = 0;
        Connection connection = null;
        try {
            String checkPatient = "SELECT COUNT(PATIENT_ID) FROM PATIENT WHERE PATIENT_ID = ?";
            connection = new DBConnection().getConnection();
            preparedStatement = connection.prepareStatement(checkPatient);
            preparedStatement.setInt(1, patientId);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            patientCount = resultSet.getInt(1);
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
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return patientCount;
    }
}
