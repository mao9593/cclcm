<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加审批步骤</title>
<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
<script  language="JavaScript" >
<!--
function addApproval()
{
	if($("td.title_box :radio:first-child").attr("checked")){
		if($("#dept_id").val().length==0){
			alert("请选择审批部门");
			return false;
		}
	}else{
		if($("#dept_level").val().length == 0){
			alert("请指定审批级别");
			$("#dept_level").focus();
			return false;
		}
		/*
		if($("#level_length").val().length == 0){
			alert("请输入最高审批部门编号长度");
			return false;
		}else if(!$.isNumeric($("#level_length").val())){
			alert("最高审批部门编号长度不是数字");
			return false;
		}*/
	}
	if($("#role_id").val().length==0){
		alert("请选择审批角色");
		return false;
	}
    var step = new Object();
    if($("td.title_box :radio:first-child").attr("checked")){
		step.dept_id = $("#dept_id").val(); 
		step.dept_name = $("#dept_name").val();
    }else{
    	step.dept_id = "L:"+$("#dept_level").val();
    	//var level = $("#level_length").val().trim()/2+$("#level_length").val().trim()%2;
    	step.dept_name = "至:"+$("#dept_level").val()+"级机构";
    }
	step.role_id = $("#role_id").val();
	step.role_name = $("#role_name").val();
	window.returnValue = step;
	window.close();
}
function selectDeptSelf(){
	if($("#dept_name").val().length==0){
		$("#dept_name").val("本部门");
		$("#dept_id").val("self");
	}else{
		$("#dept_name").val(function() {
  			return this.value + "," + "本部门";
		});
		$("#dept_id").val(function() {
  			return this.value + "," + "self";
		});
	}
}
function selectDeptSuper(){
	if($("#dept_name").val().length==0){
		$("#dept_name").val("上级部门");
		$("#dept_id").val("super");
	}else{
		$("#dept_name").val(function() {
  			return this.value + "," + "上级部门";
		});
		$("#dept_id").val(function() {
  			return this.value + "," + "super";
		});
	}
}
function selectRole(){
	var url = "${ctx}/user/selectrole.action";
	var rValue = window.showModalDialog(url,'', 'dialogHeight:400px;dialogWidth:600px;center:yes;status:no;scroll:yes;help:no;unadorned:no;resizable:yes');
	if(rValue != undefined && rValue != null){
		$("#role_name").val(rValue.role_name);
		$("#role_id").val(rValue.role_id);
	}
}
function changeStepType(){
	if($("td.title_box :radio:first-child").attr("checked")){
		$("#tr_dept").show();
		$("#tr_level").hide();
	}else{
		$("#tr_dept").hide();
		$("#tr_level").show();
	}
}
//-->
</script>
</head>
<body oncontextmenu="self.event.returnValue=false" style="margin:5,5,5,5">
<center>
  	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="100%">
	<tr style="display: none">
		 <td colspan="2" class="title_box" align="center">
            <input type="radio" name="step_type" checked="checked" onclick="changeStepType();"/>指定审批部门&nbsp;&nbsp;&nbsp;<input type="radio" name="step_type" onclick="changeStepType();"/>指定最高级别
        </td>
    </tr>
    <tr id="tr_dept" height="50">
    	<td align="center" width="70">选择部门：</td>
		<td>
			<textarea id="dept_name" readonly="readonly"></textarea>
			<input type="hidden" id="dept_id"/> 
			<input type="button" value="选择" onclick="openDeptSelect('dept_name','dept_id','checkbox')" class="button_2003"/>
			<input type="button" value="本部门" onclick="selectDeptSelf();" class="button_2003"/>
			<!-- 
			<input type="button" value="上级部门" onclick="selectDeptSuper();" class="button_2003"/>
			 -->
		</td>
    </tr>
    <tr id="tr_level" height="50" style="display: none">
    	<td align="center" width="70">指定级别：</td>
		<td>
			<select id="dept_level">
				<option value="">--请选择--</option>	
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
			</select>&nbsp;级机构(根机构为1级)
		</td>
    </tr>
    <tr>
    	<td align="center" height="70">角色：</td>
		<td>
			<textarea id="role_name" readonly="readonly"></textarea>
			<input type="hidden" id="role_id"/>
			<input type="button" value="选择" class="button_2003" onclick="selectRole();"/>
		</td>
    </tr>
    <tr>
        <td colspan="2" align="center" class="bottom_box">
            <input type="button" value="添加" class="button_2003" onclick="return addApproval()">&nbsp;
        </td>
    </tr>
  	</table>
</center>
</body>
</html>