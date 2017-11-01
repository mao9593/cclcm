<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>管理部门借用纸质台账</title>
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
	});
	function clearFindForm(){
		LedgerQueryCondForm.paper_barcode.value = "";
		LedgerQueryCondForm.file_title.value = "";
		$("#seclv_code").val("");
	}	
	function chkBorrow(paper_id){
		if(confirm("确定将此文件借出？")){
			var url = "${ctx}/borrow/confirmpaperborrow.action?type=ajax&paper_id="+escape(paper_id)+"&confirm_type=READ_BR&update=Y";
			xmlHttp.open("GET", url, true);
			xmlHttp.onreadystatechange = updatePage;
			xmlHttp.send(null);
		}
	}
	function chkReturn(paper_id){
		if(confirm("确定归还此文件？")){
			var url = "${ctx}/borrow/confirmpaperborrow.action?type=ajax&paper_id="+escape(paper_id)+"&confirm_type=READ_RT&update=Y";
			xmlHttp.open("GET", url, true);
			xmlHttp.onreadystatechange = updatePage;
			xmlHttp.send(null);
		}
	}
	function updatePage() {
		if (xmlHttp.readyState == 4) {
			  var response = xmlHttp.responseText;
			  LedgerQueryCondForm.submit();
		}
	}
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">管理部门借用纸质台账</td>
	</tr>
	<form id="LedgerQueryCondForm" method="GET" action="${ctx}/borrow/confirmpaperborrow.action" name="LedgerQueryCondForm">
	<tr>
		<td align="right">
			<table  table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<td width="10%" align="center">文件编号：</td>
					<td width="18%">
						<input type="text" id="paper_barcode" name="paper_barcode" value="${paper_barcode}"/>&nbsp;
					</td>
					<td width="10%" align="center">文件名：</td>
					<td width="18%">
						<input type="text" id="file_title" name="file_title" value="${file_title}"/>&nbsp;
					</td>
					<td width="10%" align="center">密级：</td>
					<td width="18%">
						<select name="seclv_code" id="seclv_code">
							<option value="">--所有--</option>
							<s:iterator value="#request.seclvList" var="seclv">
								<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
							</s:iterator>
						</select>
					</td>
					<td align="center"> 
						<input name="button" type="submit" class="button_2003" value="查询">&nbsp;
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
	   					<display:table requestURI="${ctx}/borrow/confirmpaperborrow.action" uid="item" class="displaytable" name="paperLedgerList"
	   							pagesize="${pageSize}" sort="page" partialList="true" size="${totalSize}" form="LedgerQueryCondForm" excludedParams="*">
							<display:column title="序号">
								<c:out value="${item_rowNum}"/>
							</display:column>		
							<display:column property="applyusername" title="借用人"/>
							<display:column property="applydeptname" title="借用部门" maxLength="15"/>					
							<display:column property="paper_barcode" title="文件编号" maxLength="30"/>
							<display:column property="file_title" title="文件名" maxLength="30"/>
							<display:column property="seclv_name" title="密级"/>
							<display:column property="print_time" sortable="true" title="产生时间"/>
							<display:column title="操作" style="width:200px">
								<input type="button" class="button_2003" value=" 查看 " onclick="go('${ctx}/ledger/viewpaperledgerinfo.action?paper_id=${item.paper_id}');"/>&nbsp;
								<c:choose>
									<c:when test="${item.borrow_status eq '0' and item.paper_state eq '6'}">
										<input type="button" class="button_2003" value=" 借出 " onclick="chkBorrow('${item.paper_id}');"/>&nbsp;
										<input type="button" class="button_2003" value=" 归还 " disabled="disabled"/>
									</c:when>
									<c:when test="${item.borrow_status eq '0' and item.paper_state eq '10'}">
										<input type="button" class="button_2003" value="待交接" disabled="disabled"/>&nbsp;
										<input type="button" class="button_2003" value=" 归还 " disabled="disabled"/>
									</c:when>
									<c:when test="${item.borrow_status eq '1' and item.paper_state eq '6'}">
										<input type="button" class="button_2003" value="已借出" disabled="disabled"/>&nbsp;
										<input type="button" class="button_2003" value=" 归还 " onclick="chkReturn('${item.paper_id}');"/>
									</c:when>
									<c:otherwise>
										<input type="button" class="button_2003" value="已借出" disabled="disabled"/>&nbsp;
										<input type="button" class="button_2003" value="待交接" disabled="disabled"/>
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
