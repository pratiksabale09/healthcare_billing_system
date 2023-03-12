package usageModels;

import java.util.Date;

import models.MedicalTest;

public class MedicalTestConducted extends MedicalTest {
    private int testConductedId; // primary key
    private int patientId; // foreign key to Patient table
    private Date date; // date of test

    // constructor
    public MedicalTestConducted(int testConductedId, int patientId, Date date, int medicalTestId, String testName,
            float testCharge) {
        super(medicalTestId, testName, testCharge);
        this.testConductedId = testConductedId;
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
        return "Test Conducted ID: " + testConductedId + "      Patient ID: " + patientId + "   Test Conducted Date: "
                + date;
    }
}
