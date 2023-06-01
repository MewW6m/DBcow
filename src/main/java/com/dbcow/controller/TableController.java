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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dbcow.model.Response;

@Controller
public class TableController {

    /**
     * テーブル一覧画面(DB)を返す
     * 
     * @return 画面
     */
    @GetMapping(value = "#{'${table.sc.db}'}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ModelAndView tableListDb() {
        Map<String, String> breadcumbs = new LinkedHashMap<>();
        breadcumbs.put("テーブル一覧", "/table");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("table/tableList");
        modelAndView.addObject("title", "テーブル一覧");
        modelAndView.addObject("breadcumbs", breadcumbs);
        return modelAndView;
    }

    /**
     * テーブル一覧画面(スキーマ)を返す
     * 
     * @param dbname DB名
     * @return 画面
     */
    @GetMapping(value = "#{'${table.sc.schema}'}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ModelAndView tableListDbSchema(@PathVariable("dbname") String dbname) {
        Map<String, String> breadcumbs = new LinkedHashMap<>();
        breadcumbs.put("テーブル一覧", "/table");
        breadcumbs.put(dbname == null ? " " : dbname, String.join("/", "/table", dbname));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("table/tableList");
        modelAndView.addObject("title", dbname);
        modelAndView.addObject("breadcumbs", breadcumbs);
        return modelAndView;
    }

    /**
     * テーブル一覧画面(テーブル)を返す
     * 
     * @param dbname DB名
     * @param schemaname スキーマ名
     * @return 画面
     */
    @GetMapping(value = "#{'${table.sc.table}'}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ModelAndView tableListDbSchemaTable(@PathVariable("dbname") String dbname,
            @PathVariable("schemaname") String schemaname) {
        Map<String, String> breadcumbs = new LinkedHashMap<>();
        breadcumbs.put("テーブル一覧", "/table");
        breadcumbs.put(dbname == null ? " " : dbname, String.join("/", "/table", dbname));
        breadcumbs.put(schemaname == null ? " " : schemaname, String.join("/", "/table", dbname, schemaname));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("table/tableList");
        modelAndView.addObject("title", schemaname);
        modelAndView.addObject("breadcumbs", breadcumbs);
        return modelAndView;
    }

    /**
     * テーブル一覧画面(データ)を返す
     * 
     * @param dbname DB名
     * @param schemaname スキーマ名
     * @param tablename テーブル名
     * @return 画面
     */
    @GetMapping(value = "#{'${table.sc.data}'}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ModelAndView tableListDbSchemaTableData(@PathVariable("dbname") String dbname,
            @PathVariable("schemaname") String schemaname, @PathVariable("tablename") String tablename) {
        Map<String, String> breadcumbs = new LinkedHashMap<>();
        breadcumbs.put("テーブル一覧", "/table");
        breadcumbs.put(dbname == null ? " " : dbname, String.join("/", "/table", dbname));
        breadcumbs.put(schemaname == null ? " " : schemaname, String.join("/", "/table", dbname, schemaname));
        breadcumbs.put(tablename == null ? " " : tablename, String.join("/", "/table", dbname, schemaname, tablename));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("table/dataList");
        modelAndView.addObject("title", tablename);
        modelAndView.addObject("breadcumbs", breadcumbs);
        return modelAndView;
    }

    /**
     * データ詳細画面
     * 
     * @param dbname DB名
     * @param schemaname スキーマ名
     * @param tablename テーブル名
     * @param dataname データ名
     * @return 画面
     */
    @GetMapping(value = "#{'${table.sc.data.detail}'}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ModelAndView dataDetail(@PathVariable("dbname") String dbname,
            @PathVariable("schemaname") String schemaname, @PathVariable("tablename") String tablename,
            @PathVariable("dataname") String dataname) {
        Map<String, String> breadcumbs = new LinkedHashMap<>();
        breadcumbs.put("テーブル一覧", "/table");
        breadcumbs.put(dbname == null ? " " : dbname, String.join("/", "/table", dbname));
        breadcumbs.put(schemaname == null ? " " : schemaname, String.join("/", "/table", dbname, schemaname));
        breadcumbs.put(tablename == null ? " " : tablename, String.join("/", "/table", dbname, schemaname, tablename));
        breadcumbs.put(dataname == null ? " " : dataname,
                String.join("/", "/table", dbname, schemaname, tablename, dataname));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("table/dataDetail");
        modelAndView.addObject("title", dataname);
        modelAndView.addObject("breadcumbs", breadcumbs);
        return modelAndView;
    }

