import { api } from "../component/api.js";
import { common } from "../component/common.js";
import { list } from "../component/list.js";
import { search } from "../component/search.js";

class ConnectList {
    #sortItem = "conname";
    #sortDirc = "asc";
    #pageLimit = 100;
    #pageOffset = 0;

    constructor() {
        list.initParam(this.#sortItem, this.#sortDirc, this.#pageLimit, this.#pageOffset);
        
        // 初期表示時
        $(window).on('load', function () {
            this.getConnectList();
        }.bind(this));

        // 検索ボタンを押下したとき
        $(document).on("click", '#searchBtn', function (e) {
            list.scrollTopLine();
            list.resetLine();
            list.resetPages(this.#pageLimit, this.#pageOffset);
            search.updateSearchParam();
            this.getConnectList();
        }.bind(this));

        // ソートボタンを押下したとき
        $(document).on('click', '#listSection thead th', function (e) {
            list.scrollTopLine();
            list.resetLine();
            list.resetPages(this.#pageLimit, this.#pageOffset);
            list.updateArrow(e.currentTarget);
            this.getConnectList();
        }.bind(this));

        // プラスボタンを押下したとき
        $(document).on("click", '#plusOneRow', function (e) {
            search.showSearchOneRow();
        });

        // 下までスクロールした時
        list.attachScrollEvent(() => {this.getConnectList()}, this.#pageLimit);
    }

    // 共通の検索ロジック
    getConnectList() {
        api.setParam(search, list);
        api.getConnectList(api.commonSearchParam).done((data) => {
            try {
                data.content = connectList.repDataContent(data.content);
                list.updateLines(data.content, connectScDetail);
            } catch (e) {
                console.error(e);
                list.updateLines(null, connectScDetail);
                common.showErrorAlertMsg("接続情報一覧取得が失敗しました。");
            }
        }).fail((jqXHR, textStatus, errorThrown) => {
            common.showErrorAlertMsg("接続情報一覧取得が失敗しました。\n" + JSON.parse(jqXHR.responseText).content);
            // ↓ログインしてないとき
            //location.href = loginPath + "?infoMsg=ログインしてください"
        });
    }

    // 一覧表示時置換
    repDataContent(dataContentObj) {
        // $.each(dataContentObj, (i1, line) => {
        //     switch (line["roles"]) {
        //         case "ROLE_USER": line["roles"] = "一般ユーザー"; break;
        //         case "ROLE_ADMIN": line["roles"] = "管理者ユーザー"; break;
        //     }

        //     line["deleteFlag"] = line["deleteFlag"] ? "無効" : "有効";
        // });
        return dataContentObj;
    }
}

let connectList = new ConnectList();