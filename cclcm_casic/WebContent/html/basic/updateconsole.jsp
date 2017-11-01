<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>修改控制台</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen"/>
	<link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
	<script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script  language="JavaScript" >
	<!--
	$(document).ready(function(){
		init();	
	});
	function init(){
		var consoleTypeIndex = "${console.console_type}";
		var secIndex = ${console.seclv_code};
		var hardwareIndex = "${console.hardware_type}";
		var allowLevel = "${console.allowSecLevel}";
		$(":checkbox[name='define_value']").each(function(){
			if(allowLevel.indexOf(this.value) > -1){
				this.checked = true;
			}
		});
		$(":checkbox[name='console_type']").each(function(){
			if(consoleTypeIndex.indexOf(this.value) > -1){
				this.checked = true;
			}
		});
		$("#seclv_med").val(secIndex);
		$("#hardware_type").val(hardwareIndex);
	}
	
	function chk()
	{
		if($("#console_name").val().trim() == ""){
			alert("请填写控制台名称");
			$("#console_name").focus();
			return false;
		}
		if($("#seclv_med").val().trim() == ""){
			alert("请填写控制台密级");
			$("#seclv_med").focus();
			return false;
		}
		var sec = $("#seclv_med").val();
		var check = true;
		$(":checkbox[name=define_value]:checked").nextAll().each(function(){
			if(this.value == sec){
				this.focus();
				check = false;
			}
		});
		if($(":checkbox[name=define_value]:checked").size() == 0){
			alert("请选择可设定密级");
			return false;
		}
		if($(":checkbox[name=console_type]:checked").size() == 0){
			alert("请选择控制台类型");
			return false;
		}
		if(!checkCode_addword($("#console_name").val())){
			alert("控制台名称只能由数字、字母、汉字或下划线组成，长度小于30位");
			$("#console_name").focus();
			return false;
		}
		if(!check){
			alert("可设定密级不能超过控制台密级。");
			return false;
		}
	    return true;
	}
	//-->
	</script>
