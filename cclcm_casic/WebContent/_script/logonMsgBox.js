function MM_displayStatusMsg(msgStr) {
    status = msgStr;
    document.MM_returnValue = true;
}

function highlight(x) {
    document.forms[x].elements[0].focus()
    document.forms[x].elements[0].select()
}

function MM_jumpMenu(targ, selObj, restore) {
    eval(targ + ".location='" + selObj.options[selObj.selectedIndex].value + "'");
    if (restore) selObj.selectedIndex = 0;
}

var NS
IE = document.all;
NS = document.layers;

hdrFontFamily = "黑体";
hdrFontSize = "2";
hdrFontColor = "#ffffff";
hdrBGColor = "#4798e3";
textFontFamily = "Verdana";
textFontSize = "2";
textFontColor = "black";
textBGColor = "white";
linkFontFamily = "Verdana";
linkFontSize = "2";
linkBGColor = "white";
linkOverBGColor = "#CCCCCC";
linkTarget = "_top";
YOffset = 120;
staticYOffset = 22;
menuBGColor = "black";
menuIsStatic = "no";
menuHeader = "登录信息"
menuWidth = 200;
// Must be a multiple of 5!
staticMode = "advanced"
barBGColor = "#1298fd";
barFontFamily = "Verdana";
barFontSize = "2";
barFontColor = "#00225b";
barText = "快捷功能";

var moving1, moving2, leftPos;

function moveOut() {
	_ssm2 = document.getElementById("ssm2");
    if (window.cancel) {
        cancel = "";
    }

    if (window.moving2) {
        clearTimeout(moving2);
        moving2 = "";
    }
    if ((IE && _ssm2.style.pixelLeft >= leftPos - menuWidth) || (NS && _ssm2.left >= leftPos - menuWidth)) {
        if (IE) {
            _ssm2.style.pixelLeft -= (5 % menuWidth);
        }
        if (NS) {
            _ssm2.left -= (5 % menuWidth);
        }
        moving1 = setTimeout('moveOut()', 5)
    }
    else {
        clearTimeout(moving1)
    }
}

function moveBack() {
    cancel = moveBack1()
}

function moveBack1() {
    if (moving1) {
        clearTimeout(moving1);
    }

	_ssm2 = document.getElementById("ssm2");
    if ((IE && _ssm2.style.pixelLeft < leftPos) || (NS && _ssm2.left > leftPos)) {
        if (IE) {
            _ssm2.style.pixelLeft += (5 % menuWidth);
        }
        if (NS) {
            _ssm2.left += (5 % menuWidth);
        }
        moving2 = setTimeout('moveBack1()', 5);
    }
    else {
        clearTimeout(moving2);
    }
}

lastY = 0;
function makeStatic(mode) {
	_ssm2 = document.getElementById("ssm2");
    if (IE) {
        winY = document.body.scrollTop;
        var NM = _ssm2.style;
    }
    if (NS) {
        winY = window.pageYOffset;
        var NM = _ssm2;
    }
    if (mode == "smooth") {
        if ((IE || NS) && winY != lastY) {
            smooth = .2 * (winY - lastY);
            if (smooth > 0) smooth = Math.ceil(smooth);
            else smooth = Math.floor(smooth);
            if (IE) NM.pixelTop += smooth;
            if (NS) NM.top += smooth;
            lastY = lastY + smooth;
        }
        setTimeout('makeStatic("smooth")', 1)
    }

    else if (mode == "advanced") {
        if ((IE || NS) && winY > YOffset - staticYOffset) {
            if (IE) {
                NM.pixelTop = winY + staticYOffset;
            }
            if (NS) {
                NM.top = winY + staticYOffset;
            }
        }
        else {
            if (IE) {
                NM.pixelTop = YOffset;
            }
            if (NS) {
                NM.top = YOffset - 7;
            }
        }
        setTimeout('makeStatic("advanced")', 1);
    }
}

