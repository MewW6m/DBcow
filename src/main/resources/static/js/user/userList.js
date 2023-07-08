import { api } from "../component/api.js";
import { common } from "../component/common.js";
import { list } from "../component/list.js";
import { search } from "../component/search.js";

class UserList {
    #sortItem = "username";
    #sortDirc = "asc";
    #pageLimit = 100;
    #pageOffset = 0;

    constructor() {
        list.initParam(this.#sortItem, this.#sortDirc, this.#pageLimit, this.#pageOffset);

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
        list.attachScrollEvent(() => {this.getUserList()}, this.#pageLimit);
    }

    // 共通の検索ロジック
    getUserList() {
        api.setParam(search, list);
        api.getUserList(api.commonSearchParam).done((data) => {
            try {
                data.content = userList.repDataContent(data.content);
                list.updateLines(data.content, userScDetail);
            } catch (e) {
                console.error(e);
                list.updateLines(null, userScDetail);
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
}

let userList = new UserList();