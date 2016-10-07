package com.academy;

import java.time.LocalDateTime;

/**
 * Created by Steven Hu on 2016-10-06.
 */
public class Reseplan {
	int duration;
	Place origin = new Place();
	Place destination = new Place();
	String journeyDetailRef;
	
	public class Place {
		long Id;
		String station;
		double lon, lat;
		LocalDateTime dateTime;
	}
}
