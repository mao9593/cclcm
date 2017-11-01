<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>续借档案列表</title>
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
		$("#job_status").val("${job_status}");
	}
	function clearFindForm(){
		$("#ArchQueryCondForm :text").val("");
		$("#template_id").val("");
		$("#job_status").val("");
	}	
	function wopen(id){
		var url = '${ctx}/arch/viewarchdetail.action?id='+id;
		window.open(url,'','height='+(screen.availHeight-40)+', width='+(screen.availWidth-5)+', top=0, left=0, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no');
	}
	function returnarch(id,barcode){
		if(confirm("确定要继续借出该文档？")){
			var url = "${ctx}/arch/setarchrenew.action?id="+id+"&barcode="+barcode;
			callServer(url);
		}
	}
	function updateResult(){
		if (xmlHttp.readyState == 4) {
		   var response = xmlHttp.responseText;
		   alert(response);
		   $("#ArchQueryCondForm").submit();
		}
	}
	</script>
</head>

<body oncontextmenu="self.event.returnValue=false">
<form id="ArchQueryCondForm" method="GET" action="${ctx}/arch/renewarch.action">
	<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">续借档案列</td>
	</tr>
	<tr>
		<td align="right">
			<table  table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<td align="center">文件标题：<input size="10" type="text" id="file_title" name="file_title" value="${file_title}"/></td>
					<td align="center">用户ID：<input size="10" type="text" id="user_iidd" name="user_iidd" value="${user_iidd}"/></td>
					<td align="center">用户姓名：<input size="10" type="text" id="user_name" name="user_name" value="${user_name}"/></td>
					<td align="center">档案类别：
						<select name="template_id" id="template_id">
							<option value="">--不限--</option>
							<s:iterator value="#request.archTypeList" var="archType">
								<option value="${archType.template_id}">${archType.type_name}</option>
							</s:iterator>
						</select>
					</td>
					<td align="center">审批状态：
						 <select id='job_status' name="job_status">
   							<option value="">--不限--</option>
   							<option value="none" <c:if test="${job_status eq 'none'}">selected</c:if>>待审批</option>
   							<option value="under" <c:if test="${job_status eq 'under'}">selected</c:if>>审批中</option>
   							<option value="false" <c:if test="${job_status eq 'false'}">selected</c:if>>已驳回</option>
   							<option value="true" <c:if test="${job_status eq 'true'}">selected</c:if>>已通过</option>
    					</select>&nbsp;&nbsp;&nbsp;
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
	   					<display:table requestURI="${ctx}/arch/renewarch.action" id="item" class="displaytable" name="eventList" pagesize="15">
							<display:column title="序号">
								<c:out value="${item_rowNum}"/>
							</display:column>
							<display:column property="user_name" title="申请人"/>
							<display:column property="dept_name" title="部门"/>
							<display:column property="file_title" title="文件标题"/>
							<display:column property="arch_type_name" title="档案类别"/>
							<display:column property="barcode" title="档案条码"/>
							<display:column property="seclv_name" title="作业密级"/>
							<display:column property="job_status_name" title="审批状态"/>
							<display:column title="操作" style="width:120px;">
								<input type="button" class="button_2003" value="档案详细" onclick="wopen('${item.at_record_id}');"/>
								<c:if test="${item.job_status == 'true'}">
									<input type="button" class="button_2003" value="续借" onclick="returnarch('${item.id}','${item.barcode}');" title="审批已通过，管理员可以借出该文档"/>
								</c:if>
								<c:if test="${item.job_status != 'true'}">
									<input type="button" class="button_2003" value="续借" disabled="disabled" title="该申请尚未审批通过或者已经被驳回"/>
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
