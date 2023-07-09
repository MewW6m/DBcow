import { api } from "../component/api.js";
import { common } from "../component/common.js";
import { list } from "../component/list.js";


class SqlExecute {
    #sortItem = null;
    #sortDirc = null;
    #pageLimit = null;
    #pageOffset = null;

    constructor() {
        api.postSqlexecute();
    }
}

let sqlExecute = new SqlExecute();