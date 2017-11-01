<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>设置导出列</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
	<script language="JavaScript" src="${ctx}/_script/function.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>	
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>	
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script>
	function onOK(){
		var cols="";
		if($(":checkbox:checked[value!='']").size()>0){
				$(":checkbox:checked[value!='']").each(function (){
				    cols += this.value+",";
				});
		}else{
			alert("请勾选导出的列！");
			return false;
		}
		
		return true;
		
	}
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<form method="post" action="${ctx}/basic/updateexceltemplet.action" id="UpdateProcessForm">
<input type="hidden" name="update" value="Y" />
<input type="hidden" name="item_key" value="${sysConfigItem.item_key}" />
<input type="hidden" name="item_type" value="${sysConfigItem.item_type}" />
<input type="hidden" name="item_name" value="${sysConfigItem.item_name}" />
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box" style="margin-top:30px">
	<tr>
		<td class="title_box" colspan="2">&nbsp;文件导出列设置</td>
	</tr>
	<tr>
		<td align="center" width="10%"><font color="red">*</font>导出的列：<input type="checkbox" name="selectAll" onclick="setSelectAllStatus(this);"/></td>
		<td>
			<c:set var="codes" value="${sysConfigItem.item_value}" scope="request"/>
			<s:iterator value="#request.typeListPaper" var="type">
				<input type="checkbox" value="${type.key}" name="values" <s:if test="#request.codes.contains(''+#type.key+'')">checked</s:if>/>${type.name}
			</s:iterator>
		</td>
	</tr>
	<tr>
		<td colspan="2"><font color="red"><b>&nbsp;注意：如果导出的列中包含“制作审批信息”、“闭环审批信息”、“用途”，每次导出的数据不要超过5000条！</b></font></td>
	</tr>
	<tr> 
		<td colspan="2" align="center">
			<input type="submit" class="button_2003" value="提交" onclick="return onOK();" />&nbsp;
			<input type="button" class="button_2003" value="返回" onclick="javascript:history.go(-1);" />
		</td>
	</tr>
</table>
</form>
</center>
</body>
</html>
