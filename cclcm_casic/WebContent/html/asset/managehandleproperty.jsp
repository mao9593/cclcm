<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>资产报废、变更申请列表</title>
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
/* 		if("${secError}" == "Y"){
			alert("勾选的载体密级不一致");
		} */
	});
	function clearFindForm(){
		LedgerQueryCondForm.device_barcode.value = "";
		$("#seclv_code").val("");		
		$("#job_status").val("");
		LedgerQueryCondForm.startTime.value = "";
		LedgerQueryCondForm.endTime.value = "";
	}	
	function optionSelect(){
		$("#seclv_code").val("${seclv_code}");		
		$("#job_status").val("${job_status}");
	}
	function chkCancel(event_code){
		if(confirm("确定要撤销该申请？")){
			//var url = "${ctx}/asset/cancelhandleproperty.action?type=ajax&property_barcode="+escape(property_barcode);
			var url = "${ctx}/asset/delpurchaseevent.action?type=ajax&eventtype=wastechange&event_code="+escape(event_code);
			xmlHttp.open("GET", url, true);
			xmlHttp.onreadystatechange = updatePage;
			xmlHttp.send(null);
		}
	}
/* 	function outProperty(property_barcode){
	    if(confirm("确定出库？")){
	    	var url = "${ctx}/asset/updatepropertystatus.action?type=ajax&property_status=2&property_barcode="+escape(property_barcode);
	    	xmlHttp.open("GET", url, true);
			xmlHttp.onreadystatechange = updatePage;
			xmlHttp.send(null);
	    }
	} */
	function changeProperty(event_code){
	    if(confirm("确定变更资产？")){
	    	var url = "${ctx}/asset/updatewastechangestatus.action?property_status=0&event_code="+escape(event_code);
	    	xmlHttp.open("GET", url, true);
			xmlHttp.onreadystatechange = updatePage;
			xmlHttp.send(null);
	    }
	}
	function wasteProperty(event_code){
	    if(confirm("确定报废资产？")){
	    	var url = "${ctx}/asset/updatewastechangestatus.action?property_status=4&event_code="+escape(event_code);
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
<!-- <body oncontextmenu="self.event.returnValue=false"> -->
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">已申请报废、变更的资产列表</td>
	</tr>
	<form id="LedgerQueryCondForm" method="GET" action="${ctx}/asset/managehandleproperty.action" name="LedgerQueryCondForm">
	<tr>
		<td align="right">
			<table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
					<tr>
						<td width="8%" align="center">作业密级 ：</td>
				 		<td width="20%">
				 			<c:set var="seclv1" value="${seclv_code}" scope="request"/>
        					<select name="seclv_code">
        						<option value="">--全部--</option>
    							<s:iterator value="#request.seclvList" var="seclv">
									<option value="${seclv.seclv_code}" <s:if test="#request.seclv1==#seclv.seclv_code">selected="selected"</s:if>>${seclv.seclv_name}</option>
								</s:iterator>
    						</select>
				 		</td>
				 		<td width="8%" align="center">申请状态：</td>
				 		<td width="20%">
				        	<select name="job_status">
    							<option value="">--全部--</option>
    							<option value="none" <c:if test="${job_status eq 'none'}">selected</c:if>>待审批</option>
    							<option value="under" <c:if test="${job_status eq 'under'}">selected</c:if>>审批中</option>
    							<option value="false" <c:if test="${job_status eq 'false'}">selected</c:if>>已驳回</option>
    							<option value="true" <c:if test="${job_status eq 'true'}">selected</c:if>>已通过</option>
    							<option value="done" <c:if test="${job_status eq 'done'}">selected</c:if>>已关闭</option>
    						</select>
			    		</td>
				 		<td width="8%" align="center">变动状态：</td>
				 		<td width="15%">
				        	<select name="waste_status">
    							<option value="">--全部--</option>
    							<option value="0" <c:if test="${waste_status == '0'}">selected</c:if>>正常</option>
    							<option value="1" <c:if test="${waste_status == '1'}">selected</c:if>>已报废</option>
    							<option value="2" <c:if test="${waste_status == '2'}">selected</c:if>>已变更</option>
    							
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
	   					<display:table requestURI="${ctx}/asset/managehandleproperty.action"  uid="item" class="displaytable" name="eventList" 
	   					pagesize="15" sort="list" excludedParams="*" form="LedgerQueryCondForm">
							<display:column title="序号">
								<c:out value="${item_rowNum}"/>
							</display:column>
							<display:column title="设备名称" property="property_name" maxLength="20"/>													
							<display:column title="作业密级" property="seclv_name"/>
							<display:column title="业务类型" property="event_type_str"/>
                            <display:column title="资产种类" property="property_kind" maxLength="20"/>
                            <display:column title="申请人" property="user_name"/>
							<display:column title="申请状态" property="job_status_name"/>
							<display:column title="申请时间" property="apply_time_str" sortable="true"/>
							<display:column title="变动状态" property="waste_status_str"/>																																
							<display:column title="操作" style="width:120px">
							<div>							
								<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/asset/viewwasteeventdetail.action?event_code=${item.event_code}');"/>
								<c:choose>
									<c:when test="${item.job_status eq 'none'}">
										&nbsp;&nbsp;<input type="button" class="button_2003" value="撤销" onclick="chkCancel('${item.event_code}')"/>
									</c:when>
									<c:otherwise>
										&nbsp;&nbsp;<input type="button" class="button_2003" value="撤销" disabled="disabled"/>
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
