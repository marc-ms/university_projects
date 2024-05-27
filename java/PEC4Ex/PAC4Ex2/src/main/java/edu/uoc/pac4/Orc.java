package edu.uoc.pac4;

import edu.uoc.pac4.exception.EntityException;
import edu.uoc.pac4.exception.PositionException;

public class Orc extends Enemy implements TransCloneable, Cloneable{
    public static final int ID = 101;
    public static final String NAME = "Orc";
    public static final int LEVEL = 46;
    public static final int MAX_HP = 190;
    public static final int MIN_GOLD = 150;
    public static final int MAX_GOLD = 200;
    public static final int EXPERIENCE = 488;
    public static final int MIN_DAMAGE = 10;
    public static final int MAX_DAMAGE = 15;
    private static final double MAX_STEP = 7.0;

    public Orc(Position position, Enemy groupLeader) throws EntityException {
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

    @Override
    public Enemy clone() {
        Orc orc;
        try {
            orc = new Orc(new Position(this.getPosition().getGameMap(), this.getPosition().getX(), this.getPosition().getY(), this.getPosition().getZ()), this.getGroupLeader());
        } catch (EntityException | PositionException e) {
            throw new RuntimeException(e);
        }

        return orc;
    }

    public OrcReborn transClone() throws EntityException, PositionException{
        if (this.getGroupLeader() == null){
            return new OrcReborn(new Position(this.getPosition().getGameMap(), this.getPosition().getX(), this.getPosition().getY(), this.getPosition().getZ()), this);
        }
        return new OrcReborn(new Position(this.getPosition().getGameMap(), this.getPosition().getX(), this.getPosition().getY(), this.getPosition().getZ()), getGroupLeader());
    }
}
