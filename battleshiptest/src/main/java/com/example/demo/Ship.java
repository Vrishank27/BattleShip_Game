package com.example.demo;


public interface Ship {
    abstract int getLength();
    abstract int getHealth();
    abstract void reduceHealth(int x , int y);
    abstract boolean isSet();
    abstract void setLocation(int x , int y);
    abstract int getId();
    abstract void eraseLocationDetails();
}
