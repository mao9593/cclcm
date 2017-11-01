<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>查看载体生命周期详细信息</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/displaytag.css" type="text/css" media="screen"/>
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen"/>
	<link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
	<script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script>
	$(document).ready(function(){
		//if('${event.job_status}' != ""){
			prepareBizApproval("${process.step_dept}","${process.step_role}","${process.step_dept_name}","${process.step_role_name}","${ctx}");
		//}
	});
	function showPrintFile(filestorename,pagecount){
		var FileStoreNameLen = filestorename.length;
		var unzipdirname = filestorename.substr(0,FileStoreNameLen-4);
		var url ="${ctx}/print/showprintfile.action?unzipdirname="+unzipdirname+"&pagecount="+pagecount;
		window.showModalDialog(url,window,"dialogHeight:"+(screen.availHeight-40)+";dialogWidth:"+(screen.availWidth-5)+";center:yes;resizable:no;status:no;scroll:no;help:no");
		return false;
	}
	function checkShowPrintFile(filestorename,pagecount,seclv_code){
		var url ="${ctx}/ledger/checkshowprintfile.action?seclv_code="+seclv_code+"&filestorename="+filestorename+"&pagecount="+pagecount;
		callServer(url);
	}
	
	function updateResult(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			var is_paper_audit = xmlHttp.responseText.split("#")[0];
			if(is_paper_audit == 'true'){
				var filestorename = xmlHttp.responseText.split("#")[1];
				var pagecount = xmlHttp.responseText.split("#")[2];
				var FileStoreNameLen = filestorename.length;
				var unzipdirname = filestorename.substr(0,FileStoreNameLen-4);
				var url ="${ctx}/print/showprintfile.action?unzipdirname="+unzipdirname+"&pagecount="+pagecount;
				window.showModalDialog(url,window,"dialogHeight:"+(screen.availHeight-40)+";dialogWidth:"+(screen.availWidth-5)+";center:yes;resizable:no;status:no;scroll:no;help:no");
				return false;
			}else{
				alert("该密级的文件不允许审计！");
			}
		}
	}
	
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<!-- 
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
-->
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
	<tr>
	    <td colspan="6" class="title_box">载体属性信息</td>
	</tr>
	<tr>
    	<td width="13%" align="center">文件名： </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${paper.file_title}</b></font></td>
    	<td width="13%" align="center">文件条码： </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${paper.paper_barcode}</b></font></td>
    	<c:if test="${paper.merge_state != '1'}">
	    	<td width="13%" align="center">当前状态： </td>
	    	<td width="20%"><font color="red"><b>&nbsp;${paper.paper_state_name}</b></font></td>
    	</c:if>
    	<c:if test="${paper.merge_state == '1'}">
	    	<td width="13%">&nbsp;</td>
	    	<td width="20%">&nbsp;</td>
    	</c:if>
	</tr>
	<tr>
    	<td align="center">文件密级： </td>
    	<td><font color="blue"><b>&nbsp;${paper.seclv_name}</b></font></td>
		<td align="center">责任人： </td>
    	<td><font color="blue"><b>&nbsp;${paper.duty_user_name}</b></font></td>
    	<td align="center">责任人部门： </td>
    	<td><font color="blue"><b>&nbsp;${paper.duty_dept_name}</b></font></td>
	</tr>
	<tr>
		<td align="center">制作人： </td>
    	<td><font color="blue"><b>&nbsp;${paper.user_name}</b></font></td>
    	<td align="center">制作人部门： </td>
    	<td><font color="blue"><b>&nbsp;${paper.dept_name}</b></font></td> 
    	<td align="center">结果： </td>
    	<td><font color="blue"><b>&nbsp;${paper.print_result_name}</b></font></td>
  	</tr>
  	<tr> 
  		<c:choose>
			<c:when test="${file_kind == 'BOOK'}">
				<td align="center">启用时间： </td>
			</c:when>
			<c:otherwise>
				<td align="center">制作时间： </td>
			</c:otherwise>
		</c:choose>
    	<td><font color="blue"><b>&nbsp;${paper.print_time}</b></font></td>
    	<td align="center">页数： </td>
    	<td><font color="blue"><b>&nbsp;${paper.page_count}</b></font></td>
	    <td align="center">纸张类型：</td>
	    <td><font color="blue"><b>&nbsp;${paper.page_size}</b></font></td>
	</tr>
	<tr> 
	    <td align="center">打印机：</td>
	    <td><font color="blue"><b>&nbsp;${paper.printer_name}</b></font></td>
    	<td align="center">是否补打： </td>
    	<td><font color="blue"><b>&nbsp;${paper.is_reprint_name}</b></font></td>
	    <td align="center">文号： </td>
    	<td><font color="blue"><b>&nbsp;${paper.fileID}</b></font></td>
	</tr>
	<tr> 
		<td align="center">单双面： </td>
    	<td><font color="blue"><b>&nbsp;${paper.print_double_name}</b></font></td>
    	<td align="center">色彩：</td>
	    <td><font color="blue"><b>&nbsp;${paper.color_name}</b></font></td>
	    <td align="center">载体归属： </td>
    	<td><font color="blue"><b>&nbsp;${paper.scope_name}</b></font></td>
    </tr>
	<tr> 	
		<td align="center">归属部门： </td>
    	<td><font color="blue"><b>&nbsp;${paper.scope_dept_name}</b></font></td>
    	<td align="center">外发接收人： </td>
    	<td><font color="blue"><b>&nbsp;${paper.output_user_name}</b></font></td>
    	<td align="center">外发接收部门： </td>
    	<td><font color="blue"><b>&nbsp;${paper.output_dept_name}</b></font></td>
    </tr>
    <c:choose>
		<c:when test="${paper.job_code.contains('SEND')}">
		    <tr>  
		        <td align="center">交接单委托打印人： </td>
		        <td colspan="5"><font color="blue"><b>&nbsp;${supervise_user_name}</b></font></td>
		    </tr>
		</c:when>
	</c:choose>
	<c:choose>
		<c:when test="${paper.create_type.contains('LEADIN')}">
		    <tr>  
		        <td align="center">原文件编号： </td>
		        <td ><font color="blue"><b>&nbsp;${paper.src_code}</b></font></td>
		        <td align="center">机要号(录入)： </td>
		        <td colspan="3"><font color="blue"><b>&nbsp;${paper.confidential_num}</b></font></td>
		    </tr>
		</c:when>
	</c:choose>
	<tr> 	
    	<td align="center">关键字： </td>
    	<td><font color="blue"><b>&nbsp;${paper.keyword_content}</b></font></td>	
		<td align="center">到期时间： </td>
    	<td><font color="blue"><b>&nbsp;${paper.expire_time}</b></font></td>
    	<td align="center">到期状态： </td>
    	<td><font color="blue"><b>&nbsp;${paper.expire_status_name}</b></font></td>
    </tr>
	<tr> 
		<td align="center">备注： </td>
    	<td><font color="blue"><b>&nbsp;${paper.fail_remark}</b></font></td>
    	<td align="center">闭环操作人： </td>
    	<td><font color="blue"><b>&nbsp;${paper.retrieve_user_name}</b></font></td>
    	<td align="center">闭环时间： </td>
    	<td><font color="blue"><b>&nbsp;${paper.retrieve_time}</b></font></td>
    </tr>
	<tr>
    	<td align="center">销毁人： </td>
    	<td><font color="blue"><b>&nbsp;${paper.destroy_user_name}</b></font></td>
    	<td align="center">销毁时间： </td>
    	<td><font color="blue"><b>&nbsp;${paper.destroy_time}</b></font></td>
    	<td>&nbsp;</td>
    	<td>&nbsp;</td>
	</tr>
	<c:choose>
		<c:when test="${paper.job_code.contains('DESTROY_PAPER_BYSELF')}">
			<tr>
  		       <td align="center">监销人：</td>
  		       <td colspan="5"><font color="blue"><b>&nbsp;${supervise_user_name}</b></font></td>
  	        </tr>
		</c:when>
	</c:choose>
	<c:choose>
		<c:when test="${paper.job_code.contains('SEND') or paper.job_code.contains('CARRYOUT')}">
		    <tr>	
				<td align="center">外发(带)方式： </td>
    			<td><font color="blue"><b>&nbsp;${paper.send_way_str}</b></font></td>
    			<td align="center">外发(带)机要号： </td>
    			<td><font color="blue"><b>&nbsp;${paper.output_confidential_num}</b></font></td>
    			<td align="center">携带人： </td>
    			<td><font color="blue"><b>&nbsp;${paper.carryout_user_names}</b></font></td>
			</tr>
		</c:when>
	</c:choose>
	<tr>
	 <td align="center">回执单文件名：</td>
	    <s:iterator value="#request.burnFileList1" var="burnFile1">
	  	<td align="center" colspan="5"><a href="${ctx}/${burnFile1.file_path}/${burnFile1.file_name}">${burnFile1.file_name}</a></td>
		</s:iterator>
	</tr>
	<c:if test="${paper.create_type == 'PRINT'}">
		<c:choose>
			<c:when test="${ledger_type == 'personal'}">
				<tr> 
					<td align="center" colspan="6">
						<input class="button_2003" type="button" value="预览" onClick="showPrintFile('${event.st_filename}','${event.page_count}');">
					</td>
				</tr>
			</c:when>
			<c:when test="${ledger_type == 'total' or ledger_type == 'dept' or ledger_type == 'file'}">
				<tr> 
					<td align="center" colspan="6">
						<input class="button_2003" type="button" value="预览" onClick="checkShowPrintFile('${event.st_filename}','${event.page_count}','${paper.seclv_code}');">
					</td>
				</tr>
			</c:when>
		</c:choose>
	</c:if>
