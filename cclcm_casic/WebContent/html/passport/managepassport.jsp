<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<title>查看护照台账</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
    <script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>   
    <script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
    <script language="javascript" src="${ctx}/_script/My97DatePicker/WdatePicker.js"></script>
	<script>
	
	$(document).ready(function(){
		onHover();
		disableEnterSubmit();
		document.getElementById("allOptions").innerHTML="";
	});
	
	function selectRecvUser(word){
		var url = "${ctx}/basic/getfuzzyuser.action";
		if(word != ""){
			callServer1(url,"fuzzy="+word);
		}else{
			document.getElementById("allOptions").innerHTML="";
		}
	}
	
	function clearFindForm(){
			QueryCondForm.passport_num.value = "";
			$("#passport_type").val("");
			$("#passport_status").val("");		
			$("#duty_dept_name").val("");
			$("#duty_dept_id").val("");
			$("#duty_user_name").val("");
			$("#duty_user_id").val("");
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
		user_name=user_name.substring(0,user_name.indexOf("/"));
		if(user_id != ""){
			$("#duty_user_id").val(user_id);
			$("#duty_user_name").val(user_name);
			document.getElementById("allOptions").innerHTML="";
		}
	}


	function exportPassport(formId,url,url1){
			document.getElementById(formId).action = url;
			document.getElementById(formId).submit();
			document.getElementById(formId).action = url1;
	}
	
	</script>
</head>

<body oncontextmenu="self.event.returnValue=false">
<form id="QueryCondForm" name="QueryCondForm" method="POST" action="${ctx}/passport/managepassport.action" >
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">护照列表</td>
	</tr>	
	<tr>
		<td align="right">
	 		<table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
					<tr>
						<td align="center">证件编号</td>
				 		<td>
				 			<input type="text" id="passport_num" name="passport_num" value="${passport_num}" />
				 		</td>
						<td align="center">证件类型 </td>
				 		<td>
							<select name="passport_type" id="passport_type">
								<option value="">--请选择--</option>
    							<option value="0" <c:if test="${passport_type == '0'}">selected</c:if>>普通护照</option>
    							<option value="1" <c:if test="${passport_type == '1'}">selected</c:if>>外交护照</option>
    							<option value="2" <c:if test="${passport_type == '2'}">selected</c:if>>公务护照</option>
    							<option value="3" <c:if test="${passport_type == '3'}">selected</c:if>>港澳通行证</option>
    							<option value="4" <c:if test="${passport_type == '4'}">selected</c:if>>大陆居民来往台湾地区通行证</option>	
							</select>
				 		</td>
				 		<td align="center">证件状态</td>
				 		<td >
				 			<select name="passport_status" id="passport_status">
								<option value="">--请选择--</option>
    							<option value="0" <c:if test="${passport_status == '0'}">selected</c:if>>在册</option>
    							<option value="1" <c:if test="${passport_status == '1'}">selected</c:if>>借出</option>
    							<option value="2" <c:if test="${passport_status == '2'}">selected</c:if>>删除</option>		
				 		</td>		
				 	</tr>
				 	<tr>
				 		<td align="center">责任部门</td><!-- 支持多选-->
				 		<td >
							<input type="text" id="duty_dept_name" name="duty_dept_name" value="${duty_dept_name}" readonly="readonly" style="cursor:hand" onclick="openDeptSelect('duty_dept_name','duty_dept_id','checkbox')"/>
    						<input type="hidden" id="duty_dept_id" name="duty_dept_id" value="${duty_dept_id}"/><br>
			    		</td> 
			    		<td align="center">责任人</td>
				 		<td>
				        	<input type="text" id="duty_user_name" name="duty_user_name" value="${duty_user_name}" onkeyup="selectRecvUser(this.value);"/>
    						<input type="hidden" id="duty_user_id" name="duty_user_id" value="${duty_user_id}"/><br>
    						<div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
			    		</td> 
				 		<td align="center" colspan="2" rowspan="2">
				 			<input name="button" type="submit" class="button_2003" value="查询">&nbsp;
							<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">
				 		</td>
					</tr>
					<tr>
						<td align="center">过期时间</td>
						<td><input type="text" name="startTime" id="startTime" value="${startTime_str}" onclick="WdatePicker()" class="Wdate"/></td>		
				 		<td align="center">至</td>
				 		<td><input type="text" name="endTime" id="endTime" value="${endTime_str}" onclick="WdatePicker()" class="Wdate"/></td>
					</tr>
				</table>	
	  </td>
	</tr>
<tr><td><input type="button" class="button_2003" value="导出EXCEL" onclick="exportPassport('QueryCondForm','${ctx}/passport/exportpassport.action','${ctx}/passport/managepassport.action');"/></td></tr>	
	<tr>
		<td>
	
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			
	 			<tr>
	   				<td>
					<display:table requestURI="${ctx}/passport/managepassport.action" id="item" class="displaytable" name="passportList" 
					pagesize="15" sort="list" excludedParams="*" form="QueryCondForm">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column property="passport_num" title="证件编号"  maxLength="25"/>
						<display:column property="passport_type_name" title="证件类型"/>
						<display:column property="passport_status_name" title="证件状态"/>
						<display:column property="duty_user_name" title="责任人" maxLength="8"/>
						<display:column property="duty_dept_name" title="所属部门" maxLength="15"/>
						<display:column property="issuing_date" title="签发日期" maxLength="15"/>
						<display:column property="ending_date" title="过期时间"/>
						<display:column property="summ" title="备注"/>
						<display:column title="操作" style="width:100px">
							<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/passport/viewpassportdetail.action?passport_id=${item.passport_id}');"/>&nbsp;
							<input type="button" class="button_2003" value="修改" onclick="go('${ctx}/passport/updatepassport.action?passport_id=${item.passport_id}');"/>&nbsp;	
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
