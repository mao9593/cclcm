<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>电子文件导入任务列表</title>
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
		/* onHover(); */
		$("#seclv_code").val("${seclv_code}");
		$("#cycle_type").val("${cycle_type}");
		$("#duty_dept_id").val("${duty_dept_id}");
		onHoverInfinite();
		preCalendar();
	 
		document.getElementById("allOptions").innerHTML=""; 
	});
	function clearFindForm(){
		$("#QueryCondForm :text").val("");
		$("#QueryCondForm select").val("");
	}
	
	function selectRecvUser(word){
	    var url = "${ctx}/basic/getfuzzyuser.action";
	    if(word != ""){
		   callServer1(url,"fuzzy="+word);
	    }else{
		   document.getElementById("allOptions").innerHTML="";
	    }
	} 
	
	function checkTime(){
	if($("input[name='start_time']").val() != "" && $("input[name='end_time']").val() != ""){
		var startTimeInput = $("input[name='start_time']").val();
		var endTimeInput = $("input[name='end_time']").val();
		var startYear = startTimeInput.substring(0,4);
		var endYear = endTimeInput.substring(0,4);
		var startTime = startTimeInput.substr(5,5)+"-"+startYear+startTimeInput.substr(10);
		var endTime = endTimeInput.substr(5,5)+"-"+endYear+endTimeInput.substr(10);
		var startLong = Date.parse(startTime);
		var endLong = Date.parse(endTime);
		if(startLong != NaN && endLong != NaN && startLong > endLong){
			alert("起止时间查询条件设置不合理，请修改");
			//$("#researchFlag").val("N");
			return false;
		}
	}	
	return true;
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
		var duty_user_iidd=$("#allOption").val();
		var duty_user_name=$("#allOption option[value='"+duty_user_iidd+"']").text();
		duty_user_name=duty_user_name.substring(0,duty_user_name.indexOf("/"));
		if(duty_user_iidd != ""){
			$("#duty_user_iidd").val(duty_user_iidd);
			$("#duty_user_name").val(duty_user_name);
			document.getElementById("allOptions").innerHTML="";
		}
	}
	/* function updatePage() {
		if (xmlHttp.readyState == 4) {
			  var response = xmlHttp.responseText;
			  QueryCondForm.submit();
		}
	} */
	function exportLedger(formId,url,url1){
		document.getElementById(formId).action = url;
		document.getElementById(formId).submit();
		document.getElementById(formId).action = url1;
	}
//-->
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">电子文件导入列表</td>
	</tr>
	<tr>
		<td align="right">
			<form id="QueryCondForm" method="post" action="${ctx}/ledger/viewdeptefileinputledger.action" name="QueryCondForm">
				<input type="hidden" id="dept_id" name="dept_id" value="${dept_ids}"/>
				<table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
					<tr>
						<td align="center">责任人: </td>
						<td>	
    		       			<input type="text" id="duty_user_name" name="duty_user_name" value="${duty_user_name}" onkeyup="selectRecvUser(this.value);"/><br>
    		       			<div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px"></div>
						</td>
						
						<td align="center">文件列表：</td>
						<td>
							<input type="text" id="file_title" name="file_title" value="${file_title}" />
						</td>
						<td align="center">用途：</td>
						<td>
							<input type="text" id="usage_code" name="usage_code" value="${usage_code}"/>&nbsp;
						</td>
							
					</tr>
					<tr>
						
				 		<td align="center">责任部门：</td>
					<td>
						<select id="duty_dept_id" name="duty_dept_id">
							<option value="">--不限--</option>
							<s:iterator value="secAdminDeptList" var="dept">
								<option value="${dept.dept_id}">${dept.dept_name}</option>	
							</s:iterator>
						</select>
					</td>	
				 		<td align="center">导入时间：</td>
				 		<td>
				        	<input type="text" name="startTime" readonly="readonly" style="cursor:hand;" value="${startTime_str}"/>
        					<img src="${ctx}/_image/time2.jpg" id="startTime_trigger">&nbsp;
			    		</td> 
			    		<td align="center">至：</td>
			    		<td>
				        	<input type="text" name="endTime" readonly="readonly" style="cursor:hand;" value="${endTime_str}"/>
        					<img src="${ctx}/_image/time2.jpg" id="endTime_trigger">&nbsp;
			    		</td> 
			    	</tr>
			    	<tr>
			    		<td align="center">申请状态：</td>
				 		<td>
				 			<select name="job_status">
    							<option value="">--全部--</option>
    							<option value="none" <c:if test="${job_status eq 'none'}">selected</c:if>>待审批</option>
    							<option value="under" <c:if test="${job_status eq 'under'}">selected</c:if>>审批中</option>
    							<option value="false" <c:if test="${job_status eq 'false'}">selected</c:if>>已驳回</option>
    							<option value="true" <c:if test="${job_status eq 'true'}">selected</c:if>>已通过</option>
    							<%-- <option value="done" <c:if test="${job_status eq 'done'}">selected</c:if>>已关闭</option> --%>
    						</select>
				 		</td>
				 		<td align="center">密级 ：</td>
						<td>
				 			<select name="seclv_code" id="seclv_code">
        					<option value="">--全部--</option>
    						<s:iterator value="#request.seclvList" var="seclv">
								<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
							</s:iterator>
    						</select>
				 		</td>
				        <td colspan=2 align="center">
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
					<display:table requestURI="${ctx}/ledger/viewdeptefileinputledger.action" uid="item" class="displaytable" name="eventList" 
					pagesize="15" sort="list" excludedParams="*" form="QueryCondForm">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column property="user_name" title="责任人"/>
						<display:column property="dept_name" title="责任人部门"/>
						<display:column title="文件列表" property="file_list" maxLength="25"/>
						<display:column title="作业审批密级" property="seclv_name"/>
						<display:column title="导入时间" property="start_time_str"   sortable="true"/>
						<display:column title="申请状态" property="job_status_name"/>
						<display:column title="用途" property="usage_code"/>
						<display:column title="介质类型" property="med_type"/>
						<display:column title="操作" style="width:50px">
						<div>
							<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/input/viewinputjobdetail.action?event_code=${item.event_code}');"/>&nbsp;&nbsp;
						</div>
						</display:column>
					</display:table>
					</td>
				</tr>
			</table>
         </td>
	</tr>
	<tr><td><input type="button" class="button_2003" value="导出EXCEL" onclick="exportLedger('QueryCondForm','${ctx}/ledger/exportdeptefileinputledger.action','${ctx}/ledger/viewdeptefileinputledger.action');"/></td></tr>
	
</table>
</form>
</body>
</html>
