package com.example.hoteloverlookjavafx.Models;

public class Booking {
    private Guest guest;
    private DateInterval dateInterval;
    private Room room;
    private int guests;
    private String extraInfo;
    private float totalPrice;
    private int nightsSpent;

    //basic constructor
    public Booking(Guest g, DateInterval dI, Room r, int guests){
        this.guest = g;
        this.dateInterval = dI;
        this.room = r;
        this.guests = guests;
    }

    //constructor with extra info
    public Booking(Guest g, DateInterval dI, Room r, int guests, String eI){
        this.guest = g;
        this.dateInterval = dI;
        this.room = r;
        this.guests = guests;
        this.extraInfo = eI;
    }

    //function to update Booking
    public void updateBooking(Guest g, DateInterval dI, Room r, int guests, String eI){
        this.guest = g;
        this.dateInterval = dI;
        this.room = r;
        this.guests = guests;
        this.extraInfo = eI;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public DateInterval getDateInterval() {
        return dateInterval;
    }

    public void setDateInterval(DateInterval dateInterval) {
        this.dateInterval = dateInterval;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getGuests() {
        return guests;
    }

    public void setGuests(int guests) {
        this.guests = guests;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getNightsSpent() {
        return nightsSpent;
    }

    public void setNightsSpent(int nightsSpent) {
        this.nightsSpent = nightsSpent;
    }
}
