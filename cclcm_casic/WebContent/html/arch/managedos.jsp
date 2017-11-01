<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html><head>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
</head>
<frameset cols='320,8,*' frameborder='NO' border='0' framespacing='0' name='mainframeset'>
    <frame src='${ctx}/arch/getarchtypetree.action' name='leftframe' scrolling='auto'>
    <frame src='${ctx}/html/user/innerline.htm' name='innerframe' scrolling='no' noresize>
    <frame src='${ctx}/html/arch/showlogo.jsp' name='workframe' scrolling='auto'>
</frameset>
<noframes></noframes>
<body></body>
</html>