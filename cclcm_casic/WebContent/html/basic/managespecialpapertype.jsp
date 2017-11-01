<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>查看类型列表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script>
	<!--
		function deleteUsage(usage_code){
		    var url="${ctx}/basic/delusage.action?type=ajax&usage_code="+escape(usage_code);
		    if(confirm("确定停用该类型？")){
		    	xmlHttp.open("GET", url, true);
				xmlHttp.onreadystatechange = updatePage;
				xmlHttp.send(null);
		    }
		}
		function recoverUsage(usage_code){
		    var url="${ctx}/basic/recoverusage.action?type=ajax&usage_code="+escape(usage_code);
		    if(confirm("确定启用该类型？")){
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
		function clearFindForm(){
			$("#is_sealed").val("");
		}
	//-->
	</script>
</head>

<body oncontextmenu="self.event.returnValue=false">
<form method="POST" action="${ctx}/basic/managespecialpapertype.action" id="QueryCondForm" name="QueryCondForm" > 
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">类型列表</td>
	</tr>
	<tr>
		<td class="nav_box" align="right">
			状态：&nbsp;&nbsp;
	  		<select name="is_sealed" id="is_sealed">
                <option value="">--所有--</option>
				<option value="N" <c:if test="${is_sealed == 'N'}">selected="selected"</c:if>>已启用</option>
				<option value="Y" <c:if test="${is_sealed == 'Y'}">selected="selected"</c:if>>已停用</option>
            </select>&nbsp;&nbsp;
            <input name="button" type="submit" class="button_2003" value="查询">
			<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">&nbsp;&nbsp;&nbsp;&nbsp;
			<input name="button" type="button" class="button_2003" onClick="go('${ctx}/basic/addspecialpapertype.action');" value="增加新类型 ">
		</td>
	</tr>
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
					<display:table requestURI="${ctx}/basic/manageusage.action" id="item" class="displaytable" name="usageList" excludedParams="*" form="QueryCondForm">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column property="usage_code" title="类型特征值"/>
						<display:column property="usage_name" title="类型名称"/>
						<display:column property="usage_content" title="说明" maxLength="30"/>
						<display:column title="操作" style="width:100px">
							<c:if test="${!item.is_delete}">
								<input type="button" class="button_2003" value="修改" onclick="go('${ctx}/basic/updatespecialpapertype.action?usage_code=${item.usage_code}');"/>&nbsp;
								<input type="button" class="button_2003" value="停用" onclick="deleteUsage('${item.usage_code}')";/>
							</c:if>
							<c:if test="${item.is_delete}">
								<input type="button" class="button_2003" value="修改" disabled="disabled"/>&nbsp;
								<input type="button" class="button_2003" value="启用" onclick="recoverUsage('${item.usage_code}')";/>
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
