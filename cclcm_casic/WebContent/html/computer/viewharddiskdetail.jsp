<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>查看硬盘信息</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<form method="post" action="${ctx}/computer/viewharddiskdetail.action">
  	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="60%">
		 <tr>
			 <td colspan="4" class="title_box">查看硬盘 </td>
	    </tr>
	     <tr>
	    	<td align="center" width="10%">责任人</td>
	    	<td width="20%"><font color="blue"><b>&nbsp;${hdisk.duty_user_name}</b></font></td>
			<td align="center" width="10%">责任部门</td> 
			<td width="20%"><font color="blue"><b>&nbsp;${hdisk.duty_dept_name}</b></font></td>
		</tr> 
	    <tr>
			<td align="center">责任单位</td>
			<td><font color="blue"><b>&nbsp;${hdisk.duty_entp_name}</b></font></td>
	        <td align="center">计算机保密编号</td>
			<td><font color="blue"><b>&nbsp;${hdisk.conf_code}</b></font></td>
		</tr> 
	    <tr>
	        <td align="center">硬盘序列号</td>
	    	<td><font color="blue"><b>&nbsp;${hdisk.hdisk_no}</b></font></td>
	        <td align="center">回收人</td>
	    	<td><font color="blue"><b>&nbsp;${hdisk.retrieve_user_name}</b></font></td>
	    </tr>
	     <tr>
	    	<td align="center">回收时间</td>
	    	<td><font color="blue"><b>&nbsp;${hdisk.retrieve_time_str}</b></font></td>
			<td align="center">备注</td>
			<td><font color="blue"><b>&nbsp;${hdisk.summ}</b></font></td>
	    </tr>
	    <tr>
	    	<td colspan="4" align="right">
	    	<c:choose>
				<c:when test="${empty type}">
					<input type="button" class="button_2003" value="查看该台账回收审批任务" onclick="go('${ctx}/computer/approvecomputerjob.action?history=Y&job_code=${hdisk.approve_url}');"/>				
				</c:when>
				<c:otherwise>
					<input type="button" class="button_2003" value="查看该台账回收审批任务" onclick="go('${ctx}/infosystem/approveinfosystemjob.action?history=Y&job_code=${hdisk.approve_url}');"/>
				</c:otherwise>
			</c:choose>
	    	</td>
	    </tr>  
	    <tr>
	        <td colspan="6" align="center">
	            <input type="button" value="返回" class="button_2003" onclick="history.go(-1);">
	        </td>
	    </tr>
	</table>
</form>
</center>
</body>
</html>