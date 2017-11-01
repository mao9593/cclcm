<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" type="text/css" href="${ctx}/_css/displaytag.css" media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/css200603.css"  media="screen"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/_css/print.css"  media="print"/>
 	<script language="javascript" src="${ctx}/_script/casic304_common.js"></script> 
	<script language="javascript" src="${ctx}/_script/casic304_ajax.js"></script>
 	<script type="text/javascript" src="${ctx}/_script/jquery/jquery.min.js"></script>
 	<script language="javascript" src="${ctx}/_script/casic304_common_bm.js"></script>
    <script language="javascript" src="${ctx}/_script/jquery.jqprint-0.3.js"></script>
    <script language="javascript" src="${ctx}/_script/My97DatePicker/WdatePicker.js"></script>
<script>
$(document).ready(function(){
	prepareBizApproval("${process.step_dept}","${process.step_role}","${process.step_dept_name}","${process.step_role_name}","${ctx}");
	if($("#next_approver_all").children().size() == 0){
		$("#selApprover1").hide();
		$("#selApprover2").hide();
	}
	$("#submit_btn").attr("disabled",false);
	if("${history}" == "Y"){
		viewOpinion("");//判断审批到哪一步
	}else{
		viewOpinion("read");//判断审批到哪一步
	}
	$("#page_print").click(function(){
		$(".printContent").jqprint();
	})
});

	function changeApproved(value){
		if(value == "true"){
			$("#opinion").val("同意");
		}else if(value == "false"){
			$("#opinion").val("不同意");
		}
	}
	
	function chk(){
		subOpinion();//提交时将审批意见复制给opinion
		if($("#opinion").val().trim() == ""){
			alert("审批意见不能为空");
			$("#opinion").focus();
			return false;
		}

		var this_step = Number($("#listSize").val()) +1;
		
		if(this_step == 6){
				if($("#key_code").val() == ""){
					alert("请填写KEY号");
					$("#key_code").focus();
					return false;
				}
			   if($("#computer_ip").val() == ""){
					alert("请填写IP地址");
					$("#computer_ip").focus();
					return false;
				}
				if($("#switch_num").val() == ""){
					alert("请填写交换机IP");
					$("#switch_num").focus();
					return false;
				}
				if($("#switch_point").val() == ""){
					alert("请填写交换机端口");
					$("#switch_point").focus();
					return false;
				}
				if($("#vlan_num").val() == ""){
					alert("请填写VLAN");
					$("#vlan_num").focus();
					return false;
				}
			   if($("#mark_code").val() == ""){
					alert("请填写掩码");
					$("#mark_code").focus();
					return false;
				}
				if($("#computer_gateway").val() == ""){
					alert("请填写网关");
					$("#computer_gateway").focus();
					return false;
				}
				if($("#hdisk_no").val() == ""){
					alert("请填写硬盘序列号");
					$("#hdisk_no").focus();
					return false;
				}
				
				if($("#computer_mac").val() == ""){
					alert("请填写MAC地址");
					$("#computer_mac").focus();
					return false;
				}
			   if($("#computer_os").val() == ""){
					alert("请填写操作系统版本");
					$("#computer_os").focus();
					return false;
				}
				if($("#install_time").val() == ""){
					alert("请填写操作系统安装时间");
					$("#install_time").focus();
					return false;
				}
				if(!document.getElementById("soft1").checked && !document.getElementById("soft2").checked){
			  		alert("请选择是否安装软件");
			  		return false;
			  	}else if(document.getElementById("soft2").checked){
			  		if($(":checkbox:checked[name='software_type'][value!='']").size()==0){
						alert("请勾选软件安装情况");
						return false;
					}
			  	}
			}
			
					if($("#next_approver_all option").size() > 0 && $("#next_approver_sel option").size() == 0 && $("#approved")[0].checked){
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
		return true;
	}

	function IsRecycle(){
		if($("#isrecycle").val() == "是"){
			$("#recycle").show();
		}else{
			$("#recycle").hide();
		}
	}
	
	function Isnewdisk(value){
		if(value == "是"){
			$("#isnew").show();
			$("#isnew1").show();
		}else{
			$("#isnew").hide();
			$("#isnew1").hide();
		}
	}
</script>
</head>
<div class="printContent">
<body oncontextmenu="self.event.returnValue=false">
<form action="${ctx}/computer/approveinternetcomputerjob.action" method="post" >
	<input type="hidden" name="next_approver" id="next_approver"/>
	<input type="hidden" name="job_code" value="${job.job_code}"/>
	<input type="hidden" id="listSize" name="listSize" value="${listSize}"/> 
	<input type="hidden" id="opinion_all" name="opinion_all" value="${opinion_all}"/>
	<input type="hidden" id="opinion" name="opinion"/>  

<table width="95%" border="1" cellspacing="0" cellpadding="0" align="center" class="table_box">

	<tr>
		<td colspan="6" class="title_box">新增计算机网络机审批表</td>
	</tr>
	<tr> 
    	<td width="14%" align="center">申请用户 </td>
    	<td width="20%"><font color="blue"><b>${event.user_name}</b></font></td>
    	<td width="13%" align="center">用户部门 </td>
    	<td width="20%"><font color="blue"><b>${event.dept_name}</b></font></td>
    	<td width="13%" align="center">当前状态 </td>
    	<td width="20%"><font color="red"><b>&nbsp;${event.job_status_name}</b></font></td> 
    </tr>
     <tr>
    	<td  align="center">申请人电话 </td>
    	<td ><font color="blue"><b>${event.user_phone}</b></font></td>
<%--     	<td  align="center">计算机品牌型号 </td>
    	<td ><font color="blue"><b>${computer.computer_name}</b></font></td>
    	<td align="center">作业密级 </td>
    	<td ><font color="blue"><b>${event.seclv_name}</td> --%>
    	<td align="center">责任人 </td>
    	<td ><font color="blue"><b>${computer.duty_user_name}</b></font></td>
    	<td align="center">责任人单位</td>
    	<td >
    	<c:if test="${computer.duty_entp_id eq '1'}"><font color="blue"><b>总部</b></font></c:if>
    	<c:if test="${computer.duty_entp_id eq '2'}"><font color="blue"><b>24所</b></font></c:if> 
    	<c:if test="${computer.duty_entp_id eq '3'}"><font color="blue"><b>26所</b></font></c:if>
    	<c:if test="${computer.duty_entp_id eq '4'}"><font color="blue"><b>44所</b></font></c:if>
    	</td>
    </tr>
    <tr>
    	<td align="center">设备密级 </td>
    	<td ><font color="blue"><b>${computer.seclv_name}</b></font></td>
        <td align="center">设备类型 </td>
    	<td ><font color="blue"><b>${computer.med_type_name}</b></font></td> 
    	</td>
    	<td align="center">接入网络 </td>
    	<td ><font color="blue"><b>${computer.internet_type_name}</b></font></td>
    </tr>
    <tr>
    	<td  align="center">计算机名称型号 </td>
    	<td ><font color="blue"><b>${computer.computer_name}</b></font></td>
        <td align="center">硬盘序列号 </td>
    	<td ><font color="blue"><b>${computer.hdisk_no}&nbsp;</b></font></td>
    	<td align="center">MAC地址 </td>
    	<td ><font color="blue"><b>${computer.computer_mac}&nbsp;</b></font></td>
    </tr>
     <tr>
    	<td  align="center">操作系统</td>
    	<td ><font color="blue"><b>${computer.computer_os}&nbsp;</b></font></td>
        <td align="center">操作系统安装时间 </td>
    	<td ><font color="blue"><b>${computer.install_time_str}&nbsp;</b></font></td>
    	<td align="center">输出端口 </td>
    	<td ><font color="blue"><b>${computer.output_point}&nbsp;</b></font>
    	</td>
    </tr>
     <tr>
    	<td align="center">安装区域 </td>
    	<td ><font color="blue"><b>${computer.storage_area}&nbsp;</b></font></td>
    	<td align="center">安装位置 </td>
    	<td ><font color="blue"><b>${computer.storage_location}&nbsp;</b></font></td>
    	<td  align="center">资产编号 </td>
    	<td ><font color="blue"><b>${computer.computer_code}&nbsp;</b></font></td>
      </tr>
      <tr>
    	<td align="center">原保密编号</td>
    	<td ><font color="blue"><b>${computer.oldconf_code}&nbsp;</b></font></td>
    	<td align="center">备注</td>
    	<td colspan="3"><font color="blue"><b>${computer.summ}&nbsp;</b></font></td>
      </tr>
      <c:if test="${history eq 'Y'}">
		<tr>
	  		<td align="center">下一步骤审批人</td>
	  		<td colspan="5"><font color="blue"><b>&nbsp;${job.next_approver_name}</b></font></td>
	  	</tr>
  	</c:if>
       <tr>
		<td align="center">申请部门领导审核</td>
			<td colspan="5" id="step1">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion1" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden1"></td></tr>
				</table>
			</td>
	</tr>
	<tr>
		<td align="center">部门保密员检查情况</td>
			<td colspan="5" id="step2">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion2" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden2"></td></tr>
				</table>
			</td>
	</tr>
	<tr>
		<td align="center">保密处审核</td>
			<td colspan="5" id="step3">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion3" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden3"></td></tr>
				</table>
			</td>
	</tr>
	<tr>
		<td align="center">信息中心领导意见</td>
			<td colspan="5" id="step4">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion4" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden4"></td></tr>
				</table>
			</td>
	</tr>
	<tr>
		<td align="center">任务分配</td>
			<td colspan="5" id="step5">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion5" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden5"></td></tr>
				</table>
			</td>
	</tr>
    <tr>
		<td align="center">信息中心处理情况</td>
			<td colspan="5" id="step6">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left" class="table_box"  readonly="readonly">
					<c:if test="${history != 'Y'}">
					<tr>
						<td width="20%" align="center"><font color="red">*</font>KEY号</td>
						<td width="30%"><input type="text" name="key_code" id="key_code" value="${computer.key_code}" /></td>
						<td width="20%" align="center"><font color="red">*</font>IP地址</td>
						<td width="30%"><input type="text" id="computer_ip" name="computer_ip" value="${computer.computer_ip}"/></td>
					</tr>
					<tr>
						<td align="center"><font color="red">*</font>交换机IP</td>
						<td><input type="text" name="switch_num" id="switch_num" value="${computer.switch_num}" /></td>
						<td align="center"><font color="red">*</font>交换机端口</td>
						<td><input type="text" id="switch_point" name="switch_point" value="${computer.switch_point}"/></td>
					</tr>
					<tr>
						<td align="center"><font color="red">*</font>VLAN</td>
						<td><input type="text" name="vlan_num" id="vlan_num" value="${computer.vlan_num}" /></td>
						<td align="center"><font color="red">*</font>掩码</td>
						<td><input type="text" id="mark_code" name="mark_code" value="${computer.mark_code}"/></td>
					</tr>
					<tr>
						<td align="center"><font color="red">*</font>网关</td>
						<td><input type="text" name="computer_gateway" id="computer_gateway" value="${computer.computer_gateway}" /></td>
						<td align="center"><font color="red">*</font>硬盘序列号</td>
						<td><input type="text" id="hdisk_no" name="hdisk_no" value="${computer.hdisk_no}"/></td>
					</tr>
					<tr>
						<td align="center"><font color="red">*</font>MAC地址</td>
						<td><input type="text" name="computer_mac" id="computer_mac" value="${computer.computer_mac}" /></td>
						<td align="center"><font color="red">*</font>操作系统版本</td>
						<td><input type="text" id="computer_os" name="computer_os" value="${computer.computer_os}"/></td>
					</tr>
					<tr>
						<td align="center"><font color="red">*</font>操作系统安装时间</td>
						<td colspan="3"><input type="text" id="install_time" name="install_time" onclick="WdatePicker()" class="Wdate" size="15"/></td>
					</tr>
					<tr>
				    	 <td align="center"><font color="red">*</font>安全软件安装情况</td>
				    	 <td colspan="3">
				    	 	<input type="radio" id="soft1" name="soft" value="无" onclick="judgeIfGray(this.value,0);">无<br>
							<input type="radio" id="soft2" name="soft" value="有" checked onclick="judgeIfGray(this.value,0);">有
				    	 	<s:iterator value="#request.infoTypeSoft5" var="type">
								<input type="checkbox" name="software_type" value="${type.info_id}" />${type.info_type}&nbsp;&nbsp;
							</s:iterator>
						</td>
					</tr>
				</c:if>
				<tr><td colspan="4"><textarea id="opinion6" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
				<tr><td colspan="4" id="hidden6"></td></tr>
			</table>
		</td>
	</tr>
	<tr>
		<td align="center">部门专、兼职保密员备案</td>
			<td colspan="5" id="step7">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion7" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden7"></td></tr>
				</table>
			</td>
	</tr>  
	<tr>
		<td align="center">信息中心备案</td>
			<td colspan="5" id="step8">
				<table width="99%" border="0" cellspacing="0" cellpadding="0" align="left">
					<tr><td><textarea id="opinion8" rows="4" cols="100" readonly="readonly"></textarea></td></tr>
					<tr><td id="hidden8"></td></tr>
				</table>
			</td>
	</tr>
		<c:if test="${history != 'Y'}">
  	<tr>
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
						<INPUT class="button_2003" onclick="add_MoreTrue('next_approver_all','next_approver_sel');" type="button" value="增加-->" /><br/>
						<br/>
						<INPUT class="button_2003" onclick="del_MoreTrue('next_approver_sel');" type="button" value="删除<--"><br/>
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
  	</c:if>
  	<tr class="Noprint">
	    <td colspan="6" align="center">
	    	<c:if test="${history != 'Y'}">
	     	<input class="button_2003" type="submit" value="提交" onclick="return chk();" id="submit_btn" disabled="disabled">&nbsp;
	     	</c:if>
			<input class="button_2003" type="button" value="返回" onClick="javascript:history.go(-1);">&nbsp;
			<c:if test="${history == 'Y'}">
			<input type="button" class="button_2003" value="打印" id="page_print" />
			</c:if>
	    </td>
  	</tr>
</table>
</form>
</body>
</div>
</html>