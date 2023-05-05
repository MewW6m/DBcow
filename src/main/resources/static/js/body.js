const messageType = ['#infoMsg', '#successMsg', '#warnMsg', '#errorMsg']

showFirstAlertMsg();

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