<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>待关闭的错误流程列表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
<script>
<!--
	$(document).ready(function(){
		onHover();
		$("#seclv_code").val("${seclv_code}");
		preCalendar();
	});
	function clearFindForm(){
		$("#QueryCondForm :text").val("");
		$("#QueryCondForm select").val("");
	}
	function chkClose(job_code){
		if(confirm("确定要关闭该流程？")){
			go("${ctx}/basic/closewrongjob.action?job_code="+escape(job_code));
		}
	}
//-->
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">待关闭的错误流程列表</td>
	</tr>
	<tr>
		<td align="right">
		<form id="QueryCondForm" method="POST" action="${ctx}/basic/viewclosejob.action">
			<table  table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<td align="center">申请人：
						<input type="text" id="user_name" name="user_name" value="${user_name}" size="15"/>&nbsp;
					</td>		
					<td align="center">密级 ：				
						<select name="seclv_code" id="seclv_code">
        					<option value="">--全部--</option>
    						<s:iterator value="#request.seclvList" var="seclv">
								<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
							</s:iterator>
    					</select>
					</td>
					<td align="center">申请时间：
						<input type="text" name="startTime" readonly="readonly" style="cursor:hand;" value="${startTime_str}"/>
        				<img src="${ctx}/_image/time2.jpg" id="startTime_trigger">&nbsp;
					</td>
					<td align="center">至：
						<input type="text" name="endTime" readonly="readonly" style="cursor:hand;" value="${endTime_str}"/>
        				<img src="${ctx}/_image/time2.jpg" id="endTime_trigger">&nbsp;
					</td>			
					<td align="center"> 
						<input name="button" type="submit" class="button_2003" value="查询" onclick="return checkTime();">&nbsp;
						<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">
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
					<display:table requestURI="${ctx}/basic/viewclosejob.action" uid="item" class="displaytable" name="jobList" pagesize="${pageSize}" sort="page" partialList="true" size="${totalSize}" form="QueryCondForm" excludedParams="*">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column title="申请人" property="user_name"/>
						<display:column title="部门" property="dept_name"/>
						<display:column title="流程类型" property="jobType.jobTypeName"/>
						<display:column title="密级" property="seclv_name"/>
						<display:column title="说明" property="comment" maxLength="40"/>
						<display:column title="申请时间" property="start_time_str"  sortable="true"/>
						<display:column title="操作" style="width:100px">
							<div>
								<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/basic/viewclosejobdetail.action?job_code=${item.job_code}');"/>&nbsp;
								<c:choose>
									<c:when test="${item.job_status eq 'under'}">
										<input type="button" class="button_2003" value="关闭" onclick="chkClose('${item.job_code}');"/>
									</c:when>
									<c:otherwise>
										<input type="button" class="button_2003" value="关闭" disabled="disabled"/>
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
</body>
</html>
