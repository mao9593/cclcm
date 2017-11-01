<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>监销作业管理</title>
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
		onHoverInfinite();
		preCalendar();
	});
	function clearFindForm(){
		$("#QueryCondForm :text").val("");
		$("#QueryCondForm select").val("");
	}
    </script>
</head>
<body oncontextmenu="self.event.returnValue=true">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">待销毁作业列表</td>
	</tr>
	<tr>
		<td align="right">
			<form id="QueryCondForm" method="GET" action="${ctx}/ledger/viewsupervisedestroycd.action" name="QueryCondForm">
				<table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
					<tr>
						<td align="center">条码号：
							<input type="text" id="barcode" name="barcode" value="${barcode}" size="15"/>&nbsp;
						</td>
				 		<td align="center">密级 ：
				    		<c:set var="seclv1" value="${seclv_code}" scope="request"/>
				        	<select name="seclv_code">
				        		<option value="">--全部--</option>
				    			<s:iterator value="#request.seclvList" var="seclv">
									<option value="${seclv.seclv_code}" <s:if test="#request.seclv1==#seclv.seclv_code">selected="selected"</s:if>>${seclv.seclv_name}</option>
								</s:iterator>
				    		</select>
			    		</td>				 		
				 		<td align="center">提交时间：
				 			<input type="text" name="startTime" readonly="readonly" style="cursor:hand;" value="${startTime_str}"/>
				        	<img src="${ctx}/_image/time2.jpg" id="startTime_trigger">&nbsp;
						</td>
				 		<td align="center">至：
							<input type="text" name="endTime" readonly="readonly" style="cursor:hand;" value="${endTime_str}"/>
				        	<img src="${ctx}/_image/time2.jpg" id="endTime_trigger">&nbsp;
				        </td>
				        <td align="center">
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
						<display:table requestURI="${ctx}/ledger/viewsupervisedestroycd.action" id="item" class="displaytable" name="cdLedgerList"
	   					pagesize="${pageSize}" sort="page" partialList="true" size="${totalSize}" excludedParams="*" form="QueryCondForm">
							<display:column title="序号">
								<c:out value="${item_rowNum}"/>
							</display:column>
							<display:column property="duty_user_name" title="责任人"/>
							<display:column property="duty_dept_name" title="责任人部门"/>
							<display:column property="file_list" title="文件列表" maxLength="40"/>
							<display:column property="cd_barcode" title="光盘编号"/>
							<display:column property="seclv_name" title="介质密级"/>
							<display:column property="user_name" title="制作人"/>
							<display:column property="dept_name" title="制作人部门"/>						
							<display:column title="操作" style="width:150px">
							<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/ledger/viewcycledetail.action?type=DISC&barcode=${item.cd_barcode}');"/>						
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
