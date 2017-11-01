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
		prepareBizApproval("${process.step_dept}","${process.step_role}","${process.step_dept_name}","${process.step_role_name}","${ctx}");
});

function getFrameReturn(){
}
//-->
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<iframe height="0" width="0" frameborder="0" id="hidden_frame" name="hidden_frame" onload="getFrameReturn();"></iframe>
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
	<tr>
	    <td colspan="6" class="title_box">查看空白盘申领作业详情</td>
	</tr>
	<tr>
		<td width ="15%"align="center">申请用户： </td>
    	<td width ="35%"><font color="blue"><b>&nbsp;${apply_user_name}</b></font></td>
    	<td width ="15%" align="center">用户部门： </td>
    	<td width ="35%"><font color="blue"><b>&nbsp;${event.scope_dept_name}</b></font></td>
    </tr>
	<tr> 
		<td align="center">密级：</td>
	    <td><font color="blue"><b>&nbsp;${event.seclv_name}</b></font></td>
	<td align="center">申请时间： </td>
    	<td><font color="blue"><b>&nbsp;${event.apply_time_str}</b></font></td> 	
    </tr>
    <tr> 	    	   	
    	<td align="center">光盘类型： </td>
    	<td><font color="blue"><b>&nbsp;${event.cd_type}</b></font></td>  
    	<td align="center">&nbsp;空白盘类型： </td>
    	<td><font color="blue"><b>&nbsp;${event.spacecd_type}</b></font></td> 	   	   	
    </tr>
    <tr>	
	   	<td align="center">申领数量： </td>
    	<td><font color="blue"><b>&nbsp;${event.enter_num}</b></font></td>
    	<td align="center">备注： </td>
    	<td><font color="blue"><b>&nbsp;${event.summ}</b></font></td>
    </tr>
  	
 	<tr>
    <td colspan="6" align="center">
		<input class="button_2003" type="button" value="返回" onClick="javascript:history.go(-1);">
    </td>
	</tr>
</table>
</body>
</html>
