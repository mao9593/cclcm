<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>台账合并申请列表</title>
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
	});
	function clearFindForm(){
		LedgerQueryCondForm.paper_barcode.value = "";
		LedgerQueryCondForm.file_title.value = "";
		$("#seclv_code").val("");
		$("#paper_state").val("");
		$("#job_status").val("");
		LedgerQueryCondForm.startTime.value = "";
		LedgerQueryCondForm.endTime.value = "";
	}	
	function optionSelect(){
		$("#seclv_code").val("${seclv_code}");
		$("#job_status").val("${job_status}");
		$("#paper_state").val("${paper_state}");
	}
	function chkCancel(paper_id){
		if(confirm("确定要撤销该流程申请？")){
			var url = "${ctx}/ledger/cancelhandlepaper.action?code=merge&type=ajax&paper_id="+escape(paper_id);
			xmlHttp.open("GET", url, true);
			xmlHttp.onreadystatechange = updatePage;
			xmlHttp.send(null);
		}
	}
	function chkQuit(paper_id){
		if(confirm("确定要撤销该闭环操作？")){
			var url = "${ctx}/ledger/giveuphandlepaper.action?code=merge&type=ajax&paper_id="+escape(paper_id);
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
		<td class="title_box">已申请台账合并申请列表</td>
	</tr>
	<form id="LedgerQueryCondForm" method="GET" action="${ctx}/ledger/mergepaperapply.action" name="LedgerQueryCondForm">
	<tr>
		<td>
			<table  table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<td width="8%" align="center">文件条码：</td>
					<td width="22%">
						<input type="text" id="paper_barcode" name="paper_barcode" value="${paper_barcode}"/>&nbsp;
					</td>
					<td width="8%" align="center">文件名：</td>
					<td width="22%">
						<input type="text" id="file_title" name="file_title" value="${file_title}"/>&nbsp;
					</td>
					<td width="8%" align="center">密级：</td>
					<td width="12%">
						<select name="seclv_code" id="seclv_code">
							<option value="">--所有--</option>
							<s:iterator value="#request.seclvList" var="seclv">
								<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
							</s:iterator>
						</select>
					</td>
					<td width="8%" align="center">文件状态：</td>
					<td width="12%">
						<select name="paper_state" id="paper_state">
							<option value="">--所有--</option>
							<s:iterator value="#request.stateList" var="state">
								<option value="${state.key}">${state.name}</option>
							</s:iterator>	
						</select>
					</td>
				</tr>
				<tr>
					<td align="center">制作时间：</td>
					<td>
						<input type="text" name="startTime" readonly="readonly" style="cursor:hand;" value="${startTime}"/>
        				<img src="${ctx}/_image/time2.jpg" id="startTime_trigger">
					</td>
					<td align="center">至：</td>
					<td>
						<input type="text" name="endTime" readonly="readonly" style="cursor:hand;" value="${endTime}"/>
        				<img src="${ctx}/_image/time2.jpg" id="endTime_trigger">
					</td>
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
	   					<display:table uid="item" class="displaytable" name="paperLedgerList" pagesize="${pageSize}" partialList="true" sort="page"  size="${totalSize}"  form="LedgerQueryCondForm" excludedParams="*">
							<display:column title="序号">
								<c:out value="${item_rowNum}"/>
							</display:column>
							<display:column property="file_title" title="文件名" maxLength="40"/>
							<display:column property="paper_barcode" title="文件条码"/>
							<display:column property="seclv_name" title="文件密级"/>
							<display:column property="create_type_name" title="制作方式"/>
							<display:column property="page_count" title="页数"/>
							<display:column property="paper_state_name" title="状态"/>
							<display:column property="print_time" sortable="true" title="制作时间"/>
							<display:column property="job_status_name" title="申请状态"/>
							<display:column title="操作" style="width:120px">
							<div>
								<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/ledger/viewpaperledgerinfo.action?op=hasprc&paper_id=${item.paper_id}');"/>&nbsp;
								<c:choose>
									<c:when test="${item.job_status eq 'none'}">
										<input type="button" class="button_2003" value="撤销" onclick="chkCancel('${item.paper_id}')"/>
									</c:when>
									<c:otherwise>
										<input type="button" class="button_2003" value="撤销" disabled="disabled"/>
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
	<tr>
		<td><font color="red">注：操作【撤销】是对整条任务撤销，并非对某条文件载体的撤销</font></td>
	</tr>
</table>	
</body>
</html>
