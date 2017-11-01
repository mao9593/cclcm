<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>笔记本变更</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
	<link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
    <script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common_bm.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script type="text/javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
 	<script type="text/javascript" src="${ctx}/uploadify/jquery.uploadify.min.js"></script>
	<script  language="JavaScript" >
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
			$("#duty_user_id").val(user_id);
			$("#duty_user_name").val(user_name);
			document.getElementById("allOptions").innerHTML="";
		}
	}

	function selectNextApprover(){	
		del_all_True('next_approver_sel');
		var url = "${ctx}/basic/getnextapprover.action";
		callServerPostForm1(url,document.forms[0]);
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
	
	function chk()
	{
		if($("#user_phone").val().trim() == ""){
				alert("请填申请人电话");
				$("#user_phone").focus();
				return false;
			}
		if($(":checkbox:checked[value!='']").size()==0){
			alert("请先勾选变更项");
			return false;
		}
		if($(":checkbox[id='after_dept'][checked]").size() > 0){
		    if($("#duty_dept_id").val().trim() == ""){
		    	alert("请选择变更后部门");
			    $("#duty_dept_name").focus();
			    return false;
		    }
		}	
		if($(":checkbox[id='after_name'][checked]").size() > 0){
		    if($("#duty_user_id").val().trim() == ""){
		    	alert("请填写变更后责任人");
				$("#duty_user_name").focus();
				return false;
		    }
		}
		if($(":checkbox[id='after_selv'][checked]").size() > 0){
		    if($("#com_seclv_code").val().trim() == ""){
			    alert("请选择变更后密级");
				$("#com_seclv_code").focus();
				return false;
		    }	
		}
		if($(":checkbox[id='after_location'][checked]").size() > 0){
		    if($("#storage_location").val().trim() == ""){
			    alert("请填写变更后存放位置");
				$("#storage_location").focus();
				return false;
		    }	
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
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<form method="post" action="${ctx}/computer/addchangebookevent.action">
	<input type="hidden" name="event_code" value="${event_code}" id="event_code"/>
	<input type="hidden" name="book_barcode" value="${book.book_barcode}" id="book_barcode"/>
	<input type="hidden" name="dept_id" id="dept_id" value="${curUser.dept_id}"/>
	<input type="hidden" name="jobType" id="jobType" value="${jobType}"/>
	<input type="hidden" name="seclv_code" id="seclv_code"/>
	<input type="hidden" name="next_approver" id="next_approver"/>
  	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="90%">
	  	<tr>
		    <td colspan="6" class="title_box">笔记本变更申请</td>
		</tr>
		<tr height="40"> 
	    	<td width="15%" align="center">申请用户 </td>
	    	<td width="23%"><font color="blue"><b>${curUser.user_name}</b></font></td>
	    	<td width="10%" align="center">用户部门 </td>
	    	<td width="23%"><font color="blue"><b>${curUser.dept_name}</b></font></td>
	    	<td align="center"><font color="red">*</font>申请人电话</td>
			<td><input type="text" name="user_phone" id="user_phone" /></td>
	     </tr>
	     <tr>
	     	<td align="center">名称型号</td>
			<td ><font color="blue"><b>&nbsp;${book.book_model}</td>
			<td align="center">统一编号 </td>
    		<td ><font color="blue"><b>&nbsp;${book.book_code}</b></font></td>
    		<td align="center">保密编号</td>
			<td ><font color="blue"><b>&nbsp;${book.conf_code}</td>
	     </tr>
	     <tr>
			<td align="center">使用单位</td>
			<td ><font color="blue"><b>&nbsp;${book.duty_entp}</td>
			<td align="center">原保密编号 </td>
    		<td ><font color="blue"><b>&nbsp;${book.oldconf_code}</b></font></td>
			<td align="center">设备密级 </td>
    		<td ><font color="blue"><b>&nbsp;${book.seclv_name}</b></font></td>
		</tr>
		<tr>
			<td align="center">硬盘序列号 </td>
    		<td ><font color="blue"><b>&nbsp;${book.hdisk_no}</b></font></td>
			<td align="center">安装地点</td>
    		<td ><font color="blue"><b>&nbsp;${book.storage_location}</b></font></td>
    		<td align="center">笔记本状态</td>
    		<td><font color="blue"><b>&nbsp;${book.book_status_name}</b></font></td>
		</tr>
		<tr>
		    <td align="center"><font color="blue"><b>变更类型</td>
		    <td align="center" colspan="2"><font color="blue"><b>变更前</td>
		    <td align="center" colspan="3"><font color="blue"><b>变更后</td>
		</tr>
		<tr>
			<td align="center"><input type="checkbox" name="_chk" id="after_dept" value="dept"/>部门</td>
			<td align="center" colspan="2">&nbsp;${book.duty_dept_name}</td>
			<td align="center" colspan="3"> 
				<input type="text" id="duty_dept_name" name="duty_dept_name" readonly="readonly" style="cursor:hand" onclick="openDeptSelect('duty_dept_name','duty_dept_id','radio')"/>
    			<input type="hidden" id="duty_dept_id" name="duty_dept_id"/><br>
    		</td>
    	</tr>
    	<tr>
    		<td align="center"><input type="checkbox" name="_chk" id="after_name" value="name"/>责任人</td>
    		<td align="center" colspan="2">&nbsp;${book.duty_user_name}</td>
			<td align="center" colspan="3">
				<input type="text" id="duty_user_name" name="duty_user_name" onkeyup="selectRecvUser(this.value);"/>
		    	<input type="hidden" id="duty_user_id" name="duty_user_id"/><br>
		    	<div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
			</td>
		</tr>
		<tr>
			<td align="center"><input type="checkbox" name="_chk" id="after_selv" value="selv"/>密级</td>
			<td align="center" colspan="2">&nbsp;${book.seclv_name}</td>
			<td align="center" colspan="3">
				<select name="com_seclv_code" id="com_seclv_code">
					<option value="">--请选择--</option>
					<s:iterator value="#request.seclvList" var="seclv">
						<option value="${seclv.seclv_code}">${seclv.seclv_name}</option>
					</s:iterator>
				</select>
			</td>
		</tr>
		<tr>
			<td align="center"><input type="checkbox" name="_chk" id="after_location" value="location"/>存放位置</td>
			<td align="center" colspan="2">&nbsp;${book.storage_location}</td>
			<td align="center" colspan="3"><input type="text" name="storage_location" id="storage_location"/></td>
		</tr>
		<tr>
			<td align="center">其他变更</td>
			<td colspan="5"><input type="text" name="other_s" id="other_s" size="55"/></td>
		</tr>
		<tr>
			<td align="center"><font color="red">*</font>备注</td>
			<td colspan="5"><textarea id="summ" name="summ" rows="3" cols="50"></textarea></td>
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
	            <input type="submit" value="添加" class="button_2003" onclick="return chk();">&nbsp;
	            <input type="reset" value="重置" class="button_2003">
	            <input type="button" value="返回" class="button_2003" onclick="history.go(-1);">
	        </td>
	    </tr>
  	</table>
</form>
</center>
</body>
</html>