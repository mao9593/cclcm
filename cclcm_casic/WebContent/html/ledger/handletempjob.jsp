<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
 	<script type="text/javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
<script>
$(document).ready(function(){
	onHover();
	$("#seclv_code option").last().attr("selected",true);
	prepareBizApproval("${process.step_dept}","${process.step_role}","${process.step_dept_name}","${process.step_role_name}","${ctx}");
	$("#submit_btn").attr("disabled",true);
});

	function chkSubmit(){
		if(${jobType.jobTypeCode.contains('PAPER_DEL')}){
	    	if($("#reason").val().trim() == ""){
				alert("请填写删除原因");
				$("#reason").focus();
				return false;
			}
	    }else if(${jobType.jobTypeCode.contains('PAPER_MODIFY')}){
	    	if($("#reason").val().trim() == ""){
				alert("请填写修改原因");
				$("#reason").focus();
				return false;
			}
			var pages = /^[0-9]+$/;
			  if($("#output_dept_name").val() == ""||$("#output_dept_name").val() == 0){
				  alert("台账修改页数不能为空或者为0");
				  $("#output_dept_name").focus();
				  return false;
			  }else{
	            if(!pages.test($("#output_dept_name").val())){
	               alert("台账修改页数应为整数");
	               $("#output_dept_name").focus();
	               return false;
	              }
	     	  }
	    }
	
		if($("#next_approver_all option").size() > 0 && $("#next_approver_sel option").size() == 0){
			alert("请选择审批人员");
			$("#next_approver_all").focus();
			return false;
		}
		var next_approver = "";
		$("#next_approver_sel option").each(function(){
			next_approver += this.value+",";
		});
		var len = next_approver.length;
		if (next_approver.lastIndexOf(",") == len - 1){
			next_approver = next_approver.substring(0, len - 1);
		}
		$("#next_approver").val(next_approver);
		
		var url = "${ctx}/ledger/handletempjob.action";
		callServerPostForm1(url,document.forms[0]);
		return true;
	}
	function getAjaxResult1(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			alert("任务添加完成");
			window.location="${ctx}/basic/viewfilepaper.action";
		}
	}

	function selectNextApprover(){
		del_all_True('next_approver_sel');
		var url = "${ctx}/basic/getnextapprover.action";
		callServerPostForm(url,document.forms[0]);
	}
	function selectSeclv(seclv){
		if(seclv == ""){
			alert("请选择作业审批密级,以确认审批流程");
			$("#seclv_code").focus();
			return false;
		}else if($("#usage_code").val() != ""){
			selectNextApprover();
		}
	}
	function selectUsage(usage){
		if(usage == ""){
			alert("请选择用途,以确认审批流程");
			$("#usage_code").focus();
			return false;
		}else if($("#seclv_code").val() != ""){
			selectNextApprover();
		}
	}
	function getAjaxResult(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			if(xmlHttp.responseText.indexOf("<SELECT") != -1){
				$("#tr_approver").show();
				document.getElementById("allApprovers").innerHTML=xmlHttp.responseText;
				$("#submit_btn").attr("disabled",false);
			}else{
				$("#tr_approver").hide();
				alert(xmlHttp.responseText);
				$("#submit_btn").attr("disabled",true);
			}
			if($("#next_approver_all").size() > 0 && $("#next_approver_all").children().size() == 0){
				$("#tr_approver").hide();
			}
		}
	}
</script>
</head>
<body oncontextmenu="self.event.returnValue=true">
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
<form action="${ctx}/ledger/handletempjob.action" method="post">
	<input type="hidden" name="addjob" value="Y"/>
	<input type="hidden" name="isDept" value="${isDept}"/>
	<input type="hidden" name="_chk" value="${_chk}"/>
	<input type="hidden" name="jobType" value="${jobType.jobTypeCode}"/>
	<input type="hidden" name="next_approver" id="next_approver"/>
	<input type="hidden" name="paper_barcodes" value="${paper_barcodes}"/>
	<input type="hidden" name="dept_id" value="${curUser.dept_id}"/>
	<input type="hidden" id="entity_type" name="entity_type" value="${entity_type}"/>
