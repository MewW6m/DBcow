/**
 * APIに関するクラス
 */
export class Api {
	/**
	 * API共通処理
	 * @param {*} ajaxOptions API設定値
	 * @returns API
	 */
	ajax(ajaxOptions) {
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
	 * テーブル一覧取得(仮)
	 * @param {*} param パラメータ
	 * @returns API
	 */
	getTableList(param) {
		return this.ajax({
			type: "GET",
			url: tableApiDb,
			data: param,
			contentType: "application/json",
			dataType: 'json'
		});
	}

	/**
	 * テーブル一覧取得2(仮)
	 * @param {*} param パラメータ
	 * @returns API
	 */
	getTableList2(param) {
		return this.ajax({
			type: "GET",
			url: tableApiTable,
			data: param,
			contentType: "application/json",
			dataType: 'json'
		});
	}

	/**
	 * データ一覧取得(仮)
	 * @param {*} param パラメータ
	 * @returns API
	 */
	getDataList(param) {
		return this.ajax({
			type: "GET",
			url: tableApiData,
			data: param,
			contentType: "application/json",
			dataType: 'json'
		});
	}

	/**
	 * データ詳細取得(仮)
	 * @param {*} param パラメータ
	 * @returns API
	 */
	getDataDetail(param) {
		return this.ajax({
			type: "GET",
			url: tableApiDataDetail,
			data: param,
			contentType: "application/json",
			dataType: 'json'
		});
	}

	/**
	 * データ詳細更新(仮)
	 * @param {*} param パラメータ
	 * @returns API
	 */
	patchDataDetail(param) {
		return this.ajax({
			type: "PATCH",
			url: tableApiDataDetail,
			data: JSON.stringify(param),
			contentType: "application/json",
			dataType: 'json'
		});
	}

	/**
	 * データ詳細削除(仮)
	 * @param {*} param パラメータ
	 * @returns API
	 */
	deleteDataDetail(param) {
		return this.ajax({
			type: "DELETE",
			url: tableApiDataDetail,
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
		return this.ajax({
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
	 * @returns API
	 */
	getConnectDetail(param) {
		return this.ajax({
			type: "GET",
			url: connectApiDetail,
			data: param,
			contentType: "application/json",
			dataType: 'json'
		});
	}

	/**
	 * 接続情報詳細登録
	 * @param {*} param パラメータ
	 * @returns API
	 */
	postConnectDetail(param) {
		return this.ajax({
			type: "POST",
			url: connectApiDetail,
			data: JSON.stringify(param),
			contentType: "application/json",
			dataType: 'json'
		});
	}

	/**
	 * 接続情報詳細更新
	 * @param {*} param パラメータ
	 * @returns API
	 */
	patchConnectDetail(param) {
		return this.ajax({
			type: "PATCH",
			url: connectApiDetail,
			data: JSON.stringify(param),
			contentType: "application/json",
			dataType: 'json'
		});
	}

	/**
	 * 接続情報詳細削除
	 * @param {*} param パラメータ
	 * @returns API
	 */
	deleteConnectDetail(param) {
		return this.ajax({
			type: "DELETE",
			url: connectApiDetail,
			data: param,
			contentType: "application/json",
			dataType: 'json'
		});
	}

	/**
	 * 検索条件一覧取得
	 * @param {*} param パラメータ
	 * @returns API
	 */
	getCondList(param) {
		return this.ajax({
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
	 * @returns API
	 */
	getCondDetail(param) {
		return this.ajax({
			type: "GET",
			url: condApiDetail,
			data: param,
			contentType: "application/json",
			dataType: 'json'
		});
	}

	/**
	 * 検索条件詳細登録
	 * @param {*} param パラメータ
	 * @returns API
	 */
	postCondDetail(param) {
		return this.ajax({
			type: "POST",
			url: condApiDetail,
			data: JSON.stringify(param),
			contentType: "application/json",
			dataType: 'json'
		});
	}

	/**
	 * 検索条件詳細更新
	 * @param {*} param パラメータ
	 * @returns API
	 */
	patchCondDetail(param) {
		return this.ajax({
			type: "PATCH",
			url: condApiDetail,
			data: JSON.stringify(param),
			contentType: "application/json",
			dataType: 'json'
		});
	}

	/**
	 * 検索条件詳細削除
	 * @param {*} param パラメータ
	 * @returns API
	 */
	deleteCondDetail(param) {
		return this.ajax({
			type: "DELETE",
			url: condApiDetail,
			data: param,
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
		return this.ajax({
			type: "POST",
			url: sqlexecuteApiDetail,
			data: JSON.stringify(param),
			contentType: "application/json",
			dataType: 'json'
		});
	}

	/**
	 * ユーザー取得API
	 * @param {*} param パラメータ
	 * @returns API
	 */
	getUserDetail(param) {
		return this.ajax({
			type: "GET",
			url: userApiDetail,
			data: param,
			dataType: "json"
		});
	}

	/**
	 * ユーザー更新API
	 * @param {*} param パラメータ
	 * @returns API
	 */
	patchUserDetail(param) {
		return this.ajax({
			type: "PATCH",
			url: userApiDetail,
			data: JSON.stringify(param),
			dataType: "json",
			contentType: "application/json"
		});
	}

	/**
	 * ユーザー削除API
	 * @param {*} param パラメータ
	 * @returns API
	 */
	deleteUserDetail(param) {
		return this.ajax({
			type: "DELETE",
			url: userApiDetail,
			data: param,
			dataType: "json"
		});
	}

	/**
	 * ユーザー登録API
	 * @param {*} param パラメータ
	 * @returns API
	 */
	postUserDetail(param) {
		return this.ajax({
			type: "POST",
			url: userApiDetail,
			data: JSON.stringify(param),
			dataType: "json",
			contentType: "application/json"
		});
	}

	/**
	 * ユーザー一覧API
	 * @param {*} param パラメータ
	 * @returns API
	 */
	getUserList(param) {
		return this.ajax({
			type: "GET",
			url: userApiList,
			data: param,
			dataType: "json"
		});
	}

	/**
	 * 設定情報取得
	 * @param {*} param パラメータ
	 * @returns API
	 */
	getSettingDetail(param) {
		return this.ajax({
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
		return this.ajax({
			type: "PATCH",
			url: settingApiDetail,
			data: JSON.stringify(param),
			contentType: "application/json",
			dataType: 'json'
		});
	}
}

export let api = new Api();