</table>
<c:if test="${not empty itemList}">
<br/>
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
	<tr>
	    <td colspan="6" class="title_box">载体全生命周期信息</td>
	</tr>
	<tr>
    	<td width="20%" align="center">载体条码 </td>
    	<td width="15%" align="center">操作人</td>
    	<td width="15%" align="center">部门</td>
    	<td width="20%" align="center">操作时间 </td>
    	<td width="15%" align="center">操作 </td>
    	<td width="15%" align="center">审批流程</td>
	</tr>
	<s:iterator value="#request.itemList" var="item">
		<tr>
	    	<td align="center">${item.barcode}</td>
	    	<td align="center">${item.user_name}</td>
	    	<td align="center">${item.dept_name}</td>
	    	<td align="center">${item.oper_time_str}</td>
	    	<td align="center">${item.oper.name}</td>
	    	<c:choose>
		    	 <c:when test="${item.job_code == 'default'}">
		    	 	<td align="center">暂无审批信息</td>
		    	 </c:when>
		    	 <c:otherwise>
		    	 	<td align="center"><input class="button_2003" type="button" value="查看审批信息" onClick="go('${ctx}/ledger/viewapprovalprocessdetail.action?job_code=${item.job_code}');"/></td>
		    	 </c:otherwise>
	    	</c:choose>
			
			
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
<br/>
<c:if test="${printType == 'replacePage' }">
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
	<tr>
		<td colspan="4" class="title_box">替换页码文件信息</td>
	</tr>
	<tr>
		<td align="center" width="13%">替换文件条码：</td>
		<td align="center" width="57%">
			<font color="blue"><b>&nbsp;<s:iterator value="#request.paper_barcode" var="barcode">${barcode}&nbsp;&nbsp;&nbsp;</s:iterator></b></font></td>
		<td align="center" width="10%">替换页码：</td>
		<td align="center" width="20%"><font color="blue"><b>&nbsp;${paper.PID_pagenum }</b></font></td>
	</tr>
	<tr>
		<td align="center">已申请监销页码：</td>
		<td align="center"><font color="blue"><b>&nbsp;${paper.retrieve_pagenum}</b></font></td>
		<td align="center">申请监销情况：</td>
		<td align="center"><font color="red">
			<c:choose>
				<c:when test="${paper.retrieve_replace=='ALL_RETRIEVED' or paper.retrieve_replace=='ALL_RETRIEVED-SUB_DESTROYED' or paper.retrieve_replace=='ALL_DESTROYED'}"><font color="red">已全部申请监销</font></c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${paper.retrieve_replace=='SUB_RETRIEVED' or paper.retrieve_replace=='SUB_RETRIEVED-SUB_DESTROYED' or (not empty paper.destroy_pagenum)}">
							<b>&nbsp;部分申请监销</b>
						</c:when>
						<c:otherwise>
							<b>&nbsp;尚未申请监销</b>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
		</font></td>
	</tr>
	<tr>
		<td align="center">已监销页码：</td>
		<td align="center"><font color="blue"><b>&nbsp;${paper.destroy_pagenum}</b></font></td>
		<td align="center">监销情况：</td>
		<td align="center"><font color="red">
			<c:choose>
				<c:when test="${paper.retrieve_replace=='ALL_DESTROYED'}"><font color="red">已全部监销</font></c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${paper.retrieve_replace=='SUB_RETRIEVED-SUB_DESTROYED' or paper.retrieve_replace=='ALL_RETRIEVED-SUB_DESTROYED' or (not empty paper.destroy_pagenum)}">
							<b>&nbsp;已部分监销</b>
						</c:when>
						<c:otherwise>
							<b>&nbsp;尚未监销</b>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
		</font></td>
	</tr>
