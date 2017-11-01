<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<base target="_self">
	<title>选择用户</title>
	<link rel="stylesheet" href="${ctx}/_css/displaytag.css" type="text/css" media="screen" />
    <link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript">
		function choose(user_iidd,user_name){
			var rValue=new Object();
			rValue.user_iidd=user_iidd;
			rValue.user_name=user_name;
			window.returnValue=rValue;
			window.close();
		}
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
	<form method="post" action="${ctx}/change/chooseviewuserbydept.action" name="SecUserQueryCondForm" id="SecUserQueryCondForm">
	<input type="hidden" name="dept_id" value="${dept_id}"/>
	<input type="hidden" name="dept_ids" value="${dept_ids}"/>
		<display:table id="user" name="secUserList" class="displaytable" pagesize="${pageSize}" sort="page" 
				partialList="true" size="${totalSize}" excludedParams="*" form="SecUserQueryCondForm">
			<display:column title="登录名称">
				<c:if test="${user.status == 1}">
					<img src="${ctx}/_image/iconx32/stop1.gif" />
				</c:if>
				<c:out value="${user.user_iidd}"/>
			</display:column>
			<display:column  title="用户名称" property="user_name"/>
			<display:column title="部门" property="dept_name" />
			<display:column title="角色" property="roleNameStr" maxLength="10"/>
			<display:column title="涉密人员类别" property="security_name"/>
			<display:column title="操作">
				<button onclick="choose('${user.user_iidd}','${user.user_name}')" class="button_2003">选择</button>
			</display:column>
		</display:table>
	</form>
</body>
</html>