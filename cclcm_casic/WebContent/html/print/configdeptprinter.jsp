<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<base target="_self">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>配置部门默认打印机</title>
<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" />
<script language="JavaScript" src="${ctx}/_script/function.js"></script>
<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
<script  language="JavaScript" >
<!--
$(document).ready(function(){
	onHover();
	if(${isEmpty}){
		alert("该部门没有配置任何打印机，请先为该部门配置至少一台打印机");
		window.close();
		return;
	}
	if("${config}" == "Y"){
		alert("配置成功");
		window.close();
		return;
	}
	$("#printer_code").val("${printer_code}");
});
function chk()
{	
    return true;
}
//-->
</script>
</head>
<body oncontextmenu="self.event.returnValue=true">
<center>
<form method="post" action="${ctx}/print/configdeptprinter.action" id="ConfigOpenScopeForm">
	<input type="hidden" name="dept_id" value="${dept_id}" />
	<input type="hidden" name="config" value="Y" /> 
  	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="95%">
	<tr>
		 <td colspan="2" class="title_box">
            	配置部门默认打印机
        </td>
    </tr>
    <tr>
		<td align="center">部门名称：</td>
		<td><font color="blue"><b>${dept_name}</b></font></td>
    </tr>
    <tr>
    	<td align="center">默认打印机：</td>
		<td>
			<select name="printer_code" id="printer_code">
				<option value="">--请选择--</option>
				<s:iterator value="#request.printerGroupList" var="printer">
					<option value="${printer.printer_code}">${printer.printer_name}</option>
				</s:iterator>
			</select>
		</td>
    </tr>
    <tr>
        <td colspan="2" align="center" class="bottom_box">
            <input type="submit" value="修改" class="button_2003" onclick="return chk();">&nbsp;
            <input type="button" value="关闭" class="button_2003" onclick="window.close();">&nbsp;
        </td>
    </tr>
  	</table>
</form>
</center>
</body>
</html>
