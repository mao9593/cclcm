<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
    <link type="text/css" rel="stylesheet" href="${ctx}/uploadify/uploadify.css"/>
	<script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>    
	<script language="JavaScript" src="${ctx}/_script/function.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
 	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>	
 	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
 	<script language="JavaScript" src="${ctx}/_script/common.js"></script>
 	<script language="javascript" src="${ctx}/_script/casic304_common_bm.js"></script>
 	<script type="text/javascript" src="${ctx}/uploadify/jquery.uploadify.min.js"></script>
 	<script language="javascript" src="${ctx}/_script/My97DatePicker/WdatePicker.js"></script>
<script>

	$(document).ready(function(){
		onHover();
		disableEnterSubmit();
		setSeclv("seclv_code");
		selectNextApprover();
		teckIsabroad();
		$("#vSave").attr("disabled",true);
		$("#dSave").attr("disabled",true);
		if("${saveStatue}" == 3 || "${saveStatue}" == 5){
			$("#vSave").attr("disabled",false);
		}else if("${saveStatue}" == 1){
			$("#vSave").attr("disabled",true);
			$("#dSave").attr("disabled",false);
		}
		
		var path = $("#headpath").val();
		if(path != ""){
			var name = path.substring(path.lastIndexOf("/")+1, path.length);
			var newname = $("#userid").val() + "/" + name;
			var newp = "${ctx}/files/secmanage/headshot/" + newname;
			//alert(newp);
			var images = document.getElementById("pic");
			images.src=newp;
		}
	});
	
	if('${enter_index}' == 'Y'){
		alert("您是第一次登陆，请完善个人信息，申请后请重新登录");
	}

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

	function selectNextApprover(){
		del_all_True('next_approver_sel');
		var url = "${ctx}/basic/getnextapprover.action";
		callServerPostForm(url,document.forms[1]);
	}
	function getAjaxResult(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			if(xmlHttp.responseText.indexOf("<SELECT") != -1){
				$("#tr_approver").show();
				document.getElementById("allApprovers").innerHTML=xmlHttp.responseText;
				$("#submit_btn").attr("disabled",false);
			}else{
				$("#tr_approver").hide();
				//alert(xmlHttp.responseText);
				$("#submit_btn").attr("disabled",true);
			}
			if($("#next_approver_all").size() > 0 && $("#next_approver_all").children().size() == 0){
				$("#tr_approver").hide();
			}
		}
	}
	
	function chk()
	{
		if($("#base_username").val() == ""){
			alert("请填写姓名");
			$("#base_username").focus();
			return false;
		}
		if(!document.getElementById("base_sex1").checked && !document.getElementById("base_sex2").checked){
	  		alert("请选择性别");
	  		return false;
	  	}
		if($("#marital_status").val() == ""){
			alert("请选择婚姻状况");
			$("#marital_status").focus();
			return false;
		}
		if($("#base_politics").val() == ""){
			alert("请选择政治面貌");
			$("#base_politics").focus();
			return false;
		}		
		if($("#edu_education").val() == ""){
			alert("请选择学历");
			$("#edu_education").focus();
			return false;
		}		
		if($("#base_country").val() == ""){
			alert("请填写国籍");
			$("#base_country").focus();
			return false;
		}
		if($("#base_nation").val() == ""){
			alert("请填写民族");
			$("#base_nation").focus();
			return false;
		}
		if($("#base_nativeplace").val() == ""){
			alert("请填写籍贯");
			$("#base_nativeplace").focus();
			return false;
		}
		if($("#base_birthday").val() == ""){
			alert("请填写出生日期");
			$("#base_birthday").focus();
			return false;
		}
		if($("#experience_time").val() == ""){
			alert("请填写个人简历");
			$("#experience_time").focus();
			return false;
		}
		if($("#idcard").val() == ""){
			alert("请填写身份证号");
			$("#idcard").focus();
			return false;
		}		
		if($("#com_telephone").val() == ""){
			alert("请填写手机号码");
			$("#com_telephone").focus();
			return false;
		}
		if($("#com_email").val() == ""){
			alert("请填写电子邮箱");
			$("#com_email").focus();
			return false;
		}
		if($("#com_residency").val() == ""){
			alert("请填写户籍所在地");
			$("#com_residency").focus();
			return false;
		}
		if($("#com_address").val() == ""){
			alert("请填写现住址");
			$("#com_address").focus();
			return false;
		}
		if($("#job_techpost").val() == ""){
			alert("请填写职务");
			$("#job_techpost").focus();
			return false;
		}
		if($("#job_techtitle").val() == ""){
			alert("请填写职称");
			$("#job_techtitle").focus();
			return false;
		}
		if($("#bmpost_id").val() == ""){
			alert("请选择岗位");
			$("#bmpost_id").focus();
			return false;
		}
		if($("#job_insectime").val() == ""){
			alert("请填写参加工作时间");
			$("#job_insectime").focus();
			return false;
		}
		if($("#resident_card").val() == ""){
			alert("请填写他国绿卡或永久居留证");
			$("#resident_card").focus();
			return false;
		}
		if($("#is_abroad").val() == ""){
			alert("请选择出国情况");
			$("#is_abroad").focus();
			return false;
		}else{
			if($("#abroad_num").val() == 0 && $("#is_abroad").val() != 2){
				alert("请填写出国情况");
				$("#abroad_num").focus();
				return false;
			}
		}
		if($("#oversea").val() == 0){
			alert("请选择本人海外经历");
			$("#oversea").focus();
			return false;
		}
		if($("#famliy_oversea").val() == 0){
			alert("请选择配偶子女海外经历");
			$("#famliy_oversea").focus();
			return false;
		}
		if($("#experience_num").val() == 0){
			alert("请填写个人简历");
			$("#experience_num").focus();
			return false;
		}
		if($("#family_num").val() == 0){
			alert("请填写家庭情况");
			$("#family_num").focus();
			return false;
		}
		if($(":checkbox:checked[name='is_promises'][value!='']").size()==0){
			alert("请勾选本人承诺");
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

//添加出国经历	
function addRow_abroads(){//添加一行
	var table = document.all("abroads");
	
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
	new_tb7 = new_tr.insertCell();
	var nums = row_num -1;
	new_tb0.innerHTML='<input type="checkbox" value="'+row_num+'" name="abroad_id"/>'+nums; 
 	new_tb1.innerHTML='<input type="text" name="abroad_time" id="abroad_time" onclick="WdatePicker()" class="Wdate" size="8"/> \
        至<input type="text" name="back_time" id="back_time" onclick="WdatePicker()" class="Wdate" size="8"/>'; 
	
	new_tb2.innerHTML='<input type="text" id="abroad_place" name="abroad_place" size="10"/>'; 
	new_tb3.innerHTML='<input type="text" id="abroad_reason" name="abroad_reason" size="10"/>'; 
	new_tb4.innerHTML='<input type="text" id="out_customs" name="out_customs" size="10"/>'; 
	new_tb5.innerHTML='<input type="text" id="in_customs" name="in_customs" size="10"/>'; 
	new_tb6.innerHTML='<input type="text" id="abroad_content" name="abroad_content" size="10"/>';
	/* new_tb7.innerHTML='<input type="text" id="abroad_file'+row_num+'" name="abroad_file" value="" size="10"/>'+"&nbsp;"+'<input type="button" class="button_2003" value="上传文件" onclick="openURL('+row_num+',null);"/>';  */
	new_tb7.innerHTML='<input type="text" id="abroad_file'+row_num+'" name="abroad_file" value="" readonly size="10"/>'+"&nbsp;"+'<input type="button" class="button_2003" value="上传文件" onclick="judgeFile('+row_num+');"/>'; 
	$("#abroad_num").val(nums);//添加的行数
}

	function judgeFile(num){
		var fileid = "abroad_file"+num;
		var dis = document.getElementById(fileid).value;
		if(dis){
			openURL(num,dis);
		}else{
			openURL(num,"");
		}
	} 

function delRow_abroads(){//删除一行
	var table = document.all("abroads");
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
		$(":checkbox:checked[value!=''][name='abroad_id']").each(function (){
			var row_num = this.value;
			table.deleteRow(this.value);
			sum_num = sum_num -1;
			for(i=2;i<sum_num;i++)
			{
				var j = i-1;
				table.rows[i].cells[0].innerHTML = '<input type="checkbox" value="'+i+'" name="abroad_id"/>'+j;
			}
		});		
	}
	
	var nums = $("#abroad_num").val();
	$("#abroad_num").val(nums-1);
	
}

//添加个人简历	
function addRow_experiences(){//添加一行
	var table = document.all("experiences");
	
	var row_num = table.rows.length;
	new_tr = table.insertRow();
	new_tr.align="center";	
	new_tb0 = new_tr.insertCell();
	new_tb1 = new_tr.insertCell();
	new_tb2 = new_tr.insertCell();
	new_tb3 = new_tr.insertCell();
	var nums = row_num -1;
	new_tb0.innerHTML='<input type="checkbox" value="'+row_num+'" name="experience_id"/>'+nums; 
	new_tb1.innerHTML='<input type="text" name="experience_time" id="experience_time" onclick="WdatePicker()" class="Wdate" size="15"/>';
	new_tb2.innerHTML='<input type="text" name="end_time" id="end_time" onclick="WdatePicker()" class="Wdate" size="15"/>';        				
	new_tb3.innerHTML='<textarea id="experience_content" name="experience_content" rows="3" cols="70%"></textarea>';
	
	$("#experience_num").val(nums);//添加的行数
}

function delRow_experiences(){//删除一行
	var table = document.all("experiences");
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
		$(":checkbox:checked[value!=''][name='experience_id']").each(function (){
			var row_num = this.value;
			row_num = row_num+1;
			table.deleteRow(this.value);
			sum_num = sum_num -1;
			for(i=2;i<sum_num;i++)
			{
				var j = i-1;
				table.rows[i].cells[0].innerHTML = '<input type="checkbox" value="'+i+'" name="experience_id"/>'+j;
			}
		});		
	}
	var nums = $("#experience_num").val();
	
	$("#experience_num").val(nums-1);
}
//添加家庭成员	
function addRow_family(){//添加一行
	var table = document.all("family");
	
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
	new_tb7 = new_tr.insertCell();
	new_tb8 = new_tr.insertCell();
	var nums = row_num -1;
	new_tb0.innerHTML='<input type="checkbox" value="'+row_num+'" name="family_id"/>'+nums; 
	new_tb1.innerHTML='<input type="text" id="family_relationship" name="family_relationship" size="5"/>'; 
	new_tb2.innerHTML='<input type="text" id="family_name" name="family_name" size="8"/>'; 
	new_tb3.innerHTML='<input type="text" id="family_nationality" name="family_nationality" value="中国" size="8"/>'; 
	new_tb4.innerHTML='<input type="text" name="family_borndate" id="family_borndate" onclick="WdatePicker()" class="Wdate" size="8"/>'; 
	new_tb5.innerHTML='<select name="family_politicalstatus" id="family_politicalstatus" > \
			<option value="" >--请选择--</option> \
			<option value="中共党员" <c:if test="${family_politicalstatus == '中共党员'}">selected</c:if>>中共党员</option>\
			<option value="共青团员" <c:if test="${family_politicalstatus == '共青团员'}">selected</c:if>>共青团员</option>\
			<option value="群众" <c:if test="${family_politicalstatus == '群众'}">selected</c:if>>群众</option>\
		</select>';  
	new_tb6.innerHTML='<input type="text" id="family_workplace" name="family_workplace" size="15"/>'; 
	new_tb7.innerHTML='<input type="text" id="family_lifeplace" name="family_lifeplace" size="15"/>'; 
	new_tb8.innerHTML='<input type="text" id="family_greencard" name="family_greencard" size="15"/>'; 
	
	$("#family_num").val(nums);//添加的行数
}

