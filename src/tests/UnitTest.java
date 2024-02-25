package tests;



import org.junit.jupiter.api.Assertions;
import Game.*;

import static org.junit.jupiter.api.Assertions.*;

class UnitTest {

    Position playerPosition = new Position(5,5);
    Position monsterPosition = new Position(4,4);
    Player player = new Warrior(playerPosition,"omer",100,100,100,5);
    Enemy enemy = new Monster('N', monsterPosition,"Noga",100,100, 100,100,5);


    @org.junit.jupiter.api.Test
    void isAlive() {
        Assertions.assertEquals(true, player.isAlive());
    }

    @org.junit.jupiter.api.Test
    void emptyInteract() {
        Tile empty = new Empty(new Position(3,3));
        player.interact(empty);
        Assertions.assertEquals(new Position(3,3), player.getPos());
    }
    @org.junit.jupiter.api.Test
    void wallInteract() {
        Position curr = player.getPos();
        Tile wall = new Wall(new Position(7,7));
        player.interact(wall);
        Assertions.assertEquals(curr, player.getPos());
    }



    @org.junit.jupiter.api.Test
    void getName() {
        Assertions.assertEquals("Noga", enemy.getName());
    }

    @org.junit.jupiter.api.Test
    void description() {
        Assertions.assertEquals("Name: Noga Game.Health: 100/100 defence points: 100 attack points: 100 Experience: 100", enemy.description());

    }

    @org.junit.jupiter.api.Test
    void isAlive2() {
        enemy.setHealth(new Health(0));
        Assertions.assertEquals(false, enemy.isAlive());

    }


}