let token = $("meta[name='_csrf']").attr("content");
let header = $("meta[name='_csrf_header']").attr("content");

$(document).ajaxSend(function (e, xhr, options) {
    xhr.setRequestHeader(header, token);
});



$(document).on('click', '#submitBtn', function() {

	let param = {};
	param.username = $('*[name=username]').val();
	param.password = $('*[name=password]').val();
	//param.re_password = $('*[name=re_password]').val();
	
	$.ajax({
            type: "POST",
            url: postUserPath,
	        data: JSON.stringify(param),
	        dataType : "json",
            contentType: "application/json",
	}).done(function(data, status, xhr) {
	    console.log(data);
	    location.href = loginPath + "?infoMsg=新規登録が成功しました"
	}).fail(function(jqXHR, textStatus, errorThrown) {
	    console.log(jqXHR.responseText);
	    showErrorAlertMsg("新規登録が失敗しました。\n" + JSON.parse(jqXHR.responseText).message);
	});
});