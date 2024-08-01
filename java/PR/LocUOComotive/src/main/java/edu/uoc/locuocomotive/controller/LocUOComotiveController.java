package edu.uoc.locuocomotive.controller;

import edu.uoc.locuocomotive.model.*;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class LocUOComotiveController {
    ArrayList<Station> stationsList = new ArrayList<>();
    ArrayList<Train> trainsList = new ArrayList<>();
    ArrayList<Route> routesList = new ArrayList<>();
    HashMap<String, Passenger> registeredPassengers = new HashMap<>();
    List<Ticket> ticketsList = new ArrayList<>();


    private Train train;


    private Passenger passenger;
    private Ticket ticket;
    private Route route;
    private int currentStationId;


    public LocUOComotiveController(String stationsFile, String routesFile, String trainsFile) {
        loadStations("stations.txt");
        loadTrains("trains.txt");
        loadRoutes("routes.txt");
        currentStationId = stationsList.get(0).getId();
    }

    private void loadStations(String stationsFile) {
        // Get the file from the resources folder
        InputStream is = getClass().getResourceAsStream("/data/" + stationsFile);

        if (is == null) {
            throw new NullPointerException("Cannot find resource file " + stationsFile);
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");

                // Create the station and add it to the list of stations
                addStation(Integer.parseInt(parts[0]), parts[1], parts[2], Integer.parseInt(parts[3]), parts[4], parts[5], Integer.parseInt(parts[6]), Integer.parseInt(parts[7]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadRoutes(String routesFile) {
        // Get the file from the resources folder
        InputStream is = getClass().getResourceAsStream("/data/" + routesFile);

        if (is == null) {
            throw new NullPointerException("Cannot find resource file " + routesFile);
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split by character :
                String[] parts = line.split("=");
                String[] parts2 = parts[0].split("\\|");

                // Create the route and add it to the list of routes
                addRoute(parts2[0], Integer.parseInt(parts2[1]), parts[1].split("\\|"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadTrains(String trainsFile) {
        // Get the file from the resources folder
        InputStream is = getClass().getResourceAsStream("/data/" + trainsFile);

        if (is == null) {
            throw new NullPointerException("Cannot find resource file " + trainsFile);
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");

                int[] seatsPerCar = new int[parts.length - 2];

                for (int i = 2; i < parts.length; i++) {
                    seatsPerCar[i - 2] = Integer.parseInt(parts[i]);
                }

                addTrain(Integer.parseInt(parts[0]), parts[1], seatsPerCar);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addStation(int id, String name, String city, int openingYear, String type, String image, int positionX, int positionY) {
        stationsList.add(new Station(id, name, city, openingYear, type, image, positionX, positionY));
    }

    public void addRoute(String id, int trainId, String... stationsAndTimes) {
        for (String stationAndTime : stationsAndTimes) {
            String[] part1 = stationAndTime.split("\\[");
            String[] part2 = part1[1].split(",");
            String[] arrivalTime = part2[1].split("]");
            route = new Route(id, trainId, Integer.parseInt(part1[0]), part2[0], arrivalTime[0]);
            routesList.add(new Route(id, trainId, Integer.parseInt(part1[0]), part2[0], arrivalTime[0]));
        }
    }

    public void addTrain(int id, String model, int... cars) {
        train = new Train(id, model, cars);
        trainsList.add(train);
        train.addCar();
        for (Car c : train.getCarsList()) {
            c.addSeats();
        }
    }

    public List<String> getStationsInfo() {
        List<String> stationsInformation = new ArrayList<>();
        for (Station value : stationsList) {
            stationsInformation.add(value.getId() + "|" + value.getName() + "|" + value.getCity() + "|" + value.getImage() + "|" + value.getPositionX() + "|" + value.getPositionY());
        }
        return stationsInformation;
    }

    public String[] getSeatTypes() {
        List<String> seatTypesList = new ArrayList<>(3);
        for (Car car : train.getCarsList()) {
            for (CarSeat seat : car.getSeatsList()) {
                if (!seatTypesList.contains(seat.getType().toString())) {
                    seatTypesList.add(seat.getType().toString());
                }
            }
        }
        // we have to order list because the second element is THIRD_CLASS, has to be SECOND_CLASS
        Collections.sort(seatTypesList);
        // cast list to be java array
        return seatTypesList.toArray(new String[0]);
    }

    public List<String> getRoutesByStation(int stationId) {
        int i = 0;
        List<String> routesByStation = new ArrayList<>();
        Route desiredDestinationRoute;
        Route previuosStationRoute;


        for (Route route : routesList) {
            i++; // 0 is origin, i = 1 is next and i-2=-1 previous to origin
            if (route.getStationId() == currentStationId) {
                if ((routesList.get(i - 2).getStationId() == stationId && routesList.get(i).getStationId() != stationId && routesByStation.isEmpty())) {
                    continue;
                }
                if (routesList.get(i - 2).getStationId() != stationId && routesList.get(i).getStationId() == stationId) { // look if next station is destination
                    previuosStationRoute = routesList.get(i - 1);
                    desiredDestinationRoute = routesList.get(i);
                    routesByStation.add(desiredDestinationRoute.getDepartureTime() + "-" + desiredDestinationRoute.getArrivalTime() + "|" + route.getId() + "|" + route.calculateTicketCost(desiredDestinationRoute.getDepartureTime(), desiredDestinationRoute.getArrivalTime()) + "|" + currentStationId + "|" + stationId + "|" + getStationNameById(currentStationId) + "|" + getStationNameById(stationId));
                    routesByStation.add(previuosStationRoute.getDepartureTime() + "-" + previuosStationRoute.getArrivalTime() + "|" + route.getId() + "|" + route.calculateTicketCost(previuosStationRoute.getDepartureTime(), previuosStationRoute.getArrivalTime()) + "|" + currentStationId + "|" + stationId + "|" + getStationNameById(currentStationId) + "|" + getStationNameById(stationId));

                } else if (routesList.get(i - 3).getStationId() != stationId && routesList.get(i + 1).getStationId() == stationId) {
                    previuosStationRoute = routesList.get(i - 1);
                    desiredDestinationRoute = routesList.get(i + 1);
                    routesByStation.add(previuosStationRoute.getArrivalTime() + "-" + desiredDestinationRoute.getArrivalTime() + "|" + route.getId() + "|" + route.calculateTicketCost(previuosStationRoute.getArrivalTime(), desiredDestinationRoute.getArrivalTime()) + "|" + currentStationId + "|" + stationId + "|" + getStationNameById(currentStationId) + "|" + getStationNameById(stationId));
                    routesByStation.add(previuosStationRoute.getDepartureTime() + "-" + desiredDestinationRoute.getDepartureTime() + "|" + route.getId() + "|" + route.calculateTicketCost(previuosStationRoute.getDepartureTime(), desiredDestinationRoute.getDepartureTime()) + "|" + currentStationId + "|" + stationId + "|" + getStationNameById(currentStationId) + "|" + getStationNameById(stationId));
                } else if (routesList.get(i - 3).getStationId() == stationId && routesList.get(i + 1).getStationId() == stationId) {
                    previuosStationRoute = routesList.get(i - 1);
                    desiredDestinationRoute = routesList.get(i + 1);
                    routesByStation.add(previuosStationRoute.getArrivalTime() + "-" + desiredDestinationRoute.getArrivalTime() + "|" + route.getId() + "|" + route.calculateTicketCost(previuosStationRoute.getArrivalTime(), desiredDestinationRoute.getArrivalTime()) + "|" + currentStationId + "|" + stationId + "|" + getStationNameById(currentStationId) + "|" + getStationNameById(stationId));
                    routesByStation.add(previuosStationRoute.getDepartureTime() + "-" + desiredDestinationRoute.getDepartureTime() + "|" + route.getId() + "|" + route.calculateTicketCost(previuosStationRoute.getDepartureTime(), desiredDestinationRoute.getDepartureTime()) + "|" + currentStationId + "|" + stationId + "|" + getStationNameById(currentStationId) + "|" + getStationNameById(stationId));
                }
            }
        }
        Collections.sort(routesByStation);
        return routesByStation;
    }

    public String getStationNameById(int stationId) {
        String stationName = "";

        for (Station station : stationsList) {
            if (station.getId() == stationId) {
                stationName = station.getName();
            }
        }

        return stationName;
    }


    public void addPassenger(String passport, String name, String surname, LocalDate birthdate, String email) throws Exception {
        if (registeredPassengers.isEmpty()) {
            passenger = new Passenger(passport, name, surname, birthdate, email);
            registeredPassengers.put(passport, new Passenger(passport, name, surname, birthdate, email));
        }
        for (String i : registeredPassengers.keySet()) {
            // if passport is not registered yet, we create a new register
            if (!passport.equals(i)) {
                registeredPassengers.put(passport, new Passenger(passport, name, surname, birthdate, email));
            } else { // in case that passenger already was registered, we replace it with last information
                registeredPassengers.replace(i, new Passenger(passport, name, surname, birthdate, email));
            }
        }

    }


    public void createTicket(String passport, String routeId, LocalTime departureTime, LocalTime arrivalTime, double cost, int originStationId, int destinationStationId, String selectedSeatType) throws Exception {
        ticket = new Ticket(passport, routeId, departureTime, arrivalTime, cost, originStationId, destinationStationId, selectedSeatType);
        passenger.addTicket(ticket);
        passenger.addSeat(trainsList, ticket.getTrainId(routesList, ticket.getRouteId()), ticket.getSelectedSeatType());
        ticket.setCurrentStationId(destinationStationId);
        currentStationId = ticket.getCurrentStationId();
        ticketsList.add(ticket);
        passenger.getSeat().disembarkingPassenger();
    }

    public void buyTicket(String passport, String name, String surname, LocalDate birthdate, String email,
                          String routeId, LocalTime departureTime, LocalTime arrivalTime, double cost, int originStationId, int destinationStationId, String selectedSeatType) throws Exception {
        addPassenger(passport, name, surname, birthdate, email);
        createTicket(passport, routeId, departureTime, arrivalTime, cost, originStationId, destinationStationId, selectedSeatType);
    }

    public List<String> getAllTickets() {
        List<String> ticketsInfo = new ArrayList<>();
        for (Ticket ticket : ticketsList) {
            ticketsInfo.add(ticket.getRouteId() + "|" + ticket.getDepartureTime() + "|" + ticket.getDepartureStationName(stationsList, ticket.getOriginStationId()) + "|" + ticket.getArrivalTime() + "|" + ticket.getArrivalStationName(stationsList, ticket.getDestinationStationId()) + "|" + ticket.getSeatName() + "|" + ticket.getCost());
        }
        return ticketsInfo;
    }

    public String getPassengerInfo(String passport) {
        String passengerInfo = "";
        for (String p : registeredPassengers.keySet()) {
            if (passport.equals(p)) {
                passengerInfo = registeredPassengers.get(p).getPassport() + "|" + registeredPassengers.get(p).getName() + "|" + registeredPassengers.get(p).getSurname() + "|" + registeredPassengers.get(p).getBirthdate() + "|" + registeredPassengers.get(p).getEmail();
            }
        }
        return passengerInfo;
    }

    public String getTrainInfo(int trainId) {
        String trainInfo = "";
        for (Train value : trainsList) {
            if (value.getId() == trainId) {
                trainInfo = value.getId() + "|" + value.getModel() + "|" + value.getCars().length;
            }

        }
        return trainInfo;
    }

    public List<String> getPassengerTickets(String passport) {
        List<String> passengerTickets = new ArrayList<>();
        for (Ticket ticket : ticketsList) {
            if (ticket.getPassport().equals(passport)) {
                passengerTickets.add(ticket.getRouteId() + "|" + ticket.getDepartureStationName(stationsList, ticket.getDestinationStationId()) + "|" + ticket.getArrivalTime() + "|" + ticket.getArrivalStationName(stationsList, ticket.getDestinationStationId()) + "|" + ticket.getSeatName() + "|" + ticket.getCost());
            }

        }

        return passengerTickets;

    }

    public List<String> getRouteDeparturesInfo(String routeId) {
        List<String> routesInformation = new ArrayList<>();
        for (Route route : routesList) {
            if (Objects.equals(route.getId(), routeId)) {
                routesInformation.add(route.getStationId() + "|" + "[" + route.getDepartureTime() + ", " + route.getArrivalTime() + "]");
            }
        }
        return routesInformation;
    }

    public int getCurrentStationId() {
        return currentStationId;
    }
}
