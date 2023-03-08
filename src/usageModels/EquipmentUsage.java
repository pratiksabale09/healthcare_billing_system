package usageModels;

import java.sql.Date;

import models.Equipment;

public class EquipmentUsage extends Equipment{

    // public EquipmentUsage(int id, String name, double charge, String category, int units) {
    //     super(id, name, charge, category, units);
    //     //TODO Auto-generated constructor stub
    // }
    private int segmentId;
    private int equipmentId;
    private int patientId;
    private int usageCount;
    private Date usageDate;

    public EquipmentUsage(int segmentId, int equipmentId, int patientId, int usageCount, Date usageDate) {
        this.segmentId = segmentId;
        this.equipmentId = equipmentId;
        this.patientId = patientId;
        this.usageCount = usageCount;
        this.usageDate = usageDate;
    }

    public int getSegmentId() {
        return segmentId;
    }

    public void setSegmentId(int segmentId) {
        this.segmentId = segmentId;
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
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

