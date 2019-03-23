package com.dheeraj.learning.labwatcher.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {
    @RequestMapping(value = "/welcome2")
    public String loginMessage() {
        return "welcome";
    }
}
