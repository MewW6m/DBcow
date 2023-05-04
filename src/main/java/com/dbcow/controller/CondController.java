package com.dbcow.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dbcow.model.Response;

@Controller
public class CondController {

    @GetMapping(value = "/cond/list")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cond/list");
        return modelAndView;
    }

    @GetMapping(value = "/cond/detail")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView detail() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cond/detail");
        return modelAndView;
    }

    @GetMapping(value = "/api/cond/list")
    @PreAuthorize("isAuthenticated()")
    @ResponseBody
    public ResponseEntity<Response> getCondList() {
        return new ResponseEntity<>(new Response(200, "GET /api/cond/list OK"), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/api/cond/detail")
    @PreAuthorize("isAuthenticated()")
    @ResponseBody
    public ResponseEntity<Response> getCondDetail() {
        return new ResponseEntity<>(new Response(200, "GET /api/cond/detail OK"), new HttpHeaders(), HttpStatus.OK);
    }
    
    @PostMapping(value = "/api/cond/detail")
    @PreAuthorize("isAuthenticated()")
    @ResponseBody
    public ResponseEntity<Response> postCondDetail() {
        return new ResponseEntity<>(new Response(200, "POST /api/cond/detail OK"), new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping(value = "/api/cond/detail")
    @PreAuthorize("isAuthenticated()")
    @ResponseBody
    public ResponseEntity<Response> putCondDetail() {
        return new ResponseEntity<>(new Response(200, "PUT /api/cond/detail OK"), new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/cond/detail")
    @PreAuthorize("isAuthenticated()")
    @ResponseBody
    public ResponseEntity<Response> deleteCondDetail() {
        return new ResponseEntity<>(new Response(200, "DELETE /api/cond/detail OK"), new HttpHeaders(), HttpStatus.OK);
    }
}
