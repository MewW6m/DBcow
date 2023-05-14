let token = $("meta[name='_csrf']").attr("content");
let header = $("meta[name='_csrf_header']").attr("content");

$(document).ajaxSend(function (e, xhr, options) {
	xhr.setRequestHeader(header, token);
});

$(document).on('click', '#submitBtn1', function () {
	let param = {};
	param.username = getPathParam(null);
	getUserDetail(param);
});

$(document).on('click', '#submitBtn2', function () {

	if (!checkValidate()) return;

	let param = {};
	param.username = "user2";
	param.password = "password";
	param.roles = "ROLE_ADMIN";

	patchUserDetail(param);
});

$(document).on('click', '#submitBtn3', function () {
	let param = {};
	param.username = getPathParam(null);
	deleteUserDetail(param);
});

function getUserDetail(param) {
    $.ajax({
        type : "GET",
		url: userDetailPath,
		data: param,
		dataType: "json",
	}).done(function (data, status, xhr) {
		console.log(data);
	}).fail(function (jqXHR, textStatus, errorThrown) {
		console.log(jqXHR.responseText);
		showErrorAlertMsg("ユーザー取得が失敗しました。\n" + JSON.parse(jqXHR.responseText).content);
        //location.href = loginPath + "#infoMsg=ログインしてください"
	});
}

function patchUserDetail(param) {
    $.ajax({
        type : "PATCH",
		url: userDetailPath,
		data: JSON.stringify(param),
		dataType: "json",
		contentType: "application/json",
	}).done(function (data, status, xhr) {
		console.log(data);
	}).fail(function (jqXHR, textStatus, errorThrown) {
		console.log(jqXHR.responseText);
		showErrorAlertMsg("ユーザー更新が失敗しました。\n" + JSON.parse(jqXHR.responseText).content);
        //location.href = loginPath + "#infoMsg=ログインしてください"
	});
}

function deleteUserDetail(param) {
    $.ajax({
        type : "DELETE",
		url: userDetailPath,
		data: param,
		dataType: "json",
	}).done(function (data, status, xhr) {
		console.log(data);
	}).fail(function (jqXHR, textStatus, errorThrown) {
		console.log(jqXHR.responseText);
		showErrorAlertMsg("ユーザー削除が失敗しました。\n" + JSON.parse(jqXHR.responseText).content);
        //location.href = loginPath + "#infoMsg=ログインしてください"
	});
}

function checkValidate() {/*
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