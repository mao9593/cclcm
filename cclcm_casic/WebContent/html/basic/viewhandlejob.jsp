<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>查看历史审批记录</title>
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
		onHoverInfinite();
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
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">查看历史审批记录</td>
	</tr>
	<form id="QueryCondForm" method="GET" action="${ctx}/basic/viewhandlejob.action?type=${type}" name="QueryCondForm" >
		<input type="hidden" id="type" name="type" value="${type}"/>
		<td align="right">
			<table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
					<tr>
						<td align="center">申请人姓名：</td>
				 		<td>
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
				 		 <td align="center">部门：</td>
					 <td>
						<input type="text" id="dept_name" name="dept_name" value="${dept_name}" size="15"/>&nbsp;
					</td>
				 		<td align="center">闭环类型：</td>
				 		<td>
				        	<select name="jobType_code" id="jobType_code">
								<option value="">--所有--</option>
								<c:if test="${type eq 'PAPER'}">
									<option value="DESTROY_PAPER">文件销毁</option>
									<option value="SEND_PAPER">文件外发</option>
									<option value="FILE_PAPER">文件归档</option>
									<option value="DELAY_PAPER">文件延期回收</option>
									<option value="MERGE_ENTITY">台账合并</option>
								</c:if>
								<c:if test="${type eq 'CD'}">
									<option value="DESTROY_CD">光盘销毁</option>
									<option value="SEND_CD">光盘外发</option>
									<option value="FILE_CD">光盘归档</option>
									<option value="DELAY_CD">光盘延期回收</option>
								</c:if>
								<!-- <option value="DESTROY">销毁</option>
								<option value="SEND">外发</option>
								<option value="FILE">归档</option>
								<option value="DELAY">延期回收</option> -->
							</select> 
			    		</td> 
				        <td align="center">
							&nbsp;<input name="button" type="submit" class="button_2003" value="查询">&nbsp;&nbsp;
							&nbsp;<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">
						</td>
					</tr>
				</table>			
		</td>
 	
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
					<display:table requestURI="${ctx}/basic/viewhandlejob.action" uid="item" class="displaytable" name="jobList" pagesize="${pageSize}" sort="page" partialList="true" size="${totalSize}" form="QueryCondForm" excludedParams="*">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column title="申请人" property="user_name"/>
						<display:column title="部门" property="dept_name"/>
						<display:column title="任务类型" property="jobType.jobTypeName"/>
						<display:column title="密级" property="seclv_name"/>
						<display:column title="申请时间" property="start_time_str" />
						<display:column title="申请状态" property="job_status_name"/>
						<display:column title="操作" style="width:50px">
							<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/basic/viewhandlejobdetail.action?type=${type}&job_code=${item.job_code}');"/>
						</display:column>
					</display:table>
					</td>
				</tr>
			</table>
         </td>
	</tr>
	
	</form>
</table>
</body>
</html>
