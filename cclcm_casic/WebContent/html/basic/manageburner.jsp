<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>查看刻录机列表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script>
	<!--
		function deleteBurner(burner_code){
		    var url="${ctx}/basic/delburner.action?burner_code="+escape(burner_code);
		    if(confirm("确定删除该刻录机？")){
		    	window.location.href =url;
		    }
		}
	//-->
	</script>
</head>

<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">刻录机列表</td>
	</tr>
	<tr>
		<td class="nav_box" align="right">
			<input name="button" type="button" class="button_2003" onClick="go('${ctx}/basic/addburner.action');" value="增加新刻录机 ">
		</td>
	</tr>
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
					<display:table requestURI="${ctx}/basic/manageburner.action" id="item" class="displaytable" name="burnerList">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column property="burner_name" title="刻录机名称"  maxLength="15"/>
						<display:column property="burner_type" title="类型"  maxLength="15"/>
						<display:column property="burner_usefor_name" title="用途"/>
						<display:column property="burner_path" title="路径"  maxLength="15"/>
						<display:column property="burner_brand" title="品牌"  maxLength="15"/>
						<display:column property="burner_model" title="型号"  maxLength="15"/>
						<display:column property="burner_location" title="位置"  maxLength="15"/>
						<display:column property="dept_name" title="所属部门"  maxLength="15"/>
						<display:column property="console_name" title="控制台"  maxLength="15"/>
						<display:column property="seclv_name" title="密级"/>
						<display:column property="mfp_name" title="一体机"  maxLength="15"/>
						<display:column property="create_time_str"   sortable="true" title="添加时间"/>
						<display:column property="delete_time_str"   sortable="true" title="删除时间"/>
						<display:column title="操作" style="width:100px">
							<c:if test="${!item.is_delete}">
								<input type="button" class="button_2003" value="修改" onclick="go('${ctx}/basic/updateburner.action?burner_code=${item.burner_code}');"/>&nbsp;
								<input type="button" class="button_2003" value="删除" onclick="deleteBurner('${item.burner_code}')";/>
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
