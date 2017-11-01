<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>申请计算机</title>
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
 	<script language="javascript" src="${ctx}/_script/My97DatePicker/WdatePicker.js"></script>
	<script  language="JavaScript" >
$(document).ready(function(){
	onHover();
	//preCalendarDay();
	setSeclv("seclv_code");
	selectNextApprover();
});
	//人员匹配
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

	function selectSeclv(seclv){
		if(seclv == ""){
			alert("请选择作业密级,以确认审批流程");
			$("#seclv_med").focus();
			return false;
		}else {
			selectNextApprover();
		}
	}

	function selectNextApprover(){
		
		del_all_True('next_approver_sel');
		var url = "${ctx}/basic/getnextapprover.action";
		$("#jobType").val("EVENT_SINCOM");//设置状态
	//	$("#hid_seclv_code").val($("#seclv_med").val());//密级
		
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


	function chk(){
		if($("#user_phone").val().trim() == ""){
			alert("请填申请人电话");
			$("#user_phone").focus();
			return false;
		}
	
		if($("#computer_name").val().trim() == ""){
			alert("请填写计算机名称型号");
			$("#computer_name").focus();
			return false;
		}
	
		if($("#duty_user_id").val().trim() == ""){
			alert("请输入责任人");
			$("#duty_user_name").focus();
			return false;
		}
		if($("#med_type").val().trim() == ""){
			alert("请选择设备类型[若类型为空，请联系保密管理员对计算机类配置“台式机”]");
			$("#med_type").focus();
			return false;
		}
		if($("#computer_code").val().trim() == ""){
			alert("请输入统一编号");
			$("#computer_code").focus();
			return false;
		}
	
		if($("#com_seclv_code").val().trim() == ""){
			alert("请选择计算机密级");
			$("#com_seclv_code").focus();
			return false;
		}
		if($("#hdisk_no").val().trim() == ""){
			alert("请填写硬盘序列号");
			$("#hdisk_no").focus();
			return false;
		}
		if($("#computer_os").val().trim() == ""){
			alert("请填写操作系统");
			$("#computer_os").focus();
			return false;
		}
		if($("input[name='install_time']").val()  == ""){
			alert("请填写安装日期");
			$("#install_time").focus();
			return false;
		}
	
		if(!checkCode_addword($("#computer_name").val())){
			$("#computer_name").focus();
			alert("计算机名称型号只能由数字、字母、汉字或下划线组成，长度小于30位");
			return false;
		}
		
        if($("#storage_area").val().length>0 && !checkCode_addword($("#storage_area").val())){
			$("#storage_area").focus();
			alert("请选择安装区域");
			return false;
		} 
		if($("#storage_location").val().length>0 && !checkCode_addword($("#storage_location").val())){
			$("#storage_location").focus();
			alert("放置地点只能由数字、字母、汉字或下划线组成，长度小于30位");
			return false;
		}
		if($("#event_reason").val().trim() == ""){
			alert("请填写申请原因");
			$("#event_reason").focus();
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
</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<form method="post" action="${ctx}/computer/addsinglecomputerevent.action">
	<input type="hidden" name="event_code" value="${event_code}" id="event_code"/>
	<input type="hidden" name="dept_id" id="dept_id" value="${curUser.dept_id}"/>
	<input type="hidden" name="jobType" id="jobType"/>
	<input type="hidden" name="seclv_code" id="seclv_code"/>
	<input type="hidden" name="next_approver" id="next_approver"/>
  	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="90%">
	  	<tr>
		    <td colspan="6" class="title_box">新增计算机（单机）审批表</td>
		</tr>
		<tr height="40"> 
	    	<td width="10%" align="center">申请用户 </td>
	    	<td width="23%"><font color="blue"><b>${curUser.user_name}</b></font></td>
	    	<td width="10%" align="center">用户部门 </td>
	    	<td width="23%"><font color="blue"><b>${curUser.dept_name}</b></font></td>
	    	<td align="center"><font color="red">*</font>申请人电话</td>
			<td>
				<input type="text" name="user_phone" id="user_phone"/>
			</td>
	    </tr>
	    <tr>
	    	<td align="center"><font color="red">*</font>责任人</td>
			<td >
				<input type="text" id="duty_user_name" name="duty_user_name" onkeyup="selectRecvUser(this.value);"/>
	    		<input type="hidden" id="duty_user_id" name="duty_user_id"/><br>
	    		<div id="allOptions" class="containDiv" style="position:absolute;border:0px solid black;padding:0px">
			</td>
			<td align="center"><font color="red">*</font>责任人单位</td>
		<td>
		   <select name="duty_entp_id" id="duty_entp_id">
				<option value="">--请选择--</option>
				<option value="1">总部</option>
				<option value="2">24所</option>
				<option value="3">26所</option>
				<option value="4">44所</option>
    		</select>&nbsp;	
		</td>
			<td align="center"><font color="red">*</font>设备类型 </td>
	    	<td>	
			   <select name="med_type" id="med_type" >
					<option value="">--请选择--</option>
	    			<s:iterator value="#request.infoType1" var="type">
	    				 <c:if test = "${type.info_type == '台式机'}">
						<option value="${type.info_id}">${type.info_type}</option>
						</c:if>
					</s:iterator>
	    		</select> 
			</td> 
	    </tr>
	    <tr>
	    	</td>
			<td align="center"><font color="red">*</font>计算机密级</td>
			<td>
				<select id="com_seclv_code" name="com_seclv_code">
					<option value="">--请选择--</option>
					<s:iterator value="#request.seclvList" var="item">
						<option value="${item.seclv_code}">${item.seclv_name}</option>
					</s:iterator>
				</select>
			</td>
	    	<td align="center"><font color="red">*</font>资产编号</td>
			<td>
				<input type="text" name="computer_code" id="computer_code"/>
			</td>
	    	<td align="center"><font color="red">*</font>计算机名称型号</td>
			<td>
				<input type="text" name="computer_name" id="computer_name"/>
			</td>
			</tr>
			<tr>
			<td align="center"><font color="red">*</font>硬盘序列号</td>
			<td>
				<input type="text" name="hdisk_no" id="hdisk_no"/>
			</td>
			<td align="center"><font color="red">*</font>MAC地址</td>
		    <td>
		   	     <input type="text" name="computer_mac" id="computer_mac"/>
		    </td>
		    <td align="center"><font color="red">*</font>操作系统</td>
			<td>
				<input type="text" name="computer_os" id="computer_os"/>
			</td>
	    </tr>
	    <tr>
			
			<td align="center"><font color="red">*</font>系统安装时间</td>
			<td>
               <input type="text" id="install_time" name="install_time" onclick="WdatePicker()" class="Wdate" size="15" value="${install_time}"/>
           </td>
			<td align="center">安装区域</td>
			<td>
			<select name="storage_area" id="storage_area">
				<option value="">--请选择--</option>
				<option value="西区">西区</option>
				<option value="南区">南区</option>
			</select>			
			</td>
			 <td align="center">放置地点</td>
			<td>
				<input type="text" name="storage_location" id="storage_location"/>
			</td>
		</tr>
		<tr>
			<td align="center">原保密编号</td>
			<td><input type="text" name="oldconf_code" id="oldconf_code"/></td>
			<td align="center">备注</td>
			<td colspan="3"><input type="text" name="summ" id="summ" size="60"/></td>
		</tr>
	    <tr>
	    	<td align="center"><font color="red">*</font>申请原因</td>
			<td colspan="5">
				<textarea id="event_reason" name="event_reason" rows="3" cols="80"></textarea>
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
	            <input type="submit" value="添加" class="button_2003" onclick="return chk();">&nbsp;
	            <input type="reset" value="重置" class="button_2003">
	        </td>
	    </tr>
  	</table>
</form>
</center>
</body>
</html>