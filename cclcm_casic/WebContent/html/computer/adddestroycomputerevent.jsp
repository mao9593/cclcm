<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>计算机报废</title>
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
	//密级选择
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
	$("#jobType").val("EVENT_DESCOM");//设置状态
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
//end

//检测必填项
function chk()
{
	if($("#user_phone").val().trim() == ""){
			alert("请填申请人电话");
			$("#user_phone").focus();
			return false;
		}
		if($("#event_reason").val().trim() == ""){
			alert("请输入变更原因");
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
<form method="post" action="${ctx}/computer/adddestroycomputerevent.action">
	<input type="hidden" name="event_code" value="${event_code}" id="event_code"/>
	<input type="hidden" name="computer_barcode" value="${computer.computer_barcode}" id="computer_barcode"/>
	<input type="hidden" name="dept_id" id="dept_id" value="${curUser.dept_id}"/>
	<input type="hidden" name="jobType" id="jobType"/>
	<input type="hidden" name="seclv_code" id="seclv_code"/>
	<input type="hidden" name="next_approver" id="next_approver"/>
  	<table class="table_box" cellspacing=0 cellpadding=0 border=1 width="90%">
	  	<tr>
		    <td colspan="6" class="title_box">计算机保密报废申请</td>
		</tr>
		<tr> 
	    	<td width="15%" align="center">申请用户 </td>
	    	<td width="18%"><font color="blue"><b>${curUser.user_name}</b></font></td>
	    	<td width="15%" align="center">用户部门 </td>
	    	<td width="18%"><font color="blue"><b>${curUser.dept_name}</b></font></td>
	    	<td width="15%" align="center"><font color="red">*</font>申请人电话</td>
			<td width="18%">
				<input type="text" name="user_phone" id="user_phone"/>
			</td>
	     </tr>
	    <tr>
		  	<td colspan="6" align="left">&nbsp;&nbsp;&nbsp;<font color="blue"><b>待报废计算机基本信息</td>
		</tr>
     <tr>
    	<td align="center">计算机名称</td>
    	<td><font color="blue"><b>&nbsp;${computer.computer_name}</b></font></td>
		<td align="center">设备类型</td>
		<td><font color="blue"><b>&nbsp;${computer.med_type_name}</b></font></td>
		<td align="center">原保密编号</td>
		<td><font color="blue"><b>&nbsp;${computer.oldconf_code}</b></font></td>
    </tr>  
    <tr>
    	<td align="center"><font color="red">*</font>密级</td>
		<td><font color="blue"><b>&nbsp;${computer.seclv_name}</b></font></td>
    	<td align="center">安全产品安装情况</td>
    	<td colspan="3"><font color="blue"><b>&nbsp;${computer.software_type}</b></font></td>
	</tr>
	<tr>
    	<td align="center"><font color="red">*</font>硬盘序列号</td>
    	<td><font color="blue"><b>&nbsp;${computer.hdisk_no}</b></font></td>
    	<td align="center"><font color="red">*</font>资产编号</td>
    	<td><font color="blue"><b>&nbsp;${computer.computer_code}</b></font></td>
		<td align="center"><font color="red">*</font>保密编号</td>
		<td><font color="blue"><b>&nbsp;${computer.conf_code}</b></font></td>
    </tr> 
    <tr>
    	<td align="center"><font color="red">*</font>责任人</td>
    	<td><font color="blue"><b>&nbsp;${computer.duty_user_name}</b></font></td>
		<td align="center"><font color="red">*</font>责任部门</td> 
		<td><font color="blue"><b>&nbsp;${computer.duty_dept_name}</b></font></td>
		<td align="center">责任单位</td>
		<td><font color="blue"><b>&nbsp;${computer.duty_entp_name}</b></font></td>
    </tr> 
    <tr>
    	<td align="center">操作系统</td>
    	<td><font color="blue"><b>&nbsp;${computer.computer_os}</b></font></td>
		<td align="center">操作系统安装时间</td>
		<td><font color="blue"><b>&nbsp;${computer.install_time_str}</b></font></td>
		<td align="center">KEY编号</td>
		<td><font color="blue"><b>&nbsp;${computer.key_code}</b></font></td>
    </tr> 
    <tr>
    	<td align="center">IP</td>
    	<td><font color="blue"><b>&nbsp;${computer.computer_ip}</b></font></td>
		<td align="center">掩码</td>
		<td><font color="blue"><b>&nbsp;${computer.mark_code}</b></font></td>
		<td align="center">MAC</td>
		<td><font color="blue"><b>&nbsp;${computer.computer_mac}</b></font></td>
	</tr> 
    
    <tr>
		<td align="center">输出端口</td>
		<td><font color="blue"><b>&nbsp;${computer.output_point}</b></font></td>
		<td align="center">VALN</td>
		<td><font color="blue"><b>&nbsp;${computer.vlan_num}</b></font></td>
		<td align="center">网关</td>
		<td><font color="blue"><b>&nbsp;${computer.computer_gateway}</b></font></td>
    </tr> 
    <tr>
		<td align="center">交换机</td>
		<td><font color="blue"><b>&nbsp;${computer.switch_num}</b></font></td>
		<td align="center">端口号</td>
		<td><font color="blue"><b>&nbsp;${computer.switch_point}</b></font></td>
		<td align="center">网络类型</td>
		<td><font color="blue"><b>&nbsp;${computer.internet_type_name}</b></font></td>
    </tr> 
     <tr>
    	<td align="center">计算机状态</td>
    	<td><font color="blue"><b>&nbsp;${computer.computer_status_name}</b></font></td>
		<td align="center">存放区域</td>
		<td><font color="blue"><b>&nbsp;${computer.storage_area}</b></font></td>
		<td align="center">存放位置</td>
		<td><font color="blue"><b>&nbsp;${computer.storage_location}</b></font></td>
	</tr> 
     <tr>
     	<td align="center">录入时间</td>
		<td><font color="blue"><b>&nbsp;${computer.enter_time_str}</b></font></td>
		<td align="center">备注</td>
		<td colspan="3"><font color="blue"><b>&nbsp;${computer.summ}</b></font></td>
    </tr> 
    <tr>
		<td align="center"><font color="red">*</font>报废原因</td>
		<td colspan="5"><textarea id="event_reason" name="event_reason" rows="3" cols="50"></textarea></td>
	</tr>
	<tr>
		<td align="center">备注</td>
		<td colspan="5"><input type="text" name="summ" id="summ" size="55"/></td>
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