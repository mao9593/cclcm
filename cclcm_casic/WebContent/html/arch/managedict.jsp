<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>字典设置</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
    <script>
	<!--
	//-->
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="100%" border="0" cellspacing="1" cellpadding="1" height="100%">
  <tr> 
    <td height="450" align="center" valign="top">       
        <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
          <tr class="layouttr">
            <td class="title_box">字典设置</td>
          </tr>
          <tr>
			  <td class="nav_box" align="right">
				<input name="button" type="button" class="button_2003" onClick="go('${ctx}/arch/adddict.action');" value="增加字典">&nbsp;
			  </td>
          </tr>
          <tr>
            <td valign="top">
			<display:table uid="dict" class="displaytable" name="dictList">
				<display:column title="序号">
					<c:out value="${dict_rowNum}"/>
				</display:column>
				<display:column property="dict_name" title="字典名称" />
				<display:column property="dict_value" title="字典取值" />
				<display:column title="启用状态">
					<c:if test="${dict.is_used == 1}">已启用</c:if>
					<c:if test="${dict.is_used == 0}">已禁用</c:if>
				</display:column>
				<display:column title="操作" style="width:150px">
					<input type="button" class="button_2003" value="设置" onclick="go('${ctx}/arch/updatedict.action?id=${dict.id}');"/>
					<c:if test="${dict.is_used == 1}"><input type="button" class="button_2003" value="禁用" onclick="go('${ctx}/arch/setdictused.action?id=${dict.id}&name=${dict.dict_name}&val=0');"/></c:if>
					<c:if test="${dict.is_used == 0}"><input type="button" class="button_2003" value="启用" onclick="go('${ctx}/arch/setdictused.action?id=${dict.id}&name=${dict.dict_name}&val=1');"/></c:if>
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
