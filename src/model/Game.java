package model;

public class Game {
    public Piece[][] board = new Piece[8][8];
    private Colour whosTurn;

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
    //ITT KELL LE ELENNŐRIZNI HOGY VALID MOVE-E


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

    public boolean isValidMove(int fromX, int fromY,int toX,int toY){
        if((toY+toX)%2!=0){
            if(board[fromY][fromX].isKing()==true){
                if (Math.abs(fromX - toX) == 2 && Math.abs(fromY - toY )== 2&&board[(fromY+toY)/2][(fromX+toX)/2]!=null
                &&board[(fromY+toY)/2][(fromX+toX)/2].getColor()==(whosTurn==Colour.WHITE?Colour.RED:Colour.WHITE)) {
                    board[(fromY+toY)/2][(fromX+toX)/2]=null;
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
                    board[fromY-1][(fromX+toX)/2]=null;
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
                    board[fromY+1][(fromX+toX)/2]=null;
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

    public Colour getWhosTurn() {
        return whosTurn;
    }

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
}
