package org.calangheiyo.honey.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by sukaixiang on 2017/9/29.
 */
@Controller
public class HomeController {
    @RequestMapping(value = {"/","/home"}, method = RequestMethod.GET)
    public String home(){
        return "home/home";
    }

    @RequestMapping(value = "/app", method = RequestMethod.GET)
    public String app(){
        return "home/home";
    }
}
