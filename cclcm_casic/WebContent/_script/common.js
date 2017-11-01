//验证是否有选中的CHECKBOX,注意不用document.form.checkbox,用document.getElementsByName
function checkCheckBoxSel(objCheckBox)
{
    var objs=document.getElementsByName(objCheckBox);
    var L=objs.length;
    var objChecked=false;
    var result=false;

    for(var n=0;n<L;n++)
    {
        if(objs[n].checked==true)
        {
            objChecked=true;
        }
    }

    if (objChecked==false)
    {
        alert("请选择要上报的网元!");
    }
    else
    {
        result=true;
    }
    return result;
}

// 用于CHECKBOX的全选/取消
s=true
function sel(obj)
{
    o=document.getElementsByName(obj)
    for(i=0;i<o.length;i++)
    o[i].checked=s
    s=!s
}


function winOpen (strURL,strName,width,height)
{
    theWindow = window.open (strURL,strName,"width="+width+" height="+height+" scrollbars=yes resizable=yes left="+(1024-width)/2+" top="+(768-height)/2);
    if (theWindow.opener == null) theWindow.opener = window;
    if (window.focus) theWindow.focus();
}

function winOpenCenterFree(strURL, strName) {
   theWindow = window.open(strURL, strName, 'left='+window.screen.width/4+',top='+window.screen.height/4+',status=yes,fullscreen=yes,toolbar=yes,menubar=yes,location=yes,scrollbars=yes,resizable=yes');
   if (theWindow.opener == null) theWindow.opener = window;
   if (window.focus) theWindow.focus();
}

function winOpenCenterHalf(strURL, strName) {
   theWindow = window.open(strURL, strName, 'width=' + window.screen.width/2 + ',height=' + +window.screen.height/2 + ',left='+window.screen.width/4+',top='+window.screen.height/4+',status=no,fullscreen=no,toolbar=no,menubar=no,location=no,scrollbars=no,resizable=yes')
   if (theWindow.opener == null) theWindow.opener = window;
   if (window.focus) theWindow.focus();
}

function goPage(strURL,ifOpenWindow) {
    if(ifOpenWindow)
        winOpenCenterHalf(strURL, "EOMS");
    else
        document.location=strURL;
    return false;
}

/*
 * 确认删除
 */
function confirmDel(url)
{
    result=true;
    if (confirm("确定删除吗?"))
    {
        winOpen(url,'',1,1);
    }
    else
    {
        result=false;
    }
    return result;
}

/*
 * 确认以后转到一个地址,用于修改状态等操作的确定上
 */
function confirmGo(msg,url)
{
    result=true;
    if (confirm(msg))
    {
        window.location.href=url;
    }
    else
    {
        result=false;
    }
    return result;
}


/*
 * 确认以后转到一个地址,用于修改状态等操作的确定上
 */
function confirmWinOpen(msg,url,width,height)
{
    result=true;
    if (confirm(msg))
    {
        window.open(url,'',"width="+width+" height="+height+" top=0 left=8000")
    }
    else
    {
        result=false;
    }
    return result;
}

/*
 * 转到一个地址
 */
function go(url)
{
    window.location.href=url;
}

/*
 * 确认删除
 */
function confirmDel(url)
{
    result=true;
    if (confirm("确定删除吗?"))
    {
        window.location.href=url;
    }
    else
    {
        result=false;
    }
    return result;
}
/*
 * 确认以后转到一个地址,用于修改状态等操作的确定上
 */
function confirmGo(msg, url)
{
    result = true;
    if (confirm(msg))
    {
        window.location.href = url;
    }
    else
    {
        result = false;
    }
    return result;
}


/*
 * 确认以后转到一个地址,用于修改状态等操作的确定上
 */
function confirmWinOpen(msg, url, width, height)
{
    result = true;
    if (confirm(msg))
    {
        window.open(url, '', "width=" + width + " height=" + height + " top=0 left=8000")
    }
    else
    {
        result = false;
    }
    return result;
}

/*
 * 转到一个地址
 */
function go(url)
{
    window.location.href = url;
}

/*
 * 确认删除
 */
function confirmDel(url)
{
    result = true;
    if (confirm("确定删除吗?"))
    {
        window.location.href = url;
    }
    else
    {
        result = false;
    }
    return result;
}

/*******************************************************************************
 * *** 判断是否为日期数据 (lhm) *****
 ******************************************************************************/
function itIsDate(DateString, Dilimeter)
{
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
    if (tempArray[0].length == 4)
    {
        tempy = tempArray[0];
        tempd = tempArray[2];
    }
    else
    {
        tempy = tempArray[2];
        tempd = tempArray[1];
    }
    tempm = tempArray[1];
    var tDateString = tempy + '/' + tempm + '/' + tempd + ' 8:0:0';
    // 加八小时是因为我们处于东八区
    var tempDate = new Date(tDateString);
    if (isNaN(tempDate))
        return false;
    if (((tempDate.getUTCFullYear()).toString() == tempy) && (tempDate.getMonth() == parseInt(tempm) - 1) && (tempDate.getDate() == parseInt(tempd)))
    {
        return true;
    }
    else
    {
        return false;
    }
}

