<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>查看申请详情</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
	<link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
    <script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
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
	preCalendarDay();
});
function preCalendarDay(){
		Calendar.setup({inputField: "start_time", button: "start_time_trigger", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d %H:%M:%S", cache: true, weekNumbers:true, showOthers: true, step: 1});
		Calendar.setup({inputField: "end_time", button: "end_time_trigger", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d %H:%M:%S", cache: true, weekNumbers:true, showOthers: true, step: 1});
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
<form action="${ctx}/infosystem/viewopenporteventdetail.action" method="POST" id="viewOpenPortEventDetailForm">
<input type="hidden" name="event_code" id="hid_event_code" value="${event_code}"/>
<input type="hidden" name="resubmit" id="resubmit" value="Y"/>
<input type="hidden" id="dept_id" name="dept_id" value="${curUser.dept_id}"/>
<input type="hidden" id="jobType" name="jobType" value="${jobType}"/>
<input type="hidden" id="next_approver" name="next_approver" />
<input type="hidden" name="seclv_code" id="seclv_code"/>
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
	 <tr>
		 <td colspan="6" class="title_box">查看开通端口申请</td>
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
	    	<td width="23%"><font color="blue"><b>&nbsp;${computer.computer_name}</b></font></td>
	    	<td align="center">责任人</td>
	    	<td width="23%"><font color="blue"><b>&nbsp;${computer.duty_user_name}</b></font></td>
			<td align="center">IP地址</td>
			<td width="23%"><font color="blue"><b>&nbsp;${computer.computer_ip}</b></font></td>
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
			<td align="center">开通输出<br>端口类型</td>
			<td colspan="5">
				<input type="checkbox" name="output_chk" value="serialport" <c:if test="${serialport == 'yes'}">checked</c:if>/>串口
				<input type="checkbox" name="output_chk" value="parallelport" <c:if test="${parallelport == 'yes'}">checked</c:if>/>并口
				<input type="checkbox" name="output_chk" value="otherport" <c:if test="${otherport == 'yes'}">checked</c:if>/>其他端口
				<input type="text" name="otherports" id="otherports" value="${otherports}"/>
			</td>
		</tr>
	    <tr>
			<td align="center">开通输入<br>端口类型</td>
			<td colspan="5">
				<input type="checkbox" name="input_chk" value="first" <c:if test="${first == 'yes'}">checked</c:if>/>三合一单导盒
				<input type="checkbox" name="input_chk" value="second" <c:if test="${second == 'yes'}">checked</c:if>/>只读光驱
				<input type="checkbox" name="input_chk" value="third" <c:if test="${third == 'yes'}">checked</c:if>/>虚拟光驱
			</td>
		</tr>
		 <tr>
			<td align="center"><font color="red">*</font>开通时间</td>
			<td>
				<input type="text" name="start_time" id="start_time" readonly="readonly" style="cursor:hand;" value="${start_time_str}"/>
       	 		<img src="${ctx}/_image/time2.jpg" id="start_time_trigger">
			</td>
			<td align="center">至</td>
			<td>
				<input type="text" name="end_time" id="end_time" readonly="readonly" style="cursor:hand;" value="${end_time_str}"/>
       	 		<img src="${ctx}/_image/time2.jpg" id="end_time_trigger">
			</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
	    <tr>
			<td align="center"><font color="red">*</font>申请原因</td>
			<td colspan="5">
				<textarea id="event_reason" name="event_reason" rows="3" cols="50">${event.event_reason}&nbsp;</textarea>
			</td>
		</tr>
		<tr>
			<td align="center">备注</td>
			<td colspan="5" >
				<input type="text" name="summ" id="summ" size="55" value="${event.summ}"/>
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