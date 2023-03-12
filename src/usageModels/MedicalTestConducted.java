package usageModels;

import java.sql.Date;

import SQLprovider.MedicalTestProvider;

public class MedicalTestConducted extends MedicalTestProvider{
    private int testConductedId;  // primary key
    private int medicalTestId;   // foreign key to medical_test table
    private int patientId;       // foreign key to Patient table
    private Date date;         // date of test

    // constructor
    public MedicalTestConducted(int testConductedId, int medicalTestId, int patientId, Date date) {
        this.testConductedId = testConductedId;
        this.medicalTestId = medicalTestId;
        this.patientId = patientId;
        this.date = date;
    }

    public MedicalTestConducted() {
    }

    // getters and setters
    public int getTestConductedId() {
        return testConductedId;
    }

    public void setTestConductedId(int testConductedId) {
        this.testConductedId = testConductedId;
    }

    public int getMedicalTestId() {
        return medicalTestId;
    }

    public void setMedicalTestId(int medicalTestId) {
        this.medicalTestId = medicalTestId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Test Conducted ID: "+testConductedId+"      Medical Test ID: "+medicalTestId+"      Patient ID: "+patientId+"   Test Conducted Date: "+date;
    }
}
