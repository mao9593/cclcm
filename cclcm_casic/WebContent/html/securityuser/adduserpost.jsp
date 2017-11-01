<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>添加岗位</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script  language="JavaScript" >
	<!--
	$(document).ready(function(){
		onHover();
	});
	function chk()
	{
		if($("#post_id").val().trim() == ""){
			alert("请填写岗位特征值");
			$("#post_id").focus();
			return false;
		}
		if($("#post_name").val().trim() == ""){
			alert("请填写岗位名称");
			$("#post_name").focus();
			return false;
		}
		if($("#dept_ids").val().trim() == ""){
			alert("请双击选择适用部门");
			$("#dept_ids").focus();
			return false;
		}
		if($("#post_desc").val().length > 1024){
			alert("岗位说明不能超过1024个字符");
			$("#post_desc").focus();
			return false;
		}
		if(!checkCode($("#post_id").val())){
			alert("岗位特征值只能由数字，字母或下划线组成，长度小于30位");
			$("#post_id").focus();
			return false;
		}
	    return true;
	}
	//-->
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<form method="post" action="${ctx}/securityuser/adduserpost.action">
  	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="60%">
	 <tr>
		 <td colspan="4" class="title_box">
            	添加岗位
        </td>
    </tr>
    <tr>
    	<td align="center"><font color="red">*</font>岗位特征值</td>
		<td>
			<input type="text" name="post_id" id="post_id"/>
		</td>
		<td align="center"><font color="red">*</font>岗位名称</td>
		<td>
			<input type="text" name="post_name" id="post_name"/>
		</td>
    </tr>
    <tr>
    	<td align="center"><font color="red">*</font>岗位排序</td>
		<td>
			<input type="text" name="post_level" id="post_level"/>
		</td>
		<td align="center"><font color="red">*</font>岗位等级</td>
		<td>
			<input type="text" name="post_class" id="post_class"/>
		</td>
    </tr>
    <tr>
    	<td align="center"><font color="red">*</font>&nbsp;适用部门</td>
	    <td>
			<input type="text" id="dept_name" value="${userpost.dept_name}" readonly="readonly" style="cursor:hand" onclick="openDeptSelect('dept_name','dept_ids','checkbox')" />
			<input type="hidden" name="dept_ids" id="dept_ids" value="${userpost.dept_id}"/>
		</td>
    	<td align="center">岗位描述</td>
		<td>
			<textarea rows="3" cols="20" name="post_desc" id="post_desc"></textarea>
		</td>
    </tr>
    <tr>
        <td colspan="4" align="center" class="bottom_box">
            <input type="submit" value="添加" class="button_2003" onclick="return chk();">&nbsp;
            <input type="reset" value="重置" class="button_2003">
            <input type="button" value="返回" class="button_2003" onclick="javascript:history.go(-1);">
        </td>
    </tr>
  	</table>
</form>
</center>
</body>
</html>