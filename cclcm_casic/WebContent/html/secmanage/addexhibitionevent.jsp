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
 	<script language="javascript" src="${ctx}/_script/casic304_common_bm.js"></script>
<script>
$(document).ready(function(){
		onHover();
		setSeclv("seclv_code");
		selectNextApprover();
	});
//根据密级设置流程
function selectNextApprover(){
	
	del_all_True('next_approver_sel');
	var url = "${ctx}/basic/getnextapprover.action";
	$("#jobType").val("EXHIBITION");//设置状态
//	$("#seclv_code").val("9");//密级:非密
	callServerPostForm1(url,document.forms[1]);
}
function getAjaxResult1(){
	if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
		if(xmlHttp.responseText.indexOf("<SELECT") != -1){
			$("#tr_approver").show();
			document.getElementById("allApprovers").innerHTML=xmlHttp.responseText;
			$("#submit_btn").attr("disabled",false);
		}else{
			$("#tr_approver").hide();
			alert(xmlHttp.responseText);
			$("#submit_btn").attr("disabled",true);
		}
		if($("#next_approver_all").size() > 0 && $("#next_approver_all").children().size() == 0){
			$("#tr_approver").hide();
		}
	}
}

//检测必填项
function chk()
{
		if($("#contact_num").val().trim() == ""){
			alert("请填联系电话");
			$("#contact_num").focus();
			return false;
		}
		
		if($("#exh_location").val().trim() == ""){
			alert("展示/展览地点");
			$("#exh_location").focus();
			return false;
		}
		
		if($(":checkbox:checked[value!='']").size()==0){
			alert("请勾选展示/展览类型");
			return false;
		}
		
		if($("#exh_reason").val().trim() == ""){
			alert("请输入申请原因");
			$("#exh_reason").focus();
			return false;
		}
		
		if($("#next_approver_all option").size() > 0 && $("#next_approver_sel option").size() == 0){
			alert("请选择审批人员");
			$("#next_approver_all").focus();
			return false;
		}
		//审批人信息
	var next_approver = "";
	
	$("#next_approver_sel option").each(function(){
		next_approver += this.value+",";
	});
	
	var len = next_approver.length;
	if (next_approver.lastIndexOf(",") == len - 1){
		next_approver = next_approver.substring(0, len - 1);
	}
	$("#next_approver").val(next_approver);

	return true;
}

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
				callServerPostForm("${ctx}/secmanage/deluploadedfile.action",form);
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
		
