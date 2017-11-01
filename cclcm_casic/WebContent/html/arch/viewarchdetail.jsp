<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<title>查看档案详细信息</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen"/>
	<link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script>
	$(document).ready(function(){
		$("#${record_count}").next().nextAll().hide();//当前tr里的其他td隐藏
		$("#${record_count}").parent().nextAll().hide();//当前tr后面的tr都隐藏
	});
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<br/>
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
	<tr>
	    <td colspan="6" class="title_box">档案详细信息</td>
	</tr>
	<tr>  	
    	<td width="13%" align="center">${fn:substringBefore(ak.file_title,'^')}： </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${av.file_title}</b></font></td>
    	<td width="13%" align="center">${fn:substringBefore(ak.barcode,'^')}： </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${av.barcode}</b></font></td>
    	<td width="13%" align="center">${fn:substringBefore(ak.dos_num,'^')}： </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${av.dos_num}</b></font></td>
	</tr>
	<tr>
		<td align="center">${fn:substringBefore(ak.arch_num,'^')}：</td>
	    <td><font color="blue"><b>&nbsp;${av.arch_num}</b></font></td>
		<td align="center">${fn:substringBefore(ak.type_code,'^')}： </td>
    	<td><font color="blue"><b>&nbsp;${av.type_code}</b></font></td>
    	<td align="center">${fn:substringBefore(ak.file_num,'^')}： </td>
    	<td><font color="blue"><b>&nbsp;${av.file_num}</b></font></td>		
	</tr>
  	<tr> 
    	<td align="center">${fn:substringBefore(ak.page_num,'^')}： </td>
    	<td><font color="blue"><b>&nbsp;${av.page_num}</b></font></td>
		<td align="center">${fn:substringBefore(ak.count,'^')}： </td>
    	<td><font color="blue"><b>&nbsp;${av.count}</b></font></td>
    	<td align="center">${fn:substringBefore(ak.total_page,'^')}： </td>
    	<td><font color="blue"><b>&nbsp;${av.total_page}</b></font></td>
	</tr>
	<tr>
		<td align="center">${fn:substringBefore(ak.seclv_code,'^')}： </td>
    	<td><font color="blue"><b>&nbsp;${av.seclv_code}</b></font></td>
    	<td align="center">${fn:substringBefore(ak.file_date,'^')}： </td>
    	<td><font color="blue"><b>&nbsp;${av.file_date}</b></font></td> 
    	<td align="center">${fn:substringBefore(ak.file_carr,'^')}： </td>
    	<td><font color="blue"><b>&nbsp;${av.file_carr}</b></font></td>
  	</tr>
  	<tr> 
		<td align="center">${fn:substringBefore(ak.keep_limit,'^')}： </td>
    	<td><font color="blue"><b>&nbsp;${av.keep_limit}</b></font></td>
    	<td align="center">${fn:substringBefore(ak.summ,'^')}： </td>
    	<td><font color="blue"><b>&nbsp;${av.summ}</b></font></td>
    	<td id="1" align="center">${fn:substringBefore(ak.t01,'^')}： </td>
    	<td><font color="blue"><b>&nbsp;${av.t01}</b></font></td>	    
	</tr>
	<tr> 
	    <td id="2" align="center">${fn:substringBefore(ak.t02,'^')}： </td>
    	<td><font color="blue"><b>&nbsp;${av.t02}</b></font></td>
    	<td id="3" align="center">${fn:substringBefore(ak.t03,'^')}： </td>
    	<td><font color="blue"><b>&nbsp;${av.t03}</b></font></td>
    	<td id="4" align="center">${fn:substringBefore(ak.t04,'^')}： </td>
    	<td><font color="blue"><b>&nbsp;${av.t04}</b></font></td>	    
	</tr>
	<tr> 
	    <td id="5" align="center">${fn:substringBefore(ak.t05,'^')}： </td>
    	<td><font color="blue"><b>&nbsp;${av.t05}</b></font></td>
    	<td id="6" align="center">${fn:substringBefore(ak.t06,'^')}： </td>
    	<td><font color="blue"><b>&nbsp;${av.t06}</b></font></td>
    	<td id="7" align="center">${fn:substringBefore(ak.t07,'^')}： </td>
    	<td><font color="blue"><b>&nbsp;${av.t07}</b></font></td>	    
	</tr>
	<tr> 
	    <td id="8" align="center">${fn:substringBefore(ak.t08,'^')}： </td>
    	<td><font color="blue"><b>&nbsp;${av.t08}</b></font></td>
    	<td id="9" align="center">${fn:substringBefore(ak.t09,'^')}： </td>
    	<td><font color="blue"><b>&nbsp;${av.t09}</b></font></td>
    	<td id="10" align="center">${fn:substringBefore(ak.t10,'^')}： </td>
    	<td><font color="blue"><b>&nbsp;${av.t10}</b></font></td>	    
	</tr>
	<tr> 
	    <td id="11" align="center">${fn:substringBefore(ak.t11,'^')}： </td>
    	<td><font color="blue"><b>&nbsp;${av.t11}</b></font></td>
    	<td id="12" align="center">${fn:substringBefore(ak.t12,'^')}： </td>
    	<td><font color="blue"><b>&nbsp;${av.t12}</b></font></td>
    	<td id="13" align="center">${fn:substringBefore(ak.t13,'^')}： </td>
    	<td><font color="blue"><b>&nbsp;${av.t13}</b></font></td>	    
	</tr>
	<tr> 
	    <td id="14" align="center">${fn:substringBefore(ak.t14,'^')}： </td>
    	<td><font color="blue"><b>&nbsp;${av.t14}</b></font></td>
    	<td id="15" align="center">${fn:substringBefore(ak.t15,'^')}： </td>
    	<td><font color="blue"><b>&nbsp;${av.t15}</b></font></td>
    	<td id="16" align="center">${fn:substringBefore(ak.t16,'^')}： </td>
    	<td><font color="blue"><b>&nbsp;${av.t16}</b></font></td>	    
	</tr>
	<tr> 
	    <td id="17" align="center">${fn:substringBefore(ak.t17,'^')}： </td>
    	<td><font color="blue"><b>&nbsp;${av.t17}</b></font></td>
    	<td id="18" align="center">${fn:substringBefore(ak.t18,'^')}： </td>
    	<td><font color="blue"><b>&nbsp;${av.t18}</b></font></td>
    	<td id="19" align="center">${fn:substringBefore(ak.t19,'^')}： </td>
    	<td><font color="blue"><b>&nbsp;${av.t19}</b></font></td>	    
	</tr>
	<tr> 
	    <td id="20" align="center">${fn:substringBefore(ak.t20,'^')}： </td>
    	<td><font color="blue"><b>&nbsp;${av.t20}</b></font></td>
    	<td id="21" align="center">${fn:substringBefore(ak.t21,'^')}： </td>
    	<td><font color="blue"><b>&nbsp;${av.t21}</b></font></td>
    	<td id="22" align="center">${fn:substringBefore(ak.t22,'^')}： </td>
    	<td><font color="blue"><b>&nbsp;${av.t22}</b></font></td>	    
	</tr>
	<tr> 
	    <td id="23" align="center">${fn:substringBefore(ak.t23,'^')}： </td>
    	<td><font color="blue"><b>&nbsp;${av.t23}</b></font></td>
    	<td id="24" align="center">${fn:substringBefore(ak.t24,'^')}： </td>
    	<td><font color="blue"><b>&nbsp;${av.t24}</b></font></td>
    	<td id="25" align="center">${fn:substringBefore(ak.t25,'^')}： </td>
    	<td><font color="blue"><b>&nbsp;${av.t25}</b></font></td>	    
	</tr>
</table>
</center>
</body>
</html>