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

	getTableList(param) {
		return this.ajax({
			type: "GET",
			url: "/api/table/list",
			data: param,
			contentType: "application/json",
			dataType: 'json'
		});
	}

	getTableList2(param) {
		return this.ajax({
			type: "GET",
			url: "/api/table/list2",
			data: param,
			contentType: "application/json",
			dataType: 'json'
		});
	}

	getDataList(param) {
		return this.ajax({
			type: "GET",
			url: "/api/data/list",
			data: param,
			contentType: "application/json",
			dataType: 'json'
		});
	}

	getDataDetail(param) {
		return this.ajax({
			type: "GET",
			url: "/api/data/detail",
			data: param,
			contentType: "application/json",
			dataType: 'json'
		});
	}

	patchDataDetail(param) {
		return this.ajax({
			type: "PATCH",
			url: "/api/data/detail",
			data: JSON.stringify(param),
			contentType: "application/json",
			dataType: 'json'
		});
	}

	deleteDataDetail(param) {
		return this.ajax({
			type: "DELETE",
			url: "/api/data/detail",
			data: param,
			contentType: "application/json",
			dataType: 'json'
		});
	}

	getConnectList(param) {
		return this.ajax({
			type: "GET",
			url: "/api/cond/list",
			data: param,
			contentType: "application/json",
			dataType: 'json'
		});
	}

	getConnectDetail(param) {
		return this.ajax({
			type: "GET",
			url: "/api/connect/detail",
			data: param,
			contentType: "application/json",
			dataType: 'json'
		});
	}

	postConnectDetail(param) {
		return this.ajax({
			type: "POST",
			url: "/api/connect/detail",
			data: JSON.stringify(param),
			contentType: "application/json",
			dataType: 'json'
		});
	}

	patchConnectDetail(param) {
		return this.ajax({
			type: "PATCH",
			url: "/api/connect/detail",
			data: JSON.stringify(param),
			contentType: "application/json",
			dataType: 'json'
		});
	}

	deleteConnectDetail(param) {
		return this.ajax({
			type: "DELETE",
			url: "/api/connect/detail",
			data: param,
			contentType: "application/json",
			dataType: 'json'
		});
	}

	getCondList(param) {
		return this.ajax({
			type: "GET",
			url: "/api/cond/list",
			data: param,
			contentType: "application/json",
			dataType: 'json'
		});
	}

	getCondDetail(param) {
		return this.ajax({
			type: "GET",
			url: "/api/cond/detail",
			data: param,
			contentType: "application/json",
			dataType: 'json'
		});
	}

	postCondDetail(param) {
		return this.ajax({
			type: "POST",
			url: "/api/cond/detail",
			data: JSON.stringify(param),
			contentType: "application/json",
			dataType: 'json'
		});
	}

	patchCondDetail(param) {
		return this.ajax({
			type: "PATCH",
			url: "/api/cond/detail",
			data: JSON.stringify(param),
			contentType: "application/json",
			dataType: 'json'
		});
	}

	deleteCondDetail(param) {
		return this.ajax({
			type: "DELETE",
			url: "/api/cond/detail",
			data: param,
			contentType: "application/json",
			dataType: 'json'
		});
	}

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

	getSettingDetail(param) {
		return this.ajax({
			type: "GET",
			url: "/api/setting/detail",
			data: param,
			contentType: "application/json",
			dataType: 'json'
		});
	}

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