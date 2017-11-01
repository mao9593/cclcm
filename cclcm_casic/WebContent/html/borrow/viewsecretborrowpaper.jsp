<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>待领用科研工作手册台账列表</title>
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
		LedgerQueryCondForm.paper_barcode.value = "";
		LedgerQueryCondForm.file_title.value = "";
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
	<input type="hidden" name="id" value="${paper_id}" id="id"/>
	<input type="hidden" name="event_code" id="event_code" value="${event_code}"/>
	<input type="hidden" name="event_codes" id="event_codes" value="${event_code}"/>
	<input type="hidden" name="entity_type" id="entity_type" value="PAPER"/>
	<input type="hidden" name="user_iidd" value="${curUser.user_iidd}"/>
	<input type="hidden" name="dept_id" value="${curUser.dept_id}"/>
	<input type="hidden" name="actionContext" value="borrow/viewpersonalborrowledger.action"/>	
	<input type="hidden" name="jobType" value="${jobType}"/>
</form>
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">待领用科研工作手册台账列表</td>
	</tr>
	<form id="LedgerQueryCondForm" method="GET" action="${ctx}/borrow/viewsecretborrowpaper.action">
	<tr>
		<td align="right">
			<table  table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<td width="10%" align="center">文件编号：</td>
					<td width="22%">
						<input type="text" id="paper_barcode" name="paper_barcode" value="${paper_barcode}"/>
					</td>
					<td width="12%" align="center">文件名：</td>
					<td width="20%">
						<input type="text" id="file_title" name="file_title" value="${file_title}"/>
					</td>
					<td width="10%" align="center">密级：</td>
					<td width="22%">
						<select name="seclv_code" id="seclv_code">
							<option value="">--所有--</option>
							<s:iterator value="#request.seclvList" var="seclv">
								<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
							</s:iterator>
						</select>
					</td>
				</tr>
				<tr>
					<td align="center">启用时间：</td>
					<td>
						<input type="text" name="startTime" readonly="readonly" style="cursor:hand;" value="${startTime}"/>
        				<img src="${ctx}/_image/time2.jpg" id="startTime_trigger">
					</td>
					<td align="center">至：</td>
					<td>
						<input type="text" name="endTime" readonly="readonly" style="cursor:hand;" value="${endTime}"/>
        				<img src="${ctx}/_image/time2.jpg" id="endTime_trigger">
					</td>
					<td align="center" colspan="2"> 
						&nbsp;<input name="button" type="submit" class="button_2003" value="查询" onclick="return checkTime();">&nbsp;
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
	   					<display:table requestURI="${ctx}/borrow/viewsecretborrowpaper.action" uid="item" class="displaytable" name="paperLedgerList"
	   							pagesize="${pageSize}" sort="page" partialList="true" size="${totalSize}" form="LedgerQueryCondForm" excludedParams="*">
							<display:column title="序号">
								<c:out value="${item_rowNum}"/>
							</display:column>						
							<display:column property="paper_barcode" title="文件编号" maxLength="30"/>
							<display:column property="file_title" title="文件名" maxLength="30"/>
							<display:column property="seclv_name" title="密级"/>
							<display:column property="print_time" title="启用时间"/>
							<display:column title="操作" style="width:150px">
								<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/ledger/viewpaperledgerinfo.action?time_flag=USETIME&paper_id=${item.paper_id}');"/>&nbsp;
								<c:choose>
									<c:when test="${item.paper_state == 6 or item.paper_state == 10}">
										<input type="button" class="button_2003" value="已经借出" disabled="disabled"/>
									</c:when>
									<c:when test="${item.paper_state == 4}">
										<input type="button" class="button_2003" value="已经销毁" disabled="disabled"/>
									</c:when>
									<c:when test="${item.paper_state == 15}">
										<input type="button" class="button_2003" value="自主销毁" disabled="disabled"/>
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${item.secret_book==1 }">
												<input type="button" class="button_2003" value="申请借用" disabled="disabled"/>
											</c:when>
											<c:otherwise>
												<input type="button" class="button_2003" value="申请借用" onclick="addBorrowJob(${item.paper_id});"/>
											</c:otherwise>
										</c:choose>
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
