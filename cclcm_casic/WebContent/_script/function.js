function winLocation(URL) {
    window.location = URL;
}

function winOpen(strURL, strName, width, height) {
    theWindow = window.open(strURL, strName, "width=" + width + " height=" + height + " scrollbars=yes left=" + (1024 - width) / 2 + " top=" + (768 - height) / 2);
    if (theWindow.opener == null) theWindow.opener = window;
    if (window.focus) theWindow.focus();
}

function winOpen2(strURL, strName, width, height) {
    theWindow = window.open(strURL, strName, "width=" + width + " height=" + height + " scrollbars=yes left=" + (1024 - width) / 2 + " top=" + 0);
    if (theWindow.opener == null) theWindow.opener = window;
    if (window.focus) theWindow.focus();
}

/*******************************************************************************
 * *** 判断是否为日期数据 (lhm) *****
 ******************************************************************************/
function itIsDate(DateString, Dilimeter) {
    if (DateString == null) return false;
    if (Dilimeter == '' || Dilimeter == null)
        Dilimeter = '-';
    var tempy = '';
    var tempm = '';
    var tempd = '';
    var tempArray;
    if (DateString.length < 8 && DateString.length > 10)
        return false;
    tempArray = DateString.split(Dilimeter);
    if (tempArray.length != 3)
        return false;
    if (tempArray[0].length == 4) {
        tempy = tempArray[0];
        tempd = tempArray[2];
    } else {
        tempy = tempArray[2];
        tempd = tempArray[1];
    }
    tempm = tempArray[1];
    var tDateString = tempy + '/' + tempm + '/' + tempd + ' 8:0:0';
    // 加八小时是因为我们处于东八区
    var tempDate = new Date(tDateString);
    if (isNaN(tempDate))
        return false;
    if (((tempDate.getUTCFullYear()).toString() == tempy) && (tempDate.getMonth() == parseInt(tempm) - 1) && (tempDate.getDate() == parseInt(tempd))) {
        return true;
    } else {
        return false;
    }
}

/*******************************************************************************
 * *** 求字符串的字节长度 (lhm) *****
 ******************************************************************************/
function byteLength(paraString) {
    var strValue = new String(paraString);
    var strLength = strValue.length;
    var numLength = 0;
    for (globle_i = 0; globle_i < strLength; globle_i++) {
        var ASCIIValue = strValue.charCodeAt(globle_i);
        if (ASCIIValue > 0 && ASCIIValue < 127)
            numLength = numLength + 1; else
            numLength = numLength + 2;
    }
    return numLength;
}

/*******************************************************************************
 * *** 提示匡 (lhm) ***** '// strMsg：输入的题示语言 '//返回值：1，2（1：确定 2：取消）
 ******************************************************************************/
function msg(strMsg) {
    if (confirm('\n' + strMsg))
        return 1;

    else
        return 2;
}

/*******************************************************************************
 * *** 去除空格 (lhm) *****
 ******************************************************************************/
function trim(inputStr) {
    var result = inputStr;
    while (result.substring(0, 1) == " ") {
        result = result.substring(1, result.length)
    }

    while (result.substring(result.length - 1, result.length) == " ") {
        result = result.substring(0, result.length - 1)
    }

    return result;
}
/*******************************************************************************
 * *** 分页 处理 (lmg) *****
 ******************************************************************************/

function go_btn(form) {
    var strwhichPage = form.whichPage.value;
    var strpageSize = form.pageSize.value;
    var strToint = parseInt(strwhichPage);
    var recordCount = form.recordCount.value;
    var strpageCount = recordCount / form.pageSize.value;
    var pageSize = parseInt(strpageSize);
    returnValue = true;

    if (isNaN(strwhichPage) == true || isNaN(strpageSize) == true || strwhichPage.indexOf(".") != -1 || strpageSize.indexOf(".") != -1) {
        alert("请输入有效的整数");
        if (isNaN(strwhichPage) == true) {
            form.whichPage.select();
        }
        if (isNaN(strpageSize) == true) {
            form.pageSize.select();
        }
        returnValue = false;
    } else {
        if (pageSize > recordCount || pageSize < 1) {
            alert("每页显示的记录个数应在1到" + recordCount + "之间");
            form.pageSize.select();
            returnValue = false;
        }

        if ((strpageCount - parseInt(strpageCount)) > 0) {
            strpageCount = strpageCount + 1;
        }

        var intMaxpage = parseInt(strpageCount);
        if (strToint < 1 || strToint > intMaxpage) {
            alert("现在的输入页数是：" + strToint + ",超出了范围");
            form.whichPage.select();
            returnValue = false;
        }
    }
}

/*******************************************************************************
 * *** 分页 处理2 (lmg) *****
 ******************************************************************************/

