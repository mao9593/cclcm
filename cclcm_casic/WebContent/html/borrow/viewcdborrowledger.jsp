<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>个人借用光盘台账列表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
		<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
	<link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
	<script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script>
	$(document).ready(function(){
		onHoverInfinite();
		optionSelect();
	});
	function optionSelect(){
		$("#seclv_code").val("${seclv_code}");
	}
	function clearFindForm(){
		LedgerQueryCondForm.cd_barcode.value = "";
		LedgerQueryCondForm.file_list.value = "";
		$("#seclv_code").val("");
	}	
	function exportLedger(formId,url,url1){
			document.getElementById(formId).action = url;
			document.getElementById(formId).submit();
			document.getElementById(formId).action = url1;
	}
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">个人借用光盘台账列表</td>
	</tr>
	<form id="LedgerQueryCondForm" method="GET" action="${ctx}/borrow/viewcdborrowledger.action">
	<tr>
		<td align="right">
			<table  table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<td width="10%" align="center">光盘编号：</td>
					<td width="18%">
						<input type="text" id="cd_barcode" name="cd_barcode" value="${cd_barcode}"/>&nbsp;
					</td>
					<td width="10%" align="center">文件列表：</td>
					<td width="18%">
						<input type="text" id="file_list" name="file_list" value="${file_list}"/>&nbsp;
					</td>
					<td width="10%" align="center">密级：</td>
					<td width="18%">
						<select name="seclv_code" id="seclv_code">
							<option value="">--所有--</option>
								<s:iterator value="#request.seclvList" var="seclv">
							<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
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
	   					<display:table requestURI="${ctx}/borrow/viewcdborrowledger.action" uid="item" class="displaytable" name="cdLedgerList"
	   							pagesize="${pageSize}" sort="page" partialList="true" size="${totalSize}" form="LedgerQueryCondForm" excludedParams="*">
							<display:column title="序号">
								<c:out value="${item_rowNum}"/>
							</display:column>	
							<display:column property="cd_barcode" title="光盘编号" maxLength="30"/>
							<display:column property="file_list" title="文件列表" maxLength="30"/>
							<display:column property="seclv_name" title="密级"/>
							<display:column property="user_name" title="制作人"/>
							<display:column property="dept_name" title="制作部门" maxLength="15"/>
							<display:column property="burn_time" sortable="true" title="产生时间"/>
							<display:column title="操作">
								<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/ledger/viewpersonalledgerinfo.action?cd_id=${item.cd_id}&seclv_code=${item.seclv_code}');"/>&nbsp;
							</display:column>
						</display:table>
					</td>
				</tr>
			</table>
         </td>
	</tr>
	<tr><td><input type="button" class="button_2003" value="导出EXCEL" onclick="exportLedger('LedgerQueryCondForm','${ctx}/ledger/exportcdborrowledger.action','${ctx}/borrow/viewcdborrowledger.action');"/></td></tr>
	
</table>	
</form>
</body>
</html>
