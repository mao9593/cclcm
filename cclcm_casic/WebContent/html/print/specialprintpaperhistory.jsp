<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>特殊打印历史记录列表</title>
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
		preCalendarDay();
		optionSelect();
	});
	function preCalendarDay(){
		Calendar.setup({inputField: "start_time", button: "start_time_trigger", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d %H:%M:%S", cache: true, weekNumbers:true, showOthers: true, step: 1});
		Calendar.setup({inputField: "end_time", button: "end_time_trigger", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d %H:%M:%S", cache: true, weekNumbers:true, showOthers: true, step: 1});
	}
	function selectRecvUser(word){
	var url = "${ctx}/basic/getfuzzyuser.action";
	if(word != ""){
		callServer1(url,"fuzzy="+word);
	}else{
		document.getElementById("allOptions").innerHTML="";
	}
	} 
	function updateResult(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
				if(xmlHttp.responseText.toString().length > 154){
					document.getElementById("allOptions").innerHTML=xmlHttp.responseText;
				}else{
					document.getElementById("allOptions").innerHTML="";
				}
			}else{
				document.getElementById("allOptions").innerHTML="";
			}
	}
	function add_True(){
		var user_id=$("#allOption").val();
		var user_name=$("#allOption option[value='"+user_id+"']").text();
		if(user_id != ""){
			$("#user_id").val(user_id);
			document.getElementById("allOptions").innerHTML="";
		}
	}
	function clearFindForm(){
		LedgerQueryCondForm.paper_barcode.value = "";
		$("#seclv_med").val("");
		LedgerQueryCondForm.start_time.value = "";
		LedgerQueryCondForm.end_time.value = "";
		LedgerQueryCondForm.user_id.value = "";
		LedgerQueryCondForm.dept_id.value = "";
		LedgerQueryCondForm.dept_name.value = "";
	}	
	function optionSelect(){
		$("#seclv_med").val(${seclv_code});
	}
	
	function reprintBarcode(event_code,paper_barcode){
		$("#event_code").val(event_code);
		$("#hid_paper_barcode").val(paper_barcode);
		callServerPostForm("${ctx}/print/reprintbarcode.action",document.forms[0]);
	}
	function getAjaxResult(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {					
			if(xmlHttp.responseText != null && "" != xmlHttp.responseText){
				var barcodetype = xmlHttp.responseText.split("#")[0];
				var fileno = xmlHttp.responseText.split("#")[1];
				var barcode = xmlHttp.responseText.split("#")[2];
				var others = xmlHttp.responseText.split("#")[3];	//条码其他信息，如：部门、用户名、密级等			
				//alert("others:"+others);			
				var url = "${ctx}/enter/configbarcode.action";
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
<form method="POST"  id="hiddenform">
	<input type="hidden" name="event_code" id="event_code"/>
	<input type="hidden" name="paper_barcode" id="hid_paper_barcode"/>
	<input type="hidden" name="print_type" value="SPECIAL_PRINT"/>
</form>
<form id="LedgerQueryCondForm" method="GET" action="${ctx}/print/specialprintpaperhistory.action">
	<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">特殊打印记录</td>
	</tr>
	<tr>
		<td align="right">
			<table  table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<td align="center">文件条码：</td>
					<td align="center">
						<input type="text" id="paper_barcode" name="paper_barcode" value="${paper_barcode}" />
					</td>
					<td align="center">文件密级：</td>
					<td align="center">
						<select name="seclv_code" id="seclv_med">
							<option value="">--所有--</option>
							<s:iterator value="#request.seclvList" var="seclv">
								<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
							</s:iterator>
						</select>
					</td>
					<td align="center">开始时间：</td>
					<td align="center">				
						<input type="text" name="start_time" readonly="readonly" style="cursor:hand;" value="${start_time}"/>
        				<img src="${ctx}/_image/time2.jpg" id="start_time_trigger">
					</td>
					<td align="center" rowspan="2"> 
						<input name="button" type="submit" class="button_2003" value="查询" onclick="return checkDateTime();">&nbsp;&nbsp;
						<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">
				    </td>	
				</tr>
				<tr>
					<td align="center">申请人：</td>
					<td align="center">	
						<input type="text" id="user_id" name="user_id" value="${user_id}" onkeyup="selectRecvUser(this.value);"/><br>
    		         	<div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px"></div>
					</td>
					<td align="center">部门：</td>
					<td align="center">
						<input type="text"  id="dept_name" name="dept_name" readonly="readonly" style="cursor:hand;" onclick="openDeptSelect('dept_name','dept_id','radio')" value="${dept_name}" />
		      			<input type="hidden" name="dept_id" id="dept_id" value="${dept_id}"/>
					</td>
					<td align="center">结束时间：</td>
					<td align="center">
						<input type="text" name="end_time" readonly="readonly" style="cursor:hand;" value="${end_time}"/>
        				<img src="${ctx}/_image/time2.jpg" id="end_time_trigger">
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
	   					<display:table requestURI="${ctx}/print/specialprintpaperhistory.action" id="item" class="displaytable" name="paperLedgerList"
	   					pagesize="${pageSize}" sort="page" partialList="true" size="${totalSize}" excludedParams="*" form="LedgerQueryCondForm">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column property="user_name" title="申请人"/>
						<display:column property="dept_name" title="部门"/>
						<display:column property="file_title" title="文件名" maxLength="30"/>
						<display:column property="paper_barcode" title="文件条码"/>
						<display:column property="seclv_name" title="文件密级"/>
						<display:column property="create_type_name" title="制作方式"/>
						<display:column property="print_time" sortable="true" title="录入时间"/>						
						<display:column property="paper_state_name" title="状态"/>
						<display:column title="操作" style="width:150px">
							<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/ledger/viewpersonalpaperledgerinfo.action?paper_id=${item.paper_id}&seclv_code=${item.seclv_code} }');"/>&nbsp;
							<input type="button" class="button_2003" value="补打条码" onclick="reprintBarcode('${item.event_code}','${item.paper_barcode}');"/>
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
