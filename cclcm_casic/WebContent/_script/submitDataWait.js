var progressEnd = 20; // set to number of progress <span>'s.
var progressColor = 'blue'; // set to progress bar color
var progressInterval = 300; // set to time between updates (milli-seconds)

var progressAt = progressEnd;
var progressTimer;
var WAITING_INFO_BAR_NAME = "WAITING_INFO_SPAN"; //进度条表格的名称
var WAITING_PROGRESS_ID_PREFIX = "WAITING_PROGRESS_"; //进度方块的ID前缀, 一共progressEnd个
var WAITING_INFO_TEXT = "正在处理数据, 请稍候...";

function waitAndGo(delay) {
//	var waitingInfo = document.getElementById(WAITING_INFO_BAR_NAME);
//
//	if( waitingInfo == null) {
		createWaitingBar();
//	} else {
//		waitingInfo.style.display = ""; //show the ProgressBar
//	}

	progress_update(); //begin the progressbar
//	if(wantHref) {
//		if(delay) {
//			window.setTimeout("location.href=\"" + wantHref + "\"", delay);
//		} else {
//			location.href=wantHref; //去掉这一行的注释即可
//		}
//	}
}

function progress_clear() {
	for (var i = 1; i <= progressEnd; i++)
		document.getElementById(WAITING_PROGRESS_ID_PREFIX + i).style.backgroundColor = 'transparent';
	progressAt = 0;
}

function createWaitingBar() {
	var spanObj = document.createElement("SPAN");
	spanObj.id = WAITING_INFO_BAR_NAME;
	var htmlCode = "<TABLE align=\"center\" id=\"waitTable\" style=\"display:none\"><TBODY><TR><TD style=\"font-size:9pt;text-align:center\">";
	htmlCode += WAITING_INFO_TEXT;
	htmlCode += "<DIV style=\"BORDER: black 1px solid; PADDING: 2px; FONT-SIZE: 2pt\">";
	for(var i=1; i<=progressEnd; i++) {
		htmlCode += "<SPAN id=\"" + WAITING_PROGRESS_ID_PREFIX + i + "\" style='background-color:red'>&nbsp; &nbsp;</SPAN> ";
	}
	htmlCode += "</DIV> </TD></TR></TBODY></TABLE>";
	spanObj.innerHTML = htmlCode;
	if(document.body) {
		document.body.appendChild(spanObj);
	}
}

function progress_update() {
	progressAt++;
	if (progressAt > progressEnd)
		progress_clear();
	else
		document.getElementById(WAITING_PROGRESS_ID_PREFIX + progressAt).style.backgroundColor = progressColor;
	progressTimer = setTimeout('progress_update()',progressInterval);
}

function progress_stop() {
	clearTimeout(progressTimer);
	progress_clear();
}


