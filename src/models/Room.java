package models;

public class Room {
    private int room_id;
    private int healthcare_provider_id;
    private String room_type;
    private double room_charge;
    
    public Room()
    {

    }
    
    public Room(int room_id, int healthcare_provider_id, String room_type, double room_charge) {
        this.room_id = room_id;
        this.healthcare_provider_id = healthcare_provider_id;
        this.room_type = room_type;
        this.room_charge = room_charge;
    }
    
    // Getters and Setters for all fields
    public int getRoomId() {
        return room_id;
    }
    
    public void setRoomId(int room_id) {
        this.room_id = room_id;
    }
    
    public int getHealthcareProviderId() {
        return healthcare_provider_id;
    }
    
    public void setHealthcareProviderId(int healthcare_provider_id) {
        this.healthcare_provider_id = healthcare_provider_id;
    }
    
    public String getRoomType() {
        return room_type;
    }
    
    public void setRoomType(String room_type) {
        this.room_type = room_type;
    }
    
    public double getRoomCharge() {
        return room_charge;
    }
    
    public void setRoomCharge(double room_charge) {
        this.room_charge = room_charge;
    }
}

