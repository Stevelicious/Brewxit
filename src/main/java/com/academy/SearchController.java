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


    private List<Butik> originStores;
    private List<Butik> destinationStores;

    public reseplanerare_API reseplanerare_api = new reseplanerare_API();

    @GetMapping("/")
//b02399a9327052084960d14e1fe0f5427305f17d
    public ModelAndView search() {
        return new ModelAndView("Search")
                .addObject("");
    }

    @RequestMapping(path="travel", method= RequestMethod.POST)
    public ModelAndView showResult(@RequestParam String a, String b) {
        Reseplan reseplan = reseplanerare_api.search(a, b);
        Parser parser = new Parser();
        originStores = parser.getButiks(reseplan.getOrigin());
        destinationStores = parser.getButiks(reseplan.getDestination());
//        parser.printButiks();
        if((originStores.size() == 0) && (destinationStores.size() == 0)) {
            return new ModelAndView("notfound");
        }
        return new ModelAndView("/results")
                .addObject("originStores", originStores)
                .addObject("destinationStores", destinationStores);
    }



}
