<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>查看地点</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
    <script>
	<!--
	$(document).ready(function(){
		onHover();
		init();
	});
	function init(){
		
	}
	function confirmDel(){
		if(confirm("确定要删除地点？")){
			return true;
		}
	}
	//-->
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">地点列表</td>
	</tr>
		<form id="QueryCondForm" method="POST" action="${ctx}/basic/addplace.action">
			<td class="nav_box" align="right">
				<input name="button_1" type="submit" class="button_2003" value="添加" >
			</td>
	 	</form>
	<tr>
		<form name="form1"> 
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
					<display:table requestURI="${ctx}/basic/viewplace.action" id="item" class="displaytable" name="places" pagesize="15" sort="list">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column title="地点代号" property="place_code"/>
						<display:column title="地点名称" property="place_name"/>
						<display:column title="地点描述" property="place_desc"/>
						<display:column title="操作" style="width:200px">
							<input type="button" class="button_2003" value="更新地点" onclick="go('${ctx}/basic/updateplace.action?place_code=${item.place_code}');"/>
							<c:if test="${item.is_sealed == 'N'}">
							<input type="button" class="button_2003" value="删除地点" onclick="if (confirmDel()) go('${ctx}/basic/delplace.action?place_code=${item.place_code}');"/>
							</c:if>
							<c:if test="${item.is_sealed == 'Y'}">
							<input type="button" class="button_2003" value="删除地点" disabled="true"/>
							</c:if>
						</display:column>
					</display:table>
					</td>
				</tr>
			</table>
         </td>
        </form> 
	</tr>
</table>
</body>
</html>
