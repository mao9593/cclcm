<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>待办任务列表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
</head>

<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center"  class="table_only_border" >
	<tr>
		<td class="title_box" width="50%">待办任务列表</td>
		<td class="title_box"  width="50%">任务通知列表</td>
	</tr>
	<tr>
		<td align="center" valign="top">
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="50%">
	 			<tr>
	   				<td>
	   				<display:table requestURI="${ctx}/client/clienttask.action?messagetype=1" uid="item" class="displaytable" name="taskList" 
	   		         pagesize="5"  excludedParams="*">
						<display:column property="title" title="申请类型"/>
						<display:column property="insert_time" title="申请时间"/>		
						<display:column title="申请内容">
						<a href="#" onclick="go('${ctx}/${item.url}');">${item.message}</a>			
						</display:column>
					</display:table>
					</td>
				</tr>
			</table>
			</td>
			<td align="center" valign="top">
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="50%">
	 			<tr>
	   				<td>
	   				<display:table requestURI="${ctx}/client/clienttask.action?" uid="item" class="displaytable" name="taskNoteList" 
	   		         pagesize="5"  excludedParams="*">
						<display:column property="title" title="通知类型"/>
						<display:column property="insert_time" title="通知时间"/>		
						<display:column title="通知内容">
						<a href="#" onclick="go('${ctx}/${item.url}');">${item.message}</a>			
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
