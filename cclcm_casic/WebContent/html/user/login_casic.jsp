<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script>
	alert("系统禁止用户直接登录，请通过门户链接进入系统");
	if(window.parent!=null){
		window.close();
	}	
</script>
</head>
</html>