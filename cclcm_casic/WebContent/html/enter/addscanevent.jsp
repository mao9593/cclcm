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
	if($("#page_num").val().trim() == ""){
		alert("请填写文件页数");
		$("#page_num").focus();
		return false;
	}
	if(!isInteger($("#page_num").val())){
	    alert("文件页数只能填写1到10000之间的整数");
	    $("#page_num").focus();
	    return false;
	}
	if($("#zipfile").val().trim() == ""){
		alert("请填写文件名称");
		$("#zipfile").focus();
		return false;
	}	
    return true;
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
	function addEnterJob(){
		var event_ids = "";
		var submitable = true;
		if($(":checkbox:checked[value!='']").size()>0){
			$(":checkbox:checked[value!='']").each(function (){
				event_ids += this.value+":";
				//seclv_code = this.value;
			});
			if(submitable){
				$("#event_ids").val(event_ids);
				//$("#seclv_code").val(seclv_code);
				$("#hid_form").submit();
			}else{
				return false;
			}
		}else{
			alert("请先勾选需要审批的作业");
			return false;
		}
	}
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">	
	<form action="${ctx}/enter/addscanevent.action" method="POST">
		<input type="hidden" name="event_code" value="${event_code}" id="event_code"/>
	<tr>
	    <td colspan="6" class="title_box">添加扫描录入申请</td>
	</tr>
	<tr> 
    	<td width="10%" align="center">申请用户： </td>
    	<td width="23%"><font color="blue"><b>${curUser.user_name}</b></font></td>
    	<td width="10%" align="center">用户部门： </td>
    	<td width="23%"><font color="blue"><b>${curUser.dept_name}</b></font></td>
    	<td width="10%" align="center"><font color="red">*</font>&nbsp;密级：</td>
	    <td width="23%">
	    <c:choose>
			<c:when test="${paper_event != null}">
				<font color="blue"><b>${paper_event.seclv_name}</b></font>
				<input type="hidden" name="seclv_code" value="${paper_event.seclv_code}" id="seclv_code"/>
			</c:when>
			<c:otherwise>
				<select id="seclv_code" name="seclv_code">
				<option value="">--请选择--</option>
				<s:iterator value="#request.seclvList" var="seclv">
					<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
				</s:iterator>
			</select>
			</c:otherwise>								
		</c:choose>
		</td>		
	</tr>
	<tr> 
    	<td align="center"><font color="red">*</font>&nbsp;文件页数： </td>
    	<td ><input type="text" name="page_num" id="page_num" <c:if test="${paper_event != null}"> value="${paper_event.page_num}" readonly="readonly"</c:if>/></td>     			    	  	
    	<td align="center">&nbsp;来源： </td>
    	<td ><input type="text" name="source" id="source" <c:if test="${paper_event != null}">value="${paper_event.source}" readonly="readonly"</c:if>/></td> 
  		<td align="center">&nbsp;使用期限： </td>
    	<td >
    		<select name="period" id="period">
    			<option value="L">长期留用</option>
    			<option value="S">短期留用</option> 
    		</select>
    	</td>	   
  	</tr> 
  	<tr>   		   	
  		<td align="center"><font color="red">*</font>&nbsp;文件名称：</td>
		<td colspan="5"><textarea name="zipfile" rows="3" cols="40" id="zipfile" <c:if test="${paper_event != null}"> readonly="readonly"</c:if>/><c:if test="${paper_event != null}">${paper_event.zipfile}</c:if></textarea></td> 
  	</tr> 	
  	<tr>   		   	
  		<td align="center">&nbsp;备注：</td>
		<td colspan="5"><textarea name="summ" rows="3" cols="40" id="summ"><c:if test="${paper_event != null}">${paper_event.summ}</c:if></textarea></td>
  	</tr>
	</form>
	 	
  	<tr>
	    <td colspan="6" align="center"> 
	      <input type="button" class="button_2003" value="添加" onclick="if(chk()) forms[0].submit();" id="submit_btn"/>&nbsp;
	  	    </td>
	</tr>
</table>
<form method="post" action="${ctx}/enter/addenterprocessjob.action" id="hid_form">
	<input type="hidden" name="jobType" value="${jobType}"/>
	<input type="hidden" name="event_ids" id="event_ids"/>	
	<input type="hidden" name="seclv_code" id="seclv_code"/> 
	<input type="hidden" name="user_iidd" value="${curUser.user_iidd}"/>
	<input type="hidden" name="dept_id" value="${curUser.dept_id}"/>
	<input type="hidden" name="add_type" value="5"/>
	<input type="hidden" name="actionContext" value="enter/submitenterevent.action"/>
</form>
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">已添加的扫描录入申请列表</td>
	</tr>
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
					<display:table uid="item" class="displaytable" name="eventList" sort="list">
						<display:column title="选择">
						<!-- 	<input type="checkbox" name="event_code" id="${item.event_code}" value="${item.seclv_code}"/>  -->
							<input type="checkbox" value="${item.id}" name="event_id"/>
						</display:column>
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column title="文件名称" property="zipfile" maxLength="20"/>				
						<display:column title="密级" property="seclv_name"/>
						<display:column title="类型" property="file_type_name" maxLength="15"/>
						<display:column title="载体归属" property="scope_name" maxLength="15"/>						
						<display:column title="申请时间" property="apply_time_str"/>
						<display:column title="操作" style="width:150px">
						<div>
							<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/enter/viewentereventdetail.action?event_code=${item.event_code}&op=view');"/>&nbsp;							
							<input type="button" class="button_2003" value="删除" onclick="if(confirm('确定要删除该申请？'))go('${ctx}/enter/delenterevent.action?event_code=${item.event_code}&del_type=1');"/>
						</div>
						</display:column>
					</display:table>
					</td>
				</tr>
			</table>
         </td>
	</tr>
	<tr><td><input type="button" value="提交" onclick="addEnterJob();" class="button_2003"/></td></tr>
</table>
</body>
</html>
