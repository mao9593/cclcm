<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>设置三员登录IP</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
<script>
var numval = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
$(document).ready(function(){
	onHover();
	if('${done}' == 'Y'){
		alert("设置成功");
	}
	$("input[type='text']").bind("blur",function(){
		var $tag = $(this);
		var ip = $tag.val().trim();
		if(ip != '' && !numval.test(ip)){
			alert("IP地址输入有误，请重新输入");
			$(this).focus();
			return false;
		}else{
			$tag.siblings("input").first().each(function(){
				tIp = this.value;
				if(tIp.substring(0,tIp.lastIndexOf(".")) != ip.substring(0,ip.lastIndexOf("."))){
					this.value=getIpPrefix(ip);	
					$(this).focus();
				}
			});
		}
	});
});
function getIpPrefix(ip){
	return ip.substring(0,ip.lastIndexOf(".")+1);
}
function chk()
{
	//校验所有输入框，检查IP地址输入是否有误
	$("input[type='text']").each(function(){
		var ip = this.value.trim();
		if(ip != '' && !numval.test(ip)){
			alert("IP地址输入有误，请重新输入");
			$(this).focus();
			return false;
		}
	});
	$("#TemplateQueryCondForm").submit();
}
</script>
</head>
<body oncontextmenu="self.event.returnValue=false" style="padding-top: 80px">
<center>
<form id="TemplateQueryCondForm" method="GET" action="${ctx}/basic/threeadminloginip.action">
	<input type="hidden" name="update" value="Y"/>
<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="60%">	
	<tr>
		<td colspan="6" class="title_box"><b>&nbsp;&nbsp;设置三员登录IP:&nbsp;&nbsp;&nbsp;<font color="yellow">(注意每个IP地址区间的前三段必须相同)</font></b></td>
	</tr>	
	<tr>
		<td align="center"><input type="checkbox" name="secIp_start" value="1" title="勾选表示启用该项设定"<c:if test='${secIp_start == 1}'>checked</c:if>/></td>
		<td align="center">安全管理员&nbsp;&nbsp;设定IP:</td>
		<td align="center"><input type="text" name="secIp1" value="${secIp1}" id="secIp1"/>
		&nbsp;—&nbsp;<input type="text" name="secIp2" value="${secIp2}" id="secIp2"/></td>
	</tr>
	<tr>
	    <td>&nbsp;</td>
	    <td>&nbsp;</td>
	    <td align="center"><input type="text" name="secIp3" value="${secIp3}" id="secIp3"/>
		&nbsp;—&nbsp;<input type="text" name="secIp4" value="${secIp4}" id="secIp4"/></td>
	</tr>
	<tr>
	    <td>&nbsp;</td>
	    <td>&nbsp;</td>
	    <td align="center"><input type="text" name="secIp5" value="${secIp5}" id="secIp5"/>
		&nbsp;—&nbsp;<input type="text" name="secIp6" value="${secIp6}" id="secIp6"/></td>
	</tr>
	<tr>
		<td align="center"><input type="checkbox" name="sysIp_start" value="1" title="勾选表示启用该项设定" <c:if test='${sysIp_start == 1}'>checked</c:if>/></td>
		<td  align="center">系统管理员&nbsp;&nbsp;设定IP:</td>
		<td align="center"><input type="text" name="sysIp1" value="${sysIp1}" id="sysIp1"/>
		&nbsp;—&nbsp;<input type="text" name="sysIp2" value="${sysIp2}" id="sysIp2"/>&nbsp;</td>
	</tr>
	<tr>
	    <td>&nbsp;</td>
	    <td>&nbsp;</td>
	    <td align="center"><input type="text" name="sysIp3" value="${sysIp3}" id="sysIp3"/>
		&nbsp;—&nbsp;<input type="text" name="sysIp4" value="${sysIp4}" id="sysIp4"/></td>
	</tr>
	<tr>
	    <td>&nbsp;</td>
	    <td>&nbsp;</td>
	    <td align="center"><input type="text" name="sysIp5" value="${sysIp5}" id="sysIp5"/>
		&nbsp;—&nbsp;<input type="text" name="sysIp6" value="${sysIp6}" id="sysIp6"/></td>
	</tr>
	
	<tr>
		<td align="center"><input type="checkbox" name="audIp_start" value="1" title="勾选表示启用该项设定" <c:if test='${audIp_start == 1}'>checked</c:if>/></td>
		<td align="center">审计管理员&nbsp;&nbsp;设定IP:</td>
		<td align="center"><input type="text" name="audIp1" value="${audIp1}" id="audIp1"/>
		&nbsp;—&nbsp;<input type="text" name="audIp2" value="${audIp2}" id="audIp2"/>&nbsp;</td>
	</tr>
		<tr>
	    <td>&nbsp;</td>
	    <td>&nbsp;</td>
	    <td align="center"><input type="text" name="audIp3" value="${audIp3}" id="audIp3"/>
		&nbsp;—&nbsp;<input type="text" name="audIp4" value="${audIp4}" id="audIp4"/></td>
	</tr>
		<tr>
	    <td>&nbsp;</td>
	    <td>&nbsp;</td>
	    <td align="center"><input type="text" name="audIp5" value="${audIp5}" id="audIp5"/>
		&nbsp;—&nbsp;<input type="text" name="audIp6" value="${audIp6}" id="audIp6"/></td>
	</tr>
	<tr>
        <td colspan="6" align="center" class="bottom_box">
            <input type="button" value="提交" class="button_2003" onclick="chk();">&nbsp;&nbsp;
        </td>
    </tr>
</table>
</form>
</center>
</body>
</html>