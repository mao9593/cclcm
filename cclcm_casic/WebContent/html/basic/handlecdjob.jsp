<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
 	<script type="text/javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
<script>
$(document).ready(function(){
	onHover();
	prepareBizApproval("${process.step_dept}","${process.step_role}","${process.step_dept_name}","${process.step_role_name}","${ctx}");
	init();
});
function init(){
	if($("#tr_approver option").size()>0){
		$("#tr_approver").show();
	}
	$("#carryuser_hid").hide();
}
function chkSubmit()
{	
	if(${jobType.jobTypeCode.contains('SEND')}){
		
		if($("#output_undertaker").val().trim() == ""){
			alert("请选择外发承办人");
			$("#output_undertaker").focus();
			return false;
		}
		if($("#output_user_name").val().trim() == ""){
			alert("请填写接收人");
			$("#output_user_name").focus();
			return false;
		}
		if($("#send_way").val().trim() == ""){
			alert("请选择外发方式");
			$("#send_way").focus();
			return false;
		}
		if($("#output_dept_name").val().trim() == ""){
			alert("请填写接收单位");
			$("#output_dept_name").focus();
			return false;
		}
		if($("#output_dept_name").val().length>100){
			alert("接收单位名字长度不能超过100个字符");
			$("#output_dept_name").focus();
			return false;
		}
		
		if($("#send_way").val().trim() == 0){
			if($("#carryout_user_names").val().trim() == ""){
				alert("请添加携带人");
				$("#carryout_user_name").focus();
				return false;
			}
		}
	}
	if(${jobType.jobTypeCode.contains('DELAY')}){
	  var delay_days = /^[0-9]+$/;
	  if($("#delay_days").val() == ""||$("#delay_days").val() == 0){
		  alert("延期留用期限不能为空或者为0");
		  $("#delay_days").focus();
		  return false;
	  }else{
            if(!delay_days.test($("#delay_days").val())){
               alert("延期留用期限应为整数");
               $("#delay_days").focus();
               return false;
              }
     	  }
    }
     if(${jobType.jobTypeCode.contains('DESTROY_CD_BYSELF')}){
    	if($("#supervise_user_iidd").val().trim() == ""){
			alert("请选择监销人");
			$("#supervise_user_iidd").focus();
			return false;
		}
		if($("#supervise_user_iidd").val().trim() == '${curUser.user_iidd}'){
			alert("监销人不能选择本人");
			$("#supervise_user_iidd").focus();
			return false;
		}
	}
	if($("#next_approver_all option").size() > 0 && $("#next_approver_sel option").size() == 0){
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
	comparedays();
	//$("#hid_form").submit();
}


function SubmitAll()
{
	if($("#next_approver_all option").size() > 0 && $("#next_approver_sel option").size() == 0){
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
     
     var url="${ctx}/basic/handlesubmitall.action";   
     callServerPostForm1(url,document.forms[0]);
}

function comparedays(){
    var url="${ctx}/basic/handlecdjob.action";   
      //callServer(url);
      callServerPostForm1(url,document.forms[0]);
    }
    
function getAjaxResult1(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			if(xmlHttp.responseText=="延期留用期限不能大于回收时间期限,请重新输入"){
			 alert("延期留用期限不能大于回收时间期限,请重新输入");
			 $("#delay_days").focus();
			}else{
			//alert("延期留用成功！");
			history.go(-1);			
			}
             return false;
		}
	}
function selectRecvUser(word){
	$("#supervise_user_iidd").val(" ");
	var url = "${ctx}/basic/getfuzzyuser.action";
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
function add_True(param1,param2){
	if(param1 == "allOption"){
		var duty_user_iidd=$("#allOption").val();
		var duty_user_name=$("#allOption option[value='"+duty_user_iidd+"']").text();
		duty_user_name=duty_user_name.substring(0,duty_user_name.indexOf("/"));
		if(duty_user_iidd != ""){
			$("#supervise_user_iidd").val(duty_user_iidd);
			$("#supervise_user_name").val(duty_user_name);
			document.getElementById("allOptions").innerHTML="";
			}
		}else{
			add_Select_True(param1,param2);
		}
}

function selectRecvUserCR(word){
    var url = "${ctx}/basic/getfuzzyuser.action?source=CR";
    if(word != ""){   	
	   callServer1(url,"fuzzy="+word);   	
    }else{
	   document.getElementById("allOptionsCR").innerHTML="";
    }
} 
function updateResult(){
	if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			if(xmlHttp.responseText.toString().length > 154){
				document.getElementById("allOptionsCR").innerHTML=xmlHttp.responseText;
			}else{
				document.getElementById("allOptionsCR").innerHTML="";
			}
		}else{
			document.getElementById("allOptionsCR").innerHTML="";
	}
}
  function addRecvUser(){
	var choose_name = $("#carryout_user_names").val() + $("#carryout_user_name").val() + ",";
	var user_iidds =  $("#carryout_user_iidds").val() + $("#carryout_user_iidd").val() + ",";
	$("#carryout_user_names").val(choose_name);
	$("#carryout_user_iidds").val(user_iidd);
}
 function add_TrueCR(){
	var carryout_user_iidd=$("#allOptionCR").val();
	var carryout_user_name=$("#allOptionCR option[value='"+carryout_user_iidd+"']").text();
	//carryout_user_name=carryout_user_name.substring(0,carryout_user_name.indexOf("/"));
	
	if(carryout_user_iidd != ""){				
		$("#carryout_user_name").val("");
		if ($("#carryout_user_iidds").val() == "" || ($("#carryout_user_iidds").val().indexOf(carryout_user_iidd) == -1 && $("#carryout_user_iidds").val().split(",").length < 3)) {
			var choose_names = $("#carryout_user_names").val() + carryout_user_name.split("/")[0] + ",";
			var user_iidds =  $("#carryout_user_iidds").val() +carryout_user_iidd + ",";
			//$("#accept_user_name").val(user_name);
			$("#carryout_user_names").val(choose_names);
			$("#carryout_user_iidds").val(user_iidds);
		}
		document.getElementById("allOptionsCR").innerHTML="";
	}
}
function add_Select_True(allOption,selectOption){
	var all = document.getElementById(allOption);
	var select = document.getElementById(selectOption);
	if (all.selectedIndex > -1){
		selected_spr_text = all.options[all.selectedIndex].text;
		selected_spr_value = all.options[all.selectedIndex].value;
		var sel_sprlen = select.options.length - 1;
		var exist_flag = 1;
		var j = 0;
		for(j = 0; j <= sel_sprlen; j++){
			if(select.options[j].value == selected_spr_value){
				exist_flag = 0;
				break;
			}
		}
		if(exist_flag){
			var temp = new Option(selected_spr_text);
			temp.value = selected_spr_value;
			select.options[++sel_sprlen] = temp;
		}
		else{
			alert("'" + selected_spr_text + "'" + "该选项已存在于右边列表中！请重新选择");
		}
	}
}
function selectSendMode(val){
   if(val=="0"){
       $("#carryuser_hid").show();
   }else{
       $("#carryuser_hid").hide();
   }
}
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
<form method="post" id="hid_form">
	<input type="hidden" name="addjob" value="Y"/>
	<input type="hidden" name="isDept" value="${isDept}"/>
	<input type="hidden" name="_chk" value="${_chk}"/>
	<input type="hidden" name="jobType" value="${jobType.jobTypeCode}"/>
	<input type="hidden" name="seclv_code" value="${seclv.seclv_code}"/>
	<input type="hidden" name="next_approver" id="next_approver"/>
	<input type="hidden" name="cd_barcodes" value="${cd_barcodes}"/>
	
	<input type="hidden" value="${hidcd_barcode}" id="hidcd_barcode" name="hidcd_barcode"/>
	<input type="hidden" value="${hidconf_code}" id="hidconf_code" name="hidconf_code"/>
	<input type="hidden" value="${hidseclv_code}" id="hidseclv_code" name="hidseclv_code"/>
	<input type="hidden" value="${hidexpire_status}" id="hidexpire_status" name="hidexpire_status"/>
	<input type="hidden" value="${hidstartTime}" id="hidstartTime" name="hidstartTime"/>
	<input type="hidden" value="${hidendTime}" id="hidendTime" name="hidendTime"/>
	<input type="hidden" value="${hidcd_state}" id="hidcd_state" name="hidcd_state"/>
	<input type="hidden" value="${hidjobType}" id="hidjobType" name="hidjobType"/>
	<input type="hidden" name="hidhandle_type" value="cd"/>
	
	<input type="hidden" value="${hidfile_list}" id="hidfile_list" name="hidfile_list"/>
	<input type="hidden" value="${hidscope_dept_id}" id="hidscope_dept_id" name="hidscope_dept_id"/>
	<input type="hidden" value="${hiddept_name}" id="hiddept_name" name="hiddept_name"/>
	<input type="hidden" value="${hiduser_name}" id="hiduser_name" name="hiduser_name"/>
	<input type="hidden" value="${hiddept_ids}" id="hiddept_ids" name="hiddept_ids"/>
	
	<input type="hidden" name="isDept" id="isDept" value="${isDept}"/>
	
	<tr>
	    <td colspan="6" class="title_box">生成审批单</td>
	</tr>
	<tr> 
    	<td width="15%" align="center">申请用户： </td>
    	<td width="35%"><font color="blue"><b>${curUser.user_name}</b></font></td>
    	<td width="15%" align="center">用户部门： </td>
    	<td width="35%"><font color="blue"><b>${curUser.dept_name}</b></font></td>
    </tr>
    <tr>
    	<td align="center">密级： </td>
    	<td><font color="blue"><b>${seclv.seclv_name}</b></font></td>
    	<td align="center">流程类型： </td>
    	<td><font color="blue"><b>${jobType.jobTypeName}</b></font></td>
	</tr>
	<c:choose>
	    <c:when test="${jobType.jobTypeCode.contains('DELAY')}">
	        <tr>
	           <td align="center"><font color="red">*</font>&nbsp;延期留用期限：</td>
		       <td colspan="3"><input type="text" name="delay_days" id="delay_days" value="${delay_days}"/>&nbsp;天</td>
	       </tr>
	    </c:when>
	</c:choose>
	<c:choose>
		<c:when test="${jobType.jobTypeCode.contains('SEND')}">
			<tr> 
		    	<td align="center"><font color="red">*</font>外发承办人： </td>
		    	<td>
		    		<select id="output_undertaker" name="output_undertaker">
				         <option value="">--请选择--</option>
				         <s:iterator value="#request.output_undertakerList" var="item">
					     <option value="${item.user_iidd}">${item.user_name}/${item.dept_name}</option>
				         </s:iterator>
			        </select>
		    	</td>
		    	<td align="center"><font color="red">*</font>接收人： </td>
		    	<td><input type="text" name="output_user_name" id="output_user_name"/></td>
			</tr>
			<tr> 
				<td align="center"><font color="red">*</font>外发方式：</td>
			     <td>
			     	<select name="send_way" id="send_way" onchange="selectSendMode(this.value)">
						<option value="">--请选择---</option>
						<option value="0">专人携带</option>
						<option value="1">发机要</option>
						<!-- <option value="1">来人接收</option> -->
					</select>
			     </td>
		    	<td align="center"><font color="red">*</font>接收单位： </td>
		    	<td><textarea rows="2" cols="45" name="output_dept_name" id="output_dept_name"></textarea></td> 
		    </tr>
			<tr id='carryuser_hid'>
				 <td align="center">携带人： </td>
				 <td>
					<input type="text" id="carryout_user_name" name="carryout_user_name" vaule="${carryout_user_name}" onkeyup="selectRecvUserCR(this.value);"/>
					<input class="button_2003" onclick="addRecvUser();" type="button" value="添加" /><br>
    		        <div id="allOptionsCR" class="containDiv" style="position:absolute;border:0px solid black;padding:0px"></div>
				 </td>
				 <td align="center"><font color="red">*</font>&nbsp;已添加的携带人：</td>
	    		 <td>
	             	<textarea readonly="true" name="carryout_user_names" id="carryout_user_names" rows="2" cols="45"></textarea>
        	        <input type="hidden"  name="carryout_user_iidds" id="carryout_user_iidds" >
        		 </td>
			</tr>
			
			<%-- <tr>  
		    	<td align="center">交接单委托打印人： </td>
		        <td>
		            <input type="text" id="supervise_user_name" name="supervise_user_name" onkeyup="selectRecvUser(this.value);" size="20"/>
		    		<input type="hidden" id="supervise_user_iidd" name="supervise_user_iidd"  value="${supervise_user_iidd}" size="10"/>
		        <br>
			    <div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
		        </div>
	            </td>
			</tr> --%>
		</c:when>
	</c:choose>
	<c:choose>
		<c:when test="${jobType.jobTypeCode.contains('DESTROY_CD_BYSELF')}">
			<tr>
			    <td align="center"><font color="red">*</font>监销人： </td>
				<td>
		    		<select id="supervise_user_iidd" name="supervise_user_iidd">
				         <option value="">--请选择--</option>
				         <s:iterator value="#request.superviseUserList" var="builder">
					     <option value="${builder.user_iidd}">${builder.user_name}</option>
				         </s:iterator>
			        </select>
		    	</td>
			</tr>
		</c:when>
	</c:choose>
	</form>
  	<tr id="tr_approver" style="display: none">
  		<td align="center" id="selApprover1">选择审批人：</td>
  		<td id="selApprover2" colspan="3">
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
					</td>
					<td>
						<SELECT size="8" multiple="true" style="WIDTH: 100px" ondblclick="del_True('next_approver_sel');" id="next_approver_sel">
						</SELECT>
					</td>
				</tr>
			</table>
		</td>
  	</tr>
  	<tr valign="middle" height="100"> 
    	<td align="center">流程信息： </td>
    	<td class="table_box_border_empty" colspan="3">
			<table class="table_box_border_empty"><tr>
				<td>
					<table>
						<tr><td align="center">
							<img alt="流程开始" border="0" src="${ctx}/_image/ico/process/prc_start.jpg" />
						</td></tr>
						<tr><td align="center">
							流程开始
						</td></tr>
					</table>
				</td>
				<td>
					<table>
						<tr><td align="center">
							<img border="0" src="${ctx}/_image/ico/process/to.gif"/>
						</td></tr>
					</table>
				</td>
				<td>
					<table>
						<tr><td align="center">
							<img alt="用户提交申请" border="0" src="${ctx}/_image/ico/process/prc_step.jpg" />
						</td></tr>
						<tr><td align="center">
							用户提交申请
						</td></tr>
					</table>
				</td>
				<td>
					<table>
						<tr><td align="center">
							<img border="0" src="${ctx}/_image/ico/process/to.gif"/>
						</td></tr>
					</table>
				</td>
				<td>
					<table>
						<tr><td align="center">
							<img border="0" src="${ctx}/_image/ico/process/prc_end.jpg" id="prc_end"/>
						</td></tr>
						<tr><td align="center">
							流程结束
						</td></tr>
					</table>
				</td>
			</tr></table>
		</td>
	</tr>
  	<tr>
	    <td colspan="4" align="center"> 
	   		<c:choose>
	    		<c:when test="${handle_type == 'submitall'}">
	    			<input type="button" class="button_2003" value="提交" onclick="this.disabled='disabled'; SubmitAll(); " id="submitall_btn"/>&nbsp;
	    		</c:when>
	    		<c:otherwise>
	    			<input type="button" class="button_2003" value="提交" onclick="chkSubmit();" id="submit_btn"/>&nbsp;
	    		</c:otherwise>
	    	</c:choose> 
	      <input class="button_2003" type="button" value="返回" onclick="javascript:history.go(-1)">&nbsp;
	    </td>
	</tr>
	<tr>
		<td colspan="6">
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	   				<td>
	   					<display:table uid="item" class="displaytable" name="cdList" form="LedgerQueryCondForm" excludedParams="*">
							<display:column title="序号">
								<c:out value="${item_rowNum}"/>
							</display:column>
							<display:column property="file_list" title="文件名" />
							<display:column property="cd_barcode" title="光盘条码" />
							<display:column property="seclv_name" title="光盘密级" />
							<display:column property="create_type_name" title="制作方式" />
							<display:column property="burn_result_name" title="刻录结果"/>
							<display:column property="cd_state_name" title="状态"/>
							<display:column property="burn_time" sortable="true" title="制作时间"/>
						</display:table>
					</td>
				</tr>
			</table>
         </td>
	</tr>
</table>
</body>
</html>
