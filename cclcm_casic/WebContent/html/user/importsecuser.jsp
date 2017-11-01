<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>批量导入用户</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
</head>
<script language="javascript">
	$(document).ready(function(){
		if('${done}'=='Y'){
			text = "导入成功：${successNum}个用户<p>";
			text = text + "导入失败：已存在的用户行号为${existList}<p>";
			text = text + "必填信息缺失的行号为${lackList}<p>";
			text = text + "涉密人员类别不存在的行号为${secWrongList}<p>";
			text = text + "部门名称不存在的行号为${deptWrongList}<p>";
			text = text + "未知原因导致失败的行号为${failList}";
			$("#td_result").html(text);
		}
	});
</script>
<body oncontextmenu="self.event.returnValue=false" onload="onHover();" style="padding-top: 120px">
<center>
<table width="60%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">批量导入用户</td>
	</tr>
	<tr style="padding-top: 20px;">
		<td align="center">
		<form id="TemplateQueryCondForm" method="POST" action="${ctx}/user/importsecuser.action" enctype="multipart/form-data">
			<input type="hidden" name="done" value="Y"/>
 			导入文件：<input type="file" name="upload" class="button_2003"/>&nbsp;&nbsp;
 				<input type="submit" class="button_2003" value="提交">&nbsp;&nbsp;
 				<a href="${ctx}/html/user/user.xls">下载模板</a>
 		</form>
		</td>
	</tr>
	<tr>
		<td id="td_result" style="border-top: 2px">
		</td>
	</tr>
</table>
</center>
</body>
</html>
