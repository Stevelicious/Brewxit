package com.academy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Steven Hu on 2016-10-06.
 */
public class Reseplan {
	int duration;
	
	List<Trip> trips = new ArrayList<>();
	String journeyDetailRef;
	
	public Point getOrigin(){
		return new Point(trips.get(0).origin.lat, trips.get(0).origin.lon);

	}
	public Point getDestination(){
		return new Point(trips.get(trips.size()-1).origin.lat, trips.get(trips.size()-1).origin.lon);
	}
}

class Trip {
	Place origin = new Place();
	Place destination = new Place();
}

class Place {
	String name;
	String type;
	int id;
	double lon;
	double lat;
	int routeIdx;
	String time;
	String date;
	
	
}
