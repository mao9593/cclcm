<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
 	<script type="text/javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>	
 	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
 	<script language="JavaScript" src="${ctx}/_script/common.js"></script>
<script>
$(document).ready(function(){
	onHover();
	prepareBizApproval("${process.step_dept}","${process.step_role}","${process.step_dept_name}","${process.step_role_name}","${ctx}");
	init();
	addSelectAllCheckbox();
});
function init(){
	if($("#tr_approver option").size()>0){
		$("#tr_approver").show();
	}
}
  function add_TrueCR(){
		var user_id=$("#allOptionCR").val();
		var user_name=$("#allOptionCR option[value='"+user_id+"']").text();
		user_name=user_name.substring(0,user_name.indexOf("/"));
		if(user_id != ""){
	    	$("#change_user_iidd").val(user_id);
			$("#change_user_name").val(user_name);
			document.getElementById("allOptions").innerHTML="";
		}
	}
	function selectSeclv(seclv){
		if(seclv == ""){
			alert("请选择作业密级,以确认审批流程");
			$("#seclv_code").focus();
			return false;
		}else if($("#usage_code").val() != ""){
			selectNextApprover();
		}
	}
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
	function selectNextApprover(){
		del_all_True('next_approver_sel');
		var url = "${ctx}/basic/getnextapprover.action";
		callServerPostForm(url,document.forms[0]);
	}
	function getAjaxResult(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			if(xmlHttp.responseText.indexOf("<SELECT") != -1){
				$("#tr_approver").show();
				document.getElementById("allApprovers").innerHTML=xmlHttp.responseText;
				$("#submit_btn").attr("disabled",false);
			}else{
				$("#tr_approver").hide();
				alert(xmlHttp.responseText);
				$("#submit_btn").attr("disabled",true);
			}
			if($("#next_approver_all").size() > 0 && $("#next_approver_all").children().size() == 0){
				$("#tr_approver").hide();
			}
		}
	}

