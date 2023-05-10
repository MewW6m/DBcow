let token = $("meta[name='_csrf']").attr("content");
let header = $("meta[name='_csrf_header']").attr("content");

$(document).ajaxSend(function (e, xhr, options) {
	xhr.setRequestHeader(header, token);
});

$(document).on('click', '#submitBtn', function () {

	if (!checkValidate()) return;

	let param = {};
	param.username = $('*[name=username]').val();
	param.password = $('*[name=password]').val();

	$.ajax({
		type: "POST",
		url: postUserPath,
		data: JSON.stringify(param),
		dataType: "json",
		contentType: "application/json",
	}).done(function (data, status, xhr) {
		console.log(data);
		location.href = loginPath + "#infoMsg=新規登録が成功しました"
	}).fail(function (jqXHR, textStatus, errorThrown) {
		console.log(jqXHR.responseText);
		showErrorAlertMsg("新規登録が失敗しました。\n" + JSON.parse(jqXHR.responseText).message);
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