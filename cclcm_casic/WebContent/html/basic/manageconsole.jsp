<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>查看控制台列表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script>
	$(document).ready(function(){
		onHover();
		optionSelect();
	});
	function clearFindForm(){
		$("#console_code").val("");
		$("#console_name").val("");
		$("#seclv_med").val("");
		$("#is_sealed").val("");
	}	
	function optionSelect(){
		$("#seclv_med").val(${seclv_code});
	}
	function deleteConsole(console_code){
	    var url="${ctx}/basic/delconsole.action?type=ajax&console_code="+escape(console_code);
	    if(confirm("确定停用该控制台？")){
	    	xmlHttp.open("GET", url, true);
			xmlHttp.onreadystatechange = updatePage;
			xmlHttp.send(null);
	    }
	}
	function recoverConsole(console_code){
	    var url="${ctx}/basic/recoverconsole.action?type=ajax&console_code="+escape(console_code);
	    if(confirm("确定启用该控制台？")){
		    xmlHttp.open("GET", url, true);
			xmlHttp.onreadystatechange = updatePage;
			xmlHttp.send(null);
	    }
	}
	function updatePage() {
		if (xmlHttp.readyState == 4) {
			  var response = xmlHttp.responseText;
			  QueryCondForm.submit();
		}
	}
	</script>
</head>

<body oncontextmenu="self.event.returnValue=false">
<form method="POST" action="${ctx}/basic/manageconsole.action" id="QueryCondForm" name="QueryCondForm">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">控制台列表</td>
	</tr>
	<tr>
		<td align="right">
			<table  table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<td align="center">
						控制台代号：<input type="text" id="console_code" name="console_code" value="${console_code}" size="15"/>
					</td>
					<td align="center">
						控制台名称：
						<input type="text" id="console_name" name="console_name" value="${console_name}" size="15"/>
					</td>
					<td align="center">
						密级：
						<select name="seclv_code" id="seclv_med">
							<option value="">--所有--</option>
							<s:iterator value="#request.seclvList" var="seclv">
								<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
							</s:iterator>
						</select>
					</td>
					<td align="center">
				  		状态：
				  		<select name="is_sealed" id="is_sealed">
			                <option value="">--所有--</option>
							<option value="N" <c:if test="${is_sealed == 'N'}">selected="selected"</c:if>>已启用</option>
							<option value="Y" <c:if test="${is_sealed == 'Y'}">selected="selected"</c:if>>已停用</option>
			            </select>
					</td>
					<td align="center" width="25%">
						<input name="button" type="submit" class="button_2003" value="查询">&nbsp;
						<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">&nbsp;&nbsp;&nbsp;&nbsp;
						<input name="button" type="button" class="button_2003" onClick="go('${ctx}/basic/addconsole.action');" value="增加新控制台 ">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input name="button" type="button" class="button_2003" onClick="go('${ctx}/basic/configconsoleexitpassword.action');" value="控制台退出密码设置 ">
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
					<display:table requestURI="${ctx}/basic/manageconsole.action" id="item" class="displaytable" name="consoleList" excludedParams="*" form="QueryCondForm">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column property="console_code" title="控制台代号"/>
						<display:column property="console_name" title="控制台名称"/>
						<display:column property="hardware_type" title="硬件类型"/>
						<display:column property="console_type_name" title="控制台类型"/>
						<display:column property="seclv_name" title="密级"/>
						<display:column property="console_location" title="控制台位置"/>
						<display:column property="curr_version" title="当前版本"/>
						<display:column title="操作" style="width:100px">
							<c:if test="${!item.is_delete}">
								<input type="button" class="button_2003" value="修改" onclick="go('${ctx}/basic/updateconsole.action?console_code=${item.console_code}');"/>&nbsp;
								<input type="button" class="button_2003" value="停用" onclick="deleteConsole('${item.console_code}')";/>
							</c:if>
							<c:if test="${item.is_delete}">
								<input type="button" class="button_2003" value="修改" disabled="disabled"/>&nbsp;
								<input type="button" class="button_2003" value="启用" onclick="recoverConsole('${item.console_code}')"/>
							</c:if>
						</display:column>
					</display:table>
					</td>
				</tr>
			</table>
         </td>
	</tr>
</table>
</form>
</body>
</html>
