package accountSectionFunctionality;

import java.util.ArrayList;

import SQLprovider.RoomProvider;
import common.CommonUtil;
import models.Room;
import usageModels.RoomUsage;

public class RoomFunctions extends RoomProvider {
    private void allocateRoom() {
        System.out.println("Enter patient ID to allocate room for:");
        int patientId = CommonUtil.scan.nextInt();
        System.out.println("Enter room ID:");
        int roomID = CommonUtil.scan.nextInt();
        System.out.println("Enter Duration");
        int duration = CommonUtil.scan.nextInt();
        int statusCode = allocateRoom(roomID, patientId, duration);
        if (statusCode == 1) {
            System.out.println("\nPatient Id is not in Database\n");
        } else if (statusCode == 2) {
            System.out.println("\nRoom is not Available\n");
        } else if (statusCode == 3) {
            System.out.println("\nRoom is alLocated to Patient: " + patientId + "\n");
        } else {

        }
    }

    private void viewAllRooms() {
        ArrayList<Room> allRooms = viewRooms();
        System.out.println("\nAvailable Rooms");
        System.out.println("+-----------------------------------------------------+");
        System.out.printf("| %-5s | %20s | %20s | %n", "ID", "Room Type", "Charge Per Day");
        System.out.println("+-----------------------------------------------------+");
        for (Room room : allRooms) {
            System.out.printf("| %-5d | %20s | %20.2f |%n", room.getRoomId(), room.getRoomType(),
                    room.getRoomCharge());
        }
        System.out.println("+-----------------------------------------------------+\n");
    }

    private void deAllocateRoom() {
        System.out.println("Enter patient ID to deallocate room:");
        int patientID = CommonUtil.scan.nextInt();
        System.out.println("Enter room ID:");
        int roomID = CommonUtil.scan.nextInt();
        if (removeRoom(patientID, roomID)) {
            System.out.println("\nRoom is Deallocated\n");
        } else {
            System.out.println("\nPatientId or RoomId is Incorrect");
        }
    }

    private void viewAllocatedRooms() {
        System.out.println("Eneter Patient Id");
        int patientID = CommonUtil.scan.nextInt();
        ArrayList<RoomUsage> allocatedRooms = viewAllocatedRooms(patientID);
        System.out.println(
                "+---------------------------------------------------------------------------------------------------+");
        System.out.printf("| %-10s  %-10s  %-10s  %-10s  %-15s  %-15s  %-15s |%n",
                "ID", "Room ID", "Patient ID", "Duration", "Date", "Room Type", "Room Charge");
        System.out.println(
                "+---------------------------------------------------------------------------------------------------+");
        for (RoomUsage roomUsage : allocatedRooms) {

            System.out.printf("| %-10d  %-10d  %-10d  %-10d  %-15s  %-15s  %-15.2f |%n",
                    roomUsage.getroomUsageId(), roomUsage.getRoomId(), roomUsage.getPatientId(),
                    roomUsage.getDurationInDays(), roomUsage.getDate(), roomUsage.getRoomType(),
                    roomUsage.getRoomCharge());

        }
        System.out.println(
                "+---------------------------------------------------------------------------------------------------+\n");

    }

    public void chooseOperation() {
        System.out.println(
                "Choose an option:\n1. Allocate Room\n2. View Available Rooms\n3. Deallocate Allocated Room By Pateint Id\n4. View Rooms allocated to Patient by ID");
        int option = CommonUtil.scan.nextInt();
        switch (option) {
            case 1:
                allocateRoom();
                break;
            case 2:
                viewAllRooms();
                break;
            case 3:
                deAllocateRoom();
                break;
            case 4:
                viewAllocatedRooms();
                break;
            default:
                break;
        }
    }
}
