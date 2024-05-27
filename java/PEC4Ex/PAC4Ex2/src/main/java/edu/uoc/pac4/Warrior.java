package edu.uoc.pac4;

import edu.uoc.pac4.exception.EntityException;
import edu.uoc.pac4.exception.PlayerException;

public class Warrior extends Player implements Movable, Speakable {
    public static final int VITALITY = 4;
    public static final int INTELLIGENCE = 3;
    public static final int STRENGTH = 6;
    public static final int AGILITY = 3;
    public static final int MAX_HP = 250;
    private static final double MAX_STEP = 20.0;

    public Warrior(String name, Position position, Empire empire) throws PlayerException, EntityException {
        super(name, 1, MAX_HP, position, 0, 0, VITALITY, INTELLIGENCE, STRENGTH, AGILITY, empire); // llamamos al constructor de la clase Player
    }

    public Warrior(String name, int level, int maxHP, Position position, int currentGold, int currentExperience, int vitality, int intelligence, int strength, int agility, Empire empire) throws PlayerException, EntityException {
        super(name, level, maxHP, position, currentGold, currentExperience, vitality, intelligence, strength, agility, empire);
    }

    public boolean move(Position position) throws EntityException { // revisar lo de la excpecion
        if (getPosition().euclideanDistance(position) <= MAX_STEP) {
            setPosition(position);
            return true;
        } else {
            return false;
        }
    }

    public String battleCry() {
        return getName() + ": To victory or death!";
    }

    public String deathCry() {
        return getName() + ": I shall return, stronger than ever...";
    }
}
