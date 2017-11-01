<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>查看光盘结果信息</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
		<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
	<link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
	<script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
	<script>
	
	$(document).ready(function(){
			onHoverInfinite();
			preCalendarDay();
			optionSelect();
		});
	function preCalendarDay(){
		Calendar.setup({inputField: "start_time", button: "start_time_trigger", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d %H:%M:%S", cache: true, weekNumbers:true, showOthers: true, step: 1});
		Calendar.setup({inputField: "end_time", button: "end_time_trigger", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d %H:%M:%S", cache: true, weekNumbers:true, showOthers: true, step: 1});
	}	
	function clearFindForm(){
			LedgerQueryCondForm.cd_barcode.value = "";
			$("#seclv_med").val("");
			LedgerQueryCondForm.start_time.value = "";
			LedgerQueryCondForm.duty_user_name.value = "";
			LedgerQueryCondForm.end_time.value = "";
	}	
	function optionSelect(){
			$("#seclv_med").val(${seclv_code});
			//$("#cd_state_id").val(${cd_state});
	}
	function reprintBarcode(event_code,cd_barcode,create_type){
		$("#event_code").val(event_code);
		$("#cd_barcode").val(cd_barcode);
		$("#create_type").val(create_type);
		callServerPostForm("${ctx}/ledger/reprintcdbarcode.action",document.forms[0]);
	}
	function getAjaxResult(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {		
			if(xmlHttp.responseText != null && "" != xmlHttp.responseText){
				var barcodetype = xmlHttp.responseText.split("#")[0];
				var fileno = xmlHttp.responseText.split("#")[1];
				var barcode = xmlHttp.responseText.split("#")[2];
				var others = xmlHttp.responseText.split("#")[3];	//条码其他信息，如：部门、用户名、密级等	

				var url = "${ctx}/ledger/configbarcode.action";
				var rValue = window.showModalDialog(url,'', 'dialogHeight:240px;dialogWidth:400px;center:yes;status:no;scroll:no;help:no;unadorned:no;resizable:no');
				if(rValue != null && rValue!= undefined && rValue.onOK == "Y"){
					var obj=new ActiveXObject("SprintCom.DataProcess.1");
					obj.PrintBarcodeInfo(barcodetype, fileno, barcode,rValue.n_dum, 1,others); 
					alert("补打完成");	
				}
			}else {
				alert("无相应条码规则或不需打条码，请联系系统管理员");	
			}
		}
	} 
	</script>
</head>

<body oncontextmenu="self.event.returnValue=false">
	<input type="hidden" name="onOk" id="onOk"/>
	<input type="hidden" name="n_dum" id="n_dum"/>
<form method="POST"  id="hiddenform">
	<input type="hidden" name="event_code" id="event_code"/>
	<input type="hidden" name="cd_barcode" id="cd_barcode"/>
	<input type="hidden" name="create_type" id="create_type"/>
</form>
<form id="LedgerQueryCondForm" method="GET" action="${ctx}/ledger/viewcdreprintbarcode.action">
	<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">查看光盘结果信息</td>
	</tr>
	<tr>
		<td align="right">
			<table  table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
				<tr>
		 			<td align="center" >条码号：</td>
		 			<td>
		 				<input type="text" id="cd_barcode" name="cd_barcode" value="${cd_barcode}" size="10"/>
		 			</td>
		 			<td align="center" >责任人：</td>
		 			<td>
		 				<input type="text" id="duty_user_name" name="duty_user_name" value="${duty_user_name}" size="10"/>
		 			</td>
					<td align="center" >密级：</td>
					<td>
						<select name="seclv_code" id="seclv_med">
							<option value="">--所有--</option>
							<s:iterator value="#request.seclvList" var="seclv">
								<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
							</s:iterator>
						</select>
					</td>
					<td align="center" >制作类型:</td>
					<td>
	        			<select name="create_type" id="create_type">
	        					<c:if test="${create_type == 'LEADIN' }">
	        					<option value="LEADIN">录入</option>
								<option value="BURN">刻录</option>
	        					</c:if>
        						<c:if test="${create_type == 'BURN' }">
								<option value="BURN">刻录</option>
        						<option value="LEADIN">录入</option>
								</c:if>
    					</select>
				  </td>
				<tr>
				</tr>
					<td align="center" >开始时间：</td>
					<td>
						<input type="text" name="start_time" readonly="readonly" style="cursor:hand;" value="${start_time}"/>
		        		<img src="${ctx}/_image/time2.jpg" id="start_time_trigger">
		        	</td>
		        	<td align="center" >结束时间：</td>
		        	<td >
		        		<input type="text" name="end_time" readonly="readonly" style="cursor:hand;" value="${end_time}"/>
		        		<img src="${ctx}/_image/time2.jpg" id="end_time_trigger">
		        	</td>
					<td colspan="4"  align="center" >
						<input name="button" type="submit" class="button_2003" value="查询" onclick="return checkDateTime();">
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
	   					<display:table requestURI="${ctx}/ledger/viewcdreprintbarcode.action" id="item" class="displaytable" name="cDLedgerList"
	   					pagesize="${pageSize}" sort="page" partialList="true" size="${totalSize}" excludedParams="*" form="LedgerQueryCondForm">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column property="cd_barcode" title="条码号"/>
						<display:column property="file_list" title="文件列表" maxLength="35"/>
						<display:column property="duty_user_name" title="责任人"/>
						<display:column property="duty_dept_name" title="部门"/>
						<display:column property="seclv_name" title="密级"/>
						<display:column property="cd_state_name" title="状态"/>
						<display:column title="制作方式">
							<c:choose>
								<c:when test="${item.create_type eq 'BURN' }">
								刻录
								</c:when>
								<c:otherwise>录入</c:otherwise>
							</c:choose>
						</display:column>
						<display:column property="burn_time" sortable="true" title="刻录时间"/>
						<display:column title="操作" style="width:100px">
							&nbsp;&nbsp;<input type="button" class="button_2003" value="补打条码" onclick="reprintBarcode('${item.event_code}','${item.cd_barcode}','${item.create_type }');"/>
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
