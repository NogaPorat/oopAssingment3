package tests;

import Game.Position;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    Position p = new Position(3,4);

    @org.junit.jupiter.api.Test
    void rangeTest(){
        assertEquals(5, p.range(new Position(0,0)));
    }
    @org.junit.jupiter.api.Test
    void MoveTest(){
        p.move(1,0);
        assertEquals(p.getX(), new Position(4,1).getX());
    }




}