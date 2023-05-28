package com.dbcow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dbcow.util.ControllerUtil;

@Controller
public class LoginController {

    @Autowired
    ControllerUtil controllerUtil;

    @GetMapping("#{'${common.sc.root}'}")
    public ModelAndView root() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/login");;
        return modelAndView;
    }

    @GetMapping(value = "#{'${common.sc.login}'}")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        if (controllerUtil.isLogged()) {
            modelAndView.setViewName("redirect:/table");
        } else {
            modelAndView.setViewName("login/login");
            modelAndView.addObject("title", "ログイン");
        }
        return modelAndView;
    }
}
