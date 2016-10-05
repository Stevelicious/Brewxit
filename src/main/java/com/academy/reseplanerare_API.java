package com.academy;

import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * Created by Steven Hu on 2016-10-05.
 */
public class reseplanerare_API {
	public void getSite() {
		try {
			URL url = new URL("http://api.sl.se/api2/TravelplannerV2/trip.json?key=5f2e9c74703f4c7e98a239dfa7f15ad1&originId=9192&destId=1002&searchForArrival=0");
			ReadableByteChannel rbc = Channels.newChannel(url.openStream());
			FileOutputStream fos = new FileOutputStream("test.json");
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		}catch (Exception e){
			System.out.println("Something went wrong in API");
		}
		
	}
}
