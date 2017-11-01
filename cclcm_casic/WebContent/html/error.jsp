<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
    <title>页面运行异常</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/my-web-control.jsp"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
</head>
<body style="text-align:center">
<table cellpadding="0" cellspacing="0" border="1" width="100%" height="100%">
	<tr>
		<td style="font-size:9pt" align="center" valign="middle">
	    <div class="panel" style="padding:10px; width:500px;  text-align:center">
	        <fieldset style="height:250px">
	            <legend><b>系统提示</b></legend>
	            <table width=100% height=100%>
		            <tr>
			            <td width=40% align="center"><img src="${ctx}/images/_system/warn.gif"/></td>
					    <td> 提示信息：${errorMsg}</td>
				    </tr>
			    </table>
	        </fieldset>
				<input type="button" value="返回" class="button_2003" onclick="javascript:history.go(-1);">
	    </div>
		</td>
	</tr>
</table>
</body>
</html>