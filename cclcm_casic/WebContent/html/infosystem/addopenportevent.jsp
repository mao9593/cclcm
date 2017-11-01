<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>开通计算机端口</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${ctx}/_css/css200603.css" type="text/css" media="screen" />
	<link href="${ctx}/_script/calendar2/calendar-blue.css" type="text/css" rel="stylesheet"/>
    <script language="javascript" src="${ctx}/_script/calendar2/calendar.js"></script>
	<script language="javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_common_bm.js"></script>
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
	<script language="javascript" src="${ctx}/_script/My97DatePicker/WdatePicker.js"></script>
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
		$("#jobType").val("EVENT_OPENPORT");//设置状态
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
	function chkTime(){
		if($("input[name='start_time']").val() != "" && $("input[name='end_time']").val() != ""){
			var startTimeInput = $("input[name='start_time']").val();
			var endTimeInput = $("input[name='end_time']").val();
			var startYear = startTimeInput.substring(0,4);
			var endYear = endTimeInput.substring(0,4);
			var startTime = startTimeInput.substr(5,5)+"-"+startYear+startTimeInput.substr(10);
			var endTime = endTimeInput.substr(5,5)+"-"+endYear+endTimeInput.substr(10);
			var startLong = Date.parse(startTime);
			var endLong = Date.parse(endTime);
			if(startLong != NaN && endLong != NaN && startLong > endLong){
				alert("起止时间设置不合理，请修改");
				return false;
			}
		}
		return true;
	}
	//检测必填项
	function chk()
	{
		if($("#user_phone").val().trim() == ""){
			alert("请填申请人电话");
			$("#user_phone").focus();
			return false;
		}
		if(!document.getElementById("output1").checked && !document.getElementById("output2").checked){
			$("#output1").focus();
	  		alert("请选择开通输入输出端口类型");
	  		return false;
	  	}
	  	if(document.getElementById("output2").checked){
	  		if($(":checkbox:checked[value!='']").size()==0){
				alert("请勾选要开通输入输出端口类型");
				return false;
			}
	  	}else{
	  		$("#output_chk").val("无");
	  	}
		
/* 		if($("input[name='start_time']").val() == ""){
			alert("请填开始时间");
			$("#start_time").focus();
			return false;
		}
		
		if($("input[name='end_time']").val() == ""){
			alert("请填结束时间");
			$("#end_time").focus();
			return false;
		}

		chkTime(); */
		
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
		return true;
	}
	</script>
</head>
<body oncontextmenu="self.event.returnValue=false">
<center>
<form method="post" action="${ctx}/infosystem/addopenportevent.action">
	<input type="hidden" name="event_code" value="${event_code}" id="event_code"/>
	<input type="hidden" name="computer_barcode" value="${computer_barcode}" id="computer_barcode"/>
	<input type="hidden" name="dept_id" id="dept_id" value="${curUser.dept_id}"/>
	<input type="hidden" name="jobType" id="jobType"/>
	<input type="hidden" name="seclv_code" id="seclv_code" value="6"/>
	<input type="hidden" name="next_approver" id="next_approver"/>
	<input type="hidden" name="output_chk" id="output_chk"/>
  	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="90%">
	  	<tr>
		    <td colspan="6" class="title_box">开通计算机端口审批表</td>
		</tr>
		<tr> 
	    	<td width="15%" align="center">申请用户 </td>
	    	<td width="15%"><font color="blue"><b>${curUser.user_name}</b></font></td>
	    	<td width="10%" align="center">用户部门 </td>
	    	<td width="10%"><font color="blue"><b>${curUser.dept_name}</b></font></td>
	    	<td width="10%" align="center"><font color="red">*</font>申请人电话</td>
			<td width="10%" align="center">
				<input type="text" name="user_phone" id="user_phone"/>
			</td>
	     </tr>
	      <tr>
	    	<td align="center">计算机名称</td>
	    	<td><font color="blue"><b>&nbsp;${computer.computer_name}</b></font></td>
	    	<td align="center">责任人</td>
	    	<td><font color="blue"><b>&nbsp;${computer.duty_user_name}</b></font></td>
			<td align="center">IP地址</td>
			<td><font color="blue"><b>&nbsp;${computer.computer_ip}</b></font></td>
	    </tr>
	    <tr>
	    	<td align="center">资产编号</td>
			<td><font color="blue"><b>&nbsp;${computer.computer_code}</b></font></td>
	    	<td align="center">保密编号</td>
			<td><font color="blue"><b>&nbsp;${computer.conf_code}</b></font></td>
			<td align="center">密级</td>
			<td><font color="blue"><b>&nbsp;${computer.seclv_name}</b></font></td>
	    </tr>
	    <tr>
			<td align="center">开通输入输出端口需求</td>
			<td colspan="5">
				<input type="radio" name="output" id="output1" value="无" onclick="judgeIfGray(this.value,1);">无<br>
				<input type="radio" name="output" id="output2" value="有" onclick="judgeIfGray(this.value,1);">有
				<input type="checkbox" name="output_chk" value="打印机"/>打印机
				<input type="checkbox" name="output_chk" value="扫描仪"/>扫描仪
				<input type="checkbox"  name="output_chk" value="单导盒"/>单导盒
				<input type="checkbox" name="output_chk" value="条码枪"/>条码枪
				<input type="checkbox" name="output_chk" value="USB口"/>USB口
				<input type="checkbox" name="output_chk" value="其他"/> 其他
				<input type="text" id="test1" name="output_chk" />
			</td>
		</tr>
<!-- 	    <tr>
			<td align="center">开通输入<br>端口类型</td>
			<td colspan="5">
				<input type="checkbox" name="input_chk" value="first"/>三合一单导盒
				<input type="checkbox" name="input_chk" value="second"/>只读光驱
				<input type="checkbox" name="input_chk" value="third"/>虚拟光驱
			</td>
		</tr> -->
<!-- 		 <tr>
			<td align="center"><font color="red">*</font>开通时间</td>
			<td><input type="text" name="start_time" id="start_time" onclick="WdatePicker()" class="Wdate" /></td>
			<td align="center">至</td>
			<td colspan="3"><input type="text" name="end_time" id="end_time" onclick="WdatePicker()" class="Wdate" /></td>
		</tr> -->
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