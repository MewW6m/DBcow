package com.dbcow.controller;

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
     * 新規登録画面を返す
     * 
     * @return 画面
     */
    @GetMapping(value = "/user/regist")
    public ModelAndView getRegist() {
        ModelAndView modelAndView = new ModelAndView();
        if (controllerUtil.isLogged())
            modelAndView.setViewName("redirect:/table/list");
        else
            modelAndView.setViewName("user/regist");
        return modelAndView;
    }

    /**
     * ユーザー情報一覧画面を返す
     * 
     * @return 画面
     */
    @GetMapping(value = "/user/list")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/list");
        return modelAndView;
    }

    /**
     * ユーザー情報詳細画面を返す
     * 
     * @param username ユーザー名
     * @return 画面
     */
    @GetMapping(value = "/user/detail/{username}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ModelAndView detail(@PathVariable("username") String username) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/detail");
        return modelAndView;
    }

    /**
     * ユーザー情報一覧JSONを返す
     * 
     * @return ユーザー情報一覧JSON
     * @throws CustomErrorException
     */
    @GetMapping(value = "/api/user/list")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ResponseBody
    public ResponseEntity<Response> getUserList() throws CustomErrorException {
        return new ResponseEntity<>(new Response(200, "GET /api/user/list OK"), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * ユーザー情報詳細JSONを返す
     * 
     * @param username ユーザー名
     * @return ユーザー情報詳細JSON
     * @throws CustomErrorException
     */
    @GetMapping(value = "/api/user/detail")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ResponseBody
    public ResponseEntity<Response> getUserDetail(@NotBlank @PathParam("username") String username) throws CustomErrorException {
        CustomUserDetails user = userService.getUser(username, false);
        return new ResponseEntity<>(new Response(200, user), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * ユーザー情報登録を実行し結果を返す
     * 
     * @param user ユーザー情報
     * @return 実行結果を返す
     * @throws CustomErrorException
     */
    @PostMapping(value = "/api/user/detail")
    @ResponseBody
    public ResponseEntity<Response> postUserDetail(
            @RequestBody @Validated(ViewGroup.PostUser.class) CustomUserDetails user)
            throws CustomErrorException {
        userService.registUser(user);
        return new ResponseEntity<>(new Response(200, ""), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * ユーザー情報更新を実行し結果を返す
     * 
     * @param user ユーザー情報
     * @return 実行結果を返す
     * @throws CustomErrorException
     */
    @PatchMapping(value = "/api/user/detail")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ResponseBody
    public ResponseEntity<Response> patchUserDetail(
            @RequestBody @Validated(ViewGroup.PatchUser.class) CustomUserDetails user)
            throws CustomErrorException {
        userService.updateUser(user);
        return new ResponseEntity<>(new Response(200, ""), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * ユーザー情報削除を実行し結果を返す
     * 
     * @param username ユーザー名
     * @return 実行結果を返す
     * @throws CustomErrorException
     */
    @DeleteMapping(value = "/api/user/detail")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ResponseBody
    public ResponseEntity<Response> deleteUserDetail(@NotBlank @PathParam("username") String username)
            throws CustomErrorException {
        userService.deleteUser(username);
        return new ResponseEntity<>(new Response(200, ""), new HttpHeaders(), HttpStatus.OK);
    }
}
