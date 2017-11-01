<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加审批流程</title>
<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" />
<script language="JavaScript" src="${ctx}/_script/function.js"></script>
<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
<script  language="JavaScript" >
<!--
function chk()
{	
	if($("#process_name").val().trim().length == 0){
		alert("请填写流程名称");
		$("#process_name").focus();
		return false;
	}
	if($("#dept_id").val() == ""){
		alert("请设置流程适用的部门");
		return false;
	}
	if($("#tr_seclv :checked").size() == 0){
		alert("请勾选流程适用的密级");
		return false;
	}
	if($("#tr_tag :checked").size() == 0){
		alert("请勾选流程适用的操作");
		return false;
	}
	if(!$("#prc_end").length){
		alert("审批步骤尚未结束");
		return false;
	}
    return true;
}
var total=0;
function addApproval(tag){
	var url = "${ctx}/html/activiti/addapproval.jsp";
	var rValue = window.showModalDialog(url,'', 'dialogHeight:210px;dialogWidth:500px;center:yes;status:no;help:no;scroll:no;unadorned:no;resizable:no');
	if(rValue != undefined && rValue != null){
		total++;
		var $parent_td = $(tag).parentsUntil("table").last().parentsUntil("tr").last();
		$td_step = create_step_element(total,rValue);
		$parent_td.before($td_step);
		$parent_td.before("<td><table><tr><td><img src='${ctx}/_image/ico/process/to.gif' border='0'/></td></tr></table></td>");
	}
}
function create_step_element(index,obj){
	$step = $("<img>",{
		alt:index+"级审批",
		border:"0",
		src:"${ctx}/_image/ico/process/prc_step.jpg",
		click:function(){
			if(confirm("确定要删除该审批步骤？")){
				var $td_step = $(this).parentsUntil("table").last().parentsUntil("tr").last();
				var $td_to = $td_step.prev();
				$td_step.remove();
				$td_to.remove();
				total--;
			}
		}
	});
	$input_dept = $("<input>",{
		type:"hidden",
		name:"step_dept",
		value:obj.dept_id.replace(/,/g,"@")
	});
	$input_role = $("<input>",{
		type:"hidden",
		name:"step_role",
		value:obj.role_id
	});
	$td=$("<td/>");
	$table=$("<table/>");
	$tbody = $("<tbody/>");
	$tr1 = $("<tr/>");
	$td1 = $("<td align='center'/>");
	$td1.append($step);
	$td1.append($input_dept);
	$td1.append($input_role);
	$tr1.append($td1);
	$tr2 = $("<tr/>");
	$td2 = $("<td>"+obj.dept_name+"["+obj.role_name+"]"+"</td>");
	$tr2.append($td2);
	$tbody.append($tr1);
	$tbody.append($tr2);
	$table.append($tbody);
	$td.append($table);
	return $td;
}
function endProcess(tag){
	var $parent_td = $(tag).parentsUntil("table").last().parentsUntil("tr").last();
	$parent_td.before("<td><table><tr><td align='center'><img id='prc_end' src='${ctx}/_image/ico/process/prc_end.jpg' alt='流程结束' border='0' onclick='startUpdateStep(this);'/></td></tr><tr><td>流程结束</td></tr></table></td>");
	//$parent_td.remove();
	$parent_td.hide();
}
function transferDeptSel(tag){
	if($(tag).attr("checked")){
		$("#dept_name").val("");
		$("#dept_name").attr("disabled",true);
		$("#dept_name").css("background-color","#C0C6C9");
		$("#dept_id").val("default");
	}else{
		$("#dept_name").attr("disabled",false);
		$("#dept_name").css("background-color","#FFFFFF");
		$("#dept_id").val("");
	}
}
function startUpdateStep(tag){
	var $parent_td = $(tag).parentsUntil("table").last().parentsUntil("tr").last().remove();
	$("#edit_btn").show();
}
function checkUsage(tag){
	if($(tag).attr("checked")){
		$(":checkbox[name='usage_code']").attr("disabled",true);
	}else{
		$(":checkbox[name='usage_code']").attr("disabled",false);
	}
}
//-->
</script>
</head>
<body oncontextmenu="self.event.returnValue=false" onload="onHover();">
<center>
<form method="post" action="${ctx}/activiti/addprocess.action" id="AddProcessForm">
  	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="95%">
	 <tr>
		 <td colspan="2" class="title_box">
            	添加审批流程
        </td>
    </tr>
    <tr>
    	<td align="center" width="15%"><font color="red">*</font>流程名称：</td>
		<td>
			<input type="text" name="process_name" id="process_name"/>
		</td>
    </tr>
    <tr>
    	<td align="center"><font color="red">*</font>部门：</td>
		<td>
			<textarea name="dept_name" id="dept_name" readonly cols="40" rows="3" onclick="openDeptSelect('dept_name','dept_id','checkbox')"></textarea>&nbsp;
			<input type="hidden" name="dept_id" id="dept_id"/>
			<input type="checkbox" onclick="transferDeptSel(this);"/>默认
		</td>
    </tr>
    <tr id="tr_seclv">
    	<td align="center"><font color="red">*</font>密级：</td>
		<td>
			<s:iterator value="#request.seclvList" var="seclv">
				<input type="checkbox" value="${seclv.seclv_code}" name="seclv_code"/>${seclv.seclv_name}
			</s:iterator>
		</td>
    </tr>
    <tr id="tr_tag">
    	<td align="center"><font color="red">*</font>操作：</td>
		<td>
			<s:iterator value="#request.typeList" var="type">
				<input type="checkbox" value="${type.jobTypeCode}" name="jobType_code"/>${type.jobTypeName}
			</s:iterator>
		</td>
    </tr>
    <tr id="tr_usage">
    	<td align="center"></font>用途：
    		<input type="checkbox" onclick="checkUsage(this);"/>不限用途
    	</td>
		<td>
			<s:iterator value="#request.usageList" var="usage">
				<input type="checkbox" value="${usage.usage_code}" name="usage_code"/>${usage.usage_name}
			</s:iterator>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="gray" >提示：载体闭环申请（延期回收、外发、归档、销毁）不需选用途,勾选“不限用途”即可</font>
		</td>
    </tr>
    <tr valign="middle" height="80">
    	<td align="center">添加审批步骤：</td>
		<td class="table_box_border_empty">
			<table class="table_box_border_empty"><tr>
				<td>
					<table>
						<tr><td align="center">
							<img alt="流程开始" border="0" src="${ctx}/_image/ico/process/prc_start.jpg" />
						</td></tr>
						<tr><td align="center">
							流程开始
						</td></tr>
					</table>
				</td>
				<td>
					<table>
						<tr><td align="center">
							<img border="0" src="${ctx}/_image/ico/process/to.gif"/>
						</td></tr>
					</table>
				</td>
				<td>
					<table>
						<tr><td align="center">
							<img alt="用户提交申请" border="0" src="${ctx}/_image/ico/process/prc_step.jpg" />
						</td></tr>
						<tr><td align="center">
							用户提交申请
						</td></tr>
					</table>
				</td>
				<td>
					<table>
						<tr><td align="center">
							<img border="0" src="${ctx}/_image/ico/process/to.gif"/>
						</td></tr>
					</table>
				</td>
				<td id="edit_btn">
					<table>
						<tr><td align="center">
							<input type="button" value="添加审批" class="button_2003" onclick="addApproval(this);"/>&nbsp;
							<input type="button" value="结束" class="button_2003" onclick="endProcess(this);"/>
						</td></tr>
					</table>
				</td>
			</tr></table>
		</td>
    </tr>
    <tr>
        <td colspan="2" align="center" class="bottom_box">
            <input type="submit" value="添加" class="button_2003" onclick="return chk();">&nbsp;
            <input type="reset" value="重置" class="button_2003">&nbsp;
            <input type="button" value="返回" class="button_2003" onclick="javascript:history.go(-1);">
        </td>
    </tr>
  	</table>
</form>
</center>
</body>
</html>
