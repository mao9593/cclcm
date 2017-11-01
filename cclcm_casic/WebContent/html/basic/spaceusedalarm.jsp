<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>空间使用告警设置</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
</head>
<script>
if('${done}' == 'Y'){
	alert("修改成功");
} 
function chk()
{	
    var numval = /^[0-9]+$/;   
    if(!numval.test(document.all.alarm_percent.value) || document.all.alarm_percent.value<0 || document.all.alarm_percent.value>100)
    {
        alert("磁盘空间告警百分比应为0-100整数");
        document.all.alarm_percent.focus();
        return false;
    }
	
   return true;
}

</script>
<body oncontextmenu="self.event.returnValue=false" onload="onHover();" style="padding-top: 100px">
<center>
<form id="TemplateQueryCondForm" method="GET" action="${ctx}/basic/spaceusedalarm.action">
<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="40%">
	<tr>
		<td colspan="3" class="title_box">&nbsp;&nbsp;空间使用告警设置</td>
	</tr>	
	<tr>		
		<td width="40%">&nbsp;&nbsp;&nbsp;&nbsp;当前磁盘空间使用百分比：</td>
		<td>&nbsp;&nbsp;${space_used}<b>&nbsp;%</b></td>
	</tr>
	
	<tr>
		<td>&nbsp;&nbsp;&nbsp;&nbsp;磁盘空间告警百分比：</td>
		<td>&nbsp;&nbsp;<b><input type="text" name="alarm_percent" id="alarm_percent" value="${alarm_percent}"/>&nbsp;%</b></td>
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