package view;

import controller.Controller;
import model.Game;

import javax.swing.*;

public class Frame extends JFrame{
        //public STATE state = STATE.MENU;
        public CheckerBoard cb;
        //public Menu menu;

        public Frame(Game g, Controller c){
            //super();
            setSize(800,800);
            setTitle("Checkers");
            setLocationRelativeTo(null);
            setResizable(false);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


           // if(state==STATE.GAME){
                cb = new CheckerBoard();
                cb.setGame(g);
                cb.addMouseListener(c);
                getContentPane().add(cb);
            //}
           /* if(state==STATE.MENU) {
                menu = new Menu();
                menu.addMouseListener(c);
                getContentPane().add(menu);
            }*/
            setVisible(true);
        }
        public void update(){
            cb.repaint();
        }
}
