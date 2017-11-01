<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ page import="hdsec.web.project.secplace.model.EntityVisitor"%>
<html>
<head>
	<title>新增要害部门部位待审批列表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
    <script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>   
    <script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
    </head>

<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">新增要害部门部位待审批列表</td>
	</tr>	
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">	
	 			<tr>
	   				<td>
					<display:table requestURI="${ctx}/secplace/managesecplaceaprvjob.action" id="item" class="displaytable" 
						name="applyList" pagesize="15" sort="list" defaultsort="7"  defaultorder="descending">
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
							<input type="button" class="button_2003" value="审批" onclick="go('${ctx}/secplace/approvesecplacejob.action?job_code=${item.job_code}');"/>&nbsp;	
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