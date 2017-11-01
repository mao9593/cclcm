<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>查看员工信息</title>
	<link rel="stylesheet" href="${ctx}/_css/displaytag.css" type="text/css" media="screen" />
    <link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>

	<script language="javascript">
		function clearFindForm(){
			queryform.base_username.value="";
			queryform.job_passnum.value = "";
			queryform.com_telephone.value = "";
			queryform.binduser.value="";
		}
		function delrealuser(real_user_id){
			//alert(real_user_id);
			if(confirm("确定删除该员工信息吗？")){
				window.location.href='${ctx}/user/delrealuser.action?real_user_id='+real_user_id;
				return true;
			}
			return false;
		}
		function showDialogr(url){
			return window.showModalDialog(url,'', 'dialogHeight:300px;dialogWidth:400px;center:yes;status:no;help:no;scroll:no;unadorned:no;resizable:no');
		}
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false" onload="onHover();">
<table border="0" cellspacing="0" cellpadding="0" class="table_only_border" width="95%" align="center">
	<tr>
		<td colspan="2"  class="title_box">员工查询</td>
	</tr>
	<tr height="20"><td>
		<table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
		<form method="post" action="${ctx}/user/viewrealuser.action" name="queryform">
			<tr>
				<td>姓名：</td>
				<td><input type="text" name="base_username" size="12" value="${base_username}"/></td>
				<td>员工编号：</td>
				<td><input type="text" name="job_passnum" size="12" value="${job_passnum}"/></td>
		        <td>固定电话：</td>
				<td><input type="text" name="com_telephone" size="12" value="${com_telephone}"/></td>
				<td>绑定状态：</td>
				<td><select name="binduser">
						<option value="">--所有--</option>
						<option value="N" <c:if test="${binduser eq 'N'.toString()}">selected</c:if>>无绑定用户</option>
						<option value="Y" <c:if test="${binduser eq 'Y'.toString()}">selected</c:if>>有绑定用户</option>
					</select></td>
		      	<td>
					<input type="submit" class="button_2003" value="查询" />&nbsp;
					<input type="button" value="清空" class="button_2003" onClick="clearFindForm()" />
				</td>
			</tr>
		</form>
		</table>
		</td>
	</tr>
	<tr>
		<td valign="top">
		<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
		    <tr>
		      <td>
				<display:table requestURI="${ctx}/user/viewrealuser.action" uid="realUser" name="realUserList" class="displaytable" defaultsort="0" pagesize="15">
					<display:column title="员工ID" property="real_user_id" sortable="true" />
					<display:column title="姓名" property="base_username" />
					<display:column title="性别" >
						<c:if test="${realUser.base_sex == 'M'}">男</c:if>
						<c:if test="${realUser.base_sex == 'F'}">女</c:if>
					</display:column>
					<display:column title="员工号" property="job_passnum" />
					<display:column title="固定电话" property="com_telephone" />
					<display:column title="移动电话" property="com_mobile" />
					<display:column title="电子邮件" property="com_email" />
					<display:column title="用户数" property="secUser_count"/>
					<display:column title="操作">
						<input type="button" value="用户" class="button_2003" onclick="showDialogr('${ctx}/user/viewsecuserbyreal.action?real_user_id=${realUser.real_user_id}')";/>&nbsp;
						<c:if test="${!realUser.is_delete}">
							<input type="button" value="修改" class="button_2003" onclick="window.location.href='${ctx}/user/updaterealuser.action?real_user_id=${realUser.real_user_id}';">&nbsp;
							<input type="button" value="删除" class="button_2003" 
								<c:choose>
									<c:when test="${realUser.secUser_count > 0}">disabled</c:when>
									<c:when test="${realUser.secUser_count == 0}"> onclick="return delrealuser('${realUser.real_user_id}');"</c:when>
								</c:choose> />
						</c:if>
						<c:if test="${realUser.is_delete}">
							<input type="button" value="修改" class="button_2003" disabled="disabled">&nbsp;
							<input type="button" value="封存" class="button_2003" disabled="disabled"/>
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