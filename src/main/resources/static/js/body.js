const messageType = ['#infoMsg', '#successMsg', '#warnMsg', '#errorMsg']

$(window).on('load', function () {
	showFirstAlertMsg();
	$('nav ul').each(function(i,elm) {UIkit.nav($(elm)).toggle(0, false);});
	$('nav ul').each(function(i,elm) {UIkit.nav($(elm)).toggle(0, false);});
});

function showAlertMsg(key, message) {
	if (messageType.includes(key)) {
		document.querySelector(key + " p").innerText = message;
		document.querySelector(key).removeAttribute("hidden");
	}
}

function resetAlertMsg() {
	for (var key of messageType) {
		document.querySelector(key + " p").innerText = "";
		document.querySelector(key).setAttribute("hidden", true);
	}
}

function showInfoAlertMsg(message) {
	resetAlertMsg();
	showAlertMsg("#infoMsg", message);
}

function showSuccessAlertMsg(message) {
	resetAlertMsg();
	showAlertMsg("#successMsg", message);
}

function showWarnAlertMsg(message) {
	resetAlertMsg();
	showAlertMsg("#warnMsg", message);
}

function showErrorAlertMsg(message) {
	resetAlertMsg();
	showAlertMsg("#errorMsg", message);
}
	
function showFirstAlertMsg() {
	resetAlertMsg();

	const searchParams = new URLSearchParams(window.location.hash)
	for (var key of searchParams.keys()) {
		if (messageType.includes(key)) {
			document.querySelector(key + " p").innerText = searchParams.get(key);
			document.querySelector(key).removeAttribute("hidden");
		}
	}
}

function banDoubleClick(elm) {
	$(elm).addClass("uk-disabled");
	$(elm).attr("disabled");
	setTimeout(function(){
		$(elm).removeClass("uk-disabled");
		$(elm).removeAttr("disabled");
		
    },3000);
}

/**
 * Get the URL parameter value
 *
 * @param  name {string} パラメータのキー文字列
 * @return  url {url} 対象のURL文字列（任意）
 */
function getQueryParam(name, url) {
    if (!url) url = window.location.href;
    name = name.replace(/[\[\]]/g, "\\$&");
    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, " "));
}

function getPathParam(url) {
	if (!url) url = window.location.href;
	return location.href.substring(location.href.lastIndexOf('/') + 1);
}