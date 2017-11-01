<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<HEAD>
<meta http-equiv="Pragma" content="No-cache">
<meta http-equiv="Cache-Control" content="no-cache, must-revalidate">
<META http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<SCRIPT LANGUAGE="JavaScript">
<!--
	function initDraft(){
		document.getElementById("draftForm").submit();
	}
//-->
</SCRIPT>
<TITLE>航盾信息安全产品网络控制台</TITLE>
</HEAD>
<BODY style="margin:0px;" onload="initDraft();" scroll="no">
<form target="mainFrame" action="${ctx}/ledger/viewselfpaperledger.action" id="draftForm">
</form>
<iframe src="${ctx}/index.action" id="topFrame" name="topFrame" height="100%" width="100%" frameborder="0" marginwidth="0" marginheight="0" style="top:0px;left:0px;z-index:9998;"></iframe>
<NOSCRIPT><IFRAME SRC=''></IFRAME></NOSCRIPT><%--加上此行, 可以使IE无法保存网页--%>

</BODY>
</HTML>