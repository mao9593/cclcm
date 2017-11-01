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
<script>
$(document).ready(function(){
	onHover();
	disableEnterSubmit();
	preCalendarDay();
	document.getElementById("allOptions").innerHTML="";
	setSeclv("seclv_code");
	selectNextApprover();
});
	function preCalendarDay(){
		Calendar.setup({inputField: "go_time", button: "go_time_trigger", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d %H:%M:%S", cache: true, weekNumbers:true, showOthers: true, step: 1});
		Calendar.setup({inputField: "back_time", button: "back_time_trigger", singleClick: true, showsTime: true, ifFormat: "%Y-%m-%d %H:%M:%S", cache: true, weekNumbers:true, showOthers: true, step: 1});
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
	    	$("#recept_user_iidd").val(user_id);
			$("#receptionist").val(user_name);
			document.getElementById("allOptions").innerHTML="";
		}
	}

	function selectNextApprover(){
		del_all_True('next_approver_sel');
		var url = "${ctx}/basic/getnextapprover.action";
		callServerPostForm(url,document.forms[1]);
	}

	function getAjaxResult1(){
		if(xmlHttp.readyState == 4 && xmlHttp.status == 200){
			alert(xmlHttp.responseText);
			if(xmlHttp.responseText == "删除成功"){
				$("#pendingDelete").remove();
			}
		}
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
		if($("#company_info").val() == ""){
			alert("请填写来访单位");
			$("#company_info").focus();
			return false;
		}
		
		if($("#go_time").val() == ""){
			alert("请填写抵达时间");
			$("#go_time").focus();
			return false;
		}
		if($("#back_time").val() == ""){
			alert("请填写离所时间");
			$("#back_time").focus();
			return false;
		}
		if($("#receptionist").val() == ""){
			alert("请填写接待人员");
			$("#receptionist").focus();
			return false;
		}		
		if($("#exchange_info").val() == ""){
			alert("请填写业务交流范围");
			$("#exchange_info").focus();
			return false;
		}		
		var table = document.all("myTable");
		var sum_num = myTable.rows.length;
		if(sum_num < 2)
		{
			alert("请添加外宾信息！");
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
  
function addEvent(){
	var sign = true;

	if(sign){
		var med_type = $("#med_type option:selected").val();
		var med_type_name = $("#med_type option:selected").text();
		var seclv_code = $("#seclv_event option:selected").val();
		var seclv_name = $("#seclv_event option:selected").text();
		$("#device_list").after(addRowAfter(med_type,seclv_code,med_type_name,seclv_name));
		$("#seclv_code").attr("disabled",true);
	}
}

function addRowAfter(med_type,seclv_code,med_type_name,seclv_name){
	var $tr_i = $("<tr>");
	var $input_hidden = $("<input>",{
		 type:"hidden",
		 name:"hiddens",
		 value:med_type+"#"+seclv_code
	});
	var $td_type = $("<td>",{
		text:med_type_name,
		align:"center"
	});
	var $td_seclv = $("<td>",{
		text:seclv_name,
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
			if(confirm("确定要取消该借用申请？")){
				$tr_i.attr("id","pendingDelete");
				$("#pendingDelete").remove();
			}
		}
	});
	$del_font.append($del_u);
	$del_but.append($del_font);
	$td_but.append($del_but);
	$tr_i.append($td_type);
	$tr_i.append($td_seclv);
	$tr_i.append($td_but);
	$tr_i.append($input_hidden);
	return $tr_i;
}  
	var tempRow=0;//用于存放临时行
	var maxRows=0;
	function insertRows(){
     tempRow=myTable.rows.length-1;
     maxRows=tempRow;
     tempRow=tempRow+1;  
     var myRows=myTable.rows;
     var newRow=myTable.insertRow(myTable.rows.length);//插入新的一行
     var myCells=newRow.cells;
     for(i=0;i<6;i++){//每行共6列
       var newCell=myRows(newRow.rowIndex).insertCell(myCells.length);
       newCell.align="center";
       switch(i){
         case 0:
           newCell.innerHTML="<input type='text' name='v_name'>";
           break;
         case 1:
           newCell.innerHTML="<select name='v_sex'><option value='女' selected>女</option><option value='男'>男</option></select>";
           break;
         case 2:
           newCell.innerHTML="<input type='text' name='v_nation'>";
           break;
         case 3:
           newCell.innerHTML="<input type='text' name='v_post'>";
           break;
         case 4: 
           newCell.innerHTML="<input type='text' name='v_cercode'>";
           break;
         case 5:
           newCell.innerHTML="<a href='javascript:delTableRow(\""+tempRow+"\")'>删除</a>";
           break;
       }
     }
     maxRows+=1;     
     $("#visitor_num").val(maxRows);
   }
   function delTableRow(rowNum){
      if(myTable.rows.length>rowNum){
        myTable.deleteRow(rowNum);
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
<table width="90%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">	
		<form action="${ctx}/secactivity/addsecoutexchangeevent.action" method="POST">
		<input type="hidden" id="event_code" name="event_code" value="${event_code}"/>
		<input type="hidden" id="dept_id" name="dept_id" value="${curUser.dept_id}"/>
		<input type="hidden" id="jobType" name="jobType" value="${jobType}"/>
		<input type="hidden" id="next_approver" name="next_approver" />
		<input type="hidden" name="visitor_num" id="visitor_num"/>
		<input type="hidden" name="seclv_code" id="seclv_code"/>
	<tr>
	    <td colspan="6" class="title_box">与境外交流保密工作审批表</td>
	</tr>
	<tr> 
    	<td width="10%" align="center">申请用户 </td>
    	<td width="23%"><font color="blue"><b>${curUser.user_name}</b></font></td>
    	<td width="10%" align="center">用户部门 </td>
    	<td width="23%"><font color="blue"><b>${curUser.dept_name}</b></font></td>
    	<td width="10%">&nbsp;</td>
	    <td width="23%">&nbsp;</td>
	</tr>
	<tr> 
   		<td align="center"><font color="red">*</font>&nbsp;来访单位</td>
		<td>
			<input type="text" name="company_info" id="company_info"/>
		</td>		   
		<td align="center"><font color="red">*</font>&nbsp;抵达时间</td>
		<td >
	 		<input type="text" name="go_time" readonly="readonly" style="cursor:hand;" value="${go_time}"/>
   			<img src="${ctx}/_image/time2.jpg" id="go_time_trigger">
	 	</td>

		<td align="center"><font color="red">*</font>&nbsp;离所时间</td>
		<td>
	 		<input type="text" name="back_time" readonly="readonly" style="cursor:hand;" value="${back_time}"/>
   			<img src="${ctx}/_image/time2.jpg" id="back_time_trigger">
	 	</td>
	</tr> 
	<tr>
		<td colspan="6" align="left">
			<font color="red">*</font>&nbsp; <input class="button_2003" type="button" value="添加外宾基本信息" onClick="insertRows()">
		</td>
	</tr>
	<tr>
		<td colspan="6" align="center">
		<table border rules=all width="100%" border="0" cellspacing="0" cellpadding="0" align="center" class="table_box_bottom_border" id="myTable">
		<td width="15%" align="center">姓 名</td>
		<td width="10%" align="center">性 别</td>
		<td width="15%" align="center">国 籍</td>
		<td width="15%" align="center">职 务</td>
		<td width="15%" align="center">证件号码</td>
		<td width="5%" align="center">删除</td>
		</table>
		</td>
	</tr>
	<tr>
		<td align="center">安全保密防范内容</td>
		<td colspan="5">
			<table width="95%" border="0" cellspacing="0" cellpadding="0" align="left">
			<tr>
			   	<td width="15%" align="center"><font color="red">*</font>&nbsp;接待人员</td>
				<td width="40%">
		    	    <input size="40" type="text" id="receptionist" name="receptionist" onkeyup="selectRecvUser(this.value);"/>
		    		<input type="hidden" id="recept_user_iidd" name="recept_user_iidd"/><br>
		    		<div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
		    		</div>
			 	</td>
			 	<td width="15%">&nbsp;</td>
			 	<td width="15%">&nbsp;</td>
			</tr>	
			<tr>
				<td align="center"><font color="red">*</font>&nbsp;业务交流范围</td>
				<td><textarea name="exchange_info" rows="3" cols="40" id="exchange_info"></textarea></td>
				<td width="15%">&nbsp;</td>
			 	<td width="15%">&nbsp;</td>
			</tr>	
			<tr>
				<td align="center">接待地点</td>
				<td><textarea name="reception" rows="3" cols="40" id="reception"></textarea></td>
				<td align="center">是否涉密军工<br>管理研发场所</td>
				<td align="center">
					<input type="radio" name="reception_sec" value="1"/>是
					<input type="radio" name="reception_sec" value="0"/>否
				</td>
			</tr>
			<tr>
				<td align="center">拟参观地点</td>
				<td><textarea name="visite_place" rows="3" cols="40" id="visite_place"></textarea></td>
				<td align="center">是否涉密军工<br>管理研发场所</td>
				<td align="center">
					<input type="radio" name="visite_sec" value="1"/>是
					<input type="radio" name="visite_sec" value="0"/>否
				</td>				
			</tr>
			<tr>
				<td align="center">提供资料</td>
				<td><textarea name="material" rows="3" cols="40" id="material"></textarea></td>
				<td align="center">是否包含涉密内容</td>
				<td align="center">
					<input type="radio" name="material_sec" value="1"/>是
					<input type="radio" name="material_sec" value="0"/>否
				</td>					
			</tr>	
		</table>
	</tr>	
	<tr>
  		<td align="center">&nbsp;备注</td>
		<td colspan="5"><textarea name="summ" rows="3" cols="80" id="summ"></textarea></td>
  	</tr>
    <tr>
  		<td align="center" width="150">上传文件</td>
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
