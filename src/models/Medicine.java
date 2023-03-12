package models;

import SQLprovider.MedicineProvider;

public class Medicine extends MedicineProvider{
    private int medicineId;
    private String medicineName;
    private float medicineChargePerUnit;
    private int units;
    private String batchNo;
    
    public Medicine()
    {
        
    }
    public Medicine(int id, String name, float charge, int units, String batch) {
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
    
    public float getMedicineChargePerUnit() {
        return medicineChargePerUnit;
    }
    public void setMedicineChargePerUnit(float medicineChargePerUnit) {
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
    @Override
    public String toString() {
        return "Medicine ID: "+medicineId+"     Medicine Name: "+medicineName+"    Medicine Charge Per Unit: "+medicineChargePerUnit+"     Available Units: "+units+"   Batch Number: "+batchNo;
    }
}

