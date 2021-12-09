package view;

import controller.Controller;
import model.Game;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame{
        //public STATE state = STATE.MENU;
        public CheckerBoard cb;
        //public Menu menu;
        public JPanel cards;


        public Frame(Game g, Controller c){

            //super();
            JPanel menu = new JPanel();
            JButton newGameButton = new JButton("New Game");
            JButton quitButton = new JButton("Quit");
            newGameButton.addActionListener(e -> {dispose();});
            quitButton.addActionListener(e -> {dispose();});
            menu.add(newGameButton);
            menu.add(quitButton);

            JPanel game = new JPanel();
            cb = new CheckerBoard();
            cb.setGame(g);
            cb.addMouseListener(c);
            getContentPane().add(cb);
            game.add(cb);

            cards = new JPanel(new CardLayout());
            cards.add(menu);
            cards.add(game);
            add(cards);

            setSize(800,800);
            setTitle("Checkers");
            setLocationRelativeTo(null);
            setResizable(false);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


           /* if(state==STATE.GAME){
                cb = new CheckerBoard();
                cb.setGame(g);
                cb.addMouseListener(c);
                getContentPane().add(cb);
            }*/
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
