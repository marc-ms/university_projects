package edu.uoc.pac4;

import edu.uoc.pac4.exception.EntityException;

public class OrcReborn extends Enemy {
    public static final int ID = 102;
    public static final String NAME = "Orc Reborn";
    public static final int LEVEL = 49;
    public static final int MAX_HP = 260;
    public static final int MIN_GOLD = 250;
    public static final int MAX_GOLD = 350;
    public static final int EXPERIENCE = 874;
    public static final int MIN_DAMAGE = 40;
    public static final int MAX_DAMAGE = 55;
    private static final double MAX_STEP = 10.0;

    public OrcReborn(Position position, Enemy groupLeader) throws EntityException {
        super(NAME, LEVEL, MAX_HP, position, ID, MIN_GOLD, MAX_GOLD, EXPERIENCE, MIN_DAMAGE, MAX_DAMAGE, groupLeader);
    }

    public boolean move(Position position) throws EntityException {
        if (getPosition().euclideanDistance(position) <= MAX_STEP) {
            setPosition(position);
            return true;
        } else {
            return false;
        }
    }
}
