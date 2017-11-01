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
<script>
$(document).ready(function(){
	onHover();
	setSeclv("seclv_code");
	selectNextApprover();
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
	    	$("#accompany_iidd").val(user_id);
			$("#accompany_name").val(user_name);
			document.getElementById("allOptions").innerHTML="";
		}
	}
	/* function selectSeclv(seclv){
		if(seclv == ""){
			alert("请选择作业密级,以确认审批流程");
			$("#seclv_code").focus();
			return false;
		}else{
			selectNextApprover();
		}
	} */
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
	var p_num = /^[0-9]{0,5}$/;
	function chk()
	{
		/* if($("#seclv_code").val().trim() == ""){
			alert("请选择作业密级");
			$("#seclv_code").focus();
			return false;
		} */
		if($(":checkbox[name='kind'][checked]").size() == 0){
			alert("请选择制作种类");
			return false;
		}
		if($("#file_name").val().trim() == ""){
			alert("请输入文件（资料）名称");
			$("#file_name").focus();
			return false;
		}
		if($("#file_count").val().trim() == ""){
			alert("请输入装订份数");
			$("#file_count").focus();
			return false;
		}else if(!p_num.test($("#file_count").val())){
			alert("装订份数只能是 0-99999 的数字");
			$("#file_count").focus();
			return false;
		}
		if($("#file_page").val().trim() == ""){
			alert("请输入每份页数");
			$("#file_page").focus();
			return false;
		}else if(!p_num.test($("#file_page").val())){
			alert("页数只能是 0-99999 的数字");
			$("#file_page").focus();
			return false;
		}
		if($("#file_seclv_code").val().trim() == ""){
			alert("请选择文件密级");
			$("#file_seclv_code").focus();
			return false;
		}
		if($("#accompany_iidd").val().trim() == ""){
			alert("请输入陪同人员");
			$("#accompany_iidd").focus();
			return false;
		}
		if($("#make_company").val().trim() == ""){
			alert("请输入制作单位");
			$("#make_company").focus();
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
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">	
	<form action="${ctx}/secmanage/addfileoutmakeevent.action" method="POST">
		<input type="hidden" id="event_code" name="event_code" value="${event_code}"/>
		<input type="hidden" id="dept_id" name="dept_id" value="${curUser.dept_id}"/>
		<input type="hidden" id="jobType" name="jobType" value="${jobType}"/>
		<input type="hidden" id="next_approver" name="next_approver" />
				<input type="hidden" name="seclv_code" id="seclv_code"/>
	<tr>
		<td colspan="6" class="title_box">涉密文件/资料外出制作审批表</td>
	</tr>
	<tr> 
    	<td width="11%" align="center">申请人 </td>
    	<td width="22%"><font color="blue"><b>${curUser.user_name}</b></font></td>
    	<td width="11%" align="center">用户部门 </td>
    	<td width="22%"><font color="blue"><b>${curUser.dept_name}</b></font></td>
    	<td width="10%" align="center"><font color="red">*</font>&nbsp;制作种类</td>
		<td width="23%">
			<input type="checkbox" name="kind" value="0"/>印刷&nbsp;
        	<input type="checkbox" name="kind" value="1"/>胶装&nbsp;
        	<input type="checkbox" name="kind" value="2"/>特殊制作&nbsp;
        	<input type="checkbox" name="kind" value="3"/>其他&nbsp;
        </td>
    	<%-- <td align="center"><font color="red">*</font>&nbsp;作业密级</td>
	    <td>
			<select id="seclv_code" name="seclv_code" onchange="selectSeclv(this.value);">
				<option value="">--请选择--</option>
				<s:iterator value="#request.seclvList" var="seclv">
					<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
				</s:iterator>
			</select>
		</td> --%>
    	<%-- <td width="8%" align="center">流水号 </td>
    	<td width="17%"><font color="blue"><b>${event_code}</b></font></td> --%>
	</tr>
	<tr>
		<td align="center"><font color="red">*</font>&nbsp;文件（资料）名称</td>
		<td><input type="text" id="file_name" name="file_name"/></td>
		<td align="center"><font color="red">*</font>&nbsp;文件密级</td>
	    <td>
			<select id="file_seclv_code" name="file_seclv_code">
				<option value="">--请选择--</option>
				<s:iterator value="#request.seclvList" var="seclv">
					<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
				</s:iterator>
			</select>
		</td>
		<td align="center"><font color="red">*</font>&nbsp;装订份数</td>
		<td><input type="text" id="file_count" name="file_count"/></td>	
		
   	</tr> 
   	<tr> 	
		<td align="center"><font color="red">*</font>&nbsp;每份页数</td>
		<td><input type="text" id="file_page" name="file_page"/></td>
		 <td align="center"><font color="red">*</font>&nbsp;制作单位</td>
		<td><input type="text" id="make_company" name="make_company"/></td>	
		<td align="center"><font color="red">*</font>&nbsp;陪同人员</td>
    	<td>
    		<input type="text" id="accompany_name" name="accompany_name" onkeyup="selectRecvUser(this.value);"/>
    		<input type="hidden" id="accompany_iidd" name="accompany_iidd"/><br>
    		<div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px"></div>
	    </td>   	
   	</tr> 
  	<tr>   		   	
  		<td align="center">&nbsp;申请理由</td>
		<td colspan="5"><textarea name="reason" rows="3" cols="40" id="reason"></textarea></td>
  	</tr>
  	<tr>
  	<td align="center">&nbsp;备注</td>
		<td colspan="5"><textarea name="summ" rows="3" cols="40" id="summ"></textarea></td>
	</tr>	
  	<tr id="tr_approver" style="display: none">
  		<td align="center" id="selApprover1">选择审批人</td>
  		<td id="selApprover2" colspan="5">
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
    <td colspan="6" align="center"> 
      <input type="button" class="button_2003" value="提交" onclick="if(chk()) forms[0].submit();" id="submit_btn"/>
    </td>
  </tr>
</table>
</body>
</html>
