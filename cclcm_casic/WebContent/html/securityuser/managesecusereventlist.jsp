<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
<script>
	$(document).ready(function(){
		onHover();
		preCalendar();
	});
	function clearFindForm(){
		$("#QueryCondForm :text").val("");
		$("#QueryCondForm select").val("");
	}
	function chkCancel(job_code,jobtype_code){
		if(confirm("确定要撤销该申请？")){
			var url = "${ctx}/securityuser/delsecuserevent.action?type=ajax&job_code="+escape(job_code)+"&style="+escape(jobtype_code);
			xmlHttp.open("GET", url, true);
			xmlHttp.onreadystatechange = updatePage;
			xmlHttp.send(null);
		}
	}
	function updatePage() {
		if (xmlHttp.readyState == 4) {
			  var response = xmlHttp.responseText;
			  QueryCondForm.submit();
		}
	}	
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">查看申请记录</td>
	</tr>
	<tr>
		<td align="right">
		<form id="QueryCondForm" method="GET" action="${ctx}/securityuser/managesecusereventlist.action" name="QueryCondForm">
			<table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
					<tr>
						<td width="13%" align="center">作业密级 </td>
				 		<td width="20%">
				 			<c:set var="seclv1" value="${seclv_code}" scope="request"/>
        					<select name="seclv_code">
        						<option value="">--全部--</option>
    							<s:iterator value="#request.seclvList" var="seclv">
									<option value="${seclv.seclv_code}" <s:if test="#request.seclv1==#seclv.seclv_code">selected="selected"</s:if>>${seclv.seclv_name}</option>
								</s:iterator>
    						</select>
				 		</td>
				 		<td width="13%" align="center">申请类型</td>
				 		<td width="20%">
				        	<select name="jobType_code">
    							<option value="">--全部--</option>
    							<option value="USERSECLV_ADD" <c:if test="${jobType_code eq 'USERSECLV_ADD'}">selected</c:if>>新增涉密人员</option>
    							<option value="USERSECLV_CHANGE" <c:if test="${jobType_code eq 'USERSECLV_CHANGE'}">selected</c:if>>涉密人员变更</option>
    							<option value="RESIGN" <c:if test="${jobType_code eq 'RESIGN'}">selected</c:if>>涉密人员脱密/离岗</option>
    							<option value="SECUSER_ABROAD" <c:if test="${jobType_code eq 'SECUSER_ABROAD'}">selected</c:if>>涉密人员因私出国/境</option>
    							<option value="SECUSER_ENTRUST" <c:if test="${jobType_code eq 'SECUSER_ENTRUST'}">selected</c:if>>委托保密管理</option>
    						</select>
			    		</td>
			    		<td width="13%" align="center">申请状态</td>
				 		<td width="20%">
				        	<select name="job_status">
    							<option value="">--全部--</option>
    							<option value="none" <c:if test="${job_status eq 'none'}">selected</c:if>>待审批</option>
    							<option value="under" <c:if test="${job_status eq 'under'}">selected</c:if>>审批中</option>
    							<option value="false" <c:if test="${job_status eq 'false'}">selected</c:if>>已驳回</option>
    							<option value="true" <c:if test="${job_status eq 'true'}">selected</c:if>>已通过</option>
    							<option value="done" <c:if test="${job_status eq 'done'}">selected</c:if>>已关闭</option>
    						</select>
			    		</td>
			    	</tr>	
			    	<tr>
			    		<td align="center">申请时间</td>
				 		<td >
				 			<input type="text" name="startTime" readonly="readonly" style="cursor:hand;" value="${startTime_str}"/>
        					<img src="${ctx}/_image/time2.jpg" id="startTime_trigger">
				 		</td>
				 		<td align="center">至</td>
				 		<td >
				        	<input type="text" name="endTime" readonly="readonly" style="cursor:hand;" value="${endTime_str}"/>
        					<img src="${ctx}/_image/time2.jpg" id="endTime_trigger">
			    		</td>
						<td colspan="2" align="center">
							<input name="button" type="submit" class="button_2003" value="查询";">&nbsp;
							<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">
						</td>	
					</tr>
				</table>
		</td>
	</tr>
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
					<display:table requestURI="${ctx}/securityuser/managesecusereventlist.action" uid="item" class="displaytable" name="jobList" 
					pagesize="15" sort="list"  excludedParams="*" form="QueryCondForm">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column title="申请类型" property="jobType.jobTypeName"/>
						<display:column title="申请人" property="user_name"/>
						<display:column title="申请人部门" property="dept_name"/>	
						<display:column title="密级" property="seclv_name"/>		
						<display:column title="申请时间" property="start_time_str" sortable="true"/>
						<display:column title="申请状态" property="job_status_name"/>
						<display:column title="操作" style="width:100px">
						<div>
							<c:if test="${item.jobType.jobTypeCode == 'USERSECLV_ADD'}">
								<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/securityuser/approveuseclvchangejob.action?history=Y&job_code=${item.job_code}&type=NEW');"/>
							</c:if>
							<c:if test="${item.jobType.jobTypeCode == 'USERSECLV_CHANGE'}">
								<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/securityuser/approveuseclvchangejob.action?history=Y&job_code=${item.job_code}');"/>
							</c:if>
							<c:if test="${item.jobType.jobTypeCode == 'RESIGN'}">
								<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/securityuser/approveuresignjob.action?history=Y&job_code=${item.job_code}');"/>
							</c:if>
							<c:if test="${item.jobType.jobTypeCode == 'SECUSER_ABROAD'}">
								<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/securityuser/approveuabroadjob.action?history=Y&job_code=${item.job_code}');"/>
							</c:if>
							<c:if test="${item.jobType.jobTypeCode == 'SECUSER_ENTRUST'}">				
								<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/securityuser/approveuserentrustjob.action?history=Y&job_code=${item.job_code}');"/>
							</c:if>
 							<c:choose>
								<c:when test="${item.job_status_name == '待审批'}">						
									&nbsp;<input type="button" class="button_2003" value="撤销" onclick="chkCancel('${item.job_code}','${item.jobType.jobTypeCode}');"/>
								</c:when>
								<c:otherwise>
									&nbsp;<input type="button" class="button_2003" value="撤销" disabled="disabled"/>
								</c:otherwise>
							</c:choose> 
						</div>
						</display:column>
					</display:table>
					</td>
				</tr>
			</table>
         </td>
	</tr>
</table>
</form>
</body>
</html>
