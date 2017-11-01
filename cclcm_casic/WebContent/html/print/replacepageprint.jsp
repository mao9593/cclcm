<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
  <head>
  	<base target="_self">
  	<title>主台账</title>
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
		preCalendar();
		optionSelect();
	});
	function clearFindForm(){
		$("#LedgerQueryCondForm :text").val("");
		$("#LedgerQueryCondForm select").val("");
	}	
	function optionSelect(){
		$("#seclv_code").val("${seclv_code}");
	}
	function chkSubmit(){
		var seclv_code = -1;
		if($(":radio:checked").size()>0){
			$(":radio:checked").each(function(){
				if(seclv_code == -1 || seclv_code == this.id){	
					seclv_code = this.id;
				}
				var result = new Object();
				result.paper_barcode = this.value;
				result.page_count = this.name;
				window.returnValue=result;	
				window.close();
			});
		}else{
			alert("请先勾选需要替换的文件");
		}
	}	
	function onCancel(){
		window.close();
	}
	</script>
  </head>
  <body oncontextmenu="self.event.returnValue=false" scroll = "yes">
   <form id="LedgerQueryCondForm" method="GET" action="${ctx}/print/replacepageprint.action">
   <table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">用户纸质台账列表</td>
	</tr>
	<tr>
		<td align="right">
			<input type="hidden" name="updateFlag" value="Y"/>
			<input type="hidden" name="user_iidd" value="${user_iidd }"/>
			<table  table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<td width="8%" align="center">文件名：</td>
					<td width="20%">
						<input type="text" id="file_title" name="file_title" value="${file_title}"/>
					</td>
					<td width="8%" align="center">文件条码：</td>
					<td width="17%">
						<input type="text" id="paper_barcode" name="paper_barcode" value="${paper_barcode}"/>
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
					<td align="center" colspan="2"> 
						&nbsp;<input name="button" type="submit" class="button_2003" value="查询" onclick="return checkTime();">
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
	   					<display:table requestURI="${ctx}/print/replacepageprint.action" uid="item" class="displaytable" name="paperLedgerList"
	   							pagesize="${pageSize}" sort="page" partialList="true" size="${totalSize}" form="LedgerQueryCondForm" excludedParams="*">		   							   					
		   					<display:column title="选择">							
								<label style="width: 80">
									<input type="radio" name="${item.page_count}" value="${item.paper_barcode}" id="${item.seclv_code}"/>
								</label>
							</display:column>
							<display:column title="序号">
								<c:out value="${item_rowNum}"/>
							</display:column>
							<display:column property="file_title" title="文件名" maxLength="30"/>
							<display:column property="paper_barcode" title="文件条码"/>
							<display:column property="seclv_name" title="文件密级"/>
							<display:column property="create_type_name" title="制作方式"/>
							<display:column property="print_result_name" title="打印结果"/>
							<display:column property="paper_state_name" title="状态"/>
							<display:column title="关键字" >
								<font color="red">${item.keyword_content}&nbsp;</font>
							</display:column>
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
							<display:column property="page_count" title="页数"/>
							<display:column property="page_size" title="纸张类型"/>
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
			<input type="button" value="确认" class="button_2003" style="margin-left: 30px" onclick="return chkSubmit();"/>
			<input type="button" class="button_2003" value="取消" onclick="onCancel();" />
		</td>
	</tr>
  </table>
 </form>
</body>
</html>
