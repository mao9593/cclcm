<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>添加磁介质</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script  language="JavaScript" >
	<!--
	$(document).ready(function(){
		onHover();
		disableEnterSubmit();
		document.getElementById("allOptions").innerHTML="";
	});
	function selectRecvUser(word){
		var url = "${ctx}/basic/getfuzzyuser.action";
		if(word != ""){
			callServer1(url,"fuzzy="+word);
		}else{
			document.getElementById("allOptions").innerHTML="";
		}
	}
	function updateResult(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
				if(xmlHttp.responseText.toString().length > 154){
					document.getElementById("allOptions").innerHTML=xmlHttp.responseText;
				}else{
					document.getElementById("allOptions").innerHTML="";
				}
			}else{
				document.getElementById("allOptions").innerHTML="";
			}
	}
	function add_True(){
		var user_id=$("#allOption").val();
		var user_name=$("#allOption option[value='"+user_id+"']").text();
		if(user_id != ""){
			$("#duty_user_iidd").val(user_id);
			$("#duty_user_name").val(user_name);
			document.getElementById("allOptions").innerHTML="";
		}
	}
	function chk()
	{
		if($("#device_name").val().trim() == ""){
			alert("请填写介质名称");
			$("#device_name").focus();
			return false;
		}
		if($("#med_type").val().trim() == ""){
			alert("请选择介质类型");
			$("#med_type").focus();
			return false;
		}
		if($("#seclv_code").val().trim() == ""){
			alert("请选择密级");
			$("#seclv_code").focus();
			return false;
		}
		if($("#duty_user_iidd").val().trim() == ""){
			alert("请输入责任人");
			$("#duty_user_name").focus();
			return false;
		}
		if(!checkCode_addword($("#device_name").val())){
			$("#device_name").focus();
			alert("设备名称只能由数字、字母、汉字或下划线组成，长度小于30位");
			return false;
		}
		if($("#device_series").val().length>0 && !checkCode_addword($("#device_series").val())){
			$("#device_series").focus();
			alert("设备编号只能由数字、字母、汉字或下划线组成，长度小于30位");
			return false;
		}
		if($("#device_model").val().length>0 && !checkCode_addword($("#device_model").val())){
			$("#device_model").focus();
			alert("型号只能由数字、字母、汉字或下划线组成，长度小于30位");
			return false;
		}
	    return true;
	}
	//-->
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<form method="post" action="${ctx}/device/adddevice.action">
  	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="60%">
	 <tr>
		 <td colspan="2" class="title_box">
            	添加磁介质
        </td>
    </tr>
    <input type="hidden" name="user_iidd" id="user_iidd" value="${curUser.user_iidd}"/>
    <tr>
    	<td align="center"><font color="red">*</font>介质名称：</td>
		<td>
			<input type="text" name="device_name" id="device_name"/>
		</td>
    </tr>   						
    <tr>
    	<td align="center">所属部门：</td>
		<td>
			<input type="text" id="dept_name" readonly="readonly" style="cursor:hand" onclick="openDeptSelect('dept_name','dept_id','radio')" />
			<input type="hidden" name="dept_id" id="dept_id"/>
		</td>
    </tr>
    <tr>
    	<td align="center"><font color="red">*</font>介质类型：</td>
		<td>
			<select id="med_type" name="med_type">
				<option value="">--请选择--</option>
				<option value="1">U盘</option>
				<option value="2">移动硬盘</option>
				<option value="3">便携式计算机</option>
				<option value="4">照相机</option>
				<option value="5">录像机</option>
				<option value="6">录音笔</option>
				<option value="8">软盘</option>
				<option value="9">磁带</option>
				<option value="10">录像带</option>
				<option value="11">录音带</option>
				<option value="12">移动光驱</option>
				<option value="13">红黑电源</option>
				<option value="14">安全设备</option>
				<option value="15">多功能导入装置</option>
				<option value="16">硬盘</option>
			</select>
		</td>
    </tr>
    <tr>
    	<td align="center"><font color="red">*</font>密级：</td>
	    <td>
			<select name="seclv_code" id="seclv_code">
				<option value="">--请选择--</option>
				<s:iterator value="#request.seclvList" var="seclv">
					<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
				</s:iterator>
			</select>
		</td>
    </tr>
    <tr>
		<td align="center"><font color="red">*</font>责任人：</td>
    	<td>
    		<input type="text" id="duty_user_name" name="duty_user_name" onkeyup="selectRecvUser(this.value);"/>
    		<input type="hidden" id="duty_user_iidd" name="duty_user_iidd"/><br>
    		<div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
    		</div>
	    </td>
    </tr> 
    <tr>
    	<td align="center">设备编号：</td>
		<td>
			<input type="text" name="device_series" id="device_series"/>
		</td>
    </tr>      
    <tr>
    	<td align="center">型号：</td>
		<td>
			<input type="text" name="device_model" id="device_model"/>
		</td>
    </tr>
    <tr>
    	<td align="center">设备配置：</td>
		<td>
			<textarea name="device_detail" rows="3" cols="50" id="device_detail"></textarea>
		</td>
    </tr>
    <tr>
    	<td align="center">说明：</td>
		<td>
			<textarea name="med_content" rows="3" cols="50" id="med_content"></textarea>
		</td>
    </tr>
    <tr>
        <td colspan="2" align="center" class="bottom_box">
            <input type="submit" value="添加" class="button_2003" onclick="return chk();">&nbsp;
            <input type="reset" value="重置" class="button_2003">
        </td>
    </tr>
  	</table>
</form>
</center>
</body>
</html>