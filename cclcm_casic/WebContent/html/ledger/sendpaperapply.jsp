<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>文件外发申请列表</title>
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
		LedgerQueryCondForm.paper_barcode.value = "";
		LedgerQueryCondForm.file_title.value = "";
		$("#seclv_code").val("");
		$("#job_status").val("");
		$("#paper_state").val("");
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
			var url = "${ctx}/ledger/cancelhandlepaper.action?type=ajax&paper_id="+escape(paper_id);
			xmlHttp.open("GET", url, true);
			xmlHttp.onreadystatechange = updatePage;
			xmlHttp.send(null);
		}
	}
	function chkQuit(paper_id){
		if(confirm("确定要撤销该闭环操作？")){
			var url = "${ctx}/ledger/giveuphandlepaper.action?type=ajax&paper_id="+escape(paper_id);
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
	function updateSendApplyStauts(barcode){
	  var url = "${ctx}/ledger/reprintsendconfirm.action?paper_barcode="+ barcode;
	        xmlHttp.open("GET", url, true);
			xmlHttp.onreadystatechange = updatePages;
			xmlHttp.send(null);
	}
	function updatePages(){
	  if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
		      alert("提交补打交接单成功！")
			  LedgerQueryCondForm.submit();
		
	  }
   }
   
   <%--
   function confirmSendPaper(paper_barcode, send_way){
		var url = "${ctx}/ledger/confirmsendpaper.action?paper_barcode="+escape(paper_barcode) + "&send_way=" + escape(send_way);
		var rValue=window.showModalDialog(url,'', 'dialogHeight:210px;dialogWidth:400px;center:yes;status:no;scroll:no;help:no;unadorned:no;resizable:no');
		if(rValue != null && rValue!= undefined){
			$("#comment").val(rValue.comment);
			$("#output_confidential_num").val(rValue.outputconfidentialnum);
			callServerPostForm1(url,document.forms[0]);	
		}
	}
    
	function rejectSendPaper(paper_barcode){
		var url = "${ctx}/ledger/rejectsendpaper.action?paper_barcode="+escape(paper_barcode);
		var rValue=window.showModalDialog(url,'', 'dialogHeight:240px;dialogWidth:400px;center:yes;status:no;scroll:no;help:no;unadorned:no;resizable:no');
		if(rValue != null && rValue!= undefined){
			$("#comment").val(rValue.comment);
			$("#reject_type").val(rValue.reject_type);
			callServerPostForm(url,document.forms[0]);
		}
	}
	--%>
	function getAjaxResult1(){
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
            alert("该纸质载体已外发");
            $("#LedgerQueryCondForm").submit();
        }
    } 
	function getAjaxResult(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			alert("该纸质载体已拒收");
			$("#LedgerQueryCondForm").submit();
		}
	} 
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<form method="POST"  id="rejectform">
	<input type="hidden" name="updateFlag" id="updateFlag" value="Y"/>
	<input type="hidden" name="reject_ok" value="Y" />
	<input type="hidden" name="comment" id="comment"/>
	<input type="hidden" name="output_confidential_num" id="output_confidential_num"/>
	<input type="hidden" name="reject_type" id="reject_type"/>
</form>
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">已申请外发纸质台账列表</td>
	</tr>
	<form id="LedgerQueryCondForm" method="GET" action="${ctx}/ledger/sendpaperapply.action" name="LedgerQueryCondForm">
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
	   					<display:table uid="item" class="displaytable" name="paperLedgerList" pagesize="${pageSize}" partialList="true" sort="page"  size="${totalSize}" form="LedgerQueryCondForm" excludedParams="*">
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
							<display:column title="操作" style="width:240px" >
							<div>
								<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/ledger/viewpaperledgerinfo.action?op=hasprc&paper_id=${item.paper_id}');"/>&nbsp;
								<c:choose>
									<c:when test="${item.job_status eq 'none'}">
										<input type="button" class="button_2003" value="撤销" onclick="chkCancel('${item.paper_id}')"/>
									</c:when>
									<c:when test="${item.job_status eq 'true' && item.paper_state !=2}">
										<input type="button" class="button_2003" value="撤销" onclick="chkQuit('${item.paper_id}')"/>
									</c:when>
									<c:otherwise>
										<input type="button" class="button_2003" value="撤销" disabled="disabled"/>
									</c:otherwise>
								</c:choose>
								<!--
								//外发承办人若为自己，则外发确认或接收在本页面
								<c:choose>
									<c:when test="${item.job_status eq 'true' && item.paper_state==16 && item.output_undertaker == item.user_iidd}">
										<input type="button" class="button_2003" value="外发确认" onclick="confirmSendPaper('${item.paper_barcode}', '${item.send_way}');"/>&nbsp;	
										<input type="button" class="button_2003" value="拒收" onclick="rejectSendPaper('${item.paper_barcode}');"/>
									</c:when>
								</c:choose>
								-->
								<!--  
								<c:choose>
									<c:when test="${item.job_status eq 'none'}">
										<input type="button" class="button_2003" value="取消" onclick="chkCancel('${item.paper_id}')"/>&nbsp;
									</c:when>
									<c:otherwise>
										<input type="button" class="button_2003" value="取消" disabled="disabled"/>&nbsp;
									</c:otherwise>
								</c:choose>
								<c:choose>
								
									<c:when test="${item.job_status eq 'true' && item.paper_state !=2}">
										<input type="button" class="button_2003" value="撤销" onclick="chkQuit('${item.paper_id}')"/>
									</c:when>
					
									<c:otherwise>
										<input type="button" class="button_2003" value="撤销" disabled="disabled"/>
									</c:otherwise>
								</c:choose>
								-->
								<%-- <input type="button" class="button_2003" value="补打交接单" onclick="updateSendApplyStauts('${item.paper_barcode}')"/>&nbsp; --%>
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
