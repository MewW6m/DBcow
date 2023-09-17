import { common } from "../component/common.js";
import { api } from "/js/component/api.js";

class SettingDetail {
    constructor() {
        this.initSetting();

        // 保存ボタンを押下したとき
        $(document).on('click', '#saveSetting', function () {
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
                div += '<div class="req-label">' + line.name + '</div>';
                div += '<div>' + settingDetail.drawSettingValue(line) + '</div>';
                div += '</div>';
                $('#settingArea').append(div);
            });
        }
    }

    updateSetting() {

        if (!settingDetail.checkValidate()) return;

        let param = {};
        $('#settingArea input, #settingArea select').each(function(i1, elm){
            if (elm.type == 'checkbox')
                param[elm.name] = elm.checked;
            else
                param[elm.name] = elm.value;
        });

        api.patchSettingDetail(param).done((data) => {
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
            case 'str': return settingDetail.drawSettingStrValue(line);
            case 'int': return settingDetail.drawSettingIntValue(line);
            case 'date': return settingDetail.drawSettingDateValue(line);
            case 'datetime': return settingDetail.drawSettingDatetimeValue(line);
            case 'list': return settingDetail.drawSettingListValue(line);
            case 'radio': return settingDetail.drawSettingRadioValue(line);
            case 'toggle': return settingDetail.drawSettingToggleValue(line);
        }
    }

    drawSettingStrValue(line) {
        return '<input type="text" name="' + line.id + '" value="' + (line.value || '') + '" class="uk-input" maxlength="20">';
    }

    drawSettingIntValue(line) {
        let intElm = '';
        intElm += '<input type="number" name="' + line.id + '" value="' + (line.value || '') + '" class="uk-input" maxLength="6" ';
        intElm += 'oninput="if(this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);"';
        intElm += '>';
        return intElm;
    }

    drawSettingDateValue(line) {
        return '<input type="date" name="' + line.id + '" value="' + (line.value || '') + '" class="uk-input">';
    }

    drawSettingDatetimeValue(line) {
        return '<input type="datetime-local" name="' + line.id + '" value="' + (line.value || '') + '" class="uk-input">';
    }

    drawSettingListValue(line) {
        if (!line || !line.candidate) return;
        let listElm = '<select class="uk-select" name="' + line.id + '" aria-label="Select">';
        $.each(line.candidate.split(","), (i1, listValue) => {
            const checkedStr = line.value == listValue ? "selected" : "";
            listElm += '<option value="' + listValue + '" ' + checkedStr + '>' + listValue + '</option>';
        });
        listElm += '</select>';
        return listElm;
    }

    drawSettingRadioValue(line) {
        if (!line || !line.candidate) return;
        let radioElm = '';
        $.each(line.candidate.split(","), (i1, radioValue) => {
            const checkedStr = line.value == radioValue ? "checked" : "";
            radioElm += '<label><input type="radio" name="' + line.id + '" class="uk-radio" value="' + radioValue + '" ' + checkedStr + '>' + radioValue + '</label>';
        });
        return radioElm;
    }

    drawSettingToggleValue(line) {
        const checkedStr = line.value == "true" ? "checked" : "";
        // change label font size for togglesize
        return '<label class="toggleButton"><input type="checkbox" name="' + line.id + '" ' + checkedStr + '><span></span></label>';
    }

	checkValidate() {
        return true;
    }
}

let settingDetail = new SettingDetail();