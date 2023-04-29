package com.example.demo;

import java.util.HashMap;
import java.util.Map;

public abstract class Player{
    protected int playerId;
    protected int opPlayerId;
    protected int hits;
    protected boolean turn;
    protected Gameboard board;
    protected HashMap<Integer , Boolean> shipList;
    protected char[][] opGridView;
    protected int ships;
    protected Ship [] listOfShips;

    abstract Map<Coordinate , Boolean> execAttack(int x , int y , Gameboard opGameboard);
    abstract boolean placeShips(int x , int y , int shipId , boolean dir);
    abstract boolean placeShips();
    abstract void reduceShips();
    protected char[][] getBoard(){
        return this.board.getViewerBoard();
    }
    protected char[][] getOpBoardView(){
        return this.opGridView;
    }
    protected Gameboard getBoardClass(){
        return this.board;
    }
    protected int getShips(){
        return this.ships;
    }
    public Ship[] removeTheElement(Ship[] arr, int index)
    {
 
        // If the array is empty
        // or the index is not in array range
        // return the original array
        if (arr == null || index < 0
            || index >= arr.length) {
 
            return arr;
        }
 
        // Create another array of size one less
        Ship[] anotherArray = new Ship[arr.length - 1];
 
        // Copy the elements except the index
        // from original array to the other array
        for (int i = 0, k = 0; i < arr.length; i++) {
 
            // if the index is
            // the removal element index
            if (i == index) {
                continue;
            }
 
            // if the index is not
            // the removal element index
            anotherArray[k++] = arr[i];
        }
 
        // return the resultant array
        return anotherArray;
    }
 
}