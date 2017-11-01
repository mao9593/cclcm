<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>查看待回收文件列表</title>
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
		addSelectAllCheckbox();
		preCalendarDay();
		optionSelect();
		if('${retrieveType}'.localeCompare("批量扫描回收") == 0){
			$("#changeRetrieve").attr("disabled","true");
			$("#retrieveType").val("批量扫描回收");
		}else if('${retrieveType}'.localeCompare("") == 0){
			$("#retrieveType").val("");
		}
	});
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
		var duty_user_iidd=$("#allOption").val();
		var duty_user_name=$("#allOption option[value='"+duty_user_iidd+"']").text();
		duty_user_name=duty_user_name.substring(0,duty_user_name.indexOf("/"));
		if(duty_user_iidd != ""){
			$("#duty_user_iidd").val(duty_user_iidd);
			$("#duty_user_name").val(duty_user_name);
			document.getElementById("allOptions").innerHTML="";
		}
	}
	function selectRecvUserCR(word){
    	var url = "${ctx}/basic/getfuzzyuser.action?source=CR";
	    if(word != ""){
		   callServer3(url,"fuzzy="+word);
	    }else{
		   document.getElementById("allOptionsCR").innerHTML="";
	         }
	} 
	function updateResult1(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
				if(xmlHttp.responseText.toString().length > 154){
					document.getElementById("allOptionsCR").innerHTML=xmlHttp.responseText;
				}else{
					document.getElementById("allOptionsCR").innerHTML="";
				}
			}else{
				document.getElementById("allOptionsCR").innerHTML="";
			}
	}
	function add_TrueCR(){
		var user_id=$("#allOptionCR").val();
		var user_name=$("#allOptionCR option[value='"+user_id+"']").text();
		user_name=user_name.substring(0,user_name.indexOf("/"));
		if(user_id != ""){
	    	$("#user_id").val(user_id);
			$("#user_name").val(user_name);
			document.getElementById("allOptionsCR").innerHTML="";
		}
	}
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
			LedgerQueryCondForm.user_name.value = "";
			LedgerQueryCondForm.dept_name.value = "";
//			LedgerQueryCondForm.paper_state_id.value = "";
	}	
	function optionSelect(){
			$("#seclv_med").val(${seclv_code});
//			$("#paper_state_id").val(${paper_state});
	}
	function exportLedger(formId,url,url1){
			document.getElementById(formId).action = url;
			document.getElementById(formId).submit();
			document.getElementById(formId).action = url1;
	}
	function addRetrieveJob(){
		if(confirm("您确认回收当前选中文件？")){
		
			var event_codes = "";
			if($(":checkbox:checked[value!='']").size()>0){
				$(":checkbox:checked[value!='']").each(function (){
					event_codes += this.value+",";
				});
				
				$("#event_codes").val(event_codes);
				var url = "${ctx}/ledger/retrievepaper.action";
				
			//	var rValue=window.showModalDialog("${ctx}/html/ledger/viewpapercomment.jsp",'', 'dialogHeight:240px;dialogWidth:400px;center:yes;status:no;scroll:no;help:no;unadorned:no;resizable:no');
				
			//	if(rValue != null && rValue!= undefined rValue.onOK == "Y"){
			//		$("#comment").val(rValue.comment);
			//	}
				
				callServerPostForm(url,document.forms[0]);
				
			}else{
				alert("请先勾选需要申请回收的文件");
				return false;
			}
		}
	}
	function rejectRetrieveJob(){
	    if(confirm("如果被退回，用户需要重新提交销毁申请，确定要退回该销毁申请？")){
	        var event_codes = "";
	        if($(":checkbox:checked[value!='']").size()>0){
	        	$(":checkbox:checked[value!='']").each(function (){
					event_codes += this.value+",";
				});
				$("#event_codes").val(event_codes);
				var url = "${ctx}/ledger/quithandlepaper.action";
				var rValue=window.showModalDialog("${ctx}/html/ledger/rejectviewpapercomment.jsp",'', 'dialogHeight:240px;dialogWidth:400px;center:yes;status:no;scroll:no;help:no;unadorned:no;resizable:no');
	          
	            if(rValue != null && rValue!= undefined && rValue.type == "Y"){
					$("#comment").val(rValue.comment);	
					callServerPostForm(url,document.forms[0]);
				}
				
	        }else{
	            alert("请先勾选需要退回的回收的文件");
				return false;
	        }
	    }
	}
	
	function RetrieveAll(){//回收所有检索结果
		if(confirm("您确认回收检索出的所有文件？")){
			//var rValue=window.showModalDialog("${ctx}/html/ledger/viewpapercomment.jsp",'', 'dialogHeight:240px;dialogWidth:400px;center:yes;status:no;scroll:no;help:no;unadorned:no;resizable:no');	
			//	if(rValue != null && rValue!= undefined){
			//		$("#hidcomment").val(rValue.comment);
			//	}
			
				if($("#paper_barcode").val() != "" && $("#paper_barcode").val() != null)
    			{
    				$("#hidpaper_barcode").val($("#paper_barcode").val());
    			}
    			
    			if($("#duty_user_name").val() != "" && $("#duty_user_name").val() != null)
    			{
    				$("#hidduty_user_name").val($("#duty_user_name").val());
    			}
    			
    			if($("#user_name").val() != "" && $("#user_name").val() != null)
    			{
    				$("#hiduser_name").val($("#user_name").val());
    			}
    			
    			if($("#dept_name").val() != "" && $("#dept_name").val() != null)
    			{
    				$("#hiddept_name").val($("#dept_name").val());
    			}
    			if($("#seclv_med").val() != "" && $("#seclv_med").val() != null)
    			{
    				$("#hidseclv_code").val($("#seclv_med").val());
    			}
    		
    			if($("#start_time").val() != "" && $("#start_time").val() != null)
    			{
    				$("#hidstart_time").val($("#start_time").val());
    			}
    			
    			if($("#end_time").val() != "" && $("#end_time").val() != null)
    			{
    				$("#hidend_time").val($("#end_time").val());
    			}
    		
    			if($("#hid_dept_ids").val() != "" && $("#hid_dept_ids").val() != null)
    			{
    				$("#hiddept_ids").val($("#hid_dept_ids").val());
    			}
    			
    		$("#RetrieveAllForm").submit();	
			//	var url = "${ctx}/ledger/retrieveall.action";
			
			//	callServerPostForm(url,document.forms[1]);
			
		}
	}
	function getAjaxResult(){
		if (xmlHttp.readyState == 4) {
			  var response = xmlHttp.responseText;
			  LedgerQueryCondForm.submit();
		}
	}
	function changeRetrieveJob(){
		$("#changeRetrieve").attr("disabled","true");
		$("#retrieveType").val("批量扫描回收");
	}
	</script>
