<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>查看载体生命周期详细信息</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen"/>
	<link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
	<script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script>
	$(document).ready(function(){
		//if('${event.job_status}' != ""){
			prepareBizApproval("${process.step_dept}","${process.step_role}","${process.step_dept_name}","${process.step_role_name}","${ctx}");
		//}
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
<center>
<c:if test="${not empty recordList}">
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
	<tr>
	    <td colspan="6" class="title_box">载体流程信息</td>
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
</table>
</c:if>
<br/>
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
	<tr>
	    <td colspan="6" class="title_box">载体属性信息</td>
	</tr>
	<tr>  	
    	<td width="13%" align="center">光盘条码： </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${cd.cd_barcode}</b></font></td>
    	<td width="13%" align="center">光盘类型： </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${cd.cd_type}</b></font></td>
    	<td width="13%" align="center">当前状态： </td>
    	<td width="20%"><font color="red"><b>&nbsp;${cd.cd_state_name}</b></font></td>
	</tr>
	<tr>
		<td align="center">卷标名称：</td>
	    <td><font color="blue"><b>&nbsp;${cd.cd_volume}</b></font></td>
		<td align="center">文件列表： </td>
    	<td><font color="blue"><b>&nbsp;${cd.file_list}</b></font></td>
    	<td align="center">文件数量： </td>
    	<td><font color="blue"><b>&nbsp;${cd.file_num}</b></font></td>		
	</tr>
  	<tr> 
    	<td align="center">光盘密级： </td>
    	<td><font color="blue"><b>&nbsp;${cd.seclv_name}</b></font></td>
		<td align="center">责任人： </td>
    	<td><font color="blue"><b>&nbsp;${cd.duty_user_name}</b></font></td>
    	<td align="center">责任人部门： </td>
    	<td><font color="blue"><b>&nbsp;${cd.duty_dept_name}</b></font></td>
	</tr>
	<tr>
		<td align="center">制作人： </td>
    	<td><font color="blue"><b>&nbsp;${cd.user_name}</b></font></td>
    	<td align="center">制作人部门： </td>
    	<td><font color="blue"><b>&nbsp;${cd.dept_name}</b></font></td> 
    	<td align="center">数据类型： </td>
    	<td><font color="blue"><b>&nbsp;${cd.data_type_name}</b></font></td>
  	</tr>
  	<tr> 
		<td align="center">刻录时间： </td>
    	<td><font color="blue"><b>&nbsp;${cd.burn_time}</b></font></td>
    	<td align="center">刻录类型： </td>
    	<td><font color="blue"><b>&nbsp;${cd.burn_type_name}</b></font></td>
    	<td align="center">刻录结果： </td>
    	<td><font color="blue"><b>&nbsp;${cd.burn_result_name}</b></font></td>	    
	</tr>
	<tr> 
	    <td align="center">刻录机器：</td>
	    <td><font color="blue"><b>&nbsp;${cd.burn_machine}</b></font></td>
	    <td align="center">刻录IP： </td>
    	<td><font color="blue"><b>&nbsp;${cd.burn_ipaddress}</b></font></td>	    
    	<td align="center">是否补刻： </td>
    	<td><font color="blue"><b>&nbsp;${cd.is_reburn_name}</b></font></td>	    
	</tr>
	<tr> 
    	<td align="center">载体归属： </td>
    	<td><font color="blue"><b>&nbsp;${cd.scope_name}</b></font></td>
		<td align="center">归属部门： </td>
    	<td><font color="blue"><b>&nbsp;${cd.scope_dept_name}</b></font></td>
		<td align="center">保密编号： </td>
    	<td><font color="blue"><b>&nbsp;${cd.conf_code}</b></font></td>
    </tr>
	<tr>	
		<td align="center">外发接收人： </td>
    	<td><font color="blue"><b>&nbsp;${cd.output_user_name}</b></font></td>
    	<td align="center">外发接收部门： </td>
    	<td><font color="blue"><b>&nbsp;${cd.output_dept_name}</b></font></td>
    	<td align="center">备注： </td>
    	<td><font color="blue"><b>&nbsp;${cd.fail_remark}</b></font></td>
  	</tr>
	<tr> 
		<td align="center">到期时间： </td>
    	<td><font color="blue"><b>&nbsp;${cd.expire_time}</b></font></td>
    	<td align="center">到期状态： </td>
    	<td><font color="blue"><b>&nbsp;${cd.expire_status_name}</b></font></td>
    	<td align="center">闭环操作人： </td>
    	<td><font color="blue"><b>&nbsp;${cd.retrieve_user_name}</b></font></td>
    </tr>
	<tr>	
    	<td align="center">闭环时间： </td>
    	<td><font color="blue"><b>&nbsp;${cd.retrieve_time}</b></font></td>
    	<td align="center">销毁人： </td>
    	<td><font color="blue"><b>&nbsp;${cd.destroy_user_name}</b></font></td>
    	<td align="center">销毁时间： </td>
    	<td><font color="blue"><b>&nbsp;${cd.destroy_time}</b></font></td>
	</tr>
	<tr>	
    	<td align="center">外发回执单号： </td>
    	<td><font color="blue"><b>&nbsp;${cd.send_num}</b></font></td>
    	<td>&nbsp;</td>
    	<td>&nbsp;</td>
    	<td>&nbsp;</td>
    	<td>&nbsp;</td>
	</tr>
<%-- 	<c:if test="${cd.create_type == 'BURN'}">
		<tr>
  		<td align="center">刻录文件列表：</td>
  		<td colspan="5">
  			<table width="60%" border="0" cellspacing="0" cellpadding="0" align="left" >
				<tr><td align="center">文件名</td><td align="center">密级</td></tr>		
	  			<s:iterator value="#request.burnFileList" var="burnFile">
	  				<c:choose>
					<c:when test="${ledger_type == 'personal'}">
						<tr>
	  						<td align="center"><a href="#" onclick="downloadFile('${burnFile.file_path}','${burnFile.file_name}');">${burnFile.file_name}</a></td>
	  						<td align="center">${burnFile.seclv_name}</td>
	  					</tr>
					</c:when>
					<c:when test="${ledger_type == 'total' or ledger_type == 'dept' or ledger_type == 'file'}">				
						<tr>
  							<td align="center"><c:if test="${is_cd_audit}"><a href="#" onclick="downloadFile('${burnFile.file_path}','${burnFile.file_name}');"></c:if>${burnFile.file_name}</a></td>
  							<td align="center">${burnFile.seclv_name}</td>
  						</tr>	
					</c:when>
		</c:choose>
				</s:iterator>
  			</table>
  		</td>
  		</tr>
	</c:if> --%>
</table>
<c:if test="${not empty itemList}">
<br/>
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
	<tr>
	    <td colspan="5" class="title_box">载体全生命周期信息</td>
	</tr>
	<tr>
    	<td width="20%" align="center">载体条码 </td>
    	<td width="20%" align="center">操作人</td>
    	<td width="20%" align="center">部门</td>
    	<td width="20%" align="center">操作时间 </td>
    	<td width="20%" align="center">操作 </td>
	</tr>
	<s:iterator value="#request.itemList" var="item">
		<tr>
	    	<td align="center">${item.barcode}</td>
	    	<td align="center">${item.user_name}</td>
	    	<td align="center">${item.dept_name}</td>
	    	<td align="center">${item.oper_time_str}</td>
	    	<td align="center">${item.oper.name}</td>
		</tr>	
	</s:iterator>
</table>
</c:if>
<c:if test="${not empty rejectRecordList}">
<br/>
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
	<tr>
	    <td colspan="5" class="title_box">载体拒收记录</td>
	</tr>
	<tr>
    	<td width="20%" align="center">载体条码 </td>
    	<td width="20%" align="center">拒收用户姓名</td>
    	<td width="20%" align="center">拒收用户部门</td>
    	<td width="15%" align="center">拒收时间 </td>
    	<td width="25%" align="center">备注 </td>
	</tr>
	<s:iterator value="#request.rejectRecordList" var="item">
		<tr>
	    	<td align="center">${item.entity_barcode}</td>
	    	<td align="center">${item.reject_user_name}</td>
	    	<td align="center">${item.reject_dept_name}</td>
	    	<td align="center">${item.reject_time_str}</td>
	    	<td align="center">${item.comment}</td>
		</tr>	
	</s:iterator>
</table>
</c:if>
<c:if test="${not empty burnEventList}">
<br/>
<table width="95%" cellspacing="0" cellpadding="0" align="center">
	<tr>
	    <td colspan="5" class="title_box">刻录申请列表</td>
	</tr>
 	<tr>
   	<td>
		<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
			 <tr>
	   			<td>
					<display:table uid="item" class="displaytable" name="burnEventList" sort="list">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column title="申请人" property="user_name" maxLength="30"/>
						<display:column title="申请部门" property="dept_name"/>
						<display:column title="文件列表" property="file_list"/>
						<display:column title="作业密级" property="seclv_name"/>
						<display:column title="申请时间" property="apply_time_str"   sortable="true"/>
						<display:column title="操作" style="width:150px">
							<div>
								<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/burn/viewnasburneventdetail.action?event_code=${item.event_code}');"/>
							</div>
						</display:column>
					</display:table>
				</td>
			</tr>
		</table>
	</td>
	</tr>
</table>
</c:if>
<br/>
<table>
  	<tr>
	    <td colspan="5" align="center"> 
			<input class="button_2003" type="button" value="返回" onClick="javascript:history.go(-1);">&nbsp;
	    </td>
  	</tr>
</table>
</center>
</body>
</html>