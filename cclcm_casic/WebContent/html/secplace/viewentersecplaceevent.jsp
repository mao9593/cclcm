<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ page import="hdsec.web.project.secplace.model.EntityVisitor"%>
<html>
<head>
	<title>已添加的涉密人员进出要害部门部位作业列表</title>
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
//要害部门部位匹配
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
//end要害部门部位匹配
//涉密人员匹配
function selectRecvUser(word){

		var url = "${ctx}/basic/getfuzzyuser.action";
		if(word != ""){
			callServer1(url,"fuzzy="+word);
		}else{
			document.getElementById("allOptions").innerHTML="";
		}
}
	
function updateResult(){
	if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
		if(xmlHttp.responseText.toString().length > 154){
				document.getElementById("allOptions").innerHTML=xmlHttp.responseText;
		}else{
			document.getElementById("allOptions").innerHTML="";
		}
	}else{
		document.getElementById("allOptions").innerHTML="";
	}
}

function add_True(){
	var user_id=$("#allOption").val();
	var user_name=$("#allOption option[value='"+user_id+"']").text();
	if(user_id != ""){
		$("#accompany_id").val(user_id);
		$("#accompany_name").val(user_name);
		document.getElementById("allOptions").innerHTML="";
	}
}
//end涉密人员匹配

function preCalendarDay(){
		Calendar.setup({inputField: "enter_time", button: "enter_time_trigger", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d %H:%M:%S", cache: true, weekNumbers:true, showOthers: true, step: 1});
}
function clearFindForm(){
		$("#QueryCondForm :text").val("");
		$("#QueryCondForm select").val("");
	}	

</script>
</head>
<body oncontextmenu="self.event.returnValue=false"> 
<form id="QueryCondForm" name="QueryCondForm" method="POST" action="${ctx}/secplace/viewentersecplaceevent.action" >
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">已添加的涉密人员进出要害部门部位列表</td>
	</tr>	
	<tr>
		<td align="right">
	 		<table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
					<tr>
						<td align="center">要害部门部位名称</td>
				 		<td>
							<input type="text" id="secplace_name" name="secplace_name" value="${secplace_name}" onkeyup="selectRecvSecplace(this.value);"/>
				    		<input type="hidden" id="secplace_code" name="secplace_code"/><br>
				    		<div id="allOptionsSP" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
				 		</td>
				 		<td align="center">陪同人员</td>
				 		<td >
				 			<input type="text" id="accompany_name" name="accompany_name" value="${user_name}" onkeyup="selectRecvUser(this.value);"/>
    						<input type="hidden" id="accompany_id" name="accompany_id"/><br>
    						<div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
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
			    		<td align="center">拟进入时间</td>
				 		<td>
				        	<input type="text" name="enter_time" readonly="readonly" style="cursor:hand;" value="${enter_time}"/>
       	 					<img src="${ctx}/_image/time2.jpg" id="enter_time_trigger">
			    		</td> 
			    		<td align="center">密级</td>
				 		<td>
				 			<select name="seclv_code" id="seclv_code">
								<option value="">--所有--</option>
								<s:iterator value="#request.seclvList" var="seclv">
									<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
								</s:iterator>
							</select>
				 		</td>
			    		<td align="center" colspan="2">
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
					<display:table requestURI="${ctx}/secplace/viewentersecplaceevent.action" id="item" class="displaytable" name="eventList" 
					pagesize="15" sort="list" excludedParams="*" form="QueryCondForm">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column property="user_name" title="申请人" />
						<display:column property="dept_name" title="申请部门"  maxLength="15"/>
						<display:column property="secplace_name" title="要害部门部位名称" maxLength="15"/>
						<display:column property="accompany_name" title="陪同人员"  maxLength="15"/>
						<display:column property="seclv_name" title="作业密级" />
						<display:column property="visit_reason" title="事由" maxLength="15"/>
						<display:column property="enter_time" title="拟进入时间" maxLength="15"/>
						<display:column property="job_status_name" title="申请状态" maxLength="15"/>
						<display:column title="操作" style="width:100px">
							<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/secplace/approveentersecplacejob.action?history=Y&job_code=${item.job_code}');"/>&nbsp;	
							<c:choose>
								<c:when test="${item.job_status_name != '待审批'}">
									<input type="button" class="button_2003" value="撤销" disabled="disabled"/>
								</c:when>
								<c:otherwise>		
									<input type="button" class="button_2003" value="撤销" onclick="go('${ctx}/secplace/deleteevent.action?event_code=${item.event_code}&type=ENTER');"/>&nbsp;	
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