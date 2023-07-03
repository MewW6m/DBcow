package com.dbcow.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Triple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dbcow.config.ViewGroup;
import com.dbcow.model.Connect;
import com.dbcow.model.GetConnectListReqParam;
import com.dbcow.model.Response;
import com.dbcow.service.ConnectService;
import com.dbcow.util.ControllerUtil;

import jakarta.validation.constraints.NotBlank;

@Controller
public class ConnectController {

    @Autowired ConnectService connectService;
    @Autowired
    ControllerUtil controllerUtil;

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
        modelAndView.setViewName("connect/connectList");
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
        if (StringUtils.equals(conname, "regist"))
            breadcumbs.put("接続情報登録", "/connect/regist");
        else 
            breadcumbs.put(conname == null ? " " : conname, String.join("/", "/connect", conname));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("connect/connectDetail");
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
    public ResponseEntity<Response> getConnectList(@ModelAttribute @Validated GetConnectListReqParam req) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Triple<String, String, String>> searchParam = controllerUtil.castListForSearchParam(
            req.getSearchItem1(), req.getSearchType1(), req.getSearchValue1(), 
            req.getSearchItem2(), req.getSearchType2(), req.getSearchValue2(), 
            req.getSearchItem3(), req.getSearchType3(), req.getSearchValue3(), 
            req.getSearchItem4(), req.getSearchType4(), req.getSearchValue4(), 
            req.getSearchItem5(), req.getSearchType5(), req.getSearchValue5());
        searchParam.add(Triple.of("username", "EQ", username));
        List<Connect> connectList = connectService.getConnectListBySearch(searchParam, 
            req.getSortItem(), req.getSortDirc(), req.getPageLimit(), req.getPageOffset());
        return new ResponseEntity<>(new Response(200, connectList), 
            new HttpHeaders(), HttpStatus.OK);
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
    public ResponseEntity<Response> getConnectDetail(@NotBlank @PathVariable("conname") String conname) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Connect connect = connectService.getConnect(conname, username);
        return new ResponseEntity<>(new Response(200, connect), new HttpHeaders(), HttpStatus.OK);
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
    public ResponseEntity<Response> postConnectDetail(@RequestBody @Validated(ViewGroup.PostConnect.class) Connect connect) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        connectService.registConnect(connect, username);
        return new ResponseEntity<>(new Response(200, ""), new HttpHeaders(), HttpStatus.OK);
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
    public ResponseEntity<Response> patchConnectDetail(@RequestBody @Validated(ViewGroup.PatchConnect.class) Connect connect) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        connectService.updateConnect(connect, username);
        return new ResponseEntity<>(new Response(200, ""), new HttpHeaders(), HttpStatus.OK);
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
    public ResponseEntity<Response> deleteConnectDetail(@NotBlank @PathVariable("conname") String conname) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        connectService.deleteConnect(conname, username);
        return new ResponseEntity<>(new Response(200, ""), new HttpHeaders(), HttpStatus.OK);
    }

}
