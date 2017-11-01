<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ page import="hdsec.web.project.secplace.model.EntityVisitor"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <link type="text/css" rel="stylesheet" href="${ctx}/uploadify/uploadify.css"/>
    <link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
    <script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
 	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script> 
 	<script language="javascript" src="${ctx}/_script/casic304_common_bm.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
 	<script type="text/javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
 	<script type="text/javascript" src="${ctx}/uploadify/jquery.uploadify.min.js"></script>
 	<script language="javascript" src="${ctx}/_script/My97DatePicker/WdatePicker.js"></script>
<script>
$(document).ready(function(){
	onHover();
	setSeclv("seclv_code");
	selectNextApprover();
});
	
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
		$("#accompany_id").val(user_id);
		$("#accompany_name").val(user_name);
		document.getElementById("allOptions").innerHTML="";
	}
}
	
function selectRecvSecplace(word){
	var url = "${ctx}/secplace/getfuzzysecplace.action";
	if(word != ""){
		callServer3(url,"fuzzy="+word);
	}else{
		document.getElementById("allOptionsSP").innerHTML="";
	}
}

function updateResult1(){
	if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
		if(xmlHttp.responseText.toString().length > 154){	
			document.getElementById("allOptionsSP").innerHTML=xmlHttp.responseText;
		}else{
			document.getElementById("allOptionsSP").innerHTML="";
		}
	}else{
		document.getElementById("allOptionsSP").innerHTML="";
	}
}

function add_TrueSP(){
	var user_id=$("#allOptionSP").val();
	var user_name=$("#allOptionSP option[value='"+user_id+"']").text();
	if(user_id != ""){
		$("#secplace_code").val(user_id);
		$("#secplace_name").val(user_name);
		document.getElementById("allOptionsSP").innerHTML="";
	}
}
function addRow(){
	var table = document.all("visitors");
	
	var row_num = table.rows.length;
	new_tr = table.insertRow();
	new_tr.align="center";	
	new_tb0 = new_tr.insertCell();
	new_tb1 = new_tr.insertCell();
	new_tb2 = new_tr.insertCell();
	new_tb3 = new_tr.insertCell();
	new_tb4 = new_tr.insertCell();
	new_tb5 = new_tr.insertCell();
	new_tb6 = new_tr.insertCell();
	var nums = row_num -1;
	new_tb0.innerHTML='<input type="checkbox" value="'+row_num+'" name="visitor_id"  size="5"/>'+nums; 
	new_tb1.innerHTML='<input type="text" name="visitor_names" size="10"/>'; 
	new_tb2.innerHTML='<input type="text" name="visitor_companys" size="15"/>'; 
	new_tb3.innerHTML='<input type="text" name="certificate_types" size="10"/>'; 
	new_tb4.innerHTML='<input type="text" name="certificate_codes" size="18"/>'; 
	new_tb5.innerHTML='<input type="text" name="visitor_belongingss" size="18"/>'; 
	new_tb6.innerHTML='<input type="text" name="security_arrangements" size="18"/>'; 
	
	$("#visitor_num").val(nums);//密级
}

function delRow(){
	var table = document.all("visitors");
	var sum_num = table.rows.length;
	if(sum_num < 3)
	{
		alert("已没有可删除的行！");
		return false;
	}
	if($(":checkbox:checked").size() == 0){
			alert("请先勾选需要删除的行");
			return false;
		}else if($(":checkbox:checked").size()>1){
			alert("每次只能删除一行！");
		}else{
			$(":checkbox:checked[value!=''][name='visitor_id']").each(function (){
				var row_num = this.value;
				table.deleteRow(this.value);
				sum_num = sum_num -1;
				for(i=2;i<sum_num;i++)
				{
					var j = i-1;
					table.rows[i].cells[0].innerHTML = '<input type="checkbox" value="'+i+'" name="visitor_id"/>'+j;
				}
			});				
		}
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
};
 
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

function selectNextApprover(){
		del_all_True('next_approver_sel');
		var url = "${ctx}/basic/getnextapprover.action";
		callServerPostForm1(url,document.forms[1]);
	}

function chkSubmit()
{
	var numval = /^[0-9]+$/;
	if($("#seclv_med").val() == ''){
		alert("请选择作业审批密级");
		$("#seclv_med").focus();
		return false;
	}
	
	if($("#secplace_name").val() == ""){
		alert("请填写要害部门部位名称");
		$("#secplace_name").focus();
		return false;
	}
	
	if($("#accompany_name").val() == ""){
		alert("请填写陪同人员");
		$("#accompany_name").focus();
		return false;
	}
	
	if($("#enter_time").val() == ""){
		alert("请填写拟进入时间");
		$("#enter_time").focus();
		return false;
	}
	if($("#leave_time").val() == ""){
		alert("请填写拟离开时间");
		$("#leave_time").focus();
		return false;
	}
	
	if($("#visit_reason").val() == ""){
		alert("请填写进入事由");
		$("#visit_reason").focus();
		return false;
	}

	var table = document.all("visitors");
	var sum_num = table.rows.length;
	if(sum_num < 3)
	{
		alert("未添加外来人员！");
		return false;
	}
	
	//上传文件信息
	
	var info="";
	var fileName;
	$("tr").filter(".files_info").each(function(){
		fileName = $(this).children("td").eq(0).text();
		info += fileName+"::";
	});
	$("#info").val(info);
	//end
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
	
	//ajax提交
	//var url = "${ctx}/secplace/addentersecplaceevent.action";
	//callServerPostForm1(url,document.getElementById("addEnterSecplaceEventForm"));
	//$("#submit_btn").attr("disabled",true);
	
    return true;
}
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
<form action="${ctx}/secplace/deluploadedfile.action" method="POST" id="hiddenDelFileForm">
  	<input type="hidden" name="event_code" value="${event_code}"/>
  	<input type="hidden" name="file_name" id="del_file_name"/>
  	<input type="hidden" name="file_type" id="file_type"/>
