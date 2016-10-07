package com.academy;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.*;
import java.net.URL;
import java.time.LocalDateTime;

/**
 * Created by Steven Hu on 2016-10-05.
 */

public class reseplanerare_API {
	
	public Reseplan search(String origin, String dest){
		try {
			URL url = new URL(
					String.format("http://api.sl.se/api2/typeahead.json?key=54f8669daa794e5fb749396bd0763e82&searchstring=%s&stationsonly=true&maxresults=1", origin)
			);
			String json = getJSONString(url);
			Station start = parseStation(json);
			
			url = new URL(
					String.format("http://api.sl.se/api2/typeahead.json?key=54f8669daa794e5fb749396bd0763e82&searchstring=%s&stationsonly=true&maxresults=1", dest)
			);
			json = getJSONString(url);
			Station destination = parseStation(json);
			
			return fetchTravelPlan(start.siteId, destination.siteId);
			
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("Something went wrong in platsuppslag API");
			return null;
		}
		
	}
	
	
	private Reseplan fetchTravelPlan(int origin, int destination) {
		try {
			URL url = new URL(
					String.format("http://api.sl.se/api2/TravelplannerV2/trip.json" +
							"?key=5f2e9c74703f4c7e98a239dfa7f15ad1" +
							"&originId=%d" +
							"&destId=%d" +
							"&searchForArrival=0" +
							"&numTrips=1",
							origin,destination
					)
			);
			
			String json = getJSONString(url);
			
			return parseReseplan(json);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Something went wrong in reseplanerare API");
			return null;
		}
		
	}
	
	private String getJSONString(URL url) throws IOException {
		BufferedReader in = new BufferedReader(
				new InputStreamReader(url.openStream()));
		
		String inputLine;
		StringBuilder sb = new StringBuilder();
		
		while ((inputLine = in.readLine()) != null) {
			sb.append(inputLine);
		}
		in.close();
		return sb.toString();
	}
	
	private Reseplan parseReseplan(String s) {
		Gson gson = new Gson();
		
		Reseplan rp = new Reseplan();
		
		// Convert JSON to Java Object
		JsonObject json = gson.fromJson(s, JsonObject.class);
		JsonObject tripList = json.getAsJsonObject("TripList");
		JsonObject trip = tripList.getAsJsonObject("Trip");
		rp.duration = trip.getAsJsonPrimitive("dur").getAsInt();
		
		
		JsonObject legList = trip.getAsJsonObject("LegList");
		JsonObject leg = legList.getAsJsonObject("Leg");
		JsonObject journeyDetailRef = leg.getAsJsonObject("JourneyDetailRef");
		rp.journeyDetailRef = journeyDetailRef.getAsJsonPrimitive("ref").getAsString();
		
		//get origin info
		JsonObject origin = leg.getAsJsonObject("Origin");
		rp.origin.Id = origin.getAsJsonPrimitive("routeIdx").getAsInt();
		rp.origin.station = origin.getAsJsonPrimitive("name").getAsString();
		rp.origin.lon = origin.getAsJsonPrimitive("lon").getAsDouble();
		rp.origin.lat = origin.getAsJsonPrimitive("lat").getAsDouble();
		String date = origin.getAsJsonPrimitive("date").getAsString() + "T" + origin.getAsJsonPrimitive("time").getAsString();
		rp.origin.dateTime = LocalDateTime.parse(date);
		
		//get destination info
		JsonObject destination = leg.getAsJsonObject("Destination");
		rp.destination.Id = destination.getAsJsonPrimitive("routeIdx").getAsInt();
		rp.destination.station = destination.getAsJsonPrimitive("name").getAsString();
		rp.destination.lon = destination.getAsJsonPrimitive("lon").getAsDouble();
		rp.destination.lat = destination.getAsJsonPrimitive("lat").getAsDouble();
		date = destination.getAsJsonPrimitive("date").getAsString() + "T" + destination.getAsJsonPrimitive("time").getAsString();
		rp.destination.dateTime = LocalDateTime.parse(date);
		
		return rp;
		
	}
	
	private Station parseStation(String s){
		Gson gson = new Gson();
		Station station = new Station();
		
		JsonObject json = gson.fromJson(s, JsonObject.class);
		JsonElement responseData = json.getAsJsonArray("ResponseData").get(0);
		JsonObject rd = responseData.getAsJsonObject();
		station.name = rd.getAsJsonPrimitive("Name").getAsString();
		station.siteId = rd.getAsJsonPrimitive("SiteId").getAsInt();
		
		return station;
		
	}
}
