package com.dbcow.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.dbcow.util.ControllerUtil;

@Controller
public class UserController {

    @Autowired ControllerUtil controllerUtil;

    @GetMapping(value = "/user/regist")
    public ModelAndView getRegist() {
        ModelAndView modelAndView = new ModelAndView();
        if (controllerUtil.isLogged())
            modelAndView.setViewName("redirect:/table/list");
        else
            modelAndView.setViewName("user/regist");
        return modelAndView;
    }

    @GetMapping(value = "/user/list")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/list");
        return modelAndView;
    }

    @GetMapping(value = "/user/detail")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView detail() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/detail");
        return modelAndView;
    }

    @GetMapping(value = "/api/user/list")
    @PreAuthorize("isAuthenticated()")
    @ResponseBody
    public ResponseEntity<Response> getUserList() {
        return new ResponseEntity<>(new Response(200, "GET /api/user/list OK"), new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/api/user/detail")
    @PreAuthorize("isAuthenticated()")
    @ResponseBody
    public ResponseEntity<Response> getUserDetail() {
        return new ResponseEntity<>(new Response(200, "GET /api/user/detail OK"), new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping(value = "/api/user/detail")
    @ResponseBody
    public ResponseEntity<Response> postUserDetail() {
        return new ResponseEntity<>(new Response(200, "POST /api/user/detail OK"), new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping(value = "/api/user/detail")
    @PreAuthorize("isAuthenticated()")
    @ResponseBody
    public ResponseEntity<Response> putUserDetail() {
        return new ResponseEntity<>(new Response(200, "PUT /api/user/detail OK"), new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/user/detail")
    @PreAuthorize("isAuthenticated()")
    @ResponseBody
    public ResponseEntity<Response> deleteUserDetail() {
        return new ResponseEntity<>(new Response(200, "DELETE /api/user/detail OK"), new HttpHeaders(), HttpStatus.OK);
    }
}
