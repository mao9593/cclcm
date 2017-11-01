<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>空白盘录入任务列表</title>
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
		$("#seclv_code").val("${seclv_code}");
		preCalendar();
	});
	function clearFindForm(){
		$("#QueryCondForm :text").val("");
		$("#QueryCondForm select").val("");
	}
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">空白盘录入任务列表</td>
	</tr>
	<tr>
		<td align="right">
			<form id="QueryCondForm" method="GET" action="${ctx}/disc/managespacecdenterlistjob.action" name="QueryCondForm">
			<input type="hidden" id="entertype" name="entertype" value="${entertype}"/>
				<table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
					<tr>
						<td width="15%" align="center">密级 ：
				 			<select name="seclv_code" id="seclv_code">
        					<option value="">--全部--</option>
    						<s:iterator value="#request.seclvList" var="seclv">
								<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
							</s:iterator>
    						</select>
				 		</td>
				 		<td width="28%" align="center">申请时间：
				        	<input type="text" name="startTime" readonly="readonly" style="cursor:hand;" value="${startTime_str}"/>
        					<img src="${ctx}/_image/time2.jpg" id="startTime_trigger">&nbsp;
			    		</td> 
			    		<td width="28%" align="center">至：
				        	<input type="text" name="endTime" readonly="readonly" style="cursor:hand;" value="${endTime_str}"/>
        					<img src="${ctx}/_image/time2.jpg" id="endTime_trigger">&nbsp;
			    		</td> 
				        <td align="center">
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
					<display:table requestURI="${ctx}/disc/managespacecdenterlistjob.action" uid="item" class="displaytable" name="spacecds" 
					pagesize="15" sort="list" excludedParams="*" form="QueryCondForm">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column title="用户名" property="user_name"/>
						<display:column title="部门" property="dept_name"/>
						<display:column title="密级" property="seclv_name"/>
						<display:column title="数量" property="enter_num"/>
					<%-- 	<display:column title="流水号" property="enter_code"/> --%>
						<display:column title="申请时间" property="apply_time_str"/>
						<%-- <display:column title="操作" style="width:50px">
							<div>
								<input type="button" class="button_2003" value="查看"/>
							</div>
						</display:column> --%>
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
