package com.academy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016-10-09.
 */
public class MapPointRepository {
    private List<MapPoints> maps = new ArrayList<>();

    public List<MapPoints> getMaps() {
        return maps;
    }

    public void setMaps(List<MapPoints> maps) {
        this.maps = maps;
    }
}
