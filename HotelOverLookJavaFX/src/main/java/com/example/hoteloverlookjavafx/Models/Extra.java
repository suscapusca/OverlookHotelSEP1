package com.example.hoteloverlookjavafx.Models;

public class Extra {
    private int extraBed;
    private boolean extraSmoke;

    //basic constructor
    public Extra(int eB, boolean eS){
        this.extraBed = eB;
        this.extraSmoke = eS;
    }

    public void changeExtras(int eB, boolean eS){
        this.extraBed = eB;
        this.extraSmoke = eS;
    }

    //GET AND SETTERS
    public int getExtraBed() {
        return extraBed;
    }

    public void setExtraBed(int extraBed) {
        this.extraBed = extraBed;
    }

    public boolean isExtraSmoke() {
        return extraSmoke;
    }

    public void setExtraSmoke(boolean extraSmoke) {
        this.extraSmoke = extraSmoke;
    }

}