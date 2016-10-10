package com.academy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016-10-09.
 */
public class PlottingData {

    List<MapPoints> route = new ArrayList<>();

    public PlottingData(Reseplan reseplan, List<Butik> origin, List<Butik> destination) {

        route.add(new MapPoints("start",
                (new Point(reseplan.getOrigin().getCoordinateX(),reseplan.getOrigin().getCoordinateY()))));

        route.add(new MapPoints("destination",
                (new Point(reseplan.getDestination().getCoordinateX(),reseplan.getDestination().getCoordinateY()))));

        for (Butik store: destination) {
            route.add(new MapPoints(store.getAddress(), store.getPoint()));
        }

        for (Butik store: origin) {
            route.add(new MapPoints(store.getAddress(), store.getPoint()));
        }

    }


    public List<MapPoints> returnMap(){
        return route;
    }
}
