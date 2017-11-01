<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增案卷</title>
<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
<script language="JavaScript" src="${ctx}/_script/function.js"></script>
<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
<script type="text/javascript" src="${ctx}/uploadify/jquery.min.js"></script>
<script  language="JavaScript" >
<!--
function chk()
{
    if($("#dos_code").val().trim() == "")	
    {
        alert("请填写案卷号");
        $("#dos_code").focus();
        return false;
    }
    var arch_num = $("#arch_num").val();
    var code_pattern=/^[0-9]+$/;
	if(arch_num.length>0 && !code_pattern.test(arch_num)){
		alert("文件件数只能输入数字");
		$("#arch_num").val("");
		$("#arch_num").focus();
		return false;
	}
    var total_page = $("#total_page").val();
	if(total_page.length>0 && !code_pattern.test(total_page)){
		alert("总页数只能输入数字");
		$("#total_page").val("");
		$("#total_page").focus();
		return false;
	}
    return true;
}
	//-->
</script>
</head>
<body onload="onHover();" oncontextmenu="self.event.returnValue=false">
<center>
<form method="post" action="${ctx}/arch/adddos.action" target="_parent">
<input type="hidden" name="add" value="Y"/>
<input type="hidden" name="item_id" value="${item_id}"/>
  <table class="table_box" cellspacing=0 cellpadding=0 border=1 width="50%">
	 <tr>
		 <td colspan="2" class="title_box">
            	新增案卷
        </td>
    </tr>
    <tr>
      <td width="40%" align="center"><font style="color:red">*</font>案卷号：</td>
      <td><input type="text" id="dos_code" name="dos_code"/></td>
    </tr>
    <tr>
      <td align="center">子项代号：</td>
      <td><input type="text" id="sub_prog_code" name="sub_prog_code"/></td>
    </tr>
    <tr>
      <td align="center">项目号：</td>
      <td><input type="text" id="prog_code" name="prog_code"/></td>
    </tr>
    <tr>
      <td align="center">分类号：</td>
      <td><input type="text" id="type_code" name="type_code"/></td>
    </tr>
    <tr>
      <td align="center">案卷题名：</td>
      <td><input type="text" id="dos_subject" name="dos_subject"/></td>
    </tr>
    <tr>
      <td align="center">目录号：</td>
      <td><input type="text" id="dir_code" name="dir_code"/></td>
    </tr>
    <tr>
      <td align="center">编制单位：</td>
      <td><input type="text" id="unit" name="unit"/></td>
    </tr>
    <tr>
      <td align="center">密级：</td>
      <td><input type="text" id="seclv_code" name="seclv_code"/></td>
    </tr>
    <tr>
      <td align="center">文件件数：</td>
      <td><input type="text" id="arch_num" name="arch_num"/></td>
    </tr>
    <tr>
      <td align="center">总页数：</td>
      <td><input type="text" id="total_page" name="total_page"/></td>
    </tr>
    <tr>
      <td align="center">档案分类：</td>
      <td><input type="text" id="arch_type" name="arch_type"/></td>
    </tr>
    <tr>
      <td align="center">立卷人：</td>
      <td><input type="text" id="create_user" name="create_user"/></td>
    </tr>
    <tr>
      <td align="center">全宗号：</td>
      <td><input type="text" id="dos_num" name="dos_num"/></td>
    </tr>
    <tr>
      <td align="center">备注：</td>
      <td><input type="text" id="summ" name="summ"/></td>
    </tr>
    <tr>
      <td align="center">责任者：</td>
      <td><input type="text" id="dutyman" name="dutyman"/></td>
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