//初始，要放在body的onload中被调用
function init() {
    content = "";
    if (IE) {
        content += '<DIV ID="ssm2" style="visibility:hidden;Position : Absolute ;Left : 0px ;Top : ' + YOffset + 'px ;Z-Index : 20;width:1px" onmouseover="moveOut()" onmouseout="moveBack()">';
    }
    if (NS) {
        content += '<LAYER visibility="hide" top="' + YOffset + '" name="ssm2" bgcolor="' + menuBGColor + '" left="0" onmouseover="moveOut()" onmouseout="moveBack()">';
    }
    tempBar = "";

    for (i = 0; i < barText.length; i++) {
        tempBar += barText.substring(i, i + 1) + "<BR>";
    }

    content += '<table id="_menuTable" border="0" cellpadding="0" cellspacing="1" width="' + (menuWidth + 22 + 2) + '" bgcolor="' + menuBGColor + '" bordercolor="#2b61a9"><tr><td align="center" valign="middle" rowspan="100" width="22" background="_image/slipMsgBoxLeftBg.gif"><span style="color:' + barFontColor + ';font-size:12px;font-weight:bold;width:20px;filter:DropShadow(Color=#ffffff, OffX=0,OffY=1,Positive=2);line-height:16px;">' + tempBar + '</span></TD><td  valign="middle" align="center" bgcolor="' + hdrBGColor + '" WIDTH="' + menuWidth + '"><table><tr><td><IMG SRC="_image/slipMsgBoxIcon.gif" width="20" height="22"  BORDER="0" ALT="" /></td><td><span style="color:' + hdrFontColor + ';font-size: 13px;font-weight:bold;font-family:黑体;width:' + menuWidth + 'px;filter:DropShadow(Color=#000000, OffX=0, OffY=1, Positive=2);">' + menuHeader + '</span></td></tr></table></td></tr>';


// and HERE! No more!

    content += '<tr><td bgcolor="#b2d9fd"><font size="0" face="Arial">&nbsp; </font></td></TR></table>';
    if (IE) {
       content += '</DIV>';
    }
    if (NS) {
        content += '</LAYER>';
    }
    if ((IE || NS) && (menuIsStatic == "yes" && staticMode)) {
        makeStatic(staticMode);
    }

    if (IE) {
       frameDIV = document.createElement("DIV");
    }
    if (NS) {
       frameDIV = document.createElement("LAYER");
    }
    frameDIV.insertAdjacentHTML("afterBegin", content);

    document.body.appendChild(frameDIV);
    position();
}

//浮动框定位
function position(){
	_ssm2 = document.getElementById("ssm2");

    windowWidth = document.body.clientWidth;
	leftPos = windowWidth - 22;
    if (IE) {
        _ssm2.style.pixelLeft = leftPos;
        _ssm2.style.visibility = "visible";
    }
    else if (NS) {
        _ssm2.left = leftPos;
        _ssm2.visibility = "show";
    }
    else {
        alert('Choose either the "smooth" or "advanced" static modes!');
    }
}



//新增一个超链接项
function addNavItem(text, link, target) {
    if (!target) {
        target = linkTarget;
    }

	mTable = document.getElementById("_menuTable");
	rowNum = mTable.rows.length;
    tempRow = mTable.insertRow(rowNum - 1);
    tempCell = tempRow.insertCell();
	tempCell.height = "22px";
	tempCell.vAlign = "middle";
	tempCell.style.backgroundColor = linkBGColor;
	tempCell.insertAdjacentHTML("afterBegin", '<div width="100%" height="100%" style="borderColor:red;borderWidth:2px" onmouseover="this.style.borderWidth=\"2px\";" onmouseout="this.style.borderWidth=\"0px\""><ILAYER><LAYER onmouseover="bgColor=\'' + linkOverBGColor + '\'" onmouseout="bgColor=\'' + linkBGColor + '\'" WIDTH="100%"><FONT face="' + linkFontFamily + '" Size="' + linkFontSize + '"> <A HREF="' + link + '" target="' + target + '" CLASS="ssm2Items">&nbsp;' + text + '</A></FONT></LAYER></ILAYER></div>');
}

//新增一个标题项
function addNavHdr(text) {
	mTable = document.getElementById("_menuTable");
	rowNum = mTable.rows.length;
    tempRow = mTable.insertRow(rowNum - 1);
    tempCell = tempRow.insertCell();
	tempCell.height = "24px";
	tempCell.vAlign = "middle";
	tempCell.style.backgroundColor = hdrBGColor;
	tempCell.style.color = hdrFontColor;
	tempCell.insertAdjacentHTML("afterBegin", '<span style="font-size: 13px;font-weight:bold;font-family:' + hdrFontFamily + ';width:' + menuWidth + 'px;filter:DropShadow(Color=#000000, OffX=0, OffY=1, Positive=2);">&nbsp;' + text + '</span>');
}

//新增一个普通文本项
function addNavText(text) {
    mTable = document.getElementById("_menuTable");
	rowNum = mTable.rows.length;
    tempRow = mTable.insertRow(rowNum - 1);
    tempCell = tempRow.insertCell();
	tempCell.height = "22px";
	tempCell.style.backgroundColor = textBGColor;
	tempCell.style.color = textFontColor;
	tempCell.insertAdjacentHTML("afterBegin", '<FONT face="' + textFontFamily + '" Size="' + textFontSize + '">&nbsp;' + text + '</FONT>');
}

//window.onload = init