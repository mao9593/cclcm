<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>档案管理</title>
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
		onHover();
		optionSelect();
		hideAddArch();
	});
	function hideAddArch(){
		$("#addArch").hide();
	if(${item_id}==0&&${type_id}==0){
		$("#addArch").show();
		}
	}
	function optionSelect(){
		$("#template_id").val("${template_id}");
	}
	function clearFindForm(){
		$("#ArchQueryCondForm :text").val("");
		$("#template_id").val("");
	}	
	function wopen(id){
		var url = '${ctx}/arch/viewarchdetail.action?id='+id;
		window.open(url,'','height='+(screen.availHeight-40)+', width='+(screen.availWidth-5)+', top=0, left=0, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no');
	}
	function reprintBarcode(id){
		$("#id").val(id);
		callServerPostForm("${ctx}/arch/reprintbarcode.action",document.forms[0]);
	}
	function getAjaxResult(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {					
			if(xmlHttp.responseText != null && "" != xmlHttp.responseText){
				//alert(xmlHttp.responseText);
				var barcodetype = xmlHttp.responseText.split("#")[0];
				var fileno = xmlHttp.responseText.split("#")[1];
				var barcode = xmlHttp.responseText.split("#")[2];		
				var url = "${ctx}/arch/configbarcode.action";
				var rValue = window.showModalDialog(url,'', 'dialogHeight:240px;dialogWidth:400px;center:yes;status:no;scroll:no;help:no;unadorned:no;resizable:no');
				if(rValue != null && rValue!= undefined && rValue.onOK == "Y"){
					var obj=new ActiveXObject("SprintCom.DataProcess.1");
					obj.PrintBarcode(barcodetype, fileno, barcode, rValue.n_dum, 0); 
					alert("补打完成");	
				}
			}else {
				alert("无默认条码规则或不需打条码，请联系系统管理员");	
			}
		}
	} 
	</script>
	<script type="text/javascript">
	function deleteArch(id){
	if(confirm("确定要删除此档案")){
		var url = "${ctx}/arch/deletearch.action?id="+escape(id);
		callServer(url);
		}
	}
	function updateResult(){
	if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
		if(xmlHttp.responseText == "ok"){
			alert("删除成功");
			go(window.location);
		}else{
			alert("取消失败，请重试");
			}
		}
	}
	function updateArch(arch_id){
		var url = "${ctx}/arch/viewupdatearch.action?id="+escape(arch_id);
		go(window.location=url)
	}
	</script>
</head>
<% /* out.clear();
   out = pageContext.pushBody(); */
 %>
<body oncontextmenu="self.event.returnValue=false">
<form method="POST" id="hiddenform">
	<input type="hidden" name="id" id="id"/>
</form>
<form id="ArchQueryCondForm" method="GET" action="${ctx}/arch/viewdosarch.action">
	<input type="hidden" id="id" name="id" value="${id}"/>
	<input type="hidden" id="type_id" name="type_id" value="${type_id}"/>
	<input type="hidden" id="item_id" name="item_id" value="${item_id}"/>
	<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">档案管理</td>
	</tr>
	<tr>
		<td align="right">
			<table  table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<td align="center">文件标题：<input type="text" id="file_title" name="file_title" value="${file_title}"/><br>
					<td align="center">档案条码：<input type="text" id="barcode" name="barcode" value="${barcode}"/><br>
					<td align="right">
						<input name="button" type="submit" class="button_2003" value="查询">&nbsp;
						<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">&nbsp;
						<input type="button" id="addArch" name="addArch" value="添加档案" class="button_2003" onclick="go('${ctx}/arch/addarch.action?id=${id}')"/>
						<input type="button" id="addArch" name="addArch" value="导出EXCEL" class="button_2003" onclick="go('${ctx}/arch/exportarchexcel.action?template_id=${template_id}&type_id=${type_id }&barcode=${barcode }&file_title=${file_title}&item_id=${item_id }&id=${id }')"/>
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
	   					<display:table requestURI="${ctx}/arch/viewdosarch.action" id="item" class="displaytable" name="archValueList"
	   					pagesize="${pageSize}" sort="page" partialList="true" size="${totalSize}" excludedParams="*" form="ArchQueryCondForm">
							<display:column title="序号">
								<c:out value="${item_rowNum}"/>
							</display:column>
							<display:column property="barcode" title="档案条码"/>
							<display:column property="dos_num" title="案卷号"/>
							<display:column property="arch_num" title="档号"/>
							<display:column property="file_title" title="文件标题" style="width:80px"/>
							<display:column property="seclv_code" title="密级"/>
							<display:column property="file_carr" title="载体"/>
							<display:column property="keep_limit" title="保管期限"/>
							<display:column property="status_name" title="状态"/>
							<display:column title="操作" style="width:220px">
								<input type="button" class="button_2003" value="详细" onclick="wopen('${item.id}');"/>
								<c:if test="${item.status eq 0 }">
								<input type="button" class="button_2003" value="删除" onclick="deleteArch('${item.id}');"/>
								</c:if>
								<c:if test="${item.status ne 0 }">
								<input type="button" class="button_2003" value="删除"  disabled="disabled"/>
								</c:if>
								<c:if test="${item.status eq 0 }">
								<input type="button" class="button_2003" value="修改" onclick="updateArch('${item.id}');"/>
								</c:if>
								<c:if test="${item.status ne 0 }">
								<input type="button" class="button_2003" value="修改" disabled="disabled"/>
								</c:if>
								<input type="button" class="button_2003" value="补打条码" onclick="reprintBarcode('${item.id}');"/>
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
