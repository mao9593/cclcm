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
	$("#seclv_med option").last().attr("selected",true);
	disableEnterSubmit();
	prepareBizApproval("${process.step_dept}","${process.step_role}","${process.step_dept_name}","${process.step_role_name}","${ctx}");
	document.getElementById("allOptions").innerHTML="";
});

function chk()
{		
	if($("#accept_user_iidd").val().trim() == ""){
		alert("请选择添加接收人");
		$("#accept_user_name").val("");
		$("#accept_user_name").focus();
		return false;
	}
	if($("#usage_code").val() == ""){
		alert("请选择用途");
		$("#usage_code").focus();
		return false;
	}
	if($("#seclv_med").val() == ""){
		alert("请选择密级");
		$("#seclv_med").focus();
		return false;
	}
    return true;
}
function chkSubmit()
{	
	if($("#chech_type").val()== "transfer"){
		if(!chk()){
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
	var url = "${ctx}/basic/addtransferprocessjob.action";
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
function selectRecvUser(word){
	$("#accept_user_iidd").val(" ");
	var url = "${ctx}/basic/getfuzzyuser.action";
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
function add_True(param1,param2){
	if(param1 == "allOption"){
		var user_id=$("#allOption").val();
		var user_name=$("#allOption option[value='"+user_id+"']").text();
		if(user_id != ""){
			$("#accept_user_iidd").val(user_id);
			$("#accept_user_name").val(user_name);
			document.getElementById("allOptions").innerHTML="";
		}
	}else{
		add_Select_True(param1,param2);
	}
}
function viewEventDetail(event_code){
	go("${ctx}/basic/vieweventdetail.action?op=view&jobType_code=${jobType.jobTypeCode}&event_code="+escape(event_code));
}

function add_Select_True(allOption,selectOption){
	var all = document.getElementById(allOption);
	var select = document.getElementById(selectOption);
	if (all.selectedIndex > -1){
		selected_spr_text = all.options[all.selectedIndex].text;
		selected_spr_value = all.options[all.selectedIndex].value;
		var sel_sprlen = select.options.length - 1;
		var exist_flag = 1;
		var j = 0;
		for(j = 0; j <= sel_sprlen; j++){
			if(select.options[j].value == selected_spr_value){
				exist_flag = 0;
				break;
			}
		}
		if(exist_flag){
			var temp = new Option(selected_spr_text);
			temp.value = selected_spr_value;
			select.options[++sel_sprlen] = temp;
		}
		else{
			alert("'" + selected_spr_text + "'" + "该选项已存在于右边列表中！请重新选择");
		}
	}
}
function selectNextApprover(){
	del_all_True('next_approver_sel');
	$("#seclv_code").val($("#seclv_med").val());
	var url = "${ctx}/basic/getnextapprover.action";
	callServerPostForm(url,document.forms[0]);
}
function selectSeclv(seclv){
	if(seclv == ""){
		alert("请选择作业审批密级,以确认审批流程");
		$("#seclv_med").focus();
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
	}else if($("#seclv_med").val() != ""){
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
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">

<form action="${ctx}/basic/addtransferprocessjob.action" method="post" onsubmit="chkSubmit();">
	<input type="hidden" name="submit" value="Y"/>
	<input type="hidden" name="event_code" value="${event_code}"/>
	<input type="hidden" name="one_cycle_type" value="${one_cycle_type}"/>
	<input type="hidden" name="actionContext" value="${actionContext}"/>
	<input type="hidden" name="jobType" value="${jobType.jobTypeCode}"/>
	<input type="hidden" name="seclv_code" value="${seclv.seclv_code}" id="seclv_code"/>
	<input type="hidden" name="next_approver" id="next_approver"/>
	<input type="hidden" id="type" name="type" value="${type}"/>
	<input type="hidden" name="event_ids" value="${event_ids}"/>
	<input type="hidden" name="dept_id" value="${curUser.dept_id}"/>
	<input type="hidden" id="chech_type" name="chech_type" value="transfer" />
	<tr>
	    <td colspan="6" class="title_box">生成审批单</td>
	</tr>
	<tr> 
    	<td width="10%" align="center">申请用户： </td>
    	<td width="23%"><font color="blue"><b>${curUser.user_name}</b></font></td>
    	<td width="10%" align="center">用户部门： </td>
    	<td width="23%"><font color="blue"><b>${curUser.dept_name}</b></font></td>
    	<td align="center"><font color="red">*</font>&nbsp;密级：</td>
		<td>
			<select name="job_seclv_code" id="seclv_med" onchange="selectSeclv(this.value);">
				<option value="">--所有--</option>
					<s:iterator value="#request.seclvList" var="sec">
						<option value="${sec.seclv_code}">${sec.seclv_name}</option>
					</s:iterator>
			</select>
		</td>
	</tr>

	<tr> 
		<td align="center"><font color="red">*</font>接收人： </td>
		<td>
		    <input type="text" id="accept_user_name" name="accept_user_name" onkeyup="selectRecvUser(this.value);" size="20"/>
		    <input type="hidden" id="accept_user_iidd" name="accept_user_iidd"  value="${accept_user_iidd}" size="10"/>
		    <br>
		    <div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
	    	<input type="hidden" id="check_type" name="check_type"  value="" size="transfer"/>
		    </div>
		</td>
		<td align="center"><font color="red">*</font>&nbsp;用途： </td>
    	<td>
    		<select name="usage_code" id="usage_code" onchange="selectUsage(this.value);">
    			<option value="">--请选择--</option>
    			<s:iterator value="#request.usageList" var="usage">
					<option value="${usage.usage_code}">${usage.usage_name}</option>
				</s:iterator>
    		</select>
    	</td>
    	<td align="center">具体说明： </td>
		<td ><textarea name="comment" rows="2" cols="30" id="comment"></textarea></td>
	</tr>
</form>
	  	<tr id="tr_approver" style="display: none">
	  		<td align="center" id="selApprover1">选择审批人：</td>
	  		<td id="selApprover2" colspan="5">
	  			<table width="300" border="0" cellspacing="0" cellpadding="0" align="left" class="table_box_border_empty">
					<tr>
						<td id="allApprovers">
							<SELECT ondblclick="add_Select_True('next_approver_all','next_approver_sel');" style="WIDTH: 100px" multiple="true" size="8" id="next_approver_all">
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
		<c:if test="${type eq 'paper' }">
			<td colspan="6">
				<display:table uid="item" class="displaytable" name="papers">
					<display:column title="序号">
						<c:out value="${item_rowNum}"/>
					</display:column>
						<display:column value="文件" title="介质类型"/>
						<display:column property="duty_user_name" title="责任人"/>
						<display:column property="dept_name" title="部门"/>
						<display:column property="paper_barcode" title="文件条码"/>
						<display:column property="file_title" title="文件名称"/>
						<display:column property="seclv_name" title="文件密级"/>
						<display:column property="print_time" title="打印时间"/>
						<display:column property="user_name" title="申请人"/>
						<display:column property="paper_state_name" title="状态"/>
				</display:table>
			</td>
		</c:if>
		<c:if test="${type eq 'disk' }">
			<td colspan="6">
				<display:table uid="item" class="displaytable" name="cds">
					<display:column title="序号">
						<c:out value="${item_rowNum}"/>
					</display:column>
					<display:column value="光盘" title="介质类型"/>
					<display:column property="duty_user_name" title="责任人"/>
					<display:column property="dept_name" title="部门"/>
					<display:column property="cd_barcode" title="光盘编号"/>
					<display:column property="file_list" title="光盘文件列表"/>
					<display:column property="seclv_name" title="介质密级"/>
					<display:column property="burn_time" title="刻录时间"/>
					<display:column property="user_name" title="申请人"/>
					<display:column property="cd_state_name" title="状态"/>
				</display:table>
			</td>
		</c:if>
	</tr>
  	<tr>
	    <td colspan="6" align="center"> 
	      <input type="submit" class="button_2003" value="提交" onclick="chkSubmit();" id="submit_btn"/>&nbsp;
	      <input class="button_2003" type="button" value="返回" onclick="javascript:history.go(-1)">&nbsp;
	    </td>
	</tr>
</table>
</body>
</html>
