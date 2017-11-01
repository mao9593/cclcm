<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>常用文档下载</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
</head>

<body oncontextmenu="self.event.returnValue=false">
<table width="80%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">文档列表</td>
	</tr>
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
					<display:table requestURI="${ctx}/basic/downloadfile.action" id="item" class="displaytable" name="fileList">
						<display:column title="序号" style="width:100px;">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column property="file_name" title="文档名称" maxLength="50"/>
						<display:column title="下载">
							<a href="${ctx}/${item.file_path}"><u>下载</u></a>
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
