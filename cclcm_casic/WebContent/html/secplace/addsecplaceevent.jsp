<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>新增要害部门部位审批表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
	<link type="text/css" rel="stylesheet" href="${ctx}/uploadify/uploadify.css"/>
    <link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
    <script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script type="text/javascript" src="${ctx}/uploadify/jquery.uploadify.min.js"></script>
	<script  language="JavaScript" >
	$(document).ready(function(){
		onHover();
		/* preCalendarDay(); */
	});
//根据密级选择审批人
function selectNextApprover(){
	del_all_True('next_approver_sel');
	var url = "${ctx}/basic/getnextapprover.action";
	$("#jobType").val("EVENT_SECPLACE");//设置状态
	$("#hid_seclv_code").val($("#seclv_med").val());//密级
	$("tr").filter(".files_info").each(function(){
 		var selectVal = $(this).children("td").eq(1).find("select").val();
	});
	callServerPostForm1(url,document.forms[1]);
}
function selectSeclv(seclv){
	if(seclv == ""){
		alert("请选择作业密级,以确认审批流程");
		$("#seclv_med").focus();
		return false;
	}else {	
		selectNextApprover();	
	}
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
//end
//模糊匹配人员
	function selectRecvUser(word){
		var url = "${ctx}/basic/getfuzzyuser.action?source=CR";
		if(word != ""){
			callServer1(url,"fuzzy="+word);
		}else{
			document.getElementById("allOptions").innerHTML="";
		}
	}
	function updateResult(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
				if(xmlHttp.responseText.toString().length > 154){
					document.getElementById("allOptions").innerHTML=xmlHttp.responseText;
				}else{
					document.getElementById("allOptions").innerHTML="";
				}
			}else{
				document.getElementById("allOptions").innerHTML="";
			}
	}
	function add_TrueCR(){
		var user_id=$("#allOptionCR").val();
		var user_name=$("#allOptionCR option[value='"+user_id+"']").text();
		if(user_id != ""){
			$("#duty_user_id").val(user_id);
			$("#duty_user_name").val(user_name);
			document.getElementById("allOptions").innerHTML="";
		}
	}
//end
//时间插件
/* function preCalendarDay(){
		Calendar.setup({inputField: "found_time", button: "found_time_trigger", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d %H:%M:%S", cache: true, weekNumbers:true, showOthers: true, step: 1});
} */
//end

//Ajax上传文件
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
				callServerPostForm("${ctx}/secplace/deluploadedfile.action",form);
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

var errorAlert = false;
function initUpload() {
      $("#file_upload").uploadify({
       	'auto' : false,
       	'method' : "get",
       	'height' : 30,
		'swf' : '${ctx}/uploadify/uploadify.swf', // flash
		'uploader' : '${ctx}/uploadsecplacefile', // 数据处理url
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
			$("#file_upload").uploadify("settings",'formData',{'event_code':'${event_code}','seclv_code':$("#seclv_file").val()});
		},
		'onSelect' : function(file){
			
		}
      });
      $("#file_upload").show();
      $("#init_upload").hide();
}
 
function upload(){
	  initUpload();
	  addFile();
}

