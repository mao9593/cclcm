<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>外发确认备注</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
	<script language="JavaScript" src="${ctx}/_script/function.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>	
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>	
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script>
	$(document).ready(function(){
			onHover();
		});	
	function onOK(){
			var url = "${ctx}/ledger/addorupdatefailremark.action";
			callServerPostForm1(url,document.getElementById("addFailRemark"));
	}
	function getAjaxResult1(){
	if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			alert("任务添加完成");			
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
<form action="${ctx}/ledger/addorupdatefailremark.action" method="post" id="addFailRemark">
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box" style="margin-top:30px">
	<tr>
		<td class="title_box" colspan="2">&nbsp;文件备注</td>
	</tr>
	<tr> 
		<td align="center">文件原备注：</td>  	
    	<td >
    		${paper.fail_remark } 
    	</td>   	
	</tr>
	<tr> 
		<td align="center">文件销毁备注：</td>  	
    	<td align="center">
    		<input type="hidden" name = "barcode" id = "barcode" value="${barcode}"/>
    		<input type="hidden" name = "tag" id = "tag" value="${tag}"/>
    		<textarea name="comment" rows="7" cols="80" id="comment"> </textarea>
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
