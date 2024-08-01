package edu.uoc.locuocomotive.model.exception;

public class PassengerException extends Exception{
    public static final String INVALID_PASSPORT = "Passport cannot be null or empty";
    public static final String INVALID_NAME = "Name cannot be null or empty";
    public static final String INVALID_SURNAME = "Surname cannot be null or empty";
    public static final String INVALID_BIRTHDATE = "Birthdate cannot be null";
    public static final String INVALID_EMAIL = "Birthdate cannot be null";

    public PassengerException(String message){
        super(message);
    }
}
