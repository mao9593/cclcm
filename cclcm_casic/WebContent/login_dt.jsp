<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>用户中心-登录</title>
	<link href="${ctx}/_css/main.css" rel="stylesheet" type="text/css">
	<link href="${ctx}/_css/css200603.css" rel="stylesheet" type="text/css"/>
	<script>
	function initusername(){
	    var secagent = new ActiveXObject("sensinfoctrl.SecAgent");
	    var issuer_name = "bmb";
	    var issuer_hash = "4e 00 32 e5 e2 84 dc d3 e8 01 63 8b 61 ce 66 35 29 99 69 4c";
	    var username = secagent.GetUsernameByCert(issuer_name,issuer_hash);
	    if (username != "") {
	        document.getElementById('j_username').value = username;
	    }else{
	        alert("get username by certificate failed");
	        if (window.opener != null)
	        {
	            window.close();
	        }else{
	            window.close();
	        }
	    }
	}
	function clearpword(){
		document.getElementById('j_password').value = "";
	}
	</script>
</head>
	<body onload="initusername()">
	<center>
	<div class="header-wrap">
			<div id="header">
				<div class="wrap clearfix">
				<img src="${ctx}/images/_system/logo.gif"><font style="padding-left: 200px" color="#FFFFFF" size="5"><b>航盾安全  航天品质</b></font></div>
			</div>
	</div>
	<div class="container">
	<table style="margin-top: 100px;" width="100%">
		<tr valign="middle">
			<td width="55%" align="right"><img src="${ctx}/images/_system/band.gif"/></td>
			<td width="2" style="background-color: #4479AB;">&nbsp;</td>
			<td align="left" valign="middle">
					<form name="loginForm" action="j_security_check" method="post" id="loginForm">
					<input type="hidden" name="j_username" id="j_username"/>
					<table align="left" style="margin-left: 20px;">
						<tr><td colspan="2">&nbsp;</td></tr>
						<tr>
							<td><font color="#4479AB"><b>登录密码</b></font></td>
							<td><input type="password" name="j_password" id="password" style="width:150px;"
								onkeypress="javasctript:if(event.keyCode==13) btn_login();" /></td>
						</tr>
						<tr>
							<td align="center" colspan="2">&nbsp;</td>
						</tr>
						<tr>
							<td></td>
							<td><input type="button" class="button_2003" onclick="btn_login();"	value="登录" style="width: 50px;"/>&nbsp;
								<input type="button" class="button_2003" onclick="clearpword();" value="重置" style="width: 50px;"/>
							</td>
						</tr>
					</table>
					</form>
			</td>
		</tr>
	</table>
	</div>
			
		<script>
	showErrorMsg('${error}');
	function showErrorMsg(error)
	{
		if(error=="1"){
			alert("用户被禁用！");
			document.getElementById('j_username').focus();
		}
		else if(error=="2"){
			alert("密码错误！");
			document.getElementById('password').focus();
		}
		else if(error=="3"){
			alert("用户不存在！");
			document.getElementById('j_username').focus();
		}
		else if(error=="4"){
			alert("会话已过期，请重新登录！");
			document.getElementById('j_username').focus();
		}
	}
	function btn_login(){
		if(checkEmpty()){
			document.getElementById('loginForm').submit();
		}
		else {
			return false;
		}
	}

	function checkEmpty()
	{
		if(checkIsEmpty('password')==true)
		{
			alert('密码不能为空');
			document.getElementById('password').focus();
			return false;
		}
		else{
			return true;
		}
	}

	function checkIsEmpty(tagName)
	{
		if(checkIsNotEmpty(tagName)==true)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	function checkIsNotEmpty(tagName)
	{
		var list = document.getElementsByName(tagName);
		if( list.length==0 	                           //none
			|| (list.length>0                          //list
			  && ( list[list.length-1].value!=''       //input tag
			    && list[list.length-1].selectedIndex!=0//select tag
			    ))
		   )
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	</script>
</center>
</body>
</html>