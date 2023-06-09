package com.dbcow.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dbcow.model.Response;

@Controller
public class SqlExecuteController {

    /**
     * SQL実行画面
     * 
     * @return 画面
     */
    @GetMapping(value = "#{'${sqlexecute.sc.detail}'}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ModelAndView sqlexecute() {
        Map<String, String> breadcumbs = new LinkedHashMap<>();
        breadcumbs.put("SQL実行", "/sqlexecute");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("sqlexecute/sqlexecute");
        modelAndView.addObject("title", "SQL実行");
        modelAndView.addObject("breadcumbs", breadcumbs);
        return modelAndView;
    }

    /**
     * SQL実行API
     * 
     * @return レスポンス
     */
    @PostMapping(value = "#{'${sqlexecute.api.detail}'}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @ResponseBody
    public ResponseEntity<Response> postSqlExecute() {
        return new ResponseEntity<>(new Response(200, "POST /api/sqlexecute OK"), new HttpHeaders(), HttpStatus.OK);
    }

}
