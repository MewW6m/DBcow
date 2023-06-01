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

    /**
     * 接続情報一覧画面
     * 
     * @return 画面
     */
    @GetMapping(value = "#{'${connect.sc.list}'}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ModelAndView connectList() {
        Map<String, String> breadcumbs = new LinkedHashMap<>();
        breadcumbs.put("接続情報一覧", "/connect");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("connect/list");
        modelAndView.addObject("title", "接続情報一覧");
        modelAndView.addObject("breadcumbs", breadcumbs);
        return modelAndView;
    }

    /**
     * 接続情報詳細画面
     * 
     * @param conname 接続情報名
     * @return 画面
     */
    @GetMapping(value = "#{'${connect.sc.detail}'}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ModelAndView connectDetail(@PathVariable("conname") String conname) {
        Map<String, String> breadcumbs = new LinkedHashMap<>();
        breadcumbs.put("接続情報一覧", "/connect");
        breadcumbs.put(conname == null ? " " : conname, String.join("/", "/connect", conname));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("connect/detail");
        modelAndView.addObject("title", conname);
        modelAndView.addObject("breadcumbs", breadcumbs);
        return modelAndView;
    }

    /**
     * 接続情報一覧取得API
     * 
     * @return レスポンス
     */
    @GetMapping(value = "#{'${connect.api.list}'}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @ResponseBody
    public ResponseEntity<Response> getConnectList() {
        return new ResponseEntity<>(new Response(200, "GET /api/connect/list OK"), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * 接続情報詳細取得API
     * 
     * @param conname 接続情報名
     * @return レスポンス
     */
    @GetMapping(value = "#{'${connect.api.detail}'}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @ResponseBody
    public ResponseEntity<Response> getConnectDetail(@PathVariable("conname") String conname) {
        return new ResponseEntity<>(new Response(200, "GET /api/connect/detail OK"), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * 接続情報詳細登録API
     * 
     * @param conname 接続情報名
     * @return レスポンス
     */
    @PostMapping(value = "#{'${connect.api.detail}'}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @ResponseBody
    public ResponseEntity<Response> postConnectDetail(@PathVariable("conname") String conname) {
        return new ResponseEntity<>(new Response(200, "POST /api/connect/detail OK"), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * 接続情報詳細更新API
     * 
     * @param conname 接続情報名
     * @return レスポンス
     */
    @PatchMapping(value = "#{'${connect.api.detail}'}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @ResponseBody
    public ResponseEntity<Response> patchConnectDetail(@PathVariable("conname") String conname) {
        return new ResponseEntity<>(new Response(200, "PATCH /api/connect/detail OK"), new HttpHeaders(),
                HttpStatus.OK);
    }

    /**
     * 接続情報詳細削除API
     * 
     * @param conname 接続情報名
     * @return レスポンス
     */
    @DeleteMapping(value = "#{'${connect.api.detail}'}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @ResponseBody
    public ResponseEntity<Response> deleteConnectDetail(@PathVariable("conname") String conname) {
        return new ResponseEntity<>(new Response(200, "DELETE /api/connect/detail OK"), new HttpHeaders(),
                HttpStatus.OK);
    }

}
