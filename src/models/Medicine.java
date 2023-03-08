package models;

public class Medicine {
    private int medicineId;
    private String medicineName;
    private double medicineChargePerUnit;
    private int units;
    private String batchNo;
    
    public Medicine(int id, String name, double charge, int units, String batch) {
        this.medicineId = id;
        this.medicineName = name;
        this.medicineChargePerUnit = charge;
        this.units = units;
        this.batchNo = batch;
    }
    
    // Getters and setters for each data field
    public int getMedicineId() {
        return medicineId;
    }
    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }
    
    public String getMedicineName() {
        return medicineName;
    }
    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }
    
    public double getMedicineChargePerUnit() {
        return medicineChargePerUnit;
    }
    public void setMedicineChargePerUnit(double medicineChargePerUnit) {
        this.medicineChargePerUnit = medicineChargePerUnit;
    }
    
    public int getUnits() {
        return units;
    }
    public void setUnits(int units) {
        this.units = units;
    }
    
    public String getBatchNo() {
        return batchNo;
    }
    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }
}

