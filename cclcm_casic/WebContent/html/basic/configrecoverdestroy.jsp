<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>回收销毁设置</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>   
</head>
<script>
    function openConfirm(module){
		var url = "${ctx}/basic/configrecoverdestroy.action?update=Y&typ="+escape(module)+"&type=open";
		callServer(url);
	}
	function closeConfirm(module){
		var url = "${ctx}/basic/configrecoverdestroy.action?update=Y&typ="+escape(module)+"&type=close";
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
<form id="TemplateQueryCondForm" method="POST" action="${ctx}/basic/configrecoverdestroy.action">
<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="50%">
	<tr>
		<td colspan="3" class="title_box">&nbsp;&nbsp;回收送销设置</td>
	</tr>	
	<tr>
		<td align="center">回收开关：</td>
		<td align="center">
			<c:choose>
				<c:when test="${recover_on_off==1 }">
					<input type="button" class="button_2003" value="已经开启" disabled="disabled"/>&nbsp;&nbsp;&nbsp;&nbsp;																		
					<input type="button" class="button_2003" value="关闭回收" onclick="closeConfirm('recover');"/>
				</c:when>
				<c:otherwise>
					<input type="button" class="button_2003" value="开启回收" onclick="openConfirm('recover');"/>&nbsp;&nbsp;&nbsp;&nbsp;																		
					<input type="button" class="button_2003" value="已经关闭" disabled="disabled"/>
				</c:otherwise>
			</c:choose>																	
		</td>
	</tr>
	<tr id="tr_copy">
		<td align="center">送销开关：</td>
		<td align="center">
			<c:choose>
				<c:when test="${senddestroy_on_off==1 }">
					<input type="button" class="button_2003" value="已经开启" disabled="disabled"/>&nbsp;&nbsp;&nbsp;&nbsp;																		
					<input type="button" class="button_2003" value="关闭送銷" onclick="closeConfirm('destroy');"/>
				</c:when>
				<c:otherwise>
					<input type="button" class="button_2003" value="开启送銷" onclick="openConfirm('destroy');"/>&nbsp;&nbsp;&nbsp;&nbsp;																		
					<input type="button" class="button_2003" value="已经关闭" disabled="disabled"/>
				</c:otherwise>
			</c:choose>
		</td>						
	</tr>
	<tr>
        <td colspan="3" align="center" class="bottom_box">
            <input type="button" value="返回" class="button_2003" onclick="history.go(-1);" style="display:none"/>
        </td>
    </tr> 
</table>
</form>
</center>
</body>
</html>