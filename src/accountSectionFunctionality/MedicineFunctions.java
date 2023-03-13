package accountSectionFunctionality;

import java.util.ArrayList;

import SQLprovider.MedicineProvider;
import common.CommonUtil;
import models.Medicine;
import usageModels.MedicineUsage;

public class MedicineFunctions extends MedicineProvider {
    private void useMedicine() {
        System.out.println("Enter Medicine ID:");
        int medicineID = CommonUtil.scan.nextInt();
        System.out.println("Enter patient ID:");
        int patientID = CommonUtil.scan.nextInt();
        System.out.println("Enter number of Units Required:");
        int units = CommonUtil.scan.nextInt();
        if (availableMedicineCount(medicineID) > units) {
            System.out.println("Available: " + availableMedicineCount(medicineID));
            int statusCode = useMedicine(medicineID, patientID, units);
            if (statusCode == 0) {
                System.out.println("\nInvalid PatientId\n");
            } else if (statusCode == 1) {
                System.out.println("\nInvalid Medicine\n");
            } else {
                System.out.println("Medicine details updated\n");
            }
        } else {
            System.out.println("Not enough medicines available!");
        }
    }

    private void showMedicines() {
        ArrayList<Medicine> allMedicines = showAllMedicines();
        if (allMedicines.size() > 0) {
            System.out.println(
                    "+-------------------------------------------------------------------------------------------------------------+");
            System.out.printf("| %-15s | %-20s | %-25s | %-20s | %-15s |%n", "Medicine ID", "Medicine Name",
                    "Medicine Charge",
                    "Available Units", "Batch Number");
            System.out.println(
                    "+-------------------------------------------------------------------------------------------------------------+");
            for (Medicine medicine : allMedicines) {
                System.out.printf("| %-15d | %-20s | %-25.2f | %-20d | %-15s |%n", medicine.getMedicineId(),
                        medicine.getMedicineName(),
                        medicine.getMedicineChargePerUnit(), medicine.getUnits(), medicine.getBatchNo());
            }
            System.out.println(
                    "+-------------------------------------------------------------------------------------------------------------+");
        }
    }

    private void getMedicinesByPatientID() {
        System.out.println("Enter patient ID:");
        int patientID = CommonUtil.scan.nextInt();
        ArrayList<MedicineUsage> allMedicines = showMedicinesByPatientID(patientID);
        System.out.println(
                "+--------------------------------------------------------------------------------------------------------------------------------+");
        System.out.printf("| %-5s | %-20s | %-10s | %-10s | %-15s | %-20s | %-10s | %-15s |\n",
                "ID", "Medicine Name", "Charge", "Units", "Batch No", "Patient ID", "UsageCount", "Date");
        System.out.println(
                "+--------------------------------------------------------------------------------------------------------------------------------+");

        // Print each row
        for (MedicineUsage mu : allMedicines) {
            System.out.printf("| %-5d | %-20s | %-10.2f | %-10d | %-15s | %-20d | %-10d | %-15s |\n",
                    mu.getMedicineUsageId(), mu.getMedicineName(), mu.getMedicineChargePerUnit(), mu.getUnits(),
                    mu.getBatchNo(),
                    mu.getPatientId(), mu.getUsageCount(), mu.getDate());
        }
        System.out.println(
                "+--------------------------------------------------------------------------------------------------------------------------------+");
    }

    public void chooseOperation() {
        System.out.println(
                "Choose an option:\n1. Use Medicine\n2. Show all available medicines\n3. Show consumed medicines by patient ID");
        int option = CommonUtil.scan.nextInt();
        switch (option) {
            case 1:
                useMedicine();
                break;
            case 2:
                showMedicines();
                break;
            case 3:
                getMedicinesByPatientID();
                break;
            default:
                break;
        }
    }

}
