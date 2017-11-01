<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>添加计算机</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
	<link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
    <script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script language="javascript" src="${ctx}/_script/My97DatePicker/WdatePicker.js"></script>
	<script  language="JavaScript" >
	
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
			$("#duty_user_id").val(user_id);
			$("#duty_user_name").val(user_name);
			document.getElementById("allOptions").innerHTML="";
		}
	}
	function chk()
	{
		if($("#duty_user_id").val().trim() == ""){
			alert("请输入责任人");
			$("#duty_user_name").focus();
			return false;
		}
		if($("#duty_dept_id").val().trim() == ""){
			alert("请输入责任部门");
			$("#duty_dept_name").focus();
			return false;
		}
		if($("#internet_type").val().trim() == ""){
			alert("请选择网络类型");
			$("#internet_type").focus();
			return false;
		}
		if($("#computer_status").val().trim() == ""){
			alert("请选择计算机状态");
			$("#computer_status").focus();
			return false;
		}	
/* 		
		if($("#computer_name").val().trim() == ""){
			alert("请填写计算机名称");
			$("#computer_name").focus();
			return false;
		}
		if($("#hdisk_no").val().trim() == ""){
			alert("请输入硬盘序列号");
			$("#hdisk_no").focus();
			return false;
		}
		if($("#computer_code").val().trim() == ""){
			alert("请输入统一编号");
			$("#computer_code").focus();
			return false;
		}
		if($("#conf_code").val().trim() == ""){
			alert("请输入保密编号");
			$("#conf_code").focus();
			return false;
		}
		if($("#seclv_code").val().trim() == ""){
			alert("请选择密级");
			$("#seclv_code").focus();
			return false;
		}

		if(!checkCode_addword($("#computer_name").val())){
			$("#computer_name").focus();
			alert("设备名称只能由数字、字母、汉字或下划线组成，长度小于30位");
			return false;
		}
		if($("#computer_code").val().length>0 && !checkCode_addword($("#computer_code").val())){
			$("#computer_code").focus();
			alert("设备编号只能由数字、字母、汉字或下划线组成，长度小于30位");
			return false;
		} */
	    return true;
	}

	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<form method="post" action="${ctx}/computer/addcomputer.action" >
