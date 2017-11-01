<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>涉密人员脱密（离岗）申请记录</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
<script>
	/* $(document).ready(function(){
		onHover();
		preCalendar();
	}); */
	function clearFindForm(){
		$("#QueryCondForm :text").val("");
		$("#QueryCondForm select").val("");
	}
	function chkCancel(event_code){
		if(confirm("确定要撤销该申请？")){
			var url = "${ctx}/securityuser/deluresignevent.action?type=ajax&event_code="+escape(event_code);
			xmlHttp.open("GET", url, true);
			xmlHttp.onreadystatechange = updatePage;
			xmlHttp.send(null);
		}
	}
	function chkChange(event_code){
		if(confirm("确定要变更该用户涉密等级？")){
			var url="${ctx}/securityuser/updateuresign.action?event_code="+escape(event_code);
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

		<td class="title_box">涉密人员脱密（离岗）申请列表</td>

	</tr>
	<td align="right">
	<form id="QueryCondForm" method="GET" action="${ctx}/securityuser/manageresignlist.action" name="QueryCondForm">
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
					<display:table requestURI="${ctx}/securityuser/manageresignlist.action" uid="item" class="displaytable" name="eventList" 
					pagesize="15" sort="list"  excludedParams="*" form="QueryCondForm">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column title="发起人" property="user_name"/>
						<display:column title="发起人部门" property="dept_name"/>
						<display:column title="离职人员" property="resign_user_name"/>
						<display:column title="离职人员部门" property="resign_dept_name"/>
						<display:column title="脱密(离岗)原因" property="reason_name"/>
						<display:column title="申请状态" property="job_status_name"/>
						<display:column title="申请时间" property="apply_time_str" sortable="true"/>
						<display:column title="操作" style="width:150px">
							<div>
								<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/securityuser/approveuresignjob.action?history=Y&job_code=${item.job_code}');"/>
								<c:choose>
									<c:when test="${item.job_status_name != '待审批'}">
										&nbsp;<input type="button" class="button_2003" value="撤销" disabled="disabled"/>
									</c:when>
									<c:otherwise>
										&nbsp;<input type="button" class="button_2003" value="撤销" onclick="chkCancel('${item.event_code}');"/>
									</c:otherwise>
								</c:choose>
							</div>
						</display:column>
					</display:table>
					</td>
				</tr>
			</table>
		</form>
		</td>
</table>
</body>
</html>
