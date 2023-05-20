package com.dbcow.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dbcow.model.Response;

@Controller
public class SettingController {
    @GetMapping(value = "/setting")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ModelAndView list() {
        Map<String, String> breadcumbs = new LinkedHashMap<>();
        breadcumbs.put("設定", "/setting");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("setting/detail");
        modelAndView.addObject("title", "設定");
        modelAndView.addObject("breadcumbs", breadcumbs);
        return modelAndView;
    }

    @GetMapping(value = "/api/setting/detail")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @ResponseBody
    public ResponseEntity<Response> getSettingDetail() {
        return new ResponseEntity<>(new Response(200, "GET /api/setting/detail OK"), new HttpHeaders(), HttpStatus.OK);
    }

    @PatchMapping(value = "/api/setting/detail")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @ResponseBody
    public ResponseEntity<Response> patchSettingDetail() {
        return new ResponseEntity<>(new Response(200, "PATCH /api/setting/detail OK"), new HttpHeaders(),
                HttpStatus.OK);
    }
}
