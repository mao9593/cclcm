<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>档案查询列表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
		<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
	<link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
	<script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script>
	$(document).ready(function(){
		onHover();
		optionSelect();
	});
	function optionSelect(){
		$("#template_id").val("${template_id}");
	}
	function clearFindForm(){
		$("#ArchQueryCondForm :text").val("");
		$("#template_id").val("");
	}	
	function wopen(id){
		var url = '${ctx}/arch/viewarchdetail.action?id='+id;
		window.open(url,'','height='+(screen.availHeight-40)+', width='+(screen.availWidth-5)+', top=0, left=0, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no');
	}
	function addBorrowJob(id){
		$("#id").val(id);
		$("#hid_event_form").submit();
	}	
	</script>
</head>

<body oncontextmenu="self.event.returnValue=false">
<form method="post" action="${ctx}/arch/addarchprocessjob.action" id="hid_event_form">
	<input type="hidden" name="id"  id="id"/>
	<input type="hidden" name="user_iidd" value="${curUser.user_iidd}"/>
	<input type="hidden" name="dept_id" value="${curUser.dept_id}"/>
	<input type="hidden" name="jobType" value="BRWARCH"/>
</form>
<form id="ArchQueryCondForm" method="GET" action="${ctx}/arch/viewarchbyuser.action">
	<input type="hidden" id="researchFlag" name="researchFlag" value="${researchFlag}"/>
	<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">档案查询列表</td>
	</tr>
	<tr>
		<td align="right">
			<table  table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<td align="center">文件标题：<input type="text" id="file_title" name="file_title" value="${file_title}"/></td>
					<td align="center">档案类别：
						<select name="template_id" id="template_id">
							<option value="">--不限--</option>
							<s:iterator value="#request.archTypeList" var="archType">
								<option value="${archType.template_id}">${archType.type_name}</option>
							</s:iterator>
						</select>
					</td>
					<td>
						<input name="button" type="submit" class="button_2003" value="查询">&nbsp;
						<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">
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
	   					<display:table requestURI="${ctx}/arch/viewarchbyuser.action" id="item" class="displaytable" name="archList"
	   					pagesize="${pageSize}" sort="page" partialList="true" size="${totalSize}" excludedParams="*" form="ArchQueryCondForm">
							<display:column title="序号">
								<c:out value="${item_rowNum}"/>
							</display:column>
							<display:column property="type_name" title="档案类别"/>
							<display:column property="barcode" title="档案条码"/>
							<display:column property="dos_num" title="案卷号"/>
							<display:column property="arch_num" title="档号"/>
							<display:column property="type_code" title="分类号"/>
							<display:column property="file_title" title="文件标题"/>
							<display:column property="page_num" title="页数"/>
							<display:column property="seclv_code" title="密级"/>
							<display:column property="file_carr" title="文件载体类别"/>
							<display:column property="keep_limit" title="保管期限"/>
							<display:column property="status_name" title="状态"/>
							<display:column title="操作" style="width:100px">
								<input type="button" class="button_2003" value="详细" onclick="wopen('${item.id}');"/>
								<c:if test="${item.status eq 0 }">
									<input type="button" class="button_2003" value="借用" onclick="addBorrowJob('${item.id}');"/>
								</c:if>
								<c:if test="${item.status ne 0 }">
									<input type="button" class="button_2003" value="借用" onclick="wopen('${item.id}');" disabled="disabled" title=""/>
								</c:if>
							</display:column>
						</display:table>
					</td>
				</tr>
			</table>
         </td>
	</tr>
</table>
</form>
</body>
</html>
