<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>载体归属转换管理</title>
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
		preCalendar();
	});
	function clearFindForm(){
		$("#QueryCondForm :text").val("");
		$("#QueryCondForm select").val("");
	}
	function chkChange(event_code, barcode){
		if(confirm("确定变更该载体的归属？")){
			$("#event_code").val(event_code);
			$("#barcode").val(barcode);
			callServerPostForm("${ctx}/change/changeentityscope.action",document.forms[0]);	
		}
	}
	function getAjaxResult(){
		if (xmlHttp.readyState == 4) {			
			alert("操作完成");
			$("#QueryCondForm").submit();	
		}
	} 
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<form method="POST"  id="hiddenform">
	<input type="hidden" name="event_code" id="event_code"/>
	<input type="hidden" id="barcode" name="barcode"/>
	<input type="hidden" id="duty_user_name" name="duty_user_name" value="${user.user_name}"/>

</form>
<form id="QueryCondForm" method="GET" action="${ctx}/change/managechangeeventlist.action">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">		
	<tr>
		<td class="title_box">载体归属转换申请列表</td>
	</tr>
	<tr>
		<td align="right">
			<table  table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
	 			<tr>
		        	<td width="10%" align="center">密级 ：</td>
		        	<td width="18%">
			        	<c:set var="seclv1" value="${seclv_code}" scope="request"/>
			        	<select name="seclv_code">
			        		<option value="">--全部--</option>
			    			<s:iterator value="#request.seclvList" var="seclv">
								<option value="${seclv.seclv_code}" <s:if test="#request.seclv1==#seclv.seclv_code">selected="selected"</s:if>>${seclv.seclv_name}</option>
							</s:iterator>
			    		</select> 
			    	</td>
		    		<td width="10%" align="center">载体类型：</td>
		        	<td width="20%">
		    			<select name="entity_type">
			    			<option value="">--全部--</option>
			    			<option value="paper" <c:if test="${entity_type eq 'paper'}">selected</c:if>>文件</option>
			    			<option value="cd" <c:if test="${entity_type eq 'cd'}">selected</c:if>>光盘</option>
			    		</select> 
			    	</td> 
			    	<td width="10%" align="center">转换类型：</td>
		        	<td width="20%">
			    		<select name="change_type">
			    			<option value="">--全部--</option>
			    			<option value="toDEPT" <c:if test="${change_type eq 'toDEPT'}">selected</c:if>>个人转部门</option>
			    			<option value="toPERSON" <c:if test="${change_type eq 'toPERSON'}">selected</c:if>>部门转个人</option>
			    		</select>
			    	</td>
			    	<td width="10%" align="center" rowspan="2">
						<input name="button" type="submit" class="button_2003" value="查询" onclick="return checkTime();">&nbsp;
						<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">
					</td>
			    </tr>
			    <tr>  
			    	<td align="center">转换状态：</td>
		        	<td>
		    			 <select name="change_status">
			    			<option value="">--全部--</option>
			    			<option value="0" <c:if test="${change_status eq '0'}">selected</c:if>>未完成</option>
			    			<option value="1" <c:if test="${change_status eq '1'}">selected</c:if>>已完成</option>
			    		</select> 
			    	</td>		
		 			<td align="center">申请时间：</td>
		        	<td>
		 				<input type="text" name="startTime" readonly="readonly" style="cursor:hand;" value="${startTime_str}"/>
		        		<img src="${ctx}/_image/time2.jpg" id="startTime_trigger">
					</td>
					<td align="center">至：</td>
		        	<td>
						<input type="text" name="endTime" readonly="readonly" style="cursor:hand;" value="${endTime_str}"/>
		        		<img src="${ctx}/_image/time2.jpg" id="endTime_trigger">
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
					<display:table requestURI="${ctx}/change/managechangeeventlist.action" uid="item" class="displaytable" name="eventList" pagesize="15" sort="list">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column title="申请人" property="user_name"/>
						<display:column title="申请人部门" property="dept_name"/>
						<display:column title="载体条码" property="barcode" maxLength="30"/>
						<display:column title="文件名称" property="file_name" maxLength="30"/>
						<display:column title="密级" property="seclv_name"/>
						<display:column title="用途" property="usage_name"/>
						<display:column title="归属部门" property="scope_dept_name"/>
						<display:column title="类型" property="change_type_name"/>
						<display:column title="转换状态" property="change_status_name"/>
						<display:column title="申请时间" property="apply_time_str"   sortable="true"/>
						<display:column title="操作" style="width:100px">
						<div>
							<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/change/viewchangeeventdetail.action?event_code=${item.event_code}');"/>
							<c:choose>
								<c:when test="${item.change_status eq '0'}">
									&nbsp;<input type="button" class="button_2003" value="转换" onclick="chkChange('${item.event_code}','${item.barcode}');"/>
								</c:when>
								<c:otherwise>
									&nbsp;<input type="button" class="button_2003" value="转换" disabled="disabled"/>
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
</form>
</body>
</html>
