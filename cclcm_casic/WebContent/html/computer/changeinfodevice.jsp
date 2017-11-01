<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
	<script language="JavaScript" src="${ctx}/_script/function.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
 	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>	
 	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
 	<script language="JavaScript" src="${ctx}/_script/common.js"></script>
 	<script language="javascript" src="${ctx}/_script/casic304_common_bm.js"></script>
<script>
$(document).ready(function(){
	onHover();
	disableEnterSubmit();
	setSeclv("seclv_code");
	selectNextApprover();
	document.getElementById("allOptions").innerHTML="";
});
	function selectRecvUser(word){
		var url = "${ctx}/basic/getfuzzyuser.action?source=CR";
		if(word != ""){
			callServer1(url,"fuzzy="+word);
		}else{
			document.getElementById("allOptions").innerHTML="";
		}
	}
	function add_TrueCR(){
		var user_id=$("#allOptionCR").val();
		var user_name=$("#allOptionCR option[value='"+user_id+"']").text();
		user_name=user_name.substring(0,user_name.indexOf("/"));
		if(user_id != ""){
	    	$("#change_user_id").val(user_id);
			$("#change_user_name").val(user_name);
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

	function selectSeclv(seclv){
		if(seclv == ""){
			alert("请选择作业密级,以确认审批流程");
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
		if($("#change_dept_id").val().trim() == "" && $("#change_user_id").val().trim() == "" && 
			$("#change_selv").val().trim() == "" && $("#change_location").val().trim() == ""){ 
				alert("请输入变更内容");
				$("#change_dept_name").focus();
				return false;
		}
		if($("#change_reason").val().trim() == ""){
			alert("请输入变更原因");
			$("#change_reason").focus();
			return false;
		}
		if($("#next_approver_all option").size() > 0 && $("#next_approver_sel option").size() == 0){
			alert("请选择审批人员");
			$("#next_approver_all").focus();
			return false;
		}		
		//去掉空格
		$("#change_user_id").val($("#change_user_id").val().trim());
		$("#change_location").val($("#change_location").val().trim());
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
<table width="70%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">	
	<form action="${ctx}/computer/changeinfodevice.action" method="POST">
		<input type="hidden" id="event_code" name="event_code" value="${event_code}"/>
		<input type="hidden" id="dept_id" name="dept_id" value="${curUser.dept_id}"/>
		<input type="hidden" id="jobType" name="jobType" value="${jobType}"/>
		<input type="hidden" id="next_approver" name="next_approver" />
		<input type="hidden" name="usage_code" id="usage_code" value="default"/>
		<input type="hidden" name="seclv_code" id="seclv_code"/>
		<input type="hidden" id="device_barcode" name="device_barcode" value="${device_barcode}"/>
	<tr>
	    <td colspan="4" class="title_box">信息设备变更审批表</td>
	</tr>
	<tr> 
    	<td width="15%" align="center">申请用户 </td>
    	<td width="18%" align="center"><font color="blue"><b>&nbsp;${curUser.user_name}</b></font></td>
    	<td width="15%" align="center">用户部门 </td>
    	<td width="17%" align="center"><font color="blue"><b>&nbsp;${curUser.dept_name}</b></font></td>
    </tr>
    <tr>
		<td align="center">设备类型</td>
		<td align="center"><font color="blue"><b>&nbsp;${eventList.device_type_name}</b></font></td>
		<td align="center">子类型</td>
		<td align="center"><font color="blue"><b>&nbsp;${eventList.info_type}</b></font></td>
	</tr>
	<tr>
		<td align="center">保密编号</td>
		<td align="center"><font color="blue"><b>&nbsp;${eventList.conf_code}</b></font></td>
		<td align="center">设备密级</td>
		<td align="center"><font color="blue"><b>&nbsp;${eventList.seclv_name}</b></font></td>
	</tr>	
	<tr>
		<td align="center">变更类型</td>
		<td align="center">变更前</td>
		<td align="center" colspan="2">变更后</td>
	</tr>
	<tr>
		<td align="center">部门</td>
		<td align="center">&nbsp;${eventList.duty_dept_name}</td>
		<td align="center" colspan="2">
			<input type="text" id="change_dept_name" name="change_dept_name" readonly="readonly" style="cursor:hand" onclick="openDeptSelect('change_dept_name','change_dept_id','radio')"/>
    		<input type="hidden" id="change_dept_id" name="change_dept_id"/><br>
		</td>
	</tr>
	<tr>
		<td align="center">责任人</td>
		<td align="center">&nbsp;${eventList.duty_user_name}</td>
		<td align="center" colspan="2">
    		<input type="text" id="change_user_name" name="change_user_name" onkeyup="selectRecvUser(this.value);"/>
    		<input type="hidden" id="change_user_id" name="change_user_id"/><br>
    		<div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
    		</div>
	    </td>
	</tr>
	<tr>
		<td align="center">设备密级</td>
		<td align="center">&nbsp;${eventList.seclv_name}</td>
		<td align="center" colspan="2">
			<select id="change_selv" name="change_selv" onchange="selectSeclv(this.value);">
				<option value="">--请选择--</option>
				<s:iterator value="#request.seclvList" var="seclv">
					<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
				</s:iterator>
			</select>
		</td>
	</tr>
	<tr>
		<td align="center">使用地点</td>
		<td align="center">&nbsp;${eventList.location}</td>
		<td align="center" colspan="2">
			<input type="text" id="change_location" name="change_location"/><br>
		</td>
	</tr>				
	<tr>
		<td align="center"><font color="red">*</font>变更原因</td>
	   	<td colspan="3"><textarea name="change_reason" rows="3" cols="70" id="change_reason"></textarea></td>
 		<!-- <td align="center">备注</td>
	   	<td><textarea name="summ" rows="3" cols="40" id="summ"></textarea></td> -->
	</tr>
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
	</form>
	 	
  	<tr>
    <td colspan="4" align="center"> 
    	<input type="button" class="button_2003" value="提交" onclick="if(chk())forms[0].submit();" id="submit_btn"/>
		<input type="button" value="返回" class="button_2003" onclick="history.go(-1);">
    </td>
  </tr>
</table>
</body>
</html>
