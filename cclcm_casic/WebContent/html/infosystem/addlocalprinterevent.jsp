<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>保留本地打印机</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
	<link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
    <script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common_bm.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script  language="JavaScript" >
	$(document).ready(function(){
		onHover();
		setSeclv("seclv_code");
		selectNextApprover();
	});

	//根据密级设置流程
function selectNextApprover(){
	
	del_all_True('next_approver_sel');
	var url = "${ctx}/basic/getnextapprover.action";
	$("#jobType").val("EVENT_LOCALPRINTER");//设置状态
//	$("#seclv_code").val("9");//密级:非密
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
//检测必填项
function chk()
{
		if($("#user_phone").val().trim() == ""){
			alert("请填申请人电话");
			$("#user_phone").focus();
			return false;
		}
		
		if($("#printer_model").val().trim() == ""){
			alert("请填打印机型号");
			$("#printer_model").focus();
			return false;
		}
		
		if($("#conf_code").val().trim() == ""){
			alert("请填保密编号");
			$("#conf_code").focus();
			return false;
		}
		
		if($("#printer_seclv_name").val().trim() == ""){
			alert("请选择密级");
			$("#printer_seclv_name").focus();
			return false;
		}
		
		if($("#event_reason").val().trim() == ""){
			alert("请输入申请原因");
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
//alert("next_approver:"+$("#next_approver").val());
	return true;
}
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<form method="post" action="${ctx}/infosystem/addlocalprinterevent.action">
	<input type="hidden" name="event_code" value="${event_code}" id="event_code"/>
	<input type="hidden" name="computer_barcode" value="${computer_barcode}" id="computer_barcode"/>
	<input type="hidden" name="dept_id" id="dept_id" value="${curUser.dept_id}"/>
	<input type="hidden" name="jobType" id="jobType"/>
	<input type="hidden" name="seclv_code" id="seclv_code" value="6"/>
	<input type="hidden" name="next_approver" id="next_approver"/>
  	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="90%">
	  	<tr>
		    <td colspan="6" class="title_box">保留本地打印机（独立模式）审批表</td>
		</tr>
		<tr height="40"> 
	    	<td width="10%" align="center">申请用户 </td>
	    	<td width="23%"><font color="blue"><b>${curUser.user_name}</b></font></td>
	    	<td width="10%" align="center">用户部门 </td>
	    	<td width="23%"><font color="blue"><b>${curUser.dept_name}</b></font></td>
	    	<td align="center"><font color="red">*</font>申请人电话</td>
			<td align="center">
				<input type="text" name="user_phone" id="user_phone"/>
			</td>
	     </tr>
	      <tr>
	    	<td align="center">计算机名称</td>
	    	<td width="23%"><font color="blue"><b>&nbsp;${computer.computer_name}</b></font></td>
	    	<td align="center">责任人</td>
	    	<td width="23%"><font color="blue"><b>&nbsp;${computer.duty_user_name}</b></font></td>
			<td align="center">IP地址</td>
			<td width="23%"><font color="blue"><b>&nbsp;${computer.computer_ip}</b></font></td>
	    </tr>
	    <tr>
	    	<td align="center">资产编号</td>
			<td width="20%"><font color="blue"><b>&nbsp;${computer.computer_code}</b></font></td>
	    	<td align="center">保密编号</td>
			<td width="20%"><font color="blue"><b>&nbsp;${computer.conf_code}</b></font></td>
			<td align="center">密级</td>
			<td width="20%"><font color="blue"><b>&nbsp;${computer.seclv_name}</b></font></td>
	    </tr>
	    <tr><td colspan="6" align="center">打印机基本信息</td></tr>
	     <tr>
	    	<td align="center"><font color="red">*</font>打印机型号</td>
			<td>
				<input type="text" name="printer_model" id="printer_model"/>
			</td>
	    	<td align="center"><font color="red">*</font>保密编号</td>
	    	<td>
				<input type="text" name="conf_code" id="conf_code"/>
			</td>
			<td align="center"><font color="red">*</font>密级</td>
			<td align="center">
				<select id="printer_seclv_name" name="printer_seclv_name">
					<option value="">--请选择--</option>
					<s:iterator value="#request.seclvList" var="item">
						<option value="${item.seclv_name}">${item.seclv_name}</option>
					</s:iterator>
				</select>
			</td>
	    </tr>
	    <tr>
			<td align="center"><font color="red">*</font>申请原因</td>
			<td colspan="5">
				<textarea id="event_reason" name="event_reason" rows="3" cols="50"></textarea>
			</td>
		</tr>
		<tr>
			<td align="center">备注</td>
			<td colspan="5" >
				<input type="text" name="summ" id="summ" size="55"/>
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
	            <input type="button" value="返回" class="button_2003" onclick="history.go(-1);">
	        </td>
	    </tr>
  	</table>
</form>
</center>
</body>
</html>