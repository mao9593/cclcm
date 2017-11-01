<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
    <script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>   
    <script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script>
//人员匹配
	function selectRecvUser(word){
		var url = "${ctx}/basic/getfuzzyuser.action?source=CR";
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
	function add_TrueCR(){
		var user_id=$("#allOptionCR").val();
		var user_name=$("#allOptionCR option[value='"+user_id+"']").text();
		if(user_id != ""){
			$("#duty_user_id").val(user_id);
			$("#duty_user_name").val(user_name);
			document.getElementById("allOptions").innerHTML="";
		}
	}
	function exportComputer(formId,url,url1){
		document.getElementById(formId).action = url;
		document.getElementById(formId).submit();
		document.getElementById(formId).action = url1;
	}

	function clearFindForm(){
		$("#QueryCondForm :text").val("");
		$("#QueryCondForm select").val("");	
	//	$("#QueryCondForm :hidden").val("");		
	}		

	function deleteEntityBook(barcode){
	   	var url="${ctx}/computer/deleteevent.action?device_barcode="+escape(barcode);
	    if(confirm("确定删除该笔记本台账？")){
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
	
	function chkSubmit(){
		var submitable = false;
		var values = "";
		var types = "";
		values = $("input:radio[name='barcode']:checked").val();
		if(values){
			$("#book_barcode").val(values);
			submitable = true;
		}else{
			alert("请选择需要操作的笔记本");
			return false;
		}
		
		types = $("input:radio[name='opre']:checked").val();
		if(submitable){
			if(types){
				if(types == "1"){
					go("${ctx}/computer/addchangebookevent.action?book_barcode="+$("#book_barcode").val());
				}else if(types == "2"){
					go("${ctx}/computer/addrepairbookevent.action?book_barcode="+$("#book_barcode").val());
				}else if(types == "3"){
					go("${ctx}/computer/adddestorybookevent.action?book_barcode="+$("#book_barcode").val());
				}else if(types == "4"){
					go("${ctx}/computer/addreinstallbookevent.action?book_barcode="+$("#book_barcode").val());
				}
			}else{
				alert("请勾选操作方式");
				return false;
			}
		}else{
			return false;
		}
	}		
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<form id="QueryCondForm" name="QueryCondForm" method="POST" action="${ctx}/computer/managebook.action" >
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
<input type="hidden" name="dept_ids" value="${dept_ids}" id="dept_ids"/>
<input type="hidden" id="type" name="type" value="${type}"/> 
<input type="hidden" name="dept_ids" value="${dept_ids}" id="dept_ids"/>
	<tr>
		<td class="title_box">笔记本列表</td>
	</tr>	
	<tr>
		<td align="right">
	 		<table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
					<tr>
						<td width="10%" align="center" width="10%">保密编号</td>
				 		<td width="15%">
				 			<input type="text" id="conf_code" name="conf_code" value="${conf_code}"/>
				 		</td>
				 		<td width="10%" align = "center">设备条码</td>
				 		<td width="15%">
				 		   	<input type="text" id="book_barcode" name="book_barcode" value="${book_barcode}"/>
				 		</td>				 		
				 		<td align="center" width="10%">密级 </td>
				 		<td width="15%"> 
				 			<c:set var="seclv1" value="${seclv_code}" scope="request"/>
				 			<select name="seclv_code" id="seclv_code">
								<option value="">--所有--</option>
								<s:iterator value="#request.seclvList" var="seclv">
									<option value="${seclv.seclv_code}" <s:if test="#request.seclv1==#seclv.seclv_code">selected="selected"</s:if>>${seclv.seclv_name}</option>
								</s:iterator>
							</select>
				 		</td>
				 		<td width="10%" align="center">笔记本状态</td>
				 		<td width="15%">
				 			<select name="computer_status" id="computer_status">
								<option value="">--所有--</option>
								<option value="1" <c:if test="${computer_status == '1'}">selected</c:if>>在用</option>
								<option value="6" <c:if test="${computer_status == '6'}">selected</c:if>>已借出</option>
								<option value="2" <c:if test="${computer_status == '2'}">selected</c:if>>停用</option>
								<option value="3" <c:if test="${computer_status == '3'}">selected</c:if>>维修</option>
								<option value="4" <c:if test="${computer_status == '4'}">selected</c:if>>报废</option>
								<option value="5" <c:if test="${computer_status == '5'}">selected</c:if>>销毁</option>			
							</select>
				 		</td> 
				 	<tr>
				 	</tr>		
				 		<c:if test="${type != 'SELF'}">		 				 		
							<td align="center" width="10%">使用部门</td>
				 			<td width="20%">
								<input type="text" id="duty_dept_name" name="duty_dept_name" value="${duty_dept_name}" readonly="readonly" style="cursor:hand" onclick="openDeptSelect('duty_dept_name','duty_dept_id','checkbox')"/>
	   							<input type="hidden" id="duty_dept_id" name="duty_dept_id" value="${duty_dept_id}"/><br>
			    			</td>
				    		<td align="center">保管人</td>
					 		<td>
					        	<input type="text" id="duty_user_name" name="duty_user_name" value="${duty_user_name}" onkeyup="selectRecvUser(this.value);"/>
	    						<input type="hidden" id="duty_user_id" name="duty_user_id" value="${duty_user_id}"/><br>
	    						<div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
				    		</td>
				    		<td align="center" colspan="4" width="10%">
					 			<input name="button" type="submit" class="button_2003" value="查询" onclick="return;">&nbsp;&nbsp;
								<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">&nbsp;&nbsp;
								<input type="button" class="button_2003" value="导出EXCEL" onclick="exportComputer('QueryCondForm','${ctx}/computer/exportbook.action','${ctx}/computer/managecomputer.action');"/></td></tr>
					 		</td>
			    		</c:if>
			    		<c:if test="${type == 'SELF'}">
			    			<td align="center" colspan="8" width="10%">
					 			<input name="button" type="submit" class="button_2003" value="查询" onclick="return;">&nbsp;&nbsp;
								<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">&nbsp;&nbsp;
								<input type="button" class="button_2003" value="导出EXCEL" onclick="exportComputer('QueryCondForm','${ctx}/computer/exportbook.action','${ctx}/computer/managecomputer.action');"/></td></tr>
					 		</td>	
			    		</c:if>
					</tr>
			</table>	
	  </td>
	</tr>
	<tr><td>
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
					<display:table requestURI="${ctx}/computer/managebook.action" id="item" class="displaytable" name="computerList" 
					pagesize="15" sort="list" excludedParams="*" form="QueryCondForm">
						<c:if test="${type != 'all' }">
							<display:column title="选择">							
			   					<c:choose>
									<c:when test="${item.book_status == 1}">
										<input type="radio" id="barcode" name="barcode" value="${item.book_barcode}"/>
									</c:when>
									<c:otherwise>
										${item.book_status_name}
									</c:otherwise>
								</c:choose>
							</display:column>
						</c:if>
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column property="conf_code" title="保密编码" />
						<display:column property="oldconf_code" title="原保密编码"  />
						<display:column property="duty_entp" title="单位" />
						<display:column property="duty_dept_name" title="使用部门" maxLength="15"/>
						<display:column property="duty_user_name" title="保管人" maxLength="15"/>
						<display:column property="seclv_name" title="密级"/>
						<display:column property="internet_type" title="联网情况"/>
						<display:column property="book_model" title="名称型号"/>
						<display:column property="storage_location" title="存放地点"/>
						<display:column property="storage_secinfo" title="存储涉密情况"/>
						<%-- <display:column property="book_barcode" title="设备条码"/> --%>						
						<%-- <display:column property="book_status_name" title="设备状态"/> --%>
						<display:column title="操作" style="width:160px" >
							<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/computer/viewbookdetail.action?device_barcode=${item.book_barcode}');"/>&nbsp;
							<input type="button" class="button_2003" value="修改" onclick="go('${ctx}/computer/updatebook.action?device_barcode=${item.book_barcode}');"/>&nbsp;
							<input type="button" class="button_2003" value="删除" onclick="deleteEntityBook('${item.book_barcode}')";/>&nbsp;
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
			<label style="width: 100">
				<input type="radio" id="opre" name="opre" value="1"/>变更
			</label>
			<label style="width: 80">
				<input type="radio" id="opre" name="opre" value="2"/>维修
			</label>
			<label style="width: 80">
				<input type="radio" id="opre" name="opre" value="3"/>报废
			</label>
			<label style="width: 80">
				<input type="radio" id="opre" name="opre" value="4"/>重装系统
			</label>
			<input type="button" value="确认" class="button_2003" style="margin-left: 30px" onclick="return chkSubmit();"/>
		</td>
	</tr>
</table>				
</form>
</body>
</html>