<input type="hidden" name="user_iidd" id="user_iidd" value="${curUser.user_iidd}"/>
  	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="90%">
	 <tr>
		 <td colspan="6" class="title_box">录入现有计算机台账</td>
    </tr>
    <tr>
    	<td align="center"><font color="red">*</font>计算机名称型号</td>
		<td>
			<input type="text" name="computer_name" id="computer_name"/>
		</td>
		<td align="center"><font color="red">*</font>资产编号</td>
		<td>
			<input type="text" name="computer_code" id="computer_code" />
		</td>
		<td align="center"><font color="red">*</font>原保密编号</td>
		<td>
			<input type="text" name="oldconf_code" id="oldconf_code" />
		</td>
    </tr>      
    <tr>
    	<td align="center"><font color="red">*</font>密级</td>
		<td>
			<select name="seclv_code" id="seclv_code">
				<option value="">--请选择--</option>
				<s:iterator value="#request.seclvList" var="seclv">
					<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
				</s:iterator>
			</select>
		</td>
    	<td align="center"><font color="red">*</font>责任人</td>
		<td>
			<input type="text" id="duty_user_name" name="duty_user_name" onkeyup="selectRecvUser(this.value);"/>
    		<input type="hidden" id="duty_user_id" name="duty_user_id"/><br>
    		<div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
		</td>
		<td align="center"><font color="red">*</font>责任部门</td>
		<td>
			<input type="text" id="duty_dept_name" name="duty_dept_name" readonly="readonly" style="cursor:hand" onclick="openDeptSelect('duty_dept_name','duty_dept_id','radio')"/>
    		<input type="hidden" id="duty_dept_id" name="duty_dept_id"/><br>
		</td>
    </tr> 
    <tr>
    	<td align="center">责任单位</td>
		<td>
			<select name="duty_entp_id" id="duty_entp_id">
				<option value="">--请选择--</option>
				<option value="1" >总部</option>
				<option value="2" >24所</option>
				<option value="3" >26所</option>
				<option value="4" >44所</option>
			</select>			
		</td>
		<td align="center"><font color="red">*</font>连接网络</td>
		<td>
			<select name="internet_type" id="internet_type">
				<option value="">--请选择--</option>
				<option value="1" >涉密网</option>
				<option value="2" >科研网</option>
				<option value="3" >市政网</option>
				<option value="4" >单机</option>
				<option value="5" >互联网</option>
				<option value="6" >非密专网</option>
				<option value="7" >其他</option>
			</select>
		</td>
		<td align="center"><font color="red">*</font>设备类型</td>
		<td>
			<select name="med_type" id="med_type">
				<option value="">--请选择--</option>
    			<s:iterator value="#request.infoType1" var="type">
					<option value="${type.info_id}">${type.info_type}</option>
				</s:iterator>
    		</select> 
		</td> 	
    </tr> 	
    <tr>
    	<td align="center">操作系统</td>
		<td>
			<input type="text" name="computer_os" id="computer_os"/>
		</td>
		<td align="center">操作系统安装时间</td>
		<td>
			<input type="text" id="install_time" name="install_time" onclick="WdatePicker()" class="Wdate"/>
		</td>
		<td align="center"><font color="red">*</font>硬盘序列号</td>
		<td>
			<input type="text" name="hdisk_no" id="hdisk_no"/>
		</td>
    </tr> 		
    <tr>
    	<td align="center">KEY编号</td>
		<td>
			<input type="text" name="key_code" id="key_code"/>
		</td>
		<td align="center">IP</td>
		<td>
			<input type="text" name="computer_ip" id="computer_ip"/>
		</td>
		<td align="center">MAC</td>
		<td>
			<input type="text" name="computer_mac" id="computer_mac"/>
		</td>
    </tr> 
    <tr>
    	<td align="center">输出端口</td>
		<td colspan="5"><input type="checkbox" name="output_point" value="无"/>无		
	        <input type="checkbox" name="output_point" value="打印机"/>打印机
	        <input type="checkbox" name="output_point" value="扫描仪"/>扫描仪
	        <input type="checkbox" name="output_point" value="单导盒"/>单导盒
	        <input type="checkbox" name="output_point" value="条码枪"/>条码枪
	        <input type="checkbox" name="output_point" value="USB口"/>USB口
	        <input type="checkbox" name="output_point" value="其他"/> 其他			    
		</td>
	</tr>
		<tr>
		<td align="center">存放区域</td>
		<td>
			<select name="storage_area" id="storage_area">
				<option value="">--请选择--</option>
				<option value="西区">西区</option>
				<option value="南区">南区</option>
			</select>			
		</td>
		<td align="center">存放地点</td>
		<td>
			<input type="text" name="storage_location" id="storage_location"/>
		</td>
		<td align="center"><font color="red">*</font>计算机状态</td>
		<td>
			<select name="computer_status" id="computer_status">
				<option value="">--请选择--</option>
				<option value="1">在用</option>
				<option value="2">停用</option>
				<option value="3">维修</option>
				<option value="4">报废</option>
				<option value="5">销毁</option>
			<!-- 	<option value="6">申请维修</option>
				<option value="7">申请报废</option> -->
			</select>
		</td>
	</tr>
     <tr>
		<td align="center">备注</td>
		<td>
			<input type="text" name="summ" id="summ"/>
		</td>
		<td align="center"><font color="red">*</font>安全产品安装情况</td>
		<td colspan="5">
			<s:iterator value="#request.infoTypeSoft5" var="type">
				<input type="checkbox" name="software_type" value="${type.info_id}"/>${type.info_type}&nbsp;&nbsp;
			</s:iterator>
		</td>
    </tr>
     <tr>
    	<td align="center">网关</td>
		<td>
			<input type="text" name="computer_gateway" id="computer_gateway"/>
		</td>
			<td align="center">掩码</td>
		<td>
			<input type="text" name="mark_code" id="mark_code"/>
		</td>
    	<td align="center">VLAN</td>
		<td>
			<input type="text" name="vlan_num" id="vlan_num"/>
		</td>
		
    </tr>
    <tr>
    	<td align="center">交换机</td>
		<td>
			<input type="text" name="switch_num" id="switch_num"/>
		</td>	
    	<td align="center">端口号</td>
		<td colspan="5">
			<input type="text" name="switch_point" id="switch_point"/>
		</td>
    </tr>  
    <tr>
    	<td colspan="6" align="center">
    	<input type="button" value="计算机批量录入" class="button_2003" onclick="go('${ctx}/computer/importcomputer.action');">
    	</td>
    </tr>
    <tr>
        <td colspan="6" align="center" class="bottom_box">
            <input type="submit" value="添加" class="button_2003" onclick="return chk();">&nbsp;
            <input type="reset" value="重置" class="button_2003">
        </td>
    </tr>
  	</table>
</form>
</center>
</body>
</html>