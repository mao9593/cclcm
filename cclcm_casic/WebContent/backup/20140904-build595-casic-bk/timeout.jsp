<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<form target="topFrame" method="post" action="login.action" id="logoutForm">
</form>
<script>
alert("会话过期，系统自动退出，请重新登录！");
window.close();
top.opener=null;
top.window.close();
</script>