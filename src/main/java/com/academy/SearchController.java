package com.academy;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by clockmice
 */

@Controller
public class SearchController {

    MapPointRepository mpr = new MapPointRepository();

    @GetMapping("/")
//b02399a9327052084960d14e1fe0f5427305f17d
    public ModelAndView search() {
        return new ModelAndView("Search")
                .addObject("");
    }

    @RequestMapping(path = "travel", method = RequestMethod.POST)
    public ModelAndView showResult(@RequestParam String a, String b) {

        Matching match = new Matching();

        List<Butik> originStores = match.origin(a, b);
        List<Butik> destinationStores = match.destination(a, b);
        Reseplan reseplan = match.reseplan(a, b);

        if ((originStores.size() == 0) && (destinationStores.size() == 0)) {
            return new ModelAndView("notfound");

        }

        mpr.setMaps(new PlottingData(reseplan, originStores, destinationStores).returnMap());

        return new ModelAndView("/results")
                .addObject("originStores", originStores)
                .addObject("destinationStores", destinationStores);


    }

    @CrossOrigin//(origins = "http://localhost:8080")
    @RequestMapping("/getDirections")
    public @ResponseBody List<MapPoints> places(){

        return mpr.getMaps();
    }

}
