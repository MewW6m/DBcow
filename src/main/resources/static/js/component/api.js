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
}

export let api = new Api();