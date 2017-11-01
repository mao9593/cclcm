<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>查看个人录入磁介质台账列表</title>
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
			addSelectAllCheckbox();
			if("${secError}" == "Y"){
			alert("勾选的载体密级不一致");
		}
		});		
	function clearFindForm(){
			$("#LedgerQueryCondForm :text").val("");
		    $("#LedgerQueryCondForm select").val("");
	}	
	function optionSelect(){
			$("#seclv_med").val(${seclv_code});
	}
	function exportLedger(formId,url,url1){
			document.getElementById(formId).action = url;
			document.getElementById(formId).submit();
			document.getElementById(formId).action = url1;
	}
	function chkSubmit(){
		var seclv_code = -1;
		var submitable = true;
		var device_barcodes = "";
		if($(":checkbox:checked[value!='']").size()>0){
			$(":checkbox:checked[value!='']").each(function (){
			    device_barcodes += this.value+",";
			    $("#device_barcodes").val(device_barcodes);
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
					$("#LedgerQueryCondForm").attr("action","${ctx}/device/handledevicejob.action");
					$("#LedgerQueryCondForm").submit();	
				}
				return true;
			}else{
				return false;
			}
		}else{
			alert("请先勾选任务");
			return false;
		}
	}	
	</script>
</head>

<body oncontextmenu="self.event.returnValue=false">
<%-- <form id="LedgerQueryCondForm" method="GET" action="${ctx}/ledger/viewpersonalledindeviceledger.action">
    <input type="hidden" id="device_barcodes" name="device_barcodes" value=""/>&nbsp; --%>
	<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">个人录入磁介质台账</td>
	</tr>
	<form id="LedgerQueryCondForm" method="GET" action="${ctx}/ledger/viewpersonalledindeviceledger.action">
    <input type="hidden" id="device_barcodes" name="device_barcodes" value=""/>&nbsp;
	<tr>
		<td align="right">
			<table  table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<td width="8%" align="center">条码号：</td>
					<td width="17%">
						<input type="text" id="device_barcode" name="device_barcode" value="${device_barcode}" size="15"/>&nbsp;
					</td>
					<td width="8%" align="center">密级：</td>
					<td width="17%">
						<select name="seclv_code" id="seclv_code">
							<option value="">--所有--</option>
							<s:iterator value="#request.seclvList" var="seclv">
								<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
							</s:iterator>
						</select>
					</td>
					<td width="8%" align="center">原磁介质编号：</td>
					<td width="17%">
						<input type="text" id="device_series" name="device_series" value="${device_series}" size="15"/>&nbsp;
					</td>
					<td width="8%" align="center">磁介质名称：</td>
					<td width="17%">
						<input type="text" id="device_name" name="device_name" value="${device_name}" size="15"/>&nbsp;
					</td>
					<td align="center"> 
						<input name="button" type="submit" class="button_2003" value="查询">&nbsp;&nbsp;
						<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">&nbsp;&nbsp;
						<input type="button" class="button_2003" value="导出EXCEL" onclick="exportLedger('LedgerQueryCondForm','${ctx}/ledger/exportpersonaldeviceledger.action','${ctx}/ledger/viewpersonaldeviceledger.action');"/>
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
	   					<display:table requestURI="${ctx}/ledger/viewpersonalledindeviceledger.action" id="item" class="displaytable" name="deviceLedgerList"
	   					pagesize="${pageSize}" sort="page" partialList="true" size="${totalSize}" excludedParams="*" form="LedgerQueryCondForm">
						<display:column title="选择">							
			   					<span id="checkbox">
			   					<c:choose>
									<c:when test="${item.device_status_name == '留用'}">
										<input type="checkbox" name="_chk" value="${item.device_barcode}" id="${item.seclv_code}"/>
									</c:when>
									<c:otherwise>
										${item.device_status_name}
									</c:otherwise>
								</c:choose>
								</span>
							</display:column>
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column property="device_name" title="磁介质名称"/>
						<display:column property="device_barcode" title="条码号"/>
						<display:column property="device_series" title="原磁介质编号" />
						<display:column property="seclv_name" title="密级"/>
						<display:column property="device_status_name" title="状态"/>
						<display:column title="操作" style="width:100px">
							<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/ledger/viewcycledetail.action?type=DEVICE&barcode=${item.device_barcode}');"/>
						</display:column>
					</display:table>
					</td>
				</tr>
			</table>
         </td>
	</tr>	
	</table>
	<table width="95%" align="center" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr height="40">
		<td align="center">
			<label style="width: 80">
				<input type="radio" name="jobType" value="DESTROY_DEVICE_BYSELF"/>销毁
			</label>
			<input type="button" value="提交" class="button_2003" style="margin-left: 30px" onclick="return chkSubmit();"/>
		</td>
	</tr>
</table>
</form>
</body>
</html>
