<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>添加项目</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
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
		if($("#project_code").val().trim() == ""){
			alert("请填写项目代号");
			$("#project_code").focus();
			return false;
		}
		if($("#project_name").val().trim() == ""){
			alert("请填写项目名称");
			$("#project_name").focus();
			return false;
		}
		if($("#project_content").val().length > 1024){
			alert("用途说明不能超过1024个字符");
			$("#usage_content").focus();
			return false;
		}
		if($("input[name=start_time]").val() > $("input[name=end_time]").val()){
			alert("有效开始时间必须小于结束时间");
			return false;
		}
		if(!checkCode($("#project_code").val())){
			alert("用途特征值只能由数字，字母或下划线组成，长度小于30位");
			$("#usage_code").focus();
			return false;
		}
	    return true;
	}
	//-->
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<form method="post" action="${ctx}/basic/addproject.action">
  	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="60%">
	 <tr>
		 <td colspan="2" class="title_box">
            	添加项目
        </td>
    </tr>
    <tr>
    	<td align="center"><font color="red">*</font>项目代号：</td>
		<td>
			<input type="text" name="project_code" id="project_code"/>
		</td>
    </tr>
    <tr>
    	<td align="center"><font color="red">*</font>项目名称：</td>
		<td>
			<input type="text" name="project_name" id="project_name"/>
		</td>
    </tr>
    <tr>
    	<td align="center">起止时间：</td>
		<td>
			<input type="text" name="start_time" readonly="readonly" style="cursor:hand;"/>
        	<img src="${ctx}/_image/time2.jpg" id="start_time_trigger">
        	&nbsp;&nbsp;至&nbsp;&nbsp;
			<input type="text" name="end_time" readonly="readonly" style="cursor:hand;"/>
        	<img src="${ctx}/_image/time2.jpg" id="end_time_trigger">
		</td>
    </tr>
    <tr>
    	<td align="center">项目说明：</td>
		<td>
			<textarea rows="3" cols="60" name="project_content" id="project_content"></textarea>
		</td>
    </tr>
    <tr>
        <td colspan="2" align="center" class="bottom_box">
            <input type="submit" value="添加" class="button_2003" onclick="return chk();">&nbsp;
            <input type="reset" value="重置" class="button_2003">
            <input type="button" value="返回" class="button_2003" onclick="javascript:history.go(-1);">
        </td>
    </tr>
  	</table>
</form>
</center>
</body>
</html>