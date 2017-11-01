<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<base target="_self"/>
<title>查看刻录文件关键字检测结果</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
	<script language="JavaScript" src="${ctx}/_script/function.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>	
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>	
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<link type="text/css" rel="stylesheet" href="${ctx}/uploadify/uploadify.css"/>
	<script type="text/javascript" src="${ctx}/uploadify/jquery.uploadify.min.js"></script>
	<script>
	$(document).ready(function(){
			onHover();
		});
	function onOK(){
		$("#submit_btn").attr("disabled",true);
		var result = new Object();
		result.confirmsubmit = 1;
		window.returnValue=result;			
		window.close();  	
	}	

	var errorAlert = false;

	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_only_border" style="margin-top:10px">
	<tr>
		<td class="title_box" colspan="2">文件关键字检测结果列表</td>
	</tr>
	<tr>
	   <td valign="top">
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
						<display:table requestURI="${ctx}/burn/showrisklistburn.action" uid="item" class="displaytable" name="eventList" pagesize="15" sort="list">
							<display:column title="序号">
							<c:out value="${item_rowNum}"/>
							</display:column>
							<display:column title="关键字" property="riskclass" maxLength="40"/>
							<display:column title="命中次数" property="hitcount"/>
							<display:column title="关键字级别" property="levelname"/>
							<display:column title="敏感内容片段" property="sensitvecontents"/>
						</display:table>
		            </td>
				</tr>
			</table>
         </td>
	</tr>
	<tr> 
		<td colspan="2" align="center">
			<input type="button" class="button_2003" value="确认" id="submit_btn" onclick="onOK();"/>&nbsp;
			<input type="button" class="button_2003" value="关闭" onclick="window.close();" />
		</td>
	</tr>
</table>
</center>
</body>
</html>
