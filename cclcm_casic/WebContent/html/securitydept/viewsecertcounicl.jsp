<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>定密委员会</title>
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
		     var url="${ctx}/securitydept/deletesecuritydept.action?id="+obj;
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
		<td class="title_box">定密委员会</td>
	</tr>
	<form id="ContactQueryCondForm" method="post" action="${ctx}/securitydept/viewsecertcounicl.action?type=5">
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
	   					<display:table requestURI="${ctx}/securitydept/viewsecertcounicl.action?type=5" uid="item" class="displaytable" name="securityDeptList"
	   							pagesize="5"  size="item.size()" form="ContactQueryCondForm" excludedParams="*">		   							   					
							<display:column title="序号">
								<c:out value="${item_rowNum}"/>
							</display:column>
							<display:column property="name" title="姓名" maxLength="30"/>
							<display:column property="post" title="职务"/>
							<display:column title="所属机构">	
							 <c:choose>
									<c:when test="${item.type == 5}">
										定密委员会
									</c:when>
									<c:when test="${item.type == 6}">
										定密工作小组
									</c:when>
									<c:when test="${item.type == 7}">
										法定代表人
									</c:when>
									<c:when test="${item.type == 8}">
									          定密责任人
									</c:when>
								</c:choose>
							</display:column>
							<display:column property="work" title="职责"/>
							<display:column property="mobile" title="手机"/>
							<display:column property="telephone" title="座机"/>
							<display:column property="email" title="邮件"/>
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
