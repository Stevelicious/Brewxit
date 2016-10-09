package com.academy;

/**
 * Created by Administrator on 2016-10-09.
 */
public class MapPoints {
    private String name;
    private Point point;

    public MapPoints(String name, Point point) {
        this.name = name;
        this.point = point;
    }

    public String getName() {
        return name;
    }

    public Point getPoint() {
        return point;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
}
