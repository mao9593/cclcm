<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>上传常用文件</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/uploadify/uploadify.css"/>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script type="text/javascript" src="${ctx}/uploadify/jquery.uploadify.min.js"></script>
	<script  language="JavaScript" >
	<!--
	$(document).ready(function(){
		onHover();
	});
	function addFile(){
		$('#file_upload').uploadify('upload','*');
	}
	var code_pattern_addword=/^[0-9a-zA-Z_\.\u4e00-\u9fa5]{1,100}$/;
	function checkFileName(value){
		if(!code_pattern_addword.test(value)){
			return false;
		}
		return true;
	}
	var errorAlert = false;
	function initUpload() {
	 $(function() {
      	$("#file_upload").uploadify({
	       	'auto' : false,
	       	'method' : "get",
	       	'height' : 30,
			'swf' : '${ctx}/uploadify/uploadify.swf', // flash
			'uploader' : '${ctx}/uploadfile', // 数据处理url
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
			},
			'onUploadStart' : function(file) {
				errorAlert = false;
				$("#btn_upload").attr("disabled",true);
			},
			'onSelect' : function(file){
				var uploadabel = true;
				if(!checkFileName(file.name)){
					alert("文件名称只能由数字、字母、汉字或下划线组成，长度小于100位");
					uploadabel = false;
				}
				if(!uploadabel){
					$("#file_upload").uploadify('cancel');
					errorAlert = true;
					return false;
				}
				$("#btn_upload").attr("disabled",false);
			}
	      });
	  });
	  $("#file_upload").show();
      $("#init_upload").hide();
  };

	function deleteFile(filename){
		if(confirm("确定要删除文件[" + filename + "]？")){
			$("#delfilepath").val(filename);
			$("#submitaction").submit();
		}
	}
	//-->
	</script>
</head>
<body oncontextmenu="returnValue=false">
<center>
<form method="post" action="${ctx}/basic/uploadfile.action" id="submitaction">
	<input type="hidden" name="delfilepath" id="delfilepath" value=""/>
   	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="90%">
	 <tr>
		 <td colspan="6" class="title_box">
            	常用文档管理
        </td>
    </tr>
    <tr>
  		<td align="center" width="150"><font color="red">*</font>&nbsp;上传文件：</td>
  		<td colspan="3">
  			<table width="95%" border="1" cellspacing="0" cellpadding="0" align="left">
	  			<tr><td colspan="2"><font color="red">注意：单个文件大小不能超过2G。若无法选择文件，请点击<a href="${ctx}/player/p.exe" style="font-style: color:blue;"> 安装Flash Player </a></font></td></tr>
	  			<tr id="uploadfile_tr">
	  				<td align="center" width="50%">
	  					<input type="button" class="button_2003" id="init_upload" value="点击加载上传控件" onclick="initUpload();"/>
			  			<input type="file" name="file_upload" id="file_upload" disabled="disabled" style="display:none"/>
			 		</td>
					<td align="center" width="50%">
						<input type="button" onclick="addFile();" value="上传" disabled="disabled" class="button_2003" id="btn_upload"/>&nbsp;&nbsp;
						<input type="button" onclick="$('#file_upload').uploadify('stop')" value="取消" class="button_2003"/>&nbsp;
			  		</td>
			 	</tr>
				<tr><td colspan="2"><div id="uploader_queue" style="width: 750px;height: 30px"></div></td></tr>			 	
		 	</table>
  		</td>
  	</tr>
  	<tr>	
  		<td align="center">&nbsp;已上传常用文档列表：</td>
  		<td colspan="3">
  			<table width="95%" border="1" cellspacing="0" cellpadding="0" align="left">
	 			<tr>
	   				<td>
			  			<display:table requestURI="${ctx}/basic/uploadfile.action" id="item" class="displaytable" name="fileList">
							<display:column property="file_name" title="文档名称" maxLength="50"/>
							<display:column title="操作">
								<a href="${ctx}/${item.file_path}"><u>下载</u></a>
								<a href="#" onclick="deleteFile('${item.file_name}');">删除</a>
							</display:column>
						</display:table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>	
</form>
</center>
</body>
</html>