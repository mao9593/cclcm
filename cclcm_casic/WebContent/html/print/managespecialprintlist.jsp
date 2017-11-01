<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>特殊文件管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
<script>
<!--
	$(document).ready(function(){
		onHover();
		preCalendar();
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
		var user_id=$("#allOption").val();
		var user_name=$("#allOption option[value='"+user_id+"']").text();
		if(user_id != ""){
			$("#user_id").val(user_id);
			document.getElementById("allOptions").innerHTML="";
		}
	}
	function clearFindForm(){
		$("#QueryCondForm :text").val("");
		$("#QueryCondForm select").val("");
	}
	function chkEnter(event_code, tag ,is_barcode){
		$("#event_code").val(event_code);
		$("#is_barcode").val(is_barcode);
		if(is_barcode=='Y'){
			var url = "${ctx}/enter/configbarcode.action";
			var rValue = window.showModalDialog(url,'', 'dialogHeight:240px;dialogWidth:400px;center:yes;status:no;scroll:no;help:no;unadorned:no;resizable:no');
			if(rValue != null && rValue!= undefined && rValue.onOK == "Y"){
				$("#n_dum").val(rValue.n_dum);	
				callServerPostForm("${ctx}/print/updatespecialstatus.action",document.forms[0]);
				$(tag).attr("disabled",true);
			}	
		}else{
			callServerPostForm("${ctx}/print/updatespecialstatus.action",document.forms[0]);
			$(tag).attr("disabled",true);
		}
	}
	
	function getAjaxResult(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {				
			if(xmlHttp.responseText != null && "" != xmlHttp.responseText){
				var paper_barcodes = xmlHttp.responseText.split(":");
				for (var i=0;i<paper_barcodes.length-1;i++){
					var barcodetype = paper_barcodes[i].split("#")[0];			
					var fileno = paper_barcodes[i].split("#")[1];	
					var barcode = paper_barcodes[i].split("#")[2];
					var others = paper_barcodes[i].split("#")[3];	//条码其他信息，如：部门、用户名、密级等	
					var is_barcode = $("#is_barcode").val();
					if(is_barcode=='Y'){
 					var obj=new ActiveXObject("SprintCom.DataProcess.1");
 						obj.PrintBarcodeInfo(barcodetype, fileno, barcode, $("#n_dum").val(), 1,others); 	
 					}
				}				
				alert("文件录入完成");	
				$("#QueryCondForm").submit();
			}else {
				alert("不打条码，录入已完成");	
				$("#QueryCondForm").submit();
			}
		}
	}
	function addEnterBarcode(event_code){
		$("#event_code").val(event_code);
		callServerPostForm1("${ctx}/enter/reprintbarcode.action",document.forms[0]);
	}
	function getAjaxResult1(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {					
			if(xmlHttp.responseText != null && "" != xmlHttp.responseText){
				var barcodetype = xmlHttp.responseText.split("#")[0];
				var fileno = xmlHttp.responseText.split("#")[1];
				var barcode = xmlHttp.responseText.split("#")[2];	
				var others = paper_barcodes[i].split("#")[3];	//条码其他信息，如：部门、用户名、密级等	
				var url = "${ctx}/enter/configbarcode.action";
				var rValue = window.showModalDialog(url,'', 'dialogHeight:240px;dialogWidth:400px;center:yes;status:no;scroll:no;help:no;unadorned:no;resizable:no');
				if(rValue != null && rValue!= undefined && rValue.onOK == "Y"){
					var obj=new ActiveXObject("SprintCom.DataProcess.1");
					obj.PrintBarcodeInfo(barcodetype, fileno, barcode, rValue.n_dum, 1,others);  
					alert("补打完成");
				}	
			}else {
				alert("该文件不需要打条码");	
			}
		}
	}
	function chkChange(event_code){
		$("#event_code").val(event_code);
		var url = "${ctx}/print/changespecialprinteventdetail.action?event_code="+event_code;
		var rValue=window.showModalDialog(url,'', 'dialogHeight:300px;dialogWidth:400px;center:yes;status:no;scroll:no;help:no;unadorned:no;resizable:no');
		if(rValue != null && rValue!= undefined){	
			$("#page_num").val(rValue.page_num);
			$("#paper_num").val(rValue.paper_num);
			callServerPostForm2(url,document.forms[1]);
		}
	}
	function getAjaxResult2(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			alert("修改成功");
			$("#QueryCondForm").submit();
		}
	}   
