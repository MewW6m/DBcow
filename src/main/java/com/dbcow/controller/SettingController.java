package com.dbcow.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dbcow.model.Response;

@Controller
public class SettingController {
    @GetMapping(value = "/setting/detail")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("setting/detail");
        return modelAndView;
    }

    @GetMapping(value = "/api/setting/detail")
    @PreAuthorize("isAuthenticated()")
    @ResponseBody
    public ResponseEntity<Response> getSettingDetail() {
        return new ResponseEntity<>(new Response(200, "GET /api/setting/detail OK"), new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping(value = "/api/setting/detail")
    @PreAuthorize("isAuthenticated()")
    @ResponseBody
    public ResponseEntity<Response> putSettingDetail() {
        return new ResponseEntity<>(new Response(200, "PUT /api/setting/detail OK"), new HttpHeaders(), HttpStatus.OK);
    }
}
