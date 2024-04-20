package edu.uoc.pac3;

import java.time.LocalDate;
import java.time.Period;

public class Guild {
    // atributos constantes
    public static final String INVALID_NAME = "[ERROR] The name cannot be null, has less than the minimum number of characters, exceeds the maximum number of characters or contains only whitespaces.";
    public static final String INVALID_LEVEL = "[ERROR] The level must be between 1 and the predefined maximum.";
    public static final String INVALID_DESCRIPTION = "[ERROR] The description cannot be null and cannot exceed the predefined maximum number of characters.";
    public static final String INVALID_CREATION_DATE = "[ERROR] The creation date cannot be null or in the future.";
    public static final String INVALID_MAX_MEMBERS = "[ERROR] The number of members cannot exceed the predefined maximum.";
    public static final String MEMBER_NULL = "[ERROR] The member cannot be null.";
    public static final String MEMBER_ALREADY_EXISTS = "[ERROR] The member already exists in the guild.";
    public static final String MEMBER_NOT_FOUND = "[ERROR] The member does not exist in the guild.";
    public static final String MEMBER_NO_PET = "[ERROR] The member does not have a pet.";
    private static final int MIN_NAME_LENGTH = 5;
    private static final int MAX_NAME_LENGTH = 25;
    private static final int MAX_LEVEL = 20;
    private static final int MAX_DESCRIPTION_LENGTH = 100;

    // atributos
    private final int NUM_MAX_MEMBERS; // cada instancia (gremio) tendrá su propio numero de jugadores maximos permitidos
    private int id;
    private static int nextId = 1;
    private String name;
    private int level;
    private String description;
    private LocalDate creationDate;
    private boolean recruiting;
    private int numMembers;
    private int sumLevels;

    // members será un array de objetos de la clase Player
    private Player[] members;

    public Guild(String name, int level, String description, LocalDate creationDate, boolean recruiting, int numMaxMembers){
        NUM_MAX_MEMBERS = numMaxMembers;
        numMembers = 0;
        sumLevels = 0;
        members = new Player[NUM_MAX_MEMBERS]; // instanciamos el array de objetos con longitud numero maximo juagores. iremos llenando los huecos null

        setId();
        setName(name);
        setLevel(level);
        setDescription(description);
        setCreationDate(creationDate);
        setRecruiting(recruiting);
    }

    public int getId() {
        return id;
    }

    private void setId() {
        id = getNextId();
        incNextId();
    }

    public static int getNextId() {
        return nextId;
    }

    private static void incNextId(){
        nextId++;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        if (name != null && !name.trim().isEmpty()){
            if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH){
                throw new IllegalArgumentException(INVALID_NAME);
            } else {
                this.name = name;
            }
        } else {
            throw new IllegalArgumentException(INVALID_NAME);
        }
    }

    public int getLevel() {
        return level;
    }

    private void setLevel(int level) {
        if (level >= 1 && level <= MAX_LEVEL){
            this.level = level;
        } else {
            throw new IllegalArgumentException(INVALID_LEVEL);
        }
    }

    public String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        if (description != null && description.length() <= MAX_DESCRIPTION_LENGTH){
            this.description = description;
        } else {
            throw new IllegalArgumentException(INVALID_DESCRIPTION);
        }
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    private void setCreationDate(LocalDate creationDate) {
        if (creationDate != null && !creationDate.isAfter(LocalDate.now())){
            this.creationDate = creationDate;
        } else {
            throw new IllegalArgumentException(INVALID_CREATION_DATE);
        }
    }

    public boolean isRecruiting() {
        return recruiting;
    }

    private void setRecruiting(boolean recruiting) {
        this.recruiting = recruiting;
    }

    public int getDaysOfLife(){
        return Period.between(getCreationDate(), LocalDate.now()).getDays();
    }

    public int getMaxMembers(){
        return NUM_MAX_MEMBERS;
    }

    public Player[] getMembers(){
        return members;
    }

    public int getNumMembers(){
        int countMembers = 0;
        for (int i =0; i < getMembers().length; i++){
            if (getMembers()[i] != null){ // contaremos solamente los miembros, no los huecos del gremio
                countMembers++;
            }
        }
        return countMembers; // numero de miembros en el gremio
    }

    private int findMember(Player member){ // se usara en containsMember FALTA COMPROBAR PET
        if (member == null) {
            throw new NullPointerException(MEMBER_NULL);
        }

        if (member.getPet() == null) {
            throw new NullPointerException(MEMBER_NO_PET);
        }
        for (int i = 0; i < getMembers().length; i++){
            if (getMembers()[i] == member) {
                return i;
            }
        }
        return -1;
    }

    private int findFirstEmptySlot(){
        for (int i = 0; i < getMembers().length; i++){
            if (getMembers()[i] == null){
                return i;
            }
        }
        return -1;
    }

    public boolean containsMember(Player member){
        if (member != null){
            return findMember(member) >= 0;
        } else{
            throw new NullPointerException(MEMBER_NULL);
        }
    }

    public void addMember(Player member) {
        // creamos variable con la primera posicion vacia
        int emptySlot = findFirstEmptySlot();

        if (member == null) {
            throw new NullPointerException(MEMBER_NULL);
        }

        if (member.getPet() == null) {
            throw new NullPointerException(MEMBER_NO_PET);
        }

        if (emptySlot == -1) {
            throw new IllegalStateException(INVALID_MAX_MEMBERS);
        }

        if (containsMember(member)) {
            throw new IllegalArgumentException(MEMBER_ALREADY_EXISTS);
        }

        // si no cumple ninguna de las condiciones anteriores, podremos añadir al miembro al grupo
        members[emptySlot] = member;
        numMembers++;
        sumLevels += member.getLevel();
    }

    public void removeMember(Player member){
        if (member == null) {
            throw new NullPointerException(MEMBER_NULL);
        }

        if (member.getPet() == null) {
            throw new NullPointerException("ERROR_NO_PET");
        }

        if (!containsMember(member)) {
            throw new IllegalArgumentException(MEMBER_NOT_FOUND);
        }

        // si no cumple ninguna de las condiciones anteriores, podremos eliminar al miembro del grupo
        sumLevels -= member.getLevel(); // restaremos el nivel del miembro eliminado
        members[findMember(member)] = null; // asignamos valor nulo a la posicion del antiguo miembro
        numMembers--; // 1 miembro menos
    }

    public double getAverageLevel(){
        if (getNumMembers() == 0){ // evitamos la division 0/0
            return 0.0;
        } else{
            return (double)sumLevels/getNumMembers();
        }
    }
}
