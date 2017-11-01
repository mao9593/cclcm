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
	    <td colspan="4" class="title_box">查看对外提供资料保密作业详情</td>
	</tr>
	<tr>
    	<td width="15%" align="center">申请部门 </td>
    	<td width="35%"><font color="blue"><b>&nbsp;${event.dept_name}</b></font></td>
    	<td width="15%" align="center">申请用户 </td>
    	<td width="35%"><font color="blue"><b>&nbsp;${event.user_name}</b></font></td>	
	</tr>
	<tr>
		<td align="center">当前状态 </td>
    	<td><font color="blue"><b>&nbsp;${event.job_status_name}</b></font></td>
    	<td align="center">申请时间 </td>
    	<td><font color="blue"><b>&nbsp;${event.apply_time_str}</b></font></td>
	</tr>
	<tr>
		<td align="center">联系电话 </td>
    	<td><font color="blue"><b>&nbsp;${event.contact_num}</b></font></td>
		<td align="center">交付材料类型 </td>
		<td><font color="blue"><b>&nbsp;${event.exc_type_name}</b></font></td>
	</tr>
	<tr>
		<td align="center">交付对象 </td>
    	<td><font color="blue"><b>&nbsp;${event.exc_object}</b></font></td>
    	<td align="center">交付地点 </td>
    	<td><font color="blue"><b>&nbsp;${event.exc_location}</b></font></td>
	</tr>
	<tr>
		<td align="center">交付资料清单</td>
		<td colspan="3" align="center">
			<table border rules=all width="60%" border="0" cellspacing="0" cellpadding="0" align="left"  class="table_box_bottom_border">
				<td width="40%" align="center">资料名称</td>
				<td width="10%" align="center">密 级</td>
				<s:iterator value="#request.material" var="matr">
	  				<tr>
	  					<td align="center"><font color="blue"><b>&nbsp;${matr.file_name}</b></font></td>
	  					<td align="center"><font color="blue"><b>&nbsp;${matr.file_selv}</b></font></td>
	  				</tr>
				</s:iterator>
			</table>
		</td>
	</tr>
  	<tr>
			<td align="center">部门审查意见</td>
			<td colspan="3" id="step1">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td>
						<textarea id="opinion1" rows="4" cols="100" readonly="readonly" ></textarea>
					</td></tr>
					<tr><td id="hidden1"></td></tr>
			</table>
	  </tr>
	  <tr>
			<td align="center">保密审批意见</td>
			<td colspan="3" id="step2">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td>
						<textarea id="opinion2" rows="4" cols="100" readonly="readonly" ></textarea>
					</td></tr>
					<tr><td id="hidden2"></td></tr>
			</table>
	  </tr>
	  <tr>
			<td align="center">材料交接情况记录</td>
			<td colspan="3" id="step3">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td>
						<textarea id="opinion3" rows="4" cols="100" readonly="readonly" ></textarea>
					</td></tr>
					<tr><td id="hidden3"></td></tr>
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
