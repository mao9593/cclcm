<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>涉密人员类别管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
</head>
<script>
<!--
function onDelete(security_code,user_count){
	if(user_count > 0){
		alert("该涉密人员类别尚有关联用户，不能删除");
		return false;
	}else{
		if(confirm("确定要删除该涉密人员类别？")){
			go("${ctx}/user/delsecurity.action?security_code="+escape(security_code));
		}
	}
}
//-->
</script>
<body oncontextmenu="self.event.returnValue=false" onload="onHover();">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">涉密人员类别管理</td>
	</tr>
	<tr>
		<td class="nav_box" align="right">
			<input name="button" type="button" class="button_2003" onClick="go('${ctx}/user/addsecurity.action');" value="增加涉密人员类别">
		</td>
	</tr>
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
					<display:table requestURI="${ctx}/user/managesecurity.action" uid="item" class="displaytable" name="securityList" pagesize="15" sort="list">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column title="类别名称" property="security_name" style="whitespace: nowrap;"/>
						<display:column property="security_desc" title="类别描述" style="whitespace: nowrap;" maxLength="50"/>
						<display:column title="操作" style="width:150px">
								<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/user/viewsecuritydetail.action?security_code=<c:out value="${item.security_code}"/>');"/>&nbsp;
								<c:if test="${!item.is_delete}">
									<input type="button" class="button_2003" value="配置" onclick="go('${ctx}/user/configsecurity.action?security_code=<c:out value="${item.security_code}"/>');"/>&nbsp;
									<input type="button" class="button_2003" value="删除" onclick="onDelete('${item.security_code}','${item.user_count}');"/>
								</c:if>
								<c:if test="${item.is_delete}">
									<input type="button" class="button_2003" value="配置" disabled="disabled"/>&nbsp;
									<input type="button" class="button_2003" value="封存" disabled="disabled"/>
								</c:if>
						</display:column>
					</display:table>
					</td>
				</tr>
			</table>
         </td>
	</tr>
</table>
</body>
</html>
