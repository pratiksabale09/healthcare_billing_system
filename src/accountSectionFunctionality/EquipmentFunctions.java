package accountSectionFunctionality;

import java.util.ArrayList;

import Common.CommonUtil;
import SQLprovider.EquipmentProvider;
import models.Equipment;

public class EquipmentFunctions extends EquipmentProvider {

    private void allocateEquipment() {
        System.out.println("Enter patient ID to allocate equipments for:");
        int patientId = CommonUtil.scan.nextInt();
        System.out.println("Enter no. of equipments to be allocated:");
        int noOfEquipments = CommonUtil.scan.nextInt();
        System.out.println("Enter equipment IDs:");
        ArrayList<Integer> equipmentIdList = new ArrayList<Integer>();
        for (int count = 0; count < noOfEquipments; count++) {
            System.out.println("Enter ID:");
            int equipId = CommonUtil.scan.nextInt();
            equipmentIdList.add(equipId);
        }
        allocateEquipments(patientId, equipmentIdList);
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
        getAllocatedEquipments(patientID);
    }

    private void deallocateEquipment() {
        System.out.println("Enter patient ID to deallocate equipment:");
        int patientID = CommonUtil.scan.nextInt();
        deallocateEquipment(patientID);
    }

    public void chooseOperation() {
        System.out.println(
                "Choose an option:\n1. Allocate Equipment\n2. View Equipment\n3.  Deallocate Equipment\n4. View Equipment used by patient ID");
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
