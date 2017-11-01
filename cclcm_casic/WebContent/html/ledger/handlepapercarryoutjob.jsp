<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
	<script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
 	<script type="text/javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
<script>
$(document).ready(function(){
	onHover();
	prepareBizApproval("${process.step_dept}","${process.step_role}","${process.step_dept_name}","${process.step_role_name}","${ctx}");
	preCalendar();
	init();
});
function init(){
	if($("#tr_approver option").size()>0){
		$("#tr_approver").show();
	}
	$("#carryuser_hid").hide();
}
function chkSubmit()
{	
	if($("#startTime").val().trim() == ""){
		alert("请填写带出时间");
		$("#startTime").focus();
		return false;
	}
	if($("#endTime").val().trim() == ""){
		alert("请填写带回时间");
		$("#endTime").focus();
		return false;
	}
		
	if($("input[name='startTime']").val() != "" && $("input[name='endTime']").val() != ""){
		var startTimeInput = $("input[name='startTime']").val();
		var endTimeInput = $("input[name='endTime']").val();
		var startYear = startTimeInput.substring(0,4);
		var endYear = endTimeInput.substring(0,4);
		var startTime = startTimeInput.substr(5,5)+"-"+startYear+startTimeInput.substr(10);
		var endTime = endTimeInput.substr(5,5)+"-"+endYear+endTimeInput.substr(10);
		var startLong = Date.parse(startTime);
		var endLong = Date.parse(endTime);
		if(startLong != NaN && endLong != NaN && startLong > endLong){
			alert("起止时间查询条件设置不合理，请修改");
			return false;
		}
	}
	
	if($("#send_way").val().trim() == ""){
			alert("请选择外发方式");
			$("#send_way").focus();
			return false;
	}
	
	if($("#carryoutInfo").val().trim() == ""){
		alert("请填写带出申请说明");
		$("#carryoutInfo").focus();
		return false;
	}
	if($("#send_way").val().trim() == 0){
		if($("#carryout_user_names").val().trim() == ""){
			alert("请填写携带人");
			$("#carryout_user_name").focus();
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

function selectRecvUserCR(word){
    var url = "${ctx}/basic/getfuzzyuser.action?source=CR";
    if(word != ""){
	   callServer1(url,"fuzzy="+word);
    }else{
	   document.getElementById("allOptionsCR").innerHTML="";
    }
} 
function updateResult(){
	if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			if(xmlHttp.responseText.toString().length > 154){
				document.getElementById("allOptionsCR").innerHTML=xmlHttp.responseText;
			}else{
				document.getElementById("allOptionsCR").innerHTML="";
			}
		}else{
			document.getElementById("allOptionsCR").innerHTML="";
	}
}
  function add_TrueCR(){
	var carryout_user_iidd=$("#allOptionCR").val();
	var carryout_user_name=$("#allOptionCR option[value='"+carryout_user_iidd+"']").text();
	//carryout_user_name=carryout_user_name.substring(0,carryout_user_name.indexOf("/"));
	
	if(carryout_user_iidd != ""){				
		$("#carryout_user_name").val("");
		if ($("#carryout_user_iidds").val() == "" || ($("#carryout_user_iidds").val().indexOf(carryout_user_iidd) == -1 && $("#carryout_user_iidds").val().split(",").length < 3)) {
			var choose_names = $("#carryout_user_names").val() + carryout_user_name.split("/")[0] + ",";
			var user_iidds =  $("#carryout_user_iidds").val() +carryout_user_iidd + ",";
			//$("#accept_user_name").val(user_name);
			$("#carryout_user_names").val(choose_names);
			$("#carryout_user_iidds").val(user_iidds);
		}
		document.getElementById("allOptionsCR").innerHTML="";
	}
}
function selectSendMode(val){
   if(val=="0"){
       $("#carryuser_hid").show();
   }else{
        $("#carryuser_hid").hide();
   }
}
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
<form method="post" action="${ctx}/ledger/handlepapercarryoutjob.action" id="hid_form">
	<input type="hidden" name="addjob" value="Y"/>
	<input type="hidden" name="isDept" value="${isDept}"/>
	<input type="hidden" name="_chk" value="${_chk}"/>
	<input type="hidden" name="jobType" value="${jobType.jobTypeCode}"/>
	<input type="hidden" name="seclv_code" value="${seclv.seclv_code}"/>
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
    	<td align="center">密级： </td>
    	<td><font color="blue"><b>${seclv.seclv_name}</b></font></td>
    	<td align="center">流程类型： </td>
    	<td><font color="blue"><b>${jobType.jobTypeName}</b></font></td>
	</tr>
	<tr> 
    	<td align="center"><font color="red">*</font>带出时间：</td>
    	<td>
			<input type="text" name="startTime" id="startTime" readonly="readonly" style="cursor:hand;" value="${startTime_str}"/>
			<img src="${ctx}/_image/time2.jpg" id="startTime_trigger">&nbsp;
		</td> 
   		<td align="center"><font color="red">*</font>带回时间：</td>
   		<td>
        	<input type="text" name="endTime" id="endTime" readonly="readonly" style="cursor:hand;" value="${endTime_str}"/>
			<img src="${ctx}/_image/time2.jpg" id="endTime_trigger">&nbsp;
   		</td> 
	</tr>
	<tr>
		<td align="center"><font color="red">*</font>外带方式：</td>
		<td>
			<select name="send_way" id="send_way" onchange="selectSendMode(this.value)">
				<option value="">--请选择---</option>
				<option value="0">专人携带</option>
				<option value="1">发机要</option>
			</select>
		</td>
		<td align="center"><font color="red">*</font>文件带出说明： </td>
    	<td>
    		<textarea id="carryoutInfo" cols="45" name="carryoutInfo"></textarea>
    	</td>
	</tr>
	<tr id='carryuser_hid'>
		<td align="center">携带人： </td>
		<td>
			<input type="text" id="carryout_user_name" name="carryout_user_name" vaule="${carryout_user_name}" onkeyup="selectRecvUserCR(this.value);"/><br>
    		<div id="allOptionsCR" class="containDiv" style="position:absolute;border:0px solid black;padding:0px"></div>
		</td>
		<td align="center"><font color="red">*</font>&nbsp;已添加的携带人：</td>
	    <td>
	        <textarea readonly="true" name="carryout_user_names" id="carryout_user_names" rows="2" cols="45"></textarea>
        	<input type="hidden"  name="carryout_user_iidds" id="carryout_user_iidds" >
        </td>
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
</table>
</body>
</html>
