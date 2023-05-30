/**
 **  * 検索条件の描画に関するクラス
 **/
export class Search {
    #itemCode = "";
    #itemName = "";
    #status = "";
    #registUser = "";
    #updateUser = "";

    /**
     **  * 検索パラメータの更新を行う
     **/
    updateSearch() {
        this.itemCode = $('#itemCodeInput').val();
        this.itemName = $('#itemNameInput').val();
        this.status = $('#statusSelect').val();
        this.registUser = $('#registUserInput').val();
        this.updateUser = $('#updateUserInput').val();
    }

    // "#searchSection > .uk-width-expand"
    attachOverFlow(elmname) {
        $(elmname).attr("overflow-two-liner", "overflow-two-liner");
    }

    get itemCode() { return this.#itemCode; }
    set itemCode(arg) { if (typeof arg !== "string") throw new Error(""); this.#itemCode = arg; }
    get itemName() { return this.#itemName; }
    set itemName(arg) { if (typeof arg !== "string") throw new Error(""); this.#itemName = arg; }
    get status() { return this.#status; }
    set status(arg) { if (typeof arg !== "string") throw new Error(""); this.#status = arg; }
    get registUser() { return this.#registUser; }
    set registUser(arg) { if (typeof arg !== "string") throw new Error(""); this.#registUser = arg; }
    get updateUser() { return this.#updateUser; }
    set updateUser(arg) { if (typeof arg !== "string") throw new Error(""); this.#updateUser = arg; }
}

export let search = new Search();