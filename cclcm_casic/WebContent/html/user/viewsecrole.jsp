<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>查看安全角色</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
<script>
<!--
	
	function showDialogr(url){
		return window.showModalDialog(url,'', 'dialogHeight:400px;dialogWidth:800px;center:yes;status:no;help:no;scroll:no;unadorned:no;resizable:no');
	}
	function clearFindForm(){
			RoleQueryCondForm.role_name.value = "";
		}
//-->
</script>
</head>

<body oncontextmenu="self.event.returnValue=false" onload="onHover();">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">查看安全角色</td>
	</tr>
	<tr>
		<td class="nav_box" align="right">
		<form id="RoleQueryCondForm" method="POST" action="${ctx}/user/viewsecrole.action">
 			选择子系统
 			<c:set var="code1" value="${subsys_code}" scope="request"/>
      		<select name="subsys_code" class="MySelect">
         		<s:iterator value="#request.subsysList" var="item">
					<c:set var="code2" value="${item.subsys_code}" scope="request"/>
					<option value="${item.subsys_code}" <s:if test="#request.code2==#request.code1">selected="selected"</s:if> >${item.subsys_name}</option>
				</s:iterator>
     		</select>
 			&nbsp;&nbsp;角色名称
			<input type="text" name="role_name" value="${role_name}"/>
			<input name="button" type="submit" class="button_2003" value="查询">
			<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">
 		</form>
		</td>
	</tr>
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
					<display:table requestURI="${ctx}/user/viewsecrole.action" uid="secRole" class="displaytable" name="secRole" pagesize="15" sort="list">
						<display:column title="序号" >
							<c:out value="${secRole_rowNum}"/>
						</display:column>
						<display:column title="角色名称" style="whitespace: nowrap;" >
							<c:out value="${secRole.role_name}" />
						</display:column>
						<display:column property="roleTypeName" title="角色类型" style="whitespace: nowrap;" nulls="false" />
						<display:column property="role_desc" title="角色描述" style="whitespace: nowrap;" />
						<display:column title="操作">
							&nbsp;<input type="button" class="button_2003" value="查看用户" onclick="showDialogr('${ctx}/user/viewuserbyrole.action?role_id=<c:out value="${secRole.role_id}"/>');">
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
    var url="${ctx}/user/delsecrole.action?role_id="+role_id;
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
