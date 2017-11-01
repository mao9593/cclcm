<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>个人闭环申请外发任务列表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
		<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
	<link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
	<script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script>
	$(document).ready(function(){
		onHoverInfinite();
		addSelectAllCheckbox();
		preCalendarDay();
	});
	function checkTime(){
		if($("input[name='startTime']").val() != "" && $("input[name='endTime']").val() != ""){
			var startTimeInput = $("input[name='startTime']").val();
			var endTimeInput = $("input[name='endTime']").val();
			var startYear = startTimeInput.substring(0,4);
			var endYear = endTimeInput.substring(0,4);
			var startTime = startTimeInput.substr(5,5)+"-"+startYear+startTimeInput.substr(10);
			var endTime = endTimeInput.substr(5,5)+"-"+endYear+endTimeInput.substr(10);
			var startLong = Date.parse(startTime);
			var endLong = Date.parse(endTime);
			if(startLong != NaN && endLong != NaN && startLong > endLong){
				alert("起止时间查询条件设置不合理，请修改");
				return false;
			}
		}
		$("#researchFlag").val("Y");
		return true;
	}
	function preCalendarDay(){
		Calendar.setup({inputField: "startTime", button: "startTime_trigger", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d %H:%M:%S", cache: true, weekNumbers:true, showOthers: true, step: 1});
		Calendar.setup({inputField: "endTime", button: "endTime_trigger", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d %H:%M:%S", cache: true, weekNumbers:true, showOthers: true, step: 1});
	}	
	function clearFindForm(){
			LedgerQueryCondForm.barcode.value = "";
			LedgerQueryCondForm.media_state.value = "";
			LedgerQueryCondForm.startTime.value = "";
			LedgerQueryCondForm.endTime.value = "";
	}	

	function confirmSendPaper(paper_barcode,send_num){
 		if(confirm("确定要确认外发？")){
			var url = "${ctx}/ledger/confirmsendpaper.action?updateFlag=Y&paper_barcode="+paper_barcode;
			callServer(url);
		}	 
	}
	function updateResult(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
				alert("该载体已外发");
				$("#LedgerQueryCondForm").submit();
			}else{
				document.getElementById("LedgerQueryCondForm").innerHTML="";
			}
	} 
	
	function confirmSendCD(cd_barcode,send_num){		
 		if(confirm("确定要确认外发？")){
			var url = "${ctx}/ledger/confirmsendcd.action?updateFlag=Y&cd_barcode="+cd_barcode;
			callServer(url);
		}	 
	}
	</script>
</head>

<body oncontextmenu="self.event.returnValue=false">
<form method="POST"  id="rejectform">
	<input type="hidden" name="updateFlag" id="updateFlag" value="Y"/>
	<input type="hidden" name="reject_ok" value="Y" />
	<input type="hidden" name="comment" id="comment"/>
	<input type="hidden" name="reject_type" id="reject_type"/>
</form>
<form id="LedgerQueryCondForm" method="GET" action="${ctx}/ledger/selfclosedsendprocess.action"> 
	<input type="hidden" name="seclv_code" id="seclv_code" value="6"/>
	<input type="hidden" id="researchFlag" name="researchFlag" value="${researchFlag}"/>
	<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">申请外发闭环任务列表</td>
	</tr>
	<tr>
		<td align="right">
			<table  table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<td align="center" width="8%">载体条码：</td>
					<td width="15%">
						<input type="text" id="barcode" name="barcode" value="${barcode}" size="18"/>&nbsp;
					</td>
					<td align="center">载体类型：</td>
					<td>
						<select name="media">
							<option value="paper" <c:if test="${media eq 'paper'}">selected</c:if>>文件</option>
							<option value="cd" <c:if test="${media eq 'cd'}">selected</c:if>>光盘</option>		
						</select>
					</td>
					<td align="center">开始时间：</td>
					<td>
						<input type="text" name="startTime" readonly="readonly" style="cursor:hand;" value="${startTime}"/>
        				<img src="${ctx}/_image/time2.jpg" id="startTime_trigger">
					</td>
					<td align="center">结束时间：</td>
					<td>
						<input type="text" name="endTime" readonly="readonly" style="cursor:hand;" value="${endTime}"/>
        				<img src="${ctx}/_image/time2.jpg" id="endTime_trigger">
					</td>
					<td align="center" >
						<input name="button" type="submit" class="button_2003" value="查询" onclick="return checkTime();">
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
						<c:if test="${media eq 'paper'}">
							<display:table requestURI="${ctx}/ledger/selfclosedsendprocess.action" id="item" class="displaytable" name="paperLedgerList"
	   						pagesize="${pageSize}" sort="page" partialList="true" size="${totalSize}" excludedParams="*" form="LedgerQueryCondForm">
							<display:column title="选择">
							<input type="checkbox" name="event_code" value="${item.paper_id}"/>
							</display:column>
							<display:column title="序号">
								<c:out value="${item_rowNum}"/>
							</display:column>
							<display:column property="paper_barcode" title="文件条码"/>
							<display:column property="duty_user_name" title="责任人"/>
							<display:column property="duty_dept_name" title="责任人部门"/>
							<display:column property="file_title" title="文件名" maxLength="35"/>
							<display:column property="seclv_name" title="文件密级"/>
							<display:column property="print_time" sortable="true" title="制作时间"/>						
							<display:column title="操作" style="width:150px">
								<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/ledger/viewcycledetail.action?type=PAPER&barcode=${item.paper_barcode}');"/>																			
								<input type="button" class="button_2003" value="外发" onclick="confirmSendPaper('${item.paper_barcode}','${item.send_num}');"/>&nbsp;	
							</display:column>
							</display:table>
						</c:if>
						<c:if test="${media eq 'cd'}">
							<display:table requestURI="${ctx}/ledger/selfclosedsendprocess.action" id="item" class="displaytable" name="cdLedgerList"
	   						pagesize="${pageSize}" sort="page" partialList="true" size="${totalSize}" excludedParams="*" form="LedgerQueryCondForm">
							<display:column title="选择">
								<input type="checkbox" name="event_code" value="${item.cd_id}"/>
							</display:column>
							<display:column title="序号">
								<c:out value="${item_rowNum}"/>
							</display:column>
							<display:column property="cd_barcode" title="光盘条码"/>
							<display:column property="duty_user_name" title="责任人"/>
							<display:column property="duty_dept_name" title="责任人部门"/>
							<display:column property="file_list" title="文件列表" maxLength="40"/>
							<display:column property="seclv_name" title="介质密级"/>
							<display:column property="burn_time" sortable="true" title="制作时间"/>
							<display:column property="cd_state_name" title="状态"/>
							<display:column title="操作" style="width:100px">
								<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/ledger/viewcycledetail.action?type=DISC&barcode=${item.cd_barcode}');"/>																			
								<input type="button" class="button_2003" value="外发" onclick="confirmSendCD('${item.cd_barcode}','${item.send_num}');"/>&nbsp;	
						</display:column>
					</display:table>
					</c:if>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<c:if test="${researchFlag eq 'WARING'}">
		<tr> 
			 <td align="center"><font style="color:red;font-size:15">请选择需要的查询条件，点击“查询”按钮查询！</font></td>	    	
		</tr>
	</c:if>
</table>
</form>
</body>
</html>
