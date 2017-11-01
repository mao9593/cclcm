<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>修改计算机</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script  language="JavaScript" >
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<form method="post" action="${ctx}/computer/viewcomputerdetail.action">
  	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="95%">
	 <tr>
		 <td colspan="6" class="title_box"> 
            	查看计算机 
        </td>
    </tr>
    <input type="hidden" name="user_iidd" id="user_iidd" value="${curUser.user_iidd}"/>
     <tr>
    	<td width="10%" align="center"><font color="red">*</font>计算机名称型号</td>
    	<td width="18%"><font color="blue"><b>&nbsp;${computer.computer_name}</b></font></td>
		<td width="10%" align="center">设备类型</td>
		<td width="15%"><font color="blue"><b>&nbsp;${computer.med_type_name}</b></font></td>
		<td width="10%" align="center">原保密编号</td>
		<td width="15%"><font color="blue"><b>&nbsp;${computer.oldconf_code}</b></font></td>
    </tr>  
    <tr>
    	<td align="center">安全产品安装情况</td>
    	<td><font color="blue"><b>&nbsp;${computer.software_type}</b></font></td>
		<td align="center">密级</td>
		<td colspan="3"><font color="blue"><b>&nbsp;${computer.seclv_name}</b></font></td>
	</tr>
	<tr>
    	<td align="center">硬盘序列号</td>
    	<td><font color="blue"><b>&nbsp;${computer.hdisk_no}</b></font></td>
    	<td align="center">资产编号</td>
    	<td><font color="blue"><b>&nbsp;${computer.computer_code}</b></font></td>
		<td align="center">保密编号</td>
		<td><font color="blue"><b>&nbsp;${computer.conf_code}</b></font></td>
    </tr> 
    <tr>
    	<td align="center">责任人</td>
    	<td><font color="blue"><b>&nbsp;${computer.duty_user_name}</b></font></td>
		<td align="center">责任部门</td> 
		<td><font color="blue"><b>&nbsp;${computer.duty_dept_name}</b></font></td>
		<td align="center">责任单位</td>
		<td><font color="blue"><b>&nbsp;${computer.duty_entp_name}</b></font></td>
    </tr> 
    <tr>
    	<td align="center">操作系统</td>
    	<td><font color="blue"><b>&nbsp;${computer.computer_os}</b></font></td>
		<td align="center">操作系统安装时间</td>
		<td><font color="blue"><b>&nbsp;${computer.install_time_str}</b></font></td>
		<td align="center">KEY编号</td>
		<td><font color="blue"><b>&nbsp;${computer.key_code}</b></font></td>
    </tr> 
    <tr>
    	<td align="center">IP</td>
    	<td><font color="blue"><b>&nbsp;${computer.computer_ip}</b></font></td>
		<td align="center">掩码</td>
		<td><font color="blue"><b>&nbsp;${computer.mark_code}</b></font></td>
		<td align="center">MAC</td>
		<td><font color="blue"><b>&nbsp;${computer.computer_mac}</b></font></td>
	</tr> 
    
    <tr>
		<td align="center">输入输出端口</td>
		<td><font color="blue"><b>&nbsp;${computer.output_point}</b></font></td>
		<td align="center">VALN</td>
		<td><font color="blue"><b>&nbsp;${computer.vlan_num}</b></font></td>
		<td align="center">网关</td>
		<td><font color="blue"><b>&nbsp;${computer.computer_gateway}</b></font></td>
    </tr> 
    <tr>
		<td align="center">交换机</td>
		<td><font color="blue"><b>&nbsp;${computer.switch_num}</b></font></td>
		<td align="center">端口号</td>
		<td><font color="blue"><b>&nbsp;${computer.switch_point}</b></font></td>
		<td align="center">网络类型</td>
		<td><font color="blue"><b>&nbsp;${computer.internet_type_name}</b></font></td>
    </tr> 
     <tr>
    	<td align="center">计算机状态</td>
    	<td><font color="blue"><b>&nbsp;${computer.computer_status_name}</b></font></td>
		<td align="center">存放区域</td>
		<td><font color="blue"><b>&nbsp;${computer.storage_area}</b></font></td>
		<td align="center">存放位置</td>
		<td><font color="blue"><b>&nbsp;${computer.storage_location}</b></font></td>
	</tr> 
     <tr>
     	<td align="center">录入时间</td>
		<td><font color="blue"><b>&nbsp;${computer.enter_time_str}</b></font></td>
		<td align="center">备注</td>
		<td colspan="3"><font color="blue"><b>&nbsp;${computer.summ}</b></font></td>
    </tr> 
</table>
</br>
<c:if test="${not empty itemList}">
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
	<tr>
	    <td colspan="6" class="title_box">载体全生命周期信息</td>
	</tr>
	<tr>
    	<td width="20%" align="center">载体条码 </td>
    	<td width="20%" align="center">操作人</td>
    	<td width="20%" align="center">部门</td>
    	<td width="20%" align="center">操作时间 </td>
    	<td width="10%" align="center">操作 </td>
    	<td width="10%" align="center">详细 </td>
	</tr>
	<s:iterator value="#request.itemList" var="item">
		<tr>
	    	<td align="center">${item.barcode}</td>
	    	<td align="center">${item.user_name}</td>
	    	<td align="center">${item.dept_name}</td>
	    	<td align="center">${item.oper_time_str}</td>
	    	<td align="center">${item.oper.name}</td>
	    	<c:choose>

				<c:when test="${item.type == 'infosystem'}">
					<td align="center"><input type="button" class="button_2003" value="查看详情" onclick="go('${ctx}/infosystem/approveinfosystemjob.action?history=Y&job_code=${item.job_code}');"/></td>
				</c:when>
				<c:when test="${item.type == 'computer'}">
					<td align="center"><input type="button" class="button_2003" value="查看详情" onclick="go('${ctx}/computer/approvecomputerjob.action?history=Y&job_code=${item.job_code}');"/></td>
				</c:when>
				<c:otherwise>
					<td align="center">无详细信息</td>
				</c:otherwise>
			</c:choose>		
		</tr>	
	</s:iterator>
</table>
</c:if>
<br>
<table> 
    <tr>
        <td colspan="6" align="center">
            <input type="button" value="返回" class="button_2003" onclick="history.go(-1);">
        </td>
    </tr>
</table>
</form>
</center>
</body>
</html>