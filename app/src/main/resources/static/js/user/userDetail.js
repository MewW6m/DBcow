import { api } from "../component/api.js";
import { common } from "../component/common.js";

class UserDetail {
	constructor() {
		$(window).on('load', function () {
			let param = {};
			param.username = common.getPathParam(null);
			api.getUserDetail(param, param.username).done((data) => {
				$('input[name=username]').val(data.content.username);
				$('select[name=roles]').val(data.content.roles);
				$('input[name=deleteFlag]').val(data.content.deleteFlag === true ? '無効' : '有効');
			}).fail((jqXHR, textStatus, errorThrown) => {
				common.showErrorAlertMsg("ユーザー取得が失敗しました。\n" + JSON.parse(jqXHR.responseText).content);
				// ↓ログインしてないとき
				//location.href = loginPath + "?infoMsg=ログインしてください"
			});
		});

		$(document).on('click', '#updateBtn', function () {

			if (!userDetail.checkValidate()) return;

			let param = {};
			param.username = $('input[name=username]').val();
			param.password = $('input[name=password]').val();
			param.roles = $('select[name=roles]').val();

			api.patchUserDetail(param, param.username).done((data) => {
				location.href = location.pathname + "?infoMsg=ユーザー更新に成功しました。"
			}).fail((jqXHR, textStatus, errorThrown) => {
				common.showErrorAlertMsg("ユーザー更新が失敗しました。\n" + JSON.parse(jqXHR.responseText).content);
				// ↓ログインしてないとき
				//location.href = loginPath + "?infoMsg=ログインしてください"
				
			});
		});

		$(document).on('click', '#deleteBtn', function () {
			let param = {};
			param.username = common.getPathParam(null);
			api.deleteUserDetail(param, param.username).done((data) => {
				location.href = location.pathname + "?infoMsg=ユーザー削除に成功しました。"
			}).fail((jqXHR, textStatus, errorThrown) => {
				common.showErrorAlertMsg("ユーザー削除が失敗しました。\n" + JSON.parse(jqXHR.responseText).content);
				// ↓ログインしてないとき
				//location.href = loginPath + "?infoMsg=ログインしてください"
			});
		});
	}

	checkValidate() {/*
		$('*[name=username]')[0].setCustomValidity("");
		$('*[name=username]')[0].checkValidity();
		$('*[name=username]')[0].reportValidity();
		if (!$('*[name=username]')[0].validity.valid) return false;

		$('*[name=password]')[0].setCustomValidity("");
		$('*[name=password]')[0].checkValidity();
		$('*[name=password]')[0].reportValidity();
		if (!$('*[name=password]')[0].validity.valid) return false;

		$('*[name=re_password]')[0].setCustomValidity("");
		$('*[name=re_password]')[0].checkValidity();
		$('*[name=re_password]')[0].reportValidity();
		if (!$('*[name=re_password]')[0].validity.valid) return false;

		if ($('*[name=password]').val() != $('*[name=re_password]').val()) {
			$('*[name=re_password]')[0].setCustomValidity("パスワードと確認用パスワードが一致しません");
			$('*[name=re_password]')[0].reportValidity();
			return false
		}
	*/
		return true;
	}
}

let userDetail = new UserDetail();