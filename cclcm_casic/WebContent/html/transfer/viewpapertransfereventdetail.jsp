<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>   
 
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
	<link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
	<script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>

</head>
<body oncontextmenu="self.event.returnValue=false">
<form action="${ctx}/transfer/addpapertransferevent.action" method="POST">
	<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
		<input type="hidden" name="event_code" value="${event_code}" id="event_code"/>
		<input type="hidden" name="seclv_code" id="hid_seclv_code"/>
		<input type="hidden" name="id" value="${id}" id="id"/>
	<tr>
	    <td colspan="6" class="title_box">查看流转作业</td>
	</tr>
	<tr> 
    	<td width="10%" align="center">申请用户： </td>
    	<td width="23%"><font color="blue"><b>${curUser.user_name}&nbsp;</b></font></td>
    	<td width="10%" align="center">用户部门： </td>
    	<td width="23%"><font color="blue"><b>${curUser.dept_name}&nbsp;</b></font></td>
    	<td width="10%" align="center">作业编号 ： </td>
    	<td width="23%"><font color="blue"><b>${transfer.event_code}&nbsp;</b></font></td>
	</tr>
	<tr> 
		<td align="center">&nbsp;作业密级：</td>
	    <td width="23%"><font color="blue"><b>${transfer.seclv_name}&nbsp;</b></font></td>
	    
    	<td align="center">用途： </td>
    	<td><font color="blue"><b>${transfer.usage_name}&nbsp;</b></font>
    	</td>
    	<td align="center">&nbsp;项目： </td>
    	<td width="23%"><font color="blue"><b>${transfer.project_name}</b></font>&nbsp;</td>
    </tr>
    <tr>
    	
    	<td align="center">&nbsp;接收人：</td>
    	<td>
    		<font color="blue"><b>${accept_user_name}&nbsp;</b></font>
	    </td>
	    <td align="center">&nbsp;接收人部门：</td>
    	<td>
    		<font color="blue"><b>${transfer.accept_dept_name}&nbsp;</b></font>
	    </td>
	    <td align="center">&nbsp;载体条码：</td>
	    <td width="23%"><font color="blue"><b>${transfer.barcode}&nbsp;</b></font></td>
	    
    </tr>
    <tr>
    	<td align="center">申请时间： </td>
    	<td><font color="blue"><b>${apply_time}&nbsp;</b></font>
    	</td>	
    	<td align="center">历史作业： </td>
    	<td><font color="blue"><b>${transfer.his_job_code}&nbsp;</b></font>
    	</td>
    	<td align="center">&nbsp;备注：</td>
		<td colspan="2"><font color="blue"><b>${transfer.summ}&nbsp;</b></font>
		</td>
    </tr>
  	<tr>
    <td colspan=6 align=center> 
		<input class="button_2003" type="button" value="返回" onClick="javascript:history.go(-1);">&nbsp;
    </td>
  </tr>
  </table>
</form>
</body>
</html>
