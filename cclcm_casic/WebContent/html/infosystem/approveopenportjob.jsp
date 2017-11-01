<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/print.css"  media="print"/>
 	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script> 
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
 	<script type="text/javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
 	<script language="javascript" src="${ctx}/_script/casic304_common_bm.js"></script>
 	<script language="javascript" src="${ctx}/_script/My97DatePicker/WdatePicker.js"></script>
	<script language="javascript" src="${ctx}/_script/jquery.jqprint-0.3.js"></script>
<script>
$(document).ready(function(){
	prepareBizApproval("${process.step_dept}","${process.step_role}","${process.step_dept_name}","${process.step_role_name}","${ctx}");
	if($("#next_approver_all").children().size() == 0){
		$("#selApprover1").hide();
		$("#selApprover2").hide();
	}
	$("#submit_btn").attr("disabled",false);
	
	if("${history}" == "Y"){
		viewOpinion_new("");//判断审批到哪一步
	}else{
		viewOpinion_new("read");//判断审批到哪一步
	}
	$("#page_print").click(function(){
		$(".printContent").jqprint();
	})
	init();
});

function init(){
	var output_point = "${output_port}";
	var test_output_point = output_point.split(",");
	
	if(output_point.indexOf("无") > 0){
		document.getElementById("output1").checked = true;
		return;
	}
	$(":checkbox[name='output_chk']").each(function(){
		for(var k = 0;k<test_output_point.length;k++){
			if(test_output_point[k].indexOf(this.value) > -1){
				this.checked = true;
			}
		}
	});
}

function setOpinionName(){
	var stepx = Number($("#current_step").val());
	if(stepx == 1){
		return "申请部门领导意见";
	}else if(stepx == 2){
		return "信息中心审批意见";
	}else if(stepx == 3){
		return "保密处审批";
	}else if(stepx == 4){
		return "保密处确认";
	}else if(stepx == 5){
		return "任务分配";
	}else if(stepx == 6){
		return "信息中心办理";
	}else if(stepx == 7){
		return "申请人确认";
	}
}

