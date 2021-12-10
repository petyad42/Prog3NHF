package model;

import java.io.Serializable;

/**
 * Bábut reprezentáló osztály
 * a bábunak van tömbön belüli koordinátái, színe, tény, hogy király-e és hogy ki van-e éppen választva
 */
public class Piece implements Serializable {
    private int x;
    private int y;
    private Colour color;      //FEHÉR=0 PIROS=1
    private boolean king;
    private boolean selected;

    /**
     * Konstruktor, lefut bábu létrehozásakor
     * Alapból nem király és nincs kiválasztva
     * @param x1 átadott sor koordináta a bábuhoz
     * @param y1 átadott oszlop koordináta a bábuhoz
     * @param c  átadott szín a bábuhoz
     */
    public Piece(int x1,int y1, Colour c)
    {
        x=x1;
        y=y1;
        color=c;
        king=false;
        selected=false;
    }

    /**
     * X koordináta Getter
     * @return Bábu X koordinátája
     */
    public int getX() {
        return x;
    }
    /**
     * Y koordináta Getter
     * @return Bábu Y koordinátája
     */
    public int getY() {
        return y;
    }

    /**
     * Szín Getter
     * @return Bábu színe
     */
    public Colour getColor() {
        return color;
    }

    /**
     * Eldönti, hogy az adott bábu király-e
     * @return ha nem üres, akkor visszaadja, a király boole-t, különben hamis ad.
     */
    public boolean isKing() {
        if(this!=null)
            return king;
        else
            return false;
    }

    /**
     * Vissza adja, hogy ki van-e választva
     * @return igaz ha kivan választva, hamis, ha nem
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * Kiválasztja a bábut, vagy megvonja a kiválasztott állapottól
     * @param selected kiválasztja/megvonja
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    /**
     * Királlyá teszi a bábut
     * @param king
     */
    public void setKing(boolean king) {
        this.king = king;
    }

    /**
     * Egy adott bábu mozgatása
     * @param x X koordináta, ahova mozogni fog
     * @param y Y koordináta, ahova mozogni fog
     */
    public void Move(int x, int y){
        this.x = x;
        this.y = y;
    }

}
