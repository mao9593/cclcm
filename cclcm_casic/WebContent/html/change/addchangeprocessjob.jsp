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
	$("#change_user_name").click(function(){
		var url="${ctx}/change/chooseviewuserbydept.action?no_user_iidd=admin&dept_ids="+$("[name=dept_ids]").val();
		var rValue = window.showModalDialog(url, '', 'dialogHeight:600px;dialogWidth:900px;center:yes;status:no;scroll:auto;help:no;unadorned:no;resizable:no');
		if(rValue!=null && rValue!=undefined){
			$("#change_user_name").val(rValue.user_name);
			$("#change_user_iidd").val(rValue.user_iidd);	
		}
	});
});
function chkSubmit()
{	
	if($("#seclv_code").val() == ""){
		alert("请选择作业审批密级");
		$("#seclv_code").focus();
		return false;
	}
	if($("#usage_code").val() == ""){
		alert("请选择用途");
		$("#usage_code").focus();
		return false;
	}
	if(${change_type == 'toDEPT'} && $("#scope_dept_id").val() == ""){
		alert("请选择归属部门");
		$("#scope_dept_id").focus();
		return false;
	}
	
		if($("#change_user_name").val() == ""){
		alert("请选择用户名称");
		$("#change_user_name").focus();
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
	
	//ajax提交
	var url = "${ctx}/change/addchangeprocessjob.action";
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
function selectSeclv(seclv){
	if(seclv == ""){
		alert("请选择作业审批密级,以确认审批流程");
		$("#seclv_code").focus();
		return false;
	}else if($("#usage_code").val() != ""){
		selectNextApprover();
	}
}

function selectUsage(usage){
	if(usage == ""){
		alert("请选择用途,以确认审批流程");
		$("#usage_code").focus();
		return false;
	}else if($("#seclv_code").val() != ""){
		selectNextApprover();
	}
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
<body >
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
<form action="${ctx}/copy/addchangeprocessjob.action" method="post">
	<input type="hidden" name="submit" value="Y"/>
	<input type="hidden" name="jobType" value="CHANGE" />
	<input type="hidden" name="dept_id" value="${curUser.dept_id}"/>
	<input type="hidden" name="dept_ids" value="${dept_ids}"/>
	<input type="hidden" name="entity_type" value="${entity_type}"/>
	<input type="hidden" name="change_type" value="${change_type}"/>
	<input type="hidden" name="actionContext" value="${actionContext}"/>
	<input type="hidden" name="next_approver" id="next_approver"/>
	<input type="hidden" name="barcode_codes" id="barcode_codes" value="${barcode_codes}"/>
	
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
    	<td align="center"><font color="red">*</font>&nbsp;作业审批密级：</td>
	    <td>
			<select name="seclv_code" id="seclv_code" onchange="selectSeclv(this.value);">
				<option value="">--请选择--</option>
				<s:iterator value="#request.seclvList" var="item">
					<c:if test="${highest_seclv >= item.seclv_rank}">
						<option value="${item.seclv_code}">${item.seclv_name}</option>
					</c:if>
				</s:iterator>
			</select>
		</td>
		<td align="center"><font color="red">*</font>&nbsp;用途： </td>
    	<td><select name="usage_code" id="usage_code" onchange="selectUsage(this.value);">
    			<option value="">--请选择--</option>
    			<s:iterator value="#request.usageList" var="usage">
					<option value="${usage.usage_code}">${usage.usage_name}</option>
				</s:iterator>
    		</select>
    	</td>
	</tr>
	<tr>		
		<c:choose>
			<c:when test="${change_type == 'toDEPT'}">
				<c:choose>
					<c:when test="${deptList !=null and deptList.size()>0 }">
						<td align="center"><font color="red">*</font>&nbsp;归属部门： </td>
	    				<td>
	    					<select name="scope_dept_id" id="scope_dept_id" >
				    			<option value="">--请选择--</option>
				    			<s:iterator value="#request.deptList" var="item">
									<option value="${item.dept_id}">${item.dept_name}</option>
								</s:iterator>
    						</select>
	    				</td>
					</c:when>
					<c:otherwise>
						<td align="center"><font color="red">*</font>&nbsp;归属部门： </td>
	    				<%-- <td>
	    					<font color="blue"><b>${curUser.dept_name}</b></font>
							<input type="hidden" name="scope_dept_id" value="${curUser.dept_id}"/>
	    				</td> --%>
	    				<td> 
							<input type="text" id="scope_dept_name" name="scope_dept_name" readonly="readonly" style="cursor:hand" onclick="openDeptSelect('scope_dept_name','scope_dept_id','radio')"/>
			    			<input type="hidden" id="scope_dept_id" name="scope_dept_id"/><br>
			    		</td>
					</c:otherwise>
				</c:choose>
				<td align="center">具体说明： </td>
			   	<td><textarea name="comment" rows="3" cols="50"></textarea></td>
			</c:when>
			<c:otherwise>
				<td align="center"><font color="red">*</font>&nbsp;用户名称： </td>
				<td>
					<input type="text" id="change_user_name" name="change_user_name" readonly="readonly"/>
					<input type="hidden" id="change_user_iidd" name="change_user_iidd" readonly="readonly"/>
				</td>
				<td align="center">具体说明： </td>
			   	<td><textarea name="comment" rows="3" cols="50"></textarea></td>
			</c:otherwise>
		</c:choose>
	</tr>
</form>
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
		<td colspan="4">
			<display:table uid="item" class="displaytable" name="eventList">
				<display:column title="序号">
					<c:out value="${item_rowNum}"/>
				</display:column>
				<display:column title="密级" property="seclv_name" />
				<display:column title="载体条码" property="barcode"/>
				<display:column title="文件名称" property="file_name"/>
			</display:table>
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
