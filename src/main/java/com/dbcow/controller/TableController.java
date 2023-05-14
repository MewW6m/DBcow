package com.dbcow.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dbcow.model.Response;

@Controller
public class TableController {
    
    @GetMapping(value = "/table/list")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView tableList() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("table/tableList");
        return modelAndView;
    }
    @GetMapping(value = "/data/list")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView dataList() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("table/dataList");
        return modelAndView;
    }
    @GetMapping(value = "/data/detail")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView dataDetail() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("table/dataDetail");
        return modelAndView;
    }

    @GetMapping(value = "/api/table/list")
    @PreAuthorize("isAuthenticated()")
    @ResponseBody
    public ResponseEntity<Response> getTableList() {
        return new ResponseEntity<>(new Response(200, "GET /api/table/list OK"), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/api/data/list")
    @PreAuthorize("isAuthenticated()")
    @ResponseBody
    public ResponseEntity<Response> getDataList() {
        return new ResponseEntity<>(new Response(200, "GET /api/data/list OK"), new HttpHeaders(), HttpStatus.OK);
    }
    
    @GetMapping(value = "/api/data/detail")
    @PreAuthorize("isAuthenticated()")
    @ResponseBody
    public ResponseEntity<Response> getDataDetail() {
        return new ResponseEntity<>(new Response(200, "GET /api/data/detail OK"), new HttpHeaders(), HttpStatus.OK);
    }

    @PatchMapping(value = "/api/data/detail")
    @PreAuthorize("isAuthenticated()")
    @ResponseBody
    public ResponseEntity<Response> patchDataDetail() {
        return new ResponseEntity<>(new Response(200, "PATCH /api/data/detail OK"), new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/data/detail")
    @PreAuthorize("isAuthenticated()")
    @ResponseBody
    public ResponseEntity<Response> deleteDataDetail() {
        return new ResponseEntity<>(new Response(200, "DELETE /api/data/detail OK"), new HttpHeaders(), HttpStatus.OK);
    }
}
