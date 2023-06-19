import { common } from "../component/common.js";
import { api } from "/js/component/api.js";

class SettingDetail {
    constructor() {
        this.initSetting();

        // 保存ボタンを押下したとき
        $(document).on('click', '#saveSetting', function(){
            settingDetail.updateSetting();
        });
    }

    initSetting() {
        api.getSettingDetail().done((data) => {
            try {
                settingDetail.drawSetting(data.content);
            } catch (e) {
                console.error(e);
                common.showErrorAlertMsg("設定情報取得が失敗しました。");
            }
        }).fail((jqXHR, textStatus, errorThrown) => {
            common.showErrorAlertMsg("設定情報取得が失敗しました。\n" + JSON.parse(jqXHR.responseText).content);
            // ↓ログインしてないとき
            //location.href = loginPath + "?infoMsg=ログインしてください"
        });
    }

    drawSetting(content) {
    }

    updateSetting() {
        api.patchSettingDetail().done((data) => {
            try {
				location.href = location.pathname + "?infoMsg=設定情報更新に成功しました。"
            } catch (e) {
                console.error(e);
                common.showErrorAlertMsg("設定情報更新が失敗しました。");
            }
        }).fail((jqXHR, textStatus, errorThrown) => {
            common.showErrorAlertMsg("設定情報更新が失敗しました。\n" + JSON.parse(jqXHR.responseText).content);
            // ↓ログインしてないとき
            //location.href = loginPath + "?infoMsg=ログインしてください"
        });
    }
}

let settingDetail = new SettingDetail();