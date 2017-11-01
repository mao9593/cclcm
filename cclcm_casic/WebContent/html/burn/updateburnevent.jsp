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
	onHover();
	init();
});
function init(){
	$("#cycle_type").val("${event.cycle_type}");
}
function chk()
{	
	var info="";
	var fileName;
	var fileSeclv = 0;
	var jobSeclv = ${event.seclv_code};
	var sign = true;
	$("tr").filter(".files_info").each(function(){
		fileName = $(this).children("td").eq(0).text();
		fileSeclv = $(this).children("td").eq(1).find("select").val();
//		alert(fileSeclv +"  "+ jobSeclv);
		if(fileSeclv == undefined || fileSeclv ==""){
			var fileSeclvName = $(this).children("td").eq(1).text();
			$("#seclv_med option").each(function(){
				if($(this).text() == fileSeclvName){
					fileSeclv = this.value;
				}
			});

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
			if(jobSeclv == fileSeclv){
				fileLevel = true;
			}
			if(!fileLevel){
				sign = fileLevel;
			}			
		}
		info += fileName+" __ "+ fileSeclv + "::";
//		alert(info);
	});
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
	if($("#data_type").val() == ""){
		alert("请选择数据类型");
		$("#data_type").focus();
		return false;
	}
	if($("label").size() == 0){
		alert("请上传刻录文件");
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
	$("#info").val(info);
    return true;
}
function addFile(){
	if($("#seclv_file").val() == ""){
		alert("请选择文件密级");
		$("#seclv_file").focus();
		return false;
	}
	var sign = true;
	//判断密级是否合理的方法
/* 	$("#seclv_file :selected").nextAll().each(function(){
		if($("#hid_seclv_code").val() == this.value){
			alert("上传文件的密级不可高于作业密级");
			sign =  false;
		}
	}); */
	if(sign){
		$('#file_upload').uploadify('upload','*');
	}
}
function addRowBefore(fileName,seclvName,seclvCode){
	var $tr_i = $("<tr class=\"files_info\">");
	var $td_file = $("<td>",{
		align:"center"
	});
	var $td_seclv = '<td align="center">密级：<select id="uploaded_file_seclv"> <option value="0">--请选择--</option>';
	$("#seclv_med option").each(function(){
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
				$("#del_sec_code").val(seclvCode);
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
function delExportFile(tag,fileName){
	if(confirm("确定要删除该上传文件？")){
		$("#del_file_name").val(fileName);
		$("#file_type").val("store");
		$(tag).parent().parent().attr("id","pendingDelete");
		var form = document.getElementById("hiddenDelFileForm");
		callServerPostForm("${ctx}/burn/deluploadedfile.action",form);
	}
}
var errorAlert = false;
	function initUpload() {
  $(function() {
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
			return false;
		},
		'onUploadSuccess' : function(file,data,response) {//上传完成时触发（每个文件触发一次）
			alert("上传完成");
			seclvCode = $("#seclv_file option:selected").val();
			seclvName = $("#seclv_file option:selected").text();
			$("#uploadfile_tr").before(addRowBefore(file.name,seclvName,seclvCode));
			$("#submit_btn").attr("disabled",false);
		},
		'onUploadStart' : function(file) {
			errorAlert = false;
			var uploadabel = true;
			var nameLength = 0;
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
			$("#file_upload").uploadify("settings",'formData',{'event_code':'${event.event_code}','seclv_code':$("#seclv_file").val()});
		},
		'onSelect' : function(file){
			//alert(file.name);
		}
      });
  });
    $("#file_upload").show();
      $("#init_upload").hide();
  };
//-->
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<form action="${ctx}/burn/deluploadedfile.action" method="POST" id="hiddenDelFileForm">
	<input type="hidden" name="seclv_code" id="del_sec_code"/> 
  	<input type="hidden" name="event_code" value="${event.event_code}"/>
  	<input type="hidden" name="file_name" id="del_file_name"/>
  	<input type="hidden" name="file_type" id="file_type"/>
</form>
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
	<form action="${ctx}/burn/updateburnevent.action" method="POST">
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
  			<s:iterator value="#request.burnFileList" var="burnFile">
  				<tr>
  					<td align="center"><label>${burnFile.file_name}</label></td>
  					<td align="center">${burnFile.seclv_name}</td>
  					<td align="center"><a style="cursor: pointer" onclick="delExportFile(this,'${burnFile.file_name}');"><font color="blue"><u>删除</u></font></a></td>
  				</tr>
			</s:iterator>
  			<tr id="uploadfile_tr">
  				<td align="center" width="250" >
  					<input type="button" class="button_2003" id="init_upload" value="点击加载上传控件" onclick="initUpload();"/>
		  			<input type="file" name="file_upload" id="file_upload" disabled="disabled" style="display:none"/>
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
      <input type="button" value="返回" class="button_2003" onclick="javascript:history.go(-1);">
      <font color="red" id="submitPrompt" style="display: none">正在提交处理，请稍等……</font>
    </td>
  </tr>
</table>
</body>
</html>
</html>
