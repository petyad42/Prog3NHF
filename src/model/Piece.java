package model;

import java.io.Serializable;

public class Piece implements Serializable {
    int x;
    int y;
    private Colour color;      //FEHÉR=0 PIROS=1
    private boolean king;
    private boolean selected;
    public Piece(int x1,int y1, Colour c)
    {
        x=x1;
        y=y1;
        color=c;
        king=false;
        selected=false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Colour getColor() {
        return color;
    }

    public boolean isKing() {
        return king;
    }

    public int getRow(){
        return y;
    }
    public int getCol(){
        return x;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void setKing(boolean king) {
        this.king = king;
    }

    public void Move(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Piece{" +
                "x=" + x +
                ", y=" + y +
                ", color=" + color +
                ", king=" + king +
                '}';
    }
}
