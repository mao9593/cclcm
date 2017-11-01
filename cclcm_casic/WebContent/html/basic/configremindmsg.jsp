<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>闭环提醒设置</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
</head>
<script>
var importShortDays = 0;
var remindMsgDays = 0;
$(document).ready(function(){
		importShortDays = ${import_short_days};
		remindMsgDays = ${remind_msg_days};
	});
if('${done}' == 'Y'){
	alert("修改成功");
} 
function chk()
{
	var import_short_days = $("#import_short_days").val().trim();
	var remind_msg_days = $("#remind_msg_days").val().trim();
    
    var numval = /^[0-9]+$/;

    if($("input[name='import_short_days_start'][checked]").size()==1){
	    if(!numval.test(import_short_days))
	    {
	        alert("载体短期留用时间应为整数");
	        document.all.import_short_days.focus();
	        return false;
	    }
	    if(import_short_days > 365){
		     alert("载体短期留用时间不超过1年");
		     document.all.import_short_days.focus();
		    return false;
		}
		$("#import_short_days").val(import_short_days);
	}else{
    	$("#import_short_days").val(importShortDays);
    }
    
    if($("input[name='remind_msg_days_start'][checked]").size()==1){
	    if(!numval.test(remind_msg_days))
	    {
	        alert("回收消息提醒周期应为整数");
	        document.all.remind_msg_days.focus();
	        return false;
	    }
	    if(remind_msg_days > 365){
		     alert("回收消息提醒周期不超过1年");
		     document.all.remind_msg_days.focus();
		    return false;
		}
		$("#remind_msg_days").val(remind_msg_days);
	}else{
    	$("#remind_msg_days").val(remindMsgDays);
    }
   //return true;
   document.all.TemplateQueryCondForm.submit();
}
</script>
<body oncontextmenu="self.event.returnValue=false" onload="onHover();" style="padding-top: 80px">
<center>
<form id="TemplateQueryCondForm" method="GET" action="${ctx}/basic/configremindmsg.action">
<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="60%">	
	<tr>
		<td colspan="3" class="title_box"><b>&nbsp;&nbsp;消息提醒时间设置:</b></td>
	</tr>	
	<tr>
		<td align="center"><input type="checkbox" name="import_short_days_start"  value="1" checked="checked" disabled="disabled"/></td>
		<td align="center">载体短期留用时间：</td>
		<td><input type="text" name="import_short_days" id="import_short_days" value="${import_short_days}"/>&nbsp;天</td>
	</tr>
	<tr>
		<td align="center"><input type="checkbox" name="remind_msg_days_start"  value="1" title="勾选表示启用该项参数" ${remind_msg_days_start}/></td>
		<td align="center">载体回收消息提醒周期：</td>
		<td><input type="text" name="remind_msg_days" id="remind_msg_days" value="${remind_msg_days}"/>&nbsp;天</td>
	</tr>
	<tr>
        <td colspan="3" align="center" class="bottom_box">
        	<input type="hidden" name="update" value="Y"/>
            <input type=button value="修改" class="button_2003" onclick="chk();">&nbsp;&nbsp;
            <input type="button" value="返回" class="button_2003" onclick="history.go(-1);"/>
        </td>
    </tr>
</table>
</form>
</center>
</body>
</html>