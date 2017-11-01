<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>申请借用归还列表</title>
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
			optionSelect();
			preCalendar();
		});		
	function clearFindForm(){
			LedgerQueryCondForm.barcode.value = "";
			$("#seclv_code").val("");
			$("#entity_type").val("");		
			$("#borrow_status").val("");	
			$("#job_status").val("");	
			LedgerQueryCondForm.startTime.value = "";
			LedgerQueryCondForm.endTime.value = "";
	}	
	function optionSelect(){
			$("#seclv_code").val(${seclv_code});
			$("#entity_type").val(${entity_type});
			$("#borrow_status").val(${borrow_status});
			$("#job_status").val(${job_status});
	}
	/* function giveBack(event_code,entity_type,barcode){
		if(confirm("确定要归还？")){
			go("${ctx}/borrow/updateborrowstatus.action?event_code="+escape(event_code)+"&entity_type="+escape(entity_type)+"&barcode="+escape(barcode));
		}
	} */
	function chkCancel(job_code){
		if(confirm("确定要撤销该借用申请？")){
			var url = "${ctx}/basic/canceljob.action?type=ajax&jobType_code=${jobType_code}&job_code="+escape(job_code)+"&actionContext="+escape('${actionContext}');
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
<form id="LedgerQueryCondForm" method="post" action="${ctx}/borrow/viewpersonalborrowledger.action" name="LedgerQueryCondForm">
	<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">借用申请记录</td>
	</tr>
	<tr>
		<td align="right">
			<table  table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<td width="8%" align="center">介质编号：</td>
					<td width="15%">
						<input type="text" id="barcode" name="barcode" value="${barcode}"/>&nbsp;
					</td>
					<td width="8%" align="center">密级：</td>
					<td width="19%">
						<select name="seclv_code" id="seclv_code">
							<option value="">--所有--</option>
							<s:iterator value="#request.seclvList" var="seclv">
								<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
							</s:iterator>
						</select>
					</td>
					<td width="8%" align="center">载体类型：</td>
					<td width="19%">
						<select name="entity_type" id="entity_type">
			    			<option value="">--全部--</option>
			    			<option value="PAPER" <c:if test="${entity_type eq 'PAPER'}">selected</c:if>>纸质</option>
			    			<option value="CD" <c:if test="${entity_type eq 'CD'}">selected</c:if>>光盘</option>
			    			<option value="DEVICE" <c:if test="${entity_type eq 'DEVICE'}">selected</c:if>>设备</option>
			    		</select>
					</td>
					<td width="8%" align="center">申请状态：</td>
					<td width="12%">
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
					<td align="center">借用状态：</td>
					<td>
						<select name="borrow_status" id="borrow_status">
						    <option value="">--所有--</option>				
							<option value="0">未借入</option>
							<option value="1">已借入</option>
							<option value="2">已归还</option>							
						</select>
					</td>
					<td align="center">申请时间：</td>
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
	   					<display:table requestURI="${ctx}/borrow/viewpersonalborrowledger.action" id="item" class="displaytable" name="eventList"
	   					pagesize="${pageSize}" sort="page" partialList="true" size="${totalSize}" excludedParams="*" form="LedgerQueryCondForm">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column property="user_name" title="申请人"/>
						<display:column property="dept_name" title="申请部门" maxLength="15"/>
						<display:column property="entity_name" title="介质名称"/>
						<display:column property="entity_type_name" title="载体类型"/>
						<display:column property="barcode" title="介质编号"/>
						<display:column property="seclv_name" title="密级"/>
						<display:column property="job_status_name" title="申请状态"/>
						<display:column property="borrow_status_name" title="状态"/>
						<display:column title="借用期限状态">							
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
						<display:column property="apply_time_str" title="申请时间" sortable="true"/>							
						<display:column title="操作" style="width:100px">
							<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/borrow/viewborroweventdetail.action?event_code=${item.event_code}');"/>
							<c:choose>
									<c:when test="${item.borrow_status == 0}">
										&nbsp;<input type="button" class="button_2003" value="撤销" onclick="chkCancel('${item.job_code}')"/>
									</c:when>
									<c:otherwise>
										&nbsp;<input type="button" class="button_2003" value="撤销" disabled="disabled"/>
									</c:otherwise>
								</c:choose>
							<!--  
							<c:choose>
								<c:when test="${item.borrow_status eq '1'}">
									&nbsp;<input type="button" class="button_2003" value="申请归还" onclick="giveBack('${item.event_code}','${item.entity_type}','${item.barcode}')"/>
								</c:when>
								<c:otherwise>
									&nbsp;<input type="button" class="button_2003" value="申请归还" disabled="disabled"/>
								</c:otherwise>
							</c:choose>
							-->
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
