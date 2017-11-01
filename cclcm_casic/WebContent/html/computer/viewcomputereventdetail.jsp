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
<center>
<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="90%">
	 <tr>
		 <td colspan="6" class="title_box"> 
			 <c:if test="${event.event_type == 1}">
	            	查看新增计算机网络机申请
	         </c:if>
	   		<c:if test="${event.event_type == 2}">
	   			查看新增计算机单机申请
	   		</c:if>
	   		<c:if test="${event.event_type == 3}">
	   			查看计算机变更申请
	   		</c:if>
	   		<c:if test="${event.event_type == 4}">
	   			查看计算机报废申请
	   		</c:if>
        </td>
    </tr>
    <input type="hidden" name="user_iidd" id="user_iidd" value="${curUser.user_iidd}"/>
    <tr>
    	<td width="10%" align="center">申请用户 </td>
    	<td width="23%"><font color="blue"><b>${event.user_name}</b></font></td>
    	<td width="10%" align="center">用户部门 </td>
    	<td width="23%"><font color="blue"><b>${event.dept_name}</b></font></td>
    	<td width="10%" align="center">当前状态 </td>
    	<td width="20%"><font color="red"><b>&nbsp;${event.job_status_name}</b></font></td> 
    </tr>
    <c:if test="${event.event_type == 1}">
    <tr>
    	<td width="10%" align="center">申请人电话 </td>
    	<td width="23%"><font color="blue"><b>${event.user_phone}</b></font></td>
    	<td width="10%" align="center">计算机名称型号 </td>
    	<td width="23%"><font color="blue"><b>${computer.computer_name}</b></font></td>
    	<td align="center">作业密级 </td>
    	<td width="23%"><font color="blue"><b>${event.seclv_name}</td>
    	
    </tr>
    <tr>
    	<td width="10%" align="center">保密编号 </td>
    	<td width="23%"><font color="blue"><b>${computer.conf_code}</b></font></td>
    	<td width="10%" align="center">计算机密级 </td>
    	<td width="23%"><font color="blue"><b>${computer.seclv_name}</b></font></td>
    	<td width="10%" align="center">责任人 </td>
    	<td width="23%"><font color="blue"><b>${computer.duty_user_name}</b></font></td>
    	
    </tr>
    <tr>
    	<td width="10%" align="center">接入网络 </td>
    	<td width="23%"><font color="blue"><b>${computer.internet_type_name}</b></font></td>
    	<td width="10%" align="center">安装区域 </td>
    	<td width="23%"><font color="blue"><b>${computer.storage_area}</b></font></td>
    	<td width="10%" align="center">安装位置 </td>
    	<td width="23%"><font color="blue"><b>${computer.storage_location}</b></font></td>
    </tr>
    </c:if>
    
   <c:if test="${event.event_type == 2}">
   <tr>
    	<td width="10%" align="center">申请人电话 </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${event.user_phone}</b></font></td>
    	<td width="10%" align="center">计算机品牌型号 </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${computer.computer_name}</b></font></td>
    	<td width="10%" align="center">作业密级 </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${event.seclv_name}</b></font></td>

    </tr>
    <tr>
    	<td width="10%" align="center">保密编号 </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${computer.conf_code}</b></font></td>
    	<td width="10%" align="center">计算机密级 </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${computer.seclv_name}</b></font></td>
    	<td width="10%" align="center">责任人 </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${computer.duty_user_name}</b></font></td>
    	
    </tr>
     <tr>
    	<td width="10%" align="center">硬盘序列号 </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${computer.hdisk_no}</b></font></td>
    	<td width="10%" align="center">操作系统 </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${computer.computer_os}</b></font></td>
    	<td width="10%" align="center">安装时间 </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${computer.install_time}</b></font></td>
    	
    </tr>
    <tr>
    	<td width="10%" align="center">安装区域 </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${computer.storage_area}</b></font></td>
    	<td width="10%" align="center">安装位置 </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${computer.storage_location}</b></font></td>
    	<td>&nbsp;</td>
    	<td>&nbsp;</td>
    </tr>
    </c:if>
    <c:if test="${event.event_type == 3}">
   <tr>
    	<td width="10%" align="center">申请人电话 </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${event.user_phone}</b></font></td>
    	<td width="10%" align="center">计算机名称 </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${computer_old.computer_name}</b></font></td>
    	<td width="10%" align="center">作业密级 </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${event.seclv_name}</b></font></td>
    </tr>
    <tr>
    	<td width="10%" align="center">计算机配置 </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${computer_old.computer_detail}</b></font></td>
    	<td width="10%" align="center">输出端口号 </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${computer_old.output_point}</b></font></td>
    	<td width="10%" align="center">KEY号 </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${computer_old.key_code}</b></font></td>
    </tr>
    <tr>
    	<td width="10%" align="center">变更类型 </td>
    	<td width="10%" align="center" colspan="2">变更前</td>  	
    	<td width="10%" align="center" colspan="2">变更后</td>
    	<td>&nbsp;</td>
    	
    </tr>
    <c:if test="${computer.duty_dept_name != ''}">
	    <tr>
	    	<td width="10%" align="center">部门</td>
	    	<td width="20%" align="center" colspan="2"><font color="blue"><b>&nbsp;${computer_old.duty_dept_name}</b></font></td>
	    	<td width="20%" align="center" colspan="2"><font color="blue"><b>&nbsp;${computer.duty_dept_name}</b></font></td>
	    	<td>&nbsp;</td>
	    	
	    </tr>
    </c:if>
     <c:if test="${computer.duty_user_name != ''}">
    <tr>
    	<td width="10%" align="center">责任人</td>
    	<td width="20%" align="center" colspan="2"><font color="blue"><b>&nbsp;${computer_old.duty_user_name}</b></font></td>
    	<td width="20%" align="center" colspan="2"><font color="blue"><b>&nbsp;${computer.duty_user_name}</b></font></td>
    	<td>&nbsp;</td>
    </tr>
    </c:if>
     <c:if test="${computer.conf_code != ''}">
    <tr>
    	<td width="10%" align="center">保密编号</td>
    	<td width="20%" align="center" colspan="2"><font color="blue"><b>&nbsp;${computer_old.conf_code}</b></font></td>
    	<td width="20%" align="center" colspan="2"><font color="blue"><b>&nbsp;${computer.conf_code}</b></font></td>
    	<td>&nbsp;</td>
    </tr>
    </c:if>
     <c:if test="${computer.seclv_name != ''}">
     <tr>
    	<td width="10%" align="center">密级</td>
    	<td width="20%" align="center" colspan="2"><font color="blue"><b>&nbsp;${computer_old.seclv_name}</b></font></td>
    	<td width="20%" align="center" colspan="2"><font color="blue"><b>&nbsp;${computer.seclv_name}</b></font></td>
    	<td>&nbsp;</td>
    </tr>
    </c:if>
     <c:if test="${computer.computer_ip != ''}">
    <tr>
    	<td width="10%" align="center">IP地址</td>
    	<td width="20%" align="center" colspan="2"><font color="blue"><b>&nbsp;${computer_old.computer_ip}</b></font></td>
    	<td width="20%" align="center" colspan="2"><font color="blue"><b>&nbsp;${computer.computer_ip}</b></font></td>
    	<td>&nbsp;</td>
    </tr>
    </c:if>
     <c:if test="${computer.computer_mac != ''}">
    <tr>
    	<td width="10%" align="center">MAC地址</td>
    	<td width="20%" align="center" colspan="2"><font color="blue"><b>&nbsp;${computer_old.computer_mac}</b></font></td>
    	<td width="20%" align="center" colspan="2"><font color="blue"><b>&nbsp;${computer.computer_mac}</b></font></td>
    	<td>&nbsp;</td>
    </tr>
    </c:if>
     <c:if test="${computer.hdisk_no != ''}">
    <tr>
    	<td width="10%" align="center">硬盘序列号</td>
    	<td width="20%" align="center" colspan="2"><font color="blue"><b>&nbsp;${computer_old.hdisk_no}</b></font></td>
    	<td width="20%" align="center" colspan="2"><font color="blue"><b>&nbsp;${computer.hdisk_no}</b></font></td>
    	<td>&nbsp;</td>
    </tr>
    </c:if>
     <c:if test="${computer.storage_area != ''}">
    <tr>
    	<td width="10%" align="center">存放区域</td>
    	<td width="20%" align="center" colspan="2"><font color="blue"><b>&nbsp;${computer_old.storage_area}</b></font></td>
    	<td width="20%" align="center" colspan="2"><font color="blue"><b>&nbsp;${computer.storage_area}</b></font></td>
    	<td>&nbsp;</td>
    </tr>
    </c:if>
     <c:if test="${computer.storage_location != ''}">
     <tr>
    	<td width="10%" align="center">存放位置</td>
    	<td width="20%" align="center" colspan="2"><font color="blue"><b>&nbsp;${computer_old.storage_location}</b></font></td>
    	<td width="20%" align="center" colspan="2"><font color="blue"><b>&nbsp;${computer.storage_location}</b></font></td>
    	<td>&nbsp;</td>
    </tr>
    </c:if>
     <c:if test="${computer.summ != ''}">
    <tr>
    	<td width="10%" align="center">其他</td>
    	<td width="20%" align="center" colspan="2"><font color="blue"><b>&nbsp;</b></font></td>
    	<td width="20%" align="center" colspan="2"><font color="blue"><b>&nbsp;${computer.summ}</b></font></td>
    	<td>&nbsp;</td>
    </tr>
     </c:if>
    </c:if>
    <c:if test="${event.event_type == 4}">
    	<tr>
    		<td align="center">申请人电话</td>
			<td><font color="blue"><b>&nbsp;${event.user_phone}</td>
			<td width="10%" align="center">作业密级 </td>
    		<td width="20%"><font color="blue"><b>&nbsp;${event.seclv_name}</b></font></td>
    		<td>&nbsp;</td>
    		<td>&nbsp;</td>
    	</tr>
    	<tr>
		  	<td colspan="6" align="left">&nbsp;<font color="blue"><b>报废计算机基本信息</td>
		</tr>
		<tr>
    	<td align="center">计算机名称</td>
		<td>
			<font color="blue"><b>&nbsp;${computer.computer_name}
		</td>
		<td align="center">型号</td>
		<td>
			<font color="blue"><b>&nbsp;${computer.computer_model}
		</td>
		<td align="center">出厂编号</td>
		<td>
			<font color="blue"><b>&nbsp;${computer.computer_no}
		</td>
		
    </tr>  
    <tr>
    	<td align="center">设备配置</td>
		<td colspan="3">
			<font color="blue"><b>&nbsp;${computer.computer_detail}
		</td>
    	<td align="center">硬盘序列号</td>
		<td>
			<font color="blue"><b>&nbsp;${computer.hdisk_no}
		</td>
    </tr> 						
    
    <tr>
    	<td align="center">资产编号</td>
		<td>
			<font color="blue"><b>&nbsp;${computer.computer_code}
		</td>
		<td align="center">保密编号</td>
		<td>
			<font color="blue"><b>&nbsp;${computer.conf_code}
		</td>
		<td align="center">密级</td>
		<td>
			<font color="blue"><b>&nbsp;${computer.seclv_name}
		</td>
    </tr> 
    
    <tr>
    	<td align="center">责任人</td>
		<td>
			<font color="blue"><b>&nbsp;${computer.duty_user_name}
		</td>
		<td align="center">责任部门</td> 
		<td>
			<font color="blue"><b>&nbsp;${computer.duty_dept_name}
		</td>
		<td align="center">责任单位</td>
		<td>
			<font color="blue"><b>&nbsp;${computer.duty_entp_name}"
		</td>
		
    </tr> 
    
    <tr>
    	<td align="center">操作系统</td>
		<td>
			<font color="blue"><b>&nbsp;${computer.computer_os}
		</td>
		<td align="center">安装时间</td>
		<td>
			<font color="blue"><b>&nbsp;${computer.install_time}
		</td>
		<td align="center">KEY编号</td>
		<td>
			<font color="blue"><b>&nbsp;${computer.key_code}
		</td>
    </tr> 
    
    <tr>
    	<td align="center">IP</td>
		<td>
			<font color="blue"><b>&nbsp;${computer.computer_ip}
		</td>
		<td align="center">MAC</td>
		<td>
			<font color="blue"><b>&nbsp;${computer.computer_mac}
		</td>
		<td align="center">输出端口</td>
		<td>
			<font color="blue"><b>&nbsp;${computer.output_point}
		</td>
    </tr> 
    <tr>
		<td align="center">交换机</td>
		<td>
			<font color="blue"><b>&nbsp;${computer.switch_num}
		</td>
		<td align="center">端口号</td>
		<td>
			<font color="blue"><b>&nbsp;${computer.switch_point}
		</td>
		<td align="center">网络类型</td>
		<td>
			<font color="blue"><b>&nbsp;${computer.internet_type_name}
		</td>
    </tr> 
     <tr>
    	<td align="center">计算机状态</td>
		<td>
			<font color="blue"><b>&nbsp;${computer.computer_status_name}
		</td>
		<td align="center">存放区域</td>
		<td>
			<font color="blue"><b>&nbsp;${computer.storage_area}
		</td>
		<td align="center">存放位置</td>
		<td>
			<font color="blue"><b>&nbsp;${computer.storage_location}
		</td>
	</tr> 
     <tr>
		<td align="center">计算机通途</td>
		<td>
			<font color="blue"><b>&nbsp;${computer.computer_application}
		</td>
		<td align="center">启用时间</td>
		<td>
			<font color="blue"><b>&nbsp;${computer.enable_time}
		</td>
		<td align="center">备注</td>
		<td>
			<font color="blue"><b>&nbsp;${computer.summ}
		</td>
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
</center>
</body>
</html>
    