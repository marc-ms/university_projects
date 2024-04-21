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

        enemy = new Enemy("15632544", 100, 50, 100, 35, 50);

        assertNotNull(enemy);
        assertEquals("15632544", enemy.getName());

        enemy = new Enemy("%$&.,/&-", 100, 50, 100, 35, 50);

        assertNotNull(enemy);
        assertEquals("%$&.,/&-", enemy.getName());
    }

    @Test
    @DisplayName("Enemy creation - Invalid name")
    void testEnemyCreationInvalidName(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Enemy(null, 100, 50, 100, 35, 50));
        assertEquals(Enemy.INVALID_NAME, exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> new Enemy("", 100, 50, 100, 35, 50));
        assertEquals(Enemy.INVALID_NAME, exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> new Enemy("A".repeat(51), 100, 50, 100, 35, 50));
        assertEquals(Enemy.INVALID_NAME, exception.getMessage());
    }

    @Test
    @DisplayName("Enemy creation - Get health")
    void testEnemyCreationGetHealth(){
        Enemy enemy = new Enemy("Marc", 100, 50, 100, 35, 50);

        assertNotNull(enemy);
        assertEquals(100, enemy.getHealth());
    }

    @Test
    @DisplayName("Enemy creation - Get minDamage")
    void testEnemyCreationGetMinDamage(){
        Enemy enemy = new Enemy("Marc", 100, 50, 100, 35, 50);

        assertNotNull(enemy);
        assertEquals(50, enemy.getMinDamage());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Enemy("Marc", 100, -1, 100, 35, 50));
        assertEquals(Enemy.INVALID_MIN_DAMAGE, exception.getMessage());
    }

    @Test
    @DisplayName("Enemy creation - Get maxDamage")
    void testEnemyCreationGetMaxDamage(){
        Enemy enemy = new Enemy("Marc", 100, 50, 100, 35, 50);

        assertNotNull(enemy);
        assertEquals(100, enemy.getMaxDamage());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Enemy("Marc", 100, 50, 49, 35, 50));
        assertEquals(Enemy.INVALID_MAX_DAMAGE, exception.getMessage());
    }

    @Test
    @DisplayName("Enemy creation - Get position")
    void testEnemyCreationGetPosition(){
        Enemy enemy = new Enemy("Marc", 100, 50, 100, 35, 50);

        assertNotNull(enemy);
        assertEquals(35, enemy.getPosition().getX());
        assertEquals(50, enemy.getPosition().getY());
    }

    @Test
    @DisplayName("Enemy creation - Live or dead")
    void testEnemyCreationValidDead(){
        Enemy enemy = new Enemy("Marc", -1, 50, 100, 35, 50);

        assertNotNull(enemy);
        assertTrue(enemy.isDead());

        enemy = new Enemy("Marc", 1, 50, 100, 35, 50);

        assertNotNull(enemy);
        assertFalse(enemy.isDead());
    }

    @Test
    @DisplayName("Enemy creation - Moving")
    void testEnemyCreationValidMove(){
        Enemy enemy = new Enemy("Marc", 10, 50, 100, 35, 50);

        assertNotNull(enemy);
        assertTrue(enemy.move(40, 50));

        enemy = new Enemy("Marc", 10, 50, 100, 0, 0);

        assertNotNull(enemy);
        assertFalse(enemy.move(51, 51));
    }

    @Test
    @DisplayName("Enemy creation - Attack")
    void testEnemyCreationValidAttack(){
         /* debido a que el metodo contiene una generador de numeros aleatorios entre 0.0 y 1.0
         no comprobaremos el valor exacto de attack sino que comprobaremos que
         se encuentra comprendido entre los valores mínimos y máximos del generador, es decir,
         para 0.0 => minDamage y para 1.0 => maxDamage + 1 */

        Enemy enemy = new Enemy("Marc", 10, 50, 100, 35, 50);

        assertNotNull(enemy);
        assertTrue(enemy.getMinDamage() <= enemy.attack() && enemy.attack() <= enemy.getMaxDamage() + 1);


    }

    @Test
    @DisplayName("Enemy creation - Receiving damage")
    void testEnemyCreationValidReceiveDamage(){
        Enemy enemy = new Enemy("Marc", 10, 50, 100, 35, 50);

        assertNotNull(enemy);
        enemy.receiveDamage(5);
        assertEquals(5, enemy.getHealth());

        enemy = new Enemy("Marc", 100, 50, 100, 35, 50);

        assertNotNull(enemy);
        enemy.receiveDamage(55);
        assertEquals(45, enemy.getHealth());

        enemy = new Enemy("Marc", 10, 50, 100, 35, 50);

        assertNotNull(enemy);
        enemy.receiveDamage(15);
        assertEquals(0, enemy.getHealth());
    }
}
