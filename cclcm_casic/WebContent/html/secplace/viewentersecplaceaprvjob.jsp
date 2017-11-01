<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ page import="hdsec.web.project.secplace.model.EntityVisitor"%>
<html>
<head>
	<title>查看涉密人员进出害部门部位已审批列表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
    <script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>   
    <script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
 <script>
	
	$(document).ready(function(){
		onHover();
	});
	function clearFindForm(){
		$("#user_name").val("");
		$("#seclv_code").val("");
	}
	
	
	</script>   
</head>

<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">查看历史审批记录</td>
	</tr>	
	<tr>
		<td>
			<form id="QueryCondForm" method="POST" action="${ctx}/secplace/viewentersecplaceaprvjob.action">
			<table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<td width="10%" align="center">申请人姓名</td>
				 	<td width="20%">
				 		<input type="text" id="user_name" value="${user_name}" name="user_name" />
    					
				 	</td>
				 	<td width="10%" align="center">任务密级</td>
				 	<td width="20%">
				        <c:set var="seclv1" value="${seclv_code}" scope="request"/>
						<select name="seclv_code" id="seclv_code">
							<option value="">--所有--</option>
							<s:iterator value="#request.seclvList" var="seclv">
								<option value="${seclv.seclv_code}" <s:if test="#request.seclv1==#seclv.seclv_code">selected="selected"</s:if>>${seclv.seclv_name}</option>
							</s:iterator>
						</select>&nbsp;&nbsp;
			    	</td> 
				    <td align="center">
						&nbsp;<input name="button" type="submit" class="button_2003" value="查询">&nbsp;
						&nbsp;<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">
					</td>
				</tr>
				</table>		
 				</form>
 			</td>
 		</tr>
	 	<tr>
	   		<td>
	   		<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	   			<tr>
	   				<td>
				<display:table requestURI="${ctx}/secplace/viewentersecplaceaprvjob.action" id="item" class="displaytable" name="applyList" 
					pagesize="15" sort="list" excludedParams="*" form="QueryCondForm">
					<display:column title="序号">
						<c:out value="${item_rowNum}"/>
					</display:column>
					<display:column title="申请人" property="user_name"/>
					<display:column title="部门" property="dept_name"/>
					<display:column title="任务类型" property="jobType.jobTypeName"/>
					<display:column title="文件列表" property="event_names" maxLength="40"/>
					<display:column title="密级" property="seclv_name"/>
					<display:column title="申请时间" property="start_time_str" sortable="true"/>
					<display:column title="申请状态" property="job_status_name"/>
					<display:column title="操作" style="width:100px">
						<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/secplace/approveentersecplacejob.action?history=Y&job_code=${item.job_code}');"/>&nbsp;	
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