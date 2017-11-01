<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>正常状态的资产台账列表</title>
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
	function chkChange(event_code){
		if(confirm("确定要出库？")){
			var url="${ctx}/asset/updatepurchasestatus.action?event_code="+escape(event_code);
			xmlHttp.open("GET", url, true);
			xmlHttp.onreadystatechange = updatePage;
			xmlHttp.send(null);
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
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">申请资产出库</td>
	</tr>
	<tr>
		<td align="right">
		<form id="QueryCondForm" method="GET" action="${ctx}/asset/managepurchaselist.action" name="QueryCondForm">
			<table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
					<tr>
						<td width="8%" align="center">资产名称 ：</td>
				 		<td width="20%">
							<input type="text" id="duty_user_name" name="duty_user_name" value="${duty_user_name}"/>
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
    							<option value="1" <c:if test="${property_status == '1'}">selected</c:if>>申请出库</option>
    							<option value="2" <c:if test="${property_status == '2'}">selected</c:if>>出库</option>
    							<option value="3" <c:if test="${property_status == '3'}">selected</c:if>>申请报废</option>
    							<option value="4" <c:if test="${property_status == '4'}">selected</c:if>>已报废</option>
    							<option value="5" <c:if test="${property_status == '5'}">selected</c:if>>申请入库</option>
    							<option value="6" <c:if test="${property_status == '6'}">selected</c:if>>入库</option>
    						</select>
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
					<display:table requestURI="${ctx}/asset/manageproperty.action" uid="item" class="displaytable" name="propertyList" 
					pagesize="15" sort="list"  excludedParams="*" form="QueryCondForm">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>					
						<display:column title="资产名称" property="property_name" maxLength="20"/>
						<display:column title="资产密级" property="seclv_name"/>
						<display:column title="采购人" property="user_name" /><%--
						<display:column title="单价" property="unit_price"/>		
						--%><display:column title="责任人" property="duty_user_name" />				
						<display:column title="入库时间" property="in_time"/>
						<display:column title="资产状态" property="property_status_str"/>	
						<display:column title="操作" style="width:150px">
							<div>
								<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/asset/viewpurchasedetail.action?event_code=${item.event_code}');"/>
								<c:choose>
									<c:when test="${item.property_status == 0}">
										&nbsp;<input type="button" class="button_2003" value="申请出库" onclick="chkChange('${item.event_code}');"/>
									</c:when>
									<c:otherwise>
										&nbsp;<input type="button" class="button_2003" value="申请出库" disabled="disabled"/>
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
