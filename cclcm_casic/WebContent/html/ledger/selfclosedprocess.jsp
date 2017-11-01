<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>个人闭环任务列表</title>
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

	function destoryPaperJob(){
		if(confirm("确定要销毁当前文件？")){
			var event_codes = "";
			if($(":checkbox:checked[value!='']").size()>0){
				$(":checkbox:checked[value!='']").each(function (){
					event_codes += this.value+",";
				});
				$("#event_code").val(event_codes);
				//alert("event_code:"+$("#event_code").val());
				$("#hid_form_paper").submit();
			}else{
				alert("请先勾选需要申请销毁的文件");
				return false;
			}
		}
	}
	
	function destoryCDJob(){
				
		if(confirm("确定要销毁当前光盘？")){
			var event_codes = "";
			if($(":checkbox:checked[value!='']").size()>0){
				$(":checkbox:checked[value!='']").each(function (){
					event_codes += this.value+",";
				});
				$("#event_codess").val(event_codes);
				$("#hid_form_cd").submit();
			}else{
				alert("请先勾选需要申请销毁的光盘");
				return false;
			}
		}
	}
	
	function rejectRetrieveJob(){
	    if(confirm("如果被退回，用户需要重新提交销毁申请，确定要退回该销毁申请？")){
	        var event_codes = "";
	        if($(":checkbox:checked[value!='']").size()>0){
	        	$(":checkbox:checked[value!='']").each(function (){
					event_codes += this.value+",";
				});
				$("#event_codes").val(event_codes);
				var url = "${ctx}/ledger/quithandlepaper.action";
				//var rValue=window.showModalDialog("${ctx}/html/ledger/rejectviewpapercomment.jsp",'', 'dialogHeight:240px;dialogWidth:400px;center:yes;status:no;scroll:no;help:no;unadorned:no;resizable:no');
	          
	          //  if(rValue != null && rValue!= undefined && rValue.type == "Y"){
				//	$("#comment").val(rValue.comment);	
					callServerPostForm(url,document.forms[0]);
				//}
	        }else{
	            alert("请先勾选需要退回的销毁的文件");
				return false;
	        }
	    }
	}
	
	function getAjaxResult(){
		if (xmlHttp.readyState == 4) {
			  var response = xmlHttp.responseText;
			  LedgerQueryCondForm.submit();
		}
	}
	function reject(cd_id){
		if(confirm("如果被退回，用户需要重新提交销毁申请，确定要退回该销毁申请？")){
			var url = "${ctx}/ledger/quithandlecd.action?type=ajax&cd_id="+escape(cd_id);
			xmlHttp.open("GET", url, true);
			xmlHttp.onreadystatechange = updatePage;
			xmlHttp.send(null);
		}
	}
	function updatePage() {
		if (xmlHttp.readyState == 4) {
			  var response = xmlHttp.responseText;
			  LedgerQueryCondForm.submit();
		}
	}
	
	</script>
</head>

<body oncontextmenu="self.event.returnValue=false">
<form method="post">
	<input type="hidden" name="comment" id="comment"/>
	<input type="hidden" name="event_codes" id="event_codes"/> 
</form>
<form method="post" action="${ctx}/ledger/destroypaper.action" id="hid_form_paper">
	<input type="hidden" name="event_codes" id="event_code"/> 
	<input type="hidden" name="type" id="type" value="Y"/> 
</form>
<form method="post" action="${ctx}/ledger/destroycd.action" id="hid_form_cd">
	<input type="hidden" name="event_codes" id="event_codess"/> 
	<input type="hidden" name="type" id="type" value="Y"/> 
</form>

<form id="RetrieveAllForm" method="post" action="${ctx}/ledger/retrieveall.action" >
	    <input type="hidden" value="" id="hidpaper_barcode" name="hidpaper_barcode"/>
	    <input type="hidden" value="" id="hidduty_user_name" name="hidduty_user_name"/>
	    <input type="hidden" value="" id="hiduser_name" name="hiduser_name"/>
	    <input type="hidden" value="" id="hiddept_name" name="hiddept_name"/>
	    <input type="hidden" value="" id="hidseclv_code" name="hidseclv_code"/>
	    <input type="hidden" value="" id="hidstart_time" name="hidstart_time"/>
	    <input type="hidden" value="" id="hidend_time" name="hidend_time"/>
	    <input type="hidden" value="" id="hiddept_ids" name="hiddept_ids"/>
	    <input type="hidden" name="hidretrieve_type" value="paper"/>
	    <input type="hidden" name="hidcomment" id="hidcomment"/>
	   
