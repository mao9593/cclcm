<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增子项目</title>
<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
<script language="JavaScript" src="${ctx}/_script/function.js"></script>
<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
<script type="text/javascript" src="${ctx}/uploadify/jquery.min.js"></script>
<script  language="JavaScript" >
<!--
function chk()
{
    if($("#item_code").val().trim() == "")	
    {
        alert("请填写子项代号");
        $("#item_code").focus();
        return false;
    }
    return true;
}
	//-->
</script>
</head>
<body onload="onHover();" oncontextmenu="self.event.returnValue=false">
<center>
<form method="post" action="${ctx}/arch/additem.action" target="_parent">
<input type="hidden" name="add" value="Y"/>
<input type="hidden" name="type_id" value="${type_id}"/>
  <table class="table_box" cellspacing=0 cellpadding=0 border=1 width="50%">
	 <tr>
		 <td colspan="2" class="title_box">
            	新增子项
        </td>
    </tr>
    <tr>
      <td width="40%" align="center"><font style="color:red">*</font>子项代号：</td>
      <td><input type="text" id="item_code" name="item_code"/></td>
    </tr>
     <tr>
      <td align="center">备注：</td>
      <td><input type="text" id="summ" name="summ"/></td>
    </tr>
    <tr>
        <td colspan="2" align="center" class="bottom_box">
            <input type="button" value="提交" class="button_2003" onclick="if(chk()) forms[0].submit();">&nbsp;
            <input type="button" value="返回" class="button_2003" onclick="history.go(-1);">
        </td>
    </tr>
  </table>
 </form>
</center>
</body>
</html>
