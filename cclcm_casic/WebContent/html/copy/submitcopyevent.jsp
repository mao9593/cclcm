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
	function addCopyJob(){
		var event_codes = "";
		var seclv_code = -1;
		var submitable = true;
		if($(":checkbox:checked[value!='']").size()>0){
			$(":checkbox:checked[value!='']").each(function (){
				if(seclv_code == -1 || seclv_code == this.value){
					event_codes += this.id+":";
					seclv_code = this.value;
				}else{
					alert("勾选的申请密级不一致，请确认");
					submitable =  false;
				}
			});
			if(submitable){
				$("#event_codes").val(event_codes);
				$("#seclv_code").val(seclv_code);
				$("#hid_form").submit();
			}else{
				return false;
			}
		}else{
			alert("请先勾选需要审批的作业");
			return false;
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
<form method="post" action="${ctx}/basic/addmultiprocessjob.action" id="hid_form">
	<input type="hidden" name="jobType" value="${jobType}"/>
	<input type="hidden" name="event_codes" id="event_codes"/>	
	<input type="hidden" name="seclv_code" id="seclv_code"/> 
	<input type="hidden" name="user_iidd" value="${curUser.user_iidd}"/>
	<input type="hidden" name="dept_id" value="${curUser.dept_id}"/>
	<input type="hidden" name="actionContext" value="copy/submitcopyevent.action"/>
</form>
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">已添加的复印申请列表</td>
	</tr>
	<tr>
		<td class="nav_box" align="right">
		<form id="QueryCondForm" method="POST" action="${ctx}/copy/submitcopyevent.action">	
				    		
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
					<display:table requestURI="${ctx}/copy/submitcopyevent.action" uid="item" class="displaytable" name="eventList" pagesize="15" sort="list">
						<display:column title="选择">
							<input type="checkbox" name="event_code" id="${item.event_code}" value="${item.seclv_code}"/>
						</display:column>
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>				
						<display:column title="文件名称" property="file_name"/>										
						<display:column title="源文件编号" property="originalid"/>
						<display:column title="密级" property="seclv_name"/>
						<display:column title="复印份数" property="copy_num"/>						
						<display:column title="申请时间" property="apply_time_str"   sortable="true"/>
						<display:column title="操作" style="width:150px">
						<div>
							<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/copy/viewcopyeventdetail.action?event_code=${item.event_code}&op=view');"/>&nbsp;							
							<input type="button" class="button_2003" value="修改" onclick="go('${ctx}/copy/updatecopyevent.action?event_code=${item.event_code}');"/>&nbsp;
							<input type="button" class="button_2003" value="删除" onclick="if(confirm('确定要删除该申请？'))go('${ctx}/copy/delcopyevent.action?event_code=${item.event_code}');"/>
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
			<input type="button" value="提交申请" onclick="addCopyJob();" class="button_2003"/>
			<input type="checkbox" onclick="selectAll(this);" class="button_2003"/>&nbsp;全选
		</td>
	</tr>
</table>
</body>
</html>
