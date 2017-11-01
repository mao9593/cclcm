<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
	<link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
	<script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script> 
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script  language="JavaScript" >

	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<form method="post" action="${ctx}/computer/viewinfodevicedetail.action">
<input type="hidden" name="user_iidd" id="user_iidd" value="${curUser.user_iidd}"/>
<input type="hidden" name="device_barcode" id="device_barcode" value="${device.device_barcode}"/>
  	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="80%">
	 <tr>
		 <td colspan="6" class="title_box">查看信息设备台账详细</td>
    </tr>
<%--     <tr>
    	<td width="15%" align="center" >录入员</td>
		<td width="20%"><font color="blue"><b>&nbsp;${curUser.user_name}</b></font></td>
		<td width="15%" align="center">录入员部门</td>
		<td width="20%"><font color="blue"><b>&nbsp;${curUser.dept_name}</b></font></td>
	</tr>  --%> 
 	<tr>
		<td width="10%" align="center">责任人</td>
		<td width="15%"><font color="blue"><b>&nbsp;${device.duty_user_name}</b></font></td>
		<td width="10%" align="center">责任人部门</td>
		<td width="15%"><font color="blue"><b>&nbsp;${device.duty_dept_name}</b></font></td>
		<td width="10%" align="center">单位</td>
    	<td width="15%"><font color="blue"><b>&nbsp;${device.company}</b></font></td>
    </tr>  
    <tr>
    	<td align="center">设备类型</td>
		<td><font color="blue"><b>&nbsp;${device.device_type_name}</b></font></td>
		<td align="center">子类型</td>
		<td><font color="blue"><b>&nbsp;${device.info_type}</b></font></td>
		<td align="center">设备状态</td>
		<td><font color="blue"><b>&nbsp;${device.device_statues_name}</b></font></td>
	</tr>     
    <tr>
    	<td align="center">设备密级</td>
    	<td><font color="blue"><b>&nbsp;${device.seclv_name}</b></font></td>
		<td align="center">原保密编号</td>
		<td><font color="blue"><b>&nbsp;${device.oldconf_code}</b></font></td>
    	<td align="center">保密编号</td>
    	<td><font color="blue"><b>&nbsp;${device.conf_code}</b></font></td>
	</tr>  
 	<tr>
		<td align="center">品牌类型</td>
		<td><font color="blue"><b>&nbsp;${device.brand_type}</b></font></td>
		<td align="center">资产编号</td>
		<td><font color="blue"><b>&nbsp;${device.device_series}</b></font></td>		
		<td align="center">设备用途</td>
		<td><font color="blue"><b>&nbsp;${device.device_usage}</b></font></td>
    </tr>  
 	 <tr>
    	<td align="center">采购时间</td>
    	<td><font color="blue"><b>&nbsp;${device.purchase_time_str}</b></font></td>
		<td align="center">启用时间</td>
		<td><font color="blue"><b>&nbsp;${device.use_time_str}</b></font></td>
		<td align="center">安装地点</td>
		<td><font color="blue"><b>&nbsp;${device.location}</b></font></td>
	</tr>  
	<c:if test="${device.device_type == '5'}">
	 	<tr>
			<td align="center">检测证书名称</td>
			<td><font color="blue"><b>&nbsp;${device.cert_name}</b></font></td>
			<td align="center">证书编号</td>
			<td colspan="3"><font color="blue"><b>&nbsp;${device.cert_num}</b></font></td>
	    </tr> 
	</c:if>
     <tr>   	
		<td align="center">型号</td>
		<td><font color="blue"><b>&nbsp;${device.device_version}</b></font></td>
		<td align="center">备注</td>
		<td colspan="3"><font color="blue"><b>&nbsp;${device.detail}</b></font></td>
		<%-- <td align="center">序列号</td>
		<td><font color="blue"><b>&nbsp;${device.serial_num}</b></font></td> --%>
	</tr>
	<tr>
		<c:if test="${device.device_type == '6'}">
			<td align="center">内存/容量</td>
			<td><font color="blue"><b>&nbsp;${device.memory}</b></font></td>
		</c:if>
		<c:if test="${device.device_statues == '6'}">
			<td align="center">报废时间</td>
			<td colspan="3"><font color="blue"><b>&nbsp;${device.destroy_time_str}</b></font></td> 
		</c:if>
	</tr>  
  	</table>
</br>
<c:if test="${not empty itemList}">
<table width="80%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
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
	    	<td align="center">&nbsp;${item.barcode}</td>
	    	<td align="center">&nbsp;${item.user_name}</td>
	    	<td align="center">&nbsp;${item.dept_name}</td>
	    	<td align="center">&nbsp;${item.oper_time_str}</td>
	    	<td align="center">&nbsp;${item.oper.name}</td>
	    	<c:choose>
				<c:when test="${empty item.job_code}">
					<td align="center">无详细信息</td>		
				</c:when>
				<c:otherwise>
					<td align="center"><input type="button" class="button_2003" value="查看详情" onclick="go('${ctx}/computer/approveinfodevicejob.action?history=Y&job_code=${item.job_code}');"/></td>
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