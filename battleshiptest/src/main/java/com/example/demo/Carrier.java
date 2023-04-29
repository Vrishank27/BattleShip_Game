package com.example.demo;

import java.util.ArrayList;
import java.util.*;

public class Carrier implements Ship {
    private int length;
    private int health;
    private ArrayList<ArrayList<Integer>> location;
    private boolean placed;
    private int id;

    public Carrier(){
        this.length = 5;
        this.health = 5;
        this.placed = false;
        location = new ArrayList<ArrayList<Integer>>();
        this.id = 1;
    }

    public int getHealth(){
        return this.health;
    }

    public int getLength(){
        return this.length;
    }

    public void setLocation(int x , int y){
        ArrayList<Integer> temp = new ArrayList<Integer>(Arrays.asList(x , y));
        location.add(temp);
        this.placed = true;
    }


    public void reduceHealth(int x , int y){
        this.health--;
        ArrayList<Integer> temp = new ArrayList<Integer>(Arrays.asList(x , y));
        location.remove(temp);
    }

    public boolean isSet(){
        return placed;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public void eraseLocationDetails() {
        location.clear();
    }
    
}
