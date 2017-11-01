<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>监销确认备注</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
    <script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
	<script language="JavaScript" src="${ctx}/_script/function.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>	
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>	
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script>
	$(document).ready(function(){
			onHover();
		});
	function chk(){		
		if($("#comment").val().trim()==""){
			alert("请填监销确认备注");
			$("#comment").focus();
			return false;
		}
		return true;
	}	
	function onOK(){
		if(chk()) 
		{
			var result = new Object();
			result.send_num = $("#comment").val();
			result.comment = "监销确认备注："+$("#comment").val().trim();
			window.returnValue=result;	
			window.close();  
		}
	}	
	function onCancel(){
		window.close();
	}
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box" style="margin-top:30px">
	<tr>
		<td class="title_box" colspan="2">&nbsp;</td>
	</tr>
	<tr> 
		<td align="center"><font color="red">*</font>监销确认备注：</td>
    	<td align="center">
    		<textarea name="comment" rows="5" cols="30" id="comment"></textarea>
    	</td>   	
	</tr>
	<tr> 
		<td colspan="2" align="center">
			<input type="button" class="button_2003" value="提交" onclick="onOK();" />&nbsp;
			<input type="button" class="button_2003" value="取消" onclick="onCancel();" />
		</td>
	</tr>
</table>
</center>
</body>
</html>
