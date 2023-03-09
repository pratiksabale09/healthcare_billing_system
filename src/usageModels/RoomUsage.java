package usageModels;

import java.util.Date;
import SQLprovider.RoomProvider;
public class RoomUsage extends RoomProvider{
    private int roomSegmentId;
    private int roomId;
    private int patientId;
    private int durationInDays;
    private Date date;

    public RoomUsage(int roomSegmentId, int roomId, int patientId, int durationInDays, Date date) {
        this.roomSegmentId = roomSegmentId;
        this.roomId = roomId;
        this.patientId = patientId;
        this.durationInDays = durationInDays;
        this.date = date;
    }

    // getters and setters for all fields

    public int getRoomSegmentId() {
        return roomSegmentId;
    }

    public void setRoomSegmentId(int roomSegmentId) {
        this.roomSegmentId = roomSegmentId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
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
}
