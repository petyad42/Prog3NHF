package view;

import controller.Controller;
import model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FrameTest {
    public Frame f;
    @BeforeEach
    public void setUp(){
        Game g = new Game();
        Controller c = new Controller();
        f=new Frame(g,c);
    }
    @Test
    public void initTest(){
        assertEquals(800,f.getWidth());
        assertEquals(800,f.getHeight());
    }

}