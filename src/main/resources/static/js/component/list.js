/**
 *  * 一覧の描画に関するクラス
 */
export class List {
    #sortItem = "";
    #sortDirc = "";
    #pageLimit = 0;
    #pageOffset = 0;

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
        if (dataList) {
            $.each(dataList, (i1, line) => {
                let userLink = userScDetail.replace("{" + listElmsKey + "}", line[listElmsKey]);
                let tr = '<tr class="listLine uk2-pointer" data-row="' + userLink + '">';
                $.each(line, (key, value) => {
                    tr += '<td class="uk-text-nowrap" data-col="' + key + '"> ' + value + '</td>';
                });
                tr += '</tr>';
                $('#listSection tbody').append(tr);
            });
        }
    }

    /**
     * 行をリセットする
     */
    resetLine() {
        $('#listSection tbody').empty();
    }

    /**
     * 行の一番上にスクロールする
     */
    scrollTopLine() {
        $('#listSection').scrollTop(0);
    }

    /**
     * ページを更新する
     */
    updatePages() {
        let pageLimit = this.#pageLimit;
        let pageOffset = this.#pageOffset + pageLimit;
        this.#updateLimitOffset(pageLimit, pageOffset);
    }

    /**
     * ページをリセットする
     * @param {*} pageLimit ページ表示数
     * @param {*} pageOffset ページ位置
     */
    resetPages(pageLimit, pageOffset) {
        this.#updateLimitOffset(pageLimit, pageOffset);
    }

    /**
     *  * ソート項目、ソート向きを更新する
     *  * @param {object} elm - 押下した要素
     *  * @param {boolean} updownFlag - 上下フラグ
     */
    #updateSortOrder(elm, updownFlag) {
        this.sortItem = $(elm).data('col');
        this.sortDirc = updownFlag ? 'asc' : 'desc';
    }

    /**
     * ページ表示数、ページ位置を更新する
     * @param {*} pageLimit ページ表示数
     * @param {*} pageOffset ページ位置
     */
    #updateLimitOffset(pageLimit, pageOffset) {
        this.pageLimit = pageLimit;
        this.pageOffset = pageOffset;
    }

    get sortItem() { return this.#sortItem; }
    set sortItem(arg) { if (typeof arg !== "string") throw new Error(""); this.#sortItem = arg; }
    get sortDirc() { return this.#sortDirc; }
    set sortDirc(arg) { if (typeof arg !== "string") throw new Error(""); this.#sortDirc = arg; }
    get pageLimit() { return this.#pageLimit; }
    set pageLimit(arg) { if (typeof arg !== "number") throw new Error(""); this.#pageLimit = arg; }
    get pageOffset() { return this.#pageOffset; }
    set pageOffset(arg) { if (typeof arg !== "number") throw new Error(""); this.#pageOffset = arg; }
}

export let list = new List();