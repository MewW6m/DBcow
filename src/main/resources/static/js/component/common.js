/**
 * 共通のロジック
 */
export class Common {
    #messageType = ['infoMsg', 'successMsg', 'warnMsg', 'errorMsg']
    #urlQueryKey;
    #urlQueryValue;
    
        
    constructor() {
        this.#setUrlProperty();
        this.#showFirstAlertMsg();
    }

    /**
     * アラートを描画する
     * @param {*} key メッセージ種類キー
     * @param {*} message メッセージ内容
     */
    showAlertMsg(key, message) {
        if (this.#messageType.includes(key)) {
            document.querySelector("#" + key + " p").innerText = message;
            document.querySelector("#" + key).removeAttribute("hidden");
        }
    }

    /**
     * アラート描画をリセットする
     */
    resetAlertMsg() {
        for (var key of this.#messageType) {
            document.querySelector("#" + key + " p").innerText = "";
            document.querySelector("#" + key).setAttribute("hidden", true);
        }
    }

    /**
     * 情報アラートを描画する
     * @param {*} message メッセージ内容
     */
    showInfoAlertMsg(message) {
        this.resetAlertMsg();
        this.showAlertMsg("infoMsg", message);
    }

    /**
     * 成功アラートを描画する
     * @param {*} message メッセージ内容
     */
    showSuccessAlertMsg(message) {
        this.resetAlertMsg();
        this.showAlertMsg("successMsg", message);
    }

    /**
     * 注意アラートを描画する
     * @param {*} message メッセージ内容
     */
    showWarnAlertMsg(message) {
        this.resetAlertMsg();
        this.showAlertMsg("warnMsg", message);
    }

    /**
     * エラーアラートを描画する
     * @param {*} message メッセージ内容
     */
    showErrorAlertMsg(message) {
        this.resetAlertMsg();
        this.showAlertMsg("errorMsg", message);
    }

    /**
     * 二重クリックを禁止する
     * @param {*} elm 対象HTML要素
     */
    banDoubleClick(elm) {
        $(elm).addClass("uk-disabled");
        $(elm).attr("disabled");
        setTimeout(function () {
            $(elm).removeClass("uk-disabled");
            $(elm).removeAttr("disabled");

        }, 3000);
    }

    /**
     * URLパラメータを返す
     * @param {*} url 対象のURL文字列（任意）
     * @param {*} name {string} パラメータのキー文字列
     * @return URLパラメータ値
     */
    getQueryParam(url, name) {
        if (!url) url = window.location.href;
        name = name.replace(/[\[\]]/g, "\\$&");
        var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
            results = regex.exec(url);
        if (!results) return null;
        if (!results[2]) return '';
        return decodeURIComponent(results[2].replace(/\+/g, " "));
    }

    /**
     * パスパラメータを返す
     * @param {*} url 対象のURL文字列（任意）
     * @returns パスパラメータ値
     */
    getPathParam(url) {
        if (!url) url = window.location.pathname;
        return url.substring(url.lastIndexOf('/') + 1);
    }

    /**
     * ディープコピーを返す
     * @param {*} obj コピー対象
     * @returns コピー結果
     */
    copy(obj) {
        return JSON.parse(JSON.stringify(obj));
    }

    /**
     * トリムした文字を返す
     * @param {*} str トリム対象
     * @returns トリム結果
     */
    trim(str) {
        return (typeof str === 'string')? str.trim() : '';
    }

    /**
     * 日時フォーマット
     * @param {*} date 日時
     * @param {*} format フォーマット
     * @returns 日時フォーマット文字列
     */
    formatDate(date, format) {
        format = format.replace(/yyyy/g, date.getFullYear());
        format = format.replace(/MM/g, ('0' + (date.getMonth() + 1)).slice(-2));
        format = format.replace(/dd/g, ('0' + date.getDate()).slice(-2));
        format = format.replace(/HH/g, ('0' + date.getHours()).slice(-2));
        format = format.replace(/mm/g, ('0' + date.getMinutes()).slice(-2));
        format = format.replace(/ss/g, ('0' + date.getSeconds()).slice(-2));
        format = format.replace(/SSS/g, ('00' + date.getMilliseconds()).slice(-3));
        return format;
    };

    /**
     * URLパラメータからアラートを描画する
     * (ex. ?infoMsg=メッセージ内容)
     */
    #showFirstAlertMsg() {
        this.resetAlertMsg();

        if (this.#urlQueryValue && this.#urlQueryValue != "") {
            document.querySelector("#" + this.#urlQueryKey + " p").innerText = this.#urlQueryValue;
            document.querySelector("#" + this.#urlQueryKey).removeAttribute("hidden");
        }
    }

    #setUrlProperty() {
        for (var key of this.#messageType) {
            let urlQueryValue2 = this.getQueryParam(null, key);
            if (!urlQueryValue2 && !urlQueryValue2 != "") continue;
            this.#urlQueryKey = key;
            this.#urlQueryValue = urlQueryValue2;
        }
    }
}

export let common = new Common();