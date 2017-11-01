<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<title>部门岗位配置</title>
<link rel="stylesheet" href="${ctx}/_css/displaytag.css" type="text/css" media="screen"/>
<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen"/>
<script language="javascript" src="${ctx}/_script/grobal_val.js"></script>
<script language="javascript" src="${ctx}/_script/selectDiv.js"></script>
<script language="javascript">
function returnClick(){
	document.form1.action="${ctx}/user/managedept.action";
	document.form1.target="mainFrame";
	document.form1.submit();
}
</script>
</head>
<body>
<form action="${ctx}/user/managedeptpost.action" name="form1" method="post">
	<input type="hidden" name="dept_id" value="${dept_id}"/>
	<input type="hidden" name="update" value="Y"/>
	<table border="0" cellspacing="0" cellpadding="0" class="table_only_border" width="100%">
		<tr>
			<td colspan="2" class="title_box">
				部门岗位配置 -- ${secDept.dept_name}</td>
		</tr>
		<tr>
			<td valign="top">
				<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
					<tr>
						<td align="center">
							<table class="table_box" defaultsort="0" cellpadding="0" cellspacing="1" border="1" width="80%">
								<tr style="FONT-WEIGHT: bold;FONT-SIZE: 10pt;">
									<td width="25%" align="center">岗位</td>
									<td width="25%" align="center">描述</td>
									<td width="25%" align="center">级别</td>
									<td width="25%" align="center">状态</td>
								</tr>
								<tr><td class="nav_box" colspan="4" align="center">已配置</td></tr>
								<c:forEach var="item" items="${inDeptPostList}" varStatus="status">
									<tr>
										<td align="center">&nbsp;${item.post_name}</td>
										<td align="center">&nbsp;${item.post_desc}</td>
										<td align="center">&nbsp;${item.post_class}</td>
										<td align="center">
											<input type="checkbox" name="selectedPostIdList" checked="checked" value="${item.post_id}"
											<c:if test="${item.user_count > 0}">disabled</c:if> />
											<c:if test="${item.user_count > 0}"><input type="hidden" name="selectedPostIdList" value="${item.post_id}"/></c:if>										
										</td>
									</tr>
								</c:forEach>
							</table>
							<table class="table_box" defaultsort="0" cellpadding="0" cellspacing="1" border="1" width="80%">
								<tr><td class="nav_box" colspan="4" align="center">未配置</td></tr>
								<c:forEach var="item" items="${outDeptPostList}" varStatus="status">
									<tr>
										<td width="25%" align="center">${item.post_name}</td>
										<td width="25%" align="center">${item.post_desc}</td>
										<td width="25%" align="center">${item.post_class}</td>
										<td width="25%" align="center"><input type="checkbox" name="selectedPostIdList" value="${item.post_id}"/>
										</td>
									</tr>
								</c:forEach>
								<tr>
									<td colspan="4" class="bottom_box">
										<input type="submit" class="button_2003" value="提交"/>&nbsp;
										<input type="button" class="button_2003" value="返回" onclick="returnClick();"/>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</form>
</body>
</html>