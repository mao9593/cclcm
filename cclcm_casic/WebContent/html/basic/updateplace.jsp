<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>更新地点</title>
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
		if($("#place_name").val() == ""){
			alert("请添加地点名称");
			$("#place_name").focus();
			return false;
		}
	    return true;
	}
	//-->
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<form method="post" action="${ctx}/basic/updateplace.action">
	<input type="hidden" value="${place.place_code}" name="place_code"/>
	<input type="hidden" value="Y" name="update"/>
	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="80%">
	 <tr>
	 	<td colspan="2" class="title_box">更新地点</td>
	 </tr>
	 <tr>
		 <td align="center">
            	地点代号
        </td>
        <td>
            <font color="blue">${place.place_code}</font>
        </td>
    </tr>
    <tr>
    	<td align="center"><font color="red">*</font>地点名称：</td>
		<td>
    		<input type="text" id="place_name" name="place_name" value="${place.place_name}" size="20"/>
		</td>
    </tr>
    <tr>
    	<td align="center">地点描述：</td>
		<td>
    		<input type="text" id="place_desc" name="place_desc" value="${place.place_desc}" size="20"/>
		</td>
    </tr>
    <tr>
        <td colspan="2" align="center" class="bottom_box">
             <input type="submit" value="修改" class="button_2003" onclick="return chk();">&nbsp;
            <input type="button" value="返回" class="button_2003" onclick="javascript:history.go(-1);">
        </td>
    </tr>
  	</table>

</form>
</center>
</body>
</html>