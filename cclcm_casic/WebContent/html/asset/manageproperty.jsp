<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>资产管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
<script>
<!--
	$(document).ready(function(){
		onHover();
		preCalendar();
	});
	function clearFindForm(){
		$("#QueryCondForm :text").val("");
		$("#QueryCondForm select").val("");
	}
	function chkOut(barcode,seclv_code){
		if(confirm("确定要变更？")){
			$("#id").val(barcode);
			$("#seclv_code").val(seclv_code);
			$("#handleType").val('PROPERTYCHANGE');//类型为报废
			$("#hid_wastform").submit();			
		}
	}
	function chkWaste(barcode,seclv_code){
		if(confirm("确定要申请报废？")){
			$("#id").val(barcode);
			$("#seclv_code").val(seclv_code);
			$("#handleType").val('WASTE');//类型为报废
			$("#hid_wastform").submit();			
		}
	}
	function updatePage() {
		if (xmlHttp.readyState == 4) {
			  var response = xmlHttp.responseText;
			  QueryCondForm.submit();
		}
	}
		function exportLedger(formId,url,url1){
			document.getElementById(formId).action = url;
			document.getElementById(formId).submit();
			document.getElementById(formId).action = url1;
	}
//-->
</script>
</head>

<body oncontextmenu="self.event.returnValue=false">
<form method="post" action="${ctx}/asset/handlepropertyjob.action" id="hid_form">
	<input type="hidden" name="jobType" value="${jobType}"/>
	<input type="hidden" name="property_barcode" id="property_barcode"/>
	<input type="hidden" name="seclv_code" id="seclv_code"/> 
	<input type="hidden" name="handletype" id="handletype" value=""/>
	
</form>
<form method="post" action="${ctx}/asset/addpropertywasteevent.action" id="hid_wastform">
	<input type="hidden" name="jobType" value="${jobType}"/>
	<input type="hidden" name="property_barcode" id="property_barcode" value="${property_barcode}"/>
	<input type="hidden" name="seclv_code" id="seclv_code"/> 
	<input type="hidden" name="handleType" id="handleType" />
	<input type="hidden" name="type" id="type" value="ok"/>
	<input type="hidden" name="id" id="id" value=""/>  
</form>
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">资产台账管理</td>
	</tr>
	<tr>
		<td align="right">
		<form id="QueryCondForm" method="GET" action="${ctx}/asset/manageproperty.action" name="QueryCondForm">
			<table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
					<tr>
						<td width="8%" align="center">设备名称 ：</td>
				 		<td width="20%">
							<input type="text" id="property_name" name="property_name" value="${property_name}"/>
				 		</td>
				 		<td width="8%" align="center">责任人：</td>
				 		<td width="20%">
				        	<input type="text" id="duty_user_name" name="duty_user_name" value="${duty_user_name}"/>
			    		</td>
				 		<td width="8%" align="center">资产状态：</td>
				 		<td width="15%">
				        	<select name="property_status">
    							<option value="">--全部--</option>
    							<option value="0" <c:if test="${property_status == '0'}">selected</c:if>>正常</option>
    							<option value="1" <c:if test="${property_status == '1'}">selected</c:if>>申请出账</option>
    							<option value="2" <c:if test="${property_status == '2'}">selected</c:if>>出库</option>
    							<option value="3" <c:if test="${property_status == '3'}">selected</c:if>>申请报废</option>
    							<option value="4" <c:if test="${property_status == '4'}">selected</c:if>>已报废</option>
    							<option value="5" <c:if test="${property_status == '5'}">selected</c:if>>申请入账</option>
    							<option value="6" <c:if test="${property_status == '6'}">selected</c:if>>已入账</option>
    						</select>
			    		</td>
			    	</tr>
			    	<tr>
			    		<td align="center">入账时间：</td>
				 		<td >
				 			<input type="text" name="startTime" readonly="readonly" style="cursor:hand;" value="${startTime_str}"/>
        					<img src="${ctx}/_image/time2.jpg" id="startTime_trigger">
				 		</td>
				 		<td align="center">至：</td>
				 		<td >
				        	<input type="text" name="endTime" readonly="readonly" style="cursor:hand;" value="${endTime_str}"/>
        					<img src="${ctx}/_image/time2.jpg" id="endTime_trigger">
			    		</td>  
				        <td align="center" colspan="6">
							<input name="button" type="submit" class="button_2003" value="查询" onclick="return checkTime();">&nbsp;
							<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">
							<input type="button" class="button_2003" value="导出EXCEL" onclick="exportLedger('QueryCondForm','${ctx}/asset/exportproperty.action','${ctx}/asset/manageproperty.action');"/>
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
					<display:table requestURI="${ctx}/asset/manageproperty.action" uid="item" class="displaytable" name="propertyList" 
					pagesize="15" sort="list"  excludedParams="*" form="QueryCondForm">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>					
						<display:column title="设备名称" property="property_name" maxLength="20"/>	
						<display:column title="资产密级" property="seclv_name"/>					
						<display:column title="责任人" property="duty_user_name" />
						<display:column title="资产规格" property="property_standard"/>
						<display:column title="资产型号" property="property_type"/>
						<display:column title="资产编号" property="property_no"/>				
						<display:column title="入账时间" property="in_time" sortable="true"/>
						<display:column title="资产状态" property="property_status_str"/>	
						<display:column title="操作" style="width:210px">
							<div>
								<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/asset/viewcycledetail.action?barcode=${item.property_barcode}');"/>
								<c:choose>
									<c:when test="${item.property_status == 0}">
										&nbsp;<input type="button" class="button_2003" value="申请变更" onclick="chkOut('${item.id}','${item.seclv_code}');"/>
										&nbsp;<input type="button" class="button_2003" value="申请报废" onclick="chkWaste('${item.id}','${item.seclv_code}');"/>
									</c:when>
									<c:otherwise>									
										&nbsp;<input type="button" class="button_2003" value="申请变更" disabled="disabled"/>
										&nbsp;<input type="button" class="button_2003" value="申请报废" disabled="disabled"/>
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
</table>
</form>
</body>
</html>
