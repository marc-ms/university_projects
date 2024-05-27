package edu.uoc.pac4;

import edu.uoc.pac4.exception.GameMapException;

public class GameMap {
    private int gameMapId;
    private String name;
    private int width;
    private int height;
    private int depth;

    private GameMapType gameMapType;

    public GameMap(int gameMapId, String name, int width, int height, int depth, GameMapType gameMapType) throws GameMapException{
        setGameMapId(gameMapId);
        setName(name);
        setWidth(width);
        setHeight(height);
        setDepth(depth);
        setGameMapType(gameMapType);
    }

    public int getGameMapId() {
        return gameMapId;
    }

    private void setGameMapId(int gameMapId) throws GameMapException {
        if (gameMapId <= 0){
            throw new GameMapException(GameMapException.INVALID_GAME_MAP_ID);
        }
        this.gameMapId = gameMapId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws GameMapException {
        if (name == null || name.trim().isEmpty()){
            throw new GameMapException(GameMapException.INVALID_NAME);
        }else{
            this.name = name;
        }

    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) throws GameMapException {
        if (width <= 0){
            throw new GameMapException(GameMapException.INVALID_WIDTH);
        }
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) throws GameMapException {
        if (height <= 0){
            throw new GameMapException(GameMapException.INVALID_HEIGHT);
        }
        this.height = height;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) throws GameMapException {
        if (depth <= 0){
            throw new GameMapException(GameMapException.INVALID_DEPTH);
        }
        this.depth = depth;
    }

    public GameMapType getGameMapType(){
        return gameMapType;
    }

    public void setGameMapType(GameMapType gameMapType) throws GameMapException {
        if (gameMapType == null){
            throw new GameMapException(GameMapException.MAP_TYPE_NULL);
        }
        this.gameMapType = gameMapType;
    }

    public boolean equals(Object obj) {
        // casteamos el obj comprobando que se trata de la clase que queremos comparar
        if (obj instanceof GameMap gameMap) {
            return this.getGameMapId() == gameMap.getGameMapId();
        } else {
            return false;
        }
    }

    public String toString(){
        return this.getGameMapId() + " | " +
                this.getName() + " (" +
                this.getWidth() + "x" + this.getHeight() + "x" + this.getDepth() + ")" +
                " | " + this.getGameMapType().name().charAt(0) + this.getGameMapType().name().substring(1).toLowerCase();
    }
}
