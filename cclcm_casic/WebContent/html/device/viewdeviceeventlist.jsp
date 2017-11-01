<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>磁介质借入申请列表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
		<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
	<link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
	<script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script>
	$(document).ready(function(){
		onHover();
		preCalendar();
		optionSelect();
	});
	function clearFindForm(){
		QueryCondForm.startTime.value = "";
		QueryCondForm.endTime.value = "";
		$("#seclv_code").val("");
		$("#distribute_state").val("");
		$("#user_name").val("");
	}	
	function optionSelect(){
		$("#seclv_code").val("${seclv_code}");	
		$("#distribute_state").val("${distribute_state}");		
	}
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">已申请借入磁介质列表</td>
	</tr>
	<form id="QueryCondForm" method="GET" action="${ctx}/device/viewdeviceeventlist.action">
	<tr>
		<td align="right">
			<table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
					<tr>
						<td width="10%" align="center"> 申请人：</td>
				 		<td width="25%">
				 			<input type="text" id="user_name" name="user_name" value="${user_name}" size="15"/>
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
				 		<td width="10%" align="center">状态：</td>
				 		<td width="25%">
				 			<select name="distribute_state" id="distribute_state">
								<option value="">--所有--</option>
								<option value="0">未分配</option>
								<option value="1">已分配</option>
							</select>
				 		</td>
				 	</tr>
				 	<tr>
				 		<td align="center">申请时间：</td>
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
							<input name="button" type="submit" class="button_2003" value="查询" onclick="return checkTime();">&nbsp;
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
	   					<display:table requestURI="${ctx}/device/viewdeviceeventlist.action"  uid="item" class="displaytable" name="eventList" pagesize="15" sort="list">
							<display:column title="序号">
								<c:out value="${item_rowNum}"/>
							</display:column>														
							<display:column title="申请人" property="user_name" />
							<display:column title="申请人部门" property="dept_name" />
							<display:column title="介质类型" property="med_type_name" />
							<display:column title="密级" property="seclv_name"/>
							<display:column title="用途" property="usage_name"/>
							<display:column title="申请时间" property="apply_time_str"   sortable="true"/>
							<display:column title="申请状态" property="job_status_name" />																										
							<display:column title="操作" style="width:150px">
							<div>							
								<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/device/viewdeviceeventdetail.action?event_code=${item.event_code}');"/>&nbsp;
								<c:choose>	
								<c:when test="${item.device_barcode==''}">
									<input type="button" class="button_2003" value="分配" onclick="go('${ctx}/device/distributedevice.action?event_code=${item.event_code}&med_type=${item.med_type}');"/>	
									<input type="button" class="button_2003" value="查看" disabled="disabled"/>	
								</c:when>
								<c:otherwise>
									<input type="button" class="button_2003" value="分配" disabled="disabled"/>	
									<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/device/viewdevicedetail.action?device_barcode=${item.device_barcode}');";/>
								</c:otherwise>
								</c:choose>	
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
