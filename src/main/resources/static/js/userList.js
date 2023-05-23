let token = $("meta[name='_csrf']").attr("content");
let header = $("meta[name='_csrf_header']").attr("content");

$(document).ajaxSend(function (e, xhr, options) {
	xhr.setRequestHeader(header, token);
});
$(window).on('load', function () {
	let param = {};
	getUserList(param);
});

function getUserList(param) {
    $.ajax({
        type : "GET",
		url: userListPath,
		data: param,
		dataType: "json",
	}).done(function (data, status, xhr) {
		console.log(data);
        try {;
            data.content.forEach(function(user,i) {
                var tr = $('<tr />');
                var userlink = !user.username ? userListScPath : userDetailScLinkPath + user.username;
                tr.append($('<td />').text(user.username));
                tr.append($('<td />').text(user.roles === 'ROLE_ADMIN' ? '管理者ユーザー' : user.roles === 'ROLE_USER' ? '一般ユーザー' : ''));
                tr.append($('<td />').text(user.deleteFlag === true ? '無効' : '有効'));
                tr.append($('<td />').text(user.createDate));
                tr.append($('<td />').text(user.updateDate));
                tr.append($('<td />').html('<a href="' + userlink + '">詳細</a>'));
                $('#userListTable').append(tr);
            });
        } catch (e) {
            console.error(e);
            showErrorAlertMsg("ユーザー一覧取得が失敗しました。");
        }
	}).fail(function (jqXHR, textStatus, errorThrown) {
		console.error(jqXHR.responseText);
		showErrorAlertMsg("ユーザー一覧取得が失敗しました。\n" + JSON.parse(jqXHR.responseText).content);
		// ↓ログインしてないとき
        //location.href = loginPath + "#infoMsg=ログインしてください"
	});
}
