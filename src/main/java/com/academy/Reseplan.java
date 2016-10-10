package com.academy;

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
		return new Point(trips.get(trips.size()-1).destination.lat, trips.get(trips.size()-1).destination.lon);
	}

	public String getOriginName(){
		return trips.get(0).origin.name;
	}

	public String getDestinationName(){
		return trips.get(trips.size()-1).destination.name;
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
