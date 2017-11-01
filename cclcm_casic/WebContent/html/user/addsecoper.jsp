<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>新增功能节点</title>
<link href="<%=request.getContextPath()%>/_css/css200603.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/_css/displaytag.css" type="text/css" media="screen"/>
<script language="JavaScript" src="<%=request.getContextPath()%>/_script/function.js"></script>
<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
<script language="javascript">
    <!--
function checkForm() {
 	if(document.all.oper_name.value == 0) {
		alert("请填写节点名称!");
		document.all.oper_name.focus();
		return false;
  	}
  	if(!specialCharFilter(document.all.oper_name.value)){
		alert("节点名称包含特殊字符");
		document.all.oper_name.focus();
		return false;
	}
  	return true;
}
function onHover(){
	var list=document.getElementsByTagName('input');
	for(i=0;i<list.length;i++){
		input = list[i];
		if(input.getAttribute('readonly')){
		}else if(input.getAttribute('type')=='text'){
			input.onfocus = new Function('this.style.backgroundColor="#ede4cd";');
			input.onblur = new Function('this.style.backgroundColor="";');
		}else if(input.getAttribute('type')=='password'){
			input.onfocus = new Function('this.style.backgroundColor="#ede4cd";');
			input.onblur = new Function('this.style.backgroundColor="";');
		}
	}
	for(i=0;i<list.length;i++){
		input = list[i];
		if(input.getAttribute('type')=='text' || input.getAttribute('type')=='password'){
			if(!input.getAttribute('readonly') && !input.getAttribute('disabled')){
				input.focus();
				break;
			}
		}
	}
}
  //-->
</script>
</head>
<body oncontextmenu="self.event.returnValue=false" onload="onHover();">
<br>
<form action="${ctx}/user/addsecoper.action" target="_parent" method="post">
	<input type="hidden" name="subsys_code" value="${subsys_code}"/>
	<input type="hidden" name="en_directory" value="${en_directory}"/>
	<input type="hidden" name="oper_code" value="${newOper_code}"/>
	<input type="hidden" name="insert" value="Y"/>
	<table width="60%" border="0" align="center" cellpadding="0" cellspacing="0" class="table_box">
		<tr>
			<td colspan="2" class="title_box">节点添加</td>
		</tr>
		<tr>
			<td nowrap="nowrap">父节点编码:</td>
			<td>${oper_code}</td>
		</tr>
		<tr>
			<td>节点类型:</td>
			<td>&nbsp;${operType}</td>
		</tr>
		<tr>
			<td>节点编码:</td>
			<td>&nbsp;${newOper_code}</td>
		</tr>
		<tr>
			<td style="color:red">节点名称:</td>
			<td><input type="text" name="oper_name" size="30"/></td>
		</tr>
		<tr>
			<td>WEB端标记:</td>
			<td><input type="text" name="web_mark" size="30"/></td>
		</tr>
		<tr>
			<td>服务端标记:</td>
			<td><input type="text" name="server_mark" size="30"/></td>
		</tr>
		<tr>
			<td>访问路径:</td>
			<td><input type="text" name="web_url" size="30"/></td>
		</tr>
		<tr>
			<td>图标路径:</td>
			<td><input type="text" name="icon_path" size="30"/></td>
		</tr>
		<tr>
			<td>节点描述:</td>
			<td><input type="text" name="oper_desc" size="30"/></td>
		</tr>
		<tr>
			<td colspan="2" class="bottom_box">
				<input name="Input" type="button" class="button_2003" value="添加" onClick="if(checkForm()) forms[0].submit();"/>
				&nbsp;<input name="Input" type="Reset" class="button_2003" value="重置"/>
				&nbsp;<input name="Input" type="button" class="button_2003" value="返回" onclick="javascript:history.go(-1);"/></td>
		</tr>
	</table>
</form>
</body>
</html>