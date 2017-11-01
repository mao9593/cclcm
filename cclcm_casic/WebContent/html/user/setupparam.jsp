<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>系统参数设置</title>
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
	var max_percent = document.all.max_percent.value;
	var alarm_percent = document.all.alarm_percent.value;
	var store_period = document.all.store_period.value;
	var numval = /^[0-9]+$/;
	if(!numval.test(max_percent))
    {
        alert("日志最大存储空间阀值应为整数");
        document.all.max_percent.focus();
        return false;
    }
    if(!numval.test(alarm_percent))
    {
        alert("日志存储空间告警百分比应为整数");
        document.all.alarm_percent.focus();
        return false;
    }
    if(!numval.test(store_period))
    {
        alert("审计日志保存周期应为整数");
        document.all.store_period.focus();
        return false;
    }
    return true;
}
</script>
<body oncontextmenu="self.event.returnValue=false" onload="onHover();" style="padding-top: 100px">
<center>
<form id="ParamSetupForm" method="POST" action="${ctx}/user/setupparam.action">
<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="60%">
	<tr>
		<td colspan="3" class="title_box">系统参数设置</td>
	</tr>
	<tr>
		<td width="30%" align="center">当前日志存储空间使用百分比：</td>
		<td><input type="text" value="${space_used}" disabled="disabled"/>&nbsp;%</td>
	</tr>
	<tr>
		<td width="30%" align="center">日志最大存储空间阀值：</td>
		<td><input type="text" name="max_percent" value="${max_percent}"/>&nbsp;%</td>
	</tr>
	<tr>
		<td width="30%" align="center">日志存储空间告警百分比：</td>
		<td><input type="text" name="alarm_percent" value="${alarm_percent}"/>&nbsp;%</td>
	</tr>
	<tr>
		<td width="30%" align="center">审计日志保存周期：</td>
		<td><input type="text" name="store_period" value="${store_period}"/>&nbsp;月</td>
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
