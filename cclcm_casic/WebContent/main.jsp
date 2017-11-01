<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<HEAD>
<meta http-equiv="Pragma" content="No-cache">
<meta http-equiv="Cache-Control" content="no-cache, must-revalidate">
<META http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<SCRIPT LANGUAGE="JavaScript">
<!--
function init(){
	//do nothing;
}
function send(reqURI){
	xml = new ActiveXObject("Microsoft.XMLHTTP");
	var post=""//构造要携带的数据
	//xml.open("POST","http://localhost:7001/TestWL/index.jsp",false);//使用POST方法打开一个到服务器的连接，以异步方式通信
	try{
		xml.open("POST", reqURI, false);
    	xml.setrequestheader("content-length",post.length);
    	xml.setrequestheader("content-type","application/x-www-form-urlencoded");
		xml.send(post);//发送数据
	}
	catch(e){
		alert(e);
	}
    return xml.responseText;//接收服务器返回的数据
}

//-->
</SCRIPT>
<TITLE>航盾信息安全产品网络控制台</TITLE>
</HEAD>
<BODY style="margin:0px;" onLoad="init();" scroll="no">
<iframe src="${ctx}/index.action" id="topFrame" name="topFrame" height="100%" width="100%" frameborder="0" marginwidth="0" marginheight="0" style="top:0px;left:0px;z-index:9998;"></iframe>
<NOSCRIPT><IFRAME SRC=''></IFRAME></NOSCRIPT><%--加上此行, 可以使IE无法保存网页--%>

</BODY>
</HTML>