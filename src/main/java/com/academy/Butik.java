package com.academy;

import com.github.goober.coordinatetransformation.positions.RT90Position;
import com.github.goober.coordinatetransformation.positions.WGS84Position;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by clockmice
 */

public class Butik implements Comparable{
    private String name;
    private String address;
    private String city;
    private Point point;
    private double distance;
    private String telefon;

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getDistance() {
        Integer i = (int) (distance*1000.0);
        return String.format("%d m bort", i);
    }

    public void setDistance(double distance) {
        BigDecimal bd = new BigDecimal(distance);
        bd = bd.setScale(1, RoundingMode.HALF_UP);
        String s = String.valueOf(bd.doubleValue()) + " km away.";
        this.distance = distance;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(double pointX, double pointY) {
        RT90Position rt90Position = new RT90Position(pointX, pointY);
        WGS84Position wgs84Position = rt90Position.toWGS84();
        Point point = new Point(wgs84Position.getLatitude(), wgs84Position.getLongitude());
        this.point = point;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    
    @Override
    public int compareTo(Object o) {
        Butik butik = (Butik)o;
        if (this.distance < butik.distance){
            return -1;
        }else if (this.distance > butik.distance){
            return 1;
        }else{
            return 0;
        }
        
    }
}
