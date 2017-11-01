<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>在线用户列表</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table cellspacing=1 cellpadding=1 border=0 width="100%">
    <tr>
	<td	class="title_box" colspan=2>在线用户列表</td>
  </tr>
    <tr>
      <td valign="top">
  			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0>
    		<tr>
      		<td>
           <display:table requestURI="${ctx}/user/viewonlineuser.action" uid="user" name="onlineUserMap" class="displaytable" defaultsort="6" defaultorder="descending" pagesize="15" sort="list">
                 <display:column title="登录名称" sortable="true" >
					<div style="cursor:hand;color:blue" onMouseOver="this.style.color='red';" onMouseOut="this.style.color='blue';" onClick="window.open('${ctx}/user/viewuserdetail.action?user_iidd=${user.userID}', '_blank', 'height=630,width=530,location=no,menubar=no,resizable=no,status=no,titlebar=no,toolbar=no,scrollbars=yes');" >
						${user.userID}
					</div>
				 </display:column>
                 <display:column title="会话ID" property="sessionID" sortable="true" />
                 <display:column title="用户名称" property="userName" sortable="true" />
                 <display:column title="部门名称" property="deptName" sortable="true" />
                 <display:column title="登录IP" property="loginIP" sortable="true" />
                 <display:column title="登录时间" property="loginTimeString" sortable="true" />
                 <display:column title="最后访问时间" property="lastAccessTimeString" sortable="true" />
          </display:table>
          </td>
          </tr>
          </table>
      </td>
    </tr>
  </table>
        <div style="width:100%;text-align:center;">当前共&nbsp;<span style="color:red;font-size:14pt"><c:out value="${user_rowNum}" /></span>&nbsp;用户在线</div>
</body>
</html>