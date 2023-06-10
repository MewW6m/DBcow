import { common } from "../component/common.js";

/**
 **  * 検索条件の描画に関するクラス
 **/
export class Search {
    #searchItem1 = "";
    #searchType1 = "";
    #searchValue1 = "";
    #searchItem2 = "";
    #searchType2 = "";
    #searchValue2 = "";
	#searchItem3 = "";
    #searchType3 = "";
    #searchValue3 = "";
    #searchItem4 = "";
    #searchType4 = "";
    #searchValue4 = "";
	#searchItem5 = "";
    #searchType5 = "";
    #searchValue5 = "";
    

    constructor() {
    }
    
    /**
     **  * 検索パラメータの更新を行う
     **/
    updateSearchParam() {
        this.#initSearchParam();
        
        if (common.trim($("#searchValue1").val()).length > 0) {
            this.#searchItem1 = $("#searchItem1").val();
            this.#searchType1 = $("#searchType1").val();
            this.#searchValue1 = $("#searchValue1").val();
        }
        if (common.trim($("#searchValue2").val()).length > 0) {
            this.#searchItem2 = $("#searchItem2").val();
            this.#searchType2 = $("#searchType2").val();
            this.#searchValue2 = $("#searchValue2").val();
        }
        if (common.trim($("#searchValue3").val()).length > 0) {
            this.#searchItem3 = $("#searchItem3").val();
            this.#searchType3 = $("#searchType3").val();
            this.#searchValue3 = $("#searchValue3").val();
        }
        if (common.trim($("#searchValue4").val()).length > 0) {
            this.#searchItem4 = $("#searchItem4").val();
            this.#searchType4 = $("#searchType4").val();
            this.#searchValue4 = $("#searchValue4").val();
        }
        if (common.trim($("#searchValue5").val()).length > 0) {
            this.#searchItem5 = $("#searchItem5").val();
            this.#searchType5 = $("#searchType5").val();
            this.#searchValue5 = $("#searchValue5").val();
        }
    }

    showSearchOneRow() {
        $("#searchForm").addClass("overflow-two-liner");
        $(".searchRow.uk-hidden").first().removeClass("uk-hidden");
    }

    #initSearchParam() {
        this.#searchItem1 = "";
        this.#searchType1 = "";
        this.#searchValue1 = "";
        this.#searchItem2 = "";
        this.#searchType2 = "";
        this.#searchValue2 = "";
        this.#searchItem3 = "";
        this.#searchType3 = "";
        this.#searchValue3 = "";
        this.#searchItem4 = "";
        this.#searchType4 = "";
        this.#searchValue4 = "";
        this.#searchItem5 = "";
        this.#searchType5 = "";
        this.#searchValue5 = "";
    }

    get searchItem1() { return this.#searchItem1; }
    set searchItem1(arg) { if (typeof arg !== "string") throw new Error(""); this.#searchItem1 = arg; }
    get searchType1() { return this.#searchType1; }
    set searchType1(arg) { if (typeof arg !== "string") throw new Error(""); this.#searchType1 = arg; }
    get searchValue1() { return this.#searchValue1; }
    set searchValue1(arg) { if (typeof arg !== "string") throw new Error(""); this.#searchValue1 = arg; }
    
    get searchItem2() { return this.#searchItem2; }
    set searchItem2(arg) { if (typeof arg !== "string") throw new Error(""); this.#searchItem2 = arg; }
    get searchType2() { return this.#searchType2; }
    set searchType2(arg) { if (typeof arg !== "string") throw new Error(""); this.#searchType2 = arg; }
    get searchValue2() { return this.#searchValue2; }
    set searchValue2(arg) { if (typeof arg !== "string") throw new Error(""); this.#searchValue2 = arg; }
    
    get searchItem3() { return this.#searchItem3; }
    set searchItem3(arg) { if (typeof arg !== "string") throw new Error(""); this.#searchItem3 = arg; }
    get searchType3() { return this.#searchType3; }
    set searchType3(arg) { if (typeof arg !== "string") throw new Error(""); this.#searchType3 = arg; }
    get searchValue3() { return this.#searchValue3; }
    set searchValue3(arg) { if (typeof arg !== "string") throw new Error(""); this.#searchValue3 = arg; }
    
    get searchItem4() { return this.#searchItem4; }
    set searchItem4(arg) { if (typeof arg !== "string") throw new Error(""); this.#searchItem4 = arg; }
    get searchType4() { return this.#searchType4; }
    set searchType4(arg) { if (typeof arg !== "string") throw new Error(""); this.#searchType4 = arg; }
    get searchValue4() { return this.#searchValue4; }
    set searchValue4(arg) { if (typeof arg !== "string") throw new Error(""); this.#searchValue4 = arg; }
    
    get searchItem5() { return this.#searchItem5; }
    set searchItem5(arg) { if (typeof arg !== "string") throw new Error(""); this.#searchItem5 = arg; }
    get searchType5() { return this.#searchType5; }
    set searchType5(arg) { if (typeof arg !== "string") throw new Error(""); this.#searchType5 = arg; }
    get searchValue5() { return this.#searchValue5; }
    set searchValue5(arg) { if (typeof arg !== "string") throw new Error(""); this.#searchValue5 = arg; }
    
}

export let search = new Search();