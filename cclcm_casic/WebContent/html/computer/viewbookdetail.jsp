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
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<form method="post" action="${ctx}/computer/viewbookdetail.action">
	
  	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="80%">
	 <tr>
		 <td colspan="6" class="title_box">查看笔记本详细台账</td>
    </tr> 
 	<tr>
		<td align="center">保管人</td>
		<td><font color="blue"><b>&nbsp;${device.duty_user_name}</b></font></td>
		<td align="center">使用部门</td>
		<td><font color="blue"><b>&nbsp;${device.duty_dept_name}</b></font></td>
		<td align="center">单位</td>
    	<td><font color="blue"><b>&nbsp;${device.duty_entp}</b></font></td>
    </tr>  
    <tr>
		<td align="center">设备状态</td>
		<td><font color="blue"><b>&nbsp;${device.book_status_name}</b></font></td>
		<td align="center">硬盘序列号</td>
		<td><font color="blue"><b>&nbsp;${device.hdisk_no}</b></font></td>
		<td align="center">统一编号</td>
		<td><font color="blue"><b>&nbsp;${device.book_code}</b></font></td>
		
	</tr>     
    <tr>
    	<td align="center">设备密级</td>
    	<td><font color="blue"><b>&nbsp;${device.seclv_name}</b></font></td>
    	<td align="center">保密编号</td>
    	<td><font color="blue"><b>&nbsp;${device.conf_code}</b></font></td>
    	<td align="center">名称型号</td>
    	<td><font color="blue"><b>&nbsp;${device.book_model}</b></font></td>
	</tr>
	<tr>
    	<%-- <td align="center">使用情况</td>
    	<td><font color="blue"><b>&nbsp;${device.useinfo}</b></font></td> --%>
    	<td align="center">外出情况</td>
    	<td><font color="blue"><b>&nbsp;${device.outsideinfo}</b></font></td>
    	<td align="center">操作系统时间</td>
    	<td><font color="blue"><b>&nbsp;${device.book_os}</b></font></td>
    	<td align="center">原保密编号</td>
    	<td><font color="blue"><b>&nbsp;${device.oldconf_code}</b></font></td>
	</tr>
	<tr>
    	<td align="center">MAC地址</td>
    	<td><font color="blue"><b>&nbsp;${device.book_mac}</b></font></td>
    	<td align="center">存储涉密信息情况</td>
		<td><font color="blue"><b>&nbsp;${device.storage_secinfo}</b></font></td>
    	<td align="center">联网情况</td>
    	<td><font color="blue"><b>&nbsp;${device.internet_type}</b></font></td>
	</tr>    
 	<tr>
		<td align="center">存放地点</td>
		<td><font color="blue"><b>&nbsp;${device.storage_location}</b></font></td>
		<td align="center">输出情况</td>
		<td><font color="blue"><b>&nbsp;${device.output_point}</b></font></td>
		<td align="center">备注</td>
		<td><font color="blue"><b>&nbsp;${device.detail}</b></font></td>
	</tr>
  	</table>
</br>
<c:if test="${not empty itemList}">
<table width="90%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
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
				<c:when test="${item.type == 'event'}">
					<td align="center"><input type="button" class="button_2003" value="查看详情" onclick="go('${ctx}/computer/approvebookjob.action?history=Y&job_code=${item.job_code}');"/></td>
				</c:when>
				<c:otherwise>
					<td align="center"><input type="button" class="button_2003" value="查看详情" onclick="go('${ctx}/computer/approveborrowbookjob.action?history=Y&job_code=${item.job_code}');"/></td>
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