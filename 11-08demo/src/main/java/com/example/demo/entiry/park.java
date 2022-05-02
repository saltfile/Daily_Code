package com.example.demo.entiry;
import lombok.Data;

@Data
public class park {
    private String park_id;

    private String place;

    private String isfree;

    private double charge;

    private double latitude;

    private double longitude;

    public park(){}
    public park(String park_id,String place
    ,String isfree,double charge,double latitude,double longitude){
        this.park_id = park_id;
        this.place = place;
        this.isfree = isfree;
        this.charge = charge;
        this.latitude = latitude;
        this.longitude = longitude;
    }



}
