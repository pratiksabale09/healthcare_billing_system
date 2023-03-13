package accountSectionFunctionality;

import java.util.ArrayList;

import SQLprovider.TreatmentProvider;
import common.CommonUtil;
import models.Doctor;
import models.Treatment;
import usageModels.TreatmentUsage;

public class TreatmentFunctions extends TreatmentProvider {
    private void useTreatment() {
        System.out.println("Enter treatment ID:");
        int treatmentID = CommonUtil.scan.nextInt();
        System.out.println("Enter patient ID:");
        int patientID = CommonUtil.scan.nextInt();
        System.out.println("Enter Doctor ID:");
        int doctorID = CommonUtil.scan.nextInt();
        int statusCode = useTreatment(treatmentID, patientID, doctorID);
        if (statusCode == 0) {
            System.out.println("\nInvalid patientId\n");
        } else if (statusCode == 1) {
            System.out.println("\nInvalid treatment Id\n");
        } else if (statusCode == 2) {
            System.out.println("\nInvalid Doctor Id Id\n");
        } else {
            System.out.println("Treatment details are inserted successfully");
        }
    }

    private void showTreatments() {
        ArrayList<Treatment> allTreatments = getAllTreatments();
        System.out.println("+-------------------------------------------------------------------------+");
        System.out.printf("| %14s | %32s | %19s |\n", "Treatment ID", "Treatment Name", "Treatment Charge");
        System.out.println("+-------------------------------------------------------------------------+");

        for (Treatment treatment : allTreatments) {
            System.out.printf("| %14d | %32s | %19.2f |\n",
                    treatment.getTreatmentId(), treatment.getTreatmentName(), treatment.getTreatmentCharge());
        }

        System.out.println("+-------------------------------------------------------------------------+");
    }

    private void getTreatmentsByPatientID() {
        System.out.println("Enter patient ID:");
        int patientID = CommonUtil.scan.nextInt();
        ArrayList<TreatmentUsage> allTreatments = showTreatmentsByPatientID(patientID);
        System.out.println(
                "+-------------------------------------------------------------------------------------------------------------------------------+");
        System.out.printf("| %-10s | %-20s | %-20s | %-20s | %-20s | %-20s |%n", "ID", "Treatment", "Charge", "Doctor",
                "Consultation",
                "Date");
        System.out.println(
                "+-------------------------------------------------------------------------------------------------------------------------------+");
        for (TreatmentUsage tu : allTreatments) {
            System.out.printf("| %-10d | %-20s | %-20.2f | %-20s | %-20.2f | %-20tF | %n",
                    tu.getTreatmentDetailsId(),
                    tu.getTreatmentName(),
                    tu.getTreatmentCharge(),
                    tu.getDoctor().getName(),
                    tu.getDoctor().getConsultationCharge(),
                    tu.getDate());
        }
        System.out.println(
                "+-------------------------------------------------------------------------------------------------------------------------------+");
    }

    private void viewDoctorsAvailable() {

        ArrayList<Doctor> allDoctors = showAvailableDoctors();
        System.out.println(
                "+-------------------------------------------------------------------------------------------------------+");
        System.out.printf("| %-10s | %-20s | %-20s | %-20s | %-10s |\n", "Doctor ID", "Name", "Qualification",
                "Specialty",
                "Consultation Charge");
        System.out.println(
                "+-------------------------------------------------------------------------------------------------------+");
        for (Doctor doctor : allDoctors) {
            System.out.printf("| %-10d | %-20s | %-20s | %-20s | %-20.2f|\n", doctor.getDoctorId(), doctor.getName(),
                    doctor.getQualification(), doctor.getSpecialty(), doctor.getConsultationCharge());
        }
        System.out.println(
                "+-------------------------------------------------------------------------------------------------------+");
    }

    public void chooseOperation() {
        System.out.println(
                "Choose an option:\n1. Get Treatment\n2. Show All Treatments / services\n3. View Treatments Taken by patient ID\n4. View Doctors");
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
