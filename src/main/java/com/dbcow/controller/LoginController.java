package com.dbcow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	
	@GetMapping("/")
	public ModelAndView topScreen() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("common/top");
        return modelAndView;
	}

    @GetMapping(value = "/login")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login/login");
        return modelAndView;
    }
}
