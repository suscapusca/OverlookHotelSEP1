package com.example.hoteloverlookjavafx.Models;

public class Room {
    private int number;
    private float price;
    private String type;
    private int beds;
    private int bedRooms;
    private Extra extra;

    //basic constructor
    public Room(int n, float p, String t, int b, int bR){
        this.number = n;
        this.price = p;
        this.type = t;
        this.beds = b;
        this.bedRooms = bR;
    }

    //constructor if room has extra
    public Room(int n, float p, String t, int b, int bR, Extra e){
        this.number = n;
        this.price = p;
        this.type = t;
        this.beds = b;
        this.bedRooms = bR;
        this.extra = e;
    }

    //function to change room info
    private void changeRoomInfo(int n, float p, String t, int b, int bR){
        this.number = n;
        this.price = p;
        this.type = t;
        this.beds = b;
        this.bedRooms = bR;
    }

    private void putExtra(Extra e){
        this.extra = e;
    }

    //GET AND SETTERS
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    public int getBedRooms() {
        return bedRooms;
    }

    public void setBedRooms(int bedRooms) {
        this.bedRooms = bedRooms;
    }
}
