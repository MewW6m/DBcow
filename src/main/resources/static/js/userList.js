let token = $("meta[name='_csrf']").attr("content");
let header = $("meta[name='_csrf_header']").attr("content");
const listApi = new ListApi();
const search = new Search();
const listHeader = new ListHeader();
const listBody = new ListBody();
const aside = new Aside();
const footer = new Footer();

$(document).ajaxSend(function (e, xhr, options) {
    xhr.setRequestHeader(header, token);
});


$(window).on('load', function () {
    let param = {};
    getUserList(param);
});


// 初期表示時
$(document).on("DOMContentLoaded", async function (e) {
    await commonSearchLogic();
    footer.updatePageSelectChoice();
});

// 検索ボタンを押下したとき
$(document).on("click", '#searchBtn', async function (e) {
    search.updateSearch();
    footer.moveFirstPage();
    await commonSearchLogic();
    footer.updatePageSelectChoice();
});

// ソートボタンを押下したとき
$(document).on('click', '#listSection thead th', async function (e) {
    listHeader.updateArrow(e.currentTarget);
    await commonSearchLogic();
});

// 一覧行を押下したとき
$(document).on('click', '#listSection tbody tr', async function (e) {
    listBody.updateLineColor(e.currentTarget);
    aside.update(e.currentTarget);
    aside.open();
});

// 最初ページボタンを押下したとき
$(document).on('click', '#firstPageBtn', async function (e) {
    footer.moveFirstPage();
    await commonSearchLogic();
});

// 前ページボタンを押下したとき
$(document).on('click', '#beforePageBtn', async function (e) {
    footer.moveBeforePage();
    await commonSearchLogic();
});

// ページセレクトボックスを切り替えしたとき
$(document).on('change', '#pageSelect', async function (e) {
    footer.updatePageSelect(Number($('#pageSelect').val()));
    await commonSearchLogic();
});

// 後ページボタンを押下したとき
$(document).on('click', '#afterPageBtn', async function (e) {
    footer.moveAfterPage();
    await commonSearchLogic();
});

// 最後ページボタンを押下したとき
$(document).on('click', '#lastPageBtn', async function (e) {
    footer.moveLastPage();
    await commonSearchLogic();
});

// 閉じるボタンを押下したとき
$(document).on('click', '#closeBtn', async function (e) {
    listBody.removeLineColor();
    aside.close()
});

// 共通の検索ロジック
async function commonSearchLogic() {
    aside.close();
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
    footer.updateTotalCount(listApi.result.total);
}

function getUserList(param) {
    $.ajax({
        type: "GET",
        url: userListPath,
        data: param,
        dataType: "json",
    }).done(function (data, status, xhr) {
        console.log(data);
        try {
            ;
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
            showErrorAlertMsg("ユーザー一覧取得が失敗しました。");
        }
    }).fail(function (jqXHR, textStatus, errorThrown) {
        console.error(jqXHR.responseText);
        showErrorAlertMsg("ユーザー一覧取得が失敗しました。\n" + JSON.parse(jqXHR.responseText).content);
        // ↓ログインしてないとき
        //location.href = loginPath + "#infoMsg=ログインしてください"
    });
}
