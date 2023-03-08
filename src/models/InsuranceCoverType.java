package models;

public class InsuranceCoverType extends InsuranceCompany{
    private int insuranceCoverTypeId;
    private String insuranceCoverType;
    private int insuranceCompanyId;
    private double premiumAmount;
    
    // Constructor
    public InsuranceCoverType(int insuranceCoverTypeId, String insuranceCoverType, int insuranceCompanyId, double premiumAmount) {
        this.insuranceCoverTypeId = insuranceCoverTypeId;
        this.insuranceCoverType = insuranceCoverType;
        this.insuranceCompanyId = insuranceCompanyId;
        this.premiumAmount = premiumAmount;
    }
    
    // Getters and setters
    public int getInsuranceCoverTypeId() {
        return insuranceCoverTypeId;
    }
    
    public void setInsuranceCoverTypeId(int insuranceCoverTypeId) {
        this.insuranceCoverTypeId = insuranceCoverTypeId;
    }
    
    public String getInsuranceCoverType() {
        return insuranceCoverType;
    }
    
    public void setInsuranceCoverType(String insuranceCoverType) {
        this.insuranceCoverType = insuranceCoverType;
    }
    
    public int getInsuranceCompanyId() {
        return insuranceCompanyId;
    }
    
    public void setInsuranceCompanyId(int insuranceCompanyId) {
        this.insuranceCompanyId = insuranceCompanyId;
    }
    
    public double getPremiumAmount() {
        return premiumAmount;
    }
    
    public void setPremiumAmount(double premiumAmount) {
        this.premiumAmount = premiumAmount;
    }
}

