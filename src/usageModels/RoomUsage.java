package usageModels;

import java.util.Date;
import models.Room;

public class RoomUsage extends Room {
    private int roomUsageId;

    private int patientId;
    private int durationInDays;
    private Date date;

    public RoomUsage(int roomUsageId, int patientId, int durationInDays, Date date, int roomId, String roomType,
            double roomCharge, boolean isAvailable) {
        super(roomId, roomType, roomCharge, isAvailable);
        this.roomUsageId = roomUsageId;
        this.patientId = patientId;
        this.durationInDays = durationInDays;
        this.date = date;

    }

    // getters and setters for all fields
    public int getRoomUsageId() {
        return roomUsageId;
    }

    public void setRoomUsageId(int roomUsageId) {
        this.roomUsageId = roomUsageId;
    }

    public int getroomUsageId() {
        return roomUsageId;
    }

    public void setroomUsageId(int roomUsageId) {
        this.roomUsageId = roomUsageId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getDurationInDays() {
        return durationInDays;
    }

    public void setDurationInDays(int durationInDays) {
        this.durationInDays = durationInDays;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "RoomUsage [roomUsageId=" + roomUsageId + ", patientId=" + patientId + ", durationInDays="
                + durationInDays + ", date=" + date + "]";
    }
}
