<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>查看空白盘载体详情</title>
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
	    <td colspan="6" class="title_box">查看空白盘载体详情</td>
	</tr>
	<tr>
    	<td width="13%" align="center">光盘条码： </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${entity.barcode}</b></font></td>
    	<td width="13%" align="center">密级： </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${entity.seclv_name }</b></font></td>
	</tr>
	
	<tr>
    	<td width="13%" align="center">制作人： </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${entity.user_name}</b></font></td>
    	<td width="13%" align="center">制作人部门： </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${entity.dept_name}</b></font></td>
	</tr>
	
	<tr>
    	<td width="13%" align="center">状态： </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${entity.spacecd_state_name}</b></font></td>
    	<td width="13%" align="center">录入时间： </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${entity.leadin_timename}</b></font></td>
	</tr>
	
	<tr>
    	<td width="13%" align="center">载体归属： </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${entity.scope_name}</b></font></td>
    	<td width="13%" align="center">光盘类型： </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${entity.cd_type}</b></font></td>
	</tr>
	
	<tr>
    	<td width="13%" align="center">创建类型： </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${entity.create_typename}</b></font></td>
    	<td width="13%" align="center">归属部门： </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${entity.scope_dept_name}</b></font></td>
	</tr>
<%-- 	<tr>
    	<td width="13%" align="center">责任人： </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${entity.duty_user_name}</b></font></td>
    	<td width="13%" align="center">责任人部门： </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${entity.duty_dept_name}</b></font></td>
	</tr> --%>
	<%-- <tr>
    	<td width="13%" align="center">文件列表： </td>
    	<td width="20%"><font color="blue"><b>&nbsp;<textarea rows="2" cols="40"  readonly="readonly">${entity.file_list }</textarea></b></font></td>
	    <td width="13%" align="center">文件数量： </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${entity.file_numname }</b></font></td>
	</tr> --%>
	
     <tr>
	    <td width="13%" align="center">备注： </td>
    	<td width="20%" colspan="3"><font color="blue"><b>&nbsp;<textarea rows="3" cols="50"  readonly="readonly">${entity.comment }</textarea></b></font></td>
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