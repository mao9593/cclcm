<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Pragma" content="No-cache">
<meta http-equiv="Cache-Control" content="no-cache, must-revalidate">
<meta http-equiv="Expires" content="-1">
<link rel="stylesheet" href="${ctx}/_css/displaytag.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
<script language="JavaScript" src="${ctx}/_script/function.js"></script>
<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
<script  language="JavaScript" >
<!--
$(document).ready(function(){
	onHover();
});

function configScope(role_id,dept_ids,dept_names){
	$("#role_id").val(role_id);
	$("#dept_ids").val(dept_ids);
	$("#dept_names").val(dept_names);
	var rValue = openDeptSelect("dept_names","dept_ids","checkbox");
	if(rValue != undefined && rValue == "Y"){
		 if($("#dept_ids").val()!=""){
			var url="${ctx}/basic/configdeptbyrole.action";
			callServerPostForm(url,document.forms[0]);
		} 
	}
}
function getAjaxResult(){
	if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
		alert(xmlHttp.responseText);
		$("#ConfigDeptForm").submit();
	}
}
function showDialogr(url){
		return window.showModalDialog(url,'', 'dialogHeight:300px;dialogWidth:800px;center:yes;status:no;help:no;scroll:no;unadorned:no;resizable:no');
	}
//-->
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table border="0" class="displaytable-outter" cellspacing="0" cellpadding="0" width="100%" height="370">
	<tr height="25">
		<td class="title_box">用户列表(角色：${role_name})</td>
	</tr>
	<tr height="285">
		<td valign="top">
<div style="overflow:auto;height:100%;width:100%">
			<display:table requestURI="" uid="user" name="secUser" class="displaytable" >
                <display:column title="序号">
						<c:out value="${user_rowNum}"/>
				</display:column>
                <display:column title="登录名称" sortable="true" >
	               	<c:if test="${user.status == 1}">
						<img src="${ctx}/_image/iconx32/stop1.gif" />
					</c:if>
	                <c:out value="${user.user_iidd}"/>
				</display:column>
                 <display:column title="用户名称"  property="user_name"/>
                 <display:column title="部门"  property="dept_name"/>
                 <display:column title="移动电话"  property="com_mobile"/>
                 <display:column title="固定电话"  property="com_telephone"/>
                 <display:column title="电子邮件"  property="com_email"/>
                 <display:column title="操作" style="width:150px">
						<input type="button" class="button_2003" value="配置部门" onclick="showDialogr('${ctx}/basic/configdeptforuser.action?user_iidd=${user.user_iidd}&role_idString=${role_id}&urlString=Y');"/>
				</display:column>
			</display:table>
	</div>
		</td>
	</tr>
	<tr>
		<td align="center" class="bottom_box">
			<input type="button" class="button_2003" value="关闭" onclick="window.close();"/>
		</td>
	</tr>
	<tr/>
</table>
</body>
</html>