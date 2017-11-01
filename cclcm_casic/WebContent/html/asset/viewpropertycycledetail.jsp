<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>查看资产生命周期详细信息</title>
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
	    <td colspan="6" class="title_box">资产信息</td>
	</tr>
	<tr>
    	<td width="13%" align="center">采购人：</td>
    	<td width="20%"><font color="blue"><b>&nbsp;${property.user_name}</b></font></td>
    	<td width="13%" align="center">采购人部门： </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${property.dept_name}</b></font></td>
    	<td width="13%" align="center">责任人： </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${property.duty_user_name}</b></font></td>
	</tr>
	<tr>
    	<td align="center">责任人部门：</td>
    	<td><font color="blue"><b>&nbsp;${property.duty_dept_name}</b></font></td>
    	<td align="center">资产密级： </td>
    	<td><font color="blue"><b>&nbsp;${property.seclv_name}</b></font></td>
    	<td align="center">资产种类： </td>
    	<td><font color="blue"><b>&nbsp;${property.property_kind}</b></font></td>   	
	</tr>
	<tr>	
    	<td align="center">设备名称： </td>
    	<td><font color="blue"><b>&nbsp;${property.property_name}</b></font></td>
    	<td align="center">资产条码：</td>
    	<td><font color="blue"><b>&nbsp;${property.property_barcode}</b></font></td>
    	<td align="center">资产状态： </td>
    	<td><font color="blue"><b>&nbsp;${property.property_status_str}</b></font></td>
	</tr>
	<tr>	
    	<td align="center">识别码： </td>
    	<td><font color="blue"><b>&nbsp;${property.identity_code}</b></font></td>
    	<td align="center">资产编号：</td>
    	<td><font color="blue"><b>&nbsp;${property.property_no}</b></font></td>
    	<td align="center">凭证号： </td>
    	<td><font color="blue"><b>&nbsp;${property.voucher_no}</b></font></td>
	</tr>
	<tr>	
    	<td align="center">出厂编号： </td>
    	<td><font color="blue"><b>&nbsp;${property.factory_no}</b></font></td>
    	<td align="center">出厂日期：</td>
    	<td><font color="blue"><b>&nbsp;${property.factory_date}</b></font></td>
    	<td align="center">启用日期： </td>
    	<td><font color="blue"><b>&nbsp;${property.use_date}</b></font></td>
	</tr>
	<tr>
    	
		<td align="center">资产规格： </td>
    	<td><font color="blue"><b>&nbsp;${property.property_standard}</b></font></td>
    	<td align="center">资产型号： </td>
    	<td><font color="blue"><b>&nbsp;${property.property_type}</b></font></td>
    	<td align="center">国别厂家： </td>
    	<td><font color="blue"><b>&nbsp;${property.supplier}</b></font></td>
	</tr>
	<tr>
    	<td align="center">原值： </td>
    	<td><font color="blue"><b>&nbsp;${property.original_value}</b></font></td>
    	<td align="center">净值： </td>
    	<td><font color="blue"><b>&nbsp;${property.average_equity}</b></font></td>
		<td align="center">安装地点： </td>
    	<td><font color="blue"><b>&nbsp;${property.setup_place}</b></font></td>    
	</tr>
	<tr>
		<td align="center">单价： </td>
    	<td><font color="blue"><b>&nbsp;${property.unit_price}</b></font></td>
    	<td align="center" >入账时间： </td>
    	<td><font color="blue"><b>&nbsp;${property.in_time}</b></font></td> 
    	<td align="center">报废时间： </td>
    	<td colspan="3"><font color="blue"><b>&nbsp;${property.waste_time}</b></font></td>
    	
  	</tr>
	<tr>
		<td align="center">备注： </td>
		<td colspan="5"><textarea name="summ" rows="3" cols="80" id="summ">${property.summ}</textarea></td>
	</tr>
	
</table>
<c:if test="${not empty itemList}">
<br/>
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
	<tr>
	    <td colspan="5" class="title_box">资产全生命周期信息</td>
	</tr>
	<tr>
    	<td width="20%" align="center">载体条码 </td>
    	<td width="20%" align="center">操作人</td>
    	<td width="20%" align="center">部门</td>
    	<td width="20%" align="center">操作时间 </td>
    	<td width="20%" align="center">操作 </td>
	</tr>
	<s:iterator value="#request.itemList" var="item">
		<tr>
	    	<td align="center">${item.barcode}</td>
	    	<td align="center">${item.user_name}</td>
	    	<td align="center">${item.dept_name}</td>
	    	<td align="center">${item.oper_time_str}</td>
	    	<td align="center">${item.oper.name}</td>
		</tr>	
	</s:iterator>
</table> 	
</c:if>
<br>
<table>
  	<tr>
	    <td colspan="5" align="center"> 
			<input class="button_2003" type="button" value="返回" onClick="javascript:history.go(-1);">&nbsp;
	    </td>
  	</tr>
</table> 
</center>
</body>
</html>