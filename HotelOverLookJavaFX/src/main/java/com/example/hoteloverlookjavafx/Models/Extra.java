package com.example.hoteloverlookjavafx.Models;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "extra")
@XmlAccessorType(XmlAccessType.FIELD)
public class Extra {
    private float priceExtra;
    private String extras;

    public Extra(){

    }

    //basic constructor
    public Extra(float eB, String eS){
        this.priceExtra = eB;
        this.extras = eS;
    }

    public void changeExtras(float eB, String eS){
        this.priceExtra = eB;
        this.extras = eS;
    }


    public float getPriceExtra() {
        return priceExtra;
    }

    public void setPriceExtra(float priceExtra) {
        this.priceExtra = priceExtra;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }
}