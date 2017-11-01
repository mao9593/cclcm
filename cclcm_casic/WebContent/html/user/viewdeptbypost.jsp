<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Pragma" content="No-cache">
<meta http-equiv="Cache-Control" content="no-cache, must-revalidate">
<meta http-equiv="Expires" content="-1">
<link rel="stylesheet" href="${ctx}/_css/displaytag.css" type="text/css" media="screen" />
<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
</head>
<body oncontextmenu="self.event.returnValue=false">
<table border="0" class="displaytable-outter" cellspacing="0" cellpadding="0" width="100%" height="370">
	<tr height="25">
		<td class="title_box">部门列表(岗位：${post_name})</td>
	</tr>
	<tr height="285">
		<td valign="top">
		<div style="overflow:auto;height:100%;width:100%">
				<display:table requestURI="" uid="dept" name="deptNameList" class="displaytable">
	                <display:column title="序号" >
							<c:out value="${dept_rowNum}"/>
					</display:column>
	                 <display:column title="部门">
	                 		${dept}
	                 </display:column>
				</display:table>
		</div>
		</td>
	</tr>
	<tr>
		<td align="center" class="bottom_box">
			<input type="button" class="button_2003" value="关闭" onclick="window.close();"/>
		</td>
	</tr>
	<tr/>
</table>
</body>
</html>