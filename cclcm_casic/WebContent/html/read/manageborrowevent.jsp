<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>已借阅纸质台账列表</title>
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
		LedgerQueryCondForm.paper_barcode.value = "";
		LedgerQueryCondForm.file_title.value = "";
		$("#seclv_code").val("");
		$("#printer_code").val("");
		$("#job_status").val("");
		LedgerQueryCondForm.startTime.value = "";
		LedgerQueryCondForm.endTime.value = "";
	}	
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">已借阅纸质台账列表</td>
	</tr>
	<form id="LedgerQueryCondForm" method="POST" action="${ctx}/ledger/managehandlepaper.action">
	<tr>
		<td align="right">
			<table  table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<td width="8%" align="center">文件条码：</td>
					<td width="17%">
						<input type="text" id="paper_barcode" name="paper_barcode" value="${paper_barcode}"/>&nbsp;
					</td>
					<td width="8%" align="center">文件名：</td>
					<td width="17%">
						<input type="text" id="file_title" name="file_title" value="${file_title}"/>&nbsp;
					</td>
					<td width="8%" align="center">密级：</td>
					<td width="17%">
						<select name="seclv_code" id="seclv_code">
							<option value="">--所有--</option>
							<s:iterator value="#request.seclvList" var="seclv">
								<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
							</s:iterator>
						</select>
					</td>
					<td width="8%" align="center">打印机：</td>
					<td width="17%">
						<select name="printer_code" id="printer_code">
							<option value="">--所有--</option>
							<s:iterator value="#request.printerList" var="printer">
								<option value="${printer.printer_code}">${printer.printer_name}</option>
							</s:iterator>
						</select>
					</td>
				</tr>
				<tr>
					<td width="10%" align="center">申请状态：</td>
					<td width="23">
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
	   					<display:table uid="item" class="displaytable" name="paperLedgerList" pagesize="${pageSize}" sort="page"
	   							 partialList="true" size="${totalSize}" form="LedgerQueryCondForm" excludedParams="*">
							<display:column title="序号">
								<c:out value="${item_rowNum}"/>
							</display:column>
							<display:column property="file_title" title="文件名" maxLength="30"/>
							<display:column property="user_name" title="申请人"/>
							<display:column property="paper_barcode" title="文件条码"/>
							<display:column property="seclv_name" title="文件密级"/>
							<display:column property="printer_name" title="打印机" maxLength="15"/>
							<display:column property="print_time" sortable="true" title="打印时间"/>
							<display:column property="job_status_name" title="申请状态"/>
							<display:column property="paper_state_name" title="状态"/>
							<display:column title="操作" style="width:150px">
							<div>
								<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/ledger/viewpaperledgerinfo.action?op=hasprc&paper_id=${item.paper_id}');"/>
								<c:choose>
									<c:when test="${item.job_status eq 'none'}">
										&nbsp;<input type="button" class="button_2003" value="取消" onclick="chkCancel('${item.paper_id}')"/>
									</c:when>
									<c:otherwise>
										&nbsp;<input type="button" class="button_2003" value="取消" disabled="disabled"/>
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
