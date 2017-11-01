<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
 	<script type="text/javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
		<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
 	<link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
<script>
$(document).ready(function(){
	onHover();
	prepareBizApproval("${process.step_dept}","${process.step_role}","${process.step_dept_name}","${process.step_role_name}","${ctx}");
	init();
	preCalendarDay();
});
function init(){
	if($("#tr_approver option").size()>0){
		$("#tr_approver").show();
	}
}
function preCalendarDay(){
		Calendar.setup({inputField: "factory_date", button: "factory_date_trigger", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d %H:%M:%S", cache: true, weekNumbers:true, showOthers: true, step: 1});
		Calendar.setup({inputField: "use_date", button: "use_date_trigger", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d %H:%M:%S", cache: true, weekNumbers:true, showOthers: true, step: 1});
	}
function selectSeclv(seclv){
	if(seclv == ""){
		alert("请选择作业审批密级,以确认审批流程");
		$("#seclv_code").focus();
		return false;
	}else{
		selectNextApprover();
	}
}
function selectNextApprover(){
	del_all_True('next_approver_sel');
	var url = "${ctx}/basic/getnextapprover.action";
	callServerPostForm(url,document.forms[0]);
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
function chkSubmit()
{	
	var numval = /^[0-9]+([.]{1}[0-9]+){0,1}$/;
	if($("#seclv_code").val() == ""){
		alert("请选择作业密级");
		$("#seclv_code").focus();
		return false;
	}
	if($("#property_name").val().trim() == ""){
		alert("请填写资产名称");
		$("#property_name").focus();
		return false;
	}
	if($("#original_value").val().trim() == ""){
		alert("请填写原值");
		$("#original_value").focus();
		return false;
	}else if($("#original_value").val().trim().substring() == "0"){
		alert("原值不能为零");
		$("#original_value").focus();
		return false;
	}else if(!numval.test( $("#original_value").val().trim())){
	    alert("原值应为整数或小数");
	    $("#original_value").focus();
	    return false;
	}
	if($("#use_date").val() == ""){
		alert("请选择启用日期");
		$("#use_date").focus();
		return false;
	}
	if($("#amount").val() == ""){
		alert("请填写数量");
		$("#amount").focus();
		return false;
	}else if(!isInteger($("#amount").val())){
	    alert("数量只能填写1到10000之间的整数");
	    $("#amount").focus();
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
	$("#hid_form").submit();
}
</script>
</head>
<!-- <body oncontextmenu="self.event.returnValue=false"> -->
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
<form method="post" action="${ctx}/asset/addstoragejob.action" id="hid_form">
	<input type="hidden" name="event_code" value="${event_code}"/>
	<input type="hidden" name="event_code_purchase" value="${event_code_purchase}"/>
 	<input type="hidden" name="jobType" value="${jobType}"/>
	<input type="hidden" name="next_approver" id="next_approver"/>
	<input type="hidden" name="dept_id" value="${curUser.dept_id}"/>

	<tr>
	    <td colspan="6" class="title_box">生成审批单</td>
	</tr>
	<tr> 
    	<td width="13%" align="center">申请用户： </td>
    	<td width="20%"><font color="blue"><b>${curUser.user_name}</b></font></td>
    	<td width="13%" align="center">用户部门： </td>
    	<td width="20%"><font color="blue"><b>${curUser.dept_name}</b></font></td>
    	<td width="13%" align="center"><font color="red">*</font>&nbsp;作业密级：</td>
	    <td width="20%">
			<select id="seclv_code" name="seclv_code" onchange="selectSeclv(this.value);">
				<option value="">--请选择--</option>
				<s:iterator value="#request.seclvList" var="seclv">
					<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
				</s:iterator>
			</select>
		</td>		
	</tr>
	<tr>     
    	<td align="center">&nbsp;资产密级： </td>
    	<td><input type="text" name="property_seclvcode" id="property_seclvcode" value="${event.property_seclvcode_name}" readonly="readonly"/></td> 
    	<td align="center" >&nbsp;资产类型： </td>
    	<td><input type="text" name="property_kind" id="property_kind" value="${event.property_kind}" readonly="readonly"/></td> 
    	<td align="center"><font color="red">*</font>&nbsp;资产名称： </td>
    	<td ><input type="text" name="property_name" id="property_name" value="${event.property_name}"/></td> 
  	</tr> 	
  	<tr>     
    	<td align="center">&nbsp;规格： </td>
    	<td ><input type="text" name="property_standard" id="property_standard" value="${event.property_standard}"/></td> 
    	<td align="center">&nbsp;型号： </td>
    	<td ><input type="text" name="property_type" id="property_type" value="${event.property_type}"/></td> 
    	<td align="center" >&nbsp;国别厂家： </td>
    	<td><input type="text" name="supplier" id="supplier" value="${event.supplier}"/></td> 	
  	</tr> 
  	<tr>   
     	<td align="center" >&nbsp;安装地点： </td>
    	<td><input type="text" name="setup_place" id="setup_place"/></td> 	 
    	<td align="center">&nbsp;出厂编号： </td>
    	<td ><input type="text" name="factory_no" id="factory_no" /></td>   
    	<!--<td align="center">&nbsp;识别码： </td>
    	<td><input type="text" name="identity_code" id="identity_code" /></td>
    	 --> 
<!--     	<td align="center">&nbsp;资产编号： </td>
    	<!--<td ><input type="text" name="property_no" id="property_no"/></td>  -->
    	<!--<td align="center" >&nbsp;凭证号： </td>
    	<td><input type="text" name="voucher_no" id="voucher_no"/></td>
    	--> 	
    	<td align="center">&nbsp;资产管理部门： </td>
    	<td >
    	   <input type="text" name="manage_dept_name" id="manage_dept_name" readonly="readonly" style="cursor:hand" onclick="openDeptSelect('manage_dept_name','be_dept_id','radio')" />
    	   <input type="hidden" id="be_dept_id" name="be_dept_id"/>
    	</td> 
  	</tr> 
  	<tr>     
    	<td align="center"><font color="red">*</font>&nbsp;原值： </td>
    	<td ><input type="text" name="original_value" id="original_value" /></td> 
		<td align="center" ><font color="red">*</font>&nbsp;启用日期： </td>
    	<td>
    		<input type="text" id="use_date" name="use_date" readonly="readonly" style="cursor:hand;"/>
   			<img src="${ctx}/_image/time2.jpg" id="use_date_trigger">
   		</td> 
   		<td align="center">&nbsp;出厂日期： </td>
    	<td>
    		<input type="text" name="factory_date" readonly="readonly" style="cursor:hand;"/>
   			<img src="${ctx}/_image/time2.jpg" id="factory_date_trigger">
    	</td>     	
  	</tr>
  	<tr>    
  		
    	<td align="center">&nbsp;单价： </td>
    	<td><input type="text" name="unit_price" id="unit_price"/></td> 
    	<td align="center"><font color="red">*</font>&nbsp;数量： </td>
    	<td colspan="3"><input type="text" name="amount" id="amount" value="1"/></td> 
    		
  	</tr> 
  	<tr>     
  		 		
  		<td align="center">&nbsp;备注：</td>
		<td colspan="5"><textarea name="summ" rows="3" cols="50" id="summ"></textarea></td>
  	</tr>
		
  	<tr id="tr_approver" style="display: none">
  		<td align="center" id="selApprover1">选择审批人：</td>
  		<td id="selApprover2" colspan="3">
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
