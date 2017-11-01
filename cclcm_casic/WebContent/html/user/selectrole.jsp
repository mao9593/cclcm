<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>选择审批角色</title>
	<link rel="stylesheet" href="${ctx}/_css/displaytag.css" type="text/css" media="screen" />
    <link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript">
	<!--
		/* function selectRole(roleId,roleName){
			var role = new Object();
			role.role_id = roleId;
			role.role_name = roleName;
			window.returnValue = role;
			window.close();
		} */
		function selectRoles(){
			var role_ids = "";
			var role_names = "";
			if($(":checkbox[name=roles][checked]").size()==0){
				alert("请先勾选角色");
				return false;
			}else{
				$(":checkbox[name=roles][checked]").each(function (){
					role_ids += this.id+"@";
					role_names += this.value+",";
				}); 
				var role = new Object();
				role.role_id = role_ids.substring(0, role_ids.length-1);
				role.role_name = role_names.substring(0, role_names.length-1);
				window.returnValue = role;
				window.close();
			}
		}
	//-->
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table border="0" cellspacing="0" cellpadding="0" class="table_only_border" width="100%" height="100%">
	<tr>
		<td colspan="2"  class="title_box">普通角色列表</td>
	</tr>
	<tr>
		<td valign="top">
		<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
		    <tr>
		      <td>
				<display:table uid="role" name="roleList" class="displaytable" defaultsort="0" >
					<display:column title="选择">
						<input type="checkbox" id="${role.role_id}" value="${role.role_name}" name="roles"/>
					</display:column>
					<display:column title="角色名称" property="role_name" />
					<display:column title="角色描述" property="role_desc" />
					<%-- <display:column title="操作" >
						<input type="button" class="button_2003" value="选择" onclick="selectRole('${role.role_id}','${role.role_name}');"/>
					</display:column> --%>
		        </display:table>
		      </td>
		    </tr>
		    <tr>
		        <td colspan="3" align="center" class="bottom_box">
		            <input type="button" value="确定" class="button_2003" onclick="return selectRoles()">&nbsp;
		            <input type="button" value="关闭" class="button_2003" onclick="window.close();"/>
		        </td>
		    </tr>
		</table>
		</td>
	</tr>
</table>
</body>
</html>