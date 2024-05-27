package edu.uoc.pac4;

public enum GameMapType {
    CITY,
    VALLEY,
    DESERT,
    FOREST,
    MOUNTAIN,
    OCEAN,
    PLAINS,
    ICE,
    VOLCANO,
    TEMPLE;

    public String toString(){
        return this.name().charAt(0) + this.name().substring(1).toLowerCase();
    }
}