</form>
<form action="${ctx}/secplace/addentersecplaceevent.action" method="post" id="addEnterSecplaceEventForm">
	<input type="hidden" name="event_code" value="${event_code}" id="event_code"/>
	<input type="hidden" name="jobType" id="jobType" value="${jobType}"/>
	<input type="hidden" name="seclv_code" id="seclv_code"/>
	<input type="hidden" name="next_approver" id="next_approver"/>
	<input type="hidden" name="dept_id" id="dept_id" value="${curUser.dept_id}"/>
	<input type="hidden" name="usage_code" id="usage_code" value="default"/>
	<input type="hidden" name="info" id="info"/>
	<input type="hidden" name="visitor_num" id="visitor_num"/>
	
	<tr>
	    <td colspan="6" class="title_box">外来人员进出要害部门部位审批表</td>
	</tr>
	<tr height="40"> 
    	<td width="10%" align="center">申请用户 </td>
    	<td width="23%"><font color="blue"><b>${curUser.user_name}</b></font></td>
    	<td width="10%" align="center">用户部门 </td>
    	<td width="23%"><font color="blue"><b>${curUser.dept_name}</b></font></td>
		<td width="10%" align="center"><font color="red">*</font>&nbsp;进入部门部位</td>
<!-- 	    <td width="23%">
			<input type="text" id="secplace_name" name="secplace_name" onkeyup="selectRecvSecplace(this.value);"/>
    		<input type="hidden" id="secplace_code" name="secplace_code"/><br>
    		<div id="allOptionsSP" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
		</td> -->
		<td width="23%">
			<select id="secplace_code" name="secplace_code">
				<option value="">--请选择--</option>
				<s:iterator value="#request.secplaceList" var="seclv">
					<option value="${seclv.secplace_code}">${seclv.secplace_name}</option>
				</s:iterator>
			</select>
		</td>
    </tr>
    <tr height="40">
    	
		<td width="10%" align="center"><font color="red">*</font>&nbsp;陪同人员 </td>
    	<td width="23%">
			<input type="text" id="accompany_name" name="accompany_name" onkeyup="selectRecvUser(this.value);"/>
    		<input type="hidden" id="accompany_id" name="accompany_id"/><br>
    		<div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
		</td>
    	<td align="center"><font color="red">*</font>&nbsp;拟进入时间 </td>
	   	<td>
	   		<input type="text" id="enter_time" name="enter_time" onclick="WdatePicker()" class="Wdate"/>
	   	</td>
	   	<td align="center"><font color="red">*</font>&nbsp;拟离开时间 </td>
	   	<td>
	   		<input type="text" id="leave_time" name="leave_time" onclick="WdatePicker()" class="Wdate"/>
	   	</td>
	</tr>
    <tr>
		<td align="center"><font color="red">*</font>&nbsp;进入事由</td>
		<td colspan="5">
			<textarea id="visit_reason" name="visit_reason" rows="3" cols="50"></textarea>
		</td>		
  	</tr>
  	 <tr>
		<td align="center"><font color="red">*</font>外来人员情况</td>
		<td colspan="5">
			<table id="visitors" name="visitors" width="98%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
				<tr>
					<input type="button" class="button_2003" value="添加一行" onclick="addRow();"/>
					<input type="button" class="button_2003" value="删除一行" onclick="delRow();"/>
				</tr>
				<tr>
					<td align="center" width="5%">序号</td>
					<td align="center" width="10%">姓名</td>
					<td align="center" width="15%">单位</td>
					<td align="center" width="10%">证件类型</td>
					<td align="center" width="20%">证件号码</td>
					<td align="center" width="20%">携带物品</td>
					<td align="center" width="20%">安全保密措施</td>
				</tr>				
			</table>
		</td>
  	</tr>
<%--   	<tr>
  		<td align="center">安全保密措施<br>附件列表</td>
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
  	</tr> --%>
  	
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
	    <td colspan="6" align="center"> 
	      <input type="submit" class="button_2003" value="提交" onclick="return chkSubmit();" id="submit_btn"/>&nbsp;
	      <input class="button_2003" type="button" value="返回" onclick="javascript:history.go(-1)">&nbsp;
	    </td>
	</tr>
</form>
</table>
</body>
</html>
