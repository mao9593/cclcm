<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>光盘录入</title>
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
		optionSelect();
	});
	function optionSelect(){
		$("#seclv_code").val("${seclv_code}");
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
		var user_iidd=$("#allOption").val();
		var user_name=$("#allOption option[value='"+user_iidd+"']").text();
		user_name=user_name.substring(0,user_name.indexOf("/"));
		if(user_iidd != ""){
			$("#user_iidd").val(user_iidd);
			$("#user_name").val(user_name);
			document.getElementById("allOptions").innerHTML="";
		}
	}
	function clearFindForm(){
		$("#QueryCondForm :text").val("");
		$("#QueryCondForm select").val("");
	}
	
	function chkEnter(event_code, tag,is_barcode,page_num,enter_num,file_name,seclv_name){
	    if(confirm("请核对: 文件名: "+ file_name +",  份数: "+ enter_num +",  密级: "+seclv_name)){
			$("#event_code").val(event_code);
			$("#is_barcode").val(is_barcode);
			if(is_barcode=='Y')
			{
				var url = "${ctx}/enter/configbarcode.action";
				var rValue = window.showModalDialog(url,'', 'dialogHeight:240px;dialogWidth:400px;center:yes;status:no;scroll:no;help:no;unadorned:no;resizable:no');
				if(rValue != null && rValue!= undefined && rValue.onOK == "Y"){
					$("#n_dum").val(rValue.n_dum);
					callServerPostForm("${ctx}/enter/updateenterstatus.action",document.forms[0]);
					$(tag).attr("disabled",true);
				}	
			}else{
				//$("#is_encrypt").val(rValue.is_encrypt);
				callServerPostForm("${ctx}/enter/updateenterstatus.action",document.forms[0]);
				$(tag).attr("disabled",true);
			}
		}
			
	}
	function getAjaxResult(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {				
			if(xmlHttp.responseText != null && "" != xmlHttp.responseText){
				var cd_barcodes = xmlHttp.responseText.split(";");
				if(cd_barcodes.length-1>0){
					var barcodetype = cd_barcodes[0].split("#")[0];
					var fileno = cd_barcodes[0].split("#")[1];
					var barcode = cd_barcodes[0].split("#")[2];
					var others = cd_barcodes[0].split("#")[3];	//条码其他信息，如：部门、用户名、密级等	
				var obj=new ActiveXObject("SprintCom.DataProcess.1");
				var is_barcode = $("#is_barcode").val();
 				if(is_barcode=='Y'){
 					obj.PrintBarcodeInfo(barcodetype, fileno, barcode, $("#n_dum").val(), 1,others); 	
 				}
				alert("光盘录入完成");	
				$("#QueryCondForm").submit();
			}else {
				alert("不打条码，录入已完成");
				$("#QueryCondForm").submit();	
			}
			}else {
				alert("文件信息不全不能打印条码");
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
				//alert(xmlHttp.responseText);
				var url = "${ctx}/enter/configbarcode.action";
				var rValue = window.showModalDialog(url,'', 'dialogHeight:240px;dialogWidth:400px;center:yes;status:no;scroll:no;help:no;unadorned:no;resizable:no');
				if(rValue != null && rValue!= undefined && rValue.onOK == "Y"){
					var obj=new ActiveXObject("SprintCom.DataProcess.1");
					obj.PrintBarcode(barcodetype, fileno, barcode, rValue.n_dum, 0);  
					alert("补打完成");
				}	
			}else {
				alert("该光盘不需要打条码");	
			}
		}
	}
	function chkCancel(job_code, event_code){
		if(confirm("确定要退回该流程申请？")){
			var url = "${ctx}/basic/canceljob.action?type=ajax&jobType_code=${jobType_code}&job_code="+escape(job_code)+"&actionContext="+escape('${actionContext}')+"&event_code="+escape(event_code);
			xmlHttp.open("GET", url, true);
			xmlHttp.onreadystatechange = updatePage;
			xmlHttp.send(null);
		}
	}
	function updatePage() {
		if (xmlHttp.readyState == 4) {
			  var response = xmlHttp.responseText;
			  QueryCondForm.submit();
		}
	}  
//-->
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<form method="POST"  id="hiddenform">
	<input type="hidden" name="event_code" id="event_code"/>
	<input type="hidden" name="file_type" id="file_type" value="2"/>
	<input type="hidden" id="n_dum"/>
	<input type="hidden" id="is_barcode"/>
	<input type="hidden" id="is_encrypt"/>
