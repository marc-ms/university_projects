package edu.uoc.pac4;

import edu.uoc.pac4.exception.PositionException;

public class Position  {
    private int x;
    private int y;
    private int z;

    private GameMap gameMap;

    public Position(GameMap gameMap, int x, int y, int z) throws PositionException {
        setGameMap(gameMap);
        setX(x);
        setY(y);
        setZ(z);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) throws PositionException {
        if (x < 0){
            throw new PositionException(PositionException.INVALID_X);
        } else if (x >= getGameMap().getWidth()) {
            throw new PositionException(PositionException.OUT_OF_BOUNDS_X);
        }
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) throws PositionException{
        if (y < 0){
            throw new PositionException(PositionException.INVALID_Y);
        } else if (y >= getGameMap().getHeight()) {
            throw new PositionException(PositionException.OUT_OF_BOUNDS_Y);
        }
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) throws PositionException{
        if (z < 0){
            throw new PositionException(PositionException.INVALID_Z);
        } else if (z >= getGameMap().getDepth()) {
            throw new PositionException(PositionException.OUT_OF_BOUNDS_Z);
        }
        this.z = z;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public void setGameMap(GameMap gameMap) throws PositionException{
        if (gameMap == null){
            throw new PositionException(PositionException.MAP_NULL);
        }
        this.gameMap = gameMap;
    }

    public double euclideanDistance(Position position){
        return Math.sqrt((this.getZ() - position.getZ()) * (this.getZ() - position.getZ()) + (this.getY() - position.getY()) * (this.getY() - position.getY()) + (this.getX() - position.getX()) * (this.getX() - position.getX()));
    }

    public boolean equals(Object obj) {
        // casteamos el obj comprobando que se trata de la clase que queremos comparar
        if (obj instanceof Position position) {
            return this.getGameMap().getGameMapId() == position.getGameMap().getGameMapId() && this.getX() == position.getX() && this.getY() == position.getY() && this.getZ() == position.getZ();
        } else {
            return false;
        }
    }

    public String toString(){
        return this.getGameMap().getGameMapId() + " | " +
                this.getGameMap().getName() + " (" +
                this.getGameMap().getWidth() + "x" + this.getGameMap().getHeight() + "x" + this.getGameMap().getDepth() + ")" +
                " | " + this.getGameMap().getGameMapType().name().charAt(0) + this.getGameMap().getGameMapType().name().substring(1).toLowerCase() +
                " | " + "Position: " + "(" + getX() + ", " + getY() + ", " + getZ() + ")";
    }
}
