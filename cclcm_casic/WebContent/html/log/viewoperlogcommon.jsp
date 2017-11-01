<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>查看普通用户操作日志</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
	<script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
	    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
<script>
<!--
	function clearFindForm(){
			LogQueryCondForm.user_name.value = "";
			LogQueryCondForm.dept_name.value = "";
			LogQueryCondForm.result.value="";
			LogQueryCondForm.startTime.value = "";
			LogQueryCondForm.endTime.value = "";
	}
	function preCalendar(){
		Calendar.setup({inputField: "startTime", button: "startTime_trigger", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d %H:%M:%S", cache: true, weekNumbers:true, showOthers: true, step: 1});
		Calendar.setup({inputField: "endTime", button: "endTime_trigger", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d %H:%M:%S", cache: true, weekNumbers:true, showOthers: true, step: 1});
	}
//-->
</script>
</head>

<body oncontextmenu="self.event.returnValue=false" onload="onHover();preCalendar();">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">查看普通用户操作日志</td>
	</tr>
	<form id="LogQueryCondForm" method="POST" action="${ctx}/log/viewoperlogcommon.action">
	<tr>
		<td class="nav_box" align="right">
			<table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
					<tr>
						<td width="10%" align="center">用户名：</td>
				 		<td width="25%">
				 			<input type="text" name="user_name" value="${user_name}" size="10"/>
				 		</td>
						<td width="10%" align="center">部门名称 ：</td>
				 		<td width="25%">
				 			<input type="text" name="dept_name" value="${dept_name}" size="10"/>
				 		</td>
				 		<td width="10%" align="center">操作结果：</td>
				 		<td width="25%">
				 			<select name="result">
			    				<option value="" selected>全部</option>
        						<option value="成功" <c:if test="${result == '成功'}">selected</c:if>>成功</option>
        						<option value="失败" <c:if test="${result == '失败'}">selected</c:if>>失败</option>
        					</select>
				 		</td>
				 	</tr>
				 	<tr>
				 		<td align="center">时间：</td>
				 		<td >
				        	<input type="text" name="startTime" readonly="readonly" style="cursor:hand;" value="${startTime_str}"/>
        					<img src="${ctx}/_image/time2.jpg" id="startTime_trigger">&nbsp;
			    		</td> 
			    		<td align="center">至：</td>
				 		<td>
				        	<input type="text" name="endTime" readonly="readonly" style="cursor:hand;" value="${endTime_str}"/>
        					<img src="${ctx}/_image/time2.jpg" id="endTime_trigger">&nbsp;
			    		</td> 
				        <td align="center" colspan="2">
							&nbsp;<input name="button" type="submit" class="button_2003" value="查询" onclick="return checkTime();">&nbsp;
							&nbsp;<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">
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
					<display:table requestURI="${ctx}/log/viewoperlogcommon.action" id="commonlog" class="displaytable" name="logList" pagesize="${pageSize}" sort="page" partialList="true" size="${totalSize}" excludedParams="*" form="LogQueryCondForm">
						<display:column title="序号">
							<c:out value="${commonlog_rowNum}"/>
						</display:column>
						<display:column property="user_id" title="用户账号" style="whitespace: nowrap;" />
						<display:column property="user_name" title="用户名" style="whitespace: nowrap;" />
						<display:column property="dept_name" title="部门" style="whitespace: nowrap;" />
						<display:column property="log_detail" title="日志内容" maxLength="30" style="whitespace: nowrap;" />
						<display:column property="result" title="操作结果" style="whitespace: nowrap;" />
					    <display:column property="log_time_str" title="时间" style="whitespace: nowrap;" sortable="true"/>
						<display:column property="login_ip" title="登录IP" style="whitespace: nowrap;" />
					</display:table>
					</td>
				</tr>
			</table>
         </td>
	</tr>
 	</form>
	<tr>
		<td>
			<input type="button" class="button_2003" value="导出EXCEL" onclick="exportLog('LogQueryCondForm','${ctx}/log/exportoperlogcommon.action','${ctx}/log/viewoperlogcommon.action');"/>
		</td>
	</tr>
</table>
</body>
</html>
