package edu.uoc.pac3;

import com.sun.source.tree.ReturnTree;

import java.time.LocalDate;

public class Player {
    // atributos constantes
    public static final String INVALID_NAME = "[ERROR] The name cannot be null, empty, consist solely of spaces, and it must be within the predefined minimum and maximum character limits.";
    public static final String INVALID_LEVEL = "[ERROR] The level must be between 1 and the predefined maximum.";
    public static final String INVALID_CREATION_DATE = "[ERROR] The creation date cannot be null or in the future.";
    public static final String INVALID_EXPERIENCE = "[ERROR] The experience must be greater than or equal to 0.";
    public static final String INVALID_GOLD = "[ERROR] The gold must be greater than or equal to 0.";
    public static final String INVALID_HEALTH_REGEN_PER_SEC = "[ERROR] The health regeneration per second must be greater than or equal to 0.";
    public static final String INVALID_CRITICAL_PCT = "[ERROR] The critical percentage must be between 0.0 and 100.0.";
    public static final String INVALID_DODGE_PCT = "[ERROR] The dodge percentage must be between 0.0 and 100.0.";
    public static final String INVALID_HONOR_TITLE = "[ERROR] The honor title cannot be null, empty, consist solely of spaces, cannot exceed the predefined maximum character limit, and can only contain characters from the English alphabet and whitespaces.";
    public static final String INVALID_PLAYER_RACE = "[ERROR] The player race cannot be null";
    private static final int MIN_NAME_LENGTH = 5;
    private static final int MAX_NAME_LENGTH = 50;
    private static final int MAX_LEVEL = 99;
    private static final int MAX_HONOR_TITLE_LENGTH = 30;

    // atributos
    private String name;
    private int level;
    private LocalDate creationDate;
    private int experience;
    private int gold;
    private double healthRegenPerSec;
    private double criticalPct;
    private double dodgePct;
    private String honorTitle;

    // atributo de la clase Pet
    private Pet pet;

    // atributo de la clase enum PlayerRace
    private PlayerRace playerRace;

    // constructores
        // constructor sin mascota
    public Player(String name, int level, LocalDate creationDate, int experience, int gold, double healthRegenPerSec, double criticalPct, double dodgePct, String honorTitle, PlayerRace playerRace){
        setName(name);
        setLevel(level);
        setCreationDate(creationDate);
        setExperience(experience);
        setGold(gold);
        setHealthRegenPerSec(healthRegenPerSec);
        setCriticalPct(criticalPct);
        setDodgePct(dodgePct);
        setHonorTitle(honorTitle);
        pet = null;
        setPlayerRace(playerRace);
    }

        // constructor con mascota
    public Player(String name, int level, LocalDate creationDate, int experience, int gold, double healthRegenPerSec, double criticalPct, double dodgePct, String honorTitle, PlayerRace playerRace, String petName, int petLevel, LocalDate petBirthdate, int petLoyalty, int petStamina, boolean petIsAggressive){
        setName(name);
        setLevel(level);
        setCreationDate(creationDate);
        setExperience(experience);
        setGold(gold);
        setHealthRegenPerSec(healthRegenPerSec);
        setCriticalPct(criticalPct);
        setDodgePct(dodgePct);
        setHonorTitle(honorTitle);

         //codificar el hecho de que si desaparce la clase compuesta desaparece la componenete
        setPet(petName, petLevel, petBirthdate, petLoyalty, petStamina, petIsAggressive);

        setPlayerRace(playerRace);
    }

    // getters y setters

    public String getName() {
        return name;
    }

    private void setName(String name) {
        if (name != null && !name.trim().isEmpty()){
            if (name.trim().length() < MIN_NAME_LENGTH || name.trim().length() > MAX_NAME_LENGTH){
                throw new IllegalArgumentException(INVALID_NAME);
            } else {
                this.name = name.trim();
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

    public int getExperience() {
        return experience;
    }

    private void setExperience(int experience) {
        if (experience >= 0){
            this.experience = experience;
        } else {
            throw new IllegalArgumentException(INVALID_EXPERIENCE);
        }
    }

    public int getGold() {
        return gold;
    }

    private void setGold(int gold) {
        if (gold >= 0){
            this.gold = gold;
        } else {
            throw new IllegalArgumentException(INVALID_GOLD);
        }
    }

    public double getHealthRegenPerSec() {
        return healthRegenPerSec;
    }

    private void setHealthRegenPerSec(double healthRegenPerSec) {
        if (healthRegenPerSec >= 0){
            this.healthRegenPerSec = healthRegenPerSec;
        } else {
            throw new IllegalArgumentException(INVALID_HEALTH_REGEN_PER_SEC);
        }
    }

    public double getCriticalPct() {
        return criticalPct;
    }

    private void setCriticalPct(double criticalPct) {
        if (criticalPct >= 0 && criticalPct <= 100){
            this.criticalPct = criticalPct;
        } else {
            throw new IllegalArgumentException(INVALID_CRITICAL_PCT);
        }
    }

    public double getDodgePct() {
        return dodgePct;
    }

    private void setDodgePct(double dodgePct) {
        if (dodgePct >= 0 && dodgePct <= 100){
            this.dodgePct = dodgePct;
        } else {
            throw new IllegalArgumentException(INVALID_DODGE_PCT);
        }
    }

    public String getHonorTitle() {
        return honorTitle;
    }

    private void setHonorTitle(String honorTitle) {
        if (honorTitle != null && !honorTitle.trim().isEmpty()){
            if (honorTitle.trim().length() > MAX_HONOR_TITLE_LENGTH){
                throw new IllegalArgumentException(INVALID_HONOR_TITLE);
            } else if (honorTitle.trim().matches("^[a-zA-Z\\s]+$")){
                this.honorTitle = honorTitle.trim();
            } else{
                throw new IllegalArgumentException(INVALID_HONOR_TITLE);
            }
        } else {
            throw new IllegalArgumentException(INVALID_HONOR_TITLE);
        }
    }

    // setter y getter clase componente
    public Pet getPet(){
        return pet;
    }

    private void setPet(String name, int level, LocalDate birthdate, int loyalty, int stamina, boolean aggressive){
        pet = new Pet(name, level, birthdate, loyalty, stamina, aggressive);
    }

    public PlayerRace getPlayerRace() {
        return playerRace;
    }

    private void setPlayerRace(PlayerRace playerRace) {
        if (playerRace == null){
            throw new IllegalArgumentException(INVALID_PLAYER_RACE);
        }
        this.playerRace = playerRace;
    }
}
