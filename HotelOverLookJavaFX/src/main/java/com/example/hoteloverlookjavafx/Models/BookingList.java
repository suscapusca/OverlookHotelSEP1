package com.example.hoteloverlookjavafx.Models;

import java.util.ArrayList;

public class BookingList {
    private ArrayList<Booking> bookings;

    public BookingList(){
        bookings = new ArrayList<>();
    }

    public void add(Booking booking){
        bookings.add(booking);
    }

    public void set(Booking booking, int index){
        bookings.set(index, booking);
    }

    public Booking getBooking(int index){
        return bookings.get(index);
    }

    public ArrayList<Booking> getAllBookings(){
        return bookings;
    }

    public int size(){
        return bookings.size();
    }
}
