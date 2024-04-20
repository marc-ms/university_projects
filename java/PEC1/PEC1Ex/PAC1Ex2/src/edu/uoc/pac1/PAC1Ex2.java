package edu.uoc.pac1;
import java.util.Locale;

public class PAC1Ex2 {

    private static final double LEVEL_MULTIPLIER = 4.5;
    private static final double[] DIFFICULTY_MULTIPLIER = {1.0, 1.5, 2.5, 5.0};
    private static final int GOLD_BASE = 100;
    public static String ERR_LEVEL = "[ERROR] Invalid level";

    public static boolean isValidLevel(byte level) { // parametro de nivel de personaje y retorna True si esta entre [1, 99]
        if (level < 1 || level > 99) {
            System.out.println(ERR_LEVEL);
            return false;
        } else {
            return true;
        }
    }

    public static boolean isValidDifficulty(byte difficulty) { // parametro nivel de dificultad y true si parametro esta [1, 4]
        if (difficulty < 1 || difficulty > 4) {
            return false;
        } else {
            return true;
        }
    }

    public static double getMultiplierByDifficulty(byte difficulty) {
        // devolvemos el valor del array al restar el nivel de dificultad menos uno para obtener la posicion
        return DIFFICULTY_MULTIPLIER[difficulty - 1];
    }

    public static double calculateGold(byte level, byte difficulty) { // recibe nivel PJ y dificultad, retorna cantidad de oro para ese logro, cada logro tiene una cantidad de oro base y luego se suma un procentaje dependiendo del nivel del PJ y la de la difiucltad del logro
        return GOLD_BASE + level * LEVEL_MULTIPLIER * getMultiplierByDifficulty(difficulty);
    }

    public static void getAchievementsGold(byte level, byte[] difficulties) {
        Locale.setDefault(Locale.US); // necesitamos esta linea para cambiar la configuracion regional y que al formatear la variable en la salida aparezca con un punto y no una coma
        double total_gold = 0;
        int valid_achiv = 0;
        int unvalid_achiv = 0;

        if (isValidLevel(level)) {
            for (int i = 0; i < difficulties.length; i++) {
                if (isValidDifficulty(difficulties[i])) {
                    total_gold += calculateGold(level, difficulties[i]);
                    valid_achiv += 1;
                } else {
                    unvalid_achiv += 1;
                }
            }
            System.out.format("You have obtained a total of %.1f gold from %d achievements.%n%d achievements with invalid difficulties have been detected.%n", total_gold, valid_achiv, unvalid_achiv);
        }
    }
}
