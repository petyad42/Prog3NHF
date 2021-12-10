package model;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PieceTest {
    Game g;

    @BeforeEach
    public void setUp(){
        g = new Game();
    }

    @Test
    void getColor() {
        assertEquals(Colour.RED,g.board[0][1].getColor());
        assertEquals(Colour.WHITE,g.board[7][0].getColor());
    }

    @Test
    void isKing() {
        assertFalse(g.board[0][1].isKing());
    }

    @Test
    void getRow() {
        assertEquals(0,g.board[0][1].getY(),0);
        assertEquals(1,g.board[1][0].getY(),0);
    }

    @Test
    void getCol() {
        assertEquals(0,g.board[7][0].getX(),0);
        assertEquals(1,g.board[6][1].getX(),0);
    }

    @Test
    void move() {
        int fromX = g.board[5][2].getX();
        int fromY = g.board[5][2].getY();
        g.Move(2,5,1,4);
        int toX =g.board[4][1].getX();
        int toY =g.board[4][1].getY();
        Assert.assertEquals(2,fromX,0);
        Assert.assertEquals(5,fromY,0);
        Assert.assertEquals(1,toX,0);
        Assert.assertEquals(4,toY,0);
        //A PARAMÉTERES NEM MŰKÖDÖTT
        fromX = g.board[2][1].getX();
        fromY = g.board[2][1].getY();
        g.Move(1,2,0,3);
        toX =g.board[3][0].getX();
        toY =g.board[3][0].getY();
        Assert.assertEquals(1,fromX,0);
        Assert.assertEquals(2,fromY,0);
        Assert.assertEquals(0,toX,0);
        Assert.assertEquals(3,toY,0);
    }
}