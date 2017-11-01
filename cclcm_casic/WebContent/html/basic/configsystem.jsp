<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>打印参数设置</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
</head>
<script>
$(document).ready(function(){
	if('${done}' == 'Y'){
		alert("修改成功");
	}	
	$("#changeResultConfigForm").submit(function(){
		var days = parseInt($("#maxChangeResultDays").val());
		if(!$.isNumeric(days) || days < 1 || days >200){
			alert("请输入1-200之间的整数");
			$("#maxChangeResultDays").focus();
			return false;
		}
		else{
			$("#maxChangeResultDays").val(days);
		}
	});
});
 

</script>
<body oncontextmenu="self.event.returnValue=false" onload="onHover();" style="padding-top: 80px">
<center>
<form id="changeResultConfigForm" method="POST">
<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="60%">
	<tr>
		<td colspan="2" class="title_box">标记失败参数设置</td>
	</tr>	
	<tr>
		<td width="25%" align="center">允许标记失败最长天数：</td>
		<td><input type="text" name="maxChangeResultDays" id="maxChangeResultDays"  value="${maxChangeResultDays}"/>&nbsp;天</td>
	</tr>
	<tr>
        <td colspan="3" align="center" class="bottom_box">
        	<input type="hidden" name="update" value="Y"/>
            <input type="submit" value="修改" class="button_2003">&nbsp;
            <input type="button" value="返回" class="button_2003" onclick="history.go(-1);"/>
        </td>
    </tr>
</table>
</form>
</center>
</body>
</html>