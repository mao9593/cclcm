<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>批量导入档案</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
		<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
	<link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script>
	$(document).ready(function(){
		var ret="${msg}";
		if(ret!=""){
			var msg=ret.split(":");
			var message="导入成功:" + msg[0] + "条档案，导入失败的档案行号为：" + msg[1];
			$("#msg").text(message);
		}
	});
	function downloadTemplate(id){
		$("#id").val(id);
		$("#ArchQueryCondForm").submit();
	}
	</script>
</head>

<body oncontextmenu="self.event.returnValue=false">
<form id="ArchQueryCondForm" method="GET" action="${ctx}/arch/batchimportarch.action">
	<input type="hidden" name="id" id="id" />  
	<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">档案查询列表</td>
	</tr>
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
	   					<display:table id="item" class="displaytable" name="archTypeList">
							<display:column title="序号">
								<c:out value="${item_rowNum}"/>
							</display:column>
							<display:column property="type_name" title="档案类别"/>
							<display:column title="下载模板">
								<label onclick="downloadTemplate('${item.ID}');"><u>下载模板</u></label>
							</display:column>
						</display:table>
					</td>
				</tr>
			</table>
         </td>
	</tr>
	<tr><td><font color="red">请点击下载模板，按照模板格式进行填充后再上传批量导入档案，<b>注意不要修改模板文件的名称</b></font></td></tr>
</table>
</form>
<br/>
<table width="60%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">批量导入档案</td>
	</tr>
	<tr style="padding-top: 20px;">
		<td align="center">
		<form id="TemplateQueryCondForm" method="POST" action="${ctx}/arch/importarch.action" enctype="multipart/form-data">
			<input type="hidden" name="done" value="Y"/>
 			上传文件：<input type="file" name="upload" class="button_2003"/>&nbsp;&nbsp;
 				<input type="submit" class="button_2003" value="提交">&nbsp;&nbsp;
 		</form>
		</td>
	</tr>
	<tr><td id="msg"></td></tr>
</table>
</body>
</html>