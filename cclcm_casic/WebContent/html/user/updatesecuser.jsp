<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<HTML><HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>安全用户修改</title>
</HEAD>
<body>
<iframe frameborder="0" scrolling="false" marginwidth=0 marginheight=0 src="${ctx}/user/updatesecuserother.action?user_iidd=${userId}" width="100%" height="250"></iframe>
<iframe frameborder="0" scrolling="false" marginwidth=0 marginheight=0 src="${ctx}/user/updatesecuserbase.action?user_iidd=${userId}&dept_id=${dept_id}&position=${position}&beforeCount=${beforeCount}&behindCount=${behindCount}" width="100%" height="900"></iframe><br/><br/>
</body>
</HTML>