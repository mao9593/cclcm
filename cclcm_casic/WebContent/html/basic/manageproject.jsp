<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>查看项目列表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script>
	<!--
		function deleteProject(project_code){
		    var url="${ctx}/basic/delproject.action?project_code="+escape(project_code);
		    if(confirm("确定删除该项目？")){
		    	window.location.href =url;
		    }
		}
	//-->
	</script>
</head>

<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">项目列表</td>
	</tr>
	<tr>
		<td class="nav_box" align="right">
			<input name="button" type="button" class="button_2003" onClick="go('${ctx}/basic/addproject.action');" value="增加新项目 ">
		</td>
	</tr>
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
					<display:table requestURI="${ctx}/basic/manageproject.action" id="item" class="displaytable" name="projectList">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column property="project_code" title="项目代号"/>
						<display:column property="project_name" title="项目名称"/>
						<display:column property="project_content" title="说明" maxLength="30"/>
						<display:column property="start_time_str"   sortable="true" title="开始时间"/>
						<display:column property="end_time_str"   sortable="true" title="结束时间"/>
						<display:column title="操作" style="width:100px">
							<c:if test="${!item.is_delete}">
								<input type="button" class="button_2003" value="修改" onclick="go('${ctx}/basic/updateproject.action?project_code=${item.project_code}');"/>&nbsp;
								<input type="button" class="button_2003" value="删除" onclick="deleteProject('${item.project_code}')";/>
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
