<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>查看系统信息</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
</head>

<body oncontextmenu="self.event.returnValue=false" onload="onHover();" style="padding-top: 100px">
<center>
<form id="Form" method="POST" action="${ctx}/basic/viewinfo.action">
<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="40%">
	<tr>
		<td colspan="2" class="title_box">查看系统信息</td>
	</tr>
	<tr>
		<td width="40%" align="center">W&nbsp;E&nbsp;B&nbsp;版本：</td>
		<td>&nbsp;&nbsp;${web_version}</td>
	</tr>
	<tr>
		<td align="center">服务器版本：</td>
		<td>&nbsp;&nbsp;${server_version}</td>
	</tr>
	<tr>
		<td width="40%" align="center">可注册人数：</td>
		<td>&nbsp;&nbsp;${numbers}</td>
	</tr>
	<tr>
		<td width="40%" align="center">剩余可注册人数：</td>
		<td>&nbsp;&nbsp;${l_numbers}</td>
	</tr>
	<tr>
		<td align="center">系统使用截止日期：</td>
		<td>&nbsp;&nbsp;${days}</td>
	</tr>
	<tr>
		<td align="center">可使用控制台个数：</td>
		<td>&nbsp;&nbsp;${hsNumbers}</td>
	</tr>
</table>
</form>
</center>
</body>
</html>