var errorAlert = false;
  function initUpload() {
// 	$(function() {
      $("#file_upload").uploadify({
       	'auto' : false,
       	'method' : "get",
       	'height' : 30,
		'swf' : '${ctx}/uploadify/uploadify.swf', // flash
		'uploader' : '${ctx}/uploadsecmanagefile', // 数据处理url
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
  };
  function upload(){
	  initUpload();
	  addFile();
  }

</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<form action="${ctx}/secmanage/deluploadedfile.action" method="POST" id="hiddenDelFileForm">
  	<input type="hidden" name="event_code" value="${event_code}"/>
  	<input type="hidden" name="file_name" id="del_file_name"/>
  	<input type="hidden" name="file_type" id="file_type"/>
  	<input type="hidden" name="type" id="type"/>
</form>
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">	
	<form action="${ctx}/secmanage/addexhibitionevent.action" method="POST">
		<input type="hidden" id="event_code" name="event_code" value="${event_code}"/>
		<input type="hidden" id="dept_id" name="dept_id" value="${curUser.dept_id}"/>
		<input type="hidden" id="jobType" name="jobType" value="${jobType}"/>
		<input type="hidden" id="next_approver" name="next_approver" />
		<input type="hidden" name="seclv_code" id="seclv_code" />
	<tr>
		<td colspan="6" class="title_box">展示/展览保密审查审批表</td>
	</tr>
	<tr> 
    	<td width="15%" align="center">申请人 </td>
    	<td width="20%"><font color="blue"><b>${curUser.user_name}</b></font></td>
    	<td width="12%" align="center">用户部门 </td>
    	<td width="20%"><font color="blue"><b>${curUser.dept_name}</b></font></td>
    	<td align="center"><font color="red">*</font>联系电话</td>
		<td><input type="text" id="contact_num" name="contact_num"/></td>
	</tr>
	<tr> 
		<td align="center"><font color="red">*</font>展示/展览地点</td>
		<td colspan="5"><input type="text" id="exh_location" name="exh_location" size="80"/></td>	
   	</tr> 
   	<tr> 
		<td align="center"><font color="red">*</font>展示/展览类型</td>
		<td colspan="5">
			&nbsp;
			<input type="checkbox" name="exh_type" value="1"/>产品
			<input type="checkbox" name="exh_type" value="2"/>模型
			<input type="checkbox" name="exh_type" value="3"/>展板
			<input type="checkbox" name="exh_type" value="4"/>宣传画册
			<input type="checkbox" name="exh_type" value="5"/>音像
			<input type="checkbox" name="exh_type" value="6"/>其他
			<input type="text" id="exh_others" name="exh_others"/>
		</td>	
   	</tr> 
   	<tr>
		<td align="center"><font color="red">*</font>申请原因</td>
		<td colspan="5">
			<textarea id="exh_reason" name="exh_reason" rows="3" cols="70"></textarea>
		</td>
	</tr>
  	<tr>
  		<td align="center" width="150"><font color="red">*</font>&nbsp;上传文件</td>
  		<td colspan="5">
  			<table width="60%" border="1" cellspacing="0" cellpadding="0" align="left">
	  			<tr><td colspan="2"><font color="red">注意：单个文件大小不能超过2G。若无法选择文件，请点击<a href="${ctx}/player/p.exe" style="font-style: color:blue;"> 安装Flash Player </a></font></td></tr>
	  			<tr id="uploadfile_tr">
	  				<td align="center" width="40%">
	  					<input type="button" class="button_2003" id="init_upload" value="点击加载上传控件" onclick="initUpload();"/>
			  			<input type="file" name="file_upload" id="file_upload" disabled="disabled" style="display:none"/>
			 		</td>
					<td align="center" width="20%">
						<input type="button" onclick="addFile();" value="上传" class="button_2003"/>&nbsp;	
			  		</td>
			 	</tr>
				<tr><td colspan="2"><div id="uploader_queue" style="width: 750px;height: 30px"></div></td></tr>			 	
		 	</table>
  		</td>
  	</tr>
	 <tr id="tr_approver" style="display: none">
	  		<td align="center" id="selApprover1">选择审批人</td>
	  		<td id="selApprover2" colspan="5">
	  			<table width="300" border="0" cellspacing="0" cellpadding="0" align="left" class="table_box_border_empty">
					<tr>
						<td id="allApprovers">
							<SELECT ondblclick="add_True('next_approver_all','next_approver_sel');" style="WIDTH: 100px" multiple="true" size="8" id="next_approver_all">
								<c:forEach var="item" items="${userList}" varStatus="status">
									<option value="${item.user_iidd}">${item.user_name}</option>
								</c:forEach>
							</SELECT>
						</td>
						<td aling="center" valign="middle">
							<INPUT class="button_2003" onclick="add_MoreTrue('next_approver_all','next_approver_sel');" type="button" value="增加-->" name="btnAddItem" /><br/>
							<br/>
							<INPUT class="button_2003" onclick="del_MoreTrue('next_approver_sel');" type="button" value="删除<--" name="btnDelItem"><br/>
							<br/>
						    <INPUT class="button_2003" onclick="add_all_True('next_approver_all','next_approver_sel');" type="button" value="全部增加" name="btnAddItem" /><br/>
						    <br/>
						    <INPUT class="button_2003" onclick="del_all_True('next_approver_sel');" type="button" value="全部删除" name="btnDelItem"><br/>
						</td>
						<td>
							<SELECT size="8" multiple="true" style="WIDTH: 100px" ondblclick="del_True('next_approver_sel');" id="next_approver_sel">
							</SELECT>
						</td>
					</tr>
				</table>
			</td>
	  	</tr>
	  	<tr>
	        <td colspan="6" align="center" class="bottom_box">
	            <input type="submit" value="添加申请" class="button_2003" onclick="return chk();">&nbsp;
	            <input type="reset" value="重置" class="button_2003">
	        </td>
	    </tr>
  	</table>
</form>
</center>
</body>
</html>