package com.dbcow.model;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetUserListReqParam {

    @Pattern(regexp = "^$|(username|password|roles|createDate|updateDate|deleteFlag)")
    private String searchItem1;

    @Pattern(regexp = "^$|(CO|EQ|SW|EW|MT|ML|IN)")
    private String searchType1;

    private String searchValue1;

    @Pattern(regexp = "^$|(username|password|roles|createDate|updateDate|deleteFlag)")
    private String searchItem2;

    @Pattern(regexp = "^$|(CO|EQ|SW|EW|MT|ML|IN)")
    private String searchType2;

    private String searchValue2;

    @Pattern(regexp = "^$|(username|password|roles|createDate|updateDate|deleteFlag)")
    private String searchItem3;

    @Pattern(regexp = "^$|(CO|EQ|SW|EW|MT|ML|IN)")
    private String searchType3;

    private String searchValue3;

    @Pattern(regexp = "^$|(username|password|roles|createDate|updateDate|deleteFlag)")
    private String searchItem4;

    @Pattern(regexp = "^$|(CO|EQ|SW|EW|MT|ML|IN)")
    private String searchType4;

    private String searchValue4;

    @Pattern(regexp = "^$|(username|password|roles|createDate|updateDate|deleteFlag)")
    private String searchItem5;

    @Pattern(regexp = "^$|(CO|EQ|SW|EW|MT|ML|IN)")
    private String searchType5;

    private String searchValue5;

    private String sortItem = "username";

    private String sortDirc = "asc";

    private Integer pageLimit = 1;

    private Integer pageOffset = 100;   
}