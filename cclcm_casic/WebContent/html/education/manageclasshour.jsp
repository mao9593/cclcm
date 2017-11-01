<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
  <head>
    
    <title>学时统计管理</title>
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
		var url = "${ctx}/basic/getfuzzyuser.action";
		if(word != ""){
			callServer1(url,"fuzzy="+word);
		}else{
			document.getElementById("allOptions").innerHTML="";
		}
	}
	
	function clearFindForm(){
			QueryCondForm.course_id.value = "";
			$("#user_iidd").val("");
			$("#user_name").val("");
			$("#dept_name").val("");
			$("#dept_id").val("");
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
		if(user_id != ""){
			$("#user_iidd").val(user_id);
			$("#user_name").val(user_name);
			document.getElementById("allOptions").innerHTML="";
		}
	}

	function exportPassport(formId,url,url1){
			document.getElementById(formId).action = url;
			document.getElementById(formId).submit();
			document.getElementById(formId).action = url1;
	}
	
	function checkSubmit(){
		var pattern = /^[2-9]{1}[0-9]{3}$/;
		if($("#edu_time").val().trim() != ""){
			if(!pattern.test($("#edu_time").val())){
		    	alert("请填写正确的4位数字年份");
		    	$("#edu_time").focus();
		    	return false;
	    	}
		}	 
		return true;	
	}
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<form id="QueryCondForm"  name="QueryCondForm" method="POST" action="${ctx}/education/manageclasshour.action">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">学时统计</td>
	</tr>
	<tr>
		<td align="right">
			<table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
			<tr>		 		
			   <td  width="13%" align="center" width="%">年度</td>
			   <td width="20%">
			 <input type="text" id="edu_time" name="edu_time" value="${edu_time}"/>
    		 </td>
			  <td  width="13%" align="center" width="%">部门</td>
			   <td width="20%">
			 <input type="text" id="dept_name" name="dept_name" value="${dept_name}" readonly="readonly" style="cursor:hand" onclick="openDeptSelect('dept_name','dept_id','radio')"/>
    		  <input type="hidden" id="dept_id" name="dept_id" value="${dept_id}"/><br>
    		 </td>
			<td align="center" width="13%">人员</td>
		    <td width="20%">
			<input type="text" id="user_name" name="user_name" value="${user_name}" onkeyup="selectRecvUser(this.value);"/>
    		<input type="hidden" id="user_iidd" name="user_iidd" value="${user_iidd}"/><br>
    		<div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
		</td>	
		<td width="13%" align="center">
			<input name="button" type="submit" class="button_2003" value="查询" onclick="return checkSubmit();">&nbsp;&nbsp;
			<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">&nbsp;&nbsp;&nbsp;
		</td>
		<!-- <td colspan=2>&nbsp;</td>	 -->										 
	  <tr>		
		 <!-- <td width="13%" align="center">查询时间</td>
		 <td width="20%" >
			<input type="text" name="startTime" id="startTime" onclick="WdatePicker()" class="Wdate" size="10"/> 	
		</td> 
		<td width="13%" align="center">至</td>
		<td width="20%">
			 <input type="text" name="endTime" id="endTime" onclick="WdatePicker()" class="Wdate" size="10"/>
	    </td> 	    	 -->
		
	</tr>
</table>
</td>
</tr>
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
					<display:table requestURI="${ctx}/education/manageclasshour.action" id="item" class="displaytable" name="hourlist" pagesize="15" 
					sort="list" excludedParams="*" form="QueryCondForm">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column property="dept_name" title="部门"  maxLength="15"/>
						<display:column property="user_name" title="姓名" maxLength="15"/>
						<display:column property="security_name" title="涉密等级"/>
						<display:column property="online" title="自助学时"  maxLength="10"/>
						<display:column property="centraining" title="集中培训学时"  maxLength="8"/>
						<display:column property="outtraining" title="外派培训学时"  maxLength="8"/>
						<display:column property="totalhour" title="总学时" maxLength="20"/>
				<%-- 	<display:column title="操作" style="width:200px">
							<input type="button" class="button_2003" value="修改" onclick="go('${ctx}/education/updatetrainingrecord.action?course_id=<c:out value="${item.course_id}"/>');"/>&nbsp;									
							 <input type="button" class="button_2003" value="删除" onclick="go('${ctx}/education/deletetrainingrecord.action?course_id=${item.course_id}');"/>  									
						</display:column> --%>
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
