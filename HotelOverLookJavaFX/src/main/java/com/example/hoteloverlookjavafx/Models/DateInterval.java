package com.example.hoteloverlookjavafx.Models;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "dateInterval")
@XmlAccessorType(XmlAccessType.FIELD)
public class DateInterval {
    @XmlElement(name = "arriveDate")
    private Date startDate;
    @XmlElement(name = "departureDate")
    private Date endDate;

    public DateInterval(){

    }
    //constructor if you know the departure date
    public DateInterval(Date sD, Date eD ){
        this.startDate = sD.copy();
        this.endDate = eD.copy();
    }

    //constructor if you just know the arriving date
    public DateInterval(Date sD){
        this.startDate = sD.copy();
    }

    public void setDates(Date sD, Date eD){
        this.startDate = sD.copy();
        this.startDate = eD.copy();
    }

    public void clearDepartureDate(){
        this.endDate = null;
    }

    //GET AND SETTERS
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
