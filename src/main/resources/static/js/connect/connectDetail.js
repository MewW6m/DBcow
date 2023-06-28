import { common } from "../component/common.js";
import { api } from "/js/component/api.js";

class ConnectDetail {
    execBtnStatus = "save";

    constructor() {
        api.getConnectDetail().done((data) => {
            if (data.content.status === 1)
                $('#constatus').html('<span class="uk-badge">接続可能</span>');
            else
                $('#constatus').html('<span class="uk-badge uk-background-muted uk-text-secondary">接続不能</span>');
            $('input[name=conname]').val(data.content.conname);
            $('select[name=dbtype]').val(data.content.dbtype);
            $('input[name=host]').val(data.content.host);
            $('input[name=user]').val(data.content.user);
            $('input[name=password]').val(data.content.password);
        }).fail((jqXHR, textStatus, errorThrown) => {
            common.showErrorAlertMsg("接続情報詳細取得が失敗しました。\n" + JSON.parse(jqXHR.responseText).content);
            // ↓ログインしてないとき
            //location.href = loginPath + "?infoMsg=ログインしてください"
        });

        // 保存ボタンを押下したとき
        $(document).on("click", '#saveBtn', function (e) {
            if (common.getPathParam() == "regist") {
                api.postConnectDetail().done((data) => {
                    // location.href = location.pathname + "?infoMsg=接続情報詳細登録に成功しました。"
                }).fail((jqXHR, textStatus, errorThrown) => {
                    common.showErrorAlertMsg("接続情報詳細登録が失敗しました。\n" + JSON.parse(jqXHR.responseText).content);
                    // ↓ログインしてないとき
                    //location.href = loginPath + "?infoMsg=ログインしてください"
                });
            } else {
                api.patchConnectDetail().done((data) => {
                    // location.href = location.pathname + "?infoMsg=接続情報詳細更新に成功しました。"
                }).fail((jqXHR, textStatus, errorThrown) => {
                    common.showErrorAlertMsg("接続情報詳細更新が失敗しました。\n" + JSON.parse(jqXHR.responseText).content);
                    // ↓ログインしてないとき
                    //location.href = loginPath + "?infoMsg=ログインしてください"
                });
            }
        }.bind(this));

        // 削除ボタンを押下したとき
        $(document).on("click", '#deleteBtn', function (e) {
            api.deleteConnectDetail().done((data) => {
                // location.href = location.pathname + "?infoMsg=接続情報詳細削除に成功しました。"
            }).fail((jqXHR, textStatus, errorThrown) => {
                common.showErrorAlertMsg("接続情報詳細削除が失敗しました。\n" + JSON.parse(jqXHR.responseText).content);
                // ↓ログインしてないとき
                //location.href = loginPath + "?infoMsg=ログインしてください"
            });
        }.bind(this));

        // 保存削除ドロップダウン選択肢を押下したとき
        $(document).on("click", '#chTriggerExecBtn', function (e) {
            if (this.execBtnStatus == "save") {
                $('#executeButtons').html(this.getDeleteExecuteButtons());
                this.execBtnStatus = "delete";
            } else {
                $('#executeButtons').html(this.getSaveExecuteButtons());
                this.execBtnStatus = "save";
            }
        }.bind(this));
    }

    getDeleteExecuteButtons() {
        return `
            <button id="deleteBtn" class="uk-button uk-button-danger uk-width-expand" type="button">削除</button>
            <div class="uk-inline">
                <button class="uk-button uk-button-danger uk2-width-xsmall1 uk-padding-remove " type="button" aria-label="Toggle Dropdown"><span
                        uk-icon="icon:  triangle-down"></span></button>
                <div uk-dropdown="mode: click; target: !.uk-button-group;">
                    <ul class="uk-nav uk-dropdown-nav">
                        <li><a href="#" id="chTriggerExecBtn">保存</a></li>
                    </ul>
                </div>
            </div>
        `;
    }

    getSaveExecuteButtons() {
        return `
            <button id="saveBtn" class="uk-button uk-button-primary uk-width-expand" type="button">保存</button>
            <div class="uk-inline">
                <button class="uk-button uk-button-primary uk2-width-xsmall1 uk-padding-remove " type="button" aria-label="Toggle Dropdown"><span
                        uk-icon="icon:  triangle-down"></span></button>
                <div uk-dropdown="mode: click; target: !.uk-button-group;">
                    <ul class="uk-nav uk-dropdown-nav">
                        <li><a href="#" id="chTriggerExecBtn">削除</a></li>
                    </ul>
                </div>
            </div>
        `;
    }
}

let connectDetail = new ConnectDetail();