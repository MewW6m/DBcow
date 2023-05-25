/**
 *  * フッターの描画に関するクラス
 */
class Footer {
    #page = 1;
    #totalCount = 0;

    /**
     *  * 最初のページに移動する
     */
    moveFirstPage() {
        const movePage = 1;
        this.updatePageSelect(movePage);
    }

    /**
     *  * 前のページに移動する
     */
    moveBeforePage() {
        const movePage = this.page == 1 ? 1 : this.page - 1;
        this.updatePageSelect(movePage);
    }

    /**
     *  * 次のページに移動する
     */
    moveAfterPage() {
        const lastPage = Math.floor(footer.totalCount / 30) + 1;
        const movePage = this.page == lastPage ? this.page : this.page + 1;
        this.updatePageSelect(movePage);

    }

    /**
     *  * 最後のページに移動する
     */
    moveLastPage() {
        const movePage = Math.floor(footer.totalCount / 30) + 1;
        this.updatePageSelect(movePage);
    }

    /**
     *  * ページ番号を更新する
     *  * @param {number} num - ページ番号
     */
    updatePageSelect(num) {
        this.page = num;
        $('#pageSelect').val(num);
    }

    /**
     *  * 件数を更新する
     *  * @param {number} num - 件数 
     */
    updateTotalCount(num) {
        this.totalCount = num;
        const pageNum = Math.floor(this.totalCount / 30) + 1;
        const firstCount = num == 0 ? 0 : 30 * (this.page - 1) + 1;
        const lastCount = num == 0 ? 0 : this.page == pageNum ? num : 30 * (this.page - 1) + 30;
        $('#totalCount').text(num);
        $('#firstCount').text(firstCount);
        $('#lastCount').text(lastCount);
    }

    /**
     *  * ページ番号セレクトボックスの選択肢を更新する
     */
    updatePageSelectChoice() {
        const pageNum = Math.floor(this.totalCount / 30) + 1;
        const pageList = Array.from(Array(pageNum), (v, k) => k);

        $('#pageSelect').empty();
        $.each(pageList, function (index, value) {
            $('#pageSelect').append('<option>' + (value + 1) + '</option>')
        });
    }


    get page() { return this.#page; }
    set page(arg) { if (typeof arg !== "number") throw new Error(""); this.#page = arg; }
    get totalCount() { return this.#totalCount; }
    set totalCount(arg) { if (typeof arg !== "number") throw new Error(""); this.#totalCount = arg; }
}