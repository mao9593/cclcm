<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="JavaScript" src="${ctx}/_script/function.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
<script>
<!--
$(document).ready(function(){
	if('${event.job_status}' != ""){
		prepareBizApproval("${process.step_dept}","${process.step_role}","${process.step_dept_name}","${process.step_role_name}","${ctx}");
	}
});

//-->
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
	<tr>
	    <td colspan="6" class="title_box">查看用户委托保密管理作业详情</td>
	</tr>
	<tr>
    	<td width="8%" align="center">申请用户 </td>
    	<td width="15%"><font color="blue"><b>&nbsp;${event.user_name}</b></font></td>
    	<td width="8%" align="center">申请用户部门 </td>
    	<td width="15%"><font color="blue"><b>&nbsp;${event.dept_name}</b></font></td>    	
    	<td width="8%" align="center">当前状态 </td>
    	<td width="15%"><font color="red"><b>&nbsp;${event.job_status_name}&nbsp;/&nbsp;${event.entrust_status_name}</b></font></td>
	</tr>
	<tr>
    	<td align="center">申请时间 </td>
    	<td colspan="5"><font color="blue"><b>&nbsp;${event.apply_time_str}</b></font></td>   	
	</tr>
	<tr>
    	<td align="center">被委托人 </td>
    	<td><font color="blue"><b>&nbsp;${event.entrust_user_name}</b></font></td>
    	<td align="center">被委托人部门</td>
	    <td><font color="blue"><b>&nbsp;${event.entrust_dept_name}</b></font></td>
		<td align="center">委托管理部门 </td>
    	<td><font color="blue"><b>&nbsp;${event.be_dept_name}</b></font></td>
  	</tr>
	<tr>
    	<td align="center">职务/职称 </td>
    	<td><font color="blue"><b>&nbsp;${event.duty_name}</b></font></td>
    	<td align="center">护照信息</td>
	    <td colspan="3"><font color="blue"><b>&nbsp;${event.passport_info}</b></font></td>
  	</tr>  	
  	<tr>
 		<td align="center">携带载体信息 </td>
    	<td colspan="5"><textarea rows="3" cols="80" readonly="readonly">&nbsp;${event.confirm_info}</textarea></td>
  	</tr>
	<tr> 
  		<td align="center">备注</td>
		<td colspan="6"><textarea rows="3" cols="80" readonly="readonly">&nbsp;${event.summ}</textarea></td>
	</tr>
  	<tr height="50" style="display: <c:if test="${empty event.job_status or op=='noprc'}">none</c:if>">
  		<td align="center">审批人</td>
  		<td colspan="6"><font color="blue"><b>&nbsp;${job.next_approver_name}</b></font></td>
  	</tr>
  	<tr style="display: <c:if test="${empty event.job_status or op=='noprc'}">none</c:if>">
  		<td align="center">流程记录</td>
  		<td colspan="6">
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
  	<tr>
    <td colspan=6 align=center> 
		<input class="button_2003" type="button" value="返回" onClick="javascript:history.go(-1);">&nbsp;
    </td>
  </tr>
</table>
</body>
</html>
