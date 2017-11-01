<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>扫描录入</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
    <link type="text/css" rel="stylesheet" href="${ctx}/uploadify/uploadify.css"/>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
    <script type="text/javascript" src="${ctx}/uploadify/jquery.uploadify.min.js"></script>
<script>

	$(document).ready(function(){
		onHover();
		preCalendar();
	});
	function clearFindForm(){
		$("#QueryCondForm :text").val("");
		$("#QueryCondForm select").val("");
	}
	function chkEnter(event_code, tag){
		$("#event_code").val(event_code);
		$("#hiddenform").submit();
	}  

</script>
</head>

<body oncontextmenu="self.event.returnValue=false">
<form method="POST"  id="hiddenform" action="${ctx}/enter/uploadscanfiles.action">
	<input type="hidden" name="event_code" id="event_code"/>
</form>
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">已扫描录入列表</td>
	</tr>
	<tr>
		<td align="right">
			<form id="QueryCondForm" method="GET" action="${ctx}/enter/managescanlist.action">
			<input type="hidden" name="self_type" id="self_type" value="Y"/>
				<table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
					<tr>
						<!-- 
						<td width="18%" align="center">录入状态：
							<select name="import_status">
					   			<option value="">--全部--</option>
					   			<option value="0" <c:if test="${import_status == '0'}">selected</c:if>>未录入</option>
					   			<option value="1" <c:if test="${import_status == '1'}">selected</c:if>>已录入</option> 			
					   		</select> 
					   	</td>
					   	 -->
						<td width="15%" align="center">密级 ：
				 			<c:set var="seclv1" value="${seclv_code}" scope="request"/>
        					<select name="seclv_code">
        						<option value="">--全部--</option>
    							<s:iterator value="#request.seclvList" var="seclv">
									<option value="${seclv.seclv_code}" <s:if test="#request.seclv1==#seclv.seclv_code">selected="selected"</s:if>>${seclv.seclv_name}</option>
								</s:iterator>
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
 			</form>
		</td>
	</tr>
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
					<display:table requestURI="${ctx}/enter/managescanlist.action" uid="item" class="displaytable" name="eventList" pagesize="15" sort="list">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column title="申请人"  property="user_name"/>						
						<display:column title="文件名称" property="zipfile" maxLength="20"/>
						<display:column title="文件编号" property="medium_serial" />				
						<display:column title="密级" property="seclv_name"/>
						<display:column title="用途" property="usage_name"/>	
						<display:column title="载体归属" property="scope_name"/>		
						<display:column title="申请时间" property="apply_time_str" sortable="true"/>										
						<display:column title="操作" style="width:160px">
							<div>
								<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/enter/viewentereventdetail.action?event_code=${item.event_code}');"/>&nbsp;&nbsp;
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
