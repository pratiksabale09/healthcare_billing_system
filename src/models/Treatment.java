package models;
import SQLprovider.TreatmentProvider;

public class Treatment extends TreatmentProvider{
    private int treatment_id;
    private String treatment_name;
    private double treatment_charge;

    public Treatment()
    {

    }
    public Treatment(int treatment_id, String treatment_name, double treatment_charge)
    {
        this.treatment_id = treatment_id;
        this.treatment_name = treatment_name;
        this.treatment_charge = treatment_charge;
    }
    
    public int getTreatmentId() {
        return treatment_id;
    }
    
    public void setTreatmentId(int treatment_id) {
        this.treatment_id = treatment_id;
    }
    
    public String getTreatmentName() {
        return treatment_name;
    }
    
    public void setTreatmentName(String treatment_name) {
        this.treatment_name = treatment_name;
    }
    
    public double getTreatmentCharge() {
        return treatment_charge;
    }
    
    public void setTreatmentCharge(double treatment_charge) {
        this.treatment_charge = treatment_charge;
    }
}

