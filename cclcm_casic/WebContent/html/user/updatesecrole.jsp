<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
	<script language="JavaScript" src="${ctx}/_script/function.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
<script>
<!--
function go(url)
	{
		window.location.href=url;
	}
function chk()
{
    if(document.all.role_name.value=='')
    {
        alert('请填写角色名称');
        document.all.role_name.focus();
        return false;
    }
	 if(!specialCharFilter(document.all.role_name.value)){
	    alert('角色名称包含特殊字符');
		document.all.role_name.focus();
        return false;
	}
    return true;
}
//-->
</script>
</head>
<body oncontextmenu="self.event.returnValue=false" onload="onHover();">
<form action="${ctx}/user/updatesecrole.action" method="POST">
	<input name="o_role_name" type="hidden" value="${secRole.role_name}"/>
	<input name="role_id" type="hidden" value="${secRole.role_id}"/>
<table width="95%" border="1" cellspacing="0" cellpadding="0"  align="center" class="table_box">
	<tr>
	    <td colspan=4  class="title_box">
		安全角色修改
	    </td>
  </tr>
  <tr> 
  <tr> 
    <td width="30%" align="center"> 
      <div style="color:red">角色名称：</div>
    </td>
    <td>
      <input name="role_name" value="${secRole.role_name}"/><c:if test="${roleExist=='Y'}"><font color="red"> 角色名  ${role_name} 已存在！</font></c:if>
	</td>
  </tr>
  <tr> 
    <td align="center"> 
      <div style="color:red">子&nbsp;&nbsp;系&nbsp;&nbsp;统：</div>
    </td>
    <td>&nbsp;${subsys_name}</td>
  </tr>
  <tr> 
    <td align="center"> 
        <div>描&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;述：</div>
    </td>
    <td>
      <textarea name="role_desc" rows="5" cols="80">${secRole.role_desc}</textarea>
	</td>
  </tr>
  <tr>
    <td colspan=2 align=center> 
      <input type="button" class="button_2003" value="修改" title="保存并返回角色列表" onclick="if(chk()) forms[0].submit();"/>
      &nbsp;
      <input type="button" value="返回" class="button_2003" onclick="javascript:history.go(-1);">
    </td>
  </tr>
</table>
</form>
</body>
</html>
