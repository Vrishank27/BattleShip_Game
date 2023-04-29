package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.swing.text.html.HTMLDocument.Iterator;

public class AiPlayer extends Player{

    private HashMap<ArrayList<Integer>, Integer > coordList;

    public AiPlayer(){
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
        coordList = new HashMap<ArrayList<Integer> , Integer>();
        for(int i = 0 ; i < 10 ; i ++){
            for(int j = 0 ; j < 10 ; j++){
                ArrayList<Integer> temp = new ArrayList<Integer>();
                temp.add(i);
                temp.add(j);
                coordList.put(temp , 1);
            }
        }
        for(ArrayList<Integer> l: coordList.keySet()){
            System.out.println(l.get(0) + " " + l.get(1));
        }
    }


    @Override
    Map<Coordinate , Boolean> execAttack(int x, int y, Gameboard opGameboard) {
        Random random = new Random();
        Map<Coordinate , Boolean> m = new HashMap<Coordinate , Boolean>();
        Coordinate c = new Coordinate(x, y);
        int res ;
        int randX = random.nextInt(board.getLength());
        int randY = random.nextInt(board.getBreadth());
        while(true){
            randX = random.nextInt(board.getLength());
            randY = random.nextInt(board.getBreadth());
            ArrayList<Integer> temp = new ArrayList<Integer>();
            temp.add(randX);
            temp.add(randY);
            System.out.println(randX + " " + randY);
            int possible = 0;
            for(ArrayList<Integer> l: coordList.keySet()){
                if(coordList.get(l) == 1){
                    possible = 1;
                    c.setX(randX);
                    c.setY(randY);
                }
            }
            if(possible == 0){
                m.put(c, true);
                return m;
            }
            if(coordList.get(temp) == 1){
                res = board.attackShip(randX, randY, opGameboard);
                coordList.replace(temp, 1, 0);
                break;
            }
        }
        System.out.println("ai " + randX + " " + randY);
        reduceShips();
        if(res == 2){
            m.put(c, false);
            return m;
        }
        else{
            m.put(c, true);
            return m;
        }
    }

    @Override
    void reduceShips() {
        for(int i = 0 ; i < listOfShips.length ; i++){
            if(listOfShips[i] != null &&  listOfShips[i].getHealth() == 0){
                this.ships--;
                this.listOfShips = removeTheElement(this.listOfShips, i);
            }
            //System.out.println(i + " "+listOfShips[i].getHealth());
        }
    }




    @Override
    boolean placeShips(int x, int y, int shipId, boolean dir) {
        // TODO Auto-generated method stub
        return false;
    }


    @Override
    boolean placeShips() {
        Random random = new Random();
        for(int i = 0 ; i < 5 ; i++){
            int randX = random.nextInt(board.getLength());
            int randY = random.nextInt(board.getBreadth());
            boolean rand_dir = random.nextBoolean();
            Ship temp = FactoryShip.getAppropriateShip(i);
            shipList.replace(i, true);
            listOfShips[i] = temp;
            while(board.setShip(randX, randY, temp, rand_dir)==false){
                randX = random.nextInt(board.getLength());
                randY = random.nextInt(board.getBreadth());
                rand_dir = random.nextBoolean();
            }
            //System.out.println("Placed ship " + i + " " + randX + " " + randY);
        }  
        return true;
    }
    
}
