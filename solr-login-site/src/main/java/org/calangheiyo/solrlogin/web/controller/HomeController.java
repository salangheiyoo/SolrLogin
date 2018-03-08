package org.calangheiyo.solrlogin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by sukaixiang on 2017/11/7.
 */
@Controller
public class HomeController {
    @RequestMapping(value = {"/","/home"}, method = RequestMethod.GET)
    public String home(){
        return "home";
    }
}
