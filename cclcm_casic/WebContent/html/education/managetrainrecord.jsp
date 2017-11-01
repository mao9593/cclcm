<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
  <head>
    
    <title>查看培训记录</title>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen"/>
        <link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script type="text/javascript" src="${ctx}/uploadify/jquery.uploadify.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script language="javascript" src="${ctx}/_script/My97DatePicker/WdatePicker.js"></script>  
<script>
$(document).ready(function(){
	onHover();
		disableEnterSubmit();
		document.getElementById("allOptions").innerHTML="";
});
	function selectRecvUser(word){
		var url = "${ctx}/basic/getfuzzyuser.action?source=CR";
		if(word != ""){
			callServer1(url,"fuzzy="+word);
		}else{
			document.getElementById("allOptions").innerHTML="";
		}
	}
	
	function clearFindForm(){
			$("#training_type").val("");
			$("#location").val("");		
			$("#course_name").val("");
			$("#edu_user_iidds").val("");
			$("#QueryCondForm :text").val("");
	        $("#QueryCondForm select").val("");
            //$("#edu_user_iidds").val(user_id);
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
			$("#edu_user_iidds").val(user_id);
			$("#attend_username").val(user_name);
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
<form id="QueryCondForm"  name="QueryCondForm" method="GET" action="${ctx}/education/managetrainrecord.action">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">培训记录</td>
	</tr>
	<tr>
		<td align="right">
		<%-- <form id="TraintrcordForm" method="POST" action="${ctx}/education/managetrainrecord.action"> --%>
			<table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
					<tr>
						<td align="center" width="13%">培训类型:</td>
					 	<td width="20%">
					 		<select name="training_type" id="training_type">
								<option value="">--请选择--</option>
							<!-- 	<option value="0">在线培训</option> -->
								<option value="1" <c:if test="${training_type eq '1'}">selected</c:if>>集中培训</option>
								<option value="2" <c:if test="${training_type eq '2'}">selected</c:if>>外派培训</option>						
							</select>
					 	</td>		 		
					 	<td align="center" width="13%">培训名称</td>
		<td width=15%>
			<input type="text" name="course_name" value="${course_name}"/>
		</td>
		<td align="center" width="13%">人员</td>
		<td width="15%">
			<input type="text" id="attend_username" name="attend_username" value="${attend_username}" onkeyup="selectRecvUser(this.value);"/>
    		<input type="hidden" id="edu_user_iidds" name="edu_user_iidds" value="${edu_user_iidds}"/><br>
    		<div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
		</td>												 
					<td align="center" width="13%">培训地点</td>
		                        <td width="17%"><input type="text" name="location" value="${location}"/>	</td>
					<tr>
					
					<td width="8%" align="center">培训时间</td>
					 	<td width="17%" >
						 <input type="text" name="start_time" id="start_time" onclick="WdatePicker()" class="Wdate" size="10" value="${start_time_str}"/> 
	        				
				    	</td> 
				    	<td width="8%" align="center">至</td>
					 	<td width="17%">
					       <input type="text" name="end_time" id="end_time" onclick="WdatePicker()" class="Wdate" size="10" value="${end_time_str}"/>
	        			
	        			</td> 
				    	
						<td width="50%" align="center" colspan=4>
					 		<input name="button" type="submit" class="button_2003" value="查询"  onclick="return">&nbsp;&nbsp;
							<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">&nbsp;&nbsp;&nbsp;
							<input name="button" type="button" class="button_2003" value="增加集中培训记录" onClick="go('${ctx}/education/addcentrainrecord.action');" value="增加集中培训">&nbsp;&nbsp;
							<input name="button" type="button" class="button_2003" value="增加外派培训记录" onClick="go('${ctx}/education/addouttrainrecord.action');" value="增加外派培训">
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
					<display:table requestURI="${ctx}/education/managetrainrecord.action" id="item" class="displaytable" name="recordList" pagesize="15" 
					sort="list" excludedParams="*" form="QueryCondForm">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>					
						<%--<c:if test="${item.training_type_name != '在线培训'}">--%>
							<display:column property="training_type_name" title="培训类型"/>
							<display:column property="edu_user_names"  title="姓名"  maxLength="30"/>
							<display:column property="course_name" title="培训名称"  maxLength="15"/>
							<display:column property="class_time" title="学时"/>
							<display:column property="certificate_no" title="证书编号"  maxLength="15"/>
							<display:column property="certificate_exp_str" title="证书有效期"/>
							<display:column title="操作" style="width:150px">
								<%-- <div>
								 <c:choose>
								<c:when test="${item.training_type_name != '在线培训'}">		 --%>
								<input type="button" class="button_2003" value="详细" onclick="go('${ctx}/education/viewrecorddetail.action?id=${item.id}');"/>&nbsp;
								<input type="button" class="button_2003" value="修改" onclick="go('${ctx}/education/updatetrainingrecord.action?id=${item.id}');"/>&nbsp;									
								<input type="button" class="button_2003" value="删除" onclick="if(confirm('确定要删除该记录？'))go('${ctx}/education/deletetrainingrecord.action?course_id=${item.course_id}');"/>	
							<%-- 	</c:when>
								<c:otherwise>
								<input type="button" class="button_2003" value="详细" disabled="disabled"/>&nbsp;
								<input type="button" class="button_2003" value="修改" disabled="disabled"/>&nbsp;									
								<input type="button" class="button_2003" value="删除" disabled="disabled"/>	
								</c:otherwise>
								</c:choose>
								</div>						 --%>
							</display:column>
						<%-- </c:if>			--%>
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
