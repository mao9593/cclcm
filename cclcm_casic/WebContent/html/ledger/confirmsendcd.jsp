<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>外发确认备注</title>
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
			if('${send_way}' == 0){$("#hid").hide();}
		});
	function chk(){		
		if($("#output_confidential_num").val().trim()==""){
			alert("外发机要号不能为空，请重新输入");
			$("#output_confidential_num").focus();
			return false;
		}
		if($("#output_confidential_num").val().trim().length > 100){
			alert("请填写正确的机要号（长度过大）");
			$("#output_confidential_num").focus();
			return false;
		}
		return true;
	}	
	function onOK(){
		var result = new Object();
		if($("#send_way").val().trim() == 1) {
			if (chk()) {
				result.outputconfidentialnum = $("#output_confidential_num").val().trim();
				result.comment = $("#comment").val().trim();
				window.returnValue=result;	
				window.close();
			}
		} else {
			result.comment = $("#comment").val().trim();
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
	<input type="hidden" name="send_way" id="send_way" value="${send_way}"/>
	<tr>
		<td class="title_box" colspan="2">&nbsp;</td>
	</tr>
	<tr id='hid'> 
		<td align="center"><font color="red">*</font>&nbsp;外发机要号：</td>
    	<td align="center">
    		<input type="text" id="output_confidential_num" name="output_confidential_num" value="" size="32"/>
    	</td>
    </tr>
	<tr> 
		<td align="center">外发情况确认(备注)：</td>
    	<td align="center">
    		<textarea name="comment" rows="3" cols="30" id="comment"></textarea>
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
