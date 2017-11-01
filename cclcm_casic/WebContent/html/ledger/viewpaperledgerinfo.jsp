<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>查看纸质载体详情</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen"/>
	<link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
	<script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script>
	<!--
	$(document).ready(function(){
		if('${paperEntity.job_status}' != ""){
			prepareBizApproval("${process.step_dept}","${process.step_role}","${process.step_dept_name}","${process.step_role_name}","${ctx}");
		}
	});
	//-->
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<table width="80%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
	<tr>
	    <td colspan="6" class="title_box">查看纸质载体详情</td>
	</tr>
	<tr>
    	<td width="13%" align="center">文件名： </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${paperEntity.file_title}</b></font></td>
    	<td width="13%" align="center">文件条码： </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${paperEntity.paper_barcode}</b></font></td>
    	<td width="13%" align="center">当前状态： </td>
    	<td width="20%"><font color="red"><b>&nbsp;${paperEntity.paper_state_name}</b></font></td>
	</tr>
	<tr>
    	<td align="center">文件密级： </td>
    	<td><font color="blue"><b>&nbsp;${paperEntity.seclv_name}</b></font></td>
		<td align="center">责任人： </td>
    	<td><font color="blue"><b>&nbsp;${paperEntity.duty_user_name}</b></font></td>
    	<td align="center">责任人部门： </td>
    	<td><font color="blue"><b>&nbsp;${paperEntity.duty_dept_name}</b></font></td>
	</tr>
	<tr>
		<td align="center">制作人： </td>
    	<td><font color="blue"><b>&nbsp;${paperEntity.user_name}</b></font></td>
    	<td align="center">制作人部门： </td>
    	<td><font color="blue"><b>&nbsp;${paperEntity.dept_name}</b></font></td> 
    	<td align="center">结果： </td>
    	<td><font color="blue"><b>&nbsp;${paperEntity.print_result_name}</b></font></td>
  	</tr>
  	<tr> 
  		<c:choose>
			<c:when test="${time_flag == 'USETIME'}">
				<td align="center">启用时间： </td>
			</c:when>
			<c:otherwise>
				<td align="center">制作时间： </td>
			</c:otherwise>
		</c:choose>
    	<td><font color="blue"><b>&nbsp;${paperEntity.print_time}</b></font></td>
    	<td align="center">页数： </td>
    	<td><font color="blue"><b>&nbsp;${paperEntity.page_count}</b></font></td>
	    <td align="center">纸张类型：</td>
	    <td><font color="blue"><b>&nbsp;${paperEntity.page_size}</b></font></td>
	</tr>
	<tr> 
	    <td align="center">打印机：</td>
	    <td><font color="blue"><b>&nbsp;${paperEntity.printer_name}</b></font></td>
    	<td align="center">是否补打： </td>
    	<td><font color="blue"><b>&nbsp;${paperEntity.is_reprint_name}</b></font></td>
	    <td align="center">文号： </td>
    	<td><font color="blue"><b>&nbsp;${paperEntity.fileID}</b></font></td>
	</tr>
	<tr> 
		<td align="center">单双面： </td>
    	<td><font color="blue"><b>&nbsp;${paperEntity.print_double_name}</b></font></td>
    	<td align="center">色彩：</td>
	    <td><font color="blue"><b>&nbsp;${paperEntity.color_name}</b></font></td>
	    <td align="center">载体归属： </td>
    	<td><font color="blue"><b>&nbsp;${paperEntity.scope_name}</b></font></td>
    </tr>
	<tr> 	
		<td align="center">归属部门： </td>
    	<td><font color="blue"><b>&nbsp;${paperEntity.scope_dept_name}</b></font></td>
    	<td align="center">外发接收人： </td>
    	<td><font color="blue"><b>&nbsp;${paperEntity.output_user_name}</b></font></td>
    	<td align="center">外发接收部门： </td>
    	<td><font color="blue"><b>&nbsp;${paperEntity.output_dept_name}</b></font></td>
    </tr>
    <c:choose>
		<c:when test="${paperEntity.job_code.contains('SEND')}">
		    <tr height="50" style="display: <c:if test="${empty paperEntity.job_status or op!='hasprc'}">none</c:if>">  
		        <td align="center">交接单委托打印人： </td>
		        <td colspan="5"><font color="blue"><b>&nbsp;${supervise_user_name}</b></font></td>
		    </tr>
		</c:when>
	</c:choose>
	<tr> 	
    	<td align="center">关键字： </td>
    	<td><font color="blue"><b>&nbsp;${paperEntity.keyword_content}</b></font></td>	
		<td align="center">到期时间： </td>
    	<td><font color="blue"><b>&nbsp;${paperEntity.expire_time}</b></font></td>
    	<td align="center">到期状态： </td>
    	<td><font color="blue"><b>&nbsp;${paperEntity.expire_status_name}</b></font></td>
    </tr>
	<tr> 
		<td align="center">备注： </td>
    	<td><font color="blue"><b>&nbsp;${paperEntity.fail_remark}</b></font></td>
    	<td align="center">闭环操作人： </td>
    	<td><font color="blue"><b>&nbsp;${paperEntity.retrieve_user_name}</b></font></td>
    	<td align="center">闭环时间： </td>
    	<td><font color="blue"><b>&nbsp;${paperEntity.retrieve_time}</b></font></td>
    </tr>
	<tr>
		<td align="center">外发(带)机要号： </td>
    	<td><font color="blue"><b>&nbsp;${paperEntity.output_confidential_num}</b></font></td>
    	<td align="center">销毁人： </td>
    	<td><font color="blue"><b>&nbsp;${paperEntity.destroy_user_name}</b></font></td>
    	<td align="center">销毁时间： </td>
    	<td><font color="blue"><b>&nbsp;${paperEntity.destroy_time}</b></font></td>
	</tr>
	<tr>
		<td align="center">外发承办人： </td>
    	<td><font color="blue"><b>&nbsp;${paperEntity.output_undertaker_name}</b></font></td>
    	<td align="center">外发(带)方式： </td>
    	<td><font color="blue"><b>&nbsp;${paperEntity.send_way_str}</b></font></td>
    	<td align="center">携带人： </td>
    	<td><font color="blue"><b>&nbsp;${paperEntity.carryout_user_names}</b></font></td>
	</tr>
	<c:choose>
		<c:when test="${paperEntity.job_code.contains('DESTROY_PAPER_BYSELF')}">
			<tr height="50" style="display: <c:if test="${empty paperEntity.job_status or op!='hasprc'}">none</c:if>">
  		       <td align="center">监销人：</td>
  		       <td colspan="5"><font color="blue"><b>&nbsp;${supervise_user_name}</b></font></td>
  	        </tr>
		</c:when>
	</c:choose>
	<tr height="50" style="display: <c:if test="${empty paperEntity.job_status or op!='hasprc'}">none</c:if>">
  		<td align="center">审批人：</td>
  		<td colspan="5"><font color="blue"><b>&nbsp;${job.next_approver_name}</b></font></td>
  	</tr>
  	<tr style="display: <c:if test="${empty paperEntity.job_status or op!='hasprc'}">none</c:if>">
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
  	<tr valign="middle" height="80" style="display: <c:if test="${empty paperEntity.job_status or op!='hasprc'}">none</c:if>"> 
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
<c:if test="${not empty mergeList}">
<table width="85%" align="center" >
	<tr>
	    <td colspan="6" class="title_box">载体合并信息</td>
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
	    <td colspan=6 align=center> 
			<input class="button_2003" type="button" value="返回" onClick="javascript:history.go(-1);">&nbsp;
	    </td>
  </tr>
</table>
</center>
</body>
</html>