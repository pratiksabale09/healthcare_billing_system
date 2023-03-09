package accountSectionFunctionality;

import java.util.ArrayList;

import Common.CommonUtil;
import SQLprovider.RoomProvider;
import models.Room;

public class RoomFunctions extends RoomProvider{
    private void allocateRoom() {
        System.out.println("Enter patient ID to allocate room for:");
        int patientId = CommonUtil.scan.nextInt();
        System.out.println("Enter room ID:");
        int roomID = CommonUtil.scan.nextInt();

        allocateRoom(roomID, patientId);
    }

    private void viewAllRooms() {
        ArrayList<Room> allRooms = viewRooms();
        for(Room room : allRooms)
        {
            System.out.println(room.toString());
        }
    }

    private  void editRoom() {
        System.out.println("Enter patient ID to edit room:");
        int patientID = CommonUtil.scan.nextInt();
        System.out.println("Enter room ID:");
        int roomID = CommonUtil.scan.nextInt();
        editRoom(patientID, roomID);
    }

    
    private  void deAllocateRoom() {
        System.out.println("Enter patient ID to deallocate room:");
        int patientID = CommonUtil.scan.nextInt();
        removeRoom(patientID);
    }

    private  void viewAllocatedRooms() {
       ArrayList<Room> allocatedRooms = viewRooms();
       for(Room room : allocatedRooms)
       {
        System.out.println(room.toString());
       }
    }

    public void chooseOperation() {
        System.out.println(
                "Choose an option:\n1. Allocate Room\n2. View Available Rooms\n3. Deallocate Allocated Room By Pateint Id\n4. Edit Room allocated to PatientId\n5. View Rooms allocated to Patient by ID");
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
                editRoom();
                break;
            case 5:
                viewAllocatedRooms();
                break;
            default:
                break;
        }
    }
}
