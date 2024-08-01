package edu.uoc.locuocomotive.model;

public class Station {
    private int id;
    private String name;
    private String city;
    private int openingYear;
    private String image;
    private int positionX;
    private int positionY;
    private String stationType;

    public Station(int id, String name, String city, int openingYear, String stationType, String image, int positionX, int positionY) {
        setId(id);
        setName(name);
        setCity(city);
        setOpeningYear(openingYear);
        setStationType(stationType);
        setImage(image);
        setPositionX(positionX);
        setPositionY(positionY);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getOpeningYear() {
        return openingYear;
    }

    public void setOpeningYear(int openingYear) {
        this.openingYear = openingYear;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public String getStationType() {
        return stationType;
    }

    public void setStationType(String stationType) {
        this.stationType = stationType;
    }


}
