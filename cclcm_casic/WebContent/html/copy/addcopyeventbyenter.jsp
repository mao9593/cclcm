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
		addSelectAllCheckbox();
	});
	function chk(){	
		if($("#seclv_code").val() == ""){
			alert("请选择密级");
			$("#seclv_code").focus();
			return false;
		}
		if($("#file_name").val().trim() == ""){
			alert("请填写文件名称");
			$("#file_name").focus();
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
		if($("#copy_num").val().trim() == ""){
			alert("请填写复印份数");
			$("#copy_num").focus();
			return false;
		}
		if(!isInteger($("#copy_num").val())){
		    alert("复印份数只能填写1到10000之间的整数");
		    $("#copy_num").focus();
		    return false;
		}
		if($("#period").val() == ""){
			alert("请选择使用期限");
			$("#period").focus();
			return false;
		}
		$("#add").val("Y");
	    return true;
	}
	function clearFindForm(){
		$("#QueryCondForm :text").val("");
		$("#QueryCondForm select").val("");
	}
	function addCopyJob(){
		var event_ids = "";
		if($(":checkbox:checked[value!='']").size()>0){
			$(":checkbox:checked[value!='']").each(function (){
				event_ids += this.value+":";
			});
			$("#event_ids").val(event_ids);
			$("#hid_form").submit();
		}else{
			alert("请先勾选需要审批的作业");
			return false;
		}
	}
	function chkDel(event_code){
		if(confirm("确定要删除该申请？")){
			var url = "${ctx}/copy/delcopyevent.action?event_code="+escape(event_code);
			xmlHttp.open("GET", url, true);
			xmlHttp.onreadystatechange = updatePage;
			xmlHttp.send(null);
		}
	}
	function updatePage() {
		if (xmlHttp.readyState == 4) {
			  var response = xmlHttp.responseText;
			  $("#add").val("N");
			  QueryCondForm.submit();
		}
	}
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">	
	<form action="${ctx}/copy/addcopyeventbyenter.action" method="POST" id="QueryCondForm" name="QueryCondForm">
		<input type="hidden" name="add" value="${add}" id="add"/>
	<tr>
	    <td colspan="6" class="title_box">添加外来文件复印申请</td>
	</tr>
	<tr> 
    	<td width="10%" align="center">申请用户： </td>
    	<td width="23%"><font color="blue"><b>${curUser.user_name}</b></font></td>
    	<td width="10%" align="center">用户部门： </td>
    	<td width="23%"><font color="blue"><b>${curUser.dept_name}</b></font></td>
    	<td width="10%" align="center"><font color="red">*</font>&nbsp;密级：</td>
	    <td width="23%">
			<select id="seclv_code" name="seclv_code">
				<option value="">--请选择--</option>
				<s:iterator value="#request.seclvList" var="seclv">
					<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
				</s:iterator>
			</select>
		</td>
	</tr>
	<tr> 
    	<td align="center"><font color="red">*</font>&nbsp;文件名称： </td>
    	<td><input type="text" name="file_name" id="file_name"/></td>  
    	<td align="center"><font color="red">*</font>&nbsp;文件页数： </td>
    	<td><input type="text" name="page_num" id="page_num" size="7"/></td>	
    	<td align="center"><font color="red">*</font>&nbsp;复印份数： </td>
    	<td><input type="text" name="copy_num" id="copy_num" size="7"/>
    	</td>
    </tr>    
    <tr>
  		<td align="center"><font color="red">*</font>&nbsp;使用期限：</td>
		<td >
			<select name="period" id="period">
				<option value="">--请选择--</option>
    			<option value="L">长期留用</option>
    			<option value="S">短期留用</option>   			
    		</select>
    	</td> 
    	<td align="center">&nbsp;纸张类型： </td>
    	<td>
	    	<select id="page_type" name="page_type">
	    		<option value="">--请选择--</option>
	    		<option value="A3">A3</option>
	    		<option value="A4">A4</option>
	    		<option value="A5">A5</option>
	    		<option value="B4">B4</option>
	    		<option value="B5">B5</option>
	    		<option value="其他">其他</option>
	    	</select>
		</td>     	   	
    	<td align="center">&nbsp;单双面： </td>
    	<td><select name="is_double" id="is_double">
    			<option value="1">单面</option>
    			<option value="2">双面左右翻</option>   
    			<option value="3">双面上下翻</option> 	 			
    		</select>
    	</td>      	
    </tr>
    <tr> 
         <td align="center">&nbsp;颜色： </td>
    	<td><select name="color" id="color">
    			<option value="1">黑白</option>
    			<option value="2">彩色</option>   			
    		</select>
    	</td> 	   	   	
	   	<td align="center">&nbsp;文件来源： </td>
    	<td><input type="text" name="source" id="source"/></td> 	   	
  		<td align="center">&nbsp;备注：</td>
		<td ><textarea name="summ" rows="3" cols="35" id="summ"></textarea></td>
  	</tr>
  	<tr>
	    <td colspan="6" align="center"> 
	      <input type="button" class="button_2003" value="添加" onclick="if(chk()) forms[0].submit();" id="submit_btn"/>&nbsp;
	  	    </td>
	</tr>
</table>
<div>&nbsp;</div>
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">已添加的复印申请列表</td>
	</tr>
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
					<display:table uid="item" class="displaytable" name="eventList" sort="list">
						<display:column title="选择">
							<input type="checkbox" value="${item.id}" name="event_id"/>
						</display:column>
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column title="密级" property="seclv_name" />
						<display:column title="文件名称" property="file_name" maxLength="30"/>
						<display:column title="页数" property="page_num"/>
						<display:column title="份数" property="copy_num"/>
						<display:column title="使用期限" property="period_name"/>
						<display:column title="申请时间" property="apply_time_str"/>
						<display:column title="操作" style="width:100px">
							<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/copy/viewcopyeventdetail.action?event_code=${item.event_code}');"/>&nbsp;							
							<input type="button" class="button_2003" value="删除" onclick="chkDel('${item.event_code}');"/>
						</display:column>
					</display:table>
					</td>
				</tr>
			</table>
         </td>
	</tr>
	<tr><td><input type="button" value="提交" onclick="addCopyJob();" class="button_2003"/></td></tr>
</table>
</form>
<form method="post" action="${ctx}/copy/addcopyprocessjobbyenter.action" id="hid_form">
	<input type="hidden" name="event_ids" id="event_ids"/>	
	<input type="hidden" name="user_iidd" value="${curUser.user_iidd}"/>
	<input type="hidden" name="dept_id" value="${curUser.dept_id}"/>
</form>
</body>
</html>
