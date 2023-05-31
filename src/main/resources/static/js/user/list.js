import { api } from "../component/api.js";
import { common } from "../component/common.js";
import { listBody } from "../component/listBody.js";
import { listHeader } from "../component/listHeader.js";
import { search } from "../component/search.js";

$(window).on('load', function () {
    let param = {};
	api.getUserList(param).done((data) => {
		try {
        	data.content.forEach(function (user, i) {
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
        	common.showErrorAlertMsg("ユーザー一覧取得が失敗しました。");
        }
	}).fail((jqXHR, textStatus, errorThrown) => {
        common.showErrorAlertMsg("ユーザー一覧取得が失敗しました。\n" + JSON.parse(jqXHR.responseText).content);
        // ↓ログインしてないとき
        //location.href = loginPath + "#infoMsg=ログインしてください"
    });
});


// 初期表示時
$(document).on("DOMContentLoaded", async function (e) {
    await commonSearchLogic();
});

// 検索ボタンを押下したとき
$(document).on("click", '#searchBtn', async function (e) {
    search.updateSearch();
    await commonSearchLogic();
});

$(document).on("click", '#plusOneRow', async function (e) {
    search.showSearchOneRow();
});

// ソートボタンを押下したとき
$(document).on('click', '#listSection thead th', async function (e) {
    listHeader.updateArrow(e.currentTarget);
    await commonSearchLogic();
});

// 閉じるボタンを押下したとき
$(document).on('click', '#closeBtn', async function (e) {
    listBody.removeLineColor();
});

// 共通の検索ロジック
async function commonSearchLogic() {
    await listApi.fireApi(
        search.itemCode,
        search.itemName,
        search.status,
        search.registUser,
        search.updateUser,
        listHeader.sort,
        listHeader.order,
        footer.page
    );
    listBody.updateLines(listApi.result.zaikoList);
}