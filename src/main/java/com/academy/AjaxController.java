package com.academy;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016-10-09.
 */
@RestController
public class AjaxController {

    @CrossOrigin//(origins = "http://localhost:8080")
    @RequestMapping("/getDirections")
    public @ResponseBody List<MapPoints> places(){
        List<MapPoints> stuff = new ArrayList<>();
        stuff.add(new MapPoints("Kista",new Point(59.310711, 18.022928)));
        stuff.add(new MapPoints("Liljeholmen", new Point(59.409644, 17.961620)));
        return stuff;
    }

}