//-->
</script>
</head>

<body oncontextmenu="self.event.returnValue=false">
<form method="POST" id="hiddenform">
	<input type="hidden" name="event_code" id="event_code"/>
	<input type="hidden" name="file_type" id="file_type" value="1"/>
	<input type="hidden" id="n_dum"/>
	<input type="hidden" id="is_barcode"/>
	<input type="hidden" id="is_encrypt"/>
</form>
<form method="POST" id="hiddenform1">
	<input type="hidden" name="page_num" id="page_num"/>
	<input type="hidden" name="paper_num" id="paper_num"/>
	<input type="hidden" name="is_change" id="is_change" value="y"/>
</form>
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">特殊打印申请列表</td>
	</tr>
	<tr>
		<td align="right">
			<form id="QueryCondForm" method="GET" action="${ctx}/print/managespecialprintlist.action?import_status=${import_status}">
				<table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
					<tr>
					   <td align="center">用户名：</td>
					   <td>
						   <input type="text" id="user_id" name="user_id" value="${user_id}" onkeyup="selectRecvUser(this.value);"/><br>
	    		           <div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px"></div>
					   </td>
					   <td align="center">制作部门：</td>
					   <td>
							<input type="text"  id="dept_name" name="dept_name" readonly="readonly" style="cursor:hand;" onclick="openDeptSelect('dept_name','dept_id','radio')" value="${dept_name}" />
		      				<input type="hidden" name="dept_id" id="dept_id" value="${dept_id}"/>
					   </td>
						<td width="8%" align="center">文件密级 ：</td>
				 		<td width="15%" >
				 			<select name="seclv_code" id="seclv_med">
								<option value="">--所有--</option>
								<s:iterator value="#request.seclvList" var="seclv">
									<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
								</s:iterator>
							</select>
				 		</td>
				 		</tr>
				 		<tr>
				 		<td align="center">申请时间：</td>
				 		<td>
				        	<input type="text" name="startTime" readonly="readonly" style="cursor:hand;" value="${startTime_str}"/>
        					<img src="${ctx}/_image/time2.jpg" id="startTime_trigger">&nbsp;
			    		</td> 
			    		<td align="center">至：</td>
				 		<td>
				        	<input type="text" name="endTime" readonly="readonly" style="cursor:hand;" value="${endTime_str}"/>
        					<img src="${ctx}/_image/time2.jpg" id="endTime_trigger">&nbsp;
			    		</td> 
				 		<td align="center" colspan="2">
							<input name="button" type="submit" class="button_2003" value="查询" onclick="return checkTime();">&nbsp;&nbsp;&nbsp;
							<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">
						</td>
					</tr>
				</table>
 			</form>
		</td>
	</tr>
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
					<display:table requestURI="${ctx}/print/managespecialprintlist.action" uid="item" class="displaytable" name="eventList" pagesize="15" sort="list">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column title="申请人"  property="user_name"/>
						<display:column title="制作人部门"  property="dept_name"/>							
						<display:column title="文件名称" property="paper_name" maxLength="15"/>
						<display:column title="文件密级" property="file_sname"/>		
						<display:column title="页数" property="page_num"/>	
						<display:column title="份数" property="paper_num"/>	
						<display:column title="用途" property="usage_name"/>
						<display:column title="申请时间" property="apply_time_str" sortable="true"/>										
						<display:column title="操作" style="width:350px">
							<div>
								<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/print/viewspecialprinteventdetail.action?event_code=${item.event_code}');"/>&nbsp;
								<input type="button" class="button_2003" value="修改" onclick="chkChange('${item.event_code}')"/>&nbsp;
								<c:choose>
									<c:when test="${item.paper_status eq '0'}">
										<input type="button" class="button_2003" value=" 打条码录入" onclick="chkEnter('${item.event_code}',this,'Y')"/>
										<input type="button" class="button_2003" value="不打条码录入" onclick="chkEnter('${item.event_code}',this,'N')"/>
									</c:when>
									<c:otherwise>
										<input type="button" class="button_2003" value="补打条码" onclick="addEnterBarcode('${item.event_code}')"/>
									</c:otherwise>
								</c:choose>
							</div>
						</display:column>
					</display:table>
					</td>
				</tr>
			</table>
         </td>
	</tr>
</table>
</body>
</html>
