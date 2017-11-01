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
	viewOpinion("");//判断审批到哪一步
});

//-->
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
<input type="hidden" id="listSize" name="listSize" value="${listSize}"/> 
<input type="hidden" id="opinion_all" name="opinion_all" value="${opinion_all}"/> 
<input type="hidden" id="type" name="type" value="${type}"/>
	<tr>
	    <td colspan="4" class="title_box">查看进入重要科研场地作业详情</td>
	</tr>
	<tr>
    	<td width="15%" align="center">申请用户 </td>
    	<td width="35%"><font color="blue"><b>&nbsp;${event.user_name}</b></font></td>
    	<td width="15%" align="center">当前状态 </td>
    	<td width="35%"><font color="red"><b>&nbsp;${event.job_status_name}</b></font></td>
	</tr>
	<tr>
    	<td align="center">申请部门 </td>
    	<td><font color="blue"><b>&nbsp;${event.dept_name}</b></font></td>
    	<td align="center">申请时间 </td>
    	<td><font color="blue"><b>&nbsp;${event.apply_time_str}</b></font></td>
	</tr>
	<tr>
    	<td align="center">作业密级</td>
	    <td><font color="blue"><b>&nbsp;${event.seclv_name}</b></font></td>
		<td align="center">联系电话 </td>
    	<td><font color="blue"><b>&nbsp;${event.contact_num}</b></font></td>
  	</tr>
	<tr>
    	<td align="center">进入成员单位 </td>
    	<td><font color="blue"><b>&nbsp;${event.visitor_company}</b></font></td>
    	<td align="center">进入成员名单 </td>
    	<td><font color="blue"><b>&nbsp;${event.visitor_names}</b></font></td>
	</tr>
	<tr>
    	<td align="center">拟进入科研场地地点 </td>
    	<td><font color="blue"><b>&nbsp;${event.field_site}</b></font></td>
    	<td align="center">携带物品 </td>
    	<td><font color="blue"><b>&nbsp;${event.belongings}</b></font></td>
	</tr>
	<tr>	
    	<td align="center">接待人 </td>
    	<td><font color="blue"><b>&nbsp;${event.rec_user_name}</b></font></td>
    	<td align="center">备注 </td>
    	<td><font color="blue"><b>&nbsp;${event.summ}</b></font></td>
	</tr>
	<tr>
    	<td align="center">拟进入时间 </td>
    	<td><font color="blue"><b>&nbsp;${event.enter_time_str}</b></font></td>
    	<td align="center">拟离开时间 </td>
    	<td><font color="blue"><b>&nbsp;${event.leave_time_str}</b></font></td>
	</tr>
	<tr> 
  		<td align="center">事由</td>
		<td colspan="3"><font color="blue"><b>&nbsp;${event.reason}</b></font></td>
	</tr>
    <tr>
			<td align="center">部门负责人意见</td>
			<td colspan="3" id="step1">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td>
						<textarea id="opinion1" rows="4" cols="100" readonly="readonly" ></textarea>
					</td></tr>
					<tr><td id="hidden1"></td></tr>
			</table>
	  </tr>
	  <tr>
			<td align="center">科研生产处（部）意见</td>
			<td colspan="3" id="step2">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td>
						<textarea id="opinion2" rows="4" cols="100" readonly="readonly" >如果有外资背景或境外人员进入时需要主管所领导审批，这种情况下请选择主管所领导，其他则跳过。</textarea>
					</td></tr>
					<tr><td id="hidden2"></td></tr>
			</table>
	  </tr>
	  <tr>
			<td align="center">主管所领导意见</td>
			<td colspan="3" id="step3">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td>
						<textarea id="opinion3" rows="4" cols="100" readonly="readonly" ></textarea>
					</td></tr>
					<tr><td id="hidden3"></td></tr>
			</table>
	  </tr>
	  <tr>
			<td align="center">接待人确认</td>
			<td colspan="3" id="step4">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td>
						<textarea id="opinion4" rows="4" cols="100" readonly="readonly" ></textarea>
					</td></tr>
					<tr><td id="hidden4"></td></tr>
			</table>
	  </tr>
	   <tr>
			<td align="center">保密处备案</td>
			<td colspan="3" id="step5">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td>
						<textarea id="opinion5" rows="4" cols="100" readonly="readonly" ></textarea>
					</td></tr>
					<tr><td id="hidden5"></td></tr>
			</table>
	  </tr>
  	
  	<tr>
    <td colspan=4 align=center> 
		<input class="button_2003" type="button" value="返回" onClick="javascript:history.go(-1);">&nbsp;
    </td>
  </tr>
</table>
</body>
</html>
