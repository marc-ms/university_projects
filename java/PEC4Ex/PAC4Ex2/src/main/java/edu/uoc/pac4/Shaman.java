package edu.uoc.pac4;

import edu.uoc.pac4.exception.EntityException;
import edu.uoc.pac4.exception.PlayerException;

public class Shaman extends Player implements Movable, Speakable{
    public static final int VITALITY = 4;
    public static final int INTELLIGENCE = 6;
    public static final int STRENGTH = 3;
    public static final int AGILITY = 3;
    public static final int MAX_HP = 150;
    private static final double MAX_STEP = 10.0;

    public Shaman(String name, Position position, Empire empire) throws PlayerException, EntityException {
        super(name, 1, MAX_HP, position, 0, 0, VITALITY, INTELLIGENCE, STRENGTH, AGILITY, empire); // llamamos al constructor de la clase Player
    }

    public Shaman(String name, int level, int maxHP, Position position, int currentGold, int currentExperience, int vitality, int intelligence, int strength, int agility, Empire empire) throws PlayerException, EntityException {
        super(name, level, maxHP, position, currentGold, currentExperience, vitality, intelligence, strength, agility, empire);
        move(position);
    }

    public boolean move(Position position) throws EntityException{ // revisar lo de la excpecion
        if (getPosition().euclideanDistance(position) <= MAX_STEP){
            setPosition(position);
            return true;
        } else {
            return false;
        }
    }

    public String battleCry(){
        return getName() + ": Spirits guide us to victory!";
    }

    public String deathCry(){
        return getName() + ": My soul joins the ancestors...";
    }
}
