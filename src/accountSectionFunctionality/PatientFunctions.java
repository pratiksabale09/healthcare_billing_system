package accountSectionFunctionality;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Common.CommonUtil;
import SQLprovider.PatientProvider;
import models.Patient;

public class PatientFunctions extends PatientProvider {

    private void addPatient() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // taking input from user
        System.out.print("Enter patient first name: ");
        String fname = CommonUtil.scan.next();
        System.out.print("Enter patient last name: ");
        String lname = CommonUtil.scan.next();
        System.out.print("Enter patient DOB(yyyy-MM-dd): ");
        String dobString = CommonUtil.scan.next();
        Date dob = null;
        try {
            dob = sdf.parse(dobString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.print("Enter patient address:\t");
        String address = CommonUtil.scan.next();
        System.out.print("Enter patient phone_number:\t");
        String phoneNumber = CommonUtil.scan.next();
        System.out.print("Enter patient gender(M/F):\t");
        String gender = CommonUtil.scan.next();
        System.out.print("Enter patient age:\t");
        int age = CommonUtil.scan.nextInt();
        System.out.print("Enter patient email:\t");
        String email = CommonUtil.scan.next();
        System.out.print("Does patient have health insurance?(Y/N) \t");
        boolean isInsured = false;
        String insurance = CommonUtil.scan.next();
        if (insurance.toUpperCase().equals("Y")) {
            isInsured = true;
        }
        int insuranceCoverTypeId = -1;
        if (isInsured) {
            System.out.print("Enter Insurance cover type ID of patient:");
            insuranceCoverTypeId = CommonUtil.scan.nextInt();
        }

        // create patient Object
        Patient patient = new Patient(fname, lname, dob, address, phoneNumber, gender, age, email, isInsured,
                insuranceCoverTypeId);
        // insert patient to db
        int id = insert(patient);
        System.out.println("\nPatient Detaiils are saved to database\nID: " + id + "\n");
    }

    private void viewPatients() {
        ArrayList<Patient> patientList = getPatients();
        System.out.println(
                "+-----------------------------------------------------------------------------------------------------------+");
        System.out.printf("| %-10s | %-20s | %-20s | %-15s | %-10s | %-15s |\n", "ID", "First Name", "Last Name",
                "Gender", "Age",
                "Phone Number");
        System.out.println(
                "+-----------------------------------------------------------------------------------------------------------+");
        // Print table rows
        for (Patient p : patientList) {
            System.out.printf("| %-10d | %-20s | %-20s | %-15s | %-10d | %-15s |\n", p.getPatientId(), p.getFirstName(),
                    p.getLastName(),
                    p.getGender(), p.getAge(), p.getPhoneNumber());
        }
        System.out.println(
                "+-----------------------------------------------------------------------------------------------------------+\n");
    }

    private void viewPatientByID() {
        System.out.println("Enter patient ID:\n");
        int id = CommonUtil.scan.nextInt();
        Patient p = getPatientById(id);
        if (p == null) {
            System.out.println("\nPatient is not in Database associated with this Id\n");
        } else {
            System.out.println("\nPatient Details with ID: " + id);
            System.out.println(p);
        }
    }

    private void removePatient() {
        System.out.println("Enter patient ID:");
        int id = CommonUtil.scan.nextInt();
        if (delete(id)) {
            System.out.println("Patient is deleted\n");
        } else {
            System.out.println("Patient is not in Database asscociated with this Id");
        }
    }

    public void chooseOperation() {
        System.out.println(
                "Choose an option:\n1. Add Patient\n2. View Patient\n3. Remove Patient\n4. View Patient by ID\n");
        int option = CommonUtil.scan.nextInt();
        switch (option) {
            case 1:
                addPatient();
                break;
            case 2:
                viewPatients();
                break;
            case 3:
                removePatient();
                break;
            case 4:
                viewPatientByID();
            default:
                break;
        }
    }
}
