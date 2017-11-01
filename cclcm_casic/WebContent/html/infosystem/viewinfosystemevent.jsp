<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>查看申请列表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
    <script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>   
    <script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
<script>
$(document).ready(function(){
	onHover();
	preCalendarDay();
});
function preCalendarDay(){
	Calendar.setup({inputField: "startTime", button: "startTime_trigger", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d %H:%M:%S", cache: true, weekNumbers:true, showOthers: true, step: 1});
	Calendar.setup({inputField: "endTime", button: "endTime_trigger", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d %H:%M:%S", cache: true, weekNumbers:true, showOthers: true, step: 1});
}
function clearFindForm(){
		$("#QueryCondForm :text").val("");
		$("#QueryCondForm select").val("");
	}
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<form id="QueryCondForm" name="QueryCondForm" method="POST" action="${ctx}/infosystem/viewinfosystemevent.action" >
	<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
		<tr>
			<td class="title_box">申请列表</td>
		</tr>	
		<tr>
			<td align="right">
		 		<table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
					<tr>
						<td align="center">申请类型</td>
					 	<td>
					 		<select name="event_type" id="event_type">
								<option value="">--请选择--</option>
								<option value="5">计算机维修</option>
								<option value="6">计算机重装系统</option>
								<option value="7">计算机退网</option>
							</select>
					 	</td>		 		
					 	<td align="center">作业密级</td>
					 	<td >
					 		<select name="seclv_code" id="seclv_code">
							<option value="">--所有--</option>
							<s:iterator value="#request.seclvList" var="seclv">
								<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
							</s:iterator>
						</select>
					 	</td>
					 	<td width="8%" align="center">申请状态</td>
					 	<td width="20%">
					        <select name="job_status">
	    						<option value="">--全部--</option>
	    						<option value="none" <c:if test="${job_status eq 'none'}">selected</c:if>>待审批</option>
	    						<option value="under" <c:if test="${job_status eq 'under'}">selected</c:if>>审批中</option>
	    						<option value="false" <c:if test="${job_status eq 'false'}">selected</c:if>>已驳回</option>
	    						<option value="true" <c:if test="${job_status eq 'true'}">selected</c:if>>已通过</option>
	    						<option value="done" <c:if test="${job_status eq 'done'}">selected</c:if>>已关闭</option>
	    					</select>
				    	</td>
					 </tr>
					 <tr>
					 	<td align="center">申请时间</td><!-- 做到点击出现所有部门，能够选择 -->
					 	<td >
							<input type="text" name="startTime" readonly="readonly" style="cursor:hand;" value="${startTime}"/>
	        				<img src="${ctx}/_image/time2.jpg" id="startTime_trigger">
				    	</td> 
				    	<td align="center">至</td><!-- 做到点击出现部门内所有人，能够选择 -->
					 	<td>
					        <input type="text" name="endTime" readonly="readonly" style="cursor:hand;" value="${endTime}"/>
	        				<img src="${ctx}/_image/time2.jpg" id="endTime_trigger">
	        			</td> 
					 	<td align="center" colspan="2" >
					 		<input name="button" type="submit" class="button_2003" value="查询" onclick="return checkTime();">&nbsp;
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
					<display:table requestURI="${ctx}/infosystem/viewinfosystemevent.action" id="item" class="displaytable" name="eventList" 
					pagesize="15" sort="list" excludedParams="*" form="QueryCondForm">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column property="event_name" title="申请类型"  maxLength="15"/>
						<display:column property="user_name" title="申请人"  maxLength="15"/>
						<display:column property="event_reason" title="申请原因"  maxLength="15"/>
						<display:column property="seclv_name" title="密级"  maxLength="15"/>
						<display:column property="apply_time_str" title="申请时间"  maxLength="15"/>
						<display:column property="summ" title="备注" maxLength="15"/>
						<display:column property="job_status_name" title="申请状态"  maxLength="15"/>
						<display:column title="操作" style="width:100px">
<%-- 							<c:choose>
								<c:when test="${item.job_status == 'false' }">
									<c:if test="${item.event_type == 5}"><!-- 维修 -->
										<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/infosystem/viewinfosystemeventdetail.action?event_code=${item.event_code}');"/>&nbsp;	
									</c:if>
									<c:if test="${item.event_type == 6}"><!-- 重装 -->
										<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/infosystem/viewinfosystemeventdetail.action?event_code=${item.event_code}');"/>&nbsp;	
									</c:if>
									<c:if test="${item.event_type == 7}"><!-- 退网 -->
										<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/infosystem/viewinfosystemeventdetail.action?event_code=${item.event_code}');"/>&nbsp;	
									</c:if>
									<c:if test="${item.event_type == 8}"><!-- USBKEY -->
										<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/infosystem/viewusbkeyeventdetail.action?event_code=${item.event_code}');"/>&nbsp;	
									</c:if>
									<c:if test="${item.event_type == 9}"><!-- 开通端口 -->
										<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/infosystem/viewopenporteventdetail.action?event_code=${item.event_code}');"/>&nbsp;	
									</c:if>
									<c:if test="${item.event_type == 10}"><!-- 保留本地打印 -->
										<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/infosystem/viewlocalprintereventdetail.action?event_code=${item.event_code}');"/>&nbsp;	
									</c:if>
									<c:if test="${item.event_type == 1}"><!-- 新增网络机 -->
										<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/computer/approvecomputerjob.action?history=Y&job_code=${item.job_code}');"/>&nbsp;
									</c:if>
									<c:if test="${item.event_type == 2}"><!-- 新增单机 -->
										<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/computer/approvecomputerjob.action?history=Y&job_code=${item.job_code}');"/>&nbsp;
									</c:if>
									<c:if test="${item.event_type == 3}"><!-- 计算机变更 -->
										<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/computer/approvecomputerjob.action?history=Y&job_code=${item.job_code}');"/>&nbsp;
									</c:if>
									<c:if test="${item.event_type == 4}"><!-- 计算机报废 -->
										<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/computer/approvecomputerjob.action?history=Y&job_code=${item.job_code}');"/>&nbsp;
									</c:if>
								</c:when>
								<c:otherwise> --%>
									<c:if test="${item.event_type == 5}"><!-- 维修 -->
										<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/infosystem/approveinfosystemjob.action?event_code=${item.event_code}&history=Y');"/>&nbsp;	
									</c:if>
									<c:if test="${item.event_type == 6}"><!-- 重装 -->
										<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/infosystem/approveinfosystemjob.action?event_code=${item.event_code}&history=Y');"/>&nbsp;	
									</c:if>
									<c:if test="${item.event_type == 7}"><!-- 退网 -->
										<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/infosystem/approveinfosystemjob.action?event_code=${item.event_code}&history=Y');"/>&nbsp;	
									</c:if>
									<c:if test="${item.event_type == 8}"><!-- USBKEY -->
										<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/infosystem/approveinfosystemjob.action?event_code=${item.event_code}&history=Y');"/>&nbsp;	
									</c:if>
									<c:if test="${item.event_type == 9}"><!-- 开通端口 -->
										<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/infosystem/approveinfosystemjob.action?event_code=${item.event_code}&history=Y');"/>&nbsp;		
									</c:if>
									<c:if test="${item.event_type == 10}"><!-- 保留本地打印 -->
										<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/infosystem/approveinfosystemjob.action?event_code=${item.event_code}&history=Y');"/>&nbsp;		
									</c:if>
									<c:if test="${item.event_type == 1}"><!-- 新增网络机 -->
										<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/computer/approvecomputerjob.action?history=Y&job_code=${item.job_code}');"/>&nbsp;
									</c:if>
									<c:if test="${item.event_type == 2}"><!-- 新增单机 -->
										<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/computer/approvecomputerjob.action?history=Y&job_code=${item.job_code}');"/>&nbsp;
									</c:if>
									<c:if test="${item.event_type == 3}"><!-- 计算机变更 -->
										<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/computer/approvecomputerjob.action?history=Y&job_code=${item.job_code}');"/>&nbsp;
									</c:if>
									<c:if test="${item.event_type == 4}"><!-- 计算机报废 -->
										<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/computer/approvecomputerjob.action?history=Y&job_code=${item.job_code}');"/>&nbsp;
									</c:if>
<%-- 								</c:otherwise>
							</c:choose> --%>
							<c:choose>
								<c:when test="${item.job_status eq 'none'}">
									<input type="button" class="button_2003" value="撤销" onclick="go('${ctx}/computer/deleteevent.action?event_code=${item.event_code}');"/>
								</c:when>
								<c:otherwise>
									<input type="button" class="button_2003" value="撤销" disabled="disabled"/>
								</c:otherwise>
							</c:choose>
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