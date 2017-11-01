<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
	<script language="JavaScript" src="${ctx}/_script/function.js"></script>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>   
    <script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script>
$(document).ready(function(){
	onHover();
	if("${type}" != "SELF"){
		document.getElementById("allOptions").innerHTML="";
	}
	if("${device_type}" !=""){
	setSelectKind("${device_type}");
	}
});
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
		user_name=user_name.substring(0,user_name.indexOf("/"));
		if(user_id != ""){
	    	$("#duty_user_id").val(user_id);
			$("#duty_user_name").val(user_name);
			document.getElementById("allOptions").innerHTML="";
		}
	}
	function clearFindForm(){
		$("#QueryCondForm :text").val("");
		$("#QueryCondForm select").val("");	
	}	
	
	function exportComputer(formId,url,url1){
			document.getElementById(formId).action = url;
			document.getElementById(formId).submit();
			document.getElementById(formId).action = url1;
	}
	function setSelectKind(type){
		var url = "${ctx}/computer/getinfotype.action";
		if(type != ""){
			callServer4(url,"info="+type);
		}else{
			document.getElementById("varinfo").innerHTML="";
		}
	}
	function updateResult2(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
				if(xmlHttp.responseText.toString().length > 154){
					document.getElementById("varinfo").innerHTML=xmlHttp.responseText;
				}else{
					document.getElementById("varinfo").innerHTML="";
				}
			}else{
				document.getElementById("varinfo").innerHTML="";
			}
	}
	
	function chkDelete(code, type){
		if(confirm("确定要删除该信息设备？")){
			if(type == "NETWORK"){
				go("${ctx}/computer/delinfodeviceevent.action?type=NETWORK&device_barcode="+escape(code));
			}else{
				go("${ctx}/computer/delinfodeviceevent.action?device_barcode="+escape(code));
			}
		}
	}		
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<form id="QueryCondForm" name="QueryCondForm" method="POST" action="${ctx}/computer/manageinfodevice.action" >
	<input type="hidden" name="type" id="type" value="${type}"/>
	<input type="hidden" id="userid" name="userid" value="${curUser.user_iidd}"/>
	<input type="hidden" name="dept_ids" value="${dept_ids}" id="dept_ids"/>
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
	   <c:choose>
		   	<c:when test="${type != 'NETWORK'}">
				<td class="title_box">信息设备列表</td>
			</c:when>
			<c:otherwise>
				<td class="title_box">网络信息设备列表</td>
			</c:otherwise>
       </c:choose>
	</tr>	
	<tr>
		<td align="right">
	 		<table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<td width="8%" align="center">条码号</td>
			 		<td width="12%">
			 			<input type="text" id="device_barcode" name="device_barcode" value="${device_barcode}"/>
			 		</td>
					<td width="10%" align="center">设备密级 </td>
			 		<td width="10%">
						<c:set var="seclv1" value="${device_seclv}" scope="request"/>
       					<select name="device_seclv">
       						<option value="">--全部--</option>
   							<s:iterator value="#request.seclvList" var="seclv">
								<option value="${seclv.seclv_code}" <s:if test="#request.seclv1==#seclv.seclv_code">selected="selected"</s:if>>${seclv.seclv_name}</option>
							</s:iterator>
   						</select>
			 		</td>
			 		<td width="10%" align = "center">资产编码</td>
			 		<td width="10%">
			 		    <input type="text" id="device_series" name="device_series" value="${device_series}" />
			 		</td>
			 		<td width="10%" align = "center">保密编码</td>
			 		<td width="10%">
			 		    <input type="text" id="conf_code" name="conf_code" value="${conf_code}" />
			 		</td>
				</tr>
			 	<tr>
			 		<td width="10%" align="center">设备状态</td>
			 		<td width="10%">
			 			<select name="device_statues" value="${device_statues}">
							<option value="">--全部--</option>
							<option value="1"<c:if test="${device_statues == 1}">selected</c:if>>在用</option>
							<option value="2"<c:if test="${device_statues == 2}">selected</c:if>>停用</option>
							<option value="3"<c:if test="${device_statues == 3}">selected</c:if>>维修</option>
							<option value="4"<c:if test="${device_statues == 4}">selected</c:if>>报废</option>
							<option value="5"<c:if test="${device_statues == 5}">selected</c:if>>已回收</option>
							<option value="6"<c:if test="${device_statues == 6}">selected</c:if>>已销毁</option>
							<option value="7"<c:if test="${device_statues == 7}">selected</c:if>>申请变更</option>
							<option value="8"<c:if test="${device_statues == 8}">selected</c:if>>申请报废</option>			
						</select>
			 		</td>
			 		<td align="center">设备类型</td>
			 		<td >
			 			<select name="device_type" id="device_type" value="${device_type}" onchange="setSelectKind(this.value)">
							<c:choose>
								<c:when test="${type eq 'NETWORK'}">
									<option value="2"<c:if test="${device_type == 2}">selected</c:if>>网络设备</option>		
								</c:when>
								<c:otherwise>
								    <option value="">--全部--</option>
									<option value="3"<c:if test="${device_type == 3}">selected</c:if>>外部设备</option>
									<option value="4"<c:if test="${device_type == 4}">selected</c:if>>办公自动化设备</option>
									<option value="5"<c:if test="${device_type == 5}">selected</c:if>>安全产品</option>
									<option value="6"<c:if test="${device_type == 6}">selected</c:if>>介质</option>		
								</c:otherwise>
							</c:choose>
						</select>
			 		</td>
				<c:choose>
					<c:when test="${type != 'SELF'}">
						<td align="center">子类型</td>
				 		<td>
					  		<div id="varinfo">
								<select>
									<option value="">--请先选择设备类型--</option>
								</select>
							</div>
						</td> 
						<td align="center">责任部门</td>
				 		<td>
							<input type="text" id="duty_dept_name" name="duty_dept_name" value="${duty_dept_name}" readonly="readonly" style="cursor:hand" onclick="openDeptSelect('duty_dept_name','duty_dept_id','checkbox')"/>
    						<input type="hidden" id="duty_dept_id" name="duty_dept_id" value="${duty_dept_id}"/><br>
			    		</td> 
					</tr>
				 	<tr>

			    		<td align="center">责任人</td>
  					  	<td>
   					 	    <input type="text" id="duty_user_name" name="duty_user_name" value="${duty_user_name}" onkeyup="selectRecvUser(this.value);"/>
 					   		<input type="hidden" id="duty_user_id" name="duty_user_id" value="${duty_user_id}"/><br>
 					   		<div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
 					   		</div>
					 	</td>	
					 	<td align="center" colspan="6">
				 			<input name="button" type="submit" class="button_2003" value="查询">&nbsp;&nbsp;
							<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">&nbsp;&nbsp;
							<input type="button" class="button_2003" value="导出EXCEL" onclick="exportComputer('QueryCondForm','${ctx}/computer/exportinfodevice.action','${ctx}/computer/manageinfodevice.action');"/>
				 		</td>	
					</c:when>
					<c:otherwise>
						<td align="center" colspan="6">
				 			<input name="button" type="submit" class="button_2003" value="查询">&nbsp;&nbsp;&nbsp;
							<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">&nbsp;&nbsp;
							<input type="button" class="button_2003" value="导出EXCEL" onclick="exportComputer('QueryCondForm','${ctx}/computer/exportinfodevice.action','${ctx}/computer/manageinfodevice.action');"/>
						</td>
					</c:otherwise>
				</c:choose>
				</tr>
			</table>	
	  </td>
	</tr>	
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
					<display:table requestURI="${ctx}/computer/manageinfodevice.action" id="item" class="displaytable" name="deviceList" 
					pagesize="15" sort="list" excludedParams="*" form="QueryCondForm">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<%-- <display:column property="device_barcode" title="设备条码" style="width:150px"/> --%>
						<display:column property="device_type_name" title="设备类型" />
						<display:column property="info_type" title="子类型"  />
						<display:column property="seclv_name" title="设备密级"/>
						<display:column property="device_series" title="资产编码"/>
						<display:column property="conf_code" title="保密编码"  />		
						<display:column property="duty_user_name" title="责任人" maxLength="10"/>
						<display:column property="duty_dept_name" title="所属部门" maxLength="10"/>
						<display:column property="device_statues_name" title="设备状态" />
						<display:column title="操作" maxLength="250">
							<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/computer/viewinfodevicedetail.action?device_barcode=${item.device_barcode}');"/>&nbsp;
						<c:if test="${type != 'ALL'}">
							<c:if test="${type != 'SELF'}">
									<input type="button" class="button_2003" value="修改" onclick="go('${ctx}/computer/updateinfodevice.action?device_barcode=${item.device_barcode}');"/>&nbsp;
								</c:if>
							<c:choose>
							<c:when test="${item.device_statues == 1}">
									<c:if test="${type != 'NETWORK'}">
										<input type="button" class="button_2003" value="变更" onclick="go('${ctx}/computer/changeinfodevice.action?device_barcode=${item.device_barcode}');"/>&nbsp;
									</c:if>	
										<input type="button" class="button_2003" value="报废" onclick="go('${ctx}/computer/destroyinfodevice.action?device_barcode=${item.device_barcode}');"/>									
							</c:when>
							<c:otherwise>
									<c:if test="${type != 'NETWORK'}">
										<input type="button" class="button_2003" value="变更" disabled="disabled"/>
									</c:if>		
										<input type="button" class="button_2003" value="报废" disabled="disabled"/>
							</c:otherwise>
							</c:choose>
							<c:if test="${type == 'DEPT' }">
								<input type="button" class="button_2003" value="删除" onclick="chkDelete('${item.device_barcode}','');"/>
							</c:if>
							<c:if test="${type == 'NETWORK'}">
								<input type="button" class="button_2003" value="删除" onclick="chkDelete('${item.device_barcode}', 'NETWORK');"/>
							</c:if>
						</c:if>
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