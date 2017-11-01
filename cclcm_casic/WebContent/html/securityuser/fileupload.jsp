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
 	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>	
 	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
 	<script language="JavaScript" src="${ctx}/_script/common.js"></script>
 	<script language="javascript" src="${ctx}/_script/casic304_common_bm.js"></script>
 	<script type="text/javascript" src="${ctx}/uploadify/jquery.uploadify.min.js"></script>
<script>

function addFile(){
	$('#file_upload').uploadify('upload','*');
}

function addRowBefore(fileName){
	var $tr_i = $("<tr class=\"files_info\">");
	var $td_file = $("<td>",{
		align:"center"
	});

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
				$("#del_file_name").val(fileName);
				$("#file_type").val("temp");
				$tr_i.attr("id","pendingDelete");
				var form = document.getElementById("hiddenDelFileForm");
				callServerPostForm1("${ctx}/securityuser/deluploadedfile.action?",form);
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
	$tr_i.append($td_but);
	return $tr_i;
}

function getAjaxResult1(){
	if(xmlHttp.readyState == 4 && xmlHttp.status == 200){
		alert(xmlHttp.responseText);
		if(xmlHttp.responseText == "删除成功"){
			$("#pendingDelete").remove();
		}
	}
}

var errorAlert = false;
function initUpload() {

      $("#file_upload").uploadify({
       	'auto' : false,
       	'method' : "get",
       	'height' : 30,
		'swf' : '${ctx}/uploadify/uploadify.swf', // flash
		'uploader' : '${ctx}/uploaduserinfosepcialfile', // 数据处理url
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
			$("#uploadfile_tr").after(addRowBefore(file.name));
			
			$("#submit_btn").attr("disabled",false);
		},
		'onUploadStart' : function(file) {
			errorAlert = false;
			var uploadabel = true;
			var nameLength = 0;
			if(file.name.indexOf(" ") != -1){
				alert("文件名不能包含空格字符，请修改后重新上传");
				uploadabel = false;
			} 
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
			$("#file_upload").uploadify("settings",'formData',{'event_code':'${event_code}'});
		},
		'onSelect' : function(file){
			
		}
      });
      $("#file_upload").show();
      $("#init_upload").hide();
}
function upload(){
	 initUpload();
	 addFile();
}

function delFile(fileName){
	if(confirm("确定要删除该上传文件？")){
		$("#del_file_name").val(fileName);
		var form = document.getElementById("hiddenDelFileForm");
		callServerPostForm1("${ctx}/securityuser/deluploadedfile.action",form);
	}
}
	
function downloadFile(file_path,file_name){
	document.getElementById("file_path").value=file_path;
	document.getElementById("file_name").value=file_name;
	document.getElementById("uploadForm").submit();
}
function getFrameReturn(){
}
function chk(){
	var ret = new Object();
	ret.filename = "";
	$("label").each(function(i){
		if($("#del_file_name").val() && $("#del_file_name").val() == this.innerText){
	
		}else{
			ret.filename = ret.filename+this.innerText+":";
		}
	});

	window.returnValue=ret;
	window.close();  
/* 	alert("filename:"+ret.filename);
	if($("#del_file_name").val()){alert($("#del_file_name").val());
		var fname = encodeURI(encodeURI($("#del_file_name").val()));
		window.location.href="${ctx}/securityuser/fileupload.action?filename="+fname;	
	} */
}
function chk_submit(){
	var form = document.getElementById("fileuploadform");
	callServerPostForm("${ctx}/securityuser/fileupload.action",form);
}
function getAjaxResult(){
	if(xmlHttp.readyState == 4 && xmlHttp.status == 200){
		alert(xmlHttp.responseText);
	}
}
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<form action="${ctx}/burn/downloadfile.action" target="hidden_frame" id="uploadForm" method="post">
	<input type="hidden" name="file_path" id="file_path"/>
	<input type="hidden" name="file_name" id="file_name"/>
</form>
<iframe height="0" width="0" frameborder="0" id="hidden_frame" name="hidden_frame" onload="getFrameReturn();"></iframe>
<form action="${ctx}/securityuser/deluploadedfile.action" method="POST" id="hiddenDelFileForm">
  	<input type="hidden" name="file_name" id="del_file_name"/>
</form>
<form action="${ctx}/securityuser/fileupload.action" method="POST" id="fileuploadform" name="fileuploadform">
	<input type="hidden" id="flag" name="flag" value="${flag}"/>
	<input type="hidden" id="filetype" name="filetype" value="Y"/>
	<input type="hidden" id="dname" name="dname" />
	<input type="hidden" id="filename" name="filename" value="${filename}"/>
<table width="450" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">	
	<tr>
		<td colspan="2" class="title_box">附件管理</td>   
	</tr>
	<tr>
  		<td width="450">
  			<table width="450" border="1" cellspacing="0" cellpadding="0" align="left">
	  			<tr><td colspan="2"><font color="red">注意：单个文件大小不能超过2G。若无法选择文件，请点击<a href="${ctx}/player/p.exe" style="font-style: color:blue;"> 安装Flash Player </a></font></td></tr>
	  			<tr id="uploadfile_tr">
	  				<td align="center" width="350">
	  					<input type="button" class="button_2003" id="init_upload" value="点击加载上传控件" onclick="initUpload();"/>
			  			<input type="file" name="file_upload" id="file_upload" disabled="disabled" style="display:none"/>
			 		</td>
					<td align="center" width="100">
						<input type="button" onclick="addFile();" value="上传" class="button_2003"/>&nbsp;	
			  		</td>
			 	</tr>
			 	<c:if test="${flag eq 'Y'}">
				 	<s:iterator value="#request.userInfoFileList" var="userFile">
		  				<tr id="'${userFile.file_name}'" style="display:block">
		  					<td align="center"><u><label style="color: blue;cursor: pointer;" onclick="downloadFile('${userFile.file_path}','${userFile.file_name}');">${userFile.file_name}</label></u></td>	  		
		  					<td><input type="button" value="删除" onclick="delFile('${userFile.file_name}');" class="button_2003"></td>
		  				</tr>
					</s:iterator>
				</c:if>
				<tr><td colspan="2"><div id="uploader_queue" style="width: 300;height: 30px"></div></td></tr>			 	
		 	</table>
  		</td>
  	</tr>
  	<tr> <td colspan="2" align="center"><input type="button" id="submit_btn" value="确定" onclick="chk();" class="button_2003"></td></tr>
 </table>
</form>
</body>
</html>