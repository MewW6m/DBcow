package com.dbcow.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dbcow.model.Response;

@Controller
public class ConnectController {

    @GetMapping(value = "/connect/list")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("connect/list");
        return modelAndView;
    }

    @GetMapping(value = "/connect/detail")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ModelAndView detail() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("connect/detail");
        return modelAndView;
    }

    @GetMapping(value = "/api/connect/list")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @ResponseBody
    public ResponseEntity<Response> getConnectList() {
        return new ResponseEntity<>(new Response(200, "GET /api/connect/list OK"), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/api/connect/detail")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @ResponseBody
    public ResponseEntity<Response> getConnectDetail() {
        return new ResponseEntity<>(new Response(200, "GET /api/connect/detail OK"), new HttpHeaders(), HttpStatus.OK);
    }
    
    @PostMapping(value = "/api/connect/detail")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @ResponseBody
    public ResponseEntity<Response> postConnectDetail() {
        return new ResponseEntity<>(new Response(200, "POST /api/connect/detail OK"), new HttpHeaders(), HttpStatus.OK);
    }

    @PatchMapping(value = "/api/connect/detail")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @ResponseBody
    public ResponseEntity<Response> patchConnectDetail() {
        return new ResponseEntity<>(new Response(200, "PATCH /api/connect/detail OK"), new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/connect/detail")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @ResponseBody
    public ResponseEntity<Response> deleteConnectDetail() {
        return new ResponseEntity<>(new Response(200, "DELETE /api/connect/detail OK"), new HttpHeaders(), HttpStatus.OK);
    }
    
}
