package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.Colour;
import model.Game;
import model.Piece;
import view.Frame;

public class Controller implements MouseListener {
    public Game game;
    Frame frame;
    Piece current;
    public Controller(){
        frame = new Frame(game,this);
    }

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
                //Erronál ford x--y
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
