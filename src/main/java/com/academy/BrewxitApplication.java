package com.academy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BrewxitApplication {

	public static void main(String[] args) {
		reseplanerare_API reseplanerareAPI = new reseplanerare_API();
		Reseplan rp =  reseplanerareAPI.search("slussen", "liljeholmen");
		System.out.println(rp.origin.station);
		System.out.println(rp.destination.station);
		System.out.println("Done");
		
		
		SpringApplication.run(BrewxitApplication.class, args);
		
	}
}
