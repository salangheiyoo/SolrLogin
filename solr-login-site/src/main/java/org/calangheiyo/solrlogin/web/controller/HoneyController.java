package org.calangheiyo.honey.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by sukaixiang on 2017/9/29.
 */
@Controller
public class HoneyController {

    @RequestMapping(value = "/honey", method = RequestMethod.GET)
    public String honey(){
        return "honey/honey";
    }
}