function go_fenye(form, whichPage) {
    form.whichPage.value = whichPage;
    form.submit();

}

/*******************************************************************************
 * *** 排序表头 (lmg) ****** 过程的使用说明： 在使用本过程时，需要在ASP脚本中使用三个隐含字段一传递参数，比如 下面所示： <input
 * type="hidden" name=strKey value="<%=request.form("strKey")%>"> …… 参数说明：
 * form：为对象，形式为 document.formNmae(formName)是表单的名字 key：字符串，是数据表中的字段，即这里要使用的健
 * image： 字符串，反映当前显示的方向图标
 ******************************************************************************/
function sortKey(form, fieldName, imageName)

{

    form.whichPage.value = 1;
    form.sortField.value = fieldName;
    if (document.images.item(imageName)) {
        strImage = document.images.item(imageName).src;

        if (strImage.indexOf("up") != -1) {
            form.strImage.value = "../images/arrow_down.gif";
            form.Direction.value = "desc";
        } else {
            form.strImage.value = "../images/arrow_up.gif";
            form.Direction.value = "asc";
        }
        form.submit();
    } else {
        form.strImage.value = "../images/arrow_down.gif";
        form.Direction.value = "desc";
        // form.action = "index.asp"
        form.target = "";
        form.submit();
    }
}

/*******************************************************************************
 * *** 排序事件处理 (lmg) *****
 ******************************************************************************/

function changeStyle(varStyle) {
    // alert(varStyle.style);

    if (varStyle.style.color == '#ffffff') {
        varStyle.style.color = '#000000';
    } else {
        varStyle.style.color = '#FFFFFF';
    }
}

/*******************************************************************************
 * *** 复选框的全选与取消 (lmg) *****
 ******************************************************************************/

function CheckAll(form) {

    o = document.getElementsByName("delCopID")
    for (i = 0; i < o.length; i++)
        o[i].checked = event.srcElement.checked

}

/*******************************************************************************
 * *** 删除处理 (lmg) *****
 ******************************************************************************/

function del_btn(form, strMsg) {

    var string = "00";
    var length = form.delCopID.length;
    if (form.delCopID.checked) { // 只有一条记录时执行此语句

        string = "1";

    }
    for (var i = 0; i < length; i++) {
        if (form.delCopID[i].checked) {
            string = "1";

        }

    }
    if (string == "00") {
        alert("没有选择任何项目!");
        return false;
    } else {
        if (msg(strMsg) == 2) return false; else {
            return true;
        }
    }

}

/*******************************************************************************
 * *** 提交处理 (lmg) *****
 ******************************************************************************/
function submit_ydh(form, strMsg) {

    if (msg(strMsg) == 2) return false; else {
        return true;
    }

}
/*******************************************************************************
 * *** 表单提交处理 (lmg) *****
 ******************************************************************************/
// ////////////////////////////////////////////////////////////////
// 过程名：formSubmit(objForm,strUrl,strActionType)
// 描 述：
// 参 数：
// 作 者：李明刚
// ////////////////////////////////////////////////////////////////
function formSubmit(objForm, strUrl, strActionType) {
    objForm.action = strUrl;
    objForm.actionType.value = strActionType;
    // alert (objForm.actionType.value);
    objForm.submit();
}

/*******************************************************************************
 * *** 项目编辑处理 (lmg) *****
 ******************************************************************************/

function itemEdit(objForm, strUrl, intItemId) {
    objForm.actionType.value = "_blank";
    objForm.action = strUrl;
    objForm.itemId.value = intItemId;
    objForm.submit();
}

/*******************************************************************************
 * *** 项目提交处理 (lmg) *****
 ******************************************************************************/

function itemSubmit(objForm, strUrl, actionType, intItemId) {
    objForm.actionType.value = actionType;
    objForm.action = strUrl;
    objForm.itemId.value = intItemId;
    objForm.submit();
}

/*******************************************************************************
 * *** 项目链接处理 (lmg) *****
 ******************************************************************************/

function itemLink(objForm, strUrl, intItemId, strItemCode) {
    objForm.actionType.value = "_blank";
    // objForm.enctype="";
    objForm.action = strUrl;
    objForm.n_parent_id.value = intItemId;
    objForm.itemName.value = strItemCode;
    objForm.submit();
}

/*******************************************************************************
 * *** 项目链接处理2 (lmg) *****
 ******************************************************************************/
function gotoLink(objForm, strUrl, parentId, itemName) {

    objForm.action = strUrl;
    objForm.actionType.value = "_blank";
    objForm.n_parent_id.value = parentId;
    objForm.itemName.value = itemName;
    // alert(objForm.strSql.value);
    // objForm.target = "mainframe";
    objForm.submit();
}

