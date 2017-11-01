<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>查看个人存储介质列表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>   
	<script>
	<!--
	$(document).ready(function(){
		onHoverInfinite();
		optionSelect();
	});		
	function clearFindForm(){
		$("#storage_name").val("");
		$("#storage_barcode").val("");
		$("#seclv_code").val("");
		$("#med_type").val("");
	}	
	function optionSelect(){
		$("#seclv_code").val("${seclv_code}");
		$("#med_type").val("${med_type}");
	}
	function exportLedger(formId,url,url1){
			document.getElementById(formId).action = url;
			document.getElementById(formId).submit();
			document.getElementById(formId).action = url1;
	}
	</script>
</head>

<body oncontextmenu="self.event.returnValue=false">
<form id="QueryCondForm" method="GET" action="${ctx}/storage/viewselfstorage.action">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">查看个人存储介质列表</td>
	</tr>	
	<tr>
		<td>
			<table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<td width="25%" align="center">载体条码号：
						<input type="text" id="storage_barcode" name="storage_barcode" value="${storage_barcode}" size="15"/></td>
					<td width="20%" align="center">介质名称：				
						<input type="text" id="storage_name" name="storage_name" value="${storage_name}" size="15"/></td>
					<td width="20%" align="center">密级：
						<select name="seclv_code" id="seclv_code">
							<option value="">--所有--</option>
							<s:iterator value="#request.seclvList" var="seclv">
								<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
							</s:iterator>
						</select>
					</td>
					<td width="20%" align="center">类型：
						<select name="med_type" id="med_type">
							<option value="">--所有--</option>				
							<option value="1">硬盘</option>
							<option value="2">存储卡</option>
							<option value="3">其他</option>							
						</select>
					</td>
					<td align="center">
						&nbsp;<input name="button" type="submit" class="button_2003" value="查询">&nbsp;
						&nbsp;<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">&nbsp;
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
					<display:table requestURI="${ctx}/storage/viewselfstorage.action" id="item" class="displaytable" name="storageList" pagesize="15" sort="list">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column property="storage_name" title="介质名称"  maxLength="15"/>
						<display:column property="dept_name" title="所属部门" maxLength="15"/>
						<display:column property="storage_barcode" title="条码号"  maxLength="30"/>
						<display:column property="med_type_name" title="类型"  maxLength="10"/>
						<display:column property="seclv_name" title="密级"/>
						<display:column property="storage_status_name" title="状态"  maxLength="10"/>
						<display:column property="duty_user_name" title="责任人"/>						
						<display:column property="use_user_name" title="当前使用人"/>
					</display:table>
					</td>
				</tr>
			</table>
         </td>
	</tr>
	<tr><td><input type="button" class="button_2003" value="导出EXCEL" onclick="exportLedger('QueryCondForm','${ctx}/ledger/exportselfstorage.action','${ctx}/storage/viewselfstorage.action');"/></td></tr>
	
</table>
</form>
</body>
</html>