</table>
</c:if>
<br/>
<c:if test="${modify_status==1}">
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
	<tr>
	    <td colspan="6" class="title_box">密级变更记录</td>
	</tr>
	<tr>
    	<td width="20%" align="center">载体条码 </td>
    	<td width="20%" align="center">类型</td>
    	<td width="20%" align="center">原密级</td>
    	<td width="15%" align="center">目标密级</td>
    	<td width="25%" align="center">具体说明 </td>
    	<td width="15%" align="center">审批流程</td>
    	
	</tr>
	<s:iterator value="#request.eventModify" var="item">
	<c:if test="${item.modify_status==1}">
		<tr>
	    	<td align="center">${item.barcode}</td>
	    	<td align="center">${item.entity_type_name}</td>
	    	<td align="center">${item.pre_seclv_name}</td>
	    	<td align="center">${item.trg_seclv_name}</td>
	    	<td align="center">${item.summ}</td>
	    	<c:choose>
		    	 <c:when test="${item.job_code == 'default' || empty item.job_code}">
		    	 	<td align="center">暂无审批信息</td>
		    	 </c:when>
		    	 <c:otherwise>
		    	 	<td align="center"><input class="button_2003" type="button" value="查看审批信息" onClick="go('${ctx}/ledger/viewapprovalprocessdetail.action?job_code=${item.job_code}');"/></td>
		    	 </c:otherwise>
	    	</c:choose>
	    	
		</tr>	
	</c:if>
</s:iterator>
</table>
</c:if>
<br/>
<c:if test="${not empty mergeList}">
<table width="95%" align="center" >
	<tr>
	    <td colspan="6" class="title_box">载体合并信息</td>
	</tr><tr>
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
						<display:column title="制作时间" sortable="true" property="print_time"/>
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