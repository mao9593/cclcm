<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/_script/jquery/jquery-ui.min.js"></script>
<script type="text/javascript" src="${ctx}/_script/jquery/jquery.yiiactiveform.js"></script>
<title>用户中心-登录</title>
<link href="${ctx}/_css/main.css" rel="stylesheet" type="text/css">
<link href="${ctx}/_css/jquery-ui-1.8.14.custom.css" rel="stylesheet" type="text/css">
</head>
	<body>
	<center>
	<div class="header-wrap">
			<div id="header">
				<div class="wrap clearfix">
				<img src="${ctx}/images/_system/logo.gif"><font style="padding-left: 300px" color="#FFFFFF" size="2"><b>航盾安全，航天品质</b></font></div>
			</div>
		</div>
		<div class="container">
			<div id="content">
<div id="main" class="wrap" style="margin-top: -17px; height: 474px;">
	<div id="login_form">

		<!--绑定后-->
		<div class="login_nav login_nav2">
		        <ul class="login_nav_top" id="_nav">
        <li class="slider" id="slider" style="left:6px;"></li>
        <li class="cursor_z1" id="passport">密码登录</li>
        </ul>
                </div>
        <div style="clear:both"></div>
        <div id="login-passport">
        	<form id="Kaf_Model_Form_Login" action="http://passport.weiphone.com/?r=user/loginProcess" method="post">	
		<table id="mima-table" border="0" cellspacing="0" cellpadding="0" class="form-table">	
			<tbody><tr>
				<td>
					<div id="cnblogs_post_body">
						<div id="cvtooltipArea">
							<div id="cvtooltipAuto" style="display: none; line-height: 24px; font-size:12px;">&nbsp;</div>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td width="170">
					<input size="30" autocomplete="off" name="Kaf_Model_Form_Login[login]" id="Kaf_Model_Form_Login_login" type="text" style="color: rgb(204, 204, 204);">									</td>
			</tr>
			<tr>
				<td>
				<input size="30" name="Kaf_Model_Form_Login[password]" id="Kaf_Model_Form_Login_password" type="password"></td>
			</tr>
			<tr>
				<td class="zz">
					<input class="btn-login" type="submit" name="yt0" value="" id="yt0">				</td>
			</tr>
		</tbody></table>
		</form>		</div>
	</div>
	<div id="login-left"><img src="${ctx}/images/_system/login-img.gif"" width="389" height="307"></div>
</div>
		<script>
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
        <script type="text/javascript">
        window_h = jQuery(window).height();
		main_h = jQuery('.header-wrap').height()+ jQuery('.container').height()+jQuery('#footer').height();
		if(main_h <  window_h){
			auto_height = window_h - jQuery('.header-wrap').height() - jQuery('#footer').height() -40;
			jQuery('#main').height(auto_height)
			}
        </script>
        </center>
</body></html>
