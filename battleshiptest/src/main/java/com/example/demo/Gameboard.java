package com.example.demo;


public class Gameboard {
    private Ship [][] board;
    private char [][] viewerBoard;
    private int l;
    private int b;

    public Gameboard(){
        this.l = 10;
        this.b = 10;
        board = new Ship[l][b];
        viewerBoard = new char[l][b];
        for(int i = 0 ; i < 10 ; i++){
            for(int j = 0 ; j < 10 ; j++){
                board[i][j] = null;
                viewerBoard[i][j] = 'W';
            }
        }
    }

    public int getLength(){
        return this.l;
    }

    public int getBreadth(){
        return this.b;
    }

    public Ship[][] getBoard(){
        return this.board;
    }

    public char[][] getViewerBoard(){
        return this.viewerBoard;
    }

    public boolean setShip(int x , int y , Ship s , boolean orientation){ // 0 means vertical , 1 means horizontal
        if(x<0 || y<0 || x>=10 || y>=10){
            return false;
        }
        if(board[x][y] == null){
            if(orientation){
                if(x+s.getLength()>=10){
                    //System.out.println("Out of bounds 1");
                    return false;
                }
                for(int i = x ; i < x+s.getLength() ; i++){
                    if(board[i][y] != null){
                        return false;
                    }
                }
                for(int i = x ; i < x+s.getLength() ; i++){
                    board[i][y] = s;
                    viewerBoard[i][y] = (char)(s.getId()+'0');
                    //System.out.println("location of ship " + s.getId() + " is " + i+ " "+y);
                    s.setLocation(i, y);
                }
                
                return true;
            }
            
            else{
                if(y+s.getLength()>=10){
                    //System.out.println("Ob 3");
                    return false;
                }
                for(int i = y ; i < y+s.getLength() ; i++){
                    if(board[x][i] != null){
                        return false;
                    }
                }
                for(int i = y ; i < y+s.getLength() ; i++){
                    board[x][i] = s;
                    viewerBoard[x][i] = (char)(s.getId()+'0');
                    s.setLocation(x, i);
                    //System.out.println("location of ship " + s.getId() + " is " + x + " "+i);

                }
                    
                return true;
            }
        }
        else{
            return false;
        }
    }
 
    

    public void modifyOnAttack(int x , int y){
        this.viewerBoard[x][y] = 'D';
        this.board[x][y] = null;
    }

    public void modifyOnWater(int x , int y){
        this.viewerBoard[x][y] = 'W';
    }

    public int attackShip(int x , int y , Gameboard opBoard){
        
        if(x<0 || y<0 || x>=10 || y>=10){
            return 0;
        }

        if(opBoard.getBoard()[x][y] == null){
            if(viewerBoard[x][y] == 'D'){
                System.out.println("Hit a destroyed location again \n");
                return 1;
            }
            else{
                System.out.println("Hit water \n");
                opBoard.modifyOnWater(x, y);
                return 2;
            }
        }
        else{
            opBoard.getBoard()[x][y].reduceHealth(x,y);
            System.out.print("Hit the target once \n");
            opBoard.modifyOnAttack(x, y);
            return 3;
        }
    }
}
