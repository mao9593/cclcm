<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>添加用途</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script  language="JavaScript" >
	
	$(document).ready(function(){
		onHover();
	});
	function chk()
	{
		if($("#usage_code").val().trim() == ""){
			alert("请填写用途特征值");
			$("#usage_code").focus();
			return false;
		}
		if($("#usage_name").val().trim() == ""){
			alert("请填写用途名称");
			$("#usage_name").focus();
			return false;
		}
		if($("#usage_content").val().length > 1024){
			alert("用途说明不能超过1024个字符");
			$("#usage_content").focus();
			return false;
		}
		if(!checkCode($("#usage_code").val())){
			alert("用途特征值只能由数字，字母或下划线组成，长度小于30位");
			$("#usage_code").focus();
			return false;
		}
		if(!checkCode_addword($("#usage_name").val())){
			alert("用途名称只能由数字、字母、汉字或下划线组成，长度小于30位");
			$("#usage_name").focus();
			return false;
		}
	    return true;
	}
	//-->
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<form method="post" action="${ctx}/basic/addusage.action">
  	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="60%">
	 <tr>
		 <td colspan="2" class="title_box">
            	添加用途
        </td>
    </tr>
    <tr>
    	<td align="center"><font color="red">*</font>用途特征值：</td>
		<td>
			<input type="text" name="usage_code" id="usage_code"/>
		</td>
    </tr>
    <tr>
    	<td align="center"><font color="red">*</font>用途名称：</td>
		<td>
			<input type="text" name="usage_name" id="usage_name"/>
		</td>
    </tr>
    <tr height="35">
	    <td align="center">适用模块：</td>
		<td colspan="3">		
			<c:if test="${isPrintEnable}">
				<input type="checkbox" value="PRINT" name="module_code"/>打印&nbsp;&nbsp;
			</c:if>
			<c:if test="${isBurnEnable}">
				<input type="checkbox" value="BURN" name="module_code"/>刻录&nbsp;&nbsp;
			</c:if>
			<c:if test="${isCopyEnable}"> 				
				<input type="checkbox" value="COPY" name="module_code"/>复印&nbsp;&nbsp;								
			</c:if>
			<c:if test="${isLeadinEnable}"> 				
				<input type="checkbox" value="LEADIN" name="module_code"/>录入&nbsp;&nbsp;	
			</c:if>
			<c:if test="${isTransferEnable}">
				<input type="checkbox" value="TRANSFER" name="module_code"/>流转&nbsp;&nbsp;
			</c:if>
			<c:if test="${isDeviceEnable}">
				<input type="checkbox" value="DEVICE" name="module_code"/>磁介质&nbsp;&nbsp;
			</c:if>
			<c:if test="${isStorageEnable}">
				<input type="checkbox" value="STORAGE" name="module_code"/>存储介质&nbsp;&nbsp;
			</c:if>
			<c:if test="${isBorrowEnable}">			
				<input type="checkbox" value="BORROW" name="module_code"/>部门载体借用&nbsp;&nbsp;
			</c:if>	
			<c:if test="${isChangeEnable}">
				<input type="checkbox" value="CHANGE" name="module_code"/>载体归属转换&nbsp;&nbsp;
			</c:if>
			<c:if test="${isInputEnable}">
				<input type="checkbox" value="INPUT" name="module_code"/>电子文件导入&nbsp;&nbsp;
			</c:if>
		</td>
	</tr>
    <tr>
    	<td align="center">用途说明：</td>
		<td>
			<textarea rows="3" cols="60" name="usage_content" id="usage_content"></textarea>
		</td>
    </tr>
    <tr>
        <td colspan="2" align="center" class="bottom_box">
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