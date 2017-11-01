<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
<title>航盾信息安全产品网络控制台</title>
<link href="${ctx}/_css/css200603.css" rel="stylesheet" type="text/css"/>
</head>
<table width="100%" height="100%">
	<tr valign="middle" height="100%">
		<td align="center" height="100%">
		<table align="center" height="100%">
			<tr height="50%" valign="bottom">
				<td align="center" background=""
					width="439" height="245"
					style="background-repeat: no-repeat; background-position: bottom">
					<img vspace="20"  src="${ctx}/images/_system/logo.jpg"/>
				</td>
           </tr>
			<tr height="50%" valign="top">
				<td valign="top">
				<hr align="left" width="100%" size="2" color="#4D86CB"
					noshade="noshade" />
				<form name="loginForm" action="j_security_check" method="post"
					id="loginForm">
				<table border="0" align="center">
					<tbody>
					    <tr>
							<td></td>
							<td><input type="hidden" maxlength="50" size="20" name="referer"
								id="referer" />
							</td>
						</tr>
						<tr>
							<td>用户ID</td>
							<td><input type="text" maxlength="30" name="j_username" style="width:150px;"
								id="uid" onkeypress="javasctript:if(event.keyCode==13) btn_login();" />
							</td>
						</tr>
						<tr>
							<td>登录密码</td>
							<td><input type="password" name="j_password" id="password" style="width:150px;"
								onkeypress="javasctript:if(event.keyCode==13) btn_login();" /></td>
						</tr>
						<tr>
							<td align="center" colspan="2"></td>
						</tr>
						<tr>
							<td></td>
							<td><input type="button" class="button_2003" onclick="btn_login();"	value="登录" /></td>
						</tr>
					</tbody>
				</table>
				</form>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>

<script type="text/javascript">
	showErrorMsg('${error}');
	setReferer();
	document.getElementById("uid").focus();
function setReferer(){
	var referer = ''; 
    if (document.referrer.length > 0) { 
        referer = document.referrer; 
        } 
    try { 
        if (referer.length == 0 && opener.location.href.length > 0) { 
            referer = opener.location.href; 
             } 
        } catch (e) {
            ;
        }
     document.getElementById('referer').value=referer;
}
function showErrorMsg(error)
{
	if(error=="1"){
		alert("用户被禁用！");
		document.getElementById('name').focus();
	}
	else if(error=="2"){
		alert("密码错误！");
		document.getElementById('password').focus();
	}
	else if(error=="3"){
		alert("用户不存在！");
		document.getElementById('name').focus();
	}
	else if(error=="4"){
		alert("会话已过期，请重新登录！");
		document.getElementById('name').focus();
	}
}
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

</html>