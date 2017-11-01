<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>查看存储介质列表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>   
	<script>
	<!--
	$(document).ready(function(){
		onHoverInfinite();
		optionSelect();
	});		
	function clearFindForm(){
		$("#storage_barcode").val("");
		$("#storage_name").val("");
		$("#use_user_name").val("");
		$("#duty_user_name").val("");
		$("#seclv_code").val("");
		$("#med_type").val("");
		$("#storage_status").val("");
	}	
	function optionSelect(){
		$("#seclv_code").val("${seclv_code}");
		$("#med_type").val("${med_type}");
		$("#storage_status").val("${storage_status.key}");
	}
	function addStorage(){
		go("${ctx}/storage/addstorage.action");
	}
	function updateStorageStatus(storage_code,storage_status){
		var url = "${ctx}/storage/updatestoragestatus.action?storage_code="+storage_code+"&storage_status="+storage_status;
		callServer(url);
	}
	function updateResult(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			alert(xmlHttp.responseText);
			$("#QueryCondForm").submit();
		}
	}
	function distributeStorage(storage_code){
		var rValue = window.showModalDialog("${ctx}/html/storage/distribute.jsp",'', 'dialogHeight:300px;dialogWidth:600px;center:yes;status:no;scroll:yes;help:no;unadorned:no;resizable:yes');
		if(rValue != undefined && rValue != null){
			var url = "${ctx}/storage/distributestorage.action?storage_code="+storage_code+"&use_user_iidd="+rValue;
			callServer2(url);
		}
	}
	function updatePage(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			alert(xmlHttp.responseText);
			$("#QueryCondForm").submit();
		}
	}
	function printBarcode(storage_barcode){
		$("#hid_storage_barcode").val(storage_barcode);
		callServerPostForm("${ctx}/storage/printbarcode.action",document.forms[0]);
	}
	function getAjaxResult(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {					
			if(xmlHttp.responseText != null && "" != xmlHttp.responseText){
				//alert(xmlHttp.responseText);
				var barcodetype = xmlHttp.responseText.split("#")[0];
				var fileno = xmlHttp.responseText.split("#")[1];
				var barcode = xmlHttp.responseText.split("#")[2];
				var others = xmlHttp.responseText.split("#")[3];		
				var url = "${ctx}/storage/configbarcode.action";
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
	</script>
</head>

<body oncontextmenu="self.event.returnValue=false">
<form method="POST"  id="hiddenform">
	<input type="hidden" name="storage_barcode" id="hid_storage_barcode"/>
</form>
<form id="QueryCondForm" method="GET" action="${ctx}/storage/viewstorage.action" name="QueryCondForm">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">存储介质列表</td>
	</tr>	
	<tr>
		<td>
			<table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<td width="10%" align="center">载体条码号：</td>
					<td width="15%"><input type="text" id="storage_barcode" name="storage_barcode" value="${storage_barcode}" size="15"/></td>
					<td width="10%" align="center">介质名称：</td>					
					<td width="15%"><input type="text" id="storage_name" name="storage_name" value="${storage_name}" size="15"/></td>
					<td width="10%" align="center">使用用户名称：</td>					
					<td width="15%"><input type="text" id="use_user_name" name="use_user_name" value="${use_user_name}" size="15"/></td>
					<td width="10%" align="center">责任人名称：</td>					
					<td width="15%"><input type="text" id="duty_user_name" name="duty_user_name" value="${duty_user_name}" size="15"/>&nbsp;</td>
				</tr>
				<tr>
					<td align="center">密级：</td>
					<td>
						<select name="seclv_code" id="seclv_code">
							<option value="">--所有--</option>
							<s:iterator value="#request.seclvList" var="seclv">
								<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
							</s:iterator>
						</select>
					</td>
					<td align="center">类型：</td>
					<td>
						<select name="med_type" id="med_type">
							<option value="">--所有--</option>				
							<option value="1">硬盘</option>
							<option value="2">存储卡</option>
							<option value="3">其他</option>							
						</select>
					</td>
					<td align="center">状态：</td>
					<td>
						<select name="storage_status" id="storage_status">
							<option value="">--所有--</option>
							<s:iterator value="#request.statusList" var="status">
								<option value="${status.key}">${status.name}</option>
							</s:iterator>					
						</select>
					</td>
					<td colspan="2" align="center">
						<input name="button" type="submit" class="button_2003" value="查询">&nbsp;
						<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">&nbsp;
						<input name="button" type="button" class="button_2003" value="录入存储介质" onclick="addStorage();">
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
					<display:table requestURI="${ctx}/storage/viewstorage.action" id="item" class="displaytable" name="storageList" 
					pagesize="3" sort="list" excludedParams="*" form="QueryCondForm">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column property="storage_name" title="介质名称"  maxLength="15"/>
						<display:column property="dept_name" title="所属部门" maxLength="15"/>
						<display:column property="storage_barcode" title="条码号"  maxLength="30"/>
						<display:column property="med_type_name" title="类型"  maxLength="10"/>
						<display:column property="seclv_name" title="密级"/>
						<display:column property="storage_status_name" title="状态"  maxLength="10"/>
						<display:column property="duty_user_name" title="责任人"/>						
						<display:column title="当前使用人">
							<c:choose>
								<c:when test="${not empty item.use_user_iidd}">
									${item.use_user_name}
								</c:when>
								<c:when test="${item.storage_status.key == '0'}">
									<input type="button" value="分配" class="button_2003" onclick="distributeStorage('${item.storage_code}');"/>
								</c:when>
								<c:otherwise>
									<input type="button" value="报废" class="button_2003" disabled="disabled"/>
								</c:otherwise>
							</c:choose>
						</display:column>
						<display:column title="操作" style="width:380px">
							<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/storage/viewstoragedetail.action?storage_code=${item.storage_code}');"/>&nbsp;	
							<c:if test="${item.storage_status.key == '0'}">
								<input type="button" class="button_2003" value="修改" onclick="go('${ctx}/storage/updatestorage.action?storage_code=${item.storage_code}');"/>&nbsp;
								<input type="button" class="button_2003" value="收回" disabled="disabled"/>&nbsp;																			
								<input type="button" class="button_2003" value="送修" onclick="if(confirm('确定要送修该存储介质？'))updateStorageStatus('${item.storage_code}','2');"/>&nbsp;
								<input type="button" class="button_2003" value="还原" disabled="disabled"/>&nbsp;
								<input type="button" class="button_2003" value="报废" onclick="if(confirm('确定要报废该存储介质？'))updateStorageStatus('${item.storage_code}','3');"/>&nbsp;
							</c:if>
							<c:if test="${item.storage_status.key == '1'}">
								<input type="button" class="button_2003" value="修改" disabled="disabled"/>&nbsp;
								<input type="button" class="button_2003" value="收回" onclick="if(confirm('确定要收回该存储介质？'))updateStorageStatus('${item.storage_code}','0');"/>&nbsp;																			
								<input type="button" class="button_2003" value="送修" onclick="if(confirm('确定要送修该存储介质？'))updateStorageStatus('${item.storage_code}','2');"/>&nbsp;
								<input type="button" class="button_2003" value="还原" disabled="disabled"/>&nbsp;
								<input type="button" class="button_2003" value="报废" disabled="disabled"/>&nbsp;
							</c:if>
							<c:if test="${item.storage_status.key == '2'}">
								<input type="button" class="button_2003" value="修改" disabled="disabled"/>&nbsp;
								<input type="button" class="button_2003" value="收回" disabled="disabled"/>&nbsp;																			
								<input type="button" class="button_2003" value="送修" disabled="disabled"/>&nbsp;
								<input type="button" class="button_2003" value="还原" onclick="if(confirm('确定要还原该存储介质？'))updateStorageStatus('${item.storage_code}','-2');"/>&nbsp;
								<input type="button" class="button_2003" value="报废" onclick="if(confirm('确定要报废该存储介质？'))updateStorageStatus('${item.storage_code}','3');"/>&nbsp;
							</c:if>
							<c:if test="${item.storage_status.key == '3'or item.storage_status.key == '4'}">
								<input type="button" class="button_2003" value="修改" disabled="disabled"/>&nbsp;
								<input type="button" class="button_2003" value="收回" disabled="disabled"/>&nbsp;																			
								<input type="button" class="button_2003" value="送修" disabled="disabled"/>&nbsp;
								<input type="button" class="button_2003" value="还原" disabled="disabled"/>&nbsp;
								<input type="button" class="button_2003" value="报废" disabled="disabled"/>&nbsp;
							</c:if>
							<input type="button" class="button_2003" value="打印条码" onclick="printBarcode('${item.storage_barcode}');"/>
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
