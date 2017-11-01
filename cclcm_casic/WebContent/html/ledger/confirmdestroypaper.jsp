<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<base target="_self"/>
<title>回执单管理</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
	<script language="JavaScript" src="${ctx}/_script/function.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>	
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>	
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<link type="text/css" rel="stylesheet" href="${ctx}/uploadify/uploadify.css"/>
	<script type="text/javascript" src="${ctx}/uploadify/jquery.uploadify.min.js"></script>
	<script>
	$(document).ready(function(){
			onHover();
		});
	function chk(){	
		if($("#send_num").val().trim()==""){
			alert("请填写回执单号");
			$("#send_num").focus();
			return false;
		}
		return true;
	}	
	function addFile(){
		$('#file_upload').uploadify('upload','*');
	}
	var code_pattern_addword=/^[0-9a-zA-Z_()（）\ \-\.\u4e00-\u9fa5]{1,100}$/;
	function checkFileName(value){
		if(!code_pattern_addword.test(value)){
			return false;
		}
		return true;
	}
	var errorAlert = false;
	function initUpload() {
	 $(function() {
	 	var job_code = $("#job_code").val();
      	$("#file_upload").uploadify({
	       	'auto' : false,
	       	'method' : "get",
	       	'height' : 30,
			'swf' : '${ctx}/uploadify/uploadify.swf', // flash
			'uploader' : '${ctx}/confirmDestroy', // 数据处理url
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
				//$("#submitaction").submit();
				$("#btn_upload").attr("disabled",true);
				//window.location = "${ctx}/ledger/confirmdestroypaper.action?job_code="+escape(job_code);
				$("#submitaction").submit();
			},
			'onUploadStart' : function(file) {
				errorAlert = false;
				$("#btn_upload").attr("disabled",true);
				$("#file_upload").uploadify("settings",'formData',{'job_code':job_code});
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
			
				//$("#file_upload").uploadify("settings",'formData',{'event_code':'${event_code}'});
			}
	      });
	  });
	  $("#file_upload").show();
      $("#init_upload").hide();
  };
	function onCancel(){
		window.close();
	}
	function deleteFile(filename){
		if(confirm("确定要删除文件：" + filename + "？")){
			$("#delfilepath").val(filename);
			$("#submitaction").submit();
		}
	}
	function downloadFile(file_path,file_name){
		document.getElementById("file_path").value=file_path+"/"+file_name;
		document.getElementById("file_name").value=file_name;
		document.getElementById("uploadForm").submit();
	}
	function getFrameReturn(){
	}
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<form action="${ctx}/burn/downloadfile.action?type='Y'" target="hidden_frame" id="uploadForm" method="post">
	<input type="hidden" name="file_path" id="file_path"/>
	<input type="hidden" name="file_name" id="file_name"/>
</form>
<iframe height="0" width="0" frameborder="0" id="hidden_frame" name="hidden_frame" onload="getFrameReturn();"></iframe>
<form method="post" action="${ctx}/ledger/uploaddestroyfiles.action" id="submitaction">
<input type="hidden" value = "<%=request.getParameter("job_code")%>" id="job_code" name = "job_code"/>
<input type="hidden"  id="delfilepath" name = "delfilepath"/>
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box" style="margin-top:10px">
	<tr>
		<td class="title_box" colspan="2">&nbsp;</td>
	</tr>
    <tr>
    	<td align="center">上传回执单文件：</td> 
	  	<td align="center" id="uploadfile_tr">
	  	     <input type="button" class="button_2003" id="init_upload" value="点击加载上传控件" onclick="initUpload();"/>
		     <input type="file" name="file_upload" id="file_upload" disabled="disabled" style="display:none"/>
		     <input type="button" onclick="addFile();" value="上传" disabled="disabled" class="button_2003" id="btn_upload"/>
		    <!--  <input type="button" onclick="$('#file_upload').uploadify('stop')" value="取消" class="button_2003"/>&nbsp; -->
		</td>
		</tr>
		<tr><td colspan="2"><div id="uploader_queue"></div></td></tr>	 	

	<tr>	
  		<td align="center">&nbsp;已上传销毁回执单列表：</td>
  		<td colspan="3">
  			<table width="90%" border="1" cellspacing="0" cellpadding="0" align="left">
			<s:iterator value="#request.fileNameList" var="fileName">
	  			<tr>
					<td align="center"><u><label style="color: blue;cursor: pointer;" onclick="downloadFile('${storePath}','${fileName}');">${fileName}</label></u></td>
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
