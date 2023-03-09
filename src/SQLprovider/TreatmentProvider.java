package SQLprovider;
import java.util.ArrayList;

import Connection.Connection;
import models.Doctor;
import models.Treatment;

public class TreatmentProvider extends Connection{
    protected  void useTreatment(int treatmentID, int patientID) {
        //SQL Query goes here
    }

    protected  ArrayList<Treatment> showAllTreatments() {
        return new ArrayList<>();
         //SQL Query goes here
    }

    protected  ArrayList<Treatment> showTreatmentsByPatientID(int patientID) {
        return new ArrayList<>();
         //SQL Query goes here
    }

    protected ArrayList<Doctor> showAvailableDoctors()
    {
        return new ArrayList<>();
    }
}
