package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.Game;
import model.Piece;
import view.Frame;
import view.STATE;

public class Controller implements MouseListener {
    Game game;
    Frame frame;
    Piece current;
    public Controller(){
        game = new Game();
        frame = new Frame(game,this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        /*
        if(frame.state==STATE.MENU){
            if(e.getX()>250&&e.getX()<550){
               if(e.getY()>100&&e.getY()<200)
                    frame.state=STATE.GAME;
                    frame.update();
            }

        }*/
        //if(frame.state==STATE.GAME) {
            int clickedOnX = (e.getX() - (e.getX() % 94)) / 94;
            int clickedOnY = (e.getY() - (e.getY() % 94)) / 94;
            if (game.board[clickedOnY][clickedOnX] == null) {
                if (current != null) {
                    try {
                        if (game.Move(current.getX(), current.getY(), clickedOnX, clickedOnY)) ;
                        current = null;
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
        //}
    }



    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
