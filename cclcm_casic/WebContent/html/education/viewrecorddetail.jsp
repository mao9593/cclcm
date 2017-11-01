<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>查看记录</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
	<script language="JavaScript" src="${ctx}/_script/function.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common_bm.js"></script>
 	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>	
 	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
 	<script language="JavaScript" src="${ctx}/_script/common.js"></script>
 	<script language="javascript" src="${ctx}/_script/My97DatePicker/WdatePicker.js"></script>  
 	
</head>
<body  oncontextmenu="self.event.returnValue=true">
<center>
	<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">	
	<tr>
	    <td colspan="6" class="title_box">查看培训记录</td>
	</tr>
	<tr> 
    	<td width="13%" align="center"><font color="red">*</font>培训名称 </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${record.course_name}</b></font></td>
		<td width="13%" align="center"><font color="red">*</font>培训编号 </td>
		<td width="20%"><font color="blue"><b>&nbsp;${record.course_id}</b></font></td>
    	<td width="13%" align="center">培训类型 </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${record.training_type_name}</b></font></td>
   	</tr>
   	<tr>
		<td width="13%" align="center"><font color="red">*</font>开始时间 </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${record.start_time_str}</b></font></td>
    	<td width="13%" align="center"><font color="red">*</font>结束时间 </td>
    	<td width="20%"><font color="blue"><b>&nbsp;${record.end_time_str}</b></font></td>
	    <td width="13%" align="center"><font color="red">*</font>学时</td>
	    <td width="20%"><font color="blue"><b>&nbsp;${record.class_hour}</b></font></td>
   	</tr>  
     <tr>
       <td width="13%" align="center" ><font color="red">*</font>培训地点</td>
       <td width="20%"><font color="blue"><b>&nbsp;${record.location}</b></font></td>		
       <td width="13%" align="center">参加人员</td>
       <td width="20%"><font color="blue"><b>&nbsp;${record.edu_user_names}</b></font></td>
       <td colspan="2">&nbsp;</td>
    </tr>
     <c:if test="${record.training_type == '2'}">
     <tr>
		   <td width="13%" align="center"><font color="red">*</font>培训机构</td>
    	    <td width="20%"><font color="blue"><b>&nbsp;${record.training_org}</b></font></td>
    	   <td width="13%" align="center"><font color="red">*</font>培训证书编号</td>
    	    <td width="20%"><font color="blue"><b>&nbsp;${record.certificate_no}</b></font></td>
    	    <td width="13%" align="center"><font color="red">*</font>证书有效期</td>
            <td width="20%"><font color="blue"><b>&nbsp;${record.certificate_exp_str}</b></font></td>
   	</tr>  
     </c:if>
   
	<tr>
 		<td align="center">备注</td>
	   	<td colspan="5"><font color="blue"><b>&nbsp;${record.summ}</b></font></td>
	</tr>	
  	<tr>
    <td colspan="6" align="center">
            <input type="button" value="返回" class="button_2003" onclick="history.go(-1);">
    </td>
  </tr>
</table>
</center>
</body>
</html>
