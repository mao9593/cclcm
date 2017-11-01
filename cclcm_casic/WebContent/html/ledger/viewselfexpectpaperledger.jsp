<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>用户预台帐列表</title>
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
		onHoverInfinite();
		//addSelectAllCheckbox();
		preCalendar();
		optionSelect();
			});
	function clearFindForm(){
		$("#LedgerQueryCondForm :text").val("");
		$("#LedgerQueryCondForm select").val("");
	}	
	function optionSelect(){
		$("#seclv_code").val("${seclv_code}");
		$("#paper_state").val("${paper_state}");
	}

	function exportLedger(formId,url,url1){
			document.getElementById(formId).action = url;
			document.getElementById(formId).submit();
			document.getElementById(formId).action = url1;
	}
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<input type="hidden" value="${paperBarcodeList}" id="paperBarcodeList"/>
	<tr>
		<td class="title_box">用户预台帐列表</td>
	</tr>
	<form id="LedgerQueryCondForm" method="GET" action="${ctx}/ledger/viewselfexpectpaperledger.action">
	<input type="hidden" name="is_book" value="Y"/>
	<input type="hidden" id="paper_barcodes" name="paper_barcodes" value=""/>&nbsp;
	<tr>
		<td align="right">
			<table  table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<td width="8%" align="center">文件名：</td>
					<td width="20%">
						<input type="text" id="file_title" name="file_title" value="${file_title}"/>&nbsp;
					</td>
					<td width="8%" align="center">文件条码：</td>
					<td width="17%">
						<input type="text" id="paper_barcode" name="paper_barcode" value="${paper_barcode}"/>&nbsp;
					</td>
					<td width="8%" align="center">关键字：</td>
					<td width="17%">
						<input type="text" id="keyword_content" name="keyword_content" value="${keyword_content}"/>&nbsp;
					</td>
				</tr>
				<tr>
					<td align="center">密级：</td>
					<td>
						<select name="seclv_code" id="seclv_code">
							<option value="">--所有--</option>
							<s:iterator value="#request.seclvList" var="seclv">
								<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
							</s:iterator>
						</select>
					</td>
					<td align="center">到期状态：</td>
					<td>
			    		<select name="expire_status" id="expire_status">
			    			<option value="">--所有--</option>
			    			<option value="0" <c:if test="${expire_status ==0}">selected</c:if>>未到期</option>
			    			<option value="2" <c:if test="${expire_status ==2}">selected</c:if>>即将到期</option>
			    			<option value="1" <c:if test="${expire_status ==1}">selected</c:if>>已到期</option>
			    		</select>
					</td>
				</tr>
				<tr>
					<td align="center">产生时间：</td>
					<td>
						<input type="text" name="startTime" readonly="readonly" style="cursor:hand;" value="${startTime}"/>
        				<img src="${ctx}/_image/time2.jpg" id="startTime_trigger">
					</td>
					<td align="center">至：</td>
					<td>
						<input type="text" name="endTime" readonly="readonly" style="cursor:hand;" value="${endTime}"/>
        				<img src="${ctx}/_image/time2.jpg" id="endTime_trigger">
					</td>
					<td align="center" colspan="2"> 
						&nbsp;<input name="button" type="submit" class="button_2003" value="查询" onclick="return checkTime();">
						&nbsp;<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">&nbsp;&nbsp;&nbsp;&nbsp;
						<%--<input type="button" class="button_2003" value="导出EXCEL" onclick="exportLedger('LedgerQueryCondForm','${ctx}/ledger/exportpersonalpaperledger.action','${ctx}/ledger/viewselfexpectpaperledger.action');"/> --%>
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
	   					<display:table requestURI="${ctx}/ledger/viewselfexpectpaperledger.action" uid="item" class="displaytable" name="paperLedgerList"
	   							pagesize="${pageSize}" sort="page" partialList="true" size="${totalSize}" form="LedgerQueryCondForm" excludedParams="*">		   							   					
							<display:column title="序号">
						    <c:out value="${item_rowNum}"/>
							</display:column>
							<display:column title="文件名">
								<c:choose>
									<c:when test="${item.create_type_name=='被替换页' }">
										<font color="orange">${item.file_title}</font>
									</c:when>
									<c:otherwise>${item.file_title}</c:otherwise>
								</c:choose>
							</display:column>
							<display:column title="文件条码">
								<c:choose>
									<c:when test="${item.create_type_name=='被替换页' }">
										<font color="orange">${item.paper_barcode}</font>
									</c:when>
									<c:otherwise>${item.paper_barcode}</c:otherwise>
								</c:choose>
							</display:column>
							<display:column property="seclv_name" title="文件密级"/>
							<display:column title="制作方式">
								<c:choose>
									<c:when test="${item.create_type=='REPLACEPRINT' || item.create_type=='PRINT'}">打印</c:when>
									<c:otherwise>${item.create_type_name}</c:otherwise>
								</c:choose>
							</display:column>
							<display:column property="print_result_name" title="打印结果"/>
							<display:column property="paper_state_name" title="状态"/>
							<display:column title="关键字" >
								<font color="red">${item.keyword_content}&nbsp;</font>
							</display:column>
							<display:column property="conf_code" title="保密编号"/>
							<display:column title="到期状态">							
			   					<c:choose>
									<c:when test="${item.expire_status == 1}">
										<font color="red">${item.expire_status_name}</font>
									</c:when>
									<c:when test="${item.expire_status == 2}">
										<font color="orange">${item.expire_status_name}</font>
									</c:when>
									<c:otherwise>
										${item.expire_status_name}
									</c:otherwise>
								</c:choose>
							</display:column>
							<display:column property="print_time" sortable="true" title="制作时间"/>
							<display:column title="操作" style="width:50px">
								<c:choose>
									<c:when test="${item.create_type_name=='被替换页'}">
										<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/ledger/viewcycledetail.action?type=PAPER&printType=replacePage&barcode=${item.paper_barcode}&ledger_type=personal');"/>
									</c:when>
									<c:otherwise>
										<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/ledger/viewcycledetail.action?type=PAPER&barcode=${item.paper_barcode}&ledger_type=personal');"/>
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

