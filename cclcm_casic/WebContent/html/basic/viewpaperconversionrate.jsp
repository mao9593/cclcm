<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>查看纸张折合率</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
	<link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
	<script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script>
		function del(type_name){
		
			if(confirm("要删除该条折合率？")){
				var url = "${ctx}/ledger/deletepaperconversionrate.action?type_name="+type_name;
				window.location = url;
			}
		}
		
		function update(type_name,conversion_rate){
				var url = "${ctx}/html/ledger/viewupdatepaperconversionrate.jsp?type_name="+type_name+"&conversion_rate="+conversion_rate;
				window.location = url;
				
			
		}
	</script>
</head>

<body oncontextmenu="self.event.returnValue=false">
<form id="LedgerQueryCondForm" method="POST" action="${ctx}/basic/viewpaperstaticbyall.action">
	<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">纸张折合率</td>
	</tr>
		<tr>
		<td  align="right"><input type="button"  class="button_2003" value="添加折合率" onclick="go('${ctx}/html/ledger/addpaperconversionrate.jsp')"/> </td>
	</tr>
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
						<display:table requestURI="${ctx}/ledger/viewpaperconversionrate.action" id="item" class="displaytable" name="paperTypeList" pagesize="15" sort="list" >	   					
							<display:column title="序号">
								<c:out value="${item_rowNum}"/>
							</display:column>													
							<display:column property="type_name" title="纸张类型"/>						
							<display:column property="conversion_rate" title="折合率"/>
							<display:column title="操作" style="width:100px">
							<div>
								<input type="button" class="button_2003" value="修改" onclick="return update('${item.type_name}','${item.conversion_rate}')"/>
								<input type="button" class="button_2003" value="删除" onclick="return del('${item.type_name}');"/>
							</div>
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
