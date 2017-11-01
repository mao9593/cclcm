<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>添加地点</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
	<link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script  language="JavaScript" >
	<!--
	$(document).ready(function(){
		onHover();
		init();
	});
	function init(){
		
	}
	
	function chk()
	{
		if($("#place_code").val().trim() == ""){
			alert("请添加地点代号");
			$("#place_code").focus();
			return false;
		}
		if($("#place_name").val() == ""){
			alert("请添加地点名称");
			$("#place_name").focus();
			return false;
		}
		var pattern = /^[a-zA-Z0-9]*$/;
		if(!pattern.test($("#place_code").val())){
			alert("添加地点代号必须为字母或数字");
			$("#place_code").focus();
			return false;
		}
		
	    return true;
	}

	//-->
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<form method="post" action="${ctx}/basic/addplace.action">
  	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="80%">
	 <tr>
	 	<td colspan="2" class="title_box">添加地点</td>
	 </tr>
	 <tr>
		 <td align="center">
            	<font color="red">*</font>地点代号
        </td>
        <td>
            <input type="text" id="place_code" name="place_code"  size="20"/>
        </td>
    </tr>
    <tr>
    	<td align="center"><font color="red">*</font>地点名称：</td>
		<td>
    		<input type="text" id="place_name" name="place_name" size="20"/>
		</td>
    </tr>
    <tr>
    	<td align="center">地点描述：</td>
		<td>
    		<input type="text" id="place_desc" name="place_desc"  size="20"/>
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