</form>
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">录入申请列表</td>
	</tr>
	<tr>
		<td align="right">
		<c:choose>
			<c:when test="${selftype == 'N'}">
			<form id="QueryCondForm" method="GET" action="${ctx}/enter/manageentercdlist.action?import_status=${import_status}">
				<table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
					<tr>
					    <td align="center">用户名：</td>
					    <td>
					    <input type="text" id="user_name" name="user_name" value="${user_name}" onkeyup="selectRecvUser(this.value);"/><br>
    		            <div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px"></div>
					    </td>
					     <td align="center">制作部门：</td>
					   <td>
						<input type="text" id="dept_name" name="dept_name" value="${dept_name}"/>&nbsp;
					   </td>
						<td width="8%" align="center">密级 ：</td>
				 		<td width="15%">
				 			<select name="seclv_code" id="seclv_code">
							        <option value="">--所有--</option>
								    <s:iterator value="#request.seclvList" var="seclv">
							            <option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
								    </s:iterator>
						    </select>
				 			<!--<c:set var="seclv1" value="${seclv_code}" scope="request"/>
        					<select name="seclv_code">
        						<option value="">--全部--</option>
    						<s:iterator value="#request.seclvList" var="seclv">
								<option value="${seclv.seclv_code}" <s:if test="#request.seclv1==#seclv.seclv_code">selected="selected"</s:if>>${seclv.seclv_name}</option>
							</s:iterator>
    					    </select> -->
				 		</td>
				 		</tr>
				 		<tr>
				 		<td width="8%" align="center">申请时间：</td>
				 		<td width="22%">
				        	<input type="text" name="startTime" readonly="readonly" style="cursor:hand;" value="${startTime_str}"/>
        					<img src="${ctx}/_image/time2.jpg" id="startTime_trigger">&nbsp;
			    		</td> 
			    		<td width="8%" align="center">至：</td>
				 		<td width="22%">
				        	<input type="text" name="endTime" readonly="readonly" style="cursor:hand;" value="${endTime_str}"/>
        					<img src="${ctx}/_image/time2.jpg" id="endTime_trigger">&nbsp;
			    		</td> 
				        <td align="center" colspan="2">
							<input name="button" type="submit" class="button_2003" value="查询" onclick="return checkTime();">&nbsp;&nbsp;
							<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">
						</td>
					</tr>
				</table>		
 			</form>
 			</c:when>
 			<c:otherwise>	
	 			<form id="QueryCondForm" method="GET" action="${ctx}/enter/manageentercdlist.action?import_status=${import_status}&selftype=Y">
						<table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
							<tr>
							    <td width="8%" align="center">文件列表：</td>
							    <td width="15%">
							    	<input type="text" id="zipfile" name="zipfile" value="${zipfile}"/><br>
							    </td>
								<td width="8%" align="center">密级 ：</td>
						 		<td width="15%">
						 			<c:set var="seclv1" value="${seclv_code}" scope="request"/>
		        					<select name="seclv_code">
		        						<option value="">--全部--</option>
		    							<s:iterator value="#request.seclvList" var="seclv">
											<option value="${seclv.seclv_code}" <s:if test="#request.seclv1==#seclv.seclv_code">selected="selected"</s:if>>${seclv.seclv_name}</option>
										</s:iterator>
		    						</select> 
						 		</td>
						 		<td width="8%" align="center">申请时间：</td>
						 		<td width="15%">
						        	<input type="text" name="startTime" readonly="readonly" style="cursor:hand;" value="${startTime_str}"/>
		        					<img src="${ctx}/_image/time2.jpg" id="startTime_trigger">&nbsp;
					    		</td> 
					    		<td width="5%" align="center">至：</td>
						 		<td width="15%">
						        	<input type="text" name="endTime" readonly="readonly" style="cursor:hand;" value="${endTime_str}"/>
		        					<img src="${ctx}/_image/time2.jpg" id="endTime_trigger">&nbsp;
					    		</td>  
					    		<td align="center" rowspan="2" colspan="2">
									<input name="button" type="submit" class="button_2003" value="查询" onclick="return checkTime();">&nbsp;&nbsp;
									<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">
								</td>    
							</tr>
					</table>		
	 			</form>
 			</c:otherwise>
 		</c:choose>	
		</td>
	</tr>
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
					<display:table requestURI="${ctx}/enter/manageentercdlist.action" uid="item" class="displaytable" name="eventList" pagesize="15" sort="list">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column title="申请人"  property="user_name"/>
						<display:column title="部门"  property="dept_name"/>						
						<display:column title="文件名称" property="zipfile" maxLength="15"/>	
						<display:column title="密级" property="seclv_name"/>
						<display:column title="用途" property="usage_name"/>	
						<display:column title="载体归属" property="scope_name"/>	
						<display:column title="申请时间" property="apply_time_str"   sortable="true"/>											
						<display:column title="操作" style="width:350px">
							<div>
								<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/enter/viewentereventdetail.action?event_code=${item.event_code}');"/>&nbsp;
								<c:choose>
									<c:when test="${item.import_status eq '0'}">
										<input type="button" class="button_2003" value="打条码录入" onclick="chkEnter('${item.event_code}',this,'Y','${item.page_num}','${item.enter_num}','${item.zipfile}','${item.seclv_name}')"/>
										<input type="button" class="button_2003" value="不打条码录入" onclick="chkEnter('${item.event_code}',this,'N','${item.page_num}','${item.enter_num}','${item.zipfile}','${item.seclv_name}')"/>
										<input type="button" class="button_2003" value="退回" onclick="chkCancel('${item.job_code}','${item.event_code}')"/>
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
