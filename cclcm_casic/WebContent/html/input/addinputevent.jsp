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
$(document).ready(function(){
	onHover();
});

function chk(){
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

	//是否选择了用途
	if($("#usage_code").val() == ""){
		alert("请选择用途");
		$("#usage_code").focus();
		return false;
	}
	
	//是否选择了介质类型
	if($("#med_type").val() == ""){
		alert("请选择介质类型");
		$("med_type").focus();
		return false;
	}
	
	if($("label").size() == 0){
		alert("请上传要导入的电子文件");
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
function selectSeclv(seclv){
	if(seclv == ""){
		alert("请选择作业审批密级,以确认审批流程");
		$("#seclv_med").focus();
		return false;
	}else if($("#usage_code").val() != "") {
		selectNextApprover();
	}
}
 function selectUsage(usage){
	if(usage == ""){
		alert("请选择用途,以确认审批流程");
		$("#usage_code").focus();
		return false;
	}else if($("#seclv_med").val() != ""){
		selectNextApprover();
	}
}

function selectNextApprover(){
	del_all_True('next_approver_sel');
	var url = "${ctx}/basic/getnextapprover.action";
	$("#jobType").val("MSG_INPUT");
	$("#hid_seclv_code").val($("#seclv_med").val());
 	$("tr").filter(".files_info").each(function(){
 		var selectVal = $(this).children("td").eq(1).find("select").val();
	});
	callServerPostForm1(url,document.forms[1]);
}
function setMedType(med_type){
	$("#med_type").val(med_type);
}
function getAjaxResult(){
	if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
		alert(xmlHttp.responseText);
		if(xmlHttp.responseText == "删除成功"){
			$("#pendingDelete").remove();
		}
	}
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
				callServerPostForm("${ctx}/input/deluploadedfile.action",form);
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
function addFile(){
	$("#seclv_med").removeAttr("disabled");
	$('#file_upload').uploadify('upload','*');
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
</script>
</head>

<body oncontextmenu="self.event.returnValue=false">
<form action="${ctx}/input/deluploadedfile.action" method="POST" id="hiddenDelFileForm">
	<input type="hidden" name="seclv_code" id="del_sec_code"/> 
  	<input type="hidden" name="event_code" value="${event_code}"/>
  	<input type="hidden" name="file_name" id="del_file_name"/>
  	<input type="hidden" name="file_type" id="file_type"/>
</form>
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box" >
 <form action="${ctx}/input/addinputevent.action" method="POST" id="AddInputEventForm">
		
		<input type="hidden" name="event_code" value="${event_code}" id="event_code"/>
		<input type="hidden" name="dept_id" value="${curUser.dept_id}"/>
		<input type="hidden" name="seclv_code" id="hid_seclv_code"/>		
	    <input type="hidden" name="jobType" id="jobType"/>	    	  
	    <input type="hidden" name="next_approver" id="next_approver"/>
	    <input type="hidden" name="info" id="info"/>
	    <input type="hidden" name="med_type" id="med_type"/>
	    <input type="hidden" name="summ" id="summ"/>
	    <input type="hidden" name="cd_num" id="cd_num"/>
	    <input type="hidden" name="internet_num" id="internet_num"/>
	    
	    <div style="display:none" id="activeObject"></div>
	 <tr>
	    <td colspan="6" class="title_box">添加文件导入申请</td>
	</tr>
	<tr> 
    	<td width="13%" align="center">申请用户： </td>
    	<td width="20%"><font color="blue"><b>${curUser.user_name}</b></font></td>
    	<td width="13%" align="center">用户部门： </td>
    	<td width="20%"><font color="blue"><b>${curUser.dept_name}</b></font></td>
    	<td width="13%" align="center"><font color="red">*</font>&nbsp;密级：</td>
	    <td width="21%">
	   	    <select name="seclv_med" id="seclv_med" onchange="selectSeclv(this.value);">
				<option value="">--请选择--</option>
				<s:iterator value="#request.seclvList" var="item">
					<option value="${item.seclv_code}">${item.seclv_name}</option>
				</s:iterator>
			</select>
		</td>		
	</tr>
	<tr>
		<td align="center"><font color="red">*</font>&nbsp;用途： </td>
   		<td colspan="5">
   			<select name="usage_code" id="usage_code" onchange="selectUsage(this.value);">
	   			<option value="">--请选择--</option>
	   			<s:iterator value="#request.usageList" var="usage">
					<option value="${usage.usage_code}">${usage.usage_name}</option>
				</s:iterator>
   			</select>
   		</td>
	</tr>
	
	<tr>
	  <td align="center"><font color="red">*</font>输入介质类型</td>
	  <td colspan="5">
	    <input type="radio" value="U盘" name="med_type" onclick="setMedType(this.value)"/><font color="blue">U盘&nbsp;&nbsp;</font>
		<input type="radio" value="光盘" name="med_type" onclick="setMedType(this.value)"/><font color="blue">光盘&nbsp;&nbsp;</font>
		<input type="radio" value="存储卡" name="med_type" onclick="setMedType(this.value)"/><font color="blue">存储卡&nbsp;&nbsp;</font>
		<input type="radio" value="其它" name="med_type" onclick="setMedType(this.value)"/><font color="blue">其它&nbsp;&nbsp;</font>
	  </td>
	  </td>
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
	<tr>
	 <td colspan="6">
		   <table width="100%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
			     <tr>
			         <td rowspan="6" align="center">文件信息</td>
			     </tr>
			     <!-- <tr >
			        	<td rowspan="3" align="center" width="15%">文件归属</td>
			     </tr> -->
			     <tr>
				       <td align="center" width="15%">单位/ 个人</td>
				       <td colspan="4"><input type="text" name="personal" id="personal"/></td>           
			     </tr>
			     <tr>
				       <td align="center" width="15%">说明(备注)</td>
				       <td colspan="4"><input type="text" name="summ" id="summ"/></td>           
			     </tr>
			     <!-- <tr >
			        	<td rowspan="3" align="center" width="15%">互联网</td>
			     </tr> -->
			     <tr>
				      <td align="center" width="15%">网上/邮件地址</td>
				      <td colspan="4"><input type="text" name="address" id="address"/></td> 
			      </tr>
			      <!-- <tr>
			       	    <td align="center">文件数量</td>
			    		<td colspan="4"><input type="text" name="file_num" id="file_num"/></td> 
			     </tr> -->
		   </table>
	   </td>
	 </tr>
	 <tr>
  	   	<td width="15%" align="center">中转盘号： </td>
	    <td colspan="2"><input type="text" name="cd_num" id="cd_num"/></td> 
		<td width="15%" align="center">互联网盘号：</td>
		<td colspan="2"><input type="text" name="internet_num" id="internet_num"/></td> 
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
	     <input type="button" class="button_2003" value="提交" onclick="if(chk()) forms[1].submit();" id="submit_btn"/>&nbsp;
	     <input class="button_2003" type="button" value="返回" onclick="javascript:history.go(-1)">&nbsp;
	   </td>
	 </tr>
</table>
</body>
</html>
