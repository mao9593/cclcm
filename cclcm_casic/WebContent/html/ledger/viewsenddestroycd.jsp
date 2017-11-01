<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>光盘送销申请列表</title>
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
		preCalendar();
	});
	function clearFindForm(){
		$("#LedgerQueryCondForm :text").val("");
		$("#LedgerQueryCondForm select").val("");
	}
	function chkCancel(job_code){
		if(confirm("确定要撤销该流程申请？")){
			var url = "${ctx}/ledger/cancelsenddestroyjob.action?type=cd&job_code="+escape(job_code);
			xmlHttp.open("GET", url, true);
			xmlHttp.onreadystatechange = updatePage;
			xmlHttp.send(null);
		}
	}
	function updatePage() {
		if (xmlHttp.readyState == 4) {
			  var response = xmlHttp.responseText;
			  LedgerQueryCondForm.submit();
		}
	}
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">光盘送销申请列表</td>
	</tr>
	<form id="LedgerQueryCondForm" method="GET" action="${ctx}/ledger/viewsenddestroycd.action" name="LedgerQueryCondForm">
	<tr>
		<td align="right">
			<table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
				<tr>
			 		<td width="15%" align="center">密级 ：
			 			<c:set var="seclv1" value="${seclv_code}" scope="request"/>
						<select name="seclv_code">
				  			<option value="">--全部--</option>
				   			 <s:iterator value="#request.seclvList" var="seclv">
								<option value="${seclv.seclv_code}" <s:if test="#request.seclv1==#seclv.seclv_code">selected="selected"</s:if>>${seclv.seclv_name}</option>
							 </s:iterator>
						</select>
			 		</td>
			 		<td width="22%" align="center">申请状态：
			 			<select name="job_status">
				 			<option value="">--全部--</option>
				  		  	<option value="none" <c:if test="${job_status eq 'none'}">selected</c:if>>待审批</option>
				   		 	<option value="under" <c:if test="${job_status eq 'under'}">selected</c:if>>审批中</option>
				    		<option value="false" <c:if test="${job_status eq 'false'}">selected</c:if>>已驳回</option>
				    		<option value="true" <c:if test="${job_status eq 'true'}">selected</c:if>>已通过</option>
				    		<option value="done" <c:if test="${job_status eq 'done'}">selected</c:if>>已关闭</option>
				   		</select>
			 		</td>
			 		<td width="28%" align="center">申请时间：
			        	<input type="text" name="startTime" readonly="readonly" style="cursor:hand;" value="${startTime_str}"/>
						<img src="${ctx}/_image/time2.jpg" id="startTime_trigger">&nbsp;
		    		</td> 
		    		<td width="25%" align="center">至：
			        	<input type="text" name="endTime" readonly="readonly" style="cursor:hand;" value="${endTime_str}"/>
						<img src="${ctx}/_image/time2.jpg" id="endTime_trigger">&nbsp;
		    		</td> 
			        <td align="center">
						&nbsp;<input name="button" type="submit" class="button_2003" value="查询" onclick="return checkTime();">&nbsp;
						&nbsp;<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">
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
	   					<display:table requestURI="${ctx}/ledger/viewsenddestroycd.action" uid="item" class="displaytable" name="jobList" 
					pagesize="15" sort="list" excludedParams="*" form="LedgerQueryCondForm">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column title="光盘列表" property="event_names" maxLength="60"/>
						<display:column title="作业审批密级" property="seclv_name"/>
						<display:column title="申请时间" property="start_time_str"   sortable="true"/>
						<display:column title="申请状态" property="job_status_name"/>
						<display:column title="操作" style="width:100px">
							<div>
								<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/ledger/viewsenddestroyjobdetail.action?job_code=${item.job_code}&type=cd');"/>
								<c:choose>
									<c:when test="${item.job_status eq 'none'}">
										&nbsp;<input type="button" class="button_2003" value="撤销" onclick="chkCancel('${item.job_code}');"/>
									</c:when>
									<c:otherwise>
										&nbsp;<input type="button" class="button_2003" value="撤销" disabled="disabled"/>
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
	</form>
</table>	
</body>
</html>
