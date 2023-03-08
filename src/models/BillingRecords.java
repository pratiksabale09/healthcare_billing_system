package models;

public class BillingRecords {
    
    // Instance variables
    private int billId;
    private String billDate;
    
    // Constructor
    public BillingRecords(int billId, String billDate) {
        this.billId = billId;
        this.billDate = billDate;
    }
    
    // Getters and setters
    public int getBillId() {
        return billId;
    }
    
    public void setBillId(int billId) {
        this.billId = billId;
    }
    
    public String getBillDate() {
        return billDate;
    }
    
    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }
    
    // Override toString method for printing bill details
    @Override
    public String toString() {
        return "Bill ID: " + billId + "\n" +
               "Bill Date: " + billDate + "\n";
    }
}