function chk(){
	var this_step = Number($("#listSize").val()) +1;
	if(this_step == 6){
		if(!document.getElementById("output1").checked && !document.getElementById("output2").checked){
			$("#output1").focus();
	  		alert("请选择开通输入输出端口类型");
	  		return false;
	  	}
	  	if(document.getElementById("output2").checked){
	  		if($(":checkbox:checked[value!='']").size()==0){
				alert("请勾选要开通输入输出端口类型");
				return false;
			}
	  	}else{
	  		$("#output_chk").val("无");
	  	}
	}
	
	var opinion_name = setOpinionName();
	var result = subOpinion_new(opinion_name);//提交时将审批意见复制给opinion
	if(result == "result_null"){
		alert("审批意见不能为空");
		return false;
	}else if(result == "result_#"){
		alert("审批意见中不能包含‘#’");
		return false;
	}
	
	if($("#next_approver_all option").size() > 0 && $("#next_approver_sel option").size() == 0 && $("#approved")[0].checked){
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
	return true;
}
</script>
</head>
<div class="printContent">
<body oncontextmenu="self.event.returnValue=false">
<form action="${ctx}/infosystem/approveopenportjob.action" method="post" >
	<input type="hidden" name="next_approver" id="next_approver"/>
	<input type="hidden" name="job_code" value="${job.job_code}"/>
	<input type="hidden" name="event_code" id="event_code" value="${event_code}"/>
	<input type="hidden" id="listSize" name="listSize" value="${listSize}"/> 
	<input type="hidden" id="opinion_all" name="opinion_all" value="${opinion_all}"/> 
	<input type="hidden" id="current_step" name="current_step" value="${current_step}"/> 
	<input type="hidden" id="opinion_history" name="opinion_history" value="${opinion_history}"/> 
	<input type="hidden" id="opinion" name="opinion"/>
	<input type="hidden" id="output_port" name="output_port" value="${output_port}"/> 
	<input type="hidden" name="output_chk" id="output_chk"/>
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
	<tr>
	    <td colspan="6" class="title_box">开通计算机端口审批表</td>
	</tr>
	<tr > 
    	<td width="14%" align="center">申请用户 </td>
    	<td width="20%"><font color="blue"><b>${event.user_name}</b></font></td>
    	<td width="13%" align="center">用户部门 </td>
    	<td width="20%"><font color="blue"><b>${event.dept_name}</b></font></td>
    	<td width="13%" align="center">当前状态 </td>
    	<td width="20%"><font color="red"><b>&nbsp;${event.job_status_name}</b></font></td> 
    </tr>
    
    <tr>
    	<td width="10%" align="center">申请人电话 </td>
    	<td width="23%"><font color="blue"><b>${event.user_phone}</b></font></td>
    	<td align="center">申请时间</td>
		<td width="20%"><font color="blue"><b>&nbsp;${event.apply_time_str}</b></font></td>
		<td>&nbsp;</td>
    	<td>&nbsp;</td>
	</tr>
	<tr>
	   	<td align="center">计算机名称</td>
	   	<td width="23%"><font color="blue"><b>${computer.computer_name}</b></font></td>
	   	<td align="center">责任人</td>
	   	<td width="23%"><font color="blue"><b>${computer.duty_user_name}</b></font></td>
		<td align="center">责任部门</td>
		<td width="23%"><font color="blue"><b>${computer.duty_dept_name}</b></font></td>
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
    	<td align="center">开通输入输出端口需求</td>
		<td colspan="5"><font color="blue"><b>&nbsp;${output_port}</b></font></td>
	</tr>
	<tr>
		<td align="center">申请原因</td>
		<td colspan="5"><font color="blue"><b>${event.event_reason}&nbsp;</td>	
	</tr>
    <tr>
		<td align="center">备注</td>
		<td colspan="5"><font color="blue"><b>${event.summ}&nbsp;</td>		
    </tr>
    <c:if test="${history eq 'Y'}">
		<tr>
	  		<td align="center">下一步骤审批人</td>
	  		<td colspan="5"><font color="blue"><b>&nbsp;${job.next_approver_name}</b></font></td>
	  	</tr>
  	</c:if>
    <!--<tr id="addapprovehistory" style="display:none"></tr>-->
  	<tr>
		<td align="center">申请部门领导意见</td>
		<td colspan="5" id="step1">
			<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
				<tr><td><textarea id="opinion1" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
				<tr><td id="hidden1"></td></tr>
			</table>
		</td>
	</tr>
	<tr>
		<td align="center">信息中心意见</td>
		<td colspan="5" id="step2">
			<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
				<tr><td><textarea id="opinion2" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
				<tr><td id="hidden2"></td></tr>
			</table>
		</td>
	</tr>
	<tr>
		<td align="center">保密处审批</td>
		<td colspan="5" id="step3">
			<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
				<tr><td><textarea id="opinion3" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
				<tr><td id="hidden3"></td></tr>
			</table>
		</td>
	</tr>
	<tr>
		<td align="center">保密处确认</td>
		<td colspan="5" id="step4">
			<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
				<tr><td><textarea id="opinion4" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
				<tr><td id="hidden4"></td></tr>
			</table>
		</td>
	</tr>
	<tr>
		<td align="center">任务分配</td>
			<td colspan="5" id="step5">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion5" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden5"></td></tr>
				</table>
			</td>
	</tr>
	<tr>
		<td align="center">信息中心办理</td>
		<td colspan="5" id="step6">
			<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
				<tr>
					<td>
						开通输入输出端口需求<br>
						<input type="radio" name="output" id="output1" value="无" onclick="judgeIfGray(this.value,1);">无<br>
						<input type="radio" name="output" id="output2" value="有" onclick="judgeIfGray(this.value,1);">有
						<input type="checkbox" name="output_chk" value="打印机"/>打印机
						<input type="checkbox" name="output_chk" value="扫描仪"/>扫描仪
						<input type="checkbox"  name="output_chk" value="单导盒"/>单导盒
						<input type="checkbox" name="output_chk" value="条码枪"/>条码枪
						<input type="checkbox" name="output_chk" value="USB口"/>USB口
						<input type="checkbox" name="output_chk" value="其他"/> 其他
						<input type="text" name="output_chk" id="test1"/>
					</td>
				</tr>
				<tr><td><textarea id="opinion6" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
				<tr><td id="hidden6"></td></tr>
			</table>
		</td>
	</tr>
	<tr>
		<td align="center">申请人确认</td>
		<td colspan="5" id="step7">
			<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
				<tr><td><textarea id="opinion7" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
				<tr><td id="hidden7"></td></tr>
			</table>
		</td>
	</tr>
	
	<c:if test="${history != 'Y'}">
	<tr>
  		<td align="center" id="selApprover1">选择审批人</td>
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
						<INPUT class="button_2003" onclick="add_MoreTrue('next_approver_all','next_approver_sel');" type="button" value="增加-->" /><br/>
						<br/>
						<INPUT class="button_2003" onclick="del_MoreTrue('next_approver_sel');" type="button" value="删除<--"><br/>
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
  	</c:if>
  	<tr class="Noprint">
	    <td colspan="6" align="center">
	    	<c:if test="${history != 'Y'}">
	     	<input class="button_2003" type="submit" value="提交" onclick="return chk();" id="submit_btn" disabled="disabled">&nbsp;
	     	</c:if>
			<input class="button_2003" type="button" value="返回" onClick="javascript:history.go(-1);">&nbsp;
			<c:if test="${history == 'Y'}">
			<input type="button" class="button_2003" value="打印" id="page_print" />
			</c:if>
	    </td>
  	</tr>
</table>
</form>
</body>
</div>
</html>