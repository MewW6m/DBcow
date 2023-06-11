import { api } from "../component/api.js";
import { common } from "../component/common.js";
import { list } from "../component/list.js";
import { search } from "../component/search.js";

class UserList {
    #sortItem = "username";
    #sortDirc = "asc";
    #pageLimit = 100;
    #pageOffset = 1;

    constructor() {
        this.initParam();

        // 初期表示時
        $(window).on('load', function () {
            this.getUserList();
        }.bind(this));

        // 検索ボタンを押下したとき
        $(document).on("click", '#searchBtn', function (e) {
            list.scrollTopLine();
            list.resetLine();
            list.resetPages(this.#pageLimit, this.#pageOffset);
            search.updateSearchParam();
            this.getUserList();
        }.bind(this));

        // ソートボタンを押下したとき
        $(document).on('click', '#listSection thead th', function (e) {
            list.scrollTopLine();
            list.resetLine();
            list.resetPages(this.#pageLimit, this.#pageOffset);
            list.updateArrow(e.currentTarget);
            this.getUserList();
        }.bind(this));

        // プラスボタンを押下したとき
        $(document).on("click", '#plusOneRow', function (e) {
            search.showSearchOneRow();
        });

        // 下までスクロールした時
        this.#attachScrollEvent();
    }

    // 共通の検索ロジック
    getUserList() {
        this.setParam();
        api.getUserList(api.commonSearchParam).done((data) => {
            try {
                data.content = userList.repDataContent(data.content);
                list.updateLines(data.content);
            } catch (e) {
                console.error(e);
                list.updateLines(null);
                common.showErrorAlertMsg("ユーザー一覧取得が失敗しました。");
            }
        }).fail((jqXHR, textStatus, errorThrown) => {
            common.showErrorAlertMsg("ユーザー一覧取得が失敗しました。\n" + JSON.parse(jqXHR.responseText).content);
            // ↓ログインしてないとき
            //location.href = loginPath + "?infoMsg=ログインしてください"
        });
    }

    // 一覧表示時置換
    repDataContent(dataContentObj) {
        $.each(dataContentObj, (i1, line) => {
            switch (line["roles"]) {
                case "ROLE_USER": line["roles"] = "一般ユーザー"; break;
                case "ROLE_ADMIN": line["roles"] = "管理者ユーザー"; break;
            }

            line["deleteFlag"] = line["deleteFlag"] ? "無効" : "有効";
        });
        return dataContentObj;
    }

    initParam() {
        list.sortItem = this.#sortItem;
        list.sortDirc = this.#sortDirc;
        list.pageLimit = this.#pageLimit;
        list.pageOffset = this.#pageOffset;
    }

    setParam() {
        api.commonSearchParam.searchItem1 = search.searchItem1;
        api.commonSearchParam.searchType1 = search.searchType1;
        api.commonSearchParam.searchValue1 = search.searchValue1;
        api.commonSearchParam.searchItem2 = search.searchItem2;
        api.commonSearchParam.searchType2 = search.searchType2;
        api.commonSearchParam.searchValue2 = search.searchValue2;
        api.commonSearchParam.searchItem3 = search.searchItem3;
        api.commonSearchParam.searchType3 = search.searchType3;
        api.commonSearchParam.searchValue3 = search.searchValue3;
        api.commonSearchParam.searchItem4 = search.searchItem4;
        api.commonSearchParam.searchType4 = search.searchType4;
        api.commonSearchParam.searchValue4 = search.searchValue4;
        api.commonSearchParam.searchItem5 = search.searchItem5;
        api.commonSearchParam.searchType5 = search.searchType5;
        api.commonSearchParam.searchValue5 = search.searchValue5;
        api.commonSearchParam.sortItem = list.sortItem;
        api.commonSearchParam.sortDirc = list.sortDirc;
        api.commonSearchParam.pageLimit = list.pageLimit;
        api.commonSearchParam.pageOffset = list.pageOffset;
    }
    
    #attachScrollEvent() {
        document.querySelector('#listSection').addEventListener('scroll', function (event) {
            console.log($('#listSection').scrollTop() + $('#listSection').innerHeight());
            if (($('#listSection').scrollTop() + $('#listSection').innerHeight() > 450) &&
                ($('#listSection').scrollTop() + $('#listSection').innerHeight() >= $('#listSection')[0].scrollHeight-1)) {
                list.updatePages();
                this.getUserList();
            
            }
        }.bind(this), true);
    }
}

let userList = new UserList();