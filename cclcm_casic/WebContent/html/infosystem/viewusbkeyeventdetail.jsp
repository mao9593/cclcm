<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>查看申请详情</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common_bm.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script language="JavaScript" src="${ctx}/_script/function.js"></script>
	<script language="JavaScript" src="${ctx}/_script/common.js"></script>	
<script  language="JavaScript" >
$(document).ready(function(){
	if('${event.job_status}' != ""){
		prepareBizApproval("${process.step_dept}","${process.step_role}","${process.step_dept_name}","${process.step_role_name}","${ctx}");
	}
	setSeclv("seclv_code");
	selectNextApprover();
});
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
function chk()
{
	if($("#user_phone").val() == ""){
		alert("请填写申请人电话");
		$("#user_phone").focus();
		return false;
	}		
	if($("#apply_type").val() == ""){
		alert("请填写申请内容");
		$("#apply_type").focus();
		return false;
	}
	if($("#power_type").val() == ""){
		alert("请填写权限类别");
		$("#power_type").focus();
		return false;
	}	
	if($("#next_approver_all option").size() > 0 && $("#next_approver_sel option").size() == 0){
		alert("请选择审批人员");
		$("#next_approver_all").focus();
		return false;
	}
	//审批人信息
	var next_approver = "";
	$("#next_approver_sel option").each(function(){
		next_approver += this.value+",";
	});
	var len = next_approver.length;
	if (next_approver.lastIndexOf(",") == len - 1){
		next_approver = next_approver.substring(0, len - 1);
	}
	$("#next_approver").val(next_approver);
	return true;
}
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<form action="${ctx}/infosystem/viewusbkeyeventdetail.action" method="POST" id="viewUsbkeyEventDetailForm">
<input type="hidden" name="event_code" id="hid_event_code" value="${event_code}"/>
<input type="hidden" name="resubmit" id="resubmit" value="Y"/>
<input type="hidden" id="dept_id" name="dept_id" value="${curUser.dept_id}"/>
<input type="hidden" id="jobType" name="jobType" value="${jobType}"/>
<input type="hidden" id="next_approver" name="next_approver" />
<input type="hidden" name="seclv_code" id="seclv_code"/>
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
	 <tr>
		 <td colspan="6" class="title_box">查看USB-KEY申请/更新申请</td>
	</tr>
	<tr>
    	<td width="10%" align="center">申请用户 </td>
    	<td width="23%"><font color="blue"><b>${event.user_name}</b></font></td>
    	<td width="10%" align="center">用户部门 </td>
    	<td width="23%"><font color="blue"><b>${event.dept_name}</b></font></td>
    	<td width="10%" align="center">当前状态 </td>
    	<td width="20%"><font color="red"><b>&nbsp;${event.job_status_name}</b></font></td> 
    </tr>
    <tr>
    	<td width="10%" align="center">申请人电话 </td>
    	<td width="23%"><input type="text" name="user_phone" id="user_phone" value="${event.user_phone}"/></td>
    	<td align="center">申请时间</td>
		<td width="20%"><font color="blue"><b>&nbsp;${event.apply_time_str}</b></font></td>
		<td>&nbsp;</td>
    	<td>&nbsp;</td>
	</tr>
	<tr>
	    <td align="center">计算机名称</td>
	    <td width="23%"><font color="blue"><b>${computer.computer_name}</b></font></td>
	    <td align="center">责任人</td>
	    <td width="23%"><font color="blue"><b>${computer.duty_user_name}</b></font></td>
		<td align="center">责任部门</td>
		<td width="23%"><font color="blue"><b>${computer.duty_dept_name}</b></font></td>
	 </tr>
	 <tr>
	    <td align="center">资产编号</td>
		<td width="20%"><font color="blue"><b>&nbsp;${computer.computer_code}</b></font></td>
	    <td align="center">保密编号</td>
		<td width="20%"><font color="blue"><b>&nbsp;${computer.conf_code}</b></font></td>
		<td align="center">密级</td>
		<td width="20%"><font color="blue"><b>&nbsp;${computer.seclv_name}</b></font></td>
	  </tr>
	  <tr>
	    	<td align="center">IP地址</td>
			<td width="20%"><font color="blue"><b>&nbsp;${computer.computer_ip}</b></font></td>
	    	<td align="center">KEY编号</td>
			<td width="20%"><font color="blue"><b>&nbsp;${computer.key_code}</b></font></td>
			<td align="center">网络连接</td>
			<td width="20%"><font color="blue"><b>&nbsp;${computer.internet_type_name}</b></font></td>
	    </tr>
	    <tr>
	    	<td align="center">申请内容</td>
			<td width="20%">
				<select name="apply_type" id="apply_type">
					<option value="1" <c:if test="${apply_type == '1'}">selected</c:if>>首次申请</option>
					<option value="2" <c:if test="${apply_type == '2'}">selected</c:if>>以旧换新</option>
					<option value="3" <c:if test="${apply_type == '3'}">selected</c:if>>退回</option>
					<!-- <option value="4" <c:if test="${apply_type == '4'}">selected</c:if>>责任人变更</option> -->
					<option value="5" <c:if test="${apply_type == '5'}">selected</c:if>>丢失遗失</option>
				</select>
			</td>
	    	<td align="center">权限类别</td>
	    	<td width="20%">
	    		<select name="power_type" id="power_type">
					<option value="1" <c:if test="${power_type == '1'}">selected</c:if>>普通用户权限</option>
					<option value="2" <c:if test="${power_type == '2'}">selected</c:if>>管理员权限</option>
				</select>
	    	</td>
	    	<td>&nbsp;</td>
    		<td>&nbsp;</td>
	    </tr>
	    <tr>
		<td align="center">申请原因</td>
		<td colspan="5"><font color="blue"><b>
			<textarea id="event_reason" name="event_reason" rows="3" cols="50">${event.event_reason}&nbsp;</textarea>
		</td>	
	</tr>
    <tr>
		<td align="center">备注</td>
		<td colspan="5"><font color="blue"><b>
			<input type="text" name="summ" id="summ" value="${event.summ}" size="55"/>
		</td>		
    </tr>
    <s:iterator value="#request.approvehistoryList" var="history">
  		<tr>
  			<td align="center">&nbsp;${history.approvename}</td>
  			<td colspan="5"><font color="blue"><b>&nbsp;${history.approvecontent}</b></font>
  		</tr>
  	</s:iterator>
  	<tr id="tr_approver" style="display: none">
  		<td align="center" id="selApprover1">选择审批人</td>
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
						<br/>
						<INPUT class="button_2003" onclick="add_all_True('next_approver_all','next_approver_sel');" type="button" value="全部增加" name="btnAddItem" /><br/>
						<br/>
						<INPUT class="button_2003" onclick="del_all_True('next_approver_sel');" type="button" value="全部删除" name="btnDelItem"><br/>
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
    <td colspan="6" align="center"> 
		 <input type="button" class="button_2003" value="重新提交" onclick="if(chk()) forms[0].submit();" id="submit_btn"/>&nbsp;
		 <input class="button_2003" type="button" value="返回" onClick="javascript:history.go(-1);">&nbsp;
    </td>
  </tr>
</table>
</form>
</body>
</html>