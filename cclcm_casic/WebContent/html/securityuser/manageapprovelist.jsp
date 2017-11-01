<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>审批任务列表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
<script>
<!--
	$(document).ready(function(){
		onHover();
		preCalendar();
		if("${type}" =="all"){
			document.getElementById("allOptions").innerHTML="";
		}
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
			$("#user_iidd").val(user_id);
			$("#user_name").val(user_name);
			document.getElementById("allOptions").innerHTML="";
		}
	}
	function checkTime(){
		if($("input[name='startTime']").val() != "" && $("input[name='endTime']").val() != ""){
			var startTimeInput = $("input[name='startTime']").val();
			var endTimeInput = $("input[name='endTime']").val();
			var startYear = startTimeInput.substring(0,4);
			var endYear = endTimeInput.substring(0,4);
			var startTime = startTimeInput.substr(5,5)+"-"+startYear+startTimeInput.substr(10);
			var endTime = endTimeInput.substr(5,5)+"-"+endYear+endTimeInput.substr(10);
			var startLong = Date.parse(startTime);
			var endLong = Date.parse(endTime);
			if(startLong != NaN && endLong != NaN && startLong > endLong){
				alert("起止时间查询条件设置不合理，请修改");
				$("#researchFlag").val("N");
				return false;
			}
		}
		$("#researchFlag").val("Y");
		return true;
	}
