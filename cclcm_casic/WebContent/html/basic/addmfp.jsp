<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>添加一体机</title>
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
		if($("#mfp_name").val().trim() == ""){
			alert("请填写一体机名称");
			$("#mfp_name").focus();
			return false;
		}
		if($("#dept_id").val().trim() == ""){
			alert("请选择所属部门");
			return false;
		}
		if($("#seclv_code").val().trim() == ""){
			alert("请选择密级");
			$("#seclv_code").focus();
			return false;
		}
		if($("#console_code").val().trim() == ""){
			alert("请选择控制台");
			$("#console_code").focus();
			return false;
		}
		if(!checkCode_addword($("#mfp_name").val())){
			$("#mfp_name").focus();
			alert("一体机名称只能由数字、字母、汉字或下划线组成，长度小于30位");
			return false;
		}
		if(!checkCode_addword($("#mfp_brand").val())){
			$("#mfp_brand").focus();
			alert("品牌只能由数字、字母、汉字或下划线组成，长度小于30位");
			return false;
		}
		if(!checkCode_addword($("#mfp_model").val())){
			$("#mfp_model").focus();
			alert("型号只能由数字、字母、汉字或下划线组成，长度小于30位");
			return false;
		}
		if(!checkCode_addword($("#mfp_location").val())){
			$("#mfp_location").focus();
			alert("地理位置只能由数字、字母、汉字或下划线组成，长度小于30位");
			return false;
		}
	    return true;
	}
	//-->
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<form method="post" action="${ctx}/basic/addmfp.action">
  	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="60%">
	 <tr>
		 <td colspan="2" class="title_box">
            	添加一体机
        </td>
    </tr>
    <tr>
    	<td align="center"><font color="red">*</font>一体机名称：</td>
		<td>
			<input type="text" name="mfp_name" id="mfp_name"/>
		</td>
    </tr>
    <tr>
    	<td align="center">品牌：</td>
		<td>
			<input type="text" name="mfp_brand" id="mfp_brand"/>
		</td>
    </tr>
    <tr>
    	<td align="center">型号：</td>
		<td>
			<input type="text" name="mfp_model" id="mfp_model"/>
		</td>
    </tr>
    <tr>
    	<td align="center">地理位置：</td>
		<td>
			<input type="text" name="mfp_location" id="mfp_location"/>
		</td>
    </tr>
    <tr>
    	<td align="center"><font color="red">*</font>所属部门：</td>
		<td>
			<input type="text" id="dept_name" readonly="readonly" style="cursor:hand" onclick="openDeptSelect('dept_name','dept_id','radio')" />
			<input type="hidden" name="dept_id" id="dept_id"/>
		</td>
    </tr>
    <tr>
    	<td align="center"><font color="red">*</font>密级：</td>
	    <td>
			<select name="seclv_code" id="seclv_code">
				<option value="">--请选择--</option>
				<s:iterator value="#request.seclvList" var="seclv">
					<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
				</s:iterator>
			</select>
		</td>
    </tr>
    <tr>
    	<td align="center"><font color="red">*</font>控制台：</td>
	    <td>
			<select name="console_code" id="console_code">
				<option value="">--请选择--</option>
				<s:iterator value="#request.consoleList" var="console">
					<option value="${console.console_code}">${console.console_name}</option>
				</s:iterator>
			</select>
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