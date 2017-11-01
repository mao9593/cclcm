<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>查看光盘刻录结果信息</title>
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
			
			$("#LedgerQueryCondForm").submit(function(){//标记只能标规定天数以内的			  
			  var maxMarkFailedDays = "${maxChangeResultDays}";		  
			  var startTime = $(":input[name='start_time']").val();
			  if(maxMarkFailedDays!=""){
			    var diffSec = (1 + parseInt(maxMarkFailedDays))*1000*3600*24;
			    if(startTime==""){
			  	//alert("开始时间不能为空！");
			  	  var now = new Date();
			  	  now.setTime(now.getTime()-diffSec+3600000);
			  	  var dateCanSel = now.getYear() +"-"+(now.getMonth()+1)+"-"+now.getDate()+" "+now.getHours()+":"+now.getMinutes()+":"+now.getSeconds();
			      $(":input[name='start_time']").val(dateCanSel);
			    }
			    else{
			      var now = new Date();
			  	  var d = startTime.split(" ")[0].split("-");
			  	  var t = startTime.split(" ")[1].split(":");
			  	  var start = new Date(d[0],d[1]-1,d[2],t[0],t[1],t[2]);
			  	  var diff = now.getTime() - start.getTime();
			  	  if(diff > diffSec){
			  		alert("查询开始时间不能早于"+maxMarkFailedDays+"天前");
			  		return false;
			  	  }
			    }
			  }
			});
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
			$("#cd_state_id").val(${cd_state});
	}
	function signResult(cd_id,cd_barcode,fail_remark){
		$("#cd_id").val(cd_id);
		$("#cd_barcode").val(cd_barcode);
		var url = "${ctx}/ledger/signburnresult.action?cd_id="+cd_id;
		var rValue=window.showModalDialog(url,'', 'dialogHeight:500px;dialogWidth:500px;center:yes;status:no;scroll:no;help:no;unadorned:no;resizable:no');
		
		if(rValue != null && rValue!= undefined){
		
			$("#fail_remark").val(rValue.fail_remark);		
			$("#isRetrieveCD").val(rValue.isRetrieveCD);		
			$("#file_list").val(rValue.file_list);		
			$("#file_num").val(rValue.file_num);
	
			callServerPostForm(url,document.forms[0]);
		}
	}
	function getAjaxResult(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			alert("标记成功");
			$("#LedgerQueryCondForm").submit();
		}
	} 
	function reprintBarcode(event_code,cd_barcode){
		$("#event_code").val(event_code);
		$("#hid_cd_barcode").val(cd_barcode);
		callServerPostForm1("${ctx}/ledger/reprintburnbarcode.action",document.forms[1]);
	}
	function getAjaxResult1(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {					
			if(xmlHttp.responseText != null && "" != xmlHttp.responseText){
				//alert(xmlHttp.responseText);
				var barcodetype = xmlHttp.responseText.split("#")[0];
				var fileno = xmlHttp.responseText.split("#")[1];
				var barcode = xmlHttp.responseText.split("#")[2];		
				var url = "${ctx}/ledger/configbarcode.action";
				var rValue = window.showModalDialog(url,'', 'dialogHeight:240px;dialogWidth:400px;center:yes;status:no;scroll:no;help:no;unadorned:no;resizable:no');
				if(rValue != null && rValue!= undefined && rValue.onOK == "Y"){
					var obj=new ActiveXObject("SprintCom.DataProcess.1");
					//alert("barcodetype:"+barcodetype+"--fileno:"+fileno+"--barcode:"+barcode+"--n_dum:"+rValue.n_dum);
					obj.PrintBarcode(barcodetype, fileno, barcode, rValue.n_dum, 0); 
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
<form method="POST"  id="signform">
	<input type="hidden" name="sign_ok" value="Y" />
	<input type="hidden" name="fail_remark" id="fail_remark"/>
	<input type="hidden" name="file_num" id="file_num"/>
	<input type="hidden" name="file_list" id="file_list"/>
	<input type="hidden" name="isRetrieveCD" id="isRetrieveCD"/>
<!-- 	<input type="hidden" name="cd_id" id="cd_id"/> -->
	<input type="hidden" name="cd_barcode" id="cd_barcode"/>
</form>
<form method="POST"  id="hiddenform">
	<input type="hidden" name="event_code" id="event_code"/>
	<input type="hidden" name="cd_barcode" id="hid_cd_barcode"/>
</form>
<form id="LedgerQueryCondForm" method="GET" action="${ctx}/ledger/viewcdburnresult.action">
	<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">查看光盘刻录结果信息</td>
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
					<td colspan="2"  align="center" >
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
	   					<display:table requestURI="${ctx}/ledger/viewcdburnresult.action" id="item" class="displaytable" name="cDLedgerList"
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
						<display:column property="burn_time" sortable="true" title="刻录时间"/>
						<display:column title="标记操作" style="width:170px">
							<c:choose>
								<c:when test="${item.burn_result==0 && item.fail_remark !=''}">
									<input type="button" class="button_2003" value="已标记"  onclick="signResult('${item.cd_id}','${item.cd_barcode}','${item.fail_remark}');"/>
								</c:when>
								<c:otherwise>
									<input type="button" class="button_2003" value="标记失败" onclick="signResult('${item.cd_id}','${item.cd_barcode}');"/>
								</c:otherwise>
							</c:choose>
							<!-- &nbsp;&nbsp;<input type="button" class="button_2003" value="补打条码" onclick="reprintBarcode('${item.event_code}','${item.cd_barcode}');"/> -->
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
