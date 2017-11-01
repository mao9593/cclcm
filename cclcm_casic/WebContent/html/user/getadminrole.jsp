<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<base target="_self"/>
	<title>查看三员角色(${subsys_name})</title>
	<link rel="stylesheet" href="${ctx}/_css/displaytag.css" type="text/css" media="screen" />
    <link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript">
	<!--
		function selectAdminRole(roleId,roleName){
			if(confirm("确定要把该用户配置为"+roleName+"?")){
				document.forms[0].role_id.value = roleId;
				document.forms[0].submit();
				return true;
			}
			return false;
		}
		function clearAdminRole(roleId,roleName){
			if(confirm("确定要清除该用户的特殊角色?")){
				document.forms[0].submit();
				return true;
			}
			return false;
		}
		
	//-->
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false" onload="onHover();">
<table border="0" cellspacing="0" cellpadding="0" class="table_only_border" width="100%" height="100%">
	<tr>
		<td colspan="2"  class="title_box">三员角色</td>
	</tr>
	<tr>
		<td valign="top">
		<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
		    <tr>
		      <td>
				<display:table requestURI="${ctx}/user/getadminrole.action" uid="role" name="adminRoleList" class="displaytable" defaultsort="0">
					<display:column title="角色名称" property="role_name" />
					<display:column title="角色类型" property="roleTypeName" />
					<display:column title="角色描述" property="role_desc" />
					<display:column title="操作" >
					<c:if test="${cfgedAdminRoleId eq role.role_id}">
						<font color="red">已分配</font>
					</c:if>
					<c:if test="${cfgedAdminRoleId != role.role_id}">
						<input type="button" class="button_2003" value="选择" onclick="selectAdminRole('${role.role_id}','${role.role_name}');"/>
					</c:if>
					</display:column>
		        </display:table>
		      </td>
		    </tr>
		</table>
		</td>
	</tr>
	<tr>
		<td align="center" class="bottom_box">
			<input type="button" class="button_2003" value="清除" onclick="clearAdminRole();"/>&nbsp;
			<input type="button" class="button_2003" value="关闭" onclick="window.close();"/>
		</td>
	</tr>
</table>
<form action="${ctx}/user/updateadminrole.action" method="post">
	<input type="hidden" name="user_iidd" value="${user_iidd}"/>
	<input type="hidden" name="subsys_code" value="${subsys_code}"/>
	<input type="hidden" name="role_id"/>
</form>
</body>
</html>