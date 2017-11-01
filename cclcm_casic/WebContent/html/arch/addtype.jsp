<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>添加类别</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
</head>
<form target="topFrame" method="post" action="${ctx}/login.action" id="loginForm">
</form>
<body oncontextmenu="self.event.returnValue=false" onload="onHover();" style="padding-top: 100px">
<center>
<form id="ChangeUserPwdForm" method="POST" action="${ctx}/arch/addtype.action">
<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="40%">
	<tr>
		<td colspan="2" class="title_box">添加的类别</td>
	</tr>
	<tr>
		<td width="40%" align="center">类别名称：</td>
		<td><input type="text" name="type_name" id="type_name" /></td>
	</tr>
	<tr>
		<td align="center">类别编号：</td>
		<td><input type="text" name="type_num" id="type_num"/></td>
	</tr>
	<tr>
        <td colspan="2" align="center" class="bottom_box">
        	<input type="hidden" name="user_iidd" value="${user.user_iidd}"/>
        	<input type="hidden" name="change" value="Y"/>
            <input type="submit" value="确定" class="button_2003" >&nbsp;
            <input type="button" value="返回" class="button_2003" onclick="history.go(-1);"/>
        </td>
    </tr>
</table>
</form>
</center>
</body>
</html>
