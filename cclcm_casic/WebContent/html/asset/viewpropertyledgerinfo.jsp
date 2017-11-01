<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>查看资产变动详情</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen"/>
	<link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
	<script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script>
	<!--
	$(document).ready(function(){
		if('${deviceEntity.job_status}' != ""){
			prepareBizApproval("${process.step_dept}","${process.step_role}","${process.step_dept_name}","${process.step_role_name}","${ctx}");
		}
	});
	//-->
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
	<tr>
	    <td colspan="6" class="title_box">查看资产详情</td>
	</tr>
	<tr>
    	<td width="13%" align="center">采购人：</td>
    	<td width="20%"><font color="blue"><b>&nbsp;${property.user_name}</b></font></td>
    	<td width="13%" align="center">采购人部门： </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${property.dept_name}</b></font></td>
    	<td width="13%" align="center">责任人： </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${property.duty_user_name}</b></font></td>
	</tr>
	<tr>
    	<td align="center">责任人部门：</td>
    	<td><font color="blue"><b>&nbsp;${property.duty_dept_name}</b></font></td>
    	<td align="center">资产密级： </td>
    	<td><font color="blue"><b>&nbsp;${property.seclv_name}</b></font></td>
    	<td align="center">资产种类： </td>
    	<td><font color="blue"><b>&nbsp;${property.property_kind}</b></font></td>   	
	</tr>
	<tr>	
    	<td align="center">资产名称： </td>
    	<td><font color="blue"><b>&nbsp;${property.property_name}</b></font></td>
    	<td align="center">资产条码：</td>
    	<td><font color="blue"><b>&nbsp;${property.property_barcode}</b></font></td>
    	<td align="center">资产状态： </td>
    	<td><font color="blue"><b>&nbsp;${property.property_status_str}</b></font></td>
	</tr>
	<tr>
    	<td align="center">单价： </td>
    	<td><font color="blue"><b>&nbsp;${property.unit_price}</b></font></td>
		<td align="center">规格型号： </td>
    	<td><font color="blue"><b>&nbsp;${property.property_standard}</b></font></td>
    	<td align="center">供应厂商： </td>
    	<td><font color="blue"><b>&nbsp;${property.supplier}</b></font></td>
	</tr>
	<tr>
    	<td align="center">入库时间： </td>
    	<td><font color="blue"><b>&nbsp;${property.in_time}</b></font></td> 
    	<td align="center">出库时间： </td>
    	<td><font color="blue"><b>&nbsp;${property.out_time}</b></font></td>
    	<td align="center">报废时间： </td>
    	<td><font color="blue"><b>&nbsp;${property.waste_time}</b></font></td>
  	</tr>
	
	<tr height="50" style="display: <c:if test="${empty property.job_status or op!='hasprc'}">none</c:if>">
  		<td align="center">审批人：</td>
  		<td colspan="5"><font color="blue"><b>&nbsp;${job.next_approver_name}</b></font></td>
  	</tr>
  	<tr style="display: <c:if test="${empty property.job_status or op!='hasprc'}">none</c:if>">
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
  	<tr valign="middle" height="80" style="display: <c:if test="${empty property.job_status or op!='hasprc'}">none</c:if>"> 
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