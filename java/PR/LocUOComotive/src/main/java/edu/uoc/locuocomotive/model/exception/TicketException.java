package edu.uoc.locuocomotive.model.exception;

public class TicketException extends Exception{
    public static final String INVALID_TICKET = "Error while creating your ticket";

    public TicketException(String message){
        super(message);
    }
}
