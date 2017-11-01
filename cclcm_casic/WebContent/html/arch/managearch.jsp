<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html><head>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
</head>
<frameset cols='200,8,*' frameborder='NO' border='0' framespacing='0' name='mainframeset'>
    <frame src='${ctx}/arch/gettree.action' name='leftframe' scrolling='auto'>
    <frame src='${ctx}/html/user/innerline.htm' name='innerframe' scrolling='no' noresize>
    <frame src='' name='workframe' scrolling='auto'>
</frameset>
<noframes></noframes>
<body></body>
</html>