package edu.uoc.locuocomotive.model;

import java.util.ArrayList;
import java.util.List;

public class Train {
    private int id;
    private String model;
    private int[] cars;
    private List<Car> carsList = new ArrayList<>();
    private Car car;

    public Train(int id, String model, int... cars) {
        setId(id);
        setModel(model);
        setCars(cars);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int[] getCars() {
        return cars;
    }

    public void setCars(int[] cars) {
        this.cars = cars;
    }

    public List<Car> getCarsList() {
        return carsList;
    }

    public void addCar() {
        for (int j = 0; j < this.getCars().length; j++) {
            int[] numSeatsPerCar = new int[this.getCars().length];
            int i = 0;
            for (int seat : this.getCars()) {
                numSeatsPerCar[i] = seat;
                i++;
            }
            this.car = new Car("C" + (j + 1), numSeatsPerCar[j], this.getId());
            carsList.add(new Car("C" + (j + 1), numSeatsPerCar[j], this.getId()));
        }

    }
}
