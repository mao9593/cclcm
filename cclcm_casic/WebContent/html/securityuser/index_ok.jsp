<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
</head>
<form target="topFrame" method="post" action="${ctx}/login.action" id="loginForm">
</form>
<script language="javascript">
if('${done}' == 'Y'){
	alert("修改成功，请重新登录");
	$("#loginForm").submit();
	if(window.parent!=null){
		window.close();
	}
}
</script>