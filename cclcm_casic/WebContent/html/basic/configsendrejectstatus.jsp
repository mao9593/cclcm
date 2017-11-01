<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>配置载体拒收后状态</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
</head>
<script>
	$(document).ready(function(){
		if('${state}' == '0'){
			alert("配置成功:载体拒收后状态为已回收");
		}else if('${state}' == '1'){
			alert("配置成功:载体拒收后状态为留用");
		}
	});
</script>
<body>
</body>
</html>