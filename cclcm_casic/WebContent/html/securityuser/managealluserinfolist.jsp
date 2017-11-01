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
		user_name=user_name.substring(0,user_name.indexOf("/"));
		if(user_id != ""){
	    	$("#user_id").val(user_id);
			$("#user_name").val(user_name);
			document.getElementById("allOptions").innerHTML="";
		}
	}
</script>
</head>

<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">用户申请资料完善详情</td>
	</tr>
	<tr>
		<td align="right">
		<form id="QueryCondForm" method="GET" action="${ctx}/securityuser/managealluserinfolist.action" name="QueryCondForm">
			<table border=0 class="table_box" cellspacing="0" cellpadding="0" width="100%">
			    	<tr>
			    		<td align="center">申请人</td>
  					  	<td>
   					 	    <input type="text" id="user_name" name="user_name" value="${user_name}" onkeyup="selectRecvUser(this.value);"/>
 					   		<input type="hidden" id="user_id" name="user_id"/><br>
 					   		<div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
 					   		</div>
					 	</td> 
					 	<td align="center">申请部门</td>
				 		<td>
							<input type="text" id="dept_name" name="dept_name" value="${dept_name}" readonly="readonly" style="cursor:hand" onclick="openDeptSelect('dept_name','dept_id','radio')"/>
    						<input type="hidden" id="dept_id" name="dept_id"/><br>
			    		</td>
			    		<td align="center">用户申请状态</td>
				 		<td>
				        	<select name="statue" value="${statue}">
    							<option value="">--全部--</option>
    							<option value="1" <c:if test="${statue == '1'}">selected</c:if>>未申请</option>
    							<option value="2" <c:if test="${statue == '2'}">selected</c:if>>已申请</option>
    							<option value="3" <c:if test="${statue == '3'}">selected</c:if>>已通过</option>
    						</select>
			    		</td>
			    		<td align="center">
							<input name="button" type="submit" class="button_2003" value="查询" onclick="return">&nbsp;
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
					<display:table requestURI="${ctx}/securityuser/managealluserinfolist.action" uid="item" class="displaytable" name="infoList" 
					pagesize="15" sort="list"  excludedParams="*" form="QueryCondForm">
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
							<display:column title="部门" property="suser.dept_name"/>
							<display:column title="姓名" property="suser.user_name"/>
							<display:column title="性别" property="bmuser.base_sex_name"/>
							<display:column title="出生日期" property="bmuser.base_birthday"/>
							<display:column title="学历" property="bmuser.edu_education_name"/>
							<display:column title="职务" property="bmuser.job_techpost"/>
							<display:column title="职称" property="bmuser.job_techtitle"/>
							<display:column title="涉密等级" property="suser.security_name"/>
							<display:column title="联系电话" property="bmuser.com_telephone"/>
							<display:column title="用户申请状态" property="statue_name"/>
						
						<display:column title="操作" style="width:70px">
							<div>
							<c:choose>
								<c:when test="${item.statue == 1}">
									<input type="button" class="button_2003" value="查看" disabled="disabled"/>
								</c:when>
								<c:otherwise>
									<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/securityuser/viewuserinfodetail.action?event_code=${item.bmuser.event_code}');"/>
								</c:otherwise>
							</c:choose>
							</div>							
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
