<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
 	<script type="text/javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
<script>
<!--
$(document).ready(function(){
	onHover();
	prepareBizApproval("${process.step_dept}","${process.step_role}","${process.step_dept_name}","${process.step_role_name}","${ctx}");
	if($("#tr_approver option").size()>0){
		$("#tr_approver").show();
	}
});
function chkSubmit(){
	if($("#seclv_code").val() == ""){
		alert("请选择作业审批密级,以确认审批流程");
		$("#seclv_code").focus();
		return false;
	}
	if($("#comment").val() == ""){
		alert("请在借用说明中填写为何借用该文档及用途");
		$("#comment").focus();
		return false;
	}
		
	if($("#next_approver_all option").size() > 0 && $("#next_approver_sel option").size() == 0){
		alert("请选择审批人员");
		$("#next_approver_all").focus();
		return false;
	}
	var next_approver = "";
	$("#next_approver_sel option").each(function(){
		next_approver += this.value+",";
	});
	var len = next_approver.length;
	if (next_approver.lastIndexOf(",") == len - 1){
		next_approver = next_approver.substring(0, len - 1);
	}
	$("#next_approver").val(next_approver);
	var url = "${ctx}/arch/addarchprocessjob.action";
	callServerPostForm1(url,document.forms[0]);
	$("#submit_btn").attr("disabled",true);
    return true;
}
function getAjaxResult1(){
	if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
		if(xmlHttp.responseText == "ok"){
			alert("任务添加完成");
			window.location="${ctx}/arch/viewarchbyuser.action";
		}else{
			alert(xmlHttp.responseText);
			$("#submit_btn").attr("disabled",false);
		}
	}
}
function selectSeclv(seclv){
	if(seclv== ""){
		alert("请选择作业审批密级,以确认审批流程");
		$("#seclv_code").focus();
		return false;
	}else {
		del_all_True('next_approver_sel');
		var url = "${ctx}/arch/getnextapprover.action";
		callServerPostForm(url,document.forms[0]);
	}
}
function getAjaxResult(){
	if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
		if(xmlHttp.responseText.indexOf("<SELECT") != -1){
			$("#tr_approver").show();
			document.getElementById("allApprovers").innerHTML=xmlHttp.responseText;
			$("#submit_btn").attr("disabled",false);
		}else{
			$("#tr_approver").hide();
			alert(xmlHttp.responseText);
			$("#submit_btn").attr("disabled",true);
		}
		if($("#next_approver_all").size() > 0 && $("#next_approver_all").children().size() == 0){
			$("#tr_approver").hide();
		}
	}
}

//-->
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
<form action="${ctx}/basic/addarchprocessjob.action" method="post">
	<input type="hidden" name="submit" value="Y"/>
	<input type="hidden" name="event_codes" value="${event_codes}"/>
	<input type="hidden" name="dept_id" value="${curUser.dept_id}"/>
	<input type="hidden" name="jobType" value="BRWARCH"/>
	<input type="hidden" name="next_approver" id="next_approver"/>
	<input type="hidden" name="barcodemedia" id="barcodemedia" value="${barcodemedia}"/>
	<input type="hidden" name="filename" id="filename" value="${filename}"/>
	<tr>
	    <td colspan="6" class="title_box">生成审批单</td>
	</tr>
	<tr> 
    	<td width="10%" align="center">申请用户： </td>
    	<td width="23%"><font color="blue"><b>${curUser.user_name}</b></font></td>
    	<td width="10%" align="center">用户部门： </td>
    	<td width="23%"><font color="blue"><b>${curUser.dept_name}</b></font></td>
       	<td align="center"><font color="red">*</font>&nbsp;作业审批密级：</td>
	    <td>
			<select name="seclv_code" id="seclv_code" onchange="selectSeclv(this.value);">
				<option value="">--请选择--</option>
				<s:iterator value="#request.seclvList" var="item">
					<option value="${item.seclv_code}">${item.seclv_name}</option>
				</s:iterator>
			</select>
		</td>
	</tr>
			<tr> 
		    	<td align="center">&nbsp;载体类型：</td>
				<td><font color="blue"><b>&nbsp;${entity_type_name}</b></font></td>
    			<td align="center">&nbsp;条码：</td>
	    		<td><font color="blue"><b>&nbsp;${arch.barcode}</b></font></td>
    			<td align="center"><font color="red">*</font>&nbsp;档号： </td>
    			<td><font color="blue"><b>&nbsp;${arch.arch_num}</b></font></td>
			</tr>
			<tr>
			<td align="center"><font color="red">*</font>&nbsp;档案类型： </td>
			 <td>
    			<select name="arche_type" id="arche_type" >				
					<option value="1">电子档案</option>
					<option value="2">纸质档案</option>
			     </select>
			   </td>
  				<td align="center"><font color="red">*</font>&nbsp;借用说明：</td>
  				<td colspan="5"><textarea name="comment" id="comment" rows="3" cols="40"></textarea></td>
  			</tr>		

  	<tr id="tr_approver" style="display: none">
  		<td align="center" id="selApprover1">选择审批人：</td>
  		<td id="selApprover2" colspan="5">
  			<table width="300" border="0" cellspacing="0" cellpadding="0" align="left" class="table_box_border_empty">
				<tr>
					<td id="allApprovers">
						<SELECT ondblclick="add_True('next_approver_all','next_approver_sel');" style="WIDTH: 100px" multiple="true" size="8" id="next_approver_all">
							<c:forEach var="item" items="${userList}" varStatus="status">
								<option value="${item.user_iidd}">${item.user_name}</option>
							</c:forEach>
						</SELECT>
					</td>
					<td aling="center" valign="middle">
						<INPUT class="button_2003" onclick="add_MoreTrue('next_approver_all','next_approver_sel');" type="button" value="增加-->" name="btnAddItem" /><br/>
						<br/>
						<INPUT class="button_2003" onclick="del_MoreTrue('next_approver_sel');" type="button" value="删除<--" name="btnDelItem"><br/>
					</td>
					<td>
						<SELECT size="8" multiple="true" style="WIDTH: 100px" ondblclick="del_True('next_approver_sel');" id="next_approver_sel">
						</SELECT>
					</td>
				</tr>
			</table>
		</td>
  	</tr>
  	
  	<tr>
	    <td colspan="6" align="center"> 
	      <input type="button" class="button_2003" value="提交" onclick="chkSubmit();" id="submit_btn"/>&nbsp;
	      <input class="button_2003" type="button" value="返回" onclick="javascript:history.go(-1)">&nbsp;
	    </td>
	</tr>
	</form>
</table>
</body>
</html>
