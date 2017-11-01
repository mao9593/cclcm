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

$(function(){
    var output_dept_count_point = $("#output_dept_count_point");
    var minOldCount = 0;
    $(".oldPageNum").each(function(){
        minOldCount += parseInt($(this).html());
    });
    var strTemp = output_dept_count_point.text() ;
    output_dept_count_point.text(strTemp + minOldCount)  ;
});

function init(){
	if($("#tr_approver option").size()>0){
		$("#tr_approver").show();
	}
	$("#carryuser_hid").hide();
    //$("#carryuser_hid2").hide();
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
	}else if(${jobType.jobTypeCode.contains('DELAY')}){
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
    }else if(${jobType.jobTypeCode.contains('PAPER_DEL')}){
    	if($("#reason").val().trim() == ""){
			alert("请填写删除原因");
			$("#reason").focus();
			return false;
		}
    }else if(${jobType.jobTypeCode.contains('PAPER_MODIFY')}){
    	if($("#reason").val().trim() == ""){
			alert("请填写修改原因");
			$("#reason").focus();
			return false;
		}
		var pages = /^[0-9]+$/;
		  if($("#output_dept_name").val() == ""||$("#output_dept_name").val() == 0){
			  alert("台账修改页数不能为空或者为0");
			  $("#output_dept_name").focus();
			  return false;
		  }else{
            if(!pages.test($("#output_dept_name").val())){
               alert("台账修改页数应为整数");
               $("#output_dept_name").focus();
               return false;
              }
     	  }
    }else if(${jobType.jobTypeCode.contains('DESTROY_PAPER_BYSELF')}){
    	if($("#supervise_user_iidd").val().trim() == ""){
			alert("请填写监销人");
			$("#supervise_user_iidd").focus();
			return false;
		}
		if($("#supervise_user_iidd").val().trim() == '${curUser.user_iidd}'){
			alert("监销人不能填写本人");
			$("#supervise_user_iidd").focus();
			return false;
		}
    }else if(${jobType.jobTypeCode.contains('MERGE_ENTITY')}){
    	if($("#output_user_name").val().trim() == "" || $("#output_user_name").val().trim() == null){
	            alert("请填写合并新台账文件名");
	            $("#output_user_name").focus();
	            return false;
	    }else{
	    	if($("#output_user_name").val().length > 100){
	    		alert("合并新台账文件名超长，请重新输入小于100字符名称");
	            $("#output_user_name").focus();
	            return false;
	    	}
	    }
	    if($("#output_dept_count").val().trim() == "" || $("#output_user_name").val().trim() == null){
	            alert("请填写合并新台账页数");
	            $("#output_dept_count").focus();
	            return false;
	    }else {
	        var reg = /^[0-9]*[1-9][0-9]*$/ ;
	        if(!reg.test($("#output_dept_count").val().trim())){
	            alert("请在合并新台账页数输入正整数值");
	            $("#output_dept_count").focus();
	            return false;
	        }
	        var minOldCount = 0;
	        $(".oldPageNum").each(function(){
	            minOldCount += parseInt($(this).html());
	        });
	        var new_count = parseInt($("#output_dept_count").val().trim()) ;
	        if(new_count < minOldCount){
	            alert("合并新台账页数不得小于原分台账总页数和!");
	            $("#output_dept_count").focus();
	            return false;
	        }
	    }
	    if($("#output_reason").val().trim() == "" || $("#output_reason").val().trim() == null){
	            alert("请填写具体说明");
	            $("#output_reason").focus();
	            return false;
	    }else{
	    	if($("#output_reason").val().length > 200){
	    		alert("具体说明字数超长，请重新输入小于200的字符");
	            $("#output_reason").focus();
	            return false;
	    	}
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
//alert("isdept:"+$("#isDept").val());
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
    var url="${ctx}/basic/handlepaperjob.action";   
      //callServer(url);
      callServerPostForm1(url,document.forms[0]);
    }
    
   function getAjaxResult1(){
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			if(xmlHttp.responseText=="延期留用期限不能大于回收时间期限,请重新输入"){
			 alert("延期留用期限不能大于回收时间期限,请重新输入");
			 $("#delay_days").focus();
			}else{
			history.go(-1);			
			}
             return false;
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
       //$("#carryuser_hid2").show();
   }else{
        $("#carryuser_hid").hide();
        //$("#carryuser_hid2").hide();
   }
}
</script>
</head>
<body oncontextmenu="self.event.returnValue=true">
<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">
<form method="post" id="hid_form">
	<input type="hidden" name="addjob" value="Y"/>
	<input type="hidden" name="isDept" value="${isDept}"/>
	<input type="hidden" name="_chk" value="${_chk}"/>
	<input type="hidden" name="jobType" value="${jobType.jobTypeCode}"/>
	<input type="hidden" name="seclv_code" value="${seclv.seclv_code}"/>
	<input type="hidden" name="next_approver" id="next_approver"/>
	<input type="hidden" name="paper_barcodes" value="${paper_barcodes}"/>
	
	<input type="hidden" value="${hidfile_title}"" id="hidfile_title" name="hidfile_title"/>
	<input type="hidden" value="${hidpaper_barcode}" id="hidpaper_barcode" name="hidpaper_barcode"/>
	<input type="hidden" value="${hidkeyword_content}" id="hidkeyword_content" name="hidkeyword_content"/>
	<input type="hidden" value="${hidpaper_state}" id="hidpaper_state" name="hidpaper_state"/>
	<input type="hidden" value="${hidseclv_code}" id="hidseclv_code" name="hidseclv_code"/>
	<input type="hidden" value="${hidstartTime}" id="hidstartTime" name="hidstartTime"/>
	<input type="hidden" value="${hidendTime}" id="hidendTime" name="hidendTime"/>
	<input type="hidden" value="${hidjobType}" id="hidjobType" name="hidjobType"/>
	<input type="hidden" value="${hidexpire_status}" id="hidexpire_status" name="hidexpire_status"/>
	<input type="hidden" name="hidhandle_type" value="paper"/>
	
	<input type="hidden" value="${hidscope_dept_id}" id="hidscope_dept_id" name="hidscope_dept_id"/>
	<input type="hidden" value="${hiddept_name}" id="hiddept_name" name="hiddept_name"/>
	<input type="hidden" value="${hiduser_name}" id="hiduser_name" name="hiduser_name"/>
	<input type="hidden" value="${hiddept_ids}" id="hiddept_ids" name="hiddept_ids"/>
	<input type="hidden" name="isDept" id="isDept" value="${isDept}"/>
	<input type="hidden" name="replacePageFlag" id="replacePageFlag" value="${replacePageFlag}"/>
	<input type="hidden" name="retrieve_pagenum" id="retrieve_pagenum" value="${retrieve_pagenum}"/>
	<input type="hidden" name="fail_remark" id="fail_remark" value="${fail_remark}"/>
	<input type="hidden" name="paper_id" id="paper_id" value="${paper_id}"/>
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
			   <!--  <td align="center"><font color="red">*</font>载体用途： </td>
		    	<td><input type="text" name="ledger_use" id="ledger_use"/></td> -->		   				     
			     <td align="center">携带人： </td>
				 <td>
					<input type="text" id="carryout_user_name" name="carryout_user_name" vaule="${carryout_user_name}" onkeyup="selectRecvUserCR(this.value);"/><!--  -->
					<input class="button_2003" onclick="addRecvUser();" type="button" value="添加" /><br>
    		        <div id="allOptionsCR" class="containDiv" style="position:absolute;border:0px solid black;padding:0px"></div>
				 </td>
				 <td align="center"><font color="red">*</font>&nbsp;已添加的携带人：</td>
	    		 <td>
	             	<textarea readonly="true" name="carryout_user_names" id="carryout_user_names" rows="2" cols="45"></textarea>
        	        <input type="hidden"  name="carryout_user_iidds" id="carryout_user_iidds" >
        		 </td>
			</tr>
			<!-- 中物五所屏蔽交接单委托打印人填写框
			<tr>  
		    	<td align="center">交接单委托打印人： </td>
		        <td>
		            <input type="text" id="supervise_user_name" name="supervise_user_name" onkeyup="selectRecvUser(this.value);" size="20"/>
		    		<input type="hidden" id="supervise_user_iidd" name="supervise_user_iidd"  value="${supervise_user_iidd}" size="10"/>
		        <br>
			    <div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
		        </div>
	            </td>
			</tr> -->
		</c:when>
		<c:when test="${jobType.jobTypeCode.contains('PAPER_DEL')}">
			<tr>
			    <td align="center"><font color="red">*</font>载体台账删除原因： </td>
			    <td colspan="3"><textarea rows="3" cols="80" name="reason" id="reason"></textarea></td>
			</tr>
		</c:when>
		<c:when test="${jobType.jobTypeCode.contains('PAPER_MODIFY')}">
			<tr>
			    <td align="center"><font color="red">*</font>载体台账修改原因： </td>
			    <td><textarea rows="3" cols="40" name="reason" id="reason"></textarea></td>
			    <td align="center"><font color="red">*</font>文件台账修改页数： </td>
			    <td><input type="text" name="output_dept_name" id="output_dept_name"/></td>
			</tr>
		</c:when>
		<c:when test="${jobType.jobTypeCode.contains('DESTROY_PAPER_BYSELF')}">
			<tr>
			    <td align="center"><font color="red">*</font>监销人： </td>
				<td colspan="3">
		    		<select id="supervise_user_iidd" name="supervise_user_iidd">
				         <option value="">--请选择--</option>
				         <s:iterator value="#request.superviseUserList" var="builder">
					     <option value="${builder.user_iidd}">${builder.user_name}</option>
				         </s:iterator>
			        </select>
		    	</td>
			</tr>
		</c:when>
		<c:when test="${jobType.jobTypeCode.contains('MERGE_ENTITY')}">
			<tr>
			    <td align="center"><font color="red">*</font>合并新台账文件名： </td>
				<td><input type="text" name="output_user_name" id="output_user_name"></td>
				<td align="center"><font color="red">*</font>合并新台账页数： </td>
				<td><input type="text" name="output_dept_name" id="output_dept_count" value="${page_all}">
				<br><font color="orange"><b id = "output_dept_count_point">新页数不低于原分台账总页数和:</b></font>
				</td>
			</tr>
			<tr>
				<td align="center"><font color="red">*</font>具体说明： </td>
				<td colspan="3"><textarea rows="3" cols="40" name="reason" id="output_reason"></textarea></td>
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
	    			<input type="button" class="button_2003" value="提交" onclick="chkSubmit(); " id="submit_btn"/>&nbsp;
	    		</c:otherwise>
	    	</c:choose> 
	      <input class="button_2003" type="button" value="返回" onclick="javascript:history.go(-1)">&nbsp;
	    </td>
	</tr>
	<tr>		
		<td colspan="6">
			<table class="displaytable-outter" cellspacing=0 cellpadding=0 border=0 width="100%">
	 			<tr>
	 				<c:choose>
						<c:when test="${time_flag == 'USETIME'}">								
	   						<td>
	   							<display:table uid="item" class="displaytable" name="paperList" form="LedgerQueryCondForm" excludedParams="*">
									<display:column title="序号">
										<c:out value="${item_rowNum}"/>
									</display:column>
									<display:column property="file_title" title="文件名" />
									<display:column property="paper_barcode" title="文件条码" />
									<display:column property="seclv_name" title="文件密级" />
									<display:column property="create_type_name" title="制作方式" />
									<display:column property="print_result_name" title="打印结果"/>
									<display:column property="paper_state_name" title="状态"/>
									<display:column property="page_count" class = "oldPageNum" title="文件页数"/>
									<display:column property="print_time"  title="启用时间"/>																				
								</display:table>
							</td>
						</c:when>
						<c:otherwise>
							<td>
	   							<display:table uid="item" class="displaytable" name="paperList" form="LedgerQueryCondForm" excludedParams="*">
									<display:column title="序号">
										<c:out value="${item_rowNum}"/>
									</display:column>
									<display:column property="file_title" title="文件名" />
									<display:column property="paper_barcode" title="文件条码" />
									<display:column property="seclv_name" title="文件密级" />
									<display:column property="create_type_name" title="制作方式" />
									<display:column property="print_result_name" title="打印结果"/>
									<display:column property="paper_state_name" title="状态"/>
									<display:column property="page_count" class = "oldPageNum" title="文件页数"/>
									<display:column property="print_time"  title="启用时间"/>																				
								</display:table>
							</td>
						</c:otherwise>
					</c:choose>	
				</tr>
			</table>
         </td>
	</tr>	
</table>
</body>
</html>