<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>添加已有要害部门部位</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
	<link type="text/css" rel="stylesheet" href="${ctx}/uploadify/uploadify.css"/>
    <link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
    <script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script type="text/javascript" src="${ctx}/uploadify/jquery.uploadify.min.js"></script>
	<script  language="JavaScript" >
	$(document).ready(function(){
		onHover();
		preCalendarDay();
	});

//模糊匹配人员
	function selectRecvUser(word){
		var url = "${ctx}/basic/getfuzzyuser.action?source=CR";
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
	function add_TrueCR(){
		var user_id=$("#allOptionCR").val();
		var user_name=$("#allOptionCR option[value='"+user_id+"']").text();
		if(user_id != ""){
			$("#duty_user_id").val(user_id);
			$("#duty_user_name").val(user_name);
			document.getElementById("allOptions").innerHTML="";
		}
	}
//end
//时间插件
function preCalendarDay(){
		Calendar.setup({inputField: "found_time", button: "found_time_trigger", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d %H:%M:%S", cache: true, weekNumbers:true, showOthers: true, step: 1});
}
//end

	function chk()
	{
		if($("#secplace_name").val().trim() == ""){
			alert("请填写要害部门部位名称");
			$("#secplace_name").focus();
			return false;
		}
		if($("#secplace_location").val().trim() == ""){
			alert("请填写物理位置");
			$("#secplace_location").focus();
			return false;
		}	
		
		if($("#secplace_type").val().trim() == ""){
			alert("请输选择要害类别");
			$("#secplace_type").focus();
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
		if($("#secplace_application").val().trim() == ""){
			alert("请输入用途");
			$("#secplace_application").focus();
			return false;
		}
		if($("#people_protect").val().trim() == ""){
			alert("请输入人防措施");
			$("#people_protect").focus();
			return false;
		}
		if($("#physical_protect").val().trim() == ""){
			alert("请输入物防措施");
			$("#physical_protect").focus();
			return false;
		}
		if($("#technology_protect").val().trim() == ""){
			alert("请输入技防措施");
			$("#technology_protect").focus();
			return false;
		}
		if(!checkCode_addword($("#secplace_name").val())){
			$("#secplace_name").focus();
			alert("名称只能由数字、字母、汉字或下划线组成，长度小于30位");
			return false;
		}
		if($("#secplace_code").val().trim() == ""){
			alert("请输入要害部门部位编号");
			$("#secplace_code").focus();
			return false;
		}
		if($("#conf_code").val().trim() == ""){
			alert("请输入保密编号");
			$("#conf_code").focus();
			return false;
		}
		if($("#found_time").val().trim() == ""){
			alert("请输成立时间");
			$("#found_time").focus();
			return false;
		}
	
	    return true;
	}
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<form action="${ctx}/secplace/deluploadedfile.action" method="POST" id="hiddenDelFileForm">
  	<input type="hidden" name="event_code" value="${event_code}"/>
  	<input type="hidden" name="file_name" id="del_file_name"/>
  	<input type="hidden" name="file_type" id="file_type"/>
</form>
<form method="post" action="${ctx}/secplace/addsecplace.action">
  	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="90%">
	 <tr>
		 <td colspan="6" class="title_box"> 
            	添加已有要害部门部位
        </td>
    </tr>
    <input type="hidden" name="user_iidd" id="user_iidd" value="${curUser.user_iidd}"/>
    <tr>
    	<td align="center"><font color="red">*</font>要害部门部位名称</td>
		<td>
			<input type="text" name="secplace_name" id="secplace_name"/>
		</td>
		<td align="center"><font color="red">*</font>要害部门部位编号</td>
		<td>
			<input type="text" name="secplace_code" id="secplace_code"/>
		</td>
		<td align="center"><font color="red">*</font>物理位置</td>
		<td>
			<input type="text" name="secplace_location" id="secplace_location"/>
		</td>
		
    </tr>  
    <tr>
    	<td align="center"><font color="red">*</font>要害类别</td>
		<td>
			<select name="secplace_type" id="secplace_type">
				<option value="">--请选择--</option>
				<option value="1">部门</option>
				<option value="2">部位</option>
			</select>
		</td>
		<td align="center"><font color="red">*</font>要害密级</td>
		<td>
			<select id="seclv_code" name="seclv_code">
				<option value="">--请选择--</option>
				<s:iterator value="#request.seclvList" var="seclv">
					<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
				</s:iterator>
			</select>
		</td>
		<td align="center"><font color="red">*</font>保密编号</td>
		<td>
			<input type="text" name="conf_code" id="conf_code"/>
		</td>
		
    </tr> 
    
    <tr>
		<td align="center"><font color="red">*</font>责任部门</td>  <!-- 选定责任人后自动出现责任部门，无需手动填写-->
		<td>
			<input type="text" id="duty_dept_name" name="duty_dept_name" readonly="readonly" style="cursor:hand" onclick="openDeptSelect('duty_dept_name','duty_dept_id','radio')"/>
    		<input type="hidden" id="duty_dept_id" name="duty_dept_id"/><br>
		</td>
		<td align="center"><font color="red">*</font>责任人</td>
		<td>
			<input type="text" id="duty_user_name" name="duty_user_name" onkeyup="selectRecvUser(this.value);"/>
    		<input type="hidden" id="duty_user_id" name="duty_user_id"/><br>
    		<div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
		</td>
		<td align="center">涉密人员数量</td>
		<td>
			<input type="text" name="user_number" id="user_number"/>
		</td>
	</tr> 
	<tr>
		<td align="center"><font color="red">*</font>成立时间</td>
		<td >
			<input type="text" id="found_time" name="found_time" readonly="readonly" style="cursor:hand;" value="${found_time}"/>
       	 	<img src="${ctx}/_image/time2.jpg" id="found_time_trigger">
		</td>
		<td align="center">使用状态</td>
		<td>
			<select name="secplace_status" id="secplace_status">
				<option value="">--请选择--</option>
				<option value="1">在用</option>
				<option value="2">停用</option>
			</select>
		</td>
		<td align="center"> &nbsp;</td>
		<td align="center"> &nbsp;</td>
	</tr>						
    <tr>
    	<td align="center"><font color="red">*</font>用途</td>
    	<td colspan="5">
			<textarea name="secplace_application" rows="3" cols="50" id="secplace_application"></textarea>
		</td>
    </tr>
     <tr>
    	<td align="center"><font color="red">*</font>人防措施</td>
		<td colspan="5">
			<textarea name="people_protect" rows="3" cols="50" id="people_protect"></textarea>
		</td>
    </tr> 	
     <tr>
    	<td align="center"><font color="red">*</font>物防措施</td>
		<td colspan="5">
			<textarea name="physical_protect" rows="3" cols="50" id="physical_protect"></textarea>
		</td>
    </tr> 	
     <tr>
    	<td align="center"><font color="red">*</font>技防措施</td>
		<td colspan="5">
			<textarea name="technology_protect" rows="3" cols="50" id="technology_protect"></textarea>
		</td>
    </tr> 	
     <tr>
    	<td align="center">备注</td>
		<td colspan="5">
			<textarea name="summ" rows="3" cols="50" id="summ"></textarea>
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