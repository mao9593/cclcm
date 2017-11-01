<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>已添加的要害部门部位作业列表</title>
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
function clearFindForm(){
		$("#QueryCondForm :text").val("");
		$("#QueryCondForm select").val("");
	}	
	

//要害部门部位模糊匹配
function selectRecvSecplace(word){
	var url = "${ctx}/secplace/getfuzzysecplace.action";
	if(word != ""){
		callServer3(url,"fuzzy="+word);
	}else{
		document.getElementById("allOptionsSP").innerHTML="";
	}
}

function updateResult1(){
	if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
		if(xmlHttp.responseText.toString().length > 154){	
			document.getElementById("allOptionsSP").innerHTML=xmlHttp.responseText;
		}else{
			document.getElementById("allOptionsSP").innerHTML="";
		}
	}else{
		document.getElementById("allOptionsSP").innerHTML="";
	}
}

function add_TrueSP(){
	var user_id=$("#allOptionSP").val();
	var user_name=$("#allOptionSP option[value='"+user_id+"']").text();
	if(user_id != ""){
		$("#secplace_code").val(user_id);
		$("#secplace_name").val(user_name);
		document.getElementById("allOptionsSP").innerHTML="";
	}
}
//end

function preCalendarDay(){
	Calendar.setup({inputField: "startTime", button: "startTime_trigger", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d %H:%M:%S", cache: true, weekNumbers:true, showOthers: true, step: 1});
	Calendar.setup({inputField: "endTime", button: "endTime_trigger", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d %H:%M:%S", cache: true, weekNumbers:true, showOthers: true, step: 1});
}


</script>
</head>

<body oncontextmenu="self.event.returnValue=false">
<form id="QueryCondForm" name="QueryCondForm" method="POST" action="${ctx}/secplace/viewsecplaceevent.action" >
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">已添加的要害部门部位作业列表</td>
	</tr>	
	<tr>
		<td align="right">
	 		<table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
					<tr>
						<td align="center">要害部门部位名称</td>
				 		<td>
				 			<input type="text" id="secplace_name" name="secplace_name" value="${secplace_name}" />			
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
			    		<td align="center" colspan="2" rowspan="2">
				 			<input name="button" type="submit" class="button_2003" value="查询" onclick="return checkTime();">&nbsp;
							<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">
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
				 		
					</tr>
				</table>
			</td>
		</tr>
		
		<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">	
	 			<tr>
	   				<td>
					<display:table requestURI="${ctx}/secplace/viewsecplaceevent.action" id="item" class="displaytable" name="eventList" 
					pagesize="15" sort="list" excludedParams="*" form="QueryCondForm">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column property="user_name" title="申请人"  maxLength="15"/>
						<display:column property="secplace_name" title="要害部门部位名称"  maxLength="25"/>
						<display:column property="secplace_location" title="物理位置"  maxLength="25"/>
						<display:column property="seclv_name" title="密级"/>
						<display:column property="duty_user_name" title="责任人" maxLength="15"/>
						<display:column property="duty_dept_name" title="责任部门" maxLength="15"/>
						<display:column property="secplace_application" title="用途"  maxLength="15"/>
						<display:column property="apply_time_str" title="申请时间"  maxLength="15"/>
						<display:column property="job_status_name" title="申请状态"  maxLength="15"/>
						<display:column title="操作" style="width:100px">
							<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/secplace/viewsecplaceeventdetail.action?event_code=${item.event_code}');"/>&nbsp;	
							<c:choose>
								<c:when test="${item.job_status_name != '待审批'}">
									<input type="button" class="button_2003" value="撤销" disabled="disabled"/>
								</c:when>
								<c:otherwise>
									<input type="button" class="button_2003" value="撤销" onclick="go('${ctx}/secplace/deleteevent.action?event_code=${item.event_code}');"/>&nbsp;	
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