/*******************************************************************************
 * *** 转化字符串 (lmg) *****
 ******************************************************************************/

function conversion_code(paraString) {
    strResult = "";
    j = 0;
    for (i = 0; i < paraString.length; i++) {
        Char = String1.charAt(i);
        if (Char == "'") {
            strResult = strResult + paraString.substring(j, i) + "\\" + "\'";
            j = i + 1;
        }
        return strResult;
    }
}

/*******************************************************************************
 * *** 数字输入控制处理 (lmg) *****
 ******************************************************************************/
function InputIntNumberCheck() {
    if (!((window.event.keyCode >= 48) && (window.event.keyCode <= 57))) {
        window.event.keyCode = 0;
    }
}

/*******************************************************************************
 * *** 项目打开处理 (lmg) *****
 ******************************************************************************/

function open_resizeble_dialog(rul, name, width, height) {
    var left = (screen.width - width) / 2;
    var top = (screen.height - height) / 2;
    window.open(rul, name, "titlebar=no,menubar=no,status=no,directories=no,scrollbars=no,resizable=yes,left=" + left + ",top=" + top + ",width=" + width + ",height=" + height);
}

/*******************************************************************************
 * *** 数据校验处理 (lmg) *****
 ******************************************************************************/
function verifyData(objField, strName, strFormat, maxLength, minLength) {
    returnValue = true;
    if (minLength != 0) {
        if (trim(objField.value) == "") {
            alert(strName + " 为必添项，请输入！");
            objField.focus();
            return returnValue = false;
        }
    }

    if (minLength > 1) {
        if (byteLength(objField.value) < minLength) {
            alert(strName + " 的长度在" + minLength + "和" + maxLength + "位之间，请确认！");
            objField.focus();
            return returnValue = false;
        }
    }

    if (byteLength(objField.value) > maxLength) {
        msgs = " 最多为" + maxLength + " 个字符，请修改！"
        msgs += "\n\n   注意：1个汉字相当于2个字符"
        alert("\n" + strName + " " + msgs);
        objField.focus();
        return returnValue = false;
    }
    if (objField.value != "" && strFormat == "number") {
        if (isNaN(objField.value) == true) {
            alert(strName + " 为有效数字，请确认！");
            objField.focus();
            return returnValue = false;
        }
    }
    if (objField.value != "" && strFormat == "mail") {
        if (objField.value.indexOf("@") == -1 || objField.value.indexOf(".") == -1) {
            alert(strName + "中邮件地址不合法，请确认！");
            objField.focus();
            return returnValue = false;
        }
    }
    if (objField.value != "" && strFormat == "char") {

        if (objField.value.length != byteLength(objField.value)) {
            alert(strName + "中有非法字符，请确认！");
            objField.select();
            objField.focus();
            return returnValue = false;
        }

    }
    if (objField.value != "" && strFormat == "chinese") {

        if (objField.value.length * 2 != byteLength(objField.value)) {
            alert(strName + "中有非中文法字符，请确认！");
            objField.select();
            objField.focus();
            return returnValue = false;
        }
    }
    if (objField.value != "" && strFormat == "dateYear") {

        if (isNaN(objField.value) == true || objField.value < 1900) {
            alert(strName + "中的年份值有误，请确认！");
            objField.select();
            objField.focus();
            return returnValue = false;
        }
    }
    if (objField.value != "" && strFormat == "phone") {

        if (objField.value.indexOf("-") == -1) {
            alert(strName + "您输入的电话号码有误，请确认！");
            objField.select();
            objField.focus();
            return returnValue = false;
        }
    }

    if (objField.value != "" && strFormat == "date") {

        if (itIsDate(objField.value) == false) {
            alert(strName + " 的日期格式有误，请确认！\n\n  建议格式：yyyy-mm-dd");
            objField.select();
            objField.focus();
            return returnValue = false;
        }
    }

    if (strFormat == "select") {

        if (objField.value == "") {
            alert(strName + " 项为必填项，请选择！");
            objField.focus();
            return returnValue = false;
        }
    }
    return returnValue;
}

/*******************************************************************************
 * *** 转化成html *****
 ******************************************************************************/
function copyWord(objFile) {
    finish = IClipProc.Transform();
    if (finish == 0) {
        htmlPath = IClipProc.HtmlPath
        zipPath = IClipProc.ZipPath;
        strResult = "";
        j = 0;
        for (i = 0; i < zipPath.length; i++) {
            Char = zipPath.charAt(i);
            if (Char == "~") {
                strResult = strResult + "\{" + "\~}";
                j = i + 1;
            } else {
                strResult = strResult + Char
            }
        }
        // alert (strResult);
        document.all("iFrame1").src = htmlPath;
        var WshShell = new ActiveXObject("WScript.Shell");
        objFile.select();
        WshShell.sendKeys("" + strResult + "");
    }
}

