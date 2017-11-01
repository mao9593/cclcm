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
<script>
$(document).ready(function(){
	onHover();
	disableEnterSubmit();
	setSeclv("seclv_code");
	selectNextApprover();
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
		//user_name=user_name.substring(0,user_name.indexOf("/"));
		if(user_id != ""){
	    	$("#change_user_iidd").val(user_id);
			$("#change_user_name").val(user_name);
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
				//$("#submit_btn").attr("disabled",false);
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
		if($("#change_user_name").val().trim() == ""){
			alert("请输入变更人");
			$("#change_user_name").focus();
			return false;
		}
		
		if($("#type").val() == "NEW"){
			if($("#seclv_code_after").val().trim() == ""){
				alert("请选择变更后涉密等级");
				$("#seclv_code_after").focus();
				return false;
			}
			
			if($("#summ").val().trim() == ""){
				alert("请输入申请理由");
				$("#summ").focus();
				return false;
			}
		}else{
			 if($("#seclv_code_after").val().trim() == "" && $("#dept_id_after").val().trim() == "" && $("#post_id_after").val().trim() == ""){
				alert("请选择需要变更的涉密等级、部门或岗位！");
				$("#seclv_code_after").focus();
				return false;
			} 
			if($("#summ").val().trim() == ""){
				alert("请输入变更理由");
				$("#summ").focus();
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
function chkinfo(){
	var url = "${ctx}/securityuser/getuserinfo.action";
	$("#useid").val($("#change_user_iidd").val());
	callServerPostForm1(url,document.forms[0]);
}
function chkchange(){
	var url = "${ctx}/securityuser/getuserinfo.action";
	$("#useid").val($("#change_user_iidd").val());
	$("#func").val("CHANGE")
	callServerPostForm1(url,document.forms[0]);
}
function getAjaxResult1(){
	if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
		if(xmlHttp.responseText.indexOf("<table") != -1){
			$("#info").show();
			document.getElementById("userinfo").innerHTML=xmlHttp.responseText;
			if(document.getElementById("info2")){
				document.getElementById("info2").style.display="None";
				document.getElementById("info3").style.display="None";
				document.getElementById("info4").style.display="None";
				document.getElementById("info5").style.display="None";
			}
			$("#submit_btn").attr("disabled",false);
		}else{
			$("#info").hide();
			alert(xmlHttp.responseText + "，不能提交申请");
			$("#submit_btn").attr("disabled",true);
		}
	}
}
function getAjaxResult2(){
	if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
		if(xmlHttp.responseText.indexOf("<table") != -1){
			$("#info1").show();
			document.getElementById("change").innerHTML=xmlHttp.responseText;
		}else{
			$("#info1").hide();
			alert(xmlHttp.responseText);
		}
	}
}
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">	
	<form action="${ctx}/securityuser/adduserseclvchangeevent.action" method="POST">
		<input type="hidden" id="event_code" name="event_code" value="${event_code}"/>
		<input type="hidden" id="dept_id" name="dept_id" value="${curUser.dept_id}"/>
		<input type="hidden" id="jobType" name="jobType" value="${jobType}"/>
		<input type="hidden" id="next_approver" name="next_approver" />
		<input type="hidden" id="type" name="type" value="${type}"/>
		<input type="hidden" id="func" name="func" value="${func}"/>
		<input type="hidden" name="seclv_code" id="seclv_code"/>
		<input type="hidden" name="useid" id="useid"/>
	<tr>
	<c:if test="${type == 'NEW'}">
		<td colspan="4" class="title_box">新增涉密人员审批表</td>
	</c:if>
	<c:if test="${type != 'NEW'}">
		<td colspan="4" class="title_box">涉密人员变更审批表</td>
	</c:if>   
	</tr>
	<tr>
    	<td width="12%" align="center">发起人 </td>
    	<td width="23%"><font color="blue"><b>${curUser.user_name}</b></font></td>
    	<td width="10%" align="center">发起人部门 </td>
    	<td width="23%"><font color="blue"><b>${curUser.dept_name}</b></font></td>
	</tr>
	<tr> 
   		<td align="center"><font color="red">*</font>申请人/申请人部门</td>
    	<td>
    		<input type="text" id="change_user_name" name="change_user_name" onkeyup="selectRecvUser(this.value);"/>
     		<c:if test="${type == 'NEW'}">
				<input type="button" value="查看人员信息" class="button_2003" onclick="return chkinfo();">
			</c:if>
			<c:if test="${type != 'NEW'}">
				<input type="button" value="查看人员信息" class="button_2003" onclick="return chkchange();">
			</c:if>
    		<input type="hidden" id="change_user_iidd" name="change_user_iidd"/><br>
    		<div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
    		</div>
	    </td>		
		<td align="center">&nbsp;联系电话</td>
  		<td><input type="text" name="contact_num" id="contact_num"/></td>
	</tr>
	<tr id="info" style="display: none">
		<td id="userinfo" colspan="4"></td>
	</tr>
	<!-- 变更内容  -->
	<c:if test="${type != 'NEW'}">
		<tr id="info1" style="display: none">
			<td id="change" colspan="4">
			</td>
		</tr>
		<tr id="info2"  style="display: block">
			<td align="center"><font color="red">*</font>变更内容</td>
			<td align="center">变更前</td>
			<td align="center" colspan = "2">变更后</td>
		</tr>
		<tr id="info3"  style="display: block">
			<td align="center"><input type="checkbox">部门</td>
			<td align="center">&nbsp;</td>
			<td align="center" colspan = "2">&nbsp;</td>
		</tr>
		<tr id="info4"  style="display: block">
			<td align="center"><input type="checkbox">岗位</td>
			<td align="center">&nbsp;</td>
			<td align="center" colspan = "2">&nbsp;</td> 	
		</tr>
		<tr id="info5"  style="display: block">
			<td align="center"><input type="checkbox">涉密等级</td>
			<td align="center">&nbsp;</td>
			<td align="center" colspan = "2">&nbsp;</td>
		</tr>
	</c:if>
	<c:if test="${type == 'NEW'}">  
	  	<tr>
			<td align="center"><font color="red">*</font>&nbsp;申请涉密等级</td>
		    <td>
				<select name="seclv_code_after" id="seclv_code_after">
					<option value="">--请选择--</option>
						<s:iterator value="#request.securityList" var="security_after">
							<option value="${security_after.security_code}">${security_after.security_name}</option>
						</s:iterator>
				</select>
			</td>
			<td align="center"><font color="red">*</font>选择岗位</td>
			<td><select name=post_id_after id="post_id_after">
					<option value="">--请选择--</option>
						<s:iterator value="#request.postList" var="post_after">
							<option value="${post_after.post_id}">${post_after.post_name}</option>
						</s:iterator>
				</select>
			</td> 
	  	</tr>
  	</c:if>
  	<tr>
  		<td align="center">&nbsp;<font color="red">*</font>申请理由</td>
  		<td colspan="3"><textarea name="summ" rows="3" cols="80" id="summ"></textarea></td>
  	</tr>
  	<tr id="tr_approver" style="display: none">
  		<td align="center" id="selApprover1"><font color="red">*</font>选择审批人</td>
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
    <td colspan="4" align="center"> 
      <input type="button" class="button_2003" value="提交" onclick="if(chk()) forms[0].submit();" id="submit_btn" disabled="true"/>
    </td>
  </tr>
</table>
</body>
</html>
