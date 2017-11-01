<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>查看载体生命周期详细信息</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen"/>
	<link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
	<script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
	<tr>
	    <td colspan="6" class="title_box">载体属性信息</td>
	</tr>
	<tr>
    	<td width="13%" align="center">介质名称：</td>
    	<td width="20%"><font color="blue"><b>&nbsp;${device.device_name}</b></font></td>
    	<c:choose>
		   <c:when test="${device.scope == 'DEPT'}">
    	       <td width="13%" align="center">介质类型： </td>
    	       <td width="20%"><font color="blue"><b>&nbsp;${device.med_type_name}</b></font></td>
    	       <td width="13%" align="center">当前状态： </td>
    	       <td width="20%"><font color="red"><b>&nbsp;${device.device_status_name}</b></font></td>
    	   </c:when>
	    </c:choose>
	    <c:choose>
		   <c:when test="${device.scope == 'PERSON'}">
    	       <td width="13%" align="center">当前状态： </td>
    	           <td width="20%"><font color="red"><b>&nbsp;${device.device_status_name}</b></font></td>
    	       <td>&nbsp; </td><td>&nbsp; </td>
    	   </c:when>
	    </c:choose>
	</tr>
	<tr>
    	<td align="center">条码号： </td>
    	<td><font color="blue"><b>&nbsp;${device.device_barcode}</b></font></td>
		<td align="center">密级： </td>
    	<td><font color="blue"><b>&nbsp;${device.seclv_name}</b></font></td>
    	<td align="center">所属部门： </td>
    	<td><font color="blue"><b>&nbsp;${device.dept_name}</b></font></td>
	</tr>
	<tr>
		<td align="center">责任人： </td>
    	<td><font color="blue"><b>&nbsp;${device.duty_user_name}</b></font></td>
    	<c:choose>
		   <c:when test="${device.scope == 'DEPT'}">
		       <td align="center">借用人： </td>
    	       <td><font color="blue"><b>&nbsp;${device.borrow_user_name}</b></font></td> 
		   </c:when>
	    </c:choose>
	    <c:choose>
		   <c:when test="${device.scope == 'DEPT'}">
    	       <td align="center">录入员： </td>
    	       <td><font color="blue"><b>&nbsp;${device.user_name}</b></font></td>
    	   </c:when>
	    </c:choose>
	    <c:choose>
		   <c:when test="${device.scope == 'PERSON'}">
    	       <td align="center">录入申请人： </td>
    	       <td><font color="blue"><b>&nbsp;${device.user_name}</b></font></td>
    	   </c:when>
	    </c:choose>
    	<c:if test="${device.scope == 'PERSON'}">
    	     <td>&nbsp;</td><td>&nbsp;</td>
    	</c:if>
  	</tr>
  	<tr> 
		<td align="center">设备编号： </td>
    	<td><font color="blue"><b>&nbsp;${device.device_series}</b></font></td>
    	<c:choose>
		   <c:when test="${device.scope == 'DEPT'}">
    	       <td align="center">型号： </td>
    	       <td><font color="blue"><b>&nbsp;${device.device_model}</b></font></td>
	           <td align="center">设备配置：</td>
	           <td><font color="blue"><b>&nbsp;${device.device_detail}</b></font></td>
	       </c:when>
	    </c:choose>
	    <c:if test="${device.scope == 'PERSON'}">
    	     <td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td>
    	</c:if>
	</tr>
	<tr> 
    	<td align="center">说明： </td>
    	<td><font color="blue"><b>&nbsp;${device.med_content}</b></font></td>
    	<td align="center">登记时间：</td>
	    <td><font color="blue"><b>&nbsp;${device.enter_time_str}</b></font></td>
	    <td>&nbsp;</td>
	    <td>&nbsp;</td>
	</tr>
</table>
<c:if test="${not empty itemList}">
<br/>
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
	<tr>
	    <td colspan="6" class="title_box">载体全生命周期信息</td>
	</tr>
	<tr>
    	<td width="20%" align="center">载体条码 </td>
    	<td width="15%" align="center">操作人</td>
    	<td width="15%" align="center">部门</td>
    	<td width="20%" align="center">操作时间 </td>
    	<td width="15%" align="center">操作 </td>
    	<td width="15%" align="center">审批流程</td>
	</tr>
	<s:iterator value="#request.itemList" var="item">
		<tr>
	    	<td align="center">${item.barcode}</td>
	    	<td align="center">${item.user_name}</td>
	    	<td align="center">${item.dept_name}</td>
	    	<td align="center">${item.oper_time_str}</td>
	    	<td align="center">${item.oper.name}</td>
	    	<c:choose>
		    	 <c:when test="${item.job_code == 'default'}">
		    	 	<td align="center">暂无审批信息</td>
		    	 </c:when>
		    	 <c:otherwise>
		    	 	<td align="center"><input class="button_2003" type="button" value="查看审批信息" onClick="go('${ctx}/ledger/viewapprovalprocessdetail.action?job_code=${item.job_code}');"/></td>
		    	 </c:otherwise>
	    	</c:choose>
		</tr>	
	</s:iterator>
  	<tr>
	    <td colspan="5" align="center"> 
			<input class="button_2003" type="button" value="返回" onClick="javascript:history.go(-1);">&nbsp;
	    </td>
  	</tr>
</table>
</c:if>
</center>
</body>
</html>