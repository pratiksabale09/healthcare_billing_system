package models;

import java.util.Date;

import SQLprovider.PatientProvider;

public class Patient extends PatientProvider {
    private int patientId;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String address;
    private String phoneNumber;
    private String gender;
    private int age;
    private String emailAddress;
    private boolean isInsured;
    private int insuranceCoverTypeId;

    public Patient() {

    }

    public Patient(String firstName, String lastName, Date dateOfBirth, String address,
            String phoneNumber, String gender, int age, String emailAddress, boolean isInsured,
            int insuranceCoverTypeId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.age = age;
        this.emailAddress = emailAddress;
        this.isInsured = isInsured;
        this.insuranceCoverTypeId = insuranceCoverTypeId;
    }

    public Patient(int patientId, String firstName, String lastName, Date dateOfBirth, String address,
            String phoneNumber, String gender, int age, String emailAddress, boolean isInsured,
            int insuranceCoverTypeId) {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.age = age;
        this.emailAddress = emailAddress;
        this.isInsured = isInsured;
        this.insuranceCoverTypeId = insuranceCoverTypeId;
    }

    // Getters and Setters
    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public boolean isInsured() {
        return isInsured;
    }

    public void setInsured(boolean isInsured) {
        this.isInsured = isInsured;
    }

    public int getInsuranceCoverType() {
        return insuranceCoverTypeId;
    }

    public void setInsuranceCoverType(int insuranceCoverTypeId) {
        this.insuranceCoverTypeId = insuranceCoverTypeId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PatientId : ").append(patientId).append("\n");
        sb.append("FirstName : ").append(firstName).append("\n");
        sb.append("LastName : ").append(lastName).append("\n");
        sb.append("DateOfBirth : ").append(dateOfBirth).append("\n");
        sb.append("Address : ").append(address).append("\n");
        sb.append("PhoneNumber : ").append(phoneNumber).append("\n");
        sb.append("Gender : ").append(gender).append("\n");
        sb.append("Age : ").append(age).append("\n");
        sb.append("EmailAddress : ").append(emailAddress).append("\n");
        sb.append("Is Insured : ").append(isInsured).append("\n");
        sb.append("Insurance CoverType Id : ").append(insuranceCoverTypeId).append("\n");
        return sb.toString();
    }

}
