package models;

import SQLprovider.EquipmentProvider;

public class Equipment extends EquipmentProvider{
    private int equipment_id;
    private String equipment_name;
    private double equipment_charge_per_unit;
    private String equipment_category;
    private int units;

    public Equipment()
    {
        
    }
    public Equipment(int id, String name, double charge, String category, int units) {
        this.equipment_id = id;
        this.equipment_name = name;
        this.equipment_charge_per_unit = charge;
        this.equipment_category = category;
        this.units = units;
    }

    // Getters and Setters for each field
    public int getEquipmentId() {
        return this.equipment_id;
    }

    public void setEquipmentId(int id) {
        this.equipment_id = id;
    }

    public String getEquipmentName() {
        return this.equipment_name;
    }

    public void setEquipmentName(String name) {
        this.equipment_name = name;
    }

    public double getEquipmentChargePerUnit() {
        return this.equipment_charge_per_unit;
    }

    public void setEquipmentChargePerUnit(double charge) {
        this.equipment_charge_per_unit = charge;
    }

    public String getEquipmentCategory() {
        return this.equipment_category;
    }

    public void setEquipmentCategory(String category) {
        this.equipment_category = category;
    }

    public int getUnits() {
        return this.units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "name: "+equipment_name+" chargeperunit: "+equipment_charge_per_unit+" category: "+equipment_category+" units: "+units;
    }
}

