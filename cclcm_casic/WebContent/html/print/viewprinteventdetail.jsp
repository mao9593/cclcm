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
$(document).ready(function(){
	if('${event.job_status}' != ""){
		prepareBizApproval("${process.step_dept}","${process.step_role}","${process.step_dept_name}","${process.step_role_name}","${ctx}");
	}
});
function showPrintFile(filestorename,pagecount){
	var FileStoreNameLen = filestorename.length;
	var unzipdirname = filestorename.substr(0,FileStoreNameLen-4);
	var url ="${ctx}/print/showprintfile.action?unzipdirname="+unzipdirname+"&pagecount="+pagecount;
//	window.open(url,'','height='+(screen.availHeight-40)+', width='+(screen.availWidth-5)+', top=0, left=0, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no');
	window.showModalDialog(url,window,"dialogHeight:"+(screen.availHeight-40)+";dialogWidth:"+(screen.availWidth-5)+";center:yes;resizable:no;status:no;scroll:no;help:no");
	return false;
}
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
	<tr>
	    <td colspan="4" class="title_box">查看打印作业详情</td>
	</tr>
	<tr>
    	<td width="15%" align="center">申请用户： </td>
    	<td width="35%"><font color="blue"><b>&nbsp;${event.user_name}</b></font></td>
    	<td width="15%" align="center">当前状态： </td>
    	<td width="35%"><font color="red"><b>&nbsp;${event.job_status_name}&nbsp;/&nbsp;${event.print_status_name}</b></font></td>
	</tr>
	<tr>
    	<td align="center">用户部门： </td>
    	<td><font color="blue"><b>&nbsp;${event.dept_name}</b></font></td>
    	<td align="center">申请时间： </td>
    	<td><font color="blue"><b>&nbsp;${event.apply_time_str}</b></font></td>
	</tr>
	<tr>
		<td align="center">文件名称： </td>
    	<td><font color="blue"><b>&nbsp;${event.file_title}</b></font></td>
		<td align="center">原文件名： </td>
    	<td><font color="blue"><b>&nbsp;${event.dl_filename}</b></font></td> 
	</tr>
	<tr> 
    	<td align="center">作业密级：</td>
	    <td><font color="blue"><b>&nbsp;${event.seclv_name}</b></font></td>
	    <td align="center">状态： </td>
    	<td><font color="blue"><b>&nbsp;${event.cycle_type_name}&nbsp;${event.period_name}</b></font></td>
  	</tr>
	<tr>
		<td align="center">页数/份数：</td>
	    <td><font color="blue"><b>&nbsp;${event.page_count}页/&nbsp;${event.print_count}份</b></font></td>
		<td align="center">用途： </td>
    	<td><font color="blue"><b>&nbsp;${event.usage_name}</b></font></td>
    	<%-- <td align="center">项目：</td>
	    <td><font color="blue"><b>&nbsp;${event.project_name}</b></font></td> --%>
	</tr>
  	<tr>
		<td align="center">文件大小： </td>
    	<td><font color="blue"><b>&nbsp;${event.file_size}B</b></font></td>
		<td align="center">单双面：</td>
	    <td><font color="blue"><b>&nbsp;${event.print_double_name}</b></font></td>
	</tr>
	<tr>
		<td align="center">纸张类型：</td>
	    <td><font color="blue"><b>&nbsp;${event.page_size}</b></font></td>
	    <td align="center">色彩类型：</td>
	    <td><font color="blue"><b>&nbsp;${event.color_name}</b></font></td>
	</tr>
	<tr>
		<td align="center">可打印份数：</td>
	    <td><font color="blue"><b>&nbsp;${event.remaintimes}</b></font></td>
	    <td align="center">可补打份数：</td>
	    <td><font color="blue"><b>&nbsp;${event.fixednum}</b></font></td>
	</tr>
	<tr>				
    	<td align="center"><font color="red"><b>&nbsp;知悉范围:</b></font></td>
    	<td><font color="red">&nbsp;${event.file_scope}</font></td>
	    <td align="center"><font color="red"><b>&nbsp;定密依据:</b></font></td>
		<td><font color="red">&nbsp;${event.seclv_accord}</font></td>
	</tr>
	<tr>	
		<td align="center"><font color="red"><b>&nbsp;保密期限:</b></font></td>
		<td><font color="red">&nbsp;${event.secret_time}</font></td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
	    <!-- 
	<tr>
	    <td align="center">逐份/页：</td>
	    <td><font color="blue"><b>&nbsp;${event.print_collate}</b></font></td>
  		<td align="center">备注：</td>
		<td><textarea rows="3" cols="60" readonly="readonly">&nbsp;${event.summ}</textarea></td>
	</tr>
	     -->
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
    <td colspan=4 align=center> 		
		<!-- input class="button_2003" type="button" value="预览" onClick="showPrintFile('${event.st_filename}','${event.page_count}');">&nbsp;	-->
		<input class="button_2003" type="button" value="返回" onClick="javascript:history.go(-1);">	
    </td>
  </tr>
</table>
</body>
</html>
