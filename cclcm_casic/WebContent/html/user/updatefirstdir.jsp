<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改目录排序值</title>
<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
<script language="JavaScript" src="${ctx}/_script/function.js"></script>
<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>

<script  language="JavaScript" >
<!--
function chk()
{
	var numval = /^[0-9]*$/;
    var dir_rank = document.getElementById("dir_rank").value;
    if(dir_rank != ""){
   	    if(!numval.test(dir_rank)) {
   	    	alert("排序值应为整数");
	    	document.getElementById("dir_rank").focus();
	    	return false;
	    	}	
	   	if(dir_rank.length > 2) {
	    	alert("请输入2位之内的排序值");
	    	document.getElementById("dir_rank").focus();
	    	return false;
	    	}
	}else {
		alert("请输入排序值");
		document.getElementById("dir_rank").focus();
		return false;
	}
    return true;
}
	//-->
</script>
</head>
<body oncontextmenu="self.event.returnValue=false" onload="onHover();">
<center>
<form method="post" action="${ctx}/user/updatefirstdir.action">
	<input type="hidden" name="update" value="Y"/>
	<input type="hidden" name="oper_code" value="${secoperation.oper_code}"/>
  <table class="table_box" cellspacing=0 cellpadding=0 border=1 width="50%">
	 <tr>
		 <td colspan="2" class="title_box">
            	修改目录排序值
        </td>
    </tr>
    <tr>
      <td width="40%" align="center">目&nbsp;录&nbsp;名&nbsp;称&nbsp;：</td>
      <td>${secoperation.oper_name}
      </td>
    </tr>
    <tr>
      <td align="center">目录排序值：</td>
      <td><input type="text" name="dir_rank" id="dir_rank" size="10" value="${secoperation.dir_rank}" maxlength="15"/>
      </td>
    </tr>
    <tr>
        <td colspan="2" align="center" class="bottom_box">
            <input type="button" value="修改" class="button_2003" onclick="if(chk()) forms[0].submit();">&nbsp;
            <input type="button" value="返回" class="button_2003" onclick="location.href='${ctx}/user/managefirstdir.action'">
        </td>
    </tr>
  </table>
  </form>
</center>
</body>
</html>
