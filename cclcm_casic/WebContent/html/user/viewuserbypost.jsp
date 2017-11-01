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
		<td class="title_box">用户列表(岗位：${post_name})</td>
	</tr>
	<tr height="285">
		<td valign="top">
<div style="overflow:auto;height:100%;width:100%">
			<display:table requestURI="" uid="user" name="secUser" class="displaytable">
                <display:column title="序号">
						<c:out value="${user_rowNum}"/>
				</display:column>
                <display:column title="登录名称" sortable="true" >
	               	<c:if test="${user.status == 1}">
						<img src="${ctx}/_image/iconx32/stop1.gif" />
					</c:if>
	                <c:out value="${user.user_iidd}"/>
				</display:column>
                 <display:column title="用户名称"  property="user_name"/>
                 <display:column title="部门"  property="dept_name"/>
                 <display:column title="岗位"  property="post_name"/>
                 <display:column title="移动电话"  property="com_mobile"/>
                 <display:column title="固定电话"  property="com_telephone"/>
                 <display:column title="电子邮件"  property="com_email"/>
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