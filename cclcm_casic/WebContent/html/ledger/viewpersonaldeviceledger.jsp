<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>查看个人磁介质台账列表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
		<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
	<link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script>
	$(document).ready(function(){
			onHoverInfinite();
			optionSelect();
		});		
	function clearFindForm(){
			LedgerQueryCondForm.device_barcode.value = "";
			$("#seclv_med").val("");
			$("#device_status_id").val("");		
			$("#med_type_id").val("");	
	}	
	function optionSelect(){
			$("#seclv_med").val(${seclv_code});
			$("#device_status_id").val(${device_status});
			$("#med_type_id").val(${med_type});
	}
	function exportLedger(formId,url,url1){
			document.getElementById(formId).action = url;
			document.getElementById(formId).submit();
			document.getElementById(formId).action = url1;
	}
	</script>
	
</head>

<body oncontextmenu="self.event.returnValue=false">
<form id="LedgerQueryCondForm" method="GET" action="${ctx}/ledger/viewpersonaldeviceledger.action">
	<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">用户磁介质借入日志</td>
	</tr>
	<tr>
		<td align="right">
			<table  table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<td width="20%" align="center">条码号：
						<input type="text" id="device_barcode" name="device_barcode" value="${device_barcode}" size="15"/>&nbsp;
					</td>
					<td width="20%" align="center">密级：
						<select name="seclv_code" id="seclv_med">
						<option value="">--所有--</option>
						<s:iterator value="#request.seclvList" var="seclv">
							<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
						</s:iterator>
						</select>
					</td>
					<td width="20%" align="center">类型：
						<select name="med_type" id="med_type_id">
							<option value="">--所有--</option>				
							<s:iterator value="#request.deviceTypeList" var="type">
								<option value="${type.id}">${type.typename}</option>
							</s:iterator>
						</select>
					</td>
					<td width="20%" align="center">状态：
						<select name="device_status" id="device_status_id">
						    <option value="">--所有--</option>
							<s:iterator value="#request.dsList" var="status">
								<option value="${status.key}">${status.name}</option>
							</s:iterator>						
						</select>
					</td>
					<td align="center"> 
						<input name="button" type="submit" class="button_2003" value="查询">&nbsp;
						<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">
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
	   					<display:table requestURI="${ctx}/ledger/viewpersonaldeviceledger.action" id="item" class="displaytable" name="deviceLedgerList"
	   					pagesize="${pageSize}" sort="page" partialList="true" size="${totalSize}" excludedParams="*" form="LedgerQueryCondForm">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column property="borrow_user_name" title="借用人"/>
						<display:column property="device_barcode" title="条码号"/>
						<display:column property="device_name" title="介质名称" />
						<display:column property="seclv_name" title="密级"/>
						<display:column property="med_type_name" title="类型"/>
						<display:column property="device_status_name" title="状态"/>
						<display:column title="操作" style="width:50px">
							<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/ledger/viewcycledetail.action?type=DEVICE&barcode=${item.device_barcode}');"/>
						</display:column>
					</display:table>
					</td>
				</tr>
			</table>
         </td>
	</tr>	
	<tr><td><input type="button" class="button_2003" value="导出EXCEL" onclick="exportLedger('LedgerQueryCondForm','${ctx}/ledger/exportpersonaldeviceledger.action','${ctx}/ledger/viewpersonaldeviceledger.action');"/></td></tr>
		
	</table>
</form>
</body>
</html>
