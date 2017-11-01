<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
 	<script type="text/javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
<script>
<!--
$(document).ready(function(){
	onHover();
	prepareBizApproval("${process.step_dept}","${process.step_role}","${process.step_dept_name}","${process.step_role_name}","${ctx}");
});
function chkSubmit()
{	
	if($("#seclv_code").val() == ''){
		alert("请选择密级");
		$("#seclv_code").focus();
		return false;
	}
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
	$("#next_approver").val(next_approver);
	var url = "${ctx}/basic/addprocessjob.action";
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
function viewEventDetail(event_code){
	go("${ctx}/basic/vieweventdetail.action?op=view&jobType_code=${jobType.jobTypeCode}&event_code="+escape(event_code));
}
function selectNextApprover(){
	del_all_True('next_approver_sel');
	if(document.getElementById("seclv_code").value==""){
		alert("请选择密级,以确认审批流程");
		document.getElementById("seclv_code").focus();
		return false;
	}
	var url = "${ctx}/basic/getnextapprover.action";
	callServerPostForm(url,document.forms[0]);
}
function getAjaxResult(){
	if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
		if(xmlHttp.responseText.indexOf("<SELECT") != -1){
			document.getElementById("allApprovers").innerHTML=xmlHttp.responseText;
			$("#submit_btn").attr("disabled",false);
		}else{
			alert(xmlHttp.responseText);
			$("#submit_btn").attr("disabled",true);
		}
	}
}
//-->
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
<form action="${ctx}/basic/addprocessjob.action" method="post">
	<input type="hidden" name="submit" value="Y"/>
	<input type="hidden" name="event_codes" value="${event_codes}"/>
	<input type="hidden" name="one_cycle_type" value="${one_cycle_type}"/>
	<input type="hidden" name="dept_id" value="${curUser.dept_id}"/>
	<input type="hidden" name="actionContext" value="${actionContext}"/>
	<input type="hidden" name="jobType" value="${jobType.jobTypeCode}"/>
	<input type="hidden" name="next_approver" id="next_approver"/>
	<tr>
	    <td colspan="6" class="title_box">生成审批单</td>
	</tr>
	<tr> 
    	<td width="15%" align="center">申请用户： </td>
    	<td width="35%"><font color="blue"><b>${curUser.user_name}</b></font></td>
    	<td width="15%" align="center">用户部门： </td>
    	<td width="35%"><font color="blue"><b>${curUser.dept_name}</b></font></td>
    </tr>
    <tr>
    	<td align="center"><font color="red">*</font>&nbsp;密级：</td>
	    <td>
			<select name="seclv_code" id="seclv_code" onchange="selectNextApprover();">
				<option value="">--请选择--</option>
				<s:iterator value="#request.seclvList" var="item">
					<option value="${item.seclv_code}">${item.seclv_name}</option>
				</s:iterator>
			</select>
		</td>
		<td align="center">&nbsp;项目： </td>
    	<td><select name="project_code" id="project_code">
    			<option value="">--请选择--</option>
    			<s:iterator value="#request.projectList" var="project">
					<option value="${project.project_code}">${project.project_name}</option>
				</s:iterator>
    		</select>
    	</td>     	
	</tr>
	<tr>		
		<td align="center"><font color="red">*</font>&nbsp;用途： </td>
    	<td><select name="usage_code" id="usage_code">
    			<option value="">--请选择--</option>
    			<s:iterator value="#request.usageList" var="usage">
					<option value="${usage.usage_code}">${usage.usage_name}</option>
				</s:iterator>
    		</select>
    	</td>	
    	<td align="center">具体说明： </td>
	   	<td><textarea name="comment" rows="2" cols="40"></textarea></td>   	
	</tr>
	<c:choose>
		<c:when test="${jobType.jobTypeCode.contains('PRINT') }">
			<tr> 
		    	<td align="center">接收单位： </td>
		    	<td><input type="text" name="output_dept_name"/></td>
		    	<td align="center">接收人： </td>
		    	<td><input type="text" name="output_user_name"/></td>
			</tr>
		</c:when>
		<c:otherwise>
		</c:otherwise>
	</c:choose>
</form>
  	<tr>
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
