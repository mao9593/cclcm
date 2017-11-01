<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>联系方式</title>
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
		$("#ContactQueryCondForm :text").val("");
		$("#ContactQueryCondForm select").val("");
	}	
	function optionSelect(){
		$("#seclv_code").val("${seclv_code}");
		$("#paper_state").val("${paper_state}");
	}
	function deletecompanycontact(obj){
		    var url="${ctx}/securityuser/deletecompanycontanct.action?id="+obj;
		    if(confirm("确定删除联系人吗？")){
		    	xmlHttp.open("GET", url, true);
				xmlHttp.onreadystatechange = updatePage;
				xmlHttp.send(null);
		    }
		}
		function updatePage() {
			if (xmlHttp.readyState == 4) {
				  var response = xmlHttp.responseText;
				  ContactQueryCondForm.submit();
			}
		}
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">联系方式</td>
	</tr>
	<form id="ContactQueryCondForm" method="GET" action="${ctx}/securityuser/clientcontanct.action">
	<tr>
		<td align="right">
			<table  table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<td width="8%" align="center">姓&nbsp&nbsp名：</td>
					<td width="20%">
						<input type="text" id="name" name="name" value="${name}"/>&nbsp;
					</td>
					<td width="8%" align="center">职&nbsp&nbsp务：</td>
					<td width="17%">
						<input type="text" id="post" name="post" value="${post}"/>&nbsp;
					</td>
					<td width="8%" align="center">手&nbsp&nbsp机:</td>
					<td width="17%">
						<input type="text" id="mobile" name="mobile" value="${mobile}"/>&nbsp;
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
	   					<display:table requestURI="${ctx}/securityuser/clientcontanct.action" uid="item" class="displaytable" name="listContact"
	   							pagesize="10"  size="item.size()" form="ContactQueryCondForm" excludedParams="*">		   							   					
							<display:column title="序号" style="width:25px">
								<c:out value="${item_rowNum}"/>
							</display:column>
							<display:column property="name" title="姓名" maxLength="30"/>
							<display:column property="post" title="职务"/>
							<display:column property="mobile" title="手机"/>
							<display:column property="telephone" title="座机"/>
							<display:column property="address" title="办公室"/>
							<display:column property="reark" title="备注"/>
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
