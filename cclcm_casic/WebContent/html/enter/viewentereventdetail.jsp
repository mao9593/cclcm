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
<form action="${ctx}/burn/downloadfile.action" target="hidden_frame" id="uploadForm" method="post">
	<input type="hidden" name="file_path" id="file_path"/>
	<input type="hidden" name="file_name" id="file_name"/>
</form>
<iframe height="0" width="0" frameborder="0" id="hidden_frame" name="hidden_frame" onload="getFrameReturn();"></iframe>
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
	<tr>
	    <td colspan="6" class="title_box">查看录入作业详情</td>
	</tr>
	<tr>
		<td width="15%" align="center">类型： </td>
    	<td width="35%"><font color="blue"><b>&nbsp;${event.file_type_name}</b></font></td>  
    	<td width="15%" align="center">当前状态： </td>
    	<td width="35%"><font color="red"><b>&nbsp;${event.job_status_name}</b></font></td>
	</tr>
	<tr>
		<td align="center">申请用户： </td>
    	<td><font color="blue"><b>&nbsp;${event.user_name}</b></font></td>
    	<td align="center">用户部门： </td>
    	<td><font color="blue"><b>&nbsp;${event.dept_name}</b></font></td>
    </tr>
	<tr> 
    	<td align="center">载体归属：</td>
    	<td><font color="blue"><b>&nbsp;${event.scope_name}</b></font></td>
    	<td align="center">归属部门：</td>
    	<td><font color="blue"><b>&nbsp;${event.scope_dept_name}</b></font></td>
	</tr>
	<tr> 
		<td align="center">密级：</td>
	    <td><font color="blue"><b>&nbsp;${event.seclv_name}</b></font></td>
	    <td align="center">用途：</td>
	    <td><font color="blue"><b>&nbsp;${event.usage_name}</b></font></td>	
    </tr>
    <tr> 	    	   	
    	<td align="center">介质编号： </td>
    	<td><font color="blue"><b>&nbsp;${event.medium_serial}</b></font></td>  
    	<td align="center">&nbsp;来源： </td>
    	<td><font color="blue"><b>&nbsp;${event.source}</b></font></td> 	   	   	
    </tr>
    <tr>	
	   	<td align="center">使用期限： </td>
    	<td><font color="blue"><b>&nbsp;${event.period_name}</b></font></td>
    	<td align="center">文件说明： </td>
    	<td><font color="blue"><b>&nbsp;${event.file_kind_name}</b></font></td>
    </tr>
    <c:choose>
		<c:when test="${event.file_type==1 || event.file_type==3}">
    	<tr>
    		<td align="center">文件份数： </td>
    		<td><font color="blue"><b>&nbsp;${event.enter_num}</b></font></td>	
		    <td align="center">文件页数： </td>
		    <td><font color="blue"><b>&nbsp;${event.page_num}</b></font></td>  
   		</tr>
    	</c:when>
	</c:choose> 
    <tr>
    	<c:choose>
			<c:when test="${time_flag == 'USETIME'}">
				<td align="center">启用时间： </td>
			</c:when>
			<c:otherwise>
				<td align="center">申请时间： </td>
			</c:otherwise>
		</c:choose>
    	<td><font color="blue"><b>&nbsp;${event.apply_time_str}</b></font></td> 	
    	<td align="center">录入状态： </td>
		<td><font color="blue"><b>&nbsp;${event.import_status_name}</b></font></td>  
    </tr>
    <c:choose>
		<c:when test="${event.import_status == 1}">
    	<tr>
    		<td align="center">录入人： </td>
    		<td><font color="blue"><b>&nbsp;${event.import_user_name}</b></font></td>	
		    <td align="center">录入时间： </td>
		    <td><font color="blue"><b>&nbsp;${event.finish_time}</b></font></td>  
   		</tr>
    	</c:when>
	</c:choose>
	<c:choose>
		<c:when test="${event.file_type==1 || event.file_type==3}">
    	<tr>
    	    <td align="center">原文件编号： </td>
    	    <td><font color="blue"><b>&nbsp;${event.src_code}</b></font></td> 	
    	    <td align="center">机要号：</td>
    	    <td><font color="blue"><b>&nbsp;${event.confidential_num}</b></font></td> 	
        </tr> 
    	</c:when>
	</c:choose> 
	<c:choose>
		<c:when test="${event.file_type==2}">
    	<tr>
    	    <td align="center">原光盘编号： </td>
    	    <td><font color="blue"><b>&nbsp;${event.src_code}</b></font></td> 	
    	    <td align="center">机要号：</td>
    	    <td><font color="blue"><b>&nbsp;${event.confidential_num}</b></font></td>  
        </tr> 
    	</c:when>
	</c:choose>
	<c:choose>
		<c:when test="${event.file_type==4}">
    	<tr>
    	    <td align="center">原磁介质编号： </td>
    	    <td><font color="blue"><b>&nbsp;${event.src_code}</b></font></td> 	
    	    <td align="center">&nbsp;</td>
		    <td>&nbsp;</td>  
        </tr> 
    	</c:when>
	</c:choose>
	<c:choose>
		<c:when test="${event.file_type==1 || event.file_type==3 || event.file_type==2}">
        <tr>	
    	    <td align="center">文件名称： </td>	
		    <td><textarea rows="3" cols="40" readonly="readonly">&nbsp;${event.zipfile}</textarea></td>	 	  		   	
		    <td align="center">备注：</td>
		    <td><textarea rows="3" cols="40" readonly="readonly">&nbsp;${event.summ}</textarea></td>
  	   </tr>
  	   </c:when>
	</c:choose>
	<c:choose>
		<c:when test="${event.file_type==4}">
        <tr>	
    	    <td align="center">磁介质名称： </td>	
		    <td><textarea rows="3" cols="40" readonly="readonly">&nbsp;${event.zipfile}</textarea></td>	 	  		   	
		    <td align="center">备注：</td>
		    <td><textarea rows="3" cols="40" readonly="readonly">&nbsp;${event.summ}</textarea></td>
  	   </tr>
  	   </c:when>
	</c:choose>
  	<c:choose>
		<c:when test="${event.file_type == 3 && event.import_status == 1}">
	    <tr>
	  		<td align="center">文件列表：</td>
	  		<td colspan="3">
	  			<table width="100%" border="0" cellspacing="0" cellpadding="0" align="left" >	
		  			<s:iterator value="#request.fileList" var="scanfile">
		  			<c:choose>
		  				<c:when test="${event.user_iidd eq current_user_iidd}">
			    		<tr>
		  					<td align="left"><a href="#" onclick="downloadFile('${scanfile.file_path}','${scanfile.file_name}');">${scanfile.file_name}</a></td>
			   			</tr>
			    		</c:when>	
			    		<c:otherwise>
			    		<tr>
		  					<td align="left">${scanfile.file_name}</td>
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
    <td colspan="6" align="center">
		<input class="button_2003" type="button" value="返回" onClick="javascript:history.go(-1);">
    </td>
	</tr>
</table>
</body>
</html>
