<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>保密组织机构</title>
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
		<td class="title_box">保密组织机构</td>
	</tr>
	<form id="ContactQueryCondForm" method="post" action="${ctx}/securitydept/mangesecuritydept.action?type=DEPT">
	<tr>
		<td align="right">
			<table  table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<td width="8%" align="center">姓&nbsp&nbsp名</td>
					<td width="20%">
						<input type="text" id="name" name="name" value="${name}"/>&nbsp;
					</td>
					<td width="8%" align="center">职&nbsp&nbsp务</td>
					<td width="17%">
						<input type="text" id="post" name="post" value="${post}"/>&nbsp;
					</td>
				<%-- 	<td width="8%" align="center">手&nbsp&nbsp机:</td>
					<td width="17%">
						<input type="text" id="mobile" name="mobile" value="${mobile}"/>&nbsp;
					</td> --%>
					<td align="center" colspan="2"> 
						&nbsp;<input name="button" type="submit" class="button_2003" value="查询" >
						&nbsp;<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" class="button_2003" value="新建保密机构" onclick="go('${ctx}/securitydept/addsecurityuser.action?name=null');"/>
					</td>	
				</tr>				
			</table>
		</td>
	</tr>
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	 			<td class="title_box" >保密组织结构</td>
	 			</tr>
	 			<tr>
	   				<td>
	   					<display:table requestURI="${ctx}/securitydept/mangesecuritydept.action" uid="item" class="displaytable" name="securityDeptList"
	   							pagesize="15"  size="item.size()" form="ContactQueryCondForm" excludedParams="*">		   							   					
							<display:column title="序号">
								<c:out value="${item_rowNum}"/>
							</display:column>
							<display:column property="name" title="姓名" maxLength="30"/>
							<display:column property="post" title="职务"/>
							<display:column title="所属机构">	
							 <c:choose>
									<c:when test="${item.type == 1}">
										保密委员会 
									</c:when>
									<c:when test="${item.type == 2}">
										保密处
									</c:when>
									<c:when test="${item.type == 3}">
										专职保密人员
									</c:when>
									<c:when test="${item.type == 4}">
									 部门兼（专）职保密员
									</c:when>
								</c:choose>
							</display:column>
							<display:column property="work" title="职责"/>
							<%-- <display:column property="mobile" title="手机"/>
							<display:column property="telephone" title="座机"/>
							<display:column property="email" title="邮件"/>
							<display:column property="address" title="办公室"/> --%>
							<display:column property="reark" title="备注"/>
							<display:column property="rank" title="排序值"/>
								<display:column title="操作" style="width:100px">
								<input type="button" class="button_2003" value="修改" onclick="go('${ctx}/securitydept/updatesecuritydept.action?id=${item.id}');"/>
								<input type="button" class="button_2003" value="删除" onclick="deletecompanycontact('${item.id}')"/>								
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
