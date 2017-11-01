<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>查看待外发纸质列表</title>
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
			QueryCondForm.send_num.value = "";
			QueryCondForm.paper_barcode.value = "";
			QueryCondForm.duty_user_name.value = "";
			$("#seclv_med").val("");
			QueryCondForm.start_time.value = "";
			QueryCondForm.end_time.value = "";	
			QueryCondForm.user_name.value = "";
			QueryCondForm.dept_name.value = "";			
	}	
	function optionSelect(){
			$("#seclv_med").val(${seclv_code});
	}
	function confirmSendPaper(paper_barcode, send_way){
		var url = "${ctx}/ledger/confirmsendpaper.action?paper_barcode="+escape(paper_barcode) + "&send_way=" + escape(send_way);
		var rValue=window.showModalDialog(url,'', 'dialogHeight:260px;dialogWidth:400px;center:yes;status:no;scroll:no;help:no;unadorned:no;resizable:no');
		if(rValue != null && rValue!= undefined){
			$("#comment").val(rValue.comment);
			$("#output_confidential_num").val(rValue.outputconfidentialnum);
			$("#update_user_name").val(rValue.updateusername);
			$("#update_dept_name").val(rValue.updatedeptname);
			callServerPostForm1(url,document.forms[0]);
		//alert("原有单号："+send_num+"传回单号："+rValue.send_num);
			/* if(send_num == rValue.send_num){
				$("#comment").val(rValue.comment);
				callServerPostForm1(url,document.forms[0]);
			}else{
				alert("填写的回执单号与实际回执单号不符，不能完成外发确认，请检查后重新操作！");
			}	 */		
		}
	}
	function getAjaxResult1(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
		//alert(xmlHttp.responseText);
			alert("该纸质载体已外发");
			$("#QueryCondForm").submit();
		}
	} 
	function rejectSendPaper(paper_barcode){
		//var url = "${ctx}/ledger/rejectsendpaper.action";
		var url = "${ctx}/ledger/rejectsendpaper.action?paper_barcode="+escape(paper_barcode);
		var rValue=window.showModalDialog(url,'', 'dialogHeight:240px;dialogWidth:400px;center:yes;status:no;scroll:no;help:no;unadorned:no;resizable:no');
		if(rValue != null && rValue!= undefined){
			$("#comment").val(rValue.comment);
			$("#reject_type").val(rValue.reject_type);
			callServerPostForm(url,document.forms[0]);
		}
	}
	function getAjaxResult(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			alert("该纸质载体已拒收");
			$("#QueryCondForm").submit();
		}
	} 
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<form method="POST"  id="rejectform">
	<input type="hidden" name="updateFlag" id="updateFlag" value="Y"/>
	<input type="hidden" name="reject_ok" value="Y" />
	<input type="hidden" name="comment" id="comment"/>
	<input type="hidden" name="output_confidential_num" id="output_confidential_num"/>
	<input type="hidden" name="update_user_name" id="update_user_name"/>
	<input type="hidden" name="update_dept_name" id="update_dept_name"/>
	<input type="hidden" name="reject_type" id="reject_type"/>
</form>
<form id="QueryCondForm" method="GET" action="${ctx}/ledger/handlesendpaper.action" name="QueryCondForm">
	<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">待外发纸质列表</td>
	</tr>
	<tr>
		<td>
			<table  table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
				<tr>					
					<td align="center">回执单号：</td>
					<td><input type="text" id="send_num" name="send_num" value="${send_num}" size="15"/></td>
					<td align="center">文件条码：</td>
					<td><input type="text" id="paper_barcode" name="paper_barcode" value="${paper_barcode}" size="15"/></td>
					<td align="center">责任人：</td>
					<td>
					    <input type="text" id="duty_user_name" name="duty_user_name" value="${duty_user_name}" onkeyup="selectRecvUser(this.value);"/><br>
    		            <div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
    	        	    </div>
					</td>	
					<td align="center">制作人：</td>
					<td>
						<input type="text" id="user_name" name="user_name" value="${user_name}" onkeyup="selectRecvUserCR(this.value);"/><br>
    		            <div id="allOptionsCR" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
    		            </div>
					</td>
					<td align="center" rowspan="2">
						<input name="button" type="submit" class="button_2003" value="查询" onclick="return checkDateTime();">
						<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">
					</td>
				</tr>
				<tr>
				    <td align="center">制作部门：</td>
				    <td>
				    <input type="text" id="dept_name" name="dept_name" value="${dept_name}" size="15"/>&nbsp;
				    </td>
					<td align="center">密级：</td>
					<td><select name="seclv_code" id="seclv_med">
							<option value="">--所有--</option>
							<s:iterator value="#request.seclvList" var="seclv">
								<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
							</s:iterator>
						</select>
					</td>
					<td align="center">开始时间：</td>
					<td>
						<input type="text" name="start_time" readonly="readonly" style="cursor:hand;" value="${start_time}"/>
	        			<img src="${ctx}/_image/time2.jpg" id="start_time_trigger"></td>
					<td align="center">结束时间：</td>
					<td>
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
						<display:table requestURI="${ctx}/ledger/handlesendpaper.action" id="item" class="displaytable" name="paperLedgerList"
	   					pagesize="${pageSize}" sort="page" partialList="true" size="${totalSize}" excludedParams="*" form="QueryCondForm">
							<display:column title="序号">
								<c:out value="${item_rowNum}"/>
							</display:column>
							<display:column property="duty_user_name" title="责任人"/>
							<display:column property="duty_dept_name" title="责任人部门"/>
							<display:column property="file_title" title="文件名" maxLength="35"/>
							<display:column property="paper_barcode" title="文件条码"/>
							<display:column property="seclv_name" title="文件密级"/>
							<display:column property="page_count" title="页数"/>
							<%-- <display:column property="send_num" title="回执单号"/> --%>
							<display:column property="send_way_str" title="外发方式"/>
							<display:column property="user_name" title="制作人"/>
							<%-- <display:column property="dept_name" title="制作人部门"/> --%>
							<display:column property="print_time" sortable="true" title="制作时间"/>						
							<display:column title="操作" style="width:180px">
							<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/ledger/viewpaperledgerinfo.action?paper_id=${item.paper_id}');"/>&nbsp;																			
							<input type="button" class="button_2003" value="外发确认" onclick="confirmSendPaper('${item.paper_barcode}', '${item.send_way}');"/>&nbsp;	
							<input type="button" class="button_2003" value="拒收" onclick="rejectSendPaper('${item.paper_barcode}');"/>							
							</display:column>
					</display:table>
				</td>
			</table>
         </td>
	</tr>
	</table>
</form>
</body>
</html>
