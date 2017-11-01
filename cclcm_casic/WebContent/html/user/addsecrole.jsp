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
<form action="${ctx}/user/addsecrole.action" method="POST">
<table width="95%" border="1" cellspacing="0" cellpadding="0"  align="center" class="table_box">
	<tr>
	    <td colspan=4  class="title_box">
		新安全角色添加
	    </td>
  </tr>
  <tr> 
  <tr> 
    <td width="30%" align="center"> 
      <div style="color:red">角色名称：</div>
    </td>
    <td>
      <input name="role_name" value="${role_name}"/><c:if test="${roleExist=='Y'}"><font color="red"> 角色名已存在！</font></c:if>
	</td>
  </tr>
  <tr> 
    <td align="center"> 
      <div style="color:red">子&nbsp;&nbsp;系&nbsp;&nbsp;统：</div>
    </td>
    <td><c:set var="code1" value="${subsys_code}" scope="request"/>
		<select name="subsys_code">
 			<s:iterator value="#request.subsysList" var="item">
				<c:set var="code2" value="${item.subsys_code}" scope="request"/>
				<option value="${item.subsys_code}" <s:if test="#request.code2==#request.code1">selected="selected"</s:if> >${item.subsys_name}</option>
			</s:iterator>
		</select>
	</td>
  </tr>
  <tr> 
    <td align="center"> 
        <div>描&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;述：</div>
    </td>
    <td>
      <textarea name="role_desc" rows="5" cols="80"></textarea>
	</td>
  </tr>
  <tr>
    <td colspan=2 align=center> 
      <input type="button" class="button_2003" value="保存返回" title="点保存之后返回角色列表" onclick="if(chk()) forms[0].submit();"/>
      &nbsp;
      <input type="reset" Class="button_2003" value="重置" />
      &nbsp;
      <input class="button_2003" type="reset" name="Submit" value="返回" title="取消新增角色类型并返回角色列表" onClick="go('${ctx}/user/managesecrole.action');">
    </td>
  </tr>
</table>
</form>
</body>
</html>
