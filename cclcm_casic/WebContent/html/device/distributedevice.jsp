<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>可分配的磁介质列表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script>
	$(document).ready(function(){
			onHover();			
		});				
	function distributeDevice(device_code,event_code){
	    if(confirm("确定要借出？")){	 	    
		$("#device_code").val(device_code);
		$("#event_code").val(event_code);
		$("#hid_form").submit();
	    }
	}
	</script>
</head>

<body oncontextmenu="self.event.returnValue=false">
<form method="post" action="${ctx}/device/distributedevice.action" id="hid_form">
	<input type="hidden" name="device_code" id="device_code"/>
	<input type="hidden" name="event_code" id="event_code"/> 
	<input type="hidden" name="is_submit" value="Y"/> 
</form>
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
<form id="QueryCondForm" method="POST" action="${ctx}/device/distributedevice.action">
	<tr>
		<td class="title_box">可分配的磁介质列表</td>
	</tr>			
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
					<display:table requestURI="${ctx}/device/distributedevice.action" id="item" class="displaytable" name="deviceList" pagesize="15" sort="list">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column property="device_name" title="介质名称"  maxLength="15"/>
						<display:column property="dept_name" title="所属部门" maxLength="15"/>
						<display:column property="device_series" title="编号"  maxLength="15"/>
						<display:column property="device_barcode" title="条码号" />
						<display:column property="med_type_name" title="类型"  maxLength="15"/>
						<display:column property="seclv_name" title="密级"/>					
						<display:column property="enter_time_str"   sortable="true" title="登记时间"/>
						<display:column title="操作" style="width:120px">							
							<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/device/viewdevicedetail.action?device_barcode=${item.device_barcode}');"/>&nbsp;																									
							<input type="button" class="button_2003" value="借出" onclick="distributeDevice('${item.device_code}','${event_code}')";/>																			
						</display:column>
					</display:table>
					</td>
				</tr>				
			</table>
         </td>
	</tr>
	<tr>
		<td align="center"><input type="button" value="返回" class="button_2003" onclick="javascript:history.go(-1);"></td>
	</tr>
</table>
</form>
</body>
</html>
