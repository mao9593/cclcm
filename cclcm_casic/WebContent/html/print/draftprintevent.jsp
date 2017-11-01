<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>提交审批</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
    <script>
    $(document).ready(function(){
		onHover();
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
		<td class="title_box">修改打印作业列表</td>
	</tr>
	<tr>
		<td class="nav_box" align="right">
			<form id="QueryCondForm" method="POST" action="${ctx}/print/draftprintevent.action">
				文件名：
				<input type="text" name="file_title" style="cursor:hand;" value="${file_title}"/>
				提交时间：
				<input type="text" name="startTime" readonly="readonly" style="cursor:hand;" value="${startTime_str}"/>
				<img src="${ctx}/_image/time2.jpg" id="startTime_trigger">&nbsp;
				至：
				<input type="text" name="endTime" readonly="readonly" style="cursor:hand;" value="${endTime_str}"/>
				<img src="${ctx}/_image/time2.jpg" id="endTime_trigger">&nbsp;&nbsp;
				<input name="button" type="submit" class="button_2003" value="查询" onclick="return checkTime();">&nbsp;
				<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">
	 		</form>
		</td>
	</tr>
	<tr>
		<td valign="top">
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
					<display:table requestURI="${ctx}/print/draftprintevent.action" uid="item" class="displaytable" name="eventList" pagesize="15" sort="list">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column title="文件名称" property="file_title" maxLength="40"/>				
						<display:column title="提交时间" property="apply_time_str"   sortable="true"/>			
						<display:column title="页数" property="page_count"/>
						<display:column title="份数" property="print_count"/>
						<display:column title="文件大小(B)" property="file_size"/>
						<display:column title="操作" style="width:150px">				
							<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/print/viewprinteventdetail.action?id=${item.id}');"/>&nbsp;
							<input type="button" class="button_2003" value="修改" onclick="go('${ctx}/print/updateprintdraft.action?id=${item.id}');"/>&nbsp;
							<input type="button" class="button_2003" value="删除" onclick="if(confirm('确定要删除该作业？'))go('${ctx}/print/delprintevent.action?key=byId&id=${item.id}');"/>
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
