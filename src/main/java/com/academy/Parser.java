package com.academy;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by clockmice
 */

public class Parser {
    // write your code here
    List<Butik> systembolaget = new ArrayList<>();

    public Parser() {

        try {

            //File fXmlFile = new File("C:/Users/vasil/Desktop/bolaget.xml");

            URL url = new URL("http://www.systembolaget.se/api/assortment/stores/xml");
            URLConnection conn = url.openConnection();

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(conn.getInputStream());

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

//            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("ButikOmbud");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                //System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    if (eElement.getElementsByTagName("Address5").item(0).getTextContent().equals("Stockholms län")) {
                        Butik butik = new Butik();
                        butik.setName(eElement.getElementsByTagName("Namn").item(0).getTextContent());
                        butik.setCity(eElement.getElementsByTagName("Address4").item(0).getTextContent().toLowerCase());
                        butik.setAddress(eElement.getElementsByTagName("Address1").item(0).getTextContent());
                        butik.setTelefon(eElement.getElementsByTagName("Telefon").item(0).getTextContent());
                        butik.setPoint(Double.parseDouble(eElement.getElementsByTagName("RT90x").item(0).getTextContent()),
                                Double.parseDouble(eElement.getElementsByTagName("RT90y").item(0).getTextContent()));
                        systembolaget.add(butik);
                    }
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void printButiks() {
        Iterator<Butik> butikIterator = systembolaget.iterator();
        while (butikIterator.hasNext()) {
            Butik butik = butikIterator.next();
            System.out.println(butik.getCity());
            System.out.println(butik.getAddress());
        }
    }

    public List<Butik> getButiks(Point point) {
        List<Butik> butiks = new ArrayList<>();
        Iterator<Butik> butikIterator = systembolaget.iterator();
        while (butikIterator.hasNext()) {
            
            Butik butik = butikIterator.next();
            double distance = distanceBetween(point, butik);
            if (distance <= 2.0) {
                butik.setDistance(distance);
                butiks.add(butik);
            }
        }
        Collections.sort(butiks);
        return butiks;
    }

    private double distanceBetween (Point point, Butik butik) {
        Location location = new Location(point.getCoordinateX(), point.getCoordinateY());
        Location bolaget = new Location(butik.getPoint().getCoordinateX(),
                butik.getPoint().getCoordinateY());
        return bolaget.distanceTo(location);
    }

}
