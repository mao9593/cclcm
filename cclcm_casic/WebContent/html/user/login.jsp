<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>用户中心-登录</title>
	<link href="${ctx}/_css/main.css" rel="stylesheet" type="text/css">
	<link href="${ctx}/_css/css200603.css" rel="stylesheet" type="text/css"/>
</head>
	<body>
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
					<table align="left" style="margin-left: 20px;">
						<tr>
							<td><font color="#4479AB"><b>用户ID</b></font></td>
							<td><input type="text" maxlength="30" name="j_username" style="width:150px;"
								id="uid" onkeypress="javasctript:if(event.keyCode==13) btn_login();" />
							</td>
						</tr>
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
							<td><input type="button" class="button_2003" onclick="btn_login();"	value="登录" style="width: 150px;"/></td>
						</tr>
					</table>
					</form>
			</td>
		</tr>
	</table>
	</div>
			
		<script>
	document.getElementById("uid").focus();
	function isEmail(s){   
	    if(s.search(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/)   !=   -1){   
	      	return true;   
	    }else{   
	    	return false;
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
		if(checkIsEmpty('name')==true)
		{
			alert('用户名不能为空');
			document.getElementById('name').focus();
			return false;
		}
		else if(checkIsEmpty('password')==true)
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
</body></html>