function delRow_family(){//删除一行
	var table = document.all("family");
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
		$(":checkbox:checked[value!=''][name='family_id']").each(function (){
			var row_num = this.value;
			table.deleteRow(this.value);
			sum_num = sum_num -1;
			for(i=2;i<sum_num;i++)
			{
				var j = i-1;
				table.rows[i].cells[0].innerHTML = '<input type="checkbox" value="'+i+'" name="family_id"/>'+j;
			}
		});		
	}
	
	var nums = $("#family_num").val();
	$("#family_num").val(nums-1);
	
}
function selectAbroad(is_abroad){
	if(is_abroad == ""){
		alert("请选择出国情况");
		$("#is_abroad").focus();
		return false;
	}else if(is_abroad == 1 || is_abroad == 3){
		$("#twice_abroads").show();
	}else if(is_abroad == 2){
		$("#twice_abroads").hide();
	}
}
function teckIsabroad(){
	if(${bmUser.is_abroad == 1} || ${bmUser.is_abroad == 3}){
		$("#twice_abroads").show();
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
				$("#type").val("EMAIL");
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
		//	alert(xmlHttp.responseText);
			if(xmlHttp.responseText == "删除成功"){
				$("#pendingDelete").remove();
			}
		}
	}
//此上传文件仅为用户上传头像使用，请勿改动，或共用
var errorAlert = false;
  function initUpload() {
      $("#file_upload").uploadify({
       	'auto' : false,
       	'method' : "get",
       	'height' : 30,
		'swf' : '${ctx}/uploadify/uploadify.swf', // flash
		'uploader' : '${ctx}/uploadsecmanagefile', // 数据处理url
		'cancelImg': '${ctx}/uploadify/uploadify-cancel.png', // 取消图片
		'width' : 100,
		'fileTypeExts' : '*.*',
		'fileSizeLimit' : 1024 * 1024 * 2,
		'buttonText' : '选择个人照片',
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
//			$("#uploadfile_tr").after(addRowBefore(file.name));
			$("#submit_btn").attr("disabled",false);
			//实时展示用户头像
			var images = document.getElementById("pic");
			var tmp = file.name.substring(file.name.indexOf("."), file.name.length);
			var newname = $("#userid").val() + "/"+file.name;
			var newpic = "${ctx}/files/secmanage/headshot/" + newname;
			//alert(newpic);
			images.src=newpic;
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
			var extStart = file.name.lastIndexOf(".");
			var ext = file.name.substring(extStart+1, file.name.length).toUpperCase();
			if(ext != "BMP" && ext != "PNG" && ext != "GIF"&& ext != "JPG"){
				alert("只限上传bmp、png、gif、jpg格式图片");
				uploadabel = false;
			}

			if(!uploadabel){
				$("#file_upload").uploadify('cancel');
				return false;
			}
			file.name = $("#userid").val();
			$("#submit_btn").attr("disabled",true);
			$("#file_upload").uploadify("settings",'formData',{'event_code':'${event_code}'});
		},
		'onSelect' : function(file){
			var extStart = file.name.lastIndexOf(".");
			var ext = file.name.substring(extStart+1, file.name.length).toUpperCase();
			if(ext != "BMP" && ext != "PNG" && ext != "GIF"&& ext != "JPG"){
				alert("只限上传bmp、png、gif、jpg格式图片");
				$("#file_upload").uploadify('cancel');
				return false;
			}
		}
      });
      $("#file_upload").show();
      $("#init_upload").hide();
  };
  function upload(){
	  initUpload();
	  addFile();
  }

  function ifSave(){
  	if(!document.getElementById("base_sex1").checked && !document.getElementById("base_sex2").checked){
  		alert("请选择性别");
  		return false;
  	}
	$("#func").val("SAVE");
	return true;
  }
  
  function openURL(flag,filename){
	var event_code = $("#event_code").val();
	var url = "${ctx}/securityuser/fileupload.action?flag=Y";
	if(filename){
		url = url +"&filename="+encodeURI(encodeURI(filename));
	}
	var num ="abroad_file"+flag;

	var rValue=window.showModalDialog(url,'', 'dialogHeight:400px;dialogWidth:500px;center:yes;status:no;scroll:no;help:no;unadorned:no;resizable:no');
		if(rValue != null && rValue!= undefined){
		for(var i=1;i<flag;i++){
			var oldname = "abroad_file"+i;
			if(document.getElementById(oldname)){
				//alert("document.getElementById(oldname).value = "+document.getElementById(oldname).value);
				if(document.getElementById(oldname).value == rValue.filename){
					alert("同名文件已经上传，请重新选择文件");
					document.getElementById(num).value = "";
					return false;
				}
			}
		}
		document.getElementById(num).value = rValue.filename;
	}
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
	<form action="${ctx}/securityuser/adduserinfoevent.action" method="POST">
		<input type="hidden" id="event_code" name="event_code" value="${event_code}"/>
		<input type="hidden" id="userid" value="${curUser.user_iidd}"/>
		<input type="hidden" id="dept_id" name="dept_id" value="${curUser.dept_id}"/>
		<input type="hidden" id="jobType" name="jobType" value="${jobType}"/>
		<input type="hidden" id="next_approver" name="next_approver" />
		<input type="hidden" name="seclv_code" id="seclv_code"/>
		<input type="hidden" name="abroad_num" id="abroad_num" value="${abroad_num}"/>
		<input type="hidden" name="experience_num" id="experience_num" value="${experience_num}"/>
		<input type="hidden" name="family_num" id="family_num" value="${family_num}"/>
		<input type="hidden" name="func" id="func"/>
		<input type="hidden" name="enter_index" id="enter_index" value="${enter_index}"/>
		<input type="hidden" id="headpath" name="headpath" value="${headpath}"/>
	<tr>
	    <td colspan="8" class="title_box">完善我的资料</td>
	</tr>	
	<tr>
		<td colspan="8" style="background-color: #ffffff">
			<input type="button" class="button_2003" value="保   存" onclick="if(ifSave()) forms[1].submit()"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="button" class="button_2003" value="查看保存记录" onclick="go('${ctx}/securityuser/adduserinfoevent.action?func=READ&event_code=${event_code}');" id="vSave"/>&nbsp;&nbsp;
			<input type="button" class="button_2003" value="撤销保存记录" onclick="go('${ctx}/securityuser/adduserinfoevent.action?');" id="dSave"/>&nbsp;&nbsp;
		
			<c:if test="${saveStatue == 1}">
				<font color="gray">用户<b>已保存</b>信息展示</font>	
			</c:if>
			<c:if test="${saveStatue == 3}">
				<font color="gray">用户<b>已通过审核</b>信息展示</font>
			</c:if>
			<c:if test="${saveStatue == 2 || saveStatue == 5}">
				<font color="gray">用户<b>已驳回审核</b>信息展示</font>
			</c:if>
		</td>
	</tr>
	<tr><td colspan="8" style="background-color: #C1E5FD" align="center">基本信息</td></tr>	
	<tr>
		<td width="10%" align="center"><font color="red">*</font>姓名</td>
		<td width="14%">
		<c:choose>
			<c:when test="${empty bmUser.base_username}">
				<input type="text" name="base_username" id="base_username" value="${secUser.user_name}">
			</c:when>
			<c:otherwise>
				<input type="text" name="base_username" id="base_username" value="${bmUser.base_username}">
			</c:otherwise>
		</c:choose>
		</td>
		<td width="10%" align="center"><font color="red">*</font>性别</td>
		<td width="14%">
			<input type="radio" id="base_sex1" name="base_sex" value="M"<c:if test="${bmUser.base_sex == 'M'}">checked</c:if>>男
			<input type="radio" id="base_sex2" name="base_sex" value="F" <c:if test="${bmUser.base_sex == 'F'}">checked</c:if>>女
		</td>
		<td width="10%" align="center"><font color="red">*</font>国籍</td>
		<td width="14%">
			<c:choose>
				<c:when test="${empty bmUser.base_country}">
					<input type="text" name="base_country" id="base_country" value="中国">
				</c:when>
				<c:otherwise>
					<input type="text" name="base_country" id="base_country" value="${bmUser.base_country}">
				</c:otherwise>
			</c:choose>	
		</td>
		<td width="14%" align="center" colspan="2" rowspan="4">
			<table border rules=all width="100%" border="0" cellspacing="0" cellpadding="0" align="left"  class="table_box_bottom_border">
				<tr>
	  				<td align="center" width="50%">
	  					<input type="button" class="button_2003" id="init_upload" value="选择个人照片" onclick="initUpload();"/>
			  			<input type="file" name="file_upload" id="file_upload" disabled="disabled" style="display:none"/>
			 		</td>
					<td align="center" width="50%">
						<input type="button" onclick="addFile();" value="上传" class="button_2003"/>&nbsp;	
			  		</td>
			 	</tr>
				<tr>
					<td colspan="2" align="center">
						<img id="pic" src="${ctx}/_image/personal.jpg" width="100" height="100"><br><br>
					</td>
				</tr>
			</table>
		</td>		
	</tr>
	<tr>
		<td align="center"><font color="red">*</font>籍贯</td>
		<td><input type="text" name="base_nativeplace" id="base_nativeplace" value="${bmUser.base_nativeplace}"></td>
		<td align="center"><font color="red">*</font>出生日期</td>
		<td>
			<input type="text" id="base_birthday" name="base_birthday" onclick="WdatePicker()" class="Wdate" size="10" value="${bmUser.base_birthday_str}"/>
	    </td>
		<td align="center"><font color="red">*</font>婚姻状况 </td>
		<td>
			<select name="marital_status" id="marital_status" style="width:155px;">
				<option value="" >--请选择--</option>
				<option value="0" <c:if test="${bmUser.marital_status == '0'}">selected</c:if>>未婚</option>
				<option value="1" <c:if test="${bmUser.marital_status == '1'}">selected</c:if>>已婚</option>
				<option value="2" <c:if test="${bmUser.marital_status == '2'}">selected</c:if>>离异</option>
				<option value="3" <c:if test="${bmUser.marital_status == '3'}">selected</c:if>>丧偶</option>
			</select>
		</td>
		<%-- <td width="10%" colspan="2" rowspan="3" align="center">
			<img id="pic" src="${ctx}/_image/noPic.jpg" width="100" height="100"><br><br>
		</td> --%>		    
	</tr>
	<tr>
		<td align="center"><font color="red">*</font>学历</td>
		<td>
			<%-- <input type="text" name="edu_education" id="edu_education" value="${bmUser.edu_education}"> --%>
			<select name="edu_education" id="edu_education" style="width:155px;">
				<option value="" >--请选择--</option>
				<option value="1" <c:if test="${bmUser.edu_education == '1'}">selected</c:if>>博士</option>
				<option value="2" <c:if test="${bmUser.edu_education == '2'}">selected</c:if>>硕士</option>
				<option value="3" <c:if test="${bmUser.edu_education == '3'}">selected</c:if>>本科</option>
				<option value="4" <c:if test="${bmUser.edu_education == '4'}">selected</c:if>>大专</option>
				<option value="5" <c:if test="${bmUser.edu_education == '5'}">selected</c:if>>高中</option>
				<option value="6" <c:if test="${bmUser.edu_education == '6'}">selected</c:if>>中专</option>
				<option value="7" <c:if test="${bmUser.edu_education == '7'}">selected</c:if>>初中及以下</option>
			</select>
		</td>
	   	<td align="center"><font color="red">*</font>身份证号</td>
		<td colspan="3"><input type="text" name="idcard" id="idcard" value="${bmUser.idcard}"></td>
	</tr>
		<td width="10%" align="center"><font color="red">*</font>民族</td>
		<td width="14%">
			<c:choose>
				<c:when test="${empty bmUser.base_nation}">
					<input type="text" name="base_nation" id="base_nation" value="汉族">
				</c:when>
				<c:otherwise>
					<input type="text" name="base_nation" id="base_nation" value="${bmUser.base_nation}">
				</c:otherwise>
			</c:choose>		
		</td>
		<td align="center"><font color="red">*</font>政治面貌</td>
		<td colspan="3">
			<select name="base_politics" id="base_politics" style="width:155px;">
				<option value="" >--请选择--</option>
				<option value="1" <c:if test="${bmUser.base_politics == '1'}">selected</c:if>>中共党员</option>
				<option value="2" <c:if test="${bmUser.base_politics == '2'}">selected</c:if>>共青团员</option>
				<option value="3" <c:if test="${bmUser.base_politics == '3'}">selected</c:if>>群众</option>
			</select>
		</td>		
	<tr>
		<td align="center"><font color="red">*</font>个人简历</td>
		<td colspan="7" >
			<table id="experiences" name="experiences" width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
				<tr>
					<input type="button" class="button_2003" value="添加一行" onclick="addRow_experiences();"/>
					<input type="button" class="button_2003" value="删除一行" onclick="delRow_experiences();"/>
					<font color="gray">从高中开始学习情况/工作基本简历（时间不间断，国外经历必填）</font>
				</tr>
				<tr>
					<td align="center" width="5%">序号</td>
					<td align="center" width="15%">开始时间</td>
					<td align="center" width="15%">结束时间</td>
					<td align="center" width="60%">工作/学习简历</td>
				</tr>
				<s:iterator value="#request.experienceinfo" var="item">
		  			<tr>
		  				<td align="center"><input type="checkbox" value="${item.row_num}" name="experience_id"/>${item.row_num}</td>
		  				<td align="center">
		  					<input type="text" name="experience_time" id="experience_time" onclick="WdatePicker()" class="Wdate" size="15" value="${item.experience_time}"/>
		  				</td>
						<td align="center">
							<input type="text" name="end_time" id="end_time" onclick="WdatePicker()" class="Wdate" size="15" value="${item.end_time}"/>
						</td>
		  				<td align="center"><textarea id="experience_content" name="experience_content" rows="3" cols="70">${item.experience_content}</textarea></td>
		  			</tr>
				</s:iterator>					
			</table>	
		</td>
	</tr>
	<tr><td colspan="8" style="background-color: #C1E5FD" align="center">通讯信息</td></tr>
	<tr>
		<td align="center"><font color="red">*</font>手机号码</td>
		<td colspan="3"><input type="text" name="com_telephone" id="com_telephone" value="${bmUser.com_telephone}"/></td>
		<td align="center"><font color="red">*</font>电子邮箱</td>
		<td colspan="3"><input type="text" name="com_email" id="com_email" value="${bmUser.com_email}"/></td>
	</tr>
	<tr>
		<td align="center"><font color="red">*</font>户籍所在地</td>
		<td colspan="3"><input type="text" size="60" name="com_residency" id="com_residency" value="${bmUser.com_residency}"></td>
		<td align="center"><font color="red">*</font>现住址</td>
		<td colspan="3"><input type="text" size="60" name="com_address" id="com_address" value="${bmUser.com_address}"></td>
	</tr>
	<tr><td colspan="8" style="background-color: #C1E5FD" align="center">职务信息</td></tr>
	<tr>
		<td align="center">涉密等级</td>
		<td><font color="blue"><b>&nbsp;${secUser.security_name}</b></font></td>
		<td align="center"><font color="red">*</font>参加工作时间</td>
		<td>
			<c:choose>
				<c:when test="${empty bmUser.job_insectime}">
					<input type="text" name="job_insectime" id="job_insectime" onclick="WdatePicker()" class="Wdate" size="10"/>
				</c:when>
				<c:otherwise>
					<c:if test="${ifedit == 1}">
						<input type="text" name="job_insectime" id="job_insectime" value="${bmUser.job_insectime_str}"/>					
					</c:if>
					<c:if test="${ifedit != 1}">
						<%-- <font color="blue"><b>&nbsp;${bmUser.job_insectime_str}</b></font> --%>
						<input type="text" name="job_insectime" id="job_insectime" value="${bmUser.job_insectime_str}" onclick="WdatePicker()" class="Wdate" size="10"/>
					</c:if>
				</c:otherwise>
			</c:choose>	
	    </td>
	  	<td align="center"><font color="red">*</font>职务</td>
		<td>
			<c:choose>
				<c:when test="${empty bmUser.job_techpost}">
					<input type="text" name="job_techpost" id="job_techpost">
				</c:when>
				<c:otherwise>
					<c:if test="${ifedit == 1}">
						<input type="text" name="job_techpost" id="job_techpost" value="${bmUser.job_techpost}">
					</c:if>
					<c:if test="${ifedit != 1}">
						<%-- <font color="blue"><b>&nbsp;${bmUser.job_techpost}</b></font> --%>
						<input type="text" name="job_techpost" id="job_techpost" value="${bmUser.job_techpost}">
					</c:if>
				</c:otherwise>
			</c:choose>	
		</td>
		<td align="center"><font color="red">*</font>职称</td>
		<td>
			<c:choose>
				<c:when test="${empty bmUser.job_techtitle}">
					<input type="text" name="job_techtitle" id="job_techtitle">
				</c:when>
				<c:otherwise>
					<c:if test="${ifedit == 1}">
						<input type="text" name="job_techtitle" id="job_techtitle" value="${bmUser.job_techtitle}">
					</c:if>
					<c:if test="${ifedit != 1}">
						<input type="text" name="job_techtitle" id="job_techtitle" value="${bmUser.job_techtitle}">
						<%-- <font color="blue"><b>&nbsp;${bmUser.job_techtitle}</b></font> --%>
					</c:if>
					
				</c:otherwise>
			</c:choose>	
		</td>
	<%--     <td align="center">&nbsp;涉密类别</td>
		<td colspan="3">
			<font color="red">涉密文件、涉密项目、其他涉密事项</font><br>	 <input type="text" size="50" name="sec_category" id="sec_category" value="${bmUser.sec_category}">
			<select name="sec_category" id="sec_category" >
				<option value="涉密文件" <c:if test="${bmUser.sec_category == '涉密文件'}">selected</c:if>>涉密文件</option>
				<option value="涉密项目" <c:if test="${bmUser.sec_category == '涉密项目'}">selected</c:if>>涉密项目</option>
				<option value="其他涉密事项" <c:if test="${bmUser.sec_category == '其他涉密事项'}">selected</c:if>>其他涉密事项</option>
			</select>
		</td> --%>
	</tr>
	<tr>
		<td align="center"><font color="red">*</font>岗位类别</td>
		<td colspan="7">
			<c:choose>
				<c:when test="${empty secUser.post_name}">
					<select name="bmpost_id" id="bmpost_id">
						<option value="">--请选择--</option>
							<s:iterator value="#request.postList" var="post_after">
								<option value="${post_after.post_id}">${post_after.post_name}</option>
							</s:iterator>
					</select> 
				</c:when>
				<c:otherwise>
					<c:if test="${ifedit == 1}">
						<select name="bmpost_id" id="bmpost_id" value="${bmUser.bmpost_id}">
							<option value="">--请选择--</option>
								<s:iterator value="#request.postList" var="post_after">
									<option value="${post_after.post_id}" <s:if test="#request.bmpost_id==#post_after.post_id">selected="selected"</s:if>>${post_after.post_name}</option>
								</s:iterator>
						</select>
					</c:if>
					<c:if test="${ifedit != 1}">
						<font color="blue"><b>&nbsp;${secUser.post_name}</b></font>
						<input type="hidden" name="bmpost_id" id="bmpost_id" value="${secUser.post_id}">
					</c:if>
				</c:otherwise>
			</c:choose>	
			
		</td>
	</tr>
	<tr><td colspan="8" style="background-color: #C1E5FD" align="center">境外情况</td></tr>
	<tr>
		<td align="center"><font color="red">*</font>因私出国（境）情况</td>
		<td>
			<c:choose>
				<c:when test="${empty bmUser.is_abroad}">
					<select name="is_abroad" id="is_abroad" onchange="selectAbroad (this.value);" style="width:155px;">
						<option value="">--请选择--</option>
						<option value="1" <c:if test="${bmUser.is_abroad == 1}">selected</c:if>>有出国（境）经历</option>
						<option value="2" <c:if test="${bmUser.is_abroad == 2}">selected</c:if>>无出国（境）经历</option>
					</select>
				</c:when>
				<c:otherwise>
					<c:if test="${ifedit == 1}">
						<select name="is_abroad" id="is_abroad" onchange="selectAbroad (this.value);" style="width:155px;">
							<option value="">--请选择--</option>
							<option value="1" <c:if test="${bmUser.is_abroad == 1}">selected</c:if>>有出国（境）经历</option>
							<option value="2" <c:if test="${bmUser.is_abroad == 2}">selected</c:if>>无出国（境）经历</option>
						</select>
					</c:if>
					<c:if test="${ifedit != 1}">
						<%-- <input type="hidden" name="is_abroad" id="is_abroad" value="${bmUser.is_abroad}">
						<font color="blue"><b>&nbsp;${bmUser.is_abroad_name}</b></font> --%>
						<select name="is_abroad" id="is_abroad" onchange="selectAbroad (this.value);" style="width:155px;">
							<option value="">--请选择--</option>
							<option value="1" <c:if test="${bmUser.is_abroad == 1}">selected</c:if>>有出国（境）经历</option>
							<option value="2" <c:if test="${bmUser.is_abroad == 2}">selected</c:if>>无出国（境）经历</option>
						</select>
					</c:if>
					
				</c:otherwise>
			</c:choose>	
		</td>
		<td align="center"><font color="red">*</font>本人海外经历</td>
		<td>
			<c:choose>
				<c:when test="${empty bmUser.oversea}">
					<select name="oversea" id="oversea" style="width:155px;">
						<option value="">--请选择--</option>
						<option value="1" <c:if test="${bmUser.oversea == 1}">selected</c:if>>无</option>
						<option value="2" <c:if test="${bmUser.oversea == 2}">selected</c:if>>有工作经历</option>
						<option value="3" <c:if test="${bmUser.oversea == 3}">selected</c:if>>有学习经历</option>
					</select>
				</c:when>
				<c:otherwise>
					<c:if test="${ifedit == 1}">
						<select name="oversea" id="oversea" style="width:155px;">
							<option value="">--请选择--</option>
							<option value="1" <c:if test="${bmUser.oversea == 1}">selected</c:if>>无</option>
							<option value="2" <c:if test="${bmUser.oversea == 2}">selected</c:if>>有工作经历</option>
							<option value="3" <c:if test="${bmUser.oversea == 3}">selected</c:if>>有学习经历</option>
						</select>
					</c:if>
					<c:if test="${ifedit != 1}">
						<%-- <input type="hidden" name="oversea" id="oversea" value="${bmUser.oversea}">
						<font color="blue"><b>&nbsp;${bmUser.oversea_name}</b></font> --%>
						<select name="oversea" id="oversea" style="width:155px;">
							<option value="">--请选择--</option>
							<option value="1" <c:if test="${bmUser.oversea == 1}">selected</c:if>>无</option>
							<option value="2" <c:if test="${bmUser.oversea == 2}">selected</c:if>>有工作经历</option>
							<option value="3" <c:if test="${bmUser.oversea == 3}">selected</c:if>>有学习经历</option>
						</select>
					</c:if>
				</c:otherwise>
			</c:choose>	
		</td>		
	    <td colspan="2" align="center"><font color="red">*</font>他国绿卡或永久居住证</td>
		<td colspan="2">
			<c:choose>
				<c:when test="${empty bmUser.resident_card}">
					<font color="gray">若无填写无，有则详细填写</font><br>
					<input type="text" name="resident_card" id="resident_card">
				</c:when>
				<c:otherwise>
					<c:if test="${ifedit == 1}">
						<font color="gray">若无填写无，有则详细填写</font><br>
						<input type="text" name="resident_card" id="resident_card" value="${bmUser.resident_card}">
					</c:if>
					<c:if test="${ifedit != 1}">
						<%-- <input type="hidden" name="resident_card" id="resident_card" value="${bmUser.resident_card}">
						<font color="blue"><b>&nbsp;${bmUser.resident_card}</b></font> --%>
						<font color="gray">若无填写无，有则详细填写</font><br>
						<input type="text" name="resident_card" id="resident_card" value="${bmUser.resident_card}">
					</c:if>
				</c:otherwise>
			</c:choose>						
		</td>	
	</tr>
	<tr id="twice_abroads" style="display:none">
	    <td align="center"><font color="red">*</font>最近两次出国（境）情况</td>
	    <td colspan="7">
	    	<table border rules=all id="abroads" name="abroads" width="100%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box_bottom_border">
				<tr>
					<c:choose>
						<c:when test="${empty bmUser.is_abroad}">
							<input type="button" class="button_2003" value="添加一行" onclick="addRow_abroads();"/>
							<input type="button" class="button_2003" value="删除一行" onclick="delRow_abroads();"/>
						</c:when>
						<c:otherwise>
							<%-- <c:if test="${ifedit == 1}"> --%>
								<input type="button" class="button_2003" value="添加一行" onclick="addRow_abroads();"/>
								<input type="button" class="button_2003" value="删除一行" onclick="delRow_abroads();"/>
							<%-- </c:if> --%>
						</c:otherwise>
					</c:choose>						
				</tr>
				<tr>
					<td align="center" width="3%">序号</td>
					<td align="center" width="11%">出国（境）日期-归国（境）日期</td>
					<td align="center" width="6%">出国（境）地点</td>
					<td align="center" width="6%">出国（境）事由</td>
					<td align="center" width="6%" >出境海关</td>
					<td align="center" width="6%">入境海关</td>
					<td align="center" width="6%">陪同人员</td>
					<td align="center" width="12%">在外情况汇报<br>
						<font color="red">*</font>请先下载&nbsp;
						<a href="${ctx}/html/securityuser/因私出境在外情况汇报表.doc" style="font-style: color:blue;"><font size="2">在外情况模板</font></a>
					</td>
				</tr>	
				<s:iterator value="#request.abroadinfo" var="itemm">
					<c:if test="${ifedit == 1}">
			  			<tr>
			  				<td align="center"><input type="checkbox" value="${itemm.row_num}" name="abroad_id"/>${itemm.row_num}</td>
			  				<td align="center">
			  					<input type="text" name="abroad_time" id="abroad_time" onclick="WdatePicker()" class="Wdate" size="8" value="${itemm.abroad_time}"/>
			  					至<input type="text" name="back_time" id="back_time" onclick="WdatePicker()" class="Wdate" size="8" value="${itemm.back_time}"/>
			  				<td align="center"><input type="text" id="abroad_place" name="abroad_place" value="${itemm.abroad_place}" size="10"/></td>
			  				<td align="center"><input type="text" id="abroad_reason" name="abroad_reason" value="${itemm.abroad_reason}" size="10"/></td>
			  				<td align="center"><input type="text" id="out_customs" name="out_customs" value="${itemm.out_customs}" size="10"/></td>
			  				<td align="center"><input type="text" id="in_customs" name="in_customs" value="${itemm.in_customs}" size="10"/></td>
			  				<td align="center"><input type="text" id="abroad_content" name="abroad_content" value="${itemm.abroad_content}" size="10"/></td>
			  				<td align="center"><input type="text" id="abroad_file${itemm.row_num}" name="abroad_file" value="${itemm.abroad_file}" readonly size="10"/>&nbsp;
			  					<input type="button" class="button_2003" value="上传文件" onclick="openURL('${itemm.row_num}','${itemm.abroad_file}');" />
			  				</td>
			  			</tr>					
					</c:if>		
					<c:if test="${ifedit != 1}">		
						<tr>
			  				<td align="center">${itemm.row_num}</td>
			  				<td align="center"><font color="blue"><b>${itemm.abroad_time}&nbsp;至&nbsp;${itemm.back_time}</b></font>
			  					<input type="hidden" name="abroad_time" id="abroad_time" size="8" value="${itemm.abroad_time}"/>
			  					<input type="hidden" name="back_time" id="back_time" size="8" value="${itemm.back_time}"/>
			  				</td>
			  				<td align="center">
			  					<font color="blue"><b>${itemm.abroad_place}</b></font>
			  					<input type="hidden" id="abroad_place" name="abroad_place" value="${itemm.abroad_place}" size="10"/>
			  				</td>
			  				<td align="center">
			  					<font color="blue"><b>${itemm.abroad_reason}</b></font>
			  					<input type="hidden" id="abroad_reason" name="abroad_reason" value="${itemm.abroad_reason}" size="10"/>
			  				</td>
			  				<td align="center">
			  					<font color="blue"><b>${itemm.out_customs}</b></font>
			  					<input type="hidden" id="out_customs" name="out_customs" value="${itemm.out_customs}" size="10"/>
			  				</td>
			  				<td align="center">
			  					<font color="blue"><b>${itemm.in_customs}</b></font>
			  					<input type="hidden" id="in_customs" name="in_customs" value="${itemm.in_customs}" size="10"/>
			  				</td>
			  				<td align="center">
			  					<font color="blue"><b>${itemm.abroad_content}</b></font>
			  					<input type="hidden" id="abroad_content" name="abroad_content" value="${itemm.abroad_content}" size="10"/>
			  				</td>
			  				<td align="center">
			  					<font color="blue"><b>${itemm.abroad_file}</b></font>
			  					<input type="hidden" id="abroad_file${itemm.row_num}" name="abroad_file" value="${itemm.abroad_file}" size="10"/>
			  				</td>
			  			</tr>
			  		</c:if>
				</s:iterator>		
			</table>  		
	</tr>
	<tr><td colspan="8" style="background-color: #C1E5FD" align="center">家庭信息</td></tr>
	<tr>
		<td align="center"><font color="red">*</font>配偶子女海外经历</td>
		<td colspan="7">
			<select name="famliy_oversea" id="famliy_oversea" style="width:155px;">
				<option value="">--请选择--</option>
				<option value="1" <c:if test="${bmUser.famliy_oversea == 1}">selected</c:if>>无</option>
				<option value="2" <c:if test="${bmUser.famliy_oversea == 2}">selected</c:if>>有工作经历</option>
				<option value="3" <c:if test="${bmUser.famliy_oversea == 3}">selected</c:if>>有学习经历</option>
			</select>
		</td>
	</tr>
	<tr>
		<td align="center"><font color="red">*</font>家庭成员信息</td>
		<td colspan="8">
			<table id="family" name="family" width="100%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box_bottom_border">
				<tr>
					<input type="button" class="button_2003" value="添加一行" onclick="addRow_family();"/>
					<input type="button" class="button_2003" value="删除一行" onclick="delRow_family();"/>
				</tr>
				<tr>
					<td align="center" width="5%">序号</td>
					<td align="center" width="10%">与本人关系</td>
					<td align="center" width="10%">姓名</td>
					<td align="center" width="10%">国籍</td>
					<td align="center" width="10%">出生年月</td>
					<td align="center" width="10%">政治面貌</td>
					<td align="center" width="15%">工作/学习单位</td>
					<td align="center" width="15%">现居住地</td>
					<td align="center" width="15%">是否持有他国绿卡或永久居住证</td>
				</tr>	
				<s:iterator value="#request.familyinfo" var="it">
		  			<tr >
		  				<td align="center"><input type="checkbox" value="${it.row_num}" name="family_id"/>${it.row_num}</td>
		  				<td align="center"><input type="text" id="family_relationship" name="family_relationship" value="${it.family_relationship}" size="5"/></td>
		  				<td align="center"><input type="text" id="family_name" name="family_name" value="${it.family_name}" size="8"/></td>
		  				<td align="center"><input type="text" id="family_nationality" name="family_nationality" value="${it.family_nationality}" size="8"/></td>
		  				<td align="center">
		  					<%-- <input type="text" id="family_borndate" name="family_borndate" value="${it.family_borndate}" size="8"/> --%>
		  					<input type="text" name="family_borndate" id="family_borndate" value="${it.family_borndate}" onclick="WdatePicker()" class="Wdate" size="8"/>
		  				</td>
		  				<td align="center">
		  					<select name="family_politicalstatus" id="family_politicalstatus" > 
								<option value="中共党员" <c:if test="${family_politicalstatus == '中共党员'}">selected</c:if>>中共党员</option>
								<option value="共青团员" <c:if test="${family_politicalstatus == '共青团员'}">selected</c:if>>共青团员</option>
								<option value="群众" <c:if test="${family_politicalstatus == '群众'}">selected</c:if>>群众</option>
							</select>
		  				</td>
		  				<td align="center"><input type="text" id="family_workplace" name="family_workplace" value="${it.family_workplace}" size="15"/></td>
		  				<td align="center"><input type="text" id="family_lifeplace" name="family_lifeplace" value="${it.family_lifeplace}" size="15"/></td>
		  				<td align="center"><input type="text" id="family_greencard" name="family_greencard" value="${it.family_greencard}" size="15"/></td>
		  			</tr>
				</s:iterator>			
			</table>	
		</td>
	</tr>
	<tr>
		<td colspan="8" align="center">
			<input type="checkbox" name="is_promises" id="is_promises" value="user_promises"/><font color="red"><b>*本人承诺所填信息真实有效，并为此承担相应责任！</b></font>
		</td>
	</tr>		
  	<tr id="tr_approver" style="display: none">
  		<td align="center" id="selApprover1">选择审批人</td>
  		<td id="selApprover2" colspan="7">
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
	</form>
	 	
  	<tr>
    <td colspan="8" align="center">
      <input type="button" class="button_2003" value="提交" onclick="if(chk()) forms[1].submit();" id="submit_btn"/>
    </td>
  </tr>
</table>

</body>
</html>