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
	
	if($("#seclv_code").val() == ""){
		alert("请选择密级");
		$("#seclv_code").focus();
		return false;
	}
	if($("#file_name").val() == ""){
		alert("请填写文件名称");
		$("#file_name").focus();
		return false;
	}	
	if($("#copy_num").val().trim() == ""){
		alert("请填写复印份数");
		$("#copy_num").focus();
		return false;
	}
	if(!numval.test( $("#copy_num").val())){
	    alert("复印份数应为整数");
	    $("#copy_num").focus();
	    return false;
	}
	if($("#page_num").val().trim() == ""){
		alert("请填写每份页数");
		$("#page_num").focus();
		return false;
	}
	if(!numval.test( $("#page_num").val())){
	    alert("每份页数应为整数");
	    $("#page_num").focus();
	    return false;
	}	
	if($("#scale").val()!= ""){
		if(!numval.test( $("#scale").val())){
	    alert("缩放应为整数");
	    $("#scale").focus();
	    return false;
	}}
    return true;
}
//-->
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">	
	<form action="${ctx}/copy/addcopyevent.action" method="POST">
		<input type="hidden" name="event_code" value="${event_code}" id="event_code"/>
	<tr>
	    <td colspan="6" class="title_box">添加复印申请</td>
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
    	<td align="center"><font color="red">*</font>&nbsp;密级：</td>
	    <td>
			<select id="seclv_code" name="seclv_code">
				<option value="">--请选择--</option>
				<s:iterator value="#request.seclvList" var="seclv">
					<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
				</s:iterator>
			</select>
		</td>		    	
    	<td align="center"><font color="red">*</font>&nbsp;文件名称： </td>
    	<td><input type="text" name="file_name" id="file_name"/></td>  
    	<td align="center">&nbsp;源文件编号： </td>
    	<td><input type="text" name="originalid" id="originalid"/></td>   		   	   	
    </tr>    
    <tr>
    	<td align="center"><font color="red">*</font>&nbsp;复印份数： </td>
    	<td><input type="text" name="copy_num" id="copy_num"/></td>
    	<td align="center"><font color="red">*</font>&nbsp;每份页数： </td>
    	<td><input type="text" name="page_num" id="page_num"/></td>	
    	<td align="center">&nbsp;缩放： </td>
    	<td><input type="text" name="scale" id="scale"/></td>
    </tr>
    <tr>
    <td align="center">&nbsp;纸张类型： </td>
    	<td>
	    	<select id="page_type" name="page_type">
	    		<option value="">--请选择--</option>
	    		<option value="A4">A4</option>
	    		<option value="A5">A5</option>
	    		<option value="B4">B4</option>
	    		<option value="B5">B5</option>
	    		<option value="其他">其他</option>
	    	</select>
		</td>     	   	
    	<td align="center">&nbsp;单双面： </td>
    	<td><select name="is_double" id="is_double">
    			<option value="1">单面</option>
    			<option value="2">双面左右翻</option>   
    			<option value="3">双面上下翻</option> 	 			
    		</select>
    	</td>    	   	    	
    	<td align="center">&nbsp;颜色： </td>
    	<td><select name="color" id="color">
    			<option value="1">黑白</option>
    			<option value="2">彩色</option>   			
    		</select>
    	</td>
    </tr>
    <tr>   	
	   	<td align="center">&nbsp;来源： </td>
    	<td><input type="text" name="source" id="source"/></td> 	   	
  		<td align="center">&nbsp;备注：</td>
		<td colspan="3"><textarea name="summ" rows="3" cols="60" id="summ"></textarea></td>
  	</tr>
	</form>
	 	
  	<tr>
    <td colspan="6" align="center"> 
      <input type="button" class="button_2003" value="提交" onclick="if(chk()) forms[0].submit();" id="submit_btn"/>
      &nbsp;
      <input class="button_2003" type="reset" value="返回"  onClick="javascript:history.go(-1);">&nbsp;
    </td>
  </tr>
</table>
</body>
</html>
