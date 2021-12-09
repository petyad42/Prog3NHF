package view;

import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Menu extends JComponent {
    public Rectangle newGame = new Rectangle(250,100,300,100);

    @Override
    public void paint(Graphics g) {
        Image background;
        background = Toolkit.getDefaultToolkit().getImage("res/background.jpg");
        g.drawImage(background,0,0,this);
        Font f1 = null;
        try {
            f1 = Font.createFont(Font.TRUETYPE_FONT,new File("res/upheavtt.ttf")).deriveFont(50f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(f1);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.setFont(f1);
        g.setColor(Color.BLACK);
        g.fillRoundRect(250,100,300,100,40,40);  //NEWGAME
        g.fillRoundRect(250,300,300,100,40,40);  //LOADGAME
        g.fillRoundRect(250,500,300,100,40,40);  //QUIT
        g.setColor(Color.WHITE);
        g.drawString("New Game",285 , 150+10);
        g.drawString("Load Game",275, 350+10);
        g.drawString("Quit",350, 550+10);
    }
}
