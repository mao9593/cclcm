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
		addSelectAllCheckbox();
		preCalendar();
	});
	function clearFindForm(){
		$("#QueryCondForm :text").val("");
		$("#QueryCondForm select").val("");
	}
	function addEnterJob(){
		var event_ids = "";
		var submitable = true;
		if($(":checkbox:checked[value!='']").size()>0){
			$(":checkbox:checked[value!='']").each(function (){
				event_ids += this.value+":";
				//seclv_code = this.value;
			});
			if(submitable){
				$("#event_ids").val(event_ids);
				//$("#seclv_code").val(seclv_code);
				$("#hid_form").submit();
			}else{
				return false;
			}
		}else{
			alert("请先勾选需要审批的作业");
			return false;
		}
	}
</script>
</head>

<body oncontextmenu="self.event.returnValue=false">
<!-- <form method="post" action="${ctx}/basic/addmultiprocessjob.action" id="hid_form">  -->
<form method="post" action="${ctx}/enter/addenterprocessjob.action" id="hid_form">
	<input type="hidden" name="jobType" value="${jobType}"/>
	<input type="hidden" name="event_ids" id="event_ids"/>	
	<input type="hidden" name="seclv_code" id="seclv_code"/> 
	<input type="hidden" name="user_iidd" value="${curUser.user_iidd}"/>
	<input type="hidden" name="dept_id" value="${curUser.dept_id}"/>
	<input type="hidden" name="actionContext" value="enter/submitenterevent.action"/>
</form>
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">已添加的录入申请列表</td>
	</tr>
	<tr>
		<td align="right">
			<form id="QueryCondForm" method="POST" action="${ctx}/enter/submitenterevent.action">	
				<table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
					<tr>
						<td width="8%" align="center">密级 ：</td>
				 		<td width="15%">
				 			<c:set var="seclv1" value="${seclv_code}" scope="request"/>
        					<select name="seclv_code">
        						<option value="">--全部--</option>
    							<s:iterator value="#request.seclvList" var="seclv">
									<option value="${seclv.seclv_code}" <s:if test="#request.seclv1==#seclv.seclv_code">selected="selected"</s:if>>${seclv.seclv_name}</option>
								</s:iterator>
    						</select>
				 		</td>
				 		<td width="8%" align="center">申请时间：</td>
				 		<td width="22%">
				        	<input type="text" name="startTime" readonly="readonly" style="cursor:hand;" value="${startTime_str}"/>
        					<img src="${ctx}/_image/time2.jpg" id="startTime_trigger">&nbsp;
			    		</td> 
			    		<td width="8%" align="center">至：</td>
				 		<td width="22%">
				        	<input type="text" name="endTime" readonly="readonly" style="cursor:hand;" value="${endTime_str}"/>
        					<img src="${ctx}/_image/time2.jpg" id="endTime_trigger">&nbsp;
			    		</td> 
				        <td align="center">
							&nbsp;<input name="button" type="submit" class="button_2003" value="查询" onclick="return checkTime();">&nbsp;
							&nbsp;<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">
						</td>
					</tr>
				</table>		
 			</form>
		</td>
	</tr>
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
					<display:table requestURI="${ctx}/enter/submitenterevent.action" uid="item" class="displaytable" name="eventList" pagesize="15" sort="list">
						<display:column title="选择">
						<!-- 	<input type="checkbox" name="event_code" id="${item.event_code}" value="${item.seclv_code}"/>  -->
							<input type="checkbox" value="${item.id}" name="event_id"/>
						</display:column>
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column title="文件名称" property="zipfile" maxLength="20"/>				
						<display:column title="密级" property="seclv_name"/>
						<display:column title="类型" property="file_type_name" maxLength="15"/>
						<display:column title="载体归属" property="scope_name" maxLength="15"/>						
						<display:column title="申请时间" property="apply_time_str"/>
						<display:column title="操作" style="width:150px">
						<div>
							<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/enter/viewentereventdetail.action?event_code=${item.event_code}&op=view');"/>&nbsp;							
							<input type="button" class="button_2003" value="修改" onclick="go('${ctx}/enter/updateenterevent.action?event_code=${item.event_code}&file_type=${item.file_type}&scope=${item.scope}');"/>&nbsp;
							<input type="button" class="button_2003" value="删除" onclick="if(confirm('确定要删除该申请？'))go('${ctx}/enter/delenterevent.action?event_code=${item.event_code}');"/>
						</div>
						</display:column>
					</display:table>
					</td>
				</tr>
			</table>
         </td>
	</tr>
	<tr><td><input type="button" value="申请录入" onclick="addEnterJob();" class="button_2003"/></td></tr>
</table>
</body>
</html>
