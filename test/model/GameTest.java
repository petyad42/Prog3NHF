package model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    Game g;

    @BeforeEach
    public void setUp(){
        g = new Game();
    }
    @Test
    void move() {
        int fromX = g.board[5][0].getX();
        int fromY = g.board[5][0].getY();
        g.Move(0,5,1,4);
        int toX =g.board[4][1].getX();
        int toY =g.board[4][1].getY();
        Assert.assertEquals(0,fromX,0);
        Assert.assertEquals(5,fromY,0);
        Assert.assertEquals(1,toX,0);
        Assert.assertEquals(4,toY,0);
    }

    @Test
    void isValidMove() {
        int fromX = g.board[5][0].getX();
        int fromY = g.board[5][0].getY();
        Assert.assertFalse(g.isValidMove(fromX,fromY,fromX+1,fromY+1));
        Assert.assertTrue(g.isValidMove(fromX,fromY,fromX+1,fromY-1));
    }

    @Test
    void getWhosTurn() {
        Assert.assertEquals(Colour.WHITE,g.getWhosTurn());
        g.Move(0,5,1,4);
        Assert.assertEquals(Colour.RED,g.getWhosTurn());
    }

    @Test
    void countPieces() {
        Assert.assertEquals(12,g.countPieces(Colour.WHITE));
        Assert.assertEquals(12,g.countPieces(Colour.RED));
    }
    @Test
    void kill(){
        g.Move(0,5,1,4);
        g.Move(3,2,2,3);
        int targetX=g.board[3][2].getX();
        int targetY=g.board[3][2].getY();
        g.Move(1,4,3,2);
        assertEquals(null,g.board[3][2]);

    }
}