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
	prepareBizApproval("${process.step_dept}","${process.step_role}","${process.step_dept_name}","${process.step_role_name}","${ctx}");
});

function chkCancel(event_code){
	if(confirm("确定要撤销该申请？")){
		var type="${type}";
		var url = "${ctx}/ledger/cancelhandletemp.action?type="+type+"&event_code="+escape(event_code);
		callServer(url);
	}
}
function updateResult(){
	if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
		alert("取消成功");
		go(window.location);
	}
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
    	<td width="23%"><font color="blue"><b>&nbsp;${job.user_name}</b></font></td>
    	<td width="10%" align="center">用户部门： </td>
    	<td width="23%"><font color="blue"><b>&nbsp;${job.dept_name}</b></font></td>
    	<td width="10%" align="center">业务类型：</td>
    	<td width="23%"><font color="blue"><b>&nbsp;${job.jobType.jobTypeName}</b></font></td>
	</tr>
	<tr>
		<td align="center">申请状态： </td>
    	<td><font color="red"><b>&nbsp;${job.job_status_name}</b></font></td> 
    	<td align="center">业务密级： </td>
    	<td><font color="blue"><b>&nbsp;${job.seclv_name}</b></font></td>
    	<td align="center">具体说明： </td>
	   	<td ><textarea name="comment" rows="2" cols="30">&nbsp;${job.comment}</textarea></td>
	</tr>
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
	    <td class="title_box">批量提交的作业列表</td>
	</tr>
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
					<display:table uid="item" class="displaytable" name="eventList" sort="list">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column title="类型" property="entity_type_name" maxLength="30"/>
						<display:column title="载体条码" property="barcode" maxLength="30"/>
						<display:column title="文件名称" property="file_name" maxLength="30"/>
						<display:column title="作业密级" property="seclv_name"/>
						<display:column title="归属部门" property="scope_dept_name"/>
						<display:column title="用途" property="usage_name"/>
						<display:column title="申请时间" property="apply_time_str"/>
						<display:column title="申请状态" property="job_status_name"/>
						<display:column title="操作" style="width:100px">
						<c:choose>
							<c:when test="${item.entity_type == 'paper'}">
								<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/ledger/viewcycledetail.action?type=PAPER&barcode=${item.barcode}&ledger_type=dept');"/>
							</c:when>
							<c:otherwise>
								<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/ledger/viewcycledetail.action?type=DISC&barcode=${item.barcode}&ledger_type=dept');"/>
							</c:otherwise>
						</c:choose>
							<c:choose>
								<c:when test="${item.job_status != true && item.job_status != false}">
									<input type="button" class="button_2003" value="撤销" onclick="chkCancel('${item.event_code}')"/>
								</c:when>
								<c:otherwise>
									<input type="button" class="button_2003" value="撤销" disabled="disabled"/>
								</c:otherwise>
							</c:choose>
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
