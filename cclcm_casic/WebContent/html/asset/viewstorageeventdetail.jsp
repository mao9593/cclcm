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
<form action="${ctx}/asset/viewstorageeventdetail.action" method="post" >
<input type="hidden" name="event_code" value="${event_code}"/>
<input type="hidden" name="updatFlag" value="Y"/>
<input type="hidden" name="approveFlag" value="Y"/>
	<tr>
	    <td colspan="4" class="title_box">查看资产入账作业详情</td>
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
	    <td align="center">资产密级： </td>
    	<td><font color="blue"><b>&nbsp;${event.property_seclvcode_name}</b></font></td>     
    </tr>
    <tr>	
	   	<td align="center">资产种类： </td>
    	<td><font color="blue"><b>&nbsp;${event.property_kind}</b></font></td>
    	<td align="center">设备名称： </td>
    	<td><font color="blue"><b>&nbsp;${event.property_name}</b></font></td>
    </tr>
   	<tr>
   		<td align="center">规格： </td>
   		<td><font color="blue"><b>&nbsp;${event.property_standard}</b></font></td>	
   		<td align="center">型号： </td>
   		<td><font color="blue"><b>&nbsp;${event.property_type}</b></font></td>	
  	</tr>
	<c:choose>
		<c:when test="${updatFlag eq 'Y'}">
			<tr>
		  		<td align="center">资产编号： </td>
		   		<td><input type="text" name="property_no" value="${event.property_no}"/></td>	
		  		<td align="center">凭证号： </td>
			    <td><input type="text" name="voucher_no" value="${event.voucher_no}"/></td>  	
  			</tr>
		</c:when>
		<c:otherwise>
			<tr>
		  		<td align="center">资产编号： </td>
		   		<td><font color="blue"><b>&nbsp;${event.property_no}</b></font></td>	
		  		<td align="center">凭证号： </td>
			    <td><font color="blue"><b>&nbsp;${event.voucher_no}</b></font></td>  	
  			</tr>
		</c:otherwise>
	</c:choose>

  	<tr>
  		<td align="center">识别码： </td>
	    <td><font color="blue"><b>&nbsp;${event.identity_code}</b></font></td>  
	    <td align="center">安装地点： </td>
   		<td><font color="blue"><b>&nbsp;${event.setup_place}</b></font></td>	  		
  	</tr>

  	<tr>
  		<td align="center">原值： </td>
	    <td><font color="blue"><b>&nbsp;${event.original_value}</b></font></td>  
   		<td align="center">资产管理部门： </td>
	    <td><font color="blue"><b>&nbsp;${event.manage_dept_name}</b></font></td>     	
  	</tr>
  	<tr>
  		<td align="center">出厂编号： </td>
	    <td><font color="blue"><b>&nbsp;${event.factory_no}</b></font></td>  
  		<td align="center">出厂日期： </td>
	    <td><font color="blue"><b>&nbsp;${event.factory_date_str}</b></font></td>  	   
  	</tr>
    <tr>	 
    	<td align="center">国别厂家： </td>
    	<td><font color="blue"><b>&nbsp;${event.supplier}</b></font></td> 	  	
	    <td align="center">数量： </td>
	    <td><font color="blue"><b>&nbsp;${event.amount}</b></font></td>	  		   	
  	</tr>
  	    <tr>	 	
    	<td align="center">启用日期： </td>
	    <td><font color="blue"><b>&nbsp;${event.use_date_str}</b></font></td>  
	    <td align="center">申请时间： </td>
    	<td><font color="blue"><b>&nbsp;${event.apply_time_str}</b></font></td>   		   	
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
    <c:if test="${updatFlag eq 'Y'}">
    	<input class="button_2003" type="button" value="保存" onClick="forms[0].submit();">
    </c:if>
		<input class="button_2003" type="button" value="返回" onClick="javascript:history.go(-1);">
    </td>
	</tr>
</from>
</table>
</body>
</html>
