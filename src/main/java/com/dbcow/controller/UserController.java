package com.dbcow.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dbcow.config.CustomErrorException;
import com.dbcow.config.ViewGroup;
import com.dbcow.model.CustomUserDetails;
import com.dbcow.model.Response;
import com.dbcow.service.UserService;
import com.dbcow.util.ControllerUtil;

import jakarta.validation.constraints.NotBlank;
import jakarta.websocket.server.PathParam;

/**
 * ユーザー情報関連のコントローラークラス
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    ControllerUtil controllerUtil;

    /**
     * ユーザー新規登録画面
     * 
     * @return 画面
     */
    @GetMapping(value = "#{'${user.sc.regist}'}")
    public ModelAndView userDetailRegist() {
        ModelAndView modelAndView = new ModelAndView();
        if (controllerUtil.isLogged()) {
            modelAndView.setViewName("redirect:/table");
        } else {
            modelAndView.setViewName("user/userRegist");
            modelAndView.addObject("title", "新規登録");
        }
        return modelAndView;
    }

    /**
     * ユーザー情報一覧画面
     * 
     * @return 画面
     */
    @GetMapping(value = "#{'${user.sc.list}'}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ModelAndView userList() {
        Map<String, String> breadcumbs = new LinkedHashMap<>();
        breadcumbs.put("ユーザー一覧", "/user");

        Map<String, String> listElms = new LinkedHashMap<>();
        listElms.put("username", "ユーザー名");
        listElms.put("roles", "役割");
        listElms.put("deleteFlag", "ユーザー有効可否");
        listElms.put("createDate", "ユーザー作成日時");
        listElms.put("updateDate", "ユーザー更新日時");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/userList");
        modelAndView.addObject("title", "ユーザー一覧");
        modelAndView.addObject("breadcumbs", breadcumbs);
        modelAndView.addObject("listElms", listElms);
        modelAndView.addObject("listElmsKey", "username");
        return modelAndView;
    }

    /**
     * ユーザー情報詳細画面
     * 
     * @param username ユーザー名
     * @return 画面
     */
    @GetMapping(value = "#{'${user.sc.detail}'}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ModelAndView userDetail(@PathVariable("username") String username) {
        Map<String, String> breadcumbs = new LinkedHashMap<>();
        breadcumbs.put("ユーザー一覧", "/user");
        breadcumbs.put(username == null ? " " : username, String.join("/", "/user", username));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/userDetail");
        modelAndView.addObject("title", username);
        modelAndView.addObject("breadcumbs", breadcumbs);
        return modelAndView;
    }

    /**
     * ユーザー情報一覧取得API
     * 
     * @return レスポンス
     * @throws CustomErrorException
     */
    @GetMapping(value = "#{'${user.api.list}'}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ResponseBody
    public ResponseEntity<Response> getUserList(
        @RequestParam(value = "searchItem1", required = false) String searchItem1,
        @RequestParam(value = "searchType1", required = false) String searchType1,
        @RequestParam(value = "searchValue1", required = false) String searchValue1,
        @RequestParam(value = "searchItem2", required = false) String searchItem2,
        @RequestParam(value = "searchType2", required = false) String searchType2,
        @RequestParam(value = "searchValue2", required = false) String searchValue2,
        @RequestParam(value = "searchItem3", required = false) String searchItem3,
        @RequestParam(value = "searchType3", required = false) String searchType3,
        @RequestParam(value = "searchValue3", required = false) String searchValue3,
        @RequestParam(value = "searchItem4", required = false) String searchItem4,
        @RequestParam(value = "searchType4", required = false) String searchType4,
        @RequestParam(value = "searchValue4", required = false) String searchValue4,
        @RequestParam(value = "searchItem5", required = false) String searchItem5,
        @RequestParam(value = "searchType5", required = false) String searchType5,
        @RequestParam(value = "searchValue5", required = false) String searchValue5,
        @RequestParam(value = "sortItem", required = false) String sortItem,
        @RequestParam(value = "sortDirc", required = false) String sortDirc,
        @RequestParam(value = "pageLimit", required = false) Integer pageLimit,
        @RequestParam(value = "pageOffset", required = false) Integer pageOffset
        ) throws CustomErrorException {
        List<CustomUserDetails> userList = new ArrayList<>();
        for (Integer i = pageOffset; i < pageOffset + pageLimit; i++) {
            CustomUserDetails user = new CustomUserDetails();
            user.setId(i);
            user.setUsername("user" + i);
            user.setRoles("ROLE_USER");
            userList.add(user);
        }        
        return new ResponseEntity<>(new Response(200, userList), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * ユーザー情報詳細取得API
     * 
     * @param username ユーザー名
     * @return レスポンス
     * @throws CustomErrorException
     */
    @GetMapping(value = "#{'${user.api.detail}'}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ResponseBody
    public ResponseEntity<Response> getUserDetail(@NotBlank @PathParam("username") String username)
            throws CustomErrorException {
        CustomUserDetails user = userService.getUser(username, false);
        return new ResponseEntity<>(new Response(200, user), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * ユーザー情報詳細登録API
     * 
     * @param user ユーザー情報
     * @return レスポンス
     * @throws CustomErrorException
     */
    @PostMapping(value = "#{'${user.api.detail}'}")
    @ResponseBody
    public ResponseEntity<Response> postUserDetail(
            @RequestBody @Validated(ViewGroup.PostUser.class) CustomUserDetails user)
            throws CustomErrorException {
        userService.registUser(user);
        return new ResponseEntity<>(new Response(200, ""), new HttpHeaders(), HttpStatus.OK);
    }
    @PostMapping(value = "/api/user/xxxxxx")
    @ResponseBody
    public ResponseEntity<Response> test(
            @RequestBody @Validated(ViewGroup.PostUser.class) CustomUserDetails user)
            throws CustomErrorException {
        userService.registUser(user);
        return new ResponseEntity<>(new Response(200, ""), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * ユーザー情報更新取得API
     * 
     * @param user ユーザー情報
     * @return レスポンス
     * @throws CustomErrorException
     */
    @PatchMapping(value = "#{'${user.api.detail}'}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ResponseBody
    public ResponseEntity<Response> patchUserDetail(
            @RequestBody @Validated(ViewGroup.PatchUser.class) CustomUserDetails user)
            throws CustomErrorException {
        userService.updateUser(user);
        return new ResponseEntity<>(new Response(200, ""), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * ユーザー情報詳細削除API
     * 
     * @param username ユーザー名
     * @return レスポンス
     * @throws CustomErrorException
     */
    @DeleteMapping(value = "#{'${user.api.detail}'}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ResponseBody
    public ResponseEntity<Response> deleteUserDetail(@NotBlank @PathParam("username") String username)
            throws CustomErrorException {
        userService.deleteUser(username);
        return new ResponseEntity<>(new Response(200, ""), new HttpHeaders(), HttpStatus.OK);
    }
}
