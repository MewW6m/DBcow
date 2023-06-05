/**
 **  * 検索条件の描画に関するクラス
 **/
export class Search {
    #params;

    constructor() {
    }
    
    /**
     **  * 検索パラメータの更新を行う
     **/
    updateSearch() {
        $('.searchItems')
    }

    showSearchOneRow() {
        $("#searchForm").addClass("overflow-two-liner");
        $(".searchRow.uk-hidden").first().removeClass("uk-hidden");
    }
}

export let search = new Search();