/*******************************************************************************
 * *** 转化字符串 (lmg) *****
 ******************************************************************************/

function single_code(paraString) {
    // alert (paraString)
    strResult = "";
    j = 0;
    for (i = 0; i < paraString.length; i++) {
        Char = paraString.charAt(i);
        if (Char == "'") {
            strResult = strResult + "\'" + "\'";
        } else {
            strResult = strResult + Char;
        }
    }
    return strResult;
}

/*******************************************************************************
 * *** 转化字符串 (lmg) *****
 ******************************************************************************/
function win_location(objField) {
    window.location = objField.value + ".htm"
}

/*******************************************************************************
 * *** 获得页面的焦点 *****
 ******************************************************************************/
function getfocus(path) {
    remote = window.open(path, "RemoteRateWin", "toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,width=300,Height=240,top=200,left=350");
    if (remote.opener == null) remote.opener = window;
    if (window.focus) {
        remote.focus();
    }
    return false;
}

/*******************************************************************************
 * *** 重新设置密码 *****
 ******************************************************************************/
function resetPassword(form) {
    if (!confirm("确定要重置口令吗?")) {
        return false;
    } else {
        form.target = "frame1";
        form.action = "resetpass.asp";
        form.submit();
    }

}

/*******************************************************************************
 * *** 打开页面时获取焦点 *****
 ******************************************************************************/
function onLoad(fieldName) {
    fieldName.focus();
}

/*******************************************************************************
 * *** 换页处理 *****
 ******************************************************************************/
function toWhichPage(whichPage) {
    document.form1.whichPage.value = whichPage;

    document.form1.submit();
}
/*******************************************************************************
 * 
 * 
 * /***************************************************************** *** 换页处理
 * 产品展示专用 *****
 ******************************************************************************/
function toWhichPage1(whichPage, parentId) {
    document.form1.whichPage.value = whichPage;
    document.form1.parentId.value = parentId;
    document.form1.submit();
}
/*******************************************************************************
 * 
 * 
 ******************************************************************************/
function alertword() {
    strword = '正在开发过程中，多谢你的支持与关注！'
    alert(strword);
}

function specialCharFilter(str) {
    var chars = new Array("~", "!", "@", "$", "%", "^", "&", "*", "|", "\\", "/", "?");
    for (var i = 0, cLen = chars.length; i < cLen; i++) {
        if (trim(str).indexOf(chars[i]) != -1) {
            return false;
        }
    }

    return true;
}

function checkMobile(str) {
    // var reg = /(^0{0,1}13[0-9]{9}$)/; yjli add
    var reg = /(^0{0,1}1[0-9]{10}$)/;
    return reg.test(str);
}

function checkPhone(str) {
    var reg = /(^[0-9]{3,4}\-[0-9]{3,8}$)|(^[0-9]{3,12}$)|(^\([0-9]{3,4}\)[0-9]{3,8}$)/
    return reg.test(str);
}
function checkEmail(str) {
    var reg = /\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/
    return(reg.test(str));
}
function compareDate(dateA,dateB){
    // var dateBegin=dateA.replace(/-/,"/");
    // var dateEnd=dateB.replace(/-/,"/");
var aPart=dateA.split("-");
var bPart=dateB.split("-");

 for(i=0;i<aPart.length;i++){
 if(parseInt(aPart[i])<parseInt(bPart[i])){
     return true;
 }
     }
    return false;
}
function getDateString(date){
var dDate=date.getDate();
if(dDate<10)
      dDate="0"+dDate;
var dMonth=date.getMonth()+1;
if(dMonth<10)
      dMonth="0"+dMonth;

var dYear=date.getYear();
return dYear+"-"+dMonth+"-"+dDate;
}

// 点击左方的缩放按钮
function hideLeftMenu() {
// var obj = top.topFrame.document.getElementById("tdPANEL");
      var obj = top.frames("topFrame").document.getElementById("tdPANEL");
// window.alert("tiaoshi hideLeftMenu()");
// window.alert("top.topFrame.document.getElementById(\"tdPANEL\")"+top.topFrame.document.getElementById("tdPANEL"));

    if (!obj) alert("对不起，页面出现了异常");
    if (obj.style.display == "none") {
        obj.style.display = "block";
    } else {
        obj.style.display = "none";
    }
// leftSepa = top.topFrame.document.getElementById("leftseparator");

    var leftSepa = top.frames("topFrame").document.getElementById("leftseparator");

    leftSepa.src = leftSepa.src.replace("open", "close");
}