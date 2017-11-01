<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>查看打印机列表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script>
	$(document).ready(function(){
		//onHover();
		optionSelect();
	});
	function clearFindForm(){
		$("#Form :text").val("");
		$("#seclv_med").val("");
	}	
	function optionSelect(){
		$("#seclv_med").val(${seclv_code});
	}
	function deletePrinter(printer_code){
	    var url="${ctx}/basic/delprinter.action?printer_code="+escape(printer_code);
	    if(confirm("确定删除该打印机？")){
	    	window.location.href =url;
	    }
	}
	</script>
</head>

<body oncontextmenu="self.event.returnValue=false">
<form  id="Form" method="POST" action="${ctx}/basic/manageprinter.action">
<input type="hidden" name="printer_code" value="${printer_code}"/>
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">打印机列表</td>
	</tr>
	<tr>
		<td align="right">
			<table  table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<td align="center">
						控制台代号：<input type="text" id="console_code" name="console_code" value="${console_code}" size="15"/>
					</td>
					<td align="center">
						控制台名称：<input type="text" id="console_name" name="console_name" value="${console_name}" size="15"/>
					</td>
					<td align="center">
						打印机名称：<input type="text" id="printer_name" name="printer_name" value="${printer_name}" size="15"/>
					</td>
					<td align="center">
						密级：
						<select name="seclv_code" id="seclv_med">
							<option value="">--所有--</option>
							<s:iterator value="#request.seclvList" var="seclv">
								<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
							</s:iterator>
						</select>
					</td>
					<td align="center" width="270">
						<input name="button" type="submit" class="button_2003" value="查询">&nbsp;
						<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">&nbsp;&nbsp;
						<input name="button" type="button" class="button_2003" onClick="go('${ctx}/basic/addprinter.action');" value="增加新打印机 ">
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
					<display:table requestURI="${ctx}/basic/manageprinter.action" id="item" class="displaytable" name="printerList">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column property="printer_name" title="打印机名称"  maxLength="15"/>
						<display:column property="seclv_name" title="密级"/>
						<display:column property="console_name" title="控制台"  maxLength="15"/>
						<display:column property="printer_path" title="路径"  maxLength="20"/>	
						<display:column property="is_double" title="支持双面"/>			
						<display:column property="printer_model" title="型号"  maxLength="15"/>																											
						<display:column property="create_time_str"   sortable="true" title="添加时间"/>							
						<display:column title="操作" style="width:180px">
							<c:if test="${!item.is_delete}">
							    <input type="button" class="button_2003" value="配置" onclick="go('${ctx}/basic/configprinter.action?printer_code=${item.printer_code}');"/>&nbsp;
							    <input type="button" class="button_2003" value="详细" onclick="go('${ctx}/basic/viewprinterdetail.action?printer_code=${item.printer_code}');"/>&nbsp;	
								<input type="button" class="button_2003" value="修改" onclick="go('${ctx}/basic/updateprinter.action?printer_code=${item.printer_code}');"/>&nbsp;
								<input type="button" class="button_2003" value="删除" onclick="deletePrinter('${item.printer_code}')";/>
							</c:if>
							<c:if test="${item.is_delete}">
								${item.deleteStatus}
							</c:if>
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
