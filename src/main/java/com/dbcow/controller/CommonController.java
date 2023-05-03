package com.dbcow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController {
    
    @GetMapping(value = "/login")
    public String index() { //←１６
        return "common/login";
    } 
}