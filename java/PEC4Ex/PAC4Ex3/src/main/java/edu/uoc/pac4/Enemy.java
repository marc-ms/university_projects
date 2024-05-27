package edu.uoc.pac4;

import edu.uoc.pac4.exception.EntityException;

public abstract class Enemy extends Entity{
    private static final double MAX_GROUP_LEADER_DISTANCE = 50.0;
    private int id;
    private int minGold;
    private int maxGold;
    private int experience;
    private int minDamage;
    private int maxDamage;

    private Enemy groupLeader;

    protected Enemy(String name, int level, int maxHP, Position position, int id, int minGold, int maxGold, int experience, int minDamage, int maxDamage, Enemy groupLeader) throws EntityException {
        super(name, level, maxHP, position);
        setId(id);
        setGold(minGold, maxGold);
        setExperience(experience);
        setDamage(minDamage, maxDamage);
        setGroupLeader(groupLeader);
    }

    public int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    public int getMinGold() {
        return minGold;
    }

    public int getMaxGold() {
        return maxGold;
    }

    public void setGold(int minGold, int maxGold) {
        if(minGold > maxGold){
            this.minGold = minGold;
        } else this.minGold = Math.max(minGold, 0);
        this.maxGold = Math.max(minGold, maxGold);
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = Math.max(experience, 0);
    }

    public int getMinDamage() {
        return minDamage;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public void setDamage(int minDamage, int maxDamage) {
        if(minDamage > maxDamage){
            this.minDamage = minDamage;
        } else this.minDamage = Math.max(minDamage, 0);
        this.maxDamage = Math.max(minDamage, maxDamage);
    }

    public Enemy getGroupLeader(){
        return groupLeader;
    }

    public void setGroupLeader(Enemy groupLeader){
        this.groupLeader = groupLeader;
    }

    public boolean isFarFromGroupLeader(){
        return !(getGroupLeader() == null) && !(this.getPosition().euclideanDistance(getGroupLeader().getPosition()) <= MAX_GROUP_LEADER_DISTANCE);
    }
}
