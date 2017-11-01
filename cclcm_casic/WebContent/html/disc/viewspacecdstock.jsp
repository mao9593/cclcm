<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>部门空白盘库存列表</title>
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
/* 		addSelectAllCheckbox(); */
		LedgerQueryCondForm.seclv_code.value = "${seclv_code}";
	});
	
	function clearFindForm(){
		$("#seclv_code").val("");
		LedgerQueryCondForm.barcode.value = "";
		LedgerQueryCondForm.startTime.value = "";
		LedgerQueryCondForm.endTime.value = "";
	}
	/* function addSpaceCdJob(){
		var spacecd_ids = "";
		var check_result=true;
		if($(":checkbox:checked[value!=''][name='spacecd_id']").size() == 0){
			alert("请选择要领用的光盘");
			return false;
		}else{
			$(":checkbox:checked[value!=''][name='spacecd_id']").each(function (){
				spacecd_ids += this.value+":";
			});
			if(check_result){
				$("#ids").val(spacecd_ids);
				$("#hid_form").submit();
			}
		}
	}
	 */
	</script>
</head>
<body oncontextmenu="self.event.returnValue=true">
<form method="post" action="${ctx}/disc/addapplyspacecdjob.action" id="hid_form">
	<input type="hidden" name="jobType" value="BURN_BORROW"/>
	<input type="hidden" name="ids" id="ids"/> 
	<input type="hidden" name="actionContext" value="disc/addspacecdprocessjob.action"/>
</form>
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">部门空白盘库存列表</td>
	</tr>
	<form id="LedgerQueryCondForm" method="GET" action="${ctx}/disc/viewspacecdstock.action">
	<tr>
		<td align="right">
			<table  table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
				
				<tr>
					<td align="center">归属部门：</td>
					<td>
						<input type="text" id="scope_dept_name" name="scope_dept_name" value="${scope_dept_name}"/>
					</td>
					<td align="center">密级：</td>
					<td>
						<select name="seclv_code" id="seclv_code">
							<option value="">--所有--</option>
							<s:iterator value="#request.seclvList" var="seclv">
								<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
							</s:iterator>
						</select>
					</td>
					<td align="center"> 
						<input name="button" type="submit" class="button_2003" value="查询">&nbsp;&nbsp;
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
	   					<display:table requestURI="${ctx}/disc/viewspacecdstock.action" uid="item" class="displaytable" name="items"
	   							pagesize="15" sort="page" form="LedgerQueryCondForm" excludedParams="*">
	   						<%-- <display:column title="选择">
								<input type="checkbox" value="${item.id}" name="spacecd_id"/>
							</display:column>	 --%>
							<display:column title="序号">
								<c:out value="${item_rowNum}"/>
							</display:column>
							<display:column property="scope_dept_name" title="归属部门"/>
							<display:column property="seclv_name" title="密级"/>
							<display:column property="spacecd_state_name" title="状态"/>
							<display:column property="total_num" title="库存数量"/>
							<%-- <display:column title="操作" style="width:100px">
								<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/disc/viewspacecdinfo.action?id=${item.id }');"/>&nbsp;&nbsp;
							</display:column> --%>
						</display:table>
					</td>
				</tr>
			</table>
         </td>
	</tr>
	<tr>
		<td>
			<input type="button" value="申请领用" onclick="go('${ctx}/disc/addapplyspacecdjob.action;')" class="button_2003"/>&nbsp;&nbsp;
		</td>
	</tr>
</table>
</form>
</body>
</html>
