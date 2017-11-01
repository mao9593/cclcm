<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>配置涉密人员类别</title>
<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" />
<script language="JavaScript" src="${ctx}/_script/function.js"></script>
<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
<script  language="JavaScript" >
<!--
$(document).ready(function(){
	onHover();
	$("#security_name").val("${security.security_name}");
	$("#security_desc").val("${security.security_desc}");
	//没有复刻功能
	$("#tr_copy_burn").hide();
	if(${isPrintEnable}){
		$(":checkbox[name='print_value']").each(function(){
			var print_value="${security.print_value}";
			if(print_value.indexOf("|"+this.value+"|") != -1){
				this.checked=true;
			}
		});
	}else{
		$("#tr_print").hide();
	}
	if(${isCopyEnable}){
		$(":checkbox[name='copy_value']").each(function(){
			var copy_value="${security.copy_value}";
			if(copy_value.indexOf("|"+this.value+"|") != -1){
				this.checked=true;
			}
		});
	}else{
		$("#tr_copy").hide();
	}
	if(${isLeadinEnable}){
		$(":checkbox[name='import_value']").each(function(){
			var import_value="${security.import_value}";
			if(import_value.indexOf("|"+this.value+"|") != -1){
				this.checked=true;
			}
		});
	}else{
		$("#tr_leadin").hide();
	}
	if(${isBurnEnable}){
		$(":checkbox[name='burn_value']").each(function(){
			var burn_value="${security.burn_value}";
			if(burn_value.indexOf("|"+this.value+"|") != -1){
				this.checked=true;
			}
		});
		/*$(":checkbox[name='copy_burn_value']").each(function(){
			var copy_burn_value="${security.copy_burn_value}";
			if(copy_burn_value.indexOf("|"+this.value+"|") != -1){
				this.checked=true;
			}
		});*/
	}else{
		$("#tr_burn").hide();
	}
	
	if(${isDeviceEnable}){
		$(":checkbox[name='device_value']").each(function(){
			var device_value="${security.device_value}";
			if(device_value.indexOf("|"+this.value+"|") != -1){
				this.checked=true;
			}
		});
	}else{
		$("#tr_device").hide();
	}
});
function chk()
{	
	if($("#security_name").val().trim() == "")
    {
        alert("请填写涉密人员类别名称");
        $("#security_name").focus();
        return false;
    }
	if(!specialCharFilter($("#security_name").val())){
		alert("类别名称包含特殊字符");
		$("#security_name").focus();
        return false;
	}
    return true;
}
//-->
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<form method="post" action="${ctx}/user/configsecurity.action" id="ConfirSecurityForm">
	<input type="hidden" name="security_code" value="${security.security_code}"/>
	<input type="hidden" name="config" value="Y"/>
  	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="80%">
		<tr>
			 <td colspan="2" class="title_box">
	            	配置涉密人员类别
	        </td>
	    </tr>
	    <tr>
	    	<td align="center" width="15%"><font color="red">*</font>类别名称：</td>
			<td>
				<input type="text" name="security_name" id="security_name"/>
			</td>
	    </tr>
	    <tr>
	    	<td align="center">类别描述：</td>
			<td>
				<textarea name="security_desc" id="security_desc" cols="40" rows="3"></textarea>&nbsp;
			</td>
	    </tr>
	    <tr height="50" id="tr_print">
	    	<td align="center">可打印密级：</td>
			<td>
				<s:iterator value="#request.seclvList" var="seclv">
					&nbsp;&nbsp;<input type="checkbox" value="${seclv.seclv_code}" name="print_value"/>${seclv.seclv_name}
				</s:iterator>
			</td>
		</tr>
		<tr height="50" id="tr_copy">
	    	<td align="center">可复印密级：</td>
			<td>
				<s:iterator value="#request.seclvList" var="seclv">
					&nbsp;&nbsp;<input type="checkbox" value="${seclv.seclv_code}" name="copy_value"/>${seclv.seclv_name}
				</s:iterator>
			</td>
		</tr>
		<tr height="50" id="tr_leadin">
	    	<td align="center">可导入密级：</td>
			<td>
				<s:iterator value="#request.seclvList" var="seclv">
					&nbsp;&nbsp;<input type="checkbox" value="${seclv.seclv_code}" name="import_value"/>${seclv.seclv_name}
				</s:iterator>
			</td>
		</tr>
		<tr height="50" id="tr_burn">
	    	<td align="center">可刻录密级：</td>
			<td>
				<s:iterator value="#request.seclvList" var="seclv">
					&nbsp;&nbsp;<input type="checkbox" value="${seclv.seclv_code}" name="burn_value"/>${seclv.seclv_name}
				</s:iterator>
			</td>
		</tr>
		<tr height="50" id="tr_copy_burn">
	    	<td align="center">可复刻密级：</td>
			<td>
				<s:iterator value="#request.seclvList" var="seclv">
					&nbsp;&nbsp;<input type="checkbox" value="${seclv.seclv_code}" name="copy_burn_value"/>${seclv.seclv_name}
				</s:iterator>
			</td>
	    </tr>
	    <tr height="50" id="tr_device">
	    	<td align="center">可借入磁介质密级：</td>
			<td>
				<s:iterator value="#request.seclvList" var="seclv">
					&nbsp;&nbsp;<input type="checkbox" value="${seclv.seclv_code}" name="device_value"/>${seclv.seclv_name}
				</s:iterator>
			</td>
	    </tr>
	    <tr>
	        <td colspan="2" align="center" class="bottom_box">
	            <input type="submit" value="确定" class="button_2003" onclick="return chk();">&nbsp;
	            <input type="button" value="返回" class="button_2003" onclick="history.go(-1);"/>&nbsp;
	        </td>
	    </tr>
  	</table>
</form>
</center>
</body>
</html>
