import { api } from "../component/api.js";
import { common } from "../component/common.js";
import { list } from "../component/list.js";
import { search } from "../component/search.js";

class UserList {
    constructor() {
        this.initParam();

        // 初期表示時
        $(window).on('load', function () {
            this.getUserList();
        }.bind(this));

        // 検索ボタンを押下したとき
        $(document).on("click", '#searchBtn', function (e) {
            search.updateSearchParam();
            this.getUserList();
        }.bind(this));

        // ソートボタンを押下したとき
        $(document).on('click', '#listSection thead th', function (e) {
            list.updateArrow(e.currentTarget);
            this.getUserList();
        }.bind(this));

        // プラスボタンを押下したとき
        $(document).on("click", '#plusOneRow', function (e) {
            search.showSearchOneRow();
        });

        //search.setSearchItem(listElms);
    }

    // 共通の検索ロジック
    getUserList() {
        this.setParam();
        api.getUserList(api.searchParam).done((data) => {
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
        list.sortItem = "username";
        list.sortDirc = "asc";
        list.pageLimit = "100";
        list.pageOffset = "0";
    }

    setParam() {
        api.searchParam.searchItems1 = search.searchItems1;
        api.searchParam.searchType1 = search.searchType1;
        api.searchParam.searchValue1 = search.searchValue1;
        api.searchParam.searchItems2 = search.searchItems2;
        api.searchParam.searchType2 = search.searchType2;
        api.searchParam.searchValue2 = search.searchValue2;
        api.searchParam.searchItems3 = search.searchItems3;
        api.searchParam.searchType3 = search.searchType3;
        api.searchParam.searchValue3 = search.searchValue3;
        api.searchParam.searchItems4 = search.searchItems4;
        api.searchParam.searchType4 = search.searchType4;
        api.searchParam.searchValue4 = search.searchValue4;
        api.searchParam.searchItems5 = search.searchItems5;
        api.searchParam.searchType5 = search.searchType5;
        api.searchParam.searchValue5 = search.searchValue5;
        api.searchParam.sortItem = list.sortItem;
        api.searchParam.sortDirc = list.sortDirc;
        api.searchParam.pageLimit = list.pageLimit;
        api.searchParam.pageOffset = list.pageOffset;
    }
}

let userList = new UserList();