<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>查看个人流转纸质台账列表</title>
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
			addSelectAllCheckbox();
		});
	function preCalendarDay(){
		Calendar.setup({inputField: "start_time", button: "start_time_trigger", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d %H:%M:%S", cache: true, weekNumbers:true, showOthers: true, step: 1});
		Calendar.setup({inputField: "end_time", button: "end_time_trigger", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d %H:%M:%S", cache: true, weekNumbers:true, showOthers: true, step: 1});
	}	
	function clearFindForm(){
			LedgerQueryCondForm.paper_barcode.value = "";
			$("#seclv_med").val("");
			$("#paper_state_id").val("");
			LedgerQueryCondForm.start_time.value = "";
			LedgerQueryCondForm.end_time.value = "";
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
	function addJob(){
		var checked = $("input[name='_chk']:checked").size();
		var hidded = $("input[name='_chk'][type='hidden']").size();
		var event_ids = "";
		if(checked+hidded == 0){
			alert("请先勾选需要审批的作业");
			return false;
		}else{
			$("input[name='_chk']:checked").each(function (){
				event_ids += this.value+":";
			});
			$("input[name='_chk'][type='hidden']").each(function (){
				event_ids += this.value+":";
			});
			$("#event_ids").val(event_ids);
			$("#hid_form").submit();
		}
	}
	</script>
</head>

<body oncontextmenu="self.event.returnValue=false">
<form method="post" action="${ctx}/basic/addtransferprocessjob.action" id="hid_form">
	<input type="hidden" name="event_ids" value="${event_ids}" id="event_ids"/>
	<input type="hidden" name="event_code" id="event_code" />
	<input type="hidden" name="jobType" value="${jobType}"/>
	<input type="hidden" name="seclv_code" id="seclv_code"/> 
	<input type="hidden" name="user_iidd" value="${curUser.user_iidd}"/>
	<input type="hidden" name="one_cycle_type" id="one_cycle_type"/> 
	<input type="hidden" name="dept_id" value="${curUser.dept_id}"/>
	<input type="hidden" id="type" name="type" value="paper"/>
	<input type="hidden" name="actionContext" value="transfer/viewtransferpaperledger.action"/>
</form>
<form id="LedgerQueryCondForm" method="GET" action="${ctx}/transfer/viewtransferpaperledger.action">
	<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">个人流转纸质台账列表</td>
	</tr>
	<tr>
		<td align="right">
			<table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
					<tr>
				 		<td align="center">文件条码：</td>
				 		<td>
				 			<input type="text" id="paper_barcode" name="paper_barcode" value="${paper_barcode}" size="15"/>	
				 		</td>
				 		<td align="center">密级 ：</td>
				 		<td>
				 			<select name="seclv_code" id="seclv_med">
								<option value="">--所有--</option>
								<s:iterator value="#request.seclvList" var="seclv">
									<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
								</s:iterator>
							</select>
				 		</td>
				 		<td align="center">文件名：</td>
				 		<td>
				 			<input type="text" id="file_title" name="file_title" value="${file_title}"/>	
				 		</td>
				 		 <td align="center">
							&nbsp;<input name="button" type="submit" class="button_2003" value="查询" onclick="return checkDateTime();">&nbsp;
						</td>
				 	</tr>
				 	<tr>
				 		<td align="center">制作时间：</td>
				 		<td>
				        	<input type="text" name="start_time" readonly="readonly" style="cursor:hand;" value="${start_time}"/>
	     					<img src="${ctx}/_image/time2.jpg" id="start_time_trigger">&nbsp;
			    		</td> 
			    		<td align="center">至：</td>
			    		<td>
				        	<input type="text" name="end_time" readonly="readonly" style="cursor:hand;" value="${end_time}"/>
	     					<img src="${ctx}/_image/time2.jpg" id="end_time_trigger">&nbsp;
			    		</td>
			    		<td align="center">制作方式：</td>
						<td>
							<select name="create_type" id="create_type">
								<option value="">--所有--</option>
								<option value="PRINT" <c:if test="${create_type eq 'PRINT'}">selected</c:if>>打印</option>
								<option value="LEADIN" <c:if test="${create_type eq 'LEADIN'}">selected</c:if>>录入</option>
								<option value="COPY" <c:if test="${create_type eq 'COPY'}">selected</c:if>>复印</option>
								<option value="OUTCOPY" <c:if test="${create_type eq 'OUTCOPY'}">selected</c:if>>外来文件复印</option>
							</select>
						</td> 
				        <td align="center">
							&nbsp;<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">
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
	   					<display:table requestURI="${ctx}/transfer/viewtransferpaperledger.action" id="item" class="displaytable" name="paperLedgerList"
	   					pagesize="${pageSize}" sort="page" partialList="true" size="${totalSize}" excludedParams="*" form="LedgerQueryCondForm" decorator="decorator">
						<display:column property="checkbox" title="选择" >
						</display:column>	
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column property="user_name" title="制作人"/>
						<display:column property="file_title" title="文件名"/>
						<display:column property="paper_barcode" title="文件条码"/>
						<display:column property="seclv_name" title="文件密级"/>
						<display:column property="print_time" sortable="true" title="制作时间"/>
						<display:column property="paper_state_name" title="状态"/>
						<display:column title="操作">
							<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/ledger/viewpersonalpaperledgerinfo.action?paper_id=${item.paper_id}&seclv_code=${item.seclv_code} }');"/>&nbsp;
						</display:column>
					</display:table>
					</td>
				</tr>
			</table>
         </td>
	</tr>
	<tr>
		<td>
			<input type="button" value="申请流转" onclick="addJob();" class="button_2003"/>
		</td>
	</tr>
	</table>
</form>
</body>
</html>