</form>

<form id="LedgerQueryCondForm" method="GET" action="${ctx}/ledger/selfclosedprocess.action"> 
	<input type="hidden" name="seclv_code" id="seclv_code" value="6"/>
	<input type="hidden" id="researchFlag" name="researchFlag" value="${researchFlag}"/>
	<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">申请销毁闭环任务列表</td>
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
					<td align="center"> 
						<input type="hidden" name="retrieveType" id="retrieveType"/>&nbsp;&nbsp;&nbsp;
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
							<display:table requestURI="${ctx}/ledger/selfclosedprocess.action" id="item" class="displaytable" name="paperLedgerList"
	   						pagesize="${pageSize}" sort="page" partialList="true" size="${totalSize}" excludedParams="*" form="LedgerQueryCondForm">
							<display:column title="选择">
							<input type="checkbox" name="event_code" value="${item.paper_id}"/>
							</display:column>
							<display:column title="序号">
								<c:out value="${item_rowNum}"/>
							</display:column>
							<display:column property="file_title" title="文件"/>
							<display:column property="paper_barcode" title="条码"/>
							<display:column property="seclv_name" title="密级"/>
							<display:column property="user_name" title="制作人"/>
							<display:column property="dept_name" title="制作人部门"/>
							<display:column property="start_time" sortable="true" title="申请时间"/>
							<display:column property="paper_state_name" title="状态"/>
							<display:column title="操作" style="width:100px">
								<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/ledger/viewcycledetail.action?type=PAPER&barcode=${item.paper_barcode}');"/>
							</display:column>
							</display:table>
						</c:if>
						<c:if test="${media eq 'cd'}">
							<display:table requestURI="${ctx}/ledger/selfclosedprocess.action" id="item" class="displaytable" name="cdLedgerList"
	   						pagesize="${pageSize}" sort="page" partialList="true" size="${totalSize}" excludedParams="*" form="LedgerQueryCondForm">
							<display:column title="选择">
								<input type="checkbox" name="event_code" value="${item.cd_id}"/>
							</display:column>
							<display:column title="序号">
								<c:out value="${item_rowNum}"/>
							</display:column>
							<display:column property="file_list" title="文件列表" maxLength="40"/>
						<display:column property="cd_barcode" title="光盘编号"/>
						<display:column property="conf_code" title="保密编号"/>
						<display:column property="seclv_name" title="密级"/>
						<display:column property="user_name" title="制作人"/>
						<display:column property="dept_name" title="制作人部门"/>
						<display:column property="start_time" sortable="true" title="申请时间"/>
							<display:column property="cd_state_name" title="状态"/>
							<display:column title="操作" style="width:100px">
								<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/ledger/viewcycledetail.action?type=DISC&barcode=${item.cd_barcode}');"/>
								<c:if test="${item.cd_state == '7'}">
									<input type="button" class="button_2003" value="退回" onclick="reject('${item.cd_id}')"/>
								</c:if>
						</display:column>
					</display:table>
					</c:if>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td>
		<c:if test="${media eq 'paper'}">
			<input type="button" class="button_2003" value="批量文件销毁" onclick="destoryPaperJob();"/>&nbsp;
			<!-- <input type="button" class="button_2003" value="批量退回" onclick="rejectRetrieveJob();"/>&nbsp; -->
		</c:if>
		<c:if test="${media eq 'cd'}">
			<input type="button" class="button_2003" value="批量光盘销毁" onclick="destoryCDJob();"/>&nbsp;
		</c:if>
		</td>
	</tr>
	<c:choose>
	<c:when test="${researchFlag eq 'WARING'}">
	<tr> 
		 <td align="center"><font style="color:red;font-size:15">请选择需要的查询条件，点击“查询”按钮查询！</font></td>	    	
	</tr>
	</c:when>
	</c:choose>
</table>
</form>
</body>
</html>
