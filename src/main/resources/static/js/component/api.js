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
			url: "/api/table/list",
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
			url: "/api/table/list2",
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
			url: "/api/data/list",
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
			url: "/api/data/detail",
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
			url: "/api/data/detail",
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
			url: "/api/data/detail",
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
			url: "/api/cond/list",
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
			url: "/api/connect/detail",
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
			url: "/api/connect/detail",
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
			url: "/api/connect/detail",
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
			url: "/api/connect/detail",
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
			url: "/api/cond/list",
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
			url: "/api/cond/detail",
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
			url: "/api/cond/detail",
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
			url: "/api/cond/detail",
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
			url: "/api/cond/detail",
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
			url: "/api/sqlexecute",
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
			url: userDetailPath,
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
			url: userDetailPath,
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
			url: userDetailPath,
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
			url: userDetailPath,
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
			url: userListPath,
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
			url: "/api/setting/detail",
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
			url: "/api/setting/detail",
			data: JSON.stringify(param),
			contentType: "application/json",
			dataType: 'json'
		});
	}
}

export let api = new Api();