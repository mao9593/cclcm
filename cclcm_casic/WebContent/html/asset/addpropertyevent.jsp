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
	<form action="${ctx}/asset/addpropertyevent.action" method="POST">
		<input type="hidden" name="event_code" value="${event_code}" id="event_code"/>
		<input type="hidden" name="property_kind_name" id="property_kind_name"/>
	<tr>
	    <td colspan="6" class="title_box">添加资产采购申请</td>
	</tr>
	<tr> 
    	<td width="13%" align="center">申请用户： </td>
    	<td width="20%"><font color="blue"><b>${curUser.user_name}</b></font></td>
    	<td width="13%" align="center">用户部门： </td>
    	<td width="20%"><font color="blue"><b>${curUser.dept_name}</b></font></td>
    	<td width="13%" align="center"><font color="red">*</font>&nbsp;密级：</td>
	    <td width="20%">
			<select id="seclv_code" name="seclv_code" value="6">
				<option value="">--请选择--</option>
				<s:iterator value="#request.seclvList" var="seclv">
					<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
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
    	<td align="center" ><font color="red">*</font>&nbsp;资产种类： </td>
    	<td>
    		<select id="property_kind" name="property_kind" onchange="selectKind();">
				<option value="">--请选择--</option>
				<s:iterator value="#request.typeList" var="tl">
					<option value="${tl.id}">${tl.property_kind}</option>
				</s:iterator>
			</select>
    	</td> 
    	<td align="center"><font color="red">*</font>&nbsp;设备名称： </td>
    	<td ><input type="text" name="property_name" id="property_name"/></td> 
  	</tr> 	
  	<tr>     
    	<td align="center">&nbsp;规格： </td>
    	<td ><input type="text" name="property_standard" id="property_standard"/></td> 
    	<td align="center">&nbsp;型号： </td>
    	<td ><input type="text" name="property_type" id="property_type"/></td> 
    	<td align="center" >&nbsp;国别厂家： </td>
    	<td><input type="text" name="supplier" id="supplier"/></td> 
    	
  	</tr> 
  	<tr>     
  		<td align="center"><font color="red">*</font>&nbsp;数量： </td>
    	<td ><input type="text" name="amount" id="amount" value="1"/></td> 
    	<td align="center"><font color="red">*</font>&nbsp;单价： </td>
    	<td ><input type="text" name="unit_price" id="unit_price"/></td> 
    	<td align="center" >&nbsp; 联系电话：</td>
    	<td><input type="text" name="telephone" id="telephone"/></td> 
  	</tr>
  	<tr>     
  		<td align="center">&nbsp;年度预算指标（元）： </td>
    	<td colspan="5"><input type="text" name="budget_year" id="budget_year"/></td> 
  	</tr>
  	<tr>   	
  		<td align="center">&nbsp;主要技术指标和配套附件： </td>
    	<td colspan="5"><textarea name="details" rows="3" cols="80" id="details"></textarea></td>   	
  	</tr> 
  	<tr>   	
  		<td align="center">&nbsp;申请原由： </td>
    	<td colspan="5"><textarea name="reason" rows="3" cols="80" id="reason"></textarea></td>   	
  	</tr>
  	<tr>   		
  		<td align="center">&nbsp;备注：</td>
		<td colspan="5"><textarea name="summ" rows="3" cols="80" id="summ"></textarea></td>
  	</tr>

	</form>
	 	
  	<tr>
	    <td colspan="6" align="center"> 
	      <input type="button" class="button_2003" value="添加" onclick="if(chk()) forms[0].submit();" id="submit_btn"/>&nbsp;
	  	    </td>
	</tr>
</table>
<form method="post" action="${ctx}/asset/deletepurchasejob.action?del_type=1" id="deleteform">
	<input type="hidden" name="event_delids" id="event_delids"/>	
</form>
<form method="post" action="${ctx}/asset/addpurchaseprocessjob.action" id="hid_form">
	<input type="hidden" name="jobType" value="${jobType}"/>
	<input type="hidden" name="event_ids" id="event_ids"/>	
	<input type="hidden" name="seclv_code" id="seclv_code" value="6"/> 
	<input type="hidden" name="user_iidd" value="${curUser.user_iidd}"/>
	<input type="hidden" name="dept_id" value="${curUser.dept_id}"/>
	<input type="hidden" name="actionContext" value="asset/submitpurchaseevent.action"/>
</form>
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">已添加的采购申请列表</td>
	</tr>
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
					<display:table uid="item" class="displaytable" name="eventList" sort="list">
						<display:column title="选择">
						<!-- 	<input type="checkbox" name="event_code" id="${item.event_code}" value="${item.seclv_code}"/>  -->
							<input type="checkbox" value="${item.event_code}" name="event_id"/>
						</display:column>
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>								
						<display:column title="密级" property="seclv_name"/>
						<display:column title="采购资产种类" property="property_kind" maxLength="20"/>
						<display:column title="设备名称" property="property_name" maxLength="20"/>
						<display:column title="数量" property="amount"/>				
						<display:column title="单价" property="unit_price"/>		
						<display:column title="申请时间" property="apply_time_str"/>
						<display:column title="操作" style="width:150px">
						<div>
							<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/asset/viewpurchaseventdetail.action?event_code=${item.event_code}&op=view');"/>&nbsp;
							<input type="button" class="button_2003" value="修改" onclick="go('${ctx}/asset/updatepropertyevent.action?event_code=${item.event_code}');"/>&nbsp;							
							<input type="button" class="button_2003" value="删除" onclick="if(confirm('确定要删除该申请？'))go('${ctx}/asset/delpurchaseevent.action?eventtype=del&event_code=${item.event_code}');"/>
						</div>
						</display:column>
					</display:table>
					</td>
				</tr>
			</table>
         </td>
	</tr>
	<tr>
	   <td>
	       <input type="button" value="批量提交" onclick="addPurchaseJob();" class="button_2003"/>&nbsp;
<!-- 	       <input type="button" value="批量删除" onclick="deletePurchaseJob();" class="button_2003"/> -->
	  </td>
	</tr>
</table>
</body>
</html>
