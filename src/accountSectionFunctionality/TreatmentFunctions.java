package accountSectionFunctionality;

import java.util.ArrayList;

import Common.CommonUtil;
import SQLprovider.TreatmentProvider;
import models.Doctor;
import models.Treatment;

public class TreatmentFunctions extends TreatmentProvider{
    private  void useTreatment() {
        System.out.println("Enter treatment ID:");
        int treatmentID = CommonUtil.scan.nextInt();
        System.out.println("Enter patient ID:");
        int patientID = CommonUtil.scan.nextInt();
        useTreatment(treatmentID, patientID);
    }
    private void showTreatments() {
        ArrayList<Treatment> allTreatments =  showAllTreatments();
        for(Treatment treatment: allTreatments)
        {
            System.out.println(treatment.toString());
        }
    }

    private void getTreatmentsByPatientID()
    {
        System.out.println("Enter patient ID:");
        int patientID = CommonUtil.scan.nextInt();
        ArrayList<Treatment> allTreatments =  showTreatmentsByPatientID(patientID);
        for(Treatment treatment: allTreatments)
        {
            System.out.println(treatment.toString());
        }
    }

    private void viewDoctorsAvailable()
    {
      
        ArrayList<Doctor> allDoctors =  showAvailableDoctors();
        for(Doctor doctor: allDoctors)
        {
            System.out.println(doctor.toString());
        }
    }

    public  void chooseOperation() {
        System.out.println(
                "Choose an option:\n1. Use Treatment\n2. Show All Treatments\n3. View Treatments by patient ID\n4. View Doctors");
        int option = CommonUtil.scan.nextInt();
        switch (option) {
            case 1:
                useTreatment();
                break;
            case 2:
                showTreatments();
                break;
          
            case 3:
                getTreatmentsByPatientID();
                break;
            case 4:
                viewDoctorsAvailable();
                break;
            default:
                break;
        }
    }
}
