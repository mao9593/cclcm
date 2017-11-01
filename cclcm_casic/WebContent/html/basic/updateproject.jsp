<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>修改项目</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen"/>
	<link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
	<script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script  language="JavaScript" >
	<!--
	$(document).ready(function(){
		onHover();
		preCalendarDay();
	});
	function chk()
	{
		if($("#project_name").val().trim() == ""){
			alert("请填写项目名称");
			$("#project_name").focus();
			return false;
		}
	    return true;
	}
	//-->
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<form method="post" action="${ctx}/basic/updateproject.action">
	<input type="hidden" value="${project.project_code}" name="project_code"/>
	<input type="hidden" value="Y" name="update"/>
   	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="60%">
	 <tr>
		 <td colspan="2" class="title_box">
            	修改项目
        </td>
    </tr>
    <tr>
    	<td align="center"><font color="red">*</font>项目代号：</td>
		<td><font color="blue"><b>${project.project_code}</b></font></td>
    </tr>
    <tr>
    	<td align="center"><font color="red">*</font>项目名称：</td>
		<td>
			<input type="text" name="project_name" maxlength="15" id="project_name" value="${project.project_name}"/>
		</td>
    </tr>
    <tr>
    	<td align="center">起止时间：</td>
		<td>
			<input type="text" name="start_time" readonly="readonly" style="cursor:hand;" value="${project.start_time_str}"/>
        	<img src="${ctx}/_image/time2.jpg" id="start_time_trigger">
        	&nbsp;&nbsp;至&nbsp;&nbsp;
			<input type="text" name="end_time" readonly="readonly" style="cursor:hand;" value="${project.end_time_str}"/>
        	<img src="${ctx}/_image/time2.jpg" id="end_time_trigger">
		</td>
    </tr>
    <tr>
    	<td align="center">项目说明：</td>
		<td>
			<textarea rows="3" cols="60" name="project_content" id="project_content">${project.project_content}</textarea>
		</td>
    </tr>
    <tr>
        <td colspan="2" align="center" class="bottom_box">
            <input type="submit" value="修改" class="button_2003" onclick="return chk();">&nbsp;
            <input type="button" value="返回" class="button_2003" onclick="javascript:history.go(-1);">
        </td>
    </tr>
  	</table>
</form>
</center>
</body>
</html>