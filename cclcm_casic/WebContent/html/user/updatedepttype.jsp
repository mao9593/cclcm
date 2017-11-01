<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改机构类型</title>
<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
<script language="JavaScript" src="${ctx}/_script/function.js"></script>
<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
<script  language="JavaScript" >
<!--
function chk()
{
    if(document.all.dept_type_name.value=='')
    {
        alert('请填写机构类型名称');
        document.all.spec_name.focus();
        return false;
    }
	if(!specialCharFilter(document.all.dept_type_name.value)){
		alert('机构类型名称包含特殊字符');
		document.all.dept_type_name.focus();
        return false;
	}
    return true;
}
	//-->
</script>
</head>
<body oncontextmenu="self.event.returnValue=false" onload="onHover();">
<center>
<form method="post" action="${ctx}/user/updatedepttype.action">
	<input type="hidden" name="dept_type_code" value="${dept_type_code}"/>
	<input type="hidden" name="update" value="Y"/>
  <table class="table_box" cellspacing=0 cellpadding=0 border=1 width="95%">
	 <tr>
		 <td colspan="2" class="title_box">
            	修改机构类型
        </td>
    </tr>
    <tr>
      <td width="30%" align="center"><font style="color:red">类型名称：</font></td>
      <td><input type="text" name="dept_type_name" size="38" value="${secDeptType.dept_type_name}"/>
      </td>
    </tr>
    <tr>
      <td align="center">类型简介：</td>
      <td>
           <textarea rows="3" cols="38" name="dept_type_desc">${secDeptType.dept_type_desc}</textarea>
      </td>
    </tr>
    <tr>
        <td colspan="2" align="center" class="bottom_box">
            <input type="button" value="修改" class="button_2003" onclick="if(chk()) forms[0].submit();">&nbsp;
            <input type="button" value="返回" class="button_2003" onclick="location.href='${ctx}/user/managedepttype.action'">
        </td>
    </tr>
  </table>
  </form>
</center>
</body>
</html>
