<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>查看系统日志</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
<script>
<!--
	function clearFindForm(){
			LogQueryCondForm.subsys_name.value = "";
		}
//-->
</script>
</head>

<body oncontextmenu="self.event.returnValue=false" onload="onHover();">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">查看系统日志</td>
	</tr>
	<tr>
		<td class="nav_box" align="right">
		<form id="LogQueryCondForm" method="POST" action="${ctx}/log/viewsystemlog.action">
 			产品名称
			<input type="text" name="subsys_name" value="${subsys_name}"/>
			日志类型
			<select name="log_type">
			    <option value="0" selected>全部</option>
        		<option value="1" <c:if test="${log_type == 1}">selected</c:if>>一般消息</option>
        		<option value="2" <c:if test="${log_type == 2}">selected</c:if>>警告</option>
        		<option value="3" <c:if test="${log_type == 3}">selected</c:if>>严重</option>
        		<option value="4" <c:if test="${log_type == 4}">selected</c:if>>运维调试</option>
        	</select>
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
					<display:table requestURI="${ctx}/log/viewsystemlog.action" uid="log" class="displaytable" name="logList" pagesize="15" sort="list">
						<display:column title="序号">
							<c:out value="${log_rowNum}"/>
						</display:column>
						<display:column property="subsys_name" title="产品来源" style="whitespace: nowrap;" />
						<display:column property="source_module_str" title="模块来源" style="whitespace: nowrap;" />
						<display:column property="log_type_str" title="日志类型" style="whitespace: nowrap;" />
						<display:column property="log_detail" title="内容" style="whitespace: nowrap;" />
						<display:column property="log_time" title="记录时间" style="whitespace: nowrap;" />
					</display:table>
					</td>
				</tr>
			</table>
         </td>
	</tr>
</table>
</body>
</html>
