package view;

import controller.Controller;
import model.Colour;
import model.Game;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame{ //TODO: NORMÁLIS MÉRETEZÉS
        public CheckerBoard cb;
        public JPanel cards =new JPanel();
        public JPanel menu = new JPanel();
        public JButton newGameButton = new JButton("New Game");
        public JButton quitButton = new JButton("Quit");
        public CardLayout c1 = new CardLayout();

        public Frame(Game g, Controller c){
            super();

            cb = new CheckerBoard();
            cb.setGame(g);
            cb.addMouseListener(c);

            cards.setLayout(c1);
            //quitButton.setPreferredSize(new Dimension(50, 100));
            menu.setLayout(new GridLayout(2,1));

            Font font1 = new Font("SansSerif", Font.BOLD, 50);

            menu.add(newGameButton);
            menu.add(quitButton);

            cards.add(menu,"MENU");
            cards.add(cb,"GAME");

            c1.show(cards,"MENU");

            JMenuBar menuBar = new JMenuBar();
            JMenu game = new JMenu("Game");
            menuBar.add(game);
            JMenuItem save = new JMenuItem("Save Game");
            save.addActionListener(e -> {
                JFileChooser fileChooser = new JFileChooser("saves");
                fileChooser.setApproveButtonText("Save");
                fileChooser.showOpenDialog(null);

                c.game.save(fileChooser.getSelectedFile().toString()+".txt");});
            JMenuItem load = new JMenuItem("Load Game");
            load.addActionListener(e -> {
                JFileChooser fileChooser = new JFileChooser("saves");
                fileChooser.showOpenDialog(null);
                c.game.load(fileChooser.getSelectedFile());
                update();});
            JMenuItem exit = new JMenuItem("Exit");
            exit.addActionListener(e -> {c1.show(cards,"MENU");});
            game.add(save);
            game.add(load);
            game.add(exit);

            quitButton.setFont(font1);
            quitButton.addActionListener(e -> {dispose();});
            newGameButton.setFont(font1);
            newGameButton.addActionListener(e -> {
                c.game = new Game();
                cb.setGame(c.game);
                c1.show(cards,"GAME");
            });

            setJMenuBar(menuBar);
            add(cards);
            pack();
            setSize(800,800);
            setTitle("Checkers");
            setLocationRelativeTo(null);
            setResizable(false);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            setVisible(true);
        }
        public void update(){
            cb.repaint();
        }
}
