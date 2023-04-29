package com.example.demo;

public class FactoryShip {

    public static Ship getAppropriateShip(int shipId){
        if(shipId == 0){
            Ship s = new Cruiser();
            return s;
        }
        else if(shipId == 1){
            Ship s = new Carrier();
            return s;
        }
        else if(shipId == 2){
            Ship s = new Destroyer();
            return s;
        }
        else if(shipId == 3){
            Ship s = new Battleship();
            return s;
        }
        else if(shipId == 4){
            Ship s = new Submarine();
            return s;
        }
        else{
            return null;
        }
    }
}
