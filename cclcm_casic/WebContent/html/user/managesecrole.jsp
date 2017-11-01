<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>安全角色管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
<script>
<!--
	function openr(url){
		var operCfg = window.open(url, "", "width=500,height=600,location=no,menubar=no,resizable=no,status=no,titlebar=no,toolbar=no,scrollbars=no");
	}

	function showDialogr(url){
		return window.showModalDialog(url,'', 'dialogHeight:400px;dialogWidth:800px;center:yes;status:no;help:no;scroll:no;unadorned:no;resizable:no');
	}
	function showConfig(url){
		var rValue = window.showModalDialog(url,'', 'dialogHeight:300px;dialogWidth:800px;center:yes;status:no;scroll:no;help:no;unadorned:no;resizable:no');
		if(rValue != null && rValue!= undefined){
			$("#role_id").val(rValue.role_id);
			$("#proxy_user_iidd").val(rValue.user_iidd);
			callServerPostForm("${ctx}/basic/configuserforrole.action",document.forms[0]);
		}
	}
	function getAjaxResult(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			alert("添加成功！");
			RoleQueryCondForm.submit();
		}
	}
	function clearFindForm(){
		RoleQueryCondForm.role_name.value = "";
	}
//-->
</script>
</head>

<body oncontextmenu="self.event.returnValue=false" onload="onHover();">
<form method="post" action="${ctx }/basic/configuserforrole.action">
<input type="hidden" value="Y" name="update" />
<input type="hidden" name="role_id" id="role_id" />
<input type="hidden" name="proxy_user_iidd" id="proxy_user_iidd" />
</form>
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">安全角色管理</td>
	</tr>
	<tr>
		<td align="right">
		<form id="RoleQueryCondForm" method="POST" action="${ctx}/user/managesecrole.action">
			<table  table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<!-- <td align="center">选择子系统：</td>
					<td>
						<c:set var="code1" value="${subsys_code}" scope="request"/>
      					<select name="subsys_code" class="MySelect">
         					<s:iterator value="#request.subsysList" var="item">
							<c:set var="code2" value="${item.subsys_code}" scope="request"/>
								<option value="${item.subsys_code}" <s:if test="#request.code2==#request.code1">selected="selected"</s:if> >${item.subsys_name}</option>
							</s:iterator>
     					</select>
					</td> -->
					<td align="center">角色名称：</td>
					<td colspan="3">
						<input type="text" name="role_name" value="${role_name}"/>
					</td>
					<td align="center"> 
						<input name="button" type="submit" class="button_2003" value="查询">&nbsp;&nbsp;
						<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">&nbsp;&nbsp;
						<input name="button" type="button" class="button_2003" onClick="go('${ctx}/user/addsecrole.action');" value="增加角色">
					</td>
				</tr>
			</table>
 		</form>
		</td>
	</tr>
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
					<display:table requestURI="${ctx}/user/managesecrole.action" uid="secRole" class="displaytable" name="secRole" pagesize="15" sort="list">
						<display:column title="序号" sortable="true">
							<c:out value="${secRole_rowNum}"/>
						</display:column>
						<display:column title="角色名称" style="whitespace: nowrap;">
							<c:out value="${secRole.role_name}" />
						</display:column>
						<display:column property="roleTypeName" title="角色类型" style="whitespace: nowrap;"/>
						<display:column property="role_desc" title="角色描述" style="whitespace: nowrap;" maxLength="50"/>
						<display:column title="操作" style="width:270px">
							<input type="button" class="button_2003" value="用户" onclick="showDialogr('${ctx}/user/viewuserbyrole.action?role_id=<c:out value="${secRole.role_id}"/>');">&nbsp;
							<c:if test="${!secRole.is_delete}">
								<c:choose>
									<c:when test="${currentUser.user_iidd == 'admin' || (secRole.role_type==3 && secRole.role_spec_key != 'BURNADMIN')}">
										<input type="button" class="button_2003" value="配置用户" onclick="showConfig('${ctx}/basic/configuserforrole.action?role_id=${secRole.role_id }')"/>&nbsp;
										<input type="button" class="button_2003" value="修改" onclick="go('${ctx}/user/updatesecrole.action?role_id=<c:out value="${secRole.role_id}"/>');"/>&nbsp;
										<input type="button" class="button_2003" value="配置" onclick="openr('${ctx}/user/prepconfigrole.action?role_id=<c:out value="${secRole.role_id}"/>&role_name=<c:out value="${secRole.role_name}"/>');">&nbsp;
										<input type="button" class="button_2003" value="删除" onclick="onclick1( <c:out value="${secRole.hasUser}"/>,<c:out value="${secRole.role_id}"/>)"/>
									</c:when>
									<c:otherwise>
										<input type="button" class="button_2003" value="配置用户" disabled="disabled"/>&nbsp;
										<input type="button" class="button_2003" value="修改" disabled="disabled"/>&nbsp;
										<input type="button" class="button_2003" value="内置" disabled="disabled">&nbsp;
										<input type="button" class="button_2003" value="删除" disabled="disabled"/>
									</c:otherwise>
								</c:choose>
							</c:if>
							<c:if test="${secRole.is_delete}">
								<input type="button" class="button_2003" value="配置用户" disabled="disabled"/>&nbsp;
								<input type="button" class="button_2003" value="修改" disabled="disabled"/>&nbsp;
								<input type="button" class="button_2003" value="配置" disabled="disabled">&nbsp;
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
<script>
function onclick1(hasUser,role_id){
    var url="${ctx}/user/delsecrole.action?role_id="+role_id+"&subsys_code=${subsys_code}";
    if(hasUser==true || hasUser=='true'){
        if(confirm("该角色下已关联用户,删除角色将会删除用户对该角色的关联,确定删除吗?")){
           window.location.href =url; 
        }
    }else{
        if(confirm("该角色下无关联用户，确定删除吗？")){
	       window.location.href =url;
        }
    }
}
</script>
</body>
</html>
