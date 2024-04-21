package edu.uoc.pac3;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
public class PositionTest {

    @Test
    @DisplayName("Position creation - Valid general parameters")
    void testPositionCreationValidParameters(){
        // creamos un objeto de Position
        Position position = new Position(50, 60);

        assertEquals(50, position.getX());
        assertEquals(60, position.getY());
    }

    @Test
    @DisplayName("Position creation - Valid X position")
    void testPositionCreationValidX(){
        // creamos un objeto de Position
        Position position = new Position(50, 60);

        assertNotNull(position);
        assertEquals(50, position.getX());

        position = new Position(0, 60);

        assertNotNull(position);
        assertEquals(0, position.getX());

        position = new Position(1024, 60);

        assertNotNull(position);
        assertEquals(1024, position.getX());
    }

    @Test
    @DisplayName("Position creation - Invalid X position")
    void testPositionCreationInvalidX(){
        // creamos un objeto de Position
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Position(-1, 60));
        assertEquals(Position.INVALID_X, exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> new Position(1025, 60));
        assertEquals(Position.INVALID_X, exception.getMessage());
    }

    @Test
    @DisplayName("Position creation - Valid Y position")
    void testPositionCreationValidY(){
        // creamos un objeto de Position
        Position position = new Position(50, 60);

        assertNotNull(position);
        assertEquals(60, position.getY());

        position = new Position(0, 0);

        assertNotNull(position);
        assertEquals(0, position.getY());

        position = new Position(1024, 512);

        assertNotNull(position);
        assertEquals(512, position.getY());
    }

    @Test
    @DisplayName("Position creation - Invalid Y position")
    void testPositionCreationInvalidY(){
        // creamos un objeto de Position
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Position(50, -1));
        assertEquals(Position.INVALID_Y, exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> new Position(50, 513));
        assertEquals(Position.INVALID_Y, exception.getMessage());
    }

    @Test
    @DisplayName("Position creation - Distance")
    void testPositionCreationDistance(){
        Position position1 = new Position(50, 60);
        Position position2 = new Position(60, 70);

        assertNotNull(position1);
        assertNotNull(position2);
        assertEquals(14.14, (double) Math.round(Position.distance(position1, position2)*100)/100);
    }

}
