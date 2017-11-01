<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>待归还档案列表</title>
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
	function returnarch(id,barcode,user_iidd,job_code){
		var url = "${ctx}/arch/setarchreturn.action?id="+id+"&barcode="+barcode+"&user_iidd="+user_iidd+"&job_code="+job_code;
		callServer(url);
	}
	function updateResult(){
		if (xmlHttp.readyState == 4) {
		   var response = xmlHttp.responseText;
		   alert(response);
		   $("#ArchQueryCondForm").submit();
		}
	}
	function askToReturn(user_iidd,job_code){
		var url = "${ctx}/arch/askingtoreturn.action?user_iidd="+user_iidd+"&job_code="+job_code;
		callServer2(url);
	}
	function updatePage(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {					
			if(xmlHttp.responseText == "ok"){
				alert("已催还");
			}
		}
	} 
	</script>
</head>

<body oncontextmenu="self.event.returnValue=false">
<form id="ArchQueryCondForm" method="GET" action="${ctx}/arch/returnarch.action">
	<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">待归还档案列表</td>
	</tr>
	<tr>
		<td align="right">
			<table  table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<td align="center">文件标题：<input type="text" id="file_title" name="file_title" value="${file_title}"/></td>
					<td align="center">用户ID：<input type="text" id="user_iidd" name="user_iidd" value="${user_iidd}"/></td>
					<td align="center">用户姓名：<input type="text" id="user_name" name="user_name" value="${user_name}"/></td>
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
	   					<display:table requestURI="${ctx}/arch/returnarch.action" id="item" class="displaytable" name="eventList" pagesize="15">
							<display:column title="序号">
								<c:out value="${item_rowNum}"/>
							</display:column>
							<display:column property="user_name" title="申请人"/>
							<display:column property="dept_name" title="部门"/>
							<display:column property="file_title" title="文件标题"/>
							<display:column property="arch_type_name" title="档案类别"/>
							<display:column property="barcode" title="档案条码"/>
							<display:column property="seclv_code" title="作业密级"/>
							<display:column title="操作" style="width:220px;">
								<input type="button" class="button_2003" value="档案详细" onclick="wopen('${item.at_record_id}');"/>
								<input type="button" class="button_2003" value="催还" onclick="askToReturn('${item.user_iidd}','${item.job_code}');"/>
								<input type="button" class="button_2003" value="归还" onclick="returnarch('${item.id}','${item.barcode}','${item.user_iidd}','${item.job_code}');"/>
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
