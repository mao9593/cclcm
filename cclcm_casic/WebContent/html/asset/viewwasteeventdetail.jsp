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
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
	<tr>
	
	   <c:if test="${event.event_type eq 'WASTE'}">
	     <td colspan="6" class="title_box">查看资产报废详情</td>
	   </c:if>
	   <c:if test="${event.event_type eq 'PROPERTYCHANGE'}">
	     <td colspan="6" class="title_box">查看资产变更详情</td>
	   </c:if>
	</tr>
	<tr>
		<td width="20%" align="center">申请用户： </td>
    	<td width="30%"><font color="blue"><b>&nbsp;${event.user_name}</b></font></td>
    	<td width="20%" align="center">用户部门： </td>
    	<td width="30%"><font color="blue"><b>&nbsp;${event.dept_name}</b></font></td>
    </tr>
	<tr> 
		<td align="center">密级：</td>
	    <td><font color="blue"><b>&nbsp;${event.seclv_name}</b></font></td>
	    <td align="center">业务类型：</td>
	    <td><font color="blue"><b>&nbsp;${event.event_type_str}</b></font></td>	
    </tr>
    <tr> 	
    	<td align="center">资产类别： </td>
    	<td><font color="blue"><b>&nbsp;${event.property_kind}</b></font></td>     	   	
    	<td align="center">设备名称： </td>
    	<td><font color="blue"><b>&nbsp;${event.property_name}</b></font></td>   	   	
    </tr>
    <tr> 	
    	<td align="center">当前责任人： </td>
    	<td><font color="blue"><b>&nbsp;${event.duty_user_name}</b></font></td>     	   	
    	<td align="center">当前责任部门： </td>
    	<td><font color="blue"><b>&nbsp;${event.duty_dept_name}</b></font></td>   	   	
    </tr>
    <c:if test="${event.event_type eq 'PROPERTYCHANGE'}">
    <tr> 	
    	<td align="center">变更后责任人： </td>
    	<td><font color="blue"><b>&nbsp;${event.user_name_after}</b></font></td>     	   	
    	<td align="center">变更后责任部门： </td>
    	<td><font color="blue"><b>&nbsp;${event.dept_name_after}</b></font></td>   	   	
    </tr>
    <tr> 	  	   	
    	<td align="center">资产存放单位： </td>
    	<td colspan="3"><font color="blue"><b>&nbsp;${event.property_place}</b></font></td>   	   	
    </tr>
	</c:if>
	<c:if test="${event.event_type eq 'WASTE'}">
    <tr> 	
    	<td align="center">折旧年限： </td>
    	<td><font color="blue"><b>&nbsp;${event.depre_life}</b></font></td>     	   	
    	<td align="center">使用年限： </td>
    	<td><font color="blue"><b>&nbsp;${event.userd_life}</b></font></td>   	   	
    </tr>
    <tr> 	  	   	
    	<td align="center">设备仪器现状及报废原因： </td>
    	<td colspan="3"><font color="blue"><b>&nbsp;${event.reason}</b></font></td>   	   	
    </tr>
	</c:if>
    <tr>	 
    	<td align="center">申请时间： </td>
    	<td colspan="3"><font color="blue"><b>&nbsp;${event.apply_time_str}</b></font></td> 		   	
  	</tr>
  	<tr>	   		   	
		<td align="center">备注：</td>
		<td colspan="3"><textarea rows="3" cols="80" readonly="readonly">&nbsp;${event.summ}</textarea></td>
  	</tr>
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
 	<tr>
    <td colspan="6" align="center">
		<input class="button_2003" type="button" value="返回" onClick="javascript:history.go(-1);">
    </td>
	</tr>
</table>
</body>
</html>
