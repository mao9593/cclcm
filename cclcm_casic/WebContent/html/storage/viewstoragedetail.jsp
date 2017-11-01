<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>查看存储介质详细信息</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<form method="post" action="${ctx}/storage/addstorage.action">
  	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="60%">
	 <tr>
		 <td colspan="2" class="title_box">
            	存储介质详细信息
        </td>
    </tr>
    <tr>
    	<td width="35%" align="center">介质名称：</td>
		<td width="65%"><font color="blue"><b>${storage.storage_name}</b></font></td>
    </tr>   						
    <tr>
    	<td align="center">所属部门：</td>
		<td><font color="blue"><b>&nbsp;${storage.dept_name}</b></font></td>
    </tr>
    <tr>
    	<td align="center">介质类型：</td>
    	<td><font color="blue"><b>&nbsp;${storage.med_type_name}</b></font></td>
    </tr>
    <tr>
    	<td align="center">介质条码：</td>
    	<td><font color="blue"><b>&nbsp;${storage.storage_barcode}</b></font></td>
    </tr>
    <tr>
    	<td align="center">介质状态：</td>
    	<td><font color="blue"><b>&nbsp;${storage.storage_status_name}</b></font></td>
    </tr>
    <tr>
    	<td align="center">密级：</td>
	    <td><font color="blue"><b>&nbsp;${storage.seclv_name}</b></font></td>
    </tr>
    <tr>
    	<td align="center">登记时间：</td>
    	<td><font color="blue"><b>&nbsp;${storage.input_time_str}</b></font></td>
    </tr>
    <tr>
    	<td align="center">录入人：</td>
    	<td><font color="blue"><b>&nbsp;${storage.input_user_name}</b></font></td>
    </tr>
    <tr>
    	<td align="center">责任人：</td>
    	<td><font color="blue"><b>&nbsp;${storage.duty_user_name}</b></font></td>
    </tr>
    <tr>
    	<td align="center">当前使用人：</td>
    	<td><font color="blue"><b>&nbsp;${storage.use_user_name}</b></font></td>
    </tr>
    <tr>
    	<td align="center">设备编号：</td>
    	<td><font color="blue"><b>&nbsp;${storage.storage_series}</b></font></td>
    </tr>
    <tr>
    	<td align="center">型号：</td>
		<td><font color="blue"><b>&nbsp;${storage.storage_model}</b></font></td>
    </tr>
    <tr>
    	<td align="center">设备配置：</td>
		<td>
			<textarea cols="60" rows="3" name="storage_detail" id="storage_detail" readonly="readonly">${storage.storage_detail}</textarea>		
		</td>
    </tr>
    <tr>
        <td colspan="2" align="center" class="bottom_box">
            <input type="button" value="返回" class="button_2003" onclick="history.go(-1);">
        </td>
    </tr>
  	</table>
</form>
</center>
</body>
</html>