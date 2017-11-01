<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title></title>
<link href="${ctx}/_css/css200603.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="${ctx}/_css/displaytag.css" type="text/css" media="screen"/>
<link rel="stylesheet" href="${ctx}/_css/my-web-control.jsp" type="text/css" media="screen"/>
<script language="javascript">
function go1(src) {
	parent.leftframe.location.href = src;
}
function go(url) {
	window.location.href = url;
}

function confirmdel() {
	var haveSub = "${haveSub}";
	if (event.srcElement.value == "删除当前节点") {
		document.forms[0].deltype.value = "";
		if (haveSub == "N") {
			return(confirm("您确认删除当前节点？"));
		} else {
			alert("您只能删除叶子节点");
			return false;
		}
	} else {
		document.forms[0].deltype.value = "class";
		return(confirm("您确认删除当前节点和所有子节点？"));

	}
}
</script>
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
			<td align="center">WEB端标记:</td>
			<td>&nbsp;${secOper.web_mark}</td>
		</tr>
		<tr>
			<td align="center">服务端标记:</td>
			<td>&nbsp;${secOper.server_mark}</td>
		</tr>
		<tr>
			<td align="center">访问路径:</td>
			<td>&nbsp;${secOper.web_url}</td>
		</tr>
		<tr>
			<td align="center">图标路径:</td>
			<td>&nbsp;${secOper.icon_path}</td>
		</tr>
		<tr>
			<td align="center">节点描述</td>
			<td>&nbsp;${secOper.oper_desc}</td>
		</tr>
		<tr>
			<td colspan="2" class="bottom_box">
				<input type="hidden" name="oper_code" value="${oper_code}"/>
				<input type="hidden" name="subsys_code" value="${subsys_code}"/>
				<input type="hidden" name="deltype"/>
				<input name="add" type="button" class="button_2003" value="添加子节点" onClick="go('${ctx}/user/addsecoper.action?oper_code=${oper_code}&subsys_code=${subsys_code}');">&nbsp;
				<input name="update" type="button" class="button_2003" value="修改节点" onClick="go('${ctx}/user/updatesecoper.action?oper_code=${oper_code}&subsys_code=${subsys_code}');">&nbsp;
				<input name="del" type="button" class="button_2003" value="删除当前节点" onClick="if(confirmdel()) forms[0].submit();">&nbsp;
				<input name="delClass" type="button" class="button_2003" value="级联删除" onClick="if(confirmdel()) forms[0].submit();">&nbsp;
			</td>
		</tr>
	</table>
	</body>
</form>
