<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改密级信息</title>
<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
<script language="JavaScript" src="${ctx}/_script/function.js"></script>
<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>

<script  language="JavaScript" >
<!--
function chkpass()
{
	if($("#password").val().trim()!= null &&$("#password").val().trim()!= ""){
	    var pwd_new =/[\u4e00-\u9fa5]+/; 
        if(pwd_new.test($("#password").val())){
            alert("密码中不能含有汉字");
            $("#password").focus();
		    return false;
        }
	} 
	return true;
}
	//-->
</script>
</head>
<body oncontextmenu="self.event.returnValue=false" onload="onHover();">
<center>
<form method="post" action="${ctx}/basic/configconsoleexitpassword.action">
  <input type="hidden" name="update" value="Y"/>
  <table class="table_box" cellspacing=0 cellpadding=0 border=1 width="50%">
	 <tr>
		 <td colspan="2" class="title_box">
            	控制台退出密码设置
        </td>
    </tr>
    <tr>
      <td width="40%" align="center">密&nbsp;码&nbsp;设&nbsp;置&nbsp;：</td>
      <td><input type="text" name="password" id="password" size="38" value="${password}" maxlength="15"/>
      </td>
    </tr>
    <tr>
        <td colspan="2" align="center" class="bottom_box">
            <input type="button" value="修改" class="button_2003" onclick="if(chkpass()) forms[0].submit();">&nbsp;
            <input type="button" value="返回" class="button_2003" onclick="location.href='${ctx}/basic/manageconsole.action'">
        </td>
    </tr>
  </table>
</form>
</center>
</body>
</html>
