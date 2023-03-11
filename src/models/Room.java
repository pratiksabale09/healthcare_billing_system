package models;

import SQLprovider.RoomProvider;

public class Room extends RoomProvider {
    private int roomId;
    private String roomType;
    private double roomCharge;
    private boolean isAvailable;

    public Room(int roomId, String roomType, double roomCharge, boolean isAvailable) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.roomCharge = roomCharge;
        this.isAvailable = isAvailable;
    }

    public Room() {

    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public double getRoomCharge() {
        return roomCharge;
    }

    public void setRoomCharge(double roomCharge) {
        this.roomCharge = roomCharge;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("roomId : ").append(roomId)
                .append("\n")
                .append("roomType : ").append(roomType)
                .append("\n")
                .append("roomCharge : ").append(roomCharge)
                .append("\n")
                .append("isAvailable : ").append(isAvailable)
                .append("\n");
        return sb.toString();
    }

}
