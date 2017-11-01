<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>要害部门部位台账</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
    <script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>   
    <script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
<script>
	$(document).ready(function(){
		onHover();
	});
	function clearFindForm(){
		$("#QueryCondForm :text").val("");
		$("#QueryCondForm select").val("");
	}
function selectRecvUser(word){

		var url = "${ctx}/basic/getfuzzyuser.action?source=CR";
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

function add_TrueCR(){
	var user_id=$("#allOptionCR").val();
	var user_name=$("#allOptionCR option[value='"+user_id+"']").text();
	if(user_id != ""){
		$("#duty_user_id").val(user_id);
		$("#duty_user_name").val(user_name);
		document.getElementById("allOptions").innerHTML="";
	}
}	
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<form id="QueryCondForm" name="QueryCondForm" method="POST" action="${ctx}/secplace/managesecplace.action" >
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">要害部门部位台账</td>
	</tr>	
	<tr>
		<td align="right">
	 		<table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
					<tr>
						<td align="center">要害部门部位名称</td>
				 		<td>
				 			<input type="text" id="secplace_name" name="secplace_name" />
				 		</td>
						<td align="center">密级 </td>
				 		<td>
				 			<select name="seclv_code" id="seclv_code">
								<option value="">--所有--</option>
								<s:iterator value="#request.seclvList" var="seclv">
									<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
								</s:iterator>
							</select>
				 		</td>
				 		<td align = "center">要害部门部位编号</td>
				 		<td>
				 		    <input type="text" id="secplace_code" name="secplace_code" />
				 		</td>
				 		
				 		<td align="center">要害类型</td>
				 		<td >
				 			<select name="secplace_type" id="secplace_type">
								<option value="">--请选择--</option>
								<option value="1">部门</option>
								<option value="2">部位</option>
							</select>
				 		</td>
				 		
				 	</tr>
				 	<tr>
				 		<td align="center">状态</td>
				 		<td>
				 			<select name="secplace_status" id="secplace_status">
								<option value="">--所有--</option>
								<option value="1">在用</option>
								<option value="2">停用</option>	
							</select>
				 		</td>
				 		<td align="center">责任部门</td><!-- 做到点击出现所有部门，能够选择 -->
				 		<td >
							<input type="text" id="duty_dept_name" name="duty_dept_name" readonly="readonly" style="cursor:hand" onclick="openDeptSelect('duty_dept_name','duty_dept_id','radio')"/>
    						<input type="hidden" id="duty_dept_id" name="duty_dept_id"/><br>
			    		</td> 
			    		<td align="center">责任人</td><!-- 做到点击出现部门内所有人，能够选择 -->
				 		<td>
				        	<input type="text" id="duty_user_name" name="duty_user_name" onkeyup="selectRecvUser(this.value);"/>
    						<input type="hidden" id="duty_user_id" name="duty_user_id"/><br>
    						<div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
			    		</td> 
				 		<td align="center" colspan="2">
				 			<input name="button" type="submit" class="button_2003" value="查询">&nbsp;
							<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">
				 		</td>
					</tr>
				</table>
			</td>
		</tr>
		
		<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">	
	 			<tr>
	   				<td>
					<display:table requestURI="${ctx}/secplace/managesecplace.action" id="item" class="displaytable" name="secplaceList" 
					pagesize="15" sort="list" excludedParams="*" form="QueryCondForm">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column property="secplace_name" title="要害部门部位名称"  maxLength="25"/>
						<display:column property="secplace_code" title="要害部门部位编号"  maxLength="15"/>
						<display:column property="conf_code" title="保密编码"  />
						<display:column property="seclv_name" title="密级"/>
						<display:column property="secplace_type_name" title="要害类型"  maxLength="15"/>
						<display:column property="duty_user_name" title="责任人" maxLength="15"/>
						<display:column property="duty_dept_name" title="责任部门" maxLength="15"/>
						<display:column property="secplace_application" title="用途"  maxLength="15"/>
						<display:column property="user_number" title="涉密人员数量"  maxLength="15"/>
						<display:column title="操作" style="width:100px">
							<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/secplace/viewsecplacedetail.action?secplace_barcode=${item.secplace_barcode}');"/>&nbsp;	
							<input type="button" class="button_2003" value="修改" onclick="go('${ctx}/secplace/updatesecplace.action?secplace_barcode=${item.secplace_barcode}');"/>&nbsp;
						</display:column>
					</display:table>
					</td>
				</tr>			
			</table>
			
         </td>
	</tr>
		
	</table>
</form>
</body>
</html>
	