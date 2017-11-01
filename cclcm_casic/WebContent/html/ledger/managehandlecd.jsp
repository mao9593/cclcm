<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>用户光盘台账列表</title>
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
		LedgerQueryCondForm.cd_barcode.value = "";
		$("#seclv_code").val("");		
		$("#job_status").val("");
		LedgerQueryCondForm.startTime.value = "";
		LedgerQueryCondForm.endTime.value = "";
	}	
	function optionSelect(){
		$("#seclv_code").val("${seclv_code}");		
		$("#job_status").val("${job_status}");
	}
	function chkCancel(cd_id){
		if(confirm("确定要撤销该流程申请？")){
			var url = "${ctx}/ledger/cancelhandlecd.action?type=ajax&cd_id="+escape(cd_id);
			xmlHttp.open("GET", url, true);
			xmlHttp.onreadystatechange = updatePage;
			xmlHttp.send(null);
		}
	}
	function chkQuit(cd_id){
		if(confirm("确定要撤销该闭环操作？")){
			var url = "${ctx}/ledger/giveuphandlecd.action?type=ajax&cd_id="+escape(cd_id);
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
		<td class="title_box">已申请闭环光盘台账列表</td>
	</tr>
	<form id="LedgerQueryCondForm" method="GET" action="${ctx}/ledger/managehandlecd.action" name="LedgerQueryCondForm">
	<tr>
		<td align="right">
			<table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
					<tr>
						<td width="10%" align="center">光盘条码：</td>
				 		<td width="25%">
				 			<input type="text" id="cd_barcode" name="cd_barcode" value="${cd_barcode}"/>
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
				 		<td align="center">制作时间：</td>
				 		<td >
				        	<input type="text" name="startTime" readonly="readonly" style="cursor:hand;" value="${startTime}"/>
     						<img src="${ctx}/_image/time2.jpg" id="startTime_trigger">&nbsp;
			    		</td> 
			    		<td align="center">至：</td>
				 		<td>
				        	<input type="text" name="endTime" readonly="readonly" style="cursor:hand;" value="${endTime}"/>
     						<img src="${ctx}/_image/time2.jpg" id="endTime_trigger">&nbsp;
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
	   					<display:table uid="item" class="displaytable" name="cdLedgerList" pagesize="${pageSize}" sort="page"
	   							 partialList="true" size="${totalSize}" form="LedgerQueryCondForm" excludedParams="*">
							<display:column title="序号">
								<c:out value="${item_rowNum}"/>
							</display:column>		
							<display:column property="file_list" title="文件列表" maxLength="40"/>												
							<display:column property="cd_barcode" title="光盘条码"/>
							<display:column property="seclv_name" title="密级"/>
							<display:column property="create_type_name" title="制作方式"/>
							<display:column property="cd_state_name" title="状态"/>														
							<display:column property="burn_time" sortable="true" title="制作时间"/>
							<display:column property="job_status_name" title="申请状态"/>
							<display:column title="操作" style="width:120px">
							<div>
								<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/ledger/viewpersonalledgerinfo.action?op=hasprc&cd_id=${item.cd_id}');"/>&nbsp;
								<c:choose>
									<c:when test="${item.job_status eq 'none'}">
										<input type="button" class="button_2003" value="撤销" onclick="chkCancel('${item.cd_id}')"/>
									</c:when>
									<c:when test="${item.job_status eq 'true' and (item.cd_state == 7 or item.cd_state == 9)}">
										<input type="button" class="button_2003" value="撤销" onclick="chkQuit('${item.cd_id}')"/>
									</c:when>
									<c:otherwise>
										<input type="button" class="button_2003" value="撤销" disabled="disabled"/>
									</c:otherwise>
								</c:choose>
								<!--  
								<c:choose>
									<c:when test="${item.job_status eq 'none'}">
										<input type="button" class="button_2003" value="取消" onclick="chkCancel('${item.cd_id}')"/>&nbsp;
									</c:when>
									<c:otherwise>
										<input type="button" class="button_2003" value="取消" disabled="disabled"/>&nbsp;
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${item.job_status eq 'true' and (item.cd_state == 7 or item.cd_state == 9)}">
										<input type="button" class="button_2003" value="撤销" onclick="chkQuit('${item.cd_id}')"/>
									</c:when>
									<c:otherwise>
										<input type="button" class="button_2003" value="撤销" disabled="disabled"/>
									</c:otherwise>
								</c:choose>
								-->
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
