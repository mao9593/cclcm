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
function downloadFile(file_path,file_name){
	//alert(file_path);
	document.getElementById("file_path").value=file_path;
	document.getElementById("file_name").value=file_name;
	document.getElementById("uploadForm").submit();
}
function getFrameReturn(){
}
//-->
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<iframe height="0" width="0" frameborder="0" id="hidden_frame" name="hidden_frame" onload="getFrameReturn();"></iframe>
<form action="${ctx}/input/downloadinput.action" target="hidden_frame" id="uploadForm" method="post">
	<input type="hidden" name="file_path" id="file_path"/>
	<input type="hidden" name="file_name" id="file_name"/>
</form>
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
	<tr>
	    <td colspan="4" class="title_box">查看电子文件导入作业详情</td>
	</tr>
	<tr>
    	<td width="15%" align="center">申请用户： </td>
    	<td width="35%"><font color="blue"><b>&nbsp;${event.user_name}</b></font></td>
    	<td width="15%" align="center">当前状态： </td>
    	<td width="35%"><font color="red"><b>&nbsp;${event.job_status_name}&nbsp;</b></font></td>
	</tr>
	<tr>
    	<td align="center">用户部门： </td>
    	<td><font color="blue"><b>&nbsp;${event.dept_name}</b></font></td>
    	<td align="center">申请时间： </td>
    	<td><font color="blue"><b>&nbsp;${event.apply_time_str}</b></font></td>
	</tr>
	<tr>
    	<td align="center">作业密级：</td>
	    <td><font color="blue"><b>&nbsp;${event.seclv_name}</b></font></td>
	    <td align="center">输入介质类型：</td>
	    <td><font color="blue"><b>&nbsp;${event.med_type}</b></font></td>
	</tr>
	<tr>
	    <td align="center">信息用途：</td>
		<td colspan="3"><font color="blue"><b>&nbsp;${event.usage_code}</b></font></td>
	</tr>
	<tr>
	 <td colspan="4">
		   <table width="100%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
			     <tr>
			         <td rowspan="5" align="center" width="15%">信息来源</td>
			     </tr>
			     <tr>
				       <td align="center">单位/ 个人</td>
				       <td colspan="3"><font color="blue"><b>&nbsp;${event.personal}</b></font></td>       
			     </tr>
			     <tr >
			        	<td rowspan="3" align="center" width="15%">互联网</td>
			     </tr>
			     <tr>
				      <td align="center" width="15%">网上/邮件地址</td>
				      <td><font color="blue"><b>&nbsp;${event.address}</b></font></td>       
			     </tr>
			      </tr>
			      <tr>
			       	    <td align="center">文件数量</td>
			    		<td><font color="blue"><b>&nbsp;${event.file_num}</b></font></td>     
			     </tr>
		   </table>
	   </td>
	 </tr>
	 <tr>
  		<td align="center">输入文件：</td>
  		<td colspan="3">
  			<table width="59%" border="0" cellspacing="0" cellpadding="0" align="left" >
				<tr><td width="40%" align="center">信息名称</td><td width="20%"  align="center">密级</td></tr>		
	  			<s:iterator value="#request.eventList" var="inputFile">
	  				<tr>
	  					<td align="center">${inputFile.file_name}</td>
	  					<td align="center">${inputFile.seclv_name}</td>
	  				</tr>
				</s:iterator>
  			</table>
  		</td>
  	</tr>
	<tr> 
    	<%-- <td align="center">项目：</td>
	    <td><font color="blue"><b>&nbsp;${event.project_name}</b></font></td> --%>
	    <td colspan="2">
	     <table width="100%" border="0" cellspacing="0" cellpadding="0" align="left" >
		     <tr>
		       	<td width="10%" align="center">中转盘号： </td>
		    	<td width="25%"><font color="blue"><b>&nbsp;${event.cd_num}</b></font></td> 
		     </tr>
		     <tr>
			     <td width="10%" align="center">互联网盘号：</td>
		    	 <td width="25%"><font color="blue"><b>&nbsp;${event.internet_num}</b></font></td>
		     </tr>
	       </table>
	    </td>
  		<td align="center">具体说明：</td>
		<td><textarea rows="3" cols="40" readonly="readonly">&nbsp;${event.summ}</textarea></td>
	</tr>
	  	<c:choose>
		<c:when test="${event.input_state == 1 and event.job_status == 'true'}">
	    <tr>
	  		<td align="center">信息名称列表：</td>
	  		<td colspan="3">
	  			<table width="100%" border="0" cellspacing="0" cellpadding="0" align="left" >	
		  			<s:iterator value="#request.eventList" var="inputfile">
		  			<c:choose>
		  				<c:when test="${event.user_iidd eq current_user_iidd}">
			    		<tr>
		  					<td align="left"><u><label style="color: blue;cursor: pointer;" onclick="downloadFile('${inputfile.file_path}','${inputfile.file_name}');">${inputfile.file_name}</label></u></td>
			   			</tr>
			    		</c:when>	
			    		<c:otherwise>
			    		<tr>
		  					<td align="left">${inputfile.file_name}</td>
			   			</tr>
			    		</c:otherwise>	 
			    	</c:choose> 				
					</s:iterator>
	  			</table>
	  		</td>
	  	</tr>
    	</c:when>
	</c:choose> 
  	<tr height="50" style="display: <c:if test="${empty event.job_status or op=='noprc'}">none</c:if>">
  		<td align="center">审批人：</td>
  		<td colspan="3"><font color="blue"><b>&nbsp;${job.next_approver_name}</b></font></td>
  	</tr>
  	<tr style="display: <c:if test="${empty event.job_status or op=='noprc'}">none</c:if>">
  		<td align="center">流程记录：</td>
  		<td colspan="3">
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
    	<td align="center">流程信息： </td>
    	<td class="table_box_border_empty" colspan="3">
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
    <td colspan=4 align=center> 
		<input class="button_2003" type="button" value="返回" onClick="javascript:history.go(-1);">&nbsp;
    </td>
  </tr>
</table>
</body>
</html>
