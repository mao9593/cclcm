<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>修改计算机</title>
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
		init();
	});
	function init(){
		var soft = "${computer.software_type}";
		var test = soft.split(",");
		var i = 0;
		
		$(":checkbox[name='software_type']").each(function(){
			for(i = 0;i<test.length;i++){
				if(test[i].indexOf(this.value) > -1){
					this.checked = true;
				}
			}
		});
		//输出端口
		var output_point = "${computer.output_point}";
		var test_output_point = output_point.split(",");
	
		$(":checkbox[name='output_point']").each(function(){
			for(var k = 0;k<test_output_point.length;k++){
				if(test_output_point[k].indexOf(this.value) > -1){
					this.checked = true;
				}
			}
		});
	}
	
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
		if($("#computer_name").val().trim() == ""){
			alert("请填写计算机名称");
			$("#computer_name").focus();
			return false;
		}
		if($("#seclv_code").val().trim() == ""){
			alert("请选择密级");
			$("#seclv_code").focus();
			return false;
		}
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
/*
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
		if(!checkCode_addword($("#computer_name").val())){
			$("#computer_name").focus();
			alert("设备名称只能由数字、字母、汉字或下划线组成，长度小于30位");
			return false;
		}
*/
	    return true;
	}
	//-->
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<form method="post" action="${ctx}/computer/updatecomputer.action">
	<input type="hidden" value="${computer.computer_barcode}" name="computer_barcode"/>
	<input type="hidden" name="user_iidd" id="user_iidd" value="${curUser.user_iidd}"/>
	<input type="hidden" name="med_type" id="med_type" value="${computer.med_type}"/>
	<input type="hidden" name="conf_code" id="conf_code" value="${computer.conf_code}"/>
	<input type="hidden" value="Y" name="update"/>
  	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="90%">
	 <tr>
		 <td colspan="6" class="title_box">修改计算机</td>
    </tr>
    <tr>
    	<td align="center"><font color="red">*</font>计算机名称</td>
		<td>
			<input type="text" name="computer_name" id="computer_name" value="${computer.computer_name}"/>
		</td>
		<td align="center">设备类型</td>
		<td><font color="blue"><b>&nbsp;${computer.med_type_name}</b></font></td>

<%-- 		<td>
			<c:set var="med1" value="${computer.med_type}" scope="request"/>
			<select name="med_type" id="med_type">
				<option value="">--请选择--</option>
    			<s:iterator value="#request.infoType1" var="type">
					<option value="${type.info_id}"<s:if test="#request.med1==#type.info_id">selected="selected"</s:if>>${type.info_type}</option>
				</s:iterator>
    		</select> 
		</td>
--%>
		<td align="center"><font color="red">*</font>原保密编号</td>
		<td>
			<input type="text" name="oldconf_code" id="oldconf_code" value="${computer.oldconf_code}"/>
		</td>		
<%-- 	
		<td align="center">型号</td>
		<td>
			<input type="text" name="computer_model" id="computer_model" value="${computer.computer_model}"/>
		</td>
		<td align="center">出厂编号</td>
		<td>
			<input type="text" name="computer_no" id="computer_no" value="${computer.computer_no}"/>
		</td> 
		<td align="center">设备配置</td>
		<td colspan="3">
			<textarea name="computer_detail" rows="3" cols="50" id="computer_detail" >${computer.computer_detail}</textarea>
		</td>		
