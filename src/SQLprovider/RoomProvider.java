package SQLprovider;

import java.util.ArrayList;

import connection.DBConnection;
import models.Room;

public class RoomProvider extends DBConnection{
    protected  void allocateRoom(int roomID, int patientId) {
        //SQL Query goes here
    }

    protected  ArrayList<Room> viewRooms() {
         //SQL Query goes here
         return new ArrayList<>();
    }

    protected  void editRoom(int patientID, int roomID) {
        //SQL Query goes here
   }

   protected static void removeRoom(int patientID) {
    //SQL Query goes here
}

    protected static void viewAllocatedRooms(Room room) {
         //SQL Query goes here
    }
}
