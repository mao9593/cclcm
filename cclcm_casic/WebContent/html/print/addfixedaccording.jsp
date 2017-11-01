<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>添加定密依据</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script  language="JavaScript" >
	<!--
	$(document).ready(function(){
		onHover();
		cleanAll();
	});
	function chk(){
		if($("#type").val().trim() == ""){
			alert("请选择类别");
			$("#type").focus();
			return false;
		}
		if($("#content").val().trim() == ""){
			alert("请填写定密内容");
			$("#content").focus();
			return false;
		}else if($("#content").val().length > 256){
			alert("定密内容数据过长，请重新输入");
			$("#content").focus();
			return false;
		}
		if($("#dept_name").val().trim() == ""){
			alert("请选择部门");
			$("#dept_name").focus();
			return false;
		}
	    return true;
	}
	//-->
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<form method="post" action="${ctx}/print/addfixedaccording.action" id="LedgerQueryCondForm">
  	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="60%">
	 <tr>
		 <td colspan="2" class="title_box">添加定密依据</td>
    </tr>
    <tr>
    	<td align="center"><font color="red">*</font>类型：</td>
		<td>
			<select id="type" name="type">
				<option value="1">定密依据</option>
				<option value="2">知悉范围</option>
				<option value="3">保密期限</option>
			</select>
		</td>
    </tr>
    <tr>
    	<td align="center">内容：</td>
		<td><textarea name="content" id="content" rows="5" cols="60"></textarea></td>
    </tr>
    <tr>
         <td align="center">为定密依据分配部门：</td>
         <td width="60%">
		       <textarea width="100%" rows="10" cols="60" id="dept_name" name="dept_name" readonly="readonly" onclick="openDeptSelect('dept_name','dept_id','checkbox')">${dept_names}</textarea>
		       <input type="hidden" name="dept_id" id="dept_id" value="${dept_ids}"/>
  		 </td>
    </tr>
    <tr>
        <td colspan="2" align="center" class="bottom_box">
            <input type="submit" value="添加" class="button_2003" onclick="return chk();">&nbsp;
            <input type="reset" value="重置" id='reset' class="button_2003">
            <input type="button" value="返回" class="button_2003" onclick="javascript:history.go(-1);">
        </td>
    </tr>
  	</table>
</form>
</center>
</body>
</html>