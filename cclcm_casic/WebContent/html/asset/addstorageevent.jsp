<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>待入账采购资产列表</title>
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
	});
	function clearFindForm(){
		$("#QueryCondForm :text").val("");
		$("#QueryCondForm select").val("");
	}
	function viewEventDetail(event_code){
		go("${ctx}/asset/viewpurchaseventdetail.action?event_code="+escape(event_code));
	}
	function chkStorage(event_code){
		go("${ctx}/asset/addstoragejob.action?event_code_purchase="+escape(event_code));			
	}
	function selectRecvUser(word){
		var url = "${ctx}/basic/getfuzzyuser.action";
		if(word != ""){
			callServer1(url,"fuzzy="+word);
		}else{
			document.getElementById("allOptions").innerHTML="";
		}
	}
	function add_True(){
		var user_id=$("#allOption").val();
		var user_name=$("#allOption option[value='"+user_id+"']").text();
		user_name=user_name.substring(0,user_name.indexOf("/"));
		if(user_id != ""){
	    	$("#user_iidd").val(user_id);
			$("#user_name").val(user_name);
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
//-->
</script>
</head>

<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">待入账采购资产列表</td>
	</tr>
	<tr>
		<td align="right">
		<form id="QueryCondForm" method="GET" action="${ctx}/asset/addstorageevent.action" name="QueryCondForm">
			<table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
					<tr>
				 		<td align="center">申请人：</td>
				 		<td >
							<input type="text" id="user_name" name="user_name" value="${user_name}" onkeyup="selectRecvUser(this.value);"/>
    						<input type="hidden" id="user_iidd" name="user_iidd"/><br>
    						<div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
    						</div>
			    		</td>
			    		<td align="center">申请时间：</td>
				 		<td >
				 			<input type="text" name="startTime" readonly="readonly" style="cursor:hand;" value="${startTime_str}"/>
        					<img src="${ctx}/_image/time2.jpg" id="startTime_trigger">
				 		</td>
				 		<td align="center">至：</td>
				 		<td >
				        	<input type="text" name="endTime" readonly="readonly" style="cursor:hand;" value="${endTime_str}"/>
        					<img src="${ctx}/_image/time2.jpg" id="endTime_trigger">
			    		</td>  
				        <td align="center" colspan="6">
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
					<display:table requestURI="${ctx}/asset/addstorageevent.action" uid="item" class="displaytable" name="eventList" 
					pagesize="15" sort="list"  excludedParams="*" form="QueryCondForm">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>					
						<display:column title="申请人" property="user_name"/>
						<display:column title="部门" property="dept_name"/>
						<display:column title="采购资产种类" property="property_kind" maxLength="25"/>
						<display:column title="设备名称" property="property_name" maxLength="25"/>
						<display:column title="数量" property="amount"/>	
						<display:column title="申请时间" property="apply_time_str" sortable="true"/>		
						<display:column title="操作" style="width:150px">
							<div>
								<input type="button" class="button_2003" value="查看" onclick="viewEventDetail('${item.event_code}');"/>
								&nbsp;&nbsp;<input type="button" class="button_2003" value="申请入账" onclick="chkStorage('${item.event_code}');"/>
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
