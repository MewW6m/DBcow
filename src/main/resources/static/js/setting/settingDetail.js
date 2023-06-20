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

    drawSetting(dataList) {
        if (dataList) {
            $.each(dataList, (i1, line) => {
                let div = '<div id="setting' + line.id + '" class="uk-column-1-2 uk-margin">';
                div += '<div>' + line.name + '</div>';
                div += '<div>' + settingDetail.drawSettingValue(line) + '</div>';
                div += '</div>';
                $('#settingArea').append(div);
            });
        }
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

    drawSettingValue(line) {
        if (!line || !line.type) return;
        switch (line.type) {
            case 'str': 
                return '<input type="text" value="' + (line.value || '') + '" class="uk-input">';
            case 'int': 
                return '<input type="number" value="' + (line.value || '') + '" class="uk-input">';
            case 'datetime': 
                return '<input type="datetime-local" value="' + (line.value || '') + '" class="uk-input">';
            case 'list': 
                return '<input type="text" value="' + (line.value || '') + '" class="uk-input">';
            case 'radio': 
                return '<input type="radio" value="' + (line.value || '') + '" class="uk-input">';
            case 'toggle': 
                return '<input type="text" value="' + (line.value || '') + '" class="uk-input">';
        }
    }
}

let settingDetail = new SettingDetail();