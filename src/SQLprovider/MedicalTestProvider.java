package SQLprovider;

import java.util.ArrayList;

import Connection.Connection;
import models.MedicalTest;

public class MedicalTestProvider extends Connection{
    protected  void conductMedicalTest(int medTestID, int patientID) {
        //SQL Query goes here
    }

    protected  ArrayList<MedicalTest> viewAllMedicalTests() {
        return new ArrayList<>();
        //SQL Query goes here
    }

    protected  ArrayList<MedicalTest> viewAllConductedTests(int patientID) {
        return new ArrayList<>();
        //SQL Query goes here
    }
}
