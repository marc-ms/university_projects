package edu.uoc.locuocomotive.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Ticket {

    private String passport;
    private String routeId;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private double cost;
    private int originStationId;
    private int destinationStationId;
    private String selectedSeatType;
    private int currentStationId;
    private CarSeat seat;
    private List<String> assignedSeatName = new ArrayList<>();


    public Ticket(String passport, String routeId, LocalTime departureTime, LocalTime arrivalTime, double cost, int originStationId, int destinationStationId, String selectedSeatType) {
        setPassport(passport);
        setRouteId(routeId);
        setDepartureTime(departureTime);
        setArrivalTime(arrivalTime);
        setCost(cost);
        setOriginStationId(originStationId);
        setDestinationStationId(destinationStationId);
        setSelectedSeatType(selectedSeatType);
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getOriginStationId() {
        return originStationId;
    }

    public void setOriginStationId(int originStationId) {
        this.originStationId = originStationId;
    }

    public int getDestinationStationId() {
        return destinationStationId;
    }

    public void setDestinationStationId(int destinationStationId) {
        this.destinationStationId = destinationStationId;
    }

    public String getSelectedSeatType() {
        return selectedSeatType;
    }

    public void setSelectedSeatType(String selectedSeatType) {
        this.selectedSeatType = selectedSeatType;
    }

    public List<CarSeat> getSeats(ArrayList<Train> trainsList, int trainId) {
        List<CarSeat> seats = new ArrayList<>();
        for (Train train : trainsList) {
            if (trainId == train.getId()) {
                for (Car car : train.getCarsList()) {
                    seats.addAll(car.getSeatsList());
                }
            }

        }
        return seats;
    }


    public CarSeat assignFirstSeat(ArrayList<Train> trainsList, int trainId, String seatClass) {
        CarSeat firstSeat;

        firstSeat = getSeats(trainsList, trainId).stream().filter(seat -> !seat.getOccupied() && seat.getType().toString().equals(seatClass)).findFirst().get();
        firstSeat.setOccupied(true);
        assignedSeatName.add(firstSeat.getName());
        seat = firstSeat;
        return seat;
    }

    public int getCurrentStationId() {
        return currentStationId;
    }

    public void setCurrentStationId(int currentStationId) {
        this.currentStationId = currentStationId;
    }

    public String getDepartureStationName(ArrayList<Station> stationsList, int departureStationId) {
        String departureStationName = "";
        for (Station station : stationsList) {
            if (departureStationId == station.getId()) {
                departureStationName = station.getName();
            }
        }
        return departureStationName;
    }

    public String getArrivalStationName(ArrayList<Station> stationsList, int arrivalStationId) {
        String arrivalStationName = "";
        for (Station station : stationsList) {
            if (arrivalStationId == station.getId()) {
                arrivalStationName = station.getName();
            }
        }
        return arrivalStationName;
    }

    public int getTrainId(ArrayList<Route> routesList, String routeId) {
        int trainId = 0;
        for (Route route : routesList) {
            if (Objects.equals(routeId, route.getId())) {
                trainId = route.getTrainId();
            }
        }
        return trainId;
    }

    public String getSeatName() {
        return assignedSeatName.get(0);
    }
}
