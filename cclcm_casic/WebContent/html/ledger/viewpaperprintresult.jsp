<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>查看纸质台账列表</title>
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
			
			$("#LedgerQueryCondForm").submit(function(){//标记只能标规定天数以内的	
			  var maxMarkFailedDays = "${maxChangeResultDays}";		  
			  var startTime = $(":input[name='start_time']").val();
			  if(maxMarkFailedDays!=""){
			    var diffSec = (1 + parseInt(maxMarkFailedDays))*1000*3600*24;
			    if(startTime==""){
			  	//alert("开始时间不能为空！");
			  	  var now = new Date();
			  	  now.setTime(now.getTime()-diffSec + 3600000);
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
			LedgerQueryCondForm.paper_barcode.value = "";
			LedgerQueryCondForm.duty_user_name.value = "";
			$("#seclv_med").val("");
			LedgerQueryCondForm.start_time.value = "";
			LedgerQueryCondForm.end_time.value = "";
			LedgerQueryCondForm.paper_state_id.value = "";
	}	
	function optionSelect(){
			$("#seclv_med").val(${seclv_code});
			$("#paper_state_id").val(${paper_state});
	}
	function signResult(paper_id,paper_barcode,fail_remark,page_count,user_iidd){
		$("#paper_id").val(paper_id);
		$("#paper_barcode").val(paper_barcode);
		$("#user_iidd").val(user_iidd);
		var url = "${ctx}/ledger/signprintresult.action?paper_id="+paper_id;
		var rValue=window.showModalDialog(url,'', 'dialogHeight:300px;dialogWidth:400px;center:yes;status:no;scroll:no;help:no;unadorned:no;resizable:no');
		if(rValue != null && rValue!= undefined){
			$("#page_count").val(rValue.page_count);
			$("#fail_remark").val(rValue.fail_remark);
			$("#isDestroyPaper").val(rValue.isDestroyPaper);
		
			callServerPostForm(url,document.forms[0]);
		}
	}
	function getAjaxResult(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			alert("标记成功");
			$("#LedgerQueryCondForm").submit();
		}
	} 
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<form method="POST"  id="signform">
	<input type="hidden" name="sign_ok" value="Y" />
	<input type="hidden" name="fail_remark" id="fail_remark"/>
	<input type="hidden" name="page_count" id="page_count"/>
	<input type="hidden" name="isDestroyPaper" id="isDestroyPaper"/>
	<input type="hidden" name="user_iidd" id="user_iidd" value="${curUser.user_iidd}"/>
<!-- 	<input type="hidden" name="paper_id" id="paper_id"/> -->
	<input type="hidden" name=paper_barcode id="paper_barcode"/>
</form>
<form id="LedgerQueryCondForm" method="GET" action="${ctx}/ledger/viewpaperprintresult.action">
	<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">用户打印纸张日志</td>
	</tr>
	<tr>
		<td align="right">
			<table  table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<td align="center">条码号：
					</td>
					<td>
						<input type="text" id="paper_barcode" name="paper_barcode" value="${paper_barcode}" size="15"/>&nbsp;
					</td>
					<td align="center">责任人：
					</td>
					<td>
						<input type="text" id="duty_user_name" name="duty_user_name" value="${duty_user_name}" size="15"/>&nbsp;
					</td>
					<td align="center">密级：
					</td>
					<td>
						<select name="seclv_code" id="seclv_med">
						<option value="">--所有--</option>
						<s:iterator value="#request.seclvList" var="seclv">
							<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
						</s:iterator>
						</select>
					</td>
					<td align="center" rowspan="2"> 
						<input name="button" type="submit" class="button_2003" value="查询" onclick="return checkDateTime();">
						<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">
					</td>
				</tr>
				<tr>
					<td align="center">状态：
					</td>
					<td>
						<select name="paper_state" id="paper_state_id">
							<option value="">--请选择--</option>
							<option value="0">留用</option>
							<option value="8">外发</option>
							<option value="9">归档</option>
						</select>
					</td>
					<td align="center">开始时间：
					</td>
					<td>
						<input type="text" name="start_time" readonly="readonly" style="cursor:hand;" value="${start_time}"/>
        				<img src="${ctx}/_image/time2.jpg" id="start_time_trigger">
					</td>
					<td align="center">结束时间：
					</td>
					<td>
						<input type="text" name="end_time" readonly="readonly" style="cursor:hand;" value="${end_time}"/>
        				<img src="${ctx}/_image/time2.jpg" id="end_time_trigger">
					</td>
				</tr>
			</table>
		</td>
		<tr>
    </tr>
 	
	</tr>
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
	   					<display:table requestURI="${ctx}/ledger/viewpaperprintresult.action" id="item" class="displaytable" name="paperLedgerList"
	   					pagesize="${pageSize}" sort="page" partialList="true" size="${totalSize}" excludedParams="*" form="LedgerQueryCondForm">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column property="paper_barcode" title="条码号"/>
						<display:column property="file_title" title="文件名" maxLength="35"/>
						<display:column property="duty_user_name" title="责任人"/>
						<display:column property="duty_dept_name" title="部门"/>
						<display:column property="seclv_name" title="密级"/>
						<display:column property="page_count" title="页数"/>
						<display:column property="paper_state_name" title="状态"/>
						<display:column property="print_time" sortable="true" title="打印时间"/>
						<display:column title="标记操作" style="width:100px">
							<c:choose>
								<c:when test="${item.print_result==0 && item.fail_remark !=''}">
									<input type="button" class="button_2003" value="已标记"  onclick="signResult('${item.paper_id}','${item.paper_barcode}','${item.fail_remark}','${user_iidd}' }),'${item.page_count}';"/>
								</c:when>
								<c:otherwise>
									<input type="button" class="button_2003" value="标记失败"  onclick="signResult('${item.paper_id}','${item.paper_barcode}','${item.fail_remark}','${item.page_count}');"/>&nbsp;&nbsp;
								</c:otherwise>
							</c:choose>
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
