package edu.uoc.locuocomotive.model;

import edu.uoc.locuocomotive.model.exception.PassengerException;
import edu.uoc.locuocomotive.model.exception.TicketException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Passenger {
    private String passport;
    private String name;
    private String surname;
    private LocalDate birthdate;
    private String email;
    private List<Ticket> ticketsList = new ArrayList<>();
    private Ticket ticket;
    private CarSeat seat;

    public Passenger(String passport, String name, String surname, LocalDate birthdate, String email) throws PassengerException {
        setPassport(passport);
        setName(name);
        setSurname(surname);
        setBirthdate(birthdate);
        setEmail(email);
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) throws PassengerException {
        if (passport == null || passport.trim().isEmpty()) {
            throw new PassengerException(PassengerException.INVALID_PASSPORT);
        }
        this.passport = passport;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws PassengerException {
        if (name == null || name.trim().isEmpty()) {
            throw new PassengerException(PassengerException.INVALID_NAME);
        }
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) throws PassengerException {
        if (surname == null || surname.trim().isEmpty()) {
            throw new PassengerException(PassengerException.INVALID_SURNAME);
        }
        this.surname = surname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) throws PassengerException {
        if (birthdate == null) {
            throw new PassengerException(PassengerException.INVALID_BIRTHDATE);
        }
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws PassengerException {
        if (email == null || !email.isEmpty() && !email.matches("^[a-zA-Z0-9._-]+@[a-z0-9.-]+\\.[a-z]{2,}$")) {
            throw new PassengerException(PassengerException.INVALID_EMAIL);
        }
        this.email = email;
    }

    public void addTicket(Ticket ticket) throws TicketException {
        if (ticket == null){
            throw new TicketException(TicketException.INVALID_TICKET);
        }
        this.ticket = ticket;
        ticketsList.add(ticket);
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void addSeat(ArrayList<Train> trainsList, int trainId, String seatClass) {
        this.seat = ticket.assignFirstSeat(trainsList, trainId, seatClass);
    }

    public CarSeat getSeat() {
        return seat;
    }
}
