<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>个人借用科研工作手册台账列表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
		<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
	<link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
	<script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script>
	$(document).ready(function(){
		onHoverInfinite();
		optionSelect();
	});
	function optionSelect(){
		$("#seclv_code").val("${seclv_code}");
	}
	function clearFindForm(){
		LedgerQueryCondForm.paper_barcode.value = "";
		LedgerQueryCondForm.file_title.value = "";
		$("#seclv_code").val("");
	}
	function chkSubmit(){
		var seclv_code = -1;
		var submitable = true;
		var paper_barcodes = "";
		if($(":checkbox:checked[value!='']").size()>0){
			$(":checkbox:checked[value!='']").each(function (){
			    paper_barcodes += this.value+",";
			    $("#paper_barcodes").val(paper_barcodes);
				if(seclv_code == -1 || seclv_code == this.id){					
					seclv_code = this.id;
				}else{
					alert("勾选的申请密级不一致，请确认");
					submitable =  false;
					return false;
				}
			});
			if(submitable){
				if($(":radio:checked").size() == 0){
					alert("请勾选闭环方式");
					$("radio:first").focus();
					return false;
				}else{
					if($(":radio:checked").val() == "DESTROY_PAPER_BYSELF"){
						$("#LedgerQueryCondForm").attr("action","${ctx}/basic/handlepaperjob.action?time_flag=USETIME");
						$("#LedgerQueryCondForm").submit();
					}
				}
				return true;
			}else{
				return false;
			}
		}else{
			alert("请先勾选文件任务");
			return false;
		}
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
	<tr>
		<td class="title_box">个人借用科研工作手册台账列表</td>
	</tr>
	<form id="LedgerQueryCondForm" method="GET" action="${ctx}/borrow/viewselfsecretpaperledger.action">
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
						<input type="button" class="button_2003" value="导出EXCEL" onclick="exportLedger('LedgerQueryCondForm','${ctx}/ledger/exportpaperborrowledger.action','${ctx}/borrow/viewpaperborrowledger.action');"/>
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
	   					<display:table requestURI="${ctx}/borrow/viewpaperborrowledger.action" uid="item" class="displaytable" name="paperLedgerList"
	   							pagesize="${pageSize}" sort="page" partialList="true" size="${totalSize}" form="LedgerQueryCondForm" excludedParams="*">
							<display:column title="选择">
								<span id="checkbox">					
			   					<c:choose>
									<c:when test="${item.paper_state == 6}">
										<input type="checkbox" name="_chk" value="${item.paper_barcode}" id="${item.seclv_code}"/>
									</c:when>
									<c:otherwise>
										${item.paper_state_name}
									</c:otherwise>
								</c:choose>
								</span>
							</display:column>
							<display:column title="序号">
								<c:out value="${item_rowNum}"/>
							</display:column>		
							<display:column property="paper_barcode" title="文件编号" maxLength="30"/>
							<display:column property="file_title" title="文件名" maxLength="30"/>
							<display:column property="page_count" title="页数"/>	
							<display:column property="seclv_name" title="密级"/>
							<display:column property="user_name" title="制作人"/>
							<display:column property="dept_name" title="制作部门" maxLength="15"/>		
							<display:column property="print_time" sortable="true" title="启用时间"/>
							<display:column title="操作">
								<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/ledger/viewpaperledgerinfo.action?time_flag=USETIME&paper_id=${item.paper_id}');"/>&nbsp;
							</display:column>
						</display:table>
					</td>
				</tr>
			</table>
         </td>
	</tr>	
</table>
<table width="95%" align="center" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">	
	<tr height="40">
		<td align="center">
			<!-- <label style="width: 80">
				<input type="radio" name="jobType" value="SEND_PAPER"/>外发
			</label>
			<label style="width: 80">
				<input type="radio" name="jobType" value="FILE_PAPER"/>归档
			</label> -->
			<label style="width: 80">
				<input type="radio" name="jobType" value="DESTROY_PAPER_BYSELF"/>销毁
			</label>
			<!-- <label style="width: 80">
				<input type="radio" name="jobType" value="DELAY_PAPER"/>延期留用
			</label> -->
			<input type="button" value="批量提交" class="button_2003" style="margin-left: 30px" onclick="return chkSubmit();"/>
		</td>
	</tr>
</table>	
</form>
</body>
</html>
