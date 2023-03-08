package usageModels;

import java.sql.Date;

public class MedicineUsage {
    private int medicineSegmentId;
    private int medicineId;
    private int patientId;
    private int usageCount;
    private Date date;

    public MedicineUsage(int medicineSegmentId, int medicineId, int patientId, int usageCount, Date date) {
        this.medicineSegmentId = medicineSegmentId;
        this.medicineId = medicineId;
        this.patientId = patientId;
        this.usageCount = usageCount;
        this.date = date;
    }

    public int getMedicineSegmentId() {
        return medicineSegmentId;
    }

    public void setMedicineSegmentId(int medicineSegmentId) {
        this.medicineSegmentId = medicineSegmentId;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
