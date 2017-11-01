<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>   
 
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
	<link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
	<script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
<script>


<!--
$(document).ready(function(){
	onHover();
	optionSelect();
	document.getElementById("allOptions").innerHTML="";
});
function optionSelect(){
	var usage_code= "${usage_code}";
	var accept_user_iidd= "${accept_user_iidd}";
	var summ = "${summ}";
	$("#accept_user_iidd").val(accept_user_iidd);
	$("#summ").val(summ);
}
function chk()
{		
	if($("#accept_user_iidd").val().trim() == ""){
		alert("请选择添加代理人");
		$("#accept_user_name").val("");
		$("#accept_user_name").focus();
		return false;
	}
    return true;
}

function selectRecvUser(word){
	$("#accept_user_iidd").val(" ");
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
		$("#accept_user_iidd").val(user_id);
		$("#accept_user_name").val(user_name);
		document.getElementById("allOptions").innerHTML="";
	}
}
function getAjaxResult(){
	if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
		if(xmlHttp.responseText.indexOf("<SELECT") != -1){
			document.getElementById("allApprovers").innerHTML=xmlHttp.responseText;
			document.getElementById("submit_btn").disabled = false;
		}else{
			alert(xmlHttp.responseText);
			document.getElementById("submit_btn").disabled = true;
		}
	}
}

//-->
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<form action="${ctx}/transfer/submitupdatepapertransferevent.action" method="POST">
	<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
		<input type="hidden" name="event_code" value="${event_code}" id="event_code"/>
		<input type="hidden" name="seclv_code" id="hid_seclv_code"/>
		<input type="hidden" name="id" value="${id}" id="id"/>
	<tr>
	    <td colspan="6" class="title_box">添加流转作业</td>
	</tr>
	<tr> 
    	<td width="15%" align="center">申请用户： </td>
    	<td width="33%"><font color="blue"><b>${curUser.user_name}&nbsp;</b></font></td>
    	<td width="15%" align="center">用户部门： </td>
    	<td width="33%"><font color="blue"><b>${curUser.dept_name}&nbsp;</b></font></td>
    </tr>
	<tr>
    	<td align="center">流水号： </td>
    	<td><font color="blue"><b>${transfer.event_code}&nbsp;</b></font></td>	 
		<td align="center">&nbsp;作业密级：</td>
	    <td><font color="blue"><b>${transfer.seclv_name}&nbsp;</b></font></td>
	</tr>
	<tr>
    	<td align="center"><font color="red">*</font>&nbsp;接收人：</td>
    	<td>
    		<input type="text" id="accept_user_name" name="accept_user_name" value="${accept_user_name}" onkeyup="selectRecvUser(this.value);" size="40"/>
    		<input type="hidden" id="accept_user_iidd" name="accept_user_iidd" size="10"/>
    		 <br>
    		 <div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
    		 </div>
	    </td>
    	<td align="center">&nbsp;备注：</td>
		<td><textarea name="summ" rows="3" cols="60" id="summ" value="${summ}"></textarea></td>
    </tr>
  	<tr>
    <td colspan="6" align="center"> 
      <input type="button" class="button_2003" value="提交" onclick="if(chk()) forms[0].submit();" id="submit_btn"/>&nbsp;
      <input class="button_2003" type="button" value="返回" onClick="javascript:history.go(-1);">&nbsp;
    </td>
  </tr>
  </table>
</form>
</body>
</html>
