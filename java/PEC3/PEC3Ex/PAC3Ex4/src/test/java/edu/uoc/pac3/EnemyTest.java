package edu.uoc.pac3;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class EnemyTest {

    @Test
    @DisplayName("Enemy creation - Valid general parameters")
    void testEnemyCreationValidParameters(){
        // creamos un objeto de Enemy
        Enemy enemy = new Enemy("Enemigo1", 50, 5, 10, 10, 15);

        assertEquals("Enemigo1", enemy.getName());
        assertEquals(50, enemy.getHealth());
        assertEquals(5, enemy.getMinDamage());
        assertEquals(10, enemy.getMaxDamage());
        assertEquals(10, enemy.getPosition().getX());
        assertEquals(15, enemy.getPosition().getY());
    }

    @Test
    @DisplayName("Enemy creation - Valid name")
    void testEnemyCreationValidName(){
        // creamos un objeto de Enemy
        Enemy enemy = new Enemy("Marc", 100, 50, 100, 35, 50);

        assertNotNull(enemy);
        assertEquals("Marc", enemy.getName());

        enemy = new Enemy(".", 100, 50, 100, 35, 50);

        assertNotNull(enemy);
        assertEquals(".", enemy.getName());
    }



}
