<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/uploadify/uploadify.css"/>
	<script language="JavaScript" src="${ctx}/_script/function.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
 	<script type="text/javascript" src="${ctx}/uploadify/jquery.min.js"></script>
 	<script type="text/javascript" src="${ctx}/uploadify/jquery.uploadify.min.js"></script>
<script>
<!--
$(document).ready(function(){
	init();
});
function init(){
	onHover();
	$("#tr_send input").attr("disabled",true);
	$("#tr_send").hide();
//	UnloadMovie("*.swf");
}


function chk()
{	
	var info="";
	var fileName;
	var fileSeclv = 0;
	var jobSeclv = $("#seclv_med").val();
	var sign = true;
	$("tr").filter(".files_info").each(function(){
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
	if($("label").size() == 0){
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
function addFile(){
	$("#seclv_med").removeAttr("disabled");
	$('#file_upload').uploadify('upload','*');
}
function addRowBefore(fileName){
	var $tr_i = $("<tr class=\"files_info\">");
	var $td_file = $("<td>",{
		align:"center"
	});
	var $td_seclv = '<td align="center">密级：<select id="uploaded_file_seclv"> <option value="0">--请选择--</option>';
	$("#seclv_med option").nextAll().each(function(){
	 	$td_seclv+='<option value="';
	 	$td_seclv+=this.value;
	 	$td_seclv+='">';
	 	$td_seclv+=this.text;
	 	$td_seclv+='</option>';
	});
	$td_seclv+='</select></td>';
	var $td_but = $("<td>",{
		align:"center"
	});
	var $del_font = $("<font>",{
		color:"blue"
	});
	var $del_u = $("<u>",{
		text:"删除"
	});
	var $del_but = $("<a>",{
		style:"cursor:pointer",
		click:function(){
			if(confirm("确定要删除该上传文件？")){
//				$("#del_sec_code").val(seclvCode);
				$("#del_file_name").val(fileName);
				$("#file_type").val("temp");
				$tr_i.attr("id","pendingDelete");
				var form = document.getElementById("hiddenDelFileForm");
				callServerPostForm("${ctx}/burn/deluploadedfile.action",form);
			}
		}
	});
	var $label_file = $("<label>",{
		text:fileName
	});
	$td_file.append($label_file);
	$del_font.append($del_u);
	$del_but.append($del_font);
	$td_but.append($del_but);
	$tr_i.append($td_file);
	$tr_i.append($td_seclv);
	$tr_i.append($td_but);
	return $tr_i;
}
function getAjaxResult(){
	if(xmlHttp.readyState == 4 && xmlHttp.status == 200){
		alert(xmlHttp.responseText);
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
		$("#period").val("S");
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
	callServerPostForm1(url,document.forms[1]);
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
var errorAlert = false;
  function initUpload() {
// 	$(function() {
      $("#file_upload").uploadify({
       	'auto' : false,
       	'method' : "get",
       	'height' : 30,
		'swf' : '${ctx}/uploadify/uploadify.swf', // flash
		'uploader' : '${ctx}/uploadify', // 数据处理url
		'cancelImg': '${ctx}/uploadify/uploadify-cancel.png', // 取消图片
		'width' : 120,
		'fileTypeExts' : '*.*',
		'fileSizeLimit' : 1024 * 1024 * 2,
		'buttonText' : '选择文件',
		'buttonImage' : null,
		'multi':false,
		'successTimeout' : 5,
		'requeueErrors' : true,
		'removeCompleted' : true,
		'queueSizeLimit' :1,
		'queueID'  : 'uploader_queue',
		'progressData' : 'percentage',
		'onUploadError' : function(file,errorCode,errorMsg,errorString,swfuploadifyQueue) {
			$("#file_upload").uploadify('cancel');
			if(!errorAlert){
				alert("用户取消了上传或者上传失败["+file.name+"]");
				errorAlert = true;
			}
			$("#submit_btn").attr("disabled",false);
			$("#seclv_med").attr("disabled",false);
			return false;
		},
		'onUploadSuccess' : function(file,data,response) {//上传完成时触发（每个文件触发一次）
			alert("上传完成");
			$("#uploadfile_tr").after(addRowBefore(file.name));
			$("#submit_btn").attr("disabled",false);
		},
		'onUploadStart' : function(file) {
			errorAlert = false;
			var uploadabel = true;
			var nameLength = 0;
		/* 	if(file.name.indexOf(" ") != -1){
				alert("文件名不能包含空格字符，请修改后重新上传");
				uploadabel = false;
			} */
			if(file.name.length > 100){
				alert("文件名超长(100个字符)，请重命名后重新上传。");
				uploadabel = false;
			}
			if(uploadabel){
				nameLength = 0;
				$("label").each(function(i){
					nameLength += this.innerText.length;
					if(this.innerText == file.name){
						alert("同名文件已经上传，请重新选择文件");
						uploadabel = false;
					}
				});
			}
			if(uploadabel){
				nameLength += file.name.length;
				if(nameLength > 500){
					alert("文件名称字数总和已超过500，无法继续上传，请重命名文件或将文件打包后重新上传。");
					uploadabel = false;
				}
			}
			if(!uploadabel){
				$("#file_upload").uploadify('cancel');
				return false;
			}
			$("#submit_btn").attr("disabled",true);
			$("#hid_seclv_code").val($("#seclv_med").val());
			$("#file_upload").uploadify("settings",'formData',{'event_code':'${event_code}','seclv_code':$("#seclv_file").val()});
		},
		'onSelect' : function(file){
			
		}
      });
      $("#file_upload").show();
      $("#init_upload").hide();
  };
  function upload(){
	  initUpload();
	  addFile();
  }
  function clearAll(){
//  	alert("begin");
//  	$("#file_upload").remove();
//  	alert("end");
//  	$("body").remove();
//  	document.execCommand("stop");
//  	$.ajaxSetup({
//  		cache : false
//  	});
  	//$("html").remove();
//  	UnloadMovie("*.swf");
//  	$("#file_upload")=null;
//  	CollectGarbage();
//  	setTimeout(CollectGarbage,1);
// 	location.reload();
  	//$("#AddBurnEventForm").remove();
  }
//-->
</script>
</head>
<body oncontextmenu="self.event.returnValue=false" onbeforeunload="clearAll();">
<form action="${ctx}/burn/deluploadedfile.action" method="POST" id="hiddenDelFileForm">
	<input type="hidden" name="seclv_code" id="del_sec_code"/> 
  	<input type="hidden" name="event_code" value="${event_code}"/>
  	<input type="hidden" name="file_name" id="del_file_name"/>
  	<input type="hidden" name="file_type" id="file_type"/>
</form>
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
	<form action="${ctx}/burn/addburnevent.action" method="POST" id="AddBurnEventForm">
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
		</td>
    	<td align="center"><font color="red">*</font>&nbsp;用途： </td>
   		<td>
   			<select name="usage_code" id="usage_code" onchange="selectUsage(this.value);">
	   			<option value="">--请选择--</option>
	   			<s:iterator value="#request.usageList" var="usage">
					<option value="${usage.usage_code}">${usage.usage_name}</option>
				</s:iterator>
   			</select>
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
  	<tr id="tr_send"  height="40"> 
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
  			<tr><td colspan="3"><font color="red">注意：单个文件大小不能超过2G。文件名称字数总和不要超过500，若文件过多，请打包上传。若无法选择文件，请点击<a href="${ctx}/player/p.exe" style="font-style: color:blue;"> 安装Flash Player </a></font></td></tr>
  			<tr id="uploadfile_tr">
  				<td align="center" width="250" >
  					<input type="button" class="button_2003" id="init_upload" value="点击加载上传控件" onclick="initUpload();"/>
		  			<input type="file" name="file_upload" id="file_upload" disabled="disabled" style="display:none"/>
		  			<!--  div id="file_upload" /-->
		 		</td>
		 		<td align="center" width="250">
  					&nbsp;&nbsp;&nbsp;&nbsp;
					 文件密级
				</td>
				<td align="center" width="250">
					<input type="button" onclick="addFile();" value="上传" class="button_2003"/>&nbsp;
		  			<input type="button" onclick="$('#file_upload').uploadify('stop')" value="取消" class="button_2003"/>&nbsp;
		  		</td>
		 	</tr>
		 	<tr><td colspan="3"><div id="uploader_queue" style="width: 750px;height: 30px"></div></td></tr>
		 	</table>
  		</td>
  	</tr>
  	<tr>
    <td colspan="6" align="center"> 
      <input type="button" class="button_2003" value="提交" onclick="if(chk()) forms[1].submit();" id="submit_btn"/>
      &nbsp;
      <input class="button_2003" type="reset" value="返回"  onClick="javascript:history.go(-1);">&nbsp;
      <font color="red" id="submitPrompt" style="display: none">正在提交处理，请稍等……</font>
    </td>
  </tr>
</table>
</body>
</html>