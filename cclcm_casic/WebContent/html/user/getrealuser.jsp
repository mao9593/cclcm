<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<base target="_self"/>
	<title>查看员工信息</title>
	<link rel="stylesheet" href="${ctx}/_css/displaytag.css" type="text/css" media="screen" />
    <link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript">
	<!--
		function clearFindForm(){
			queryform.base_username.value="";
			queryform.job_passnum.value = "";
			queryform.com_telephone.value = "";
		}
		function selectUserInfo(id,name){
			var realuser = new Object();
			realuser.id = id;
			realuser.name = name;
			window.returnValue = realuser;
			window.close();
		}
	//-->
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false" onload="onHover();">
<table border="0" cellspacing="0" cellpadding="0" class="table_only_border" width="100%" height="100%">
	<tr>
		<td colspan="2"  class="title_box">员工查询</td>
	</tr>
	<tr height="20"><td>
		<table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
		<form method="post" action="${ctx}/user/getrealuser.action" name="queryform">
			<tr>
				<td>姓名：</td>
				<td><input type="text" name="base_username" size="12" value="${base_username}"/></td>
				<td>员工编号：</td>
				<td><input type="text" name="job_passnum" size="12"/ value="${job_passnum}"></td>
		        <td>固定电话：</td>
				<td><input type="text" name="com_telephone" size="12" value="${com_telephone}"/></td>
		      	<td>
					<input type="button" value="清空" class="button_2003" onClick="clearFindForm()" />&nbsp;
					<input type="submit" class="button_2003" value="查询" />
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
				<display:table requestURI="${ctx}/user/getrealuser.action" uid="u" name="realUserList" class="displaytable" defaultsort="0" pagesize="15">
					<display:column title="员工ID" property="real_user_id" sortable="true" />
					<display:column title="姓名" property="base_username" />
					<display:column title="性别" >
						<c:if test="${u.base_sex == 'M'}">男</c:if>
						<c:if test="${u.base_sex == 'F'}">女</c:if>
					</display:column>
					<display:column title="员工号" property="job_passnum" sortable="true" />
					<display:column title="固定电话" property="com_telephone" sortable="true"/>
					<display:column title="移动电话" property="com_mobile" sortable="true"/>
					<display:column title="电子邮件" property="com_email" sortable="true"/>
					<display:column  title="操作">
						<c:if test="${!u.is_delete}">
							<input type="button" class="button_2003" value="选择" onclick="selectUserInfo('${u.real_user_id}','${u.base_username}');"/>
						</c:if>
						<c:if test="${u.is_delete}">
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