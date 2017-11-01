<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
    <script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
    <script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
<script>
	$(document).ready(function(){
		onHover();
		document.getElementById("allOptions").innerHTML="";
		optionSelect();
	});
	function clearFindForm(){
		$("#QueryCondForm :text").val("");
		$("#QueryCondForm select").val("");
	}
    function optionSelect(){
		$("#security_code").val("${security_code}");
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
		user_name=user_name.substring(0,user_name.indexOf("/"));
		if(user_id != ""){
	    	$("#user_id").val(user_id);
			$("#user_name").val(user_name);
			document.getElementById("allOptions").innerHTML="";
		}
	}
	
	function exportComputer(formId,url,url1){
			document.getElementById(formId).action = url;
			document.getElementById(formId).submit();
			document.getElementById(formId).action = url1;
	}	
</script>
</head>

<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">涉密人员台账</td>
	</tr>
	<tr>
		<td align="right">
		<form id="QueryCondForm" method="GET" action="${ctx}/securityuser/managebmsecuserlist.action" name="QueryCondForm">
			<input type="hidden" name="type" value="${type}" id="type"/>
			<input type="hidden" name="dept_ids" value="${dept_ids}" id="dept_ids"/>
			<table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
			    	<tr>
			    		<td align="center">用户登录名</td>
  					  	<td>
   					 	    <input type="text" id="user_name" name="user_name" value="${user_name}" onkeyup="selectRecvUser(this.value);"/>
 					   		<input type="hidden" id="user_id" name="user_id"/><br>
 					   		<div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
 					   		</div>
					 	</td>
					 	<td align="center">姓名</td>
  					  	<td>
   					 	    <input type="text" id="base_name" name="base_name" value="${base_name}"/>
					 	</td> 
					 	<td align="center">保密责任部门</td>
				 		<td>
							<input type="text" id="dept_name" name="dept_name" value="${dept_name}" readonly="readonly" style="cursor:hand" onclick="openDeptSelect('dept_name','dept_id','checkbox')"/>
    						<input type="hidden" id="dept_id" name="dept_id" value="${dept_id}"/><br>
			    		</td>
			    		<td align="center" rowspan="2">
							<input name="button" type="submit" class="button_2003" value="查询">&nbsp;
							<input name="button" type="button" class="button_2003" value="清空" onclick="clearFindForm();">
						</td>
				 </tr>
				 <tr>				    	
					 	<%-- <td align="center">性别</td>
  					  	<td>
				 			<select name="base_sex" id="base_sex" value="${base_sex}">
								<option value="">--全部--</option>
								<option value="M" <c:if test="${base_sex == 'M'}">selected</c:if>>男</option>
								<option value="F" <c:if test="${base_sex == 'F'}">selected</c:if>>女</option>
							</select>  					 	    
					 	</td>		 --%>
					 	<td align="center">岗位类别</td>
  					  	<td>
							<select name="post_id" id="post_id">
						    <option value="">--请选择--</option>
							<s:iterator value="#request.postList" var="post_after">
								<option value="${post_after.post_id}">${post_after.post_name}</option>
							</s:iterator>		 	    
					 	</td>			 		    		
			    		<td align="center">涉密等级</td>
  					  	<td>
							<select name="security_code" id="security_code">
								<option value="">--全部--</option>
									<s:iterator value="#request.securityList" var="sec">
										<option value="${sec.security_code}">${sec.security_name}</option> 
									</s:iterator>
							</select> 					 	    
					 	</td>

					 	<td align="center">人员状态</td>
  					  	<td>
				 			<select name="user_statue" id="user_statue" value="${user_statue}">
								<option value="">--全部--</option>
								<option value="1" <c:if test="${user_statue == '1'}">selected</c:if>>在岗</option>
								<option value="2" <c:if test="${user_statue == '2'}">selected</c:if>>退休</option>
								<option value="3" <c:if test="${user_statue == '3'}">selected</c:if>>离职</option>
							</select>  					 	    
					 	</td>
<%-- 					 	<td align="center">脱密期</td>
  					  	<td>
				 			<select name="secret_statue" id="secret_statue">
								<option value="">--全部--</option>
								<option value="1" <c:if test="${secret_statue == '1'}">selected</c:if>>脱密期中</option>
							</select>  					 	    
					 	</td>	 --%>			
			    					    		
					</tr>
				</table>			
		</td>
	</tr>
	<tr><td><input type="button" class="button_2003" value="导出EXCEL" onclick="exportComputer('QueryCondForm','${ctx}/securityuser/exportbmsecuser.action','${ctx}/securityuser/managebmsecuserlist.action');"/></td></tr>
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
					<display:table requestURI="${ctx}/securityuser/managebmsecuserlist.action" id="item" class="displaytable" name="userTemp" 
					pagesize="${pageSize}" sort="page" partialList="true" size="${totalSize}" excludedParams="*" form="QueryCondForm">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<!-- 部门，姓名，性别，出生日期，学历，职务，职称，涉密等级，涉密资料总数，联系电话 -->
							<display:column title="保密责任部门" property="dept_name"/>
							<display:column title="姓名" property="user_name"/>
							<display:column title="性别" >
								<c:if test="${item.base_sex == 'M'}">男</c:if>
								<c:if test="${item.base_sex == 'F'}">女</c:if>
							</display:column>
							<display:column title="出生日期" property="base_birthday"/>
							<display:column title="学历">
								<c:if test="${item.edu_education == '1'}">博士</c:if>
								<c:if test="${item.edu_education == '2'}">硕士</c:if>
								<c:if test="${item.edu_education == '3'}">本科</c:if>
								<c:if test="${item.edu_education == '4'}">大专</c:if>
								<c:if test="${item.edu_education == '5'}">高中</c:if>
								<c:if test="${item.edu_education == '6'}">中专</c:if>
								<c:if test="${item.edu_education == '7'}">初中及以下</c:if>
							</display:column>
							<display:column title="职称" property="job_techtitle"/>
							<display:column title="职务" property="job_techpost"/>
							<display:column title="涉密等级" property="security_name"/>
							<display:column title="涉密资料总数">
								${item.ext_code}
							</display:column> 
							<display:column title="联系电话" property="com_telephone"/>
						<display:column title="操作" style="width:70px">
							<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/securityuser/viewbmuserinfodetail.action?user_id=${item.user_iidd}');"/>
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
