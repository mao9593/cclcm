<%@ page language="java" pageEncoding="GBK" contentType="text/html;charset=GBK"%>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
	<title>�޸�����</title>
	<link href="<%=request.getContextPath()%>/_css/css200603.css" type="text/css" rel="stylesheet"/>
	<LINK href="<%=request.getContextPath()%>/_css/my-web-control.jsp" rel="stylesheet" type="text/css"/>
</head>
<body>
<div align="center" >
  <form action="/changePw.do?action=do" method="post">
  <table width="80%" class="table_box">
	<tr>
		<td colspan="2" class="title_box">�޸�����</td>
	</tr>
	<tr>
		<td>��ʾ</td>
		<td><font color="red"><%=request.getAttribute("message")%></font></td>
	</tr>
	<tr>
		<td>�û���</td>
		<td><%=request.getAttribute("cp_user_name")%></td>
	</tr>
	<tr>
		<td>ԭ����</td>
		<td><input type="password" value="" name="old_pw" size="10"></td>
	</tr>
	<tr>
		<td>������</td>
		<td><input type="password" value="" name="new_pw" size="10"></td>
	</tr>
	<tr>
		<td>�ٴ���������</td>
		<td><input type="password" value="" name="confirm_new_pw" size="10"></td>
	</tr>
  </table>
  <br>
  <input type="submit" value="�� ��" class="button_2003">
  <input type="reset" value="�� ��" class="button_2003">
  </form>
</div>
</body>
</html>