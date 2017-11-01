<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>条码规则列表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
<script>
<!--
	function updateProcess(barcode_code){
	    var url="${ctx}/basic/updatebarcode.action?barcode_code="+escape(barcode_code);
    	window.location.href =url;
	}
	function deleteProcess(barcode_code){
	    var url="${ctx}/basic/delbarcode.action?barcode_code="+escape(barcode_code);
	    if(confirm("确定删除该条码规则？")){
	    	window.location.href =url;
	    }
	}
	function clearFindForm(){
		$("#ProcessQueryCondForm :text").val("");
		$("#seclv_code").val("");
		$("#jobType_code").val("");
		$("#dept_id").val("");
	}
//-->
</script>
</head>

<body oncontextmenu="self.event.returnValue=false" onload="onHover();">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">条码规则列表</td>
	</tr>
	<tr style="padding-bottom: 20px">
		<form id="ProcessQueryCondForm" method="POST" action="${ctx}/basic/managebarcode.action">
		<td class="nav_box" align="right">
			<input name="button"  type="button" class="button_2003" onClick="go('${ctx}/basic/addbarcode.action');" value="添加条码规则">
		</td>
		</form>
	</tr>
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
					<display:table requestURI="${ctx}/basic/managebarcode.action" id="item" class="displaytable" name="barcodes" pagesize="15">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column property="barcode_name" title="条码规则名称"/>
						<display:column title="是否默认">
							${item.is_default == 'Y' ? "是":"否"}
						</display:column>
						<display:column title="密级" maxLength="30">
							${empty item.seclv_names ?"所有":item.seclv_names}
						</display:column>
						<display:column title="用途" maxLength="30">
							${empty item.usage_names ?"所有":item.usage_names}
						</display:column>
						<%-- <display:column title="项目" maxLength="30">
							${empty item.project_names ?"所有":item.project_names}
						</display:column> --%>
						<display:column title="控制台" maxLength="30">
							${empty item.console_names ? "所有":item.console_names}
						</display:column>
						<display:column property="form_name" title="条码形式"/>
						<display:column property="position_name" title="条码位置"/>
						<display:column property="bypage_name" title="条码分配"/>
						<display:column property="perpage_name" title="条码输出"/>
						<display:column property="pageno_name" title="页码输出"/>
						<display:column title="操作">
							<input type="button" class="button_2003" value="修改" onclick="updateProcess('${item.barcode_code}')";/>&nbsp;
							<c:if test="${item.is_default == 'N' }">
								<input type="button" class="button_2003" value="删除" onclick="deleteProcess('${item.barcode_code}')";/>
							</c:if>
							<c:if test="${item.is_default == 'Y' }">
								<input type="button" class="button_2003" value="删除" disabled="disabled" title="默认条码规则不能删除，只能修改"/>
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
