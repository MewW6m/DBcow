import { common } from "../component/common.js";
import { api } from "/js/component/api.js";

class ConnectDetail {
    constructor() {
        api.getConnectDetail().done((data) => {
            //
        }).fail((jqXHR, textStatus, errorThrown) => {
            common.showErrorAlertMsg("接続情報詳細取得が失敗しました。\n" + JSON.parse(jqXHR.responseText).content);
            // ↓ログインしてないとき
            //location.href = loginPath + "?infoMsg=ログインしてください"
        });

        api.postConnectDetail().done((data) => {
            // location.href = location.pathname + "?infoMsg=接続情報詳細登録に成功しました。"
        }).fail((jqXHR, textStatus, errorThrown) => {
            common.showErrorAlertMsg("接続情報詳細登録が失敗しました。\n" + JSON.parse(jqXHR.responseText).content);
            // ↓ログインしてないとき
            //location.href = loginPath + "?infoMsg=ログインしてください"
        });
        
        api.patchConnectDetail().done((data) => {
            // location.href = location.pathname + "?infoMsg=接続情報詳細更新に成功しました。"
        }).fail((jqXHR, textStatus, errorThrown) => {
            common.showErrorAlertMsg("接続情報詳細更新が失敗しました。\n" + JSON.parse(jqXHR.responseText).content);
            // ↓ログインしてないとき
            //location.href = loginPath + "?infoMsg=ログインしてください"
        });
        
        api.deleteConnectDetail().done((data) => {
            // location.href = location.pathname + "?infoMsg=接続情報詳細削除に成功しました。"
        }).fail((jqXHR, textStatus, errorThrown) => {
            common.showErrorAlertMsg("接続情報詳細削除が失敗しました。\n" + JSON.parse(jqXHR.responseText).content);
            // ↓ログインしてないとき
            //location.href = loginPath + "?infoMsg=ログインしてください"
        });
    }
}

let connectDetail = new ConnectDetail();