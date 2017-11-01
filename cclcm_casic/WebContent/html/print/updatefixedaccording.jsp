<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
	<link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
	<script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script> 
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script language="JavaScript" >
	<!--
	$(document).ready(function(){
		onHover();
	});
	function chkSubmit()
	{
		if($("#content").val().trim() == ""){
			alert("请填写定密内容");
			$("#content").focus();
			return false;
		}else if($("#content").val().length > 256){
			alert("定密内容数据过长，请重新输入");
			$("#content").focus();
			return false;
		}
	    return true;
	}
	//-->
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<form method="post" action="${ctx}/print/updatefixedaccording.action">
    <input type="hidden" name="update" id="update" value="Y"/>
	<input type="hidden" name="id" id="id" value="${id}"/>
	
  	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="80%">
	 <tr>
		 <td colspan="6" class="title_box">修改定密依据</td>
    </tr>   
    <tr>
		<td align="center">类型</td>
		<td><font color="blue"><b>${list.type_name}</b></font></td>
    	<td align="center"><font color="red">*</font>内容</td>
		<td><textarea name="content" id="content" rows="5" cols="80">${list.content}</textarea></td>
    </tr>    
    <tr>
        <td colspan="6" align="center" class="bottom_box">
        	<input type="button" class="button_2003" value="确认修改" onclick="if(chkSubmit()) forms[0].submit();"/>
            <input type="button" value="返回" class="button_2003" onclick="history.go(-1);">
        </td>
    </tr>
  	</table>
</form>
</center>
</body>
</html>