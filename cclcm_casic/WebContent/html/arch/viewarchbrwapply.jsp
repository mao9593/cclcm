<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>查询借阅申请记录</title>
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
	</script>
	<script type="text/javascript">
	function chkCancel(event_code){
	if(confirm("确定要撤销该录入申请？")){
		var url = "${ctx}/arch/cancelarchevent.action?event_code="+escape(event_code);
		callServer(url);
		}
	}
	function updateResult(){
	if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
		if(xmlHttp.responseText == "hasEvents"){
			alert("取消成功");
			go(window.location);
		}else if(xmlHttp.responseText == "noEvent"){
			alert("取消成功,所有申请都已取消,审批单撤销");
			go(window.location);
		}else{
			alert("取消失败，请重试");
			}
		}
	}
	</script>
</head>

<body oncontextmenu="self.event.returnValue=false">
<form id="ArchQueryCondForm" method="GET" action="${ctx}/arch/viewkeeparchbyuser.action">
	<input type="hidden" id="researchFlag" name="researchFlag" value="${researchFlag}"/>
	<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">查询借阅申请记录</td>
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
	   					<display:table requestURI="${ctx}/arch/viewkeeparchbyuser.action" id="item" class="displaytable" name="eventList" pagesize="15">
							<display:column title="序号">
								<c:out value="${item_rowNum}"/>
							</display:column>
							<display:column property="user_name" title="申请人"/>
							<display:column property="dept_name" title="部门"/>
							<display:column property="file_title" title="文件标题"/>
							<display:column property="arch_type_name" title="档案类别"/>
							<display:column property="barcode" title="档案条码"/>
							<display:column property="job_status_name" title="审批状态"/>
							<display:column property="borrow_status_name" title="借入状态"/>
							<display:column title="操作" style="width:170px;">
								<input type="button" class="button_2003" value="档案详细" onclick="wopen('${item.at_record_id}');"/>
								<c:if test="${item.borrow_status eq 0 || item.borrow_status eq 3}">
								<input type="button" class="button_2003" value="撤销" onclick="chkCancel('${item.event_code}');"/>
								</c:if>
								<c:if test="${item.borrow_status ne 0 && item.borrow_status ne 3}">
								<input type="button" class="button_2003" value="撤销" disabled="disabled"/>
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
