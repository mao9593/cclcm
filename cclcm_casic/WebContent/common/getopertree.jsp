<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<meta http-equiv='Content-Type' content='text/html; charset=gb2312'>
<link rel="StyleSheet" href="${ctx}/_css/css200603.css" type="text/css"/>
<script type="text/javascript" src="${ctx}/_script/tree/dtree.js"></script>
<link rel="stylesheet" href="${ctx}/_css/displaytag.css" type="text/css" media="screen"/>
<script language="javascript">
	function changeSubsys() {
		document.forms[0].submit();
	}
</script>
</head>

<body style="margin-left: 3px;margin-top: 3px;" oncontextmenu="self.event.returnValue=false">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td height=1 class=title>功能管理</td>
	</tr>
	<tr>
		<td class="hr"></td>
	</tr>
</table>
<br>
<table>
	<tr>
		<td>子系统选择:</td>
	</tr>
	<tr>
		<td>
			<form action="${ctx}/user/getopertree.action" method="post">
				<input type="hidden" name="config" value="${config}"/>
				<select name="subsys_code" onchange="changeSubsys();">
					<c:forEach var="item" items="${subsysList}" varStatus="status">
						<option value="${item.subsys_code}" <c:if test="${item.subsys_code==subsys_code}">selected</c:if>>${item.subsys_name}</option>
					</c:forEach>
				</select>
			</form>
		</td>
	</tr>
</table>

<div id="Layer1" style="white-space: nowrap;">
	<br><a href="javascript: tree.openAll();">打开全部</a> | <a href="javascript: tree.closeAll();">关闭全部</a>	<br>

	<!-- 准备树 -->
	<script type="text/javascript" language="JScript">
		var tree = new dTree('tree', '${ctx}/_image/');
        tree.add("__x",-1,'功能树图');
		<c:forEach var="item" items="${operTreeList}" varStatus = "status">
			var temp = "__x";
			<c:if test="${not empty item.oper_parent_code}">
				temp = '<c:out value="${item.oper_parent_code}"/>';
			</c:if>
			<c:if test="${config eq 'Y'}">
				tree.add("${item.oper_code}",temp,"${item.oper_name}",
					"javascript:window.location=\'${ctx}/user/configoper.action?oper_code=${item.oper_code}&subsys_code=${subsys_code}\'",
					"${item.oper_desc}","workframe")
			</c:if>
			<c:if test="${config eq 'N'}">
				tree.add("${item.oper_code}",temp,"${item.oper_name}",
					"javascript:window.location=\'${ctx}/user/viewoperdetail.action?oper_code=${item.oper_code}&subsys_code=${subsys_code}\'",
					"${item.oper_desc}","workframe")
			</c:if>
		</c:forEach>
		document.write(tree);
	</script>
</div>
</body>
</html>