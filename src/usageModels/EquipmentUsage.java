package usageModels;

import java.util.Date;

import models.Equipment;

public class EquipmentUsage extends Equipment{
    private int equipmentUsageId;
    private int patientId;
    private int usageCount;
    private Date usageDate;

    public EquipmentUsage(int equipmentUsageId, int patientId, int usageCount, Date usageDate, int equipmentId, String equipmentName, float equipmentChargePerUnit, String equipmentCategory, int units) {
        super( equipmentId, equipmentName, equipmentChargePerUnit, equipmentCategory, units);
        this.equipmentUsageId = equipmentUsageId;
        this.patientId = patientId;
        this.usageCount = usageCount;
        this.usageDate = usageDate;
    }

    public int getSegmentId() {
        return equipmentUsageId;
    }

    public void setSegmentId(int equipmentUsageId) {
        this.equipmentUsageId = equipmentUsageId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getUsageCount() {
        return usageCount;
    }

    public void setUsageCount(int usageCount) {
        this.usageCount = usageCount;
    }

    public Date getUsageDate() {
        return usageDate;
    }

    public void setUsageCount(Date usageDate) {
        this.usageDate = usageDate;
    }
    
}