--%>
    </tr> 		
    <tr>
		<td align="center"><font color="red">*</font>安全产品安装情况</td>
		<td colspan="5">
			<s:iterator value="#request.infoTypeSoft5" var="type">
				<input type="checkbox" name="software_type" value="${type.info_id}"/>${type.info_type}&nbsp;&nbsp;
			</s:iterator>
		</td>    
    </tr>				
    <tr>
    	<td align="center"><font color="red">*</font>硬盘序列号</td>
		<td>
			<input type="text" name="hdisk_no" id="hdisk_no" value="${computer.hdisk_no}"/>
		</td>
    	<td align="center"><font color="red">*</font>资产编号</td>
		<td colspan="3">
			<input type="text" name="computer_code" id="computer_code" value="${computer.computer_code}"/>
		</td>

    </tr> 						
    <tr>		
		<td align="center"><font color="red">*</font>保密编号</td>
		<td><font color="blue"><b>&nbsp;${computer.conf_code}</b></font></td>
		<%-- <td><input type="text" name="conf_code" id="conf_code" value="${computer.conf_code}"/></td> --%>
		<td align="center"><font color="red">*</font>密级</td>
		<td>
			<select name="seclv_code" id="seclv_code">
				<option value="">--请选择--</option>
				<s:iterator value="#request.seclvList" var="seclv">
					<option value="${seclv.seclv_code}" <c:if test="${computer.seclv_code == seclv.seclv_code}">selected</c:if> >${seclv.seclv_name}</option>
				</s:iterator>
			</select>
		</td>
		<td align="center">网络类型</td>
		<td>
			<select name="internet_type" id="internet_type">
				<option value="">--请选择--</option>
				<option value="1" <c:if test="${computer.internet_type == '1'}">selected</c:if> >涉密网</option>
				<option value="2" <c:if test="${computer.internet_type == '2'}">selected</c:if> >科研网</option>
				<option value="3" <c:if test="${computer.internet_type == '3'}">selected</c:if> >市政网</option>
				<option value="4" <c:if test="${computer.internet_type == '4'}">selected</c:if> >单机</option>
				<option value="5" <c:if test="${computer.internet_type == '5'}">selected</c:if> >互联网</option>
				<option value="6" <c:if test="${computer.internet_type == '6'}">selected</c:if> >非密专网</option>
				<option value="7" <c:if test="${computer.internet_type == '7'}">selected</c:if> >其他</option>
			</select>
		</td>		
    </tr> 
    <tr>
    	<td align="center"><font color="red">*</font>责任人</td>
		<td>
			<input type="text" id="duty_user_name" name="duty_user_name"  value="${computer.duty_user_name}" onkeyup="selectRecvUser(this.value);"/>
    		<input type="hidden" id="duty_user_id" name="duty_user_id" value="${computer.duty_user_id}"/><br>
    		<div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
		</td>
		<td align="center"><font color="red">*</font>责任部门</td>  <!-- 选定责任人后自动出现责任部门，无需手动填写-->
		<td>
			<input type="text" id="duty_dept_name" name="duty_dept_name" value="${computer.duty_dept_name}" readonly="readonly" style="cursor:hand" onclick="openDeptSelect('duty_dept_name','duty_dept_id','radio')"/>
    		<input type="hidden" id="duty_dept_id" name="duty_dept_id" value="${computer.duty_dept_id}"/><br>
		</td>
		<td align="center">责任单位</td>
		<td>
			<select name="duty_entp_id" id="duty_entp_id">
				<option value="">--请选择--</option>
				<option value="1" <c:if test="${computer.duty_entp_id == '1'}">selected</c:if> >总部</option>
				<option value="2" <c:if test="${computer.duty_entp_id == '2'}">selected</c:if> >24所</option>
				<option value="3" <c:if test="${computer.duty_entp_id == '3'}">selected</c:if> >26所</option>
				<option value="4" <c:if test="${computer.duty_entp_id == '4'}">selected</c:if> >44所</option>
			</select>			
		</td> 
    </tr> 
    
    <tr>
    	<td align="center">操作系统</td>
		<td>
			<input type="text" name="computer_os" id="computer_os" value="${computer.computer_os}"/>
		</td>
		<td align="center">操作系统安装时间</td>
		<td>
			<input type="text" id="install_time" name="install_time" onclick="WdatePicker()" class="Wdate" value="${computer.install_time_str}"/>
		</td>
		<td align="center">KEY编号</td>
		<td>
			<input type="text" name="key_code" id="key_code" value="${computer.key_code}"/>
		</td>
    </tr> 
    
    <tr>
    	<td align="center">IP</td>
		<td>
			<input type="text" name="computer_ip" id="computer_ip" value="${computer.computer_ip}"/>
		</td>
		<td align="center">掩码</td>
		<td>
			<input type="text" name="mark_code" id="mark_code" value="${computer.mark_code}"/>
		</td>
		<td align="center">MAC</td>
		<td>
			<input type="text" name="computer_mac" id="computer_mac" value="${computer.computer_mac}"/>
		</td>
	</tr> 
    <tr>
		<td align="center">输出端口</td>
		<td>
			<input type="checkbox" name="output_point" value="无"/>无
			<input type="checkbox" name="output_point" value="打印机"/>打印机
	        <input type="checkbox" name="output_point" value="扫描仪"/>扫描仪
	        <input type="checkbox"  name="output_point" value="单导盒"/>单导盒
	        <input type="checkbox" name="output_point" value="条码枪"/>条码枪
	        <input type="checkbox" name="output_point" value="USB口"/>USB口
	        <input type="checkbox" name="output_point" value="其他"/> 其他		
		</td>
		<td align="center">VLAN</td>
		<td>
			<input type="text" name="vlan_num" id="vlan_num" value="${computer.vlan_num}"/>
		</td>
		<td align="center">网关</td>
		<td>
			<input type="text" name="computer_gateway" id="computer_gateway" value="${computer.computer_gateway}"/>
		</td>
    </tr> 
    
    <tr>
    	<td align="center">计算机状态</td>
		<td>
			<select name="computer_status" id="computer_status">
				<option value="">--请选择--</option>
				<option value="1" <c:if test="${computer.computer_status == '1'}">selected</c:if>>在用</option>
				<option value="2" <c:if test="${computer.computer_status == '2'}">selected</c:if> >停用</option>
				<option value="3" <c:if test="${computer.computer_status == '3'}">selected</c:if> >维修</option>
				<option value="4" <c:if test="${computer.computer_status == '4'}">selected</c:if> >报废</option>
				<option value="5" <c:if test="${computer.computer_status == '5'}">selected</c:if> >销毁</option>
				<option value="6" <c:if test="${computer.computer_status == '6'}">selected</c:if>>申请维修</option>
				<option value="7" <c:if test="${computer.computer_status == '7'}">selected</c:if>>申请报废</option>
			</select>
		</td>    	
		<td align="center">交换机</td>
		<td>
			<input type="text" name="switch_num" id="switch_num" value="${computer.switch_num}"/>
		</td>
		<td align="center">端口号</td>
		<td>
			<input type="text" name="switch_point" id="switch_point" value="${computer.switch_point}"/>
		</td>
    </tr> 
    
     <tr>
		<td align="center">存放区域</td>
		<td>
			<input type="text" name="storage_area" id="storage_area" value="${computer.storage_area}"/>
		</td>
		<td align="center">存放位置</td>
		<td>
			<input type="text" name="storage_location" id="storage_location" value="${computer.storage_location}"/>
		</td>
		<td align="center">备注</td>
		<td>
			<input type="text" name="summ" id="summ" value="${computer.summ}"/>
		</td>		
	</tr> 
    
     <tr>
<%-- 		
		<td align="center">计算机用途</td>
		<td>
			<input type="text" name="computer_application" id="computer_application" value="${computer.computer_application}"/>
		</td>
		<td align="center">启用时间</td>
		<td>
			<input type="text" id="enable_time" name="enable_time" onclick="WdatePicker()" class="Wdate" value="${computer.enable_time}"/>
		</td>
--%>
    </tr> 
    <tr>
        <td colspan="6" align="center" class="bottom_box">
            <input type="submit" value="保存" class="button_2003" onclick="return chk();">&nbsp;
            <input type="button" value="返回" class="button_2003" onclick="history.go(-1);">
        </td>
    </tr>
  	</table>
</form>
</center>
</body>
</html>