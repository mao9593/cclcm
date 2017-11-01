<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>版本信息</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
		<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
	<link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
	<script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script>
	</script>
</head>

<body oncontextmenu="self.event.returnValue=false">
<form id="LedgerQueryCondForm" method="POST" action="${ctx}/basic/viewversionfile.action">
	<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">版本信息</td>
	</tr>
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
	   				<display:table requestURI="${ctx}/basic/viewversionfile.action" id="item" class="displaytable" name="versions"  
					pagesize="15" sort="list" excludedParams="*" form="LedgerQueryCondForm">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column property="file_name" title="文件名"/>
						<display:column property="prod_num_name" title="产品编号"/>
						<display:column property="version" title="版本"/>
						<display:column property="create_user_name" title="上传人"/>
						<display:column property="create_time" title="上传时间"/>
						<display:column property="update_user_name" title="更新人"/>
						<display:column property="update_time" title="更新时间"/>
						<display:column property="comment" title="备注" maxLength="30"/>
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
