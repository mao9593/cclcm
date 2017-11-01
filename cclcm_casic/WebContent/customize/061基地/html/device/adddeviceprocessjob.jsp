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
});
var i = 0;
function chkSubmit()
{	
	if($("#seclv_code").val() == ''){
		alert("请选择作业审批密级");
		$("#seclv_code").focus();
		return false;
	}
	if($("#usage_code").val() == ""){
		alert("请选择用途");
		$("#usage_code").focus();
		return false;
	}
	if($("input[name='hiddens']").size()==0){
		alert("请添加磁介质");
		$("#med_type").focus();
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
	var url = "${ctx}/device/adddeviceprocessjob.action";
	callServerPostForm1(url,document.getElementById("addProcessJobForm"));
	$("#submit_btn").attr("disabled",true);
    return true;
}
function getAjaxResult1(){
	if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
		if(xmlHttp.responseText == "ok"){
			alert("任务添加完成");
			window.location="${ctx}/device/managedevicejob.action";
		}else{
			alert(xmlHttp.responseText);
			$("#submit_btn").attr("disabled",false);
		}
	}
}
function selectNextApprover(){
	del_all_True('next_approver_sel');
	var url = "${ctx}/basic/getnextapprover.action";
	callServerPostForm(url,document.getElementById("addProcessJobForm"));
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
function addEvent(){
	if($("#med_type").val() == ""){
		alert("请选择介质类型");
		$("#med_type").focus();
		return false;
	}
	if($("#seclv_code").val() == ""){
		alert("请选择作业审批密级");
		$("#seclv_code").focus();
		return false;
	}
	if($("#seclv_event").val() == ""){
		alert("请选择介质密级");
		$("#seclv_event").focus();
		return false;
	}
	var sign = true;
	//判断密级是否合理的方法
	$("#seclv_event :selected").nextAll().each(function(){
		if($("#seclv_code").val() == this.value){
			alert("磁介质的密级不可高于作业审批密级");
			sign =  false;
		}
	});
	if(sign){
		var med_type = $("#med_type option:selected").val();
		var med_type_name = $("#med_type option:selected").text();
		var seclv_code = $("#seclv_event option:selected").val();
		var seclv_name = $("#seclv_event option:selected").text();
		$("#device_list").after(addRowAfter(med_type,seclv_code,med_type_name,seclv_name));
		$("#seclv_code").attr("disabled",true);
	}
}

function addRowAfter(med_type,seclv_code,med_type_name,seclv_name){
	var $tr_i = $("<tr>");
	var $input_hidden = $("<input>",{
		 type:"hidden",
		 name:"hiddens",
		 value:med_type+"#"+seclv_code
	});
	var $td_type = $("<td>",{
		text:med_type_name,
		align:"center"
	});
	var $td_seclv = $("<td>",{
		text:seclv_name,
		align:"center"
	});
	var $td_but = $("<td>",{
		align:"center"
	});
	var $del_font = $("<font>",{
		color:"blue"
	});
	var $del_u = $("<u>",{
		text:"删除"
	});
	var $del_but = $("<a>",{
		style:"cursor:pointer",
		click:function(){
			if(confirm("确定要取消该借用申请？")){
				$tr_i.attr("id","pendingDelete");
				$("#pendingDelete").remove();
			}
		}
	});
	$del_font.append($del_u);
	$del_but.append($del_font);
	$td_but.append($del_but);
	$tr_i.append($td_type);
	$tr_i.append($td_seclv);
	$tr_i.append($td_but);
	$tr_i.append($input_hidden);
	return $tr_i;
}

//-->
</script>
</head>
<body >
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
<form action="${ctx}/device/adddeviceprocessjob.action" method="post" id="addProcessJobForm">
	<input type="hidden" name="submit" value="Y"/>
	<input type="hidden" name="dept_id" value="${curUser.dept_id}"/>
	<input type="hidden" name="jobType" value="DEVICE"/>
	<input type="hidden" name="next_approver" id="next_approver"/>
	<tr>
	    <td colspan="6" class="title_box">生成审批单</td>
	</tr>
	<tr height="40"> 
    	<td width="10%" align="center">申请用户： </td>
    	<td width="23%"><font color="blue"><b>${curUser.user_name}</b></font></td>
    	<td width="10%" align="center">用户部门： </td>
    	<td width="23%"><font color="blue"><b>${curUser.dept_name}</b></font></td>
    	<td width="10%" align="center">审批单类型： </td>
    	<td width="23%"><font color="blue"><b>借用磁介质</b></font></td>
    </tr>
    <tr height="40">
    	<td width="10%" align="center"><font color="red">*</font>&nbsp;作业审批密级：</td>
	    <td width="23%">
			<select name="seclv_code" id="seclv_code" onchange="selectSeclv(this.value);">
				<option value="">--请选择--</option>
				<s:iterator value="#request.seclvList" var="item">
					<option value="${item.seclv_code}">${item.seclv_name}</option>
				</s:iterator>
			</select>
		</td>
		<td width="10%" align="center"><font color="red">*</font>&nbsp;用途： </td>
    	<td width="23%"><select name="usage_code" id="usage_code" onchange="selectUsage(this.value);">
    			<option value="">--请选择--</option>
    			<s:iterator value="#request.usageList" var="usage">
					<option value="${usage.usage_code}">${usage.usage_name}</option>
				</s:iterator>
    		</select>
    	</td>
    	<td align="center">具体说明： </td>
	   	<td><textarea name="comment" rows="3" cols="40"></textarea></td>
    	<%-- <td width="10%" align="center">&nbsp;项目： </td>
    	<td width="23%"><select name="project_code" id="project_code">
    			<option value="">--请选择--</option>
    			<s:iterator value="#request.projectList" var="project">
					<option value="${project.project_code}">${project.project_name}</option>
				</s:iterator>
    		</select>
    	</td>  --%>  
	</tr>
    <tr>
		<td align="center">&nbsp;添加磁介质：</td>
		<td colspan="5" align="center">
  			<table width="80%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_box_bottom_border">
  			<tr id="device_list">
  				<td  align="center">&nbsp;&nbsp;
  					磁介质类型：<select name="med_type" id="med_type">
			    			<option value="">--请选择--</option>
			    			<option value="1">U盘</option>
			    			<option value="2">移动硬盘</option>
			    			<option value="3">笔记本</option>
			    			<option value="4">照相机</option>
							<option value="5">录像机</option>
							<option value="6">录音笔</option>
							<option value="8">软盘</option>
							<option value="9">磁带</option>
							<option value="10">录像带</option>
							<option value="11">录音带</option>
							<option value="12">移动光驱</option>
							<option value="13">红黑电源</option>
							<option value="14">安全设备</option>
							<option value="15">多功能导入装置</option>
							<option value="16">硬盘</option>
			    		</select>
				</td>
				<td  align="center">
  					磁介质密级：<select name="seclv_event" id="seclv_event">
  							<option value="">--请选择--</option>
  							<s:iterator value="#request.seclvList" var="seclv">
								<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
							</s:iterator>
						</select>
				</td>
				<td  align="center"><font color="red">*</font>
					<input type="button" onclick="addEvent();" value="添加" class="button_2003"/>
		  		</td>
		 	</tr>
		 	</table>
  		</td>
  	</tr>
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
</form>
</table>
</body>
</html>
