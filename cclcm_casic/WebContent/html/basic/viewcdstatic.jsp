<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>查看光盘统计</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
	<link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
	<script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script>
	$(document).ready(function(){
			onHover();
			preCalendarDay();
			optionSelect();
		});
	function preCalendarDay(){
		Calendar.setup({inputField: "start_time", button: "start_time_trigger", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d %H:%M:%S", cache: true, weekNumbers:true, showOthers: true, step: 1});
		Calendar.setup({inputField: "end_time", button: "end_time_trigger", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d %H:%M:%S", cache: true, weekNumbers:true, showOthers: true, step: 1});
	}	
	function clearFindForm(){
			LedgerQueryCondForm.dept_name.value = "";
			LedgerQueryCondForm.dept_id.value = "";			
			$("#seclv_med").val("");
			LedgerQueryCondForm.start_time.value = "";
			LedgerQueryCondForm.end_time.value = "";
	}	
	function optionSelect(){
			$("#seclv_med").val(${seclv_code});
	}
	function chk()
	{	
		if($("#dept_name").val() == ""){
			alert("请选择所查询的部门");
			$("#dept_name").focus();
			return false;
		}
		if(!checkDateTime()){
			return false;
		}
	    return true;
	}
	</script>
</head>

<body oncontextmenu="self.event.returnValue=false">
<form id="LedgerQueryCondForm" method="POST" action="${ctx}/basic/viewcdstatic.action">
	<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">光盘统计</td>
	</tr>
	<tr>
		<td>
			<table  table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<td align="center">
						部门：				
						<input type="text" width="100%"  id="dept_name" name="dept_name" readonly="readonly" style="cursor:hand;" onclick="openDeptSelect('dept_name','dept_id','radio')" value="${dept_name}" /input>&nbsp;&nbsp;
		      			<input type="hidden" name="dept_id" id="dept_id" value="${dept_id}"/>	
		      		<td align="center">			
						密级：
						<select name="seclv_code" id="seclv_med">
							<option value="">--所有--</option>
							<s:iterator value="#request.seclvList" var="seclv">
								<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
							</s:iterator>
						</select>
					<td align="center">
						开始时间：
						<input type="text" name="start_time" readonly="readonly" style="cursor:hand;" value="${start_time}"/>
	        			<img src="${ctx}/_image/time2.jpg" id="start_time_trigger">
					<td align="center">
						结束时间：
						<input type="text" name="end_time" readonly="readonly" style="cursor:hand;" value="${end_time}"/>
	        			<img src="${ctx}/_image/time2.jpg" id="end_time_trigger">
					</td>
					<td colspan="2" align="center">
						<input name="button" type="button" class="button_2003" value="查询" onclick="if(chk()) forms[0].submit();" >
						<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">
						<input type="hidden" name="query" value="Y"/>
					</td>
				</tr>
			</table>
		</td>
    </tr>
	</tr>
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
						<display:table requestURI="${ctx}/basic/viewcdstatic.action" id="item" class="displaytable" name="cdStaticList" pagesize="15" sort="list" >	   					
							<display:column title="序号">
								<c:out value="${item_rowNum}"/>
							</display:column>													
							<display:column property="dept_name" title="部门名称"/>						
							<display:column property="seclv_name" title="密级"/>						
							<display:column property="disc_count" title="光盘数量"/>							
					</display:table>
					</td>
				</tr>
				<tr>
					<td align="right">总数合计：${disc_sum}个&nbsp;&nbsp;&nbsp;&nbsp;</td>
				</tr>
			</table>
         </td>
	</tr>
	</table>
</form>
</body>
</html>
