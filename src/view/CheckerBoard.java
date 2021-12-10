package view;

import model.Colour;
import model.Game;

import javax.swing.*;
import java.awt.*;

public class CheckerBoard extends JComponent {
    private int boxSize=94;
    private Game game;

    @Override
    public void paint(Graphics g) {
        Image crown;
        crown = Toolkit.getDefaultToolkit().getImage("E:/BME/III/Prog3/NHF_reloaded/res/crown.png");

        for(int i =0;i<8;i++)
        {
            for (int j =0;j<8;j++)
            {
                if((i+j)%2==0)
                    g.setColor(Color.white);
                else
                    g.setColor(Color.black);
                g.fillRect(boxSize*j,boxSize*i,boxSize,boxSize);
            }
        }

        for(int i =0;i<8;i++)
        {
            for (int j =0;j<8;j++)
            {
                if(game.board[i][j]!=null) {
                    //KÖRVONAL
                    g.setColor(game.board[i][j].getColor() == Colour.RED ? Color.red : Color.white);
                    g.fillOval(j*boxSize+5, i*boxSize+5, 94-10, 94-10);
                    //BELE
                    g.setColor(game.board[i][j].getColor() == Colour.RED ? new Color(150,0,0): new Color(150,150,150));
                    g.fillOval(j*boxSize+15, i*boxSize+15, 94-30, 94-30);
                    if(game.board[i][j].isSelected()==true){
                        g.setColor(Color.green);
                        g.fillOval(j*boxSize+15, i*boxSize+15, 94-30, 94-30);
                    }
                    if(game.board[i][j].isKing()==true)
                    g.drawImage(crown,j*boxSize+15,i*boxSize+15,94-30,94-30,this);
                }
            }
        }

    }

    public void setGame(Game game) {
        this.game = game;
    }
}
