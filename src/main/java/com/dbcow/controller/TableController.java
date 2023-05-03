package com.dbcow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TableController {
    @GetMapping(value = "/table/list")
    public ModelAndView tableList() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("table/tableList");
        return modelAndView;
    }
    @GetMapping(value = "/data/list")
    public ModelAndView dataList() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("table/dataList");
        return modelAndView;
    }
    @GetMapping(value = "/data/detail")
    public ModelAndView dataDetail() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("table/dataDetail");
        return modelAndView;
    }
    
}
