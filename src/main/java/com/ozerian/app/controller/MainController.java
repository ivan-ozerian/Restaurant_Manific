package com.ozerian.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcomePage() {
        return "index";
    }

    @RequestMapping(value = "/greetingPage", method = RequestMethod.GET)
    public String greetingPage() {
        return "greetingPage";
    }
}
