<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>待借用光盘台账列表</title>
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
		preCalendar();
		optionSelect();
	});
	function clearFindForm(){
		LedgerQueryCondForm.cd_barcode.value = "";
		$("#seclv_code").val("");
		LedgerQueryCondForm.startTime.value = "";
		LedgerQueryCondForm.endTime.value = "";
	}	
	function addBorrowJob(id){
		$("#id").val(id);
		$("#hid_event_form").submit();
	}
	function optionSelect(){
		$("#seclv_code").val(${seclv_code});
	}
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<form method="post" action="${ctx}/basic/addmultiprocessjob.action" id="hid_event_form">
	<input type="hidden" name="id" value="${cd_id}" id="id"/>	
	<input type="hidden" name="event_code" id="event_code" value="${event_code}"/>
	<input type="hidden" name="event_codes" id="event_codes" value="${event_code}"/>
	<input type="hidden" name="entity_type" id="entity_type" value="CD"/>
	<input type="hidden" name="user_iidd" value="${curUser.user_iidd}"/>
	<input type="hidden" name="dept_id" value="${curUser.dept_id}"/>
	<input type="hidden" name="actionContext" value="borrow/viewpersonalborrowledger.action"/>	
	<input type="hidden" name="jobType" value="${jobType}"/>
</form>
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">待借用光盘台账列表</td>
	</tr>
	<tr>
		<td  align="right">
			<form id="LedgerQueryCondForm" method="GET" action="${ctx}/borrow/viewborrowcd.action">
				<table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
					<tr>
				 		<td width="25%" align="center">光盘编号：
				 			<input type="text" id="cd_barcode" name="cd_barcode" value="${cd_barcode}"/>
				 		</td>
				 		<td width="15%" align="center">密级 ：
				 			<select name="seclv_code" id="seclv_code">
							<option value="">--所有--</option>
							<s:iterator value="#request.seclvList" var="seclv">
								<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
							</s:iterator>
							</select>
				 		</td>
				 		<td width="27%" align="center">产生时间：
				        	<input type="text" name="startTime" readonly="readonly" style="cursor:hand;" value="${startTime}"/>
 							<img src="${ctx}/_image/time2.jpg" id="startTime_trigger">
			    		</td> 
			    		<td width="23%" align="center">至：
				        	<input type="text" name="endTime" readonly="readonly" style="cursor:hand;" value="${endTime}"/>
    						<img src="${ctx}/_image/time2.jpg" id="endTime_trigger">
			    		</td> 
				        <td align="center">
							&nbsp;<input name="button" type="submit" class="button_2003" value="查询" onclick="return checkTime();">&nbsp;
							&nbsp;<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">
						</td>
					</tr>
				</table>	
			</form>	
		</td>
	</tr>
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
	   					<display:table requestURI="${ctx}/borrow/viewborrowcd.action" uid="item" class="displaytable" name="cdLedgerList"
	   							pagesize="${pageSize}" sort="page" partialList="true" size="${totalSize}" form="LedgerQueryCondForm" excludedParams="*">
							<display:column title="序号">
								<c:out value="${item_rowNum}"/>
							</display:column>							
							<display:column property="cd_barcode" title="光盘编号"/>
							<display:column property="file_list" title="文件列表"/>
							<display:column property="scope_dept_name" title="归属部门"/>
							<display:column property="seclv_name" title="密级"/>
							<display:column property="burn_time" sortable="true" title="产生时间"/>
							<display:column title="操作" style="width:150px">
								<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/ledger/viewpersonalledgerinfo.action?cd_id=${item.cd_id}&seclv_code=${item.seclv_code}');"/>&nbsp;
								<c:choose>
									<c:when test="${item.cd_state == 6 or item.cd_state == 10}">
										<input type="button" class="button_2003" value="已经借用" disabled="disabled"/>
									</c:when>
									<c:otherwise>
										<input type="button" class="button_2003" value="申请借用" onclick="addBorrowJob(${item.cd_id});"/>
									</c:otherwise>
								</c:choose>
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
