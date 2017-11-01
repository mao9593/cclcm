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
});

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
    document.forms[0].submit();
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
	var event_ids = "";
	var submitable = true;
	var event_delids = "";
	
	function addPurchaseJob(){
		if($(":checkbox:checked[value!='']").size()>0){
			$(":checkbox:checked[value!='']").each(function (){
				event_ids += this.value+":";
			});
			if(submitable){
				$("#event_ids").val(event_ids);
				$("#hid_form").submit();
			}else{
				return false;
			}
		}else{
			alert("请先勾选需要提交的作业");
			return false;
		}
	}
	function deletePurchaseJob(){
	if($(":checkbox:checked[value!='']").size()>0){
			$(":checkbox:checked[value!='']").each(function (){
				event_delids += this.value+":";
			});
			if(submitable){
				$("#event_delids").val(event_delids);
				$("#deleteform").submit();
			}else{
				return false;
			}
		}else{
			alert("请先勾选需要提交的作业");
			return false;
		}
	}
	function selectKind(){
		$("#property_kind_name").val($("#property_kind option[selected]").text());
	}
</script>
</head>
<!-- <body oncontextmenu="self.event.returnValue=false"> -->
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">	
	<form action="${ctx}/asset/updatepropertyevent.action" method="POST">
		<input type="hidden" name="event_code" value="${event.event_code}" id="event_code"/>
		<input type="hidden" name="property_kind_name" id="property_kind_name"/>
		<input type="hidden" name="update" id="update" value="Y"/>
	<tr>
	    <td colspan="6" class="title_box">修改资产采购申请</td>
	</tr>
	<tr> 
    	<td width="13%" align="center">申请用户： </td>
    	<td width="20%"><font color="blue"><b>${curUser.user_name}</b></font></td>
    	<td width="13%" align="center">用户部门： </td>
    	<td width="20%"><font color="blue"><b>${curUser.dept_name}</b></font></td>
    	<td width="13%" align="center"><font color="red">*</font>&nbsp;密级：</td>
	    <td width="20%">
			<select id="seclv_code" name="seclv_code" value="${event.seclv_code}">
				<option value="">--请选择--</option>
				<s:iterator value="#request.seclvList" var="seclv">
					<option value="${seclv.seclv_code}" <s:if test="#request.seclv_code==#seclv.seclv_code">selected="selected"</s:if> >${seclv.seclv_name}</option>
				</s:iterator>
			</select>
		</td>		
	</tr>

  	<tr>     
    	<td align="center"><font color="red">*</font>&nbsp;资产密级： </td>
    	<td >
    		<select id="property_seclvcode" name="property_seclvcode" value="${event.property_seclvcode}">
				<option value="">--请选择--</option>
				<s:iterator value="#request.propertySeclvList" var="pseclv">
					<option value="${pseclv.seclv_code}" <s:if test="#property_seclvcode==#pseclv.seclv_code">selected="selected"</s:if>>${pseclv.seclv_name}</option>
				</s:iterator>
			</select>
    	</td> 
    	<td align="center" ><font color="red">*</font>&nbsp;资产种类： </td>
    	<td>
    		<select id="property_kind" name="property_kind" value="${event.property_kind}" onchange="selectKind(); ">
    		     <option value="">--请选择--</option>
				<s:iterator value="#request.typeList" var="tl">
					<option value="${tl.id}" <s:if test="#request.property_kind==#tl.id">selected="selected"</s:if>>${tl.property_kind}</option>
				</s:iterator>
			</select>
    	</td> 
    	<td align="center"><font color="red">*</font>&nbsp;设备名称： </td>
    	<td ><input type="text" name="property_name" id="property_name" value="${event.property_name}"/></td> 
  	</tr> 	
  	<tr>     
    	<td align="center">&nbsp;规格： </td>
    	<td ><input type="text" name="property_standard" id="property_standard"  value="${event.property_standard}"/></td> 
    	<td align="center">&nbsp;型号： </td>
    	<td ><input type="text" name="property_type" id="property_type" value="${event.property_type}"/></td> 
    	<td align="center" >&nbsp;国别厂家： </td>
    	<td><input type="text" name="supplier" id="supplier" value="${event.supplier}"/></td> 
    	
  	</tr> 
  	<tr>     
  		<td align="center"><font color="red">*</font>&nbsp;数量： </td>
    	<td ><input type="text" name="amount" id="amount" value="${event.amount}"/></td> 
    	<td align="center"><font color="red">*</font>&nbsp;单价： </td>
    	<td ><input type="text" name="unit_price" id="unit_price" value="${event.unit_price}"/></td> 
    	<td align="center" >&nbsp; 联系电话：</td>
    	<td><input type="text" name="telephone" id="telephone" value="${event.telephone}"/></td> 
  	</tr>
  	<tr>     
  		<td align="center">&nbsp;年度预算指标（元）： </td>
    	<td colspan="5"><input type="text" name="budget_year" id="budget_year" value="${event.budget_year}"/></td> 
  	</tr>
  	<tr>   	
  		<td align="center">&nbsp;主要技术指标和配套附件： </td>
    	<td colspan="5"><textarea name="details" rows="3" cols="80" id="details">${event.details}</textarea></td>   	
  	</tr> 
  	<tr>   	
  		<td align="center">&nbsp;申请原由： </td>
    	<td colspan="5"><textarea name="reason" rows="3" cols="80" id="reason" >${event.reason}</textarea></td>   	
  	</tr>
  	<tr>   		
  		<td align="center">&nbsp;备注：</td>
		<td colspan="5"><textarea name="summ" rows="3" cols="80" id="summ" >${event.summ}</textarea></td>
  	</tr>

	</form>
	 	
  	<tr>
	    <td colspan="6" align="center"> 
	      <input type="button" class="button_2003" value="修改" onclick="chk()" id="submit_btn"/>&nbsp;
	      <input class="button_2003" type="button" value="取消" onClick="javascript:history.go(-1);">
	  	 </td>
	</tr>
</table>
</body>
</html>
