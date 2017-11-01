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
		//var SprintComData = new ActiveXObject("SprintCom.DataProcess.1");
		//SprintComData.UnzipEmf( $("#file_upload"));
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
			'uploader' : '${ctx}/uploadsetup', // 数据处理url
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
				$("#btn_upload").attr("disabled",true);
				return false;
			},
			'onUploadSuccess' : function(file,data,response) {//上传完成时触发（每个文件触发一次）
				alert("上传完成, 文件名：" + file.name );
				$("#submitaction").submit();
				$("#btn_upload").attr("disabled",true);
				//$("#uploadfile_tr").before(addRowBefore(file.name));
			},
			'onUploadStart' : function(file) {
				errorAlert = false;
			},
			'onSelect' : function(file){
				if($("label").size() > 0){
					$("#file_upload").uploadify('cancel');
					alert("一次最多上传一个文件");
					return false;
				}
				if(checkPass(file) ){
					$("#btn_upload").attr("disabled",false);
					return true;
				}
				$("#btn_upload").attr("disabled",true);
				return false;
			}
	      });
	  });
	  $("#file_upload").show();
      $("#init_upload").hide();
  };

	function checkPass(file){
		var pattern=/^HD.*\.[rar|zip|exe]+$/;
		if(!pattern.test(file.name)){
			alert("安装包文件名格式不正确，请检查后重新上传。");
			$("#file_upload").uploadify('cancel');
			return false;
		}
		return true;
	}
	function deleteFile(filename){
		//alert(filepath);
		if(confirm("确定要删除文件：" + filename + "？")){
			$("#delfilepath").val(filename);
			$("#submitaction").submit();
		}
	}
	//-->
	</script>
</head>
<body oncontextmenu="returnValue=false">
<center>
<form method="post" action="${ctx}/basic/uploadsetupfiles.action" id="submitaction">
	<input type="hidden" name="delfilepath" id="delfilepath" value=""/>
   	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="90%">
	 <tr>
		 <td colspan="6" class="title_box">
            	安装文件管理
        </td>
    </tr>
    <tr>
  		<td align="center" width="150"><font color="red">*</font>&nbsp;上传文件：</td>
  		<td colspan="3">
  			<table width="90%" border="1" cellspacing="0" cellpadding="0" align="left">
  			<tr><td colspan="3"><font color="red">注意：单个文件大小不能超过2G。若无法选择文件，请点击<a href="${ctx}/player/p.exe" style="font-style: color:blue;"> 安装Flash Player </a></font></td></tr>
  			<tr id="uploadfile_tr">
  				<td align="center" width="250">
  					<input type="button" class="button_2003" id="init_upload" value="点击加载上传控件" onclick="initUpload();"/>
		  			<input type="file" name="file_upload" id="file_upload" disabled="disabled" style="display:none"/>
		 		</td>
				<td align="center" width="250">
					<input type="button" onclick="addFile();" value="上传" disabled="disabled" class="button_2003" id="btn_upload"/>&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" onclick="$('#file_upload').uploadify('stop')" value="取消" class="button_2003"/>&nbsp;
		  		</td>
		 	</tr>
		 	<tr><td colspan="3"><font color="red">注意：只能上传exe、rar、zip格式的文件</font><div id="uploader_queue" style="width: 750px;height: 30px"></div></td></tr>
		 	</table>
  		</td>
  	</tr>
  	<tr>	
  		<td align="center">&nbsp;已上传安装文件列表：</td>
  		<td colspan="3">
  			<table width="90%" border="1" cellspacing="0" cellpadding="0" align="left">
			<s:iterator value="#request.fileNameList" var="fileName">
	  			<tr>
	  				<td align="center">
	  		  			<a href="${ctx}/files/setup/${fileName}">${fileName}</a>
	  				</td>
	  				<td align="center">
	  					<a href="#" onclick="deleteFile('${fileName}');">删除</a>
	  				</td>
	  			</tr>
			</s:iterator>
			</table>
		</td>
	</tr>
</table>	
</form>
</center>
</body>
</html>