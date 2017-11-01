<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/print.css"  media="print"/>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="JavaScript" src="${ctx}/_script/function.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common_bm.js"></script>
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
		viewOpinion("");//判断审批到哪一步
	}else{
		viewOpinion("read");//判断审批到哪一步
	}		
	
	$("#page_print").click(function(){
		$(".printContent").jqprint();
	})
});
function viewEventDetail(event_code){
	go("${ctx}/securityuser/viewuserentrustdetail.action?event_code="+escape(event_code));
}
function chk(){
	subOpinion();//提交时将审批意见复制给opinion
	if($("#opinion").val().trim() == ""){
		alert("审批意见不能为空");
		$("#opinion").focus();
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
function changeApproved(value){
	if(value == "true"){
		$("#opinion").val("同意");
	}else if(value == "false"){
		$("#opinion").val("不同意");
	}
}
</script>
</head>
<div class="printContent">
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
	<form action="${ctx}/securityuser/approveuserentrustjob.action" method="post" >
		<input type="hidden" name="next_approver" id="next_approver"/>
		<input type="hidden" name="job_code" value="${job.job_code}"/>
		<input type="hidden" id="listSize" name="listSize" value="${listSize}"/> 
		<input type="hidden" id="opinion_all" name="opinion_all" value="${opinion_all}"/> 
		<input type="hidden" name="event_code" id="event_code" value="${event.event_code}"/> 
		<input type="hidden" id="opinion" name="opinion"/> 
	<tr>
	    <td colspan="6" align="center" class="title_box">委托保密管理审批表</td>
	</tr>
	<tr> 
    	<td width="10%" align="center">申请用户 </td>
    	<td width="23%"><font color="blue"><b>${job.user_name}</b></font></td>
    	<td width="10%" align="center">用户部门 </td>
    	<td width="23%"><font color="blue"><b>${job.dept_name}</b></font></td>
    	<td width="10%" align="center">业务类型</td>
    	<td width="23%"><font color="red"><b>${job.jobType.jobTypeName}</b></font></td>
	</tr>
	<tr>
    	<td align="center">被委托人</td>
	    <td><font color="blue"><b>&nbsp;${event.entrust_user_name}</b></font></td>
	    <td align="center">被委托部门</td>
	    <td><font color="blue"><b>&nbsp;${event.entrust_dept_name}</b></font></td>
	    <td align="center">申请状态 </td>
    	<td><font color="red"><b>&nbsp;${job.job_status_name}</b></font></td>
	</tr>
	<tr>
    	<td align="center">职务/职称</td>
	    <td><font color="blue"><b>&nbsp;${event.duty_name}</b></font></td>
	    <td align="center">护照信息</td>
	    <td colspan="3"><font color="blue"><b>&nbsp;${event.passport_info}</b></font></td>
	</tr>
	<tr>	
		<td align="center">委托管理部门</td>
    	<td><font color="blue"><b>&nbsp;${event.be_dept_name}</b></font></td>
    	<td align="center">备注</td>
    	<td colspan="3"><font color="blue"><b>&nbsp;${event.summ}</b></font></td>
	</tr>
	<c:if test="${history eq 'Y'}">
		<tr>
	  		<td align="center">下一步骤审批人</td>
	  		<td colspan="5"><font color="blue"><b>&nbsp;${job.next_approver_name}</b></font></td>
	  	</tr>
  	</c:if>
	<tr>
		<td align="center">部门意见</td>
		<td colspan="5" id="step1">
			<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
				<tr><td>			
				</td></tr>
				<tr><td><textarea id="opinion1" rows="4" cols="100" readonly="readonly">该涉密人员密级为     
现委托进行保密管理的部门为   
请于我部门兼职保密管理员          办理保密手续
				</textarea></td></tr>
				<tr><td id="hidden1"></td></tr>
		</table>
		</td>
	</tr>
	<tr>
		<td align="center">接受部门意见</td>
		<td colspan="5" id="step2">
			<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
				<tr><td><textarea id="opinion2" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
				<tr><td id="hidden2"></td></tr>
			</table>
		</td>
	</tr>	
	<tr>
		<td align="center">原部门兼职保密员交接确认</td>
		<td colspan="5" id="step3">
			<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
				<tr><td><textarea id="opinion3" rows="4" cols="100" readonly="readonly">该人员带走：
1、（   ）份纸质涉密资料，其中机密（  ）份，秘密（  ）份；
2、 计算机（   ）台，涉密移动介质（  ）个，光盘（  ）张，其他（   ）。
（密级、编号、序列号等保密要素详情请在下面说明）

				</textarea></td></tr>
				<tr><td id="hidden2"></td></tr>
			</table>
		</td>
	</tr>
	<tr>
		<td align="center">委托部门兼职保密员接受确认</td>
		<td colspan="5" id="step4">
			<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
				<tr><td><textarea id="opinion4" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
				<tr><td id="hidden2"></td></tr>
			</table>
		</td>
	</tr>		
	<tr>
		<td align="center">公司人力资源部审批</td>
		<td colspan="5" id="step5">
			<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
				<tr><td><textarea id="opinion5" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
				<tr><td id="hidden2"></td></tr>
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
  	</form>
</table>
</body>
</div>
</html>
