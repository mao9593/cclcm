<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>修改护照信息</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
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
		user_name=user_name.substring(0,user_name.indexOf("/"));
		if(user_id != ""){
			$("#duty_user_iidd").val(user_id);
			$("#duty_user_name").val(user_name);
			document.getElementById("allOptions").innerHTML="";
		}
	}
	function chk()
	{
		
		/*if($("#passport_num").val().trim() == ""){
			alert("请填写证件编号");
			$("#passport_num").focus();
			return false;
		}
		var regx = /^[A-Za-z0-9]*$/;
		if(!regx.test($("#passport_num").val())){
			alert("证件编号必须为字母加数字");
			$("#passport_num").focus();
			return false;
		}*/
		if($("#duty_user_name").val().trim() == ""){
			alert("请填写责任人");
			$("#duty_user_name").focus();
			return false;
		}
		if($("#duty_dept_name").val().trim() == ""){
			alert("请填写责任部门");
			$("#duty_dept_name").focus();
			return false;
		}
		if($("#passport_type").val().trim() == ""){
			alert("请选择证件类型");
			$("#passport_type").focus();
			return false;
		}	
		if($("#passport_status").val().trim() == ""){
			alert("请选择证件状态");
			$("#passport_status").focus();
			return false;
		}
		if($("#issuing_authority").val().trim() == ""){
			alert("请填写签发机关");
			$("#issuing_authority").focus();
			return false;
		}
		if($("#issuing_date").val().trim() == ""){
			alert("请填写签发日期");
			$("#issuing_date").focus();
			return false;
		}
		if($("#ending_date").val().trim() == ""){
			alert("请填写失效日期");
			$("#ending_date").focus();
			return false;
		}
	    return true;
	}
	//-->
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<form method="post" action="${ctx}/passport/updatepassport.action">
	<input type="hidden" value="${passport.passport_num}" name="passport_num" id="passport_num"/>
	<input type="hidden" value="${passport_id}" name="passport_id" id="passport_id"/>
	<input type="hidden" value="Y" name="update"/>
  	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="90%">
	 <tr>
		 <td colspan="6" class="title_box"> 
            	修改护照信息
        </td>
    </tr>
    <input type="hidden" name="user_iidd" id="user_iidd" value="${curUser.user_iidd}"/>
    <tr>
    	<td align="center"><font color="red">*</font>证件编号</td>
		<td>
			<font color="blue"><b>&nbsp;${passport.passport_num}</b></font>
		</td>
		<td align="center"><font color="red">*</font>责任人</td>
		<td>
			<input type="text" id="duty_user_name" name="duty_user_name"  value="${passport.duty_user_name}" onkeyup="selectRecvUser(this.value);"/>
    		<input type="hidden" id="duty_user_iidd" name="duty_user_iidd" value="${passport.duty_user_iidd}"/><br>
    		<div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
		</td>
		<td align="center"><font color="red">*</font>责任部门</td>  <!-- 选定责任人后自动出现责任部门，无需手动填写-->
		<td>
			<input type="text" id="duty_dept_name" name="duty_dept_name"  value="${passport.duty_dept_name}" readonly="readonly" style="cursor:hand" onclick="openDeptSelect('duty_dept_name','duty_dept_id','radio')"/>
    		<input type="hidden" id="duty_dept_id" name="duty_dept_id" value="${passport.duty_dept_id}"/><br>
		</td>
    </tr>  
    <tr>
    	<td align="center">证件类型</td>
		<td>
			<select name="passport_type" id="passport_type">
				<option value="">--请选择--</option>
				<option value="0" <c:if test="${passport.passport_type == '0'}">selected</c:if>>普通护照</option>
				<option value="1" <c:if test="${passport.passport_type == '1'}">selected</c:if> >外交护照</option>
				<option value="2" <c:if test="${passport.passport_type == '2'}">selected</c:if> >公务护照</option>
				<option value="3" <c:if test="${passport.passport_type == '3'}">selected</c:if> >港澳通行证</option>
				<option value="4" <c:if test="${passport.passport_type == '4'}">selected</c:if> >大陆居民来往台湾地区通行证</option>
			</select>
		</td>
		<td align="center"><font color="red">*</font>证件状态</td>
		<td>
			<select name="passport_status" id="passport_status">
				<option value="">--请选择--</option>
				<option value="0" <c:if test="${passport.passport_status == '0'}">selected</c:if>>在册</option>
				<option value="1" <c:if test="${passport.passport_status == '1'}">selected</c:if> >借出</option>
				<option value="2" <c:if test="${passport.passport_status == '2'}">selected</c:if> >删除</option>
			</select>
		</td>
		<td align="center"><font color="red">*</font>签发机关</td>
		<td>
			<input type="text" id="issuing_authority" name="issuing_authority" value="${passport.issuing_authority}"/>
	    </td>
    </tr> 
    <tr>
    	<td align="center"><font color="red">*</font>签发时间</td>
		<td>
			<input type="text" id="issuing_date" name="issuing_date" onclick="WdatePicker()" class="Wdate" value="${passport.issuing_date}"/>
	    </td>
    	<td align="center"><font color="red">*</font>过期时间</td>
		<td>
			<input type="text" id="ending_date" name="ending_date" onclick="WdatePicker()" class="Wdate" value="${passport.ending_date}"/>
	    </td>
	    <td colspan="2">&nbsp;
	    </td>
    </tr>	
    <tr>
    	<td align="center">备注：</td>
		<td colspan="5">
			<textarea name="summ" rows="3" cols="50" id="summ" >${passport.summ}</textarea>
		</td>
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