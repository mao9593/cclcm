<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>默认密码设置</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
</head>
<script>
if('${done}' == 'Y'){
	alert("修改成功");
}else if("${msg}" != ""){
	alert("${msg}");
}
	
function chk()
{	
	var defaultpassword =/[\u4e00-\u9fa5]+/; 
    if(defaultpassword.test($("#defaultpassword").val())){
    alert("默认密码不能含有汉字");
    $("#defaultpassword").focus();
		return false;
    }
   if($("#defaultpassword").val().length == 0){
		alert("默认密码不能为空");
		document.all.defaultpassword.focus();
        return false;
	}
   return true;
}

</script>
<body oncontextmenu="self.event.returnValue=false" onload="onHover();" style="padding-top: 100px">
<center>
<form id="DefaultPasswordForm" method="GET" action="${ctx}/basic/defaultpassword.action">
<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="40%">
		
<tr>
		<td colspan="3" class="title_box"><b>&nbsp;&nbsp;默认密码设置:</b></td>
	</tr>	
	<tr>
		<td align="center">默认密码设置：</td>
		<td><input type="text" name="defaultpassword" id="defaultpassword" value="${defaultpassword}"/>&nbsp;</td>
	</tr>
	
	<tr>
        <td colspan="3" align="center" class="bottom_box">
        	<input type="hidden" name="update" value="Y"/>
            <input type="button" value="修改" class="button_2003" onclick="if(chk()) forms[0].submit();">&nbsp;
            <input type="button" value="返回" class="button_2003" onclick="history.go(-1);"/>
        </td>
    </tr>
</table>
</form>
</center>
</body>
</html>