<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
 	<script type="text/javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
<script>
$(document).ready(function(){
	onHover();
	init();
});
function init(){
	if('${send_way}' == 0){$("#hid").hide();}
}
function chkSubmit()
{	
	if($("#send_way").val().trim() == 1) {
		if($("#output_confidential_num").val().trim()==""){
			alert("机要号不能为空，请重新输入");
			$("#output_confidential_num").focus();
			return false;
		}
		if($("#output_confidential_num").val().trim().length > 100){
			alert("请填写正确的机要号（长度过大）");
			$("#output_confidential_num").focus();
			return false;
		}
	}
	if($("#carryoutInfo").val().trim() == ""){
		alert("请填写带回说明");
		$("#carryoutInfo").focus();
		return false;
	}
	$("#hid_form").submit();
}
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
<form method="post" action="${ctx}/ledger/handlecarryinconfirmjob.action" id="hid_form">
	<input type="hidden" name="updateFlag" value="Y"/>
	<input type="hidden" name="id" value="${eventCarryOut.id }">
	<input type="hidden" name="entity_type" value="${eventCarryOut.entity_Type }" />
	<input type="hidden" name="job_Code" value="${eventCarryOut.job_Code }" />
	<input type="hidden" name="barcode" value="${eventCarryOut.barcode }" />
	<input type="hidden" name="send_way" id="send_way" value="${send_way}"/>
	<tr>
	    <td colspan="6" class="title_box">载体外带说明</td>
	</tr>
	<tr> 
    	<td width="15%" align="center">申请用户： </td>
    	<td width="35%"><font color="blue"><b>${secUser.user_name }</b></font></td>
    	<td width="15%" align="center">用户部门： </td>
    	<td width="35%"><font color="blue"><b>${secUser.dept_name }</b></font></td>
    </tr>
    <tr id='hid'> 
		<td align="center"><font color="red">*</font>&nbsp;外带机要号：</td>
    	<td colspan="3">
    		<input type="text" id="output_confidential_num" name="output_confidential_num" value="" size="32"/>
    	</td>
    </tr>
	<tr> 
		<td align="center"><font color="red">*</font>带回说明： </td>
		<td colspan="3">
		    <textarea rows="2"  id="carryoutInfo" cols="60" name="carryinInfo"></textarea>
		</td>
	</tr>
</form>  	
  	<tr>
	    <td colspan="4" align="center"> 
	      <input type="button" class="button_2003" value="提交" onclick="chkSubmit();" id="submit_btn"/>&nbsp;
	      <input class="button_2003" type="button" value="返回" onclick="javascript:history.go(-1)">&nbsp;
	    </td>
	</tr>
</table>
</body>
</html>
