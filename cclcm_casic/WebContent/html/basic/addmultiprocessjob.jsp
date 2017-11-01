<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
	<script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
 	<script type="text/javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
<script>
<!--
$(document).ready(function(){
	onHover();
	preCalendar();
	prepareBizApproval("${process.step_dept}","${process.step_role}","${process.step_dept_name}","${process.step_role_name}","${ctx}");
	if($("#tr_approver option").size()>0){
		$("#tr_approver").show();
	}
});
function preCalendar(){
	Calendar.setup({inputField: "limitTime", button: "limitTime_trigger", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d %H:%M:%S", cache: true, weekNumbers:true, showOthers: true, step: 1});
}
function chkSubmit(){		
	if($("#usage_code").val() == ""){
		alert("请选择用途");
		$("#usage_code").focus();
		return false;
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
	if($("#limitTime").val() == ""){
		alert("请选择借用期限");
		$("#limitTime").focus();
		return false;
	}
	$("#next_approver").val(next_approver);
	var url = "${ctx}/basic/addmultiprocessjob.action";
	callServerPostForm1(url,document.forms[0]);
	$("#submit_btn").attr("disabled",true);
    return true;
}
function getAjaxResult1(){
	if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
		if(xmlHttp.responseText.indexOf("ok:") != -1){
			alert("任务添加完成");
			var actionContext=xmlHttp.responseText.substr(3);
			window.location="${ctx}/"+actionContext;
		}else{
			alert(xmlHttp.responseText);
			$("#submit_btn").attr("disabled",false);
		}
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
//-->
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
<form action="${ctx}/basic/addmultiprocessjob.action" method="post">
	<input type="hidden" name="submit" value="Y"/>
	<input type="hidden" name="event_codes" value="${event_codes}"/>
	<input type="hidden" name="one_cycle_type" value="${one_cycle_type}"/>
	<input type="hidden" name="dept_id" value="${curUser.dept_id}"/>
	<input type="hidden" name="actionContext" value="${actionContext}"/>
	<input type="hidden" name="jobType" value="${jobType.jobTypeCode}"/>
	<input type="hidden" name="seclv_code" value="${seclv.seclv_code}"/>
	<input type="hidden" name="next_approver" id="next_approver"/>
	<input type="hidden" name="entity_type" id="entity_type" value="${entity_type}"/>
	<input type="hidden" name="barcodemedia" id="barcodemedia" value="${barcodemedia}"/>
	<input type="hidden" name="filename" id="filename" value="${filename}"/>
	<tr>
	    <td colspan="6" class="title_box">生成审批单</td>
	</tr>
	<tr> 
    	<td width="10%" align="center">申请用户： </td>
    	<td width="23%"><font color="blue"><b>${curUser.user_name}</b></font></td>
    	<td width="10%" align="center">用户部门： </td>
    	<td width="23%"><font color="blue"><b>${curUser.dept_name}</b></font></td>
    	<td width="10%" align="center">密级： </td>
    	<td width="23%"><font color="blue"><b>${seclv.seclv_name}</b></font></td>
	</tr>
	<c:choose>
		<c:when test="${jobType.jobTypeCode.contains('BORROW')}">
			<tr> 
		    	<td align="center">&nbsp;载体类型：</td>
				<td><font color="blue"><b>&nbsp;${entity_type_name}</b></font></td>
    			<td align="center">&nbsp;条码：</td>
	    		<td><font color="blue"><b>&nbsp;${barcodemedia}</b></font></td>
    			<td align="center"><font color="red">*</font>&nbsp;用途： </td>
    			<td><select name="usage_code" id="usage_code" onchange="selectNextApprover();">
    				<option value="">--请选择--</option>
    					<s:iterator value="#request.usageList" var="usage">
						<option value="${usage.usage_code}">${usage.usage_name}</option>
					</s:iterator>
    				</select>
    			</td> 
			</tr>
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;借用期限：</td>
				<td>
					<input type="text" id="limitTime" name="limitTime" readonly="readonly" style="cursor:hand;" value="${limitTime}"/>
       				<img src="${ctx}/_image/time2.jpg" id="limitTime_trigger">
				</td>
  				<td align="center">&nbsp;备注：</td>
  				<td colspan="5"><textarea name="comment" id="comment" rows="2" cols="30"></textarea></td>
  			</tr>		
		</c:when>
	</c:choose>
</form>
  	<tr id="tr_approver" style="display: none">
  		<td align="center" id="selApprover1">选择审批人：</td>
  		<td id="selApprover2" colspan="5">
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
	    <td colspan="6" align="center"> 
	      <input type="button" class="button_2003" value="提交" onclick="chkSubmit();" id="submit_btn"/>&nbsp;
	      <input class="button_2003" type="button" value="返回" onclick="javascript:history.go(-1)">&nbsp;
	    </td>
	</tr>
</table>
</body>
</html>
