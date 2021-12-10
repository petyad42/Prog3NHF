package model;

import java.io.*;

/**
 * A játékot modellező osztály
 */
public class Game implements Serializable { //TODO: VIZSGÁK UTÁN JSON
    public Piece[][] board = new Piece[8][8];
    private Colour whosTurn;

    /**
     * Default konstruktor, kitölti a pályát a bábukkal, szabványnak megfelelően(lásd: https://hu.wikipedia.org/wiki/D%C3%A1maj%C3%A1t%C3%A9k )
     * Senki sem lesz király és a fehér kezd mindig és mindig váltakozik, hogy ki jön következőnek
     */
    public Game(){
        whosTurn=Colour.WHITE;
        for(int i =0;i<8;i++)
            for (int j =0;j<8;j++)
            {
                if((i+j)%2!=0)
                {
                    if(i<3)
                        board[i][j]=new Piece(j,i,Colour.RED);
                    else if(i>4)
                        board[i][j]=new Piece(j,i,Colour.WHITE);
                }
            }
            Status();
    }

    /**
     * Mozgás függvény a bábu mozgatásához
     * Ha elér a bábu a pálya túlsó felére, király lesz
     * @param fromX A bábu eredeti sora a tömbben
     * @param fromY A bábu eredeti oszlopa a tömbben
     * @param toX A bábu cél sora a tömbben
     * @param toY A bábu cél oszlopa a tömbben
     * @return  Igazgént tér vissza, ha a lépés sikeres volt
     * @throws NullPointerException Ha üresmezőre próbálunk kattintani
     */
    public boolean Move(int fromX, int fromY,int toX,int toY)throws NullPointerException{
        if(isValidMove(fromX, fromY, toX, toY)){
            System.out.println(fromX + " " + fromY + " " + toX + " " + toY);
            board[fromY][fromX].Move(toX, toY);
            board[toY][toX] = board[fromY][fromX];
            board[fromY][fromX] = null;
            board[toY][toX].setSelected(false);
            if(whosTurn==Colour.WHITE) {
                if(toY==0)
                    board[toY][toX].setKing(true);
                whosTurn = Colour.RED;
            }
            else{
                if(toY==7)
                    board[toY][toX].setKing(true);
                whosTurn = Colour.WHITE;
            }
            Status();
            return true;
        }
        return false;
    }

    /**
     * A mozgáshoz ellenőrzi a lehetséges lépéseket adott bábura, a Move()-nak segít lépni szabályosan.
     * @param fromX Adott bábu eredeti sora a tömbben
     * @param fromY Adott bábu eredeti oszlopa a tömbben
     * @param toX Adott bábu cél sora a tömbben
     * @param toY Adott bábu cél oszlopa a tömbben
     * @return Igazként tér vissza, ha érvényes a lépés, hamis, ha nem
     */
    public boolean isValidMove(int fromX, int fromY,int toX,int toY){
        if((toY+toX)%2!=0){
            if(board[fromY][fromX].isKing()==true){
                if (Math.abs(fromX - toX) == 2 && Math.abs(fromY - toY )== 2&&board[(fromY+toY)/2][(fromX+toX)/2]!=null
                &&board[(fromY+toY)/2][(fromX+toX)/2].getColor()==(whosTurn==Colour.WHITE?Colour.RED:Colour.WHITE)) {
                    kill((fromX+toX)/2,(fromY+toY)/2);
                    return true;
                }
                if(Math.abs(fromX - toX) == 1 && Math.abs(fromY - toY) == 1){
                    return true;
                }
                else {
                    board[fromY][fromX].setSelected(false);
                    System.out.println("NEM ÉRVÉNYES LÉPÉS!");
                    return false;
                }
            }
            else if(whosTurn==Colour.WHITE) {
                if (Math.abs(fromX - toX) == 2 && fromY - toY == 2&&board[fromY-1][(fromX+toX)/2]!=null
                        &&board[fromY-1][(fromX+toX)/2].getColor()==Colour.RED) {
                    kill((fromX+toX)/2,fromY-1);
                    return true;
                }
                if(Math.abs(fromX - toX) == 1 && fromY - toY == 1){
                    return true;
                }
                else {
                    board[fromY][fromX].setSelected(false);
                    System.out.println("NEM ÉRVÉNYES LÉPÉS!");
                    return false;
                }
            }
            else if(whosTurn==Colour.RED) {
                if (Math.abs(fromX - toX) == 2 && toY - fromY == 2&&board[fromY+1][(fromX+toX)/2]!=null
                        &&board[fromY+1][(fromX+toX)/2].getColor()==Colour.WHITE) {
                    kill((fromX+toX)/2,fromY+1);
                    return true;
                }
                if(Math.abs(fromX - toX) == 1 && toY - fromY == 1){
                    return true;
                }
                else {
                    board[fromY][fromX].setSelected(false);
                    System.out.println("NEM ÉRVÉNYES LÉPÉS!");
                    return false;
                }
            }

        }
        System.out.println("NEM ÉRVÉNYES LÉPÉS!");
        board[fromY][fromX].setSelected(false);
        return false;
    }



    /**
     * Ellenőrzi, hogy ki van éppen körön
     * @return Visszadja a színt, aki éppen körön van
     */
    public Colour getWhosTurn() {
        return whosTurn;
    }

    /**
     * Debughoz használatos, a consolera kiírja a játék jelenlegi állapotát szöveg formátumban
     */
    public void Status(){
        for(int i =0;i<8;i++)
        {
            for (int j =0;j<8;j++)
            {
                if(board[i][j]!=null)
                    System.out.print(board[i][j].getColor());
                else
                    System.out.print("-");
            }
            System.out.println("");
        }
        System.out.println("WHITES: "+countPieces(Colour.WHITE)+" REDS: "+countPieces(Colour.RED));
        System.out.println("\n");
    }

    /**
     * Megszámolja, hogy egy adott színű bábuból mennyi van a pályán. Segít eldönteni, hogy vesztett-e valaki
     * @param c Keresett szín
     * @return Átadott szímű bábuk száma pályán
     */
    public int countPieces(Colour c){
        int sum=0;
        for(int i =0;i<8;i++)
        {
            for (int j =0;j<8;j++)
            {
                if(board[i][j]!=null&&board[i][j].getColor()==c)
                    sum++;
            }
        }
        return sum;
    }

    /**
     * Elmenti a pályát
     * @param path Mentési fájl útvonala
     */
    public void save(String path){
        FileOutputStream f = null;
        try {
            f = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(f);
            out.writeObject(board);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Betölt egy pályát
     * @param input beltöltendő fájl útvonala
     */
    public void load(File input){
        {
            try { FileInputStream f = new FileInputStream(input);
                ObjectInputStream in = null;
                in = new ObjectInputStream(f);
                board = (Piece[][]) in.readObject();
                in.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Levesz "megöl" egy bábut a pályán. Kettőt lépéskor használja a program, leütéshez
     * @param x Törölni kívánt bábu sora
     * @param y Törölni kívánt bábu oszlopa
     */
    public void kill(int x, int y){
        board[y][x]=null;
    }
}
