package com.example.hoteloverlookjavafx.Models;

public class Date {
    private int day;
    private int month;
    private int year;

    public Date(){

    }

    //constructor
    public Date(int d, int m, int y){
        this.day = d;
        this.month = m;
        this.year = y;
    }

    public Date copy(){
        return new Date(day,month,year);
    }
    //calling object will return toString
    @Override
    public String toString() {
        return day+"/"+month+"/"+year;
    }

    //function to change the date
    public void changeDate(int d, int m, int y){
        this.day = d;
        this.month = m;
        this.year = y;
    }


    //GET AND SETTERS
    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
