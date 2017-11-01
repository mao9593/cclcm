<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>磁介质详细信息</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen"/>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<form method="post" action="${ctx}/device/updatedevice.action">
	<input type="hidden" value="${device.device_code}" name="device_code"/>
   	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="60%">
	 <tr>
		 <td colspan="2" class="title_box">
            	磁介质详细信息
        </td>
    </tr>
    <tr>
    	<td align="center">介质名称：</td>
    	<td><font color="blue"><b>&nbsp;${device.device_name}</b></font></td>
    </tr>
    <tr>
    	<td align="center">介质类型：</td>
    	<td><font color="blue"><b>&nbsp;${device.med_type_name}</b></font></td>
    </tr>
    <tr>
    	<td align="center">所属部门：</td>
    	<td><font color="blue"><b>&nbsp;${device.dept_name}</b></font></td>
    </tr>    
    <tr>
    	<td align="center">条码号：</td>
    	<td><font color="blue"><b>&nbsp;${device.device_barcode}</b></font></td>
    </tr>    
    <tr>
    	<td align="center">密级：</td>
    	<td><font color="blue"><b>&nbsp;${device.seclv_name}</b></font></td>
    </tr>
    <tr>
    	<td align="center">责任人：</td>
    	<td><font color="blue"><b>&nbsp;${device.duty_user_name}</b></font></td>
    </tr>    
    <tr>
    	<td align="center">借用人：</td>
    	<td><font color="blue"><b>&nbsp;${device.borrow_user_name}</b></font></td>
    </tr>
    <tr>
    	<td align="center">介质状态：</td>
    	<td><font color="blue"><b>&nbsp;${device.device_status_name}</b></font></td>
    </tr>   
    <tr>
    	<td align="center">设备编号：</td>   	
		<td><font color="blue"><b>&nbsp;${device.device_series}</b></font></td>
    </tr>
    <tr>
    	<td align="center">型号：</td>
    	<td><font color="blue"><b>&nbsp;${device.device_model}</b></font></td>
    </tr>
    <tr>
    	<td align="center">设备配置：</td>
    	<td><font color="blue"><b>&nbsp;${device.device_detail}</b></font></td>
    </tr> 
    <tr>
    	<td align="center">录入员：</td>
    	<td><font color="blue"><b>&nbsp;${device.user_name}</b></font></td>
    </tr> 
    <tr>
    	<td align="center">登记时间：</td>
    	<td><font color="blue"><b>&nbsp;${device.enter_time_str}</b></font></td>
    </tr>
    
    <tr>
    	<td align="center">说明：</td>
    	<td><font color="blue"><b>&nbsp;${device.med_content}</b></font></td>
    </tr> 
    <tr>
        <td colspan="2" align="center" class="bottom_box">
            <input type="button" value="返回" class="button_2003" onclick="history.go(-1)">&nbsp;
        </td>
    </tr>
  	</table>
</form>
</center>
</body>
</html>