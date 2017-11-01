<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>密级变更管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
    <script>
    $(document).ready(function(){
		onHoverInfinite();
		preCalendar();
	});
	function clearFindForm(){
		$("#QueryCondForm :text").val("");
		$("#QueryCondForm select").val("");
	}
	function confirmModify(event_code,entity_type){
		if(confirm("确定变更该载体密级？")){
			$("#event_code").val(event_code);
			$("#entity_type").val(entity_type);
			var url = "${ctx}/ledger/confirmmodify.action";
			callServerPostForm(url,document.forms[0]);	
		}
	}
	function getAjaxResult(){	
		if (xmlHttp.readyState == 4 ) {		
			 if(xmlHttp.responseText == "done"){
				alert("变更载体成功");
				$("#QueryCondForm").submit();
			}
		}
	} 
    </script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<form method="POST"  id="hiddenform">
	<input type="hidden" id="event_code" name="event_code"/>
	<input type="hidden" id="entity_type" name="entity_type"/>
</form>
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">变更待确认作业列表</td>
	</tr>
	<tr>
		<td align="right">
			<form id="QueryCondForm" method="GET" action="${ctx}/ledger/viewconfirmmodify.action" name="QueryCondForm">
				<table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
					<tr>
						<td align="center">条码号：
							<input type="text" id="barcode" name="barcode" value="${barcode}" size="15"/>&nbsp;
						</td>
				 		<td align="center">密级 ：
				    		<c:set var="seclv1" value="${seclv_code}" scope="request"/>
				        	<select name="seclv_code">
				        		<option value="">--全部--</option>
				    			<s:iterator value="#request.seclvList" var="seclv">
									<option value="${seclv.seclv_code}" <s:if test="#request.seclv1==#seclv.seclv_code">selected="selected"</s:if>>${seclv.seclv_name}</option>
								</s:iterator>
				    		</select>
			    		</td>				 		
				 		<td align="center">提交时间：
				 			<input type="text" name="startTime" readonly="readonly" style="cursor:hand;" value="${startTime_str}"/>
				        	<img src="${ctx}/_image/time2.jpg" id="startTime_trigger">&nbsp;
						</td>
				 		<td align="center">至：
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
		<td valign="top">
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
					<display:table requestURI="${ctx}/ledger/viewconfirmmodify.action" uid="item" class="displaytable" name="eventList" 
					pagesize="15" sort="list" excludedParams="*" form="QueryCondForm">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>		
						<display:column title="申请人" property="user_name"/>
						<display:column title="申请人部门" property="dept_name"/>	
						<display:column title="文件名" property="file_name"/>	
						<display:column title="条码号" property="barcode"/>
						<display:column title="原密级" property="pre_seclv_name"/>
						<display:column title="目标密级" property="trg_seclv_name"/>
						<display:column title="类型" property="entity_type_name"/>	
						<display:column title="提交时间" property="apply_time_str" sortable="true"/>																				
						<display:column title="操作" style="width:150px">				
							<div>
								<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/ledger/viewmodjobdetail.action?is_flag=Y&job_code=${item.job_code}');"/>&nbsp;
								<c:if test="${item.modify_status == 0}">
									<input type="button" class="button_2003" value="确认变更" onclick="confirmModify('${item.event_code}','${item.entity_type}');"/>		
								</c:if>	
								<c:if test="${item.modify_status == 1}">
									<input type="button" class="button_2003" value="确认变更" disabled="disabled"/>		
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
</form>
</body>
</html>
