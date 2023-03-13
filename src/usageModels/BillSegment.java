package usageModels;

public class BillSegment {
    private int patientId; // foreign key
    private String segmentName;
    private double segmentBillAmount;
    private String status;
    
    // Constructor
    public BillSegment(int patientId, String segmentName, double segmentBillAmount, String status) {
        this.patientId = patientId;
        this.segmentName = segmentName;
        this.segmentBillAmount = segmentBillAmount;
        this.status = status;
    }
    
    // Getter methods
    public int getPatientId() {
        return patientId;
    }
    
    public String getSegmentName() {
        return segmentName;
    }
    
    public double getSegmentBillAmount() {
        return segmentBillAmount;
    }
    
    public String getStatus() {
        return status;
    }
    
    // Setter methods
    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }
    
    public void setSegmentName(String segmentName) {
        this.segmentName = segmentName;
    }
    
    public void setSegmentBillAmount(double segmentBillAmount) {
        this.segmentBillAmount = segmentBillAmount;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Patient ID: "+patientId+"   Segment Name: "+segmentName+"   Segment Bill Amount: "+segmentBillAmount+"   Segment Status:"+status;
    }
}

