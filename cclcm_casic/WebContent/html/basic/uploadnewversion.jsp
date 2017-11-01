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
	function chk(){	
		if($("label").size() == 0){
			alert("请上传版本升级文件");
			return false;
		}
		if($("#type").val() ==""){
			alert("请选择上传类型");
			$("#type").focus();
			return false;
		}
				if($("#type").val()=="CONSOLE"){
		    var value=/^[HDCONSOLE]+_[^ \f\n\r\t\v]+\.[0-9a-zA-Z]+$/;
		   if(!value.test($("#need_upload_file_name").val())){
		   alert("上传的升级文件与类型不匹配,请重新输入!");
			$("#type").focus();
			return false;
		    }
		}
		if($("#type").val()=="CLIENT"){
		    var value1=/^[HDPrintCL]+_[^ \f\n\r\t\v]+\.[0-9a-zA-Z]+$/;
		   if(!value1.test($("#need_upload_file_name").val())){
		   alert("上传的升级文件与类型不匹配,请重新输入!");
			$("#type").focus();
			return false;
		    }
		}
	    return true;
	}
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
			'uploader' : '${ctx}/uploadversion', // 数据处理url
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
				$("#submit_btn").attr("disabled",false);
				return false;
			},
			'onUploadSuccess' : function(file,data,response) {//上传完成时触发（每个文件触发一次）
				alert("上传完成, 文件名：" + file.name );
				$("#btn_upload").attr("disabled",true);
				$("#uploadfile_tr").before(addRowBefore(file.name));
				$("#submit_btn").attr("disabled",false);
			},
			'onUploadStart' : function(file) {
				errorAlert = false;
				var uploadabel = true;
				$("label").each(function(i){
					if(this.innerText == file.name){
						alert("同名文件已经上传，请重新选择文件");
						uploadabel = false;
					}
				});
				if(!uploadabel){
					$("#file_upload").uploadify('cancel');
					return false;
				}
				$("#need_upload_file_name").val(file.name);
				$("#submit_btn").attr("disabled",true);
				$("#file_upload").uploadify("settings",'formData');
			},
			'onSelect' : function(file){
				if($("label").size() > 0){
					$("#file_upload").uploadify('cancel');
					alert("一次最多上传一个文件");
					return false;
				}
				if(checkPass(file)){
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
	  
	function addRowBefore(fileName){
	var $tr_i = $("<tr>");
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
				$("#file_type").val("temp");
				$("#del_file_name").val(fileName);
				$tr_i.attr("id","pendingDelete");
				var form = document.getElementById("hiddenDelFileForm");
				callServerPostForm("${ctx}/basic/deluploadedfile.action",form);
				$("#btn_upload").attr("disabled",false);
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
	function getAjaxResult(){
		if(xmlHttp.readyState == 4 && xmlHttp.status == 200){
			alert(xmlHttp.responseText);
			if(xmlHttp.responseText == "删除成功"){
				$("#pendingDelete").remove();
			}
		}
	} 
	function checkPass(file){
		var pattern=/^[HD|CONSOLE][0-9a-zA-Z]+_[^ \f\n\r\t\v]+\.[0-9a-zA-Z]+$/;
		if(!pattern.test(file.name)){
			alert("文件名格式不正确");
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
<form action="${ctx}/burn/deluploadedfile.action" method="POST" id="hiddenDelFileForm">
  	<input type="hidden" name="file_name" id="del_file_name"/>
  	<input type="hidden" name="file_type" id="file_type"/>
</form>
<form method="post" action="${ctx}/basic/uploadnewversion.action">
	<input type="hidden" name="file_name" id="del_file_name"/>
	<input type="hidden" name="is_upload" value="true"/>
	<input type="hidden" name="need_upload_file_name" id="need_upload_file_name"/>
   	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="90%">
	 <tr>
		 <td colspan="6" class="title_box">
            	上传升级文件
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
					<input type="button" onclick="addFile();" value="上传" disabled="disabled" class="button_2003" id="btn_upload"/>&nbsp;
		  			<input type="button" onclick="$('#file_upload').uploadify('stop')" value="取消" class="button_2003"/>&nbsp;
		  		</td>
		 	</tr>
		 	<tr><td colspan="3"><div id="uploader_queue" style="width: 750px;height: 30px"></div></td></tr>
		 	</table>
  		</td>
  	</tr>
  	<tr>
  		<td align="center"><font color="red">*</font>&nbsp;类型：</td>
	    <td colspan="3" > 
		     <select name="type" id="type">
		     	<option value="">--请选择--</option>
		     	<option value="CLIENT">客户端</option>
		     	<option value="CONSOLE">控制台</option>
		     </select>
	    </td>
	</tr>
  	<tr>
  		<td align="center">&nbsp;备注：</td>
	    <td colspan="3" > 
	      <textarea name="comment" id="comment" cols="80" rows="3" ></textarea>&nbsp;
			
	    </td>
	</tr>
  	<tr>
	    <td colspan="4" align="center"> 
	      <input type="button" class="button_2003" value="提交" onclick="if(chk()) forms[1].submit();" id="submit_btn"/>
	      &nbsp;
	      <input class="button_2003" type="reset" value="返回"  onClick="javascript:history.go(-1);">&nbsp;
	      <font color="red" id="submitPrompt" style="display: none">正在提交处理，请稍等……</font>
	    </td>
	</tr>
  	</table>
</form>
</center>
</body>
</html>