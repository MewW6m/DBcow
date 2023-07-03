package com.dbcow.model;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetConnectListReqParam {

    @Pattern(regexp = "^$|(conname|dbtype|host|status|createDate|updateDate)")
    private String searchItem1;

    @Pattern(regexp = "^$|(CO|EQ|SW|EW|MT|ML|IN)")
    private String searchType1;

    private String searchValue1;

    @Pattern(regexp = "^$|(conname|dbtype|host|status|createDate|updateDate)")
    private String searchItem2;

    @Pattern(regexp = "^$|(CO|EQ|SW|EW|MT|ML|IN)")
    private String searchType2;

    private String searchValue2;

    @Pattern(regexp = "^$|(conname|dbtype|host|status|createDate|updateDate)")
    private String searchItem3;

    @Pattern(regexp = "^$|(CO|EQ|SW|EW|MT|ML|IN)")
    private String searchType3;

    private String searchValue3;

    @Pattern(regexp = "^$|(conname|dbtype|host|status|createDate|updateDate)")
    private String searchItem4;

    @Pattern(regexp = "^$|(CO|EQ|SW|EW|MT|ML|IN)")
    private String searchType4;

    private String searchValue4;

    @Pattern(regexp = "^$|(conname|dbtype|host|status|createDate|updateDate)")
    private String searchItem5;

    @Pattern(regexp = "^$|(CO|EQ|SW|EW|MT|ML|IN)")
    private String searchType5;

    private String searchValue5;

    @Pattern(regexp = "^$|(conname|dbtype|host|status|createDate|updateDate)")
    private String sortItem = "conname";

    @Pattern(regexp = "^$|(desc|asc)")
    private String sortDirc = "asc";

    private Integer pageLimit = 0;

    private Integer pageOffset = 100;
}