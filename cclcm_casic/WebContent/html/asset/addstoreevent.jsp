<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>已出库的资产台账列表</title>
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
	//20150627,gxm,暂时只入库，后续可扩展打印条码
	function chkIn(property_barcode,seclv_code){
		if(confirm("确定要申请入账？")){
			$("#property_barcode").val(property_barcode);
			$("#seclv_code").val(seclv_code);
			$("#handletype").val('STORE');//类型默认为报废，此为出库
			$("#hid_form").submit();			
		}
	}
	function updatePage() {
		if (xmlHttp.readyState == 4) {
			  var response = xmlHttp.responseText;
			  QueryCondForm.submit();
		}
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
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">申请资产入库</td>
	</tr>
	<tr>
		<td align="right">
		<form id="QueryCondForm" method="GET" action="${ctx}/asset/addstoreevent.action" name="QueryCondForm">
			<table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
					<tr>
						<td width="8%" align="center">资产名称 ：</td>
				 		<td width="20%">
							<input type="text" id="property_name" name="property_name" value="${property_name}"/>
				 		</td>
				 		<td width="8%" align="center">责任人：</td>
				 		<td width="20%">
				        	<input type="text" id="duty_user_name" name="duty_user_name" value="${duty_user_name}"/>
			    		</td>
				 		<td width="8%" align="center">条码号：</td>
				 		<td width="15%">
				        	<input type="text" id="property_barcode" name="property_barcode" value="${property_barcode}"/>
			    		</td>
			    	</tr>
			    	<tr>
			    		<td align="center">申请时间：</td>
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
					<display:table requestURI="${ctx}/asset/addstoreevent.action" uid="item" class="displaytable" name="propertyList" 
					pagesize="15" sort="list"  excludedParams="*" form="QueryCondForm">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>					
						<display:column title="资产名称" property="property_name" maxLength="20"/>
						<display:column title="条码号" property="property_barcode"/>
						<display:column title="资产密级" property="seclv_name"/>
						<display:column title="责任人" property="duty_user_name" />				
						<display:column title="入库时间" property="in_time"/>
						<display:column title="资产状态" property="property_status_str"/>	
						<display:column title="操作" style="width:150px">
							<div>
								<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/asset/viewcycledetail.action?barcode=${item.property_barcode}');"/>
								<c:choose>
									<c:when test="${item.property_status == 2}">
										&nbsp;<input type="button" class="button_2003" value="申请入库" onclick="chkIn('${item.property_barcode}','${item.seclv_code}');"/>
									</c:when>
									<c:otherwise>
										&nbsp;<input type="button" class="button_2003" value="申请入库" disabled="disabled"/>
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
