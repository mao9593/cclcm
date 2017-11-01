<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改资产种类</title>
<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
<script language="JavaScript" src="${ctx}/_script/function.js"></script>
<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>

<script  language="JavaScript" >
<!--
function chk(){
	if(document.all.property_prefix.value==''){
        alert("请填写种类前缀");
        document.all.property_prefix.focus();
        return false;
    }
    if(document.all.typename.value==''){
        alert("请填写种类名称");
        document.all.typename.focus();
        return false;
    }
    if(!specialCharFilter(document.all.property_prefix.value)){
		alert("种类前缀不能包含特殊字符");
		document.all.property_prefix.focus();
        return false;
	}
	if(!specialCharFilter(document.all.typename.value)){
		alert("种类名称不能包含特殊字符");
		document.all.typename.focus();
        return false;
	}
	if(!isInteger(document.all.serial_no.value)){
		alert("种类流水号不合法！");
		document.all.typename.focus();
        return false;
	}
    return true;
}
	//-->
</script>
</head>
<body oncontextmenu="self.event.returnValue=false" onload="onHover();">
<center>
<form method="post" action="${ctx}/asset/updatekind.action">
	<input type="hidden" name="update" value="Y"/>
	<input type="hidden" name="id" value="${type.id}"/>
  <table class="table_box" cellspacing=0 cellpadding=0 border=1 width="50%">
	 <tr>
		 <td colspan="2" class="title_box">修改资产种类(提示：种类名称中包含设备或者建筑字样才能触发净值计算) </td>
    </tr>
    <tr>
      <td width="40%" align="center"><font style="color:red">*</font>种类前缀：</td>
      <td><input type="text" name="property_prefix" size="38" value="${type.property_prefix}" maxlength="15"/>
      </td>
    </tr>
    <tr>
      <td width="40%" align="center"><font style="color:red">*</font>种类名称：</td>
      <td><input type="text" name="typename" size="38" value="${type.property_kind}" maxlength="15"/>
      </td>
    </tr>
    <tr>
      <td width="40%" align="center"><font style="color:red">*</font>种类流水号（该流水号修改将影响后续台账，修改请慎重！）：</td>
      <td><input type="text" name="serial_no" size="38" value="${type.serial_no}" maxlength="15"/>
      </td>
    </tr>
    <tr>
        <td colspan="2" align="center" class="bottom_box">
            <input type="button" value="修改" class="button_2003" onclick="if(chk()) forms[0].submit();">&nbsp;
            <input type="button" value="返回" class="button_2003" onclick="location.href='${ctx}/asset/managekind.action'">
        </td>
    </tr>
  </table>
  </form>
</center>
</body>
</html>
