<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>部门载体归属转换个人模式配置</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>   
</head>
<script>
	$(document).ready(function(){
		onHover();
	});
	function handleConfirm(type){
		var url = "${ctx}/basic/configdepttopersonmode.action?update=Y&type="+escape(type);
		callServer(url);
	}
	function updateResult(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			alert("修改成功");
			$("#TemplateQueryCondForm").submit();
		}
	}
</script>
<body oncontextmenu="self.event.returnValue=false" onload="onHover();" style="padding-top: 80px">
<center>
<form id="TemplateQueryCondForm" method="POST" action="${ctx}/basic/configdepttopersonmode.action">
<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="50%">
	<tr>
		<td colspan="3" class="title_box">&nbsp;&nbsp;部门载体归属转换个人模式配置</td>
	</tr>	
	<tr>
		<td align="center">
			<c:choose>
				<c:when test="${onoff == 0}">
					<input type="button" class="button_2003" value="通用模式关闭" disabled="disabled"/>																		
					<input type="button" class="button_2003" value="个人确认模式开启" onclick="handleConfirm('open');"/>
				</c:when>
				<c:otherwise>
					<input type="button" class="button_2003" value="通用模式开启" onclick="handleConfirm('close');"/>																		
					<input type="button" class="button_2003" value="个人确认模式关闭" disabled="disabled"/>
				</c:otherwise>
			</c:choose>																	
		</td>
	</tr>
</table>
</form>
</center>
</body>
</html>