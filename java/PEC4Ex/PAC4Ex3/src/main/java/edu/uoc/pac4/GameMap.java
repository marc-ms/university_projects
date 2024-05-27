package edu.uoc.pac4;

import edu.uoc.pac4.exception.EntityException;
import edu.uoc.pac4.exception.GameMapException;
import edu.uoc.pac4.exception.PositionException;

import java.util.Comparator;
import java.util.Hashtable;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameMap {
    public static final int MAX_ENTITIES = 1000;
    private int gameMapId;
    private String name;
    private int width;
    private int height;
    private int depth;

    private GameMapType gameMapType;
    private Map<Integer, Entity> entities;

    public GameMap(int gameMapId, String name, int width, int height, int depth, GameMapType gameMapType) throws GameMapException{
        entities = new Hashtable<>();
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

    public void addEntity(Entity entity){
        if (entities.size() < MAX_ENTITIES){
            entities.put(entity.getVid(), entity);
        }
    }

    public void removeEntity(Integer vid){
        entities.remove(vid);
    }

    public Entity getEntity(Integer vid){
        return entities.get(vid);
    }

    public long getNumPlayers(){
        return entities.values().stream().filter(player -> player instanceof Player).count();
    }

    public long getNumEnemiesById(int id){
        // primero filtraremos para obtener el id de solo las clases hijas de Enemy
        // tenremos que downcastear para obtener el metodo getId()
        return entities.values().stream().filter(enemy -> enemy instanceof Enemy).filter(enemy -> ((Enemy) enemy).getId() == id).count();
    }

    public Entity findNearestEnemy(Position position) {
        return entities.values().stream().filter(enemy -> enemy instanceof Enemy).min(Comparator.comparing(f -> Math.sqrt(((f.getPosition().getZ() - position.getZ()) * (f.getPosition().getZ() - position.getZ()) + (f.getPosition().getY()) - position.getY()) * (f.getPosition().getY() - position.getY()) + (f.getPosition().getX() - position.getX()) * (f.getPosition().getX() - position.getX())))).orElse(null);
    }
}
