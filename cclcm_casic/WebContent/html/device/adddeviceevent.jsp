<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
	<script language="JavaScript" src="${ctx}/_script/function.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
 	<script type="text/javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>	
<script>
<!--
$(document).ready(function(){
	onHover();
});
function chk()
{	
	var numval = /^[0-9]+$/;
	
	if($("#med_type").val() == ""){
		alert("请选择介质类型");
		$("#med_type").focus();
		return false;
	}
	if($("#seclv_code").val() == ""){
		alert("请选择密级");
		$("#seclv_code").focus();
		return false;
	}	
	if($("#borrow_date").val()!= ""){
		if(!numval.test( $("#borrow_date").val())){
	    alert("借用时间应为整数");
	    $("#borrow_date").focus();
	    return false;
	}
	/*
	if($("#borrow_date").val() > 356000){
	    alert("借用时间不能超过1000年");
	    $("#borrow_date").focus();
	    return false;
	}
	*/
	}
	var summ = $("#summ").val();
	if(summ.length > 1024){
	    alert("备注不能超过1024个字符");
	    $("#summ").focus();
	    return false;
	}

    return true;
}


//-->
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
	<form action="${ctx}/device/adddeviceevent.action" method="POST">
		<input type="hidden" name="event_code" value="${event_code}" id="event_code"/>
	<tr>
	    <td colspan="6" class="title_box">添加磁介质借入申请</td>
	</tr>
	<tr> 
    	<td width="10%" align="center">申请用户： </td>
    	<td width="23%"><font color="blue"><b>${curUser.user_name}</b></font></td>
    	<td width="10%" align="center">用户部门： </td>
    	<td width="23%"><font color="blue"><b>${curUser.dept_name}</b></font></td>
    	<td width="10%" align="center">流水号： </td>
    	<td width="23%"><font color="blue"><b>${event_code}</b></font></td>
	</tr>
	<tr> 		
    	<td align="center"><font color="red">*</font>&nbsp;介质类型： </td>
    	<td><select name="med_type" id="med_type">
    			<option value="">--请选择--</option>
    			<s:iterator value="#request.deviceTypeList" var="type">
					<option value="${type.id}">${type.typename}</option>
				</s:iterator>
    		</select>
    	</td>
    	<td align="center"><font color="red">*</font>&nbsp;密级：</td>
	    <td>
			<select id="seclv_code" name="seclv_code">
				<option value="">--请选择--</option>
				<s:iterator value="#request.seclvList" var="seclv">
					<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
				</s:iterator>
			</select>
		</td>
	   	<td align="center">&nbsp;借用时间： </td>
    	<td><input type="text" name="borrow_date" id="borrow_date"/>天</td>   	
    </tr>
    <tr>
  		<td align="center">&nbsp;备注：</td>
		<td colspan="5">
      		<textarea name="summ" rows="3" cols="60" id="summ"></textarea>
		</td>
  	</tr>
	</form>
  	
  	<tr>
    <td colspan="6" align="center"> 
      <input type="button" class="button_2003" value="提交" onclick="if(chk()) forms[0].submit();" id="submit_btn"/>
      &nbsp;
      <input class="button_2003" type="reset" value="返回"  onClick="javascript:history.go(-1);">&nbsp;
      <font color="red" id="submitPrompt" style="display: none">正在提交处理，请稍等……</font>
    </td>
  </tr>
</table>
</body>
</html>
