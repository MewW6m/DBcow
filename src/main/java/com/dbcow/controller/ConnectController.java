package com.dbcow.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dbcow.model.Response;

@Controller
public class ConnectController {

    @GetMapping(value = "#{'${connect.sc.list}'}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ModelAndView list() {
        Map<String, String> breadcumbs = new LinkedHashMap<>();
        breadcumbs.put("接続情報一覧", "/connect");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("connect/list");
        modelAndView.addObject("title", "接続情報一覧");
        modelAndView.addObject("breadcumbs", breadcumbs);
        return modelAndView;
    }

    @GetMapping(value = "#{'${connect.sc.detail}'}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ModelAndView detail(@PathVariable("conname") String conname) {
        Map<String, String> breadcumbs = new LinkedHashMap<>();
        breadcumbs.put("接続情報一覧", "/connect");
        breadcumbs.put(conname == null ? " " : conname, String.join("/", "/connect", conname));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("connect/detail");
        modelAndView.addObject("title", conname);
        modelAndView.addObject("breadcumbs", breadcumbs);
        return modelAndView;
    }

    @GetMapping(value = "#{'${connect.api.list}'}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @ResponseBody
    public ResponseEntity<Response> getConnectList() {
        return new ResponseEntity<>(new Response(200, "GET /api/connect/list OK"), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "#{'${connect.api.detail}'}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @ResponseBody
    public ResponseEntity<Response> getConnectDetail() {
        return new ResponseEntity<>(new Response(200, "GET /api/connect/detail OK"), new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping(value = "#{'${connect.api.detail}'}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @ResponseBody
    public ResponseEntity<Response> postConnectDetail() {
        return new ResponseEntity<>(new Response(200, "POST /api/connect/detail OK"), new HttpHeaders(), HttpStatus.OK);
    }

    @PatchMapping(value = "#{'${connect.api.detail}'}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @ResponseBody
    public ResponseEntity<Response> patchConnectDetail() {
        return new ResponseEntity<>(new Response(200, "PATCH /api/connect/detail OK"), new HttpHeaders(),
                HttpStatus.OK);
    }

    @DeleteMapping(value = "#{'${connect.api.detail}'}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @ResponseBody
    public ResponseEntity<Response> deleteConnectDetail() {
        return new ResponseEntity<>(new Response(200, "DELETE /api/connect/detail OK"), new HttpHeaders(),
                HttpStatus.OK);
    }

}
