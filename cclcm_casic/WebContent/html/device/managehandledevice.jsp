<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>磁介质销毁申请列表</title>
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
		LedgerQueryCondForm.device_barcode.value = "";
		$("#seclv_code").val("");		
		$("#job_status").val("");
		LedgerQueryCondForm.startTime.value = "";
		LedgerQueryCondForm.endTime.value = "";
	}	
	function optionSelect(){
		$("#seclv_code").val("${seclv_code}");		
		$("#job_status").val("${job_status}");
	}
	function chkCancel(device_code){
		if(confirm("确定要撤销该流程申请？")){
			var url = "${ctx}/device/cancelhandledevice.action?type=ajax&device_code="+escape(device_code);
			xmlHttp.open("GET", url, true);
			xmlHttp.onreadystatechange = updatePage;
			xmlHttp.send(null);
		}
	}
	function destroyDevice(device_code){
	    if(confirm("确定销毁该介质？")){
	    	var url = "${ctx}/device/updatedevicestatus.action?type=ajax&device_status=5&device_code="+escape(device_code);
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
		<td class="title_box">已申请销毁磁介质列表</td>
	</tr>
	<form id="LedgerQueryCondForm" method="GET" action="${ctx}/device/managehandledevice.action" name="LedgerQueryCondForm">
	<tr>
		<td align="right">
			<table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
					<tr>
						<td width="10%" align="center">条码号：</td>
				 		<td width="25%">
				 			<input type="text" id="device_barcode" name="device_barcode" value="${device_barcode}"/>
				 		</td>
						<td width="10%" align="center">密级 ：</td>
				 		<td width="25%">
				 			<select name="seclv_code" id="seclv_code">
								<option value="">--所有--</option>
								<s:iterator value="#request.seclvList" var="seclv">
									<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
								</s:iterator>
							</select>
				 		</td>
				 		<td width="10%" align="center">申请状态：</td>
				 		<td width="25%">
				 			<select name="job_status" id="job_status">
    							<option value="">--全部--</option>
    							<option value="none" <c:if test="${job_status eq 'none'}">selected</c:if>>待审批</option>
    							<option value="under" <c:if test="${job_status eq 'under'}">selected</c:if>>审批中</option>
    							<option value="false" <c:if test="${job_status eq 'false'}">selected</c:if>>已驳回</option>
    							<option value="true" <c:if test="${job_status eq 'true'}">selected</c:if>>已通过</option>
    							<option value="done" <c:if test="${job_status eq 'done'}">selected</c:if>>已关闭</option>
    						</select>
				 		</td>
				 	</tr>
				 	<tr>
				 		<td align="center">登记时间：</td>
				 		<td >
				        	<input type="text" name="startTime" readonly="readonly" style="cursor:hand;" value="${startTime}"/>
     						<img src="${ctx}/_image/time2.jpg" id="startTime_trigger">
			    		</td> 
			    		<td align="center">至：</td>
				 		<td>
				        	<input type="text" name="endTime" readonly="readonly" style="cursor:hand;" value="${endTime}"/>
     						<img src="${ctx}/_image/time2.jpg" id="endTime_trigger">
			    		</td> 
				        <td align="center" colspan="2">
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
	   					<display:table requestURI="${ctx}/device/managehandledevice.action"  uid="item" class="displaytable" name="deviceList" 
	   					pagesize="15" sort="list" excludedParams="*" form="LedgerQueryCondForm">
							<display:column title="序号">
								<c:out value="${item_rowNum}"/>
							</display:column>														
							<display:column property="device_name" title="介质名称"/>
							<display:column property="dept_name" title="所属部门"/>
							<display:column property="device_series" title="编号"/>
							<display:column property="device_barcode" title="条码号" />
							<display:column property="med_type_name" title="类型"/>
							<display:column property="seclv_name" title="密级"/>
							<display:column property="enter_time_str"  sortable="true" title="登记时间"/>
							<display:column property="device_status_name" title="状态"/>	
							<display:column property="job_status_name" title="申请状态"/>																										
							<display:column title="操作" style="width:150px">
							<div>							
							<c:if test="${!item.is_delete}">
								<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/device/viewdeviceledgerinfo.action?op=hasprc&device_code=${item.device_code}');"/>
								<c:choose>
									<c:when test="${item.job_status eq 'none'}">
										&nbsp;<input type="button" class="button_2003" value="撤销" onclick="chkCancel('${item.device_code}')"/>
									</c:when>
									<c:otherwise>
										&nbsp;<input type="button" class="button_2003" value="撤销" disabled="disabled"/>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${item.job_status eq 'true'}">
										&nbsp;<input type="button" class="button_2003" value="销毁" onclick="destroyDevice('${item.device_code}')"/>
									</c:when>
									<c:otherwise>
										&nbsp;<input type="button" class="button_2003" value="销毁" disabled="disabled"/>
									</c:otherwise>
								</c:choose>								
							</c:if>
							<c:if test="${item.is_delete}">
								<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/device/viewdeviceledgerinfo.action?op=hasprc&device_code=${item.device_code}');"/>
								&nbsp;<input type="button" class="button_2003" value="撤销" disabled="disabled"/>
								&nbsp;<input type="button" class="button_2003" value="销毁" disabled="disabled"/>
							</c:if>		
							</div>
						</display:column>
						</display:table>
					</td>
				</tr>
			</table>
         </td>
	</tr>
	</form>
</table>	
</body>
</html>
