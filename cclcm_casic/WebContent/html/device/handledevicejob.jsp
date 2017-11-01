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
<script>
$(document).ready(function(){
	onHover();
	prepareBizApproval("${process.step_dept}","${process.step_role}","${process.step_dept_name}","${process.step_role_name}","${ctx}");
	init();
});
function init(){
	if($("#tr_approver option").size()>0){
		$("#tr_approver").show();
	}
}
function chkSubmit()
{	
    if(${jobType.jobTypeCode.contains('DESTROY_DEVICE_BYSELF')}){
    	if($("#supervise_user_iidd").val().trim() == ""){
			alert("请填写监销人");
			$("#supervise_user_iidd").focus();
			return false;
		}
		if($("#supervise_user_iidd").val().trim() == '${curUser.user_iidd}'){
			alert("监销人不能填写本人");
			$("#supervise_user_iidd").focus();
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

    var temp = $("#supervise_user_iidd").val();
    $("#temp_supervise_user_iidd").val(temp);
	$("#hid_form").submit();
}
  
function selectRecvUser(word){
	$("#supervise_user_iidd").val(" ");
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
		var duty_user_iidd=$("#allOption").val();
		var duty_user_name=$("#allOption option[value='"+duty_user_iidd+"']").text();
		duty_user_name=duty_user_name.substring(0,duty_user_name.indexOf("/"));
		if(duty_user_iidd != ""){
			$("#supervise_user_iidd").val(duty_user_iidd);
			$("#supervise_user_name").val(duty_user_name);
			document.getElementById("allOptions").innerHTML="";
			}
		}else{
			add_Select_True(param1,param2);
		}
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
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
<form method="post" action="${ctx}/device/handledevicejob.action" id="hid_form">
	<input type="hidden" name="addjob" value="Y"/>
	<input type="hidden" name="device_code" value="${device_code}"/>
	<input type="hidden" name="jobType" value="${jobType.jobTypeCode}"/>
	<input type="hidden" name="seclv_code" value="${seclv.seclv_code}"/>
	<input type="hidden" name="next_approver" id="next_approver"/>
	<input type="hidden" name="device_barcodes" value="${device_barcodes}"/>
	<input type="hidden" id="temp_supervise_user_iidd" name="temp_supervise_user_iidd"  value="${temp_supervise_user_iidd}"/>
</form>
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
    	<td align="center">密级： </td>
    	<td><font color="blue"><b>${seclv.seclv_name}</b></font></td>
    	<td align="center">流程类型： </td>
    	<td><font color="blue"><b>${jobType.jobTypeName}</b></font></td>
	</tr>
	<c:choose>
		<c:when test="${jobType.jobTypeCode.contains('DESTROY_DEVICE_BYSELF')}">
			<tr>
			    <td align="center"><font color="red">*</font>监销人： </td>
				<td>
		    		<select id="supervise_user_iidd" name="supervise_user_iidd">
				         <option value="">--请选择--</option>
				         <s:iterator value="#request.superviseUserList" var="builder">
					     <option value="${builder.user_iidd}">${builder.user_name}</option>
				         </s:iterator>
			        </select>
		    	</td>
			</tr>
		</c:when>
	</c:choose>
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
  	<tr valign="middle" height="100"> 
    	<td align="center">流程信息： </td>
    	<td class="table_box_border_empty" colspan="3">
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
				<td>
					<table>
						<tr><td align="center">
							<img border="0" src="${ctx}/_image/ico/process/prc_end.jpg" id="prc_end"/>
						</td></tr>
						<tr><td align="center">
							流程结束
						</td></tr>
					</table>
				</td>
			</tr></table>
		</td>
	</tr>
  	<tr>
	    <td colspan="4" align="center"> 
	      <input type="button" class="button_2003" value="提交" onclick="chkSubmit();" id="submit_btn"/>&nbsp;
	      <input class="button_2003" type="button" value="返回" onclick="javascript:history.go(-1)">&nbsp;
	    </td>
	</tr>
	<c:choose>
	    <c:when test="${device_barcodes != '' and device_barcodes != null}">
	    <tr>
		<td colspan="6"> 
			<table width="95%" class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
	   					<display:table uid="item" class="displaytable" name="deviceList" form="LedgerQueryCondForm" excludedParams="*">
							<display:column title="序号">
								<c:out value="${item_rowNum}"/>
							</display:column>
							<display:column property="device_name" title="介质名称"/>
						    <display:column property="device_barcode" title="条码号"/>
						    <display:column property="device_series" title="设备编码"/>
						    <display:column property="seclv_name" title="密级"/>
						    <display:column property="device_status_name" title="状态"/>						
						    <display:column property="enter_time_str"   sortable="true" title="登记时间"/>
						</display:table>
					</td>
				</tr>
			</table>	
	     </td>
	     </tr>	 
	    </c:when>
	</c:choose> 	
</table>
</body>
</html>
