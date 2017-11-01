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
 	<script language="javascript" src="${ctx}/_script/casic304_common_bm.js"></script>
 	<script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
 	<script language="javascript" src="${ctx}/_script/My97DatePicker/WdatePicker.js"></script>
 	<link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
<script>
$(document).ready(function(){
	onHover();
	disableEnterSubmit();
	setSeclv("seclv_code");
	if("${type}" != "rectify"){
		selectNextApprover();
		document.getElementById("allOptions").innerHTML="";
	}else{
		if("${func}" == "ok"){
			$("#tr_approver").show();
			$("#submit_btn").attr("disabled",false);
		}else if("${func}" == "none"){
			alert("选择的[整改部门]未配置[可审批权限]用户。请重新选择,或联系管理员！");
			$("#submit_btn").attr("disabled",true);
		}else{
			alert("请先选择[整改部门]并点击[查询下级审批人]。");
			$("#submit_btn").attr("disabled",true);
		}
	}
});

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
	    	$("#duty_user_iidd").val(user_id);
			$("#duty_user_name").val(user_name);
			document.getElementById("allOptions").innerHTML="";
		}
	}
	function selectNextApprover(){
		del_all_True('next_approver_sel');
		var url = "${ctx}/basic/getnextapprover.action";
		callServerPostForm(url,document.forms[0]);
	}
	function getAjaxResult(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			if(xmlHttp.responseText.indexOf("<SELECT") != -1){
				$("#tr_approver").show();
				document.getElementById("allApprovers").innerHTML=xmlHttp.responseText;
				$("#submit_btn").attr("disabled",false);
			}else{
				$("#tr_approver").hide();
				alert(xmlHttp.responseText);
				$("#submit_btn").attr("disabled",true);
			}
			if($("#next_approver_all").size() > 0 && $("#next_approver_all").children().size() == 0){
				$("#tr_approver").hide();
			}
		}
	}
	function chk()
	{
		if("${type}" == "dept"){
			if($("#company_code").val().trim() == ""){
				alert("请选择单位");
				$("#company_code").focus();
				return false;
			}
			if($("#punish_dept_name").val().trim() == ""){
				alert("请选择部门");
				$("#punish_dept_name").focus();
				return false;
			}
			if($("#duty_user_iidd").val().trim() == ""){
				alert("请输入责任人");
				$("#duty_user_name").focus();
				return false;
			}
		}else if("${type}" == "seccheck"){
			if($("#company_code").val().trim() == ""){
				alert("请选择处罚单位");
				$("#company_code").focus();
				return false;
			}
			if($("#punish_dept_name").val().trim() == ""){
				alert("请选择处罚部门");
				$("#punish_dept_name").focus();
				return false;
			}
			if($("#duty_user_iidd").val().trim() == ""){
				alert("请输入办理人");
				$("#duty_user_name").focus();
				return false;
			}
		}
		if("${type}" == "rectify"){
			if($("#punish_dept_name").val().trim() == ""){
				alert("请输入整改部门");
				$("#punish_dept_name").focus();
				return false;
			}
		}

		if($("#next_approver_all option").size() > 0 && $("#next_approver_sel option").size() == 0){
			alert("请选择审批人员");
			$("#next_approver_all").focus();
			return false;
		}
		//审批人信息
		var next_approver = "";
		$("#next_approver_sel option").each(function(){
			next_approver += this.value+",";
		});
		var len = next_approver.length;
		if (next_approver.lastIndexOf(",") == len - 1){
			next_approver = next_approver.substring(0, len - 1);
		}
		$("#next_approver").val(next_approver);
	    return true;
	}
	function ifDept(){
		if($("#punish_dept_name").val().trim() == ""){
			alert("请输入整改部门");
			$("#punish_dept_name").focus();
			return false;
		}
		$("#func").val("NEXT");
		return true;
	}
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="85%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">	
	<form action="${ctx}/secmanage/addpunishevent.action" method="POST">
		<input type="hidden" id="event_code" name="event_code" value="${event_code}"/>
		<input type="hidden" id="dept_id" name="dept_id" value="${curUser.dept_id}"/>
		<input type="hidden" id="jobType" name="jobType" value="${jobType}"/>
		<input type="hidden" id="next_approver" name="next_approver" />
		<input type="hidden" id="type" name="type" value="${type}"/>
		<input type="hidden" name="seclv_code" id="seclv_code"/>
		<input type="hidden" id="func" name="func" value="${func}"/>
	<tr>
		<c:if test="${type == 'dept'}">
			<td colspan="4" class="title_box">部门保密自查违规处罚表</td>
		</c:if>
		<c:if test="${type == 'seccheck'}">
			<td colspan="4" class="title_box">单位保密检查违规处罚表</td>
		</c:if>  
		<c:if test="${type == 'rectify'}">
			<td colspan="4" class="title_box">保密工作不合规事项整改督查表</td>
		</c:if>  
	</tr>
	<tr> 
    	<td width="12%" align="center">申请人 </td>
    	<td width="23%"><font color="blue"><b>${curUser.user_name}</b></font></td>
    	<td width="10%" align="center">用户部门 </td>
    	<td width="23%"><font color="blue"><b>${curUser.dept_name}</b></font></td>
	</tr>
	<c:choose>
		<c:when test="${type != 'rectify'}">
		<tr> 
			<td align="center"><font color="red">*</font><c:if test="${type == 'dept'}">单位</c:if><c:if test="${type == 'seccheck'}">处罚单位</c:if></td>
	    	<td>
	    		<select name="company_code" id="company_code">
					<option value="">--请选择--</option>
					<option value="62">声光电公司</option>
					<option value="24">24所</option>
					<option value="26">26所</option>
					<option value="44">44所</option>
				</select>
		    </td>	
		    <td align="center"><font color="red">*</font><c:if test="${type == 'dept'}">部门</c:if><c:if test="${type == 'seccheck'}">处罚部门</c:if></td>
	    	<td>
	    		<input type="text" name="punish_dept_name" id="punish_dept_name" readonly="readonly" style="cursor:hand" onclick="openDeptSelect('punish_dept_name','punish_dept_id','radio')" />
				<input type="hidden" name="punish_dept_id" id="punish_dept_id" />
		    </td>
		 </tr>
		 <tr>
		    <td align="center"><font color="red">*</font><c:if test="${type == 'dept'}">责任人</c:if><c:if test="${type == 'seccheck'}">办理人</c:if></td>
	    	<td colspan="3">
	    		<input type="text" id="duty_user_name" name="duty_user_name" onkeyup="selectRecvUser(this.value);"/>
	    		<input type="hidden" id="duty_user_iidd" name="duty_user_iidd"/><br>
	    		<div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
	    		</div>
		    </td>
		</tr>
		</c:when>
		<c:otherwise>
			<tr>
		    	<td align="center"><font color="red">*</font>整改部门</td>
		    	<td>
		    		<input type="text" name="punish_dept_name" id="punish_dept_name"  readonly="readonly" style="cursor:hand" onclick="openDeptSelect('punish_dept_name','punish_dept_id','radio')" value="${punish_dept_name}"/>
					<input type="hidden" name="punish_dept_id" id="punish_dept_id" value="${punish_dept_id}"/>
					<input type="button" class="button_2003" value="查询下级审批人" onclick="if(ifDept())forms[0].submit();"/>
			    </td>
			    <td align="center">整改完成时间</td>
				<td>
	   				<input type="text" id="rectify_time" name="rectify_time" onclick="WdatePicker()" class="Wdate" />
		 		</td>
		 	</tr>
		</c:otherwise>
	</c:choose>		
	
  	<tr>   		   	
  		<td align="center">
  			<c:if test="${type == 'dept'}">违规事项描述</c:if>
  			<c:if test="${type == 'seccheck'}">处罚事项描述</c:if>
  			<c:if test="${type == 'rectify'}">不合规事项描述</c:if>
  		</td>
		<td colspan="3"><textarea name="describe" rows="3" cols="80" id="describe" value="${describe}"></textarea></td>
  	</tr>
  	<c:if test="${type == 'seccheck'}">
  	<tr>   		   	
  		<td align="center">处罚建议</td>
		<td colspan="3"><textarea name="advise" rows="3" cols="80" id="advise"></textarea></td>
  	</tr>
  	</c:if>	
  	<c:if test="${type == 'rectify'}">
  	<tr>
		<td align="center">整改依据</td>
		<td colspan="3"><textarea name="rectify_according" rows="3" cols="80" id="rectify_according" value="${rectify_according}"></textarea></td>
	</tr>
	 <tr>
		<td align="center">整改要求</td>
		<td colspan="3"><textarea name="rectify_demand" rows="3" cols="80" id="rectify_demand" value="${rectify_demand}"></textarea></td>
	</tr>
	</c:if> 
  	<tr id="tr_approver" style="display: none">
  		<td align="center" id="selApprover1">选择审批人</td>
  		<td id="selApprover2" colspan="3">
  			<table width="300" border="0" cellspacing="0" cellpadding="0" align="left" class="table_box_border_empty">
				<tr>
					<td id="allApprovers">
						<SELECT ondblclick="add_True('next_approver_all','next_approver_sel');" style="WIDTH: 100px" multiple="true" size="8" id="next_approver_all">
							<c:forEach var="item" items="${userList}" varStatus="status">
								<option value="${item.user_iidd}">${item.user_name}</option>
							</c:forEach>
						</SELECT>
					</td>
					<td aling="center" valign="middle">
						<INPUT class="button_2003" onclick="add_MoreTrue('next_approver_all','next_approver_sel');" type="button" value="增加-->" name="btnAddItem" /><br/>
						<br/>
						<INPUT class="button_2003" onclick="del_MoreTrue('next_approver_sel');" type="button" value="删除<--" name="btnDelItem"><br/>
						<br/>
						<INPUT class="button_2003" onclick="add_all_True('next_approver_all','next_approver_sel');" type="button" value="全部增加" name="btnAddItem" /><br/>
						<br/>
						<INPUT class="button_2003" onclick="del_all_True('next_approver_sel');" type="button" value="全部删除" name="btnDelItem"><br/>
					</td>
					<td>
						<SELECT size="8" multiple="true" style="WIDTH: 100px" ondblclick="del_True('next_approver_sel');" id="next_approver_sel">
						</SELECT>
					</td>
				</tr>
			</table>
		</td>
  	</tr>
  	
	</form>
	 	
  	<tr>
    <td colspan="5" align="center"> 
     	<input type="button" class="button_2003" value="提交" onclick="if(chk()) forms[0].submit();" id="submit_btn"/>
		<input type="hidden" name="rectify_time" readonly="readonly" style="cursor:hand;" value="${rectify_time_str}"/>
	</td>
  </tr>
</table>
</body>
</html>
