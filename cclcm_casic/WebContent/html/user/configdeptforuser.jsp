<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<base target="_self">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>为用户角色配置管理部门</title>
<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
<link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
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
		 //if($("#dept_ids").val()!=""){
			var url="${ctx}/basic/configdeptbyrole.action";
			callServerPostForm(url,document.forms[0]);
		//} 
	}
}
function configPartTime(dept_ids,dept_names){
	$("#dept_ids_parttime").val(dept_ids);
	$("#dept_names_parttime").val(dept_names);
	var rValue = openDeptSelect("dept_names_parttime","dept_ids_parttime","checkbox");
	if(rValue != undefined && rValue == "Y"){
		 //if($("#dept_ids_parttime").val()!=""){
		var url="${ctx}/basic/configparttimedept.action";
		callServerPostForm(url,document.forms[1]);
		//} 
	}
}
function getAjaxResult(){
	if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
		alert(xmlHttp.responseText);
		$("#ConfigDeptForm").submit();
	}
}
//-->
</script>
</head>
<body oncontextmenu="self.event.returnValue=true">
<center>
<form method="post" action="${ctx}/basic/configdeptbyrole.action" >
	<input type="hidden" name="user_iidd" value="${user_iidd}" />
	<input type="hidden" name="role_id" id="role_id" />
	<input type="hidden" name="dept_ids" id="dept_ids" />
	<input type="hidden" name="dept_names" id="dept_names" />
</form>
<form method="post" action="${ctx}/basic/configparttimedept.action" >
	<input type="hidden" name="user_iidd" value="${user_iidd}" />
	<input type="hidden" name="dept_ids" id="dept_ids_parttime" />
	<input type="hidden" name="dept_names" id="dept_names_parttime" />
</form>
<form method="post" action="${ctx}/basic/configdeptforuser.action" id="ConfigDeptForm">
  <input type="hidden" name="user_iidd" value="${user_iidd}" />
  <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">为${user_name}配置管理部门</td>
	</tr>
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
				<tr>
					<td>
						<display:table uid="secRole" class="displaytable" name="secRoleList" defaultsort="1" sort="list">
							<display:column title="角色名称" >
								<div title="<c:out value="${secRole.role_id}" />">
									<c:out value="${secRole.role_name}"/>
								</div>
							</display:column>
							<display:column property="role_desc"  title="角色描述" maxLength="40"/>
							<display:column title="管理部门" maxLength="40">
								${secRole.deptAdminConfig.dept_names}
							</display:column>
							<display:column  title="操作">
								<c:choose>
									<c:when test="${secRole.role_spec_key eq '' or secRole.role_spec_key == null}">
										<input type="button" class="button_2003" value="配置部门" onclick="configScope('${secRole.role_id}','${secRole.deptAdminConfig.dept_ids}','${secRole.deptAdminConfig.dept_names}');"/>
									</c:when>
									<c:otherwise>
										<input type="button" class="button_2003" value="不可配置" disabled="disabled"/>
									</c:otherwise>
								</c:choose>
							</display:column>
						</display:table>
					</td>
				</tr>
				<tr>
			        <td align="center" class="bottom_box">
			            <input type="button" value="关闭" class="button_2003" onClick="go('${ctx}/user/viewuserbydept.action');">
			        </td>
				</tr>
			</table>
		</td>
    </tr>
  </table>
<br><br>
<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="95%">
	<tr>
		<td colspan="3" class="title_box">配置兼职审批部门</td>
	</tr>
	<tr>
		<td align="center">
			<input type="button" class="button_2003" value="配置兼职部门" onclick="configPartTime('${partTimeDept.dept_ids}','${partTimeDept.dept_names}');"/>
		</td>
		<td width="85%">
			<texera name="summ" rows="3" cols="80">${partTimeDept.dept_names}</texera>
		</td>
	</tr>
</table>
</form>
</center>
</body>
</html>
