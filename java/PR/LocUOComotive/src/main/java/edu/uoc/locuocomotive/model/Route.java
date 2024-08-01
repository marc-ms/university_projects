package edu.uoc.locuocomotive.model;

public class Route {
    private String id;
    private String departureTime;
    private String arrivalTime;

    private int trainId;
    private int stationId;


    public Route(String id, int trainId, int stationId, String departureTime, String arrivalTime) {
        setId(id);
        setTrainId(trainId);
        setStationId(stationId);
        setDepartureTime(departureTime);
        setArrivalTime(arrivalTime);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }


    public int calculateTicketCost(String departureTime, String arrivalTime) {
        String[] depSep = departureTime.split(":");
        String[] arrSep = arrivalTime.split(":");
        int depTime = Integer.parseInt(depSep[0]) * 60 + Integer.parseInt(depSep[1]);
        int arrTime = Integer.parseInt(arrSep[0]) * 60 + Integer.parseInt(arrSep[1]);
        boolean flag = false;

        if (depTime < 12 * 60 && arrTime > 12 * 60) {
            depTime = depTime + 12 * 60;
            flag = true;
        }

        if (depTime > 12 * 60 && arrTime < 12 * 60) {
            arrTime = arrTime + 12 * 60;
            flag = true;

        }

        int ticketCost = getTicketCost(arrTime, depTime, flag);

        return Math.abs(ticketCost);
    }

    public static int getTicketCost(int arrTime, int depTime, boolean flag) {
        int difference = arrTime - depTime;

        if (((depTime < 12 * 60 && arrTime > 12 * 60) || (depTime > 12 * 60 && arrTime < 12 * 60)) && difference == 0) {
            difference = 12 * 60;
        }

        if (((depTime > 12 * 60 && arrTime > 12 * 60) || (depTime < 12 * 60 && arrTime < 12 * 60)) && difference == 0) {
            if (flag) {
                difference = 12 * 60;
            } else {
                difference = 24 * 60;
            }
        }

        if (depTime < 12 * 60 && arrTime < 12 * 60 && difference < 0) {
            difference = difference + 24 * 60;
        }

        if (difference < 0) {
            difference = difference + 12 * 60;
            if (difference == 0) {
                difference = 12 * 60;
            }
        }

        return 30 * (difference / 60);
    }
}
