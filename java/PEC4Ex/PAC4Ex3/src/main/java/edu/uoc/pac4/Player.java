package edu.uoc.pac4;

import edu.uoc.pac4.exception.EntityException;
import edu.uoc.pac4.exception.PlayerException;

public abstract class Player extends Entity implements Speakable{
    private static final int MAX_STAT = 90;
    private int currentGold;
    private int currentExperience;
    private int vitality;
    private int intelligence;
    private int strength;
    private int agility;

    private Empire empire;

    protected Player(String name, int level, int maxHP, Position position, int currentGold, int currentExperience, int vitality, int intelligence, int strength, int agility, Empire empire) throws PlayerException, EntityException {
        super(name, level, maxHP, position);
        setCurrentGold(currentGold);
        setCurrentExperience(currentExperience);
        setVitality(vitality);
        setIntelligence(intelligence);
        setStrength(strength);
        setAgility(agility);
        setEmpire(empire);
    }

    public int getCurrentGold() {
        return currentGold;
    }

    public void setCurrentGold(int currentGold) {
        this.currentGold = Math.max(currentGold, 0);
    }

    public int getCurrentExperience() {
        return currentExperience;
    }

    public void setCurrentExperience(int currentExperience) {
        this.currentExperience = Math.max(currentExperience, 0);
    }

    public int getVitality() {
        return vitality;
    }

    public void setVitality(int vitality) {
        this.vitality = Math.min(Math.max(vitality, 0), MAX_STAT);
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = Math.min(Math.max(intelligence, 0), MAX_STAT);
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = Math.min(Math.max(strength, 0), MAX_STAT);
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = Math.min(Math.max(agility, 0), MAX_STAT);
    }

    public Empire getEmpire(){
        return empire;
    }

    public void setEmpire(Empire empire) throws PlayerException {
        if (empire == null) {
            throw new PlayerException(PlayerException.EMPIRE_NULL);
        }
        this.empire = empire;
    }

    public String sayName(){
        return "name: " + getName();
    }
}
