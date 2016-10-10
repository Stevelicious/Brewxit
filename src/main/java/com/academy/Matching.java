package com.academy;

import java.util.List;

/**
 * Created by Administrator on 2016-10-09.
 */
public class Matching {

    private reseplanerare_API reseplanerare_api = new reseplanerare_API();
    private Parser parser = new Parser();

    public Reseplan reseplan (String a, String b){
        Reseplan reseplan = reseplanerare_api.search(a, b);

        return reseplan;
    }

    public List<Butik> origin (String a, String b){
        List<Butik> originStores;
        originStores = parser.getButiks(reseplan(a, b).getOrigin());
        return originStores;
    }

    public List<Butik> destination (String a, String b){
        List<Butik> destinationStores;
        destinationStores = parser.getButiks(reseplan(a, b).getDestination());
        return destinationStores;
    }

}
