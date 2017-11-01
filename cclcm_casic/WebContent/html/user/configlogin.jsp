<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>安全登录参数设置</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
</head>
<script language="javascript">
if('${done}' == 'Y'){
	alert("修改成功");
}
function chk()
{
	var pwd_length = document.all.pwd_length.value;
	var valid_days = document.all.valid_days.value;
	var fail_times = document.all.fail_times.value;
	var pwd_notation = document.all.pwd_notation.value;
    var pattern = /^[!@#$%^&*()_+:]+$/;
    var numval = /^[0-9]+$/;
    //if(pwd_length=='' || isNaN(pwd_length))
    if(!numval.test(pwd_length))
    {
        alert("密码长度应为整数");
        document.all.pwd_length.focus();
        return false;
    }
    if(parseInt(pwd_length) < 8)
    {
        alert("密码长度不足8位");
        document.all.pwd_length.focus();
        return false;
    }
    if(!numval.test(valid_days))
    {
        alert("密码有效时间应为整数");
        document.all.valid_days.focus();
        return false;
    }
    if(!numval.test(fail_times))
    {
        alert("密码错误次数应为整数");
        document.all.fail_times.focus();
        return false;
    }
	if(!pattern.test(pwd_notation)){
   		alert("指定的特殊字符范围为空或者超出了全部合法特殊字符的范围");
   		document.all.pwd_notation.focus();
        return false;
	}
    return true;
}
</script>
<body oncontextmenu="self.event.returnValue=false" onload="onHover();" style="padding-top: 100px">
<center>
<form id="TemplateQueryCondForm" method="GET" action="${ctx}/user/configlogin.action">
<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="60%">
	<tr>
		<td colspan="3" class="title_box">安全登录参数设置</td>
	</tr>
	<tr>
		<td width="5%" align="center"><input type="checkbox" name="pwd_length_start" value="1" title="勾选表示启用该项参数" ${pwd_length_start}/></td>
		<td width="20%" align="center">登录密码长度：</td>
		<td><input type="text" name="pwd_length" value="${pwd_length}"/>&nbsp;位</td>
	</tr>
	<tr>
		<td align="center"><input type="checkbox" name="valid_days_start" value="1" title="勾选表示启用该项参数" ${valid_days_start}/></td>
		<td align="center">密码有效时间：</td>
		<td><input type="text" name="valid_days" value="${valid_days}"/>&nbsp;天</td>
	</tr>
	<tr>
		<td align="center"><input type="checkbox" name="fail_times_start" value="1" title="勾选表示启用该项参数" ${fail_times_start}/></td>
		<td align="center">密码错误次数：</td>
		<td><input type="text" name="fail_times" value="${fail_times}"/>&nbsp;次&nbsp;(<font color="red">超限则用户锁定十分钟</font>)</td>
	</tr>
	<tr>
		<td align="center"><input type="checkbox" name="pwd_digit_start" value="1" title="勾选表示启用该项参数" ${pwd_digit_start}/></td>
		<td align="center">密码包含数字：</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="center"><input type="checkbox" name="pwd_alpha_start" value="1" title="勾选表示启用该项参数" ${pwd_alpha_start}/></td>
		<td align="center">密码包含字母：</td>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td align="center"><input type="checkbox" name="pwd_notation_start" value="1" title="勾选表示启用该项参数" ${pwd_notation_start}/></td>
		<td align="center">密码特殊字符范围：</td>
		<td><input type="text" name="pwd_notation" value="${pwd_notation}"/>&nbsp;(<font color="red">全部特殊字符范围：!@#$%^&*()+_:</font>)</td>
	</tr>
	<tr>
        <td colspan="3" align="center" class="bottom_box">
        	<input type="hidden" name="update" value="Y"/>
            <input type="button" value="确定" class="button_2003" onclick="if(chk()) forms[0].submit();">&nbsp;
            <input type="button" value="返回" class="button_2003" onclick="history.go(-1);"/>
        </td>
    </tr>
</table>
</form>
</center>
</body>
</html>
