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
			$("#device_status_id").val("");		
			$("#med_type_id").val("");	
			$("#QueryCondForm :text").val("");
	}	
	function optionSelect(){
			$("#seclv_med").val(${seclv_code});
			$("#device_status_id").val(${device_status});
			$("#med_type_id").val(${med_type});
	}	
	function destroyDeviceJob(device_code,seclv_code){
	    if(confirm("确定要申请销毁？")){	    
		$("#device_code").val(device_code);
		$("#seclv_code").val(seclv_code);
		$("#hid_form").submit();						    	
	    }
	}	
	function returnDevice(device_code){
		if(confirm("确定要归还？")){
			var url = "${ctx}/device/returndevice.action?device_code="+escape(device_code);
			callServer(url);
		}
	}	
	function canBorrow(device_code){
		if(confirm("确定要重置为在档可借？")){
			var url = "${ctx}/device/updatedevicestatus.action?device_status=0&device_code="+escape(device_code);
			callServer(url);
		}	
	}
	function toRepair(device_code){
		if(confirm("确定要送修？")){
			var url="${ctx}/device/updatedevicestatus.action?device_status=6&device_code="+escape(device_code);
			callServer(url);
		}
	}
	function updateResult(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			alert(xmlHttp.responseText);
			$("#QueryCondForm").submit();
		}
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
				var url = "${ctx}/device/configbarcode.action";
				var rValue = window.showModalDialog(url,'', 'dialogHeight:240px;dialogWidth:400px;center:yes;status:no;scroll:no;help:no;unadorned:no;resizable:no');
				if(rValue != null && rValue!= undefined && rValue.onOK == "Y"){
					var obj=new ActiveXObject("SprintCom.DataProcess.1");
					obj.PrintBarcode(barcodetype, fileno, barcode, rValue.n_dum, 0); 
					alert("条码打印完成");
				} 
			}else {
				alert("无相应条码规则或不需打条码，请联系系统管理员");	
			}
		}
	}  
	</script>
</head>

<body oncontextmenu="self.event.returnValue=false">
<form method="post" action="${ctx}/device/handledevicejob.action" id="hid_form">
	<input type="hidden" name="jobType" value="${jobType}"/>
	<input type="hidden" name="device_code" id="device_code"/>
	<input type="hidden" name="seclv_code" id="seclv_code"/> 
</form>
<form id="QueryCondForm" method="GET" action="${ctx}/device/managedevice.action" name="QueryCondForm">
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
				 		<td align="center">类型：</td>
				 		<td >
				 			<select name="med_type" id="med_type_id">
								<option value="">--所有--</option>				
								<option value="1">U盘</option>
				                <option value="2">移动硬盘</option>
				                <option value="3">便携式计算机</option>
				                <option value="4">照相机</option>
				                <option value="5">录像机</option>
				                <option value="6">录音笔</option>
				                <option value="8">软盘</option>
				                <option value="9">磁带</option>
				                <option value="10">录像带</option>
				                <option value="11">录音带</option>
				                <option value="12">移动光驱</option>
				                <option value="13">红黑电源</option>
				                <option value="14">安全设备</option>
				                <option value="15">多功能导入装置</option>
				                <option value="16">硬盘</option>					
							</select>
				 		</td>
				 		<td  rowspan="2">
				 			&nbsp;<input name="button" type="submit" class="button_2003" value="查询" onclick="return checkTime();">&nbsp;
							&nbsp;<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">
				 		</td>
				 	</tr>
				 	<tr>
				 		<td align="center">状态：</td>
				 		<td>
				 			<select name="device_status" id="device_status_id">
								<option value="">--所有--</option>
								<s:iterator value="#request.dsList" var="status">
									<option value="${status.key}">${status.name}</option>
								</s:iterator>					
							</select>
				 		</td>
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
					</tr>
				</table>	
	  </td>
	</tr>		
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
					<display:table requestURI="${ctx}/device/managedevice.action" id="item" class="displaytable" name="deviceList" 
					pagesize="15" sort="list" excludedParams="*" form="QueryCondForm">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column property="device_name" title="介质名称"  maxLength="15"/>
						<display:column property="dept_name" title="所属部门" maxLength="15"/>
						<display:column property="device_barcode" title="条码号"  />
						<display:column property="med_type_name" title="类型"  maxLength="15"/>
						<display:column property="seclv_name" title="密级"/>
						<display:column property="device_status_name" title="状态"  maxLength="15"/>						
						<display:column property="enter_time_str"   sortable="true" title="登记时间"/>
						<display:column title="操作" style="width:400px">
							<c:if test="${!item.is_delete}">
								<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/ledger/viewcycledetail.action?type=DEVICE&barcode=${item.device_barcode}');"/>&nbsp;	
								<c:choose>	
								<c:when test="${item.deviceStatus.key eq '0'}">
									<input type="button" class="button_2003" value="修改" onclick="go('${ctx}/device/updatedevice.action?device_barcode=${item.device_barcode}');"/>&nbsp;										
								</c:when>
								<c:otherwise>
									<input type="button" class="button_2003" value="修改" disabled="disabled";/>&nbsp;
								</c:otherwise>
								</c:choose>																	
								<c:choose>
								<c:when test="${item.deviceStatus.key eq '1'}">
									<input type="button" class="button_2003" value="归还" onclick="returnDevice('${item.device_code}')"/>&nbsp;
								</c:when>
								<c:otherwise>
									<input type="button" class="button_2003" value="归还" disabled="disabled"/>&nbsp;
								</c:otherwise>
								</c:choose>
								<c:choose>
								<c:when test="${item.deviceStatus.key eq '3' or item.deviceStatus.key eq '6'}">
									<input type="button" class="button_2003" value="还原" onclick="canBorrow('${item.device_code}')"/>&nbsp;
								</c:when>
								<c:otherwise>
									<input type="button" class="button_2003" value="还原" disabled="disabled"/>&nbsp;
								</c:otherwise>
								</c:choose>
								<c:choose>	
								<c:when test="${item.deviceStatus.key eq '0'}">
									<input type="button" class="button_2003" value="送修" onclick="toRepair('${item.device_code}')";/>&nbsp;
								</c:when>
								<c:otherwise>
									<input type="button" class="button_2003" value="送修" disabled="disabled";/>&nbsp;
								</c:otherwise>
								</c:choose>	
								<c:choose>	
								<c:when test="${item.deviceStatus.key eq '0'}">
									<input type="button" class="button_2003" value="申请销毁" onclick="destroyDeviceJob('${item.device_code}','${item.seclv_code}')";/>&nbsp;
								</c:when>
								<c:otherwise>
									<input type="button" class="button_2003" value="申请销毁" disabled="disabled";/>&nbsp;
								</c:otherwise>
								</c:choose>		
								<input type="button" class="button_2003" value="打印条码" onclick="printBarcode('${item.device_barcode}');"/>					
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
