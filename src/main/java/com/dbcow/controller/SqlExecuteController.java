package com.dbcow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SqlExecuteController {
    @GetMapping(value = "/sqlexecute")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("sqlexecute/sqlexecute");
        return modelAndView;
    }
    
}
