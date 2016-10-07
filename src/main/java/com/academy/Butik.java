package com.academy;

import com.github.goober.coordinatetransformation.positions.RT90Position;
import com.github.goober.coordinatetransformation.positions.WGS84Position;

import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by clockmice
 */

public class Butik {
    private String name;
    private String address;
    private String city;
    private Point point;
    private String distance;

    public String getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        BigDecimal bd = new BigDecimal(distance);
        bd = bd.setScale(1, RoundingMode.HALF_UP);
        String s = String.valueOf(bd.doubleValue()) + " km away.";
        this.distance = s;
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

}
