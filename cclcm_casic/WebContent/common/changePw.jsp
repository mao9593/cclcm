<%@ page language="java" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
	<title>修改密码</title>
	<link href="<%=request.getContextPath()%>/_css/css200603.css" type="text/css" rel="stylesheet"/>
	<LINK href="<%=request.getContextPath()%>/_css/my-web-control.jsp" rel="stylesheet" type="text/css"/>
</head>
<body>
<div align="center" >
  <form action="/changePw.do?action=do" method="post">
  <table width="80%" class="table_box">
	<tr>
		<td colspan="2" class="title_box">修改密码</td>
	</tr>
	<tr>
		<td>提示</td>
		<td><font color="red"><%=request.getAttribute("message")%></font></td>
	</tr>
	<tr>
		<td>用户名</td>
		<td><%=request.getAttribute("cp_user_name")%></td>
	</tr>
	<tr>
		<td>原密码</td>
		<td><input type="password" value="" name="old_pw" size="10"></td>
	</tr>
	<tr>
		<td>新密码</td>
		<td><input type="password" value="" name="new_pw" size="10"></td>
	</tr>
	<tr>
		<td>再次输入密码</td>
		<td><input type="password" value="" name="confirm_new_pw" size="10"></td>
	</tr>
  </table>
  <br>
  <input type="submit" value="提 交" class="button_2003">
  <input type="reset" value="重 置" class="button_2003">
  </form>
</div>
</body>
</html>