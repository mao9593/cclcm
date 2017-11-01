<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>交接确认配置</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>   
</head>
<script>
    function configSelfApprove(type){
		var url = "${ctx}/basic/configselfapprove.action?update=Y&type="+escape(type);
		//alert(url);
		callServer(url);
	}
	function updateResult(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			alert("修改成功");
			$("#TemplateQueryCondForm").submit();
		}
	}
</script>
<body oncontextmenu="self.event.returnValue=false" onload="onHover();" style="padding-top: 180px">
<center>
<form id="TemplateQueryCondForm" method="POST" action="${ctx}/basic/configselfapprove.action">
<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="40%">
	<tr>
		<td colspan="3" class="title_box">&nbsp;&nbsp;自审批设置</td>
	</tr>	
	<tr>
		<td align="center">是否开启自审批：</td>
		<td align="center">
			<c:choose>
				<c:when test="${start == 1}">
					<input type="button" class="button_2003" value="已开启" disabled="disabled"/>&nbsp;&nbsp;&nbsp;&nbsp;																		
					<input type="button" class="button_2003" value="关闭" onclick="configSelfApprove('close');"/>
				</c:when>
				<c:otherwise>
					<input type="button" class="button_2003" value="开启" onclick="configSelfApprove('open');"/>&nbsp;&nbsp;&nbsp;&nbsp;																		
					<input type="button" class="button_2003" value="已关闭" disabled="disabled"/>
				</c:otherwise>
			</c:choose>
		</td>						
	</tr>
</table>
</form>
</center>
</body>
</html>