<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>待审批任务列表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
</head>

<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">待审批任务列表</td>
	</tr>
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
					<display:table requestURI="${ctx}/securityuser/manageaprvjob.action" uid="job" class="displaytable" 
						name="applyList" pagesize="15" sort="list" defaultsort="7"  defaultorder="descending">
						<display:column title="序号">
							<c:out value="${job_rowNum}"/>
						</display:column>
						<display:column title="任务类型" property="jobType.jobTypeName"/>
						<display:column title="申请人" property="user_name"/>
						<display:column title="部门" property="dept_name"/>				
						<display:column title="密级" property="seclv_name"/>
						<display:column title="申请时间" property="start_time_str" sortable="true"/>
						<display:column title="申请状态" property="job_status_name"/>
						<display:column title="操作" style="width:80px">   
						<c:choose>
							<c:when test="${module eq 'secmanageBMC'}">
								<c:if test="${job.jobType.jobTypeCode == 'SEC_CHECK'}"> 
									<input type="button" class="button_2003" value="审批" onclick="go('${ctx}/secmanage/approveseccheckjob.action?job_code=${job.job_code}');"/>
								</c:if>	
								<c:if test="${job.jobType.jobTypeCode == 'PUNISH_DEPT' or job.jobType.jobTypeCode == 'PUNISH_SECCHECK' or job.jobType.jobTypeCode == 'PUNISH_RECTIFY'}"> 
									<input type="button" class="button_2003" value="审批" onclick="go('${ctx}/secmanage/approvepunishjob.action?job_code=${job.job_code}');"/>
								</c:if>	
							</c:when>
							<c:when test="${module eq 'secmanage'}">
								<c:if test="${job.jobType.jobTypeCode == 'FIELDIN'}"> 
									<input type="button" class="button_2003" value="审批" onclick="go('${ctx}/secmanage/approveresearchfieldinjob.action?job_code=${job.job_code}');"/>
								</c:if>
								<c:if test="${job.jobType.jobTypeCode == 'FILEOUTMAKE'}"> 
									<input type="button" class="button_2003" value="审批" onclick="go('${ctx}/secmanage/approvefileoutmakejob.action?job_code=${job.job_code}');"/>
								</c:if>
								<c:if test="${job.jobType.jobTypeCode == 'USERSEC_ACTIVITY'}"> 
									<input type="button" class="button_2003" value="审批" onclick="go('${ctx}/secactivity/approveusecactijob.action?job_code=${job.job_code}');"/>
								</c:if>
								<c:if test="${job.jobType.jobTypeCode == 'ENTER_SECPLACE'}"> 
									<input type="button" class="button_2003" value="审批" onclick="go('${ctx}/secplace/approveentersecplacejob.action?job_code=${job.job_code}');"/>
								</c:if>
								<c:if test="${job.jobType.jobTypeCode == 'OUT_EXCHANGE'}">
									<input type="button" class="button_2003" value="审批" onclick="go('${ctx}/secactivity/approvesecoutexchangejob.action?job_code=${job.job_code}');"/>
								</c:if>
							</c:when>
							<c:when test="${module eq 'publicity'}">				
								<c:if test="${job.jobType.jobTypeCode == 'EVENT_REPORT' or job.jobType.jobTypeCode == 'EVENT_REPORT2'or job.jobType.jobTypeCode == 'EVENT_REPORT3'or job.jobType.jobTypeCode == 'EVENT_DEPTREPORT' or job.jobType.jobTypeCode == 'EVENT_INTRAPUBL' or job.jobType.jobTypeCode == 'EVENT_INTERPUBL'}"> 
									<input type="button" class="button_2003" value="审批" onclick="go('${ctx}/publicity/approvereportjob.action?job_code=${job.job_code}');"/>
								</c:if>
								<c:if test="${job.jobType.jobTypeCode == 'INTER_EMAIL'}"> 
									<input type="button" class="button_2003" value="审批" onclick="go('${ctx}/secmanage/approveinternetemailjob.action?job_code=${job.job_code}');"/>
								</c:if>
								<c:if test="${job.jobType.jobTypeCode == 'MATERIAL'}"> 
									<input type="button" class="button_2003" value="审批" onclick="go('${ctx}/secmanage/approveexchangematerialjob.action?job_code=${job.job_code}');"/>
								</c:if>
								<c:if test="${job.jobType.jobTypeCode == 'EXHIBITION'}"> 
									<input type="button" class="button_2003" value="审批" onclick="go('${ctx}/secmanage/approveexhibitionjob.action?job_code=${job.job_code}');"/>
								</c:if>
								<c:if test="${job.jobType.jobTypeCode == 'PAPERPATENT'}"> 
									<input type="button" class="button_2003" value="审批" onclick="go('${ctx}/secmanage/approvepaperpatentjob.action?job_code=${job.job_code}');"/>
								</c:if>
								<c:if test="${job.jobType.jobTypeCode == 'PAPER_OTHERS'}"> 
									<input type="button" class="button_2003" value="审批" onclick="go('${ctx}/secmanage/approvepaperpatentjob.action?job_code=${job.job_code}');"/>
								</c:if>
								<c:if test="${job.jobType.jobTypeCode == 'PAPER_RESEARCH'}"> 
									<input type="button" class="button_2003" value="审批" onclick="go('${ctx}/secmanage/approvepaperpatentjob.action?job_code=${job.job_code}');"/>
								</c:if>
							</c:when>
							<c:otherwise>			 		
					 			<c:if test="${job.jobType.jobTypeCode == 'USER_INFO'}"> 
									<input type="button" class="button_2003" value="审批" onclick="go('${ctx}/securityuser/approveuserinfojob.action?job_code=${job.job_code}');"/>
								</c:if>				
								<c:if test="${job.jobType.jobTypeCode == 'USERSECLV_ADD' or job.jobType.jobTypeCode == 'USERSECLV_CHANGE'}"> 
									<input type="button" class="button_2003" value="审批" onclick="go('${ctx}/securityuser/approveuseclvchangejob.action?job_code=${job.job_code}');"/>
								</c:if>
								<c:if test="${job.jobType.jobTypeCode == 'RESIGN'}"> 
									<input type="button" class="button_2003" value="审批" onclick="go('${ctx}/securityuser/approveuresignjob.action?job_code=${job.job_code}');"/>
								</c:if>
								<c:if test="${job.jobType.jobTypeCode == 'SECUSER_ABROAD'}"> 
									<input type="button" class="button_2003" value="审批" onclick="go('${ctx}/securityuser/approveuabroadjob.action?job_code=${job.job_code}');"/>
								</c:if>					
								<c:if test="${job.jobType.jobTypeCode == 'SECUSER_ENTRUST'}"> 
									<input type="button" class="button_2003" value="审批" onclick="go('${ctx}/securityuser/approveuserentrustjob.action?job_code=${job.job_code}');"/>
								</c:if>	
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
</body>
</html>
