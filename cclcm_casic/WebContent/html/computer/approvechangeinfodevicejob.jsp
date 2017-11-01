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

</script>
</head>
<div class="printContent">
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
<form action="${ctx}/computer/approveinfodevicejob.action" method="post" >
	<input type="hidden" name="next_approver" id="next_approver"/>
	<input type="hidden" name="job_code" value="${job.job_code}"/>
	<input type="hidden" id="listSize" name="listSize" value="${listSize}"/> 
	<input type="hidden" id="opinion_all" name="opinion_all" value="${opinion_all}"/> 
	<input type="hidden" name="event_code" id="event_code" value="${event.event_code}"/> 
	<input type="hidden" id="opinion" name="opinion"/> 
	<tr>
	    <td colspan="4" align="center" class="title_box">信息设备变更审批表</td>
	</tr>
	<tr> 
    	<td width="10%" align="center">申请用户 </td>
    	<td width="10%"><font color="blue"><b>${job.user_name}</b></font></td>
    	<td width="10%" align="center">用户部门 </td>
    	<td width="10%"><font color="blue"><b>${job.dept_name}</b></font></td>
    </tr>
	<tr>    	
		<td width="10%" align="center">申请状态 </td>
    	<td width="10%"><font color="red"><b>${job.job_status_name}</b></font></td>    	
    	<td align="center">业务类型</td>
    	<td><font color="blue"><b>${job.jobType.jobTypeName}</b></font></td>
    </tr>
	<tr>    	
    	<td align="center">设备类型</td>
    	<td><font color="blue"><b>&nbsp;${event.device_type_name}</b></font></td>
    	<td align="center">子类型</td>
		<td><font color="blue"><b>&nbsp;${event.info_type}</b></font></td>  
	</tr>  
	<tr>    	
    	<td align="center">设备密级</td>
    	<td><font color="blue"><b>&nbsp;${device.seclv_name}</b></font></td>
    	<td align="center">保密编号</td>
		<td><font color="blue"><b>&nbsp;${device.conf_code}</b></font></td>  
	</tr>
	<tr>    	
    	<td align="center">设备责任人</td>
    	<td><font color="blue"><b>&nbsp;${device.duty_user_name}</b></font></td>
    	<td align="center">设备责任部门</td>
		<td><font color="blue"><b>&nbsp;${device.duty_dept_name}</b></font></td>  
	</tr>   	
	<c:if test="${event.change_user_name != ''}">
		<tr>
			<td align="center">变更前责任人</td>
			<td><font color="blue"><b>&nbsp;${event.duty_user_name}</b></font></td>
			<td align="center">变更后责任人</td>
			<td><font color="blue"><b>&nbsp;${event.change_user_name}</b></font></td>
		</tr>  
	</c:if>
	<c:if test="${event.change_dept_name != ''}">
	    <tr>
			<td align="center">变更前责任部门</td>
			<td><font color="blue"><b>&nbsp;${event.duty_dept_name}</b></font></td>
			<td align="center">变更后责任部门</td>
			<td><font color="blue"><b>&nbsp;${event.change_dept_name}</b></font></td>
		</tr>  
	</c:if>
	<c:if test="${event.change_selv_name != ''}">
	    <tr>
			<td align="center">变更前设备密级</td>
			<td><font color="blue"><b>&nbsp;${event.selv_name_before}</b></font></td>
			<td align="center">变更后设备密级</td>
			<td><font color="blue"><b>&nbsp;${event.change_selv_name}</b></font></td>
		</tr>  
	</c:if>
	<c:if test="${event.change_location != ''}">
	    <tr>
			<td align="center">变更前使用地点</td>
			<td><font color="blue"><b>&nbsp;${event.location_before}</b></font></td>
			<td align="center">变更后使用地点</td>
			<td><font color="blue"><b>&nbsp;${event.change_location}</b></font></td>
	    </tr>  
	</c:if>
	<tr>
    	<td align="center">申请时间 </td>
    	<td><font color="blue"><b>&nbsp;${event.apply_time_str}</b></font></td>	
    	<td align="center">备注</td>
    	<td><font color="blue"><b>&nbsp;${event.summ}</b></font></td>
	</tr>	
	<tr>		
		<td align="center">变更原因</td>
		<td colspan="3"><font color="blue"><b>&nbsp;${event.change_reason}</b></font></td>
	</tr>	
	<c:if test="${history eq 'Y'}">
		<tr>
	  		<td align="center">下一步骤审批人</td>
	  		<td colspan="5"><font color="blue"><b>&nbsp;${job.next_approver_name}</b></font></td>
	  	</tr>
  	</c:if>
	<tr>
		<td align="center">部门领导审批</td>
		<td colspan="5" id="step1">
			<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
				<tr><td><textarea id="opinion1" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
				<tr><td id="hidden1"></td></tr>
			</table>
		</td>
	</tr>
	<c:choose>
		<c:when test="${event.device_type == '6'}">
			<tr>
				<td align="center">申请人确认</td>
				<td colspan="5" id="step2">
					<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
						<tr><td><textarea id="opinion2" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
						<tr><td id="hidden2"></td></tr>
					</table>
				</td>
			</tr>	
		</c:when>
		<c:otherwise>
			<tr>
				<td align="center">申请人确认</td>
				<td colspan="5" id="step2">
					<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
						<tr><td><textarea id="opinion2" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
						<tr><td id="hidden2"></td></tr>
					</table>
				</td>
			</tr>	
		</c:otherwise>
	</c:choose>	

	<c:if test="${history != 'Y'}">
	<tr>
  		<td align="center" id="selApprover1">选择审批人</td>
  		<td id="selApprover2" colspan="6">
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
</br></br>
</body>
</div>
</html>
