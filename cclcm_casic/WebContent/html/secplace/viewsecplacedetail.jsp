<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>查看要害部门部位</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<form method="post" action="${ctx}/computer/viewcomputerdetail.action">
  	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="90%">
  	 <tr>
    	<td align="center"><font color="red">*</font>要害部门部位名称</td>
		<td><font color="blue"><b>&nbsp;${secplace.secplace_name}</b></font></td>
		<td align="center"><font color="red">*</font>物理位置</td>
		<td><font color="blue"><b>&nbsp;${secplace.secplace_location}</b></font></td>
		<td align="center"><font color="red">*</font>要害部门部位编号</td>
		<td><font color="blue"><b>&nbsp;${secplace.secplace_code}</b></font></td>
    </tr>  
    <tr>
    	<td align="center"><font color="red">*</font>要害类别</td>
		<td>
			<font color="blue">
				<b>
					&nbsp;
					<c:if test="${secplace.secplace_type=='1'}">部门</c:if>
					<c:if test="${secplace.secplace_type=='2'}">部位</c:if>
				</b>
			</font>
		</td>
		<td align="center"><font color="red">*</font>要害密级</td>
		<td><font color="blue"><b>&nbsp;${secplace.seclv_name}</b></font></td>
		<td align="center"><font color="red">*</font>保密编号</td>
		<td><font color="blue"><b>&nbsp;${secplace.conf_code}</b></font></td>
    </tr> 
    <tr>
    	
    	<td align="center"><font color="red">*</font>主管领导</td>
		<td><font color="blue"><b>&nbsp;${secplace.leader_name}</b></font></td>
		<td align="center"><font color="red">*</font>责任人</td>
		<td><font color="blue"><b>&nbsp;${secplace.duty_user_name}</b></font></td>
		<td align="center"><font color="red">*</font>责任部门</td> 
		<td><font color="blue"><b>&nbsp;${secplace.duty_dept_name}</b></font></td>
    </tr> 						
    
    <tr>
    	<td align="center">涉密人员数量</td>
		<td><font color="blue"><b>&nbsp;${secplace.user_number}</b></font></td>
		<td align="center">成立时间</td>
		<td><font color="blue"><b>&nbsp;${secplace.found_time}</b></font></td>
		<td align="center">使用状态</td>
		<td>
			<font color="blue">
				<b>
					&nbsp;
					<c:if test="${secplace.secplace_status=='1'}">在用</c:if>
					<c:if test="${secplace.secplace_status=='2'}">停用</c:if>
				</b>
			</font>
		</td>
	</tr> 						
    <tr>
		<td align="center">用途</td>
		<td colspan="5"><font color="blue"><b>&nbsp;${secplace.secplace_application}</b></font></td>
		
    </tr> 
     <tr>
    	<td align="center">人防措施</td>
		<td colspan="5"><font color="blue"><b>&nbsp;${secplace.people_protect}</b></font></td>
    </tr> 	
     <tr>
    	<td align="center">物防措施</td>
		<td colspan="5"><font color="blue"><b>&nbsp;${secplace.physical_protect}</b></font></td>
    </tr> 	
     <tr>
    	<td align="center">技防措施</td>
		<td colspan="5"><font color="blue"><b>&nbsp;${secplace.technology_protect}</b></font></td>
    </tr> 	
     <tr>
    	<td align="center">备注</td>
		<td colspan="5"><font color="blue"><b>&nbsp;${secplace.summ}</b></font></td>
    </tr> 	
    <tr>
        <td colspan="6" align="center" class="bottom_box">
            <input type="button" value="返回" class="button_2003" onclick="history.go(-1);">
        </td>
    </tr>
  	</table>
</form>
</center>
</body>
</html>