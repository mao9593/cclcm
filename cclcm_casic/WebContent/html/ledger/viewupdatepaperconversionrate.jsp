<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>修改折合率</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
</head>
<script>

function chk()
{	
	var reg =/^\d+\.{0,1}\d*$/;
    if(!reg.test($("#conversion_rate").val().trim())){
    	 alert("请输入数字");
   		 $("#conversion_rate").focus();
		 return false;
    }
   if($("#conversion_rate").val().length == 0){
		alert("折合率不能为空");
        return false;
	}
   return true;
}

</script>
<body oncontextmenu="self.event.returnValue=false" onload="onHover();" style="padding-top: 100px">
<center>
<form id="DefaultPasswordForm" method="GET" action="${ctx}/ledger/updatepaperconversionrate.action">
<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="40%">
		
	<tr>
		<td colspan="3" class="title_box"><b>&nbsp;&nbsp;修改折合率</b></td>
	</tr>			
	<tr>
		<td align="center">当前纸张类型</td>
		<td align="center">与A4纸折合率</td>
	</tr>
	
	<tr>
		<input type="hidden" id="type_name" name="type_name" value="<%=request.getParameter("type_name") %>"/>
		<td align="center"><%=request.getParameter("type_name") %></td>
		<td align="center"><input type="text" name="conversion_rate" id="conversion_rate" value="<%=request.getParameter("conversion_rate") %>"/>&nbsp;</td>
	</tr>
	
	<tr>
        <td colspan="3" align="center" class="bottom_box">
            <input type="submit" value="修改" class="button_2003" onclick="return chk()">&nbsp;
            <input type="button" value="返回" class="button_2003" onclick="history.go(-1);"/>
        </td>
    </tr>
</table>
</form>
</center>
</body>
</html>

