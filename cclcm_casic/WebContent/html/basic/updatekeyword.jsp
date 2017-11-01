<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>修改关键字</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script  language="JavaScript" >
	<!--
	$(document).ready(function(){
		onHover();
	});
	function chk()
	{
		if($("#keyword_content").val().trim() == ""){
			alert("请填写关键字");
			$("#keyword_content").focus();
			return false;
		}
		/*var pattern=/^[0-9a-zA-Z_\u4e00-\u9fa5]{1,15}$/;
		if(!pattern.test($("#keyword_content").val())){
			alert("关键字只能由数字、字母、汉字或下划线组成，长度小于15位");
			$("#keyword_content").focus();
			return false;
		}*/
	    return true;
	}
	//-->
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<form method="post" action="${ctx}/basic/updatekeyword.action">
	<input type="hidden" value="${keyword.keyword_code}" name="keyword_code"/>
	<input type="hidden" value="Y" name="update"/>
   	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="60%">
	 <tr>
		 <td colspan="2" class="title_box">
            	修改关键字
        </td>
    </tr>
    <tr>
    	<td align="center"><font color="red">*</font>关键字：</td>
		<td>
			<input type="text" name="keyword_content" id="keyword_content" value="${keyword.keyword_content}"/>
			<font color="red">　　关键字支持字母、数字、汉字、"★"字符</font>
		</td>
    </tr>
    <tr>
    	<td align="center">关键字说明：</td>
		<td>
			<textarea rows="3" cols="60" name="keyword_detail" id="keyword_detail">${keyword.keyword_detail}</textarea>
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