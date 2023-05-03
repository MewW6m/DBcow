package com.dbcow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @GetMapping(value = "/user/regist")
    public ModelAndView regist() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/regist");
        return modelAndView;
    }

    @GetMapping(value = "/user/list")
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/list");
        return modelAndView;
    }
    @GetMapping(value = "/user/detail")
    public ModelAndView detail() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/detail");
        return modelAndView;
    }
}
