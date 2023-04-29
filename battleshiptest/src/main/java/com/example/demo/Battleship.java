package com.example.demo;

import java.util.*;

public class Battleship implements Ship{
    private int length;
    private int health;
    private ArrayList<ArrayList<Integer>> location;
    private boolean placed;
    private int id;


    public Battleship(){
        this.length = 4;
        this.placed = false;
        this.health = 4;
        location = new ArrayList<ArrayList<Integer>>();
        this.id = 3;
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
