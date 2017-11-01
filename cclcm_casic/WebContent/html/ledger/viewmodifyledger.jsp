<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>密级变更申请</title>
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
		addSelectAllCheckbox();
		preCalendar();
		optionSelect();
		if("${secError}" == "Y"){
			alert("勾选的载体密级不一致");
		}
	});
	function clearFindForm(){
		$("#LedgerQueryCondForm :text").val("");
		$("#LedgerQueryCondForm select").val("");
	}	
	function optionSelect(){
		$("#seclv_code").val("${seclv_code}");
		$("#paper_state").val("${paper_state}");
		$("#entity_type").val("${entity_type}");
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
	function chkSubmit(){
		var seclv_code = -1;
		var submitable = true;
		var event_ids = "";
		if($(":checkbox:checked[value!='']").size()>0){
			$(":checkbox:checked[value!='']").each(function (){
			    $("#event_code").val(this.name);
			    event_ids += this.value+":";
			    $("#event_ids").val(event_ids);
				if(seclv_code == -1 || seclv_code == this.id){					
					seclv_code = this.id;
				}else{
					alert("勾选的申请密级不一致，请确认");
					submitable =  false;
					return false;
				}
			});
			if(submitable){
					if($(":radio:checked").size() == 0){
					alert("请勾选闭环方式");
					$("radio:first").focus();
					return false;
				}else{
			
					$("#LedgerQueryCondForm").attr("action","${ctx}/ledger/handlemodifyjob.action");
					$("#LedgerQueryCondForm").submit();
				}
				return true;
			}else{
				return false;
			}
		$("#researchFlag").val("Y");
		}else{
			alert("请先勾选需要审批的作业");
			return false;
		}
	}	
	function exportLedger(formId,url,url1){
			document.getElementById(formId).action = url;
			document.getElementById(formId).submit();
			document.getElementById(formId).action = url1;
	}
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<input type="hidden" value="${paperBarcodeList}" id="paperBarcodeList"/>
	<tr>
		<td class="title_box">密级变更申请列表</td>
	</tr>
	<form id="LedgerQueryCondForm" method="GET" action="${ctx}/ledger/viewmodifyledger.action">
	<input type="hidden" id="event_ids" name="event_ids" value=""/>&nbsp;
	<input type="hidden" id="event_code" name="event_code" value=""/>&nbsp;
	<input type="hidden" id="researchFlag" name="researchFlag" value="${researchFlag}"/>
	<tr>
		<td align="right">
			<table  table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<td align="center">载体条码：</td>
					<td>
						<input type="text" id="barcode" name="barcode" value="${barcode}"/>&nbsp;
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
				</tr>
				<tr>	
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
						&nbsp;<input name="button" type="submit" class="button_2003" value="查询" onclick="return checkTime();">
						&nbsp;<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">&nbsp;&nbsp;&nbsp;&nbsp;
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
	   				  <c:if test="${entity_type eq 'Paper'}">
	   					<display:table requestURI="${ctx}/ledger/viewmodifyledger.action" uid="item" class="displaytable" name="paperLedgerList"
	   							pagesize="${pageSize}" sort="page" partialList="true" size="${totalSize}" form="LedgerQueryCondForm" excludedParams="*">		   							   					
		   					<display:column title="选择">							
			   					<span id="checkbox">
			   					<c:choose>
									<c:when test="${item.paper_state == 0}">
										<input type="checkbox" name="${item.event_code}" value="${item.paper_barcode}" id="${item.seclv_code}"/>
									</c:when>
									<c:otherwise>
										${item.paper_state_name}
									</c:otherwise>
								</c:choose>
								</span>
							</display:column>
							<display:column title="序号">
								<c:out value="${item_rowNum}"/>
							</display:column>
							<display:column property="file_title" title="文件名" />
							<display:column property="paper_barcode" title="载体条码" />
							<display:column property="seclv_name" title="文件密级"/>
							<display:column title="制作方式">
								<c:choose>
									<c:when test="${item.create_type=='REPLACEPRINT' || item.create_type=='PRINT'}">打印</c:when>
									<c:otherwise>${item.create_type_name}</c:otherwise>
								</c:choose>
							</display:column>
							<display:column title="关键字" >
								<font color="red">${item.keyword_content}&nbsp;</font>
							</display:column>
							<display:column title="到期状态">							
			   					<c:choose>
									<c:when test="${item.expire_status == 1}">
										<font color="red">${item.expire_status_name}</font>
									</c:when>
									<c:when test="${item.expire_status == 2}">
										<font color="orange">${item.expire_status_name}</font>
									</c:when>
									<c:otherwise>
										${item.expire_status_name}
									</c:otherwise>
								</c:choose>
							</display:column>
							<display:column property="page_count" title="页数"/>	
							<display:column property="print_time" sortable="true" title="制作时间"/>
							<display:column title="操作" style="width:50px">
							<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/ledger/viewcycledetail.action?type=PAPER&barcode=${item.paper_barcode}&ledger_type=personal');"/>
							</display:column>
						</display:table>
					</c:if>
				    <c:if test="${entity_type eq 'CD'}">
						<display:table requestURI="${ctx}/ledger/viewmodifyledger.action" uid="item" class="displaytable" name="cdLedgerList"
	   							pagesize="${pageSize}" sort="page" partialList="true" size="${totalSize}" form="LedgerQueryCondForm" excludedParams="*">		   							   					
		   					<display:column title="选择">							
			   					<span id="checkbox">
			   					<c:choose>
									<c:when test="${item.cd_state == 0}">
										<input type="checkbox" name="${item.event_code}" value="${item.cd_barcode}" id="${item.seclv_code}"/>
									</c:when>
									<c:otherwise>
										${item.cd_state_name}
									</c:otherwise>
								</c:choose>
								</span>
							</display:column>
							<display:column title="序号">
								<c:out value="${item_rowNum}"/>
							</display:column>
						
							<display:column property="cd_barcode" title="载体条码" />
							<display:column property="seclv_name" title="文件密级"/>
							<display:column property="create_type_name" title="制作方式"/>
							<display:column title="到期状态">							
			   					<c:choose>
									<c:when test="${item.expire_status == 1}">
										<font color="red">${item.expire_status_name}</font>
									</c:when>
									<c:when test="${item.expire_status == 2}">
										<font color="orange">${item.expire_status_name}</font>
									</c:when>
									<c:otherwise>
										${item.expire_status_name}
									</c:otherwise>
								</c:choose>
							</display:column>
							<display:column property="burn_time" sortable="true" title="制作时间"/>
							<display:column title="操作" style="width:50px">
							<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/ledger/viewcycledetail.action?type=DISC&barcode=${item.cd_barcode}&ledger_type=personal');"/>
							</display:column>
						</display:table>
						</c:if>
						
					</td>
				</tr>
			</table>
         </td>
	</tr>
	<c:choose>
		<c:when test="${researchFlag eq 'WARING'}">
			<tr> 
		    	<td align="center"><font style="color:red;font-size:15">请选择需要的查询条件，点击“查询”按钮查询文件台账！</font></td>	    	
			</tr>
		</c:when>
	</c:choose>
</table>	
<table width="95%" align="center" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr height="40">
		<td align="center">
			<label style="width: 80">
				<input type="radio" name="jobType" value="MODIFY_SECLV"/>密级变更
			</label>
			<input type="button" value="批量提交" class="button_2003" style="margin-left: 30px" onclick="return chkSubmit();"/>
		</td>
	</tr>
</table>
</form>
</body>
</html>
