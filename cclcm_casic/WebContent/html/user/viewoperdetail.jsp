<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title></title>
<link href="${ctx}/_css/css200603.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="${ctx}/_css/displaytag.css" type="text/css" media="screen"/>
<link rel="stylesheet" href="${ctx}/_css/my-web-control.jsp" type="text/css" media="screen"/>
</head>
	<body oncontextmenu="self.event.returnValue=false">
	<br/>
	<form action="${ctx}/user/delsecoper.action" target="_parent">
	<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_box">
		<tr>
			<td colspan="2" class="title_box">节点信息</td>
		</tr>
		<tr>
			<td width="30%" align="center">节点类型:</td>
			<td>&nbsp;${secOper.operType}</td>
		</tr>
		<tr>
			<td align="center">节点编码:</td>
			<td>&nbsp;${secOper.oper_code}</td>
		</tr>
		<tr>
			<td align="center">节点名称:</td>
			<td>&nbsp;${secOper.oper_name}</td>
		</tr>
		<tr>
			<td align="center">节点描述</td>
			<td>&nbsp;${secOper.oper_desc}</td>
		</tr>
	</table>
	</body>
</form>
