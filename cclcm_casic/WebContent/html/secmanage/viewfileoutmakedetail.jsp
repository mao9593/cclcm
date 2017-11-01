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
	<script language="javascript" src="${ctx}/_script/casic304_common_bm.js"></script>
<script>
<!--
$(document).ready(function(){
	if('${event.job_status}' != ""){
		prepareBizApproval("${process.step_dept}","${process.step_role}","${process.step_dept_name}","${process.step_role_name}","${ctx}");
	}
	viewOpinion();//判断审批到哪一步
});

//-->
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
<input type="hidden" id="listSize" name="listSize" value="${listSize}"/> 
<input type="hidden" id="opinion_all" name="opinion_all" value="${opinion_all}"/> 
	<tr>
	    <td colspan="4" class="title_box">查看涉密文件（资料）外出制作作业详情</td>
	</tr>
	<tr>
    	<td width="15%" align="center">申请用户 </td>
    	<td width="35%"><font color="blue"><b>&nbsp;${event.user_name}</b></font></td>
    	<td width="15%" align="center">当前状态 </td>
    	<td width="35%"><font color="red"><b>&nbsp;${event.job_status_name}</b></font></td>
	</tr>
	<tr>
    	<td align="center">申请用户部门 </td>
    	<td><font color="blue"><b>&nbsp;${event.dept_name}</b></font></td>
    	<td align="center">申请时间 </td>
    	<td><font color="blue"><b>&nbsp;${event.apply_time_str}</b></font></td>
	</tr>
	<tr>
    	<td align="center">作业密级</td>
	    <td><font color="blue"><b>&nbsp;${event.seclv_name}</b></font></td>
		<td align="center">制作种类 </td>
    	<td><font color="blue"><b>&nbsp;${event.kind_str}</b></font></td>
  	</tr>
	<tr>
    	<td align="center">文件（资料）名称 </td>
    	<td><font color="blue"><b>&nbsp;${event.file_name}</b></font></td>
    	<td align="center">文件密级 </td>
    	<td><font color="blue"><b>&nbsp;${event.file_seclv_code}</b></font></td>
    	
	</tr>
	<tr>
		<td align="center">装订份数 </td>
    	<td><font color="blue"><b>&nbsp;${event.file_count}</b></font></td>
    	<td align="center">每份页数 </td>
    	<td><font color="blue"><b>&nbsp;${event.file_page}</b></font></td>
	</tr>
	<tr>	
    	<td align="center">陪同人员 </td>
    	<td><font color="blue"><b>&nbsp;${event.accompany_name}</b></font></td>
    	<td align="center">制作单位 </td>
    	<td><font color="blue"><b>&nbsp;${event.make_company}</b></font></td>
	</tr>
	<tr> 	
    	<td align="center">备注 </td>
    	<td colspan="3"><font color="blue"><b>&nbsp;${event.summ}</b></font></td>
	</tr>
	<tr> 
  		<td align="center">申请理由</td>
		<td colspan="3"><font color="blue"><b>&nbsp;&nbsp;${event.reason}</b></font></td>
	</tr>
  	<tr>
		<td align="center">部门意见</td>
			<td colspan="3" id="step1">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion1" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden1"></td></tr>
				</table>
			</td>
	</tr>
	<tr>
		<td align="center">部门意见</td>
			<td colspan="3" id="step2">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion2" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden2"></td></tr>
				</table>
			</td>
	</tr>
	<tr>
		<td align="center">公司（部）领导意见</td>
			<td colspan="3" id="step3">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion3" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden3"></td></tr>
				</table>
			</td>
	</tr>
	<tr>
		<td align="center">保密处登记</td>
			<td colspan="3" id="step3">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion4" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden4"></td></tr>
				</table>
			</td>
	</tr>
  	<tr>
    <td colspan=4 align=center> 
		<input class="button_2003" type="button" value="返回" onClick="javascript:history.go(-1);">&nbsp;
    </td>
  </tr>
</table>
</body>
</html>
