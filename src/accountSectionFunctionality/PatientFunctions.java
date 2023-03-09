package accountSectionFunctionality;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Common.CommonUtil;
import SQLprovider.PatientProvider;
import models.Patient;

public class PatientFunctions extends PatientProvider{

    private  void addPatient() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        System.out.println("Enter patient first name: ");
        String fname = CommonUtil.scan.next();
        System.out.println("Enter patient last name: ");
        String lname = CommonUtil.scan.next();
        System.out.println("Enter patient DOB(yyyy-MM-dd): ");
        String dobString = CommonUtil.scan.next();
        Date dob = null;
        try {
             dob = sdf.parse(dobString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("Enter patient address: ");
        String address = CommonUtil.scan.next();
        System.out.println("Enter patient phone_number: ");
        String phoneNumber = CommonUtil.scan.next();
        System.out.println("Enter patient gender(M/F): ");
        String gender = CommonUtil.scan.next();
        System.out.println("Enter patient age: ");
        int age = CommonUtil.scan.nextInt();
        System.out.println("Enter patient email: ");
        String email = CommonUtil.scan.next();
        System.out.println("Does patient have health insurance?(Y/N) ");
        boolean isInsured = false;
         String insurance = CommonUtil.scan.next();
         if(insurance.toUpperCase()=="Y")
         {
            isInsured = true;
         }
         int insuranceCoverTypeId=-1;
         if(isInsured)
         {
            System.out.println("Enter Insurance cover type ID of patient:");
            insuranceCoverTypeId = CommonUtil.scan.nextInt();
            //get insurance cover type list, choose one
            //set to patient
        }
        Patient patient = new Patient(fname, lname, dob, address, phoneNumber, gender, age, email, isInsured, insuranceCoverTypeId);
        insert(patient);
    }

    private  void viewPatients() {
       ArrayList<Patient> patientList =  getPatients();
       for(Patient p : patientList)
       {
        System.out.println(p.toString());
       }
    }

    private  void editPatient() {
        System.out.println("Enter patient id:");
        int id = CommonUtil.scan.nextInt();
        update(id);
    }

    private  void removePatient() {
        System.out.println("Enter patient ID:");
        int id = CommonUtil.scan.nextInt();
        delete(id);
    }

    public  void chooseOperation() {
        System.out.println(
                "Choose an option:\n1. Add Patient\n2. View Patient\n3. Edit Patients by ID\n4. Remove Patient(Discharge)\n5. View Patient by ID");
        int option = CommonUtil.scan.nextInt();
        switch (option) {
            case 1:
                addPatient();
                break;
            case 2:
                viewPatients();
                break;
            case 3:
                editPatient();
                break;
            case 4:
                removePatient();
                break;
            case 5:
                viewPatients();
            default:
                break;
        }
    }
}
