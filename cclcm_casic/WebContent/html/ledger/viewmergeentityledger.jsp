<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>台账合并新台账列表</title>
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
		preCalendar();
	});
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
	function clearFindForm(){
		LedgerQueryCondForm.paper_barcode.value = "";
		LedgerQueryCondForm.file_title.value = "";
		LedgerQueryCondForm.startTime.value = "";
		LedgerQueryCondForm.endTime.value = "";
		LedgerQueryCondForm.user_name.value = "";
		LedgerQueryCondForm.user_id.value = "";
		LedgerQueryCondForm.dept_id.value = "";
		LedgerQueryCondForm.dept_name.value = "";
	}	
	function reprintBarcode(paper_barcode){
		var url = "${ctx}/copy/configbarcode.action";
		var rValue = window.showModalDialog(url,'', 'dialogHeight:240px;dialogWidth:400px;center:yes;status:no;scroll:no;help:no;unadorned:no;resizable:no');
		if(rValue != null && rValue!= undefined && rValue.onOK == "Y"){
			$("#n_dum").val(rValue.n_dum);
			$("#hid_paper_barcode").val(paper_barcode);
			callServerPostForm("${ctx}/print/reprintbarcode.action",document.forms[0]);
		}
	}
	function getAjaxResult(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {					
			if(xmlHttp.responseText != null && "" != xmlHttp.responseText){
				//alert(xmlHttp.responseText);
				var barcodetype = xmlHttp.responseText.split("#")[0];
				var fileno = xmlHttp.responseText.split("#")[1];
				var barcode = xmlHttp.responseText.split("#")[2];		
				var others = "";
				var obj=new ActiveXObject("SprintCom.DataProcess.1");
				obj.PrintBarcodeInfo(barcodetype, fileno, barcode, $("#n_dum").val(), 1,others); 
				alert("输出条码完成");	
				$("#LedgerQueryCondForm").submit();
				
			}else {
				alert("无相应条码规则或不需打条码，请联系系统管理员");	
			}
		}
	}
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<form method="POST"  id="hiddenform">
	<input type="hidden" name="print_type" id="print_type" value="merge"/>
	<input type="hidden" name="paper_barcode" id="hid_paper_barcode"/>
	<input type="hidden" id="n_dum"/>
</form>
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">台账合并新台账列表</td>
	</tr>
	<form id="LedgerQueryCondForm" method="get" action="${ctx}/ledger/viewmergeentityledger.action">
	<tr>
		<td align="right">
			<table  table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<td width="10%" align="center">文件条码：</td>
					<td width="17%">
						<input type="text" id="paper_barcode" name="paper_barcode" value="${paper_barcode}"/>&nbsp;
					</td>
					
					<td width="10%" align="center">文件名：</td>
					<td width="17%">
						<input type="text" id="file_title" name="file_title" value="${file_title}"/>&nbsp;
					</td>
					<td width="10%" align="center">密级：</td>
					<td width="13%">
						<select name="seclv_code" id="seclv_code">
						<option value="">--请选择--</option>
							<s:iterator value="#request.seclvList" var="seclv">
								<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
							</s:iterator>
						</select>
					</td>
				</tr>
				<tr>
					<td align="center">责任人: </td>
					<td>
	    		       <input type="text" id="user_name" name="user_name" value="${user_name}" onkeyup="selectRecvUserCR(this.value);"/><br>
	    		       <input type="hidden" name="user_id" id="user_id" value="${user_id}"/>
	    		       <div id="allOptionsCR" class="containDiv" style="position:absolute;border:0px solid black;padding:0px"></div>
					</td>
					<td align="center">责任人部门：</td>
					<td>		
						<input type="text"  id="dept_name" name="dept_name" readonly="readonly" style="cursor:hand;" onclick="openDeptSelect('dept_name','dept_id','radio')" value="${duty_dept_name}"/>
		      			<input type="hidden" name="dept_id" id="dept_id" value="${dept_id}"/>
					</td>
					<td align="center">是否打印条码：</td>
					<td>
						<select name="is_print" id="is_print">
							<option value="">--所有--</option>
							<option value="N"<c:if test="${is_print eq 'N'}">selected</c:if>>未打印</option>
							<option value="Y"<c:if test="${is_print eq 'Y'}">selected</c:if>>已打印</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="center">产生时间：</td>
					<td>
						<input type="text" id="startTime" name="startTime" readonly="readonly" style="cursor:hand;" value="${startTime}"/>
        				<img src="${ctx}/_image/time2.jpg" id="startTime_trigger">
					</td>
					<td align="center">至：</td>
					<td>
						<input type="text" id="endTime" name="endTime" readonly="readonly" style="cursor:hand;" value="${endTime}"/>
        				<img src="${ctx}/_image/time2.jpg" id="endTime_trigger">
					</td>
					<td align="center" colspan="2"> 
						<input name="button" type="submit" class="button_2003" value="查询" onclick="return checkTime();">&nbsp;
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
	   					<display:table requestURI="${ctx}/ledger/viewmergeentityledger.action" id="item" class="displaytable" name="paperList"
	   						pagesize="${pageSize}" sort="page" partialList="true" size="${totalSize}" excludedParams="*" form="LedgerQueryCondForm">
							<display:column title="序号">
								<c:out value="${item_rowNum}"/>
							</display:column>
							<display:column property="user_name" title="责任人"/>
							<display:column property="dept_name" title="责任部门"/>
							<display:column property="paper_barcode" title="文件条码" maxLength="25"/>
							<display:column property="file_title" title="文件名" maxLength="30"/>
							<display:column property="seclv_name" title="文件密级"/>
							<display:column property="page_count" title="页数"/>
							<display:column property="print_time" sortable="true" title="产生时间"/>
							<display:column property="paper_state_name" title="状态"/>
							<display:column title="操作" style="width:150px">
								<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/ledger/viewcycledetail.action?type=PAPER&barcode=${item.paper_barcode}&ledger_type=personal');"/>								
							<c:if test="${item.mergecode_print == 0}">
								<input type="button" class="button_2003" value="打条码" onclick="reprintBarcode('${item.paper_barcode}');"/>
							</c:if>
							<c:if test="${item.mergecode_print != 0}">
								<input type="button" class="button_2003" value="补打条码" onclick="reprintBarcode('${item.paper_barcode}');"/>
							</c:if>							
							</display:column>
						</display:table>
					</td>
				</tr>
			</table>
         </td>
	</tr>
</form>
</table>
</body>
</html>
