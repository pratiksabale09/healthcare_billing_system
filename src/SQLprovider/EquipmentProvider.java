package SQLprovider;

import java.util.ArrayList;

import connection.DBConnection;

public class EquipmentProvider extends DBConnection{
    protected  void allocateEquipments(ArrayList<Integer> equipmentIDs, int patientID) {
        //SQL Query goes here
    }

    protected  void viewEquipments() {
        //SQL Query goes here
    }

    protected  void viewAllocatedEquipments(int patientID) {
        //SQL Query goes here
    }

    protected  void deallocateEquipment(int patientID) {
         //SQL Query goes here
    }

}
