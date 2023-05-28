import { common } from "./component/common.js";

let token = $("meta[name='_csrf']").attr("content");
let header = $("meta[name='_csrf_header']").attr("content");

$(document).ajaxSend(function (e, xhr, options) {
	xhr.setRequestHeader(header, token);
});

$(window).on('load', function () {
	common.showFirstAlertMsg();
	$('nav ul').each(function(i,elm) {UIkit.nav($(elm)).toggle(0, false);});
	$('nav ul').each(function(i,elm) {UIkit.nav($(elm)).toggle(0, false);});
});