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
public class CondController {

    @GetMapping(value = "#{'${cond.sc.list}'}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ModelAndView list() {
        Map<String, String> breadcumbs = new LinkedHashMap<>();
        breadcumbs.put("検索条件一覧", "/cond");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cond/list");
        modelAndView.addObject("title", "検索条件一覧");
        modelAndView.addObject("breadcumbs", breadcumbs);
        return modelAndView;
    }

    @GetMapping(value = "#{'${cond.sc.detail}'}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ModelAndView detail(@PathVariable("condname") String condname) {
        Map<String, String> breadcumbs = new LinkedHashMap<>();
        breadcumbs.put("検索条件一覧", "/cond");
        breadcumbs.put(condname == null ? " " : condname, String.join("/", "/cond", condname));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cond/detail");
        modelAndView.addObject("title", condname);
        modelAndView.addObject("breadcumbs", breadcumbs);
        return modelAndView;
    }

    @GetMapping(value = "#{'${cond.api.list}'}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @ResponseBody
    public ResponseEntity<Response> getCondList() {
        return new ResponseEntity<>(new Response(200, "GET /api/cond/list OK"), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "#{'${cond.api.detail}'}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @ResponseBody
    public ResponseEntity<Response> getCondDetail() {
        return new ResponseEntity<>(new Response(200, "GET /api/cond/detail OK"), new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping(value = "#{'${cond.api.detail}'}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @ResponseBody
    public ResponseEntity<Response> postCondDetail() {
        return new ResponseEntity<>(new Response(200, "POST /api/cond/detail OK"), new HttpHeaders(), HttpStatus.OK);
    }

    @PatchMapping(value = "#{'${cond.api.detail}'}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @ResponseBody
    public ResponseEntity<Response> patchCondDetail() {
        return new ResponseEntity<>(new Response(200, "PATCH /api/cond/detail OK"), new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping(value = "#{'${cond.api.detail}'}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @ResponseBody
    public ResponseEntity<Response> deleteCondDetail() {
        return new ResponseEntity<>(new Response(200, "DELETE /api/cond/detail OK"), new HttpHeaders(), HttpStatus.OK);
    }
}