function getAjaxResult(){
	if(xmlHttp.readyState == 4 && xmlHttp.status == 200){
		alert(xmlHttp.responseText);
		if(xmlHttp.responseText == "删除成功"){
			$("#pendingDelete").remove();
		}
	}
}
//end
	function chk()
	{
		if($("#secplace_name").val().trim() == ""){
			alert("请填写要害部门部位名称");
			$("#secplace_name").focus();
			return false;
		}
		if($("#secplace_location").val().trim() == ""){
			alert("请填写物理位置");
			$("#secplace_location").focus();
			return false;
		}	
		
		if($("#secplace_type").val().trim() == ""){
			alert("请输选择要害类别");
			$("#secplace_type").focus();
			return false;
		}
		
		if($("#seclv_med").val().trim() == ""){
			alert("请选择密级");
			$("#seclv_med").focus();
			return false;
		}
		if($("#duty_user_id").val().trim() == ""){
			alert("请输入责任人");
			$("#duty_user_name").focus();
			return false;
		}
		if($("#duty_dept_id").val().trim() == ""){
			alert("请输入责任部门");
			$("#duty_dept_name").focus();
			return false;
		}
		if($("#secplace_application").val().trim() == ""){
			alert("请输入用途");
			$("#secplace_application").focus();
			return false;
		}
		if($("#people_protect").val().trim() == ""){
			alert("请输入人防措施");
			$("#people_protect").focus();
			return false;
		}
		if($("#physical_protect").val().trim() == ""){
			alert("请输入物防措施");
			$("#physical_protect").focus();
			return false;
		}
		if($("#technology_protect").val().trim() == ""){
			alert("请输入技防措施");
			$("#technology_protect").focus();
			return false;
		}
		if(!checkCode_addword($("#secplace_name").val())){
			$("#secplace_name").focus();
			alert("名称只能由数字、字母、汉字或下划线组成，长度小于30位");
			return false;
		}
		if($("#secplace_code").val().trim() == ""){
			alert("请输入要害部门部位编号");
			$("#secplace_code").focus();
			return false;
		}
		if($("#conf_code").val().trim() == ""){
			alert("请输入保密编号");
			$("#conf_code").focus();
			return false;
		}
		if($("#found_time").val().trim() == ""){
			alert("请输成立时间");
			$("#found_time").focus();
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
		//end
	    return true;
	}
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<form action="${ctx}/secplace/deluploadedfile.action" method="POST" id="hiddenDelFileForm">
  	<input type="hidden" name="event_code" value="${event_code}"/>
  	<input type="hidden" name="file_name" id="del_file_name"/>
  	<input type="hidden" name="file_type" id="file_type"/>
</form>
<form method="post" action="${ctx}/secplace/addsecplaceevent.action">
	<input type="hidden" name="event_code" value="${event_code}" id="event_code"/>
	<input type="hidden" name="jobType" id="jobType"/>
	<input type="hidden" name="seclv_code" id="hid_seclv_code"/>
	<input type="hidden" name="next_approver" id="next_approver"/>
	<input type="hidden" name="dept_id" id="dept_id" value="${curUser.dept_id}"/>
	<input type="hidden" name="usage_code" id="usage_code" value="default"/>
  	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="90%">
	 <tr>
		 <td colspan="6" class="title_box"> 
            	新增要害部门部位审批表
        </td>
    </tr>
    <input type="hidden" name="user_iidd" id="user_iidd" value="${curUser.user_iidd}"/>
    <tr>
    	<td align="center"><font color="red">*</font>要害部门部位名称</td>
		<td>
			<input type="text" name="secplace_name" id="secplace_name" value="${secplace_name}"/>
		</td>
		<td align="center"><font color="red">*</font>要害部门部位编号</td>
		<td>
			<input type="text" name="secplace_code" id="secplace_code" value="${secplace_code}"/>
		</td>
		<td align="center"><font color="red">*</font>物理位置</td>
		<td>
			<input type="text" name="secplace_location" id="secplace_location" value="${secplace_location}"/>
		</td>
		
    </tr>  
    <tr>
    	<td align="center"><font color="red">*</font>要害类别</td>
		<td>
			<select name="secplace_type" id="secplace_type">
				<option value="">--请选择--</option>
				<option value="1" <c:if test="${secplace_type == '1'}">selected</c:if>>部门</option>
				<option value="2" <c:if test="${secplace_type == '2'}">selected</c:if>>部位</option>
			</select>
		</td>
		<td align="center"><font color="red">*</font>要害密级</td>
		<td>
			<select id="seclv_med" onchange="selectSeclv(this.value);">
				<option value="">--请选择--</option>
				<s:iterator value="#request.seclvList" var="seclv">
					<option value="${seclv.seclv_code}" <c:if test="${seclv_code == seclv.seclv_code}">selected</c:if>>${seclv.seclv_name}</option>
				</s:iterator>
			</select>
		</td>
		<%-- <td align="center"><font color="red">*</font>保密编号</td>
		<td>
			<input type="text" name="conf_code" id="conf_code" value="${conf_code}"/>
		</td> --%>
    </tr> 
    <tr>
		<td align="center"><font color="red">*</font>责任部门</td>  <!-- 选定责任人后自动出现责任部门，无需手动填写-->
		<td>
			<input type="text" id="duty_dept_name" name="duty_dept_name" value="${duty_dept_name}" readonly="readonly" style="cursor:hand" onclick="openDeptSelect('duty_dept_name','duty_dept_id','radio')"/>
    		<input type="hidden" id="duty_dept_id" name="duty_dept_id" value="${duty_dept_id}"/><br>
		</td>
		<td align="center"><font color="red">*</font>责任人</td>
		<td>
			<input type="text" id="duty_user_name" name="duty_user_name" value="${duty_user_name}" onkeyup="selectRecvUser(this.value);"/>
    		<input type="hidden" id="duty_user_id" name="duty_user_id" value="${duty_user_id}"/><br>
    		<div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
		</td>
	</tr> 
	<tr>
		<%-- <td align="center"><font color="red">*</font>预计成立时间</td>
		<td >
			<input type="text" id="found_time" name="found_time" readonly="readonly" style="cursor:hand;" value="${found_time}"/>
       	 	<img src="${ctx}/_image/time2.jpg" id="found_time_trigger">
		</td>
		<td align="center">&nbsp; </td>
		<td align="center"> &nbsp;</td>
		<td align="center"> &nbsp;</td>
		<td align="center"> &nbsp;</td> --%>
		<td align="center">重要涉密人员数量</td>
		<td>
			<input type="text" name="user_number1" id="user_number1" value="${user_number1}"/>
		</td>
		<td align="center">一般涉密人员数量</td>
		<td>
			<input type="text" name="user_number2" id="user_number2" value="${user_number2}"/>
		</td>
		<td align="center">涉密人员总数</td>
		<td>
			<input type="text" name="user_number_sum" id="user_number_sum" value="${user_number_sum}"/>
		</td>
	</tr>					
    <tr>
    	<td align="center"><font color="red">*</font>确定理由及依据</td>
    	<td colspan="5">
			<textarea name="secplace_application" rows="3" cols="50" id="secplace_application" value="${secplace_application}"></textarea>
		</td>
    </tr>
     <tr>
    	<td align="center"><font color="red">*</font>人防措施</td>
		<td colspan="5">
			<textarea name="people_protect" rows="3" cols="50" id="people_protect" value="${people_protect}"></textarea>
		</td>
    </tr> 	
     <tr>
    	<td align="center"><font color="red">*</font>物防措施</td>
		<td colspan="5">
			<textarea name="physical_protect" rows="3" cols="50" id="physical_protect" value="${physical_protect}"></textarea>
		</td>
    </tr> 	
     <tr>
    	<td align="center"><font color="red">*</font>技防措施</td>
		<td colspan="5">
			<textarea name="technology_protect" rows="3" cols="50" id="technology_protect" value="${technology_protect}"></textarea>
		</td>
    </tr> 	
     <tr>
    	<td align="center">备注</td>
		<td colspan="5">
			<textarea name="summ" rows="3" cols="50" id="summ" value="${summ}"></textarea>
		</td>
    </tr> 	
    <tr>
  		<td align="center">附件列表</td>
  		<td colspan="5">
  			<table width="90%" border="1" cellspacing="0" cellpadding="0" align="left">
  				<tr>
  					<td colspan="3">
  						<font color="red">注意：单个文件大小不能超过2G。文件名称字数总和不要超过500，若文件过多，请打包上传。若无法选择文件，请点击<a href="${ctx}/player/p.exe" style="font-style: color:blue;"> 安装Flash Player </a></font>
  					</td>
  				</tr>
  				<tr id="uploadfile_tr">
  					<td align="center" width="250" >
  						<input type="button" class="button_2003" id="init_upload" value="点击加载上传控件" onclick="initUpload();"/>
		  				<input type="file" name="file_upload" id="file_upload" disabled="disabled" style="display:none"/>
		 		</td>
				<td align="center" width="250">
					<input type="button" onclick="addFile();" value="上传" class="button_2003"/>&nbsp;
		  		</td>
		 	</tr>
		 	<tr>
		 		<td colspan="3">
		 			<div id="uploader_queue" style="width: 750px;height: 30px"></div>
		 		</td>
		 	</tr>
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