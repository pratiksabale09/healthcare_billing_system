package models;

public class InsuranceCompany {
    private int insuranceCompanyId;
    private String name;
    private String address;
    private String phoneNumber;
    private String emailAddress;
    private String taxIdNumber;
    
    // Constructor
    public InsuranceCompany()
    {

    }
    
    public InsuranceCompany(int insuranceCompanyId, String name, String address, String phoneNumber, String emailAddress, String taxIdNumber) {
        this.insuranceCompanyId = insuranceCompanyId;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.taxIdNumber = taxIdNumber;
    }
    
    // Getters and setters
    public int getInsuranceCompanyId() {
        return insuranceCompanyId;
    }
    
    public void setInsuranceCompanyId(int insuranceCompanyId) {
        this.insuranceCompanyId = insuranceCompanyId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String getEmailAddress() {
        return emailAddress;
    }
    
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    
    public String getTaxIdNumber() {
        return taxIdNumber;
    }
    
    public void setTaxIdNumber(String taxIdNumber) {
        this.taxIdNumber = taxIdNumber;
    }
}






