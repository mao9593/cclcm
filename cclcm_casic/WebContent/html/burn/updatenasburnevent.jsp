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
function init(){
	onHover();
	$("#cycle_type").val("${event.cycle_type}");
	nas_url = "${nas_url}";
	nas_username = "${nas_username}";
	nas_password = "${nas_password}";
}
function delExportFile(tag,fileName){
	if(confirm("确定要删除该上传文件？")){
		removeFiles(tag);
	}
}
function removeFiles(obj){
	$(obj).closest("tr").remove();
}
function chk()
{	
	var info="";
	var fileName;
	var fileSeclv = 0;
//	var jobSeclv = $("#seclv_med").val();
	var jobSeclv = ${event.seclv_code};
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
			$("#seclv_med").val(jobSeclv);
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
    var connected=nas.AddNASConnect(address,nas_username+"\\fileswap", nas_password);
    if(connected == 0){
	    var tfile = address+"\\"+'${event.event_code}'+"\\";
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
	$("#seclv_med option").each(function(){
		str+='<option value='+this.value+'>'+ this.text+'</option>';
	});	
	str+='</select></td><td align="center"><input class="button_2003" type="button" value="删除文件" id="add_file" onclick="removeFiles(this)"/></td></tr>';
	$("#app").append(str);
}
function getAjaxResult(){
	if(xmlHttp.readyState == 4 && xmlHttp.status == 200){
		alert(xmlHttp.responseText);
		if(xmlHttp.responseText == "删除成功"){
			$("#pendingDelete").remove();
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
	<form action="${ctx}/burn/updatenasburnevent.action" method="POST">
		<input type="hidden" name="event_code" value="${event.event_code}" id="event_code"/>
		<input type="hidden" name="seclv_code" id="hid_seclv_code" value="${event.seclv_code}"/>
		<input type="hidden" name="update" value="Y"/>
		<input type="hidden" name="info" id="info"/>
	<tr>
	    <td colspan="6" class="title_box">修改刻录作业</td>
	</tr>
	<tr style="display:none">
		 <td>
			<select id="seclv_med" onchange="selectSeclv(this.value);" type="hidden">
				<s:iterator value="#request.seclvList" var="seclv">
					<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
				</s:iterator>
			</select>
		</td>
	</tr>
	<tr> 
    	<td width="10%" align="center">申请用户： </td>
    	<td width="23%"><font color="blue"><b>${curUser.user_name}</b></font></td>
    	<td width="10%" align="center">用户部门： </td>
    	<td width="23%"><font color="blue"><b>${curUser.dept_name}</b></font></td>
    	<td width="10%" align="center">状态： </td>
    	<td width="23%"><font color="blue"><b>${event.cycle_type_name}&nbsp;${event.period_name}</b></font></td>
	</tr>
	<tr> 
		<td align="center">作业密级： </td>
    	<td><font color="blue"><b>${event.seclv_name}</b></font></td>
    	<td align="center">用途： </td>
    	<td><font color="blue"><b>${event.usage_name}</b></font></td>
    	<td align="center"><font color="red">*</font>&nbsp;数据类型： </td>
    	<td><select name="data_type" id="data_type">
    			<option value="">--请选择--</option>
    			<option value="0" <c:if test="${event.data_type == '0'}">selected</c:if>>数据刻录</option>
    			<%-- <option value="1" <c:if test="${event.data_type == '1'}">selected</c:if>>镜像刻录</option>
    			<option value="2" <c:if test="${event.data_type == '2'}">selected</c:if>>音视频刻录</option> --%>
    		</select>
    	</td>
    </tr>
    <tr>
    	<td align="center"><font color="red">*</font>&nbsp;刻录份数： </td>
    	<td><input type="text" name="cd_num" id="cd_num" value="${event.cd_num}"/></td>
    	<td align="center">&nbsp;保密编号： </td>
    	<td><input type="text" name="conf_code" id="conf_code" value="${event.conf_code}"/></td>
  		<td align="center">&nbsp;备注：</td>
		<td><textarea name="summ" rows="3" cols="40" id="summ">${event.summ}</textarea></td>
  	</tr>
	</form>
  	<tr>
  		<td align="center"><font color="red">*</font>&nbsp;上传导出文件：</td>
  		<td colspan="5">
  			<table width="90%" border="1" cellspacing="0" cellpadding="0" align="left">
  			<tr><td colspan="3"><font color="red">注意：文件名称字数总和不要超过500，若文件过多，请打包上传。若无法选择文件，请点击<a href="${ctx}/player/p.exe" style="font-style: color:blue;"> 安装Flash Player </a></font></td></tr>
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
  			<s:iterator value="#request.burnFileList" var="burnFile">
  				<tr class="files_info">
  					<td align="center">${burnFile.file_name}</td>
  					<td align="center">${burnFile.seclv_name}</td>
  					<td align="center"><a style="cursor: pointer" onclick="delExportFile(this);"><font color="blue"><u>删除</u></font></a></td>
  				</tr>
			</s:iterator>
			<tr id="app"/>
		 	<tr><td colspan="3"><div id="uploader_queue" style="width: 750px;height: 30px"></div></td></tr>
		 	</table>
  		</td>
  	</tr>
  	<tr>
    <td colspan="6" align="center"> 
      <input type="button" class="button_2003" value="提交" onclick="if(chk()) forms[0].submit();" id="submit_btn"/>
      &nbsp;
      <input type="button" value="返回" class="button_2003" onclick="javascript:history.go(-1);">
      <font color="red" id="submitPrompt" style="display: none">正在提交处理，请稍等……</font>
    </td>
  </tr>
</table>
</body>
</html>
