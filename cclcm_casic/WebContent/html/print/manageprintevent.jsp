<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>打印作业管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
    <script>
    $(document).ready(function(){
		onHover();
		preCalendar();
	});
	function clearFindForm(){
		$("#QueryCondForm :text").val("");
		$("#QueryCondForm select").val("");
	}
	function chkCancel(event_code){
		if(confirm("确定要撤销该流程申请？")){
			go("${ctx}/print/cancelprintevent.action?event_code="+escape(event_code));
		}
	}
    </script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">查看打印作业申请列表</td>
	</tr>
	<tr>
		<td align="right">
			<form id="QueryCondForm" method="POST" action="${ctx}/print/manageprintevent.action">
				<table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
					<tr>
						<td align="center">文件名：</td>
				 		<td>
				 			<input type="text" name="file_title" style="cursor:hand;" value="${file_title}"/>
				 		</td>
				 		<td align="center">密级 ：</td>
				 		<td>
				    		<c:set var="seclv1" value="${seclv_code}" scope="request"/>
				        	<select name="seclv_code">
				        		<option value="">--全部--</option>
				    			<s:iterator value="#request.seclvList" var="seclv">
									<option value="${seclv.seclv_code}" <s:if test="#request.seclv1==#seclv.seclv_code">selected="selected"</s:if>>${seclv.seclv_name}</option>
								</s:iterator>
				    		</select>
			    		</td>
				 		<td align="center">申请状态：</td>
				 		<td><select name="job_status">
				    			<option value="">--全部--</option>
				    			<option value="none" <c:if test="${job_status eq 'none'}">selected</c:if>>待审批</option>
				    			<option value="under" <c:if test="${job_status eq 'under'}">selected</c:if>>审批中</option>
				    			<option value="false" <c:if test="${job_status eq 'false'}">selected</c:if>>已驳回</option>
				    			<option value="true" <c:if test="${job_status eq 'true'}">selected</c:if>>已通过</option>
				    			<option value="done" <c:if test="${job_status eq 'done'}">selected</c:if>>已关闭</option>
				    		</select>
				    	</td>
			 			<td align="center">打印状态 ：</td>
				 		<td>
				    		<select name="print_status">
				    			<option value="">--全部--</option>
				    			<option value="0">未打印</option>
				    			<option value="1">已打印</option>
				    			<option value="2">打印中断</option>
				    			<option value="3">超时不可打印</option>
				    			<option value="4">超时不可补打</option>
				    		</select>
			    		</td>
			    	</tr>
			    	<tr>
				 		<td align="center">提交时间：</td>
				 		<td>
				 			<input type="text" name="startTime" readonly="readonly" style="cursor:hand;" value="${startTime_str}"/>
				        	<img src="${ctx}/_image/time2.jpg" id="startTime_trigger">&nbsp;
						</td>
				 		<td align="center">至：</td>
				 		<td>
							<input type="text" name="endTime" readonly="readonly" style="cursor:hand;" value="${endTime_str}"/>
				        	<img src="${ctx}/_image/time2.jpg" id="endTime_trigger">&nbsp;
				        </td>
				        <td colspan="4">
							&nbsp;<input name="button" type="submit" class="button_2003" value="查询" onclick="return checkTime();">
							&nbsp;<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">
						</td>
					</tr>
				</table>
	 		</form>
		</td>
	</tr>
	<tr>
		<td valign="top">
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
					<display:table requestURI="${ctx}/print/manageprintevent.action" uid="item" class="displaytable" name="eventList" pagesize="15" sort="list">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column title="文件名称" property="file_title" maxLength="30"/>				
						<display:column title="密级" property="seclv_name"/>
						<display:column title="提交时间" property="apply_time_str"   sortable="true"/>			
						<display:column title="申请状态" property="job_status_name"/>
						<display:column title="打印状态" property="print_status_name"/>
						<display:column title="用途" property="usage_name"/>
						<display:column title="操作" style="width:150px">				
							<div>
								<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/print/viewprinteventdetail.action?id=${item.id}&op=view');"/>
								<c:if test="${empty proxyprint_user_iidd or proxyprint_user_iidd == null}">
								<c:choose>
									<c:when test="${item.job_status eq 'none'}">
										&nbsp;<input type="button" class="button_2003" value="撤销" onclick="chkCancel('${item.event_code}')"/>
									</c:when>
									<c:otherwise>
										&nbsp;<input type="button" class="button_2003" value="撤销" disabled="disabled"/>
									</c:otherwise>
								</c:choose>
								</c:if>
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
