<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看涉密人员类别信息</title>
<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" />
<script language="JavaScript" src="${ctx}/_script/function.js"></script>
<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
<script type="text/javascript">
<!--
	$(document).ready(function(){
		if(${isPrintEnable}){
			$("#tr_print").show();
		}
		if(${isCopyEnable}){
			$("#tr_copy").show();
		}
		if(${isLeadinEnable}){
			$("#tr_leadin").show();
		}
		if(${isBurnEnable}){
			$("#tr_burn").show();
			//$("#tr_copy_burn").show();
		}
		if(${isDeviceEnable}){
			$("#tr_device").show();
		}
	});
//-->
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
  	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="80%">
		<tr>
			 <td colspan="2" class="title_box">
	            	查看涉密人员类别信息
	        </td>
	    </tr>
	    <tr>
	    	<td align="center" width="15%">类别名称：</td>
			<td><font color="blue"><b>${security.security_name}</b></font></td>
	    </tr>
	    <tr>
	    	<td align="center">类别描述：</td>
			<td>
				<textarea cols="40" rows="3" readonly="readonly">${security.security_desc}</textarea>
			</td>
	    </tr>
	    <tr height="50" id="tr_print" style="display: none">
	    	<td align="center">可打印密级：</td>
			<td><font color="blue"><b>&nbsp;${security.print_name}</b></font></td>
		</tr>
		<tr height="50" id="tr_copy" style="display: none">
	    	<td align="center">可复印密级：</td>
			<td><font color="blue"><b>&nbsp;${security.copy_name}</b></font></td>
		</tr>
		<tr height="50" id="tr_leadin" style="display: none">
	    	<td align="center">可导入密级：</td>
			<td><font color="blue"><b>&nbsp;${security.import_name}</b></font></td>
		</tr>
		<tr height="50" id="tr_burn" style="display: none">
	    	<td align="center">可刻录密级：</td>
			<td><font color="blue"><b>&nbsp;${security.burn_name}</b></font></td>
		</tr>
		<tr height="50" id="tr_copy_burn" style="display: none">
	    	<td align="center">可复刻密级：</td>
			<td><font color="blue"><b>&nbsp;${security.copy_burn_name}</b></font></td>
	    </tr>
	    <tr height="50" id="tr_device" style="display: none">
	    	<td align="center">可借入磁介质密级：</td>
			<td><font color="blue"><b>&nbsp;${security.device_name}</b></font></td>
	    </tr>
	    <tr>
	        <td colspan="2" align="center" class="bottom_box">
	            <input type="button" value="返回" class="button_2003" onclick="javascript:history.go(-1);">
	        </td>
	    </tr>
  	</table>
</center>
</body>
</html>
