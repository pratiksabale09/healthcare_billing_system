package models;

import SQLprovider.EquipmentProvider;

public class Equipment extends EquipmentProvider {
    private int equipmentId;
    private String equipmentName;
    private float equipmentChargePerUnit;
    private String equipmentCategory;
    private int units;

    public Equipment(int equipmentId, String equipmentName, float equipmentChargePerUnit, String equipmentCategory,
            int units) {
        this.equipmentId = equipmentId;
        this.equipmentName = equipmentName;
        this.equipmentChargePerUnit = equipmentChargePerUnit;
        this.equipmentCategory = equipmentCategory;
        this.units = units;
    }

    public Equipment() {

    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public double getEquipmentChargePerUnit() {
        return equipmentChargePerUnit;
    }

    public void setEquipmentChargePerUnit(float equipmentChargePerUnit) {
        this.equipmentChargePerUnit = equipmentChargePerUnit;
    }

    public String getEquipmentCategory() {
        return equipmentCategory;
    }

    public void setEquipmentCategory(String equipmentCategory) {
        this.equipmentCategory = equipmentCategory;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    @Override
    public String toString() {
        return "Equipment [equipmentId=" + equipmentId + ", equipmentName=" + equipmentName
                + ", equipmentChargePerUnit=" + equipmentChargePerUnit + ", equipmentCategory=" + equipmentCategory
                + ", units=" + units + "]";
    }
}
