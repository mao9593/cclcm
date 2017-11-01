<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>添加要害部门部位</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
	<link type="text/css" rel="stylesheet" href="${ctx}/uploadify/uploadify.css"/>
    <link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
    <script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script type="text/javascript" src="${ctx}/uploadify/jquery.uploadify.min.js"></script>
<script  language="JavaScript" >
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
 <table class="table_box" cellspacing=0 cellpadding=0 border=1 width="90%">
	 <tr>
		 <td colspan="6" class="title_box"> 
            	查看要害部门部位申请
        </td>
    </tr>
    <input type="hidden" name="user_iidd" id="user_iidd" value="${curUser.user_iidd}"/>
    <tr>
    	<td width="13%" align="center">申请用户 </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${event.user_name}</b></font></td>
    	<td width="10%" align="center">申请用户部门 </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${event.dept_name}</b></font></td> 
    	<td width="10%" align="center">当前状态 </td>
    	<td width="20%"><font color="red"><b>&nbsp;${event.job_status_name}</b></font></td>    
    	   		
	</tr>
    <tr>
    	<td align="center">要害部门部位名称</td>
		<td><font color="blue"><b>&nbsp;${event.secplace_name}</td>
		<td align="center"><font color="red">*</font>物理位置</td>
		<td><font color="blue"><b>&nbsp;${event.secplace_location}</td>
		<td align="center"><font color="red">*</font>责任人</td>
		<td><font color="blue"><b>&nbsp;${event.duty_user_name}</td>
    </tr>  
    <tr>
    	<td align="center"><font color="red">*</font>要害类别</td>
		<td><font color="blue"><b>&nbsp;
			<c:if test="${event.secplace_type=='1'}">部门</c:if>
			<c:if test="${event.secplace_type=='2'}">部位</c:if>
		</td>
		<td align="center"><font color="red">*</font>要害密级</td>
		<td><font color="blue"><b>&nbsp;${event.seclv_name}</td>
		<td align="center"><font color="red">*</font>责任部门</td>  
		<td><font color="blue"><b>&nbsp;${event.duty_dept_name}</td>
    </tr> 
    
    <tr>
    	<td align="center">涉密人员数量</td>
    	<td><font color="blue"><b>&nbsp;${event.user_number}</td>
		
		<td align="center">预计成立时间</td>
		<td><font color="blue"><b>&nbsp;${event.found_time}</td>
		
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr> 						
    <tr>
    	<td align="center"><font color="red">*</font>用途</td>
    	<td colspan="5"><font color="blue"><b>&nbsp;${event.secplace_application}</td>
    </tr>
     <tr>
    	<td align="center"><font color="red">*</font>人防措施</td>
		<td colspan="5"><font color="blue"><b>&nbsp;${event.people_protect}</td>
    </tr> 	
     <tr>
    	<td align="center"><font color="red">*</font>物防措施</td>
		<td colspan="5"><font color="blue"><b>&nbsp;${event.physical_protect}</td>
    </tr> 	
     <tr>
    	<td align="center"><font color="red">*</font>技防措施</td>
		<td colspan="5"><font color="blue"><b>&nbsp;${event.technology_protect}</td>
    </tr> 	
     <tr>
    	<td align="center">备注</td>
		<td colspan="5"><font color="blue"><b>&nbsp;${event.summ}</td>
    </tr> 	
    <tr>
  		<td align="center">附件列表</td>
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
  	<tr>
	    <td colspan="6" align="center"> 
	      <input class="button_2003" type="button" value="返回" onclick="javascript:history.go(-1)">&nbsp;
	    </td>
	</tr>
</table>

</body>
</html>