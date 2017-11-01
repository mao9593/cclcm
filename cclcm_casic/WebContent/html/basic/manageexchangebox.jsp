<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>查看智能交换柜列表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script>
	<!--
		function deleteExchangeBox(box_code){
		    var url="${ctx}/basic/delexchangebox.action?box_code="+escape(box_code);
		    if(confirm("确定删除该交换柜？")){
		    	window.location.href =url;
		    }
		}
	//-->
	</script>
</head>

<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">智能交换柜列表</td>
	</tr>
	<tr>
		<td class="nav_box" align="right">
			<input name="button" type="button" class="button_2003" onClick="go('${ctx}/basic/addexchangebox.action');" value="增加新智能交换柜">
		</td>
	</tr>
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
					<display:table requestURI="${ctx}/basic/manageexchangebox.action" id="item" class="displaytable" name="exchangeBoxList">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column property="box_name" title="交换柜名称"  maxLength="15"/>
						<display:column property="seclv_name" title="密级"/>
						<display:column property="box_status_name" title="状态"/>
						<display:column property="box_location" title="地点"  maxLength="15"/>
						<display:column title="操作" style="width:100px">
							<c:if test="${!item.is_delete}">
								<input type="button" class="button_2003" value="修改" onclick="go('${ctx}/basic/updateexchangebox.action?box_code=${item.box_code}');"/>&nbsp;
								<input type="button" class="button_2003" value="删除" onclick="deleteExchangeBox('${item.box_code}')";/>
							</c:if>
							<c:if test="${item.is_delete}">
								${item.deleteStatus}
							</c:if>
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
