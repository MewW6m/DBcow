import { api } from "../component/api.js";
import { common } from "../component/common.js";
$(document).on('click', '#submitBtn', function () {

	if (!checkValidate()) return;

	let param = {};
	param.username = $('*[name=username]').val();
	param.password = $('*[name=password]').val();

	api.postUserDetail(param).done((data) => {
		location.href = loginPath + "#infoMsg=新規登録が成功しました";
	}).fail((jqXHR, textStatus, errorThrown) => {
		common.showErrorAlertMsg("新規登録が失敗しました。\n" + JSON.parse(jqXHR.responseText).content);
    });
});

function checkValidate() {
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

	return true;
}