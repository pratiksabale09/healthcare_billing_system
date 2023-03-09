package accountSectionFunctionality;

import java.util.ArrayList;

import Common.CommonUtil;
import SQLprovider.EquipmentProvider;

public class EquipmentFunctions extends EquipmentProvider{


    private  void allocateEquipment() {
        System.out.println("Enter patient ID to allocate equipments for:");
        int patientId = CommonUtil.scan.nextInt();
        System.out.println("Enter no. of equipments to be allocated:");
        int noOfEquipments = CommonUtil.scan.nextInt();
        System.out.println("Enter equipment IDs:");
        ArrayList<Integer> equipmentIdList = new ArrayList<Integer>();
        for(int count = 0;count<noOfEquipments;count++)
        {
            System.out.println("Enter ID:");
            int equipId = CommonUtil.scan.nextInt();
            equipmentIdList.add(equipId);
        }
        allocateEquipments(equipmentIdList, patientId);
    }


    private void viewAllEquipments() {
        viewAllEquipments();
    }


    private  void viewAllocatedEquipments() {
        System.out.println("Enter patient id to view his allocated equipments:");
        int patientID = CommonUtil.scan.nextInt();
        viewAllocatedEquipments(patientID);
    }


    private  void deallocateEquipment() {
        System.out.println("Enter patient ID to deallocate equipment:");
        int patientID = CommonUtil.scan.nextInt();
        deallocateEquipment(patientID);
    }


    public  void chooseOperation() {
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
