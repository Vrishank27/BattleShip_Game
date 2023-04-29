package com.example.demo;

import java.util.HashMap;
import java.util.Map;

public class HumanPlayer extends Player{
    

    public HumanPlayer(){
        this.playerId = 1;
        this.opPlayerId = 0;
        this.hits = 0;
        this.board = new Gameboard();
        this.shipList = new HashMap<Integer , Boolean>();
        this.turn = true;
        this.opGridView = new char[10][10];
        for(int i = 0 ; i < 10 ; i++){
            for(int j = 0 ; j < 10 ; j++){
                opGridView[i][j] = 'X';
            }
        }
        shipList.put(0, false);
        shipList.put(1, false);
        shipList.put(2, false);
        shipList.put(3, false);
        shipList.put(4, false);
        this.ships = 5;
        this.listOfShips = new Ship[5];
    }

    @Override
    Map<Coordinate , Boolean> execAttack(int x, int y , Gameboard opGameboard) {
        int res_value = this.board.attackShip(x, y, opGameboard);
        reduceShips();
        if(res_value == 2 || res_value == 1){
            this.opGridView[x][y] = 'W';
            Map<Coordinate , Boolean> m = new HashMap<Coordinate , Boolean>();
            Coordinate c = new Coordinate(x, y);
            m.put( c , false);
            return m;
        }
        if(res_value == 3){
            this.opGridView[x][y] = 'D';
            Map<Coordinate , Boolean> m = new HashMap<Coordinate , Boolean>();
            Coordinate c = new Coordinate(x, y);
            m.put( c , true);
            return m;
        }
        else
        {
            Map<Coordinate , Boolean> m = new HashMap<Coordinate , Boolean>();
            Coordinate c = new Coordinate(x, y);
            m.put( c , false);
            return m;
        }
    }

    @Override
    boolean placeShips(int x, int y, int shipId, boolean dir) {
        if(!shipList.get(shipId)){
            Ship temp = FactoryShip.getAppropriateShip(shipId);
            shipList.replace(shipId, true);
            listOfShips[shipId] = temp;
            return board.setShip(x , y , temp , dir);
        }
        else{
            return false;
        }
    }

    @Override
    void reduceShips() {
        for(int i = 0 ; i < listOfShips.length ; i++){
            if(listOfShips[i] != null && listOfShips[i].getHealth() == 0){
                this.ships--;
                this.listOfShips = removeTheElement(this.listOfShips, i);
            }
            //System.out.println(i + " "+listOfShips[i].getHealth());
        }
    }


    @Override
    boolean placeShips() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'placeShips'");
    }
    
}
