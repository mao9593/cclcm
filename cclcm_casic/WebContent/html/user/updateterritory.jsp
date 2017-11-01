<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改地区信息</title>
<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
<script language="JavaScript" src="${ctx}/_script/function.js"></script>
<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
<script  language="JavaScript" >
<!--
function chk()
{
    if(document.all.terr_name.value=='')
    {
        alert("请填写地区名称");
        document.all.terr_name.focus();
        return false;
    }
	if(!specialCharFilter(document.all.terr_name.value)){
		alert("地区名称包含特殊字符");
		document.all.terr_name.focus();
        return false;
	}
    return true;
}
	//-->
</script>
</head>
<body oncontextmenu="self.event.returnValue=false" onload="onHover();">
<center>
<form method="post" action="${ctx}/user/updateterritory.action">
	<input type="hidden" name="update" value="Y"/>
	<input type="hidden" name="terr_code" value="${terr_code}"/>
  <table class="table_box" cellspacing=0 cellpadding=0 border=1 width="95%">
	 <tr>
		 <td colspan="2" class="title_box">
            	修改地区信息
        </td>
    </tr>
    <tr>
     <td width="30%" align="center"><font style="color:red">地区编号：</font></td>
      <td>${terr_code}</td>
    </tr>
    <tr>
      <td align="center"><font style="color:red">地区名称：</font></td>
      <td><input type="text" name="terr_name" size="38" value="${secTerr.terr_name}"/>
      </td>
    </tr>
    <tr>
     <td align="center">地区简介：</td>
      <td>
            <textarea rows="3" cols="38" name="terr_desc">${secTerr.terr_desc}</textarea>
      </td>
    </tr>
    <tr>
        <td colspan="2" align="center" class="bottom_box">
            <input type="button" value="修改" class="button_2003" onclick="if(chk()) forms[0].submit();">&nbsp;
            <input type="button" value="返回" class="button_2003" onclick="location.href='${ctx}/user/manageterr.action'">
        </td>
    </tr>
  </table>
  </form>
</center>
</body>
</html>
