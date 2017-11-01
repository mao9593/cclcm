<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>打印机管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
</head>
<body oncontextmenu="self.event.returnValue=false" onload="onHover();">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">打印机管理</td>
	</tr>
	<tr>
		<td class="nav_box" align="right">
		<form id="RoleQueryCondForm" method="POST" action="${ctx}/print/viewprinter.action">
 			打印机名称
			<input type="text" name="printer_name" value="${printer_name}"/>
			<input name="button" type="submit" class="button_2003" value="查询">
			<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">
			<input name="button" type="button" class="button_2003" onClick="go('${ctx}/print/addprinter.action');" value="增加打印机">
 		</form>
		</td>
	</tr>
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
					<display:table requestURI="${ctx}/print/viewprinter.action" uid="printer" class="displaytable" name="sysPrinterList" pagesize="15" sort="list">
						<display:column title="序号" sortable="true">
							<c:out value="${printer_rowNum}"/>
						</display:column>
						<display:column title="打印机名称" style="whitespace: nowrap;" property="printer_name"/>
					</display:table>
					</td>
				</tr>
			</table>
         </td>
	</tr>
</table>
</body>
</html>
