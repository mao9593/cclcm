<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增密级</title>
<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
<script language="JavaScript" src="${ctx}/_script/function.js"></script>
<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
<script  language="JavaScript" >
<!--
function chk()
{
    if(parseInt(${seclvCount}) >= 8){
    	alert("在用密级不能超过8个");
		return false;
    }
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
	if(document.all.seclv_rank.value=='')
    {
        alert("请填写密级排序号");
        document.all.seclv_rank.focus();
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
<body onload="onHover();" oncontextmenu="self.event.returnValue=false">
<center>
<form method="post" action="${ctx}/user/addseclv.action">
<input type="hidden" name="add" value="Y"/>
  <table class="table_box" cellspacing=0 cellpadding=0 border=1 width="50%">
	 <tr>
		 <td colspan="2" class="title_box">
            	新增密级
        </td>
    </tr>
    <tr>
      <td width="40%" align="center"><font style="color:red">*</font>密&nbsp;级&nbsp;名&nbsp;称&nbsp;：</td>
      <td><input type="text" name="seclv_name" size="38" maxlength="15"/>
      </td>
    </tr>
    <tr>
      <td align="center"><font style="color:red">*</font>密级排序号：</td>
      <td><input type="text" name="seclv_rank" size="38" maxlength="15"/>
      </td>
    </tr>
    <tr>
      <td align="center">密&nbsp;级&nbsp;别&nbsp;名&nbsp;：</td>
      <td><input type="text" name="othername" size="38" value="${seclv.othername}" maxlength="15"/>
      </td>
    </tr>
    <tr>
        <td colspan="2" align="center" class="bottom_box">
            <input type="button" value="添加" class="button_2003" onclick="if(chk()) forms[0].submit();">&nbsp;
            <input type="reset" value="重置" class="button_2003">&nbsp;
            <input type="button" value="返回" class="button_2003" onclick="location.href='${ctx}/user/viewseclevel.action'">
        </td>
    </tr>
  </table>
  </form>
</center>
</body>
</html>
