<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
	<script language="JavaScript" src="${ctx}/_script/function.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
 	<script type="text/javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
<script>
<!--
$(document).ready(function(){
	init();
});
var nas_url;
var nas;
var nas_username;
var nas_password;
var event_code ;
function init(){
	onHover();
	$("#tr_send input").attr("disabled",true);
	$("#tr_send").hide();
	nas_url = "${nas_url}";
	nas_username = "${nas_username}";
	nas_password = "${nas_password}";
	event_code = "${event_code}";
}

function chk()
{	
	var info="";
	var fileName;
	var fileSeclv = 0;
	var jobSeclv = $("#seclv_med").val();
	var sign = true;
	var uploaded= false;
	$("tr").filter(".files_info").each(function(){
		uploaded = true;
		fileName = $(this).children("td").eq(0).text();
		fileSeclv = $(this).children("td").eq(1).find("select").val();
		if(fileSeclv == undefined || fileSeclv ==""){
			var fileSeclvName = $(this).children("td").eq(1).text();
			$("#seclv_med option").each(function(){
				if($(this).text() == fileSeclvName){
					fileSeclv = this.value;
				}
			});
			if(fileSeclv == undefined){
				alert("该文件密级没有在系统中定义，请联系管理员。");
			}
		}
		
		if(fileSeclv == 0){
//			alert("请选择文件密级");
			$(this).children("td").eq(1).find("select").focus();
			sign = false;
		}else{
			var fileLevel = false;
			$("#seclv_med :selected").nextAll().each(function(){
				if(fileSeclv == this.value){
					fileLevel = true;
				}
			});
//			alert($("#seclv_med").val() + "  " + fileSeclv);
			if($("#seclv_med").val() == fileSeclv){
				fileLevel = true;
			}
			if(!fileLevel){
				sign = fileLevel;
			}			
		}
		info += fileName+" __ "+ fileSeclv + "::";
//		alert(info);
	});
	if($("#seclv_med").val() == ""){
		alert("请选择作业密级");
		$("#seclv_med").focus();
		return false;
	}
	if($("#cycle_type").val() == ""){
		alert("请选择刻录状态");
		$("#cycle_type").focus();
		return false;
	}
	if($("#cd_num").val().trim() == ""){
		alert("刻录份数不能为空");
		$("#cd_num").focus();
		return false;
	}
	var pattern = /^[0-9]*$/;
	if(!pattern.test($("#cd_num").val())){
    	alert("刻录份数只能填写数字");
    	$("#cd_num").focus();
    	return false;
    }
	if($("#cd_num").val() == 0 || $("#cd_num").val() > 10000){
		alert("刻录份数不能为0,或超过10000份");
		$("#cd_num").focus();
		return false;
	}
	if($("#data_type").val() == ""){
		alert("请选择数据类型");
		$("#data_type").focus();
		return false;
	}
	//是否选择了用途
	if($("#usage_code").val() == ""){
		alert("请选择用途");
		$("#usage_code").focus();
		return false;
	}
	//判断保密编号
	/* if($("#conf_code").val() == ""){
		alert("请填写保密编号");
		$("#conf_code").focus();
		return false;
	}
	if(!checkCode($("#conf_code").val())){
    	alert("保密编号中不应有汉字");
    	$("#conf_code").focus();
    	return false;
    } */
	//是否填写了接收人
	if($("#cycle_type").val() == "SEND"){
		if($("#output_dept_name").val().trim() == ""){
			alert("请填写接收单位");
			$("#output_dept_name").focus();
			return false;
		}
		if($("#output_user_name").val().trim() == ""){
			alert("请填写接收人");
			$("#output_user_name").focus();
			return false;
		}
	}
	if(!uploaded){
		alert("请上传刻录文件");
		return false;
	}
	if($("#next_approver_all option").size() > 0 && $("#next_approver_sel option").size() == 0){
		alert("请选择审批人员");
		$("#next_approver_all").focus();
		return false;
	}
	if(!sign){
		alert("上传文件的密级不可高于作业密级");
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
	//提交时提交按钮置灰
	$("#submit_btn").attr("disabled",true);
	$("#info").val(info);
	
    return true;
}
var fileUrl;
var filename;
function addFile(obj){
	if($("#file_upload").val() == null || $("#file_upload").val()==""){
		return;
	} 
	var address = "\\\\"+nas_url+"\\fileswap";  
	var nas =  document.getElementById('nas');
    nas.DeleteNASConnect(address);
	fileUrl = $("#file_upload").val();
    var file_name=getFilename(fileUrl);
    var nameLength = 0;
    if(file_name.length > 100){
		alert("文件名超长(100个字符)，请重命名后重新上传。");
		return;
	}
    if(checkFilename(file_name)){
    	alert("文件名称一样，请重新上传");
    	return;
    }
    nameLength += getFilenameLength();
    nameLength += file_name.length;
	if(nameLength > 500){
		alert("文件名称字数总和已超过500，无法继续上传，请重命名文件或将文件打包后重新上传。");
		return;
	}
    var connected=nas.AddNASConnect(address,nas_username, nas_password);
    if(connected == 0){
	    var tfile = address+"\\"+event_code+"\\";
	    var fileok=nas.MakeSureDirectoryPathExists(tfile);
	    if(!nas.IsDirectoryExists(address)){
		    if(nas.UploadFile(fileUrl,tfile)){
		        alert("文件上传成功");	    
				addRowBefore(file_name);
		    }else{
		        alert("文件上传失败");
		    }
	    }
		nas.DeleteNASConnect(address);
	}else if(connected == 5){
		alert("连接NAS失败，请确认关闭安全NAS系统的WEB管理页面后重试");
	}else if(connected == 1219){
		alert("连接NAS失败，请确认关闭安全NAS系统的文件操作窗口后重试");
	}else{
		alert("连接NAS失败,错误代码:" + connected);
	}
}
function checkFilename(file_name){
	var checked = false;
	$("tr").filter(".files_info").each(function(){
		fileName = $(this).children("td").eq(0).text();
		if(file_name == fileName){
			checked = true;
		}
	});
	return checked;
}
function getFilenameLength(){
	var length = 0;
	$("tr").filter(".files_info").each(function(){
		fileName = $(this).children("td").eq(0).text();
		length += fileName.length;
	});
	return length;
}
function getFilename(url){
	var begin = url.lastIndexOf("\\") + 1;
	return url.substring(begin);
}
function removeFiles(obj){
	$(obj).closest("tr").remove();
}
function addRowBefore(fileName){
	var str="";
	str+='<tr class="files_info"><td align="center">'+fileName+'</td> <td align="center"><select id="uploaded_file_seclv">';
	str+='<option value="">--请选择--</option>';
	$("#seclv_med option").nextAll().each(function(){
		str+='<option value='+this.value+'>'+ this.text+'</option>';
	});	
	str+='</select></td><td align="center"><input class="button_2003" type="button" value="删除文件" id="add_file" onclick="removeFiles(this)"/></td></tr>';
	$("#app").append(str);
}
function getAjaxResult(){
	if(xmlHttp.readyState == 4 && xmlHttp.status == 200){
		//alert(xmlHttp.responseText);
		if(xmlHttp.responseText == "删除成功"){
			$("#pendingDelete").remove();
		}
	}
}
function selectCycle(cycle){
	if(cycle == ""){
		alert("请选择状态,以确认审批流程");
		$("#cycle_type").focus();
		return false;
	}else if($("#seclv_med").val() != "" && $("#usage_code").val() != ""){
		selectNextApprover();
	}
	if(cycle == "SEND"){
		$("#tr_send input").attr("disabled",false);
		$("#tr_send").show();
	}else{
		$("#submit_btn").attr("disabled",false);
		$("#tr_send input").attr("disabled",true);
		$("#tr_send").hide();
	}
	if(cycle == "REMAIN"){
		$("#period").show();
	}else{
		$("#period").hide();
	}
}
function selectSeclv(seclv){
	if(seclv == ""){
		alert("请选择作业密级,以确认审批流程");
		$("#seclv_med").focus();
		return false;
	}else if($("#cycle_type").val() != "" && $("#usage_code").val() != ""){
		selectNextApprover();
	}
}
function selectUsage(usage){
	if(usage == ""){
		alert("请选择用途,以确认审批流程");
		$("#usage_code").focus();
		return false;
	}else if($("#seclv_med").val() != "" && $("#cycle_type").val() != ""){
		selectNextApprover();
	}
}
function selectNextApprover(){
	del_all_True('next_approver_sel');
	var url = "${ctx}/basic/getnextapprover.action";
	$("#jobType").val("BURN_"+$("#cycle_type").val());
	$("#hid_seclv_code").val($("#seclv_med").val());
 	$("tr").filter(".files_info").each(function(){
 		var selectVal = $(this).children("td").eq(1).find("select").val();
	});
	callServerPostForm1(url,document.forms[0]);
}
function getAjaxResult1(){
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
//-->
</script>
<OBJECT ID="nas"
	CLASSID="CLSID:29570FE6-F45D-4627-8338-CD7CD4BD8B88"
	CODEBASE="${ctx}/html/burn/BurnByNAS.cab#version=1,0,0,3">
</OBJECT>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
	<form action="${ctx}/burn/addnasburnevent.action" method="POST" id="AddBurnEventForm">
		<input type="hidden" name="event_code" value="${event_code}" id="event_code"/>
		<input type="hidden" name="dept_id" value="${curUser.dept_id}"/>
		<input type="hidden" name="seclv_code" id="hid_seclv_code"/>
		<input type="hidden" name="jobType" id="jobType"/>
		<input type="hidden" name="next_approver" id="next_approver"/>
		<input type="hidden" name="info" id="info"/>
		<input type="hidden" name="data_type" id="data_type" value="0"/>
		
		<div style="display:none" id="activeObject">
		</div>
	<tr>
	    <td colspan="6" class="title_box">添加刻录申请</td>
	</tr>
	<tr height="40"> 
    	<td width="10%" align="center">申请用户： </td>
    	<td width="23%"><font color="blue"><b>${curUser.user_name}</b></font></td>
    	<td width="10%" align="center">用户部门： </td>
    	<td width="23%"><font color="blue"><b>${curUser.dept_name}</b></font></td>
		<td width="10%" align="center"><font color="red">*</font>&nbsp;状态： </td>
    	<td width="23%">
    		<select id="cycle_type" name="cycle_type" onchange="selectCycle(this.value);">
    			<option value="REMAIN">留用</option>
    			<option value="FILE">归档</option>
    			<option value="SEND">外发</option>
			</select>&nbsp;&nbsp;&nbsp;
    		<select name="period" id="period">
    			<option value="S">短期</option>
				<option value="L">长期</option>
    		</select>
    	</td>
	</tr>
	<tr height="40"> 
		<td align="center"><font color="red">*</font>&nbsp;作业密级：</td>
	    <td>
			<select id="seclv_med" onchange="selectSeclv(this.value);">
				<option value="">--请选择--</option>
				<s:iterator value="#request.seclvList" var="seclv">
					<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
				</s:iterator>
			</select>
    	<td align="center"><font color="red">*</font>&nbsp;用途： </td>
   		<td>
   			<select name="usage_code" id="usage_code" onchange="selectUsage(this.value);">
	   			<option value="">--请选择--</option>
	   			<s:iterator value="#request.usageList" var="usage">
					<option value="${usage.usage_code}">${usage.usage_name}</option>
				</s:iterator>
   			</select>
   		</td>
		</td>
    	<td align="center"><font color="red">*</font>&nbsp;代理刻录： </td>
    	<td><select name="is_proxy" id="is_proxy">
    			<option value="0">否</option>
    			<option value="1">是</option>
    		</select>
    	</td>
    </tr>
    <tr height="40">
    	<td align="center"><font color="red">*</font>&nbsp;刻录份数： </td>
    	<td><input type="text" name="cd_num" id="cd_num" value="1"/></td>
    	<td align="center">&nbsp;保密编号： </td>
    	<td><input type="text" name="conf_code" id="conf_code" /></td>
    	<td align="center">&nbsp;具体说明：</td>
		<td><textarea name="comment" rows="3" cols="40" id="comment"></textarea></td> 	
	</tr>
  	<tr id="tr_send"  height="40" style="display:none"> 
    	<td align="center"><font color="red">*</font>&nbsp;接收单位： </td>
    	<td><input type="text" name="output_dept_name" id="output_dept_name" title="刻录状态为外发时，才需要填写接收单位和接收人"/></td>
    	<td align="center"><font color="red">*</font>&nbsp;接收人： </td>
    	<td colspan="3"><input type="text" name="output_user_name" id="output_user_name" title="刻录状态为外发时，才需要填写接收单位和接收人"/></td>
	</tr>
	<tr id="tr_approver" style="display: none">
  		<td align="center" id="selApprover1">选择审批人：</td>
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
  		<td align="center"><font color="red">*</font>&nbsp;上传导出文件：</td>
  		<td colspan="5">
  			<table width="90%" border="1" cellspacing="0" cellpadding="0" align="left">
  			<tr><td colspan="3"><font color="red">注意：文件名称字数总和不要超过500，若文件过多，请打包上传。</font></td></tr>
  			<tr id="uploadfile_tr">
	  			<td>
	 				<input name="file_upload" type="file" id="file_upload" class="button_2003">
				</td>
		 		<td align="center" width="250">
  					&nbsp;&nbsp;&nbsp;&nbsp;
					 文件密级
				</td>
		  		<td align=center width=300>
					<input type=button value="上传" OnClick="addFile(this)" class="button_2003"/>&nbsp;
					<font id="waitingTx" style="display:none">读取文件中，请稍候...</font>
				</td>
		 	</tr>
		 	<tr id="app"/>
		 	</table>
  		</td>
  	</tr>
  	<tr>
    <td colspan="6" align="center"> 
      <input type="button" class="button_2003" value="提交" onclick="if(chk()) forms[0].submit();" id="submit_btn"/>
      &nbsp;
      <input class="button_2003" type="reset" value="返回"  onClick="javascript:history.go(-1);">&nbsp;
      <font color="red" id="submitPrompt" style="display: none">正在提交处理，请稍等……</font>
    </td>
  </tr>
</table>
</body>
</html>