<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>查看光盘信息详情</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen"/>
	<link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
	<script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<table width="80%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
	<tr>
	    <td colspan="6" class="title_box">查看光盘信息详情</td>
	</tr>
	<tr>  	
    	<td width="13%" align="center">光盘条码： </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${cdEntity.cd_barcode}</b></font></td>
    	<td width="13%" align="center">光盘类型： </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${cdEntity.cd_type}</b></font></td>
    	<td width="13%" align="center">当前状态： </td>
    	<td width="20%"><font color="red"><b>&nbsp;${cdEntity.cd_state_name}</b></font></td>
	</tr>
	<tr>
		<td align="center">卷标名称：</td>
	    <td><font color="blue"><b>&nbsp;${cdEntity.cd_volume}</b></font></td>
		<td align="center">文件列表： </td>
    	<td><font color="blue"><b>&nbsp;${cdEntity.file_list}</b></font></td>
    	<td align="center">文件数量： </td>
    	<td><font color="blue"><b>&nbsp;${cdEntity.file_num}</b></font></td>		
	</tr>
  	<tr> 
    	<td align="center">光盘密级： </td>
    	<td><font color="blue"><b>&nbsp;${cdEntity.seclv_name}</b></font></td>
		<td align="center">责任人： </td>
    	<td><font color="blue"><b>&nbsp;${cdEntity.duty_user_name}</b></font></td>
    	<td align="center">责任人部门： </td>
    	<td><font color="blue"><b>&nbsp;${cdEntity.duty_dept_name}</b></font></td>
	</tr>
	<tr>
		<td align="center">制作人： </td>
    	<td><font color="blue"><b>&nbsp;${cdEntity.user_name}</b></font></td>
    	<td align="center">制作人部门： </td>
    	<td><font color="blue"><b>&nbsp;${cdEntity.dept_name}</b></font></td> 
    	<td align="center">数据类型： </td>
    	<td><font color="blue"><b>&nbsp;${cdEntity.data_type_name}</b></font></td>
  	</tr>
  	<tr> 
		<td align="center">刻录时间： </td>
    	<td><font color="blue"><b>&nbsp;${cdEntity.burn_time}</b></font></td>
    	<td align="center">刻录类型： </td>
    	<td><font color="blue"><b>&nbsp;${cdEntity.burn_type_name}</b></font></td>
    	<td align="center">刻录结果： </td>
    	<td><font color="blue"><b>&nbsp;${cdEntity.burn_result_name}</b></font></td>	    
	</tr>
	<tr> 
	    <td align="center">刻录机器：</td>
	    <td><font color="blue"><b>&nbsp;${cdEntity.burn_machine}</b></font></td>
	    <td align="center">刻录IP： </td>
    	<td><font color="blue"><b>&nbsp;${cdEntity.burn_ipaddress}</b></font></td>	    
    	<td align="center">是否补刻： </td>
    	<td><font color="blue"><b>&nbsp;${cdEntity.is_reburn_name}</b></font></td>	    
	</tr>
	<tr> 
    	<td align="center">载体归属： </td>
    	<td><font color="blue"><b>&nbsp;${cdEntity.scope_name}</b></font></td>
		<td align="center">归属部门： </td>
    	<td><font color="blue"><b>&nbsp;${cdEntity.scope_dept_name}</b></font></td>
    	<td align="center">保密编号： </td>
    	<td><font color="blue"><b>&nbsp;${cdEntity.conf_code}</b></font></td>
    </tr>
	<tr>	
		<td align="center">外发接收人： </td>
    	<td><font color="blue"><b>&nbsp;${cdEntity.output_user_name}</b></font></td>
    	<td align="center">外发接收部门： </td>
    	<td><font color="blue"><b>&nbsp;${cdEntity.output_dept_name}</b></font></td>
    	<td align="center">备注： </td>
    	<td><font color="blue"><b>&nbsp;${cdEntity.fail_remark}</b></font></td>
  	</tr>
  	<c:choose>
		<c:when test="${cdEntity.job_code.contains('SEND')}">
		    <tr height="50" style="display: <c:if test="${empty cdEntity.job_status or op!='hasprc'}">none</c:if>">  
		        <td align="center">交接单委托打印人： </td>
		        <td colspan="5"><font color="blue"><b>&nbsp;${supervise_user_name}</b></font></td>
		    </tr>
		</c:when>
	</c:choose>
	<tr> 
		<td align="center">到期时间： </td>
    	<td><font color="blue"><b>&nbsp;${cdEntity.expire_time}</b></font></td>
    	<td align="center">到期状态： </td>
    	<td><font color="blue"><b>&nbsp;${cdEntity.expire_status_name}</b></font></td>
    	<td align="center">闭环操作人： </td>
    	<td><font color="blue"><b>&nbsp;${cdEntity.retrieve_user_name}</b></font></td>
	</tr>
	<tr>	
    	<td align="center">闭环时间： </td>
    	<td><font color="blue"><b>&nbsp;${cdEntity.retrieve_time}</b></font></td>
    	<td align="center">销毁人： </td>
    	<td><font color="blue"><b>&nbsp;${cdEntity.destroy_user_name}</b></font></td>
    	<td align="center">销毁时间： </td>
    	<td><font color="blue"><b>&nbsp;${cdEntity.destroy_time}</b></font></td>
	</tr>
  	<c:choose>
		<c:when test="${cdEntity.job_code.contains('DESTROY_CD_BYSELF')}">
			<tr height="50" style="display: <c:if test="${empty cdEntity.job_status or op!='hasprc'}">none</c:if>">
  		       <td align="center">监销人：</td>
  		       <td colspan="5"><font color="blue"><b>&nbsp;${supervise_user_name}</b></font></td>
  	        </tr>
		</c:when>
	</c:choose>
	<td align="center">外发承办人： </td>
    	<td><font color="blue"><b>&nbsp;${cdEntity.output_undertaker_name}</b></font></td>
    	<td align="center">外发(带)方式： </td>
    	<td><font color="blue"><b>&nbsp;${cdEntity.send_way_str}</b></font></td>
    	<td align="center">携带人： </td>
    	<td><font color="blue"><b>&nbsp;${cdEntity.carryout_user_names}</b></font></td>
	<tr>
		<td align="center">外发(带)机要号： </td>
    	<td colspan="5"><font color="blue"><b>&nbsp;${cdEntity.output_confidential_num}</b></font></td>
	</tr>
	<tr height="50" style="display: <c:if test="${empty cdEntity.job_status or op!='hasprc'}">none</c:if>">
  		<td align="center">审批人：</td>
  		<td colspan="5"><font color="blue"><b>&nbsp;${job.next_approver_name}</b></font></td>
  	</tr>
  	<tr style="display: <c:if test="${empty cdEntity.job_status or op!='hasprc'}">none</c:if>">
  		<td align="center">流程记录：</td>
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
  	<tr valign="middle" height="80" style="display: <c:if test="${empty cdEntity.job_status or op!='hasprc'}">none</c:if>"> 
    	<td align="center">流程信息： </td>
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
    	<td colspan=6 align=center> 
		<input class="button_2003" type="button" value="返回" onClick="javascript:history.go(-1);">&nbsp;
    	</td>
  </tr>
</table>
</center>
</body>
</html>