<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>查看光盘信息详情</title>
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
			preCalendarDay();
			optionSelect();
		});
		
	function preCalendarDay(){
		Calendar.setup({inputField: "start_time", button: "start_time_trigger", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d %H:%M:%S", cache: true, weekNumbers:true, showOthers: true, step: 1});
		Calendar.setup({inputField: "end_time", button: "end_time_trigger", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d %H:%M:%S", cache: true, weekNumbers:true, showOthers: true, step: 1});
	}	
	function clearFindForm(){
			LedgerQueryCondForm.cd_barcode.value = "";
			LedgerQueryCondForm.cd_state_id.value = "";
			LedgerQueryCondForm.start_time.value = "";
			LedgerQueryCondForm.end_time.value = "";
			$("#seclv_med").val("");
	}	
	function optionSelect(){
			$("#seclv_med").val(${seclv_code});
			$("#cd_state_id").val(${cd_state});
	}
	function exportLedger(formId,url,url1){
			document.getElementById(formId).action = url;
			document.getElementById(formId).submit();
			document.getElementById(formId).action = url1;
	}
	function exportLedger(formId,url,url1){
			document.getElementById(formId).action = url;
			document.getElementById(formId).submit();
			document.getElementById(formId).action = url1;
	}
	function addBurnJob(id){
		$("#id").val(id);
		$("#hid_event_form").submit();
	}
	</script>
</head>

<body oncontextmenu="self.event.returnValue=false">
<form method="post" action="${ctx}/transfer/addtransferevent.action" id="hid_event_form">
	<input type="hidden" name="id" value="${cd_id}" id="id"/>
	<input type="hidden" name="event_code" id="event_code" value="${event_code}"/>
</form>
<form id="LedgerQueryCondForm" method="POST" action="${ctx}/ledger/viewpersonalledger.action">
	<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">查看光盘信息详情</td>
	</tr>
	<tr>
		<td class="nav_box" align="right">
 			光盘编号：<input type="text" id="cd_barcode" name="cd_barcode" value="${cd_barcode}" size="10"/>&nbsp;
 			状态：
 			<select name="cd_state" id="cd_state_id">
							<option value="">--请选择--</option>
							<option value="0">留用</option>
							<option value="1">已回收</option>
							<option value="2">已外发</option>
							<option value="3">已归档</option>
							<option value="4">已销毁</option>
			</select>
			&nbsp;
			密级：
			<select name="seclv_code" id="seclv_med">
				<option value="">--所有--</option>
				<s:iterator value="#request.seclvList" var="seclv">
					<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
				</s:iterator>
			</select>
			起止时间：
			<input type="text" name="start_time" readonly="readonly" style="cursor:hand;" value="${start_time}"/>
        	<img src="${ctx}/_image/time2.jpg" id="start_time_trigger">
        	&nbsp;&nbsp;至&nbsp;&nbsp;
			<input type="text" name="end_time" readonly="readonly" style="cursor:hand;" value="${end_time}"/>
        	<img src="${ctx}/_image/time2.jpg" id="end_time_trigger">
        	
			<input name="button" type="submit" class="button_2003" value="查询" onclick="return checkTime();">
			<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">
		</td>

	</tr>
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
	   					<display:table requestURI="${ctx}/ledger/viewpersonalledger.action" id="item" class="displaytable" name="cDLedgerList"
	   					pagesize="${pageSize}" sort="page" partialList="true" size="${totalSize}" excludedParams="*" form="LedgerQueryCondForm">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column property="user_name" title="申请人"/>
						<display:column property="cd_barcode" title="光盘编号"/>
						<display:column property="seclv_name" title="介质密级"/>
						<display:column property="burn_time" sortable="true" title="刻录时间"/>
						<display:column property="burn_machine" title="刻录机器"/>
						<display:column property="cd_state_name" title="状态"/>
						<display:column title="操作">
							<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/transfer/viewpersonalledgerinfo.action?cd_id=${item.cd_id}&seclv_code=${item.seclv_code} }');"/>&nbsp;
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
