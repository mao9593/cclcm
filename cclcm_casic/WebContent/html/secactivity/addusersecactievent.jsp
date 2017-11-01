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
	<script type="text/javascript" src="${ctx}/uploadify/jquery.uploadify.min.js"></script>	
	<script language="javascript" src="${ctx}/_script/casic304_common_bm.js"></script>
	<script language="javascript" src="${ctx}/_script/My97DatePicker/WdatePicker.js"></script>
<script>
$(document).ready(function(){
	onHover();
	disableEnterSubmit();
	setSeclv("seclv_code");
	selectNextApprover();
	document.getElementById("allOptions").innerHTML="";
});
	
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
				alert(xmlHttp.responseText);
				$("#submit_btn").attr("disabled",true);
			}
			if($("#next_approver_all").size() > 0 && $("#next_approver_all").children().size() == 0){
				$("#tr_approver").hide();
			}
		}
	}
	function chk()
	{
		if($("#act_selv").val().trim() == ""){
			alert("请选择会议密级");
			$("#act_selv").focus();
			return false;
		}
		if($("#name").val().trim() == ""){
			alert("请填写活动名称");
			$("#name").focus();
			return false;
		}
		if($("#apply_type").val().trim() == ""){
			alert("请选择申请类型");
			$("#apply_type").focus();
			return false;
		}
		
/* 		if($("#secret_type").val().trim() == ""){
			alert("请选择活动类型");
			$("#secret_type").focus();
			return false;
		}	
		 */
		if($("#place").val().trim() == ""){
			alert("请填写活动地点");
			$("#place").focus();
			return false;
		}	
		
		if($("#secret_director_name").val().trim() == ""){
			alert("请填写活动负责人");
			$("#secret_director_name").focus();
			return false;
		}
		if($("#contact").val().trim() == ""){
			alert("请填写联系方式");
			$("#contact").focus();
			return false;
		}
		if($("#act_user").val().trim() == ""){
			alert("请填写参会人员");
			$("#act_user").focus();
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
				callServerPostForm1("${ctx}/secactivity/deluploadedfile.action",form);
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
var errorAlert = false;
  function initUpload() {
// 	$(function() {
      $("#file_upload").uploadify({
       	'auto' : false,
       	'method' : "get",
       	'height' : 30,
		'swf' : '${ctx}/uploadify/uploadify.swf', // flash
		'uploader' : '${ctx}/uploadactivityfile', // 数据处理url
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
		user_name=user_name.substring(0,user_name.indexOf("/"));
		if(user_id != ""){
	    	$("#secret_director").val(user_id);
			$("#secret_director_name").val(user_name);
			document.getElementById("allOptions").innerHTML="";
		}
	}
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<form action="${ctx}/secactivity/deluploadedfile.action" method="POST" id="hiddenDelFileForm">
  	<input type="hidden" name="event_code" value="${event_code}"/>
  	<input type="hidden" name="file_name" id="del_file_name"/>
  	<input type="hidden" name="file_type" id="file_type"/>  	
</form>
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">	
		<form action="${ctx}/secactivity/addusersecactievent.action" method="POST">
		<input type="hidden" id="event_code" name="event_code" value="${event_code}"/>
		<input type="hidden" id="dept_id" name="dept_id" value="${curUser.dept_id}"/>
		<input type="hidden" id="jobType" name="jobType" value="${jobType}"/>
		<input type="hidden" id="next_approver" name="next_approver" />
		<input type="hidden" name="seclv_code" id="seclv_code"/>
	<tr>
	    <td colspan="6" class="title_box">重大涉密活动（会议）保密方案审批表</td>
	</tr>
	<tr> 
    	<td width="13%" align="center">申请用户 </td>
    	<td width="17%"><font color="blue"><b>${curUser.user_name}</b></font></td>
    	<td width="13%" align="center">用户部门 </td>
    	<td width="17%"><font color="blue"><b>${curUser.dept_name}</b></font></td>
		<td width="13%" align="center"><font color="red">*</font>&nbsp;活动/会议 密级</td>
	    <td width="17%">
			<select id="act_selv" name="act_selv">
				<option value="">--请选择--</option>
				<s:iterator value="#request.seclvList" var="seclv">
					<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
				</s:iterator>
			</select>
		</td>
	</tr>
	<tr>
   		<td align="center"><font color="red">*</font>&nbsp;活动/会议名称</td>
		<td colspan="5"><input type="text" name="name" id="name" size="80"/></td>
	</tr>
	<tr>
		<td colspan="6" align="center">
		<a href="${ctx}/html/secactivity/参加涉密会议（活动）人员登记表.doc" style="font-style: color:red;"><font color="red">*</font><font size="2">请下载并打印《参加涉密会议（活动）人员登记表》，填写完整，会后交至保密处 </font></a></font>
		</td>
	</tr>
	<tr> 	
		<td align="center">会务承担单位/部门</td>
		<td>
			<input type="text" id="act_dept_name" name="act_dept_name" readonly="readonly" style="cursor:hand" onclick="openDeptSelect('act_dept_name','act_dept_id','radio')"/>
    		<input type="hidden" id="act_dept_id" name="act_dept_id"/><br>
		</td>			
		<td align="center"><font color="red">*</font>&nbsp;申请类型 </td>
		<td>
			<select name="apply_type" id="apply_type">
				<option value="">--请选择--</option>
				<option value="1">涉密活动</option>
				<option value="2">涉密会议</option>
				<option value="3">外场实验</option>
			</select>
		</td>
<!-- 		<td align="center"><font color="red">*</font>&nbsp;活动类型 </td>
		<td>
			<select name="secret_type" id="secret_type">
				<option value="">--请选择--</option>
				<option value="1">重要涉密活动</option>
				<option value="2">一般涉密活动</option>
				<option value="3">领导参观</option>
				<option value="4">涉密展览会</option>
				<option value="5">其他</option>
			</select>
		</td> -->
		<td align="center"><font color="red">*</font>&nbsp;活动地点</td>
		<td><input type="text" name="place" id="place"/></td>	
	</tr>
	<tr>
		<td align="center">主办单位</td>
		<td><input type="text" name="sponsor" id="sponsor"/></td>
		<td align="center">承办单位</td>
		<td><input type="text" name="organizer" id="organizer"/></td>
		<td align="center"><font color="red">*</font>&nbsp;活动/会议负责人</td>
		<td>
    		<input type="text" id="secret_director_name" name="secret_director_name" onkeyup="selectRecvUser(this.value);"/>
    		<input type="hidden" id="secret_director" name="secret_director"/><br>
    		<div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
    		</div>
	    </td>
	</tr>
	
  	<tr>   		   
		<td align="center">开始时间</td>
		<td>
			<input type="text" id="start_time" name="start_time" onclick="WdatePicker()" class="Wdate"/>
	 	</td>
		<td align="center">结束时间</td>
		<td>
			<input type="text" id="end_time" name="end_time" onclick="WdatePicker()" class="Wdate"/>
	 	</td>
	 	<td align="center"><font color="red">*</font>&nbsp;联系方式</td>
		<td><input type="text" name="contact" id="contact"/></td>	
	</tr>
	<tr>
  		<td align="center"><font color="red">*</font>&nbsp;参会人员</td>
		<td colspan="3"><textarea name="act_user" rows="3" cols="60" id="act_user"></textarea></td>
  		<td align="center">&nbsp;备注</td>
		<td><textarea name="summ" rows="3" cols="30" id="summ"></textarea></td>
  	</tr>
  	<tr>
  		<td align="center">保密措施详细情况</td>
  		<td colspan="6">
			<table width="90%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_box_bottom_border">
				<tr>
					<td align="center" width="60%">保密措施</td>
					<td align="center" width="40%">具体负责人</td>
				</tr>
				<tr>
					<td align="center">对场所进行安全检查</td>
					<td align="center"><input type="text" name="secret_leader" size="30"/></td>
				</tr>
				<tr>
					<td align="center">会场使用手机信号屏蔽器，并要求与会人员进入会场关闭手机</td>
					<td align="center"><input type="text" name="secret_leader" size="30"/></td>
				</tr>
				<tr>
					<td align="center">检查是否使用无线话筒及其他无线设备</td>
					<td align="center"><input type="text" name="secret_leader" size="30"/></td>
				</tr>
				<tr>
					<td align="center">检查参会人员参会证件</td>
					<td align="center"><input type="text" name="secret_leader" size="30"/></td>
				</tr>
				<tr>
					<td align="center">通知参会人员使用参会证件</td>
					<td align="center"><input type="text" name="secret_leader" size="30"/></td>
				</tr>
				<tr>
					<td align="center">通知参会人员使用保密记录本</td>
					<td align="center"><input type="text" name="secret_leader" size="30"/></td>
				</tr>
				<tr>
					<td align="center">会议文件、资料专人管理，统一编号、登记、发放、回收</td>
					<td align="center"><input type="text" name="secret_leader" size="30"/></td>
				</tr>
				<tr>
					<td align="center">会议照像、录音、录像人员</td>
					<td align="center"><input type="text" name="secret_leader" size="30"/></td>
				</tr>
				<tr>
					<td align="center">会议休息、散场期间，负责照看、清理会场人员</td>
					<td align="center"><input type="text" name="secret_leader" size="30"/></td>
				</tr>
				<tr>
					<td align="center">会议设立专门保卫人员</td>
					<td align="center"><input type="text" name="secret_leader" size="30"/></td>
				</tr>
				<tr>
					<td align="center">会议通讯录发放回收</td>
					<td align="center"><input type="text" name="secret_leader" size="30"/></td>
				</tr>
				<tr>
					<td align="center">其他</td>
					<td align="center"><input type="text" name="secret_leader" size="30"/></td>
				</tr>
			</table>
		</td>
  	</tr>
    <tr>
  		<td align="center" width="150"><font color="red">*</font>&nbsp;上传保密预案附件</td>
  		<td colspan="5">
  			<table width="95%" border="1" cellspacing="0" cellpadding="0" align="left">
	  			<tr><td colspan="2"><font color="red">注意：单个文件大小不能超过2G。若无法选择文件，请点击<a href="${ctx}/player/p.exe" style="font-style: color:blue;"> 安装Flash Player </a></font></td></tr>
	  			<tr id="uploadfile_tr">
	  				<td align="center" width="50%">
	  					<input type="button" class="button_2003" id="init_upload" value="点击加载上传控件" onclick="initUpload();"/>
			  			<input type="file" name="file_upload" id="file_upload" disabled="disabled" style="display:none"/>
			 		</td>
					<td align="center" width="50%">
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
	</form>
	 	
  	<tr>
    <td colspan="6" align="center"> 
      <input type="button" class="button_2003" value="提交" onclick="if(chk()) forms[1].submit();" id="submit_btn"/>
    </td>
  </tr>
</table>
</body>
</html>
