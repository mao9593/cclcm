<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>查看申请详情</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
<script  language="JavaScript" >
$(document).ready(function(){
	if('${event.job_status}' != ""){
		prepareBizApproval("${process.step_dept}","${process.step_role}","${process.step_dept_name}","${process.step_role_name}","${ctx}");
	}
});
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="90%">
	 <tr>
		 <td colspan="6" class="title_box"> 
			 <c:if test="${event.event_type == 5}">
	            	查看计算机维修申请
	         </c:if>
	   		<c:if test="${event.event_type == 6}">
	   			查看计算机重装系统申请
	   		</c:if>
	   		<c:if test="${event.event_type == 7}">
	   			查看计算机退网申请
	   		</c:if>
	   		<c:if test="${event.event_type == 8}">
	   			查看USB-KEY申请/更新申请
	   		</c:if>
	   		<c:if test="${event.event_type == 9}">
	   			查看开通计算机端口申请
	   		</c:if>
	   		<c:if test="${event.event_type == 10}">
	   			查看保留本地打印机（独立模式）申请
	   		</c:if>
	   	</td>
    </tr>
    <tr>
    	<td width="10%" align="center">申请用户 </td>
    	<td width="23%"><font color="blue"><b>${event.user_name}</b></font></td>
    	<td width="10%" align="center">用户部门 </td>
    	<td width="23%"><font color="blue"><b>${event.dept_name}</b></font></td>
    	<td width="10%" align="center">当前状态 </td>
    	<td width="20%"><font color="red"><b>&nbsp;${event.job_status_name}</b></font></td> 
    </tr>
    <tr>
    	<td width="10%" align="center">申请人电话 </td>
    	<td width="23%"><font color="blue"><b>${event.user_phone}</b></font></td>
    	<td align="center">申请时间</td>
		<td width="20%"><font color="blue"><b>&nbsp;${event.apply_time_str}</b></font></td>
		<td>&nbsp;</td>
    	<td>&nbsp;</td>
	</tr>
	
	<tr>
	    	<td align="center">计算机名称</td>
	    	<td width="23%"><font color="blue"><b>${computer.computer_name}</b></font></td>
	    	<td align="center">责任人</td>
	    	<td width="23%"><font color="blue"><b>${computer.duty_user_name}</b></font></td>
			<td align="center">责任部门</td>
			<td width="23%"><font color="blue"><b>${computer.duty_dept_name}</b></font></td>
	    </tr>
	    <tr>
	    	<td align="center">资产编号</td>
			<td width="20%"><font color="blue"><b>&nbsp;${computer.computer_code}</b></font></td>
	    	<td align="center">保密编号</td>
			<td width="20%"><font color="blue"><b>&nbsp;${computer.conf_code}</b></font></td>
			<td align="center">密级</td>
			<td width="20%"><font color="blue"><b>&nbsp;${computer.seclv_name}</b></font></td>
	    </tr>
	<c:if test="${event.event_type == 6}">
		<tr>
			<td align="center">操作系统</td>
			<td width="20%"><font color="blue"><b>&nbsp;${computer.computer_os}</b></font></td>
	    	<td align="center">硬盘序列号</td>
			<td width="20%"><font color="blue"><b>&nbsp;${computer.hdisk_no}</b></font></td>
			<td>&nbsp;</td>
    		<td>&nbsp;</td>
		</tr>
    </c:if>
    <c:if test="${event.event_type == 7}">
    	 <tr>
	    	<td align="center">IP地址</td>
			<td width="20%"><font color="blue"><b>&nbsp;${computer.computer_ip}</b></font></td>
	    	<td align="center">MAC地址</td>
			<td width="20%"><font color="blue"><b>&nbsp;${computer.computer_mac}</b></font></td>
			<td align="center">联网情况</td>
			<td width="20%"><font color="blue"><b>&nbsp;${computer.internet_type_name}</b></font></td>
	    </tr>
	    <tr>
			<td align="center">安装区域</td>
			<td width="20%"><font color="blue"><b>&nbsp;${computer.storage_area}</b></font></td>
			<td align="center">安装位置</td>
			<td width="20%"><font color="blue"><b>&nbsp;${computer.storage_location}</b></font></td>
			<td>&nbsp;</td>
    		<td>&nbsp;</td>
    	</tr>
    </c:if>
    <c:if test="${event.event_type == 8}">
     <tr>
	    	<td align="center">IP地址</td>
			<td width="20%"><font color="blue"><b>&nbsp;${computer.computer_ip}</b></font></td>
	    	<td align="center">KEY编号</td>
			<td width="20%"><font color="blue"><b>&nbsp;${computer.key_code}</b></font></td>
			<td align="center">联网情况</td>
			<td width="20%"><font color="blue"><b>&nbsp;${computer.internet_type_name}</b></font></td>
	    </tr>
	    <tr>
	    	<td align="center">申请内容</td>
			<td width="20%"><font color="blue"><b>&nbsp;${apply_type_name}</b></font></td>
	    	<td align="center">权限类别</td>
	    	<td width="20%"><font color="blue"><b>&nbsp;${power_type_name}</b></font></td>
	    	<td>&nbsp;</td>
    		<td>&nbsp;</td>
	    </tr>
    </c:if>
    <c:if test="${event.event_type == 9}">
    	<tr>
    		<td align="center">开通输出端口</td>
			<td width="20%"><font color="blue"><b>&nbsp;${output_port}</b></font></td>
			<td align="center">开通时间</td>
			<td width="20%" colspan="3"><font color="blue"><b>&nbsp;${start_time}至${end_time}</b></font></td>
		</tr>
		<tr>
			<td align="center">开通输入端口</td>
			<td width="20%"><font color="blue"><b>&nbsp;${input_port}</b></font></td>
			<td>&nbsp;</td>
    		<td>&nbsp;</td>
    		<td>&nbsp;</td>
    		<td>&nbsp;</td>
		</tr>
    </c:if>
    <c:if test="${event.event_type == 10}">
    	<tr><td colspan="6" align="center">打印机基本信息</td></tr>
	     <tr>
	    	<td align="center">打印机型号</td>
			<td width="20%"><font color="blue"><b>&nbsp;${printer_model}</b></font></td>
	    	<td align="center">保密编号</td>
	    	<td width="20%"><font color="blue"><b>&nbsp;${printer_conf_code}</b></font></td>
			<td align="center">密级</td>
			<td width="20%"><font color="blue"><b>&nbsp;${printer_seclv_name}</b></font></td>
	    </tr>
    </c:if>
    <tr>
		<td align="center">申请原因</td>
		<td colspan="5"><font color="blue"><b>
			${event.event_reason}&nbsp;
		</td>	
	</tr>
    <tr>
		<td align="center">备注</td>
		<td colspan="5"><font color="blue"><b>
			${event.summ}&nbsp;
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
	    <td colspan="6" align="center"> 
	      <input class="button_2003" type="button" value="返回" onclick="javascript:history.go(-1)">&nbsp;
	    </td>
	</tr>
</table>
</body>
</html>