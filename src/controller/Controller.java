package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.Colour;
import model.Game;
import model.Piece;
import view.Frame;

/**
 * Esemény kezelő kontroller osztály
 */
public class Controller implements MouseListener {
    public Game game;
    public Frame frame;
    public Piece current;

    /**
     * Default konstruktor, létrehozza a játékhoz tartozó ablakot
     */
    public Controller(){
        frame = new Frame(game,this);
    }

    /**
     * Egérkattintás eseménykezelő.
     * Ellenőrzi, hogy kattintáskor, van-e már kiválasztott bábu és akkor mozogjon, vagy hogy egyáltalán bábura kattintott,
     * érvényes kattintás után frissíti a kijelzőt. Ha kiválaszt egy bábut, akkor kiválasztotá teszi, amit a CheckerBoard
     * osztály megjelítéskor zöld telikörrel jelez.
     * @param e Kattintás esemény
     */
    @Override
    public void mouseClicked(MouseEvent e) {
            int clickedOnX = (e.getX() - (e.getX() % 94)) / 94;
            int clickedOnY = (e.getY() - (e.getY() % 94)) / 94;
            if (game.board[clickedOnY][clickedOnX] == null) {
                if (current != null) {
                    try {
                        if (game.Move(current.getX(), current.getY(), clickedOnX, clickedOnY)){
                            current = null;
                            if(game.countPieces(Colour.WHITE)==0||game.countPieces(Colour.RED)==0){
                                frame.c1.show(frame.cards, "MENU");
                            }
                        }
                    } catch (NullPointerException npe) {
                        System.out.println("ROSSZ LÉPÉS");
                    }
                    frame.update();
                }

            } else if (game.getWhosTurn() == game.board[clickedOnY][clickedOnX].getColor()) {
                if (current != null)
                    current.setSelected(false);
                current = game.board[clickedOnY][clickedOnX];

                game.board[clickedOnY][clickedOnX].setSelected(true);
                frame.update();
                //VALID LÉPÉSEK ZÖLDDEL
            }
    }

    /**
     * NEM HASZNALT FUGGVENY
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e) {

    }
    /**
     * NEM HASZNALT FUGGVENY
     * @param e
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }
    /**
     * NEM HASZNALT FUGGVENY
     * @param e
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }
    /**
     * NEM HASZNALT FUGGVENY
     * @param e
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }
}
