<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>   
</head>
<script>
    function configSet(type){
		var url = "${ctx}/securityuser/logoset.action?update=Y&type="+escape(type);
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
<form id="TemplateQueryCondForm" method="POST" action="${ctx}/securityuser/logoset.action">
<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="40%">
	<tr>
		<td colspan="3" class="title_box">&nbsp;&nbsp;LOGO设置</td>
	</tr>	
	<tr>
		<td align="center">是否设置LOGO</td>
		<td align="center">
			<c:choose>
				<c:when test="${start == 0}">
					<input type="button" class="button_2003" value="使用cclcm" onclick="configSet('open');"/>&nbsp;&nbsp;&nbsp;&nbsp;																		
					<input type="button" class="button_2003" value="已关闭bm" disabled="disabled"/>
				</c:when>
				<c:otherwise>
					<input type="button" class="button_2003" value="已使用cclcm" disabled="disabled"/>&nbsp;&nbsp;&nbsp;&nbsp;																		
					<input type="button" class="button_2003" value="关闭bm" onclick="configSet('close');"/>
				</c:otherwise>
			</c:choose>
		</td>						
	</tr>
	<td colspan="3" align="center"><font color="red"><b>注：设置成功之后请重新登录</b></font></td>
</table>
</form>
</center>
</body>
</html>