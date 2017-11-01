<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>修改硬盘</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
	<link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
    <script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script language="javascript" src="${ctx}/_script/My97DatePicker/WdatePicker.js"></script>
	<script language="JavaScript" >
	
	$(document).ready(function(){
		onHover();
		disableEnterSubmit();
		document.getElementById("allOptions").innerHTML="";
		init();
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

	    return true;
	}
	//-->
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<form method="post" action="${ctx}/computer/updateharddisk.action">
	<input type="hidden" value="${hdisk.hdisk_no}" name="hdisk_no"/>
	<input type="hidden" name="user_iidd" id="user_iidd" value="${curUser.user_iidd}"/>
	<input type="hidden" name="conf_code" id="conf_code" value="${hdisk.conf_code}"/>
	<input type="hidden" value="Y" name="update"/>
  	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="90%">
	 <tr>
		 <td colspan="6" class="title_box">修改硬盘</td>
    </tr>
     <tr>
    	<td align="center"><font color="red">*</font>责任人</td>
		<td>
			<input type="text" id="duty_user_name" name="duty_user_name"  value="${hdisk.duty_user_name}" onkeyup="selectRecvUser(this.value);"/>
    		<input type="hidden" id="duty_user_id" name="duty_user_id" value="${hdisk.duty_user_id}"/><br>
    		<div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
		</td>
		<td align="center"><font color="red">*</font>责任部门</td>  <!-- 选定责任人后自动出现责任部门，无需手动填写-->
		<td>
			<input type="text" id="duty_dept_name" name="duty_dept_name" value="${hdisk.duty_dept_name}" readonly="readonly" style="cursor:hand" onclick="openDeptSelect('duty_dept_name','duty_dept_id','radio')"/>
    		<input type="hidden" id="duty_dept_id" name="duty_dept_id" value="${hdisk.duty_dept_id}"/><br>
		</td>
		<td align="center">责任单位</td>
		<td>
			<select name="duty_entp_id" id="duty_entp_id" value="${hdisk.duty_entp_name}">
				<option value="">--请选择--</option>
				<option value="1" <c:if test="${hdisk.duty_entp_id == '1'}">selected</c:if> >总部</option>
				<option value="2" <c:if test="${hdisk.duty_entp_id == '2'}">selected</c:if> >24所</option>
				<option value="3" <c:if test="${hdisk.duty_entp_id == '3'}">selected</c:if> >26所</option>
				<option value="4" <c:if test="${hdisk.duty_entp_id == '4'}">selected</c:if> >44所</option>
			</select>			
		</td> 
    </tr> 
    <tr>
    	<td align="center"><font color="red">*</font>计算机保密编号</td>
    	<td><font color="blue"><b>&nbsp;${hdisk.conf_code}</b></font></td>
		<td align="center">硬盘序列号</td>
			<td><font color="blue"><b>&nbsp;${hdisk.hdisk_no}</b></font></td>
		<td align="center"><font color="red">*</font>回收人</td>
		<td><font color="blue"><b>&nbsp;${hdisk.retrieve_user_name}</b></font></td>	
    </tr> 		
     <tr>
    	<td align="center"><font color="red">*</font>回收时间</td>
    	<td><font color="blue"><b>&nbsp;${hdisk.retrieve_time_str}</b></font></td>
		<td align="center">备注</td>
		<td>
			<input type="text" name="summ" id="summ" value="${hdisk.summ}"/>
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