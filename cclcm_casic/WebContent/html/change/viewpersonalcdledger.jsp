<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>个人台帐列表</title>
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
			LedgerQueryCondForm.cd_barcode.value = "";
			$("#seclv_med").val("");
			LedgerQueryCondForm.start_time.value = "";
			LedgerQueryCondForm.end_time.value = "";
	}	
	function optionSelect(){
			$("#seclv_med").val(${seclv_code});
	}
	
	function addChangeJob(){
		var checked = $("input[name='_chk']:checked").size();
		var hidded = $("input[name='_chk'][type='hidden']").size();
		if(checked+hidded == 0){
			alert("请先勾选需要转换的光盘");
			return false;
		}else{
			$("#LedgerQueryCondForm").attr("action","${ctx}/change/addchangeprocessjob.action");
			$("#LedgerQueryCondForm").submit();
		}
	} 
	</script>
</head>

<body >
<form id="LedgerQueryCondForm" method="GET" action="${ctx}/change/viewpersonalcdledger.action">
	<input type="hidden" name="entity_type" value="cd"/>
	<input type="hidden" name="change_type" value="toDEPT"/>
	<input type="hidden" name="actionContext" value="change/viewpersonalcdledger.action"/>
	<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">申请个人光盘转为部门光盘</td>
	</tr>
	<tr>
		<td align="right">
			<table  table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<td align="center">光盘条码：
						<input type="text" id="cd_barcode" name="cd_barcode" value="${cd_barcode}" size="15"/>&nbsp;
					</td>
					<td align="center">密级：
						<select name="seclv_code" id="seclv_med">
							<option value="">--所有--</option>
							<s:iterator value="#request.seclvList" var="seclv">
								<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
							</s:iterator>
						</select>
					</td>					
					<td align="center">开始时间：					
						<input type="text" name="start_time" readonly="readonly" style="cursor:hand;" value="${start_time}"/>
        				<img src="${ctx}/_image/time2.jpg" id="start_time_trigger">
					</td>
					<td align="center">结束时间：
						<input type="text" name="end_time" readonly="readonly" style="cursor:hand;" value="${end_time}"/>
        				<img src="${ctx}/_image/time2.jpg" id="end_time_trigger">
					</td>
					<td align="center"> 
						<input name="button" type="submit" class="button_2003" value="查询" onclick="return checkDateTime();">&nbsp;&nbsp;
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
	   					<display:table requestURI="${ctx}/change/viewpersonalcdledger.action" id="item" class="displaytable" name="cdLedgerList"
	   					pagesize="${pageSize}" sort="page" partialList="true" size="${totalSize}" excludedParams="*" form="LedgerQueryCondForm" decorator="decorator">
						<display:column property="checkbox" title="选择"></display:column>						
						<display:column property="file_list" title="文件列表" maxLength="40"/>
						<display:column property="cd_barcode" title="光盘条码"/>
						<display:column property="seclv_name" title="密级"/>
						<display:column property="create_type_name" title="制作方式" sortable="true"/>	
						<display:column property="burn_time" title="制作时间" sortable="true"/>
						<display:column title="操作" style="width:50px">
							<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/ledger/viewledgerinfo.action?cd_id=${item.cd_id}');"/>
						</display:column>
					</display:table>
					</td>
				</tr>
			</table>
         </td>
	</tr>
	<tr>
		<td>
			<input type="button" value="提交申请" onclick="addChangeJob();" class="button_2003"/>
		</td>
	</tr>
	</table>
</form>
</body>
</html>
