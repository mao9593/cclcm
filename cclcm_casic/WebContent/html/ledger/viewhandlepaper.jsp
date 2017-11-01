<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>查看待归档纸质列表</title>
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
			LedgerQueryCondForm.paper_barcode.value = "";
			LedgerQueryCondForm.duty_user_name.value = "";
			$("#seclv_med").val("");
			LedgerQueryCondForm.start_time.value = "";
			LedgerQueryCondForm.end_time.value = "";
			LedgerQueryCondForm.paper_state_id.value = "";
	}	
	function optionSelect(){
			$("#seclv_med").val(${seclv_code});
			$("#paper_state_id").val(${paper_state});
	}
	function exportLedger(formId,url,url1){
			document.getElementById(formId).action = url;
			document.getElementById(formId).submit();
			document.getElementById(formId).action = url1;
	}
	
	function addHandleJob(){
		if(confirm("您确认归档当前纸张？")){
			var event_codes = "";
			if($(":checkbox:checked[value!='']").size()>0){
				$(":checkbox:checked[value!='']").each(function (){
					event_codes += this.value+",";
				});
				$("#event_codes").val(event_codes);
				$("#hid_form").submit();
			}else{
				alert("请先勾选需要申请归档的纸张");
				return false;
			}
		}
	}
	</script>
</head>

<body oncontextmenu="self.event.returnValue=false">
<form method="post" action="${ctx}/ledger/handlepaper.action" id="hid_form">
	<input type="hidden" name="event_codes" id="event_codes"/> 
</form>
<form id="LedgerQueryCondForm" method="POST" action="${ctx}/ledger/viewhandlepaper.action">
	<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">待归档纸质列表</td>
	</tr>
	<tr>
		<td align="right">
			<table  table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<td>文件条码：
					</td>
					<td>
						<input type="text" id="paper_barcode" name="paper_barcode" value="${paper_barcode}" size="15"/>&nbsp;
					</td>
					<td>责任人：
					</td>
					<td>
						<input type="text" id="duty_user_name" name="duty_user_name" value="${duty_user_name}" size="15"/>&nbsp;
					</td>
					<td>密级：
					</td>
					<td>
						<select name="seclv_code" id="seclv_med">
						<option value="">--所有--</option>
						<s:iterator value="#request.seclvList" var="seclv">
							<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
						</s:iterator>
						</select>
					</td>
				</tr>
				<tr>
						<td>开始时间：
						</td>
					<td>
						<input type="text" name="start_time" readonly="readonly" style="cursor:hand;" value="${start_time}"/>
        				<img src="${ctx}/_image/time2.jpg" id="start_time_trigger">
					</td>
					<td>结束时间：
					</td>
					<td>
						<input type="text" name="end_time" readonly="readonly" style="cursor:hand;" value="${end_time}"/>
        				<img src="${ctx}/_image/time2.jpg" id="end_time_trigger">
					</td>
					<td colspan="2"> 
						<input name="button" type="submit" class="button_2003" value="查询" onclick="return checkTime();">
						<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">
					</td>
				</tr>
			</table>
		</td>
		<tr>
    </tr>
	</tr>
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
						<display:table requestURI="${ctx}/ledger/viewhandlepaper.action" id="item" class="displaytable" name="paperLedgerList"
	   					pagesize="${pageSize}" sort="page" partialList="true" size="${totalSize}" excludedParams="*" form="LedgerQueryCondForm">
						<display:column title="选择">
							<input type="checkbox" name="event_code" value="${item.paper_id}"/>
						</display:column>
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column property="duty_user_name" title="责任人"/>
						<display:column property="dept_name" title="部门"/>
						<display:column property="paper_barcode" title="文件条码"/>
						<display:column property="seclv_name" title="文件密级"/>
						<display:column property="print_time" sortable="true" title="打印时间"/>
						<display:column property="user_name" title="申请人"/>
						<display:column property="paper_state_name" title="状态"/>
					</display:table>
					</td>
				</tr>
				<tr>
				<td>
					<input type="button" class="button_2003" value="批量归档" onclick="addHandleJob();"/>&nbsp;
				</td>
				</tr>
			</table>
         </td>
	</tr>
	</table>
</form>
</body>
</html>
