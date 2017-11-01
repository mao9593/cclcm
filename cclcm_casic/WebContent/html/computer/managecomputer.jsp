<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>查看计算机列表</title>
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
	function DeleteEntity(computer_barcode){
		if(confirm("确定删除该计算机台账？")){
			go("${ctx}/computer/deleteevent.action?computer_barcode="+escape(computer_barcode));
		}
	}

	function chkSubmit(){
		var submitable = false;
		var values = "";
		var types = "";
		values = $("input:radio[name='barcode']:checked").val();
	
		if(values){
			$("#computer_barcode").val(values);
			//alert($("#computer_barcode").val());
			submitable = true;
		}else{
			alert("请选择需要操作的计算机");
			return false;
		}
		
		types = $("input:radio[name='opre']:checked").val();
		if(submitable){
			if(types){
				if(types == "1"){
					go("${ctx}/infosystem/addusbkeyevent.action?computer_barcode="+$("#computer_barcode").val());
				}else if(types == "2"){
					go("${ctx}/infosystem/addopenportevent.action?computer_barcode="+$("#computer_barcode").val());
				}else if(types == "3"){
					go("${ctx}/computer/addchangecomputerevent.action?type=Y&computer_barcode="+$("#computer_barcode").val());
				}else if(types == "4"){
					go("${ctx}/infosystem/addlocalprinterevent.action?computer_barcode="+$("#computer_barcode").val());
				}else if(types == "5"){
					go("${ctx}/computer/adddestroycomputerevent.action?computer_barcode="+$("#computer_barcode").val());
				}else if(types == "6"){
					go("${ctx}/computer/updatecomputer.action?computer_barcode="+$("#computer_barcode").val());
				}else if(types == "7"){
					go("${ctx}/infosystem/addcomputerrepairevent.action?computer_barcode="+$("#computer_barcode").val());
				}else if(types == "8"){
					go("${ctx}/infosystem/addreinstallsystemevent.action?computer_barcode="+$("#computer_barcode").val());
				}else if(types == "9"){
					go("${ctx}/infosystem/addquitinternetevent.action?computer_barcode="+$("#computer_barcode").val());
				}else if(types == "10"){
					DeleteEntity($("#computer_barcode").val());
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
<form id="QueryCondForm" name="QueryCondForm" method="POST" action="${ctx}/computer/managecomputer.action" >
<input type="hidden" name="type" value="${type}" id="type"/>
<input type="hidden" name="computer_barcode" id="computer_barcode"/> 
<input type="hidden" name="dept_ids" value="${dept_ids}" id="dept_ids"/>
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<c:if test="${type == 'SELF'}">
			<td class="title_box">个人计算机管理</td>
		</c:if>
		<c:if test="${type == 'DEPT'}">
			<td class="title_box">部门计算机管理</td>
		</c:if>
		<c:if test="${type == 'all'}">
			<td class="title_box">计算机总台账</td>
		</c:if>
	</tr>	
	<tr>
		<td align="right">
	 		<table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<td align="center" width="10%">保密编号</td>
			 		<td align="center">
			 			<input type="text" id="conf_code" name="conf_code" value="${conf_code}"/>
			 		</td>
			 		<td align = "center">资产编码</td>
			 		<td align="center">
			 		   	<input type="text" id="computer_code" name="computer_code" value="${computer_code}"/>
			 		</td>				 		
			 		<td align="center" width="10%">密级 </td>
			 		<td align="center"> 
			 			<select name="seclv_code" id="seclv_code">
							<option value="">--所有--</option>
							<s:iterator value="#request.seclvList" var="seclv">
								<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
							</s:iterator>
						</select>
			 		</td>
			 		<td align="center">使用状态</td>
			 		<td align="center">
			 			<select name="computer_status" id="computer_status">
							<option value="">--所有--</option>
							<option value="1" <c:if test="${computer_status == '1'}">selected</c:if>>在用</option>
							<option value="2" <c:if test="${computer_status == '2'}">selected</c:if>>停用</option>
							<option value="3" <c:if test="${computer_status == '3'}">selected</c:if>>维修</option>
							<option value="4" <c:if test="${computer_status == '4'}">selected</c:if>>报废</option>
							<option value="5" <c:if test="${computer_status == '5'}">selected</c:if>>销毁</option>
							<option value="6" <c:if test="${computer_status == '6'}">selected</c:if>>申请维修</option>
							<option value="7" <c:if test="${computer_status == '7'}">selected</c:if>>申请报废</option>			
						</select>
			 		</td>	
			 		<c:if test="${type == 'SELF' }">
			 		 <td align="center" colspan="2">
			 			<input name="button" type="submit" class="button_2003" value="查询" onclick="return;">&nbsp;
						<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">
			 		</td>		
			 		</c:if>						
				</tr>
				<c:if test="${type != 'SELF' }">
				<tr>				 				 		
					<td align="center" width="10%">责任部门</td>
					<td align="center">
						<input type="text" id="duty_dept_name" name="duty_dept_name" value="${duty_dept_name}" readonly="readonly" style="cursor:hand" onclick="openDeptSelect('duty_dept_name','duty_dept_id','checkbox')"/>
						<input type="hidden" id="duty_dept_id" name="duty_dept_id" value="${duty_dept_id}"/><br>
						</td>
						<td align="center">责任人</td>
						<td align="center">
					    	<input type="text" id="duty_user_name" name="duty_user_name" value="${duty_user_name}" onkeyup="selectRecvUser(this.value);"/>
							<input type="hidden" id="duty_user_id" name="duty_user_id" value="${duty_user_id}"/><br>
							<div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
						</td> 
					 </td>
					 <td align="center" colspan="4">
						<input name="button" type="submit" class="button_2003" value="查询" onclick="return;">&nbsp;&nbsp;
						<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">&nbsp;&nbsp;
						<input type="button" class="button_2003" value="导出EXCEL" onclick="exportComputer('QueryCondForm','${ctx}/computer/exportcomputer.action','${ctx}/computer/managecomputer.action');"/>
			 		</td>													 		
				</tr>
				</c:if>
			</table>	
	  </td>
	</tr>
	<c:if test="${type == 'SELF' }">
		<tr><td><input type="button" class="button_2003" value="导出EXCEL" onclick="exportComputer('QueryCondForm','${ctx}/computer/exportcomputer.action','${ctx}/computer/managecomputer.action');"/></td></tr>
	</c:if>
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			
	 			<tr>
	   				<td>
					<display:table requestURI="${ctx}/computer/managecomputer.action" id="item" class="displaytable" name="computerList" 
					pagesize="15" sort="list" excludedParams="*" form="QueryCondForm">
					<c:if test="${type != 'all' }">
						<display:column title="选择">							
		   					<c:choose>
								<c:when test="${item.computer_status == 1}">
									<input type="radio" id="barcode" name="barcode" value="${item.computer_barcode}"/>
								</c:when>
								<c:otherwise>
									${item.computer_status_name}
								</c:otherwise>
							</c:choose>
						</display:column>
					</c:if>
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<%-- <display:column property="computer_barcode" title="设备条码" /> --%>
						<display:column property="duty_entp_name" title="单位" />
						<display:column property="computer_name" title="计算机名称型号"/>
						<display:column property="med_type_name" title="设备类型" />
						<display:column property="internet_type_name" title="网络类型" />
						<display:column property="computer_code" title="资产编码" />
						<display:column property="conf_code" title="保密编号"  />
						<display:column property="duty_user_name" title="责任人" maxLength="15"/>
						<display:column property="duty_dept_name" title="责任部门" maxLength="15"/>
						<display:column property="seclv_name" title="密级"/>
						<display:column property="computer_status_name" title="设备状态"/>	
						<display:column title="操作" maxLength="150">					
							<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/computer/viewcomputerdetail.action?computer_barcode=${item.computer_barcode}');"/>&nbsp;
							<c:if test="${type == 'DEPT' }">
								<input type="button" class="button_2003" value="修改" onclick="go('${ctx}/computer/updatecomputer.action?computer_barcode=${item.computer_barcode}');"/>
								<input type="button" class="button_2003" value="删除" onclick="DeleteEntity('${item.computer_barcode}');"/>
							</c:if>							
<%-- 							<c:if test="${type == 'SELF' }">
								<c:choose>
									<c:when test="${item.computer_status gt 2 }">
										<input type="button" class="button_2003" value="USB-KEY申请" disabled="disabled"/>
										<input type="button" class="button_2003" value="开通端口" disabled="disabled"/>
										<input type="button" class="button_2003" value="本地打印机" disabled="disabled"/>
										<input type="button" class="button_2003" value="变更" disabled="disabled"/>
										<input type="button" class="button_2003" value="保密报废" disabled="disabled"/>
									</c:when>
									<c:otherwise>
										<input type="button" class="button_2003" value="USB-KEY申请" onclick="go('${ctx}/infosystem/addusbkeyevent.action?computer_barcode=${item.computer_barcode}');"/>&nbsp;
										<input type="button" class="button_2003" value="开通端口" onclick="go('${ctx}/infosystem/addopenportevent.action?computer_barcode=${item.computer_barcode}');"/>&nbsp;
										<input type="button" class="button_2003" value="变更" onclick="go('${ctx}/computer/addchangecomputerevent.action?computer_barcode=${item.computer_barcode}&type=Y');"/>&nbsp;
										<input type="button" class="button_2003" value="本地打印机" onclick="go('${ctx}/infosystem/addlocalprinterevent.action?computer_barcode=${item.computer_barcode}');"/>&nbsp;
										<input type="button" class="button_2003" value="保密报废" onclick="go('${ctx}/computer/adddestroycomputerevent.action?computer_barcode=${item.computer_barcode}');"/>&nbsp;
									</c:otherwise>
								</c:choose>
							</c:if>
							<c:if test="${type == 'DEPT'}">
								<c:choose>
									<c:when test="${item.computer_status gt 2 }">
										<input type="button" class="button_2003" value="修改" disabled="disabled"/>
										<input type="button" class="button_2003" value="维修" disabled="disabled"/>
										<input type="button" class="button_2003" value="重装系统" disabled="disabled"/>
										<input type="button" class="button_2003" value="退网" disabled="disabled"/>
									</c:when>
									<c:otherwise>
										<input type="button" class="button_2003" value="修改" onclick="go('${ctx}/computer/updatecomputer.action?computer_barcode=${item.computer_barcode}');"/>
										<input type="button" class="button_2003" value="维修" onclick="go('${ctx}/infosystem/addcomputerrepairevent.action?computer_barcode=${item.computer_barcode}');"/>
										<input type="button" class="button_2003" value="重装系统" onclick="go('${ctx}/infosystem/addreinstallsystemevent.action?computer_barcode=${item.computer_barcode}');"/>
										<input type="button" class="button_2003" value="退网" onclick="go('${ctx}/infosystem/addquitinternetevent.action?computer_barcode=${item.computer_barcode}');"/>
									</c:otherwise>
								</c:choose>
								<input type="button" class="button_2003" value="删除" onclick="DeleteEntity('${item.computer_barcode}');"/>
							</c:if> --%>
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
		<c:if test="${type != 'all'}">
			<label style="width: 100">
				<input type="radio" id="opre" name="opre" value="1"/>USB-KEY申请
			</label>
			<label style="width: 80">
				<input type="radio" id="opre" name="opre" value="2"/>开通端口
			</label>
			<label style="width: 80">
				<input type="radio" id="opre" name="opre" value="3"/>变更
			</label>
			<label style="width: 80">
				<input type="radio" name="opre" id="opre" value="4"/>本地打印机
			</label>
			<label style="width: 80">
				<input type="radio" name="opre" id="opre" value="5"/>保密报废
			</label>
			<!-- <label style="width: 80">
				<input type="radio" name="opre" id="opre" value="6"/>修改
			</label> -->
			<label style="width: 80">
				<input type="radio" name="opre" id="opre" value="7"/>维修
			</label>
			<label style="width: 80">
				<input type="radio" name="opre" id="opre" value="8"/>重装系统
			</label>
			<label style="width: 80">
				<input type="radio" name="opre" id="opre" value="9"/>退网
			</label>
			<input type="button" value="确认" class="button_2003" style="margin-left: 30px" onclick="return chkSubmit();"/>
		</c:if>
		</td>
	</tr>
</table>
</form>
</body>
</html>
