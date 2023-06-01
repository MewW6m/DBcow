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

    /**
     * 設定画面
     * 
     * @return 画面
     */
    @GetMapping(value = "#{'${setting.sc.detail}'}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ModelAndView settingDetail() {
        Map<String, String> breadcumbs = new LinkedHashMap<>();
        breadcumbs.put("設定", "/setting");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("setting/detail");
        modelAndView.addObject("title", "設定");
        modelAndView.addObject("breadcumbs", breadcumbs);
        return modelAndView;
    }

    /**
     * 設定情報詳細取得API
     * 
     * @return レスポンス
     */
    @GetMapping(value = "#{'${setting.api.detail}'}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @ResponseBody
    public ResponseEntity<Response> getSettingDetail() {
        return new ResponseEntity<>(new Response(200, "GET /api/setting/detail OK"), new HttpHeaders(), HttpStatus.OK);
    }


    /**
     * 設定情報詳細更新API
     * 
     * @return レスポンス
     */
    @PatchMapping(value = "#{'${setting.api.detail}'}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @ResponseBody
    public ResponseEntity<Response> patchSettingDetail() {
        return new ResponseEntity<>(new Response(200, "PATCH /api/setting/detail OK"), new HttpHeaders(),
                HttpStatus.OK);
    }
}