function chkSubmit()
{	
	if($("#seclv_code").val() == ""){
		alert("请选择作业密级,以确认审批流程");
		$("#seclv_code").focus();
		return false;
	}
	if($("#handleType").val() =="WASTE")
	{
	if($("#deper_life").val() == 0){
		alert("请输入折旧年限");
		$("#deper_life").focus();
		return false;
	}
	if($("#deper_life").val() == 0){
		alert("请输入使用年限");
		$("#deper_life").focus();
		return false;
	}
	if($("#reason").val() == 0){
		alert("请输入报废原因");
		$("#reason").focus();
		return false;
	}
/*	if($("#suggestion").val() == 0){
		alert("请输入鉴定意见");
		$("#suggestion").focus();
		return false;
	}
	if($("#suggestion_waste").val() == 0){
		alert("请输入报废处理意见");
		$("#suggestion_waste").focus();
		return false;
	}  */
	}else
	{
	  if($("#change_user_iidd").val() == 0){
		alert("请输入变更人");
		$("#change_user_name").focus();
		return false;
	}
	}
	if($("#next_approver_all option").size() > 0 && $("#next_approver_sel option").size() == 0){
		alert("请选择审批人员");
		$("#next_approver_all").focus();
		return false;
	}
	var next_approver = "";
	$("#next_approver_sel option").each(function(){
		next_approver += this.value+",";
	});
	var len = next_approver.length;
	if (next_approver.lastIndexOf(",") == len - 1){
		next_approver = next_approver.substring(0, len - 1);
	}
	$("#next_approver").val(next_approver);
	$("#hid_form").submit();
}
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
<form method="post" action="${ctx}/asset/addpropertywasteevent.action" id="hid_form">
	<input type="hidden" name="addjob" value="Y"/>
	<input type="hidden" name="property_barcode" value="${property_barcode}"/>
	<%-- <input type="hidden" name="jobType" value="${jobType.jobTypeCode}"/> --%>
	<!-- <input type="hidden" name="seclv_code" id="seclv_code"/> -->
	<input type="hidden" name="next_approver" id="next_approver"/>
	<input type="hidden" id="event_code" name="event_code" value="${event_code}"/>
	<input type="hidden" id="dept_id" name="dept_id" value="${curUser.dept_id}"/>
	<input type="hidden" id="jobType" name="jobType" value="${jobType}"/>	
    <input type="hidden" id="type" name="type" value="${type}"/>
    <input type="hidden" id="handleType" name="handleType" value="${handleType}"/>
    <input type="hidden" name="id" value="${id}"/>
	<tr>
	    <td colspan="6" class="title_box">生成审批单</td>
	</tr>
	<tr> 
    	<td width="15%" align="center">申请用户： </td>
    	<td width="35%"><font color="blue"><b>${curUser.user_name}</b></font></td>
    	<td width="15%" align="center">用户部门： </td>
    	<td width="35%"><font color="blue"><b>${curUser.dept_name}</b></font></td>
    </tr>
    <tr><td align="center"><font color="red">*</font>&nbsp;作业审批密级：</td>
	    <td colspan="1">
			<select id="seclv_code" name="seclv_code" onchange="selectSeclv(this.value);">
				<option value="">--请选择--</option>
				<s:iterator value="#request.seclvList" var="seclv">
					<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
				</s:iterator>
			</select>
		</td>
		<td width="10%" align="center">业务类型：</td>
		<c:if test="${handleType eq 'WASTE'}">
    	<td width="23%"><font color="blue"><b>资产报废</b></font></td>
    	</c:if>
    	<c:if test="${handleType eq 'PROPERTYCHANGE'}">
    	<td width="23%"><font color="blue"><b>资产变更</b></font></td>
    	</c:if>
	</tr>
		</tr>
	<tr>
    	<td align="center">设备编号： </td>
    	<td><font color="blue"><b>&nbsp;${property.property_no}</b></font></td>
    	<td align="center">设备名称： </td>
    	<td><font color="blue"><b>&nbsp;${property.property_name}</b></font></td>
    	
	</tr>
	<c:if test="${handleType eq 'WASTE'}">
	<tr>
    	<td align="center"><font color="red">*</font>折旧年限： </td>
    	<td><input type="text" name="deper_life" id="deper_life"/></td>
    	<td align="center"><font color="red">*</font>已使用年限： </td>
    	<td><input type="text" name="userd_life" id="userd_life"/></td>
	</tr>
	</c:if>
	<tr>
    	<td align="center">型号： </td>
    	<td><font color="blue"><b>&nbsp;${property.property_type}</b></font></td>
    	<td align="center">原值： </td>
    	<td><font color="blue"><b>&nbsp;${property.original_value}</b></font></td>
	</tr>
	<tr>
    	<td align="center">规格： </td>
    	<td><font color="blue"><b>&nbsp;${property.property_standard}</b></font></td>
    	<td align="center">资产种类： </td>
    	<td><font color="blue"><b>&nbsp;${property.property_kind}</b></font></td>
	</tr>
	<tr>
    	<td align="center">制造厂商： </td>
    	<td><font color="blue"><b>&nbsp;${property.supplier}</b></font></td>
    	<td align="center">使用部门： </td>
    	<td><font color="blue"><b>&nbsp;${property.duty_dept_name}</b></font></td>
	</tr>
	<c:if test="${handleType eq 'WASTE'}">
	<tr>
    	<td align="center"><font color="red">*</font>仪器现状及报废原因： </td>
    	<td colspan="3"><textarea rows="3" cols="80" name="reason" id="reason"></textarea></td>
	</tr>
	<!--  <tr>
    	<td align="center"><font color="red">*</font>鉴定意见及负责鉴定人： </td>
    	<td colspan="3"><textarea rows="3" cols="80" name="suggestion" id="suggestion"></textarea></td>
	</tr>
	<tr>
    	<td align="center"><font color="red">*</font>设备报废后处理意见： </td>
    	<td colspan="3"><textarea rows="3" cols="80" name="suggestion_waste" id="suggestion_waste"></textarea></td>
	</tr> -->
	</c:if>
	<c:if test="${handleType eq 'PROPERTYCHANGE'}">
    <tr> 	
    	<td align="center">变更后责任人： </td>
    	<td>
    		<input type="text" id="change_user_name" name="change_user_name" onkeyup="selectRecvUser(this.value);"/>
    		<input type="hidden" id="change_user_iidd" name="change_user_iidd"/><br>
    		<div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
    		</div>
	    </td>	     	   	   	   	  	   	
    	<td align="center">资产存放单位： </td>
    	<td><input type="text" name="property_place" id="property_place"/></td>   	   	
    </tr>
	</c:if>
  	<tr id="tr_approver" style="display: none">
  		<td align="center" id="selApprover1">选择审批人：</td>
  		<td id="selApprover2" colspan="3">
  			<table width="300" border="0" cellspacing="0" cellpadding="0" align="left" class="table_box_border_empty">
				<tr>
					<td id="allApprovers">
						<SELECT ondblclick="add_True('next_approver_all','next_approver_sel');" style="WIDTH: 100px" multiple="true" size="8" id="next_approver_all">
							<c:forEach var="item" items="${userList}" varStatus="status">
								<option value="${item.user_iidd}">${item.user_name}</option>
							</c:forEach>
						</SELECT>
					</td>
					<td aling="center" valign="middle">
						<INPUT class="button_2003" onclick="add_MoreTrue('next_approver_all','next_approver_sel');" type="button" value="增加-->" name="btnAddItem" /><br/>
						<br/>
						<INPUT class="button_2003" onclick="del_MoreTrue('next_approver_sel');" type="button" value="删除<--" name="btnDelItem"><br/>
					</td>
					<td>
						<SELECT size="8" multiple="true" style="WIDTH: 100px" ondblclick="del_True('next_approver_sel');" id="next_approver_sel">
						</SELECT>
					</td>
				</tr>
			</table>
		</td>
  	</tr>	

  	<tr>
	    <td colspan="4" align="center"> 
	      <input type="button" class="button_2003" value="提交" onclick="chkSubmit();" id="submit_btn"/>&nbsp;
	      <input class="button_2003" type="button" value="返回" onclick="javascript:history.go(-1)">&nbsp;
	    </td>
	</tr>
	</form>
</table>
</body>
</html>
