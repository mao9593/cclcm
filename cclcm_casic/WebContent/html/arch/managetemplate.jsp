<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>类别管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
<script>
<!--
	function openr(url){
		var url = "${ctx}/html/arch/addfield.jsp";
	    window.location=url;
	}

	function showDialogr(url){
		return window.showModalDialog(url,'', 'dialogHeight:400px;dialogWidth:800px;center:yes;status:no;help:no;scroll:no;unadorned:no;resizable:no');
	}
	function clearFindForm(){
		RoleQueryCondForm.role_name.value = "";
	}
//-->
</script>
</head>

<body oncontextmenu="self.event.returnValue=false" onload="onHover();">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr align="right">
	<td align="right"><input name="button" type="button" class="button_2003" onClick="go('${ctx}/html/arch/addtype.jsp');" value="增加类别">
			</td></tr>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
					<display:table requestURI="${ctx}/arch/managetemplate.action" uid="item" class="displaytable" name="typeList" pagesize="15" sort="list">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column property="type_name" title="类别名称" style="whitespace: nowrap;"/>
						<display:column property="type_num" title="类别编号" style="whitespace: nowrap;"/>
						<display:column title="操作" style="width:200px">
								<c:choose>
									<c:when test="${item.template_id eq null}">
										<input type="button" class="button_2003" value="档案模板设计" onClick="go('${ctx}/arch/addcustomform.action?archTypeId=${item.ID }');"/>
									</c:when>
									<c:otherwise>
										<input type="button" class="button_2003" value="档案模板设计" onClick="go('${ctx}/arch/viewarchtemplate.action?template_id=${item.template_id }');"/>
									</c:otherwise>
								</c:choose>
						</display:column>
					</display:table>
					</td>
				</tr>
			</table>
         </td>
	</tr>
</table>
</body>
</html>
