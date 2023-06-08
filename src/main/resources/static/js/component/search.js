import { common } from "../component/common.js";

/**
 **  * 検索条件の描画に関するクラス
 **/
export class Search {
    #searchItems1 = "";
    #searchType1 = "";
    #searchValue1 = "";
    #searchItems2 = "";
    #searchType2 = "";
    #searchValue2 = "";
	#searchItems3 = "";
    #searchType3 = "";
    #searchValue3 = "";
    #searchItems4 = "";
    #searchType4 = "";
    #searchValue4 = "";
	#searchItems5 = "";
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
            this.#searchItems1 = $("#searchItems1").val();
            this.#searchType1 = $("#searchType1").val();
            this.#searchValue1 = $("#searchValue1").val();
        }
        if (common.trim($("#searchValue2").val()).length > 0) {
            this.#searchItems2 = $("#searchItems2").val();
            this.#searchType2 = $("#searchType2").val();
            this.#searchValue2 = $("#searchValue2").val();
        }
        if (common.trim($("#searchValue3").val()).length > 0) {
            this.#searchItems3 = $("#searchItems3").val();
            this.#searchType3 = $("#searchType3").val();
            this.#searchValue3 = $("#searchValue3").val();
        }
        if (common.trim($("#searchValue4").val()).length > 0) {
            this.#searchItems4 = $("#searchItems4").val();
            this.#searchType4 = $("#searchType4").val();
            this.#searchValue4 = $("#searchValue4").val();
        }
        if (common.trim($("#searchValue5").val()).length > 0) {
            this.#searchItems5 = $("#searchItems5").val();
            this.#searchType5 = $("#searchType5").val();
            this.#searchValue5 = $("#searchValue5").val();
        }
    }

    showSearchOneRow() {
        $("#searchForm").addClass("overflow-two-liner");
        $(".searchRow.uk-hidden").first().removeClass("uk-hidden");
    }

    #initSearchParam() {
        this.#searchItems1 = "";
        this.#searchType1 = "";
        this.#searchValue1 = "";
        this.#searchItems2 = "";
        this.#searchType2 = "";
        this.#searchValue2 = "";
        this.#searchItems3 = "";
        this.#searchType3 = "";
        this.#searchValue3 = "";
        this.#searchItems4 = "";
        this.#searchType4 = "";
        this.#searchValue4 = "";
        this.#searchItems5 = "";
        this.#searchType5 = "";
        this.#searchValue5 = "";
    }

    get searchItems1() { return this.#searchItems1; }
    set searchItems1(arg) { if (typeof arg !== "string") throw new Error(""); this.#searchItems1 = arg; }
    get searchType1() { return this.#searchType1; }
    set searchType1(arg) { if (typeof arg !== "string") throw new Error(""); this.#searchType1 = arg; }
    get searchValue1() { return this.#searchValue1; }
    set searchValue1(arg) { if (typeof arg !== "string") throw new Error(""); this.#searchValue1 = arg; }
    get searchItems1() { return this.#searchItems1; }
    
    get searchItems2() { return this.#searchItems2; }
    set searchItems2(arg) { if (typeof arg !== "string") throw new Error(""); this.#searchItems2 = arg; }
    get searchType2() { return this.#searchType2; }
    set searchType2(arg) { if (typeof arg !== "string") throw new Error(""); this.#searchType2 = arg; }
    get searchValue2() { return this.#searchValue2; }
    set searchValue2(arg) { if (typeof arg !== "string") throw new Error(""); this.#searchValue2 = arg; }
    get searchItems2() { return this.#searchItems2; }
    
    get searchItems3() { return this.#searchItems3; }
    set searchItems3(arg) { if (typeof arg !== "string") throw new Error(""); this.#searchItems3 = arg; }
    get searchType3() { return this.#searchType3; }
    set searchType3(arg) { if (typeof arg !== "string") throw new Error(""); this.#searchType3 = arg; }
    get searchValue3() { return this.#searchValue3; }
    set searchValue3(arg) { if (typeof arg !== "string") throw new Error(""); this.#searchValue3 = arg; }
    get searchItems3() { return this.#searchItems3; }
    
    get searchItems4() { return this.#searchItems4; }
    set searchItems4(arg) { if (typeof arg !== "string") throw new Error(""); this.#searchItems4 = arg; }
    get searchType4() { return this.#searchType4; }
    set searchType4(arg) { if (typeof arg !== "string") throw new Error(""); this.#searchType4 = arg; }
    get searchValue4() { return this.#searchValue4; }
    set searchValue4(arg) { if (typeof arg !== "string") throw new Error(""); this.#searchValue4 = arg; }
    get searchItems4() { return this.#searchItems4; }
    
    get searchItems5() { return this.#searchItems5; }
    set searchItems5(arg) { if (typeof arg !== "string") throw new Error(""); this.#searchItems5 = arg; }
    get searchType5() { return this.#searchType5; }
    set searchType5(arg) { if (typeof arg !== "string") throw new Error(""); this.#searchType5 = arg; }
    get searchValue5() { return this.#searchValue5; }
    set searchValue5(arg) { if (typeof arg !== "string") throw new Error(""); this.#searchValue5 = arg; }
    get searchItems5() { return this.#searchItems5; }
    
}

export let search = new Search();