<%-- 	<input type="hidden" value="${hidfile_title}"" id="hidfile_title" name="hidfile_title"/>
	<input type="hidden" value="${hidpaper_barcode}" id="hidpaper_barcode" name="hidpaper_barcode"/>
	<input type="hidden" value="${hidkeyword_content}" id="hidkeyword_content" name="hidkeyword_content"/>
	<input type="hidden" value="${hidpaper_state}" id="hidpaper_state" name="hidpaper_state"/>
	<input type="hidden" value="${hidseclv_code}" id="hidseclv_code" name="hidseclv_code"/>
	<input type="hidden" value="${hidstartTime}" id="hidstartTime" name="hidstartTime"/>
	<input type="hidden" value="${hidendTime}" id="hidendTime" name="hidendTime"/>
	<input type="hidden" value="${hidjobType}" id="hidjobType" name="hidjobType"/>
	<input type="hidden" value="${hidexpire_status}" id="hidexpire_status" name="hidexpire_status"/>
	<input type="hidden" name="hidhandle_type" value="paper"/>
	
	<input type="hidden" value="${hidscope_dept_id}" id="hidscope_dept_id" name="hidscope_dept_id"/>
	<input type="hidden" value="${hiddept_name}" id="hiddept_name" name="hiddept_name"/>
	<input type="hidden" value="${hiduser_name}" id="hiduser_name" name="hiduser_name"/>
	<input type="hidden" value="${hiddept_ids}" id="hiddept_ids" name="hiddept_ids"/> --%>
	
	<tr>
	    <td colspan="6" class="title_box">生成审批单</td>
	</tr>
	<tr> 
    	<td width="15%" align="center">申请用户： </td>
    	<td width="35%"><font color="blue"><b>&nbsp;${curUser.user_name}</b></font></td>
    	<td width="15%" align="center">用户部门： </td>
    	<td width="35%"><font color="blue"><b>&nbsp;${curUser.dept_name}</b></font></td>
    </tr>
    <tr>
    	<td align="center"><font color="red">*</font>&nbsp;作业审批密级：</td>
	    <td>
			<select name="seclv_code" id="seclv_code" onchange="selectSeclv(this.value);">
				<option value="">--请选择--</option>
				<s:iterator value="#request.seclvList" var="item">
					<c:if test="${highest_seclv >= item.seclv_rank}">
						<option value="${item.seclv_code}">${item.seclv_name}</option>
					</c:if>
				</s:iterator>
			</select>
		</td>
		<td align="center"><font color="red">*</font>&nbsp;用途： </td>
    	<td><select name="usage_code" id="usage_code" onchange="selectUsage(this.value);">
    			<option value="">--请选择--</option>
    			<s:iterator value="#request.usageList" var="usage">
					<option value="${usage.usage_code}">${usage.usage_name}</option>
				</s:iterator>
    		</select>
    	</td>
	</tr>
	<tr>
		<c:choose>
			<c:when test="${jobType.jobTypeCode.contains('PAPER_MODIFY')}">
				    <td align="center"><font color="red">*</font>文件台账修改页数： </td>
				    <td><input type="text" name="output_dept_name" id="output_dept_name"/></td>
			</c:when>
		</c:choose>
		<c:choose>
			<c:when test="${jobType.jobTypeCode.contains('FILECD_DESTROY') || jobType.jobTypeCode.contains('FILE_DESTROY')}">
				    <td align="center">归属部门：<br><font color="red">（不填默认为本部门）</font></td>
				    <td>
				    	<input type="text"  id="scope_dept_name" name="scope_dept_name" readonly="readonly" style="cursor:hand;" onclick="openDeptSelect('scope_dept_name','scope_dept_id','radio')" />
			      		<input type="hidden" name="scope_dept_id" id="scope_dept_id" />
			      	</td>
			</c:when>
		</c:choose>
		<td align="center">流程类型： </td>
    	<td><font color="blue"><b>${jobType.jobTypeName}</b></font></td>
	</tr>
	<tr>
		<td align="center">备注： </td>
		<td colspan="4"><textarea rows="3" cols="40" name="summ" id="summ"></textarea></td>
	</tr>
