package edu.uoc.pac4;

import java.util.Arrays;
import java.util.Objects;

public class DPOOVector<T> {
    private T[] elements; // la clase usara este vector para almacenar las diferentes clases
    private int size; // tamaño del vector

    @SuppressWarnings("unchecked")
    public DPOOVector(int size) { // constructor donde indicamos el tamaño maximo que podra tener el vector
        elements = (T[]) new Object[size]; // inicializamos elements como un array de tipo Object casteandolo al tipo generico que queremos
    }

    public boolean add(T elem) {
        if (elem == null || (int) Arrays.stream(elements).filter(Objects::nonNull).count() == elements.length) {
            return false;
        }
        elements[size++] = elem;
        return true;
    }

    public int size() {
        return (int) Arrays.stream(elements).filter(Objects::nonNull).count();
    } // filtramos los objetos no nulos

    public T get(int index) {
        if (elements[index] == null) {
            return null;
        }
        return elements[index];
    }

    public void remove(int index) {
        if (index < 0 || index >= elements.length) { // si el indice es negativo o mayor que el array, no hace nada
            return;
        }
        for (int i = index; i < elements.length - 1; i++) { // desplazamos el resto de elementos para ocupar la posicion del elemento eleiminado
            elements[i] = elements[i + 1];
        }
        elements[elements.length - 1] = null; // colocamos valor null en ultima posicion
    }
}
