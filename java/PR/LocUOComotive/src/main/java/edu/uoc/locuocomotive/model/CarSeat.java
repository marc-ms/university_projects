package edu.uoc.locuocomotive.model;

public class CarSeat {

    private String name; // it will be carName + seatNumber
    private boolean occupied;
    public CarType type;


    public CarSeat(String name, boolean status, CarType type) {
        setName(name);
        setOccupied(status);
        setType(type);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public CarType getType() {
        return type;
    }

    public void setType(CarType type) {
        this.type = type;
    }

    public void disembarkingPassenger() {
        setOccupied(false);
    }


}
