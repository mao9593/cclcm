<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>查看定密依据库</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script>
	<!--
		function deleteBurner(id){
		    var url="${ctx}/print/delprintevent.action?type=F&id="+escape(id);
		    if(confirm("确定删除该定密依据内容？")){
		    	window.location.href =url;
		    }
		}
	//-->
	</script>
</head>

<body oncontextmenu="self.event.returnValue=false">
<table width="70%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">定密依据列表</td>
	</tr>
	<tr>
		<td class="nav_box" align="right">
			<input name="button" type="button" class="button_2003" onClick="go('${ctx}/print/addfixedaccording.action');" value="新增定密依据">
		</td>
	</tr>
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
					<display:table requestURI="${ctx}/print/managefixedaccording.action" id="item1" class="displaytable" name="fixList1">
						<display:column title="序号" style="width:100px">
							<c:out value="${item1_rowNum}"/>
						</display:column>
						<display:column property="type_name" title="类型名称" style="width:100px"/>
						<display:column property="content" title="内容"/>
						<display:column title="操作" style="width:100px">
							<input type="button" class="button_2003" value="修改" onclick="go('${ctx}/print/updatefixedaccording.action?id=${item1.id}');"/>&nbsp;
							<input type="button" class="button_2003" value="删除" onclick="deleteBurner('${item1.id}')";/>
						</display:column>
					</display:table>
					</td>
				</tr>
			</table>
         </td>
	</tr>
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
					<display:table requestURI="${ctx}/print/managefixedaccording.action" id="item2" class="displaytable" name="fixList2">
						<display:column title="序号" style="width:100px">
							<c:out value="${item2_rowNum}"/>
						</display:column>
						<display:column property="type_name" title="类型名称" style="width:100px"/>
						<display:column property="content" title="内容"/>
						<display:column title="操作" style="width:100px">
							<input type="button" class="button_2003" value="修改" onclick="go('${ctx}/print/updatefixedaccording.action?id=${item2.id}');"/>&nbsp;
							<input type="button" class="button_2003" value="删除" onclick="deleteBurner('${item2.id}')";/>
						</display:column>
					</display:table>
					</td>
				</tr>
			</table>
         </td>
	</tr>
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
					<display:table requestURI="${ctx}/print/managefixedaccording.action" id="item3" class="displaytable" name="fixList3">
						<display:column title="序号" style="width:100px">
							<c:out value="${item3_rowNum}"/>
						</display:column>
						<display:column property="type_name" title="类型名称" style="width:100px"/>
						<display:column property="content" title="内容"/>
						<display:column title="操作" style="width:100px">
							<input type="button" class="button_2003" value="修改" onclick="go('${ctx}/print/updatefixedaccording.action?id=${item3.id}');"/>&nbsp;
							<input type="button" class="button_2003" value="删除" onclick="deleteBurner('${item3.id}')";/>
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
