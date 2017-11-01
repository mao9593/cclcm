<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>上传扫描文件</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen"/>
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/uploadify/uploadify.css"/>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script type="text/javascript" src="${ctx}/uploadify/jquery.uploadify.min.js"></script>
	<script  language="JavaScript" >
	<!--
	function addFile(){
		$('#file_upload').uploadify('upload','*');
	}
	var errorAlert = false;
	function initUpload() {
      	$("#file_upload").uploadify({
	       	'auto' : false,
	       	'method' : "get",
	       	'height' : 30,
			'swf' : '${ctx}/uploadify/uploadify.swf', // flash
			'uploader' : '${ctx}/uploadscan', // 数据处理url
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
				$("#submitaction").submit();
				$("#submit_btn").attr("disabled",true);
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
				if(!uploadabel){
					$("#file_upload").uploadify('cancel');
					return false;
				}
				$("#submit_btn").attr("disabled",true);
				$("#file_upload").uploadify("settings",'formData',{'event_code':'${event_code}'});
			},
			'onSelect' : function(file){
				$("#submit_btn").attr("disabled",false);
			}
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
	function chkSubmit(){
		$("#update").val("Y");
		$("#submitaction").submit();
	}
	//-->
	</script>
</head>
<body oncontextmenu="returnValue=false">
<center>
<form method="post" action="${ctx}/enter/uploadscanfiles.action" id="submitaction">
	<input type="hidden" name="delfilepath" id="delfilepath" value=""/>
	<input type="hidden" name="update" id="update" value="N"/>
	<input type="hidden" name="event_code" id="event_code" value="${event_code}"/>
   	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="90%">
	 <tr>
		 <td colspan="6" class="title_box">
            	上传扫描文件
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
					<input type="button" onclick="addFile();" value="上传" disabled="disabled" class="button_2003" id="submit_btn"/>&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" onclick="$('#file_upload').uploadify('stop')" value="取消" class="button_2003"/>&nbsp;
		  		</td>
		 	</tr>
		 	<tr><td colspan="3"><div id="uploader_queue" style="width: 750px;height: 30px"></div></td></tr>
		 	</table>
  		</td>
  	</tr>
  	<tr>	
  		<td align="center">&nbsp;已上传扫描文件列表：</td>
  		<td colspan="3">
  			<table width="95%" border="1" cellspacing="0" cellpadding="0" align="left">
	 			<tr>
	   				<td>
			  			<display:table requestURI="${ctx}/enter/uploadscanfiles.action" id="item" class="displaytable" name="fileList">
							<display:column property="file_name" title="文档名称" maxLength="50"/>
							<display:column title="删除">
								<a href="#" onclick="deleteFile('${item.file_name}');">删除</a>
							</display:column>
							<display:column title="操作">
								<a href="${ctx}/files/setup/${event_code}/${item.file_name}"><u>下载</u></a>
							</display:column>
						</display:table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="4" align="center">
			<input type="button" class="button_2003" value="提交" onclick="chkSubmit();"/>&nbsp;&nbsp;
      		<input type="button" class="button_2003" value="返回" onClick="go('${ctx}/enter/managescanlist.action')">&nbsp;
		</td>
	</tr>
</table>	
</form>
</center>
</body>
</html>