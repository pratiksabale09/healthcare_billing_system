package SQLprovider;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.DBConnection;
import models.Equipment;

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
                double equipmentChargePerUnit = resultSet.getDouble("EQUIPMENT_CHARGE_PER_UNIT");
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

    protected void allocateEquipments(int patientID, ArrayList<Integer> equipmentIDs) {
        
        // SQL Query goes here
        
    }

    protected void getAllocatedEquipments(int patientID) {
        // SQL Query goes here
    }

    protected void deallocateEquipment(int patientID) {
        // SQL Query goes here
    }

}
