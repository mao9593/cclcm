<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>查看用户预台帐日志</title>
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
			LogQueryCondForm.console_name.value="";
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
		<td class="title_box">查看用户预台帐日志</td>
	</tr>
	<form id="LogQueryCondForm" method="POST" action="${ctx}/ledger/viewunlockeventlog.action">
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
				 		<td width="10%" align="center">控制台名称：</td>
				 		<td width="25%">
				 			<input type="text" name="console_name" value="${console_name}" size="10"/>
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
					<display:table requestURI="${ctx}/ledger/viewunlockeventlog.action" id="item" class="displaytable" name="eventLogs" sort="page" pagesize="15" excludedParams="*" form="LogQueryCondForm">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column property="user_name" title="用户名" style="whitespace: nowrap;" />
						<display:column property="dept_name" title="部门" style="whitespace: nowrap;" />
						<display:column property="console_name" title="控制台名称" style="whitespace: nowrap;" />
						<display:column property="file_title" title="文件名称" style="whitespace: nowrap;" />
						<display:column property="event_type_name" title="作业类型" style="whitespace: nowrap;" />
						<display:column property="unlock_time_name" title="解锁时间" style="whitespace: nowrap;" />
					</display:table>
					</td>
				</tr>
			</table>
         </td>
	</tr>
 	</form>
 	<tr>
		<td>
			<input type="button" class="button_2003" value="导出EXCEL" onclick="exportLog('LogQueryCondForm','${ctx}/ledger/exportunlockeventlog.action','${ctx}/ledger/viewunlockeventlog.action');"/>
		</td>
	</tr>
</table>
</body>
</html>
