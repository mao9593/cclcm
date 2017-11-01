<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<link rel="stylesheet" href="${ctx}/_css/displaytag.css" type="text/css" media="screen" />
    <link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript">
		$(document).ready(function(){
			onHover();
		});
		/**
 		* 弹出清空密码结果的对话框
 		*/
		function showDialogModifyPW(userID){
			if(confirm("确定重置该用户的密码？")){
				mp.location.href = "${ctx}/user/resetuserpwd.action?user_iidd=" + userID;
			}
		}
	</script>
  </head>
  <body> 
  <iframe id="mp" width="0" height="0" ></iframe>
  	<form method="post" action="${ctx}/basic/audresetuserpwd.action" name="SecUserQueryCondForm" id="SecUserQueryCondForm">
    <table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
   	<tr>
		<td class="title_box">&nbsp;&nbsp;重置密码</td>
	</tr>
    <tr>
      <td>
           <display:table requestURI="${ctx}/basic/audresetuserpwd.action" id="user" class="displaytable" name="secUserList">
                <display:column title="序号">
                	<c:out value="${user_rowNum }"></c:out>
                </display:column>
                <display:column title="登录名称">
	                <c:if test="${user.status == 1}">
						<img src="${ctx}/_image/iconx32/stop1.gif" />
					</c:if>
						<c:out value="${user.user_iidd}"/>
				</display:column>
                <display:column title="用户名称" property="user_name" />
                <display:column title="部门" property="dept_name" />
	            <display:column title="角色" property="roleNameStr" maxLength="10"/>
	            <display:column title="涉密人员类别" property="security_name"/>
	            <display:column title="用户卡号" property="pass_num" maxLength="10"/>
                <display:column title="操作" style="width:200px">
					<c:if test="${admin_version == 0 or (admin_version == 1 and display_flag == 'SEC')}">
					<input type="button" class="button_2003" value="重置密码" onClick="showDialogModifyPW('${user.user_iidd}');"/>&nbsp;
					</c:if>
                 </display:column>
          </display:table>
      </td>
    </tr>
  </table>
  </form>
  </body>
</html>
