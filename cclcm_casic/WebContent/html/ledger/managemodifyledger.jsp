<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>变更申请列表</title>
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
		preCalendar();
		optionSelect();
		if("${secError}" == "Y"){
			alert("勾选的载体密级不一致");
		}
	});
	function clearFindForm(){
		LedgerQueryCondForm.barcode.value = "";
		$("#seclv_code").val("");
		$("#job_status").val("");
		LedgerQueryCondForm.startTime.value = "";
		LedgerQueryCondForm.endTime.value = "";
	}
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
	function optionSelect(){
		$("#seclv_code").val("${seclv_code}");
		$("#job_status").val("${job_status}");
		$("#entity_type").val("${entity_type}");
	}
	function chkCancel(event_code,entity_type){
		if(confirm("确定要撤销该流程申请？")){
		    var url = "${ctx}/ledger/cancelhandleledger.action?entity_type=${entity_type}&type=ajax&event_code="+escape(event_code);
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
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">变更申请列表</td>
	</tr>
	<form id="LedgerQueryCondForm" method="GET" action="${ctx}/ledger/managemodifyledger.action" name="LedgerQueryCondForm">
	<input type="hidden" id="researchFlag" name="researchFlag" value="${researchFlag}"/>
	<tr>
		<td align="right">
			<table  table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<td align="center">载体条码：</td>
					<td >
						<input type="text" id="barcode" name="barcode" value="${barcode}"/>
					</td>
					<td align="center">密级：</td>
					<td>
						<select name="seclv_code" id="seclv_code">
							<option value="">--所有--</option>
							<s:iterator value="#request.seclvList" var="seclv">
								<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
							</s:iterator>
						</select>
					</td>
					<td align="center"><font color="red">*</font>载体类型：</td>
					<td>
						<select name="entity_type" id="entity_type">
							<option value="Paper" <c:if test="${entity_type eq 'Paper'}">selected</c:if>>文件</option>
							<option value="CD" <c:if test="${entity_type eq 'CD'}">selected</c:if>>光盘</option>
						</select>
					</td>
					<td align="center" rowspan="2"> 
						<input name="button" type="submit" class="button_2003" value="查询" onclick="return checkTime();">&nbsp;
						<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">
					</td>
				</tr>
				<tr>
				<td align="center">申请状态：</td>
					<td>
			    		<select name="job_status" id="job_status">
			    			<option value="">--全部--</option>
			    			<option value="none" <c:if test="${job_status eq 'none'}">selected</c:if>>待审批</option>
			    			<option value="under" <c:if test="${job_status eq 'under'}">selected</c:if>>审批中</option>
			    			<option value="false" <c:if test="${job_status eq 'false'}">selected</c:if>>已驳回</option>
			    			<option value="true" <c:if test="${job_status eq 'true'}">selected</c:if>>已通过</option>
			    			<option value="done" <c:if test="${job_status eq 'done'}">selected</c:if>>已关闭</option>
			    		</select>
					</td>
					<td align="center">产生时间：</td>
					<td>
						<input type="text" name="startTime" readonly="readonly" style="cursor:hand;" value="${startTime}"/>
        				<img src="${ctx}/_image/time2.jpg" id="startTime_trigger">
					</td>
					<td align="center">至：</td>
					<td>
						<input type="text" name="endTime" readonly="readonly" style="cursor:hand;" value="${endTime}"/>
        				<img src="${ctx}/_image/time2.jpg" id="endTime_trigger">
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
						<display:table uid="item" class="displaytable" name="eventModidy" pagesize="${pageSize}" sort="page"
	   							 partialList="true" size="${totalSize}" form="LedgerQueryCondForm" excludedParams="*">
							<display:column title="序号">
								<c:out value="${item_rowNum}"/>
							</display:column>
							<display:column title="文件名" maxLength="40">
							<c:choose>
								<c:when test="${entity_type eq 'Paper'}">${item.file_title}</c:when>
								<c:otherwise>${item.file_list}</c:otherwise>
							</c:choose>
							</display:column>
							<display:column property="barcode" title="载体条码"/>
							<display:column property="pre_seclv_name" title="原密级"/>
							<display:column property="trg_seclv_name" title="目标密级"/>
							<display:column title="状态">
							<c:choose>
								<c:when test="${entity_type eq 'Paper'}">${item.paper_state_name}</c:when>
								<c:otherwise>${item.cd_state_name}</c:otherwise>
							</c:choose>
							</display:column>
							<display:column property="apply_time_str" sortable="true" title="申请时间"/>
							<display:column property="job_status_name" title="申请状态"/>
							<display:column title="操作" style="width:120px">
						    <c:if test="${item.entity_type eq 'Paper'}">
							  <div>
								<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/ledger/viewmodjobdetail.action?is_flag=Y&job_code=${item.job_code}');"/>&nbsp;
								<c:choose>
								   
									    <c:when test="${item.job_status eq 'none'}">
											<input type="button" class="button_2003" value="撤销" onclick="chkCancel('${item.event_code}','${item.entity_type}')"/>
										</c:when>
										<c:when test="${item.job_status eq 'true' and (item.paper_state == 12 )}">
											<input type="button" class="button_2003" value="撤销" disabled="disabled"/>
										</c:when>
										<c:otherwise>
											<input type="button" class="button_2003" value="撤销" disabled="disabled"/>
										</c:otherwise>
								</c:choose>
							</div>
						 </c:if>
						   <c:if test="${item.entity_type eq 'CD'}">
							  <div>
								<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/ledger/viewmodjobdetail.action?is_flag=Y&job_code=${item.job_code}');"/>&nbsp;
								<c:choose>
								   
									    <c:when test="${item.job_status eq 'none'}">
											<input type="button" class="button_2003" value="撤销" onclick="chkCancel('${item.event_code}','${item.entity_type}')"/>
										</c:when>
										<c:when test="${item.job_status eq 'true' and (item.cd_state == 12 )}">
											<input type="button" class="button_2003" value="撤销" disabled="disabled"/>
										</c:when>
										<c:otherwise>
											<input type="button" class="button_2003" value="撤销" disabled="disabled"/>
										</c:otherwise>
								</c:choose>
							</div>
						 </c:if>
						</display:column>
						</display:table>
					</td>
				</tr>
			</table>
         </td>
	</tr>
	</form>
	<c:choose>
		<c:when test="${researchFlag eq 'WARING'}">
			<tr> 
		    	<td align="center"><font style="color:red;font-size:15">请选择需要的查询条件，点击“查询”按钮查询文件台账！</font></td>	    	
			</tr>
		</c:when>
	</c:choose>
</table>	
</body>
</html>
