/*
package com.academy;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

*/
/**
 * Created by clockmice
 *//*


@Controller
public class ParseController {

    private List<Butik> originStores;
    private List<Butik> destinationStores;

    public reseplanerare_API reseplanerare_api = new reseplanerare_API();

    @GetMapping("/testsearch")
    public ModelAndView search() {
        return new ModelAndView("results")
                .addObject("");
    }

    @PostMapping("/testresult")
    public ModelAndView showResult(@RequestParam String a, String b) {
        Reseplan reseplan = reseplanerare_api.search(a, b);
        Parser parser = new Parser();
        originStores = parser.getButiks(reseplan.getOrigin());
        destinationStores = parser.getButiks(reseplan.getDestination());
//        parser.printButiks();
        if((originStores.size() == 0) && (destinationStores.size() == 0)) {
            return new ModelAndView("notfound");
        }
        return new ModelAndView("testresult")
                .addObject("originStores", originStores)
                .addObject("destinationStores", destinationStores);
    }


}
*/
