package models;
public class Doctor {
    private int doctorId;
    private String name;
    private String qualification;
    private String specialty;
    private double consultationCharge;
    
    public Doctor(int doctorId, String name, String qualification, String specialty, double consultationCharge) {
        this.doctorId = doctorId;
        this.name = name;
        this.qualification = qualification;
        this.specialty = specialty;
        this.consultationCharge = consultationCharge;
    }
    
    // getters and setters for all instance variables
    public int getDoctorId() {
        return doctorId;
    }
    
    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getQualification() {
        return qualification;
    }
    
    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
    
    public String getSpecialty() {
        return specialty;
    }
    
    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
    
    public double getConsultationCharge() {
        return consultationCharge;
    }
    
    public void setConsultationCharge(double consultationCharge) {
        this.consultationCharge = consultationCharge;
    }

    @Override
    public String toString() {
        return "name: "+name+" qualification: "+qualification+" speciality: "+specialty+" consultaion charge: "+consultationCharge;
    }
}

