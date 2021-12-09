package view;

import controller.Controller;
import model.Game;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame{
        //public STATE state = STATE.MENU;
        public CheckerBoard cb;
        //public Menu menu;
        public JPanel cards =new JPanel();
        public JPanel game = new JPanel();
        public JPanel menu = new JPanel();
        public JButton newGameButton = new JButton("New Game");
        public JButton quitButton = new JButton("Quit");
        public CardLayout c1 = new CardLayout();

        public Frame(Game g, Controller c){

            //super();

            cb = new CheckerBoard();
            cb.setGame(g);
            cb.addMouseListener(c);

            cards.setLayout(c1);

            menu.add(newGameButton);
            menu.add(quitButton);
            game.add(cb);

            cards.add(menu,"MENU");
            cards.add(game,"GAME");

            c1.show(cards,"GAME");


            quitButton.addActionListener(e -> {dispose();});
            newGameButton.addActionListener(e -> { c1.show(cards,"GAME");});

            add(cards);
            pack();
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
