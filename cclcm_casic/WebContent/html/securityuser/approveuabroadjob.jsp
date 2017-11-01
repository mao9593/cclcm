<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <link type="text/css" rel="stylesheet" href="${ctx}/uploadify/uploadify.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/print.css"  media="print"/>
    <script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="JavaScript" src="${ctx}/_script/function.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common_bm.js"></script>
	<script type="text/javascript" src="${ctx}/uploadify/jquery.uploadify.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script language="javascript" src="${ctx}/_script/My97DatePicker/WdatePicker.js"></script>
	<script language="javascript" src="${ctx}/_script/jquery.jqprint-0.3.js"></script>
<script>
$(document).ready(function(){
	prepareBizApproval("${process.step_dept}","${process.step_role}","${process.step_dept_name}","${process.step_role_name}","${ctx}");
	if($("#next_approver_all").children().size() == 0){
		$("#selApprover1").hide();
		$("#selApprover2").hide();
		
	}
	$("#submit_btn").attr("disabled",false);
	if("${history}" == "Y"){
		viewOpinion("");//判断审批到哪一步
	}else{
		viewOpinion("read");//判断审批到哪一步
	}	
	
	$("#page_print").click(function(){
		$(".printContent").jqprint();
	})		
});

function chk(){
    subOpinion();//提交时将审批意见复制给opinion
	var this_step = Number($("#listSize").val()) +1;
	if(this_step == 7){
		var a_value = $("input:radio[name='p_chk']:checked").val();
		if(a_value == null){
			alert("请选择护照是否交回！");
			$("#p_chk").focus();
			return false;
		}
	}else if(this_step == 6 && "${judge}" == ""){
		if($("#abroad_time").val().trim() == ""){
			alert("请填写实际出国时间");
			$("#abroad_time").focus();
			return false;
		}
		if($("#abroad_back").val().trim() == ""){
			alert("请填写实际回国时间");
			$("#abroad_back").focus();
			return false;
		}
		if($("#out_customs").val().trim() == ""){
			alert("请填写出境海关");
			$("#out_customs").focus();
			return false;
		}
		if($("#in_customs").val().trim() == ""){
			alert("请填写入境海关");
			$("#in_customs").focus();
			return false;
		}								
	}    
	if($("#opinion").val().trim() == ""){
		alert("审批意见不能为空");
		$("#opinion").focus();
		return false;
	}
	if($("#next_approver_all option").size() > 0 && $("#next_approver_sel option").size() == 0 && $("#approved")[0].checked){
		alert("请选择审批人员");
		$("#next_approver_all").focus();
		return false;
	}
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



//上传文件
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
			$("#file_upload").uploadify("settings",'formData',{'event_code':'${event.event_code}'});
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
				callServerPostForm1("${ctx}/secmanage/deluploadedfile.action",form);
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
	
	
function downloadFile(file_path,file_name){
	$("#file_name").val(file_name);
	document.getElementById("file_path").value=file_path;
	document.getElementById("uploadForm").submit();
}
function getFrameReturn(){
}
</script>
</head>
<div class="printContent">
<body oncontextmenu="self.event.returnValue=false">
<form action="${ctx}/secmanage/deluploadedfile.action" method="POST" id="hiddenDelFileForm">
  	<input type="hidden" name="event_code" value="${event.event_code}"/>
  	<input type="hidden" name="file_name" id="del_file_name"/>
  	<input type="hidden" name="file_type" id="file_type"/>
  	<input type="hidden" name="type" id="type"/>
</form>
<form action="${ctx}/burn/downloadfile.action" target="hidden_frame" id="uploadForm" method="post">
	<input type="hidden" name="file_path" id="file_path"/>
	<input type="hidden" name="file_name" id="file_name"/>
</form>
<iframe height="0" width="0" frameborder="0" id="hidden_frame" name="hidden_frame" onload="getFrameReturn();"></iframe>
<form action="${ctx}/securityuser/approveuabroadjob.action" method="post" >
	<input type="hidden" id="listSize" name="listSize" value="${listSize}"/>
	<input type="hidden" name="event_code" id="event_code" value="${event.event_code}"/> 
	<input type="hidden" id="opinion_all" name="opinion_all" value="${opinion_all}"/> 
	<input type="hidden" name="next_approver" id="next_approver"/>
	<input type="hidden" name="job_code" value="${job.job_code}"/>
	<input type="hidden" id="opinion" name="opinion"/> 
	<input type="hidden" id="types" name="types" value="${types}"/>
	<input type="hidden" id="judge" name="judge" value="${judge}"/>
	
<table width="90%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
	<tr>
	    <td colspan="6" align="center" class="title_box">涉密人员因私出国/境审批表</td>
	</tr>
	<tr> 
    	<td width="14%" align="center">申请用户 </td>
    	<td width="15%"><font color="blue"><b>${job.user_name}</b></font></td>
    	<td width="14%" align="center">用户部门 </td>
    	<td width="16%"><font color="blue"><b>${job.dept_name}</b></font></td>
    	<td width="14%" align="center">申请状态 </td>
    	<td width="16%"><font color="red"><b>&nbsp;${event.job_status_name}&nbsp;/&nbsp;${event.abroad_status_name}</b></font></td> 
	</tr>
	<tr>
		<td align="center">业务类型</td>
    	<td ><font color="blue"><b>${job.jobType.jobTypeName}</b></font></td>
    	<td align="center">申请时间 </td>
    	<td colspan="3"><font color="blue"><b>&nbsp;${event.apply_time_str}</b></font></td>
	 </tr>  	
	<tr>
    	<td align="center">姓名 </td>
    	<td><font color="blue"><b>&nbsp;${event.user_name}</b></font></td>
		<td align="center">联系电话 </td>
    	<td><font color="blue"><b>&nbsp;${applyinfo.com_telephone}</b></font></td>	    	
    	<td align="center">政治面貌 </td>
    	<td><font color="blue"><b>&nbsp;${applyinfo.base_politics_name}</b></font></td>	
     </tr>
	</tr>
	<tr>
    	<td align="center">身份证号 </td>
    	<td><font color="blue"><b>&nbsp;${applyinfo.idcard}</b></font></td>
		<td align="center">出生日期 </td>
    	<td><font color="blue"><b>&nbsp;${applyinfo.base_birthday}</b></font></td>
    	<td align="center">人员状态 </td>
    	<td><font color="blue"><b>&nbsp;${applyinfo.user_statue_name}</b></font></td>
	</tr>
<c:if test="${applyinfo.user_statue == 3}">
	<tr>
		<td align="center">脱密期时间</td>
		<td colspan="6"><font color="red"><b>&nbsp;${revent.start_time}&nbsp;至&nbsp;${revent.end_time}&nbsp;</b></font></td>
	</tr>
</c:if>
	<tr>
	    <td align="center">人员涉密等级 </td>
    	<td><font color="blue"><b>&nbsp;${applyuser.security_name}</b></font></td>  
    	<td align="center">职务 </td>
    	<td><font color="blue"><b>&nbsp;${applyinfo.job_techpost}</b></font></td>
    	<td align="center">职称 </td>
    	<td><font color="blue"><b>&nbsp;${applyinfo.job_techtitle}</b></font></td>
    </tr>	
     <tr>
     	<td align="center">&nbsp;婚姻状况 </td>
	    <td><font color="blue"><b>&nbsp;${applyinfo.marital_status_str}</b></font></td>
	    <td align="center">文化程度 </td>
    	<td><font color="blue"><b>&nbsp;${applyinfo.edu_education_name}</b></font></td>		
        <td align="center">他国绿卡或永久居留证</td>
		<td><font color="blue"><b>&nbsp;${applyinfo.resident_card}</b></font></td>
      </tr>
	<c:choose>
		<c:when test="${empty userpassport}">
		<tr>
			<td align="center">护照信息</td>
			<td colspan="5"><font color="blue"><b>无护照信息，或不在档</b></font></td>
		</tr>
		</c:when>
	<c:otherwise>
		<tr>
			<td align="center">护照信息</td>
			<td colspan="5">
				<font color="blue"><b>&nbsp;
		    		<table border rules=all width="100%" border="0" cellspacing="0" cellpadding="0" align="left"  class="table_box_bottom_border">
		    		<tr>
						<td align="center" width="20%"><font color="blue"><b>护照类型</b></font></td>
						<td align="center" width="10%"><font color="blue"><b>护照状态</b></font></td>
						<td align="center" width="10%"><font color="blue"><b>签发机关</b></font></td>
						<td align="center" width="13%"><font color="blue"><b>签发时间</b></font></td>
						<td align="center" width="13%"><font color="blue"><b>过期时间</b></font></td>
						<td align="center" width="20%"><font color="blue"><b>备注</b></font></td>
					</tr>		
		  			<s:iterator value="#request.userpassport" var="itemmm">
		  				<tr>
		  					<td align="center"><font color="blue"><b>&nbsp;${itemmm.passport_type_name}</b></font></td>
			  				<td align="center"><font color="red"><b>&nbsp;${itemmm.passport_status_name}</b></font></td>
			  				<td align="center"><font color="blue"><b>&nbsp;${itemmm.issuing_authority}</b></font></td>
			  				<td align="center"><font color="blue"><b>&nbsp;${itemmm.issuing_date}</b></font></td>	
			  				<td align="center"><font color="blue"><b>&nbsp;${itemmm.ending_date}</b></font></td>
			  				<td align="center"><font color="blue"><b>&nbsp;${itemmm.summ}</b></font></td>
		  				</tr>
					</s:iterator>
		  			</table>
				</b></font>
			</td>
		</tr>
	</c:otherwise>
	</c:choose>      
	<tr>
	   	<td align="center">计划外出时间</td>
	   	<td><font color="blue"><b>&nbsp;${event.go_time_str}</b></font></td>	
	   	<td align="center">计划返回时间</td>
	   	<td><font color="blue"><b>&nbsp;${event.back_time_str}</b></font></td>
	   	<td align="center">家庭住址 </td>
    	<td><font color="blue"><b>&nbsp;${applyinfo.com_address}</b></font></td>	  	
	</tr>
	<tr>
		<td align="center">目的地或途径国家</td>
	   	<td colspan="5"><font color="blue"><b>&nbsp;${event.termini}</b></font></td>			 	
	</tr>	
	<tr>				
	   	<td align="center">历史出境情况</td>
	   	<td colspan="5"><font color="blue"><b>&nbsp;${event.journey}</b></font></td>    	
  	</tr>			
  	<tr>
	   	<td align="center">请假情况</td>
	   	<td colspan="5"><font color="blue"><b>&nbsp;${event.leave_name}</b></font></td>
	</tr>	
	<tr>
	   	<td align="center">申请事由</td>
	   	<td colspan="5"><font color="blue"><b>&nbsp;${event.reason}</b></font></td>
	</tr>
	<tr>
  		<td align="center">&nbsp;个人承诺</td>
  		<td colspan="5">
  		  <table width="95%" border="0" cellspacing="0" cellpadding="0" align="left">
  		   <tr>
  		       <td colspan="5"><font color="blue"><b>&nbsp;${event.summ}</b></font></td>
  		   </tr>
  		   <tr>
  		   <td align="center"> 申请人签名</td><td><font color="blue"><b>&nbsp;${event.signname}</td>
		       <td align="center"> 时&nbsp;&nbsp;间</td><td><font color="blue"><b>&nbsp;${event.sign_time}</td>
  		   </tr>
  		   </table>	
  		 </td>
	</tr>
	<c:if test="${history eq 'Y'}">
		<tr>
	  		<td align="center">下一步骤审批人</td>
	  		<td colspan="5"><font color="blue"><b>&nbsp;${job.next_approver_name}</b></font></td>
	  	</tr>
  	</c:if>
	<tr>
			<td align="center">申请部门领导意见</td>
			<td colspan="5" id="step1">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion1" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden1"></td></tr>
				</table>
			</td>
	  </tr>
	  <tr>
			<td align="center">人力资源部意见</td>
			<td colspan="5" id="step2">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion2" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden2"></td></tr>
			</table>
			</td>
	   </tr>
	   <tr>
			<td align="center">保密处意见</td>
			<td colspan="5" id="step3">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion3" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden3"></td></tr>
				</table>
		</tr>
		<tr>
			<td align="center">保密委意见</td>
			<td colspan="5" id="step4">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion4" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden4"></td></tr>
				</table>
		</tr>
		<tr>
			<td align="center">人力资源部保密教育提醒</td>
			<td colspan="5" id="step5">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<c:if test="${history != 'Y'}">
						<tr><td><font color="red">*</font>
							护照类型
							<input type="checkbox" id="passorttype" name="passorttype" value="0" />普通护照
							<input type="checkbox" id="passorttype" name="passorttype" value="1" />外交护照
							<input type="checkbox" id="passorttype" name="passorttype" value="2" />公务护照
							<input type="checkbox" id="passorttype" name="passorttype" value="3" />港澳通行证
							<input type="checkbox" id="passorttype" name="passorttype" value="4" />大陆居民来往台湾地区通行证
						</td></tr>
					</c:if>
					<tr><td><textarea id="opinion5" rows="4" cols="100" readonly="readonly">提醒人</textarea></td></tr>
					<tr><td id="hidden5"></td></tr>
				</table>
		</tr>
		<tr>
			<td align="center">申请人回国后确认</td>
			<td colspan="5" id="step6">
			<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
				<tr><td id="text6"><textarea id="opinion6" rows="4" cols="100" readonly="readonly"></textarea></td></tr>				
				<c:if test="${history != 'Y' && empty judge}">
				<tr>
					<td id="addfile6" style="display:none">
  						<table width="90%" border="1" cellspacing="0" cellpadding="0" align="left">
							<tr>
									<font color="red">*</font>&nbsp;出国时间
									<input type="text" id="abroad_time" name="abroad_time" onclick="WdatePicker()" class="Wdate" size="15"/>
									<font color="red">*</font>&nbsp;回国时间
									<input type="text" id="abroad_back" name="abroad_back" onclick="WdatePicker()" class="Wdate" size="15"/></td>
									<font color="red">*</font>&nbsp;出境海关
									<input type="text" id="out_customs" name="out_customs" size="15"/>
									<font color="red">*</font>&nbsp;入境海关
									<input type="text" id="in_customs" name="in_customs" size="15"/>
							</tr>  						
  							<tr>
								<td align="center" colspan="2"><font color="red">*</font>请先点击&nbsp;
											<a href="${ctx}/html/securityuser/因私出境在外情况汇报表.doc"  style="font-style: color:blue;"><font size="2">下载在外情况模板</font></a> 再上传在外情况汇报表
						  		</td>
							</tr>
	  						<tr><td colspan="2"><font color="red">注意：单个文件大小不能超过2G。若无法选择文件，请点击<a href="${ctx}/player/p.exe" style="font-style: color:blue;"> 安装Flash Player </a>)</font></td></tr>
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
	  			</c:if>
	  			<tr>
	  				<td id="showfile6" style="display:none">
			  			 <table width="90%" border="0" cellspacing="0" cellpadding="0" align="left" >
							<tr><td align="center">文件名</td></tr>		
				  			<s:iterator value="#request.approveFileList" var="approveFile">
				  				<tr>
				  					<td align="center"><u><label style="color: blue;cursor: pointer;" onclick="downloadFile('${approveFile.file_path}','${approveFile.file_name}');">${approveFile.file_name}</label></u></td>	  		
				  				</tr>
							</s:iterator>
			  			</table> 
			  		</td>
  				</tr>
  				<tr><td id="hidden6"></td></tr>
  				<tr><td id="fileflag6" style="display:none"></td></tr>
			</table>					
		</tr>
		<tr>
			<td align="center">人力资源部确认</td>
			<td colspan="5" id="step7">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<c:if test="${history != 'Y'}">
					<tr>
						<td align="left"><font color="red">*</font>
						<input type="radio" id="p_chk" name="p_chk" value="yes" />护照已交回
						<input type="radio" id="p_chk" name="p_chk" value="no" />护照未交回
						<input type="radio" id="p_chk" name="p_chk" value="other" />接收新护照
						</td>
			        </tr>
			        </c:if>
					<tr><td><textarea id="opinion7" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
			        <tr><td id="hidden7"></td></tr>
				</table>
			</td>
		  </tr>
		  <tr>
			<td align="center">保密处确认出境汇报情况</td>
			<td colspan="5" id="step8">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion8" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden8"></td></tr>
				</table>		
		  </tr>	       
	<c:if test="${history != 'Y'}">
	<tr>
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
						<INPUT class="button_2003" onclick="add_MoreTrue('next_approver_all','next_approver_sel');" type="button" value="增加-->" /><br/>
						<br/>
						<INPUT class="button_2003" onclick="del_MoreTrue('next_approver_sel');" type="button" value="删除<--"><br/>
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
  	</c:if>
  	<tr class="Noprint">
	    <td colspan="6" align="center">
	    	<c:if test="${history != 'Y'}">
	     	<input class="button_2003" type="submit" value="提交" onclick="return chk();" id="submit_btn" disabled="disabled">&nbsp;
	     	</c:if>
			<input class="button_2003" type="button" value="返回" onClick="javascript:history.go(-1);">&nbsp;
			<c:if test="${history == 'Y'}">
				<input type="button" class="button_2003" value="打印" id="page_print" />
			</c:if>
	    </td>
  	</tr>
  	</form>
</table>
</body>
</div>
</html>
