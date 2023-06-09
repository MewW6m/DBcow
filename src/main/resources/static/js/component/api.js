/**
 * APIに関するクラス
 */
export class Api {

	commonSearchParam = {
		searchItem1: "", searchType1: "", searchValue1: "", searchItem2: "", searchType2: "", searchValue2: "",
		searchItem3: "", searchType3: "", searchValue3: "", searchItem4: "", searchType4: "", searchValue4: "",
		searchItem5: "", searchType5: "", searchValue5: "", sortItem: "", sortDirc: "", pageLimit: 0, pageOffset: 0
	}

	constructor() {
		let token = $("meta[name='_csrf']").attr("content");
		let header = $("meta[name='_csrf_header']").attr("content");

		$(document).ajaxSend(function (e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});
	}

	/**
	 * 検索条件一覧取得
	 * @param {*} param パラメータ
	 * @returns API
	 */
	getCondList(param) {
		return this.#ajax({
			type: "GET",
			url: condApiList,
			data: param,
			contentType: "application/json",
			dataType: 'json'
		});
	}

	/**
	 * 検索条件詳細取得
	 * @param {*} param パラメータ
	 * @param {string} condname 検索条件名
	 * @returns API
	 */
	getCondDetail(param, condname) {
		return this.#ajax({
			type: "GET",
			url: this.#replaceParamStr(
				condApiDetail,
				{ "condname": condname }
			),
			data: param,
			contentType: "application/json",
			dataType: 'json'
		});
	}

	/**
	 * 検索条件詳細登録
	 * @param {*} param パラメータ
	 * @param {string} condname 検索条件名
	 * @returns API
	 */
	postCondDetail(param, condname) {
		return this.#ajax({
			type: "POST",
			url: this.#replaceParamStr(
				condApiDetail,
				{ "condname": condname }
			),
			data: JSON.stringify(param),
			contentType: "application/json",
			dataType: 'json'
		});
	}

	/**
	 * 検索条件詳細更新
	 * @param {*} param パラメータ
	 * @param {string} condname 検索条件名
	 * @returns API
	 */
	patchCondDetail(param, condname) {
		return this.#ajax({
			type: "PATCH",
			url: this.#replaceParamStr(
				condApiDetail,
				{ "condname": condname }
			),
			data: JSON.stringify(param),
			contentType: "application/json",
			dataType: 'json'
		});
	}

	/**
	 * 検索条件詳細削除
	 * @param {*} param パラメータ
	 * @param {string} condname 検索条件名
	 * @returns API
	 */
	deleteCondDetail(param, condname) {
		return this.#ajax({
			type: "DELETE",
			url: this.#replaceParamStr(
				condApiDetail,
				{ "condname": condname }
			),
			data: param,
			contentType: "application/json",
			dataType: 'json'
		});
	}

	/**
	 * 接続情報一覧取得
	 * @param {*} param パラメータ
	 * @returns API
	 */
	getConnectList(param) {
		return this.#ajax({
			type: "GET",
			url: connectApiList,
			data: param,
			contentType: "application/json",
			dataType: 'json'
		});
	}

	/**
	 * 接続情報詳細取得
	 * @param {*} param パラメータ
	 * @param {string} conname 接続名
	 * @returns API
	 */
	getConnectDetail(param, conname) {
		return this.#ajax({
			type: "GET",
			url: this.#replaceParamStr(
				connectApiDetail,
				{ "conname": conname }
			),
			data: param,
			contentType: "application/json",
			dataType: 'json'
		});
	}

	/**
	 * 接続情報詳細登録
	 * @param {*} param パラメータ
	 * @param {string} conname 接続名
	 * @returns API
	 */
	postConnectDetail(param, conname) {
		return this.#ajax({
			type: "POST",
			url: this.#replaceParamStr(
				connectApiDetail,
				{ "conname": conname }
			),
			data: JSON.stringify(param),
			contentType: "application/json",
			dataType: 'json'
		});
	}

	/**
	 * 接続情報詳細更新
	 * @param {*} param パラメータ
	 * @param {string} conname 接続名
	 * @returns API
	 */
	patchConnectDetail(param, conname) {
		return this.#ajax({
			type: "PATCH",
			url: this.#replaceParamStr(
				connectApiDetail,
				{ "conname": conname }
			),
			data: JSON.stringify(param),
			contentType: "application/json",
			dataType: 'json'
		});
	}

	/**
	 * 接続情報詳細削除
	 * @param {*} param パラメータ
	 * @param {string} conname 接続名
	 * @returns API
	 */
	deleteConnectDetail(param, conname) {
		return this.#ajax({
			type: "DELETE",
			url: this.#replaceParamStr(
				connectApiDetail,
				{ "conname": conname }
			),
			data: param,
			contentType: "application/json",
			dataType: 'json'
		});
	}

	/**
	 * 設定情報取得
	 * @param {*} param パラメータ
	 * @returns API
	 */
	getSettingDetail(param) {
		return this.#ajax({
			type: "GET",
			url: settingApiDetail,
			data: param,
			contentType: "application/json",
			dataType: 'json'
		});
	}

	/**
	 * 設定情報更新
	 * @param {*} param パラメータ
	 * @returns API
	 */
	patchSettingDetail(param) {
		return this.#ajax({
			type: "PATCH",
			url: settingApiDetail,
			data: JSON.stringify(param),
			contentType: "application/json",
			dataType: 'json'
		});
	}

	/**
	 * SQL実行
	 * @param {*} param パラメータ
	 * @returns API
	 */
	postSqlexecute(param) {
		return this.#ajax({
			type: "POST",
			url: sqlexecuteApiDetail,
			data: JSON.stringify(param),
			contentType: "application/json",
			dataType: 'json'
		});
	}

	/**
	 * テーブル一覧取得
	 * @param {*} param パラメータ
	 * @returns API
	 */
	getTableList(param) {
		return this.#ajax({
			type: "GET",
			url: tableApiListAll,
			data: param,
			contentType: "application/json",
			dataType: 'json'
		});
	}

	/**
	 * テーブル一覧取得(DB)
	 * @param {*} param パラメータ
	 * @returns API
	 */
	getTableListDb(param) {
		return this.#ajax({
			type: "GET",
			url: tableApiDb,
			data: param,
			contentType: "application/json",
			dataType: 'json'
		});
	}

	/**
	 * テーブル一覧取得(スキーマ)
	 * @param {*} param パラメータ
	 * @param {string} dbname DB名
	 * @returns API
	 */
	getTableListDbSchema(param, dbname) {
		return this.#ajax({
			type: "GET",
			url: this.#replaceParamStr(
				tableApiSchema,
				{ "dbname": dbname }
			),
			data: param,
			contentType: "application/json",
			dataType: 'json'
		});
	}

	/**
	 * テーブル一覧取得(テーブル)
	 * @param {*} param パラメータ
	 * @param {string} dbname DB名
	 * @param {string} schemaname スキーマ名
	 * @returns API
	 */
	getTableListDbSchemaTable(param, dbname, schemaname) {
		return this.#ajax({
			type: "GET",
			url: this.#replaceParamStr(
				tableApiTable,
				{
					"dbname": dbname,
					"schemaname": schemaname
				}
			),
			data: param,
			contentType: "application/json",
			dataType: 'json'
		});
	}

	/**
	 * テーブル一覧取得(データ)
	 * @param {*} param パラメータ
	 * @param {string} dbname DB名
	 * @param {string} schemaname スキーマ名
	 * @param {string} tablename テーブル名
	 * @returns API
	 */
	getTableListDbSchemaTableData(param, dbname, schemaname, tablename) {
		return this.#ajax({
			type: "GET",
			url: this.#replaceParamStr(tableApiData,
				{
					"dbname": dbname,
					"schemaname": schemaname,
					"tablename": tablename
				}
			),
			data: param,
			contentType: "application/json",
			dataType: 'json'
		});
	}

	/**
	 * データ詳細取得
	 * @param {*} param パラメータ
	 * @param {string} dbname DB名
	 * @param {string} schemaname スキーマ名
	 * @param {string} tablename テーブル名
	 * @param {string} dataname データ名
	 * @returns API
	 */
	getDataDetail(param, dbname, schemaname, tablename, dataname) {
		return this.#ajax({
			type: "GET",
			url: this.#replaceParamStr(
				tableApiDataDetail,
				{
					"dbname": dbname,
					"schemaname": schemaname,
					"tablename": tablename,
					"dataname": dataname
				}
			),
			data: param,
			contentType: "application/json",
			dataType: 'json'
		});
	}

	/**
	 * データ詳細更新
	 * @param {*} param パラメータ
	 * @param {string} dbname DB名
	 * @param {string} schemaname スキーマ名
	 * @param {string} tablename テーブル名
	 * @param {string} dataname データ名
	 * @returns API
	 */
	patchDataDetail(param, dbname, schemaname, tablename, dataname) {
		return this.#ajax({
			type: "PATCH",
			url: this.#replaceParamStr(
				tableApiDataDetail,
				{
					"dbname": dbname,
					"schemaname": schemaname,
					"tablename": tablename,
					"dataname": dataname
				}
			),
			data: JSON.stringify(param),
			contentType: "application/json",
			dataType: 'json'
		});
	}

	/**
	 * データ詳細削除
	 * @param {*} param パラメータ
	 * @param {string} dbname DB名
	 * @param {string} schemaname スキーマ名
	 * @param {string} tablename テーブル名
	 * @param {string} dataname データ名
	 * @returns API
	 */
	deleteDataDetail(param, dbname, schemaname, tablename, dataname) {
		return this.#ajax({
			type: "DELETE",
			url: this.#replaceParamStr(
				tableApiDataDetail,
				{
					"dbname": dbname,
					"schemaname": schemaname,
					"tablename": tablename,
					"dataname": dataname
				}
			),
			data: param,
			contentType: "application/json",
			dataType: 'json'
		});
	}

	/**
	 * ユーザー一覧API
	 * @param {*} param パラメータ
	 * @returns API
	 */
	getUserList(param) {
		return this.#ajax({
			type: "GET",
			url: userApiList,
			data: param,
			dataType: "json"
		});
	}

	/**
	 * ユーザー取得API
	 * @param {*} param パラメータ
	 * @param {string} username ユーザー名
	 * @returns API
	 */
	getUserDetail(param, username) {
		return this.#ajax({
			type: "GET",
			url: this.#replaceParamStr(
				userApiDetail,
				{ "username": username }
			),
			data: param,
			dataType: "json"
		});
	}

	/**
	 * ユーザー登録API
	 * @param {*} param パラメータ
	 * @param {string} username ユーザー名
	 * @returns API
	 */
	postUserDetail(param, username) {
		return this.#ajax({
			type: "POST",
			url: this.#replaceParamStr(
				userApiDetail,
				{ "username": username }
			),
			data: JSON.stringify(param),
			dataType: "json",
			contentType: "application/json"
		});
	}

	/**
	 * ユーザー更新API
	 * @param {*} param パラメータ
	 * @param {string} username ユーザー名
	 * @returns API
	 */
	patchUserDetail(param, username) {
		return this.#ajax({
			type: "PATCH",
			url: this.#replaceParamStr(
				userApiDetail,
				{ "username": username }
			),
			data: JSON.stringify(param),
			dataType: "json",
			contentType: "application/json"
		});
	}

	/**
	 * ユーザー削除API
	 * @param {*} param パラメータ
	 * @param {string} username ユーザー名
	 * @returns API
	 */
	deleteUserDetail(param, username) {
		return this.#ajax({
			type: "DELETE",
			url: this.#replaceParamStr(
				userApiDetail,
				{ "username": username }
			),
			data: param,
			dataType: "json"
		});
	}

	/**
	 * API共通処理
	 * @param {*} ajaxOptions API設定値
	 * @returns API
	 */
	#ajax(ajaxOptions) {
		var deferred = new $.Deferred;
		ajaxOptions.beforeSend = () => { $('#loading').show(); }
		$.ajax(ajaxOptions)
			.done((data, status, xhr) => {
				console.log(data);
				deferred.resolve(data);
			}).fail((jqXHR, textStatus, errorThrown) => {
				console.error(jqXHR.responseText);
				deferred.reject(jqXHR, textStatus, errorThrown);
			}).always(() => {
				setInterval(() => { $('#loading').hide() }, 1000);
			});
		return deferred.promise();
	}

	/**
	 * URLのパスパラメータを置換する
	 * @param {string} target 置換対象(URL)
	 * @param {Object.<string, string>} strdict パスパラメータ
	 */
	#replaceParamStr(target, strdict) {
		if (!target)
			return "";
		if (!strdict)
			return target;
		try {
			for (let key in strdict) {
				let repKey = "{" + key + "}";
				let repValue = strdict[key].split('#')[0];
				target = target.replace(repKey, repValue);
			}
		} catch (e) {
			console.error(e);
		}
		return target;
	}

    setParam(search, list) {
        api.commonSearchParam.searchItem1 = search.searchItem1;
        api.commonSearchParam.searchType1 = search.searchType1;
        api.commonSearchParam.searchValue1 = search.searchValue1;
        api.commonSearchParam.searchItem2 = search.searchItem2;
        api.commonSearchParam.searchType2 = search.searchType2;
        api.commonSearchParam.searchValue2 = search.searchValue2;
        api.commonSearchParam.searchItem3 = search.searchItem3;
        api.commonSearchParam.searchType3 = search.searchType3;
        api.commonSearchParam.searchValue3 = search.searchValue3;
        api.commonSearchParam.searchItem4 = search.searchItem4;
        api.commonSearchParam.searchType4 = search.searchType4;
        api.commonSearchParam.searchValue4 = search.searchValue4;
        api.commonSearchParam.searchItem5 = search.searchItem5;
        api.commonSearchParam.searchType5 = search.searchType5;
        api.commonSearchParam.searchValue5 = search.searchValue5;
        api.commonSearchParam.sortItem = list.sortItem;
        api.commonSearchParam.sortDirc = list.sortDirc;
        api.commonSearchParam.pageLimit = list.pageLimit;
        api.commonSearchParam.pageOffset = list.pageOffset;
    }
}

export let api = new Api();