</head>
<body oncontextmenu="self.event.returnValue=true">
<center>
<form method="post" action="${ctx}/basic/updateconsole.action">
<%-- 	<input type="hidden" value="${console.console_code}" name="console_code"/> --%>
	<input type="hidden" value="Y" name="update"/>
   	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="90%">
	 <tr>
		 <td colspan="4" class="title_box">
            	修改控制台
        </td>
    </tr>
    <tr>
    	<td align="center"><font color="red">*</font>控制台代号：</td>
		<%-- <td><font color="blue"><b>${console.console_code}</b></font></td> --%>   
		<td>
		<input type="text" name="new_console_code" maxlength="15" id="new_console_code" value="${console.console_code}"/>
			<input type="hidden" name="console_code" maxlength="15" id="console_code" value="${console.console_code}"/>
		</td>
		 	
    	<td align="center">当前版本：</td>
		<td><font color="blue"><b>${console.curr_version}</b>&nbsp;</font></td>		    	
    </tr>
    <tr>
    	<td align="center">控制台IP：</td>
		<td><font color="blue"><b>${console.console_ipaddr}</b>&nbsp;</font></td>  
		
    	<td align="center">控制台MAC地址：</td>
		<td><font color="blue"><b>${console.console_mac}</b>&nbsp;</font></td>
    </tr>
    <tr>
    	<td align="center">监控状态：</td>
		<td><font color="blue"><b>${console.console_status_name}</b>&nbsp;</font></td>
		
		<td align="center">是否在线：</td>
		<td><font color="blue"><b>${console.is_online_name}</b>&nbsp;</font></td>
    </tr>
    <tr>   
    	<td align="center">安装时间：</td>
		<td><font color="blue"><b>${console.install_time_str}</b>&nbsp;</font></td>
		
		<td align="center">卸载时间：</td>
		<td><font color="blue"><b>${console.uninstall_time_str}</b>&nbsp;</font></td>
    </tr>
    <tr>
    	<td align="center">上次连接时间：</td>
		<td><font color="blue"><b>${console.last_connect_time_str}</b>&nbsp;</font></td>
		<td>&nbsp;</td><td>&nbsp;</td>
    </tr>   
    <tr>
    <td align="center"><font color="red">*</font>控制台名称：</td>
		<td>
			<input type="text" name="console_name" maxlength="15" id="console_name" value="${console.console_name}"/>
		</td>
    
    	<td align="center"><font color="red">*</font>控制台密级：</td>
		<td>
		<select name="seclv_code" id="seclv_med">
				<option value="">--请选择--</option>
				<s:iterator value="#request.seclvList" var="seclv">
					<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
				</s:iterator>
			</select>
		</td>
    </tr>
    <tr>
    	<td align="center">控制台位置：</td>
		<td>
			<input type="text" name="console_location" maxlength="30" id="console_location" value="${console.console_location}"/>
		</td>
    
    	<td align="center">设定版本：</td>
		<td>
			<input type="text" name="set_version" maxlength="30" id="set_version" value="${console.set_version}"/>
		</td>
	</tr>
    <tr>
    	<td align="center"><font color="red">*</font>硬件类型：</td>
		<td colspan="3">
		<select name="hardware_type" id="hardware_type">
    			<option value="PC">PC电脑</option>
    			<option value="平板">平板终端</option>
    	</select>
		</td>    	
    </tr>
    <tr height="50">
	    <td align="center"><font color="red">*</font>可设定密级：</td>
		<td colspan="3">
			<s:iterator value="#request.seclvList" var="seclv">
				&nbsp;&nbsp;<input type="checkbox" value="${seclv.seclv_code}" name="define_value"/>${seclv.seclv_name}
			</s:iterator>
		</td>
	</tr>
	<tr height="50">
	    <td align="center"><font color="red">*</font>控制台类型：</td>
		<td colspan="3">&nbsp;&nbsp;			
			<c:if test="${is_print}">
				<input type="checkbox" value="PRINT" name="console_type"/>打印&nbsp;&nbsp;
			</c:if>
			<c:if test="${is_burn}">
				<input type="checkbox" value="BURN" name="console_type"/>刻录&nbsp;&nbsp;
			</c:if>
			<c:if test="${file_confirm}"> 				
				<input type="checkbox" value="FILE" name="console_type"/>归档&nbsp;&nbsp;								
			</c:if>
			<%-- <c:if test="${retrieve_confirm}"> 				
				<input type="checkbox" value="RETRIEVE" name="console_type"/>回收&nbsp;&nbsp;								
			</c:if> --%>
			<c:if test="${is_transfer and transfer_confirm}">
				<input type="checkbox" value="TRANSFER" name="console_type"/>流转&nbsp;&nbsp;
			</c:if>
			<c:if test="${is_borrow and read_br_confirm}"> 				
				<input type="checkbox" value="READ_BR" name="console_type"/>借阅文件&nbsp;&nbsp;	
			</c:if>
			<c:if test="${is_borrow and read_rt_confirm}">			
				<input type="checkbox" value="READ_RT" name="console_type"/>归还文件&nbsp;&nbsp;
			</c:if>	
			<c:if test="${is_device and device_br_confirm}">
				<input type="checkbox" value="DEVICE_BR" name="console_type"/>借用磁介质&nbsp;&nbsp;
			</c:if>
			<c:if test="${is_device and device_rt_confirm}">
				<input type="checkbox" value="DEVICE_RT" name="console_type"/>归还磁介质&nbsp;&nbsp;
			</c:if>
			<%-- <c:if test="${is_storage and storage_br_confirm}">
				<input type="checkbox" value="STORAGE_BR" name="console_type"/>分配存储介质&nbsp;&nbsp;
			</c:if>
			<c:if test="${is_storage and storage_rt_confirm}">
				<input type="checkbox" value="STORAGE_RT" name="console_type"/>归还存储介质
			</c:if> --%>
		</td>
	</tr>
    <tr>
        <td colspan="4" align="center" class="bottom_box">
            <input type="submit" value="修改" class="button_2003" onclick="return chk();">&nbsp;
            <input type="button" value="返回" class="button_2003" onclick="javascript:history.go(-1);">
        </td>
    </tr>
  	</table>
</form>
</center>
</body>
</html>