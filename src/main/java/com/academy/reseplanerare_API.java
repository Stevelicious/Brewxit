package com.academy;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/*
 * Created by Steven Hu on 2016-10-05.
 */
public class reseplanerare_API {

    public Reseplan search(String origin, String dest) {
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

        } catch (Exception e) {
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
                            origin, destination
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
        System.out.println(json.toString());
        JsonObject tripList = json.getAsJsonObject("TripList");
        JsonObject trip = tripList.getAsJsonObject("Trip");
        rp.duration = trip.getAsJsonPrimitive("dur").getAsInt();


        JsonObject legList = trip.getAsJsonObject("LegList");


        int j = 0;
        Trip legTrip;
        JsonArray legs = new JsonArray();
	    
	    if(legList.get("Leg").isJsonArray()){
		    legs.addAll(legList.getAsJsonArray("Leg"));
	    }else{
		    legs.add(legList.getAsJsonObject("Leg"));
	    }

        for (int i = 0; i < legs.size(); i++) {
            JsonObject leg = legs.get(i).getAsJsonObject();

            if (leg.getAsJsonPrimitive("hide") == null) {
                JsonObject journeyDetailRef = leg.getAsJsonObject("JourneyDetailRef");
//                rp.journeyDetailRef = journeyDetailRef.getAsJsonPrimitive("ref").getAsString();

                legTrip = new Trip();
                legTrip.origin = gson.fromJson(leg.get("Origin"), Place.class);
                legTrip.destination = gson.fromJson(leg.get("Destination"), Place.class);
                rp.trips.add(legTrip);

            }

        }
        return rp;

    }

    private Station parseStation(String s) {
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
