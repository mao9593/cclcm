<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改密级信息</title>
<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
<script language="JavaScript" src="${ctx}/_script/function.js"></script>
<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>

<script  language="JavaScript" >
<!--
function chk()
{
    if(document.all.seclv_name.value=='')
    {
        alert("请填写密级名称");
        document.all.seclv_name.focus();
        return false;
    }
	if(!specialCharFilter(document.all.seclv_name.value)){
		alert("密级名称包含特殊字符");
		document.all.seclv_name.focus();
        return false;
	}
	if(!isInteger(document.all.seclv_rank.value)){
    	alert("密级排序号应为1~10000的整数");
    	document.all.seclv_rank.focus();
    	return false;
    }
    return true;
}
	//-->
</script>
</head>
<body oncontextmenu="self.event.returnValue=false" onload="onHover();">
<center>
<form method="post" action="${ctx}/user/updateseclv.action">
	<input type="hidden" name="update" value="Y"/>
	<input type="hidden" name="seclv_code" value="${seclv_code}"/>
  <table class="table_box" cellspacing=0 cellpadding=0 border=1 width="50%">
	 <tr>
		 <td colspan="2" class="title_box">
            	修改密级信息
        </td>
    </tr>
    <tr>
      <td width="40%" align="center">密&nbsp;级&nbsp;名&nbsp;称&nbsp;：</td>
      <td><input type="text" name="seclv_name" size="38" value="${seclv.seclv_name}" maxlength="15"/>
      </td>
    </tr>
    <tr>
      <td align="center">密级排序号：</td>
      <td><input type="text" name="seclv_rank" size="38" value="${seclv.seclv_rank}" maxlength="15"/>
      </td>
    </tr>
    <tr>
      <td align="center">密&nbsp;级&nbsp;编&nbsp;码&nbsp;：</td>
      <td><input type="text" name="othername" size="38" value="${seclv.othername}" maxlength="15"/>
      </td>
    </tr>
    <tr>
        <td colspan="2" align="center" class="bottom_box">
            <input type="button" value="修改" class="button_2003" onclick="if(chk()) forms[0].submit();">&nbsp;
            <input type="button" value="返回" class="button_2003" onclick="location.href='${ctx}/user/viewseclevel.action'">
        </td>
    </tr>
  </table>
  </form>
</center>
</body>
</html>
