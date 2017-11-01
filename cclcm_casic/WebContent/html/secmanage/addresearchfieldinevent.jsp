<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
	<script language="JavaScript" src="${ctx}/_script/function.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common_bm.js"></script>
 	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>	
 	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
 	<script language="JavaScript" src="${ctx}/_script/common.js"></script>
 	<script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
 	<link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
<script>
$(document).ready(function(){
	onHover();
	disableEnterSubmit();
	document.getElementById("allOptions").innerHTML="";
	preCalendarDay();
	setSeclv("seclv_code");
	selectNextApprover();
});
	function preCalendarDay(){
		Calendar.setup({inputField: "enter_time", button: "enter_time_trigger", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d %H:%M:%S", cache: true, weekNumbers:true, showOthers: true, step: 1});
		Calendar.setup({inputField: "leave_time", button: "leave_time_trigger", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d %H:%M:%S", cache: true, weekNumbers:true, showOthers: true, step: 1});
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
	    	$("#rec_user_iidd").val(user_id);
			$("#rec_user_name").val(user_name);
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
		if($("#visitor_company").val().trim() == ""){
			alert("请输入进入成员单位");
			$("#visitor_company").focus();
			return false;
		}
		if($("#visitor_names").val().trim() == ""){
			alert("请输入进入成员名单");
			$("#visitor_names").focus();
			return false;
		}
		if($("#field_site").val().trim() == ""){
			alert("请输入拟进入科研场地地点");
			$("#field_site").focus();
			return false;
		}
		if($("#rec_user_iidd").val().trim() == ""){
			alert("请输入接待人");
			$("#rec_user_name").focus();
			return false;
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
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="90%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">	
	<form action="${ctx}/secmanage/addresearchfieldinevent.action" method="POST">
		<input type="hidden" id="event_code" name="event_code" value="${event_code}"/>
		<input type="hidden" id="dept_id" name="dept_id" value="${curUser.dept_id}"/>
		<input type="hidden" id="jobType" name="jobType" value="${jobType}"/>
		<input type="hidden" id="next_approver" name="next_approver" />
		<input type="hidden" name="seclv_code" id="seclv_code" />
	<tr>
		<td colspan="4" class="title_box">进入重要科研场地审批表</td>
	</tr>
	<tr> 
    	<td width="15%" align="center">申请人 </td>
    	<td width="30%"><font color="blue"><b>${curUser.user_name}</b></font></td>
    	<td width="15%" align="center">用户部门 </td>
    	<td width="30%"><font color="blue"><b>${curUser.dept_name}</b></font></td>
	</tr>
	<tr> 
		<td align="center"><font color="red">*</font>&nbsp;接待人</td>
    	<td>
    		<input type="text" id="rec_user_name" name="rec_user_name" onkeyup="selectRecvUser(this.value);"/>
    		<input type="hidden" id="rec_user_iidd" name="rec_user_iidd"/><br>
    		<div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
    		</div>
	    </td>
	        	<td width="13%" align="center">联系电话 </td>
    	<td width="23%"><input type="text" id="contact_num" name="contact_num"/></td>
    </tr>
    <tr>
	    <td align="center">&nbsp;拟进入时间</td>
		<td>
	 		<input type="text" name="enter_time" readonly="readonly" style="cursor:hand;" value="${enter_time_str}"/>
   			<img src="${ctx}/_image/time2.jpg" id="enter_time_trigger">
	 	</td>
		<td align="center">&nbsp;拟离开时间</td>
		<td>
	 		<input type="text" name="leave_time" readonly="readonly" style="cursor:hand;" value="${leave_time_str}"/>
   			<img src="${ctx}/_image/time2.jpg" id="leave_time_trigger">
	 	</td>	
    </tr>	
	<tr> 
		<td align="center"><font color="red">*</font>&nbsp;进入成员单位</td>
		<td><textarea name="visitor_company" rows="2" cols="40" id="visitor_company"></textarea></td>
		<td align="center"><font color="red">*</font>&nbsp;进入成员名单</td>
		<td><textarea name="visitor_names" rows="2" cols="40" id="visitor_names"></textarea></td>
   	</tr> 
   	<tr> 
		<td align="center"><font color="red">*</font>&nbsp;拟进入科研场地地点</td>
		<td><textarea name="field_site" rows="2" cols="40" id="field_site"></textarea></td>
		<td align="center">&nbsp;携带物品</td>
		<td><textarea name="belongings" rows="2" cols="40" id="belongings"></textarea></td>
   	</tr>   
  	<tr>   		   	
  		<td align="center">&nbsp;事由</td>
		<td><textarea name="reason" rows="3" cols="40" id="reason"></textarea></td>
		<td align="center">&nbsp;备注</td>
		<td><textarea name="text" rows="3" cols="40" id="text"></textarea></td>
  	</tr>
  	<!-- <tr>
			<td align="center">部门负责人意见</td>
			<td colspan="3" id="step1">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td>
						<textarea id="opinion1" rows="4" cols="100" readonly="readonly" ></textarea>
					</td></tr>
					<tr><td id="hidden1"></td></tr>
			</table>
	  </tr>
	  <tr>
			<td align="center">科研生产处（部）意见</td>
			<td colspan="3" id="step2">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td>
						<textarea id="opinion2" rows="4" cols="100" readonly="readonly" >如果有外资背景或境外人员进入时需要主管所领导审批，这种情况下请选择主管所领导，其他则跳过。</textarea>
					</td></tr>
					<tr><td id="hidden2"></td></tr>
			</table>
	  </tr>
	  <tr>
			<td align="center">主管所领导意见</td>
			<td colspan="3" id="step3">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td>
						<textarea id="opinion3" rows="4" cols="100" readonly="readonly" ></textarea>
					</td></tr>
					<tr><td id="hidden3"></td></tr>
			</table>
	  </tr>
	  <tr>
			<td align="center">接待人确认</td>
			<td colspan="3" id="step4">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td>
						<textarea id="opinion4" rows="4" cols="100" readonly="readonly" ></textarea>
					</td></tr>
					<tr><td id="hidden4"></td></tr>
			</table>
	  </tr>
	   <tr>
			<td align="center">保密处备案</td>
			<td colspan="3" id="step5">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td>
						<textarea id="opinion5" rows="4" cols="100" readonly="readonly" ></textarea>
					</td></tr>
					<tr><td id="hidden5"></td></tr>
			</table>
	  </tr> -->

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
    <td colspan="4" align="center"> 
      <input type="button" class="button_2003" value="提交" onclick="if(chk()) forms[0].submit();" id="submit_btn"/>
    </td>
  </tr>
</table>
</body>
</html>
