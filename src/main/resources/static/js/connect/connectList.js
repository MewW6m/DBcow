import { common } from "../component/common.js";
import { api } from "/js/component/api.js";

class ConnectList {
    constructor() {
        let paran;
        api.getConnectList(api.commonSearchParam).done((data) => {

        }).fail((jqXHR, textStatus, errorThrown) => {
            common.showErrorAlertMsg("接続情報一覧取得が失敗しました。\n" + JSON.parse(jqXHR.responseText).content);
            // ↓ログインしてないとき
            //location.href = loginPath + "?infoMsg=ログインしてください"
        });
    }
}

let connectList = new ConnectList();