/*******************************************************************************
 * *** 求字符串的字节长度 (lhm) *****
 ******************************************************************************/
function byteLength(paraString)
{
    var strValue = new String(paraString);
    var strLength = strValue.length;
    var numLength = 0;
    for (globle_i = 0; globle_i < strLength; globle_i++) {
        var ASCIIValue = strValue.charCodeAt(globle_i);
        if (ASCIIValue > 0 && ASCIIValue < 127)
            numLength = numLength + 1
        else
            numLength = numLength + 2
    }
    return numLength;
}

/*******************************************************************************
 * *** 提示匡 (lhm) ***** '// strMsg：输入的题示语言 '//返回值：1，2（1：确定 2：取消）
 ******************************************************************************/
function msg(strMsg)
{
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
 * *** 删除处理 (lmg) *****
 ******************************************************************************/

function del_btn(form, strMsg)
{

    var string = "00";
    var length = form.delCopID.length;
    if (form.delCopID.checked) { // 只有一条记录时执行此语句

        string = "1";

    }
    for (var i = 0; i < length; i++)
    {
        if (form.delCopID[i].checked) {
            string = "1";

        }

    }
    if (string == "00")
    {
        alert("没有选择任何项目!");
        return false;
    }
    else
    {
        if (msg(strMsg) == 2) return false;
        else {
            return true;
        }
    }

}

/*******************************************************************************
 * *** 提交处理 (lmg) *****
 ******************************************************************************/
function submit_ydh(form, strMsg)
{


    if (msg(strMsg) == 2) return false;
    else {
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
function formSubmit(objForm, strUrl, strActionType)
{
    objForm.action = strUrl;
    objForm.actionType.value = strActionType;
    // alert (objForm.actionType.value);
    objForm.submit();
}


/*******************************************************************************
 * *** 转化字符串 (lmg) *****
 ******************************************************************************/

function conversion_code(paraString)
{
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
function InputIntNumberCheck()
{
    if (!((window.event.keyCode >= 48) && (window.event.keyCode <= 57)))
    {
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
            }
            else {
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

function single_code(paraString)
{
    // alert (paraString)
    strResult = "";
    j = 0;
    for (i = 0; i < paraString.length; i++) {
        Char = paraString.charAt(i);
        if (Char == "'") {
            strResult = strResult + "\'" + "\'";
        }
        else
        {
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
function getfocus(path)
{
    remote = window.open(path, "RemoteRateWin", "toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,width=300,Height=240,top=200,left=350");
    if (remote.opener == null) remote.opener = window;
    if (window.focus)
    {
        remote.focus();
    }
    return false;
}


/*******************************************************************************
 * *** 打开页面时获取焦点 *****
 ******************************************************************************/
function onLoad(fieldName) {
    fieldName.focus();
}

// 显示,不显示一个对象
function showHide(obj)
	{
		if(obj.style.display=="none")
		{
			obj.style.display="";
		}
		else
		{
			obj.style.display="none";
		}
	}

/*******************************************************************************
 * *** 密码强度验证 *****
 ******************************************************************************/

// CharMode函数
// 测试某个字符是属于哪一类.
function CharMode(iN){
if (iN>=48 && iN <=57) // 数字
return 1;
if (iN>=65 && iN <=90) // 大写字母
return 2;
if (iN>=97 && iN <=122) // 小写
return 4;
else
return 8; // 特殊字符
}

// bitTotal函数
// 计算出当前密码当中一共有多少种模式
function bitTotal(num){
modes=0;
for (i=0;i<4;i++){
if (num & 1) modes++;
num>>>=1;
}
return modes;
}

// checkStrong函数
// 返回密码的强度级别

function checkStrong(sPW){
if (sPW.length<=6)
return 0; // 密码太短
Modes=0;
for (i=0;i<sPW.length;i++){
// 测试每一个字符的类别并统计一共有多少种模式.
Modes|=CharMode(sPW.charCodeAt(i));
}

return bitTotal(Modes);

}

// pwStrength函数
// 当用户放开键盘或密码输入框失去焦点时,根据不同的级别显示不同的颜色

function pwStrength(pwd,outId){
var text="";
O_color="#eeeeee";
L_color="#FF0000";
M_color="#FF9900";
H_color="#33CC00";
if (pwd==null||pwd==''){
Lcolor=Mcolor=Hcolor=L_color;
text="请输入";
}
else{
S_level=checkStrong(pwd);
switch(S_level) {
case 0:
Lcolor=Mcolor=Hcolor=L_color;
text="密码太短";
break;
case 1:
Lcolor=L_color;
Mcolor=Hcolor=O_color;
text="安全性:弱";
break;
case 2:
Lcolor=Mcolor=M_color;
Hcolor=O_color;
text="安全性:中";
break;
default:
Lcolor=Mcolor=Hcolor=H_color;
text="安全性:强";
}
}

document.getElementById(outId).style.color=Lcolor;
// document.getElementById("strength_M").style.background=Mcolor;
// document.getElementById("strength_H").style.background=Hcolor;
document.getElementById(outId).innerText=text;
return;
}

