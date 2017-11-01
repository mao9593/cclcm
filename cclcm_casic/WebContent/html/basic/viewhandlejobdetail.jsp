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
<script>
<!--
$(document).ready(function(){
	prepareBizApproval("${process.step_dept}","${process.step_role}","${process.step_dept_name}","${process.step_role_name}","${ctx}");
});
function viewEntityDetail(id){
	go("${ctx}/ledger/viewpaperledgerinfo.action?paper_id="+escape(id));
}
//-->
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
	<tr>
	    <td colspan="6" class="title_box">查看任务详情</td>
	</tr>
	<tr> 
    	<td width="10%" align="center">申请用户： </td>
    	<td width="23%"><font color="blue"><b>${job.user_name}</b></font></td>
    	<td width="10%" align="center">用户部门： </td>
    	<td width="23%"><font color="blue"><b>${job.dept_name}</b></font></td>
    	<td width="10%" align="center">任务流水号： </td>
    	<td width="23%"><font color="blue"><b>${job_code}</b></font></td>
	</tr>
	<tr> 
    	<td align="center">密级： </td>
    	<td><font color="blue"><b>${job.seclv_name}</b></font></td>
    	<td align="center">业务类型： </td>
    	<td><font color="blue"><b>${job.jobType.jobTypeName}</b></font></td>
    	<td align="center">状态：</td>
    	<td><font color="blue"><b>${job.job_status_name}</b></font></td>
	</tr>
	<c:choose>
		<c:when test="${job.jobType.jobTypeCode.contains('SEND')}">
			<tr> 
		    	<td align="center">接收单位： </td>		    	
		    	<td><font color="blue"><b>${job.output_dept_name}</b></font></td>
		    	<td align="center">接收人： </td>		    	
		    	<td><font color="blue"><b>${job.output_user_name}</b></font></td>
		    	<td>&nbsp;</td>	
		    	<td>&nbsp;</td>			    	
			</tr>
		</c:when>
	</c:choose>
  	<tr height="50">
  		<td align="center">审批人：</td>
  		<td colspan="5"><font color="blue"><b>&nbsp;${job.next_approver_name}</b></font></td>
  	</tr>
  	<tr>
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
  	<tr valign="middle" height="80"> 
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
    <td colspan="6" align="center"> 
		<input class="button_2003" type="button" value="返回" onClick="javascript:history.go(-1);">&nbsp;
    </td>
  </tr>
</table>
</br></br>
<table align="center" width="95%">
	<tr>
	    <td class="title_box">载体列表</td>
	</tr>
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
					<display:table uid="item" class="displaytable" name="paperList" pagesize="15" sort="list">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column title="申请人" property="user_name"/>
						<display:column title="部门" property="dept_name"/>
						<display:column title="文件名" property="file_title"/>
						<display:column title="密级" property="seclv_name"/>
						<display:column title="载体条码" property="paper_barcode"/>
						<display:column title="打印时间" property="print_time" />
						<display:column title="状态" property="paper_state_name"/>
						<display:column title="操作" style="width:50px">
							<input type="button" class="button_2003" value="查看" onclick="viewEntityDetail('${item.paper_id}');"/>
						</display:column>
					</display:table>
					</td>
				</tr>
			</table>
         </td>
	</tr>
</table>
</body>
</html>
