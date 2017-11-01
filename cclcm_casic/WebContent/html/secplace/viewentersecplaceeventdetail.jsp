<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <link type="text/css" rel="stylesheet" href="${ctx}/uploadify/uploadify.css"/>
    <link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
    <script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
 	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script> 
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
 	<script type="text/javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
 	<script type="text/javascript" src="${ctx}/uploadify/jquery.uploadify.min.js"></script>
<script>
$(document).ready(function(){
	if('${event.job_status}' != ""){
		prepareBizApproval("${process.step_dept}","${process.step_role}","${process.step_dept_name}","${process.step_role_name}","${ctx}");
	}
});
	
function downloadFile(file_path,file_name){
	document.getElementById("file_path").value=file_path;
	document.getElementById("file_name").value=file_name;
	document.getElementById("uploadForm").submit();
}
function getFrameReturn(){
}

</script>
</head>
<body oncontextmenu="self.event.returnValue=false">

<form action="${ctx}/burn/downloadfile.action" target="hidden_frame" id="uploadForm" method="post">
	<input type="hidden" name="file_path" id="file_path"/>
	<input type="hidden" name="file_name" id="file_name"/>
</form>
<iframe height="0" width="0" frameborder="0" id="hidden_frame" name="hidden_frame" onload="getFrameReturn();"></iframe>
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
	<tr>
	    <td colspan="6" class="title_box">查看申请详情</td>
	</tr>
	<tr > 
    	<td width="10%" align="center">申请用户 </td>
    	<td width="23%"><font color="blue"><b>${event.user_name}</b></font></td>
    	<td width="10%" align="center">用户部门 </td>
    	<td width="23%"><font color="blue"><b>${event.dept_name}</b></font></td>
    	<td width="10%" align="center">当前状态 </td>
    	<td width="20%"><font color="red"><b>&nbsp;${event.job_status_name}</b></font></td> 
    </tr>
    <tr >
  	 	<td align="center">要害部门部位名称 </td>
		<td width="20%"><font color="blue"><b>&nbsp;${event.secplace_name}</td>
    	<td align="center">作业密级 </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${event.seclv_name}</td>
    	<td align="center">申请时间</td>
    	<td width="20%"><font color="blue"><b>&nbsp;${event.apply_time_str}</td>
	</tr>
	<tr >	
		<td align="center">陪同人员 </td>
		<td width="20%"><font color="blue"><b>&nbsp;${event.accompany_name}</td>
    	<td align="center">拟进入时间 </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${event.enter_time}</td>
    	<td align="center">拟离开时间 </td>
	   	<td width="20%"><font color="blue"><b>&nbsp;${event.leave_time}</td>
	</tr>
    <tr>
		<td align="center">进入事由</td>
	   	<td colspan="5"><font color="blue"><b>&nbsp;${event.visit_reason}</td>
  	</tr>
  	 <tr>
		<td align="center">外来人员情况</td>
		<td colspan="5">
  			<table width="60%" border="0" cellspacing="0" cellpadding="0" align="left" >
				<tr>
					<td align="center">姓名</td>
					<td align="center">单位</td>
					<td align="center">证件类型</td>
					<td align="center">证件号</td>
					<td align="center">携带物</td>
					<td align="center">安全措施</td>
				</tr>		
	  			<s:iterator value="#request.visitorList" var="visitor">
	  				<tr>
	  					<td ><font color="blue"><b>&nbsp;${visitor.visitor_name}</td>
	  					<td ><font color="blue"><b>&nbsp;${visitor.visitor_company}</td>
	  					<td ><font color="blue"><b>&nbsp;${visitor.certificate_type}</td>
	  					<td ><font color="blue"><b>&nbsp;${visitor.certificate_code}</td>
	  					<td ><font color="blue"><b>&nbsp;${visitor.visitor_belongings}</td>
	  					<td ><font color="blue"><b>&nbsp;${visitor.security_arrangement}</td>
	  				</tr>
				</s:iterator>
  			</table>
  		</td>
  	</tr>
  	<tr>
  		<td>安全保密措施附件列表</td>
  		<td colspan="5">
  			<table width="60%" border="0" cellspacing="0" cellpadding="0" align="left" >
				<tr><td align="center">文件名</td></tr>		
	  			<s:iterator value="#request.secplaceFileList" var="secplaceFile">
	  				<tr>
	  					<td align="center"><u><label style="color: blue;cursor: pointer;" onclick="downloadFile('${secplaceFile.file_path}','${secplaceFile.file_name}');">${secplaceFile.file_name}</label></u></td>
	  				</tr>
				</s:iterator>
  			</table>
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
  	<tr>
	    <td colspan="4" align="center"> 
	      <input class="button_2003" type="button" value="返回" onclick="javascript:history.go(-1)">&nbsp;
	    </td>
	</tr>
</table>
</body>
</html>
