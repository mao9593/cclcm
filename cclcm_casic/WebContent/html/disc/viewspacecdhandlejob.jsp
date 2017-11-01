<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>查看空白盘审批记录</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
    <script>
	<!--
	$(document).ready(function(){
		onHover();
		optionSelect();
	});
	function clearFindForm(){
		$("#user_name").val("");
		$("#seclv_code").val("");
		$("#jobType_code").val("");
		$("#dept_name").val("");
		
	}
	function optionSelect(){
		$("#user_name").val("${user_name}");
		$("#seclv_code").val("${seclv_code}");
		$("#jobType_code").val("${jobType_code}");
		$("#dept_name").val("${dept_name}");
		
	}
	//-->
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">查看空白盘审批记录</td>
	</tr>
	<form id="QueryCondForm" method="GET" action="${ctx}/disc/viewspacecdhandlejob.action">
		<td align="right">
			<table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
					<tr>
						<td width="10%" align="center">申请人姓名：</td>
				 		<td width="15%">
				 			<input type="text" name="user_name" size="15" id="user_name"/>
				 		</td>
				 		
				 		 <td align="center">密级：</td>
					 <td>
						<select name="seclv_code" id="seclv_code">
							<option value="">--所有--</option>
							<s:iterator value="#request.seclvList" var="seclv">
								<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
							</s:iterator>
						</select>
					</td>
				 		
				        <td align="center">
							&nbsp;<input name="button" type="submit" class="button_2003" value="查询">&nbsp;&nbsp;
							&nbsp;<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">
						</td>
					</tr>
				</table>			
		</td>
 	</form>
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
					<display:table uid="job" class="displaytable" name="jobList" pagesize="15" sort="list" defaultsort="6">
						<display:column title="序号">
							<c:out value="${job_rowNum}"/>
						</display:column>
						<display:column title="申请人" property="user_name"/>
						<display:column title="部门" property="dept_name"/>
						<display:column title="任务类型" property="jobType.jobTypeName"/>
						<display:column title="密级" property="seclv_name"/>
						<display:column title="申请时间" property="start_time_str" />
						<display:column title="申请状态" property="job_status_name"/>
						<display:column title="操作" style="width:50px">
							<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/disc/viewspacecdhandlejobdetail.action?type=SPACECD&job_code=${job.job_code}');"/>
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
