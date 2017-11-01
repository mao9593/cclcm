<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>修改页数、份数</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
	<script language="JavaScript" src="${ctx}/_script/function.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>	
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>	
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script>
	function onOK(){
		var p_num = /^[0-9]{0,4}$/;
		if(!p_num.test($("#page_num").val())){
			alert("页数只能是 0-9999 的数字");
			$("#page_num").focus();
			return false;
		}
		if($("#page_num").val().trim() == '0'){
			alert("页数不能为0");
			$("#page_num").focus();
			return false;
		}
		if(!p_num.test($("#paper_num").val())){
			alert("份数只能是 0-9999 的数字");
			$("#paper_num").focus();
			return false;
		}
		if($("#paper_num").val().trim() == '0'){
			alert("份数不能为0");
			$("#paper_num").focus();
			return false;
		}
		if($("#page_num").val().trim() == ''){
			alert("页数不能为空");
			$("#page_num").focus();
			return false;
		}
		if($("#paper_num").val().trim() == ''){
			alert("份数不能为空");
			$("#paper_num").focus();
			return false;
		}

		var result = new Object();
		result.page_num = $("#page_num").val().trim();
		result.paper_num = $("#paper_num").val().trim();
		window.returnValue=result;	
		window.close();  
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
	<tr >
		<td align="center">页数：</td>
		<td align="center">
			<input type="text" name="page_num" id="page_num" value="${event.page_num}"/>
		</td>
	</tr>
		<td align="center">份数：</td>
		<td align="center">
			<input type="text" name="paper_num" id="paper_num" value="${event.paper_num}"/>
		</td>
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
