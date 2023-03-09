package usageModels;

import java.util.Date;

import models.Treatment;

public class TreatmentUsage extends Treatment{
    
    private int treatmentDetailsId; // Primary key
    private int treatmentId; // Foreign key to Room table
    private int patientId; // Foreign key to Patient table
    private int doctorId; // Foreign key to Doctor table
    private Date date;

    public TreatmentUsage(int treatmentDetailsId, int treatmentId, int patientId, int doctorId, Date date) {
        this.treatmentDetailsId = treatmentDetailsId;
        this.treatmentId = treatmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
    }

    // Getters and setters for all fields
    public int getTreatmentDetailsId() {
        return treatmentDetailsId;
    }

    public void setTreatmentDetailsId(int treatmentDetailsId) {
        this.treatmentDetailsId = treatmentDetailsId;
    }

    public int getTreatmentId() {
        return treatmentId;
    }

    public void setTreatmentId(int treatmentId) {
        this.treatmentId = treatmentId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

