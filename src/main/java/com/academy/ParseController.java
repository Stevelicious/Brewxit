package com.academy;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by clockmice
 */

@Controller
public class ParseController {

    private List<Butik> searchResult;

    @GetMapping("/")
    public ModelAndView search() {
        return new ModelAndView("searchcoord")
                .addObject("");
    }

    @PostMapping("/result")
    public ModelAndView showResult(@RequestParam String keywordx, String keywordy) {
        Parser parser = new Parser();
        searchResult = parser.getButiks(keywordx, keywordy);
//        parser.printButiks();
        if(searchResult.size() == 0) {
            return new ModelAndView("notfound");
        }
        return new ModelAndView("result")
                .addObject("searchResult", searchResult);
    }


}
