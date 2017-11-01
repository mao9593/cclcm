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
		<script language="javascript" src="${ctx}/_script/casic304_common_bm.js"></script>
 	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>	
 	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
 	<script language="JavaScript" src="${ctx}/_script/common.js"></script>	
 	<script type="text/javascript" src="${ctx}/uploadify/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx}/uploadify/jquery.uploadify.min.js"></script>	
<script>
$(document).ready(function(){
		onHover();
		disableEnterSubmit();
});
	
	function selectSeclv(seclv){
		if(seclv == ""){
			alert("请选择作业密级,以确认审批流程");
			$("#seclv_code").focus();
			return false;
		}else{
			selectNextApprover();
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
		var info="";
	var fileName;
	var fileSeclv = 0;
	var jobSeclv = $("#seclv_med").val();
	var sign = true;
	$("tr").filter(".files_info").each(function(){
		fileName = $(this).children("td").eq(0).text();
		fileSeclv = $(this).children("td").eq(1).find("select").val();
		if(fileSeclv == undefined || fileSeclv ==""){
			var fileSeclvName = $(this).children("td").eq(1).text();
			$("#seclv_med option").each(function(){
				if($(this).text() == fileSeclvName){
					fileSeclv = this.value;
				}
			});
			if(fileSeclv == undefined){
				alert("该文件密级没有在系统中定义，请联系管理员。");
			}
		}
		
		if(fileSeclv == 0){
//			alert("请选择文件密级");
			$(this).children("td").eq(1).find("select").focus();
			sign = false;
		}else{
			var fileLevel = false;
			$("#seclv_med :selected").nextAll().each(function(){
				if(fileSeclv == this.value){
					fileLevel = true;
				}
			});
//			alert($("#seclv_med").val() + "  " + fileSeclv);
			if($("#seclv_med").val() == fileSeclv){
				fileLevel = true;
			}
			if(!fileLevel){
				sign = fileLevel;
			}			
		}
		info += fileName+" __ "+ fileSeclv + "::";
//		alert(info);
	});
	if($("#seclv_med").val() == ""){
		alert("请选择作业密级");
		$("#seclv_med").focus();
		return false;
	}
	
	if($("#seclv_code").val().trim() == ""){
			alert("请选择资料最高密级");
			$("#seclv_code").focus();
			return false;
		}
       /*  if("${type}" == "report"){
		if($("#report_style").val().trim() == ""){
			alert("请选择去向");
			$("#report_style").focus();
			return false;
		}
		} */
		
		if("${type}" != "report"){
		if($("#report_name").val().trim() == ""){
			alert("请填写报道名称");
			$("#report_name").focus();
			return false;
		}	
		}
	    if("${type}" != "report"){
		if($("#report_name").val().length>0 && !checkCode_addword($("#report_name").val())){
			$("#report_name").focus();
			alert("报道名称只能由数字、字母、汉字或下划线组成，长度小于30位");
			return false;
		}
		}
		if($("#next_approver_all option").size() > 0 && $("#next_approver_sel option").size() == 0){
			alert("请选择审批人员");
			$("#next_approver_all").focus();
			return false;
		}
/* 		if(!sign){
		   alert("上传文件的密级不可高于作业密级");
		   return false;
	} */
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
	$("#seclv_med").removeAttr("disabled");
	$('#file_upload').uploadify('upload','*');
}

function addRowBefore(fileName){
	var $tr_i = $("<tr class=\"files_info\">");
	var $td_file = $("<td>",{
		align:"center"
	});
	
	var $td_seclv = '<td align="center">密级：<select name="file_seclevel"> \
				<option value="">--请选择--</option> \
				<option value="机密">机密</option> \
				<option value="秘密">秘密</option> \
				<option value="内部">内部</option>\
				<option value="非密">非密</option> </select>';
	
	if("${type}" != "interpubl"){
	var $td_pic = '<td align="center">是否有图片：<select name="file_seclevel"> \
				<option value="">--请选择--</option> \
				<option value="有图片">是</option> \
				<option value="无图片">否</option> </select>';
	}

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
				$("#type").val("REPORT");
				$tr_i.attr("id","pendingDelete");
				var form = document.getElementById("hiddenDelFileForm");
				callServerPostForm1("${ctx}/publicity/deluploadedfile.action",form);
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
	$tr_i.append($td_seclv);
	$tr_i.append($td_pic);
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
		'uploader' : '${ctx}/uploadreportfile', // 数据处理url
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
                      	$("#seclv_med").attr("disabled",false);
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
            $("#hid_seclv_code").val($("#seclv_med").val());
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

</script>
</head>
<body oncontextmenu="self.event.returnValue=false"> 
<form action="${ctx}/publicity/deluploadedfile.action" method="POST" id="hiddenDelFileForm">
  	<input type="hidden" name="event_code" value="${event_code}"/> 
  	<input type="hidden" name="file_name" id="del_file_name"/>
  	<input type="hidden" name="file_type" id="file_type"/>
  	<input type="hidden" name="type" id="type"/>
        <input type="hidden" name="seclv_code" id="del_sec_code"/> 
</form>

<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">	
		<form action="${ctx}/publicity/addreportevent.action" method="POST">
		<input type="hidden" id="event_code" name="event_code" value="${event_code}"/>
		<input type="hidden" id="dept_id" name="dept_id" value="${curUser.dept_id}"/>
		<input type="hidden" id="jobType" name="jobType" value="${jobType}"/>
		<input type="hidden" id="next_approver" name="next_approver" />
		<input type="hidden" id="type" name="type" value="${type}"/>
		
		

	<tr>
	<c:if test="${type == 'report'}">
	    <td colspan="6" class="title_box">宣传报道保密审查审批表</td>
	</c:if>
	<c:if test="${type == 'deptreport'}">
		<td colspan="6" class="title_box">部门投稿保密审查审批表</td>
	</c:if>  
	<c:if test="${type == 'intrapubl'}">
		<td colspan="6" class="title_box">内网信息发布保密审查审批表</td>
	</c:if>
	<c:if test="${type == 'interpubl'}">
		<td colspan="6" class="title_box">外网信息发布保密审查审批表</td>
	</c:if>     
	</tr>
	<tr> 
    	<td width="10%" align="center">申请用户 </td>
    	<td width="23%"><font color="blue"><b>${curUser.user_name}</b></font></td>
    	<td width="10%" align="center">申请人部门 </td>
    	<td width="23%"><font color="blue"><b>${curUser.dept_name}</b></font></td>
    	<td width="10%" align="center"><font color="red">*</font>&nbsp;资料最高密级</td>
	    <td width="23%">
			<select id="seclv_code" name="seclv_code" onchange="selectSeclv(this.value);">
				<option value="">--请选择--</option>
				<s:iterator value="#request.seclvList" var="seclv">
					<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
				</s:iterator>
			</select>
		</td>
	</tr>
	
	
	<tr>
		<td align="center"><font color="red">*</font>&nbsp;联系电话</td>
		  <td>
			<input type="text" name="user_phone" id="user_phone"/>
		  </td>
		<c:if test= "${type != 'report'}"> 	 
		<td align="center"><font color="red">*</font>&nbsp;发布信息标题</td>
		  <td>
			<input type="text" name="report_name"id="report_name"/>
	    </td>
	    </c:if>
			
		<c:if test= "${type == 'report'}"> 	
		<td align="center">去向</td>
		<td colspan="3">	
	        <input type="checkbox" name="report_style" value="1"/>所内涉密网
	        <input type="checkbox" name="report_style" value="2"/>集团科研网
	         <input type="checkbox" name="report_style" value="3"/>信息报送
	        <input type="checkbox" name="report_style" value="4"/>对外宣传网
	        <input type="checkbox" name="report_style" value="5"/>电科重庆
	        <input type="checkbox" name="report_style" value="6"/>集团公司《中国电科报》
	        <br></br><input type="checkbox" name="report_style" value="7"/> 上级机关或部门	
	        <input type="checkbox" name="report_style" value="8"/> 其他&nbsp;
			<input type="text" name="other_style" id="other_style"/>
	        	    
		</td>
		<!-- <td align="center"><font color="red">*</font>&nbsp;去向 </td>
		  <td>
			<select name="report_style" id="report_style">
				<option value="">--请选择--</option>
				<option value="1">涉密网</option>
				<option value="2">对外宣传网</option>
				<option value="3">集团科研网</option>
				<option value="4">电科重庆</option>
				<option value="5">集团公司《中国电科技》</option>
				<option value="6">上级机关或部门</option>
				<option value="7">其他</option>
			</select>
		</td>  -->
		</c:if>
		<c:if test="${type !='report'}">
		    <td colspan="2">&nbsp;</td>
       </c:if> 
	  </tr>
     <!--  <tr>
  		<td align="center">&nbsp;资料内容</td>
		<td colspan="5"><textarea name="report_description" rows="3" cols="100" id="report_description"></textarea></td>
  	   </tr>     -->
   		<%-- <td align="center"><font color="red">*</font>&nbsp;申请类型 </td>
   		<td>
			<select name="apply_type" id="apply_type">
				<option value="">--请选择--</option>
				<option value="1">部门投稿</option>
				<option value="2">宣传报道</option>
				<option value="3">内网信息发布</option>
				<option value="4">外网信息发布</option>
			</select>--%>
	<tr>
  		<td align="center">&nbsp;备注</td>
		<td colspan="5"><textarea name="summ" rows="3" cols="120" id="summ"></textarea></td>
  	</tr>
 <c:if test= "${type != 'interpubl'}"> 	
  	<tr>
  		<td align="center"><font color="red">*</font>&nbsp;上传文件</td>
  		<td colspan="5">
  			<table width="90%" border="1" cellspacing="0" cellpadding="0" align="left">
  			<tr><td colspan="4"><font color="red">注意：单个文件大小不能超过2G。文件名称字数总和不要超过500，若文件过多，请打包上传。若无法选择文件，请点击<a href="${ctx}/player/p.exe" style="font-style: color:blue;"> 安装Flash Player </a></font></td></tr>
  			<tr id="uploadfile_tr">
  				<td align="center" width="250" >
  					<input type="button" class="button_2003" id="init_upload" value="点击加载上传控件" onclick="initUpload();"/>
		  			<input type="file" name="file_upload" id="file_upload" disabled="disabled" style="display:none"/>
		  			<!--  div id="file_upload" /-->
		 		</td>
		 		 <td  align="center" width="250" >
  					&nbsp;&nbsp;&nbsp;&nbsp;
					 文件密级
				</td>
				<td align="center" width="250" >
  					&nbsp;&nbsp;&nbsp;&nbsp;
					 是否含有图片
				</td>
		 		<td align="center" width="250">
					<input type="button" onclick="addFile();" value="上传" class="button_2003"/>&nbsp;
		  			<input type="button" onclick="$('#file_upload').uploadify('stop')" value="取消" class="button_2003"/>&nbsp;
		  		</td>
		   	</tr>
		 	<tr><td colspan="4"><div id="uploader_queue" style="width: 750px;height: 30px"></div></td></tr>
		 	</table>
  		</td>
  	</tr>
  	</c:if>
  	
  	 <c:if test= "${type == 'interpubl'}"> 	
  	<tr>
  		<td align="center"><font color="red">*</font>&nbsp;上传文件</td>
  		<td colspan="5">
  			<table width="90%" border="1" cellspacing="0" cellpadding="0" align="left">
  			<tr><td colspan="4"><font color="red">注意：单个文件大小不能超过2G。文件名称字数总和不要超过500，若文件过多，请打包上传。若无法选择文件，请点击<a href="${ctx}/player/p.exe" style="font-style: color:blue;"> 安装Flash Player </a></font></td></tr>
  			<tr id="uploadfile_tr">
  				<td align="center" width="250" >
  					<input type="button" class="button_2003" id="init_upload" value="点击加载上传控件" onclick="initUpload();"/>
		  			<input type="file" name="file_upload" id="file_upload" disabled="disabled" style="display:none"/>
		  			<!--  div id="file_upload" /-->
		 		</td>
		 		 <td  align="center" width="250" >
  					&nbsp;&nbsp;&nbsp;&nbsp;
					 文件密级
				</td>
				<!-- <td align="center" width="250" >
  					&nbsp;&nbsp;&nbsp;&nbsp;
					 是否含有图片
				</td> -->
		 		<td align="center" width="250">
					<input type="button" onclick="addFile();" value="上传" class="button_2003"/>&nbsp;
		  			<input type="button" onclick="$('#file_upload').uploadify('stop')" value="取消" class="button_2003"/>&nbsp;
		  		</td>
		   	</tr>
		 	<tr><td colspan="4"><div id="uploader_queue" style="width: 750px;height: 30px"></div></td></tr>
		 	</table>
  		</td>
  	</tr>
  	</c:if>
  <%--   <tr>
  		<td align="center" width="150"><font color="red">*</font>&nbsp;上传文件</td>
  		<td colspan="5">
  			<table width="95%" border="1" cellspacing="0" cellpadding="0" align="left">
	  			<tr><td colspan="2"><font color="red">注意：单个文件大小不能超过2G。若无法选择文件，请点击<a href="${ctx}/player/p.exe" style="font-style: color:blue;"> 安装Flash Player </a></font></td></tr>
	  			<tr id="uploadfile_tr">
	  				<td align="center" width="50%">
	  					
	  					<input type="button" class="button_2003" id="init_upload" value="点击加载上传控件" onclick="initUpload();"/>
			  			<!-- <input type="file" name="file_upload" id="file_upload" disabled="disabled" style="display:none"/> -->
			  			<input type="button" onclick="$('#file_upload').uploadify('stop')" value="取消" class="button_2003"/>&nbsp;
			 		</td>
					<td align="center" width="50%">
						<input type="button" onclick="addFile();" value="上传" class="button_2003"/>&nbsp;	
			  		</td>
						<!--  div id="file_upload" /-->
		 		</td>
		 		<td align="center" width="250">
  					&nbsp;&nbsp;&nbsp;&nbsp;
					 文件密级
				</td><!-- 
				<td align="center" width="250">
					<input type="button" onclick="addFile();" value="上传" class="button_2003"/>&nbsp;
		  			<input type="button" onclick="$('#file_upload').uploadify('stop')" value="取消" class="button_2003"/>&nbsp;
		  		</td> -->	
			 	</tr>
				<tr><td colspan="2"><div id="uploader_queue" style="width: 750px;height: 30px"></div></td></tr>					 	
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
	</form>
	 	
  	<tr>
    <td colspan="6" align="center"> 
      <input type="button" class="button_2003" value="提交" onclick="if(chk()) forms[1].submit();" id="submit_btn"/>
    </td>
  </tr>
</table>
</body>
</html>
