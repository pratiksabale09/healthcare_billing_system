package models;

public class MedicalTest {
    private int medicalTestId;
    private String testName;
    private double testCharge;
    
    public MedicalTest(int medicalTestId, String testName, double testCharge) {
        this.medicalTestId = medicalTestId;
        this.testName = testName;
        this.testCharge = testCharge;
    }
    
    public int getMedicalTestId() {
        return medicalTestId;
    }
    
    public String getTestName() {
        return testName;
    }
    
    public double getTestCharge() {
        return testCharge;
    }
    
    public void setMedicalTestId(int medicalTestId) {
        this.medicalTestId = medicalTestId;
    }
    
    public void setTestName(String testName) {
        this.testName = testName;
    }
    
    public void setTestCharge(double testCharge) {
        this.testCharge = testCharge;
    }
}

