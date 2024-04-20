package edu.uoc.pac2;

import java.util.HashMap;
import java.util.Map;

public class PasswordImprover {

    private static String specialCharacters = "!@#$%^&*()_+-=[]{}|;:',.<>/?";

    // deberemos usar StringBuilder ya que modificamos frecuentemente un string y así optimizamos el rendimiento del código
    // además, al ser un programa single thread usaremos StringBuilder
    // para usarlo debemos crearlos a partir de un constructor

    public static String replaceVowels(String password) {
        // constructor de StringBuilder
        StringBuilder newPassword = new StringBuilder();

        // creamos un array asociativo para los pares de valores prupuestos
        Map<Character, Character> vowelsForNumbers = new HashMap<>();

        // almacenamos los pares clave-valores para cada letra
        vowelsForNumbers.put('a', '4');
        vowelsForNumbers.put('e', '3');
        vowelsForNumbers.put('i', '1');
        vowelsForNumbers.put('o', '0');
        vowelsForNumbers.put('u', '2');
        vowelsForNumbers.put('A', '4');
        vowelsForNumbers.put('E', '3');
        vowelsForNumbers.put('I', '1');
        vowelsForNumbers.put('O', '0');
        vowelsForNumbers.put('U', '2');

        for (char c : password.toCharArray()){
            for (Map.Entry<Character, Character> entry : vowelsForNumbers.entrySet()){
                if (c == entry.getKey()){
                    newPassword.append(entry.getValue());
                } else if (!vowelsForNumbers.containsKey(c)){ // nunca escribirá una vocal sin transformar
                    newPassword.append(c);
                    break;
                }
            }
        }
        return newPassword.toString();
    }

    public static boolean isLetter(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    public static String replaceWithSpecialCharacters(String password) {
        // constructor de StringBuilder
        StringBuilder specialPassword = new StringBuilder();

        specialPassword.append(password.charAt(0));

        // entero que usaremos para poder repetir los caracteres especiales
        int specialCharacterPosition = 0;

        for (int i = 1; i < password.length(); i++){
            if ((isLetter(password.charAt(i)) && isLetter(password.charAt(i - 1)))){
                specialPassword.append(specialCharacters.charAt(specialCharacterPosition));
                if (specialCharacterPosition >= specialCharacters.length() - 1){
                    specialCharacterPosition = -1;
                }
                specialCharacterPosition++;
            } else{
                specialPassword.append(password.charAt(i));
            }
        }
        return specialPassword.toString();
    }

    public static String improvePassword(String password) {
        // primero eliminamos los posibles espacios en blanco del parametro password
        String cleanPassword = password.replaceAll("\\s+", "");
        // metodo para convertir las vocales en numeros
        String firstReplacePassword = replaceVowels(cleanPassword);
        return replaceWithSpecialCharacters(firstReplacePassword);
    }

}
