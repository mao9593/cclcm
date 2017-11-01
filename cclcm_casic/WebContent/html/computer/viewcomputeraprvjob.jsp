<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>查看计算机申请已审批列表</title>
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
	});
	function clearFindForm(){
		
	   $("#QueryCondForm :text").val("");
		$("#QueryCondForm select").val("");/* $("#user_name").val("");
		$("#seclv_code").val(""); */
	}
	
	//人员匹配
	function selectRecvUser(word){
		var url = "${ctx}/basic/getfuzzyuser.action?source=CR";
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
	function add_TrueCR(){
		var user_id=$("#allOptionCR").val();
		var user_name=$("#allOptionCR option[value='"+user_id+"']").text();
		if(user_id != ""){
			$("#user_id").val(user_id);
			$("#user_name").val(user_name);
			document.getElementById("allOptions").innerHTML="";
		}
	}
	//end
</script>   
</head>

<body oncontextmenu="self.event.returnValue=false">
<form id="QueryCondForm"  name="QueryCondForm" method="GET" action="${ctx}/computer/viewcomputeraprvjob.action" >
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">已审批列表</td>
	</tr>
		
	<tr>
		<td align="right">
		
		 	<table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
		 	
		 	<tr>
				<td width="8%" align="center">申请人</td>
				<td width="20%">
				   <input type="text" id="user_name" value="${user_name}" name="user_name" /> 
			     <%-- <input type="text" id="user_name" name="user_name" onkeyup="selectRecvUser(this.value);"/>--%>
    		      <input type="hidden" id="user_id" name="user_id"/><br>
    		       <div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
				</td>
				<td align="center">作业密级</td>
				<td >
					<select name="seclv_code" value="${seclv_code}" id="seclv_code">
						<option value="">--所有--</option>
						<s:iterator value="#request.seclvList" var="seclv">
							<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
						</s:iterator>
					</select>
				</td>
				<td align="center" colspan="2" >
					<input name="button" type="submit" class="button_2003" value="查询" onclick="return">&nbsp;
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
						<display:table requestURI="${ctx}/computer/viewcomputeraprvjob.action" id="item" class="displaytable" name="applyList" 
							pagesize="15" sort="list" excludedParams="*" form="QueryCondForm">
							<display:column title="序号">
								<c:out value="${item_rowNum}"/>
							</display:column>
							<display:column title="申请人" property="user_name"/>
								<display:column title="部门" property="dept_name"/>
								<display:column title="任务类型" property="jobType.jobTypeName"/>
						<%-- <display:column title="申请类型" property="event_names" maxLength="40"/> --%>
								<display:column title="密级" property="seclv_name"/>
								<display:column title="申请时间" property="start_time_str" sortable="true"/>
								<display:column title="申请状态" property="job_status_name"/>
							    <display:column title="操作" style="width:100px">
									<%-- <input type="button" class="button_2003" value="详细" onclick="go('${ctx}/computer/approvecomputerjob.action?history=Y&job_code=${item.job_code}');"/> --%>
								<c:if test="${item.jobType.jobTypeCode == 'BORROW_BOOK' || item.jobType.jobTypeCode == 'BORROW_BOOKOUT'}">
									<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/computer/approveborrowbookjob.action?job_code=${item.job_code}&history=Y');"/>
								</c:if>
								<c:if test="${item.jobType.jobTypeCode == 'EVENT_REPCOM'}">
									<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/infosystem/approvecomputerrepairjob.action?job_code=${item.job_code}&history=Y');"/>&nbsp;
								</c:if>
								<c:if test="${item.jobType.jobTypeCode == 'EVENT_REINSTALL'}">
									<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/infosystem/approvereinstallsystemjob.action?job_code=${item.job_code}&history=Y');"/>&nbsp;
								</c:if>
								<c:if test="${item.jobType.jobTypeCode == 'EVENT_QUITINT'}">
									<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/infosystem/approvequitinternetjob.action?job_code=${item.job_code}&history=Y');"/>&nbsp;
								</c:if>
								<c:if test="${item.jobType.jobTypeCode == 'EVENT_USBKEY'}">
									<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/infosystem/approveusbkeyjob.action?job_code=${item.job_code}&history=Y');"/>&nbsp;
								</c:if>
								<c:if test="${item.jobType.jobTypeCode == 'EVENT_OPENPORT'}">
									<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/infosystem/approveopenportjob.action?job_code=${item.job_code}&history=Y');"/>&nbsp;
								</c:if>
								<c:if test="${item.jobType.jobTypeCode == 'EVENT_LOCALPRINTER'}">
									<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/infosystem/approvelocalprinterjob.action?job_code=${item.job_code}&history=Y');"/>&nbsp;
								</c:if>
								<c:if test="${item.jobType.jobTypeCode == 'EVENT_INTCOM'}">
									<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/computer/approveinternetcomputerjob.action?job_code=${item.job_code}&history=Y');"/>&nbsp;
								</c:if>
								<c:if test="${item.jobType.jobTypeCode == 'EVENT_SINCOM'}">
									<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/computer/approvesinglecomputerjob.action?job_code=${item.job_code}&history=Y');"/>&nbsp;
								</c:if>
								<c:if test="${item.jobType.jobTypeCode == 'EVENT_CHGCOM'}">
									<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/computer/approvechangecomputerjob.action?job_code=${item.job_code}&history=Y');"/>&nbsp;
								</c:if>
								<c:if test="${item.jobType.jobTypeCode == 'EVENT_DESCOM'}">
									<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/computer/approvedestorycomputerjob.action?job_code=${item.job_code}&history=Y');"/>&nbsp;
								</c:if>
								<c:if test="${item.jobType.jobTypeCode == 'BOOK_CHANGE' || item.jobType.jobTypeCode == 'BOOK_REPAIR' || item.jobType.jobTypeCode == 'BOOK_DES' || item.jobType.jobTypeCode == 'BOOK_REINSTALL'}">
									<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/computer/approvebookjob.action?job_code=${item.job_code}&history=Y');"/>&nbsp;
								</c:if>											
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
