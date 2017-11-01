<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>修改客户端版本</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen"/>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<form method="post" action="${ctx}/client/viewcvsdetail.action">
	<input type="hidden" value="${sysCVS.cvs_code}" name="cvs_code"/>
   	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="60%">
	 <tr>
		 <td colspan="2" class="title_box">
            	设定客户端版本
        </td>
    </tr>
    <tr>
    	<td align="center"><font color="red">*</font>用户名：</td>
		<td>
		    <font color="blue"><b>&nbsp;${sysCVS.user_name}</b></font>
		</td>
    </tr>
    <tr>
    	<td align="center">计算机名：</td>
		<td>
		    <font color="blue"><b>&nbsp;${sysCVS.computer_name}</b></font>
		</td>
    </tr>
    <tr>
    	<td align="center">IP地址：</td>
		<td>
		    <font color="blue"><b>&nbsp;${sysCVS.ip_address}</b></font>
		</td>
    </tr>
    <tr>
    	<td align="center">软件类别：</td>
		<td>
		    <font color="blue"><b>&nbsp;${sysCVS.soft_code}</b></font>
		</td>
    </tr>
    <tr>
    	<td align="center">当前版本：</td>
		<td>
		    <font color="blue"><b>&nbsp;${sysCVS.curr_version}</b></font>
		</td>
    </tr>
    
    
    
      <tr>
    	<td align="center"><font color="red">*</font>初始版本：</td>
		<td>
		    <font color="blue"><b>&nbsp;${sysCVS.install_version}</b></font>
		</td>
    </tr>
    <tr>
    	<td align="center">安装时间：</td>
		<td>
		    <font color="blue"><b>&nbsp;${sysCVS.install_time_str}</b></font>
		</td>
    </tr>
    <tr>
    	<td align="center">上次更新时间：</td>
		<td>
		    <font color="blue"><b>&nbsp;${sysCVS.update_time_str}</b></font>
		</td>
    </tr>
    <tr>
    	<td align="center">操作系统版本：</td>
		<td>
		    <font color="blue"><b>&nbsp;${sysCVS.os_version}</b></font>
		</td>
    </tr>
    <tr>
    	<td align="center">上次在线时间：</td>
		<td>
		    <font color="blue"><b>&nbsp;${sysCVS.last_time_str}</b></font>
		</td>
    </tr>
    <tr>
    	<td align="center">是否在线：</td>
		<td>
		    <font color="blue"><b>&nbsp;${sysCVS.is_online_name}</b></font>
		</td>
    </tr>
    <tr>
    	<td align="center">卸载时间：</td>
		<td>
		    <font color="blue"><b>&nbsp;${sysCVS.uninstall_str}</b></font>
		</td>
    </tr>
    <tr>
    	<td align="center">设定客户端版本：</td>
		<td>
		    <font color="blue"><b>&nbsp;${sysCVS.set_version}</b></font>
		</td>
    </tr>
    <tr>
        <td colspan="2" align="center" class="bottom_box">
            <input type="button" value="关闭" class="button_2003" onclick="javascript:history.go(-1);">
        </td>
    </tr>
  	</table>
</form>
</center>
</body>
</html>