</head>

<body oncontextmenu="self.event.returnValue=false">
<form method="post">
	<input type="hidden" name="comment" id="comment"/>
	<input type="hidden" name="event_codes" id="event_codes"/> 
	
</form>

<form id="RetrieveAllForm" method="post" action="${ctx}/ledger/retrieveall.action" >
	    <input type="hidden" value="" id="hidpaper_barcode" name="hidpaper_barcode"/>
	    <input type="hidden" value="" id="hidduty_user_name" name="hidduty_user_name"/>
	    <input type="hidden" value="" id="hiduser_name" name="hiduser_name"/>
	    <input type="hidden" value="" id="hiddept_name" name="hiddept_name"/>
	    <input type="hidden" value="" id="hidseclv_code" name="hidseclv_code"/>
	    <input type="hidden" value="" id="hidstart_time" name="hidstart_time"/>
	    <input type="hidden" value="" id="hidend_time" name="hidend_time"/>
	    <input type="hidden" value="" id="hiddept_ids" name="hiddept_ids"/>
	    <input type="hidden" name="hidretrieve_type" value="paper"/>
	    <input type="hidden" name="hidcomment" id="hidcomment"/>
	   
</form>

<form id="LedgerQueryCondForm" method="GET" action="${ctx}/ledger/viewpaper.action"> 
	<input type="hidden" name="hid_dept_ids" id="hid_dept_ids" value="${hid_dept_ids}"/>
	<input type="hidden" name="paper_barcodes" value="${paper_barcodes}"/>
	<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">待回收文件列表</td>
	</tr>
	<tr>
		<td align="right">
			<table  table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<td align="center">文件条码：
					</td>
					<td>
						<input type="text" id="paper_barcode" name="paper_barcode" value="${paper_barcode}" size="15"/>&nbsp;
					</td>
					<td align="center">责任人：
					</td>
					<td>
						<input type="text" id="duty_user_name" name="duty_user_name" value="${duty_user_name}" onkeyup="selectRecvUser(this.value);"/><br>
    		            <div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
    	        	    </div>
					</td>
					<td align="center">制作人：
					</td>
					<td>
						<input type="text" id="user_name" name="user_name" value="${user_name}" onkeyup="selectRecvUserCR(this.value);"/><br>
    		            <div id="allOptionsCR" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
    		            </div>
					</td>
						<td align="center">制作部门：
					</td>
					<td>
						<input type="text" id="dept_name" name="dept_name" value="${dept_name}" size="15"/>&nbsp;
					</td>
				</tr>
				<tr>
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
					<td align="center">开始时间：</td>
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
					<td align="center" colspan="2"> 
						<input name="button" type="button" class="button_2003" id="changeRetrieve" value="批量扫描回收" onclick="changeRetrieveJob();">
						<input type="hidden" name="retrieveType" id="retrieveType"/>&nbsp;&nbsp;&nbsp;
						<input name="button" type="submit" class="button_2003" value="查询" onclick="return checkDateTime();">
						<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">
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
						<display:table requestURI="${ctx}/ledger/viewpaper.action" id="item" class="displaytable" name="paperLedgerList"
	   					pagesize="${pageSize}" sort="page" partialList="true" size="${totalSize}" excludedParams="*" form="LedgerQueryCondForm">
						<display:column title="选择">
							<input type="checkbox" name="event_code" value="${item.paper_id}"/>
						</display:column>
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column property="duty_user_name" title="责任人"/>
						<display:column property="duty_dept_name" title="责任人部门"/>
						<display:column property="file_title" title="文件名称"/>
						<display:column property="paper_barcode" title="文件条码"/>
						<display:column property="seclv_name" title="文件密级"/>
						<display:column property="page_count" title="页数"/>
						<display:column property="user_name" title="制作人"/>
						<display:column property="dept_name" title="制作人部门"/>
						<display:column property="start_time" sortable="true" title="申请时间"/>
						<display:column title="操作" style="width:100px">
							<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/ledger/viewcycledetail.action?type=PAPER&barcode=${item.paper_barcode}');"/>
						</display:column>
					</display:table>
					</td>
				</tr>
			</table>
				<tr>
					<td>
						<input type="button" class="button_2003" value="批量回收" onclick="addRetrieveJob();"/>&nbsp;
						<input type="button" class="button_2003" value="批量退回" onclick="rejectRetrieveJob();"/>&nbsp;
						<input type="button" class="button_2003" value="全部回收" onclick="RetrieveAll();"/>&nbsp;
					</td>
				</tr>
	         </td>
		</tr>
	</table>
</form>
</body>
</html>
