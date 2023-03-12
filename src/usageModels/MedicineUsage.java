package usageModels;

import java.util.Date;
import models.Medicine;

public class MedicineUsage extends Medicine {
    private int medicineUsageId;
    private int patientId;
    private int usageCount;
    private Date date;

    public MedicineUsage(int medicineUsageId, int patientId, int usageCount, Date date, int id, String name,
            float charge, int units, String batch) {
        super(id, name, charge, units, batch);
        this.medicineUsageId = medicineUsageId;
        this.patientId = patientId;
        this.usageCount = usageCount;
        this.date = date;
    }

    public MedicineUsage() {
    }

    public int getMedicineUsageId() {
        return medicineUsageId;
    }

    public void setMedicineUsageId(int medicineUsageId) {
        this.medicineUsageId = medicineUsageId;
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

    @Override
    public String toString() {
        return "MedicineUsage [medicineUsageId=" + medicineUsageId + ", patientId=" + patientId + ", usageCount="
                + usageCount + ", date=" + date + "]";
    }

}
