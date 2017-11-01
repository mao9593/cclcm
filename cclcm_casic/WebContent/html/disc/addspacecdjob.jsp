<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
	<script language="JavaScript" src="${ctx}/_script/function.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
 	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>	
 	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
 	<script language="JavaScript" src="${ctx}/_script/common.js"></script>
<script>
$(document).ready(function(){
	onHover();
});

var pattern = /^[0-9]{0,5}$/;
function isInteger(value){
	if(!pattern.test(value)){
		return false;
	}
	if(parseInt(value)>1000 || parseInt(value)==0){
		return false;
	}
	return true;
}

function chk(){
	if($("#spacecd_count").val()==""){
		alert("录入的空白盘数量不能为空");
		return false;
	}
	
	//判断空白盘数量是否合理
	var spacecd_count_wrong = null;
	var spacecd_count_zero = null;
	$("input[name='spacecd_count']").each(function(){
		if(!isInteger(this.value)&&parseInt(this.value)!=0){
			spacecd_count_wrong = this;
			return false;
		}
		if(parseInt(this.value)==0){
			spacecd_count_zero = this;
			return false;
		}
	});
	if(spacecd_count_wrong != null){
		alert("空白盘数量只能填写1~1000之间的数字");
		$(spacecd_count_wrong).focus();
		return false;
	}
	if(spacecd_count_zero != null){
		alert("空白盘数量不能为0");
		$(spacecd_count_zero).focus();
		return false;
	}
	
	if($("#scope_dept_id").val()==""){
		alert("请选择部门");
		return false;
	}
    return true;
}
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">	
	<form action="${ctx}/disc/addspacecdjob.action" method="get">
	<input type="hidden" name="update" id="update" value="Y"/>
	<tr>
	    <td colspan="6" class="title_box">添加空白盘录入</td>
	</tr>
	<tr> 
    	<td width="20%" align="center">申请用户： </td>
    	<td width="30%"><font color="blue"><b>${curUser.user_name}</b></font></td>
    	<td width="20%" align="center">用户部门： </td>
    	<td width="30%"><font color="blue"><b>${curUser.dept_name}</b></font></td>
	</tr>
	<tr>
		<td width="20%" align="center">&nbsp;密级：</td>
	    <td width="30%">
			<select id="seclv_code" name="seclv_code">
				<option value="">--请选择--</option>
				<s:iterator value="#request.seclvList" var="seclv">
					<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
				</s:iterator>
			</select>
		</td> 
    	<td  width="20%" align="center"><font color="red">*</font>&nbsp;空白盘数量： </td>
    	<td  width="30%"><input type="text" name="enter_num" id="enter_num"/></td>    	  	
  	</tr>
  	<tr>
		<td width="20%" align="center">&nbsp;光盘类型：</td>
	    <td width="30%">
			<select id="cd_type" name="cd_type">
					<option value="">--请选择--</option>
					<option value="CD-R">CD-R</option>
					<option value="DVD-R">DVD-R</option>
			</select>
		</td> 
    	<td align="center"><font color="red">*</font>&nbsp;归属部门：</td>
		<td>
			<select id="scope_dept_id" name="scope_dept_id">
				<option value="">--请选择--</option>
				<s:iterator value="#request.deptAdminList" var="groupscope">
					<option value="${groupscope.dept_id}">${groupscope.dept_name}</option>
				</s:iterator>
			</select>
		</td>   	  	
  	</tr>
	</form>
	 	
  	<tr>
    <td colspan="6" align="center"> 
      <input type="button" class="button_2003" value="添加" onclick="if(chk()) forms[0].submit();" id="submit_btn"/>
         </td>
  </tr>
</table>
</body>
</html>
