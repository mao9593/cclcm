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
<form action="${ctx}/burn/downloadfile.action" target="hidden_frame" id="uploadForm" method="post">
	<input type="hidden" name="file_path" id="file_path"/>
	<input type="hidden" name="file_name" id="file_name"/>
</form>
<iframe height="0" width="0" frameborder="0" id="hidden_frame" name="hidden_frame" onload="getFrameReturn();"></iframe>
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
	<tr>
	    <td colspan="4" class="title_box">查看涉外交流作业详情</td>
	</tr>
	<tr>
    	<td width="15%" align="center">申请用户 </td>
    	<td width="35%"><font color="blue"><b>&nbsp;${event.user_name}</b></font></td>
    	<td align="center">申请用户部门 </td>
    	<td><font color="blue"><b>&nbsp;${event.dept_name}</b></font></td>
	</tr>
<%-- 	<tr>
    	<td align="center">作业密级</td>
	    <td><font color="blue"><b>&nbsp;${event.seclv_name}</b></font></td>
		<td align="center">用途 </td>
    	<td><font color="blue"><b>&nbsp;${event.usage_name}</b></font></td>
  	</tr> --%>
  	<tr>
    	<td align="center">来访单位</td>
	    <td><font color="blue"><b>&nbsp;${event.company_info}</b></font></td>  	
		<td align="center">提供资料</td>
	    <td><font color="blue"><b>&nbsp;${event.material}</b></font></td>
	</tr>
  	<tr>
    	<td align="center">抵达时间</td>
	    <td><font color="blue"><b>&nbsp;${event.go_time_str}</b></font></td>  	
    	<td align="center">离所时间</td>
	    <td><font color="blue"><b>&nbsp;${event.back_time_str}</b></font></td>  	
	</tr>	
	<tr>
    	<td align="center">接待人员</td>
	    <td><font color="blue"><b>&nbsp;${event.receptionist}</b></font></td>  	
    	<td align="center">业务交流范围</td>
	    <td><font color="blue"><b>&nbsp;${event.exchange_info}</b></font></td>  	
	</tr>	
	<tr>
    	<td align="center">接待地点</td>
	    <td><font color="blue"><b>&nbsp;${event.reception}</b></font></td>  	
    	<td align="center">拟参观地点</td>
	    <td><font color="blue"><b>&nbsp;${event.visite_place}</b></font></td>  	
	</tr>
	<tr>
    	<td align="center">申请时间 </td>
    	<td><font color="blue"><b>&nbsp;${event.apply_time_str}</b></font></td>
  		<td align="center">备注</td>
		<td><textarea rows="3" cols="40" readonly="readonly">&nbsp;${event.summ}</textarea></td>
	</tr>
	<tr>
		<td align="center">外宾基本信息</td>
		<td colspan="3" align="center">
			<table border rules=all  cellspacing=5 border=5 bodercolor=#ffaa00 border="0" cellspacing="0" cellpadding="0" align="left">
				<td width="15%" align="center">姓 名</td>
				<td width="10%" align="center">性 别</td>
				<td width="15%" align="center">国 籍</td>
				<td width="20%" align="center">职 务</td>
				<td width="20%" align="center">证件号码</td>
				<s:iterator value="#request.visitorList" var="visitor">
	  				<tr>
	  					<td align="center">${visitor.visitor_name}</td>
	  					<td align="center">${visitor.visitor_sex}</td>
	  					<td align="center">${visitor.nationality}</td>
	  					<td align="center">${visitor.post_info}</td>
	  					<td align="center">${visitor.certificate_code}</td>
	  				</tr>
				</s:iterator>
			</table>
		</td>
	</tr>	
  	<tr>
  		<td align="center">涉外交流附件</td>
  		<td colspan="3">
  			<table width="60%" border="0" cellspacing="0" cellpadding="0" align="left" >
				<tr><td align="center">文件名</td></tr>		
	  			<s:iterator value="#request.burnFileList" var="burnFile">
	  				<tr>
	  					<td align="center"><u><label style="color: blue;cursor: pointer;" onclick="downloadFile('${burnFile.file_path}','${burnFile.file_name}');">${burnFile.file_name}</label></u></td>	  		
	  				</tr>
				</s:iterator>
  			</table>
  		</td>
  	</tr>	
  	<tr height="50" style="display: <c:if test="${empty event.job_status or op=='noprc'}">none</c:if>">
  		<td align="center">审批人</td>
  		<td colspan="3"><font color="blue"><b>&nbsp;${job.next_approver_name}</b></font></td>
  	</tr>
  	<tr style="display: <c:if test="${empty event.job_status or op=='noprc'}">none</c:if>">
  		<td align="center">流程记录</td>
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
    	<td align="center">流程信息 </td>
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
