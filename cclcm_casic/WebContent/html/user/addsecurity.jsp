<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加涉密人员类别</title>
<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
<script language="javascript" src="${ctx}/_script/function.js"></script>
<script  language="JavaScript" >
<!--
function chk()
{
    if($("#security_name").val().trim() == "")
    {
        alert("请填写涉密人员类别名称");
        $("#security_name").focus();
        return false;
    }
	if(!specialCharFilter($("#security_name").val())){
		alert("类别名称包含特殊字符");
		$("#security_name").focus();
        return false;
	}
    return true;
}
	//-->
</script>
</head>
<body oncontextmenu="self.event.returnValue=false" onload="onHover();">
<center>
<form method="post" action="${ctx}/user/addsecurity.action">
  <table class="table_box" cellspacing=0 cellpadding=0 border=1 width="95%">
	 <tr>
		 <td colspan="2" class="title_box">
            	添加涉密人员类别
        </td>
    </tr>
    <tr>
      <td width="30%" align="center"><font style="color:red">*</font>&nbsp;类别名称：</td>
      <td><input type="text" name="security_name" size="38" id="security_name"/>
      </td>
    </tr>
    <tr>
      <td align="center">类别简介：</td>
      <td>
           <textarea rows="3" cols="38" name="security_desc" id="security_desc"></textarea>
      </td>
    </tr>
    <tr>
        <td colspan="2" align="center" class="bottom_box">
            <input type="button" value="添加" class="button_2003" onclick="if(chk()) forms[0].submit();">&nbsp;
            <input type="reset" value="重置" class="button_2003">&nbsp;
            <input type="button" value="返回" class="button_2003" onclick="location.href='${ctx}/user/managesecurity.action'">
        </td>
    </tr>
  </table>
  </form>
</center>
</body>
</html>
