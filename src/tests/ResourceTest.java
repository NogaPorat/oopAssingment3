package tests;

import Game.*;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

class ResourceTest {

    Energy energy = new Energy(10);
    Health health = new Health(100);
    Cooldown coolDown = new Cooldown(5);
    Mana mana = new Mana(400,400);
    Arrows arrows = new Arrows(1);


    @org.junit.jupiter.api.Test
    void arrowsEnoughTest() {
        Assertions.assertEquals(true, arrows.enough());
    }

    @org.junit.jupiter.api.Test
    void manaEnoughTest() {
        Assertions.assertEquals(false, mana.enough());
    }
    @org.junit.jupiter.api.Test
    void coolDownEnoughTest() {
        Assertions.assertEquals(true, coolDown.enough());
    }
    @org.junit.jupiter.api.Test
    void coolDownGameCastTest() {
        while(!coolDown.enough())
            coolDown.gameTick(1);
        coolDown.cast();
        Assertions.assertEquals(false, coolDown.getRemaining()==0);
    }



    @org.junit.jupiter.api.Test
    void coolDownGameTickTest() {
        coolDown.cast();
        coolDown.gameTick(1);
        Assertions.assertEquals(4, coolDown.getRemaining());
    }





}