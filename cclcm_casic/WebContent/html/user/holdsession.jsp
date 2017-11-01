<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>锁定会话解锁</title>
	<base target="_self"/>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
</head>
<form target="topFrame" method="post" action="${ctx}/login.action" id="loginForm">
</form>
<script language="javascript">
if('${done}' == 'Y'){
	if(window.parent!=null){
		window.returnValue = "unlock";
		window.close();
	}
}else if("${msg}" != ""){
	alert("${msg}");
}
var closewindow = 1;
function CheckWindowClose(){
	alert("请输入密码解锁，如果强制关闭，则返回登录页面");
	return false;
}
function forceClose(){
	if(closewindow == 1){
		parent.document.getElementById("loginForm").submit();
		window.close();
	}
}
</script>
<body oncontextmenu="self.event.returnValue=false" onload="onHover();" style="padding-top: 20px" onmouseover="closewindow=0;" onmouseout="closewindow=1;">
<center>
<form id="HoldSessionForm" method="POST" action="${ctx}/user/holdsession.action">
<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="80%">
	<tr>
		<td colspan="2" class="title_box">输入密码解锁(${user_iidd})</td>
	</tr>
	<tr>
		<td width="40%" align="center">登录密码：</td>
		<td><input type="password" name="password" /></td>
	</tr>
	<tr>
        <td colspan="2" align="center" class="bottom_box">
        	<input type="hidden" name="user_iidd" value="${user_iidd}"/>
        	<input type="hidden" name="checkpwd" value="Y"/>
            <input type="button" value="确定" class="button_2003" onclick="forms[1].submit();">&nbsp;
        </td>
    </tr>
</table>
</form>
</center>
</body>
</html>
