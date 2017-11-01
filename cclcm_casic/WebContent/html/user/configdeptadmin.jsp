<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<base target="_self">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>配置部门管理员</title>
<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" />
<script language="JavaScript" src="${ctx}/_script/function.js"></script>
<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
<script  language="JavaScript" >
<!--
$(document).ready(function(){
	onHover();
	//$("#is_inherit").attr("checked",${adminConfig.is_inherit eq 'Y'})
	if("${config}" == "Y"){
		alert("配置成功");
		window.close();
		return;
	}
});
function chk()
{	
	if($("#dept_ids").val() == ""){
		alert("请设置管理部门");
		return false;
	}
    return true;
}
//-->
</script>
</head>
<body oncontextmenu="self.event.returnValue=true">
<center>
<form method="post" action="${ctx}/user/configdeptadmin.action" id="ConfigDeptAdminForm">
	<input type="hidden" name="user_iidd" value="${user_iidd}" />
	<input type="hidden" name="config" value="Y" /> 
  	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="95%">
	<tr>
		 <td colspan="2" class="title_box">
            	配置部门管理员
        </td>
    </tr>
    <tr>
		<td align="center">管理员用户名：</td>
		<td><font color="blue"><b>${user_name}</b></font></td>
    </tr>
    <tr>
    	<td align="center">部门名称：</td>
		<td>
			<textarea id="dept_names" readonly cols="40" rows="3" onclick="openDeptSelect('dept_names','dept_ids','checkbox')">${adminConfig.dept_names}</textarea>&nbsp;
			<input type="hidden" name="dept_ids" id="dept_ids" value="${adminConfig.dept_ids}"/>
			<!-- 
			<input type="checkbox" onclick="(this);" name="is_inherit" id="is_inherit" value="Y"/>含子部门
			 -->
		</td>
    </tr>
    <tr>
        <td colspan="2" align="center" class="bottom_box">
            <input type="submit" value="修改" class="button_2003" onclick="return chk();">&nbsp;
            <input type="button" value="关闭" class="button_2003" onclick="window.close();">&nbsp;
        </td>
    </tr>
  	</table>
</form>
</center>
</body>
</html>
