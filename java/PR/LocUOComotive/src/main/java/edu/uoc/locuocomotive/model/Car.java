package edu.uoc.locuocomotive.model;

import java.util.ArrayList;
import java.util.List;

public class Car {
    private String name;

    private int trainId;
    private int numSeatsPerCar;

    private List<CarSeat> seatsList = new ArrayList<>();


    public Car(String name, int numSeatsPerCar, int trainId) {
        setName(name);
        setNumSeatsPerCar(numSeatsPerCar);
        setTrainId(trainId);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public int getNumSeatsPerCar() {
        return numSeatsPerCar;
    }

    public void setNumSeatsPerCar(int numSeatsPerCar) {
        this.numSeatsPerCar = numSeatsPerCar;
    }

    public List<CarSeat> getSeatsList() {
        return seatsList;
    }

    public void addSeats() {
        boolean occupied = false;
        int seat = this.getNumSeatsPerCar();
        if (seat > 0 && seat < 20) {
            for (int i = 1; i <= seat; i++) {
                seatsList.add(new CarSeat(this.getName() + "-" + i, occupied, CarType.FIRST_CLASS));
            }
        } else if (seat >= 20 && seat < 50) {
            for (int i = 1; i <= seat; i++) {
                seatsList.add(new CarSeat(this.getName() + "-" + i, occupied, CarType.SECOND_CLASS));
            }
        } else if (seat >= 50) {
            for (int i = 1; i <= seat; i++) {
                seatsList.add(new CarSeat(this.getName() + "-" + i, occupied, CarType.THIRD_CLASS));
            }
        }

    }

}
