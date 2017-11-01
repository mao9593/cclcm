<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>查看磁介质列表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
    <script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>   
    <script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script>
	<!--
	$(document).ready(function(){
			onHoverInfinite();
			optionSelect();
			preCalendar();
		});		
	function clearFindForm(){
			QueryCondForm.device_barcode.value = "";
			$("#seclv_med").val("");
			$("#QueryCondForm :text").val("");
	}	
	function optionSelect(){
			$("#seclv_med").val(${seclv_code});
	}	
	function printBarcode(device_barcode){
		callServer2("${ctx}/device/printbarcode.action?device_barcode="+escape(device_barcode));
	}
	function updatePage(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {					
			if(xmlHttp.responseText != null && "" != xmlHttp.responseText){
				//alert(xmlHttp.responseText);
				var barcodetype = xmlHttp.responseText.split("#")[0];
				var fileno = xmlHttp.responseText.split("#")[1];
				var barcode = xmlHttp.responseText.split("#")[2];	
				var others = xmlHttp.responseText.split("#")[3];	
				var url = "${ctx}/device/configbarcode.action";
				var rValue = window.showModalDialog(url,'', 'dialogHeight:240px;dialogWidth:400px;center:yes;status:no;scroll:no;help:no;unadorned:no;resizable:no');
				if(rValue != null && rValue!= undefined && rValue.onOK == "Y"){
					var obj=new ActiveXObject("SprintCom.DataProcess.1");
					obj.PrintBarcodeInfo(barcodetype, fileno, barcode, rValue.n_dum, 1,others); 
					alert("条码打印完成");
				} 
			}else {
				alert("无相应条码规则或不需打条码，请联系系统管理员");	
			}
		}
	}  
	function manageType(){
		go("${ctx}/device/managedevicetype.action");
	}
	function exportLedger(formId,url,url1){
		document.getElementById(formId).action = url;
		document.getElementById(formId).submit();
		document.getElementById(formId).action = url1;
	}
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<form id="QueryCondForm" method="GET" action="${ctx}/enter/enterdevicehistory.action" name="QueryCondForm">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">磁介质列表</td>
	</tr>	
	<tr>
		<td align="right">
	 		<table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
					<tr>
						<td align="center">条码号：</td>
				 		<td>
				 			<input type="text" id="device_barcode" name="device_barcode" value="${device_barcode}"/>
				 		</td>
						<td align="center">密级 ：</td>
				 		<td>
				 			<select name="seclv_code" id="seclv_med">
								<option value="">--所有--</option>
								<s:iterator value="#request.seclvList" var="seclv">
									<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
								</s:iterator>
							</select>
				 		</td>
				 		<td align = "center">原磁介质编号：</td>
				 		<td>
				 		    <input type="text" id="device_series" name="device_series" value="${device_series}"/>
				 		</td>
				 	</tr>
				 	<tr>
				 		<td align="center">登记时间：</td>
				 		<td >
				        	<input type="text" name="startTime" readonly="readonly" style="cursor:hand;" value="${startTime_str}"/>
       						<img src="${ctx}/_image/time2.jpg" id="startTime_trigger">
			    		</td> 
			    		<td align="center">至：</td>
				 		<td>
				        	<input type="text" name="endTime" readonly="readonly" style="cursor:hand;" value="${endTime_str}"/>
    					   	<img src="${ctx}/_image/time2.jpg" id="endTime_trigger">
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
					<display:table requestURI="${ctx}/enter/enterdevicehistory.action" id="item" class="displaytable" name="deviceList" 
					pagesize="${pageSize}" sort="page" partialList="true" size="${totalSize}" excludedParams="*" form="QueryCondForm">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column property="device_name" title="磁介质名称"  maxLength="15"/>
						<display:column property="dept_name" title="所属部门" maxLength="15"/>
						<display:column property="device_barcode" title="条码号"  />
						<display:column property="device_series" title="原磁介质编号"  />
						<display:column property="seclv_name" title="密级"/>					
						<display:column property="enter_time_str"   sortable="true" title="登记时间"/>
						<display:column title="操作" style="width:400px">
							<c:if test="${!item.is_delete}">
								<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/ledger/viewcycledetail.action?type=DEVICE&barcode=${item.device_barcode}');"/>&nbsp;	
								<input type="button" class="button_2003" value="补打条码" onclick="printBarcode('${item.device_barcode}');"/>					
							</c:if>
							<c:if test="${item.is_delete}">
								${item.deleteStatus}
							</c:if>
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
