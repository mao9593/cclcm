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
 	<script language="javascript" src="${ctx}/_script/jquery.jqprint-0.3.js"></script>
<script>
$(document).ready(function(){
	prepareBizApproval("${process.step_dept}","${process.step_role}","${process.step_dept_name}","${process.step_role_name}","${ctx}");
	if($("#next_approver_all").children().size() == 0){
		$("#selApprover1").hide();
		$("#selApprover2").hide();
	}
	$("#submit_btn").attr("disabled",false);
});

function changeApproved(value){
	if(value == "true"){
		$("#opinion").val("同意");
	}else if(value == "false"){
		$("#opinion").val("不同意");
	}
	$("#page_print").click(function(){
		$(".printContent").jqprint();
	})
}


function chk(){
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

</script>
</head>
<div class="printContent">
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
	<tr>
	    <td colspan="6" class="title_box">查看任务详情</td>
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
	<c:if test="${event.event_type == 6}">
		<tr>
			<td align="center">操作系统</td>
			<td width="20%"><font color="blue"><b>&nbsp;${computer.computer_os}</b></font></td>
	    	<td align="center">硬盘序列号</td>
			<td width="20%"><font color="blue"><b>&nbsp;${computer.hdisk_no}</b></font></td>
			<td>&nbsp;</td>
    		<td>&nbsp;</td>
		</tr>
    </c:if>
    <c:if test="${event.event_type == 7}">
    	 <tr>
	    	<td align="center">IP地址</td>
			<td width="20%"><font color="blue"><b>&nbsp;${computer.computer_ip}</b></font></td>
	    	<td align="center">MAC地址</td>
			<td width="20%"><font color="blue"><b>&nbsp;${computer.computer_mac}</b></font></td>
			<td align="center">联网情况</td>
			<td width="20%"><font color="blue"><b>&nbsp;${computer.internet_type_name}</b></font></td>
	    </tr>
	    <tr>
			<td align="center">安装区域</td>
			<td width="20%"><font color="blue"><b>&nbsp;${computer.storage_area}</b></font></td>
			<td align="center">安装位置</td>
			<td width="20%"><font color="blue"><b>&nbsp;${computer.storage_location}</b></font></td>
			<td>&nbsp;</td>
    		<td>&nbsp;</td>
    	</tr>
    </c:if>
    <c:if test="${event.event_type == 8}">
     <tr>
	    	<td align="center">IP地址</td>
			<td width="20%"><font color="blue"><b>&nbsp;${computer.computer_ip}</b></font></td>
	    	<td align="center">KEY编号</td>
			<td width="20%"><font color="blue"><b>&nbsp;${computer.key_code}</b></font></td>
			<td align="center">联网情况</td>
			<td width="20%"><font color="blue"><b>&nbsp;${computer.internet_type_name}</b></font></td>
	    </tr>
	    <tr>
	    	<td align="center">申请内容</td>
			<td width="20%"><font color="blue"><b>&nbsp;${apply_type_name}</b></font></td>
	    	<td align="center">权限类别</td>
	    	<td width="20%"><font color="blue"><b>&nbsp;${power_type_name}</b></font></td>
	    	<td>&nbsp;</td>
    		<td>&nbsp;</td>
	    </tr>
    </c:if>
    <c:if test="${event.event_type == 9}">
    	<tr>
    		<td align="center">开通输出端口</td>
			<td width="20%"><font color="blue"><b>&nbsp;${output_port}</b></font></td>
			<td align="center">开通时间</td>
			<td width="20%" colspan="3"><font color="blue"><b>&nbsp;${start_time}至${end_time}</b></font></td>
		</tr>
		<tr>
			<td align="center">开通输入端口</td>
			<td width="20%"><font color="blue"><b>&nbsp;${input_port}</b></font></td>
			<td>&nbsp;</td>
    		<td>&nbsp;</td>
    		<td>&nbsp;</td>
    		<td>&nbsp;</td>
		</tr>
    </c:if>
    <c:if test="${event.event_type == 10}">
    	<tr><td colspan="6" align="center">打印机基本信息</td></tr>
	     <tr>
	    	<td align="center">打印机型号</td>
			<td width="20%"><font color="blue"><b>&nbsp;${printer_model}</b></font></td>
	    	<td align="center">保密编号</td>
	    	<td width="20%"><font color="blue"><b>&nbsp;${printer_conf_code}</b></font></td>
			<td align="center">密级</td>
			<td width="20%"><font color="blue"><b>&nbsp;${printer_seclv_name}</b></font></td>
	    </tr>
    </c:if>
    <tr>
		<td align="center">申请原因</td>
		<td colspan="5"><font color="blue"><b>
			${event.event_reason}&nbsp;
		</td>	
	</tr>
    <tr>
		<td align="center">备注</td>
		<td colspan="5"><font color="blue"><b>
			${event.summ}&nbsp;
		</td>		
    </tr>
    <tr height="50" style="display: <c:if test="${empty event.job_status or op=='noprc'}">none</c:if>">
  		<td align="center">审批人</td>
  		<td colspan="5"><font color="blue"><b>&nbsp;${job.next_approver_name}</b></font></td>
  	</tr>
  	
  	<tr style="display: <c:if test="${empty event.job_status or op=='noprc'}">none</c:if>">
  		<td align="center">流程记录</td>
  		<td colspan="5">
  			<table width="90%" border="0" cellspacing="0" cellpadding="0" align="left" >
				<tr>
					<td align="center" width="100">操作</td>
					<td align="center" width="100">操作人</td>
					<td align="center" width="100">部门</td>
					<td align="center" width="150">时间</td>
					<td align="center">意见</td>
				</tr>		
	  			<s:iterator value="#request.recordList" var="item">
	  				<tr>
	  					<td align="center">&nbsp;${item.operation}</td>
	  					<td align="center">&nbsp;${item.user_name}</td>
	  					<td align="center">&nbsp;${item.dept_name}</td>
	  					<td align="center">&nbsp;${item.op_time_str}</td>
	  					<td align="center">&nbsp;${item.opinion}</td>
	  				</tr>
				</s:iterator>
  			</table>
  		</td>
  	</tr>
  	<tr valign="middle" height="80" style="display: <c:if test="${empty event.job_status or op=='noprc'}">none</c:if>"> 
    	<td align="center">流程信息 </td>
    	<td class="table_box_border_empty" colspan="5">
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
	
	<form action="${ctx}/infosystem/approveinfosystemjob.action" method="post" >
	<input type="hidden" name="next_approver" id="next_approver"/>
	<input type="hidden" name="job_code" value="${job.job_code}"/>
	<tr>
		<td align="center"><font color="red">*</font>&nbsp;审批</td>
  		<td class="table_box_border_empty" colspan="2">
  			<table width="90%" border="0" cellspacing="0" cellpadding="0" align="left" >
				<tr>
					<td>
						&nbsp;&nbsp;<input type="radio" name="approved" checked="checked" value="true" id="approved" onclick="changeApproved(this.value);"/>同意
						&nbsp;&nbsp;<input type="radio" name="approved" value="false" onclick="changeApproved(this.value);"/>不同意
					</td>
				</tr>		
	  			<tr>
	  				<td>
	  					<textarea rows="3" cols="50" name="opinion" id="opinion">同意</textarea>
	  				</td>
	  			</tr>
  			</table>
  		</td>
  		<td align="center" id="selApprover1">选择审批人</td>
  		<td id="selApprover2" colspan="2">
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