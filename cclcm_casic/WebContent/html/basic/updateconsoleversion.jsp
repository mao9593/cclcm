<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>设定控制台版本</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen"/>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script  language="JavaScript" >
	<!--
	$(document).ready(function(){
		onHover();
	});
	function chk()
	{
		if($("#set_version").val().trim() == ""){
			alert("请填写客户端版本");
			$("#set_version").focus();
			return false;
		}	
	    window.returnValue = $("#set_version").val().trim(); 
	    window.close();
	}
	//-->
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
   	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="100%" height="100%">
	 <tr>
		 <td colspan="2" class="title_box">
            	设定控制台版本
        </td>
    </tr>
    <tr>
    	<td align="center">设定控制台版本：</td>
		<td>
		    <select name="set_version" id="set_version">
				<option value="">--请选择--</option>
					<s:iterator value="#request.versions" var="ver">
						<option value="${ver.version}">${ver.version}</option>
					</s:iterator>
			</select>
		</td>
    </tr>
    <tr>
        <td colspan="2" align="center" class="bottom_box">
            <input type="submit" value="修改" class="button_2003" onclick="return chk();">&nbsp;
            <input type="button" value="取消" class="button_2003" onclick="javascript:window.close();">
        </td>
    </tr>
  	</table>
</center>
</body>
</html>