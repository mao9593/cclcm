<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen"/>
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
//-->
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
	<tr>
	    <td colspan="4" class="title_box">查看复印作业详情</td>
	</tr>
	<tr>
    	<td width="15%" align="center">申请用户： </td>
    	<td width="35%"><font color="blue"><b>&nbsp;${event.user_name}</b></font></td>
    	<td width="15%" align="center">当前状态： </td>
    	<td width="35%"><font color="red"><b>&nbsp;${event.job_status_name}/&nbsp;${event.copy_status_name}</b></font></td>
	</tr>
	<tr>
    	<td align="center">用户部门： </td>
    	<td><font color="blue"><b>&nbsp;${event.dept_name}</b></font></td>
    	<td align="center">申请时间： </td>
    	<td><font color="blue"><b>&nbsp;${event.apply_time_str}</b></font></td>
	</tr>
	<tr>
		<td align="center">文件名称： </td>
    	<td><font color="blue"><b>&nbsp;${event.file_name}</b></font></td> 
    	<td align="center">源文件编号： </td>
    	<td><font color="blue"><b>&nbsp;${event.originalid}</b></font></td>
	</tr>
	<tr> 
		<td align="center">作业密级：</td>
	    <td><font color="blue"><b>&nbsp;${event.seclv_name}</b></font></td>
	    <td align="center">用途：</td>
	    <td><font color="blue"><b>&nbsp;${event.usage_name}</b></font></td>
  	</tr>
  	<tr>
		<td align="center">页数：</td>
	    <td><font color="blue"><b>&nbsp;${event.page_num}页</b></font></td>
	    <td align="center">份数：</td>
	    <td><font color="blue"><b>&nbsp;${event.copy_num}份</b></font></td>
    	<%-- <td align="center">所属项目：</td>
	    <td><font color="blue"><b>&nbsp;${event.project_name}</b></font></td> --%>
	</tr>
	<tr>		
	    <td align="center">单双面：</td>
	    <td><font color="blue"><b>&nbsp;${event.is_double_name}</b></font></td>
	    <td align="center">色彩类型：</td>
	    <td><font color="blue"><b>&nbsp;${event.color_name}</b></font></td>
	</tr>
	<tr>		
	    <td align="center">使用期限：</td>
	    <td><font color="blue"><b>&nbsp;${event.period_name}</b></font></td>
	    <td align="center">复印类型：</td>
	    <td><font color="blue"><b>&nbsp;${event.copy_type_name}</b></font></td>
	</tr>
	<tr>	
		<td align="center">纸张类型：</td>
	    <td><font color="blue"><b>&nbsp;${event.page_type}</b></font></td> 	    
	     <td align="center">文件来源：</td>
	    <td><font color="blue"><b>&nbsp;${event.source}</b></font></td> 
	</tr>
	<tr>
  		<td align="center">备注：</td>
  		<td colspan="3"><textarea rows="3" cols="50" readonly="readonly">&nbsp;${event.summ}</textarea></td>
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
<c:if test="${not empty mergeList}">
<table width="95%" align="center" >
	<tr>
	    <td colspan="4" class="title_box">载体合并信息</td>
	</tr>
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
					<display:table uid="item" defaultsort="10" defaultorder="descending" class="displaytable" name="mergeList" sort="list" >
						<display:column title="序号">
							<c:out value="${item_rowNum}" />
						</display:column>
						
						<display:column title="文件名">
							<c:choose>
								<c:when test="${item.create_type_name=='被替换页' }">
									<font color="orange">${item.file_title}</font>
								</c:when>
								<c:otherwise>${item.file_title}</c:otherwise>
							</c:choose>
						</display:column>
						
						<display:column title="文件条码">
							<c:choose>
								<c:when test="${item.create_type_name=='被替换页' }">
									<font color="orange">${item.paper_barcode}</font>
								</c:when>
								<c:otherwise>${item.paper_barcode}</c:otherwise>
							</c:choose>
						</display:column>
						
						<display:column title="文件密级" property="seclv_name" />
						<display:column title="制作方式">
							<c:choose>
								<c:when test="${item.create_type=='REPLACEPRINT' || item.create_type=='PRINT'}">打印</c:when>
								<c:otherwise>${item.create_type_name}</c:otherwise>
							</c:choose>
						</display:column>
						<display:column title="打印结果" property="print_result_name" />
						<display:column title="状态" property="paper_state_name" />
						<display:column title="页数" property="page_count" />	
						<display:column title="机要号(录入)" property="confidential_num" />	
						<display:column title="制作时间" property="print_time"/>
						<display:column title="操作" style="width:50px">
							<c:choose>
								<c:when test="${item.create_type_name=='被替换页' }">
									<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/ledger/viewcycledetail.action?type=PAPER&printType=replacePage&barcode=${item.paper_barcode}&ledger_type=personal');"/>
								</c:when>
								<c:otherwise>
									<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/ledger/viewcycledetail.action?type=PAPER&barcode=${item.paper_barcode}&ledger_type=personal');"/>
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
</c:if>		
	<tr>
		<td colspan="4" align="center"> 
			<input class="button_2003" type="button" value="返回" onClick="javascript:history.go(-1);">&nbsp;
		</td>
	</tr>
</table>
</body>
</html>
