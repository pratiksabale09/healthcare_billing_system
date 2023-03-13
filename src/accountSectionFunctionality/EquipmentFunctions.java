package accountSectionFunctionality;

import java.util.ArrayList;

import SQLprovider.EquipmentProvider;
import common.CommonUtil;
import models.Equipment;
import usageModels.EquipmentUsage;

public class EquipmentFunctions extends EquipmentProvider {

    // allocate equipment to a patient by taking patient id and equipment ids
    private void allocateEquipment() {
        System.out.println("Enter patient ID to allocate equipments for:");
        int patientId = CommonUtil.scan.nextInt();
        System.out.println("Enter equipment IDs:");
        int equipmentId = CommonUtil.scan.nextInt();
        System.out.println("Enter Equipment Count");
        int count = CommonUtil.scan.nextInt();
        int statusCode = allocateEquipment(patientId, equipmentId, count);
        if (statusCode == 0) {
            System.out.println("\nInvalid PatientId\n");
        } else if (statusCode == 1) {
            System.out.println("\nInvalid Equipment\n");
        } else if (statusCode == 2) {
            System.out.println("\nEquipment is Already Allocated to patient\n");
        } else if (statusCode == 3) {
            System.out.println("\nEquipment is Allocated to patient\n");
        }
    }

    private void viewAllEquipments() {
        ArrayList<Equipment> equipmentList = getAllEquipments();
        System.out.println(
                "+-------------------------------------------------------------------------------------------------------------+");
        System.out.printf("| %-15s | %-25s | %-20s | %-20s | %-15s |\n", "Equipment ID", "Equipment Name",
                "Charge Per Unit",
                "Category", "Units Available");
        System.out.println(
                "+-------------------------------------------------------------------------------------------------------------+");
        // Iterate over the equipmentList and print each object in a formatted way
        for (Equipment equipment : equipmentList) {
            System.out.printf("| %-15d | %-25s | %-20.2f | %-20s | %-15d |\n", equipment.getEquipmentId(),
                    equipment.getEquipmentName(),
                    equipment.getEquipmentChargePerUnit(), equipment.getEquipmentCategory(), equipment.getUnits());
        }
        System.out.println(
                "+-------------------------------------------------------------------------------------------------------------+");
    }

    private void viewAllocatedEquipments() {
        System.out.println("Enter patient id to view his allocated equipments:");
        int patientID = CommonUtil.scan.nextInt();
        ArrayList<EquipmentUsage> equipments = getAllocatedEquipments(patientID);
        if (equipments == null || equipments.size() == 0) {
            System.out.println("\nEquipments are not allocated to patient Id " + patientID + "\n");
        } else {

            System.out.println(
                    "+---------------------------------------------------------------------------------------------------------------------------------------+");
            System.out.printf("| %-15s | %-15s | %-25s | %-15s | %-15s | %-15s | %-15s |\n",
                    "PatientId", "EquipmentId", "EquipmentName", "UsageCount",
                    "UsageDate", "ChargePerUnit", "Category");
            System.out.println(
                    "+---------------------------------------------------------------------------------------------------------------------------------------+");
            // Print table data
            for (EquipmentUsage eu : equipments) {
                System.out.printf("| %-15d | %-15d | %-25s | %-15d | %-15s | %-15.2f | %-15s |\n",
                        eu.getPatientId(), eu.getEquipmentId(),
                        eu.getEquipmentName(), eu.getUsageCount(), eu.getUsageDate(),
                        eu.getEquipmentChargePerUnit(), eu.getEquipmentCategory());
            }
            System.out.println(
                    "+---------------------------------------------------------------------------------------------------------------------------------------+");
        }
    }

    private void deallocateEquipment() {
        System.out.println("Enter patient ID to deallocate equipment:");
        int patientID = CommonUtil.scan.nextInt();
        System.out.println("Enter patient ID to deallocate equipment:");
        int equipmentId = CommonUtil.scan.nextInt();
        removeEquipment(patientID, equipmentId);
        System.out.println();
    }

    public void chooseOperation() {
        System.out.println(
                "Choose an option:\n1. Allocate Equipment\n2. View All Available Equipments\n3. Deallocate Equipment by Patient ID\n4. View Equipments used by patient ID");
        int option = CommonUtil.scan.nextInt();
        switch (option) {
            case 1:
                allocateEquipment();
                break;
            case 2:
                viewAllEquipments();
                break;
            case 3:
                deallocateEquipment();
                break;
            case 4:
                viewAllocatedEquipments();
            default:
                break;
        }
    }

}
