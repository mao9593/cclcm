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
 	<script type="text/javascript" src="${ctx}/uploadify/jquery.uploadify.min.js"></script>
<script>
$(document).ready(function(){
	onHover();
	$("#submit_btn").attr("disabled",true);
});

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
		$tr_i.append($td_but);
		return $tr_i;
	}
	function getAjaxResult(){
		if(xmlHttp.readyState == 4 && xmlHttp.status == 200){
			alert(xmlHttp.responseText);
			if(xmlHttp.responseText == "删除成功"){
				$("#pendingDelete").remove();
				$("#file_upload").show();
				$("#addfile").show();
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
			'uploader' : '${ctx}/upLoadSepcial', // 数据处理url
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
				$("#file_upload").hide();
				$("#addfile").hide();
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
	};
	  
	 function upload(){
		initUpload();
		addFile();
	 }
	 
	function chk(){	
 		if($("#file_selv").val() == ""){
			alert("请选择文件密级");
			$("#file_selv").focus();
			return false;
		}
		if($("#paper_num").val().trim() == ""){
			alert("请填写文件份数");
			$("#paper_num").focus();
			return false;
		}
		if(!isInteger($("#paper_num").val())){
		    alert("文件份数只能填写1到10000之间的整数");
		    $("#paper_num").focus();
		    return false;
		} 
		if($("#make_type").val().trim() == ""){
			alert("请选择制作种类");
			$("#make_type").focus();
			return false;
		}
		if($("#print_double").val().trim() == ""){
			alert("请选择单双面");
			$("#print_double").focus();
			return false;
		}
		if($("#paper_color").val().trim() == ""){
			alert("请选择色彩");
			$("#paper_color").focus();
			return false;
		}
		if($("#page_num").val().trim() == ""){
			alert("请填写页数");
			$("#page_num").focus();
			return false;
		}
		if(!isInteger($("#page_num").val())){
		    alert("文件页数只能填写1到10000之间的整数");
		    $("#page_num").focus();
		    return false;
		}
		
	    return true;
	}
</script>
<script>
	$(document).ready(function(){
		onHover();
		addSelectAllCheckbox();
	});
	
	var event_ids = "";
	var submitable = true;
	var event_delids = "";
	function addEnterJob(){
		if($(":checkbox:checked[value!=''][name='event_id']").size()>0){
			$(":checkbox:checked[name='event_id'][value!='']").each(function (){
				event_ids += this.value+":";
			});
			if(submitable){
				$("#event_ids").val(event_ids);
				$("#hid_form").submit();
			}else{
				return false;
			}
		}else{
			alert("请先勾选需要提交的作业");
			return false;
		}
	}
	function deleteEnterJob(){
	if($(":checkbox:checked[value!=''][name='event_id']").size()>0){
			$(":checkbox:checked[value!=''][name='event_id']").each(function (){
				event_delids += this.value+":";
			});
			if(submitable){
				$("#event_delids").val(event_delids);
				$("#deleteform").submit();
			}else{
				return false;
			}
		}else{
			alert("请先勾选需要删除的作业");
			return false;
		}
	}
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<form action="${ctx}/burn/deluploadedfile.action" method="POST" id="hiddenDelFileForm">
	<input type="hidden" name="seclv_code" id="del_sec_code"/> 
  	<input type="hidden" name="event_code" value="${event_code}"/>
  	<input type="hidden" name="file_name" id="del_file_name"/>
  	<input type="hidden" name="file_type" id="file_type"/>
</form>
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">	
	<form action="${ctx}/print/addspecialprintevent.action" method="POST">
		<input type="hidden" name="event_code" value="${event_code}" id="event_code"/>
	<tr>
	    <td colspan="6" class="title_box">添加OA特殊打印申请</td>
	</tr>
	<tr> 
    	<td width="10%" align="center">申请用户： </td>
    	<td width="23%"><font color="blue"><b>${curUser.user_name}</b></font></td>
    	<td width="10%" align="center">用户部门： </td>
    	<td width="23%"><font color="blue"><b>${curUser.dept_name}</b></font></td>
    	<td width="10%" align="center"><font color="red">*</font>文件密级：</td>
	    <td width="23%">
			<select id="file_selv" name="file_selv">
				<option value="">--请选择--</option>
				<s:iterator value="#request.seclvList" var="seclv">
					<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
				</s:iterator>
			</select>
		</td>		
	</tr>
	<tr> 
		<td align="center"><font color="red">*</font>文件份数：</td>
		<td><input type="text" name="paper_num" id="paper_num"/></td>   
		<td align="center"><font color="red">*</font>制作类别：</td>
		<td>
			<select name="make_type" id="make_type">
	   			<option value="">--请选择--</option>
	   			<s:iterator value="#request.typeList" var="item">
					<option value="${item.usage_code}">${item.usage_name}</option>
				</s:iterator>
   			</select>
		</td>
		<td align="center"><font color="red">*</font>单双面：</td>
		<td>
			<select name="print_double" id="print_double">
				<option value="">--请选择--</option>
    			<option value="1">单面</option>
    			<option value="2">双面左右翻</option>
    			<option value="3">双面上下翻</option>   	   			
    		</select>
		</td>
  	</tr>
  	<tr>
  		<td align="center"><font color="red">*</font>纸张类型：</td>
		<td>
			<input type="text" name="paper_kind" id="paper_kind" value="A4">
		</td>
		<td align="center"><font color="red">*</font>色彩：</td>
		<td>
			<select name="paper_color" id="paper_color">
				<option value="">--请选择--</option>
    			<option value="1">黑白</option>
    			<option value="2">彩色</option>   			
    		</select>
		</td>
		<td align="center">横纵向：</td>
		<td>
			<select name="print_direct" id="print_direct">
				<option value="">--请选择--</option>
    			<option value="1">纵向</option>
    			<option value="2">横向</option>   			
    		</select>
		</td>
  	</tr> 
  	<tr>   		   	
  		<td align="center"><font color="red">*</font>文件页数：</td>
		<td><input type="text" name="page_num" id="page_num"/></td>  
  		<td align="center">备注：</td>
		<td colspan="3"><textarea name="summ" rows="3" cols="40" id="summ"></textarea></td>
  	</tr>  	
  	<tr>
  		<td align="center"><font color="red">*</font>&nbsp;上传文件：</td>
  		<td colspan="5">
  			<table width="95%" border="1" cellspacing="0" cellpadding="0" align="left">
	  			<tr><td colspan="2"><font color="red">注意：只允许上传一个文件，大小不能超过2G。若无法选择文件，请点击<a href="${ctx}/player/p.exe" style="font-style: color:blue;"> 安装Flash Player </a></font></td></tr>
	  			<tr id="uploadfile_tr">
	  				<td align="center" width="50%">
	  					<input type="button" class="button_2003" id="init_upload" value="点击加载上传控件" onclick="initUpload();"/>
			  			<input type="file" name="file_upload" id="file_upload" disabled="disabled" style="display:none"/>
			 		</td>
					<td align="center" width="50%">
						<input type="button" onclick="addFile();" value="上传" class="button_2003" id="addfile"/>&nbsp;	
			  		</td>
			 	</tr>
				<tr><td colspan="2"><div id="uploader_queue" style="width: 750px;height: 30px"></div></td></tr>			 	
		 	</table>
  		</td>
  	</tr>  	
	</form>
  	<tr>
	    <td colspan="6" align="center"> 
			<input type="button" class="button_2003" value="添加" onclick="if(chk()) forms[1].submit();" id="submit_btn"/>
		</td>
	</tr>
</table>
<form method="post" action="${ctx}/print/delmultiprintevent.action" id="deleteform">
	<input type="hidden" name="event_delids" id="event_delids"/>	
</form>
<form method="post" action="${ctx}/print/addspecialprocessjob.action" id="hid_form">
	<input type="hidden" name="jobType" value="${jobType}"/>
	<input type="hidden" name="event_ids" id="event_ids"/>	
	<input type="hidden" name="seclv_code" id="seclv_code"/> 
	<input type="hidden" name="user_iidd" value="${curUser.user_iidd}"/>
	<input type="hidden" name="dept_id" value="${curUser.dept_id}"/>
</form>
<table width="95%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_only_border">
	<tr>
		<td class="title_box">已添加的特殊打印作业列表</td>
	</tr>
	<tr>
		<td>
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
					<display:table uid="item" class="displaytable" name="eventList" sort="list">
						<display:column title="选择">
							<input type="checkbox" value="${item.id}" name="event_id"/>
						</display:column>
						<display:column title="序号">
							<c:out value="${item_rowNum}"/>
						</display:column>
						<display:column title="文件名称" property="paper_name" maxLength="20"/>				
						<display:column title="页数" property="page_num"/>
						<display:column title="文件密级" property="file_sname"/>
						<display:column title="制作类别" property="make_type_str"/>
						<display:column title="份数" property="paper_num"/>										
						<display:column title="申请时间" property="apply_time_str"/>
						<display:column title="操作" style="width:100px">
						<div>
							<input type="button" class="button_2003" value="查看" onclick="go('${ctx}/print/viewspecialprinteventdetail.action?event_code=${item.event_code}&op=view');"/>							
						</div>
						</display:column>
					</display:table>
					</td>
				</tr>
			</table>
         </td>
	</tr>
	<tr>
	   <td>
	       <input type="button" value="批量提交" onclick="addEnterJob();" class="button_2003"/>
	       <input type="button" value="批量删除" onclick="deleteEnterJob();" class="button_2003"/>
	  </td>
	</tr>
</table>
</body>
</html>
