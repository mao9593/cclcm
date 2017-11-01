<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>设定控制台版本</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen"/>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<form method="post" action="${ctx}/client/viewconsoledetail.action">
	<input type="hidden" value="${sysConsole.console_code}" name="console_code"/>
   	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="60%">
	 <tr>
		 <td colspan="4" class="title_box">
            	设定控制台版本
        </td>
    </tr>
    <tr>
    	<td align="center"><font color="red">*</font>控制台代号：</td>
		<td><font color="blue"><b>${sysConsole.console_code}</b></font></td>   
		 	
    	<td align="center">当前版本：</td>
		<td><font color="blue"><b>${sysConsole.curr_version}</b>&nbsp;</font></td>	
    </tr>
    <tr>
    	<td align="center">控制台IP：</td>
		<td><font color="blue"><b>${sysConsole.console_ipaddr}</b>&nbsp;</font></td>  
		
    	<td align="center">控制台MAC地址：</td>
		<td><font color="blue"><b>${sysConsole.console_mac}</b>&nbsp;</font></td>
    </tr>
    <tr>
    	<td align="center">监控状态：</td>
		<td><font color="blue"><b>${sysConsole.console_status_name}</b>&nbsp;</font></td>
		
		<td align="center">是否在线：</td>
		<td><font color="blue"><b>${sysConsole.is_online_name}</b>&nbsp;</font></td>
    </tr>
    <tr>
    	<td align="center">安装时间：</td>
		<td><font color="blue"><b>${sysConsole.install_time_str}</b>&nbsp;</font></td>
		
		<td align="center">卸载时间：</td>
		<td><font color="blue"><b>${sysConsole.uninstall_time_str}</b>&nbsp;</font></td>
    </tr>
    <tr>
    	<td align="center">上次连接时间：</td>
		<td><font color="blue"><b>${sysConsole.last_connect_time_str}</b>&nbsp;</font></td>
		<td align="center">控制台位置：</td>
		<td><font color="blue"><b>${sysConsole.console_location}</b>&nbsp;</font></td>
    </tr>
     <tr>
    	<td align="center"><font color="red">*</font>控制台名称：</td>
		<td><font color="blue"><b>${sysConsole.console_name}</b>&nbsp;</font></td>
		<td align="center"><font color="red">*</font>控制台密级：</td>
		<td><font color="blue"><b>${sysConsole.seclv_name}</b>&nbsp;</font></td>
    </tr>
      <tr>
    	<td align="center"><font color="red">*</font>硬件类型：</td>
		<td><font color="blue"><b>${sysConsole.hardware_type}</b>&nbsp;</font></td>
		<td align="center"><font color="red">*</font>控制台类型：</td>
		<td><font color="blue"><b>${sysConsole.console_type_name}</b>&nbsp;</font></td>
    </tr>
    <tr>
        <td colspan="4" align="center" class="bottom_box">
            <input type="button" value="关闭" class="button_2003" onclick="javascript:history.go(-1);">
        </td>
    </tr>
  	</table>
</form>
</center>
</body>
</html>