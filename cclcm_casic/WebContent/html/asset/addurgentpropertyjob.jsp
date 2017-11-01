<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
	<script language="JavaScript" src="${ctx}/_script/function.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
 	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>	
 	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
 	<script language="JavaScript" src="${ctx}/_script/common.js"></script>
<script>
$(document).ready(function(){
	onHover();
	selectNextApprover();
});
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
function chk(){	
	if($("#seclv_code").val() == ""){
		alert("请选择密级");
		$("#seclv_code").focus();
		return false;
	}
	if($("#property_seclvcode").val().trim() == ""){
		alert("请选择资产密级");
		$("#property_seclvcode").focus();
		return false;
	}
	if($("#property_kind").val() == ""){
		alert("请填写资产种类");
		$("#property_kind").focus();
		return false;
	}
	if($("#property_name").val().trim() == ""){
		alert("请填写资产名称");
		$("#property_name").focus();
		return false;
	}
	if($("#amount").val() == ""){
		alert("请填写采购数量");
		$("#amount").focus();
		return false;
	}else if(!isInteger($("#amount").val())){
	    alert("数量只能填写1到10000之间的整数");
	    $("#amount").focus();
	    return false;
	}
	if($("#unit_price").val() == ""){
		alert("请填写单价");
		$("#unit_price").focus();
		return false;
	}	
	if($("#next_approver_all option").size() > 0 && $("#next_approver_sel option").size() == 0){
			alert("请选择审批人员");
			$("#next_approver_all").focus();
			return false;
		}
		//审批人信息
		var next_approver = "";
		$("#next_approver_sel option").each(function(){
			next_approver += this.value+",";
		});
		var len = next_approver.length;
		if (next_approver.lastIndexOf(",") == len - 1){
			next_approver = next_approver.substring(0, len - 1);
		}
		$("#next_approver").val(next_approver);
		
    return true;
}
</script>
<script language="javascript">
function updateResult() {
  if (xmlHttp.readyState == 4) {
    var response = xmlHttp.responseText;
	alert(response.substring(response.indexOf("00\">",0)+4, response.indexOf("</div>",0)));
	$("#medium_serial").focus();
  }
} 

</script>
<script>
	$(document).ready(function(){
		onHover();
		addSelectAllCheckbox();
	});
	function clearFindForm(){
		$("#QueryCondForm :text").val("");
		$("#QueryCondForm select").val("");
	}
	
	function selectKind(){
		$("#property_kind_name").val($("#property_kind option[selected]").text());
	}
</script>
</head>
<!-- <body oncontextmenu="self.event.returnValue=false"> -->
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">	
	<form action="${ctx}/asset/addurgentpropertyjob.action" method="POST">
		<input type="hidden" name="event_code" value="${event_code}" id="event_code"/>
		<input type="hidden" name="property_kind_name" id="property_kind_name"/>
		<input type="hidden" id="dept_id" name="dept_id" value="${curUser.dept_id}"/>
		<input type="hidden" id="jobType" name="jobType" value="${jobType}"/>
		<input type="hidden" id="next_approver" name="next_approver" />
		<input type="hidden" id="usage_code" name="usage_code" />
		<input type="hidden" id="seclv_code" name="seclv_code" value="6"/>
	<tr>
	    <td colspan="6" class="title_box">紧急资产采购申请</td>
	</tr>
	<tr> 
    	<td width="21%" align="center">申请用户： </td>
    	<td width="29%"><font color="blue"><b>${curUser.user_name}</b></font></td>
    	<td width="21%" align="center">用户部门： </td>
    	<td width="28%"><font color="blue"><b>${curUser.dept_name}</b></font></td>
		</td>		
	</tr>

  	<tr>     
    	<!-- <td align="center"><font color="red">*</font>&nbsp;资产密级： </td>
    	<td >
    		<select id="property_seclvcode" name="property_seclvcode">
				<option value="">--请选择--</option>
				<s:iterator value="#request.propertySeclvList" var="pseclv">
					<option value="${pseclv.seclv_code}">${pseclv.seclv_name}</option>
				</s:iterator>
			</select>
    	</td> -->
    	<td align="center"><font color="red">*</font>&nbsp;设备名称： </td>
    	<td ><input type="text" name="property_name" id="property_name"/></td> 
        <td align="center" ><font color="red">*</font>&nbsp;资产种类： </td>
    	<td>
    		<select id="property_kind" name="property_kind" onchange="selectKind();">
				<option value="">--请选择--</option>
				<s:iterator value="#request.typeList" var="tl">
					<option value="${tl.id}">${tl.property_kind}</option>
				</s:iterator>
			</select>
    	</td> 
  	</tr> 	
  	<tr>
  	<td align="center"><font color="red">*</font>&nbsp;资产密级： </td>
    	<td >
    		<select id="property_seclvcode" name="property_seclvcode">
				<option value="">--请选择--</option>
				<s:iterator value="#request.propertySeclvList" var="pseclv">
					<option value="${pseclv.seclv_code}">${pseclv.seclv_name}</option>
				</s:iterator>
			</select>
    	</td>
    	<td align="center" >&nbsp;国别厂家： </td>
    	<td><input type="text" name="supplier" id="supplier"/></td>
  	</tr>
  	<tr>     
    	<td align="center">&nbsp;规格： </td>
    	<td ><input type="text" name="property_standard" id="property_standard"/></td> 
    	<td align="center">&nbsp;型号： </td>
    	<td ><input type="text" name="property_type" id="property_type"/></td> 
  	</tr> 
  	<tr>     
  		<td align="center"><font color="red">*</font>&nbsp;物资数量： </td>
    	<td ><input type="text" name="amount" id="amount" value="1"/></td> 
    	<td align="center"><font color="red">*</font>&nbsp;拟采购单价： </td>
    	<td ><input type="text" name="unit_price" id="unit_price"/></td> 
  	</tr>
  	<tr>     
  		<td align="center">&nbsp;计划金额（元）： </td>
    	<td colspan=5><input type="text" name="budget_year" id="budget_year"/></td>
    	 
  	</tr>
  	<tr>   	
  		<td align="center">&nbsp;紧急采购原因： </td>
    	<td colspan="5"><textarea name="reason" rows="3" cols="80" id="reason"></textarea></td>   	
  	</tr>
  	<tr>   		
  		<td align="center">&nbsp;备注：</td>
		<td colspan="5"><textarea name="summ" rows="3" cols="80" id="summ"></textarea></td>
  	</tr>
<tr id="tr_approver" style="display: none">
  		<td align="center" id="selApprover1"><font color="red">*</font>选择审批人</td>
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
						<br/>
						<INPUT class="button_2003" onclick="add_all_True('next_approver_all','next_approver_sel');" type="button" value="全部增加" name="btnAddItem" /><br/>
						<br/>
						<INPUT class="button_2003" onclick="del_all_True('next_approver_sel');" type="button" value="全部删除" name="btnDelItem"><br/>
					</td>
					<td>
						<SELECT size="8" multiple="true" style="WIDTH: 100px" ondblclick="del_True('next_approver_sel');" id="next_approver_sel">
						</SELECT>
					</td>
				</tr>
			</table>
		</td>
  	</tr>
	</form>
	 	
  	<tr>
	    <td colspan="6" align="center"> 
	      <input type="button" class="button_2003" value="添加" onclick="if(chk()) forms[0].submit();" id="submit_btn"/>&nbsp;
	  	    </td>
	</tr>
</table>
</body>
</html>
