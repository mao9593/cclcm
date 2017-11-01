<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
<script>
<!--
$(document).ready(function(){
	onHover();
});
function chk()
{	
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
	var numval = /^[0-9]+$/;
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
		}*/
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
	<form action="${ctx}/device/updatedeviceevent.action" method="POST">
		<input type="hidden" name="event_code" value="${event.event_code}" id="event_code"/>
		<input type="hidden" name="update" value="Y"/>
	<tr>
	    <td colspan="6" class="title_box">修改借入申请</td>
	</tr>
	<tr> 
    	<td width="10%" align="center">申请用户： </td>
    	<td width="23%"><font color="blue"><b>${curUser.user_name}</b></font></td>
    	<td width="10%" align="center">用户部门： </td>
    	<td width="23%"><font color="blue"><b>${curUser.dept_name}</b></font></td>
    	<td width="10%" align="center">流水号： </td>
    	<td width="23%"><font color="blue"><b>${event.event_code}</b></font></td>
	</tr>
	<tr> 
		<td align="center"><font color="red">*</font>&nbsp;介质类型： </td>
    	<td><select name="med_type" id="med_type">
    			<option value="">--请选择--</option>
    			<option value="1" <c:if test="${event.med_type == '1'}">selected</c:if>>U盘</option>
    			<option value="2" <c:if test="${event.med_type == '2'}">selected</c:if>>移动硬盘</option>
    			<option value="3" <c:if test="${event.med_type == '3'}">selected</c:if>>笔记本</option>
    			<option value="4" <c:if test="${event.med_type == '4'}">selected</c:if>>照相机</option>
    			<option value="5" <c:if test="${event.med_type == '5'}">selected</c:if>>录像机</option>
    			<option value="6" <c:if test="${event.med_type == '6'}">selected</c:if>>录音笔</option>
    			<option value="8" <c:if test="${event.med_type == '8'}">selected</c:if>>软盘</option>
    			<option value="9" <c:if test="${event.med_type == '9'}">selected</c:if>>磁带</option>
    		</select>
    	</td>
		<td align="center"><font color="red">*</font>&nbsp;密级：</td>
	    <td>
	    	<s:set value="#request.event.seclv_code" var="seclv1"/>
			<select id="seclv_code" name="seclv_code">
				<option value="">--请选择--</option>
				<s:iterator value="#request.seclvList" var="seclv">
					<option value="${seclv.seclv_code}" <s:if test="#seclv.seclv_code==#seclv1">selected</s:if>>${seclv.seclv_name}</option>
				</s:iterator>
			</select>
		</td>
	   	<td align="center">&nbsp;借用时间： </td>
    	<td><input type="text" name="borrow_date" id="borrow_date" value="${event.borrow_date}"/></td>    	
    </tr>
    <tr>
  		<td align="center">&nbsp;备注：</td>
		<td colspan="5">
      		<textarea name="summ" rows="3" cols="60" id="summ">${event.summ}</textarea>
		</td>
  	</tr>
	</form> 	
  	<tr>
    <td colspan="6" align="center"> 
      <input type="button" class="button_2003" value="提交" onclick="if(chk()) forms[0].submit();" id="submit_btn"/>
      &nbsp;
      <input type="button" value="返回" class="button_2003" onclick="javascript:history.go(-1);">
      <font color="red" id="submitPrompt" style="display: none">正在提交处理，请稍等……</font>
    </td>
  </tr>
</table>
</body>
</html>
