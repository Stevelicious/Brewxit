package com.academy;

import java.util.List;

/**
 * Created by Administrator on 2016-10-09.
 */
public class Matching {

    private reseplanerare_API reseplanerare_api = new reseplanerare_API();
    private Systemet_API systemetAPI = new Systemet_API();

    public Reseplan reseplan (String a, String b){
        Reseplan reseplan = reseplanerare_api.search(a, b);
    
        return reseplan;
    }

    public List<Butik> origin (Reseplan reseplan){
        List<Butik> originStores;
        
        originStores = systemetAPI.getButiks(reseplan.getOrigin());
        return originStores;
    }

    public List<Butik> destination (Reseplan reseplan){
        List<Butik> destinationStores;
        destinationStores = systemetAPI.getButiks(reseplan.getDestination());
        return destinationStores;
    }

}
