package com.dbcow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConnectController {
    @GetMapping(value = "/connect/list")
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("connect/list");
        return modelAndView;
    }
    @GetMapping(value = "/connect/detail")
    public ModelAndView detail() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("connect/detail");
        return modelAndView;
    }
    
}
