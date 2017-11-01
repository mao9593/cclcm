<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/print.css"  media="print"/>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="JavaScript" src="${ctx}/_script/function.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common_bm.js"></script>
	<script language="javascript" src="${ctx}/_script/jquery.jqprint-0.3.js"></script>
<script>
$(document).ready(function(){
	prepareBizApproval("${process.step_dept}","${process.step_role}","${process.step_dept_name}","${process.step_role_name}","${ctx}");
	if($("#next_approver_all").children().size() == 0){
		$("#selApprover1").hide();
		$("#selApprover2").hide();
	}
	$("#submit_btn").attr("disabled",false);
	if("${history}" == "Y"){
		viewOpinion("");//判断审批到哪一步
	}else{
		viewOpinion("read");//判断审批到哪一步
	}	
	
	$("#page_print").click(function(){
		$(".printContent").jqprint();
	})	
});
function chk(){
	subOpinion();//提交时将审批意见复制给opinion
	if($("#opinion").val().trim() == ""){
		alert("审批意见不能为空");
		$("#opinion").focus();
		return false;
	}
	if($("#next_approver_all option").size() > 0 && $("#next_approver_sel option").size() == 0 && $("#approved")[0].checked){
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
	return true;
}
function changeApproved(value){
	if(value == "true"){
		$("#opinion").val("同意");
	}else if(value == "false"){
		$("#opinion").val("不同意");
	}
}
function downloadFile(file_path,file_name){
	document.getElementById("file_path").value=file_path;
	document.getElementById("file_name").value=file_name;
	document.getElementById("uploadForm").submit();
}
function getFrameReturn(){
}

</script>
</head>
<div class="printContent">
<body oncontextmenu="self.event.returnValue=false">
<form action="${ctx}/burn/downloadfile.action" target="hidden_frame" id="uploadForm" method="post">
	<input type="hidden" name="file_path" id="file_path"/>
	<input type="hidden" name="file_name" id="file_name"/>
</form>
<iframe height="0" width="0" frameborder="0" id="hidden_frame" name="hidden_frame" onload="getFrameReturn();"></iframe>
<form action="${ctx}/publicity/approvereportjob.action" method="post" >
	<input type="hidden" name="next_approver" id="next_approver"/>
	<input type="hidden" name="job_code" value="${job.job_code}"/>
	<input type="hidden" id="listSize" name="listSize" value="${listSize}"/> 
     <input type="hidden" id="opinion_all" name="opinion_all" value="${opinion_all}"/> 
    <%-- <input type="hidden" name="event_code" id="event_code" value="${event.event_code}"/> --%> 
    <input type="hidden" id="opinion" name="opinion"/> 
   <%--  <input type="hidden" id="types" name="types" value="${types}"/> --%>
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">	
	<tr>
	<c:if test="${event.apply_type == 1}">
	    <td colspan="6" class="title_box">宣传报道保密审查审批表</td>
	</c:if>
	<c:if test="${event.apply_type == 2}">
		<td colspan="6" class="title_box">部门投稿保密审查审批表</td>
	</c:if>  
	<c:if test="${event.apply_type == 3}">
		<td colspan="6" class="title_box">内网信息发布保密审查审批表</td>
	</c:if>
	<c:if test="${event.apply_type == 4}">
		<td colspan="6" class="title_box">外网信息发布保密审查审批表</td>
	</c:if>     
	</tr>
	
	<!-- <tr>
	    <td colspan="6" class="title_box">查看任务详情</td>
	</tr> -->
	<tr> 
    	<td width="10%" align="center">申请用户 </td>
    	<td width="23%"><font color="blue"><b>${job.user_name}</b></font></td>
    	<td width="10%" align="center">用户部门 </td>
    	<td width="23%"><font color="blue"><b>${job.dept_name}</b></font></td>
    	<td width="10%" align="center">业务类型</td>
    	<td width="23%"><font color="blue"><b>${job.jobType.jobTypeName}</b></font></td>
	</tr>
	<tr>
		<td align="center">联系电话</td>
    	<td><font color="blue"><b>${event.user_phone}</b></font></td>
		<td align="center">申请状态 </td>
    	<td><font color="blue"><b>${job.job_status_name}</b></font></td> 
    	<td align="center">业务密级 </td>
    	<td><font color="blue"><b>${job.seclv_name}</b></font></td>
	</tr>
   <tr>
		<c:if test="${event.apply_type != 1}">
		<td align="center">发布信息标题 </td>
    	<td><font color="blue"><b>${event.report_name}</b></font></td> 
    	</c:if>
    	<c:if test="${event.apply_type == 1}">
    	<td align="center">去向 </td>
    	<td><font color="blue"><b>${report_style_name}&nbsp;</b></font></td>
    	<td align="center">备注 </td>
    	<td colspan="4"><font color="blue"><b>${event.summ}&nbsp;</b></font></td>
    	</c:if>
    	 <c:if test="${event.apply_type != 1}">
 <%--    	<td align="center">&nbsp;资料内容 </td>
    	<td colspan="3"><font color="blue"><b>${event.report_description}</b></font></td> --%>
    	 
      <td colspan="4">&nbsp;</td> 
      </c:if>
	</tr>
<%--      <c:if test="${event.apply_type == 1}">
     <tr>
  		<td align="center">&nbsp;资料内容</td>
		<td colspan="5"><font color="blue"><b>${event.report_description}</b></font></td>
  	 </tr>
  	 </c:if> --%>
     <tr>
  		<td align="center">报道附件</td>
  		<td colspan="5">
  			<table width="100%" border="0" cellspacing="0" cellpadding="0" align="left" >
				<tr>
				   <td align="center">文件名</td>
				   <td align="center">文件密级</td>
				   <!-- <td align="center">是否含有图片</td>  -->
				</tr>		
	  			<s:iterator value="#request.burnFileList" var="burnFile">
	  				<tr>
	  					<td align="center"><u><label style="color: blue;cursor: pointer;" onclick="downloadFile('${burnFile.file_path}','${burnFile.file_name}');">${burnFile.file_name}</label></u></td>
	  					<td align="center"><u><label style="color: blue;cursor: pointer;">${job.seclv_name}</label></u></td>
	  				</tr>
	  		    </s:iterator>
  			</table>
  		</td>
  	</tr>
  	<c:if test="${history eq 'Y'}">
		<tr>
	  		<td align="center">下一步骤审批人</td>
	  		<td colspan="5"><font color="blue"><b>&nbsp;${job.next_approver_name}</b></font></td>
	  	</tr>
  	</c:if>
   <c:if test="${event.apply_type == 1 and job.jobType== 'EVENT_REPORT'}">
   	<tr>
		<td align="center">党群处审核意见</td>
			<td colspan="5" id="step1">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion1" rows="4" cols="100" readonly="readonly">注：此处审核包括保密审查</textarea></td></tr>
					<tr><td id="hidden1"></tr>
				</table>
			</td>
	</tr>
	 <tr>
		<td align="center">党群处办理</td>
			<td colspan="5" id="step2">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion2" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden2"></td></tr>
				</table>
			</td>
	</tr>
	</c:if>
	<c:if test="${event.apply_type == 1 and job.jobType== 'EVENT_REPORT2'}">
		<tr>
		<td align="center">党群处审核意见</td>
			<td colspan="5" id="step1">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion1" rows="4" cols="100" readonly="readonly">注：此处审核包括保密审查</textarea></td></tr>
					<tr><td id="hidden1"></tr>
				</table>
			</td>
	</tr>
	 <tr>
		<td align="center">所领导审查意见</td>
			<td colspan="5" id="step2">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion2" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden2"></td></tr>
				</table>
			</td>
	</tr>
	 <tr>
		<td align="center">党群处办理</td>
			<td colspan="5" id="step3">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion3" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden3"></td></tr>
				</table>
			</td>
	</tr>
	</c:if>
	<c:if test="${event.apply_type == 1 and job.jobType== 'EVENT_REPORT3'}">
    
    <tr>
		<td align="center">党群处审核意见</td>
			<td colspan="5" id="step1">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion1" rows="4" cols="100" readonly="readonly">注：此处审核包括保密审查</textarea></td></tr>
					<tr><td id="hidden1"></tr>
				</table>
			</td>
	</tr>
	 <tr>
		<td align="center">保密处审查意见</td>
			<td colspan="5" id="step2">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion2" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden2"></td></tr>
				</table>
			</td>
	</tr>
	 <tr>
		<td align="center">所领导审查意见</td>
			<td colspan="5" id="step3">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion3" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden3"></td></tr>
				</table>
			</td>
	</tr>
	 <tr>
		<td align="center">党群处办理</td>
			<td colspan="5" id="step4">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion4" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden4"></td></tr>
				</table>
			</td>
	</tr>
	</c:if>
	<c:if test="${event.apply_type == 2}">
	<tr>
		<td align="center">部门通讯员意见</td>
			<td colspan="5" id="step1">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion1" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden1"></td></tr>
				</table>
			</td>
	</tr>
    <tr>
		<td align="center">所属部门领导审查意见</td>
			<td colspan="5" id="step2">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion2" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden2"></td></tr>
				</table>
			</td>
	</tr>
	 <tr>
		<td align="center">党群工作部备案</td>
			<td colspan="5" id="step3">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion3" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden3"></td></tr>
				</table>
			</td>
	</tr>
	</c:if>
	<c:if test="${event.apply_type == 3}">
	<tr>
		<td align="center">部门审批意见</td>
			<td colspan="5" id="step1">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion1" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden1"></td></tr>
				</table>
			</td>
	</tr>
	</c:if>
	<c:if test="${event.apply_type == 4}">
	<tr>
		<td align="center">部门审批意见</td>
			<td colspan="5" id="step1">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion1" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden1"></td></tr>
				</table>
			</td>
	</tr>
    <tr>
		<td align="center">保密处审批意见</td>
			<td colspan="5" id="step2">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion2" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden2"></td></tr>
				</table>
			</td>
	</tr>
	 <tr>
		<td align="center">公司分管领导审批意见</td>
			<td colspan="5" id="step3">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion3" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden3"></td></tr>
				</table>
			</td>
	</tr>
        <tr>
		<td align="center">信息中心办理</td>
			<td colspan="5" id="step4">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion4" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden4"></td></tr>
				</table>
			</td>
	</tr>
	</c:if>
	  <tr>
  		<td align="center" id="selApprover1">选择审批人</td>
  		<td id="selApprover2" colspan="2">
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
						<INPUT class="button_2003" onclick="add_MoreTrue('next_approver_all','next_approver_sel');" type="button" value="增加-->" /><br/>
						<br/>
						<INPUT class="button_2003" onclick="del_MoreTrue('next_approver_sel');" type="button" value="删除<--"><br/>
						<br/>
						<INPUT class="button_2003" onclick="add_all_True('next_approver_all','next_approver_sel');" type="button" value="全部增加" name="btnAddItem" /><br/>
						<br/>
						<INPUT class="button_2003" onclick="del_all_True('next_approver_sel');" type="button" value="全部删除" name="btnDelItem"><br/>
					</td>
					<td>
						<SELECT size="8" multiple="true" style="WIDTH: 100px" ondblclick="del_True('next_approver_sel');" id="next_approver_sel">
						</SELECT>
					</td>
				</tr>
			</table>
		</td>
  	</tr>
  </div>
  	<tr class="Noprint">
	    <td colspan="6" align="center">
	    	<c:if test="${history != 'Y'}">
	     	<input class="button_2003" type="submit" value="提交" onclick="return chk();" id="submit_btn" disabled="disabled">&nbsp;
	     	</c:if>
			<input class="button_2003" type="button" value="返回" onClick="javascript:history.go(-1);">&nbsp;
			<c:if test="${history == 'Y'}">
			<input type="button" class="button_2003" value="打印" id="page_print" />
			</c:if>
	    </td>
  	</tr>
</table>
</form>
</body>

</html>
