<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>录入作业管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
<script>
<!--
	$(document).ready(function(){
		onHover();
		preCalendar();
	});
	function clearFindForm(){
		$("#QueryCondForm :text").val("");
		$("#QueryCondForm select").val("");
	}
	function chkCancel(job_code){
		if(confirm("确定要撤销该审批申请？")){
			go("${ctx}/basic/canceljob.action?jobType_code=${jobType.jobTypeCode}&job_code="+escape(job_code)+"&actionContext="+escape('${actionContext}'));
		}
	}
	function chkClose(job_code,job_status){
		var con = false;
		if(job_status == 'true'){
			if(confirm("流程关闭后，任务将无法录入。如果确认审批单内的载体已经录入完毕或者放弃录入，请点击确定。")){
				con = true;
			}
		}else{
			if(confirm("确定要关闭该流程？")){
				con = true;
			}
		}
		if(con){
			go("${ctx}/basic/closejob.action?jobType_code=${jobType_code}&job_code="+escape(job_code)+"&actionContext="+escape('${actionContext}'));
		}
	}
//-->
</script>
</head>

<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">已添加的录入申请列表</td>
	</tr>
	<tr>
		<td class="nav_box" align="right">
		<form id="QueryCondForm" method="POST" action="${ctx}/enter/manageenterevent.action">
        	作业密级 ：
        	<c:set var="seclv1" value="${seclv_code}" scope="request"/>
        	<select name="seclv_code">
        		<option value="">--全部--</option>
    			<s:iterator value="#request.seclvList" var="seclv">
					<option value="${seclv.seclv_code}" <s:if test="#request.seclv1==#seclv.seclv_code">selected="selected"</s:if>>${seclv.seclv_name}</option>
				</s:iterator>
    		</select>
    		申请状态：
    		<select name="job_status">
    			<option value="">--全部--</option>
    			<option value="none" <c:if test="${job_status eq 'none'}">selected</c:if>>待审批</option>
    			<option value="under" <c:if test="${job_status eq 'under'}">selected</c:if>>审批中</option>
    			<option value="false" <c:if test="${job_status eq 'false'}">selected</c:if>>已驳回</option>
    			<option value="true" <c:if test="${job_status eq 'true'}">selected</c:if>>已通过</option>
    			<option value="done" <c:if test="${job_status eq 'done'}">selected</c:if>>已关闭</option>
    		</select>   
    		录入状态：
    		<select name="import_status">
    			<option value="">--全部--</option>
    			<option value="0" <c:if test="${import_status == '0'}">selected</c:if>>未录入</option>
    			<option value="1" <c:if test="${import_status == '1'}">selected</c:if>>已录入</option> 			
    		</select> 		
 			添加时间：
 			<input type="text" name="startTime" readonly="readonly" style="cursor:hand;" value="${startTime_str}"/>
        	<img src="${ctx}/_image/time2.jpg" id="startTime_trigger">&nbsp;
			至：
			<input type="text" name="endTime" readonly="readonly" style="cursor:hand;" value="${endTime_str}"/>
        	<img src="${ctx}/_image/time2.jpg" id="endTime_trigger">&nbsp;
			&nbsp;<input name="button" type="submit" class="button_2003" value="查询" onclick="return checkTime();">
			&nbsp;<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">
 		</form>
		</td>
	</tr>
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
					<display:table requestURI="${ctx}/enter/manageenterevent.action" uid="item" class="displaytable" name="eventList" pagesize="15" sort="list">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column title="文件名称" property="zipfile"/>				
						<display:column title="作业密级" property="seclv_name"/>
						<display:column title="用途" property="usage_name"/>
						<display:column title="申请时间" property="apply_time_str"/>
						<display:column title="申请状态" property="job_status_name"/>
						<display:column title="录入状态" property="import_status_name"/>
						<display:column title="操作" style="width:150px">
							<div>
								<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/enter/viewentereventdetail.action?event_code=${item.event_code}');"/>
								<c:choose>
									<c:when test="${item.job_status eq 'none'}">
										&nbsp;<input type="button" class="button_2003" value="撤销" onclick="chkCancel('${item.job_code}')"/>
									</c:when>
									<c:otherwise>
										&nbsp;<input type="button" class="button_2003" value="撤销" disabled="disabled"/>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${item.job_status eq 'true' or item.job_status eq 'false'}">
										&nbsp;<input type="button" class="button_2003" value="关闭" onclick="chkClose('${item.job_code}','${item.job_status}')"/>
									</c:when>
									<c:otherwise>
										&nbsp;<input type="button" class="button_2003" value="关闭" disabled="disabled"/>
									</c:otherwise>
								</c:choose>
							</div>
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
