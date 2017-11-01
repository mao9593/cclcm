<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>修改用户密码</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
</head>
<form target="topFrame" method="post" action="${ctx}/login.action" id="loginForm">
</form>
<script language="javascript">
if('${enter}' != 'Y'){
	//mod by wx 20150502
	//alert("密码有效期已过，请修改密码重新登录");
	alert("密码已过有效期或初始密码尚未更改，请修改密码后重新登录");
	//above
}
if('${done}' == 'Y'){
	alert("修改成功，请重新登录");
	$("#loginForm").submit();
	if(window.parent!=null){
		window.close();
	}
}else if("${msg}" != ""){
	alert("${msg}");
}
function chk()
{
	if($("#pwd_new").val().length == 0){
		alert("新密码不能为空");
		$("#pwd_new").focus();
        return false;
	}
	if($("#pwd_new").val() != $("#pwd_firm").val()){
		alert("密码确认失败");
		$("#pwd_firm").focus();
		return false;
	}
	if($("#pwd_new").val().trim()!= null &&$("#pwd_new").val().trim()!= ""){
	var pwd_new =/[\u4e00-\u9fa5]+/; 
    if(pwd_new.test($("#pwd_new").val())){
    alert("默认密码不能含有汉字");
    $("#pwd_new").focus();
		return false;
    }
	}
	return true;
}
</script>
<body oncontextmenu="self.event.returnValue=false" onload="onHover();" style="padding-top: 100px">
<center>
<form id="ChangeUserPwdForm" method="POST" action="${ctx}/user/changepwd.action">
<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="40%">
	<tr>
		<td colspan="2" class="title_box">修改用户密码(${user.user_name})</td>
	</tr>
	<tr>
		<td width="40%" align="center">原密码：</td>
		<td><input type="password" name="pwd_old" /></td>
	</tr>
	<tr>
		<td align="center">新密码：</td>
		<td><input type="password" name="pwd_new" id="pwd_new"/></td>
	</tr>
	<tr>
		<td align="center">密码确认：</td>
		<td><input type="password" id="pwd_firm" /></td>
	</tr>
	<tr>
        <td colspan="2" align="center" class="bottom_box">
        	<input type="hidden" name="user_iidd" value="${user.user_iidd}"/>
        	<input type="hidden" name="change" value="Y"/>
            <input type="submit" value="确定" class="button_2003" onclick="return chk()">&nbsp;
            <input type="button" value="返回" class="button_2003" onclick="history.go(-1);"/>
        </td>
    </tr>
</table>
</form>
</center>
</body>
</html>
