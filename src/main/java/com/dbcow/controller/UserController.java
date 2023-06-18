package com.dbcow.controller;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.tuple.Triple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

import com.dbcow.config.CustomErrorException;
import com.dbcow.config.ViewGroup;
import com.dbcow.model.CustomUserDetails;
import com.dbcow.model.GetUserListReqParam;
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
    public ResponseEntity<Response> getUserList(@ModelAttribute @Validated GetUserListReqParam req) throws CustomErrorException {
        List<Triple<String, String, String>> searhParam = this.castListForSearchParam(
            req.getSearchItem1(), req.getSearchType1(), req.getSearchValue1(), 
            req.getSearchItem2(), req.getSearchType2(), req.getSearchValue2(), 
            req.getSearchItem3(), req.getSearchType3(), req.getSearchValue3(), 
            req.getSearchItem4(), req.getSearchType4(), req.getSearchValue4(), 
            req.getSearchItem5(), req.getSearchType5(), req.getSearchValue5());
        List<CustomUserDetails> userList = userService.getUserListBySearch(searhParam, 
            req.getSortItem(), req.getSortDirc(), req.getPageLimit(), req.getPageOffset());
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

    private List<Triple<String, String, String>> castListForSearchParam(
            String searchItem1, String searchType1, String searchValue1,
            String searchItem2, String searchType2, String searchValue2,
            String searchItem3, String searchType3, String searchValue3,
            String searchItem4, String searchType4, String searchValue4,
            String searchItem5, String searchType5, String searchValue5) {

        Triple<String, String, String> search1 = Triple.of(searchItem1, searchType1, searchValue1);
        Triple<String, String, String> search2 = Triple.of(searchItem2, searchType2, searchValue2);
        Triple<String, String, String> search3 = Triple.of(searchItem3, searchType3, searchValue3);
        Triple<String, String, String> search4 = Triple.of(searchItem4, searchType4, searchValue4);
        Triple<String, String, String> search5 = Triple.of(searchItem5, searchType5, searchValue5);

        List<Triple<String, String, String>> searchParam = new LinkedList();

        searchParam.add(search1);
        searchParam.add(search2);
        searchParam.add(search3);
        searchParam.add(search4);
        searchParam.add(search5);

        return searchParam;
    }
}
