<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>查看纸质载体详情</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen"/>
	<link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
	<script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script>
	<!--
	$(document).ready(function(){
		if('${paperEntity.job_status}' != ""){
			prepareBizApproval("${process.step_dept}","${process.step_role}","${process.step_dept_name}","${process.step_role_name}","${ctx}");
		}
	});
	//-->
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<table width="80%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
	<tr>
	    <td colspan="6" class="title_box">查看外带载体详情</td>
	</tr>
	<tr>
    	<td width="13%" align="center">文件条码： </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${eventOut.barcode}</b></font></td>
    	<td width="13%" align="center">文件类型： </td>
    	<td width="20%"><font color="red"><b>&nbsp;${eventOut.entityType_name}</b></font></td>
	</tr>
	
	<tr>
    	<td width="13%" align="center">申请用户名： </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${eventOut.user_name}</b></font></td>
    	<td width="13%" align="center">申请用户部门名称： </td>
    	<td width="20%"><font color="red"><b>&nbsp;${eventOut.dept_name}</b></font></td>
	</tr>
	
	<tr>
    	<td width="13%" align="center">类型： </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${eventOut.jobType.jobTypeName}</b></font></td>
    	<td width="13%" align="center">审批状态： </td>
    	<td width="20%"><font color="red"><b>&nbsp;${eventOut.job_status_name}</b></font></td>
	</tr>
	
	<tr>
    	<td width="13%" align="center">申请时间： </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${eventOut.apply_time_str}</b></font></td>
    	<td width="13%" align="center">密级名称： </td>
    	<td width="20%"><font color="red"><b>&nbsp;${eventOut.seclv_name}</b></font></td>
	</tr>
	
	
	<tr>
    	<td width="13%" align="center">带出时间： </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${eventOut.start_Time_str}</b></font></td>
    	<td width="13%" align="center">带回时间： </td>
    	<td width="20%"><font color="red"><b>&nbsp;${eventOut.end_Time_str}</b></font></td>
	</tr>
	
	
	
	<tr>
    	<td width="13%" align="center">带出说明： </td>
    	<td width="20%" colspan="3"><font color="blue"><b>&nbsp;<textarea rows="6" cols="100"  readonly="readonly">${eventOut.carryout_Info }</textarea></b></font></td>
	</tr>
	
	
  	
  	
  	<tr>
	    <td colspan=6 align=center> 
			<input class="button_2003" type="button" value="返回" onClick="javascript:history.go(-1);">&nbsp;
	    </td>
  </tr>
</table>
</center>
</body>
</html>