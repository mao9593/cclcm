<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>个人涉密资料台帐管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
    <script language="javascript" src="${ctx}/_script/My97DatePicker/WdatePicker.js"></script>  
<script>
	$(document).ready(function(){
		onHover();
	});
	function clearFindForm(){
		$("#QueryCondForm :text").val("");
		$("#QueryCondForm select").val("");
	}
	function chkCancel(event_code,apply_type){
		if(confirm("确定要撤销该申请？")){
			var url = "${ctx}/publicity/delreportevent.action?type=ajax&event_code="+escape(event_code)+"&apply_type="+escape(apply_type);
			xmlHttp.open("GET", url, true);
			xmlHttp.onreadystatechange = updatePage;
			xmlHttp.send(null);
		}
	}
	function updatePage() {
		if (xmlHttp.readyState == 4) {
			  var response = xmlHttp.responseText;
			  QueryCondForm.submit();
		}
	}	
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">已添加的涉密资料台帐申请列表</td>
	</tr>
	<tr>
		<td align="right">
		<form id="QueryCondForm" method="GET" action="${ctx}/carriermanage/managepersonalfileevent.action" name="QueryCondForm">
			<table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
					<tr>
				 		<td align="center">年份</td>
						<td>
							<input type="text" id="file_year" name="file_year" value="${file_year}" onclick="WdatePicker({dateFmt:'yyyy'})" class="Wdate" size="5"/>
						</td>
						<td align="center">季度</td>
						<td>
							<select name="file_quarter" id="file_quarter">
								<option value="">--季度--</option>
								<option value="一季度"<c:if test="${file_quarter == '一季度'}">selected</c:if>>一季度</option>
								<option value="二季度"<c:if test="${file_quarter == '二季度'}">selected</c:if>>二季度</option>
								<option value="三季度"<c:if test="${file_quarter == '三季度'}">selected</c:if>>三季度</option>
								<option value="四季度"<c:if test="${file_quarter == '四季度'}">selected</c:if>>四季度</option>
			    			</select>&nbsp;
						</td>
						<td align="center">单位</td>
						<td>
							<select name="duty_entp_id" id="duty_entp_id">
								<option value="">--请选择--</option>
								<option value="总部"<c:if test="${duty_entp_id == '总部'}">selected</c:if>>总部</option>
								<option value="24所"<c:if test="${duty_entp_id == '24所'}">selected</c:if>>24所</option>
								<option value="26所"<c:if test="${duty_entp_id == '26所'}">selected</c:if>>26所</option>
								<option value="44所"<c:if test="${duty_entp_id == '44所'}">selected</c:if>>44所</option>
				    		</select>&nbsp;	
						</td>
				 		<td align="center">申请状态</td>
				 		<td>
				        	<select name="job_status">
    							<option value="">--全部--</option>
    							<option value="none" <c:if test="${job_status eq 'none'}">selected</c:if>>待审批</option>
    							<option value="under" <c:if test="${job_status eq 'under'}">selected</c:if>>审批中</option>
    							<option value="false" <c:if test="${job_status eq 'false'}">selected</c:if>>已驳回</option>
    							<option value="true" <c:if test="${job_status eq 'true'}">selected</c:if>>已通过</option>
    							<option value="done" <c:if test="${job_status eq 'done'}">selected</c:if>>已关闭</option>
    						</select>
			    		</td>	  		
				        <td align="center" colspan="6">
							<input name="button" type="submit" class="button_2003" value="查询";">&nbsp;
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
					<display:table requestURI="${ctx}/carriermanage/managepersonalfileevent.action" uid="item" class="displaytable" name="eventList" 
					pagesize="15" sort="list"  excludedParams="*" form="QueryCondForm">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column property="file_year" title="年份"/>
						<display:column property="file_quarter" title="季度"/>
						<display:column property="duty_entp_id"  title="单位"/>
						<display:column title="责任部门" property="dept_name"/>
						<display:column title="责任人" property="user_name"/>				
						<display:column title="申请状态" property="job_status_name"/>
						<display:column title="申请时间" property="apply_time_str" sortable="true"/>
						<display:column title="操作" style="width:150px">
							<div>
								<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/carriermanage/approvepersonalfilejob.action?history=Y&job_code=${item.job_code}');;"/>
							<input type="button" class="button_2003" value="撤销" onclick="chkCancel('${item.event_code}');"/>
							</div>
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
