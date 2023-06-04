/**
 *  * 一覧の描画に関するクラス
 */
export class List {
    #sort = ""; #order = "";

    constructor() {
        $(document).on("click", ".listLine", function () {
            window.location = $(this).data('row');
        });
    }

    /**
     *  * 矢印を更新する
     *  * @param {object} elm - 押下した要素
     */
    updateArrow(elm) {
        let updownFlag = false;
        if ($(elm).find('.listHeaderArrow').attr('uk-icon') == 'icon: triangle-down') { updownFlag = true; }

        $('.listHeaderArrow').each((i, e) => { // resetArrow
            $(e).attr('uk-icon', 'icon: none');
            $(e).children().remove();
        });

        if (updownFlag) {
            $(elm).find('.listHeaderArrow').attr('uk-icon', 'icon: triangle-up');
        } else {
            $(elm).find('.listHeaderArrow').attr('uk-icon', 'icon: triangle-down');
        }

        this.#updateSortOrder(elm, updownFlag);
    }
    
    /**
     *  * 行を更新する
     *  * @param {jsonObject} json - 更新データ
     */
    updateLines(dataList) {
        $('#listSection tbody').empty();

        $.each(dataList, (i1, line) => {
            let userLink = userScDetail.replace("{" + listElmsKey + "}", line[listElmsKey]);
            let tr = '<tr class="listLine uk2-pointer" data-row="' + userLink + '">';
            $.each(line, (key, value) => {
                tr += '<td class="uk-text-nowrap" data-col="' + key + '"> ' + value + '</td>';
            });
            tr += '</tr>';
            $('#listSection tbody').append(tr);
        });

        $('#listSection > div').scrollTop(0);
    }

    /* 日時フォーマット(引用：https://zukucode.com/2017/04/javascript-date-format.html) */
    #formatDate(date, format) {
        format = format.replace(/yyyy/g, date.getFullYear());
        format = format.replace(/MM/g, ('0' + (date.getMonth() + 1)).slice(-2));
        format = format.replace(/dd/g, ('0' + date.getDate()).slice(-2));
        format = format.replace(/HH/g, ('0' + date.getHours()).slice(-2));
        format = format.replace(/mm/g, ('0' + date.getMinutes()).slice(-2));
        format = format.replace(/ss/g, ('0' + date.getSeconds()).slice(-2));
        format = format.replace(/SSS/g, ('00' + date.getMilliseconds()).slice(-3));
        return format;
    };

    get sort() { return this.#sort; }
    set sort(arg) { if (typeof arg !== "string") throw new Error(""); this.#sort = arg; }
    get order() { return this.#order; }
    set order(arg) { if (typeof arg !== "string") throw new Error(""); this.#order = arg; }

    /**
     *  * sort, orderを更新する
     *  * @param {object} elm - 押下した要素
     *  * @param {boolean} updownFlag - 上下フラグ
     */
    #updateSortOrder(elm, updownFlag) {
        this.sort = $(elm).data('col');
        this.order = updownFlag ? 'asc' : 'desc';
    }
}

export let list = new List();