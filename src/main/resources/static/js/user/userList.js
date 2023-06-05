import { api } from "../component/api.js";
import { common } from "../component/common.js";
import { list } from "../component/list.js";
import { search } from "../component/search.js";

class UserList {
    #param = {};
    #result = {};

/*
{
  "search": [
    {
      "items": "Jane Doe",
      "type": "888-555-1212",
      "Value": "spouse"
    }
  ],
  "sort": {
    "item": "\"\"",
    "dir": "\"\""
  },
  "page": {
    "limit": "100",
    "offset": "0"
  }
}
*/

    constructor() {
        this.param;

        // 初期表示時
        $(window).on('load', function () {
            // this.getUserList(this.param);
        });

        // 検索ボタンを押下したとき
        $(document).on("click", '#searchBtn', async function (e) {
            search.updateSearch();
            // this.getUserList(this.param);
        });

        // ソートボタンを押下したとき
        $(document).on('click', '#listSection thead th', async function (e) {
            list.updateArrow(e.currentTarget);
            // this.getUserList(this.param);
        });

        // プラスボタンを押下したとき
        $(document).on("click", '#plusOneRow', async function (e) {
            search.showSearchOneRow();
        });

        //search.setSearchItem(listElms);
    }

    // 共通の検索ロジック
    getUserList(param) {
        api.getUserList(param).done((data) => {
            try {
                data.content = userList.repDataContent(data.content);
                list.updateLines(data.content);
            } catch (e) {
                console.error(e);
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