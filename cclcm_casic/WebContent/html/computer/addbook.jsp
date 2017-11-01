<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
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
		if("${med_type}" == ""){
			alert("配置管理中信息设备管理中计算机类未配置笔记本，请联系保密管理员配置");
			$("#update").attr("disabled",true);
			$("#import").attr("disabled",true);
		}
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
/* 		if($("#internet_type").val().trim() == ""){
			alert("请选择网络类型");
			$("#internet_type").focus();
			return false;
		}
		if($("#computer_status").val().trim() == ""){
			alert("请选择计算机状态");
			$("#computer_status").focus();
			return false;
		}	 */

	    return true;
	}

	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<form method="post" action="${ctx}/computer/addbook.action" >
<input type="hidden" name="user_iidd" id="user_iidd" value="${curUser.user_iidd}"/>
<input type="hidden" name="med_type" id="med_type" value="${med_type}"/>
  	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="90%">
	 <tr>
		 <td colspan="6" class="title_box">录入现有笔记本台账</td>
    </tr>
    <tr>
    	<td align="center"><font color="red">*</font>保管人</td>
		<td>
			<input type="text" id="duty_user_name" name="duty_user_name" onkeyup="selectRecvUser(this.value);"/>
    		<input type="hidden" id="duty_user_id" name="duty_user_id"/><br>
    		<div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
		</td>
		<td align="center"><font color="red">*</font>使用部门</td>
		<td>
			<input type="text" id="duty_dept_name" name="duty_dept_name" readonly="readonly" style="cursor:hand" onclick="openDeptSelect('duty_dept_name','duty_dept_id','radio')"/>
    		<input type="hidden" id="duty_dept_id" name="duty_dept_id"/><br>
		</td>
		<td align="center">单位</td>
		<td>
			<select name="duty_entp" id="duty_entp">
				<option value="">--请选择--</option>
				<option value="总部" >总部</option>
				<option value="24所" >24所</option>
				<option value="26所" >26所</option>
				<option value="44所" >44所</option>
			</select>			
		</td> 
    </tr>     
    <tr>

		<td align="center"><font color="red">*</font>硬盘序列号</td>
		<td>
			<input type="text" name="hdisk_no" id="hdisk_no"/>
		</td>
    	<td align="center"><font color="red">*</font>统一编号</td>
		<td>
			<input type="text" name="book_code" id="book_code" />
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
		<td align="center">联网情况</td>
		<td>
			<select name="internet_type" id="internet_type">
				<option value="">--请选择--</option>
				<option value="涉密网" >涉密网</option>
				<option value="科研网" >科研网</option>
				<option value="市政网" >市政网</option>
				<option value="单机" >单机</option>
				<option value="互联网" >互联网</option>
				<option value="非密专网" >非密专网</option>
				<option value="其他" >其他</option>
			</select>
		</td>		
    	<td align="center"><font color="red">*</font>笔记本状态</td>
		<td>
			<select name="book_status" id="book_status">
				<option value="">--请选择--</option>
				<option value="1">在用</option>
				<option value="6">已借出</option>
				<option value="2">停用</option>
				<option value="3">维修</option>
				<option value="4">报废</option>
				<option value="5">销毁</option>
			</select>
		</td>    
    </tr> 		
   <tr>
		<td align="center">名称型号</td>
		<td>
			<input type="text" id="book_model" name="book_model"/>
		</td>
		<!-- <td align="center">使用情况</td>
		<td>
			<input type="text" name="useinfo" id="useinfo"/>
		</td> -->
		<td align="center">外出情况</td>
		<td colspan="3">
			<input type="text" name="outsideinfo" id="outsideinfo"/>
		</td>
   </tr>
    <tr>
		<td align="center">系统时间</td>
		<td>
			<input type="text" id="book_os" name="book_os" onclick="WdatePicker()" class="Wdate"/>
		</td>
		<td align="center">MAC地址</td>
		<td>
			<input type="text" name="book_mac" id="book_mac"/>
		</td>
		<td align="center">存放地点</td>
		<td>
			<input type="text" name="storage_location" id="storage_location"/>
		</td>		
    </tr> 
     <tr>
     	<td align="center">输出情况</td>
		<td>
			<input type="text" name="output_point" id="output_point"/>
		</td>
		<td align="center">存储涉密信息情况</td>
		<td>
			<input type="text" name="storage_secinfo" id="storage_secinfo"/>
		</td>
		<td align="center">备注</td>
		<td colspan="3">
			<input type="text" name="detail" id="detail"/>
		</td>
    </tr> 
     <tr>
    	<td colspan="6" align="center">
    		<input id="import" type="button" value="笔记本批量录入" class="button_2003" onclick="go('${ctx}/computer/importbook.action?med_type=${med_type}');">
    	</td>
    </tr>
    <tr>
        <td colspan="6" align="center" class="bottom_box">
            <input type="submit" value="添加" class="button_2003" onclick="return chk();" id="update">&nbsp;
            <input type="reset" value="重置" class="button_2003">
        </td>
    </tr>
  	</table>
</form>
</center>
</body>
</html>