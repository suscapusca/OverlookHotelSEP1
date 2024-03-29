package com.example.hoteloverlookjavafx.Models;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;

@XmlRootElement(name = "bookings")
@XmlAccessorType(XmlAccessType.FIELD)
public class BookingList {
    @XmlElement(name = "booking")
    public ArrayList<Booking> bookings;

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
