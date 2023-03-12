package usageModels;

import java.util.Date;

import models.Doctor;
import models.Treatment;

public class TreatmentUsage extends Treatment{
    private int treatmentDetailsId;
    private int patientId;
    private Doctor doctor;
    private Date date;

    public TreatmentUsage(int treatmentDetailsId, int patientId, Doctor doctor, Date date, int treatment_id, String treatment_name, double treatment_charge) {
        super(treatment_id, treatment_name, treatment_charge);
        this.treatmentDetailsId = treatmentDetailsId;
        this.doctor = doctor;
        this.patientId = patientId;
        this.date = date;
    }

    public int getTreatmentDetailsId() {
        return treatmentDetailsId;
    }

    public void setTreatmentDetailsId(int treatmentDetailsId) {
        this.treatmentDetailsId = treatmentDetailsId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctorId(Doctor doctor) {
        this.doctor = doctor;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