    /**
     * テーブル一覧取得API
     * 
     * @return レスポンス
     */
    @GetMapping(value = "#{'${table.api.list.all}'}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @ResponseBody
    public ResponseEntity<Response> getTableList() {
        return new ResponseEntity<>(new Response(200, "GET /api/table/list OK"), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * テーブル一覧取得(DB)API
     * 
     * @return レスポンス
     */
    @GetMapping(value = "#{'${table.api.db}'}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @ResponseBody
    public ResponseEntity<Response> getTableListDb() {
        return new ResponseEntity<>(new Response(200, "GET /api/table/list OK"), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * テーブル一覧取得(スキーマ)API
     * 
     * @param dbname DB名
     * @return レスポンス
     */
    @GetMapping(value = "#{'${table.api.schema}'}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @ResponseBody
    public ResponseEntity<Response> getTableListDbSchema(@PathVariable("dbname") String dbname) {
        return new ResponseEntity<>(new Response(200, "GET /api/table/list OK"), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * テーブル一覧取得(テーブル)API
     * 
     * @param dbname DB名
     * @param schemaname スキーマ名
     * @return レスポンス
     */
    @GetMapping(value = "#{'${table.api.table}'}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @ResponseBody
    public ResponseEntity<Response> getTableListDbSchemaTable(@PathVariable("dbname") String dbname,
    @PathVariable("schemaname") String schemaname) {
        return new ResponseEntity<>(new Response(200, "GET /api/table/list OK"), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * テーブル一覧取得(データ)API
     * 
     * @param dbname DB名
     * @param schemaname スキーマ名
     * @param tablename テーブル名
     * @return レスポンス
     */
    @GetMapping(value = "#{'${table.api.data}'}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @ResponseBody
    public ResponseEntity<Response> getTableListDbSchemaTableData(@PathVariable("dbname") String dbname,
    @PathVariable("schemaname") String schemaname, @PathVariable("tablename") String tablename) {
        return new ResponseEntity<>(new Response(200, "GET /api/data/list OK"), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * データ詳細取得API
     * 
     * @param dbname DB名
     * @param schemaname スキーマ名
     * @param tablename テーブル名
     * @param dataname データ名
     * @return レスポンス
     */
    @GetMapping(value = "#{'${table.api.data.detail}'}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @ResponseBody
    public ResponseEntity<Response> getDataDetail(@PathVariable("dbname") String dbname,
    @PathVariable("schemaname") String schemaname, @PathVariable("tablename") String tablename,
    @PathVariable("dataname") String dataname) {
        return new ResponseEntity<>(new Response(200, "GET /api/data/detail OK"), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * データ詳細更新API
     * 
     * @param dbname DB名
     * @param schemaname スキーマ名
     * @param tablename テーブル名
     * @param dataname データ名
     * @return レスポンス
     */
    @PatchMapping(value = "#{'${table.api.data.detail}'}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @ResponseBody
    public ResponseEntity<Response> patchDataDetail(@PathVariable("dbname") String dbname,
    @PathVariable("schemaname") String schemaname, @PathVariable("tablename") String tablename,
    @PathVariable("dataname") String dataname) {
        return new ResponseEntity<>(new Response(200, "PATCH /api/data/detail OK"), new HttpHeaders(), HttpStatus.OK);
    }

    /**
     * データ詳細削除API
     * 
     * @param dbname DB名
     * @param schemaname スキーマ名
     * @param tablename テーブル名
     * @param dataname データ名
     * @return レスポンス
     */
    @DeleteMapping(value = "#{'${table.api.data.detail}'}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @ResponseBody
    public ResponseEntity<Response> deleteDataDetail(@PathVariable("dbname") String dbname,
    @PathVariable("schemaname") String schemaname, @PathVariable("tablename") String tablename,
    @PathVariable("dataname") String dataname) {
        return new ResponseEntity<>(new Response(200, "DELETE /api/data/detail OK"), new HttpHeaders(), HttpStatus.OK);
    }
}
