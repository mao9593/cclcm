<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>待借阅光盘台账列表</title>
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
	});
	function clearFindForm(){
		LedgerQueryCondForm.cd_barcode.value = "";
		$("#seclv_code").val("");
		LedgerQueryCondForm.startTime.value = "";
		LedgerQueryCondForm.endTime.value = "";
	}	
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">待借阅光盘台账列表</td>
	</tr>
	<form id="LedgerQueryCondForm" method="POST" action="${ctx}/borrow/submitborrowcd.action">
	<tr>
		<td class="nav_box" align="right">
			光盘编号：
			<input type="text" id="cd_barcode" name="cd_barcode" value="${cd_barcode}"/>&nbsp;&nbsp;&nbsp;&nbsp;			
			密级：					
			<select name="seclv_code" id="seclv_code">
				<option value="">--所有--</option>
				<s:iterator value="#request.seclvList" var="seclv">
					<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
				</s:iterator>
			</select>&nbsp;&nbsp;&nbsp;&nbsp;
			产生时间：
			<input type="text" name="startTime" readonly="readonly" style="cursor:hand;" value="${startTime}"/>
 			<img src="${ctx}/_image/time2.jpg" id="startTime_trigger">
			&nbsp;&nbsp;至&nbsp;&nbsp;
			<input type="text" name="endTime" readonly="readonly" style="cursor:hand;" value="${endTime}"/>
    		<img src="${ctx}/_image/time2.jpg" id="endTime_trigger">
			&nbsp;&nbsp;&nbsp;&nbsp;
			<input name="button" type="submit" class="button_2003" value="查询" onclick="return checkTime();">
			<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">
		</td>
	</tr>
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
	   					<display:table requestURI="${ctx}/borrow/submitborrowcd.action" uid="item" class="displaytable" name="cdLedgerList"
	   							pagesize="${pageSize}" sort="page" partialList="true" size="${totalSize}" form="LedgerQueryCondForm" excludedParams="*">
							<display:column title="序号">
								<c:out value="${item_rowNum}"/>
							</display:column>							
							<display:column property="cd_barcode" title="光盘编号"/>
							<display:column property="seclv_name" title="密级"/>
							<display:column property="project_name" title="所属项目"/>
							<display:column property="burn_time" sortable="true" title="产生时间"/>
							<display:column title="操作">
								<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/ledger/viewpersonalledgerinfo.action?cd_id=${item.cd_id}&seclv_code=${item.seclv_code}');"/>&nbsp;
								<c:choose>
									<c:when test="${item.is_read eq 'Y'}">
										<input type="button" class="button_2003" value="已借阅" disabled="disabled"/>
									</c:when>
									<c:otherwise>
										<input type="button" class="button_2003" value="借阅" onclick=""/>
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
