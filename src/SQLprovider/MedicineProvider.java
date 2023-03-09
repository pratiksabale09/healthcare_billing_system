package SQLprovider;
import java.util.ArrayList;

import Connection.Connection;
import models.Medicine;

public class MedicineProvider extends Connection{
    protected  void useMedicine(int medicineID, int patientID) {
        //SQL Query goes here
    }

    protected  ArrayList<Medicine> showAllMedicines() {
        return new ArrayList<>();
         //SQL Query goes here
    }

    protected  ArrayList<Medicine> showMedicinesByPatientID(int patientID) {
        return new ArrayList<>();
         //SQL Query goes here
    }
}