</form>
  	<tr id="tr_approver" style="display: none">
  		<td align="center" id="selApprover1">选择审批人：</td>
  		<td id="selApprover2" colspan="5">
  			<table width="300" border="0" cellspacing="0" cellpadding="0" align="left" class="table_box_border_empty">
				<tr>
					<td id="allApprovers">
						<SELECT ondblclick="add_True('next_approver_all','next_approver_sel');" style="WIDTH: 100px" multiple="true" size="8" id="next_approver_all">
							<c:forEach var="item" items="${userList}" varStatus="status">
								<option value="${item.user_iidd}">${item.user_name}</option>
							</c:forEach>
						</SELECT>
					</td>
					<td aling="center" valign="middle">
						<INPUT class="button_2003" onclick="add_MoreTrue('next_approver_all','next_approver_sel');" type="button" value="增加-->" name="btnAddItem" /><br/>
						<br/>
						<INPUT class="button_2003" onclick="del_MoreTrue('next_approver_sel');" type="button" value="删除<--" name="btnDelItem"><br/>
					</td>
					<td>
						<SELECT size="8" multiple="true" style="WIDTH: 100px" ondblclick="del_True('next_approver_sel');" id="next_approver_sel">
						</SELECT>
					</td>
				</tr>
			</table>
		</td>
  	</tr>
  	<tr valign="middle" height="100"> 
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
	    <td colspan="4" align="center">
	    	<input type="button" class="button_2003" value="提交" onclick="chkSubmit(); " id="submit_btn"/>&nbsp;
			<input class="button_2003" type="button" value="返回" onclick="javascript:history.go(-1)">&nbsp;
	    </td>
	</tr>
	<c:if test="${entity_type == 'paper'}">
		<tr>
			<td colspan="6">
				<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
		 			<tr>
		   				<td>
		   					<display:table uid="item" class="displaytable" name="paperList" form="LedgerQueryCondForm" excludedParams="*">
								<display:column title="序号">
									<c:out value="${item_rowNum}"/>
								</display:column>
								<display:column property="file_title" title="文件名" />
								<display:column property="paper_barcode" title="文件条码" />
								<display:column property="seclv_name" title="文件密级" />
								<display:column property="create_type_name" title="制作方式" />
								<display:column property="print_result_name" title="打印结果"/>
								<display:column property="paper_state_name" title="状态"/>
								<display:column property="page_count" title="文件页数"/>
								<display:column property="print_time" sortable="true" title="制作时间"/>
							</display:table>
						</td>
					</tr>
				</table>
	         </td>
		</tr>
	</c:if>	
	<c:if test="${entity_type == 'cd'}">
		<tr>
			<td colspan="6">
				<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
		 			<tr>
		   				<td>
		   					<display:table uid="item" class="displaytable" name="cdList" form="LedgerQueryCondForm" excludedParams="*">
								<display:column title="序号">
									<c:out value="${item_rowNum}"/>
								</display:column>
								<display:column property="file_list" title="文件名" />
								<display:column property="cd_barcode" title="文件条码" />
								<display:column property="seclv_name" title="文件密级" />
								<display:column property="create_type_name" title="制作方式" />
								<display:column property="burn_result_name" title="打印结果"/>
								<display:column property="cd_state_name" title="状态"/>
								<display:column property="burn_time" sortable="true" title="制作时间"/>
							</display:table>
						</td>
					</tr>
				</table>
	         </td>
		</tr>
	</c:if>
</table>
</body>
</html>