<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>上传保密学习资料</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen"/>
	<link type="text/css" rel="stylesheet" href="${ctx}/uploadify/uploadify.css"/>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script type="text/javascript" src="${ctx}/uploadify/jquery.uploadify.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script  language="JavaScript" >
	<!--
	$(document).ready(function(){
		onHover();
	});
	function addFile(){
		$('#file_upload').uploadify('upload','*');
	}
	var code_pattern_addword=/^[0-9a-zA-Z_\.\u4e00-\u9fa5\(\)]{1,100}$/;
	function checkFileName(value){
		if(!code_pattern_addword.test(value)){
			return false;
		}
		
		if($("#file_id").val().trim() == ""){
		alert("文档编号不能为空");
		$("#file_id").focus();
		return false;
		}
		if($("#file_sec_lv").val().trim() == ""){
		alert("密级不能为空");
		$("#file_sec_lv").focus();
		return false;
		}
		if($("#file_edu_hour").val().trim() == ""){
		alert("学时不能为空");
		$("#file_edu_hour").focus();
		return false;
		}
		if($("#file_type").val().trim() == ""){
		alert("文档类型不能为空");
		$("#file_type").focus();
		return false;
		}
				
		
		var pattern = /^[0-9]*$/;
		if(!code_pattern_addword.test($("#file_id").val())){
	    	alert("文档编号中存在不允许的特殊字符");
	    	$("#file_id").focus();
	    	return false;
	    }
	    if(!pattern.test($("#file_edu_hour").val())){
	    	alert("学时必须是数字");
	    	$("#file_edu_hour").focus();
	    	return false;
	    }
		return true;
	}
	var errorAlert = false;
	function initUpload(){
	 $(function() {
      	$("#file_upload").uploadify({
	       	'auto' : false,
	       	'method' : "get",
	       	'height' : 30,
			'swf' : '${ctx}/uploadify/uploadify.swf', // flash
			'uploader' : '${ctx}/uploadlearningfile', // 数据处理url
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
				$("#btn_confirm").attr("disabled",false);
				$("#file_name").val(file.name);
			},
			'onUploadStart' : function(file) {
				errorAlert = false;
				$("label").each(function(i){
					if(this.innerText == file.name){
						alert("同名文件已经上传，请重新选择文件");
						$("#file_upload").uploadify('cancel');
						errorAlert = true;
						return false;
					}
				});
				if(!checkFileName(file.name)){
					alert("文件名称只能由数字、字母、汉字或下划线组成，长度小于100位");
					$("#file_upload").uploadify('cancel');
					errorAlert = true;
					return false;
				}
				$("#btn_upload").attr("disabled",true);
			},
			'onSelect' : function(file){
				var uploadabel = true;
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
			$("#submitFlag").val("");
			$("#submitaction").submit();
		}
	}
	
function panelSwitchClicked() {
    pStateOld = document.getElementById("tdPANEL").style.display == "none" ? "close" : "open";
    switchBar("tdPANEL");
    pState = document.getElementById("tdPANEL").style.display == "none" ? "close" : "open";
    event.srcElement.src = event.srcElement.src.replace(pStateOld, pState);
    //saveCookie();
}

function OnSubmitDown(){
	
	$("#submitFlag").val("add");
	//alert($("#submitFlag").val());
	$("#submitaction").submit();

}


//动态改变图片
function imgOver() {
    var obj = event.srcElement;
    if (obj.src.lastIndexOf("-over") < 0) obj.src = obj.src.substring(0, obj.src.lastIndexOf(".")) + "-over.gif";
}
function imgOut() {
    var obj = event.srcElement;
    if (obj.src.lastIndexOf("-over") > 0) obj.src = obj.src.substring(0, obj.src.lastIndexOf("-")) + ".gif";
}
	
		
     //-->
	</script>
</head>
<body oncontextmenu="returnValue=false">
<center>
<form method="post" action="${ctx}/education/uploadlearningfile.action" id="submitaction">
	<input type="hidden" name="delfilepath" id="delfilepath" value=""/>
	<input type="hidden" name="submitFlag" id="submitFlag" value=""/>
	<input type="hidden" name="file_name" id="file_name" value=""/>
   	
   	<table cellpadding="0" cellspacing="0" border="0" width="100%" height="100%"><tr>
        <!--**左侧导航-->
       <%--  <td id="tdPANEL" width="175" style="display:block" valign="top" bgcolor="#BBBBBB" nowrap="nowrap">
        ${menuPanel}
        </td>
        <!--**左侧导航控制*-->
        <td width="7" align="center" background="../images/_system/portal/switch-panel-bg.gif">
            <img alt="" name="leftseparator" 
                 src="../images/_system/portal/switch-panel-open.gif"
                 onClick="panelSwitchClicked();" onMouseOver="imgOver();" onMouseOut="imgOut();"/>
        </td> --%>
        <!--**主页面框架-->
        <td width="100%" height="100%">
          <!--   <iframe src="about:blank" id="mainFrame" name="mainFrame" height="100%" width="100%" frameborder="0" 
                    style="z-index:2;position:relative;"></iframe>
           -->
           
             
            <table class="table_box" cellspacing=0 cellpadding=0 border=1 width="100%" height="100%">
				 <tr>
					 <td colspan="6" class="title_box">
			            	保密学习资料管理
			        </td>
			    </tr>
			    <tr>
			  		<td align="center" width="150" height="20%"><font color="red">*</font>&nbsp;上传文件</td>
			  		<td>
			  		<table class="table_box" width="95%" border="1" cellspacing="0" cellpadding="0" align="left">
				  		<tr>
				  			<td align="center" width="20%"><font color="red">*</font>&nbsp;公文号</td>
			    			<td><input type="text" name="file_id" id="file_id" /></td>
			    			
			    			<td align="center"><font color="red">*</font>&nbsp;密级</td>
			    			<td>
							<select id="file_sec_lv" name="file_sec_lv">
								<option value="">--请选择--</option>
									<s:iterator value="#request.seclvList" var="seclv">
									<option value="${seclv.seclv_name}">${seclv.seclv_name}</option>
								</s:iterator>
							</select>
							</td>
			    		</tr>
			    		
			    		<tr>
				  			<td align="center"><font color="red">*</font>&nbsp;学时要求</td>
			    			<td><input type="text" name="file_edu_hour" id="file_edu_hour" /></td>
			    			
			    			<td align="center"><font color="red">*</font>&nbsp;材料类型</td>
			    			<td>
							<select id="file_type" name="file_type" >
								<option value="">--请选择--</option>
								<option value="政策法规">法律法规</option>
								<option value="管理制度">管理制度</option>
								<option value="学习资料">学习资料</option>
								<option value="图文资料">图文资料</option>
								<option value="警钟长鸣">警钟长鸣</option>	
							</select>
							</td>
			    		</tr>
				  		
				  		<tr>
				  			<td align="center">&nbsp;摘要</td>
			    			<td colspan="3" width="100%"><input size="100" type="text" name="comment" id="comment" value=""/></td>
			    		</tr>
				  		
				  		<tr>
				  			<td align="center"><font color="red">*</font>&nbsp;上传人</td>
			    			<td colspan="3"><input type="text" readonly name="upload_user" id="upload_user" value="${secUser.user_name}"/></td>
			    		</tr>	
				  		<tr id="uploadfile_tr">
				  				<td align="center" width="50%" colspan="2">
				  					<input type="button" class="button_2003" id="init_upload" value="点击加载上传控件" onclick="initUpload();"/>
						  			<input type="file" name="file_upload" id="file_upload" disabled="disabled" style="display:none"/>
						 		</td>
								<td align="center" width="50%" colspan="2">
									<input type="button" onclick="addFile();" value="上传" disabled="disabled" class="button_2003" id="btn_upload"/>&nbsp;&nbsp;
									<input type="button" onclick="OnSubmitDown();" value="确认添加" disabled="disabled" class="button_2003" id="btn_confirm"/>&nbsp;&nbsp;
									<input type="button" onclick="$('#file_upload').uploadify('stop')" value="取消" class="button_2003"/>&nbsp;
						  		</td>
						 </tr>
						<tr><td colspan="4"><div id="uploader_queue" style="width: 750px;height: 30px"></div></td>
						</tr>	
						<tr>
					  		<td colspan="4"><font color='black'>注意：单个文件大小不能超过2G，建议上传PDF格式文件。若无法选择文件，请点击<a href="${ctx}/player/p.exe" style="font-style: color:blue;"> 安装Flash Player </a></font>
					  		</td>
					  	</tr>		 	
					 	</table>
			  		</td>
			  	</tr>
				<tr>	
			         <td align="center">&nbsp;已上传保密教育学习材料列表</td>
			         <td colspan="3" valign="top">  			
			            <table  class="table_box" width="100%" border="1" cellspacing="0" cellpadding="0" align="left">
						    <tr>
						       <td align="center" width="10%">文件号</td>
						       <td align="center" width="30%">文档名称 </td>
						       <td align="center" width="20%">学时要求</td>
						       <td align="center" width="10%">密级</td>
						       <td align="center" width="10%">材料类型</td>
						       <td align="center" width="10%">上传人</td>
						       <td align="center" width="10%">操作</td>
						    </tr>
						   <s:iterator value="#request.uploadEvent" var="uploadfile">
							<tr>
							<td align="center" width="10%">&nbsp; ${uploadfile.file_id}</td>
							<td align="center" width="30%">&nbsp; <label>${uploadfile.file_name}</label></td>
							<td align="center" width="20%">&nbsp; ${uploadfile.file_edu_hour}</td>
							<td align="center" width="10%">&nbsp; ${uploadfile.file_sec_lv}</td>
							<td align="center" width="10%">&nbsp; ${uploadfile.file_type}</td>
							<td align="center" width="10%">&nbsp; ${uploadfile.upload_user}</td>
							<td align="center" width="10%">&nbsp;<a href="#" onclick="deleteFile('${uploadfile.file_name}');">删除</a></td>
							</tr>
							</s:iterator>
						  
						</table>
					</td>	
				</tr>
				
				
		</table>	        
                    
        </td>
    </tr></table>
   	
</form>
</center>
</body>
</html>