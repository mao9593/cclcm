<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>计算机申请待审批列表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">计算机申请待审批列表</td>
	</tr>
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
					<display:table requestURI="${ctx}/computer/managecomputeraprvjob.action" id="item" class="displaytable" 
						name="applyList" pagesize="15" sort="list" defaultsort="7"  defaultorder="descending">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column title="申请人" property="user_name"/>
						<display:column title="部门" property="dept_name"/>
						<display:column title="任务类型" property="jobType.jobTypeName"/>
						<%-- <display:column title="申请类型" property="event_names" maxLength="40"/> --%>
						<display:column title="密级" property="seclv_name"/>
						<display:column title="申请时间" property="start_time_str" sortable="true"/>
						<display:column title="申请状态" property="job_status_name"/>
						<display:column title="操作" style="width:100px">
							<c:if test="${item.jobType.jobTypeCode == 'BORROW_BOOK' || item.jobType.jobTypeCode == 'BORROW_BOOKOUT'}">
								<input type="button" class="button_2003" value="审批" onclick="go('${ctx}/computer/approveborrowbookjob.action?job_code=${item.job_code}');"/>
							</c:if>
							<c:if test="${item.jobType.jobTypeCode == 'EVENT_REPCOM'}">
								<input type="button" class="button_2003" value="审批" onclick="go('${ctx}/infosystem/approvecomputerrepairjob.action?job_code=${item.job_code}');"/>&nbsp;
							</c:if>
							<c:if test="${item.jobType.jobTypeCode == 'EVENT_REINSTALL'}">
								<input type="button" class="button_2003" value="审批" onclick="go('${ctx}/infosystem/approvereinstallsystemjob.action?job_code=${item.job_code}');"/>&nbsp;
							</c:if>
							<c:if test="${item.jobType.jobTypeCode == 'EVENT_QUITINT'}">
								<input type="button" class="button_2003" value="审批" onclick="go('${ctx}/infosystem/approvequitinternetjob.action?job_code=${item.job_code}');"/>&nbsp;
							</c:if>
							<c:if test="${item.jobType.jobTypeCode == 'EVENT_USBKEY'}">
								<input type="button" class="button_2003" value="审批" onclick="go('${ctx}/infosystem/approveusbkeyjob.action?job_code=${item.job_code}');"/>&nbsp;
							</c:if>
							<c:if test="${item.jobType.jobTypeCode == 'EVENT_OPENPORT'}">
								<input type="button" class="button_2003" value="审批" onclick="go('${ctx}/infosystem/approveopenportjob.action?job_code=${item.job_code}');"/>&nbsp;
							</c:if>
							<c:if test="${item.jobType.jobTypeCode == 'EVENT_LOCALPRINTER'}">
								<input type="button" class="button_2003" value="审批" onclick="go('${ctx}/infosystem/approvelocalprinterjob.action?job_code=${item.job_code}');"/>&nbsp;
							</c:if>
							<c:if test="${item.jobType.jobTypeCode == 'EVENT_INTCOM'}">
								<input type="button" class="button_2003" value="审批" onclick="go('${ctx}/computer/approveinternetcomputerjob.action?job_code=${item.job_code}');"/>&nbsp;
							</c:if>
							<c:if test="${item.jobType.jobTypeCode == 'EVENT_SINCOM'}">
								<input type="button" class="button_2003" value="审批" onclick="go('${ctx}/computer/approvesinglecomputerjob.action?job_code=${item.job_code}');"/>&nbsp;
							</c:if>
							<c:if test="${item.jobType.jobTypeCode == 'EVENT_CHGCOM'}">
								<input type="button" class="button_2003" value="审批" onclick="go('${ctx}/computer/approvechangecomputerjob.action?job_code=${item.job_code}');"/>&nbsp;
							</c:if>
							<c:if test="${item.jobType.jobTypeCode == 'EVENT_DESCOM'}">
								<input type="button" class="button_2003" value="审批" onclick="go('${ctx}/computer/approvedestorycomputerjob.action?job_code=${item.job_code}');"/>&nbsp;
							</c:if>							
							<c:if test="${item.jobType.jobTypeCode == 'BOOK_CHANGE' || item.jobType.jobTypeCode == 'BOOK_REPAIR' || item.jobType.jobTypeCode == 'BOOK_DES' || item.jobType.jobTypeCode == 'BOOK_REINSTALL'}">
								<input type="button" class="button_2003" value="审批" onclick="go('${ctx}/computer/approvebookjob.action?job_code=${item.job_code}');"/>&nbsp;
							</c:if>							
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