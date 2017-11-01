<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>上传文件</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen"/>
	<link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/uploadify/uploadify.css"/>
	<script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script type="text/javascript" src="${ctx}/uploadify/jquery.uploadify.min.js"></script>
	<script  language="JavaScript" >
	<!--
	$(document).ready(function(){
		onHover();
	});
	function addFile(){
		$('#file_upload').uploadify('upload','*');
	}
	var errorAlert = false;
	function initUpload() {
	 $(function() {
      	$("#file_upload").uploadify({
	       	'auto' : false,
	       	'method' : "get",
	       	'height' : 30,
			'swf' : '${ctx}/uploadify/uploadify.swf', // flash
			'uploader' : '${ctx}/uploadpermissionfile', // 数据处理url
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
				return false;
			},
			'onUploadSuccess' : function(file,data,response) {//上传完成时触发（每个文件触发一次）
				alert("授权文件上传完成, 需要重新启动WEB服务器生效");
			},
			'onUploadStart' : function(file) {
				errorAlert = false;
				$("#need_upload_file_name").val(file.name);
				$("#file_upload").uploadify("settings",'formData');
			},
			'onSelect' : function(file){
				if(checkPass(file)){
					return true;
				} 
			}
	      });
	  });
	   $("#file_upload").show();
      $("#init_upload").hide();
  };
	  
	function checkPass(file){
		if(file.name != "License.copyright"){
			alert("文件名不正确");
			$("#file_upload").uploadify('cancel');
			return false;
		}
		return true;
	}
	//-->
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<form method="post" action="${ctx}/uploadpermissionfile.action">
	<input type="hidden" name="need_upload_file_name" id="need_upload_file_name"/>
   	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="90%">
	 <tr>
		 <td colspan="6" class="title_box">
            	上传授权许可文件
        </td>
    </tr>
    <tr>
  		<td align="center"><font color="red">*</font>&nbsp;上传文件：</td>
  		<td colspan="3">
  			<table width="90%" border="1" cellspacing="0" cellpadding="0" align="left">
  			<tr><td colspan="3"><font color="red">注意：单个文件大小不能超过2G。若无法选择文件，请点击<a href="${ctx}/player/p.exe" style="font-style: color:blue;"> 安装Flash Player </a></font></td></tr>
  			<tr id="uploadfile_tr">
  				<td align="center" width="250">
  					<input type="button" class="button_2003" id="init_upload" value="点击加载上传控件" onclick="initUpload();"/>
		  			<input type="file" name="file_upload" id="file_upload" disabled="disabled" style="display:none"/>
		 		</td>
				<td align="center" width="250">
					<input type="button" onclick="addFile();" value="上传" class="button_2003" id="btn_upload"/>&nbsp;
		  			<input type="button" onclick="$('#file_upload').uploadify('stop')" value="取消" class="button_2003"/>&nbsp;
		  		</td>
		 	</tr>
		 	<tr><td colspan="3"><div id="uploader_queue" style="width: 750px;height: 30px"></div></td></tr>
		 	</table>
  		</td>
  	</tr>
  	<tr>
	    <td colspan="4" align="center"> 
	      <input class="button_2003" type="reset" value="返回"  onClick="javascript:history.go(-1);" id="reset_btn">&nbsp;
	    </td>
	</tr>
  	</table>
</form>
</center>
</body>
</html>