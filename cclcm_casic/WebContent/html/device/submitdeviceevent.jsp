<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>提交审批</title>
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
	function addDeviceJob(){
		var event_ids = "";
		var check_result=true;
		if($(":checkbox:checked[value!=''][name='event_id']").size() == 0){
			alert("请先勾选需要审批的作业");
			return false;
		}else{
			$(":checkbox:checked[value!=''][name='event_id']").each(function (){
				event_ids += this.value+":";
			});
			if(check_result){
				$("#event_ids").val(event_ids);
				$("#hid_form").submit();
			}
		}
	}	
	function selectAll(tag){
		if(tag.checked){
			$(":checkbox").attr("checked",true);
		}else{
			$(":checkbox").attr("checked",false);
		}
	}
</script>
</head>

<body oncontextmenu="self.event.returnValue=false">
<form method="post" action="${ctx}/device/adddeviceprocessjob.action" id="hid_form">
	<input type="hidden" name="jobType" value="${jobType}"/>
	<input type="hidden" name="event_ids" id="event_ids"/>
	<input type="hidden" name="actionContext" value="device/submitdeviceevent.action"/>
</form>
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">已添加的介质申请列表</td>
	</tr>
	<tr>
		<td class="nav_box" align="right">
		<form id="QueryCondForm" method="POST" action="${ctx}/device/submitdeviceevent.action">	
			介质状态：
			<select name="med_type">
    			<option value="">--全部--</option>   			
    			<option value="1" <c:if test="${med_type eq '1'}">selected</c:if>>U盘</option>
    			<option value="2" <c:if test="${med_type eq '2'}">selected</c:if>>移动硬盘</option>
    			<option value="3" <c:if test="${med_type eq '3'}">selected</c:if>>笔记本</option>
    			<option value="4" <c:if test="${med_type eq '4'}">selected</c:if>>照相机</option>
    			<option value="5" <c:if test="${med_type eq '5'}">selected</c:if>>录像机</option>
    			<option value="6" <c:if test="${med_type eq '6'}">selected</c:if>>录音笔</option>
    			<option value="8" <c:if test="${med_type eq '8'}">selected</c:if>>软盘</option>    
    			<option value="9" <c:if test="${med_type eq '9'}">selected</c:if>>磁带</option>        			
    		</select>		    		
			密级 ：
        	<c:set var="seclv1" value="${seclv_code}" scope="request"/>
        	<select name="seclv_code">
        		<option value="">--全部--</option>
    			<s:iterator value="#request.seclvList" var="seclv">
					<option value="${seclv.seclv_code}" <s:if test="#request.seclv1==#seclv.seclv_code">selected="selected"</s:if>>${seclv.seclv_name}</option>
				</s:iterator>
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
					<display:table requestURI="${ctx}/device/submitdeviceevent.action" uid="item" class="displaytable" name="eventList" pagesize="15" sort="list">
						<display:column title="选择">
							<input type="checkbox" value="${item.id}" name="event_id"/>
						</display:column>
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>				
						<display:column title="介质类型" property="med_type_name"/>					
						<display:column title="密级" property="seclv_name"/>												
						<display:column title="借用时间  (天)" property="borrow_date"/>												
						<display:column title="申请时间" property="apply_time_str" sortable="true"/>
						<display:column title="操作" style="width:150px">
							<div>
								<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/device/viewdeviceeventdetail.action?event_code=${item.event_code}');"/>							
								<input type="button" class="button_2003" value="修改" onclick="go('${ctx}/device/updatedeviceevent.action?event_code=${item.event_code}');"/>&nbsp;
								<input type="button" class="button_2003" value="删除" onclick="if(confirm('确定要删除该申请？'))go('${ctx}/device/deldeviceevent.action?event_code=${item.event_code}');"/>
							</div>
						</display:column>
					</display:table>
					</td>
				</tr>
			</table>
         </td>
	</tr>
	<tr>
		<td>
			<input type="button" value="提交审批" onclick="addDeviceJob();" class="button_2003"/>
			<input type="checkbox" onclick="selectAll(this);" class="button_2003"/>&nbsp;全选
		</td>
	</tr>
</table>
</body>
</html>