//-->
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">审批任务列表</td>
	</tr>
	<tr>
		<td align="right">
		<form id="QueryCondForm" method="GET" action="${ctx}/securityuser/manageapprovelist.action" name="QueryCondForm">
			<input type="hidden" name="type" value="${type}">
			<input type="hidden" id="researchFlag" name="researchFlag" value="${researchFlag}"/>
			<table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
					<tr>			
						<td width="8%" align="center">发起人</td>
						<td width="17%">
				 			<input type="text" id="user_name" name="user_name" value="${user_name}" onkeyup="selectRecvUser(this.value);"/>
				    		<input type="hidden" id="user_iidd" name="user_iidd" value="${user_iidd}"/><br>
				    		<div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
				    		</div>		
				 		</td>
				 		<td width="8%" align="center">发起部门</td>
						<td width="20%">
							<input type="text" id="dept_name" name="dept_name" value="${dept_name}" readonly="readonly" style="cursor:hand" onclick="openDeptSelect('dept_name','dept_id','radio')" />
							<input type="hidden" name="dept_id" id="dept_id" value="${dept_id}"/>
						</td>
				 		<td width="8%" align="center">流程名称</td>
						<td width="20%">
				 			<c:set var="jobtype1" value="${jobType_code}" scope="request"/>
				 			<select name="jobType_code" id="jobType_code">
								<option value="">--全部--</option>
								<s:iterator value="#request.useredJobType" var="jobtype">
									<option value="${jobtype.jobTypeCode}" <s:if test="#request.jobtype1==#jobtype.jobTypeCode">selected="selected"</s:if>>${jobtype.jobTypeName}</option>
								</s:iterator>
							</select>				 	
				 		</td>
				 		<td width="8%" align="center">申请状态</td>
						<td width="17%">
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
	 					<td align="center">密级</td>
						<td>
						<c:set var="seclv1" value="${seclv_code}" scope="request"/>
						<select name="seclv_code" id="seclv_code">
							<option value="">--所有--</option>
							<s:iterator value="#request.seclvList" var="seclv">
								<option value="${seclv.seclv_code}" <s:if test="#request.seclv1==#seclv.seclv_code">selected="selected"</s:if>>${seclv.seclv_name}</option>
							</s:iterator>
						</select>
						</td>
			    		<td align="center">申请时间</td>
						<td>
				 			<input type="text" name="startTime" readonly="readonly" style="cursor:hand;" value="${startTime_str}"/>
        					<img src="${ctx}/_image/time2.jpg" id="startTime_trigger">
				 		</td>
				 		<td align="center">至</td>
						<td>
				        	<input type="text" name="endTime" readonly="readonly" style="cursor:hand;" value="${endTime_str}"/>
        					<img src="${ctx}/_image/time2.jpg" id="endTime_trigger">
			    		</td>
			    		<td align="center" colspan='2'>
							<input name="button" type="submit" class="button_2003" value="查询" onclick="return checkTime();">&nbsp;&nbsp;
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
					<display:table requestURI="${ctx}/securityuser/manageapprovelist.action" uid="item" class="displaytable" name="jobList"
						 pagesize="15" sort="list" excludedParams="*" form="QueryCondForm">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column title="流程名称" property="jobType.jobTypeName" maxLength='15'/>
						<display:column title="发起部门" property="dept_name" maxLength='15'/>
						<display:column title="发起人" property="user_name"/>
						<display:column title="密级" property="seclv_name"/>
						<display:column title="申请时间" property="start_time_str" sortable="true"/>
						<display:column title="申请状态" property="job_status_name"/>
						<display:column title="操作" style="width:100px">
							<%-- <input type="button" class="button_2003" value="查看" onclick="go('${ctx}/securityuser/viewalljobdetail.action?is_prop=Y&job_code=${item.job_code}&jobTypeCode=${item.jobType.jobTypeCode}');"/> --%>
								<c:if test="${item.jobType.jobTypeCode == 'USERSECLV_ADD'}"> 
									<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/securityuser/approveuseclvchangejob.action?history=Y&job_code=${item.job_code}');"/>
								</c:if>
								<c:if test="${item.jobType.jobTypeCode == 'USERSECLV_CHANGE'}"> 
									<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/securityuser/approveuseclvchangejob.action?history=Y&job_code=${item.job_code}');"/>
								</c:if>
								<c:if test="${item.jobType.jobTypeCode == 'USERSEC_ACTIVITY'}"> 
									<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/secactivity/approveusecactijob.action?history=Y&job_code=${item.job_code}');"/>
								</c:if>
								<c:if test="${item.jobType.jobTypeCode == 'SECUSER_ABROAD'}"> 
									<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/securityuser/approveuabroadjob.action?history=Y&job_code=${item.job_code}');"/>
								</c:if>
								<c:if test="${item.jobType.jobTypeCode == 'SECUSER_ENTRUST'}"> 
									<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/securityuser/approveuserentrustjob.action?history=Y&job_code=${item.job_code}');"/>
								</c:if>
								<c:if test="${item.jobType.jobTypeCode == 'RESIGN'}"> 
									<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/securityuser/approveuresignjob.action?history=Y&job_code=${item.job_code}');"/>
								</c:if>
								<c:if test="${item.jobType.jobTypeCode == 'ENTER_SECPLACE'}"> 
									<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/secplace/approveentersecplacejob.action?history=Y&job_code=${item.job_code}');"/>&nbsp;
								</c:if>
								<c:if test="${item.jobType.jobTypeCode == 'EVENT_SECPLACE'}"> 
									<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/secplace/viewsecplaceeventdetail.action?type=Y&job_code=${item.job_code}');"/>&nbsp;
								</c:if>
								<c:if test="${item.jobType.jobTypeCode == 'OUT_EXCHANGE'}"> 
									<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/secactivity/approvesecoutexchangejob.action?history=Y&job_code=${item.job_code}');"/>
								</c:if>
								<c:if test="${item.jobType.jobTypeCode == 'EVENT_INTCOM'}"> 
									<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/computer/approveinternetcomputerjob.action?history=Y&job_code=${item.job_code}');"/>&nbsp;
								</c:if>
								<c:if test="${item.jobType.jobTypeCode == 'EVENT_SINCOM'}"> 
									<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/computer/approvesinglecomputerjob.action?history=Y&job_code=${item.job_code}');"/>&nbsp;
								</c:if>
								<c:if test="${item.jobType.jobTypeCode == 'EVENT_CHGCOM'}"> 
									<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/computer/approvechangecomputerjob.action?history=Y&job_code=${item.job_code}');"/>&nbsp;
								</c:if>
								<c:if test="${item.jobType.jobTypeCode == 'EVENT_DESCOM'}"> 
									<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/computer/approvedestorycomputerjob.action?history=Y&job_code=${item.job_code}');"/>&nbsp;
								</c:if>
								<c:if test="${item.jobType.jobTypeCode == 'USER_INFO'}">
									<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/securityuser/approveuserinfojob.action?history=Y&job_code=${item.job_code}');"/>
								</c:if>
								<c:if test="${item.jobType.jobTypeCode == 'EVENT_REPORT'or item.jobType.jobTypeCode == 'EVENT_REPORT2'or item.jobType.jobTypeCode == 'EVENT_REPORT3'}"> 
									<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/publicity/approvereportjob.action?history=Y&job_code=${item.job_code}');"/>
								</c:if>
								<c:if test="${item.jobType.jobTypeCode == 'FIELDIN'}"> 
									<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/secmanage/approveresearchfieldinjob.action?history=Y&job_code=${item.job_code}');"/>
								</c:if>
								<c:if test="${item.jobType.jobTypeCode == 'INFO_DEVICE' or item.jobType.jobTypeCode == 'INFO_OTHER' or item.jobType.jobTypeCode == 'DEVICE_CHANGE' or item.jobType.jobTypeCode == 'CHANGE_OTHER' or item.jobType.jobTypeCode == 'DEVICE_DES'}"> 
									<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/computer/approveinfodevicejob.action?history=Y&job_code=${item.job_code}');"/>
								</c:if>
								<c:if test="${item.jobType.jobTypeCode == 'EVENT_REPCOM'}"> 
									<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/infosystem/approvecomputerrepairjob.action?history=Y&job_code=${item.job_code}');"/>&nbsp;
								</c:if>
								<c:if test="${item.jobType.jobTypeCode == 'EVENT_REINSTALL'}"> 
									<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/infosystem/approvereinstallsystemjob.action?history=Y&job_code=${item.job_code}');"/>&nbsp;
								</c:if>
								<c:if test="${item.jobType.jobTypeCode == 'EVENT_QUITINT'}"> 
									<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/infosystem/approvequitinternetjob.action?history=Y&job_code=${item.job_code}');"/>&nbsp;
								</c:if>
								<c:if test="${item.jobType.jobTypeCode == 'EVENT_USBKEY'}"> 
									<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/infosystem/approveusbkeyjob.action?history=Y&job_code=${item.job_code}');"/>&nbsp;
								</c:if>
								<c:if test="${item.jobType.jobTypeCode == 'EVENT_OPENPORT'}"> 
									<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/infosystem/approveopenportjob.action?history=Y&job_code=${item.job_code}');"/>&nbsp;
								</c:if>
								<c:if test="${item.jobType.jobTypeCode == 'EVENT_LOCALPRINTER'}"> 
									<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/infosystem/approvelocalprinterjob.action?history=Y&job_code=${item.job_code}');"/>&nbsp;
								</c:if>		
								<c:if test="${item.jobType.jobTypeCode == 'INTER_EMAIL'}"> 
									<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/secmanage/approveinternetemailjob.action?history=Y&job_code=${item.job_code}');"/>
								</c:if>
								<c:if test="${item.jobType.jobTypeCode == 'SEC_CHECK'}"> 
									<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/secmanage/approveseccheckjob.action?history=Y&job_code=${item.job_code}');"/>
								</c:if>
								<c:if test="${item.jobType.jobTypeCode == 'FILEOUTMAKE'}"> 
									<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/secmanage/approvefileoutmakejob.action?history=Y&job_code=${item.job_code}');"/>
								</c:if>
								<c:if test="${item.jobType.jobTypeCode == 'MATERIAL'}"> 
									<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/secmanage/approveexchangematerialjob.action?history=Y&job_code=${item.job_code}');"/>
								</c:if>
								<c:if test="${item.jobType.jobTypeCode == 'EXHIBITION'}"> 
									<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/secmanage/approveexhibitionjob.action?history=Y&job_code=${item.job_code}');"/>
								</c:if>
								<c:if test="${item.jobType.jobTypeCode == 'PUNISH_DEPT' or item.jobType.jobTypeCode == 'PUNISH_SECCHECK' or item.jobType.jobTypeCode == 'PUNISH_RECTIFY'}"> 
									<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/secmanage/approvepunishjob.action?history=Y&job_code=${item.job_code}');"/>
								</c:if>			
								<c:if test="${item.jobType.jobTypeCode == 'PAPERPATENT'}"> 
									<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/secmanage/approvepaperpatentjob.action?history=Y&job_code=${item.job_code}');"/>
								</c:if>
								<c:if test="${item.jobType.jobTypeCode == 'PAPER_OTHERS'}"> 
									<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/secmanage/approvepaperpatentjob.action?history=Y&job_code=${item.job_code}');"/>
								</c:if>
								<c:if test="${item.jobType.jobTypeCode == 'PAPER_RESEARCH'}"> 
									<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/secmanage/approvepaperpatentjob.action?history=Y&job_code=${item.job_code}');"/>
								</c:if>
								<c:if test="${item.jobType.jobTypeCode == 'BORROW_BOOK' || item.jobType.jobTypeCode == 'BORROW_BOOKOUT'}">  
									<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/computer/approveborrowbookjob.action?history=Y&job_code=${item.job_code}');"/>
								</c:if>
								<c:if test="${item.jobType.jobTypeCode == 'PERSONAL_FILE'}">  
									<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/carriermanage/approvepersonalfilejob.action?history=Y&job_code=${item.job_code}');"/>
								</c:if>
								<!-- 涉密载体 -->
								<c:if test="${item.jobType.jobTypeCode == 'PRINT_FILE' or item.jobType.jobTypeCode == 'PRINT_REMAIN' or item.jobType.jobTypeCode == 'PRINT_SEND'}"> 
									<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/print/viewprintjobdetail.action?job_code=${item.job_code}');"/>
								</c:if>
								<c:if test="${item.jobType.jobTypeCode == 'BURN_FILE' or item.jobType.jobTypeCode == 'BURN_REMAIN' or item.jobType.jobTypeCode == 'BURN_SEND' or item.jobType.jobTypeCode == 'BORROW' or item.jobType.jobTypeCode == 'TRANSFER'}"> 
									<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/basic/viewjobdetail.action?job_code=${item.job_code}&file_src=${file_src}');"/>
								</c:if>
								<c:if test="${item.jobType.jobTypeCode == 'COPY' or item.jobType.jobTypeCode == 'COPY_SEND' or item.jobType.jobTypeCode == 'COPY_FILE' or item.jobType.jobTypeCode == 'OUTCOPY_REMAIN' or item.jobType.jobTypeCode == 'OUTCOPY_SEND' or item.jobType.jobTypeCode == 'OUTCOPY_FILE'}"> 
									<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/copy/viewcopyjobdetail.action?job_code=${item.job_code}');"/>
								</c:if>
								<c:if test="${item.jobType.jobTypeCode == 'LEADIN' or item.jobType.jobTypeCode == 'SCAN_LEADIN'}"> 
									<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/enter/viewenterjobdetail.action?job_code=${item.job_code}');"/>
								</c:if>
								<c:if test="${item.jobType.jobTypeCode == 'DEVICE'}"> 
									<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/device/viewdevicejobdetail.action?job_code=${item.job_code}');"/>
								</c:if>
								<c:if test="${item.jobType.jobTypeCode == 'DELAY' or item.jobType.jobTypeCode == 'DELAY_PAPER' or item.jobType.jobTypeCode == 'SEND' or item.jobType.jobTypeCode == 'FILE' or item.jobType.jobTypeCode == 'DESTROY' or item.jobType.jobTypeCode == 'DELAY_PAPER'  or item.jobType.jobTypeCode == 'SEND_PAPER'  or item.jobType.jobTypeCode == 'FILE_PAPER'  or item.jobType.jobTypeCode == 'DESTROY_PAPER'}"> 
									<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/basic/viewhandlejobdetail.action?type=PAPER&job_code=${item.job_code}');"/>
								</c:if>
								<c:if test="${item.jobType.jobTypeCode == 'DELAY_CD' or item.jobType.jobTypeCode == 'SEND_CD' or item.jobType.jobTypeCode == 'FILE_CD' or item.jobType.jobTypeCode == 'DESTROY_CD' or item.jobType.jobTypeCode == 'DELAY_CD'}"> 
									<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/basic/viewhandlejobdetail.action?type=CD&job_code=${item.job_code}');"/>
								</c:if>
								<c:if test="${item.jobType.jobTypeCode =='DESTROY_DEVICE'}"> 
									<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/basic/viewhandlejobdetail.action?type=DEVICE&job_code=${item.job_code}');"/>
								</c:if>							
								<c:if test="${item.jobType.jobTypeCode =='CHANGE'}"> 
									<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/change/viewchangejobdetail.action?job_code=${item.job_code}');"/>
								</c:if>
								<c:if test="${item.jobType.jobTypeCode =='SENDES_PAPER'}"> 
									<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/ledger/viewsenddestroyjobdetail.action?type=paper&job_code=${item.job_code}');"/>
								</c:if>
								<c:if test="${item.jobType.jobTypeCode =='SENDES_CD'}"> 
									<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/ledger/viewsenddestroyjobdetail.action?type=cd&job_code=${item.job_code}');"/>
								</c:if>
								<c:if test="${item.jobType.jobTypeCode =='MODIFY_SECLV'}"> 
									<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/ledger/viewmodjobdetail.action?job_code=${item.job_code}');"/>
								</c:if>
								<c:if test="${item.jobType.jobTypeCode =='SPACECD_BORROW'}"> 
									<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/basic/viewhandlejobdetail.action?type=SPACECD&job_code=${item.job_code}');"/>
								</c:if>
								<c:if test="${item.jobType.jobTypeCode =='CARRYOUT_PAPER' or item.jobType.jobTypeCode =='CARRYOUT_CD'}"> 
									<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/ledger/viewcarryoutjobdetail.action?job_code=${item.job_code}');"/>
								</c:if>
								<c:if test="${item.jobType.jobTypeCode == 'BOOK_CHANGE' || item.jobType.jobTypeCode == 'BOOK_REPAIR' || item.jobType.jobTypeCode == 'BOOK_DES' || item.jobType.jobTypeCode == 'BOOK_REINSTALL'}">
									<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/computer/approvebookjob.action?job_code=${item.job_code}&history=Y');"/>&nbsp;
								</c:if>
						</display:column>
					</display:table>
					</td>
				</tr>
			</table>
         </td>
	</tr>
	<c:choose>
		<c:when test="${researchFlag eq 'N'}">
			<tr> 
		    	<td align="center"><font style="color:red;font-size:15">请选择需要的查询条件，点击“查询”按钮进行查询！</font></td>	    	
			</tr>
		</c:when>
	</c:choose>
</table>
</form>
</body>
</html>
