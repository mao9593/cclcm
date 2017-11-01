<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>修改要害部门部位</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
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
			$("#duty_user_iidd").val(user_id);
			$("#duty_user_name").val(user_name);
			document.getElementById("allOptions").innerHTML="";
		}
	}
	function chk(){
	
	
		if($("#secplace_name").val().trim() == ""){
			alert("请填写害部门部位名称");
			$("#secplace_name").focus();
			return false;
		}
		
		if($("#secplace_location").val().trim() == ""){
			alert("请填写物理位置");
			$("#secplace_location").focus();
			return false;
		}	
		
		if($("#secplace_code").val().trim() == ""){
			alert("请填写要害部门部位编号");
			$("#secplace_code").focus();
			return false;
		}
		
		if($("#secplace_type").val().trim() == ""){
			alert("请输选择要害类别");
			$("#secplace_type").focus();
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
		
		if($("#duty_user_id").val().trim() == ""){
			alert("请输入责任人");
			$("#duty_user_name").focus();
			return false;
		}
		
		if(!checkCode_addword($("#secplace_name").val())){
			$("#secplace_name").focus();
			alert("名称只能由数字、字母、汉字或下划线组成，长度小于30位");
			return false;
		}
		
		if($("#secplace_code").val().length>0 && !checkCode_addword($("#secplace_code").val())){
			$("#secplace_code").focus();
			alert("编号只能由数字、字母、汉字或下划线组成，长度小于30位");
			return false;
		}
		
	    return true;
	}
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<form method="post" action="${ctx}/secplace/updatesecplace.action">
	<input type="hidden" value="${secplace.secplace_barcode}" name="secplace_barcode"/>
	<input type="hidden" value="Y" name="update"/>
  	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="90%">
	 <tr>
		 <td colspan="6" class="title_box"> 
            	修改害部门部位
        </td>
    </tr>
    <input type="hidden" name="user_iidd" id="user_iidd" value="${curUser.user_iidd}"/>
    <tr>
    	<td align="center"><font color="red">*</font>害部门部位名称</td>
		<td>
			<input type="text" name="secplace_name" id="secplace_name" value="${secplace.secplace_name}"/>
		</td>
		<td align="center"><font color="red">*</font>物理位置</td>
		<td>
			<input type="text" name="secplace_location" id="secplace_location" value="${secplace.secplace_location}"/>
		</td>
		<td align="center"><font color="red">*</font>要害部门部位编号</td>
		<td>
			<input type="text" name="secplace_code" id="secplace_code" value="${secplace.secplace_code}"/>
		</td>
    </tr>  
    <tr>
    	<td align="center"><font color="red">*</font>要害类别</td>
		<td>
			<select name="secplace_type" id="secplace_type">
				<option value="">--请选择--</option>
				<option value="1" <c:if test="${secplace.secplace_type == '1'}">selected</c:if> >部门</option>
				<option value="2" <c:if test="${secplace.secplace_type == '2'}">selected</c:if> >部位</option>
			</select>
		</td>
		<td align="center"><font color="red">*</font>要害密级</td>
		<td>
			<select name="seclv_code" id="seclv_code">
				<option value="">--请选择--</option>
				<s:iterator value="#request.seclvList" var="seclv">
					<option value="${seclv.seclv_code}" <c:if test="${secplace.seclv_code == seclv.seclv_code}">selected</c:if>>${seclv.seclv_name}</option>
				</s:iterator>
			</select>
		</td>
		<td align="center"><font color="red">*</font>保密编号</td>
		<td>
			<input type="text" name="conf_code" id="conf_code" value="${secplace.conf_code}"/>
		</td>
    </tr> 
    <tr>
    	
    	<td align="center"><font color="red">*</font>主管领导</td>
		<td >
			<input type="text" id="leader_name" name="leader_name" value="${secplace.leader_name}" onkeyup="selectRecvUser(this.value);"/>
    		<input type="hidden" id="leader_id" name="leader_id"  value="${secplace.leader_id}"/><br>
    		<div id="leaderOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
		</td>
		<td align="center"><font color="red">*</font>责任人</td>
		<td>
			<input type="text" id="duty_user_name" name="duty_user_name" value="${secplace.duty_user_name}" onkeyup="selectRecvUser(this.value);"/>
    		<input type="hidden" id="duty_user_id" name="duty_user_id" value="${secplace.duty_user_id}"/><br>
    		<div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
		</td>
		<td align="center"><font color="red">*</font>责任部门</td>  <!-- 选定责任人后自动出现责任部门，无需手动填写-->
		<td>
			<input type="text" id="duty_dept_name" name="duty_dept_name" value="${secplace.duty_dept_name}" readonly="readonly" style="cursor:hand" onclick="openDeptSelect('duty_dept_name','duty_dept_id','radio')"/>
    		<input type="hidden" id="duty_dept_id" name="duty_dept_id" value="${secplace.duty_dept_id}"/><br>
		</td>
    </tr> 						
    
    <tr>
    	<td align="center">涉密人员数量</td>
		<td>
			<input type="text" name="user_number" id="user_number" value="${secplace.user_number}"/>
		</td>
		<td align="center">成立时间</td>
		<td >
			<input type="text" name="found_time" id="found_time" value="${secplace.found_time}"/>
		</td>
		<td align="center">使用状态</td>
		<td>
			<select name="secplace_status" id="secplace_status">
				<option value="">--请选择--</option>
				<option value="1" <c:if test="${secplace.secplace_status == '1'}">selected</c:if>>在用</option>
				<option value="2" <c:if test="${secplace.secplace_status == '2'}">selected</c:if>>停用</option>
			</select>
		</td>
	</tr> 						
    <tr>
		<td align="center">用途</td>
		<td>
			<input type="text" name="secplace_application" id="secplace_application" value="${secplace.secplace_application}"/>
		</td>
		
    </tr> 
     <tr>
    	<td align="center">人防措施</td>
		<td colspan="5">
			<textarea name="people_protect" rows="3" cols="50" id="people_protect">${secplace.people_protect}</textarea>
		</td>
    </tr> 	
     <tr>
    	<td align="center">物防措施</td>
		<td colspan="5">
			<textarea name="physical_protect" rows="3" cols="50" id="physical_protect">${secplace.physical_protect}</textarea>
		</td>
    </tr> 	
     <tr>
    	<td align="center">技防措施</td>
		<td colspan="5">
			<textarea name="technology_protect" rows="3" cols="50" id="technology_protect">${secplace.technology_protect}</textarea>
		</td>
    </tr> 	
     <tr>
    	<td align="center">备注</td>
		<td colspan="5">
			<textarea name="summ" rows="3" cols="50" id="summ">${secplace.summ}</textarea>
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